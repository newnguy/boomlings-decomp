package org.cocos2dx.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class Cocos2dxEditText extends EditText {
  private Cocos2dxGLSurfaceView mView;
  
  public Cocos2dxEditText(Context paramContext) {
    super(paramContext);
  }
  
  public Cocos2dxEditText(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public Cocos2dxEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool = super.onKeyDown(paramInt, paramKeyEvent);
    if (paramInt == 4)
      this.mView.requestFocus(); 
    return bool;
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4 && paramKeyEvent.getAction() == 0) {
      this.mView.onKeyDown(paramInt, paramKeyEvent);
      return true;
    } 
    return false;
  }
  
  public void setMainView(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView) {
    this.mView = paramCocos2dxGLSurfaceView;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\Cocos2dxEditText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */