package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterAppFluentPage extends AbstractWebuiFluentPage {

  private final static String inputName = "#inputName"; // id in the html code
  private final static String inputDescription = "#inputDescription"; // id in the html code
  private final static String buttonRegister = "#buttonRegister"; // id in the html code

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Register App");
  }
  public void typeName(String name) { fill(inputName).with(name); }
  public void typeDescription(String description) { fill(inputDescription).with(description); }
  public void clickRegister() { click(buttonRegister); }
  public String getUrl() { return "/home"; }

}
