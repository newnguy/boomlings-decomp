package org.cocos2dx.lib;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class i extends Handler {
    final /* synthetic */ Cocos2dxActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(Cocos2dxActivity cocos2dxActivity) {
        this.a = cocos2dxActivity;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.showDialog(((ar) message.obj).a, ((ar) message.obj).b);
                return;
            default:
                return;
        }
    }
}
