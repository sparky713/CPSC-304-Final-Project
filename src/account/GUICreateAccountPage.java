package account;

import javax.swing.*;
import java.awt.*;

public class GUICreateAccountPage extends JPanel {
    public static final int TEXT_FIELD_X = 117;
    public static final int TEXT_FIELD_W = 265;
    public static final int TEXT_FIELD_H = 20;
    public static final int BUTTON_X = 117;
    public static final int BUTTON_Y = 117;
    public static final int BUTTON_W = 265;
    public static final int BUTTON_H = 20;
    public JTextField tfName;
    public JTextField tfEmail;
    public JTextField tfPassword;
    public JTextField tfDisplayName;
    public JButton btnSignUp;

    public GUICreateAccountPage() {
//        this.setBackground(new Color(245,225,195));
        this.setBounds(0, 0, 500, 700);
        setLayout(null);
        tfName = new JTextField("Full Name");
        tfName.setBounds(TEXT_FIELD_X, 250, TEXT_FIELD_W, TEXT_FIELD_H);

        tfEmail = new JTextField("Email Address");
        tfEmail.setBounds(TEXT_FIELD_X, 297, TEXT_FIELD_W, TEXT_FIELD_H);

        tfPassword = new JTextField("Password");
        tfPassword.setBounds(TEXT_FIELD_X, 345, TEXT_FIELD_W, TEXT_FIELD_H);

        tfDisplayName = new JTextField("Display Name");
        tfDisplayName.setBounds(TEXT_FIELD_X, 393, TEXT_FIELD_W, TEXT_FIELD_H);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(TEXT_FIELD_X, 448, TEXT_FIELD_W, TEXT_FIELD_H);
        
    }

    public void paint(Graphics g) {

    }
}
