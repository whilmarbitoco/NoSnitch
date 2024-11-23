package models;

import java.io.Serializable;


public class User extends Model<User> implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public String email;
    public String username;
    public String password;
    
    public User() {
        super();
        modelName = "User";
        load();
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
    public User getByEmail(String email) {
        for (User user : model) {
            if (user.email.equals(email)) {
                return user;
            }
        }
        
        return null;
    }
    
}
