package com.flurry.android;

import android.util.Log;
import com.millennialmedia.android.MMAdView;
import java.util.Collections;

/* loaded from: classes.dex */
final class ak implements MMAdView.MMAdListener {
    private /* synthetic */ al a;

    public ak(al alVar) {
        this.a = alVar;
    }

    public final void MMAdCachingCompleted(MMAdView mMAdView, boolean z) {
        Log.d("FlurryAgent", "Millennial MMAdView banner caching completed.");
    }

    public final void MMAdClickedToOverlay(MMAdView mMAdView) {
        this.a.onAdClicked(Collections.emptyMap());
        Log.d("FlurryAgent", "Millennial MMAdView clicked to overlay.");
    }

    public final void MMAdFailed(MMAdView mMAdView) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Millennial MMAdView failed to load ad.");
    }

    public final void MMAdOverlayLaunched(MMAdView mMAdView) {
        Log.d("FlurryAgent", "Millennial MMAdView banner overlay launched.");
    }

    public final void MMAdRequestIsCaching(MMAdView mMAdView) {
        Log.d("FlurryAgent", "Millennial MMAdView banner request is caching.");
    }

    public final void MMAdReturned(MMAdView mMAdView) {
        this.a.onAdFilled(Collections.emptyMap());
        this.a.onAdShown(Collections.emptyMap());
        Log.d("FlurryAgent", "Millennial MMAdView returned ad." + System.currentTimeMillis());
    }
}
