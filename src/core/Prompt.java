package core;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Prompt {
    
    public static void error(JFrame parent, String msg, String title) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public static void information(JFrame parent, String msg, String title) {
        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    public static int cancel(JFrame parent, String msg, String title) {
        return JOptionPane.showConfirmDialog(parent, msg, title, JOptionPane.CANCEL_OPTION);
    }
    
    
}
