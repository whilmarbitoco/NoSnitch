package core;

public class Validator {

    public static boolean wordCount(String words, int count) {
        return words.split(" ").length > count;
    }
    
    public static boolean textCount(String text, int count) {
        return text.length() > count;
    }
    
    public static boolean email(String email) {
         String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    
    public static boolean password(String password) {
        return textCount(password, 5);
    }
    
}
