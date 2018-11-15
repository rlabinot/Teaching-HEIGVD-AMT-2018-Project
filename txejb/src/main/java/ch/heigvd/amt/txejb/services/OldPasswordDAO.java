package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.OldPassword;

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
public class OldPasswordDAO implements OldPasswordDAOLocal {

    private final String COUNT = "CALL countOldPassword(?)";
    private final String CREATE = "CALL createOldPassword(?, ?)";
    private final String READ_ALL   = "CALL readOldPasswordFromUser(?)";
    private final String DELETE_ALL   = "CALL deleteOldPasswordFromUser(?)";

    @Resource(name = "jdbc/stackoveramt")
    DataSource dataSource;

    @Override
    public int countOldPassword(String email) {
        int nb = 0;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT);
            preparedStatement.setString(1, email);
            ResultSet rs =  preparedStatement.executeQuery();

            if (rs.next()) {
                nb = rs.getInt("nb");
            }

            return nb;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createOldPassword(OldPassword oldPasswordToAdd) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, oldPasswordToAdd.getEmail());
            preparedStatement.setString(2, oldPasswordToAdd.getPassword());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<OldPassword> readAllOldPasswordFromUser(String email) {
        ArrayList<OldPassword> oldPasswords = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);
            preparedStatement.setString(1, email);
            ResultSet rs =  preparedStatement.executeQuery();

            while (rs.next()) {
                oldPasswords.add( new OldPassword(rs.getString("OPref"),
                        rs.getString("OPpassword")
                ));
            }

            return oldPasswords;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void deleteAllOldPasswordFromUser(String email) {

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL);
            preparedStatement.setString(1, email);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
