package com.b.a;

import android.os.Bundle;
import android.webkit.CookieSyncManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class g implements i {
    final /* synthetic */ f a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(f fVar) {
        this.a = fVar;
    }

    @Override // com.b.a.i
    public void onCancel() {
        i iVar;
        s.a("Facebook-authorize", "Login canceled");
        iVar = this.a.l;
        iVar.onCancel();
    }

    @Override // com.b.a.i
    public void onComplete(Bundle bundle) {
        i iVar;
        i iVar2;
        CookieSyncManager.getInstance().sync();
        this.a.a(bundle.getString("access_token"));
        this.a.b(bundle.getString("expires_in"));
        if (!this.a.b()) {
            iVar = this.a.l;
            iVar.onFacebookError(new m("Failed to receive access token."));
            return;
        }
        s.a("Facebook-authorize", "Login Success! access_token=" + this.a.c() + " expires=" + this.a.d());
        iVar2 = this.a.l;
        iVar2.onComplete(bundle);
    }

    @Override // com.b.a.i
    public void onError(e eVar) {
        i iVar;
        s.a("Facebook-authorize", "Login failed: " + eVar);
        iVar = this.a.l;
        iVar.onError(eVar);
    }

    @Override // com.b.a.i
    public void onFacebookError(m mVar) {
        i iVar;
        s.a("Facebook-authorize", "Login failed: " + mVar);
        iVar = this.a.l;
        iVar.onFacebookError(mVar);
    }
}
