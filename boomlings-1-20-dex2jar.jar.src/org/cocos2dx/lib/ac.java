package org.cocos2dx.lib;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

class ac extends Handler {
  final Cocos2dxGLSurfaceView a;
  
  ac(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView) {}
  
  public void handleMessage(Message paramMessage) {
    switch (paramMessage.what) {
      default:
        return;
      case 2:
        if (Cocos2dxGLSurfaceView.access$0(this.a) != null && Cocos2dxGLSurfaceView.access$0(this.a).requestFocus()) {
          Cocos2dxGLSurfaceView.access$0(this.a).removeTextChangedListener(Cocos2dxGLSurfaceView.access$1());
          Cocos2dxGLSurfaceView.access$0(this.a).setText("");
          String str = (String)paramMessage.obj;
          Cocos2dxGLSurfaceView.access$0(this.a).append(str);
          Cocos2dxGLSurfaceView.access$1().a(str);
          Cocos2dxGLSurfaceView.access$0(this.a).addTextChangedListener(Cocos2dxGLSurfaceView.access$1());
          ((InputMethodManager)Cocos2dxGLSurfaceView.access$2().getContext().getSystemService("input_method")).showSoftInput((View)Cocos2dxGLSurfaceView.access$0(this.a), 0);
          Log.d("GLSurfaceView", "showSoftInput");
        } 
      case 3:
        break;
    } 
    if (Cocos2dxGLSurfaceView.access$0(this.a) != null) {
      Cocos2dxGLSurfaceView.access$0(this.a).removeTextChangedListener(Cocos2dxGLSurfaceView.access$1());
      ((InputMethodManager)Cocos2dxGLSurfaceView.access$2().getContext().getSystemService("input_method")).hideSoftInputFromWindow(Cocos2dxGLSurfaceView.access$0(this.a).getWindowToken(), 0);
      Log.d("GLSurfaceView", "HideSoftInput");
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\ac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */