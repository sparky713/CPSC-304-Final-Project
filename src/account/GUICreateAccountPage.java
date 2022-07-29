package account;

import javax.swing.*;

public class GUICreateAccountPage extends JPanel {
    JTextField tfName;
    JTextField tfEmail;
    JTextField tfPassword;
    JTextField tfDisplayName;
    JButton btnSignUp;

    public GUICreateAccountPage() {
        tfName = new JTextField("Full Name");
        tfEmail = new JTextField("Email Address");
        tfPassword = new JTextField("Password");
        tfDisplayName = new JTextField("Display Name");
        btnSignUp = new JButton("Sign Up");
    }
}
