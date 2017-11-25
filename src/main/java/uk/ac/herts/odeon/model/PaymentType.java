package uk.ac.herts.odeon.model;

public enum PaymentType {
  CREDIT_CARD("CC"),
  CASH("CASH");

  private String type;

  PaymentType(String type) {
    this.type = type;
  }

  public String getType() {
    return this.type;
  }
}
