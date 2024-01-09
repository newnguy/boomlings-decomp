package com.google.ads.internal;

import android.os.Handler;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class k implements Runnable {
    private WeakReference a;
    private Handler b = new Handler();

    public k(AdVideoView adVideoView) {
        this.a = new WeakReference(adVideoView);
    }

    public void a() {
        this.b.postDelayed(this, 250L);
    }

    @Override // java.lang.Runnable
    public void run() {
        AdVideoView adVideoView = (AdVideoView) this.a.get();
        if (adVideoView == null) {
            com.google.ads.util.b.d("The video must be gone, so cancelling the timeupdate task.");
            return;
        }
        adVideoView.f();
        this.b.postDelayed(this, 250L);
    }
}
