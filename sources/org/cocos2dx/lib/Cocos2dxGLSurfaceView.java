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

/* loaded from: classes.dex */
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

    public Cocos2dxGLSurfaceView(Context context) {
        super(context);
        initView();
    }

    public Cocos2dxGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public static void closeIMEKeyboard() {
        Message message = new Message();
        message.what = 3;
        handler.sendMessage(message);
    }

    private void dumpEvent(MotionEvent motionEvent) {
        StringBuilder sb = new StringBuilder();
        int action = motionEvent.getAction();
        int i = action & 255;
        sb.append("event ACTION_").append(new String[]{"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"}[i]);
        if (i == 5 || i == 6) {
            sb.append("(pid ").append(action >> 8);
            sb.append(")");
        }
        sb.append("[");
        for (int i2 = 0; i2 < motionEvent.getPointerCount(); i2++) {
            sb.append("#").append(i2);
            sb.append("(pid ").append(motionEvent.getPointerId(i2));
            sb.append(")=").append((int) motionEvent.getX(i2));
            sb.append(",").append((int) motionEvent.getY(i2));
            if (i2 + 1 < motionEvent.getPointerCount()) {
                sb.append(";");
            }
        }
        sb.append("]");
        Log.d(TAG, sb.toString());
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
        return this.mTextField;
    }

    protected void initView() {
        setFocusableInTouchMode(true);
        if (Build.VERSION.SDK_INT >= 11) {
            setPreserveEGLContextOnPause(true);
        }
        textInputWraper = new bd(this);
        handler = new ac(this);
        mainView = this;
    }

    public void insertText(String str) {
        queueEvent(new ai(this, str));
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 || i == 82) {
            if (keyEvent.getRepeatCount() == 0 && !Boomlings.blockBackButton_) {
                queueEvent(new af(this, i));
                return true;
            }
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        queueEvent(new ag(this));
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        queueEvent(new ah(this));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mRenderer.setScreenWidthAndHeight(i, i2);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        int[] iArr = new int[pointerCount];
        float[] fArr = new float[pointerCount];
        float[] fArr2 = new float[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            iArr[i] = motionEvent.getPointerId(i);
            fArr[i] = motionEvent.getX(i);
            fArr2[i] = motionEvent.getY(i);
        }
        switch (motionEvent.getAction() & 255) {
            case 0:
                queueEvent(new al(this, motionEvent.getPointerId(0), fArr[0], fArr2[0]));
                return true;
            case 1:
                queueEvent(new ad(this, motionEvent.getPointerId(0), fArr[0], fArr2[0]));
                return true;
            case 2:
                queueEvent(new am(this, iArr, fArr, fArr2));
                return true;
            case 3:
                queueEvent(new ae(this, iArr, fArr, fArr2));
                return true;
            case 4:
            default:
                return true;
            case 5:
                int action = motionEvent.getAction() >> 8;
                queueEvent(new ak(this, motionEvent.getPointerId(action), motionEvent.getX(action), motionEvent.getY(action)));
                return true;
            case 6:
                int action2 = motionEvent.getAction() >> 8;
                queueEvent(new an(this, motionEvent.getPointerId(action2), motionEvent.getX(action2), motionEvent.getY(action2)));
                return true;
        }
    }

    public void setCocos2dxRenderer(Cocos2dxRenderer cocos2dxRenderer) {
        this.mRenderer = cocos2dxRenderer;
        setRenderer(this.mRenderer);
    }

    public void setTextField(Cocos2dxEditText cocos2dxEditText) {
        this.mTextField = cocos2dxEditText;
        if (this.mTextField == null || textInputWraper == null) {
            return;
        }
        this.mTextField.setOnEditorActionListener(textInputWraper);
        this.mTextField.setMainView(this);
        requestFocus();
    }
}
