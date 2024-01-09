package com.chartboost.sdk.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

class ad$i extends ad$c {
  ad$i(af paramaf) {
    super(paramaf);
  }
  
  public void a(Object paramObject, StringBuilder paramStringBuilder) {
    Date date = (Date)paramObject;
    paramObject = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    paramObject.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
    this.a.a(new w("$date", paramObject.format(date)), paramStringBuilder);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ad$i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */