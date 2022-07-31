package account;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.BOLD;

public class GUICreateAccountPage extends JPanel {
    Graphics g = null;
    public static final String SIGN_UP_BTN_IMAGE_FILENAME = "images/sign_up_btn.png";
    public static final String BORDER_IMAGE_FILENAME = "images/sign_up_border.png";
    public static final int W = 500;
    public static final int H = 700;

    public static final int LBL_X = 112;
    public static final int LBL_Y = 115;
    public static final int LBL_W = 272;
    public static final int LBL_H = 32;
    public static final int BORDER_X = 53;
    public static final int BORDER_Y = 55;
    public static final int TEXT_FIELD_X = 112;
    public static final int TEXT_FIELD_W = 280;
    public static final int TEXT_FIELD_H = 40;

    public static final int TEXT_FIELD_MARGIN_TOP = 50;
    public static final int TEXT_FIELD_FULL_NAME_Y = 210;
    public static final int TEXT_FIELD_EMAIL_Y = TEXT_FIELD_FULL_NAME_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_PASSWORD_Y = TEXT_FIELD_EMAIL_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_DISPLAY_NAME_Y = TEXT_FIELD_PASSWORD_Y + TEXT_FIELD_MARGIN_TOP;

    public static final int BUTTON_X = 123;
    public static final int BUTTON_Y = 448;
    public static final int BUTTON_W = 254;
    public static final int BUTTON_H = 40;

    public BufferedImage signUpbtnImage;
    public BufferedImage borderImage;
    public JLabel lblCreateAccount;
    public JTextField tfName;
    public JTextField tfEmail;
    public JTextField tfPassword;
    public JTextField tfDisplayName;
    public JButton btnSignUp;

    public GUICreateAccountPage() {
        setLayout(null);
        this.setBackground(new Color(0,255,255));
        this.setBounds(0, 0, 500, 700);

        //---------------------------------------------------------------------
        // read images
        //---------------------------------------------------------------------
        try { // signUp button image
            signUpbtnImage = ImageIO.read(new File(SIGN_UP_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUICreateAccountPage::GUICreateAccountPage(): error: file not found: " + SIGN_UP_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // border image
            borderImage = ImageIO.read(new File(BORDER_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUICreateAccountPage::GUICreateAccountPage(): error: file not found: " + BORDER_IMAGE_FILENAME);
            System.exit(1);
        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------
        lblCreateAccount = new JLabel("CREATE ACCOUNT");
        lblCreateAccount.setBounds(LBL_X, LBL_Y, LBL_W, LBL_H);
        lblCreateAccount.setFont(new Font("Arial", BOLD, 28));
        this.add(lblCreateAccount);

        tfName = new JTextField("Full Name");
        tfName.setBounds(TEXT_FIELD_X, TEXT_FIELD_FULL_NAME_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        this.add(tfName);

        tfEmail = new JTextField("Email Address");
        tfEmail.setBounds(TEXT_FIELD_X, TEXT_FIELD_EMAIL_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        this.add(tfEmail);

        tfPassword = new JTextField("Password");
        tfPassword.setBounds(TEXT_FIELD_X, TEXT_FIELD_PASSWORD_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        this.add(tfPassword);

        tfDisplayName = new JTextField("Display Name");
        tfDisplayName.setBounds(TEXT_FIELD_X, TEXT_FIELD_DISPLAY_NAME_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        this.add(tfDisplayName);

        btnSignUp = new JButton(new ImageIcon(signUpbtnImage));
        btnSignUp.setBounds(BUTTON_X, BUTTON_Y, BUTTON_W, BUTTON_H);
        this.add(btnSignUp);

        repaint();
    }

    public void paint(Graphics g) {
        System.out.println("oop");
        g.drawImage(borderImage, BORDER_X, BORDER_Y, null);
        paintComponents(g);
    }
}
