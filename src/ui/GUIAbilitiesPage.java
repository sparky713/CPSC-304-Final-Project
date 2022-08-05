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
import java.util.HashMap;
import java.util.Vector;

import static java.awt.Font.BOLD;

public class GUIAbilitiesPage extends JPanel {
    Graphics g = null;
    public static final String BACKGROUND_IMAGE_FILENAME = "images/weapons_page_bg.png";
    public static final String ABILITIES_PANEL_FILENAME = "images/abilities_panel.png";
    public static final String APPLY_BTN_IMAGE_FILENAME = "images/abilities_apply_btn.png";
//    public static final String SPARKS_AND_SPLASH_IMAGE_FILENAME = "images/abilities/sparks_and_splash.png";
//    public static final String PRESERVER_OF_FORTUNE_PANEL_FILENAME = "images/abilities/preserver_of_fortune.png";
//    public static final String RIFF_REVOLUTION_IMAGE_FILENAME = "images/abilities/riff_revolution.png";
//    public static final String BAND_OF_ALL_EVIL_IMAGE_FILENAME = "images/abilities/bane_of_all_evil.png";
//    public static final String FATE_IMAGE_FILENAME = "images/abilities/fate.png";

    public static final int W = GUIMainPage.W;
    public static final int H = GUIMainPage.H;

    public static final int SPARKS_AND_SPLASH = 0;
    public static final int PRESERVER_OF_FORTUNE = 1;
    public static final int RIFF_REVOLUTION = 2;
    public static final int BAND_OF_ALL_EVIL = 3;
    public static final int FATE = 4;

    public static final int ICONS_Y = 207;
    public static final int ICONS_W = 68;
    public static final int ICONS_MARGIN = 52;
    public static final int SPARKS_AND_SPLASH_ICON_X = 145;
    public static final int PRESERVER_OF_FORTUNE_ICON_X = SPARKS_AND_SPLASH_ICON_X + ICONS_W + ICONS_MARGIN;
    public static final int RIFF_REVOLUTION_ICON_X = PRESERVER_OF_FORTUNE_ICON_X + ICONS_W + ICONS_MARGIN;
    public static final int BAND_OF_ALL_EVIL_ICON_X = RIFF_REVOLUTION_ICON_X + ICONS_W + ICONS_MARGIN;
    public static final int FATE_ICON_X = BAND_OF_ALL_EVIL_ICON_X + ICONS_W + ICONS_MARGIN;

    public static final int LIST_Y = 360;
    public static final int LIST_W = 100;
    public static final int LIST_H = 100;
    public static final int LIST_MARGIN = 46;
    public static final int SPARKS_AND_SPLASH_LIST_X = 78;
    public static final int PRESERVER_OF_FORTUNE_LIST_X = SPARKS_AND_SPLASH_LIST_X + LIST_W + LIST_MARGIN;
    public static final int RIFF_REVOLUTION_LIST_X = PRESERVER_OF_FORTUNE_LIST_X + LIST_W + LIST_MARGIN;
    public static final int BAND_OF_ALL_EVIL_LIST_X = RIFF_REVOLUTION_LIST_X + LIST_W + LIST_MARGIN;
    public static final int FATE_LIST_X = BAND_OF_ALL_EVIL_LIST_X + LIST_W + LIST_MARGIN;

    public static final int DROP_DOWN_CHARACTER_MENU_X = 118;
    public static final int DROP_DOWN_CHARACTER_MENU_Y = 142;
    public static final int DROP_DOWN_CHARACTER_MENU_W = 115;
    public static final int DROP_DOWN_CHARACTER_MENU_H = 30;

    public static final int DROP_DOWN_LEVEL_MENU_X = 240;
    public static final int DROP_DOWN_LEVEL_MENU_Y = DROP_DOWN_CHARACTER_MENU_Y;
    public static final int DROP_DOWN_LEVEL_MENU_W = 115;
    public static final int DROP_DOWN_LEVEL_MENU_H = DROP_DOWN_CHARACTER_MENU_H;

    public static final int LBL_MARGIN_BOTTOM = 20;
    public static final int LBL_TITLE_X = 83;
    public static final int LBL_TITLE_Y = 65;
    public static final int LBL_TITLE_W = 300;
    public static final int LBL_TITLE_H = 37;
    public static final int LBL_TITLE_FONT_SIZE = 32;

    public static final int LBL_SHOW_X = DROP_DOWN_CHARACTER_MENU_X;
    public static final int LBL_SHOW_Y = DROP_DOWN_CHARACTER_MENU_Y - LBL_MARGIN_BOTTOM;
    public static final int LBL_SHOW_W = DROP_DOWN_CHARACTER_MENU_W;
    public static final int LBL_SHOW_H = DROP_DOWN_CHARACTER_MENU_H;
    public static final int LBL_SHOW_FONT_SIZE = 16;

