package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Home" page in the MVCDemo app. Notice that in
 * the constructor, we check if we are on the correct page by checking the HTML
 * title of the page. This is used to detect navigation issues (for example, you
 * expect to arrive on the Beers page, but the title of the actual page is
 * "Login Page" because of some error.
 *
 * @author Olivier Liechti
 */
public class HomeFluentPage extends AbstractWebuiFluentPage {

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Manage your apps");
  }

  public String getUrl() {
    return "/home";
  }

}
