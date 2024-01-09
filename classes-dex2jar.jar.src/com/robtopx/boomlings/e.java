package com.robtopx.boomlings;

import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

class e implements Runnable {
  public void run() {
    if (Boomlings.keyboardActive_) {
      Cocos2dxGLSurfaceView.openIMEKeyboard();
      return;
    } 
    Cocos2dxGLSurfaceView.closeIMEKeyboard();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\robtopx\boomlings\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */