package com.appian.microservices.catalog.model;

public class Update {

  private String sku;
  private int quantity;
  private Type type;

  public Update() {
  }

  public Update(String sku, int quantity, Type type) {
    this.sku = sku;
    this.quantity = quantity;
    this.type = type;
  }

  public String getSku() {
    return sku;
  }

  public int getQuantity() {
    return quantity;
  }

  public Type getType() {
    return type;
  }
}
