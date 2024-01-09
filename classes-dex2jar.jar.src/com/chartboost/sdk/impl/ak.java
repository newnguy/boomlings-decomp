package com.chartboost.sdk.impl;

public class ak implements ah {
  static final String[] a = new String[128];
  
  private byte[] b = new byte[1024];
  
  private byte[] c = new byte[1024];
  
  private aq d = new aq();
  
  static {
    a((byte)48, (byte)57);
    a((byte)97, (byte)122);
    a((byte)65, (byte)90);
  }
  
  static void a(byte paramByte1, byte paramByte2) {
    while (paramByte1 < paramByte2) {
      String str = "" + (char)paramByte1;
      a[paramByte1] = str;
      paramByte1 = (byte)(paramByte1 + 1);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */