package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Register User" page in the Webui app.
 *
 * @author Labinot Rashiti
 */
public class RegisterUserFluentPage extends AbstractWebuiFluentPage {

  private final static String inputName = "#inputName"; // id in the html code
  private final static String inputEmail = "#inputEmail"; // id in the html code
  private final static String inputPassword = "#inputPassword"; // id in the html code
  private final static String buttonRegister = "#buttonRegister"; // id in the html code

  @Override
  public void isAt() {assertThat(title()).isEqualTo("Register User");}
  public void typeName(String name) { fill(inputName).with(name); }
  public void typeEmail(String email) { fill(inputEmail).with(email); }
  public void typePassword(String password) { fill(inputPassword).with(password); }
  public void clickRegister() { click(buttonRegister); }
  public String getUrl() { return "/home"; }

}
