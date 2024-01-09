package com.flurry.android;

import android.util.Log;
import com.jumptap.adtag.JtAdView;
import com.jumptap.adtag.JtAdViewListener;
import java.util.Collections;

final class aw implements JtAdViewListener {
  private g a;
  
  aw(g paramg) {}
  
  public final void onAdError(JtAdView paramJtAdView, int paramInt1, int paramInt2) {
    Log.d("FlurryAgent", "Jumptap Interstitial error.");
  }
  
  public final void onFocusChange(JtAdView paramJtAdView, int paramInt, boolean paramBoolean) {
    Log.d("FlurryAgent", "Jumptap Interstitial focus changed.");
  }
  
  public final void onInterstitialDismissed(JtAdView paramJtAdView, int paramInt) {
    this.a.onAdClosed(Collections.emptyMap());
    Log.d("FlurryAgent", "Jumptap Interstitial dismissed.");
  }
  
  public final void onNewAd(JtAdView paramJtAdView, int paramInt, String paramString) {
    this.a.onAdFilled(Collections.emptyMap());
    this.a.onAdShown(Collections.emptyMap());
    Log.d("FlurryAgent", "Jumptap Interstitial new ad.");
  }
  
  public final void onNoAdFound(JtAdView paramJtAdView, int paramInt) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Jumptap Interstitial no ad found.");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\aw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */