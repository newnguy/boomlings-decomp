package com.flurry.org.codehaus.jackson.map.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ISO8601DateFormat extends DateFormat {
  private static Calendar CALENDAR = new GregorianCalendar();
  
  private static NumberFormat NUMBER_FORMAT = new DecimalFormat();
  
  private static final long serialVersionUID = 1L;
  
  public Object clone() {
    return this;
  }
  
  public StringBuffer format(Date paramDate, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    paramStringBuffer.append(ISO8601Utils.format(paramDate));
    return paramStringBuffer;
  }
  
  public Date parse(String paramString, ParsePosition paramParsePosition) {
    paramParsePosition.setIndex(paramString.length());
    return ISO8601Utils.parse(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\ISO8601DateFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */