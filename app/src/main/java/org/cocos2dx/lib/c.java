package org.cocos2dx.lib;

/* loaded from: classes.dex */
public class c implements Runnable {
    final /* synthetic */ a a;

    public c(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.bannerEnabled_ = false;
        this.a.adview_banner.setVisibility(8);
    }
}
