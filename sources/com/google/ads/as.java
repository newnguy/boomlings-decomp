package com.google.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class as implements Runnable {
    final /* synthetic */ c a;
    final /* synthetic */ e b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public as(e eVar, c cVar) {
        this.b = eVar;
        this.a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.google.ads.internal.d dVar;
        dVar = this.b.a;
        dVar.b(this.a);
    }
}
