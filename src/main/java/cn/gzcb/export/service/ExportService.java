package cn.gzcb.export.service;

import cn.gzcb.export.model.Customer;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiongxianwei
 * 2018/3/9
 */
public interface ExportService {
    /**
     * 获得所有客户信息jdbctemplate
     */
    public List<Customer> getCustomer();

    /**
     * 原生jdbc分页获得数据
     * @return
     */
    public List<Customer> getCustomerJdbc(int curPage) throws FileNotFoundException;

    /**
     * 获得客户总数并计算页数
     */
    public int getCustomerCount() throws SQLException;

    /**
     * 批量插入数据
     */
    public void insertBatchCustomer() throws SQLException;

    public void exportCustomers() throws FileNotFoundException;
}
