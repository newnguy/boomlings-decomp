package org.cocos2dx.lib;

/* loaded from: classes.dex */
public class b implements Runnable {
    final /* synthetic */ a a;

    public b(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.bannerEnabled_ = true;
        this.a.adview_banner.setVisibility(0);
    }
}
