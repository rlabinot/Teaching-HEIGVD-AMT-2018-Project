package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.Engine;

import javax.ejb.Local;
import java.util.UUID;

@Local
public interface EngineDAOLocal {

  public Engine buildEngine(UUID orderId);

}
