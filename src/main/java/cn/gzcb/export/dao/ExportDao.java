package cn.gzcb.export.dao;

import cn.gzcb.export.model.Customer;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xiongxianwei
 * 2018/3/7
 */
public interface ExportDao {

    /**
     * 获得所有客户信息
     */
    public List<Customer> getCustomer();

    /**
     * 批量插入数据
     */
    public void insertBatchCustomer() throws SQLException;
}
