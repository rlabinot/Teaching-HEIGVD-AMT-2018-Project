package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Manage Users" page in the Webui app.
 *
 * @author Labinot Rashiti
 */
public class ManageUsersFluentPage extends AbstractWebuiFluentPage {

  private final static String buttonSuspend = "#buttonSuspend"; // id in the html code
  private final static String buttonActivate = "#buttonActivate"; // id in the html code
  private final static String buttonResetPassword = "#buttonResetPassword"; // id in the html code
  private final static String buttonDelete = "#buttonDelete"; // id in the html code
  private final static String buttonPrevious = "#buttonPrevious"; // id in the html code
  private final static String buttonNext = "#buttonNext"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Manage Users");
  }
  public void clickSuspend() {
    click(buttonSuspend);
  }
  public void clickActivate() {
    click(buttonActivate);
  }
  public void clickResetPassword() {
        click(buttonResetPassword);
    }
  public void clickDelete() {
        click(buttonDelete);
    }
  public void clickPrevious() {find(buttonPrevious).first().click();}
  public void clickNext() {find(buttonNext).first().click();}
  public String getUrl() {
    return "/home";
  }

}
