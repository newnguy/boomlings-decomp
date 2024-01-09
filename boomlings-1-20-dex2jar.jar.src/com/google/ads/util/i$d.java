package com.google.ads.util;

import java.lang.ref.WeakReference;

public final class i$d extends i$a {
  final i d;
  
  public i$d(i parami, String paramString, Object paramObject) {
    super(parami, paramString, new WeakReference(paramObject), null);
  }
  
  public Object a() {
    return ((WeakReference)this.a).get();
  }
  
  public String toString() {
    return this.d.toString() + "." + this.b + " = " + a() + " (?)";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\i$d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */