package ui;

import controller.Main;
import database.DatabaseConnectionHandler;
import model.Character;
import model.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class GUIFoodPage extends JPanel {
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

    private JTextField userText;
    private JTextField atkText;

    private JButton showFoodInfoButton;
    private JTable characterTable;

    private JButton returnButton;
    //private ArrayList<Character> characters = null;
    private ArrayList<Map<String, Integer>> playerFoodInfo;
    private ArrayList<Weapon> weapons = null;

    DatabaseConnectionHandler dbHandler;

    public GUIFoodPage(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;

        setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(0, 0, GUIMainPage.W, GUIMainPage.H);
        Main.frame.add(this, 0);

        userText = new JTextField("Food Name");
        userText.setEnabled(false);
        userText.setDisabledTextColor(Color.gray);
        userText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP, TEXT_FIELD_W, TEXT_FIELD_H);
        userText.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        userText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userText.requestFocus();
                userText.setEnabled(true);
            }
        });

        atkText = new JTextField("");
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

        showFoodInfoButton = new JButton("Show Food Info");
        showFoodInfoButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_MARGIN_TOP, 80, TEXT_FIELD_H);

        Object[][] s = new Object[10][10];
        Object[] c = {"Food", "Quantity"};
        characterTable = new JTable(s,c);
        characterTable.setVisible(true);
        characterTable.setBackground(Color.white);
        characterTable.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 100, 500, 500);

        showFoodInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String username = userText.getText();
                //int minATK = Integer.parseInt(atkText.getText());

//                weapons = dbHandler.giveOwnedWeaponWithMinATK(minATK, username);
//
//                for (int i = 0; i < weapons.size() - 1; i++) {
//                    s[i][0] = weapons.get(i).getName();
//                    s[i][1] = String.valueOf(weapons.get(i).getBaseATK());
//                }

                //clears the table in a super scuffed way
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        s[i][j] = "";
                    }
                }

                characterTable.revalidate();
                characterTable.updateUI();

                playerFoodInfo = dbHandler.getPlayerFoodInfo(Main.currPlayer);
                //characters = dbHandler.getPlayerFoodInfo(Main.currPlayer);

                for (int i = 0; i < playerFoodInfo.size(); i++) {
//                    System.out.println(characters.size());

                    Optional<String> firstKey = (playerFoodInfo.get(i)).keySet().stream().findFirst();
                    if (firstKey.isPresent()) {
                        String key = firstKey.get();
                        s[i][0] = key;
                        s[i][1] = String.valueOf((playerFoodInfo.get(i)).get(key));
                    } else {
                        s[i][0] = " ";
                        s[i][1] = " ";
                    }

//                    System.out.println(s[i][0]);
//                    s[i][1] = String.valueOf(characters.get(i).getLevel());
//                    s[i][2] = String.valueOf(characters.get(i).getBaseATK());
//                    s[i][3] = String.valueOf(characters.get(i).getBaseHP());
//                    s[i][4] = characters.get(i).getElement();
                }

                characterTable.revalidate();
                characterTable.updateUI();

            }
        });

        returnButton = new JButton("Return");
        returnButton.setBounds(GUIMainPage.BTN_BACK_X, GUIMainPage.BTN_BACK_Y, GUIMainPage.BTN_BACK_W + 50, GUIMainPage.BTN_BACK_H);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(2);
                Main.guiCharacterByPlayerPage.setVisible(false);
            }
        });
        this.add(returnButton);

        this.add(atkText);
        this.add(userText);
        this.add(showFoodInfoButton);
        this.add(characterTable);

    }
}
