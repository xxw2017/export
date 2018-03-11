package cn.gzcb.export.blockingqueueTest;

import cn.gzcb.export.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Consumer implements Runnable {

    private BlockingQueue<String> queue;

    private BlockingQueue<CsvWriter> csvWriterQueue;

    //读写锁保证消费多线程安全
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    public static int abc=0;

    public Consumer(BlockingQueue<String> queue,BlockingQueue<CsvWriter> csvWriterQueue) throws FileNotFoundException {
        this.queue = queue;
        this.csvWriterQueue=csvWriterQueue;
    }

    public void run() {
        System.err.println(Thread.currentThread().getName()+"启动消费者线程！");
        long start=System.currentTimeMillis();
        boolean isRunning = true;
        try {

            while (isRunning) {
                //System.out.println("正从队列获取数据...");
                String data = queue.poll(2, TimeUnit.SECONDS);
                //System.err.println(data);
                if (null != data) {
                    try {
                        abc +=1;
                        rwl.writeLock().lock();

                        System.err.println(Thread.currentThread().getName()+"正在消费第"+abc+"条数据");
                        CsvWriter ce=csvWriterQueue.take();
                        ce.writeLine(data);
                        ce.flush();
                        csvWriterQueue.put(ce);
                        rwl.writeLock().unlock();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                }
            }
            System.out.println(abc+"11111111111");
            long end=System.currentTimeMillis();
            System.err.println("用时："+(end-start)/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {

            System.err.println(Thread.currentThread().getName()+"退出消费者线程！");
        }
    }


}
