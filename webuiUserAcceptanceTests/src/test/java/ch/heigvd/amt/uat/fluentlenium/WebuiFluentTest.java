package ch.heigvd.amt.uat.fluentlenium;

import ch.heigvd.amt.uat.fluentlenium.pages.*;
import io.probedock.client.annotations.ProbeTest;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.fluentlenium.core.annotation.Page;

import java.util.UUID;

/**
 * This is the file used to list all the tests that will be done.
 *
 * @author Labinot Rashiti
 */
public class WebuiFluentTest extends FluentTest {

  private final String loginUrl = "http://localhost:8080/webui/login";
  private final String baseUrl = "http://localhost:8080/webui";

  @Page public IndexFluentPage indexPage;
  @Page public LoginFluentPage loginPage;
  @Page public RegisterUserFluentPage registerUserPage;
  @Page public ManageAppsFluentPage manageAppsPage;
  @Page public RegisterAppFluentPage registerAppPage;

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
    goTo(loginUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("not a valid email");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    loginPage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToRegister() {
    goTo(loginUrl);
    loginPage.isAt();
    loginPage.clickRegister();
    registerUserPage.isAt();
    registerUserPage.typeName("Random");
    // UUID because we can't register 2 times
    String username = UUID.randomUUID().toString().substring(0,5);
    registerUserPage.typeEmail(username + "@stackoveramt.ch");
    registerUserPage.typePassword("Random123");
    registerUserPage.clickRegister();
    loginPage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToHaveAWeakPassword() {
    goTo(loginUrl);
    loginPage.isAt();
    loginPage.clickRegister();
    registerUserPage.isAt();
    registerUserPage.typeName("Random");
    // UUID because we can't register 2 times
    String username = UUID.randomUUID().toString().substring(0,5);
    registerUserPage.typeEmail(username + "@stackoveramt.ch");
    registerUserPage.typePassword("12345678");
    registerUserPage.clickRegister();
    registerUserPage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void successfulSigninShouldBringUserToHomePage() {
    goTo(loginUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("user@stackoveramt.ch");
    loginPage.typePassword("user");
    loginPage.clickSignin();
    manageAppsPage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToAddSomeApps() {
    goTo(loginUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("user@stackoveramt.ch");
    loginPage.typePassword("user");
    loginPage.clickSignin();
    manageAppsPage.isAt();

    // Adding 25 apps
    for (int i = 0; i < 25; ++i) {
      manageAppsPage.clickAdd();
      registerAppPage.isAt();
      registerAppPage.typeName("Name" + i);
      registerAppPage.typeDescription("Description" + i);
      registerAppPage.clickRegister();
      manageAppsPage.isAt();
    }

    // Navigating with pagination
    manageAppsPage.isAt();
    manageAppsPage.clickNext(); // 10 to 20 apps
    manageAppsPage.clickNext(); // 20 to 25 apps
    manageAppsPage.clickPrevious();
    manageAppsPage.clickPrevious();
    manageAppsPage.isAt();

    // logout and check if can access to securised page with goTo
    manageAppsPage.goToLogoutPageViaMenu();
    loginPage.isAt();
    goTo(baseUrl + "/home");
    loginPage.isAt();
  }


  // TODO : Manage SQL Exception and redirect the page to registerUserPage
  /**
  Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToRegisterWithSameUser() {
    goTo(loginUrl);
    loginPage.isAt();
    loginPage.clickRegister();
    registerUserPage.isAt();
    registerUserPage.typeName("user");
    registerUserPage.typeEmail("user@stackoveramt.ch");
    registerUserPage.typePassword("User12345");
    registerUserPage.clickRegister();
    registerUserPage.isAt();
  }
  **/

  // TODO : Need to found a way to edit the targeted app because there is one edit button for each app
  /**
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToAddAnAppEditItAndDeleteIt() {
    goTo(loginUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("user@stackoveramt.ch");
    loginPage.typePassword("user");
    loginPage.clickSignin();
    manageAppsPage.isAt();
    manageAppsPage.clickAdd();
    registerAppPage.isAt();
    registerAppPage.typeName("NewApp");
    registerAppPage.typeDescription("My NewApp");
    registerAppPage.clickRegister();
    manageAppsPage.isAt();
    manageAppsPage.clickEdit();
    registerAppPage.typeName("NewAppEdited");
    registerAppPage.clickRegister();
    manageAppsPage.isAt();
    manageAppsPage.clickDelete();
    manageAppsPage.isAt();
  }
   **/

  
  @Override
  public WebDriver getDefaultDriver() {
    //return new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", "C:/Users/Labinot/Documents/AMT/chromedriver.exe");
    return new ChromeDriver();
  }

  @Override
  public String getDefaultBaseUrl() {
    return baseUrl;
  }



  
}
