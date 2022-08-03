package database;

import model.Character;
import model.ElementModel;
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

//            rs.close();
//            ps.close();
        } catch (SQLException e) {
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
    // TODO: update Character model to calculate currHP using baseHP and level
    public void insertCharacter(Character character) {
        try {
            String charHPQuery = "INSERT INTO CharacterHP(character_level, baseHP, currHP) VALUES (?, ?, ?)";
            String characterQuery = "INSERT INTO Character(name, character_level, baseHP, baseATK, ename) VALUES (?, ?, ?, ?, ?)";
            PrintablePreparedStatement psHP = new PrintablePreparedStatement(connection.prepareStatement(charHPQuery), charHPQuery, false);
            PrintablePreparedStatement psChar = new PrintablePreparedStatement(connection.prepareStatement(characterQuery), characterQuery, false);

            psHP.setInt(1, character.getLevel());
            psHP.setInt(2, character.getBaseHP());
            psHP.setInt(3, character.getBaseHP());

            psChar.setString(1, character.getName());
            psChar.setInt(2, character.getLevel());
            psChar.setInt(3, character.getBaseHP());
            psChar.setInt(4, character.getBaseATK());
            psChar.setString(5, character.getElement().getName());

            psHP.executeUpdate();
            psChar.executeUpdate();
            connection.commit();

            psChar.close();
            psHP.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    //levels up a character by given amount
    public void levelCharacter(String cName, int amount) {
        try {
            //TODO: make this level by adding amount to current level in database
            String query = "UPDATE Character SET CHARACTER_LEVEL = ? WHERE NAME = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setInt(1, amount);
            ps.setString(2, cName.toLowerCase());

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + "Character " + cName + " does not exist!");
            }

            connection.commit();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

//    // inserting the user input for a new account
//    public void insertPlayer(PlayerModel player) {
////        try {
////            String query = "INSERT INTO player VALUES (?,?,?,?,?)";
////            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
////                    ResultSet.CONCUR_READ_ONLY);
////            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
////            ps.setInt(1, model.getId());
////            ps.setString(2, model.getName());
////            ps.setString(3, model.getAddress());
////            ps.setString(4, model.getCity());
////            if (model.getPhoneNumber() == 0) {
////                ps.setNull(5, java.sql.Types.INTEGER);
////            } else {
////                ps.setInt(5, model.getPhoneNumber());
////            }
////
////            ps.executeUpdate();
////            connection.commit();
////
////            ps.close();
////        } catch (SQLException e) {
////            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
////            rollbackConnection();
////        }
//    }
//
//
//    private void playerDatabaseSetup() {
//        dropPlayerTableIfExists();
//
//        try {
//            String query = "CREATE TABLE Player (username char(60) PRIMARY KEY, password char(80) NOT NULL, email char(80) NOT NULL, displayName char(80) NOT NULL, UNIQUE(email))";
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//    }
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
