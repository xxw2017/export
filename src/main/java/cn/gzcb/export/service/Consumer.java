package cn.gzcb.export.service;

import cn.gzcb.export.utils.FileUtils;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Consumer implements Runnable {

    private BlockingQueue<String> queue;
    /**
     * 读写锁保证消费多线程安全
     */
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    public static int abc=0;


    public Consumer(BlockingQueue<String> queue) throws FileNotFoundException {
        this.queue = queue;
    }

    @Override
    public void run() {
        File file = FileUtils.getFile(Thread.currentThread().getName());
        CsvWriter cw = null;

        System.err.println(Thread.currentThread().getName()+"启动消费者线程！");
        long start=System.currentTimeMillis();
        boolean isRunning = true;
        try {
            cw = new CsvWriter(new PrintWriter(file));
            while (isRunning) {
                //System.out.println("正从队列获取数据...");
                //String data=queue.take();
                String data = queue.poll(10, TimeUnit.SECONDS);
                //System.err.println(data);
                if (null != data) {
                    try {
                        rwl.writeLock().lock();
                        abc +=1;
                        //System.err.println(Thread.currentThread().getName()+"正在消费第"+abc+"条数据");
                        cw.writeLine(data);
                        cw.flush();
                        rwl.writeLock().unlock();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                }

            }

            long end=System.currentTimeMillis();
            System.err.println("用时："+(end-start)/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                cw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName()+"退出消费者线程！");
        }
    }


}
