package com.b.a;

public class m extends RuntimeException {
  private int a = 0;
  
  private String b;
  
  public m(String paramString) {
    super(paramString);
  }
  
  public m(String paramString1, String paramString2, int paramInt) {
    super(paramString1);
    this.b = paramString2;
    this.a = paramInt;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\b\a\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */