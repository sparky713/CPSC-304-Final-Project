package database;

import controller.Main;
import model.*;
import model.Character;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

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

    //changes attributes in the Player table
    public void updatePlayer(Player player) {
        try {
            String query = "UPDATE PLAYER SET PASSWORD = ?, EMAIL = ?, DISPLAYNAME = ? WHERE USERNAME = ?";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
            ps.setString(4, player.getUserName());
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
        List<String> projection = Arrays.asList();

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
        System.out.println("DCH::showAbilitiesProperties: " + selectedColumns);
        try {

            String query = "SELECT " + selectedColumns + " FROM ABILITY";
            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);

            ResultSet rsAbilities = ps.getResultSet();
            rsAbilities.last();
            int numAbilities = rsAbilities.getRow();
            rsAbilities.beforeFirst();

            for (int i = 0; i < numAbilities; i++) {
                rsAbilities.next();
                //set list text
                Main.guiAbilitiesPage.abilitiesMap.get(i)[0] = ps.getResultSet().getString(1); // cname
                Main.guiAbilitiesPage.abilitiesMap.get(i)[1] = Integer.toString(ps.getResultSet().getInt(2)); // ability_level
                Main.guiAbilitiesPage.abilitiesMap.get(i)[2] = Float.toString(ps.getResultSet().getFloat(3)); // cd
                Main.guiAbilitiesPage.abilitiesMap.get(i)[3] = Integer.toString(ps.getResultSet().getInt(4)); // dmg
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


    public void deleteConsumes(String playerUsername, String foodName) {
        try {
            // DO I ADD THE "and foodName = ?" PART TOO?? vvvvvvvv
            // yes or else you delete all the food that player has (or rather oracle doesn't let u because of pk ic)
            // - W
            String query = "DELETE FROM Consumes WHERE USERNAME = ? and FNAME = ?";
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
