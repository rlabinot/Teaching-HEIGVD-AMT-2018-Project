package ch.heigvd.amt.uat.fluentlenium.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;

/**
 * Most pages served by the application have the same structure: they have the
 * same header (with a navigation menu) and the same footer. These elements, and
 * the WebDriver UI locators, are capture in this abstract class. This makes it
 * possible to issue "clicks" on the navigation menu via the API from any page,
 * without code duplication.
 *
 * @author Olivier Liechti and Labinot Rashiti
 */
public abstract class AbstractWebuiFluentPage extends FluentPage {

  /**
   * These locators are used to find elements in the web page. In the code, 'id'
   * is the HTML id attribute that is defined in the page markup. This shows
   * that when you write automated tests with Selenium, you have to prepare your
   * HTML code so that important elements have an id (which can then be
   * referenced in the test). Note that there are other types of locators. See:
   * http://www.seleniumhq.org/docs/03_webdriver.jsp#selenium-webdriver-api-commands-and-operations
   */
  private final static String menuItemUsers = "#menuItemUsers";
  private final static String menuItemApps = "#menuItemApps";
  private final static String menuItemLogout = "#menuItemLogout";

  /**
   * This method illustrates two aspects of the Page Object pattern.
   *
   * Firstly, notice that we expose a user intent (he wants to go to the Beers
   * List page by selecting the proper item in the navigation menu), but we hide
   * the details (when calling this method, you don't need to know about HTML
   * IDs and other implementation details that could change). It makes the tests
   * easier to read and more robust (if an HTML ID changes, you only need to
   * change the Page and not all the tests that use it).
   */
  public void goToManageUsersPageViaMenu() {
    click(menuItemUsers);
  }

  public void goToManageAppsPageViaMenu() {
    click(menuItemApps);
  }

  public void goToLogoutPageViaMenu() {
    click(menuItemLogout);
  }

}
