package com.flurry.android;

import android.util.Log;
import com.jumptap.adtag.JtAdView;
import com.jumptap.adtag.JtAdViewListener;
import java.util.Collections;

/* loaded from: classes.dex */
final class w implements JtAdViewListener {
    private /* synthetic */ g a;

    public w(g gVar) {
        this.a = gVar;
    }

    public final void onAdError(JtAdView jtAdView, int i, int i2) {
        Log.d("FlurryAgent", "Jumptap AdView error.");
    }

    public final void onFocusChange(JtAdView jtAdView, int i, boolean z) {
        Log.d("FlurryAgent", "Jumptap AdView focus changed.");
    }

    public final void onInterstitialDismissed(JtAdView jtAdView, int i) {
        this.a.onAdClosed(Collections.emptyMap());
        Log.d("FlurryAgent", "Jumptap AdView dismissed.");
    }

    public final void onNewAd(JtAdView jtAdView, int i, String str) {
        this.a.onAdFilled(Collections.emptyMap());
        this.a.onAdShown(Collections.emptyMap());
        Log.d("FlurryAgent", "Jumptap AdView new ad.");
    }

    public final void onNoAdFound(JtAdView jtAdView, int i) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Jumptap AdView no ad found.");
    }
}
