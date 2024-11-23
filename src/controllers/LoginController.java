package controllers;

import javax.swing.JOptionPane;
import models.Models;
import models.Post;
import models.User;
import views.HomeView;
import views.LoginView;
import views.SignUpView;

public class LoginController {
    
    Models models;
    LoginView loginView;

    public LoginController(Models models, LoginView loginView) {
        this.models = models;
        this.loginView = loginView;
        init();
    }    
    
    public void init() {
        loginView.setController(this);
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
    }
    
    public void login(String email, String password) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(loginView, "Invalid Email", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;   
        }
        
        User user = models.user.getByEmail(email);
        if (user == null) {
            JOptionPane.showMessageDialog(loginView, "User Does Not Exist", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!user.password.equals(password)) {
            JOptionPane.showMessageDialog(loginView, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        gotoHome(user);

    }
    
    public void gotoHome(User user) {
        new HomeController(models, user);
        loginView.dispose();
    }

    public void gotoSignUp() {
        new SignupController(models, new SignUpView());
        loginView.dispose();
    }
}
