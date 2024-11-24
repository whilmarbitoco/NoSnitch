package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Session extends Model<Session> implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public User user;

    public Session() {
        super();
        modelName = "Session";
        load();
    }

    public Session(User user) {
        this.user = user;
        date = LocalDate.now();
    }
    
    public void createSession(User user) {
        if (!model.isEmpty()) {
            deleteSession();
        }
        
        add(new Session(user));
    }
    
    public void deleteSession() {
        model.clear();
        save();
    }
    
    public Session getSession() {
        if (model.isEmpty()) {
            return null;
        }
       return model.getFirst();
    }
}
