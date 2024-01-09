package org.cocos2dx.lib;

import android.widget.Toast;

/* loaded from: classes.dex */
class aw implements Runnable {
    final /* synthetic */ as a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aw(as asVar) {
        this.a = asVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Toast.makeText(this.a.cAct_.getApplicationContext(), "Logged Out", 0).show();
    }
}
