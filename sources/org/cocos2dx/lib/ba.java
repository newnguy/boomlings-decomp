package org.cocos2dx.lib;

import android.os.Bundle;
import android.widget.Toast;

/* loaded from: classes.dex */
public class ba extends d {
    final /* synthetic */ as this$0;

    public ba(as asVar) {
        this.this$0 = asVar;
    }

    @Override // org.cocos2dx.lib.d, com.b.a.i
    public void onCancel() {
        Toast.makeText(this.this$0.cAct_.getApplicationContext(), "Update status cancelled", 0).show();
    }

    @Override // com.b.a.i
    public void onComplete(Bundle bundle) {
        if (bundle.getString("post_id") != null) {
            Toast.makeText(this.this$0.cAct_.getApplicationContext(), "Update Status executed", 0).show();
        } else {
            Toast.makeText(this.this$0.cAct_.getApplicationContext(), "No wall post made", 0).show();
        }
    }

    @Override // org.cocos2dx.lib.d, com.b.a.i
    public void onFacebookError(com.b.a.m mVar) {
        Toast.makeText(this.this$0.cAct_.getApplicationContext(), "Facebook Error: " + mVar.getMessage(), 0).show();
    }
}
