package nosnitch;

import controllers.LoginController;
import models.Models;

public class NoSnitch {

    public static void main(String[] args) {
       
        new LoginController(new Models());
        
    }
    
}
