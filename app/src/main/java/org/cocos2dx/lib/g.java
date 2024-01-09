package org.cocos2dx.lib;

import com.chartboost.sdk.Chartboost;

/* loaded from: classes.dex */
public class g implements Runnable {
    final /* synthetic */ f a;

    public g(f fVar) {
        this.a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Chartboost chartboost;
        this.a.tryingToDisplay_ = true;
        chartboost = this.a.cb_;
        chartboost.showInterstitial();
    }
}