    public static final int TF_MIN_CD_X = DROP_DOWN_LEVEL_MENU_X + DROP_DOWN_LEVEL_MENU_W + 15;
    public static final int TF_MIN_CD_Y = DROP_DOWN_CHARACTER_MENU_Y + 5;
    public static final int TF_MIN_CD_W = 80;
    public static final int TF_MIN_CD_H = 20;

    public static final int TF_MIN_DMG_X = TF_MIN_CD_X + 63 + 35;
    public static final int TF_MIN_DMG_Y = TF_MIN_CD_Y;
    public static final int TF_MIN_DMG_W = TF_MIN_CD_W;
    public static final int TF_MIN_DMG_H = TF_MIN_CD_H;

    public static final int BTN_APPLY_X = 640;
    public static final int BTN_APPLY_Y = 125;
    public static final int BTN_APPLY_W = 80;
    public static final int BTN_APPLY_H = 50;

    public static final int ABILITIES_PANEL_IMAGE_X = 50;
    public static final int ABILITIES_PANEL_IMAGE_Y = 177;

    public BufferedImage bgImage;
    public BufferedImage abilitiesPanelImage;
    public BufferedImage applyBtnImage;
//    public BufferedImage sparksAndSplashImage;
//    public BufferedImage preserverOfFortuneImage;
//    public BufferedImage riffRevolutionImage;
//    public BufferedImage baneOfAllEvilImage;
//    public BufferedImage fateImage;


    public Font lstFont = new Font("Arial", Font.PLAIN, 18);
    public JLabel lblTitle;
    public JLabel show;
    public JTextArea taSparksAndSplash;
    public JTextArea taPreserverOfFortune;
    public JTextArea taRiffRevolution;
    public JTextArea taBaneOfAllEvil;
    public JTextArea taFate;

    public HashMap<Integer, String[]> abilitiesMap;
    public String sasList[];
    public String pofList[];
    public String rrList[];
    public String boaList[];
    public String fList[];

    public JList lstSparksAndSplash;
    public JList lstPreserverOfFortune;
    public JList lstRiffRevolution;
    public JList lstBaneOfAllEvil;
    public JList lstFate;
//    public JLabel lblOwner;
//    public JLabel lblLevel;
//    public JLabel lblCD;
//    public JLabel lblDMG;
    public JCheckBox cbOwner;
    public JCheckBox cbLevel;
    public JCheckBox cbCD;
    public JCheckBox cbDMG;

    public JButton btnApply;

    public Vector<Abilities> abilities;

