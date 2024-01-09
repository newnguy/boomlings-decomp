package com.flurry.org.codehaus.jackson;

public enum JsonEncoding {
  UTF16_BE,
  UTF16_LE,
  UTF32_BE,
  UTF32_LE,
  UTF8("UTF-8", false);
  
  private static final JsonEncoding[] $VALUES;
  
  protected final boolean _bigEndian;
  
  protected final String _javaName;
  
  static {
    UTF16_BE = new JsonEncoding("UTF16_BE", 1, "UTF-16BE", true);
    UTF16_LE = new JsonEncoding("UTF16_LE", 2, "UTF-16LE", false);
    UTF32_BE = new JsonEncoding("UTF32_BE", 3, "UTF-32BE", true);
    UTF32_LE = new JsonEncoding("UTF32_LE", 4, "UTF-32LE", false);
    $VALUES = new JsonEncoding[] { UTF8, UTF16_BE, UTF16_LE, UTF32_BE, UTF32_LE };
  }
  
  JsonEncoding(String paramString1, boolean paramBoolean) {
    this._javaName = paramString1;
    this._bigEndian = paramBoolean;
  }
  
  public String getJavaName() {
    return this._javaName;
  }
  
  public boolean isBigEndian() {
    return this._bigEndian;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */