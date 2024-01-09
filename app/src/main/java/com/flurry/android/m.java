package com.flurry.android;

import android.util.Log;
import com.inmobi.androidsdk.IMAdInterstitial;
import com.inmobi.androidsdk.IMAdInterstitialListener;
import com.inmobi.androidsdk.IMAdRequest;
import java.util.Collections;

/* loaded from: classes.dex */
final class m implements IMAdInterstitialListener {
    private /* synthetic */ ae a;

    public m(ae aeVar) {
        this.a = aeVar;
    }

    public final void onAdRequestFailed(IMAdInterstitial iMAdInterstitial, IMAdRequest.ErrorCode errorCode) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "InMobi imAdView ad request failed.");
    }

    public final void onAdRequestLoaded(IMAdInterstitial iMAdInterstitial) {
        this.a.onAdFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "InMobi Interstitial ad request completed.");
        if (IMAdInterstitial.State.READY.equals(iMAdInterstitial.getState())) {
            this.a.onAdShown(Collections.emptyMap());
            iMAdInterstitial.show();
        }
    }

    public final void onDismissAdScreen(IMAdInterstitial iMAdInterstitial) {
        this.a.onAdClosed(Collections.emptyMap());
        Log.d("FlurryAgent", "InMobi Interstitial ad dismissed.");
    }

    public final void onLeaveApplication(IMAdInterstitial iMAdInterstitial) {
        Log.d("FlurryAgent", "InMobi onLeaveApplication");
    }

    public final void onShowAdScreen(IMAdInterstitial iMAdInterstitial) {
        Log.d("FlurryAgent", "InMobi Interstitial ad shown.");
    }
}
