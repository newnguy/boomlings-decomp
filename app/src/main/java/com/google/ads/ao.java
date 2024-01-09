package com.google.ads;

import android.view.View;

/* loaded from: classes.dex */
public class ao implements Runnable {
    final /* synthetic */ View a;
    final /* synthetic */ f b;
    final /* synthetic */ e c;

    public ao(e eVar, View view, f fVar) {
        this.c = eVar;
        this.a = view;
        this.b = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.google.ads.internal.d dVar;
        h hVar;
        dVar = this.c.a;
        View view = this.a;
        hVar = this.c.b;
        dVar.a(view, hVar, this.b, true);
    }
}
