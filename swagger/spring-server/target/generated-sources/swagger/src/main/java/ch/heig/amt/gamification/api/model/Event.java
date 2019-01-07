package ch.heig.amt.gamification.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
/**
 * Event
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-07T16:35:12.037+01:00")

public class Event   {
  @JsonProperty("userId")
  private Integer userId = null;

  @JsonProperty("timestamp")
  private Integer timestamp = null;

  @JsonProperty("eventType")
  private String eventType = null;

  @JsonProperty("eventProperties")
  private List<String> eventProperties = new ArrayList<String>();

  public Event userId(Integer userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Event timestamp(Integer timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Get timestamp
   * @return timestamp
  **/
  @ApiModelProperty(value = "")
  public Integer getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Integer timestamp) {
    this.timestamp = timestamp;
  }

  public Event eventType(String eventType) {
    this.eventType = eventType;
    return this;
  }

   /**
   * Get eventType
   * @return eventType
  **/
  @ApiModelProperty(value = "")
  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public Event eventProperties(List<String> eventProperties) {
    this.eventProperties = eventProperties;
    return this;
  }

  public Event addEventPropertiesItem(String eventPropertiesItem) {
    this.eventProperties.add(eventPropertiesItem);
    return this;
  }

   /**
   * Get eventProperties
   * @return eventProperties
  **/
  @ApiModelProperty(value = "")
  public List<String> getEventProperties() {
    return eventProperties;
  }

  public void setEventProperties(List<String> eventProperties) {
    this.eventProperties = eventProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Event event = (Event) o;
    return Objects.equals(this.userId, event.userId) &&
        Objects.equals(this.timestamp, event.timestamp) &&
        Objects.equals(this.eventType, event.eventType) &&
        Objects.equals(this.eventProperties, event.eventProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, timestamp, eventType, eventProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Event {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    eventType: ").append(toIndentedString(eventType)).append("\n");
    sb.append("    eventProperties: ").append(toIndentedString(eventProperties)).append("\n");
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

