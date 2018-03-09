package cn.gzcb.export.service;

import cn.gzcb.export.common.constant.ExportConstant;
import cn.gzcb.export.model.Customer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable{
    private volatile boolean isRunning = true;
    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {

        System.err.println(Thread.currentThread().getName()+"启动生产者线程！");
        long start=System.currentTimeMillis();
        try {

            while (isRunning) {
                //System.out.println("正在生产数据...");
                for (int i = 0; i< ExportConstant.MAX_EXPORT_COUNT; i++){
                    Customer customer = new Customer();
                    customer.setCustomer_id(i);
                    customer.setCust_age(18);
                    customer.setCust_name("name " +i);
                    customer.setCust_sex(i%2==0 ?"boy":"girl");
                    if (!queue.offer(customer.toString(), 2, TimeUnit.SECONDS)) {
                        System.err.println("放入数据失败：" + customer.toString());
                    }
                }
                stop();
                //data = "data:" + count.incrementAndGet();
                //System.out.println("将数据：" + customers + "放入队列...");
                long end=System.currentTimeMillis();
                System.err.println("生产者用时："+(end-start)/1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.err.println(Thread.currentThread().getName()+"退出生产者线程！");
        }
    }

    public void stop() {
        isRunning = false;
    }


}
