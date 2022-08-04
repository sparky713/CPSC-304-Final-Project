package ui;

import controller.Main;
import model.Abilities;

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

public class GUIAbilitiesPage extends JPanel {
    Graphics g = null;
    public static final String BACKGROUND_IMAGE_FILENAME = "images/weapons_page_bg.png";
    public static final String ABILITIES_PANEL_FILENAME = "images/weapons_panel.png";
    public static final String APPLY_BTN_IMAGE_FILENAME = "images/abilities_apply_btn.png";
    public static final int W = GUIMainPage.W;
    public static final int H = GUIMainPage.H;

    public static final int DROP_DOWN_CHARACTER_MENU_X = 118;
    public static final int DROP_DOWN_CHARACTER_MENU_Y = 142;
    public static final int DROP_DOWN_CHARACTER_MENU_W = 115;
    public static final int DROP_DOWN_CHARACTER_MENU_H = 30;

    public static final int DROP_DOWN_LEVEL_MENU_X = 240;
    public static final int DROP_DOWN_LEVEL_MENU_Y = DROP_DOWN_CHARACTER_MENU_Y;
    public static final int DROP_DOWN_LEVEL_MENU_W = 115;
    public static final int DROP_DOWN_LEVEL_MENU_H = DROP_DOWN_CHARACTER_MENU_H;

    public static final int LBL_MARGIN_TOP = 15;
    public static final int LBL_TITLE_X = 83;
    public static final int LBL_TITLE_Y = 65;
    public static final int LBL_TITLE_W = 300;
    public static final int LBL_TITLE_H = 37;
    public static final int LBL_TITLE_FONT_SIZE = 32;

    public static final int LBL_FONT_SIZE = 16;

    public static final int LBL_CHARACTERS_X = DROP_DOWN_CHARACTER_MENU_X;
    public static final int LBL_CHARACTERS_Y = DROP_DOWN_CHARACTER_MENU_Y - 30;
    public static final int LBL_CHARACTERS_W = 100;
    public static final int LBL_CHARACTERS_H = 37;

    public static final int LBL_LEVEL_X = DROP_DOWN_LEVEL_MENU_X;
    public static final int LBL_LEVEL_Y = DROP_DOWN_LEVEL_MENU_Y - 30;
    public static final int LBL_LEVEL_W = LBL_CHARACTERS_W;
    public static final int LBL_LEVEL_H = LBL_CHARACTERS_H;

    public static final int LBL_MIN_CD_X = DROP_DOWN_LEVEL_MENU_X + DROP_DOWN_LEVEL_MENU_W + 15;
    public static final int LBL_MIN_CD_Y = DROP_DOWN_CHARACTER_MENU_Y - 20;
    public static final int LBL_MIN_CD_W = 63;
    public static final int LBL_MIN_CD_H = 20;

    public static final int TF_MIN_CD_X = LBL_MIN_CD_X;
    public static final int TF_MIN_CD_Y = DROP_DOWN_CHARACTER_MENU_Y + 5;
    public static final int TF_MIN_CD_W = 80;
    public static final int TF_MIN_CD_H = LBL_MIN_CD_H;

    public static final int LBL_MIN_DMG_X = LBL_MIN_CD_X + LBL_MIN_CD_W + 35;
    public static final int LBL_MIN_DMG_Y = LBL_MIN_CD_Y;
    public static final int LBL_MIN_DMG_W = 80;
    public static final int LBL_MIN_DMG_H = LBL_MIN_CD_H;

    public static final int TF_MIN_DMG_X = LBL_MIN_DMG_X;
    public static final int TF_MIN_DMG_Y = TF_MIN_CD_Y;
    public static final int TF_MIN_DMG_W = TF_MIN_CD_W;
    public static final int TF_MIN_DMG_H = TF_MIN_CD_H;

    public static final int BTN_APPLY_X = 640;
    public static final int BTN_APPLY_Y = 125;
    public static final int BTN_APPLY_W = 80;
    public static final int BTN_APPLY_H = 50;

    public static final int ABILITIES_PANEL_IMAGE_X = 113;
    public static final int ABILITIES_PANEL_IMAGE_Y = 177;

    public BufferedImage bgImage;
    public BufferedImage abilitiesPanelImage;
    public BufferedImage applyBtnImage;
    public JComboBox<String> characterDropDown;
    public JComboBox<Integer> levelDropDown;
    public String[] characterNames;
    public Integer[] levels;
    public JLabel lblTitle;
    public JLabel lblCharacterName;
    public JLabel lblLevel;
    public JLabel lblCD;
    public JLabel lblDMG;
    public JTextField tfCD;
    public JTextField tfDMG;
    public JButton btnApply;

    public Vector<Abilities> weapons;

