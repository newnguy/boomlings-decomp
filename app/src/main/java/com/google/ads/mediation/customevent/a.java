package com.google.ads.mediation.customevent;

import android.view.View;
import com.google.ads.mediation.MediationBannerListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a implements CustomEventBannerListener {
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
