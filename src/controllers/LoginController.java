package controllers;

import core.Auth;
import core.Prompt;
import models.Models;
import models.User;
import views.LoginView;

public class LoginController extends Controller<LoginView>{
    

    public LoginController(Models model) {
        models = model;
        view = new LoginView();
        init();
    }    
    
    private void init() {
        view.setController(this);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    public void login(String email, String password) {
        
        if (!Auth.validateEmail(email)) {
            Prompt.error(view, "Invalid Email", "Login Failed");
            return;   
        }
        
        User user = models.user.getByEmail(email);
        if (user == null) {
            Prompt.error(view, "User Does Not Exist", "Login Failed");
            return;
        }
        
        if (!user.password.equals(password)) {
            Prompt.error(view, "Invalid Username or Password", "Login Failed");
            return;
        }
        Prompt.information(view, "Welcome to NoSnitch", "Login Success");
        gotoHome(user);

    }
    
    public void gotoHome(User user) {
        Auth.setUser(user);
        new HomeController(models);
        view.dispose();
    }

    public void gotoSignUp() {
        new SignupController(models);
        view.dispose();
    }
}
