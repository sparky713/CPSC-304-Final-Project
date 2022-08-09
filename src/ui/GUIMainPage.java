package ui;

import controller.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.BOLD;

public class GUIMainPage extends JPanel {
    Graphics g = null;
    public static final String BACKGROUND_IMAGE_FILENAME = "images/main_screen_bg.png";
    public static final String USER_IMAGE_FILENAME = "images/user.png";
    public static final String CHARACTERS_BTN_IMAGE_FILENAME = "images/characters_btn.png";
    public static final String PARTIES_BTN_IMAGE_FILENAME = "images/parties_btn.png";
    public static final String WEAPONS_BTN_IMAGE_FILENAME = "images/weapons_btn.png";
    public static final String ABILITIES_BTN_IMAGE_FILENAME = "images/abilities_btn.png";
    public static final String FOOD_BTN_IMAGE_FILENAME = "images/food_btn.png";
    public static final String DEMO_PLAYER_BTN_IMAGE_FILENAME = "images/demo_player_btn.png";
    public static final String EDIT_PROFILE_BTN_IMAGE_FILENAME = "images/edit_profile_btn.png";
    public static final String BACK_BTN_IMAGE_FILENAME = "images/back_btn_white.png";

    public static final int W = 850;
    public static final int H = 550;

    public static final int LEFT_FRAME_W = 248;

    public static final int BTN_BACK_X = 34;
    public static final int BTN_BACK_Y = 32;
    public static final int BTN_BACK_W = 30;
    public static final int BTN_BACK_H = 30;

    public static final int BTN_CHARACTERS_X = LEFT_FRAME_W + 87;
    public static final int BTN_CHARACTERS_Y = 90;
    public static final int BTN_CHARACTERS_W = 190;
    public static final int BTN_CHARACTERS_H = 130;

    public static final int BTN_PARTIES_X = LEFT_FRAME_W + 332;
    public static final int BTN_PARTIES_Y = BTN_CHARACTERS_Y;
    public static final int BTN_PARTIES_W = BTN_CHARACTERS_W;
    public static final int BTN_PARTIES_H = BTN_CHARACTERS_H;

    public static final int BTN_WEAPONS_X = 318;
    public static final int BTN_WEAPONS_Y = BTN_CHARACTERS_Y + 190;
    public static final int BTN_WEAPONS_W = 150;
    public static final int BTN_WEAPONS_H = 110;

    public static final int BTN_FOODS_X = BTN_WEAPONS_X + BTN_WEAPONS_W + 16;
    public static final int BTN_FOODS_Y = BTN_WEAPONS_Y;
    public static final int BTN_FOODS_W = BTN_WEAPONS_W;
    public static final int BTN_FOODS_H = BTN_WEAPONS_H;

    public static final int BTN_ABILITIES_X = BTN_FOODS_X + BTN_FOODS_W + 16;
    public static final int BTN_ABILITIES_Y = BTN_WEAPONS_Y;
    public static final int BTN_ABILITIES_W = BTN_WEAPONS_W;
    public static final int BTN_ABILITIES_H = BTN_WEAPONS_H;

    public static final int BTN_EDIT_PROFILE_X = 71;
    public static final int BTN_EDIT_PROFILE_Y = 375;
    public static final int BTN_EDIT_PROFILE_W = 104;
    public static final int BTN_EDIT_PROFILE_H = 40;

    public static final int BTN_DEMO_PLAYER_X = 67;
    public static final int BTN_DEMO_PLAYER_Y = 80;
    public static final int BTN_DEMO_PLAYER_W = 121;
    public static final int BTN_DEMO_PLAYER_H = 50;

