package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterUserFluentPage extends AbstractWebuiFluentPage {

  private final static String inputName = "#inputName"; // id in the html code
  private final static String inputEmail = "#inputEmail"; // id in the html code
  private final static String inputPassword = "#inputPassword"; // id in the html code
  //private final static String inputNewPassword = "#inputNewPassword"; // id in the html code
  private final static String buttonRegister = "#buttonRegister"; // id in the html code

  @Override
  public void isAt() {assertThat(title()).isEqualTo("Register");}
  public void typeName(String name) { fill(inputName).with(name); }
  public void typeEmail(String email) { fill(inputEmail).with(email); }
  public void typePassword(String password) { fill(inputPassword).with(password); }
  //public void typeNewPassword(String newPassword) { fill(inputNewPassword).with(newPassword); }
  public void clickRegister() { click(buttonRegister); }
  public String getUrl() { return "/home"; }

}
