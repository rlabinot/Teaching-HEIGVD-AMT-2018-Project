package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.Engine;
import ch.heigvd.amt.txejb.model.Tire;

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
public class TireDAO implements TireDAOLocal {

  private final String queryInsertTire = "INSERT INTO `tires` (`id`, `orderId`) VALUES (NULL, ?)";
  private final String queryGetInsertedTireId = "SELECT LAST_INSERT_ID()";
  private final String queryCount = "SELECT count(*) FROM `tires`";

  @Resource(name = "jdbc/AMTCars")
  DataSource dataSource;

  @Override
  public Tire[] buildTires(UUID orderId, int numberOfTires) {
    Tire[] result = new Tire[numberOfTires];
    for (int i = 0; i < numberOfTires; i++) {
      result[i] = buildTire(orderId);
    }
    return result;
  }

  public long count() {
    try (Connection connection = dataSource.getConnection()) {
      ResultSet rs = connection
        .prepareStatement(queryCount)
        .executeQuery();
      rs.next();
      return rs.getLong(1);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private Tire buildTire(UUID orderId) {
    Tire tire = new Tire();

    PreparedStatement statement2;
    try (Connection connection = dataSource.getConnection()) {
      PreparedStatement statement = connection.prepareStatement(queryInsertTire);
      statement.setString(1, orderId.toString());
      statement.execute();
      statement2 = connection.prepareStatement(queryGetInsertedTireId);
      ResultSet rs = statement2.executeQuery();
      rs.next();
      Long engineId = rs.getLong(1);
      tire.setId(engineId);
      tire.setOderId(orderId);
      return tire;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
