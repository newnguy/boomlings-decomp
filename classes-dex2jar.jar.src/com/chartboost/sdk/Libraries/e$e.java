package com.chartboost.sdk.Libraries;

import java.io.FilterInputStream;
import java.io.InputStream;

class e$e extends FilterInputStream {
  public e$e(InputStream paramInputStream) {
    super(paramInputStream);
  }
  
  public long skip(long paramLong) {
    long l = 0L;
    while (true) {
      if (l < paramLong) {
        long l2 = this.in.skip(paramLong - l);
        long l1 = l2;
        if (l2 == 0L)
          if (read() >= 0) {
            l1 = 1L;
          } else {
            return l;
          }  
        l = l1 + l;
        continue;
      } 
      return l;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Libraries\e$e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */