package com.google.ads;

import com.google.ads.util.a;
import com.google.ads.util.b;

class au implements Runnable {
  final h a;
  
  au(h paramh) {}
  
  public void run() {
    if (this.a.l()) {
      a.b(h.a(this.a));
      try {
        h.a(this.a).a();
        b.a("Called destroy() for adapter with class: " + h.a(this.a).getClass().getName());
      } catch (Throwable throwable) {
        b.b("Error while destroying adapter (" + this.a.h() + "):", throwable);
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */