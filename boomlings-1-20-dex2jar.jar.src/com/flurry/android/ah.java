package com.flurry.android;

import java.io.File;

final class ah {
  private static String a = "FlurryAgent";
  
  static boolean a(File paramFile) {
    paramFile = paramFile.getParentFile();
    if (!paramFile.mkdirs() && !paramFile.exists()) {
      bm.b(a, "Unable to create persistent dir: " + paramFile);
      return false;
    } 
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */