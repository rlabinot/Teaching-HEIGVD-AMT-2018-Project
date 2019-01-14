package ch.heig.amt.gamification.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * Rule
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-14T16:24:30.542+01:00")

public class Rule   {
  @JsonProperty("ruleName")
  private String ruleName = null;

  @JsonProperty("badgeId")
  private Integer badgeId = null;

  @JsonProperty("pointScale")
  private String pointScale = null;

  @JsonProperty("eventTrigger")
  private String eventTrigger = null;

  public Rule ruleName(String ruleName) {
    this.ruleName = ruleName;
    return this;
  }

   /**
   * Get ruleName
   * @return ruleName
  **/
  @ApiModelProperty(value = "")
  public String getRuleName() {
    return ruleName;
  }

  public void setRuleName(String ruleName) {
    this.ruleName = ruleName;
  }

  public Rule badgeId(Integer badgeId) {
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

  public Rule pointScale(String pointScale) {
    this.pointScale = pointScale;
    return this;
  }

   /**
   * Get pointScale
   * @return pointScale
  **/
  @ApiModelProperty(value = "")
  public String getPointScale() {
    return pointScale;
  }

  public void setPointScale(String pointScale) {
    this.pointScale = pointScale;
  }

  public Rule eventTrigger(String eventTrigger) {
    this.eventTrigger = eventTrigger;
    return this;
  }

   /**
   * Get eventTrigger
   * @return eventTrigger
  **/
  @ApiModelProperty(value = "")
  public String getEventTrigger() {
    return eventTrigger;
  }

  public void setEventTrigger(String eventTrigger) {
    this.eventTrigger = eventTrigger;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rule rule = (Rule) o;
    return Objects.equals(this.ruleName, rule.ruleName) &&
        Objects.equals(this.badgeId, rule.badgeId) &&
        Objects.equals(this.pointScale, rule.pointScale) &&
        Objects.equals(this.eventTrigger, rule.eventTrigger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ruleName, badgeId, pointScale, eventTrigger);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rule {\n");
    
    sb.append("    ruleName: ").append(toIndentedString(ruleName)).append("\n");
    sb.append("    badgeId: ").append(toIndentedString(badgeId)).append("\n");
    sb.append("    pointScale: ").append(toIndentedString(pointScale)).append("\n");
    sb.append("    eventTrigger: ").append(toIndentedString(eventTrigger)).append("\n");
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

