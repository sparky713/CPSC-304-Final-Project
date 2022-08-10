package ui;

import controller.Main;
import database.DatabaseConnectionHandler;
import model.Abilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

    public static final int ICONS_Y = 207;
    public static final int ICONS_W = 68;
    public static final int ICONS_MARGIN = 52;
    public static final int SPARKS_AND_SPLASH_ICON_X = 145;
    public static final int PRESERVER_OF_FORTUNE_ICON_X = SPARKS_AND_SPLASH_ICON_X + ICONS_W + ICONS_MARGIN;
    public static final int RIFF_REVOLUTION_ICON_X = PRESERVER_OF_FORTUNE_ICON_X + ICONS_W + ICONS_MARGIN;
    public static final int BAND_OF_ALL_EVIL_ICON_X = RIFF_REVOLUTION_ICON_X + ICONS_W + ICONS_MARGIN;
    public static final int FATE_ICON_X = BAND_OF_ALL_EVIL_ICON_X + ICONS_W + ICONS_MARGIN;

    public static final int LIST_Y = 335;
    public static final int LIST_W = 100;
    public static final int LIST_H = 145;
    public static final int LIST_MARGIN = 46;
    public static final int SPARKS_AND_SPLASH_LIST_X = 78;
    public static final int PRESERVER_OF_FORTUNE_LIST_X = SPARKS_AND_SPLASH_LIST_X + LIST_W + LIST_MARGIN;
    public static final int RIFF_REVOLUTION_LIST_X = PRESERVER_OF_FORTUNE_LIST_X + LIST_W + LIST_MARGIN;
    public static final int BAND_OF_ALL_EVIL_LIST_X = RIFF_REVOLUTION_LIST_X + LIST_W + LIST_MARGIN;
    public static final int FATE_LIST_X = BAND_OF_ALL_EVIL_LIST_X + LIST_W + LIST_MARGIN;

    public static final int LBL_MARGIN_RIGHT = 12;
    public static final int LBL_TITLE_X = 83;
    public static final int LBL_TITLE_Y = 65;
    public static final int LBL_TITLE_W = 300;
    public static final int LBL_TITLE_H = 37;
    public static final int LBL_TITLE_FONT_SIZE = 32;

    public static final int ABILITIES_PANEL_IMAGE_X = 50;
    public static final int ABILITIES_PANEL_IMAGE_Y = 165;

    public static final int CHECK_BOX_MARGIN = 10;

    public static final int DROP_DOWN_CHARACTER_MENU_X = 300;
    public static final int DROP_DOWN_CHARACTER_MENU_Y = ABILITIES_PANEL_IMAGE_Y - 40;
    public static final int DROP_DOWN_CHARACTER_MENU_W = 80;
    public static final int DROP_DOWN_CHARACTER_MENU_H = 30;

    public static final int DROP_DOWN_LEVEL_MENU_X = DROP_DOWN_CHARACTER_MENU_X + DROP_DOWN_CHARACTER_MENU_W + CHECK_BOX_MARGIN;
    public static final int DROP_DOWN_LEVEL_MENU_Y = DROP_DOWN_CHARACTER_MENU_Y;
    public static final int DROP_DOWN_LEVEL_MENU_W = DROP_DOWN_CHARACTER_MENU_W;
    public static final int DROP_DOWN_LEVEL_MENU_H = DROP_DOWN_CHARACTER_MENU_H;

    public static final int LBL_SHOW_W = 50;
    public static final int LBL_SHOW_H = DROP_DOWN_CHARACTER_MENU_H;
    public static final int LBL_SHOW_X = DROP_DOWN_CHARACTER_MENU_X - LBL_SHOW_W - LBL_MARGIN_RIGHT;
    public static final int LBL_SHOW_Y = DROP_DOWN_CHARACTER_MENU_Y;

    public static final int LBL_SHOW_FONT_SIZE = 16;

    public static final int TF_MIN_CD_X = DROP_DOWN_LEVEL_MENU_X + DROP_DOWN_LEVEL_MENU_W + CHECK_BOX_MARGIN;
    public static final int TF_MIN_CD_Y = DROP_DOWN_CHARACTER_MENU_Y + 5;
    public static final int TF_MIN_CD_W = DROP_DOWN_CHARACTER_MENU_W;
    public static final int TF_MIN_CD_H = 20;

    public static final int TF_MIN_DMG_X = TF_MIN_CD_X + TF_MIN_CD_W + CHECK_BOX_MARGIN;
    public static final int TF_MIN_DMG_Y = TF_MIN_CD_Y;
    public static final int TF_MIN_DMG_W = DROP_DOWN_CHARACTER_MENU_W;
    public static final int TF_MIN_DMG_H = TF_MIN_CD_H;

    public static final int BTN_APPLY_X = 680;
    public static final int BTN_APPLY_Y = 116;
    public static final int BTN_APPLY_W = 80;
    public static final int BTN_APPLY_H = 50;

    public static final String DEFAULT_STRING = "-------";

    public BufferedImage bgImage;
    public BufferedImage abilitiesPanelImage;
    public BufferedImage applyBtnImage;
    public BufferedImage backBtnImage;
