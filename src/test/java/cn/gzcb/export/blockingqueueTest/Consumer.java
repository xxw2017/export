package cn.gzcb.export.blockingqueueTest;

import cn.gzcb.export.utils.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private BlockingQueue<String> queue;
    File file = FileUtils.getFile();
    CsvWriter cw = new CsvWriter(new PrintWriter(file));


    public Consumer(BlockingQueue<String> queue) throws FileNotFoundException {
        this.queue = queue;
    }

    public void run() {
        System.out.println("启动消费者线程！");
        boolean isRunning = true;
        try {
            while (isRunning) {
                //System.out.println("正从队列获取数据...");
                String data = queue.poll(2, TimeUnit.SECONDS);
                System.err.println(data);
                if (null != data) {
                    //System.err.println("正在消费数据：" + data);
                    try {
                        cw.writeLine(data);
                        cw.flush();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出消费者线程！");
        }
    }


}
