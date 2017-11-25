package uk.ac.herts.odeon.model;

public enum SeatType {
  REGULAR("REGULAR"),
  VIP("VIP");

  private String type;

  SeatType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}
