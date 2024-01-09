package com.google.ads.util;

import android.content.Context;
import android.util.DisplayMetrics;

public final class e {
  private static int a(Context paramContext, float paramFloat, int paramInt) {
    int i = paramInt;
    if (((paramContext.getApplicationInfo()).flags & 0x2000) != 0)
      i = (int)(paramInt / paramFloat); 
    return i;
  }
  
  public static int a(Context paramContext, DisplayMetrics paramDisplayMetrics) {
    return a(paramContext, paramDisplayMetrics.density, paramDisplayMetrics.heightPixels);
  }
  
  public static int b(Context paramContext, DisplayMetrics paramDisplayMetrics) {
    return a(paramContext, paramDisplayMetrics.density, paramDisplayMetrics.widthPixels);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */