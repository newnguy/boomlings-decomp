package org.cocos2dx.lib;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

/* loaded from: classes.dex */
public class ac extends Handler {
    final /* synthetic */ Cocos2dxGLSurfaceView a;

    public ac(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.a = cocos2dxGLSurfaceView;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Cocos2dxEditText cocos2dxEditText;
        Cocos2dxEditText cocos2dxEditText2;
        bd bdVar;
        Cocos2dxGLSurfaceView cocos2dxGLSurfaceView;
        Cocos2dxEditText cocos2dxEditText3;
        Cocos2dxEditText cocos2dxEditText4;
        Cocos2dxEditText cocos2dxEditText5;
        Cocos2dxEditText cocos2dxEditText6;
        bd bdVar2;
        Cocos2dxEditText cocos2dxEditText7;
        Cocos2dxEditText cocos2dxEditText8;
        bd bdVar3;
        Cocos2dxEditText cocos2dxEditText9;
        bd bdVar4;
        Cocos2dxGLSurfaceView cocos2dxGLSurfaceView2;
        Cocos2dxEditText cocos2dxEditText10;
        switch (message.what) {
            case 2:
                cocos2dxEditText4 = this.a.mTextField;
                if (cocos2dxEditText4 != null) {
                    cocos2dxEditText5 = this.a.mTextField;
                    if (cocos2dxEditText5.requestFocus()) {
                        cocos2dxEditText6 = this.a.mTextField;
                        bdVar2 = Cocos2dxGLSurfaceView.textInputWraper;
                        cocos2dxEditText6.removeTextChangedListener(bdVar2);
                        cocos2dxEditText7 = this.a.mTextField;
                        cocos2dxEditText7.setText("");
                        String str = (String) message.obj;
                        cocos2dxEditText8 = this.a.mTextField;
                        cocos2dxEditText8.append(str);
                        bdVar3 = Cocos2dxGLSurfaceView.textInputWraper;
                        bdVar3.a(str);
                        cocos2dxEditText9 = this.a.mTextField;
                        bdVar4 = Cocos2dxGLSurfaceView.textInputWraper;
                        cocos2dxEditText9.addTextChangedListener(bdVar4);
                        cocos2dxGLSurfaceView2 = Cocos2dxGLSurfaceView.mainView;
                        cocos2dxEditText10 = this.a.mTextField;
                        ((InputMethodManager) cocos2dxGLSurfaceView2.getContext().getSystemService("input_method")).showSoftInput(cocos2dxEditText10, 0);
                        Log.d("GLSurfaceView", "showSoftInput");
                        return;
                    }
                    return;
                }
                return;
            case 3:
                cocos2dxEditText = this.a.mTextField;
                if (cocos2dxEditText != null) {
                    cocos2dxEditText2 = this.a.mTextField;
                    bdVar = Cocos2dxGLSurfaceView.textInputWraper;
                    cocos2dxEditText2.removeTextChangedListener(bdVar);
                    cocos2dxGLSurfaceView = Cocos2dxGLSurfaceView.mainView;
                    cocos2dxEditText3 = this.a.mTextField;
                    ((InputMethodManager) cocos2dxGLSurfaceView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(cocos2dxEditText3.getWindowToken(), 0);
                    Log.d("GLSurfaceView", "HideSoftInput");
                    return;
                }
                return;
            default:
                return;
        }
    }
}
