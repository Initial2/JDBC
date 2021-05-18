package dao1;

import JDBC.utils.JDBCUtils;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author initial
 * @createTime 2021/5/16  下午2:07
 * @Description 提供一个基础的数据库操纵对象
 */
public abstract class BaseDAO<T> {
    
    Class<T> clazz;
    
    /*
       获取当前类的父类的泛型。 这样可以省去我们每次指定要操作的数据表的类型。
      */ {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] arguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) arguments[0];
    }
    
    /**
     * 基础的查询方法，查询结果是一个列表,包含多条数据.
     *
     * @param conn 数据库连接
     * @param sql  需要执行的SQL语句
     * @param args 占位符的填充
     * @return 返回查询结果的集合.
     */
    public List<T> queryForList(Connection conn, String sql, Object... args) {
        ArrayList<T> list = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            
            resultSet = ps.executeQuery();
            //创建一个List，用于存放查询结果.
            list = new ArrayList<>();
            //获取查询结果的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //看看查询结果有多少列
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                //把每一行的值都放入一个对象. 一个对象对应结果集的一行数据。
                T t = clazz.getDeclaredConstructor().newInstance();
                
                //每遍历一行，就把所有列的信息添加到对应的对象中.
                for (int i = 0; i < columnCount; i++) {
                    //获取每一列的值 索引从1开始
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取这一列的名字 索引从1开始
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    
                    //通过反射，找到对应类中的对应属性. 并且修改它的值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            //最后关闭资源
            JDBCUtils.closeResource(null, ps, resultSet);
        }
        return list;
        
    }
    
    /**
     * @param conn 数据库连接
     * @param sql  需要执行的SQL语句
     * @param args 占位符的填充
     * @return 返回一条查询记录.
     * @Description 通用的查询方法
     */
    public T query(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            resultSet = ps.executeQuery();
            //获取查询结果的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //看看查询结果有多少列
            int columnCount = metaData.getColumnCount();
            T t = clazz.getDeclaredConstructor().newInstance();
            if (resultSet.next()) {
                //把所有列的信息添加到对应的对象中.
                for (int i = 0; i < columnCount; i++) {
                    //获取每一列的值 索引从1开始
                    Object columnValue = resultSet.getObject(i + 1);
                    //获取这一列的名字 索引从1开始
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //通过反射，找到对应类中的对应属性. 并且修改它的值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
            }
            return t;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            //最后关闭资源
            JDBCUtils.closeResource(null, ps, resultSet);
        }
        
        return null;
        
    }
    
    /**
     * @param conn 数据库连接
     * @param sql  需要执行的SQL语句.
     * @param args SQL语句中有几个占位符,就传进来几个参数.  长度必须保持一致.
     * @Description 一个通用的增删改操作
     */
    public void update(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            //2. 预编译
            ps = conn.prepareStatement(sql);
            //3.根据参数长度,来填充占位符
            for (int i = 0; i < args.length; i++) {
                //占位符索引从1开始!!
                ps.setObject(i + 1, args[i]);
            }
            //4.执行
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //5.关闭资源
            JDBCUtils.closeResource(null, ps);
        }
        
    }
    
    
    /**
     * 分组函数的支持。
     *
     * @param conn 数据库连接
     * @param sql  查询语句
     * @param args 占位符填充
     * @return 返回带有分组函数查询的结果。
     */
    public <E> E getCount(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
    
    
}