    public static final int LBL_W = 272;
    public static final int LBL_H = 32;
    public static final int LBL_MARGIN_TOP = 15;
    public static final int LBL_DISPLAY_NAME_X = 18;
    public static final int LBL_DISPLAY_NAME_Y = 152;
    public static final int LBL_DISPLAY_NAME_W = 212;
    public static final int LBL_DISPLAY_NAME_H = 32;
    public static final int LBL_CHARACTERS_X = LEFT_FRAME_W + 125;
    public static final int LBL_CHARACTERS_Y = BTN_CHARACTERS_Y + BTN_CHARACTERS_H + LBL_MARGIN_TOP;
    public static final int LBL_PARTIES_X = LEFT_FRAME_W + 390;
    public static final int LBL_PARTIES_Y = LBL_CHARACTERS_Y;
    public static final int LBL_WEAPONS_X = BTN_WEAPONS_X + 26;
    public static final int LBL_WEAPONS_Y = BTN_WEAPONS_Y + BTN_WEAPONS_H + LBL_MARGIN_TOP;
    public static final int LBL_FOODS_X = 277 + LEFT_FRAME_W;
    public static final int LBL_FOODS_Y = LBL_WEAPONS_Y;
    public static final int LBL_ABILITIES_X = 421 + LEFT_FRAME_W;
    public static final int LBL_ABILITIES_Y = LBL_WEAPONS_Y;

    public static final int USER_IMAGE_X = 48;
    public static final int USER_IMAGE_Y = 205;

    public BufferedImage bgImage;
    public BufferedImage userImage;
    public BufferedImage charactersBtnImage;
    public BufferedImage partiesBtnImage;
    public BufferedImage weaponsBtnImage;
    public BufferedImage abilitiesBtnImage;
    public BufferedImage foodsBtnImage;
    public BufferedImage editProfileBtnImage;
    public BufferedImage demoPlayerBtnImage;
    public JLabel lblDisplayName;
    public JLabel lblCharacters;
    public JLabel lblParties;
    public JLabel lblWeapons;
    public JLabel lblFoods;
    public JLabel lblAbilities;
    public JButton btnCharacters;
    public JButton btnParties;
    public JButton btnWeapons;
    public JButton btnAbilities;
    public JButton btnFoods;
    public JButton btnEditProfile;

    public JButton switchToDemoPlayer;

    public static final int DROP_DOWN_MENU_X = 615;
    public static final int DROP_DOWN_MENU_Y = 135;
    public static final int DROP_DOWN_MENU_W = 115;
    public static final int DROP_DOWN_MENU_H = 30;

    public JComboBox<String> weaponDropDown;
    public String[] weaponTypes;

