package com.yf.kp.template;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author BlackCode
 */
public class CustomDesktopPane extends JDesktopPane{
    
    private final Image image;
    
    public CustomDesktopPane(){
        ImageIcon icon = new ImageIcon(getClass().getResource("/com/yf/kp/images/cover.jpg"));
        image = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g.create();
        gd.drawImage(image, 0, 0, null);
        gd.dispose();
    }
    
    

}
