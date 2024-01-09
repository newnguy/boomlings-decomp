package com.google.ads.mediation.admob;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;

/* loaded from: classes.dex */
class c implements AdListener {
    final /* synthetic */ AdMobAdapter a;

    private c(AdMobAdapter adMobAdapter) {
        this.a = adMobAdapter;
    }

    @Override // com.google.ads.AdListener
    public void onDismissScreen(Ad ad) {
        AdMobAdapter.b(this.a).c(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {
        AdMobAdapter.b(this.a).a(this.a, errorCode);
    }

    @Override // com.google.ads.AdListener
    public void onLeaveApplication(Ad ad) {
        AdMobAdapter.b(this.a).d(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onPresentScreen(Ad ad) {
        AdMobAdapter.b(this.a).b(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onReceiveAd(Ad ad) {
        AdMobAdapter.b(this.a).a(this.a);
    }
}
