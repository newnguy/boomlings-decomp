package net.robotmedia.billing.c;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import java.security.SecureRandom;
import java.util.HashSet;

public class g {
  private static HashSet a = new HashSet();
  
  private static final SecureRandom b = new SecureRandom();
  
  private static final String c = g.class.getSimpleName();
  
  private static a d = null;
  
  public static long a() {
    long l = b.nextLong();
    a.add(Long.valueOf(l));
    return l;
  }
  
  public static String a(Context paramContext, byte[] paramArrayOfbyte, String paramString) {
    return a(paramContext, paramArrayOfbyte).a(paramString);
  }
  
  private static a a(Context paramContext, byte[] paramArrayOfbyte) {
    if (d == null) {
      String str2 = f.a(paramContext);
      String str1 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      d = new a(paramArrayOfbyte, String.valueOf(str2) + str1 + paramContext.getPackageName());
    } 
    return d;
  }
  
  public static boolean a(long paramLong) {
    return a.contains(Long.valueOf(paramLong));
  }
  
  public static String b(Context paramContext, byte[] paramArrayOfbyte, String paramString) {
    a a1 = a(paramContext, paramArrayOfbyte);
    try {
      String str = a1.b(paramString);
    } catch (b b) {
      Log.w(c, "Invalid obfuscated data or key");
      b = null;
    } 
    return (String)b;
  }
  
  public static void b(long paramLong) {
    a.remove(Long.valueOf(paramLong));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\c\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */