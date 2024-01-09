package net.robotmedia.billing.c;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;
import java.lang.reflect.Method;

public class e {
  public static int a;
  
  private static Method b;
  
  private static final Class[] c = new Class[] { IntentSender.class, Intent.class, int.class, int.class, int.class };
  
  static {
    b();
  }
  
  public static void a(Activity paramActivity, IntentSender paramIntentSender, Intent paramIntent) {
    if (b != null)
      try {
        b.invoke(paramActivity, new Object[] { paramIntentSender, paramIntent, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) });
      } catch (Exception exception) {
        Log.e(e.class.getSimpleName(), "startIntentSender", exception);
      }  
  }
  
  public static boolean a() {
    return (b != null);
  }
  
  private static void b() {
    try {
      a = Service.class.getField("START_NOT_STICKY").getInt(null);
    } catch (Exception exception) {
      a = 2;
    } 
    try {
      b = Activity.class.getMethod("startIntentSender", c);
    } catch (SecurityException securityException) {
      b = null;
    } catch (NoSuchMethodException noSuchMethodException) {
      b = null;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\c\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */