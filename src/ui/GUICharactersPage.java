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
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import static java.awt.Font.BOLD;

// UNUSED
public class GUICharactersPage extends JPanel {
    Graphics g = null;
    private DatabaseConnectionHandler dbHandler = null;
    public static final String BACKGROUND_IMAGE_FILENAME = "images/weapons_page_bg.png";
    public static final String ABILITIES_PANEL_FILENAME = "images/abilities_panel.png";

    public static final int W = GUIMainPage.W;
    public static final int H = GUIMainPage.H;

    public static final int LBL_MARGIN_RIGHT = 12;
    public static final int LBL_TITLE_X = 83;
    public static final int LBL_TITLE_Y = 65;
    public static final int LBL_TITLE_W = 300;
    public static final int LBL_TITLE_H = 37;
    public static final int LBL_TITLE_FONT_SIZE = 32;

    public static final int BTN_APPLY_X = 680;
    public static final int BTN_APPLY_Y = 116;
    public static final int BTN_APPLY_W = 80;
    public static final int BTN_APPLY_H = 50;

    public static final int ABILITIES_PANEL_IMAGE_X = 50;
    public static final int ABILITIES_PANEL_IMAGE_Y = 165;

    public BufferedImage bgImage;
    public BufferedImage abilitiesPanelImage;
    public BufferedImage backBtnImage;

    public Font lstFont = new Font("Arial", Font.PLAIN, 13);
    public JLabel lblTitle;
    public JLabel show;

    public JButton btnApply;
    public JButton btnBack;

    public GUICharactersPage() {
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
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + BACKGROUND_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // abilities panel image
            abilitiesPanelImage = ImageIO.read(new File(ABILITIES_PANEL_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + ABILITIES_PANEL_FILENAME);
            System.exit(1);
        }

        try { // back button image
            backBtnImage = ImageIO.read(new File(GUIMainPage.BACK_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + GUIMainPage.BACK_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------

//        lblTitle = new JLabel("Abilities");
//        lblTitle.setBounds(LBL_TITLE_X, LBL_TITLE_Y, LBL_TITLE_W, LBL_TITLE_H);
//        lblTitle.setFont(new Font("Helvetica", BOLD, LBL_TITLE_FONT_SIZE));
//        lblTitle.setForeground(Color.white);
//        this.add(lblTitle);

        btnBack = new JButton(new ImageIcon(backBtnImage));
        btnBack.setBounds(GUIMainPage.BTN_BACK_X, GUIMainPage.BTN_BACK_Y, GUIMainPage.BTN_BACK_W, GUIMainPage.BTN_BACK_H);
        btnBack.setBorder(BorderFactory.createEmptyBorder());
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(2);
                Main.guiCharactersPage.setVisible(false);
            }
        });
        this.add(btnBack);

        repaint();

        //---------------------------------------------------------------------
        // timer(thread) - to call paint()
        //---------------------------------------------------------------------
        java.util.Timer t = new Timer(true);
        t.schedule(new TimerTask() {
            public void run() {
                repaint();
            }
        }, 0, 10);
    }

    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, null);
//        g.drawImage(abilitiesPanelImage, ABILITIES_PANEL_IMAGE_X, ABILITIES_PANEL_IMAGE_Y, null);

        paintComponents(g);
    }

    public void setDbHandler(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;
    }
}
