package ui;

import controller.Main;
import database.DatabaseConnectionHandler;
import model.Food;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import static java.awt.Font.BOLD;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * The ui for the sign-up window
 */
public class GUICreateAccountPage extends JPanel {
    private DatabaseConnectionHandler dbHandler = null;
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
    //    public static final int TEXT_FIELD_NAME_W = 280 / 2 - 5;
    public static final int TEXT_FIELD_X = 112;
    public static final int TEXT_FIELD_W = 280;
    public static final int TEXT_FIELD_H = 40;

    public static final int TEXT_FIELD_MARGIN_TOP = 50;
    public static final int TEXT_FIELD_MARGIN_LEFT = 10;
    public static final int TEXT_FIELD_USERNAME_Y = 210;
    public static final int TEXT_FIELD_EMAIL_Y = TEXT_FIELD_USERNAME_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_PASSWORD_Y = TEXT_FIELD_EMAIL_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_DISPLAY_NAME_Y = TEXT_FIELD_PASSWORD_Y + TEXT_FIELD_MARGIN_TOP;

    public static final int BUTTON_X = 123;
    public static final int BUTTON_Y = 448;
    public static final int BUTTON_W = 254;
    public static final int BUTTON_H = 40;

    public static final String DEAFULT_TEXT_USERNAME = " Username";
    public static final String DEAFULT_TEXT_EMAIL = " Email Address";
    public static final String DEAFULT_TEXT_PWD = " Password";
    public static final String DEAFULT_TEXT_DISPLAY_NAME = " Display Name";

    public static final int POPUP_MENU_X = 200;
    public static final int POPUP_MENU_Y = 300;
    public static final int POPUP_MENU_W = 100;
    public static final int POPUP_MENU_H = 100;

    public BufferedImage signUpbtnImage;
    public BufferedImage borderImage;
    public JLabel lblCreateAccount;
    public JTextField tfUsername;
    //    public JTextField tfLastName;
    public JTextField tfEmail;
    public JTextField tfPassword;
    public JTextField tfDisplayName;
    public JButton btnSignUp;
    public JOptionPane loginSuccessMessageWindow;

    public Vector<JTextField> tfs;
//    public boolean tfEdited;

    public GUICreateAccountPage() {
        setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(0, 0, W, H);
        Main.frame.add(this, 0);

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

        loginSuccessMessageWindow = new JOptionPane();
        loginSuccessMessageWindow.setBounds(POPUP_MENU_X, POPUP_MENU_Y, POPUP_MENU_W, POPUP_MENU_H);

        tfs = new Vector<JTextField>();
        lblCreateAccount = new JLabel("CREATE ACCOUNT");
        lblCreateAccount.setBounds(LBL_X, LBL_Y, LBL_W, LBL_H);
        lblCreateAccount.setFont(new Font("Arial", BOLD, 28));
        this.add(lblCreateAccount);

        tfUsername = new JTextField(DEAFULT_TEXT_USERNAME);
        tfUsername.setEnabled(false);
        tfUsername.setDisabledTextColor(Color.gray);
        tfUsername.setBounds(TEXT_FIELD_X, TEXT_FIELD_USERNAME_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        tfUsername.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        tfUsername.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfUsername.requestFocus();
                tfUsername.setEnabled(true);
                if (tfUsername.getText().equals(DEAFULT_TEXT_USERNAME)) {
                    tfUsername.setText("");
                }
            }
        });

        this.add(tfUsername);

        tfEmail = new JTextField(DEAFULT_TEXT_EMAIL);
        tfEmail.setEnabled(false);
        tfEmail.setDisabledTextColor(Color.gray);
        tfEmail.setBounds(TEXT_FIELD_X, TEXT_FIELD_EMAIL_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        tfEmail.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        tfEmail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfEmail.requestFocus();
                tfEmail.setEnabled(true);
                if (tfEmail.getText().equals(DEAFULT_TEXT_EMAIL)) {
                    tfEmail.setText("");
                }
            }
        });
        this.add(tfEmail);

        tfPassword = new JTextField(DEAFULT_TEXT_PWD);
        tfPassword.setEnabled(false);
        tfPassword.setDisabledTextColor(Color.gray);
        tfPassword.setBounds(TEXT_FIELD_X, TEXT_FIELD_PASSWORD_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        tfPassword.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        tfPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfPassword.requestFocus();
                tfPassword.setEnabled(true);
                if (tfPassword.getText().equals(DEAFULT_TEXT_PWD)) {
                    tfPassword.setText("");
                }
            }
        });
        this.add(tfPassword);

        tfDisplayName = new JTextField(DEAFULT_TEXT_DISPLAY_NAME);
        tfDisplayName.setEnabled(false);
        tfDisplayName.setDisabledTextColor(Color.gray);
        tfDisplayName.setBounds(TEXT_FIELD_X, TEXT_FIELD_DISPLAY_NAME_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        tfDisplayName.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        tfDisplayName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfDisplayName.requestFocus();
                tfDisplayName.setEnabled(true);
                if (tfDisplayName.getText().equals(DEAFULT_TEXT_DISPLAY_NAME)) {
                    tfDisplayName.setText("");
                }
            }
        });
        this.add(tfDisplayName);

        tfs.add(tfUsername);
