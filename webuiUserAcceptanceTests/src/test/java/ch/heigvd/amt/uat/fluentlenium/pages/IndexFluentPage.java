package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Login" page in the MVCDemo app.
 *
 * @author Olivier Liechti
 */
public class IndexFluentPage extends AbstractWebuiFluentPage {

  private final static String buttonLogin = "#buttonLogin"; // id in the html code
  private final static String buttonRegister = "#buttonRegister"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("About");
  }

  public void clickLogin() {
    click(buttonLogin);
  }
  public void clickRegister() {
    click(buttonRegister);
  }

  public String getUrl() {
    return "/login";
  }

}
