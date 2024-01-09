package com.google.ads.mediation.customevent;

import android.view.View;
import com.google.ads.mediation.MediationBannerListener;

/* loaded from: classes.dex */
class a implements CustomEventBannerListener {
    final /* synthetic */ CustomEventAdapter a;
    private View b;
    private final MediationBannerListener c;

    public a(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
        this.a = customEventAdapter;
        this.c = mediationBannerListener;
    }

    public synchronized View a() {
        return this.b;
    }
}
