package org.cocos2dx.lib;

class am implements Runnable {
  final Cocos2dxGLSurfaceView a;
  
  private final int[] b;
  
  private final float[] c;
  
  private final float[] d;
  
  am(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView, int[] paramArrayOfint, float[] paramArrayOffloat1, float[] paramArrayOffloat2) {}
  
  public void run() {
    Cocos2dxGLSurfaceView.access$3(this.a).handleActionMove(this.b, this.c, this.d);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\am.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */