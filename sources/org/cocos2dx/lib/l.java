package org.cocos2dx.lib;

/* loaded from: classes.dex */
class l implements Runnable {
    private final /* synthetic */ String a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(String str) {
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity.fbDelegate_.appRequestFriends(this.a);
    }
}
