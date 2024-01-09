package org.cocos2dx.lib;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ah implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ah(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.a = cocos2dxGLSurfaceView;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxRenderer cocos2dxRenderer;
        cocos2dxRenderer = this.a.mRenderer;
        cocos2dxRenderer.handleOnResume();
    }
}
