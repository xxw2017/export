package cn.gzcb.export.service;

import cn.gzcb.export.common.constant.ExportConstant;
import cn.gzcb.export.dao.ExportDao;
import cn.gzcb.export.model.Customer;
import cn.gzcb.export.strategypattern.common.Parameter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author xiongxianwei
 * 2018/3/9
 */
@Service
public class ExportServiceImpl implements ExportService {

    LinkedBlockingQueue<Runnable> threadQueue = new LinkedBlockingQueue<>(10);

    ThreadFactory nameThread=new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
    ExecutorService service = new ThreadPoolExecutor(
            10,
            100,
            100L,
            TimeUnit.SECONDS,
            threadQueue,
            nameThread
    );

    @Autowired
    private ExportDao exportDao;
    @Override
    public List<Customer> getCustomer() {
        return null;
    }

    @Override
    public List<Customer> getCustomerJdbc(int curPage) throws FileNotFoundException {
        List<Customer> customers=exportDao.getCustomerJdbc(curPage);
        return customers;
    }

    @Override
    public int getCustomerCount() throws SQLException {
        int count=exportDao.getCustomerCount();
        return count;
    }


    @Override
    public void insertBatchCustomer() throws SQLException {

    }

    @Override
    public void exportCustomers(){
       /* long start=System.currentTimeMillis();
        // 声明一个linkedBlockingQueue缓存队列
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
       *//*Producer producer3 = new Producer(queue);
        Producer producer4 = new Producer(queue);
        Producer producer5 = new Producer(queue);*//*
        try {
            for(int i=1;i<= ExportConstant.PRODUCER_THREAD_SIZE;i++){
                service.execute(new Producer(queue));
            }

            //睡15秒
            Thread.sleep(15 * 1000);
            for(int i=1;i<= ExportConstant.CONSUMER_THREAD_SIZE;i++){
                service.execute(new Consumer(queue));
            }
        //Consumer consumer2 = new Consumer(queue);
        //Consumer consumer3 = new Consumer(queue);
        // 启动线程
        //service.execute(consumer1);
        //service.execute(consumer2);
        //service.execute(consumer3);

        service.shutdown();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(service.isTerminated()) {
                long end = System.currentTimeMillis();
                System.err.println("主线程用时：" + (end - start) / 1000);
            }
        }*/


    }

    @Override
    public void exportCustomers(List<Parameter> parameters) throws FileNotFoundException, InterruptedException {
        long start=System.currentTimeMillis();
        // 声明一个linkedBlockingQueue缓存队列
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
       /*Producer producer3 = new Producer(queue);
        Producer producer4 = new Producer(queue);
        Producer producer5 = new Producer(queue);*/
        try {
            for(int i=1;i<= ExportConstant.PRODUCER_THREAD_SIZE;i++){
                service.execute(new Producer(queue,parameters));
            }

            //睡15秒
            Thread.sleep(15 * 1000);
            for(int i=1;i<= ExportConstant.CONSUMER_THREAD_SIZE;i++){
                service.execute(new Consumer(queue));
            }
            //Consumer consumer2 = new Consumer(queue);
            //Consumer consumer3 = new Consumer(queue);
            // 启动线程
            //service.execute(consumer1);
            //service.execute(consumer2);
            //service.execute(consumer3);

            service.shutdown();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(service.isTerminated()) {
                long end = System.currentTimeMillis();
                System.err.println("主线程用时：" + (end - start) / 1000);
            }
        }

    }
}
