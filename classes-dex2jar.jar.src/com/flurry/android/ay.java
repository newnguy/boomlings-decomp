package com.flurry.android;

import android.util.Log;
import com.millennialmedia.android.MMAdView;
import java.util.Collections;

final class ay implements MMAdView.MMAdListener {
  private al a;
  
  ay(al paramal) {}
  
  public final void MMAdCachingCompleted(MMAdView paramMMAdView, boolean paramBoolean) {
    Log.d("FlurryAgent", "Millennial MMAdView Interstitial caching completed." + System.currentTimeMillis() + " fInter_success = " + al.a(this.a) + " success = " + paramBoolean);
    if (!al.a(this.a))
      paramMMAdView.display(); 
  }
  
  public final void MMAdClickedToOverlay(MMAdView paramMMAdView) {
    this.a.onAdClicked(Collections.emptyMap());
    Log.d("FlurryAgent", "Millennial MMAdView Interstitial clicked to overlay." + System.currentTimeMillis());
  }
  
  public final void MMAdFailed(MMAdView paramMMAdView) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Millennial MMAdView Interstitial failed to load ad.");
  }
  
  public final void MMAdOverlayLaunched(MMAdView paramMMAdView) {
    this.a.onAdFilled(Collections.emptyMap());
    this.a.onAdShown(Collections.emptyMap());
    this.a.onAdClosed(Collections.emptyMap());
    Log.d("FlurryAgent", "Millennial MMAdView Interstitial overlay launched." + System.currentTimeMillis());
  }
  
  public final void MMAdRequestIsCaching(MMAdView paramMMAdView) {
    Log.d("FlurryAgent", "Millennial MMAdView Interstitial request is caching.");
  }
  
  public final void MMAdReturned(MMAdView paramMMAdView) {
    Log.d("FlurryAgent", "Millennial MMAdView In returned interstitial ad.");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\ay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */