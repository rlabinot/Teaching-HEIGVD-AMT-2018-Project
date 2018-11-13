package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.Engine;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EngineDAO implements  EngineDAOLocal {

  private final String queryInsertEngine = "INSERT INTO `engines` (`id`, `orderId`) VALUES (NULL, ?)";
  private final String queryGetInsertedEngineId = "SELECT LAST_INSERT_ID()";

  @Resource(name = "jdbc/AMTCars")
  DataSource dataSource;

  @Override
  public Engine buildEngine(UUID orderId) {
    Engine engine = new Engine();

    PreparedStatement statement2;
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement statement = connection.prepareStatement(queryInsertEngine);
      statement.setString(1, orderId.toString());
      statement.execute();
      statement2 = connection.prepareStatement(queryGetInsertedEngineId);
      ResultSet rs = statement2.executeQuery();
      rs.next();
      Long engineId = rs.getLong(1);
      engine.setId(engineId);
      engine.setOderId(orderId);
      throw new RuntimeException("boum");

      //return engine;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
