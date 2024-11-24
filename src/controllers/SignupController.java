package controllers;

import core.Auth;
import core.Prompt;
import models.Models;
import models.User;
import views.SignUpView;


public class SignupController extends Controller<SignUpView>{

    public SignupController(Models model) {
        models = model;
        view = new SignUpView();
        
        init();
    }
    
    private void init() {
        view.setController(this);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    public void signup(String email, String username, String password, String confirmPassword) {
        
        if (!Auth.validateEmail(email)) {
            Prompt.error(view, "Invalid Email", "Sign Up Failed");            
            return;   
        }
        
        User checkUser = models.user.getByEmail(email);
        
        if (checkUser != null) {
            Prompt.error(view, "Email Already Exist", "Sign Up Failed");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            Prompt.error(view,  "Password Do Not Match", "Sign Up Failed");
            return;
        }
        
        User user = new User(email, username, password);
        
        
        models.user.add(user);
        Prompt.information(view, "Account Created", "Sign Up Success");
        
        gotoLogin();
    }
    
    public void gotoLogin() {
        new LoginController(models);
        view.dispose();
    }
}
