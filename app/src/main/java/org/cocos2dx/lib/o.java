package org.cocos2dx.lib;

/* loaded from: classes.dex */
class o implements Runnable {
    private final /* synthetic */ String a;

    public o(String str) {
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity.fbDelegate_.postScore(this.a);
    }
}
