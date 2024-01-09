package com.tapjoy;

import android.os.Build;
import java.lang.reflect.Field;

public class ah {
  public String a() {
    String str;
    try {
      Field field = Class.forName("android.os.Build").getDeclaredField("SERIAL");
      if (!field.isAccessible())
        field.setAccessible(true); 
      str = field.get(Build.class).toString();
      try {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        aj.a("TapjoyHardwareUtil", stringBuilder.append("serial: ").append(str).toString());
        return str;
      } catch (Exception null) {}
    } catch (Exception exception) {
      str = null;
    } 
    aj.b("TapjoyHardwareUtil", exception.toString());
    return str;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */