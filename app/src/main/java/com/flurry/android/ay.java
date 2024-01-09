package com.flurry.android;

import android.util.Log;
import com.millennialmedia.android.MMAdView;
import java.util.Collections;

/* loaded from: classes.dex */
final class ay implements MMAdView.MMAdListener {
    private /* synthetic */ al a;

    public ay(al alVar) {
        this.a = alVar;
    }

    public final void MMAdCachingCompleted(MMAdView mMAdView, boolean z) {
        boolean z2;
        boolean z3;
        StringBuilder append = new StringBuilder().append("Millennial MMAdView Interstitial caching completed.").append(System.currentTimeMillis()).append(" fInter_success = ");
        z2 = this.a.e;
        Log.d("FlurryAgent", append.append(z2).append(" success = ").append(z).toString());
        z3 = this.a.e;
        if (z3) {
            return;
        }
        mMAdView.display();
    }

    public final void MMAdClickedToOverlay(MMAdView mMAdView) {
        this.a.onAdClicked(Collections.emptyMap());
        Log.d("FlurryAgent", "Millennial MMAdView Interstitial clicked to overlay." + System.currentTimeMillis());
    }

    public final void MMAdFailed(MMAdView mMAdView) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Millennial MMAdView Interstitial failed to load ad.");
    }

    public final void MMAdOverlayLaunched(MMAdView mMAdView) {
        this.a.onAdFilled(Collections.emptyMap());
        this.a.onAdShown(Collections.emptyMap());
        this.a.onAdClosed(Collections.emptyMap());
        Log.d("FlurryAgent", "Millennial MMAdView Interstitial overlay launched." + System.currentTimeMillis());
    }

    public final void MMAdRequestIsCaching(MMAdView mMAdView) {
        Log.d("FlurryAgent", "Millennial MMAdView Interstitial request is caching.");
    }

    public final void MMAdReturned(MMAdView mMAdView) {
        Log.d("FlurryAgent", "Millennial MMAdView In returned interstitial ad.");
    }
}
