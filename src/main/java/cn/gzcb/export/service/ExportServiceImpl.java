package cn.gzcb.export.service;

import cn.gzcb.export.dao.ExportDao;
import cn.gzcb.export.model.Customer;
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
    // 借助Executors
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
    public void exportCustomers()  {
        long start=System.currentTimeMillis();

        // 声明一个容量为10的缓存队列
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
       /*
        Producer producer3 = new Producer(queue);
        Producer producer4 = new Producer(queue);
        Producer producer5 = new Producer(queue);*/

        Consumer consumer1 = null;
        try {
            consumer1 = new Consumer(queue);

        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);

        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        /*service.execute(producer3);
        service.execute(producer4);
        service.execute(producer5);*/

        Thread.sleep(15 * 1000);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);

        // 执行10s
       /*
        producer1.stop();*/
        // 退出Executor
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
