package JDBC.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 使用DB-Utils工具类来实现资源的关闭
 * 以及使用Druid来创建数据库连接池
 *
 * @author initial
 * @createTime 2021/5/17  下午9:04
 * @Description
 */
public class JDBCUtils1 {
    
    /**
     * 保证所有连接都是从一个连接池获取.
     */
    private static DataSource dataSource;
    
    static {
        try {
            //通过加载Properties配置文件来创建数据库连接池
            Properties pros = new Properties();
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("druid.properties");
            pros.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 从数据库连接池中获取一个连接。
     *
     * @return 返回一个从连接池中取出的连接
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    
    /**
     * 关闭数据库连接
     * @param conn  需要关闭的数据库连接
     */
    public static void closeResource(Connection conn){
        DbUtils.closeQuietly(conn);
       
    }
    
    /**
     * 关闭数据库资源
     * @param conn  需要关闭的数据库连接
     * @param stmt  需要关闭的Statement对象
     * @param rs   需要关闭的结果集
     */
    public static void closeResources(Connection conn, Statement stmt , ResultSet rs){
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(stmt);
        DbUtils.closeQuietly(rs);
    }
    
}
