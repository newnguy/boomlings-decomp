package org.cocos2dx.lib;

/* loaded from: classes.dex */
public class af implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView a;
    private final /* synthetic */ int b;

    public af(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int i) {
        this.a = cocos2dxGLSurfaceView;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxRenderer cocos2dxRenderer;
        cocos2dxRenderer = this.a.mRenderer;
        cocos2dxRenderer.handleKeyDown(this.b);
    }
}
