package com.google.ads;

import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class ae implements Runnable {
    private WeakReference a;

    public ae(com.google.ads.internal.d dVar) {
        this.a = new WeakReference(dVar);
    }

    @Override // java.lang.Runnable
    public void run() {
        com.google.ads.internal.d dVar = (com.google.ads.internal.d) this.a.get();
        if (dVar == null) {
            com.google.ads.util.b.a("The ad must be gone, so cancelling the refresh timer.");
        } else {
            dVar.x();
        }
    }
}
