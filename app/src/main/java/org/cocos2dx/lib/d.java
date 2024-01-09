package org.cocos2dx.lib;

/* loaded from: classes.dex */
public abstract class d implements com.b.a.i {
    @Override // com.b.a.i
    public void onCancel() {
    }

    @Override // com.b.a.i
    public void onError(com.b.a.e eVar) {
        eVar.printStackTrace();
    }

    @Override // com.b.a.i
    public void onFacebookError(com.b.a.m mVar) {
        mVar.printStackTrace();
    }
}
