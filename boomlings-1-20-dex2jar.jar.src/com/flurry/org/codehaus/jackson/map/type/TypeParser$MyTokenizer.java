package com.flurry.org.codehaus.jackson.map.type;

import java.util.StringTokenizer;

final class TypeParser$MyTokenizer extends StringTokenizer {
  protected int _index;
  
  protected final String _input;
  
  protected String _pushbackToken;
  
  public TypeParser$MyTokenizer(String paramString) {
    super(paramString, "<,>", true);
    this._input = paramString;
  }
  
  public String getAllInput() {
    return this._input;
  }
  
  public String getRemainingInput() {
    return this._input.substring(this._index);
  }
  
  public String getUsedInput() {
    return this._input.substring(0, this._index);
  }
  
  public boolean hasMoreTokens() {
    return (this._pushbackToken != null || super.hasMoreTokens());
  }
  
  public String nextToken() {
    if (this._pushbackToken != null) {
      String str1 = this._pushbackToken;
      this._pushbackToken = null;
      this._index += str1.length();
      return str1;
    } 
    String str = super.nextToken();
    this._index += str.length();
    return str;
  }
  
  public void pushBack(String paramString) {
    this._pushbackToken = paramString;
    this._index -= paramString.length();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\TypeParser$MyTokenizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */