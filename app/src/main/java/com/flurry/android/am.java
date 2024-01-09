package com.flurry.android;

import android.util.Log;
import com.mobclix.android.sdk.MobclixAdView;
import com.mobclix.android.sdk.MobclixAdViewListener;
import java.util.Collections;

/* loaded from: classes.dex */
final class am implements MobclixAdViewListener {
    private /* synthetic */ ag a;

    public am(ag agVar) {
        this.a = agVar;
    }

    public final String keywords() {
        Log.d("FlurryAgent", "Mobclix keyword callback.");
        return null;
    }

    public final void onAdClick(MobclixAdView mobclixAdView) {
        this.a.onAdClicked(Collections.emptyMap());
        Log.d("FlurryAgent", "Mobclix AdView ad clicked.");
    }

    public final void onCustomAdTouchThrough(MobclixAdView mobclixAdView, String str) {
        this.a.onAdClicked(Collections.emptyMap());
        Log.d("FlurryAgent", "Mobclix AdView custom ad clicked.");
    }

    public final void onFailedLoad(MobclixAdView mobclixAdView, int i) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Mobclix AdView ad failed to load.");
    }

    public final boolean onOpenAllocationLoad(MobclixAdView mobclixAdView, int i) {
        Log.d("FlurryAgent", "Mobclix AdView loaded an open allocation ad.");
        return true;
    }

    public final void onSuccessfulLoad(MobclixAdView mobclixAdView) {
        this.a.onAdFilled(Collections.emptyMap());
        this.a.onAdShown(Collections.emptyMap());
        Log.d("FlurryAgent", "Mobclix AdView ad successfully loaded.");
    }

    public final String query() {
        Log.d("FlurryAgent", "Mobclix query callback.");
        return null;
    }
}
