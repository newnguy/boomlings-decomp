package com.chartboost.sdk.Libraries;

public enum CBOrientation {
  LANDSCAPE, LANDSCAPE_REVERSE, PORTRAIT, PORTRAIT_REVERSE, UNSPECIFIED;
  
  public static final CBOrientation LANDSCAPE_LEFT;
  
  public static final CBOrientation LANDSCAPE_RIGHT;
  
  public static final CBOrientation PORTRAIT_LEFT;
  
  public static final CBOrientation PORTRAIT_RIGHT;
  
  private static int[] a;
  
  private static final CBOrientation[] b;
  
  static {
    PORTRAIT = new CBOrientation("PORTRAIT", 1);
    LANDSCAPE = new CBOrientation("LANDSCAPE", 2);
    PORTRAIT_REVERSE = new CBOrientation("PORTRAIT_REVERSE", 3);
    LANDSCAPE_REVERSE = new CBOrientation("LANDSCAPE_REVERSE", 4);
    b = new CBOrientation[] { UNSPECIFIED, PORTRAIT, LANDSCAPE, PORTRAIT_REVERSE, LANDSCAPE_REVERSE };
    PORTRAIT_LEFT = PORTRAIT_REVERSE;
    PORTRAIT_RIGHT = PORTRAIT;
    LANDSCAPE_LEFT = LANDSCAPE;
    LANDSCAPE_RIGHT = LANDSCAPE_REVERSE;
  }
  
  public boolean isLandscape() {
    return !(this != LANDSCAPE && this != LANDSCAPE_REVERSE);
  }
  
  public boolean isPortrait() {
    return !(this != PORTRAIT && this != PORTRAIT_REVERSE);
  }
  
  public CBOrientation rotate180() {
    return rotate90().rotate90();
  }
  
  public CBOrientation rotate270() {
    return rotate90().rotate90().rotate90();
  }
  
  public CBOrientation rotate90() {
    switch (a()[ordinal()]) {
      default:
        return UNSPECIFIED;
      case 3:
        return PORTRAIT_LEFT;
      case 4:
        return LANDSCAPE_RIGHT;
      case 5:
        return PORTRAIT_RIGHT;
      case 2:
        break;
    } 
    return LANDSCAPE_LEFT;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Libraries\CBOrientation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */