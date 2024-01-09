package org.cocos2dx.lib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/* loaded from: classes.dex */
public class Cocos2dxEditText extends EditText {
    private Cocos2dxGLSurfaceView mView;

    public Cocos2dxEditText(Context context) {
        super(context);
    }

    public Cocos2dxEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Cocos2dxEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean onKeyDown = super.onKeyDown(i, keyEvent);
        if (i == 4) {
            this.mView.requestFocus();
        }
        return onKeyDown;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0) {
            this.mView.onKeyDown(i, keyEvent);
            return true;
        }
        return false;
    }

    public void setMainView(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.mView = cocos2dxGLSurfaceView;
    }
}
