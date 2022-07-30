package account;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GUICreateAccountPage extends JPanel {
    public static final int TEXT_FIELD_X = 117;
    public static final int TEXT_FIELD_W = 265;
    public static final int TEXT_FIELD_H = 20;
    public static final int BUTTON_X = 117;
    public static final int BUTTON_Y = 448;
    public static final int BUTTON_W = 254;
    public static final int BUTTON_H = 40;

    public BufferedImage signUpbtnImage;
    public JTextField tfName;
    public JTextField tfEmail;
    public JTextField tfPassword;
    public JTextField tfDisplayName;
    public JButton btnSignUp;

    public GUICreateAccountPage() {
//        this.setBackground(new Color(245,225,195));
        this.setBounds(0, 0, 500, 700);
        setLayout(null);

        //read images
//        try { // offline button image
//            signUpbtnImage = ImageIO.read(new File(OFFLINE_BUTTON_IMAGE_FILENAME));
//        } catch (IOException e) {
//            System.out.println("LoginPanel::LoginPanel(...): error: file not found: " + OFFLINE_BUTTON_IMAGE_FILENAME);
//            System.exit(1);
//        }

        tfName = new JTextField("Full Name");
        tfName.setBounds(TEXT_FIELD_X, 250, TEXT_FIELD_W, TEXT_FIELD_H);

        tfEmail = new JTextField("Email Address");
        tfEmail.setBounds(TEXT_FIELD_X, 297, TEXT_FIELD_W, TEXT_FIELD_H);

        tfPassword = new JTextField("Password");
        tfPassword.setBounds(TEXT_FIELD_X, 345, TEXT_FIELD_W, TEXT_FIELD_H);

        tfDisplayName = new JTextField("Display Name");
        tfDisplayName.setBounds(TEXT_FIELD_X, 393, TEXT_FIELD_W, TEXT_FIELD_H);

//        btnSignUp = new JButton(new ImageIcon(loginButtonImage));
//        btnSignUp.setBounds(BUTTON_X, BUTTON_Y, BUTTON_W, BUTTON_H);
        
    }

    public void paint(Graphics g) {

    }
}
