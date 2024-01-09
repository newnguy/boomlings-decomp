package org.cocos2dx.lib;

class ag implements Runnable {
  final Cocos2dxGLSurfaceView a;
  
  ag(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView) {}
  
  public void run() {
    Cocos2dxGLSurfaceView.access$3(this.a).handleOnPause();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */