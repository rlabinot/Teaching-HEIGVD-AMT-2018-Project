package ch.heig.amt.gamification.business.dao;
import ch.heig.amt.gamification.model.Log;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LogDAO implements LogDAOLocal{

    private final String CREATE="CALL createActionLogs(?,?,?,?,?)";

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
            throw new RuntimeException(e);
        }
    }

    @Override
    public Log readLogFromDate(long date) {
        return null;
    }

    @Override
    public void updateLog() {

    }

    @Override
    public void deleteLog(int logId) {

    }
}