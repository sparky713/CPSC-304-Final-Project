package database;

import model.Player;

import java.sql.*;

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

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void deletePlayerFood(int foodname) {
//        try {
//            String query = "DELETE FROM branch WHERE branch_id = ?";
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            ps.setInt(1, foodname);
//
//            int rowCount = ps.executeUpdate();
//            if (rowCount == 0) {
//                System.out.println(WARNING_TAG + " Branch " + foodname + " does not exist!");
//            }
//
//            connection.commit();
//
//            ps.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//            rollbackConnection();
//        }
//    }

    // inserting the user input for a new account
    public void insertPlayer(Player player) {
//        try {
//            String query = "INSERT INTO player VALUES (?,?,?,?,?)";
//            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_READ_ONLY);
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            ps.setInt(1, model.getId());
//            ps.setString(2, model.getName());
//            ps.setString(3, model.getAddress());
//            ps.setString(4, model.getCity());
//            if (model.getPhoneNumber() == 0) {
//                ps.setNull(5, java.sql.Types.INTEGER);
//            } else {
//                ps.setInt(5, model.getPhoneNumber());
//            }
//
//            ps.executeUpdate();
//            connection.commit();
//
//            ps.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//            rollbackConnection();
//        }
    }

    //need to change attributes of bank to attributes of actual character
        // I think instead of player being able to view a character's weapons, they should be able to view a character
        //and their corresponding attributes such as name, level, currATK, currHP, baseHP, baseATK
        public BranchModel[] getCharacterInfo() {
            ArrayList<BranchModel> result = new ArrayList<BranchModel>();

            try {
                String query = "SELECT * FROM branch";
                PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    BranchModel model = new BranchModel(rs.getString("branch_addr"),
                            rs.getString("branch_city"),
                            rs.getInt("branch_id"),
                            rs.getString("branch_name"),
                            rs.getInt("branch_phone"));
                    result.add(model);
                }

                rs.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            }

            return result.toArray(new BranchModel[result.size()]);
        }

//    public boolean login(String username, String password) {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//
//            connection = DriverManager.getConnection(ORACLE_URL, username, password);
//            connection.setAutoCommit(false);
//
//            System.out.println("\nConnected to Oracle!");
//            return true;
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//            return false;
//        }
//    }

    private void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
//
//    public void databaseSetup() {
//        dropBranchTableIfExists();
//
//        try {
//            String query = "CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)";
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//
//        BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
//        insertBranch(branch1);
//
//        BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
//        insertBranch(branch2);
//    }
//
//    private void dropBranchTableIfExists() {
//        try {
//            String query = "select table_name from user_tables";
//            PrintablePreparedStatement ps = new PrintablePreparedStatement(connection.prepareStatement(query), query, false);
//            ResultSet rs = ps.executeQuery();
//
//            while(rs.next()) {
//                if(rs.getString(1).toLowerCase().equals("branch")) {
//                    ps.execute("DROP TABLE branch");
//                    break;
//                }
//            }
//
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//    }
}
