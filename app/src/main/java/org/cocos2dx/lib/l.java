package org.cocos2dx.lib;

/* loaded from: classes.dex */
class l implements Runnable {
    private final /* synthetic */ String a;

    public l(String str) {
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity.fbDelegate_.appRequestFriends(this.a);
    }
}
