   package com.yf.kp;

import com.yf.kp.design.login.DialogLogIn;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author BlackCode
 */
public class Main {

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
                try {
                    UIManager.setLookAndFeel(ch.randelshofer.quaqua.QuaquaManager.getLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                new DialogLogIn(null, true).setVisible(true);
            }
        });
    }

}
