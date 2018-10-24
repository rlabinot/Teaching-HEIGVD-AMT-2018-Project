package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.model.*;
import java.sql.*;
import java.util.ArrayList;

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
    private static final String account = "stackoveramt";

    /**
     * the password of the MySQL account
     */
    private static final String password = "stackoveramt";

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

    public void createUser(User user) {

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

    public User readUser(String emailToRead) {
        String name = "";
        String email = "";
        String password = "";
        boolean isAdmin = false;
        boolean isActive = false;

        try {
            Statement statement = connection.createStatement();
            ResultSet result;
            PreparedStatement ps;
            sql = "CALL readUser(?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, emailToRead);
            result = ps.executeQuery();

            name = result.getString("Uname");
            email = result.getString("Umail");
            password = result.getString("Upassword");
            isAdmin = result.getBoolean("UisAdmin");
            isActive = result.getBoolean("UisActive");

        } catch (SQLException e){
            e.printStackTrace();
        }
        return new User(name, email, password, isAdmin, isActive);
    }

    public void createApplication(Application application) {
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

    public ArrayList<Application> readApplicationFromUser(String userToRead) {
        ArrayList<Application> appList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result;
            PreparedStatement ps;
            sql = "CALL readApplicationFromUser(?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, userToRead);
            result = ps.executeQuery();

            if(result == null){
                return appList;
            }
            while (result.next()){
                int id = result.getInt("Aid");
                String name = result.getString("Aname");
                String description = result.getString("Adescription");
                String apiKey = result.getString("AapiKey");
                String apiSecret = result.getString("AapiSecret");
                String user = result.getString("RefUmail");

                appList.add(new Application(id,name,description,apiKey,apiSecret,user));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return appList;
    }

    public void createActionLogs(Log log) {
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