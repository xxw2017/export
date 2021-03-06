package cn.gzcb.export.blockingqueueTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.*;

public class BlockingQueueTest {


    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        long start=System.currentTimeMillis();
        // 声明一个容量为10的缓存队列
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();



        // 借助Executors
        //ExecutorService service = Executors.newCachedThreadPool();
        LinkedBlockingQueue<Runnable> threadQueue = new LinkedBlockingQueue<>(10);
        ThreadFactory nameThread=new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
        // 借助Executors
        ExecutorService service = new ThreadPoolExecutor(
                10,
                100,
                100L,
                TimeUnit.SECONDS,
                threadQueue,
                nameThread
        );

        Producer producer1 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        //Producer producer2 = new Producer(queue);
        //Producer producer3 = new Producer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);
        // 启动线程
        service.execute(producer1);
        //service.execute(producer2);
        //.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);

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
