package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class a {
  private Map a = Collections.synchronizedMap(new LinkedHashMap<Object, Object>(10, 1.5F, true));
  
  private long b = 0L;
  
  private long c = 1000000L;
  
  public a() {
    a(Runtime.getRuntime().maxMemory() / 4L);
  }
  
  private static long a(Bitmap paramBitmap) {
    return (paramBitmap == null) ? 0L : (paramBitmap.getRowBytes() * paramBitmap.getHeight());
  }
  
  private void b() {
    if (this.b > this.c) {
      Iterator<Map.Entry> iterator = this.a.entrySet().iterator();
      while (true) {
        if (iterator.hasNext()) {
          Map.Entry entry = iterator.next();
          this.b -= a(((a$a)entry.getValue()).b());
          iterator.remove();
          if (this.b <= this.c)
            return; 
          continue;
        } 
        return;
      } 
    } 
  }
  
  public a$a a(String paramString) {
    return !this.a.containsKey(paramString) ? null : (a$a)this.a.get(paramString);
  }
  
  public void a() {
    this.a.clear();
  }
  
  public void a(long paramLong) {
    this.c = paramLong;
  }
  
  public void a(String paramString, a$a parama$a) {
    try {
      if (this.a.containsKey(paramString))
        this.b -= a(((a$a)this.a.get(paramString)).b()); 
      this.a.put(paramString, parama$a);
      this.b += a(parama$a.b());
      b();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Libraries\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */