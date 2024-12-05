
package core;

import models.Session;
import models.User;

public class Auth {
    
    public static User user;
    public static String anon;
    public static int anonId;
    
    public static void setUser(User u) {
        user = u;
        anonId = (int) (Math.random() * Integer.MAX_VALUE) % 3999;
        setAnon(anonId);
    }
    
    
    private static void setAnon(int id) {
        anon = "Anonymous#" + Integer.toString(id);
    }
    
    public static Session session() {
       return new Session();
    }
}
