package com.google.ads;

import android.view.View;
import com.google.ads.util.b;

class at implements Runnable {
  final h a;
  
  final View b;
  
  final f c;
  
  final e d;
  
  at(e parame, h paramh, View paramView, f paramf) {}
  
  public void run() {
    if (e.a(this.d, this.a)) {
      b.a("Trying to switch GWAdNetworkAmbassadors, but GWController().destroy() has been called. Destroying the new ambassador and terminating mediation.");
      return;
    } 
    e.b(this.d).a(this.b, this.a, this.c, false);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */