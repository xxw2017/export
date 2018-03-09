package cn.gzcb.export.blockingqueueTest;

import cn.gzcb.export.ExportApplication;
import cn.gzcb.export.dao.ExportDao;
import cn.gzcb.export.dao.ExportDaoImpl;
import cn.gzcb.export.model.Customer;
import cn.gzcb.export.service.ExportServiceImpl;
import cn.gzcb.export.utils.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author xiongxianwei
 * 2018/3/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(ExportApplication.class)
public class SqlTest {

    /*@Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;*/

   /* @Autowired
    private ExportDao exportDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;*/
    @Test
    public void insert(){
        ExportDaoImpl exportDao=new ExportDaoImpl();
        long start=System.currentTimeMillis();
        exportDao.insertBatchCustomer();
        long end=System.currentTimeMillis();
        System.err.println("插入用时："+(end-start)/1000);
    }


    @Test
    public void getAll(){
        long start=System.currentTimeMillis();
        ExportDaoImpl exportDao=new ExportDaoImpl();
        List<Customer> list=exportDao.getAll();
        long end=System.currentTimeMillis();
        System.err.println("查询用时："+(end-start)/1000);
    }

    @Test
    public void getCustomerJdbc(){
        long start=System.currentTimeMillis();
        ExportDaoImpl exportDao=new ExportDaoImpl();
        List<Customer> list=exportDao.getCustomerJdbc(1);
        long end=System.currentTimeMillis();
        System.err.println("查询用时："+(end-start)/1000);
    }
}
