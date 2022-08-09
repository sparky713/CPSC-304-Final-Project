package database;

import controller.Main;
import model.*;
import model.Character;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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


    public void insertPlayer(Player player) {
        try {
            String playerQuery = "INSERT INTO PLAYER (username, password, email, displayName) VALUES (?,?,?,?)";
            PrintablePreparedStatement psPlayer = new PrintablePreparedStatement(connection.prepareStatement(playerQuery), playerQuery, false);

            psPlayer.setString(1, player.getUsername());
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

    //changes attributes in the Player table
    public void updatePlayer(Player player) {
        try {
            String query = "UPDATE PLAYER SET PASSWORD = ?, EMAIL = ?, DISPLAYNAME = ? WHERE USERNAME = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(4, player.getUsername());
            ps.setString(1, player.getPassword());
            ps.setString(2, player.getEmail());
            ps.setString(3, player.getDisplayName());

            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }

    }

    // finds a player given a username
    public Player selectPlayer(String username) {
        try {
            String query = "SELECT * FROM PLAYER WHERE USERNAME = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();




            String email = "";
            String password = "";
            String displayName = "";

            while (rs.next()) {
                email = rs.getString("email");
                password = rs.getString("password");
                displayName = rs.getString("DISPLAYNAME");
            }

            Player p = new Player(username, email, password, displayName);

            ps.close();
            rs.close();

            return p;

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return null;
    }

    public void deletePlayer(String username) {
        try {
            String q = "DELETE FROM PLAYER WHERE USERNAME = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(q), q, false);
            ps.setString(1, username);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }


    public void insertAbility(Abilities abilities) {
        try {
            insertAbilityDMG(abilities.getLevel(), abilities.getDmg());
            String abilitiesQuery = "INSERT INTO ABILITY(aname, cname, ABILITY_LEVEL, cd, dmg) VALUES (?,?,?,?,?)";
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

    public void insertAbilityDMG(int level, int dmg) {
        try {
            String abilitiesDMGQuery = "INSERT INTO ABILITYDMG (ability_level, dmg) VALUES (?,?)";
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

    public void showAbilitiesProperties(boolean showOwner, boolean showLevel, boolean showCD, boolean showDMG) {
        Vector<String> projection = new Vector<String>();

//        if (showOwner) {
//            projection.add("cname");
//        }
//
//        if (showLevel) {
//            projection.add("ability_level");
//        }
//
//        if (showCD) {
//            projection.add("cd");
//        }
//
//        if (showDMG) {
//            projection.add("dmg");
//        }

        projection.add("cname");
        projection.add("ability_level");
        projection.add("cd");
        projection.add("dmg");

        String selectedColumns = String.join(",", projection);
        System.out.println("DCH::showAbilitiesProperties: " + selectedColumns);
        try {

            String query = "SELECT " + selectedColumns + " FROM ABILITY";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

////            ps.getResultSet().setFetchDirection(ResultSet.TYPE_SCROLL_SENSITIVE,
////                    ResultSet.CONCUR_UPDATABLE);
//            ResultSet rsAbilities = ps.getResultSet();
            ResultSet rsAbilities = ps.executeQuery(query);
//            rsAbilities.setFetchDirection(ResultSet.FETCH_REVERSE);
////            rsAbilities.setFetchDirection(ResultSet.TYPE_SCROLL_INSENSITIVE);
////            rsAbilities.setFetchDirection(ResultSet.CONCUR_UPDATABLE);
//            System.out.println("fetch dir: " + ps.getFetchDirection());
//            rsAbilities.last();
//            int numAbilities = rsAbilities.getRow();
//            int num = ps.getMaxRows();

//            rsAbilities.beforeFirst();
            for (int i = 0; i < 5; i++) {
                rsAbilities.next();
                //set list text
                if (showOwner) { // cname
                    Main.guiAbilitiesPage.deafultListModels[i].set(1,ps.getResultSet().getString(1));
                }
                else {
                    Main.guiAbilitiesPage.deafultListModels[i].set(1, Main.guiAbilitiesPage.DEFAULT_STRING);
                }

                if (showLevel) { // ability_level
                    Main.guiAbilitiesPage.deafultListModels[i].set(3, Integer.toString(ps.getResultSet().getInt(2)));

                }
                else {
                    Main.guiAbilitiesPage.deafultListModels[i].set(3, Main.guiAbilitiesPage.DEFAULT_STRING);
                }

                if (showCD) { // cd
                    Main.guiAbilitiesPage.deafultListModels[i].set(5, Float.toString(ps.getResultSet().getFloat(3)));
                }
                else {
                    Main.guiAbilitiesPage.deafultListModels[i].set(5, Main.guiAbilitiesPage.DEFAULT_STRING);
                }

                if (showDMG) { // dmg
                    Main.guiAbilitiesPage.deafultListModels[i].set(7, Integer.toString(ps.getResultSet().getInt(4)));
                }
                else {
                    Main.guiAbilitiesPage.deafultListModels[i].set(7, Main.guiAbilitiesPage.DEFAULT_STRING);
                }
            }
            ps.executeUpdate();
            connection.commit();
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
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

    public Food selectFood(String foodName) {
        try {
            String query = "SELECT * FROM food WHERE name = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, foodName);
            ResultSet rs = ps.executeQuery();




            int healAmount = 0;

            while (rs.next()) {
                healAmount = rs.getInt("healAmount");
            }

            Food f = new Food(foodName, healAmount);

            ps.close();
            rs.close();

            return f;

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return null;
    }


    public void deleteConsumes(String playerUsername, String foodName) {
        try {
            // DO I ADD THE "and foodName = ?" PART TOO?? vvvvvvvv
            // yes or else you delete all the food that player has (or rather oracle doesn't let u because of pk ic)
            // - W
            String query = "DELETE FROM Consumes WHERE USERNAME = ? and FNAME = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, playerUsername);
            ps.setString(2, foodName);

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
    public void insertConsumes(int id, Player player, Food food, int amount) {
        try {
            String q = "INSERT INTO Consumes VALUES (?, ?, ?)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(q), q, false);
            ps.setInt(1, id);
            ps.setString(2, player.getUsername());
            ps.setString(3, food.getFoodName());
            ps.setInt(4, amount);

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            rollbackConnection();
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

    }


    //            while(rs.next()) {
//                Map<String,Integer> oneFood = new HashMap<String,Integer>();
////                Food foodModel =  new Food(rs.getString("name"),
////                        rs.getInt("healAmount"));
//                try {
//                    int foodModelQuantity = rs.getInt("amount");
//                } catch (SQLException e) {
//                System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//            }
//                oneFood.put(rs.getString("fname"), foodModelQuantity);
//                result.add(oneFood);
//            }




    public ArrayList<Map<String,Integer>> getPlayerFoodInfo(Player player) {
        ArrayList<Map<String,Integer>> result = new ArrayList<Map<String,Integer>>();

        try {
            String playerName = player.getUsername();

            String query = " SELECT fname, SUM(amount) FROM consumes WHERE username = '" + playerName + "' GROUP BY fname ";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            System.out.println("line 384");
            //ResultSet rs = ps.getResultSet();

            // changing this back to "executeQuery() got rid of cursor error, but gave and error for
            // "INSERT INTO Food VALUES" for "Mushroom Pizza 2"
            ResultSet rs = ps.executeQuery();
            System.out.println("line 386");

            while (rs.next()) {
                System.out.println("line 389");
                Map<String, Integer> oneFood = new HashMap<String, Integer>();
                System.out.println("line 391");
                oneFood.put(rs.getString(3), rs.getInt(4));
                System.out.println("line 393");
                result.add(oneFood);
                System.out.println("line 395");
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result;
    }

    public String[] getPlayersWithAllFood() {
        ArrayList<String> result = new ArrayList<String>();

        try {
            String query = "SELECT * FROM consumes WHERE NOT EXISTS (SELECT name FROM food MINUS (SELECT fname FROM consumes))";

            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                String playerName = rs.getString("username");
                result.add(playerName);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        //System.out.println(result.toArray(new String[result.size()]));

        return result.toArray(new String[result.size()]);
    }





//            String query = " SELECT p.username FROM player as p WHERE NOT EXISTS " +
//                    "((SELECT f.name FROM food as f) EXCEPT " +
//                    "(SELECT c.fname FROM consumes c WHERE c.username = p.username)) ";



//                BranchModel model = new BranchModel(rs.getString("branch_addr"),
//                        rs.getString("branch_city"),
//                        rs.getInt("branch_id"),
//                        rs.getString("branch_name"),
//                        rs.getInt("branch_phone"));
//                result.add(model);

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

    // inserts a character (does not work)
    // TODO: insert into CharHP only if key does not already exist
    // TODO: insert into CharATK
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

}
