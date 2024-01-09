package com.google.ads.util;

public abstract class i$a {
  protected Object a;
  
  protected final String b;
  
  final i c;
  
  private i$a(i parami, String paramString) {
    this(parami, paramString, (Object)null);
  }
  
  private i$a(i parami, String paramString, Object paramObject) {
    this.b = paramString;
    i.a(parami, this);
    this.a = paramObject;
  }
  
  public String toString() {
    return this.c.toString() + "." + this.b + " = " + this.a;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\i$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */