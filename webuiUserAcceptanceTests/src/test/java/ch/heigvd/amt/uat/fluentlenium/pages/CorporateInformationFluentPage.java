package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Corporate Information" page in the MVCDemo
 * app.
 *
 * @author Olivier Liechti
 */
public class CorporateInformationFluentPage extends AbstractWebuiFluentPage {

  /*
   * See: http://elementalselenium.com/tips/25-tables
   */
  private static final String allCompanyLinks = "#companiesTable tbody tr td:nth-of-type(1) a";

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Corporate Information");
  }

  public void clickOnFirstCompanyLinkInCompaniesTable() {
    find(allCompanyLinks).first().click();
  }

  public String getUrl() {
    return "/pages/corporateInformation";
  }

}
