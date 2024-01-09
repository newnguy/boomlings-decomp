package org.cocos2dx.lib;

/* loaded from: classes.dex */
class n implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(String str, String str2, String str3, String str4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity.fbDelegate_.postToWall(this.a, this.b, this.c, this.d);
    }
}
