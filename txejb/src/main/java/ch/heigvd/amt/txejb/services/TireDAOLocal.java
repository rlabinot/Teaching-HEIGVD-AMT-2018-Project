package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.Tire;

import javax.ejb.Local;
import java.util.UUID;

@Local
public interface TireDAOLocal {

  public Tire[] buildTires(UUID orderId, int numberOfTires);

  public long count();

}
