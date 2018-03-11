package cn.gzcb.export.common.constant;

/**
 * @author xiongxianwei
 * 2018/3/6
 */
public class ExportConstant {

    /**
     * 每页显示数
     */
    public static final int PAGE_SIZE=15;

    /**
     * 每个线程分页查询数量
     */
    public static final int PER_THREAD_SIZE=100000;

    /**
     * 最大导出数据量
     */
    public static final int MAX_EXPORT_COUNT=100000;

    /**
     * 导出文件路径
     */
    public static final String FILE_PATH="D:\\test\\";

    /**
     * 导出文件名
     */
    public static final String FILENAME=".csv";

    /**
     * jdbc连接数据库连接
     */
    public static final String URL="jdbc:mysql://localhost:3306/export";
    public static final String USERNAME="root";
    public static final String PSW="root";
    public static final String DRIVER="com.mysql.jdbc.Driver";
}
