package cn.gzcb.export;

import cn.gzcb.export.dao.ExportDaoImpl;
import cn.gzcb.export.model.Customer;
import cn.gzcb.export.service.ExportServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExportApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Test
	public void contextLoads() {

	}
	@Test
	public void getCustomers(){
		long start=System.currentTimeMillis();
		String sql = "SELECT * FROM t_test_customer";

		List<Customer> customers  = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper(Customer.class));

		long end=System.currentTimeMillis();
		System.err.println("查询用时："+(end-start)/1000);
	}

	@Test
	public void getCustomerLimit() throws FileNotFoundException {
		long start=System.currentTimeMillis();
		ExportDaoImpl service=new ExportDaoImpl();
		List<Customer> list=service.getCustomerJdbc(1);
		long end=System.currentTimeMillis();
		System.err.println("查询用时："+(end-start)/1000);
	}
}
