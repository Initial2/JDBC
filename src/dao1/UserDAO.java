package dao1;

import bean.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author initial
 * @createTime 2021/5/16  下午2:12
 * @Description  user表的基础操作类
 */
public interface UserDAO {
    
    
    /**
     * 向User表中1插入一条数据。
     * @param conn 数据库连接
     * @param user 要插入的数据对象
     */
    void insert(Connection conn, User user);
    
    /**
     * 根据提供的ID，删除指定的数据。
     * @param conn 数据库连接
     * @param id 指定的ID
     */
    void deleteById(Connection conn,int id);
    
    /**
     * 更新传入的用户在数据库中对应的id的数据。
     * @param conn   数据库连接
     * @param user  需要更新的数据对象
     */
    void updateById(Connection conn,User user);
    
    
    /**
     * 根据指定的ID，查询在数据库中的数据
     * @param conn 数据库连接
     * @param id  指定的ID
     * @return  返回对应的数据,用User类的对象来存放。
     */
    User  query(Connection conn, int id);
    
    /**
     * 查询数据表中的所有数据
     * @param conn 数据库连接
     * @return 返回一个集合，里面包含数据表中的所有记录
     */
    List<User> queryAll(Connection conn);
    
    /**
     * 查询表中有多少条记录
     * @param conn 数据库连接
     * @return 返回表中记录的个数
     */
    Long count(Connection conn);
    
    
    
    /**
     * 返回数据表中，年龄最大的一条记录
     * @param conn 数据库连接
     * @return  返回一个user对象，此对象为数据表中，年龄最大的一条记录。
     */
    Date getMaxAge(Connection conn);
    
    
}
