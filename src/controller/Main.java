package controller;

import database.DatabaseConnectionHandler;
import model.Character;
import model.ElementModel;
import model.Food;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        DatabaseConnectionHandler dbHandler = new DatabaseConnectionHandler();
        dbHandler.login("ORA_jmann507", "???");

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


    }
}
