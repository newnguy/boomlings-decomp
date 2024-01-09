package com.google.ads;

import com.google.ads.mediation.MediationAdapter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class au implements Runnable {
    final /* synthetic */ h a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public au(h hVar) {
        this.a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediationAdapter mediationAdapter;
        MediationAdapter mediationAdapter2;
        MediationAdapter mediationAdapter3;
        if (this.a.l()) {
            mediationAdapter = this.a.g;
            com.google.ads.util.a.b(mediationAdapter);
            try {
                mediationAdapter2 = this.a.g;
                mediationAdapter2.a();
                StringBuilder append = new StringBuilder().append("Called destroy() for adapter with class: ");
                mediationAdapter3 = this.a.g;
                com.google.ads.util.b.a(append.append(mediationAdapter3.getClass().getName()).toString());
            } catch (Throwable th) {
                com.google.ads.util.b.b("Error while destroying adapter (" + this.a.h() + "):", th);
            }
        }
    }
}