    public GUIMainPage() {
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
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + BACKGROUND_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // user image
            userImage = ImageIO.read(new File(USER_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + USER_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // characters button image
            charactersBtnImage = ImageIO.read(new File(CHARACTERS_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + CHARACTERS_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // parties button image
            partiesBtnImage = ImageIO.read(new File(PARTIES_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + PARTIES_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // weapons button image
            weaponsBtnImage = ImageIO.read(new File(WEAPONS_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + WEAPONS_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // abilities button image
            abilitiesBtnImage = ImageIO.read(new File(ABILITIES_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + ABILITIES_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // food button image
            foodsBtnImage = ImageIO.read(new File(FOOD_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + FOOD_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // edit profile button image
            editProfileBtnImage = ImageIO.read(new File(EDIT_PROFILE_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + EDIT_PROFILE_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // demo player button image
            demoPlayerBtnImage = ImageIO.read(new File(DEMO_PLAYER_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIMainPage::GUIMainPage(): error: file not found: " + DEMO_PLAYER_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------

        lblDisplayName = new JLabel("DISPLAY NAME", SwingConstants.CENTER);
        lblDisplayName.setBounds(LBL_DISPLAY_NAME_X, LBL_DISPLAY_NAME_Y, LBL_DISPLAY_NAME_W, LBL_DISPLAY_NAME_H);
        lblDisplayName.setFont(new Font("Arial", BOLD, 28));
        lblDisplayName.setForeground(Color.white);
        this.add(lblDisplayName);

        lblCharacters = new JLabel("Characters");
        lblCharacters.setBounds(LBL_CHARACTERS_X, LBL_CHARACTERS_Y, LBL_W, LBL_H);
        lblCharacters.setFont(new Font("Arial", BOLD, 20));
        lblCharacters.setForeground(Color.white);
        this.add(lblCharacters);

        lblParties = new JLabel("Parties");
        lblParties.setBounds(LBL_PARTIES_X, LBL_PARTIES_Y, LBL_W, LBL_H);
        lblParties.setFont(new Font("Arial", BOLD, 20));
        lblParties.setForeground(Color.white);
        this.add(lblParties);

        lblWeapons = new JLabel("Weapons");
        lblWeapons.setBounds(LBL_WEAPONS_X, LBL_WEAPONS_Y, LBL_W, LBL_H);
        lblWeapons.setFont(new Font("Arial", BOLD, 20));
        lblWeapons.setForeground(Color.white);
        this.add(lblWeapons);

        lblFoods = new JLabel("Foods");
        lblFoods.setBounds(LBL_FOODS_X, LBL_FOODS_Y, LBL_W, LBL_H);
        lblFoods.setFont(new Font("Arial", BOLD, 20));
        lblFoods.setForeground(Color.white);
        this.add(lblFoods);

        lblAbilities = new JLabel("Abilities");
        lblAbilities.setBounds(LBL_ABILITIES_X, LBL_ABILITIES_Y, LBL_W, LBL_H);
        lblAbilities.setFont(new Font("Arial", BOLD, 20));
        lblAbilities.setForeground(Color.white);
        this.add(lblAbilities);

        switchToDemoPlayer = new JButton(new ImageIcon(demoPlayerBtnImage));
        switchToDemoPlayer.setBounds(BTN_DEMO_PLAYER_X, BTN_DEMO_PLAYER_Y, BTN_DEMO_PLAYER_W, BTN_DEMO_PLAYER_H);
        switchToDemoPlayer.setBorder(BorderFactory.createEmptyBorder());
        switchToDemoPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to edit profile page
                Main.currPlayer.setUserName("player2");
                lblDisplayName.setText("DonutLover");
            }
        });
        this.add(switchToDemoPlayer);

        btnCharacters = new JButton(new ImageIcon(charactersBtnImage));
        btnCharacters.setBounds(BTN_CHARACTERS_X, BTN_CHARACTERS_Y, BTN_CHARACTERS_W, BTN_CHARACTERS_H);
        btnCharacters.setBorder(BorderFactory.createEmptyBorder());
        btnCharacters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to characters page
                Main.changeScreen(8);
            }
        });
        this.add(btnCharacters);

        btnParties = new JButton(new ImageIcon(partiesBtnImage));
        btnParties.setBounds(BTN_PARTIES_X, BTN_PARTIES_Y, BTN_PARTIES_W, BTN_PARTIES_H);
        btnParties.setBorder(BorderFactory.createEmptyBorder());
        btnParties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to parties page
                Main.changeScreen(4);
            }
        });
        this.add(btnParties);

        btnWeapons = new JButton(new ImageIcon(weaponsBtnImage));
        btnWeapons.setBounds(BTN_WEAPONS_X, BTN_WEAPONS_Y, BTN_WEAPONS_W, BTN_WEAPONS_H);
        btnWeapons.setBorder(BorderFactory.createEmptyBorder());
        btnWeapons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to abilities page
                Main.changeScreen(5);
            }
        });
        this.add(btnWeapons);

        btnFoods = new JButton(new ImageIcon(foodsBtnImage));
        btnFoods.setBounds(BTN_FOODS_X, BTN_FOODS_Y, BTN_FOODS_W, BTN_FOODS_H);
        btnFoods.setBorder(BorderFactory.createEmptyBorder());
        btnFoods.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to foods page
                Main.changeScreen(10);
            }
        });
        this.add(btnFoods);

        btnAbilities = new JButton(new ImageIcon(abilitiesBtnImage));
        btnAbilities.setBounds(BTN_ABILITIES_X, BTN_ABILITIES_Y, BTN_ABILITIES_W, BTN_ABILITIES_H);
        btnAbilities.setBorder(BorderFactory.createEmptyBorder());
        btnAbilities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to abilities page
                Main.changeScreen(6);
            }
        });
        this.add(btnAbilities);

        btnEditProfile = new JButton(new ImageIcon(editProfileBtnImage));
        btnEditProfile.setBounds(BTN_EDIT_PROFILE_X, BTN_EDIT_PROFILE_Y, BTN_EDIT_PROFILE_W, BTN_EDIT_PROFILE_H);
        btnEditProfile.setBorder(BorderFactory.createEmptyBorder());
        btnEditProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // go to edit profile page
                Main.changeScreen(7);
            }
        });
        this.add(btnEditProfile);

        repaint();
    }

//    public void update() {
//        updateSignUpButton();
//    }

    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, null);
        g.drawImage(userImage, USER_IMAGE_X, USER_IMAGE_Y, null);
        paintComponents(g);
    }
}
