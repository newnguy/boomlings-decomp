package com.chartboost.sdk.impl;

import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class x extends ag {
  static final Logger a = Logger.getLogger("com.mongodb");
  
  static final boolean b = Boolean.getBoolean("DEBUG.MONGO");
  
  public static final ByteOrder c = ByteOrder.LITTLE_ENDIAN;
  
  static final int d = Integer.parseInt(System.getProperty("MONGO.POOLSIZE", "10"));
  
  static final ay e = new ay(-1, -1, -1);
  
  static {
    if (a.getLevel() == null)
      if (b) {
        a.setLevel(Level.ALL);
      } else {
        a.setLevel(Level.WARNING);
      }  
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */