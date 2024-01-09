package org.cocos2dx.lib;

import android.os.Bundle;
import android.widget.Toast;

/* loaded from: classes.dex */
public class ax extends d {
    final /* synthetic */ as this$0;

    public ax(as asVar) {
        this.this$0 = asVar;
    }

    @Override // org.cocos2dx.lib.d, com.b.a.i
    public void onCancel() {
        Toast.makeText(this.this$0.cAct_.getApplicationContext(), "App request cancelled", 0).show();
    }

    @Override // com.b.a.i
    public void onComplete(Bundle bundle) {
        if (bundle.getString("request") != null) {
            Toast.makeText(this.this$0.cAct_.getApplicationContext(), "App request sent", 0).show();
        } else {
            Toast.makeText(this.this$0.cAct_.getApplicationContext(), "No request sent", 0).show();
        }
    }

    @Override // org.cocos2dx.lib.d, com.b.a.i
    public void onFacebookError(com.b.a.m mVar) {
        Toast.makeText(this.this$0.cAct_.getApplicationContext(), "Facebook Error: " + mVar.getMessage(), 0).show();
    }
}
