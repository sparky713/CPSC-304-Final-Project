package ui;

import controller.Main;
import model.Weapon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static java.awt.Font.BOLD;

/**
 * The ui for the abilities page.
 */
public class GUIWeaponsPage extends JPanel {
    Graphics g = null;
    public static final String BACKGROUND_IMAGE_FILENAME = "images/weapons_page_bg.png";
    public static final String WEAPONS_PANEL_FILENAME = "images/weapons_panel.png";
    public static final String WEAPONS_PANEL_BOWS_FILENAME = "images/weapons_panel_bows.png";
    public static final String WEAPONS_PANEL_SWORDS_FILENAME = "images/weapons_panel_swords.png";

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
    public BufferedImage weaponsPanelBowsImage;
    public BufferedImage weaponsPanelSwordsImage;
    public BufferedImage backBtnImage;
    public JComboBox<String> weaponDropDown;
    public String[] weaponTypes;
    public JLabel lblTitle;

    public Vector<Weapon> weapons;

    public JButton btnBack;

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

        try { // weapons panel image
            weaponsPanelImage = ImageIO.read(new File(WEAPONS_PANEL_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIWeaponsPage::GUIWeaponsPage(): error: file not found: " + WEAPONS_PANEL_FILENAME);
            System.exit(1);
        }

        try { // back button image
            backBtnImage = ImageIO.read(new File(GUIMainPage.BACK_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIWeaponsPage::GUIWeaponsPage(): error: file not found: " + GUIMainPage.BACK_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // bows panel image
            weaponsPanelBowsImage = ImageIO.read(new File(WEAPONS_PANEL_BOWS_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIWeaponsPage::GUIWeaponsPage(): error: file not found: " + WEAPONS_PANEL_BOWS_FILENAME);
            System.exit(1);
        }

        try { // swords panel image
            weaponsPanelSwordsImage = ImageIO.read(new File(WEAPONS_PANEL_SWORDS_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIWeaponsPage::GUIWeaponsPage(): error: file not found: " + WEAPONS_PANEL_SWORDS_FILENAME);
            System.exit(1);
        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------

        // String array containing the options for the drop-down menu
        weaponTypes = new String[]{"All", "Swords", "Bows"};

        weaponDropDown = new JComboBox<>(weaponTypes);
        weaponDropDown.setBounds(DROP_DOWN_MENU_X, DROP_DOWN_MENU_Y, DROP_DOWN_MENU_W, DROP_DOWN_MENU_H);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        };
        weaponDropDown.addActionListener(actionListener);
        this.add(weaponDropDown);

        lblTitle = new JLabel("Character Weapons");
        lblTitle.setBounds(LBL_TITLE_X, LBL_TITLE_Y, LBL_TITLE_W, LBL_TITLE_H);
        lblTitle.setFont(new Font("Helvetica", BOLD, LBL_TITLE_FONT_SIZE));
        lblTitle.setForeground(Color.white);
        this.add(lblTitle);

        btnBack = new JButton(new ImageIcon(backBtnImage));
        btnBack.setBounds(GUIMainPage.BTN_BACK_X, GUIMainPage.BTN_BACK_Y, GUIMainPage.BTN_BACK_W, GUIMainPage.BTN_BACK_H);
        btnBack.setBorder(BorderFactory.createEmptyBorder());
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(2);
                Main.guiWeaponsPage.setVisible(false);
            }
        });
        this.add(btnBack);

        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, null);
        switch ((String)weaponDropDown.getSelectedItem()) {//check for a match
            case "Swords":
                g.drawImage(weaponsPanelSwordsImage, WEAPONS_PANEL_IMAGE_X, WEAPONS_PANEL_IMAGE_Y, null);
                System.out.println("sd");
                break;
            case "Bows":
                g.drawImage(weaponsPanelBowsImage, WEAPONS_PANEL_IMAGE_X, WEAPONS_PANEL_IMAGE_Y, null);
                break;
            default:
                g.drawImage(weaponsPanelImage, WEAPONS_PANEL_IMAGE_X, WEAPONS_PANEL_IMAGE_Y, null);
                break;
        }

        paintComponents(g);
    }
}
