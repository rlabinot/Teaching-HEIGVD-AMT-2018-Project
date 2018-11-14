package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserDAO implements UserDAOLocal {
    private final String COUNT = "CALL countUser()";
    private final String CREATE = "CALL createUser(?, ?, ?, ?, ?, ?)";
    private final String READ   = "CALL readUser(?)";
    private final String READ_ALL   = "CALL readAllUser()";
    private final String READ_ALL_OFF   = "CALL readAllUserOffset(?, ?)";
    private final String LOGIN   = "CALL userLogin(?, ?)";
    private final String UPDATE = "CALL updateUser(?, ?, ?, ?, ?, ?)";
    private final String DELETE = "CALL deleteUser(?)";
    private final String CHANGE_STATE = "CALL changeUserState(?, ?)";
    private final String CHANGE_USER_PASSWORD = "CALL changeUserPassword(?, ?, ?)";


    @Resource(name = "jdbc/stackoveramt")
    DataSource dataSource;

    @Override
    public int countUser() {
        int nb = 0;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT);
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next()) {
                nb = rs.getInt("nb");

            }
            return nb;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createUser(User userToCreate) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, userToCreate.getEmail());
            preparedStatement.setString(2, userToCreate.getName());
            preparedStatement.setString(3, DigestUtils.sha256Hex(userToCreate.getPassword()));
            preparedStatement.setBoolean(4, userToCreate.isAdmin());
            preparedStatement.setBoolean(5, userToCreate.isActive());
            preparedStatement.setBoolean(6, userToCreate.getMustChangePassword());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User readUser(String emailToRead) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setString(1, emailToRead);
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next()) {
                return new User(rs.getString("Uname"),
                        rs.getString("Umail"),
                        rs.getString("Upassword"),
                        rs.getBoolean("UisAdmin"),
                        rs.getBoolean("UisActive"),
                        rs.getBoolean("UmustChangePassword")
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<User> readAllUser() {
        ArrayList<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet rs =  preparedStatement.executeQuery();
            while (rs.next()) {
                users.add( new User(rs.getString("Uname"),
                        rs.getString("Umail"),
                        rs.getString("Upassword"),
                        rs.getBoolean("UisAdmin"),
                        rs.getBoolean("UisActive"),
                        rs.getBoolean("UmustChangePassword")
                ));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<User> readAllUser(int offset, int size) {
        ArrayList<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_OFF);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, size);
            ResultSet rs =  preparedStatement.executeQuery();
            while (rs.next()) {
                users.add( new User(rs.getString("Uname"),
                        rs.getString("Umail"),
                        rs.getString("Upassword"),
                        rs.getBoolean("UisAdmin"),
                        rs.getBoolean("UisActive"),
                        rs.getBoolean("UmustChangePassword")
                ));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User userLogin(String emailToRead, String password) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN);
            preparedStatement.setString(1, emailToRead);
            preparedStatement.setString(2, DigestUtils.sha256Hex(password));
            ResultSet rs =  preparedStatement.executeQuery();
            if(rs.next()) {
                return new User(rs.getString("Uname"),
                        rs.getString("Umail"),
                        rs.getString("Upassword"),
                        rs.getBoolean("UisAdmin"),
                        rs.getBoolean("UisActive"),
                        rs.getBoolean("UmustChangePassword"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(String userToUpdate, User values) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, userToUpdate);
            preparedStatement.setString(2, values.getName());
            preparedStatement.setString(3, DigestUtils.sha256Hex(values.getPassword()));
            preparedStatement.setBoolean(4, values.isAdmin());
            preparedStatement.setBoolean(5, values.isActive());
            preparedStatement.setBoolean(6, values.getMustChangePassword());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(String userToDelete) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1,userToDelete);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void changeUserState(String userEmail, boolean state) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STATE);
            preparedStatement.setString(1,userEmail);
            preparedStatement.setBoolean(2, state);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeUserPassword(String mail, String password, boolean mustChangePassword) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_PASSWORD);
            preparedStatement.setString(1,mail);
            preparedStatement.setString(2, DigestUtils.sha256Hex(password));
            preparedStatement.setBoolean(3, mustChangePassword);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
