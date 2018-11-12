package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Manage Apps" page in the Webui app.
 *
 * @author Labinot Rashiti
 */
public class ManageAppsFluentPage extends AbstractWebuiFluentPage {

  private final static String buttonAdd = "#buttonAdd"; // id in the html code
  private final static String buttonShow = "#buttonShow"; // id in the html code
  private final static String buttonEdit = "#buttonEdit"; // id in the html code
  private final static String buttonDelete = "#buttonDelete"; // id in the html code
  private final static String buttonPrevious = "#table_previous"; // id in the html code
  private final static String buttonNext = "#table_next"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Manage Apps");
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
  public void clickPrevious() {
    click(buttonPrevious);
  }
  public void clickNext() {
    click(buttonNext);
  }
  public String getUrl() {
    return "/home";
  }

}
