package controllers;

import core.Auth;
import core.Prompt;
import models.Models;
import views.ChangePassView;
import views.SettingsView;

public class SettingsController extends Controller<SettingsView>{
        
    public SettingsController(Models model) {
        models = model;
        view = new SettingsView();
        init();
    }
    
     private void init() {
        view.setController(this);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
     
    public void changeBoard(String board) {
        new HomeController(models).changeBoard(board);
        view.dispose();
    }
    
    public void changePassword() {
        new ChangePassController(models);
    }
    
    public void update(String username, String email) {
        if (models.user.getByEmail(Auth.user.email) == null) {
            Prompt.error(view, "Cannot find current user", "Something went wrong...");
            logout();
            return;
        }
        
        if (!Auth.validateEmail(email)) {
            Prompt.error(view, "Invalid Email", "Update Failed");
            return;
        }
        
        if (models.user.getByEmail(email) != null) {
            Prompt.error(view, "Email is Already Taken", "Update Failed");
            return;
        }        
        
        Auth.user.email = email;
        Auth.user.username = username;
        
        boolean res = models.user.updateById(Auth.user.id, Auth.user);
        if (!res) {
            Prompt.error(view, "Cannot update current user", "Something went wrong...");
            logout();
            return;
        }
        Prompt.information(view, Auth.user.username + " updated", "Update Success");
    }
}
