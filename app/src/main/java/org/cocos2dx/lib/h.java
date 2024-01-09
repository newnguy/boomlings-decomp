package org.cocos2dx.lib;

import com.chartboost.sdk.Chartboost;

/* loaded from: classes.dex */
public class h implements Runnable {
    final /* synthetic */ f a;
    private final /* synthetic */ String b;

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
