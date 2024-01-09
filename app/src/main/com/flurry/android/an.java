package com.flurry.android;

/* loaded from: classes.dex */
abstract class an implements Runnable {
    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a();
        } catch (Throwable th) {
            th.printStackTrace();
            bm.b("FlurryAgent", "", th);
        }
    }
}
