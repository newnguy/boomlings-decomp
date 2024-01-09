package org.cocos2dx.lib;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bc implements Runnable {
    final /* synthetic */ bb a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bc(bb bbVar) {
        this.a = bbVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.updateResultsInUi();
    }
}
