package com.appian.microservices.catalog.repository;

import static com.appian.microservices.catalog.model.Status.ACTIVE;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.appian.microservices.catalog.model.Status;
import com.appian.microservices.catalog.util.Utils;

@Document(collection="catalog")
public class Inventory {

  @Id
  private String id;
  private String sku;
  private int quantity;
  private Status status;
  private long timestamp;

  public Inventory() {
    this.timestamp = Utils.localToUTC();
    this.status = ACTIVE;
  }

  public Inventory(String sku, int quantity) {
    this.sku = sku;
    this.quantity = quantity;
    this.timestamp = Utils.localToUTC();
    this.status = ACTIVE;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
