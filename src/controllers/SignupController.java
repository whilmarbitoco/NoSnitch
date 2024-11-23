package controllers;

import javax.swing.JOptionPane;
import models.Models;
import models.Post;
import models.User;
import views.LoginView;
import views.SignUpView;


public class SignupController {
    Models models;
    SignUpView signupView;

    public SignupController(Models models, SignUpView signupView) {
        this.models = models;
        this.signupView = signupView;
        
        init();
    }
    
    public void init() {
        signupView.setController(this);
        signupView.setLocationRelativeTo(null);
        signupView.setVisible(true);
    }
    
    public void signup(String email, String username, String password, String confirmPassword) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(signupView, "Invalid Email", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            return;   
        }
        
        User checkUser = models.user.getByEmail(email);
        
        if (checkUser != null) {
            JOptionPane.showMessageDialog(signupView, "Email Already Exist", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(signupView, "Password Do Not Match", "Sign Up Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        User user = new User(email, username, password);
        
        
        models.user.add(user);
        JOptionPane.showMessageDialog(signupView, "Account Created", "Sign Up Success", JOptionPane.INFORMATION_MESSAGE);
        
        gotoLogin();
    }
    
    public void gotoLogin() {
        new LoginController(models, new LoginView());
        signupView.dispose();
    }
}
