package com.flurry.android;

import android.util.Log;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.InterstitialAd;
import java.util.Collections;

/* loaded from: classes.dex */
final class j implements AdListener {
    private /* synthetic */ ab a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(ab abVar) {
        this.a = abVar;
    }

    @Override // com.google.ads.AdListener
    public final void onDismissScreen(Ad ad) {
        this.a.onAdClosed(Collections.emptyMap());
        Log.i("FlurryAgent", "Admob Interstitial dismissed from screen.");
    }

    @Override // com.google.ads.AdListener
    public final void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Admob Interstitial failed to receive takeover.");
    }

    @Override // com.google.ads.AdListener
    public final void onLeaveApplication(Ad ad) {
        this.a.onAdClicked(Collections.emptyMap());
        Log.i("FlurryAgent", "Admob Interstitial leave application.");
    }

    @Override // com.google.ads.AdListener
    public final void onPresentScreen(Ad ad) {
        Log.d("FlurryAgent", "Admob Interstitial present on screen.");
    }

    @Override // com.google.ads.AdListener
    public final void onReceiveAd(Ad ad) {
        InterstitialAd interstitialAd;
        this.a.onAdFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Admob Interstitial received takeover.");
        this.a.onAdShown(Collections.emptyMap());
        interstitialAd = this.a.f;
        interstitialAd.a();
    }
}
