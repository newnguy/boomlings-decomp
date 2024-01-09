package org.cocos2dx.lib;

import android.content.Intent;
import android.net.Uri;

/* loaded from: classes.dex */
class s implements Runnable {
    private final /* synthetic */ String a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(String str) {
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity cocos2dxActivity;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.a));
        cocos2dxActivity = Cocos2dxActivity.me;
        cocos2dxActivity.startActivity(intent);
    }
}
