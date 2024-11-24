package controllers;

import core.Auth;
import core.Prompt;
import javax.swing.JFrame;
import models.Models;


public class Controller<T extends JFrame> {
    
    Models models;
    T view;
    
    public void exit(JFrame parent, int frame) {
        int choice = Prompt.cancel(parent, "Are you sure you want to exit?", "Confirm Exit");
        if (choice != 0) {
            return;
        }
        
        if (frame == 1) {
            Auth.session().createSession(Auth.user);
        }
        System.exit(choice);
    }
    
    
    public void logout() {    
        int choice = Prompt.cancel(view, "Do you really want to logout?", "Confirm Logout");
        if (choice != 0) {
            return;
        }
        Auth.session().deleteSession();
        new LoginController(models);
        this.view.dispose();
    }
    
}
