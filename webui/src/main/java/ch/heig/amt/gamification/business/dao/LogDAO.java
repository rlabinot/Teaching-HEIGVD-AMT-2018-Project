package ch.heig.amt.gamification.business.dao;
import ch.heig.amt.gamification.model.Log;

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
public class LogDAO implements LogDAOLocal{

    private final String CREATE = "CALL createActionLogs(?, ?, ?, ?, ?)";
    private final String READ = "CALL readActionLogs(?)";
    private final String READ_ALL = "CALL readAllActionLogs()";
    private final String UPDATE = "CALL updateActionLogs(?, ?, ?, ?, ?, ?)";
    private final String DELETE = "CALL deleteActionLogs(?)";

    @Resource(name="jdbc/stackoveramt")
    DataSource dataSource;

    public void createLog(Log log){

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(CREATE);
            statement.setString(1,log.getUser());
            statement.setLong(2, log.getDate());
            statement.setString(3, log.getStatus());
            statement.setString(4, log.getAction());
            statement.setString(5, log.getDescription());
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Log readLog(int logId) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setInt(1,logId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new Log(
                        rs.getInt("Lid"),
                        rs.getString("Luser"),
                        rs.getLong("Ltimestamp"),
                        rs.getString("Lstatus"),
                        rs.getString("Laction"),
                        rs.getString("Ldescription")
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Log> readAllLog() {
        ArrayList<Log> allLogs = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                allLogs.add(new Log(
                        rs.getInt("Lid"),
                        rs.getString("Luser"),
                        rs.getLong("Ltimestamp"),
                        rs.getString("Lstatus"),
                        rs.getString("Laction"),
                        rs.getString("Ldescription")
                ));
            }
            return allLogs;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
// IN Lstatus VARCHAR(50), IN Laction VARCHAR(50), IN Ldescription VARCHAR(50)
    @Override
    public void updateLog(int idToUpdate, Log values) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1,idToUpdate);
            preparedStatement.setString(2, values.getUser());
            preparedStatement.setLong(3, values.getDate());
            preparedStatement.setString(4, values.getStatus());
            preparedStatement.setString(5,values.getAction());
            preparedStatement.setString(6, values.getDescription());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLog(int logId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,logId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}