
package controllers;

import core.Auth;
import core.Prompt;
import core.Validator;
import javax.swing.JFrame;
import models.Models;
import views.ChangePassView;


public class ChangePassController extends Controller<ChangePassView>{

    public ChangePassController(Models model) {
        models = model;
        view = new ChangePassView();
        init();
    }
    
    private void init() {
        view.setLocationRelativeTo(null);
        view.setController(this);
        view.setVisible(true);
    }
    
    public void changePass(String current, String newPass, String confirm) {
        if (!current.equals(Auth.user.password)) {
            Prompt.error(view, "Current Password Invalid", "Update Failed");
            return;
        }
        
        
        if (!newPass.equals(confirm)) {
            Prompt.error(view, "Password Do Not Match", "Update Failed");
            return;
        }
        
        if (Validator.password(newPass)) {
            Prompt.error(view, "Password Length Invalid", "Update Failed");
            return;            
        }
        
        Auth.user.password = newPass;
        models.user.updateById(Auth.user.id, Auth.user);
        Prompt.information(view, "Password Change", "Update Success");
        view.dispose();
    }
}
