package ui;

import controller.Main;
import database.DatabaseConnectionHandler;
import model.Character;
import model.Weapon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUINestedAggregationPage extends JPanel {
    public static final int W = 500;
    public static final int H = 700;
    public static final int BORDER_X = 53;
    public static final int BORDER_Y = 55;
    //    public static final int TEXT_FIELD_NAME_W = 280 / 2 - 5;
    public static final int TEXT_FIELD_X = 20;
    public static final int TEXT_FIELD_W = 280;
    public static final int TEXT_FIELD_H = 30;

    public static final int TEXT_FIELD_MARGIN_TOP = 20;
    public static final int TEXT_FIELD_USERNAME_Y = 210;
    public static final int TEXT_FIELD_EMAIL_Y = TEXT_FIELD_USERNAME_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_PASSWORD_Y = TEXT_FIELD_EMAIL_Y + TEXT_FIELD_MARGIN_TOP;

    private JTable characterTable;

    private ArrayList<Character> characters = null;
    DatabaseConnectionHandler dbHandler;

    public GUINestedAggregationPage(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;

        setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(0, 0, GUIMainPage.W, GUIMainPage.H);
        Main.frame.add(this, 0);

        Object[][] s = new Object[100][100];
        Object[] c = {"Name", "Level", "BaseATK", "BaseHP", "Element"};

        characters = dbHandler.nestedAggregation();

        for (int i = 0; i < characters.size(); i++) {
            s[i][0] = characters.get(i).getName();
            System.out.println(s[i][0]);
            s[i][1] = String.valueOf(characters.get(i).getLevel());
            s[i][2] = String.valueOf(characters.get(i).getBaseATK());
            s[i][3] = String.valueOf(characters.get(i).getBaseHP());
            s[i][4] = characters.get(i).getElement();
        }

        characterTable = new JTable(s, c);
        characterTable.setVisible(true);
        characterTable.setBackground(Color.white);
        characterTable.setGridColor(Color.black);
        characterTable.setBounds(100, 50, 500, 500);
        this.add(characterTable);
    }

}
