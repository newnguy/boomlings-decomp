package com.tapjoy;

import android.widget.TextView;

/* loaded from: classes.dex */
class av implements Runnable {
    final /* synthetic */ TapjoyVideoView a;

    public av(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    @Override // java.lang.Runnable
    public void run() {
        TextView textView;
        int f;
        textView = this.a.j;
        StringBuilder append = new StringBuilder().append("");
        f = this.a.f();
        textView.setText(append.append(f).append(" seconds").toString());
    }
}
