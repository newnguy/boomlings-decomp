package org.cocos2dx.lib;

class ah implements Runnable {
  final Cocos2dxGLSurfaceView a;
  
  ah(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView) {}
  
  public void run() {
    Cocos2dxGLSurfaceView.access$3(this.a).handleOnResume();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */