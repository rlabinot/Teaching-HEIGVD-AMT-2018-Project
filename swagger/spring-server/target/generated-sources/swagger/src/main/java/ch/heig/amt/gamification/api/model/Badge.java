package ch.heig.amt.gamification.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * Badge
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-21T10:39:27.116+01:00")

public class Badge   {
  @JsonProperty("badgeId")
  private Integer badgeId = null;

  @JsonProperty("badgeName")
  private String badgeName = null;

  public Badge badgeId(Integer badgeId) {
    this.badgeId = badgeId;
    return this;
  }

   /**
   * Get badgeId
   * @return badgeId
  **/
  @ApiModelProperty(value = "")
  public Integer getBadgeId() {
    return badgeId;
  }

  public void setBadgeId(Integer badgeId) {
    this.badgeId = badgeId;
  }

  public Badge badgeName(String badgeName) {
    this.badgeName = badgeName;
    return this;
  }

   /**
   * Get badgeName
   * @return badgeName
  **/
  @ApiModelProperty(value = "")
  public String getBadgeName() {
    return badgeName;
  }

  public void setBadgeName(String badgeName) {
    this.badgeName = badgeName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Badge badge = (Badge) o;
    return Objects.equals(this.badgeId, badge.badgeId) &&
        Objects.equals(this.badgeName, badge.badgeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(badgeId, badgeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Badge {\n");
    
    sb.append("    badgeId: ").append(toIndentedString(badgeId)).append("\n");
    sb.append("    badgeName: ").append(toIndentedString(badgeName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

