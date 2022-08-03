package controller;

import database.DatabaseConnectionHandler;
import model.Character;
import model.ElementModel;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        dbHandler.login("ORA_SCW2018", "a13454772");

        ElementModel cryo = new ElementModel("Cryo");
        Character qiqi = new Character("Qiqi", cryo);
        qiqi.setBaseATK(42);
        qiqi.setBaseHP(100);
        qiqi.setLevel(0);

        dbHandler.databaseSetup();
        dbHandler.insertElement(cryo);
        dbHandler.insertCharacter(qiqi);
//
        dbHandler.levelCharacter("Qiqi", 5);

    }
}
