package ui;

import controller.Main;
import database.DatabaseConnectionHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIPartiesPage extends JPanel {
    Graphics g = null;
    private DatabaseConnectionHandler dbHandler = null;
    public static final String BACKGROUND_IMAGE_FILENAME = "images/party_screen_bg.png";
//    public static final String PARTIES_PANEL_FILENAME = "images/party_page_parties.png";
//    public static final String CHARACTERS_PANEL_FILENAME = "images/party_page_characters.png";

    public static final int W = GUIMainPage.W;
    public static final int H = GUIMainPage.H;

    public static final int LBL_SHOW_W = 50;
    public static final int LBL_SHOW_H = 25;
    public static final int LBL_SHOW_X = 595;
    public static final int LBL_SHOW_Y = 108;

    public static final int LBL_PARTIES_PANEL_X = 165;
    public static final int LBL_PARTIES_PANEL_Y = 46;
    public static final int LBL_PARTIES_PANEL_W = 110;
    public static final int LBL_PARTIES_PANEL_H = 40;

    public static final int LBL_CHARACTERS_PANEL_X = 551;
    public static final int LBL_CHARACTERS_PANEL_Y = LBL_PARTIES_PANEL_Y;
    public static final int LBL_CHARACTERS_PANEL_W = 170;
    public static final int LBL_CHARACTERS_PANEL_H = LBL_PARTIES_PANEL_H;

    public static final int LBL_PARTY1_X = 110;
    public static final int LBL_PARTY1_Y = 170;
    public static final int LBL_PARTY1_W = 150;
    public static final int LBL_PARTY1_H = 30;
    public static final int LBL_PARTIES_MARGIN_BOTTOM = 61;
    public static final int LBL_PARTIES_FONT_SIZE = 22;

    public static final int LBL_MAX_LEVEL_X = 302;
    public static final int LBL_MAX_LEVEL_Y = LBL_PARTY1_Y;
    public static final int LBL_MAX_LEVEL_W = 30;
    public static final int LBL_MAX_LEVEL_H = 30;
    public static final int LBL_MAX_LEVEL_FONT_SIZE = LBL_PARTIES_FONT_SIZE;

    public static final int LBL_CHARACTER1_X = 528;
    public static final int LBL_CHARACTER1_Y = 155;
    public static final int LBL_CHARACTER1_W = 150;
    public static final int LBL_CHARACTER1_H = 30;
    public static final int LBL_CHARACTERS_MARGIN_BOTTOM = 78;
    public static final int LBL_CHARACTERS_FONT_SIZE = 20;

    public static final int LBL_NUM_PARTIES_X = 710;
    public static final int LBL_NUM_PARTIES_Y = LBL_CHARACTER1_Y;
    public static final int LBL_NUM_PARTIES_W = 30;
    public static final int LBL_NUM_PARTIES_H = 30;
    public static final int LBL_NUM_PARTIES_FONT_SIZE = LBL_PARTIES_FONT_SIZE;

    public static final int CB_NUM_PARTIES_X = 644;
    public static final int CB_NUM_PARTIES_Y = LBL_SHOW_Y;
    public static final int CB_NUM_PARTIES_W = 138;
    public static final int CB_NUM_PARTIES_H = 20;
    public static final int CB_NUM_PARTIES_FONT_SIZE = 16;

    public static final int LBL_PARTY_ORDER_BY_X = 90;
    public static final int LBL_PARTY_ORDER_BY_Y = 110;
    public static final int LBL_PARTY_ORDER_BY_W = 60;
    public static final int LBL_PARTY_ORDER_BY_H = 30;

    public static final int CB_PARTY_OPTIONS_X = LBL_PARTY_ORDER_BY_X + LBL_PARTY_ORDER_BY_W + 8;
    public static final int CB_PARTY_OPTIONS_Y = LBL_PARTY_ORDER_BY_Y;
    public static final int CB_PARTY_OPTIONS_W = 120;
    public static final int CB_PARTY_OPTIONS_H = 30;

    public static final int LBL_MIN_MAX_LEVEL_BY_X = CB_PARTY_OPTIONS_X + CB_PARTY_OPTIONS_W + 10;
    public static final int LBL_MIN_MAX_LEVEL_BY_Y = CB_PARTY_OPTIONS_Y;
    public static final int LBL_MIN_MAX_LEVEL_BY_W = 30;
    public static final int LBL_MIN_MAX_LEVEL_BY_H = 30;

    public static final int TF_MIN_MAX_LEVEL_BY_X = LBL_MIN_MAX_LEVEL_BY_X + LBL_MIN_MAX_LEVEL_BY_W + 2;
    public static final int TF_MIN_MAX_LEVEL_BY_Y = LBL_MIN_MAX_LEVEL_BY_Y;
    public static final int TF_MIN_MAX_LEVEL_BY_W = 40;
    public static final int TF_MIN_MAX_LEVEL_BY_H = 30;

    public BufferedImage bgImage;
    public BufferedImage backBtnImage;

    public JComboBox<String> partyOptions;

    public JLabel partyLabels[];
    public JLabel maxLevelLabels[];
    public JLabel characterLabels[];
    public JLabel numPartyLabels[];

    public JLabel lblPartiesTitle;
    public JLabel lblCharactersTitle;
    public JLabel lblShow;
    public JLabel lblMin;
    public JLabel lblOrderBy;

    public JTextField tfMinMaxLevel;
    public JCheckBox cbShowNumParties;

    public JButton btnBack;

    public GUIPartiesPage() {
        setLayout(null);
        this.setBackground(new Color(255,255,255));
        this.setBounds(0, 0, W, H);
        Main.frame.add(this, 0);

        //---------------------------------------------------------------------
        // read images
        //---------------------------------------------------------------------
        try { // background image
            bgImage = ImageIO.read(new File(BACKGROUND_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIPartiesPage::GUIPartiesPage(): error: file not found: " + BACKGROUND_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // back button image
            backBtnImage = ImageIO.read(new File(GUIMainPage.BACK_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIPartiesPage::GUIPartiesPage(): error: file not found: " + GUIMainPage.BACK_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

//        try { // parties panel image
//            partiesPanelImage = ImageIO.read(new File(PARTIES_PANEL_FILENAME));
//        } catch (IOException e) {
//            System.out.println("GUIPartiesPage::GUIPartiesPage(): error: file not found: " + PARTIES_PANEL_FILENAME);
//            System.exit(1);
//        }
//
//        try { // characters panel image
//            charactersPanelImage = ImageIO.read(new File(CHARACTERS_PANEL_FILENAME));
//        } catch (IOException e) {
//            System.out.println("GUIPartiesPage::GUIPartiesPage(): error: file not found: " + CHARACTERS_PANEL_FILENAME);
//            System.exit(1);
//        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------

        partyLabels = new JLabel[4];
        maxLevelLabels = new JLabel[4];
        characterLabels = new JLabel[5];
        numPartyLabels = new JLabel[5];

        lblPartiesTitle = new JLabel("Parties");
        lblPartiesTitle.setBounds(LBL_PARTIES_PANEL_X, LBL_PARTIES_PANEL_Y, LBL_PARTIES_PANEL_W, LBL_PARTIES_PANEL_H);
        lblPartiesTitle.setFont(new Font("Helvetica", Font.BOLD, 30));
        lblPartiesTitle.setForeground(Color.white);
        this.add(lblPartiesTitle);

        lblCharactersTitle = new JLabel("Characters");
        lblCharactersTitle.setBounds(LBL_CHARACTERS_PANEL_X, LBL_CHARACTERS_PANEL_Y, LBL_CHARACTERS_PANEL_W, LBL_CHARACTERS_PANEL_H);
        lblCharactersTitle.setFont(new Font("Helvetica", Font.BOLD, 30));
        lblCharactersTitle.setForeground(Color.white);
        this.add(lblCharactersTitle);

        lblOrderBy = new JLabel("order by:");
        lblOrderBy.setBounds(LBL_PARTY_ORDER_BY_X, LBL_PARTY_ORDER_BY_Y, LBL_PARTY_ORDER_BY_W, LBL_PARTY_ORDER_BY_H);
        lblOrderBy.setFont(new Font("Helvetica", Font.PLAIN, 15));
        lblOrderBy.setForeground(Color.white);
        this.add(lblOrderBy);

        lblMin = new JLabel("min:");
        lblMin.setBounds(LBL_MIN_MAX_LEVEL_BY_X, LBL_MIN_MAX_LEVEL_BY_Y, LBL_MIN_MAX_LEVEL_BY_W, LBL_MIN_MAX_LEVEL_BY_H);
        lblMin.setFont(new Font("Helvetica", Font.PLAIN, 15));
        lblMin.setForeground(Color.white);
        this.add(lblMin);

        lblShow = new JLabel("Show:");
        lblShow.setBounds(LBL_SHOW_X, LBL_SHOW_Y, LBL_SHOW_W, LBL_SHOW_H);
        lblShow.setFont(new Font("Helvetica", Font.PLAIN, 15));
        lblShow.setForeground(Color.white);
        this.add(lblShow);

        String partyNames[] = {"animalParty", "battleTeam", "mainParty", "theBestTeam"};
        // party name labels
        for (int p = 0; p < partyNames.length; p++) {
            JLabel party = new JLabel(partyNames[p]);
            if (p == 0) {
                party.setBounds(LBL_PARTY1_X, LBL_PARTY1_Y, LBL_PARTY1_W, LBL_PARTY1_H);
            }
            else {
                party.setBounds(LBL_PARTY1_X, partyLabels[p-1].getY() + partyLabels[p-1].getHeight() + LBL_PARTIES_MARGIN_BOTTOM, LBL_PARTY1_W, LBL_PARTY1_H);
            }
            party.setFont(new Font("Times New Roman", Font.PLAIN, LBL_PARTIES_FONT_SIZE));
            party.setForeground(Color.orange);
            this.add(party);
            partyLabels[p] = party;
        }

        String emptyStrings[] = {" ", " ", " ", " ", " "};
        // max level labels
        for (int l = 0; l < emptyStrings.length - 1; l++) {
            JLabel maxLevel = new JLabel(emptyStrings[l]);
            if (l == 0) {
                maxLevel.setBounds(LBL_MAX_LEVEL_X, LBL_MAX_LEVEL_Y, LBL_MAX_LEVEL_W, LBL_MAX_LEVEL_H);
            }
            else {
                maxLevel.setBounds(LBL_MAX_LEVEL_X, partyLabels[l].getY(), LBL_MAX_LEVEL_W, LBL_MAX_LEVEL_H);
            }
            maxLevel.setFont(new Font("Times New Roman", Font.PLAIN, LBL_PARTIES_FONT_SIZE));
            maxLevel.setForeground(Color.white);
            this.add(maxLevel);
            maxLevelLabels[l] = maxLevel;
        }

        String characterNames[] = {"Xinyan", "Xiao", "Mona", "Klee", "Qiqi"};
        // character name labels
        for (int c = 0; c < characterLabels.length; c++) {
            JLabel character = new JLabel(characterNames[c]);
            if (c == 0) {
                character.setBounds(LBL_CHARACTER1_X, LBL_CHARACTER1_Y, LBL_CHARACTER1_W, LBL_CHARACTER1_H);
            }
            else {
                character.setBounds(LBL_CHARACTER1_X, characterLabels[c-1].getY() + LBL_CHARACTERS_MARGIN_BOTTOM, LBL_CHARACTER1_W, LBL_CHARACTER1_H);
            }
            character.setFont(new Font("Times New Roman", Font.PLAIN, LBL_CHARACTERS_FONT_SIZE));
            character.setForeground(Color.cyan);
            this.add(character);
            characterLabels[c] = character;
        }

        // num parties labels
        for (int n = 0; n < emptyStrings.length; n++) {
            JLabel numParties = new JLabel(emptyStrings[n]);
            if (n == 0) {
                numParties.setBounds(LBL_NUM_PARTIES_X, LBL_NUM_PARTIES_Y, LBL_NUM_PARTIES_W, LBL_NUM_PARTIES_H);
            }
            else {
                numParties.setBounds(LBL_NUM_PARTIES_X, characterLabels[n].getY(), LBL_MAX_LEVEL_W, LBL_MAX_LEVEL_H);
            }
            numParties.setFont(new Font("Times New Roman", Font.PLAIN, LBL_PARTIES_FONT_SIZE));
            numParties.setForeground(Color.white);
            this.add(numParties);
            numPartyLabels[n] = numParties;
        }

        partyOptions = new JComboBox<>(new String[]{"-------", "max character lvl"});
        partyOptions.setBounds(CB_PARTY_OPTIONS_X, CB_PARTY_OPTIONS_Y, CB_PARTY_OPTIONS_W, CB_PARTY_OPTIONS_H);
        ActionListener partyActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) partyOptions.getSelectedItem();//get the selected item

                switch (s) {//check for a match
                    case "max character lvl":
                        dbHandler.strongestCharacterLevelInParty(Main.currPlayer, 0);
                        break;
                    default:
                        for (int i = 0; i < maxLevelLabels.length; i++) {
                            maxLevelLabels[i].setText(" ");
                        }
                        break;
                }
            }
        };
        partyOptions.addActionListener(partyActionListener);
        this.add(partyOptions);

        tfMinMaxLevel = new JTextField();
        tfMinMaxLevel.setEnabled(false);
        tfMinMaxLevel.setBounds(TF_MIN_MAX_LEVEL_BY_X, TF_MIN_MAX_LEVEL_BY_Y, TF_MIN_MAX_LEVEL_BY_W, TF_MIN_MAX_LEVEL_BY_H);
        tfMinMaxLevel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (partyOptions.getSelectedItem() == "max character lvl") {
                    tfMinMaxLevel.requestFocus();
                    tfMinMaxLevel.setEnabled(true);
                }
            }
        });
        KeyListener tfKeyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && ((String)(partyOptions.getSelectedItem())) == "max character lvl") {
                    try {
                        Integer.parseInt(tfMinMaxLevel.getText());
                    } catch(NumberFormatException exc){
                        JOptionPane invalidInput = new JOptionPane();
                        invalidInput.setBounds(Main.guiCreateAccountPage.POPUP_MENU_X, Main.guiCreateAccountPage.POPUP_MENU_Y, Main.guiCreateAccountPage.POPUP_MENU_W, Main.guiCreateAccountPage.POPUP_MENU_H);
                        invalidInput.showMessageDialog(null, "Please enter a valid integer.", "Invalid Number Format",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("GUIPartiesPage::line 292: Invalid input. Please enter a number.");
                        return;
                    }
                    dbHandler.strongestCharacterLevelInParty(Main.currPlayer, Integer.parseInt(tfMinMaxLevel.getText()));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        tfMinMaxLevel.addKeyListener(tfKeyListener);
        this.add(tfMinMaxLevel);

        cbShowNumParties = new JCheckBox("# of parties");
        cbShowNumParties.setBounds(CB_NUM_PARTIES_X, CB_NUM_PARTIES_Y, CB_NUM_PARTIES_W, CB_NUM_PARTIES_H);
        cbShowNumParties.setForeground(Color.white);
        ActionListener cbActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbShowNumParties.isSelected()) {
                    dbHandler.numPartiesPerCharacter(Main.currPlayer);
                }
                else {
                    for (int i = 0; i < numPartyLabels.length; i++) {
                        numPartyLabels[i].setText(" ");
                    }
                }
            }
        };
        cbShowNumParties.addActionListener(cbActionListener);
        this.add(cbShowNumParties);

        btnBack = new JButton(new ImageIcon(backBtnImage));
        btnBack.setBounds(GUIMainPage.BTN_BACK_X, GUIMainPage.BTN_BACK_Y, GUIMainPage.BTN_BACK_W, GUIMainPage.BTN_BACK_H);
        btnBack.setBorder(BorderFactory.createEmptyBorder());
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(2);
                Main.guiPartiesPage.setVisible(false);
            }
        });
        this.add(btnBack);

        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, null);

        paintComponents(g);
    }

    public void setDbHandler(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
}
