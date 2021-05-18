package dao2;

import bean.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author initial
 * @createTime 2021/5/17  下午9:16
 * @Description
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    
    
    @Override
    public void insert(Connection conn, User user) {
        String sql = "insert into user(name,email,birth) values(?,?,?)";
        update(conn, sql, user.getUser(), user.getEmail(), user.getBirth());
    }
    
    
    @Override
    public void deleteById(Connection conn, int id) {
        String sql = "delete from user where user.id = ?";
        update(conn, sql, id);
    }
    
    
    @Override
    public void updateById(Connection conn, User user) {
        String sql = "update user set name=?,email=?,birth=? where id=?";
        update(conn, sql, user.getUser(), user.getEmail(), user.getBirth(), user.getId());
    }
    
    
    @Override
    public User query(Connection conn, int id) {
        String sql = "select id,name,email,birth from user where id=?";
        return  getBean(conn,sql,id);
    }
    
    
    @Override
    public List<User> queryAll(Connection conn) {
        String sql = "select id,name,email,birth from user";
        return getBeanList(conn, sql);
    }
    
    
    @Override
    public Long count(Connection conn) {
        String sql = "select count(*) from user";
        return getValue(conn, sql);
    }
    
    
    @Override
    public Date getMaxAge(Connection conn) {
        String sql = "select max(birth) from user";
        return getValue(conn, sql);
    }
}

