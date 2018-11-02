package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.Application;

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
public class ApplicationDAO implements ApplicationDAOLocal {
    private final String CREATE = "CALL createApplication(?,?,?,?,?)";
    private final String READ   = "CALL readApplicationFromUser(?)";
    private final String UPDATE = "";
    private final String DELETE = "";

    @Resource(name = "jdbc/stackoveramt")
    DataSource dataSource;

    @Override
    public void createApplication(Application application) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setString(1, application.getName());
            preparedStatement.setString(2, application.getDescription());
            preparedStatement.setString(3, application.getApiKey());
            preparedStatement.setString(4, application.getApiSecret());
            preparedStatement.setString(5, application.getUser());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Application> readApplicationFromUser(String email) {
        ArrayList<Application> appList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(READ);
            preparedStatement.setString(1, email);
            ResultSet rs =  preparedStatement.executeQuery();
            while(rs.next()){
                appList.add(new Application(rs.getInt("Aid"),
                                            rs.getString("Aname"),
                                            rs.getString("Adescription"),
                                            rs.getString("AapiKey"),
                                            rs.getString("AapiSecret"),
                                            rs.getString("RefUmail")
                ));
            }
            return appList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateApplication(int appId, Application values) {

    }

    @Override
    public void deleteApplication(int appId) {

    }
}
