package cn.gzcb.export.blockingqueueTest;

import cn.gzcb.export.dao.ExportDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xiongxianwei
 * 2018/3/7
 */
public class SqlTest {
    @Test
    public void insert(){
        ExportDaoImpl exportDao=new ExportDaoImpl();
        exportDao.insertBatchCustomer();
    }

    @Test
    public void getAll(){
        ExportDaoImpl exportDao=new ExportDaoImpl();
        exportDao.getCustomer();
    }
}
