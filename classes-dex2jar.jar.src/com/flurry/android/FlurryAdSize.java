package com.flurry.android;

public enum FlurryAdSize {
  BANNER_BOTTOM,
  BANNER_TOP(1),
  FULLSCREEN(1);
  
  private static final FlurryAdSize[] b;
  
  private int a;
  
  static {
    BANNER_BOTTOM = new FlurryAdSize("BANNER_BOTTOM", 1, 2);
    FULLSCREEN = new FlurryAdSize("FULLSCREEN", 2, 3);
    b = new FlurryAdSize[] { BANNER_TOP, BANNER_BOTTOM, FULLSCREEN };
  }
  
  FlurryAdSize(int paramInt1) {
    this.a = paramInt1;
  }
  
  final int a() {
    return this.a;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\FlurryAdSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */