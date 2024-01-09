package com.flurry.android;

import android.util.Log;
import com.millennialmedia.android.MMAdView;
import java.util.Collections;

final class ak implements MMAdView.MMAdListener {
  private al a;
  
  ak(al paramal) {}
  
  public final void MMAdCachingCompleted(MMAdView paramMMAdView, boolean paramBoolean) {
    Log.d("FlurryAgent", "Millennial MMAdView banner caching completed.");
  }
  
  public final void MMAdClickedToOverlay(MMAdView paramMMAdView) {
    this.a.onAdClicked(Collections.emptyMap());
    Log.d("FlurryAgent", "Millennial MMAdView clicked to overlay.");
  }
  
  public final void MMAdFailed(MMAdView paramMMAdView) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Millennial MMAdView failed to load ad.");
  }
  
  public final void MMAdOverlayLaunched(MMAdView paramMMAdView) {
    Log.d("FlurryAgent", "Millennial MMAdView banner overlay launched.");
  }
  
  public final void MMAdRequestIsCaching(MMAdView paramMMAdView) {
    Log.d("FlurryAgent", "Millennial MMAdView banner request is caching.");
  }
  
  public final void MMAdReturned(MMAdView paramMMAdView) {
    this.a.onAdFilled(Collections.emptyMap());
    this.a.onAdShown(Collections.emptyMap());
    Log.d("FlurryAgent", "Millennial MMAdView returned ad." + System.currentTimeMillis());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */