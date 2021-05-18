package JDBC.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 简单的一个JDBC工具类
 * @createTime 2021/5/17
 * @author initial
 */
public class JDBCUtils {
    
    /**
     * @Description  获取数据库连接
     * @return  返回一个数据库连接
     */
     public  static Connection  getConnection(){
         Connection conn = null;
         try {
             //1.加载配置文件,获取链接的信息
             InputStream is = ClassLoader.getSystemResourceAsStream("JDBC.properties");
             //2.读取配置文件中的连接信息
             Properties properties = new Properties();
             properties.load(is);
             String user = properties.getProperty("user");
             String password = properties.getProperty("password");
             String url = properties.getProperty("url");
             String className = properties.getProperty("className");
             //3.加载数据库驱动
             Class.forName(className);
             //4. 建立数据库连接
             conn = DriverManager.getConnection(url, user, password);
         } catch (IOException | ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
         return conn;
     }
    
    
    /**
     * @Description  关闭数据库资源
     * @param conn  需要关闭的连接
     * @param ps  需要关闭的Statement
     */
     public static  void  closeResource(Connection conn, Statement ps){
         if (ps != null) {
             try {
                 ps.close();
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         }
    
         if (conn != null) {
             try {
                 conn.close();
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         }
     }
    
    
    /**
     * @Description  关闭数据库资源
     * @param conn  需要关闭的连接
     * @param ps  需要关闭的Statement
     * @param resultSet  需要关闭的结果集
     */
    public static  void  closeResource(Connection conn, Statement ps, ResultSet resultSet){
        
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        
        
    }
}
