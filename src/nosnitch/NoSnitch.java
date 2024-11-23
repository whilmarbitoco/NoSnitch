package nosnitch;

import controllers.LoginController;
import models.Models;
import views.LoginView;


public class NoSnitch {

    public static void main(String[] args) {
       
        LoginView loginView = new LoginView();
        new LoginController(new Models(), loginView);
        
        
    }
    
}
