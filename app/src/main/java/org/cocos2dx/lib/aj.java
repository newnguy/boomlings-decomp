package org.cocos2dx.lib;

/* loaded from: classes.dex */
public class aj implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView a;

    public aj(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.a = cocos2dxGLSurfaceView;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxRenderer cocos2dxRenderer;
        cocos2dxRenderer = this.a.mRenderer;
        cocos2dxRenderer.handleDeleteBackward();
    }
}
