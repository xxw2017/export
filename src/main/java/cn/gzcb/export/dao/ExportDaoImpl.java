package cn.gzcb.export.dao;

import cn.gzcb.export.common.constant.ExportConstant;
import cn.gzcb.export.model.Customer;
import cn.gzcb.export.utils.JdbcUtil;
import cn.gzcb.export.utils.NumProductUtil;
import cn.gzcb.export.utils.SpringUtil;
import cn.gzcb.export.utils.TimeUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Customer> getCustomerJdbc(int curPage) {
        List<Customer> list=new ArrayList<>();
        String sql=
                "select customer_id,cust_name,cust_id_no,phone1,company_addr1,created_time,updated_time from t_test_customerlimit ?,?";

        connection = JdbcUtil.getConnection();
        try {
            ps= connection.prepareStatement(sql);
            ps.setInt(1,(curPage-1)* ExportConstant.PAGE_SIZE);
            ps.setInt(2,ExportConstant.PAGE_SIZE);
            rs=ps.executeQuery();
            while (rs.next()){
                Customer cust=new Customer();
                cust.setCustomer_id(rs.getInt("customer_id"));
                cust.setCust_name(rs.getString("cust_name"));
                cust.setCust_id_no(rs.getString("cust_id_no"));
                cust.setPhone1(rs.getString("phone1"));
                cust.setCompany_addr1(rs.getString("company_addr1"));
                cust.setCreated_time(rs.getString("created_time"));
                cust.setUpdated_time(rs.getString("updated_time"));
                list.add(cust);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.free(rs,ps,connection);
        }
        return list;
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
            connection = JdbcUtil.getConnection();
            ps= connection.prepareStatement(sql);
            for (int i = 10000; i < 1000000; i++) {
                ps.setInt(1, (i + 1));
                ps.setString(2, "testname" + i);
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

    @Override
    public int getCustomerCount(){
        int count=0;
        String sql="SELECT count(customer_id) FROM t_test_customer";
        connection=JdbcUtil.getConnection();
        try {
            ps=connection.prepareStatement(sql);

            rs=ps.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.free(rs,ps,connection);
        }
        return count;
    }
}
