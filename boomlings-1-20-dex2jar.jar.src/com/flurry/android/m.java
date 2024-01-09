package com.flurry.android;

import android.util.Log;
import com.inmobi.androidsdk.IMAdInterstitial;
import com.inmobi.androidsdk.IMAdInterstitialListener;
import com.inmobi.androidsdk.IMAdRequest;
import java.util.Collections;

final class m implements IMAdInterstitialListener {
  private ae a;
  
  m(ae paramae) {}
  
  public final void onAdRequestFailed(IMAdInterstitial paramIMAdInterstitial, IMAdRequest.ErrorCode paramErrorCode) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "InMobi imAdView ad request failed.");
  }
  
  public final void onAdRequestLoaded(IMAdInterstitial paramIMAdInterstitial) {
    this.a.onAdFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "InMobi Interstitial ad request completed.");
    if (IMAdInterstitial.State.READY.equals(paramIMAdInterstitial.getState())) {
      this.a.onAdShown(Collections.emptyMap());
      paramIMAdInterstitial.show();
    } 
  }
  
  public final void onDismissAdScreen(IMAdInterstitial paramIMAdInterstitial) {
    this.a.onAdClosed(Collections.emptyMap());
    Log.d("FlurryAgent", "InMobi Interstitial ad dismissed.");
  }
  
  public final void onLeaveApplication(IMAdInterstitial paramIMAdInterstitial) {
    Log.d("FlurryAgent", "InMobi onLeaveApplication");
  }
  
  public final void onShowAdScreen(IMAdInterstitial paramIMAdInterstitial) {
    Log.d("FlurryAgent", "InMobi Interstitial ad shown.");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */