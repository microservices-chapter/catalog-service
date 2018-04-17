package com.appian.microservices.catalog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class Utils {

  private Utils(){}

  public static long localToUTC() {
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    try {
      return sdf.parse(sdf.format(date)).getTime();
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

}
