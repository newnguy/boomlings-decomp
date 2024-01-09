package org.cocos2dx.lib;

/* loaded from: classes.dex */
public class bc implements Runnable {
    final /* synthetic */ bb a;

    public bc(bb bbVar) {
        this.a = bbVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.updateResultsInUi();
    }
}
