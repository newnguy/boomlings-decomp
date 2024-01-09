package org.cocos2dx.lib;

/* loaded from: classes.dex */
class am implements Runnable {
    final /* synthetic */ Cocos2dxGLSurfaceView a;
    private final /* synthetic */ int[] b;
    private final /* synthetic */ float[] c;
    private final /* synthetic */ float[] d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public am(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView, int[] iArr, float[] fArr, float[] fArr2) {
        this.a = cocos2dxGLSurfaceView;
        this.b = iArr;
        this.c = fArr;
        this.d = fArr2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Cocos2dxRenderer cocos2dxRenderer;
        cocos2dxRenderer = this.a.mRenderer;
        cocos2dxRenderer.handleActionMove(this.b, this.c, this.d);
    }
}
