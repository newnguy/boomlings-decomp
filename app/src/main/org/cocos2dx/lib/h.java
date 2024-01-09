package org.cocos2dx.lib;

import com.chartboost.sdk.Chartboost;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h implements Runnable {
    final /* synthetic */ f a;
    private final /* synthetic */ String b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(f fVar, String str) {
        this.a = fVar;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        Chartboost chartboost;
        this.a.tryingToDisplay_ = true;
        chartboost = this.a.cb_;
        chartboost.showInterstitial(this.b);
    }
}
