package com.google.ads;

public enum AdRequest$ErrorCode {
  a("Invalid Ad request."),
  b("Ad request successful, but no ad returned due to lack of ad inventory."),
  c("A network error occurred."),
  d("There was an internal error.");
  
  private static final AdRequest$ErrorCode[] f = new AdRequest$ErrorCode[] { a, b, c, d };
  
  private final String e;
  
  AdRequest$ErrorCode(String paramString1) {
    this.e = paramString1;
  }
  
  public String toString() {
    return this.e;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\AdRequest$ErrorCode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */