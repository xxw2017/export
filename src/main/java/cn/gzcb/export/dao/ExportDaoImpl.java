package cn.gzcb.export.dao;

import cn.gzcb.export.model.Customer;
import cn.gzcb.export.utils.JdbcUtil;
import cn.gzcb.export.utils.NumProductUtil;
import cn.gzcb.export.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xiongxianwei
 * 2018/3/7
 */
@Repository
public class ExportDaoImpl implements ExportDao{

    /**
     * JdbcTemplate注入实例
     */
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    Connection connection=null;
    PreparedStatement ps=null;
    ResultSet rs=null;


    @Override
    public List<Customer> getCustomer() {
        String sql = "SELECT * FROM t_test_customer";

        List<Customer> customers  = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(Customer.class));

        return customers;
    }

    @Override
    public void insertBatchCustomer() {

        try {
            StringBuffer sb=new StringBuffer();
            sb.append("customer_id,");
            sb.append("cust_name,");
            //sb.append("cust_sex,");
            //sb.append("cust_age,");
            //sb.append("cust_birthday,");
            //sb.append("cust_merry,");
            //sb.append("cust_id_type,");
            sb.append("cust_id_no,");
            //sb.append("is_manage_owner,");
            //sb.append("project_code,");
            //sb.append("special_code,");
            //sb.append("profession_code,");
            //sb.append("branch_office,");
            //sb.append("cust_manager_id,");
            //sb.append("cust_manager_name,");
            //sb.append("getCust_manager_grade,");
            //sb.append("has_children,");
            //sb.append("census_register,");
            sb.append("phone1,");
            //sb.append("phone2,");
            //sb.append("pbc_phone,");
            //sb.append("company_phone1,");
            //sb.append("company_phone2,");
            //sb.append("home_phone1,");
            //sb.append("company_name1,");
            //sb.append("company_name2,");
            sb.append("company_addr1,");
            //sb.append("company_addr2,");
            //sb.append("home_addr1,");
            //sb.append("home_addr2,");
            sb.append("created_time,");
            //sb.append("created_by,");
            sb.append("updated_time");
            //sb.append("update_by)");
            String sql ="insert into t_test_customer("+sb.toString()+") values(?,?,?,?,?,?,?)";
            Connection connection = JdbcUtil.getConnection();
            ps= connection.prepareStatement(sql);
            for (int i = 0; i < 10000; i++) {
                ps.setInt(1, (i + 1));
                ps.setString(2, "name" + i);
                ps.setString(3, NumProductUtil.createData(18));
                ps.setString(4,NumProductUtil.createData(11));
                ps.setString(5,"广东省广州市天河区天河南二路广州银行");
                ps.setString(6, TimeUtils.getCurDateFormat());
                ps.setString(7, TimeUtils.getCurDateFormat());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.free(null,ps,connection);
        }

    }
}
