package cn.gzcb.export.dao;

import cn.gzcb.export.model.Customer;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiongxianwei
 * 2018/3/7
 */
public interface ExportDao {

    /**
     * 获得所有客户信息jdbctemplate
     */
    public List<Customer> getCustomer();

    /**
     * 原生jdbc分页获得customers
     * @return
     */
    public List<Customer> getCustomerJdbc(int curPage) throws FileNotFoundException;

    public List<Customer> getAll();
    /**
     * 批量插入数据
     */
    public void insertBatchCustomer() throws SQLException;

    /**
     * 获得客户总数并计算页数
     */
    public int getCustomerCount() throws SQLException;


}
