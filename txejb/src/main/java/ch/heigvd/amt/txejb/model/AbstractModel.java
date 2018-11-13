package ch.heigvd.amt.txejb.model;

import java.util.UUID;

public class AbstractModel {
  private Long id;
  private UUID oderId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getOderId() {
    return oderId;
  }

  public void setOderId(UUID oderId) {
    this.oderId = oderId;
  }

  @Override
  public String toString() {
    return "AbstractModel{" +
      "id=" + id +
      ", oderId=" + oderId +
      '}';
  }
}
