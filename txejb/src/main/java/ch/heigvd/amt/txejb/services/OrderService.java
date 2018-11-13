package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.Car;
import ch.heigvd.amt.txejb.model.Engine;
import ch.heigvd.amt.txejb.model.Tire;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class OrderService implements OrderServiceLocal {

  private final String queryInsertCar = "INSERT INTO `cars` (`id`, `model`, `color`) VALUES (NULL, ?, ?)";
  private final String queryGetInsertedCarId = "SELECT LAST_INSERT_ID()";

  @EJB
  EngineDAOLocal engineDAO;

  @EJB
  TireDAOLocal tireDAO;

  @Override
  public Car orderCar(String customerName) {
    UUID orderId = UUID.randomUUID();

    Tire[] tires = tireDAO.buildTires(orderId, 4);
    Engine engine = engineDAO.buildEngine(orderId);

    Car car = new Car();
    car.setId(4242L);
    car.setEngine(engine);
    car.setTires(tires);

    return car;
  }

}