    public GUIAbilitiesPage() {
        setLayout(null);
        this.setBackground(new Color(255,255,255));
        this.setBounds(0, 0, W, H);
        Main.frame.add(this, 0);

        abilities = null;

        abilitiesMap = new HashMap<Integer, String[]>();

//        pofList = new String[]{"owner:", "", "level:", "", "cd:", "", "dmg", ""};
        sasList = new String[]{"", "", "", ""};
        pofList = new String[]{"", "", "", ""};
        rrList = new String[]{"", "", "", ""};
        boaList = new String[]{"", "", "", ""};
        fList = new String[]{"", "", "", ""};

        abilitiesMap.put(SPARKS_AND_SPLASH, sasList);
        abilitiesMap.put(PRESERVER_OF_FORTUNE, pofList);
        abilitiesMap.put(RIFF_REVOLUTION, rrList);
        abilitiesMap.put(BAND_OF_ALL_EVIL, boaList);
        abilitiesMap.put(FATE, fList);

        lstSparksAndSplash = new JList(abilitiesMap.get(0));
        lstSparksAndSplash.setBounds(SPARKS_AND_SPLASH_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstSparksAndSplash.setFont(lstFont);
        this.add(lstSparksAndSplash);

        abilitiesMap.get(0)[0] = "v";
        lstPreserverOfFortune = new JList(abilitiesMap.get(1));
        lstPreserverOfFortune.setBounds(PRESERVER_OF_FORTUNE_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstPreserverOfFortune.setFont(lstFont);
        this.add(lstPreserverOfFortune);

        lstRiffRevolution = new JList(abilitiesMap.get(2));
        lstRiffRevolution.setBounds(RIFF_REVOLUTION_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstRiffRevolution.setFont(lstFont);
        this.add(lstRiffRevolution);

        lstBaneOfAllEvil = new JList(abilitiesMap.get(3));
        lstBaneOfAllEvil.setBounds(BAND_OF_ALL_EVIL_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstBaneOfAllEvil.setFont(lstFont);
        this.add(lstBaneOfAllEvil);

        lstFate = new JList(abilitiesMap.get(4));
        lstFate.setBounds(FATE_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstFate.setFont(lstFont);
        this.add(lstFate);

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

        try { // apply button image
            applyBtnImage = ImageIO.read(new File(APPLY_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + APPLY_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

//        try { // icon images
//            sparksAndSplashImage = ImageIO.read(new File(SPARKS_AND_SPLASH_IMAGE_FILENAME));
//            preserverOfFortuneImage = ImageIO.read(new File(PRESERVER_OF_FORTUNE_PANEL_FILENAME));
//            riffRevolutionImage = ImageIO.read(new File(RIFF_REVOLUTION_IMAGE_FILENAME));
//            baneOfAllEvilImage = ImageIO.read(new File(BAND_OF_ALL_EVIL_IMAGE_FILENAME));
//            fateImage = ImageIO.read(new File(FATE_IMAGE_FILENAME));
//        } catch (IOException e) {
//            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file for ability icon not found");
//            System.exit(1);
//        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------

        lblTitle = new JLabel("Abilities");
        lblTitle.setBounds(LBL_TITLE_X, LBL_TITLE_Y, LBL_TITLE_W, LBL_TITLE_H);
        lblTitle.setFont(new Font("Helvetica", BOLD, LBL_TITLE_FONT_SIZE));
        lblTitle.setForeground(Color.white);
        this.add(lblTitle);

        show = new JLabel("Show:");
        show.setBounds(LBL_SHOW_X, LBL_SHOW_Y, LBL_SHOW_W, LBL_SHOW_H);
        show.setFont(new Font("Helvetica", Font.PLAIN, LBL_SHOW_FONT_SIZE));
        show.setForeground(Color.white);
        this.add(show);

        taSparksAndSplash = new JTextArea();
        taPreserverOfFortune = new JTextArea();
        taRiffRevolution = new JTextArea();
        taBaneOfAllEvil = new JTextArea();
        taFate = new JTextArea();

        cbOwner = new JCheckBox("owner");
        cbOwner.setBounds(DROP_DOWN_CHARACTER_MENU_X, DROP_DOWN_CHARACTER_MENU_Y, DROP_DOWN_CHARACTER_MENU_W, DROP_DOWN_CHARACTER_MENU_H);
        this.add(cbOwner);

        cbLevel = new JCheckBox("level");
        cbLevel.setBounds(DROP_DOWN_LEVEL_MENU_X, DROP_DOWN_LEVEL_MENU_Y, DROP_DOWN_LEVEL_MENU_W, DROP_DOWN_LEVEL_MENU_H);
        this.add(cbLevel);

        cbCD = new JCheckBox("CD");
        cbCD.setBounds(TF_MIN_CD_X, TF_MIN_CD_Y, TF_MIN_CD_W, TF_MIN_CD_H);
        this.add(cbCD);

        cbDMG = new JCheckBox("DMG");
        cbDMG.setBounds(TF_MIN_DMG_X, TF_MIN_DMG_Y, TF_MIN_DMG_W, TF_MIN_DMG_H);
        this.add(cbDMG);


        btnApply = new JButton(new ImageIcon(applyBtnImage));
        btnApply.setBounds(BTN_APPLY_X, BTN_APPLY_Y, BTN_APPLY_W, BTN_APPLY_H);
        btnApply.setBorder(BorderFactory.createEmptyBorder());
        btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: filter abilities according to properties specified
//                tfCD.getText(), tfDMG.getText();
                // check which JCheckBoxes are checked off

                // insert newPlayer (handled in DatabaseConnectionHandler.java)
//                try {
//                    Main.dbHandler.showAbilitiesProperties(cbOwner.isSelected(), cbLevel.isSelected(), cbCD.isSelected(), cbDMG.isSelected());
//                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
            }
        });
        this.add(btnApply);

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

//        g.drawImage(sparksAndSplashImage, SPARKS_AND_SPLASH_ICON_X, ICONS_Y, null);
//        g.drawImage(preserverOfFortuneImage, PRESERVER_OF_FORTUNE_ICON_X, ICONS_Y, null);
//        g.drawImage(riffRevolutionImage, RIFF_REVOLUTION_ICON_X, ICONS_Y, null);
//        g.drawImage(baneOfAllEvilImage, BAND_OF_ALL_EVIL_ICON_X, ICONS_Y, null);
//        g.drawImage(fateImage, FATE_ICON_X, ICONS_Y, null);

        paintComponents(g);
    }
}
