package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Change Password" page in the Webui app.
 *
 * @author Labinot Rashiti
 */
public class ChngPasswordFluentPage extends AbstractWebuiFluentPage {

  private final static String inputPassword = "#inputPassword"; // id in the html code
  private final static String inputNewPassword = "#inputNewPassword"; // id in the html code
  private final static String buttonContinue = "#buttonContinue"; // id in the html code

  @Override
  public void isAt() {assertThat(title()).isEqualTo("Change Password");}
  public void typePassword(String password) { fill(inputPassword).with(password); }
  public void typeNewPassword(String newPassword) { fill(inputNewPassword).with(newPassword); }
  public void clickContinue() { click(buttonContinue); }
  public String getUrl() { return "/password"; }

}
