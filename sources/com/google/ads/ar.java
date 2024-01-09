package com.google.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ar implements Runnable {
    final /* synthetic */ e a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ar(e eVar) {
        this.a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.google.ads.internal.d dVar;
        dVar = this.a.a;
        dVar.u();
    }
}
