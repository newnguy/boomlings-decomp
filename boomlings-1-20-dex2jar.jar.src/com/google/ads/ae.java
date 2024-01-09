package com.google.ads;

import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.lang.ref.WeakReference;

public class ae implements Runnable {
  private WeakReference a;
  
  public ae(d paramd) {
    this.a = new WeakReference<d>(paramd);
  }
  
  public void run() {
    d d = this.a.get();
    if (d == null) {
      b.a("The ad must be gone, so cancelling the refresh timer.");
      return;
    } 
    d.x();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */