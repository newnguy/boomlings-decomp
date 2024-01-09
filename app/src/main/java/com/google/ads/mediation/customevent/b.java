package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationInterstitialListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements CustomEventInterstitialListener {
    final /* synthetic */ CustomEventAdapter a;
    private final MediationInterstitialListener b;

    public b(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
        this.a = customEventAdapter;
        this.b = mediationInterstitialListener;
    }
}
