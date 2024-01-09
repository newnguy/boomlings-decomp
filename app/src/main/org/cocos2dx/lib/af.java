package org.cocos2dx.lib;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class af implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView a;
    private final /* synthetic */ int b;

    /* JADX INFO: Access modifiers changed from: package-private */
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
