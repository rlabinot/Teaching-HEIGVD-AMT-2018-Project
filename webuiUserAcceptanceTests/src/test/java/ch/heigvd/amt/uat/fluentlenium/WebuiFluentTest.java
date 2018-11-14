package ch.heigvd.amt.uat.fluentlenium;

import ch.heigvd.amt.uat.fluentlenium.pages.*;
import com.github.javafaker.Faker;
import io.probedock.client.annotations.ProbeTest;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.fluentlenium.core.annotation.Page;
import java.util.UUID;

/**
 * This is the file used to list all the tests that will be done.
 *
 * @author Labinot Rashiti
 */
public class WebuiFluentTest extends FluentTest {

  // TODO : Change localhost for 192.168.99.100 if using docker-machine
  private final String loginUrl = "http://localhost:8080/webui/login";
  private final String baseUrl = "http://localhost:8080/webui";

  @Page public LoginFluentPage loginPage;
  @Page public RegisterUserFluentPage registerUserPage;
  @Page public ManageAppsFluentPage manageAppsPage;
  @Page public RegisterAppFluentPage registerAppPage;
  @Page public ErrorFluentPage errorPage;


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
    registerUserPage.typeName(Faker.instance().name().firstName());
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
    registerUserPage.typeName(Faker.instance().name().firstName());
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
      registerAppPage.typeName("Application" + i);
      registerAppPage.typeDescription("Random description");
      registerAppPage.clickRegister();
      manageAppsPage.isAt();
    }

    // TODO : CAREFUL WITH THESE CLICKS BECAUSE PAGINATION WILL BE UPDATED
    // Navigating with pagination
    // BE CAREFUL, our webapp display always the end of the list after an add
    manageAppsPage.isAt();
    manageAppsPage.clickPrevious();
    manageAppsPage.clickPrevious();
    manageAppsPage.clickNext();
    manageAppsPage.clickNext();
    manageAppsPage.isAt();

    // logout and check if can access to securised page with goTo
    manageAppsPage.goToLogoutPageViaMenu();
    loginPage.isAt();
    goTo(baseUrl + "/home");
    loginPage.isAt();
  }

  @Test
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

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToAccessToAdminContentWithAUser() {
    goTo(loginUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("user@stackoveramt.ch");
    loginPage.typePassword("user");
    loginPage.clickSignin();
    manageAppsPage.isAt();
    goTo(baseUrl + "/user?action=suspend&id=user@stackoveramt.ch"); // try to suspend user
    errorPage.isAt();
  }

  @Override
  public WebDriver getDefaultDriver() {
    // Be careful here, the default path is the root path for the driver
    System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
    return new ChromeDriver();
  }

  @Override
  public String getDefaultBaseUrl() {
    return baseUrl;
  }

}
