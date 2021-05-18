package bean;

import java.sql.Date;

/**
 *
 * User类对应数据库中的User表
 *
 * @author initial
 * @createTime 2021/5/13  下午8:06
 * @Description
 */
public class User {
    
    private int id;
    private String name;
    private String email;
    private Date birth;
    
    
    
    
    
    public User() {
    }
    
    public User(int id, String user, String email, Date date) {
        this.id = id;
        this.name = user;
        this.email = email;
        this.birth = date;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUser() {
        return name;
    }
    
    public void setUser(String user) {
        this.name = user;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getBirth() {
        return birth;
    }
    
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
