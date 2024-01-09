package com.flurry.org.codehaus.jackson.map.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUMap extends LinkedHashMap {
  protected final int _maxEntries;
  
  public LRUMap(int paramInt1, int paramInt2) {
    super(paramInt1, 0.8F, true);
    this._maxEntries = paramInt2;
  }
  
  protected boolean removeEldestEntry(Map.Entry paramEntry) {
    return (size() > this._maxEntries);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\LRUMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */