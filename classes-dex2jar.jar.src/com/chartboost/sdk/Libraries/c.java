package com.chartboost.sdk.Libraries;

import android.net.wifi.WifiManager;
import android.provider.Settings;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.al;
import com.chartboost.sdk.impl.am;

public class c {
  public static String a() {
    return b.b(c());
  }
  
  private static String b() {
    return d.c() ? null : b.b(b.a(e()));
  }
  
  private static byte[] c() {
    String str1 = d();
    String str2 = b();
    am am = new am();
    am.a("uuid", str1);
    am.a("macid", str2);
    return (new al()).a((aj)am);
  }
  
  private static String d() {
    return d.c() ? null : Settings.Secure.getString(Chartboost.sharedChartboost().getContext().getContentResolver(), "android_id");
  }
  
  private static byte[] e() {
    try {
      String str = ((WifiManager)Chartboost.sharedChartboost().getContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (str == null || str.equals(""))
        return null; 
      String[] arrayOfString = str.split(":");
      byte[] arrayOfByte = new byte[6];
      byte b = 0;
      while (true) {
        byte[] arrayOfByte1 = arrayOfByte;
        if (b < arrayOfString.length) {
          arrayOfByte[b] = Integer.valueOf(Integer.parseInt(arrayOfString[b], 16)).byteValue();
          b++;
          continue;
        } 
        return arrayOfByte1;
      } 
    } catch (Exception exception) {
      exception = null;
    } 
    return (byte[])exception;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Libraries\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */