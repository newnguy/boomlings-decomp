package com.flurry.android;

import android.util.Log;
import com.jumptap.adtag.JtAdView;
import com.jumptap.adtag.JtAdViewListener;
import java.util.Collections;

/* loaded from: classes.dex */
final class aw implements JtAdViewListener {
    private /* synthetic */ g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aw(g gVar) {
        this.a = gVar;
    }

    public final void onAdError(JtAdView jtAdView, int i, int i2) {
        Log.d("FlurryAgent", "Jumptap Interstitial error.");
    }

    public final void onFocusChange(JtAdView jtAdView, int i, boolean z) {
        Log.d("FlurryAgent", "Jumptap Interstitial focus changed.");
    }

    public final void onInterstitialDismissed(JtAdView jtAdView, int i) {
        this.a.onAdClosed(Collections.emptyMap());
        Log.d("FlurryAgent", "Jumptap Interstitial dismissed.");
    }

    public final void onNewAd(JtAdView jtAdView, int i, String str) {
        this.a.onAdFilled(Collections.emptyMap());
        this.a.onAdShown(Collections.emptyMap());
        Log.d("FlurryAgent", "Jumptap Interstitial new ad.");
    }

    public final void onNoAdFound(JtAdView jtAdView, int i) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Jumptap Interstitial no ad found.");
    }
}
