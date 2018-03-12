package cn.gzcb.export.service;

import cn.gzcb.export.common.constant.ExportConstant;
import cn.gzcb.export.dao.ExportDao;
import cn.gzcb.export.dao.ExportDaoImpl;
import cn.gzcb.export.model.Customer;
import cn.gzcb.export.strategypattern.Service;
import cn.gzcb.export.strategypattern.common.Parameter;
import cn.gzcb.export.utils.JdbcUtil;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class Producer implements Runnable{


    Connection connection=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    private LinkedBlockingQueue queue;
    private Service service;

    public Producer(LinkedBlockingQueue queue, List<Parameter> parameters) {
        this.queue = queue;
        service=new Service(parameters);
    }
    @Override
    public void run() {
        int abc=0;
        System.err.println(Thread.currentThread().getName()+"启动生产者线程！");
        ExportDao exportDao=new ExportDaoImpl();
        String threadName=Thread.currentThread().getName();
        String curPage=threadName.substring(threadName.length()-1,threadName.length());
        int page=Integer.parseInt(curPage);

        long start=System.currentTimeMillis();
        try {
            List<Customer> customers=exportDao.getCustomerJdbc(page);
            if(customers!=null){
                for(int i=0;i<customers.size();i++) {
                    abc++;
                    service.doSomething(customers.get(i));
                    if (!queue.offer(customers.get(i).toString(), 2, TimeUnit.SECONDS)) {
                        System.err.println("放入数据失败：" + customers.get(i).toString());
                    }
                }
                System.err.println(Thread.currentThread().getName()+"生产了"+abc+"条数据！");
                Thread.currentThread().interrupt();
            }else{
                System.err.println("生产者获取数据失败！");
            }
            long end=System.currentTimeMillis();
            System.err.println("生产者用时："+(end-start)/1000);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.err.println(Thread.currentThread().getName()+"退出生产者线程！");
        }
    }
/*
    public void stop() {
        isRunning = false;
    }*/



}
