package uk.ac.herts.odeon.model;

import java.util.Arrays;
import java.util.List;

public enum ScreeningType {
  AFTERNOON("Afternoon"),
  EVENING("Evening"),
  NIGHT("Night"),
  LATE_NIGHT("Late Night");

  private String value;

  ScreeningType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

  public static ScreeningType getType(String type) {
    for(ScreeningType st: ScreeningType.values()) {
      if(st.getValue().equals(type)) { return st; }
    }

    return null;
  }

  public static List<ScreeningType> asList() {
    return Arrays.asList(ScreeningType.values());
  }
}
