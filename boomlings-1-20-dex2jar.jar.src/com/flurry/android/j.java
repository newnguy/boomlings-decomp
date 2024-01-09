package com.flurry.android;

import android.util.Log;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import java.util.Collections;

final class j implements AdListener {
  private ab a;
  
  j(ab paramab) {}
  
  public final void onDismissScreen(Ad paramAd) {
    this.a.onAdClosed(Collections.emptyMap());
    Log.i("FlurryAgent", "Admob Interstitial dismissed from screen.");
  }
  
  public final void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Admob Interstitial failed to receive takeover.");
  }
  
  public final void onLeaveApplication(Ad paramAd) {
    this.a.onAdClicked(Collections.emptyMap());
    Log.i("FlurryAgent", "Admob Interstitial leave application.");
  }
  
  public final void onPresentScreen(Ad paramAd) {
    Log.d("FlurryAgent", "Admob Interstitial present on screen.");
  }
  
  public final void onReceiveAd(Ad paramAd) {
    this.a.onAdFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Admob Interstitial received takeover.");
    this.a.onAdShown(Collections.emptyMap());
    ab.a(this.a).a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */