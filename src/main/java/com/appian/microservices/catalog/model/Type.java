package com.appian.microservices.catalog.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Type {

  INCREASE, DECREASE;

  @JsonCreator
  public static Type fromText(String text){
    return Type.valueOf(text.toUpperCase());
  }

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
