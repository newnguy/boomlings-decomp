package com.flurry.android;

public enum FlurryAdType {
  VIDEO_TAKEOVER, WEB_BANNER, WEB_TAKEOVER;
  
  private static final FlurryAdType[] a;
  
  static {
    VIDEO_TAKEOVER = new FlurryAdType("VIDEO_TAKEOVER", 2);
    a = new FlurryAdType[] { WEB_BANNER, WEB_TAKEOVER, VIDEO_TAKEOVER };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\FlurryAdType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */