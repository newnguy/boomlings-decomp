package com.google.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class am implements Runnable {
    final /* synthetic */ c a;
    final /* synthetic */ AdRequest b;
    final /* synthetic */ e c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public am(e eVar, c cVar, AdRequest adRequest) {
        this.c = eVar;
        this.a = cVar;
        this.b = adRequest;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        this.c.b(this.a, this.b);
        obj = this.c.e;
        synchronized (obj) {
            this.c.d = null;
        }
    }
}
