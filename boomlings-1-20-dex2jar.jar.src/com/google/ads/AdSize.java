package com.google.ads;

import android.content.Context;
import android.util.DisplayMetrics;

public class AdSize {
  public static final int AUTO_HEIGHT = -2;
  
  public static final AdSize BANNER;
  
  public static final int FULL_WIDTH = -1;
  
  public static final AdSize IAB_BANNER;
  
  public static final AdSize IAB_LEADERBOARD;
  
  public static final AdSize IAB_MRECT;
  
  public static final AdSize IAB_WIDE_SKYSCRAPER;
  
  public static final int LANDSCAPE_AD_HEIGHT = 32;
  
  public static final int LARGE_AD_HEIGHT = 90;
  
  public static final int PORTRAIT_AD_HEIGHT = 50;
  
  public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
  
  private final int a;
  
  private final int b;
  
  private boolean c;
  
  private boolean d;
  
  private boolean e;
  
  private String f;
  
  static {
    BANNER = new AdSize(320, 50, "mb");
    IAB_MRECT = new AdSize(300, 250, "as");
    IAB_BANNER = new AdSize(468, 60, "as");
    IAB_LEADERBOARD = new AdSize(728, 90, "as");
    IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
  }
  
  public AdSize(int paramInt1, int paramInt2) {
    this(paramInt1, paramInt2, null);
    if (f()) {
      this.e = false;
      this.f = "mb";
      return;
    } 
    this.e = true;
  }
  
  private AdSize(int paramInt1, int paramInt2, String paramString) {
    boolean bool1;
    this.a = paramInt1;
    this.b = paramInt2;
    this.f = paramString;
    if (paramInt1 == -1) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.c = bool1;
    if (paramInt2 == -2) {
      bool1 = bool2;
    } else {
      bool1 = false;
    } 
    this.d = bool1;
    this.e = false;
  }
  
  private static int a(Context paramContext) {
    DisplayMetrics displayMetrics = paramContext.getResources().getDisplayMetrics();
    return (int)(displayMetrics.widthPixels / displayMetrics.density);
  }
  
  public static AdSize a(AdSize paramAdSize, Context paramContext) {
    int i;
    int j;
    if (paramContext == null || !paramAdSize.f()) {
      adSize = paramAdSize;
      if (paramAdSize.f())
        adSize = BANNER; 
      return adSize;
    } 
    if (paramAdSize.c) {
      i = a((Context)adSize);
    } else {
      i = paramAdSize.a();
    } 
    if (paramAdSize.d) {
      j = b((Context)adSize);
    } else {
      j = paramAdSize.b();
    } 
    AdSize adSize = new AdSize(i, j, paramAdSize.f);
    adSize.d = paramAdSize.d;
    adSize.c = paramAdSize.c;
    adSize.e = paramAdSize.e;
    return adSize;
  }
  
  private static int b(Context paramContext) {
    DisplayMetrics displayMetrics = paramContext.getResources().getDisplayMetrics();
    null = (int)(displayMetrics.heightPixels / displayMetrics.density);
    return (null <= 400) ? 32 : ((null <= 720) ? 50 : 90);
  }
  
  private boolean f() {
    return (this.a < 0 || this.b < 0);
  }
  
  public int a() {
    if (this.a < 0)
      throw new UnsupportedOperationException("Ad size was not set before getWidth() was called."); 
    return this.a;
  }
  
  public AdSize a(AdSize... paramVarArgs) {
    AdSize adSize2 = null;
    AdSize adSize1 = null;
    double d = 0.0D;
    if (paramVarArgs != null) {
      Object object;
      int i = paramVarArgs.length;
      byte b = 0;
      while (true) {
        adSize2 = adSize1;
        if (b < i) {
          adSize2 = paramVarArgs[b];
          if (a(adSize2.a, adSize2.b)) {
            double d2 = adSize2.a * adSize2.b / this.a * this.b;
            double d1 = d2;
            if (d2 > 1.0D)
              d1 = 1.0D / d2; 
            if (d1 > object) {
              adSize1 = adSize2;
              continue;
            } 
          } 
        } else {
          return adSize2;
        } 
        Object object1 = object;
        continue;
        b++;
        object = SYNTHETIC_LOCAL_VARIABLE_4;
      } 
    } 
    return adSize2;
  }
  
  public boolean a(int paramInt1, int paramInt2) {
    return (paramInt1 <= this.a * 1.25D && paramInt1 >= this.a * 0.8D && paramInt2 <= this.b * 1.25D && paramInt2 >= this.b * 0.8D);
  }
  
  public int b() {
    if (this.b < 0)
      throw new UnsupportedOperationException("Ad size was not set before getHeight() was called."); 
    return this.b;
  }
  
  public boolean c() {
    return this.c;
  }
  
  public boolean d() {
    return this.d;
  }
  
  public boolean e() {
    return this.e;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (!(paramObject instanceof AdSize))
      return bool2; 
    paramObject = paramObject;
    boolean bool1 = bool2;
    if (this.a == ((AdSize)paramObject).a) {
      bool1 = bool2;
      if (this.b == ((AdSize)paramObject).b)
        bool1 = true; 
    } 
    return bool1;
  }
  
  public int hashCode() {
    return Integer.valueOf(this.a).hashCode() << 16 | Integer.valueOf(this.b).hashCode() & 0xFFFF;
  }
  
  public String toString() {
    StringBuilder stringBuilder = (new StringBuilder()).append(a()).append("x").append(b());
    if (this.f == null) {
      String str1 = "";
      return stringBuilder.append(str1).toString();
    } 
    String str = "_" + this.f;
    return stringBuilder.append(str).toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\AdSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */