package org.cocos2dx.lib;

import com.chartboost.sdk.Chartboost;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class g implements Runnable {
    final /* synthetic */ f a;

    /* JADX INFO: Access modifiers changed from: package-private */
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
