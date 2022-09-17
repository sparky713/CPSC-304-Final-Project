package database;

import controller.Main;
import model.*;
import model.Character;
import util.PrintablePreparedStatement;

import javax.swing.*;
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

    //----------------------------------------------------------------------
    // Player
    // ---------------------------------------------------------------------
    public boolean insertPlayer(Player player) {
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
            JOptionPane duplicateUsernameMessage = new JOptionPane();
            duplicateUsernameMessage.setBounds(Main.guiCreateAccountPage.POPUP_MENU_X, Main.guiCreateAccountPage.POPUP_MENU_Y, Main.guiCreateAccountPage.POPUP_MENU_W, Main.guiCreateAccountPage.POPUP_MENU_H);
            duplicateUsernameMessage.showMessageDialog(null, Main.guiCreateAccountPage.tfUsername.getText()
                    + " or " + Main.guiCreateAccountPage.tfEmail.getText() + " is already taken.", "Username or Email Taken",
                    JOptionPane.INFORMATION_MESSAGE);
            rollbackConnection();
            return false;
        }
        return true;
    }

    // updates player table in the database given a player
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

    // finds a player in the database given a username
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

    // deletes a player from the database given a username
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

    //----------------------------------------------------------------------
    // Party
    // ---------------------------------------------------------------------

    //group by
    public void numPartiesPerCharacter(Player player) {
        try {
            String query = "SELECT cname, count(*) FROM COMPRISEDOF WHERE username = ? GROUP BY cname ORDER BY count(*) DESC";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, player.getUsername());
            ResultSet rs = ps.executeQuery();

            int i = 0;
            while (rs.next()) {
//            //set list text
                Main.guiPartiesPage.characterLabels[i].setText(ps.getResultSet().getString(1));
                Main.guiPartiesPage.numPartyLabels[i].setText(ps.getResultSet().getString(2));
                i++;
            }
            ps.executeUpdate();
            connection.commit();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    //aggregation with having
    public void strongestCharacterLevelInParty(Player player, int level) { // party with highest level character

        try {
            // 1) group by pname where username = current player's username (player2)
            // 2) join CHARACTER and COMPRISEDOF tables
            // 3) find the party(s) containing the character with the highest level greater than the given level input
            String query = "SELECT co.pname, max(c.character_level)\n" +
                    "FROM character c, comprisedof co\n" +
                    "WHERE co.username = ? AND c.name = co.cname\n" +
                    "GROUP BY co.pname\n" +
                    "HAVING max(c.character_level) >= ?\n" +
                    "ORDER BY max(c.character_level) DESC";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ps.setString(1, player.getUsername());
            ps.setInt(2, level);

            ResultSet rs = ps.executeQuery();
            int i = 0;
            for (int j = 0; j < Main.guiPartiesPage.partyLabels.length; j++) {
                Main.guiPartiesPage.partyLabels[j].setText(" ");
            }

            for (int c = 0; c < Main.guiPartiesPage.partyLabels.length; c++) {
                Main.guiPartiesPage.maxLevelLabels[c].setText(" ");
            }

            while (rs.next()) {
//            //set list text
                Main.guiPartiesPage.partyLabels[i].setText(ps.getResultSet().getString(1));
                Main.guiPartiesPage.maxLevelLabels[i].setText(ps.getResultSet().getString(2));
                i++;
            }

            ps.executeUpdate();
            connection.commit();
            ps.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    //----------------------------------------------------------------------
    // Ability
    // ---------------------------------------------------------------------
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

    // projection
    public void showAbilitiesProperties(boolean showOwner, boolean showLevel, boolean showCD, boolean showDMG) {
        Vector<String> projection = new Vector<String>();

        if (showOwner) {
            projection.add("cname");
        }

        if (showLevel) {
            projection.add("ability_level");
        }

        if (showCD) {
            projection.add("cd");
        }

        if (showDMG) {
            projection.add("dmg");
        }

        String selectedColumns = String.join(",", projection);
//        System.out.println("DCH::showAbilitiesProperties: " + selectedColumns);
        try {

            String query = "SELECT " + selectedColumns + " FROM ABILITY";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rsAbilities = ps.executeQuery(query);

            for (int i = 0; i < 5; i++) {
                rsAbilities.next();
                //set list text
                if (showOwner) { // cname
                    Main.guiAbilitiesPage.deafultListModels[i].set(1, ps.getResultSet().getString("cname"));
                } else {
                    Main.guiAbilitiesPage.deafultListModels[i].set(1, Main.guiAbilitiesPage.DEFAULT_STRING);
                }

                if (showLevel) { // ability_level
                    Main.guiAbilitiesPage.deafultListModels[i].set(3, Integer.toString(ps.getResultSet().getInt("ability_level")));

                } else {
                    Main.guiAbilitiesPage.deafultListModels[i].set(3, Main.guiAbilitiesPage.DEFAULT_STRING);
                }

                if (showCD) { // cd
                    Main.guiAbilitiesPage.deafultListModels[i].set(5, Float.toString(ps.getResultSet().getFloat("cd")));
                } else {
                    Main.guiAbilitiesPage.deafultListModels[i].set(5, Main.guiAbilitiesPage.DEFAULT_STRING);
                }

                if (showDMG) { // dmg
                    Main.guiAbilitiesPage.deafultListModels[i].set(7, Integer.toString(ps.getResultSet().getInt("dmg")));
                } else {
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

    //----------------------------------------------------------------------
    // Food
    // ---------------------------------------------------------------------
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
//            String query = "DELETE FROM Consumes WHERE USERNAME = ? and FNAME = ?"; // old query
            String query = "DELETE FROM Food WHERE NAME = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            ps.setString(1, playerUsername);
//            ps.setString(2, foodName);

            ps.setString(1, foodName);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Food " + foodName + " does not exist!");
                JOptionPane invalidInput = new JOptionPane();
                invalidInput.setBounds(Main.guiCreateAccountPage.POPUP_MENU_X, Main.guiCreateAccountPage.POPUP_MENU_Y, Main.guiCreateAccountPage.POPUP_MENU_W, Main.guiCreateAccountPage.POPUP_MENU_H);
                invalidInput.showMessageDialog(null, (String) Main.guiFoodPage.foodToDeleteText.getSelectedItem() + " does not exist.", "Invalid Food",
                        JOptionPane.INFORMATION_MESSAGE);
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
            String q = "INSERT INTO consumes VALUES (?, ?, ?, ?)";
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


    public ArrayList<Object> getPlayerInfo(String table, String attribute, String condition) {
        ArrayList<Object> result = new ArrayList<Object>();

        try {
            //String playerName = player.getUsername();

            String query = " SELECT DISTINCT " + attribute + " FROM " + table + " WHERE " + condition + "";
            //fname, SUM(amount) FROM consumes WHERE username = '" + playerName + "' GROUP BY fname ";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            System.out.println("line 384");
            //ResultSet rs = ps.getResultSet();

            // changing this back to "executeQuery() got rid of cursor error, but gave and error for
            // "INSERT INTO Food VALUES" for "Mushroom Pizza 2"
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object obj = new Object();
                obj = (rs.getString(1));
                result.add(obj);
            }


            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result;
    }


//    public ArrayList<Map<String, Integer>> getPlayerFoodInfo(Player player) {
//        ArrayList<Map<String, Integer>> result = new ArrayList<Map<String, Integer>>();
//
//        try {
//            String playerName = player.getUsername();
//
//            String query = " SELECT fname, SUM(amount) FROM consumes WHERE username = '" + playerName + "' GROUP BY fname ";
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            System.out.println("line 384");
//            //ResultSet rs = ps.getResultSet();
//
//            // changing this back to "executeQuery() got rid of cursor error, but gave and error for
//            // "INSERT INTO Food VALUES" for "Mushroom Pizza 2"
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Map<String, Integer> oneFood = new HashMap<String, Integer>();
//                oneFood.put(rs.getString(1), rs.getInt(2));
//                result.add(oneFood);
//            }
//
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//
//        return result;
//    }

    public ArrayList<String> getPlayersWithAllFood() {
        ArrayList<String> result = new ArrayList<String>();

        try {
//            String query = "SELECT username FROM consumes WHERE NOT EXISTS (SELECT name FROM food MINUS (SELECT fname FROM consumes))";

            String query = "SELECT username FROM player WHERE NOT EXISTS ((SELECT name FROM food) MINUS (SELECT fname FROM consumes WHERE username = player.username))";

            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String playerName = rs.getString("username");
                result.add(playerName);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }

//    public String[] getPlayersWithAllFood() {
//        ArrayList<String> result = new ArrayList<String>();
//
//        try {
//            String query = "SELECT * FROM consumes WHERE NOT EXISTS (SELECT name FROM food MINUS (SELECT fname FROM consumes))";
//
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String playerName = rs.getString("username");
//                result.add(playerName);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//        return result.toArray(new String[result.size()]);
//    }

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

//    // inserts elements
//    public void insertElement(ElementModel elementModel) {
//        try {
//            String q = "INSERT INTO Element VALUES (?)";
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(q), q, false);
//            ps.setString(1, elementModel.getName());
//            ps.executeUpdate();
//            connection.commit();
//            ps.close();
//        } catch (SQLException e) {
//            rollbackConnection();
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//
//    }


    //----------------------------------------------------------------------
    // Character
    // ---------------------------------------------------------------------

    // returns a list of owned weapons with ATK greater than minATK
    // UNUSED
    public ArrayList<Weapon> giveOwnedWeaponWithMinATK(int minATK, String username) {
        try {
            String query = "SELECT * FROM Weapon INNER JOIN OWNSWEAPON ON OWNSWEAPON.WNAME = WEAPON.NAME WHERE OWNSWEAPON.USERNAME = ? AND WEAPON.BASEATK > ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, username);
            ps.setInt(2, minATK);

            ResultSet rs = ps.executeQuery();

            ArrayList<Weapon> wList = new ArrayList<Weapon>();

            while (rs.next()) {
                String wname = rs.getString("name");
                int baseATK = rs.getInt("baseATK");
                Weapon w = new Weapon(wname, baseATK);
                wList.add(w);
            }

            ps.close();
            rs.close();
            return wList;

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return null;
    }

    // returns a list of owned characters with ATK greater than minATK
    public ArrayList<Character> giveCharacterWithMinATK(int minATK, String username) {
        try {

            String query = "SELECT * FROM CHARACTER INNER JOIN PLAYS ON PLAYS.CNAME = CHARACTER.NAME WHERE PLAYS.USERNAME = ? AND CHARACTER.BASEATK > ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(1, username);
            ps.setInt(2, minATK);

            ResultSet rs = ps.executeQuery();

            ArrayList<Character> cList = new ArrayList<Character>();

            while (rs.next()) {
                String cName = rs.getString("name");
                int level = rs.getInt(2);
                int baseHP = rs.getInt(3);
                int baseATK = rs.getInt(4);
                String eName = rs.getString(5);

                Character character = new Character(cName, level, baseHP, baseATK, eName);
                cList.add(character);

            }

            ps.close();
            rs.close();
            return cList;

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return null;
    }

    // returns a list of characters that have a baseATK greater than
    // each average base atk of characters owned by a certain players
    public ArrayList<Character> nestedAggregation() {
        try {
            String query = "SELECT * FROM CHARACTER WHERE CHARACTER.BASEATK > ALL (SELECT AVG(C.BASEATK) FROM CHARACTER C, PLAYS P WHERE P.CNAME = C.NAME GROUP BY USERNAME)";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ArrayList<Character> characters = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String cName = rs.getString(1);
                int level = rs.getInt(2);
                int baseHP = rs.getInt(3);
                int baseATK = rs.getInt(4);
                String e = rs.getString(5);
                Character c = new Character(cName, level, baseHP, baseATK, e);
                characters.add(c);
            }

            ps.close();
            rs.close();

            return characters;

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return null;
    }


}
