package com.appian.microservices.catalog.model;

public class Delete {

  private String sku;

  public Delete() {
  }

  public Delete(String sku) {
    this.sku = sku;
  }

  public String getSku() {
    return sku;
  }
}
