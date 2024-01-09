package com.google.ads;

import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.util.b;

class av implements Runnable {
  final MediationInterstitialAdapter a;
  
  final h b;
  
  av(h paramh, MediationInterstitialAdapter paramMediationInterstitialAdapter) {}
  
  public void run() {
    try {
      this.a.e();
    } catch (Throwable throwable) {
      b.b("Error while telling adapter (" + this.b.h() + ") ad to show interstitial: ", throwable);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\av.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */