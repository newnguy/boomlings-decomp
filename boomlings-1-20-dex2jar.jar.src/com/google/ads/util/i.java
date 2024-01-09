package com.google.ads.util;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class i {
  private static final Object a = new Object();
  
  private static int b = 0;
  
  private static HashMap c = new HashMap<Object, Object>();
  
  private final ArrayList d = new ArrayList();
  
  public final int o;
  
  public i() {
    synchronized (a) {
      int j = b;
      b = j + 1;
      this.o = j;
      Integer integer = (Integer)c.get(getClass());
      if (integer == null) {
        c.put(getClass(), Integer.valueOf(1));
      } else {
        c.put(getClass(), Integer.valueOf(integer.intValue() + 1));
      } 
      b.d("State created: " + toString());
      return;
    } 
  }
  
  private void a(i$a parami$a) {
    this.d.add(parami$a);
  }
  
  protected void finalize() {
    synchronized (a) {
      c.put(getClass(), Integer.valueOf(((Integer)c.get(getClass())).intValue() - 1));
      super.finalize();
      return;
    } 
  }
  
  public String toString() {
    return getClass().getSimpleName() + "[" + this.o + "]";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */