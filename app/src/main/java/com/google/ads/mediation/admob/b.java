package com.google.ads.mediation.admob;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerListener;

/* loaded from: classes.dex */
public class b implements AdListener {
    final /* synthetic */ AdMobAdapter a;

    /* JADX INFO: Access modifiers changed from: private */
    public b(AdMobAdapter adMobAdapter) {
        this.a = adMobAdapter;
    }

    @Override // com.google.ads.AdListener
    public void onDismissScreen(Ad ad) {
        MediationBannerListener mediationBannerListener;
        mediationBannerListener = this.a.a;
        mediationBannerListener.c(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode errorCode) {
        MediationBannerListener mediationBannerListener;
        mediationBannerListener = this.a.a;
        mediationBannerListener.a(this.a, errorCode);
    }

    @Override // com.google.ads.AdListener
    public void onLeaveApplication(Ad ad) {
        MediationBannerListener mediationBannerListener;
        mediationBannerListener = this.a.a;
        mediationBannerListener.d(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onPresentScreen(Ad ad) {
        MediationBannerListener mediationBannerListener;
        MediationBannerListener mediationBannerListener2;
        mediationBannerListener = this.a.a;
        mediationBannerListener.e(this.a);
        mediationBannerListener2 = this.a.a;
        mediationBannerListener2.b(this.a);
    }

    @Override // com.google.ads.AdListener
    public void onReceiveAd(Ad ad) {
        MediationBannerListener mediationBannerListener;
        mediationBannerListener = this.a.a;
        mediationBannerListener.a(this.a);
    }
}
