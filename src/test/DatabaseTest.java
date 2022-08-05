package test;

import database.DatabaseConnectionHandler;
import model.Character;
import model.ElementModel;
import model.Food;
import model.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

// test suite for the database class
public class DatabaseTest {
    static DatabaseConnectionHandler dbHandler = null;

    @BeforeAll
    static void runBefore() {
        dbHandler = new DatabaseConnectionHandler();
        dbHandler.login("ORA_<CWL>", "a<SNUM>");
    }

    @AfterAll
   static void runAfter() {
        dbHandler.close();
    }

    @Test
    void testFood() {
        //creating test player
        Player player1 = new Player("player1", "password123", "player1@gmail.com", "Tiger123");

        //adding food
        Food mushroomPizza = new Food("Mushroom Pizza", 450);
        player1.consumes(mushroomPizza, 2);

        dbHandler.insertPlayer(player1);

        dbHandler.insertFood(mushroomPizza);
        dbHandler.insertConsumes(player1, mushroomPizza, 2);

        // uncomment this to test deleteConsumes method AFTER testing
        // insertFood method first VVVVVVV

        // dbHandler.deleteConsumes("player1", "Mushroom Pizza");

    }

    @Test
    void testInsertCharacter() {

        ElementModel cryo = new ElementModel("Cryo");
        Character kaeya = new Character("Kaeya", cryo);
        kaeya.setBaseATK(205);
        kaeya.setBaseHP(109);
        kaeya.setLevel(0);


        dbHandler.insertElement(cryo);
        dbHandler.insertCharacter(kaeya);


    }

}
