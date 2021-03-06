package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Show App" page in the Webui app.
 *
 * @author Labinot Rashiti
 */
public class ShowAppFluentPage extends AbstractWebuiFluentPage {

  private final static String buttonEdit = "#buttonEdit"; // id in the html code
  private final static String buttonDelete = "#buttonDelete"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Edit App");
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
