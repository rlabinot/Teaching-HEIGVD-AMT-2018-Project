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
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class WebuiFluentTest extends FluentTest {

  private final String baseUrl = "http://localhost:8080/webui/login";

  @Page public IndexFluentPage indexPage;
  @Page public LoginFluentPage loginPage;
  @Page public RegisterUserFluentPage registerUserPage;
  @Page public ManageAppsFluentPage manageAppsPage;
  @Page public RegisterAppFluentPage registerAppPage;

  /**
  @Page
  public ChngPasswordFluentPage chngPasswordFluentPage;
  @Page
  public EditAppFluentPage editAppFluentPage;
  @Page
  public LoginFluentPage loginPage;
  @Page
  public manageAppsPage manageAppsPage;
  @Page
  public ManageUsersFluentPage manageUsersFluentPage;
  @Page
  public RegisterAppFluentPage registerAppFluentPage;
  @Page
  public RegisterUserFluentPage registerUserFluentPage;
  @Page
  public ShowAppFluentPage showAppFluentPage;
  **/

  /**
  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("not a valid email");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    loginPage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void successfulSigninShouldBringUserToHomePage() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    homePage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToGetDetailsForACompanyAfterSignin() {
    goTo(corporateInformationPage);
    loginPage.isAt(); // we have not logged in, so we should be redirected
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    corporateInformationPage.isAt(); // we should be redirected toward the original target after signin
    corporateInformationPage.clickOnFirstCompanyLinkInCompaniesTable();
    companyDetailsPage.isAt();
  }

   **/


  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("not a valid email");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    loginPage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToRegister() {
    goTo(baseUrl);
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
    goTo(baseUrl);
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
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("user@stackoveramt.ch");
    loginPage.typePassword("user");
    loginPage.clickSignin();
    manageAppsPage.isAt();
  }

  // TODO : Manage SQL Exception and redirect the page to registerUserPage
  /**
  Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToRegisterWithSameUser() {
    goTo(baseUrl);
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
    goTo(baseUrl);
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
