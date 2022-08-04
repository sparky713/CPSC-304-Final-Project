package controller;

import database.DatabaseConnectionHandler;
import model.Character;
import model.ElementModel;
import model.Food;
import model.Player;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class Main {
    //    public static DatabaseConnectionHandler dbHandler = null;
    public static JFrame frame;
    public static GUIMainPage guiMainPage;
    public static GUICreateAccountPage guiCreateAccountPage;
    public static GUICharactersPage guiCharactersPage;
    public static GUIPartiesPage guiPartiesPage;
    public static GUIWeaponsPage guiWeaponsPage;
    public static GUIAbilitiesPage guiAbilitiesPage;
    public static void main(String[] args) {

        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        dbHandler.login("ORA", "???");

        //creating test player
        Player player1 = new Player("player1", "password123", "player1@gmail.com", "Tiger123");

        //adding food
        Food mushroomPizza = new Food("Mushroom Pizza", 450);


        ElementModel cryo = new ElementModel("Cryo");
        Character qiqi = new Character("Qiqi", cryo);
        qiqi.setBaseATK(42);
        qiqi.setBaseHP(100);
        qiqi.setLevel(0);

        player1.consumes(mushroomPizza, 2);

        dbHandler.databaseSetup();
        dbHandler.insertElement(cryo);
        dbHandler.insertCharacter(qiqi);

        dbHandler.insertFood(mushroomPizza);
        dbHandler.insertConsumes(player1, mushroomPizza, 2);

        dbHandler.levelCharacter("Qiqi", 5);
//
    }

    public static void changeScreen(int screenNum) {
        if (screenNum == 1) { //sign-up page
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            frame.setSize(GUICreateAccountPage.W, GUICreateAccountPage.H);
            guiCreateAccountPage.setVisible(true);
        }
        else if (screenNum == 2) {
            if (guiCreateAccountPage.isVisible()) {
                guiCreateAccountPage.setVisible(false);
            }
            frame.setSize(GUIMainPage.W, GUIMainPage.H);
            guiMainPage.setVisible(true);
        }
//        else if (screenNum == 3) {
//            if (guiMainPage.isVisible()) {
//                guiMainPage.setVisible(false);
//            }
//            guiCharactersPage.setVisible(true);
//        }
//        else if (screenNum == 4) {
//            if (guiMainPage.isVisible()) {
//                guiMainPage.setVisible(false);
//            }
//            guiPartiesPage.setVisible(true);
//        }
        else if (screenNum == 5) {
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiWeaponsPage.setVisible(true);
        }
        else if (screenNum == 6) {
            if (guiMainPage.isVisible()) {
                guiMainPage.setVisible(false);
            }
            guiAbilitiesPage.setVisible(true);
        }
        else {
//            ERROR MESSAGE!!!!!!
            System.out.println("Game::changeScreen(" + screenNum + "): Error. Page Not Found");
        }
    }
}
