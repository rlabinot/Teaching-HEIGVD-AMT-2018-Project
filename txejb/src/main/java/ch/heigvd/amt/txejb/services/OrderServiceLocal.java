package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.Car;

import javax.ejb.Local;

@Local
public interface OrderServiceLocal {
  public Car orderCar(String customerName);
}
