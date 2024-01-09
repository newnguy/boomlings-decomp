package com.google.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class ah {
  public static boolean a(Context paramContext) {
    Intent intent = new Intent();
    intent.setComponent(new ComponentName("com.google.android.apps.plus", "com.google.android.apps.circles.platform.PlusOneActivity"));
    return a(intent, paramContext);
  }
  
  public static boolean a(Intent paramIntent, Context paramContext) {
    return (paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */