package ui;

import controller.Main;
import database.DatabaseConnectionHandler;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static java.awt.Font.BOLD;

public class GUIEditProfilePage extends JPanel {
    Graphics g = null;
    public BufferedImage bgImage;
    public static final int W = 850;
    public static final int H = 750;

    public static final String BACK_BTN_IMAGE_FILENAME = "images/back_btn_white.png";

    public BufferedImage backBtnImage;

    public static final int LBL_X = 112;
    public static final int LBL_Y = 115;
    public static final int LBL_W = 272;
    public static final int LBL_H = 32;
    public static final int TEXT_FIELD_X = 112;
    public static final int TEXT_FIELD_W = 280;
    public static final int TEXT_FIELD_H = 40;

    public static final int TEXT_FIELD_MARGIN_TOP = 50;
    public static final int TEXT_FIELD_USERNAME_Y = 210;
    public static final int TEXT_FIELD_EMAIL_Y = TEXT_FIELD_USERNAME_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_PASSWORD_Y = TEXT_FIELD_EMAIL_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_DISPLAY_NAME_Y = TEXT_FIELD_PASSWORD_Y + TEXT_FIELD_MARGIN_TOP;

    public static final String DEFAULT_TEXT_EMAIL = " Email Address";
    public static final String DEFAULT_TEXT_PWD = " Password";
    public static final String DEFAULT_TEXT_DISPLAY_NAME = " Display Name";

    JLabel lblCreateAccount;

    public JTextField tfEmail;
    public JTextField tfPassword;
    public JTextField tfDisplayName;

    public Vector<JTextField> tfs;

    public JButton emailButton;
    public JButton passwordButton;
    public JButton displayNameButton;
    public JButton returnButton;

    private DatabaseConnectionHandler handler;
    private Player player;

    public GUIEditProfilePage(DatabaseConnectionHandler handler) {
        this.handler = handler;
        setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(0, 0, GUIMainPage.W, GUIMainPage.H);
        Main.frame.add(this, 0);

        try { // background image
            bgImage = ImageIO.read(new File(GUIWeaponsPage.BACKGROUND_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIWeaponsPage::GUIWeaponsPage(): error: file not found: " + GUIWeaponsPage.BACKGROUND_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // back button image
            backBtnImage = ImageIO.read(new File(BACK_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIEditProfilePage::GUIEditProfilePage(): error: file not found: " + BACK_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        tfs = new Vector<JTextField>();
        lblCreateAccount = new JLabel("EDIT PROFILE");
        lblCreateAccount.setBounds(LBL_X, LBL_Y, LBL_W, LBL_H);
        lblCreateAccount.setFont(new Font("Arial", BOLD, 28));
        this.add(lblCreateAccount);

        emailButton = new JButton("Change Email");
        emailButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_EMAIL_Y, 200, 40);
        this.add(emailButton);
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String new_email = tfEmail.getText();
                player.setEmail(new_email);
                handler.updatePlayer(player);
                Main.guiEditProfilePage.setVisible(false);
                Main.changeScreen(2);
            }
        });
        passwordButton = new JButton("Change Password");
        passwordButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_PASSWORD_Y, 200, 40);
        this.add(passwordButton);
        passwordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setPassword(tfPassword.getText());
                handler.updatePlayer(player);
                Main.guiEditProfilePage.setVisible(false);
                Main.changeScreen(2);
            }
        });

        displayNameButton = new JButton("Change Display Name");
        displayNameButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_DISPLAY_NAME_Y, 200, 40);
        displayNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setDisplayName(tfDisplayName.getText());
                handler.updatePlayer(player);
                Main.guiEditProfilePage.setVisible(false);
                Main.changeScreen(2);
            }
        });
        this.add(displayNameButton);

        returnButton = new JButton(new ImageIcon(backBtnImage));
        returnButton.setBounds(GUIMainPage.BTN_BACK_X, GUIMainPage.BTN_BACK_Y, GUIMainPage.BTN_BACK_W, GUIMainPage.BTN_BACK_H);
        returnButton.setBorder(BorderFactory.createEmptyBorder());
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(2);
                Main.guiEditProfilePage.setVisible(false);
            }
        });
        this.add(returnButton);

        tfEmail = new JTextField(DEFAULT_TEXT_EMAIL);
        tfEmail.setEnabled(false);
        tfEmail.setDisabledTextColor(Color.gray);
        tfEmail.setBounds(TEXT_FIELD_X, TEXT_FIELD_EMAIL_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        tfEmail.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        tfEmail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfEmail.requestFocus();
                tfEmail.setEnabled(true);
                if (tfEmail.getText().equals(DEFAULT_TEXT_EMAIL)) {
                    tfEmail.setText("");
                }
            }
        });
        this.add(tfEmail);

        tfPassword = new JTextField(DEFAULT_TEXT_PWD);
        tfPassword.setEnabled(false);
        tfPassword.setDisabledTextColor(Color.gray);
        tfPassword.setBounds(TEXT_FIELD_X, TEXT_FIELD_PASSWORD_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        tfPassword.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        tfPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfPassword.requestFocus();
                tfPassword.setEnabled(true);
                if (tfPassword.getText().equals(DEFAULT_TEXT_PWD)) {
                    tfPassword.setText("");
                }
            }
        });
        this.add(tfPassword);

        tfDisplayName = new JTextField(DEFAULT_TEXT_DISPLAY_NAME);
        tfDisplayName.setEnabled(false);
        tfDisplayName.setDisabledTextColor(Color.gray);
        tfDisplayName.setBounds(TEXT_FIELD_X, TEXT_FIELD_DISPLAY_NAME_Y, TEXT_FIELD_W, TEXT_FIELD_H);
        tfDisplayName.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        tfDisplayName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfDisplayName.requestFocus();
                tfDisplayName.setEnabled(true);
                if (tfDisplayName.getText().equals(DEFAULT_TEXT_DISPLAY_NAME)) {
                    tfDisplayName.setText("");
                }
            }
        });
        this.add(tfDisplayName);

        tfs.add(tfEmail);
        tfs.add(tfPassword);
        tfs.add(tfDisplayName);

        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, null);

        paintComponents(g);
    }

    public void addPlayer(Player player){
        this.player = player;
    }

    public Player getCurrentPlayer(){
        return player;
    }
}
