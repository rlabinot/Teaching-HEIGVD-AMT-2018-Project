package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class ManageAppsFluentPage extends AbstractWebuiFluentPage {

  private final static String buttonAdd = "#buttonAdd"; // id in the html code
  private final static String buttonShow = "#buttonShow"; // id in the html code
  private final static String buttonEdit = "#buttonEdit"; // id in the html code
  private final static String buttonDelete = "#buttonDelete"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Manage your apps");
  }
  public void clickAdd() {
    click(buttonAdd);
  }
  public void clickShow() {
        click(buttonShow);
    }
  public void clickEdit() {
        click(buttonEdit);
    }
  public void clickDelete() {
        click(buttonDelete);
    }
  public String getUrl() {
    return "/home";
  }

}
