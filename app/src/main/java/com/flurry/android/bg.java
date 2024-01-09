package com.flurry.android;

import android.util.Log;
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import java.util.Collections;

/* loaded from: classes.dex */
final class bg implements AdListener {
    private /* synthetic */ ab a;

    public bg(ab abVar) {
        this.a = abVar;
    }

    @Override // com.google.ads.AdListener
    public final void onDismissScreen(Ad ad) {
        this.a.onAdClosed(Collections.emptyMap());
        Log.i("FlurryAgent", "Admob AdView dismissed from screen.");
    }

    @Override // com.google.ads.AdListener
    public final void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {
        this.a.onAdUnFilled(Collections.emptyMap());
        Log.d("FlurryAgent", "Admob AdView failed to receive ad.");
    }

    @Override // com.google.ads.AdListener
    public final void onLeaveApplication(Ad ad) {
        this.a.onAdClicked(Collections.emptyMap());
        Log.i("FlurryAgent", "Admob AdView leave application.");
    }

    @Override // com.google.ads.AdListener
    public final void onPresentScreen(Ad ad) {
        Log.d("FlurryAgent", "Admob AdView present on screen.");
    }

    @Override // com.google.ads.AdListener
    public final void onReceiveAd(Ad ad) {
        this.a.onAdFilled(Collections.emptyMap());
        this.a.onAdShown(Collections.emptyMap());
        Log.d("FlurryAgent", "Admob AdView received ad.");
    }
}
