package com.chartboost.sdk.Libraries;

public enum CBOrientation$Difference {
  ANGLE_0, ANGLE_180, ANGLE_270, ANGLE_90;
  
  private static int[] a;
  
  private static final CBOrientation$Difference[] b;
  
  static {
    ANGLE_180 = new CBOrientation$Difference("ANGLE_180", 2);
    ANGLE_270 = new CBOrientation$Difference("ANGLE_270", 3);
    b = new CBOrientation$Difference[] { ANGLE_0, ANGLE_90, ANGLE_180, ANGLE_270 };
  }
  
  public int getAsInt() {
    switch (a()[ordinal()]) {
      default:
        return 0;
      case 2:
        return 90;
      case 3:
        return 180;
      case 4:
        break;
    } 
    return 270;
  }
  
  public boolean isOdd() {
    return !(this != ANGLE_90 && this != ANGLE_270);
  }
  
  public boolean isReverse() {
    return !(this != ANGLE_180 && this != ANGLE_270);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Libraries\CBOrientation$Difference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */