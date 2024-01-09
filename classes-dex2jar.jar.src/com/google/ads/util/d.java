package com.google.ads.util;

import android.os.Build;

class d {
  static final d d = new d();
  
  static final d e = new d("unknown", "generic", "generic");
  
  public final String a = Build.BOARD;
  
  public final String b = Build.DEVICE;
  
  public final String c = Build.BRAND;
  
  d() {}
  
  d(String paramString1, String paramString2, String paramString3) {}
  
  private static boolean a(String paramString1, String paramString2) {
    return (paramString1 != null) ? paramString1.equals(paramString2) : ((paramString1 == paramString2));
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (!(paramObject instanceof d))
      return bool2; 
    paramObject = paramObject;
    boolean bool1 = bool2;
    if (a(this.a, ((d)paramObject).a)) {
      bool1 = bool2;
      if (a(this.b, ((d)paramObject).b)) {
        bool1 = bool2;
        if (a(this.c, ((d)paramObject).c))
          bool1 = true; 
      } 
    } 
    return bool1;
  }
  
  public int hashCode() {
    int j = 0;
    if (this.a != null)
      j = 0 + this.a.hashCode(); 
    int i = j;
    if (this.b != null)
      i = j + this.b.hashCode(); 
    j = i;
    if (this.c != null)
      j = i + this.c.hashCode(); 
    return j;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */