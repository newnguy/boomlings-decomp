package com.tapjoy;

import java.util.TimerTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ba extends TimerTask {
    final /* synthetic */ TapjoyVideoView a;

    private ba(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ ba(TapjoyVideoView tapjoyVideoView, at atVar) {
        this(tapjoyVideoView);
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.a.e.post(this.a.g);
    }
}
