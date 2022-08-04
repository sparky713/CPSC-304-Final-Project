package ui;

import controller.Main;
import model.Weapon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static java.awt.Font.BOLD;

/**
 * The ui for the weapons page.
 */
public class GUIWeaponsPage extends JPanel {
    Graphics g = null;
    public static final String BACKGROUND_IMAGE_FILENAME = "images/weapons_page_bg.png";
    public static final String WEAPONS_PANEL_FILENAME = "images/weapons_panel.png";
    public static final int W = GUIMainPage.W;
    public static final int H = GUIMainPage.H;

    public static final int DROP_DOWN_MENU_X = 615;
    public static final int DROP_DOWN_MENU_Y = 135;
    public static final int DROP_DOWN_MENU_W = 115;
    public static final int DROP_DOWN_MENU_H = 30;

    public static final int LBL_MARGIN_TOP = 15;
    public static final int LBL_TITLE_X = 83;
    public static final int LBL_TITLE_Y = 74;
    public static final int LBL_TITLE_W = 500;
    public static final int LBL_TITLE_H = 37;
    public static final int LBL_TITLE_FONT_SIZE = 32;

    public static final int WEAPONS_PANEL_IMAGE_X = 113;
    public static final int WEAPONS_PANEL_IMAGE_Y = 177;

    public BufferedImage bgImage;
    public BufferedImage weaponsPanelImage;
    public JComboBox<String> weaponDropDown;
    public String[] weaponTypes;
    public JLabel lblTitle;

    public Vector<Weapon> weapons;

    public GUIWeaponsPage() {
        setLayout(null);
        this.setBackground(new Color(255,255,255));
        this.setBounds(0, 0, W, H);
        Main.frame.add(this, 0);

        weapons = null;

        //---------------------------------------------------------------------
        // read images
        //---------------------------------------------------------------------
        try { // background image
            bgImage = ImageIO.read(new File(BACKGROUND_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIWeaponsPage::GUIWeaponsPage(): error: file not found: " + BACKGROUND_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // weapons button image
            weaponsPanelImage = ImageIO.read(new File(WEAPONS_PANEL_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + WEAPONS_PANEL_FILENAME);
            System.exit(1);
        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------

        // String array containing the options for the drop-down menu
        weaponTypes = new String[]{"All", "Swords", "Bows"};

        weaponDropDown = new JComboBox<>(weaponTypes);
        weaponDropDown.setBounds(DROP_DOWN_MENU_X, DROP_DOWN_MENU_Y, DROP_DOWN_MENU_W, DROP_DOWN_MENU_H);
        this.add(weaponDropDown);

        lblTitle = new JLabel("Character Weapons");
        lblTitle.setBounds(LBL_TITLE_X, LBL_TITLE_Y, LBL_TITLE_W, LBL_TITLE_H);
        lblTitle.setFont(new Font("Helvetica", BOLD, LBL_TITLE_FONT_SIZE));
        lblTitle.setForeground(Color.white);
        this.add(lblTitle);

        // TODO: add weapons and attach labels
//        lblWeapons = new JLabel("bow 1");
//        lblWeapons.setBounds(LBL_WEAPONS_X, LBL_WEAPONS_Y, LBL_W, LBL_H);
//        lblWeapons.setFont(new Font("Arial", BOLD, 20));
//        lblWeapons.setForeground(Color.white);
//        this.add(lblWeapons);

        repaint();

//        //---------------------------------------------------------------------
//        // timer(thread) - to call update() and paint()
//        //---------------------------------------------------------------------
//        java.util.Timer t = new Timer(true);
//        t.schedule(new TimerTask() {
//            public void run() {
////                update();
//                repaint();
//            }
//        },0, 10);
    }

//    public void update() {
//        updateSignUpButton();
//    }

    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, null);
        g.drawImage(weaponsPanelImage, WEAPONS_PANEL_IMAGE_X, WEAPONS_PANEL_IMAGE_Y, null);
        paintComponents(g);
    }
}
