
package core;

import models.Session;
import models.User;

public class Auth {
    
    public static Session userSession;
    public static User user;
    public static String anon;
    public static int anonId;
    
    public static void setUser(User u) {
        user = u;
        anonId = u.generateId();
        setAnon(anonId);
    }
    
    
    private static void setAnon(int id) {
        anon = "Anonymous#" + Integer.toString(id);
    }
    
    public static Session session() {
        userSession = new Session();
        return userSession;
    }
    
    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
     
        return email.matches(emailRegex);
    }
    
}
