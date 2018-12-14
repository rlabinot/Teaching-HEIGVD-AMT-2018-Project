package ch.heig.amt.gamification.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
/**
 * PointScale
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-12-14T10:59:31.034+01:00")

public class PointScale   {
  @JsonProperty("pointScaleName")
  private String pointScaleName = null;

  @JsonProperty("counter")
  private Integer counter = null;

  public PointScale pointScaleName(String pointScaleName) {
    this.pointScaleName = pointScaleName;
    return this;
  }

   /**
   * Get pointScaleName
   * @return pointScaleName
  **/
  @ApiModelProperty(value = "")
  public String getPointScaleName() {
    return pointScaleName;
  }

  public void setPointScaleName(String pointScaleName) {
    this.pointScaleName = pointScaleName;
  }

  public PointScale counter(Integer counter) {
    this.counter = counter;
    return this;
  }

   /**
   * Get counter
   * @return counter
  **/
  @ApiModelProperty(value = "")
  public Integer getCounter() {
    return counter;
  }

  public void setCounter(Integer counter) {
    this.counter = counter;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PointScale pointScale = (PointScale) o;
    return Objects.equals(this.pointScaleName, pointScale.pointScaleName) &&
        Objects.equals(this.counter, pointScale.counter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pointScaleName, counter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointScale {\n");
    
    sb.append("    pointScaleName: ").append(toIndentedString(pointScaleName)).append("\n");
    sb.append("    counter: ").append(toIndentedString(counter)).append("\n");
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

