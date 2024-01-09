package com.chartboost.sdk.impl;

public class ac {
  public static String a(Object paramObject) {
    StringBuilder stringBuilder = new StringBuilder();
    a(paramObject, stringBuilder);
    return stringBuilder.toString();
  }
  
  public static void a(Object paramObject, StringBuilder paramStringBuilder) {
    ad.a().a(paramObject, paramStringBuilder);
  }
  
  static void a(StringBuilder paramStringBuilder, String paramString) {
    paramStringBuilder.append("\"");
    for (byte b = 0; b < paramString.length(); b++) {
      char c = paramString.charAt(b);
      if (c == '\\') {
        paramStringBuilder.append("\\\\");
      } else if (c == '"') {
        paramStringBuilder.append("\\\"");
      } else if (c == '\n') {
        paramStringBuilder.append("\\n");
      } else if (c == '\r') {
        paramStringBuilder.append("\\r");
      } else if (c == '\t') {
        paramStringBuilder.append("\\t");
      } else if (c == '\b') {
        paramStringBuilder.append("\\b");
      } else if (c >= ' ') {
        paramStringBuilder.append(c);
      } 
    } 
    paramStringBuilder.append("\"");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */