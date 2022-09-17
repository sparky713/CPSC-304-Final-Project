package controller;

import database.DatabaseConnectionHandler;
import model.Food;
import model.Player;
import ui.*;

import javax.swing.*;
import java.awt.*;

//TEST
public class Main {
    public static DatabaseConnectionHandler dbHandler = null;
    public static JFrame frame;
    public static GUIMainPage guiMainPage;
    public static GUICreateAccountPage guiCreateAccountPage;
    public static GUICharactersPage guiCharactersPage;
    public static GUIPartiesPage guiPartiesPage;
    public static GUIWeaponsPage guiWeaponsPage;
    public static GUIAbilitiesPage guiAbilitiesPage;
    public static GUIEditProfilePage guiEditProfilePage;

    public static GUICharacterByPlayerPage guiCharacterByPlayerPage;
    public static GUIFoodPage guiFoodPage;

    public static GUINestedAggregationPage guiNestedAggregationPage;

    public static Player currPlayer;

    public static void main(String[] args) {

        // the testing code has moved to test.DatabaseTest to keep main from getting cluttered

        //----------------------------------------------------------------------
        // Database Setup
        // ---------------------------------------------------------------------

        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        dbHandler.login("ORA_", "a");

        //---------------------------------------------------------------------
        // GUI Setup
        //---------------------------------------------------------------------
        currPlayer = null;
        frame = new JFrame("CPSC 304 Group 44 Project");
        frame.setLayout(null);
        frame.setBackground(Color.white);
        frame.setSize(GUICreateAccountPage.W, GUICreateAccountPage.H);
//        frame.setSize(GUIMainPage.W, GUIMainPage.H);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        guiMainPage = new GUIMainPage();
        guiCreateAccountPage = new GUICreateAccountPage();
        guiCreateAccountPage.setDbHandler(dbHandler);
        guiCharactersPage = new GUICharactersPage();
        guiPartiesPage = new GUIPartiesPage();
        guiPartiesPage.setDbHandler(dbHandler);
        guiWeaponsPage = new GUIWeaponsPage();
        guiAbilitiesPage = new GUIAbilitiesPage();
        guiAbilitiesPage.setDbHandler(dbHandler);
        guiEditProfilePage = new GUIEditProfilePage(dbHandler);
        guiCharacterByPlayerPage = new GUICharacterByPlayerPage(dbHandler);
        guiFoodPage = new GUIFoodPage(dbHandler);

        guiNestedAggregationPage = new GUINestedAggregationPage(dbHandler);

        guiMainPage.setVisible(false);
        guiCreateAccountPage.setVisible(true);
        guiCharacterByPlayerPage.setVisible(false);
        guiFoodPage.setVisible(false);
        guiNestedAggregationPage.setVisible(false);

        guiCharactersPage.setVisible(false);
        guiPartiesPage.setVisible(false);
        guiWeaponsPage.setVisible(false);
        guiAbilitiesPage.setVisible(false);
        guiEditProfilePage.setVisible(false);

        frame.setVisible(true);

    }

    public static void changeScreen(int screenNum) {
        if (screenNum == 1) { //sign-up page
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            frame.setSize(GUICreateAccountPage.W, GUICreateAccountPage.H);
            guiCreateAccountPage.setVisible(true);
        } else if (screenNum == 2) { // main page
            if (guiCreateAccountPage.isVisible()) {
                guiCreateAccountPage.setVisible(false);
            }
            frame.setSize(GUIMainPage.W, GUIMainPage.H);
            guiMainPage.setVisible(true);
        }
//        else if (screenNum == 3) { // characters page
//            if (guiMainPage.isVisible()) {
//                guiMainPage.setVisible(false);
//            }
//            guiCharactersPage.setVisible(true);
//        }
        else if (screenNum == 4) { // parties page
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiPartiesPage.setVisible(true);
        } else if (screenNum == 5) { // weapons page
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiWeaponsPage.setVisible(true);
        } else if (screenNum == 6) { // abilities page
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiAbilitiesPage.setVisible(true);
        } else if (screenNum == 7) { // edit profile page
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiEditProfilePage.setVisible(true);
        } else if (screenNum == 8) { //friends page
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiCharacterByPlayerPage.setVisible(true);
        } else if (screenNum == 9) {
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiNestedAggregationPage.setVisible(true);
        }
        else if (screenNum == 10) {
            if(guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiFoodPage.setVisible(true);
        } else {
//            ERROR MESSAGE!!!!!!
            System.out.println("Game::changeScreen(" + screenNum + "): Error. Page Not Found");
        }
    }
}
