package org.cocos2dx.lib;

import android.widget.Toast;

/* loaded from: classes.dex */
class av implements Runnable {
    final /* synthetic */ as a;

    public av(as asVar) {
        this.a = asVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Toast.makeText(this.a.cAct_.getApplicationContext(), "Logged In", 0).show();
    }
}
