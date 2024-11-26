package controllers;

import core.Auth;
import core.Prompt;
import javax.swing.JFrame;
import models.Models;


abstract class Controller<T extends JFrame> {
    
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
        view.dispose();
    }
    
    public void listenCombo(String item) {
        if(item.equalsIgnoreCase(Auth.user.username)) {
            System.out.println("Profile");
            return;
        } 
        
        if (item.equals("Settings")) {
            new SettingsController(models);
            view.dispose();
            return;
        } 
        if (item.equalsIgnoreCase("Logout")) {
            logout();
        }
    }
    
}
