package cn.gzcb.export.utils;

import cn.gzcb.export.common.constant.ExportConstant;

import java.sql.*;

/**
 * @author xxw
 */
public class JdbcUtil {


    //1.静态代码块，类加载时就加载当前数据库驱动
    static{
        try {
            Class.forName(ExportConstant.DRIVER); //1.加载驱动
        }catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败");
            e.printStackTrace();
        }
    }
    private static Connection conn;

    private JdbcUtil(){

    }

    /**
     * 2.单例获取连接
     * @return
     */
    public synchronized static Connection getConnection(){
        try {
                if(conn==null){
                    //通过驱动管理类获得数据库连接
                    conn = DriverManager.getConnection(
                            ExportConstant.URL,
                            ExportConstant.USERNAME,
                            ExportConstant.PSW
                    );
                }
        }catch (SQLException e) {
            System.out.println("获得数据库连接失败");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 3.释放资源
     */
    public static void free(ResultSet rs, Statement ps, Connection connection){
        try {
            if(rs!=null){
                rs.close();//关闭结果集
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(ps!=null){
                    ps.close();//关闭statement会话
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                try{
                    if(connection!=null){
                        connection.close();//关闭连接
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}