//    public BufferedImage sparksAndSplashImage;
//    public BufferedImage preserverOfFortuneImage;
//    public BufferedImage riffRevolutionImage;
//    public BufferedImage baneOfAllEvilImage;
//    public BufferedImage fateImage;


    public Font lstFont = new Font("Arial", Font.PLAIN, 13);
    public JLabel lblTitle;
    public JLabel show;

    public String emptyList[];

    DefaultListModel<String> sasList;
    DefaultListModel<String> pofList;
    DefaultListModel<String> rrList;
    DefaultListModel<String> boaList;
    DefaultListModel<String> fList;

    public DefaultListModel deafultListModels[];
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
    public JButton btnBack;

    public Vector<Abilities> abilities;
    private DatabaseConnectionHandler dbHandler = null;

    public GUIAbilitiesPage() {
        setLayout(null);
        this.setBackground(new Color(255,255,255));
        this.setBounds(0, 0, W, H);
        Main.frame.add(this, 0);

        abilities = null;

//        pofList = new String[]{" ", " ", " ", " "};
        sasList = new DefaultListModel<>();
        pofList = new DefaultListModel<>();
        rrList = new DefaultListModel<>();
        boaList = new DefaultListModel<>();
        fList = new DefaultListModel<>();
        emptyList = new String[]{"owner:", DEFAULT_STRING, "level:", DEFAULT_STRING, "cd:", DEFAULT_STRING, "dmg", DEFAULT_STRING};

        for (String val : emptyList) {
            sasList.addElement(val);
        }
        for (String val : emptyList) {
            pofList.addElement(val);
        }
        for (String val : emptyList) {
            rrList.addElement(val);
        }
        for (String val : emptyList) {
            boaList.addElement(val);
        }
        for (String val : emptyList) {
            fList.addElement(val);
        }

//        lstSparksAndSplash = new JList(abilitiesMap.get(0));
        lstSparksAndSplash = new JList(sasList);
        lstSparksAndSplash.setBounds(SPARKS_AND_SPLASH_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstSparksAndSplash.setFont(lstFont);
        lstSparksAndSplash.setBackground(Color.lightGray);
        this.add(lstSparksAndSplash);

//        lstPreserverOfFortune = new JList(abilitiesMap.get(1));
        lstPreserverOfFortune = new JList(pofList);
        lstPreserverOfFortune.setBounds(PRESERVER_OF_FORTUNE_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstPreserverOfFortune.setFont(lstFont);
        this.add(lstPreserverOfFortune);

//        lstRiffRevolution = new JList(abilitiesMap.get(2));
        lstRiffRevolution = new JList(rrList);
        lstRiffRevolution.setBounds(RIFF_REVOLUTION_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstRiffRevolution.setFont(lstFont);
        lstRiffRevolution.setBackground(Color.lightGray);
        this.add(lstRiffRevolution);

//        lstBaneOfAllEvil = new JList(abilitiesMap.get(3));
        lstBaneOfAllEvil = new JList(boaList);
        lstBaneOfAllEvil.setBounds(BAND_OF_ALL_EVIL_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstBaneOfAllEvil.setFont(lstFont);
        this.add(lstBaneOfAllEvil);

//        lstFate = new JList(abilitiesMap.get(4));
        lstFate = new JList(fList);
        lstFate.setBounds(FATE_LIST_X, LIST_Y, LIST_W, LIST_H);
        lstFate.setFont(lstFont);
        lstFate.setBackground(Color.lightGray);
        this.add(lstFate);

        deafultListModels = new DefaultListModel[5];
        deafultListModels[0] = sasList;
        deafultListModels[1] = pofList;
        deafultListModels[2] = rrList;
        deafultListModels[3] = boaList;
        deafultListModels[4] = fList;


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

        try { // back button image
            backBtnImage = ImageIO.read(new File(GUIMainPage.BACK_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + GUIMainPage.BACK_BTN_IMAGE_FILENAME);
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
        show.setForeground(Color.black);
        this.add(show);

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
                if (cbOwner.isSelected() || cbLevel.isSelected() || cbCD.isSelected() || cbDMG.isSelected()) {
                    // check which JCheckBoxes are checked off
                    System.out.println("GUIAbilitiesPage:: " + cbOwner.isSelected() + cbLevel.isSelected() + cbCD.isSelected() + cbDMG.isSelected());
                    dbHandler.showAbilitiesProperties(cbOwner.isSelected(), cbLevel.isSelected(), cbCD.isSelected(), cbDMG.isSelected());
                }
                else {
                    for (int j = 0; j < 5; j++) {
                        for (int i = 1; i <= 7; i += 2) {
                            Main.guiAbilitiesPage.deafultListModels[j].set(i, Main.guiAbilitiesPage.DEFAULT_STRING);
                        }
                    }
                }
            }
        });
        this.add(btnApply);

        btnBack = new JButton(new ImageIcon(backBtnImage));
        btnBack.setBounds(GUIMainPage.BTN_BACK_X, GUIMainPage.BTN_BACK_Y, GUIMainPage.BTN_BACK_W, GUIMainPage.BTN_BACK_H);
        btnBack.setBorder(BorderFactory.createEmptyBorder());
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(2);
                Main.guiAbilitiesPage.setVisible(false);
            }
        });
        this.add(btnBack);

        repaint();
    }

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

    public void setDbHandler(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
}
