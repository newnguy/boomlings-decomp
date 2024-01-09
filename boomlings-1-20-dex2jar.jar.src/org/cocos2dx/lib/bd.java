package org.cocos2dx.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

class bd implements TextWatcher, TextView.OnEditorActionListener {
  private static final Boolean a = Boolean.valueOf(false);
  
  private Cocos2dxGLSurfaceView b;
  
  private String c;
  
  private String d;
  
  public bd(Cocos2dxGLSurfaceView paramCocos2dxGLSurfaceView) {
    this.b = paramCocos2dxGLSurfaceView;
  }
  
  private Boolean a() {
    return Boolean.valueOf(((InputMethodManager)this.b.getTextField().getContext().getSystemService("input_method")).isFullscreenMode());
  }
  
  private void b(String paramString) {
    if (a.booleanValue())
      Log.d("TextInputFilter", paramString); 
  }
  
  public void a(String paramString) {
    this.d = paramString;
  }
  
  public void afterTextChanged(Editable paramEditable) {
    if (a().booleanValue())
      return; 
    b("afterTextChanged: " + paramEditable);
    int j = paramEditable.length() - this.c.length();
    int i = j;
    if (j > 0) {
      String str = paramEditable.subSequence(this.c.length(), paramEditable.length()).toString();
      this.b.insertText(str);
      b("insertText(" + str + ")");
    } else {
      while (true) {
        if (i < 0) {
          this.b.deleteBackward();
          b("deleteBackward");
          i++;
          continue;
        } 
        this.c = paramEditable.toString();
        return;
      } 
    } 
    this.c = paramEditable.toString();
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
    b("beforeTextChanged(" + paramCharSequence + ")start: " + paramInt1 + ",count: " + paramInt2 + ",after: " + paramInt3);
    this.c = paramCharSequence.toString();
  }
  
  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent) {
    if (this.b.getTextField() == paramTextView && a().booleanValue())
      for (paramInt = this.d.length();; paramInt--) {
        if (paramInt <= 0) {
          String str2 = paramTextView.getText().toString();
          String str1 = str2;
          if (str2.compareTo("") == 0)
            str1 = "\n"; 
          str2 = str1;
          if ('\n' != str1.charAt(str1.length() - 1))
            str2 = String.valueOf(str1) + '\n'; 
          this.b.insertText(str2);
          b("insertText(" + str2 + ")");
          return false;
        } 
        this.b.deleteBackward();
        b("deleteBackward");
      }  
    return false;
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\bd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */