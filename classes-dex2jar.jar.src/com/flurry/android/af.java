package com.flurry.android;

import android.util.Log;
import com.inmobi.androidsdk.IMAdListener;
import com.inmobi.androidsdk.IMAdRequest;
import com.inmobi.androidsdk.IMAdView;
import java.util.Collections;

final class af implements IMAdListener {
  private ae a;
  
  af(ae paramae) {}
  
  public final void onAdRequestCompleted(IMAdView paramIMAdView) {
    this.a.onAdFilled(Collections.emptyMap());
    this.a.onAdShown(Collections.emptyMap());
    Log.d("FlurryAgent", "InMobi imAdView ad request completed.");
  }
  
  public final void onAdRequestFailed(IMAdView paramIMAdView, IMAdRequest.ErrorCode paramErrorCode) {
    this.a.onAdUnFilled(Collections.emptyMap());
    Log.d("FlurryAgent", "InMobi imAdView ad request failed.");
  }
  
  public final void onDismissAdScreen(IMAdView paramIMAdView) {
    this.a.onAdClosed(Collections.emptyMap());
    Log.d("FlurryAgent", "InMobi imAdView dismiss ad.");
  }
  
  public final void onLeaveApplication(IMAdView paramIMAdView) {
    Log.d("FlurryAgent", "InMobi onLeaveApplication");
  }
  
  public final void onShowAdScreen(IMAdView paramIMAdView) {
    this.a.onAdClicked(Collections.emptyMap());
    Log.d("FlurryAgent", "InMobi imAdView ad shown.");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\af.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */