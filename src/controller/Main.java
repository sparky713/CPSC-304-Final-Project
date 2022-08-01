package controller;

import ui.GUICreateAccountPage;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class Main {
    public static JFrame frame;
    public static GUICreateAccountPage guiCreateAccountPage;

    public static void main(String[] args) {
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
}
