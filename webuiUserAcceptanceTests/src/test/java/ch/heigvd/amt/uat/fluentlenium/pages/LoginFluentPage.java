package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Login" page in the Webui app.
 *
 * @author Labinot Rashiti
 */
public class LoginFluentPage extends AbstractWebuiFluentPage {

  private final static String inputEmail = "#inputEmail"; // id in the html code
  private final static String inputPassword = "#inputPassword"; // id in the html code
  private final static String buttonSignin = "#buttonSignIn"; // id in the html code
  private final static String buttonRegister = "#buttonRegister"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Login");
  }

  public void typeEmailAddress(String email) {
    fill(inputEmail).with(email);
  }

  public void typePassword(String password) {
    fill(inputPassword).with(password);
  }

  public void clickSignin() {
    click(buttonSignin);
  }

  public void clickRegister() {
    click(buttonRegister);
  }

  public String getUrl() {
    return "/login";
  }

}
