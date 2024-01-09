package org.cocos2dx.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/* loaded from: classes.dex */
public class bd implements TextWatcher, TextView.OnEditorActionListener {
    private static final Boolean a = false;
    private Cocos2dxGLSurfaceView b;
    private String c;
    private String d;

    public bd(Cocos2dxGLSurfaceView cocos2dxGLSurfaceView) {
        this.b = cocos2dxGLSurfaceView;
    }

    private Boolean a() {
        return Boolean.valueOf(((InputMethodManager) this.b.getTextField().getContext().getSystemService("input_method")).isFullscreenMode());
    }

    private void b(String str) {
        if (a.booleanValue()) {
            Log.d("TextInputFilter", str);
        }
    }

    public void a(String str) {
        this.d = str;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (a().booleanValue()) {
            return;
        }
        b("afterTextChanged: " + ((Object) editable));
        int length = editable.length() - this.c.length();
        if (length > 0) {
            String charSequence = editable.subSequence(this.c.length(), editable.length()).toString();
            this.b.insertText(charSequence);
            b("insertText(" + charSequence + ")");
        } else {
            while (length < 0) {
                this.b.deleteBackward();
                b("deleteBackward");
                length++;
            }
        }
        this.c = editable.toString();
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        b("beforeTextChanged(" + ((Object) charSequence) + ")start: " + i + ",count: " + i2 + ",after: " + i3);
        this.c = charSequence.toString();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (this.b.getTextField() == textView && a().booleanValue()) {
            for (int length = this.d.length(); length > 0; length--) {
                this.b.deleteBackward();
                b("deleteBackward");
            }
            String charSequence = textView.getText().toString();
            if (charSequence.compareTo("") == 0) {
                charSequence = "\n";
            }
            if ('\n' != charSequence.charAt(charSequence.length() - 1)) {
                charSequence = String.valueOf(charSequence) + '\n';
            }
            this.b.insertText(charSequence);
            b("insertText(" + charSequence + ")");
            return false;
        }
        return false;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
