package com.flurry.org.codehaus.jackson.map.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils {
  private static final String GMT_ID = "GMT";
  
  private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT");
  
  private static void checkOffset(String paramString, int paramInt, char paramChar) {
    char c = paramString.charAt(paramInt);
    if (c != paramChar)
      throw new IndexOutOfBoundsException("Expected '" + paramChar + "' character but found '" + c + "'"); 
  }
  
  public static String format(Date paramDate) {
    return format(paramDate, false, TIMEZONE_GMT);
  }
  
  public static String format(Date paramDate, boolean paramBoolean) {
    return format(paramDate, paramBoolean, TIMEZONE_GMT);
  }
  
  public static String format(Date paramDate, boolean paramBoolean, TimeZone paramTimeZone) {
    int i;
    int j;
    GregorianCalendar gregorianCalendar = new GregorianCalendar(paramTimeZone, Locale.US);
    gregorianCalendar.setTime(paramDate);
    int k = "yyyy-MM-ddThh:mm:ss".length();
    if (paramBoolean) {
      i = ".sss".length();
    } else {
      i = 0;
    } 
    if (paramTimeZone.getRawOffset() == 0) {
      j = "Z".length();
    } else {
      j = "+hh:mm".length();
    } 
    StringBuilder stringBuilder = new StringBuilder(j + k + i);
    padInt(stringBuilder, gregorianCalendar.get(1), "yyyy".length());
    stringBuilder.append('-');
    padInt(stringBuilder, gregorianCalendar.get(2) + 1, "MM".length());
    stringBuilder.append('-');
    padInt(stringBuilder, gregorianCalendar.get(5), "dd".length());
    stringBuilder.append('T');
    padInt(stringBuilder, gregorianCalendar.get(11), "hh".length());
    stringBuilder.append(':');
    padInt(stringBuilder, gregorianCalendar.get(12), "mm".length());
    stringBuilder.append(':');
    padInt(stringBuilder, gregorianCalendar.get(13), "ss".length());
    if (paramBoolean) {
      stringBuilder.append('.');
      padInt(stringBuilder, gregorianCalendar.get(14), "sss".length());
    } 
    k = paramTimeZone.getOffset(gregorianCalendar.getTimeInMillis());
    if (k != 0) {
      byte b;
      j = Math.abs(k / 60000 / 60);
      i = Math.abs(k / 60000 % 60);
      if (k < 0) {
        b = 45;
      } else {
        b = 43;
      } 
      stringBuilder.append(b);
      padInt(stringBuilder, j, "hh".length());
      stringBuilder.append(':');
      padInt(stringBuilder, i, "mm".length());
      return stringBuilder.toString();
    } 
    stringBuilder.append('Z');
    return stringBuilder.toString();
  }
  
  private static void padInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2) {
    String str = Integer.toString(paramInt1);
    for (paramInt1 = paramInt2 - str.length(); paramInt1 > 0; paramInt1--)
      paramStringBuilder.append('0'); 
    paramStringBuilder.append(str);
  }
  
  public static Date parse(String paramString) {
    try {
      byte b;
      boolean bool;
      IndexOutOfBoundsException indexOutOfBoundsException;
      int k = parseInt(paramString, 0, 4);
      checkOffset(paramString, 4, '-');
      int i1 = parseInt(paramString, 5, 7);
      checkOffset(paramString, 7, '-');
      int i = parseInt(paramString, 8, 10);
      checkOffset(paramString, 10, 'T');
      int n = parseInt(paramString, 11, 13);
      checkOffset(paramString, 13, ':');
      int m = parseInt(paramString, 14, 16);
      checkOffset(paramString, 16, ':');
      int j = parseInt(paramString, 17, 19);
      if (paramString.charAt(19) == '.') {
        checkOffset(paramString, 19, '.');
        bool = parseInt(paramString, 20, 23);
        b = 23;
      } else {
        b = 19;
        bool = false;
      } 
      char c = paramString.charAt(b);
      if (c == '+' || c == '-') {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        String str = stringBuilder.append("GMT").append(paramString.substring(b)).toString();
      } else if (c == 'Z') {
        String str = "GMT";
      } else {
        indexOutOfBoundsException = new IndexOutOfBoundsException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        this(stringBuilder.append("Invalid time zone indicator ").append(c).toString());
        throw indexOutOfBoundsException;
      } 
      TimeZone timeZone = TimeZone.getTimeZone((String)indexOutOfBoundsException);
      if (!timeZone.getID().equals(indexOutOfBoundsException)) {
        indexOutOfBoundsException = new IndexOutOfBoundsException();
        this();
        throw indexOutOfBoundsException;
      } 
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      this(timeZone);
      gregorianCalendar.setLenient(false);
      gregorianCalendar.set(1, k);
      gregorianCalendar.set(2, i1 - 1);
      gregorianCalendar.set(5, i);
      gregorianCalendar.set(11, n);
      gregorianCalendar.set(12, m);
      gregorianCalendar.set(13, j);
      gregorianCalendar.set(14, bool);
      return gregorianCalendar.getTime();
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      throw new IllegalArgumentException("Failed to parse date " + paramString, indexOutOfBoundsException);
    } catch (NumberFormatException numberFormatException) {
      throw new IllegalArgumentException("Failed to parse date " + paramString, numberFormatException);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new IllegalArgumentException("Failed to parse date " + paramString, illegalArgumentException);
    } 
  }
  
  private static int parseInt(String paramString, int paramInt1, int paramInt2) {
    if (paramInt1 < 0 || paramInt2 > paramString.length() || paramInt1 > paramInt2)
      throw new NumberFormatException(paramString); 
    int j = 0;
    int i = paramInt1;
    if (paramInt1 < paramInt2) {
      i = Character.digit(paramString.charAt(paramInt1), 10);
      if (i < 0)
        throw new NumberFormatException("Invalid number: " + paramString); 
      j = -i;
      i = paramInt1 + 1;
    } 
    while (i < paramInt2) {
      paramInt1 = Character.digit(paramString.charAt(i), 10);
      if (paramInt1 < 0)
        throw new NumberFormatException("Invalid number: " + paramString); 
      j = j * 10 - paramInt1;
      i++;
    } 
    return -j;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\ISO8601Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */