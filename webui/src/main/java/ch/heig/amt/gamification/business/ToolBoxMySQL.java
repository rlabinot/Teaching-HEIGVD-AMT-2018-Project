package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.model.*;
import java.sql.*;

/**
 * A class to connect and perform actions on a MySQL database
 *
 * @author Dejvid Muaremi
 */
public class ToolBoxMySQL{

    /**
     * the database name
     */
    private static final String database = "stackoveramt";

    /**
     * a MySQL account to access the database
     */
    private static final String account = "root";

    /**
     * the password of the MySQL account
     */
    private static final String password = "root";

    /**
     * the connexion to perform action on the MySQL database
     */
    private Connection connection;

    /**
     * a string containing a MySQL request
     */
    private String sql;

    /**
     * Default constructor
     */
    public ToolBoxMySQL(){}

    /**
     * A method initialize the connexion to the database
     */
    public void initConnection() {
        try {
            String server = "127.0.0.1";

            String url = "jdbc:mysql://"+server+":3306/" + database + "?user=" + account + "&password=" + password;
            connection = DriverManager.getConnection(url);
        } catch(SQLException e){
            e.printStackTrace();
            closeConnection();
        }
    }

    /**
     * This method close the connection to the database
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createUser(User user) {

        ResultSet result;
        PreparedStatement ps;

        try (Statement statement = connection.createStatement()) {


            // Check if the user already exists
            sql = "CALL readUser(?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            result = ps.executeQuery();

            // Add the user if he doesn't exist
            if (!result.next()) {
                sql = "CALL createUser(?,?,?,?,?)";
                ps = connection.prepareCall(sql);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                ps.setBoolean(4, user.isAdmin());
                ps.setBoolean(5, user.isActive());
                ps.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
            closeConnection();
        }
    }

    private void createApplication(Application application) {
        PreparedStatement ps;

        try (Statement statement = connection.createStatement()) {
            sql = "CALL createApplication(?,?,?,?,?)";
            ps = connection.prepareCall(sql);
            ps.setString(1, application.getName());
            ps.setString(2, application.getDescription());
            ps.setString(3, application.getApiKey());
            ps.setString(4, application.getApiSecret());
            ps.setString(5, application.getUser());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            closeConnection();
        }
    }

    private void createActionLogs(Log log) {
        //IN Luser VARCHAR(50), IN Ltimestamp DATETIME, IN Lstatus VARCHAR(50), IN Laction VARCHAR(50), IN Ldescription VARCHAR(50)
        PreparedStatement ps;

        try (Statement statement = connection.createStatement()) {
            sql = "CALL createActionLogs(?,?,?,?,?)";
            ps = connection.prepareCall(sql);
            ps.setString(1, log.getUser());
            ps.setLong(2, log.getDate());
            ps.setString(3, log.getStatus());
            ps.setString(4, log.getAction());
            ps.setString(5, log.getDescription());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            closeConnection();
        }
    }

}