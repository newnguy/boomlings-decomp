package com.google.ads;

import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.util.a;
import com.google.ads.util.b;

class az implements MediationInterstitialListener {
  private final h a;
  
  az(h paramh) {
    this.a = paramh;
  }
  
  public void a(MediationInterstitialAdapter paramMediationInterstitialAdapter) {
    synchronized (this.a) {
      a.a(paramMediationInterstitialAdapter, this.a.i());
      if (this.a.c()) {
        b.b("Got an onReceivedAd() callback after loadAdTask is done from an interstitial adapter. Ignoring callback.");
      } else {
        this.a.a(true, g$a.a);
      } 
      return;
    } 
  }
  
  public void a(MediationInterstitialAdapter paramMediationInterstitialAdapter, AdRequest$ErrorCode paramAdRequest$ErrorCode) {
    synchronized (this.a) {
      a.a(paramMediationInterstitialAdapter, this.a.i());
      StringBuilder stringBuilder = new StringBuilder();
      this();
      b.a(stringBuilder.append("Mediation adapter ").append(paramMediationInterstitialAdapter.getClass().getName()).append(" failed to receive ad with error code: ").append(paramAdRequest$ErrorCode).toString());
      if (this.a.c()) {
        b.b("Got an onFailedToReceiveAd() callback after loadAdTask is done from an interstitial adapter.  Ignoring callback.");
      } else {
        g$a g$a;
        h h1 = this.a;
        if (paramAdRequest$ErrorCode == AdRequest$ErrorCode.b) {
          g$a = g$a.b;
        } else {
          g$a = g$a.c;
        } 
        h1.a(false, g$a);
      } 
      return;
    } 
  }
  
  public void b(MediationInterstitialAdapter paramMediationInterstitialAdapter) {
    synchronized (this.a) {
      this.a.j().a(this.a);
      return;
    } 
  }
  
  public void c(MediationInterstitialAdapter paramMediationInterstitialAdapter) {
    synchronized (this.a) {
      this.a.j().b(this.a);
      return;
    } 
  }
  
  public void d(MediationInterstitialAdapter paramMediationInterstitialAdapter) {
    synchronized (this.a) {
      this.a.j().c(this.a);
      return;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */