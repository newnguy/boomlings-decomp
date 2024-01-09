package com.tapjoy;

import java.util.TimerTask;

/* loaded from: classes.dex */
public class ba extends TimerTask {
    final /* synthetic */ TapjoyVideoView a;

    private ba(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    public /* synthetic */ ba(TapjoyVideoView tapjoyVideoView, at atVar) {
        this(tapjoyVideoView);
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.a.e.post(this.a.g);
    }
}
