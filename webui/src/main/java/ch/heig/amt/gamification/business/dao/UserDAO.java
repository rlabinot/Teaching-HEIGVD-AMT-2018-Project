package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserDAO implements UserDAOLocal {
    private final String CREATE = "CALL createUser(?,?,?,?,?)";
    private final String READ   = "CALL readUser(?)";
    private final String UPDATE = "";
    private final String DELETE = "";

    @Resource(name = "jdbc/stackoveramt")
    DataSource dataSource;

    @Override
    public void createUser(User userToCreate) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, userToCreate.getEmail());
            preparedStatement.setString(2, userToCreate.getName());
            preparedStatement.setString(3, userToCreate.getPassword());
            preparedStatement.setBoolean(4, userToCreate.isAdmin());
            preparedStatement.setBoolean(5, userToCreate.isActive());
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
            rs.next();
            return new  User(rs.getString("Uname"),
                             rs.getString("Umail"),
                             rs.getString("Upassword"),
                             rs.getBoolean("UisAdmin"),
                             rs.getBoolean("UisActive"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(String userToUpdate, User values) {
    }

    @Override
    public void deleteUser(String userToDelete) {

    }
}
