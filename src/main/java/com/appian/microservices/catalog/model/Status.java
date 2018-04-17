package com.appian.microservices.catalog.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
  ACTIVE, INACTIVE;

  @JsonCreator
  public static Status fromText(String text){
    return Status.valueOf(text.toUpperCase());
  }

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
