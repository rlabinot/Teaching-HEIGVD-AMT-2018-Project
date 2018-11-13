package ch.heigvd.amt.txejb.model;

import java.util.Arrays;

public class Car extends AbstractModel {

  private Engine engine;

  private Tire[] tires;

  private String customerName;

  public Engine getEngine() {
    return engine;
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  public Tire[] getTires() {
    return tires;
  }

  public void setTires(Tire[] tires) {
    this.tires = tires;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  @Override
  public String toString() {
    return "Car{" +
      "engine=" + engine +
      ", tires=" + Arrays.toString(tires) +
      ", customerName='" + customerName + '\'' +
      "} " + super.toString();
  }
}
