package ui;

import controller.Main;
import database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import static java.awt.Font.BOLD;

public class GUIFriendsPage extends JPanel{
    public static final int W = 500;
    public static final int H = 700;

    private JTextField userText;
    private JTextField atkText;

    private JButton userButton;
    private JButton minATKButton;
    private JFrame jFrame;

    DatabaseConnectionHandler dbHandler;

    public GUIFriendsPage(DatabaseConnectionHandler dbHandler){
        this.dbHandler = dbHandler;

        setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(0, 0, GUIMainPage.W, GUIMainPage.H);
        Main.frame.add(this, 0);


    }
}
