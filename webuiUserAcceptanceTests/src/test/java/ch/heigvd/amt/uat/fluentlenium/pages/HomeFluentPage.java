package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Home" page in the Webui app.
 * @author Labinot Rashiti
 */
public class HomeFluentPage extends AbstractWebuiFluentPage {

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Manage your apps");
  }

  public String getUrl() {
    return "/home";
  }

}
