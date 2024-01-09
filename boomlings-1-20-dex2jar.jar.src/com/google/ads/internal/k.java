package com.google.ads.internal;

import android.os.Handler;
import com.google.ads.util.b;
import java.lang.ref.WeakReference;

class k implements Runnable {
  private WeakReference a;
  
  private Handler b;
  
  public k(AdVideoView paramAdVideoView) {
    this.a = new WeakReference<AdVideoView>(paramAdVideoView);
    this.b = new Handler();
  }
  
  public void a() {
    this.b.postDelayed(this, 250L);
  }
  
  public void run() {
    AdVideoView adVideoView = this.a.get();
    if (adVideoView == null) {
      b.d("The video must be gone, so cancelling the timeupdate task.");
      return;
    } 
    adVideoView.f();
    this.b.postDelayed(this, 250L);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */