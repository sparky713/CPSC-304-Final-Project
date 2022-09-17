package ui;

import controller.Main;
import database.DatabaseConnectionHandler;
import model.Character;
import model.Weapon;

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
import java.util.ArrayList;

public class GUICharacterByPlayerPage extends JPanel {
    Graphics g = null;
    public static final String BACKGROUND_IMAGE_FILENAME = "images/weapons_page_bg.png";

    public BufferedImage bgImage;
    public BufferedImage backBtnImage;
    public static final int W = 500;
    public static final int H = 700;
    public static final int BORDER_X = 53;
    public static final int BORDER_Y = 55;
    //    public static final int TEXT_FIELD_NAME_W = 280 / 2 - 5;
    public static final int TEXT_FIELD_X = 120;
    public static final int TEXT_FIELD_W = 280;
    public static final int TEXT_FIELD_H = 30;

    public static final int TEXT_FIELD_MARGIN_TOP = 20;
    public static final int TEXT_FIELD_MARGIN_LEFT = 10;
    public static final int TEXT_FIELD_USERNAME_Y = 210;
    public static final int TEXT_FIELD_EMAIL_Y = TEXT_FIELD_USERNAME_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_PASSWORD_Y = TEXT_FIELD_EMAIL_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_DISPLAY_NAME_Y = TEXT_FIELD_PASSWORD_Y + TEXT_FIELD_MARGIN_TOP;

//    private JTextField userText;
    private JComboBox<String> userText;
    private JTextField atkText;
    private JButton userButton;
    private JTable characterTable;
    private JButton returnButton;
    private JButton nestedButton;
    private ArrayList<Character> characters = null;
    private ArrayList<Weapon> weapons = null;

    DatabaseConnectionHandler dbHandler;

    public GUICharacterByPlayerPage(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;

        setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(0, 0, GUIMainPage.W, GUIMainPage.H);
        Main.frame.add(this, 0);

        try { // background image
            bgImage = ImageIO.read(new File(BACKGROUND_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIWeaponsPage::GUIWeaponsPage(): error: file not found: " + BACKGROUND_IMAGE_FILENAME);
            System.exit(1);
        }

        String[] usernames = new String[6];
        usernames[0] = "select a user";
        usernames[1] = "player1";
        usernames[2] = "player2";
        usernames[3] = "player3";
        usernames[4] = "player4";
        usernames[5] = "player5";

        userText = new JComboBox<>(usernames);
        userText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP, TEXT_FIELD_W, TEXT_FIELD_H);
        ActionListener partyActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        userText.addActionListener(partyActionListener);
        this.add(userText);

//        userText = new JTextField("Username");
//        userText.setEnabled(false);
//        userText.setDisabledTextColor(Color.gray);
//        userText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP, TEXT_FIELD_W, TEXT_FIELD_H);
//        userText.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
//        userText.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                userText.requestFocus();
//                userText.setEnabled(true);
//            }
//        });

        atkText = new JTextField("Atk >");
        atkText.setEnabled(false);
        atkText.setDisabledTextColor(Color.gray);
        atkText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        atkText.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        atkText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                atkText.requestFocus();
                atkText.setEnabled(true);
            }
        });

        userButton = new JButton("Submit");
        userButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_MARGIN_TOP, 80, TEXT_FIELD_H);

        Object[][] s = new Object[100][100];
        Object[] c = {"Name", "Level", "BaseATK", "BaseHP", "Element"};
        characterTable = new JTable(s,c);
        characterTable.setVisible(true);
        characterTable.setBackground(Color.gray);
        characterTable.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 100, 500, 310);

        characterTable.getTableHeader().setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 80, 500, 20);

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = (String) userText.getSelectedItem();
                int minATK = Integer.parseInt(atkText.getText());

//                weapons = dbHandler.giveOwnedWeaponWithMinATK(minATK, username);
//
//                for (int i = 0; i < weapons.size() - 1; i++) {
//                    s[i][0] = weapons.get(i).getName();
//                    s[i][1] = String.valueOf(weapons.get(i).getBaseATK());
//                }

                //clears the table in a super scuffed way
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        s[i][j] = "";
                    }
                }

                characterTable.revalidate();
                characterTable.updateUI();

                characters = dbHandler.giveCharacterWithMinATK(minATK, username);

                for (int i = 0; i < characters.size(); i++) {
//                    System.out.println(characters.size());
                    s[i][0] = characters.get(i).getName();
//                    System.out.println(s[i][0]);
                    s[i][1] = String.valueOf(characters.get(i).getLevel());
                    s[i][2] = String.valueOf(characters.get(i).getBaseATK());
                    s[i][3] = String.valueOf(characters.get(i).getBaseHP());
                    s[i][4] = characters.get(i).getElement();
                }

                characterTable.revalidate();
                characterTable.updateUI();

            }
        });

        try { // return button image
            backBtnImage = ImageIO.read(new File(GUIMainPage.BACK_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + GUIMainPage.BACK_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        returnButton = new JButton(new ImageIcon(backBtnImage));
        returnButton.setBounds(GUIMainPage.BTN_BACK_X - 10, TEXT_FIELD_MARGIN_TOP, GUIMainPage.BTN_BACK_W, GUIMainPage.BTN_BACK_H);
        returnButton.setBorder(BorderFactory.createEmptyBorder());
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(2);
                Main.guiCharacterByPlayerPage.setVisible(false);
            }
        });

        nestedButton = new JButton("Strongest Characters");
        nestedButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10, GUIMainPage.BTN_BACK_W + 200, GUIMainPage.BTN_BACK_H);
        nestedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(9);
                Main.guiCharacterByPlayerPage.setVisible(false);
            }
        });

        this.add(nestedButton);
        this.add(returnButton);
        this.add(atkText);
        this.add(userText);
        this.add(userButton);
        this.add(characterTable.getTableHeader());
        this.add(characterTable);

        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, null);
        paintComponents(g);
    }
}
