package dao.test;

import JDBC.utils.JDBCUtils;
import JDBC.utils.JDBCUtils1;
import dao2.UserDAOImpl;
import org.junit.jupiter.api.Test;
import bean.User;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;


/**
 * @author initial
 * @createTime 2021/5/16  下午3:51
 * @Description
 */
class UserDAOImplTest {
    
    private final UserDAOImpl userDao = new UserDAOImpl();
    @Test
    void testInsert() {
        Connection conn = null;
        try {
            conn = JDBCUtils1.getConnection();
            User user = new User(1, "两仪落", "jaychou@126.com", new Date(999912626346L));
            userDao.insert(conn, user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    
    
    @Test
    void testDeleteById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            userDao.deleteById(conn, 11);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
        
        
    }
    
    @Test
    void testUpdateById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            User user = new User(12, "周杰伦", "jaychou@126.com", new Date(29999912626346L));
            userDao.updateById(conn, user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    
    @Test
    void testQuery() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            User query = userDao.query(conn, 10);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    
    @Test
    void testQueryAll() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<User> users = userDao.queryAll(conn);
            users.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    
    @Test
    void testCount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Long count = userDao.count(conn);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
    
    @Test
    void testGetMaxAge() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Date maxAge = userDao.getMaxAge(conn);
            System.out.println(maxAge);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}