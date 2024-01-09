package com.flurry.android;

import android.util.Log;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import java.util.Collections;

final class bg implements AdListener {
  private ab a;
  
  bg(ab paramab) {}
  
  public final void onDismissScreen(Ad paramAd) {
    this.a.onAdClosed(Collections.emptyMap());
    Log.i("FlurryAgent", "Admob AdView dismissed from screen.");
  }
  
  public final void onFailedToReceiveAd(Ad paramAd, AdRequest.ErrorCode paramErrorCode) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "Admob AdView failed to receive ad.");
  }
  
  public final void onLeaveApplication(Ad paramAd) {
    this.a.onAdClicked(Collections.emptyMap());
    Log.i("FlurryAgent", "Admob AdView leave application.");
  }
  
  public final void onPresentScreen(Ad paramAd) {
    Log.d("FlurryAgent", "Admob AdView present on screen.");
  }
  
  public final void onReceiveAd(Ad paramAd) {
    this.a.onAdFilled(Collections.emptyMap());
    this.a.onAdShown(Collections.emptyMap());
    Log.d("FlurryAgent", "Admob AdView received ad.");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\bg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */