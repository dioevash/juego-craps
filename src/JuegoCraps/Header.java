package JuegoCraps;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a kind of JLabel used to create a personalized Header for the project
 * autor Diego Alvarez
 * @version V.1.0.0 date: 9.12.2021
 */
public class Header extends JLabel {
    /**
     * constructor of the Header Class
     * @param title String that contains Header text
     *
     * @param colorBackground Color object to be assigned for the Header background
     */
    public Header(String title, Color colorBackground) {
        this.setText(title);
        this.setBackground(colorBackground);
        this.setForeground(new Color(255,255,255));
        this.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);


    }
}
