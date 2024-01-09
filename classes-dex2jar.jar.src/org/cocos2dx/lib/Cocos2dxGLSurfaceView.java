package org.cocos2dx.lib;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;
import com.robtopx.boomlings.Boomlings;

public class Cocos2dxGLSurfaceView extends GLSurfaceView {
  private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
  
  private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
  
  private static final String TAG = Cocos2dxGLSurfaceView.class.getCanonicalName();
  
  private static final boolean debug = false;
  
  private static Handler handler;
  
  private static Cocos2dxGLSurfaceView mainView;
  
  private static bd textInputWraper;
  
  private Cocos2dxRenderer mRenderer;
  
  private Cocos2dxEditText mTextField;
  
  public Cocos2dxGLSurfaceView(Context paramContext) {
    super(paramContext);
    initView();
  }
  
  public Cocos2dxGLSurfaceView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initView();
  }
  
  public static void closeIMEKeyboard() {
    Message message = new Message();
    message.what = 3;
    handler.sendMessage(message);
  }
  
  private void dumpEvent(MotionEvent paramMotionEvent) {
    byte b = 0;
    StringBuilder stringBuilder = new StringBuilder();
    int j = paramMotionEvent.getAction();
    int i = j & 0xFF;
    (new String[10])[0] = "DOWN";
    (new String[10])[1] = "UP";
    (new String[10])[2] = "MOVE";
    (new String[10])[3] = "CANCEL";
    (new String[10])[4] = "OUTSIDE";
    (new String[10])[5] = "POINTER_DOWN";
    (new String[10])[6] = "POINTER_UP";
    (new String[10])[7] = "7?";
    (new String[10])[8] = "8?";
    (new String[10])[9] = "9?";
    stringBuilder.append("event ACTION_").append((new String[10])[i]);
    if (i == 5 || i == 6) {
      stringBuilder.append("(pid ").append(j >> 8);
      stringBuilder.append(")");
    } 
    stringBuilder.append("[");
    while (true) {
      if (b >= paramMotionEvent.getPointerCount()) {
        stringBuilder.append("]");
        Log.d(TAG, stringBuilder.toString());
        return;
      } 
      stringBuilder.append("#").append(b);
      stringBuilder.append("(pid ").append(paramMotionEvent.getPointerId(b));
      stringBuilder.append(")=").append((int)paramMotionEvent.getX(b));
      stringBuilder.append(",").append((int)paramMotionEvent.getY(b));
      if (b + 1 < paramMotionEvent.getPointerCount())
        stringBuilder.append(";"); 
      b++;
    } 
  }
  
  private String getContentText() {
    return this.mRenderer.getContentText();
  }
  
  public static void openIMEKeyboard() {
    Message message = new Message();
    message.what = 2;
    message.obj = mainView.getContentText();
    handler.sendMessage(message);
  }
  
  public void deleteBackward() {
    queueEvent(new aj(this));
  }
  
  public TextView getTextField() {
    return (TextView)this.mTextField;
  }
  
  protected void initView() {
    setFocusableInTouchMode(true);
    if (Build.VERSION.SDK_INT >= 11)
      setPreserveEGLContextOnPause(true); 
    textInputWraper = new bd(this);
    handler = new ac(this);
    mainView = this;
  }
  
  public void insertText(String paramString) {
    queueEvent(new ai(this, paramString));
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    boolean bool = true;
    if (paramInt == 4 || paramInt == 82) {
      if (paramKeyEvent.getRepeatCount() != 0)
        return bool; 
      boolean bool1 = bool;
      if (!Boomlings.blockBackButton_) {
        queueEvent(new af(this, paramInt));
        bool1 = bool;
      } 
      return bool1;
    } 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void onPause() {
    queueEvent(new ag(this));
    super.onPause();
  }
  
  public void onResume() {
    super.onResume();
    queueEvent(new ah(this));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mRenderer.setScreenWidthAndHeight(paramInt1, paramInt2);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    int j = paramMotionEvent.getPointerCount();
    int[] arrayOfInt = new int[j];
    float[] arrayOfFloat1 = new float[j];
    float[] arrayOfFloat2 = new float[j];
    int i = 0;
    while (true) {
      if (i >= j) {
        switch (paramMotionEvent.getAction() & 0xFF) {
          default:
            return true;
          case 5:
            i = paramMotionEvent.getAction() >> 8;
            queueEvent(new ak(this, paramMotionEvent.getPointerId(i), paramMotionEvent.getX(i), paramMotionEvent.getY(i)));
          case 0:
            queueEvent(new al(this, paramMotionEvent.getPointerId(0), arrayOfFloat1[0], arrayOfFloat2[0]));
          case 2:
            queueEvent(new am(this, arrayOfInt, arrayOfFloat1, arrayOfFloat2));
          case 6:
            i = paramMotionEvent.getAction() >> 8;
            queueEvent(new an(this, paramMotionEvent.getPointerId(i), paramMotionEvent.getX(i), paramMotionEvent.getY(i)));
          case 1:
            queueEvent(new ad(this, paramMotionEvent.getPointerId(0), arrayOfFloat1[0], arrayOfFloat2[0]));
          case 3:
            break;
        } 
      } else {
        arrayOfInt[i] = paramMotionEvent.getPointerId(i);
        arrayOfFloat1[i] = paramMotionEvent.getX(i);
        arrayOfFloat2[i] = paramMotionEvent.getY(i);
        i++;
        continue;
      } 
      queueEvent(new ae(this, arrayOfInt, arrayOfFloat1, arrayOfFloat2));
    } 
  }
  
  public void setCocos2dxRenderer(Cocos2dxRenderer paramCocos2dxRenderer) {
    this.mRenderer = paramCocos2dxRenderer;
    setRenderer(this.mRenderer);
  }
  
  public void setTextField(Cocos2dxEditText paramCocos2dxEditText) {
    this.mTextField = paramCocos2dxEditText;
    if (this.mTextField != null && textInputWraper != null) {
      this.mTextField.setOnEditorActionListener(textInputWraper);
      this.mTextField.setMainView(this);
      requestFocus();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\Cocos2dxGLSurfaceView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */