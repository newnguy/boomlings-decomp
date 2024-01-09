package com.flurry.android;

import android.util.Log;
import com.inmobi.androidsdk.IMAdListener;
import com.inmobi.androidsdk.IMAdRequest;
import com.inmobi.androidsdk.IMAdView;
import java.util.Collections;

/* loaded from: classes.dex */
final class af implements IMAdListener {
    private /* synthetic */ ae a;

    public af(ae aeVar) {
        this.a = aeVar;
    }

    public final void onAdRequestCompleted(IMAdView iMAdView) {
        this.a.onAdFilled(Collections.emptyMap());
        this.a.onAdShown(Collections.emptyMap());
        Log.d("FlurryAgent", "InMobi imAdView ad request completed.");
    }

    public final void onAdRequestFailed(IMAdView iMAdView, IMAdRequest.ErrorCode errorCode) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "InMobi imAdView ad request failed.");
    }

    public final void onDismissAdScreen(IMAdView iMAdView) {
        this.a.onAdClosed(Collections.emptyMap());
        Log.d("FlurryAgent", "InMobi imAdView dismiss ad.");
    }

    public final void onLeaveApplication(IMAdView iMAdView) {
        Log.d("FlurryAgent", "InMobi onLeaveApplication");
    }

    public final void onShowAdScreen(IMAdView iMAdView) {
        this.a.onAdClicked(Collections.emptyMap());
        Log.d("FlurryAgent", "InMobi imAdView ad shown.");
    }
}
