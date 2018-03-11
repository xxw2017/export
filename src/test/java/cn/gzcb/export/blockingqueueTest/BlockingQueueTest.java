package cn.gzcb.export.blockingqueueTest;

import cn.gzcb.export.utils.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.*;

public class BlockingQueueTest {



    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        long start=System.currentTimeMillis();
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(100);
        BlockingQueue<CsvWriter> CsvWriterQueue = new ArrayBlockingQueue<CsvWriter>(10);

        for (int i=0;i<10;i++){
            File file = FileUtils.getFile(""+i+".csv");
            CsvWriter cw = new CsvWriter(new PrintWriter(file));
            CsvWriterQueue.put(cw);
        }

        Producer producer1 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue,CsvWriterQueue);
        Consumer consumer2 = new Consumer(queue,CsvWriterQueue);
        Consumer consumer3 = new Consumer(queue,CsvWriterQueue);
        Consumer consumer4 = new Consumer(queue,CsvWriterQueue);
        Consumer consumer5 = new Consumer(queue,CsvWriterQueue);

        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        //service.execute(producer2);
        //.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);
        service.execute(consumer4);
        service.execute(consumer5);

        // 执行10s
       /* Thread.sleep(10 * 1000);
        producer1.stop();*/


        //producer2.stop();
        //producer3.stop();

        //Thread.sleep(2000);
        // 退出Executor
        service.shutdown();
        long end=System.currentTimeMillis();
        System.err.println("主线程用时："+(end-start)/1000);
    }

    /**
     * 根据指定长度生成纯数字的随机数
     */
    @Test
    public void createData() {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();
        for(int i=0;i<18;i++)
        {
            sb.append(rand.nextInt(10));
        }
        String data=sb.toString();
        System.err.println(data);
    }
}
