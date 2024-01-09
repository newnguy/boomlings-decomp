package org.cocos2dx.lib;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.widget.Toast;

/* loaded from: classes.dex */
class z implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public z(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxActivity cocos2dxActivity;
        Cocos2dxActivity cocos2dxActivity2;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{this.a});
        intent.putExtra("android.intent.extra.SUBJECT", this.b);
        intent.putExtra("android.intent.extra.TEXT", this.c);
        try {
            cocos2dxActivity2 = Cocos2dxActivity.me;
            cocos2dxActivity2.startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (ActivityNotFoundException e) {
            cocos2dxActivity = Cocos2dxActivity.me;
            Toast.makeText(cocos2dxActivity, "There are no email clients installed.", 0).show();
        }
    }
}
