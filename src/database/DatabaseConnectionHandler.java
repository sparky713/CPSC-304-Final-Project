package database;

import model.*;
import model.Character;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//import model.PlayerModel;

/**
 * This class handles all database related transactions
 * Based off of CPSC304_sample_project given in tutorial 6
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            if (connection == null) {
                System.out.println("\nConnection failed!");
            } else {
                System.out.println("\nConnected to Oracle!");
            }
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // sets up all the tables in the database
    public void databaseSetup() {
        dropTablesIfExists();
        setupElement();
        setupCharacter();
        setupFood();
        setupConsumes();
        setupPlayer();

    }

    // clears the database of tables with the same name
    private void dropTablesIfExists() {
        try {
            String query = "select table_name from user_tables";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            ps.execute("DROP TABLE Character");
            ps.execute("DROP TABLE Element");
            ps.execute("DROP TABLE CharacterHP");

//            while (rs.next()) {
//                String tableName = rs.getString(1).toLowerCase();
//                System.out.println(tableName);
//


//                if (tableName.equals("character")) {
//                    ps.execute("DROP TABLE Character");
//                    System.out.println("Character table dropped!");
//                } else if (tableName.equals("element")) {
//                    ps.execute("DROP TABLE Element");
//                    System.out.println("Element table dropped!");
//                } else if (tableName.equals("characterhp")) {
//                    System.out.println("CharacterHP table dropped!");
//                    ps.execute("DROP TABLE CharacterHP");
//                }
//            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // set up the player table
    private void setupPlayer() {
        try {
            String playerQuery = "CREATE TABLE Player\n" +
                "(\n" +
                "    username char(60) PRIMARY KEY,\n" +
                "    password char(80) NOT NULL,\n" +
                "    email       char(80) NOT NULL,\n" +
                "    displayName char(80) NOT NULL,\n" +
                "    UNIQUE (email)\n" +
                ")";
            PrintablePreparedStatement psChar = new PrintablePreparedStatement(connection.prepareStatement(playerQuery), playerQuery, false);
            psChar.executeUpdate();
            psChar.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

        public void insertPlayer(Player player) {
        try {
            String playerQuery = "INSERT INTO Player (username, password, email, displayName) VALUES (?,?,?,?)";
            PrintablePreparedStatement psPlayer = new PrintablePreparedStatement(connection.prepareStatement(playerQuery), playerQuery, false);

            psPlayer.setString(1, player.getUserName());
            psPlayer.setString(2, player.getPassword());
            psPlayer.setString(3, player.getEmail());
            psPlayer.setString(4, player.getDisplayName());

            psPlayer.executeUpdate();
            connection.commit();
            psPlayer.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    // set up the abilities table
    private void setupAbility() {
        String abilitiesQuery = "CREATE TABLE Ability\n" +
                "(\n" +
                "    aname char(80) PRIMARY KEY,\n" +
                "    cname char(80) NOT NULL,\n" +
                "    level       int ,\n" +
                "    cd float,\n" +
                "    dmg int,\n" +
                "    FOREIGN KEY (cname) REFERENCES Character ON DELETE CASCADE\n" +
                ")";
        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(abilitiesQuery), abilitiesQuery, false);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        setupAbilityDMG();
    }

    public void insertAbility(Abilities abilities) {
        try {
            insertAbilityDMG(abilities.getLevel(), abilities.getDmg());
            String abilitiesQuery = "INSERT INTO Ability (aname, cname, level, cd, dmg) VALUES (?,?,?,?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(abilitiesQuery), abilitiesQuery, false);

            ps.setString(1, abilities.getAname());
            ps.setString(2, abilities.getCname());
            ps.setInt(3, abilities.getLevel());
            ps.setFloat(4, abilities.getCd());
            ps.setInt(5, abilities.getDmg());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    private void setupAbilityDMG() {
        String abilitiesDMGQuery = "CREATE TABLE AbilityDMG\n" +
                "(\n" +
                "    level       int ,\n" +
                "    dmg int,\n" +
                "    FOREIGN KEY (level) REFERENCES Ability ON DELETE CASCADE\n" +
                ")";
        try {
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(abilitiesDMGQuery), abilitiesDMGQuery, false);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void insertAbilityDMG(int level, int dmg) {
        try {
            String abilitiesDMGQuery = "INSERT INTO AbilityCast (level, dmg) VALUES (?,?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(abilitiesDMGQuery), abilitiesDMGQuery, false);

            ps.setInt(1, level);
            ps.setInt(2, dmg);

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    // set up the food table
    private void setupFood() {
        try {
            String charQuery = "CREATE TABLE Food\n" +
                    "(\n" +
                    "    name    char(80) PRIMARY KEY,\n" +
                    "    healAmount int DEFAULT 0,\n" +
                    ")";
            PrintablePreparedStatement psChar = new PrintablePreparedStatement(connection.prepareStatement(charQuery), charQuery, false);
            psChar.executeUpdate();
            psChar.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    // inserts food
    public void insertFood(Food food) {
        try {
            String q = "INSERT INTO Food VALUES (?, ?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(q), q, false);
            ps.setString(1, food.getFoodName());
            ps.setInt(2, food.getFoodHealAmount());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            rollbackConnection();
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

    }


    // set up the consumes table
    private void setupConsumes() {
        try {
            String charQuery = "CREATE TABLE Consumes\n" +
                    "(\n" +
                    "    username    char(80) PRIMARY KEY,\n" +
                    "    fname    char(80) PRIMARY KEY,\n" +
                    "    amount int,\n" +
                    ")";

            PrintablePreparedStatement psChar = new PrintablePreparedStatement(connection.prepareStatement(charQuery), charQuery, false);
            psChar.executeUpdate();
            psChar.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

    }

    public void deleteConsumes(String playerUsername, String foodName) {
        try {
            // DO I ADD THE "and foodName = ?" PART TOO?? vvvvvvvv
            // yes or else you delete all the food that player has (or rather oracle doesn't let u because of pk ic)
            // - W
            String query = "DELETE FROM Consumes WHERE playerUsername = ? and foodName = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, playerUsername);
            ps.setString(1, foodName);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Food " + foodName + " does not exist!");
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    // inserts consumes
    public void insertConsumes(Player player, Food food, int amount) {
        try {
            String q = "INSERT INTO Consumes VALUES (?, ?, ?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(q), q, false);
            ps.setString(1, player.getUserName());
            ps.setString(2, food.getFoodName());
            ps.setInt(3, amount);

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            rollbackConnection();
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

    }



    // creates the Element table
    private void setupElement() {
        try {
            String query = "CREATE TABLE Element\n" +
                    "(\n" +
                    "    name char(80) PRIMARY KEY\n" +
                    ")";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

        }
    }

    // inserts elements
    public void insertElement(ElementModel elementModel) {
        try {
            String q = "INSERT INTO Element VALUES (?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(q), q, false);
            ps.setString(1, elementModel.getName());
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            rollbackConnection();
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

    }

    // set up the character table
    private void setupCharacter() {
        try {
            setupCharHP();
            String charQuery = "CREATE TABLE Character\n" +
                    "(\n" +
                    "    name    char(80) PRIMARY KEY,\n" +
                    "    character_level int,\n" +
                    "    baseHP  int,\n" +
                    "    baseATK int,\n" +
                    "    ename   char(80) NOT NULL,\n" +
                    "    FOREIGN KEY (ename) REFERENCES Element,\n" +
                    "    FOREIGN KEY (character_level, baseHP) REFERENCES CharacterHP\n" +
                    ")";
            PrintablePreparedStatement psChar = new PrintablePreparedStatement(connection.prepareStatement(charQuery), charQuery, false);
            psChar.executeUpdate();
            psChar.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

    }

    //setup charHP table
    private void setupCharHP() {
        String charHPQuery = "CREATE TABLE CharacterHP\n" +
                "(\n" +
                "    character_level int,\n" +
                "    baseHP  int,\n" +
                "    currHP  int,\n" +
                "    CONSTRAINT pk_charHP PRIMARY KEY (character_level, baseHP)\n" +
                ")";
        try {
            PrintablePreparedStatement psHP = new PrintablePreparedStatement(connection.prepareStatement(charHPQuery), charHPQuery, false);
            psHP.executeUpdate();
            psHP.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    // inserts a character
    // TODO: insert into CharHP only if key does not already exist
      public void insertCharacter(Character character) {
        try {
            insertCharHP(character.getLevel(), character.getBaseHP());
            String characterQuery = "INSERT INTO Character(name, character_level, baseHP, baseATK, ename) VALUES (?, ?, ?, ?, ?)";
            PrintablePreparedStatement psChar = new PrintablePreparedStatement(connection.prepareStatement(characterQuery), characterQuery, false);

            psChar.setString(1, character.getName());
            psChar.setInt(2, character.getLevel());
            psChar.setInt(3, character.getBaseHP());
            psChar.setInt(4, character.getBaseATK());
            psChar.setString(5, character.getElement().getName());

            psChar.executeUpdate();
            connection.commit();

            psChar.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    private void insertCharHP(int level, int baseHP) {
        try {
            String charHPQuery = "INSERT INTO CharacterHP(character_level, baseHP, currHP) VALUES (?, ?, ?)";
            PrintablePreparedStatement psHP = new PrintablePreparedStatement(connection.prepareStatement(charHPQuery), charHPQuery, false);

            psHP.setInt(1, level);
            psHP.setInt(2, baseHP);
            psHP.setInt(3, baseHP + 50 * level);
            psHP.executeUpdate();
            psHP.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }



//
//    private void dropPlayerTableIfExists() {
////        try {
////            String query = "select table_name from user_tables";
////            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
////            ResultSet rs = ps.executeQuery();
////
////            while(rs.next()) {
////                if(rs.getString(1).toLowerCase().equals("branch")) {
////                    ps.execute("DROP TABLE branch");
////                    break;
////                }
////            }
////
////            rs.close();
////            ps.close();
////        } catch (SQLException e) {
////            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
////        }
//    }

}
