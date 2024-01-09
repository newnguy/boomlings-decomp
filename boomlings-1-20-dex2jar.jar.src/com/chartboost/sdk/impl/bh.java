package com.chartboost.sdk.impl;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class bh {
  final int a;
  
  private Queue b = new ConcurrentLinkedQueue();
  
  public bh(int paramInt) {
    this.a = paramInt;
  }
  
  protected boolean a(Object paramObject) {
    return true;
  }
  
  protected abstract Object b();
  
  public void b(Object paramObject) {
    if (a(paramObject) && this.b.size() <= this.a)
      this.b.add(paramObject); 
  }
  
  public Object c() {
    Object object = this.b.poll();
    if (object == null)
      object = b(); 
    return object;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\bh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */