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

    //读写锁保证消费多线程安全
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    public AtomicInteger abc =new AtomicInteger(0);

    File file = FileUtils.getFile();
    CsvWriter cw = new CsvWriter(new PrintWriter(file));


    public Consumer(BlockingQueue<String> queue) throws FileNotFoundException {
        this.queue = queue;
    }

    public void run() {
        //
        System.err.println(Thread.currentThread().getName()+"启动消费者线程！");
        long start=System.currentTimeMillis();
        boolean isRunning = true;
        try {
            while (isRunning) {
                //System.out.println("正从队列获取数据...");
                String data = queue.poll(2, TimeUnit.SECONDS);
                //System.err.println(data);
                if (null != data) {

                    int num= abc.incrementAndGet();
                    System.err.println(Thread.currentThread().getName()+"正在消费第"+num+"条数据");
                    //System.err.println("正在消费数据：" + data);
                    try {

                        cw.writeLine(data);
                        cw.flush();
                        rwl.writeLock().unlock();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    /*System.out.println("拿到数据：" + data);
                    */
                    //Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));

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
        } finally {
            //rwl.writeLock().unlock();
            System.err.println(Thread.currentThread().getName()+"退出消费者线程！");
        }
    }


}