//        tfs.add(tfLastName);
        tfs.add(tfEmail);
        tfs.add(tfPassword);
        tfs.add(tfDisplayName);

        btnSignUp = new JButton(new ImageIcon(signUpbtnImage));
        btnSignUp.setBounds(BUTTON_X, BUTTON_Y, BUTTON_W, BUTTON_H);
        btnSignUp.setEnabled(false);

        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player newPlayer = new Player(tfUsername.getText(), tfEmail.getText(),
                        tfPassword.getText(), tfDisplayName.getText());

                if (!dbHandler.insertPlayer(newPlayer)) {
                    return;
                }

                Main.guiEditProfilePage.addPlayer(newPlayer);
                // open main page
                Main.guiMainPage.lblDisplayName.setText(tfDisplayName.getText());
                Main.changeScreen(2);
                // lblShow message indicating successful login
                showMessageDialog(null, "Welcome " +
                        tfDisplayName.getText() + "!", "Account Created Successfully", JOptionPane.INFORMATION_MESSAGE);
                Main.currPlayer = newPlayer;

                //Jasmine added:
                // Automatically giving user a mushroom pizza

                Food mushroomPizza = new Food("Mushroom Pizza", 450);
                //dbHandler.insertFood(octopusPizza);

                // random number generator for unique id:
                Random rand = new Random(); //instance of random class
                int upperbound = 1000000;
                int int_random = rand.nextInt(upperbound);

                dbHandler.insertConsumes(int_random, newPlayer, mushroomPizza, 2);
            }
        });
        this.add(btnSignUp);
        Main.frame.getRootPane().setDefaultButton(btnSignUp);
        //---------------------------------------------------------------------
        // timer(thread) - to call update() and paint()
        //---------------------------------------------------------------------
        java.util.Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            public void run() {
                update();
                repaint();
            }
        }, 0, 10);
    }

    public void update() {
        updateSignUpButton();
    }

    private void updateSignUpButton() {
//        if (tfEdited) {
        //iterates through tfs and checks that user input is present in all text fields to enable sign-up button
        for (int i = 0; i < tfs.size(); i++) {
            if (tfs.elementAt(i).getText().equals("") || !tfs.elementAt(i).isEnabled()) {
                btnSignUp.setEnabled(false);
                return;
            }
        }
        btnSignUp.setEnabled(true);
//        }
    }

    public void paint(Graphics g) {
        g.drawImage(borderImage, BORDER_X, BORDER_Y, null);
        paintComponents(g);
    }

    public void setDbHandler(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
}