    public GUIAbilitiesPage() {
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
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + BACKGROUND_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // abilities panel image
            abilitiesPanelImage = ImageIO.read(new File(ABILITIES_PANEL_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + ABILITIES_PANEL_FILENAME);
            System.exit(1);
        }

        try { // abilities button image
            applyBtnImage = ImageIO.read(new File(APPLY_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + APPLY_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------

        lblTitle = new JLabel("Abilities");
        lblTitle.setBounds(LBL_TITLE_X, LBL_TITLE_Y, LBL_TITLE_W, LBL_TITLE_H);
        lblTitle.setFont(new Font("Helvetica", BOLD, LBL_TITLE_FONT_SIZE));
        lblTitle.setForeground(Color.white);
        this.add(lblTitle);

        // String array containing the options for the drop-down menu
        characterNames = new String[]{"All", "Swords", "Bows"};
        levels = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};

        lblCharacterName = new JLabel("character:");
        lblCharacterName.setBounds(LBL_CHARACTERS_X, LBL_CHARACTERS_Y, LBL_CHARACTERS_W, LBL_CHARACTERS_H);
        lblCharacterName.setFont(new Font("Helvetica", Font.PLAIN, LBL_FONT_SIZE));
        lblCharacterName.setForeground(Color.white);
        this.add(lblCharacterName);

        characterDropDown = new JComboBox<>(characterNames);
        characterDropDown.setBounds(DROP_DOWN_CHARACTER_MENU_X, DROP_DOWN_CHARACTER_MENU_Y, DROP_DOWN_CHARACTER_MENU_W,
                                    DROP_DOWN_CHARACTER_MENU_H);
        this.add(characterDropDown);


        lblLevel = new JLabel("level:");
        lblLevel.setBounds(LBL_LEVEL_X, LBL_LEVEL_Y, LBL_LEVEL_W, LBL_LEVEL_H);
        lblLevel.setFont(new Font("Helvetica", Font.PLAIN, LBL_FONT_SIZE));
        lblLevel.setForeground(Color.white);
        this.add(lblLevel);

        levelDropDown = new JComboBox<>(levels);
        levelDropDown.setBounds(DROP_DOWN_LEVEL_MENU_X, DROP_DOWN_LEVEL_MENU_Y, DROP_DOWN_LEVEL_MENU_W, DROP_DOWN_LEVEL_MENU_H);
        this.add(levelDropDown);


        lblCD = new JLabel("min cd:");
        lblCD.setBounds(LBL_MIN_CD_X, LBL_MIN_CD_Y, LBL_MIN_CD_W, LBL_MIN_CD_H);
        lblCD.setFont(new Font("Helvetica", Font.PLAIN, LBL_FONT_SIZE));
        lblCD.setForeground(Color.white);
        this.add(lblCD);

        tfCD = new JTextField();
        tfCD.setBounds(TF_MIN_CD_X, TF_MIN_CD_Y, TF_MIN_CD_W, TF_MIN_CD_H);
//        tfCD.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                }
//            }
//        });
        this.add(tfCD, new Integer(0), 0);

        tfCD = new JTextField();
        tfCD.setBounds(TF_MIN_CD_X, TF_MIN_CD_Y, TF_MIN_CD_W, TF_MIN_CD_H);
//        tfCD.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                }
//            }
//        });
        this.add(tfCD, new Integer(0), 0);

        lblDMG = new JLabel("min dmg:");
        lblDMG.setBounds(LBL_MIN_DMG_X, LBL_MIN_DMG_Y, LBL_MIN_DMG_W, LBL_MIN_DMG_H);
        lblDMG.setFont(new Font("Helvetica", Font.PLAIN, LBL_FONT_SIZE));
        lblDMG.setForeground(Color.white);
        this.add(lblDMG);

        tfDMG = new JTextField();
        tfDMG.setBounds(TF_MIN_DMG_X, TF_MIN_DMG_Y, TF_MIN_DMG_W, TF_MIN_DMG_H);
//        tfDMG.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                }
//            }
//        });
        this.add(tfDMG, new Integer(0), 0);

        btnApply = new JButton(new ImageIcon(applyBtnImage));
        btnApply.setBounds(BTN_APPLY_X, BTN_APPLY_Y, BTN_APPLY_W, BTN_APPLY_H);
        btnApply.setBorder(BorderFactory.createEmptyBorder());
        btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: filter abilities according to properties specified
//                tfCD.getText(), tfDMG.getText();

                // insert newPlayer (handled in DatabaseConnectionHandler.java)
//                try {
//                    Game.dbHandler.insertPlayer(newPlayer);
//                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
//                }

                // TODO: show message indicating successful login
            }
        });
        this.add(btnApply);



        // TODO: add abilities and attach labels
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
        g.drawImage(abilitiesPanelImage, ABILITIES_PANEL_IMAGE_X, ABILITIES_PANEL_IMAGE_Y, null);
        paintComponents(g);
    }
}
