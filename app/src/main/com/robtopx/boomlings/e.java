package com.robtopx.boomlings;

import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

/* loaded from: classes.dex */
class e implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        if (Boomlings.keyboardActive_) {
            Cocos2dxGLSurfaceView.openIMEKeyboard();
        } else {
            Cocos2dxGLSurfaceView.closeIMEKeyboard();
        }
    }
}
