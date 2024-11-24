package controllers;

import core.Prompt;
import javax.swing.JFrame;
import models.Models;


public class Controller<T extends JFrame> {
    
    Models models;
    T view;
    
    public void exit(JFrame parent) {
        int choice = Prompt.cancel(parent, "Are you sure you want to exit?", "Confirm Exit");
        if (choice == 0) {
            System.exit(choice);
        }
    }
    
    
    public void logout() {    
        int choice = Prompt.cancel(view, "Do you really want to logout?", "Confirm Logout");
        if (choice != 0) {
            return;
        }
        
        new LoginController(models);
        this.view.dispose();
    }
    
}
