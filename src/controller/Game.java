package controller;

import database.DatabaseConnectionHandler;
import model.Player;
import ui.GUICreateAccountPage;

import javax.swing.*;
import java.awt.*;

public class Game {
    public static DatabaseConnectionHandler dbHandler = null;
    public static JFrame frame;
    public static GUICreateAccountPage guiCreateAccountPage;


    public Game() {
        dbHandler = new DatabaseConnectionHandler();

        frame = new JFrame("CPSC 304 Group 44 Project");
        frame.setLayout(null);
        frame.setBackground(Color.white);
        frame.setSize(GUICreateAccountPage.W, GUICreateAccountPage.H);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        guiCreateAccountPage = new GUICreateAccountPage();
        frame.add(guiCreateAccountPage, 0);
        guiCreateAccountPage.setVisible(true);

        frame.setVisible(true);
    }

    /**
     * TermainalTransactionsDelegate Implementation (from the initial sample code)
     *
     * Insert a branch with the given info
     */
//    public void insertPlayer(Player player) {
//        dbHandler.insertPlayer(player);
//    }

    public static void main(String args[]) {
        Game game = new Game();
    }
}
