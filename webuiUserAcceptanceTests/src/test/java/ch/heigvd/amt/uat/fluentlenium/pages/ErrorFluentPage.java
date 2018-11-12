package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "404" page in the Webui app.
 *
 * @author Labinot Rashiti
 */
public class ErrorFluentPage extends AbstractWebuiFluentPage {

  private final static String buttonHome = "#buttonHome"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("404 Page");
  }
  public void clickHome() {
    click(buttonHome);
  }
  public String getUrl() {
    return "/404";
  }

}
