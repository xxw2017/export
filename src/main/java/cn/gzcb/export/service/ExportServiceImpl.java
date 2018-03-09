package cn.gzcb.export.service;

import cn.gzcb.export.dao.ExportDao;
import cn.gzcb.export.model.Customer;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.*;


/**
 * @author xiongxianwei
 * 2018/3/9
 */
public class ExportServiceImpl implements ExportService {
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
    public void exportCustomers() throws FileNotFoundException {
        long start=System.currentTimeMillis();
        // 声明一个容量为10的缓存队列
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        LinkedBlockingQueue<Runnable> threadQueue = new LinkedBlockingQueue<>();

        //Producer producer1 = new Producer(queue);



        Consumer consumer1 = new Consumer(queue);
        //Producer producer2 = new Producer(queue);
        //Producer producer3 = new Producer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        ThreadFactory nameThread=new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
        // 借助Executors
        ExecutorService service = new ThreadPoolExecutor(
                0,
                100,
                100L,
                TimeUnit.SECONDS,
                threadQueue,
                nameThread
        );

        // 启动线程
        //service.execute(producer1);
        //service.execute(producer2);
        //.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);

        // 执行10s
       /* Thread.sleep(10 * 1000);
        producer1.stop();*/
        // 退出Executor
        service.shutdown();
        long end=System.currentTimeMillis();
        System.err.println("主线程用时："+(end-start)/1000);
    }
}
