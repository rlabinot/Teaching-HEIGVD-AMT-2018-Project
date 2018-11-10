package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class ManageUsersFluentPage extends AbstractWebuiFluentPage {

  private final static String buttonSuspend = "#buttonSuspend"; // id in the html code
  private final static String buttonActivate = "#buttonActivate"; // id in the html code
  private final static String buttonResetPassword = "#buttonResetPassword"; // id in the html code
  private final static String buttonDelete = "#buttonDelete"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Manage your users");
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
  public String getUrl() {
    return "/home";
  }

}
