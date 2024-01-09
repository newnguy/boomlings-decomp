package com.flurry.android;

import android.util.Log;
import com.mobclix.android.sdk.MobclixFullScreenAdView;
import com.mobclix.android.sdk.MobclixFullScreenAdViewListener;
import java.util.Collections;

/* loaded from: classes.dex */
final class ad implements MobclixFullScreenAdViewListener {
    private /* synthetic */ ag a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ad(ag agVar) {
        this.a = agVar;
    }

    public final String keywords() {
        Log.d("FlurryAgent", "Mobclix keyword callback.");
        return null;
    }

    public final void onDismissAd(MobclixFullScreenAdView mobclixFullScreenAdView) {
        this.a.onAdClosed(Collections.emptyMap());
        Log.d("FlurryAgent", "Mobclix Interstitial ad dismissed.");
    }

    public final void onFailedLoad(MobclixFullScreenAdView mobclixFullScreenAdView, int i) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Mobclix Interstitial ad failed to load.");
    }

    public final void onFinishLoad(MobclixFullScreenAdView mobclixFullScreenAdView) {
        this.a.onAdFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Mobclix Interstitial ad successfully loaded.");
    }

    public final void onPresentAd(MobclixFullScreenAdView mobclixFullScreenAdView) {
        this.a.onAdShown(Collections.emptyMap());
        Log.d("FlurryAgent", "Mobclix Interstitial ad successfully shown.");
    }

    public final String query() {
        Log.d("FlurryAgent", "Mobclix query callback.");
        return null;
    }
}
