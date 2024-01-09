package org.cocos2dx.lib;

/* loaded from: classes.dex */
class m implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;

    public m(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity.fbDelegate_.appRequestFriendsRefID(this.a, this.b);
    }
}
