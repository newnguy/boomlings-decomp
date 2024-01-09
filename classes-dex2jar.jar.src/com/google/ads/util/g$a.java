package com.google.ads.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.ads.AdActivity;
import com.google.ads.internal.AdWebView;
import com.google.ads.l;
import com.google.ads.m;

public class g$a extends WebChromeClient {
  private final m a;
  
  public g$a(m paramm) {
    this.a = paramm;
  }
  
  private static void a(AlertDialog.Builder paramBuilder, Context paramContext, String paramString1, String paramString2, JsPromptResult paramJsPromptResult) {
    LinearLayout linearLayout = new LinearLayout(paramContext);
    linearLayout.setOrientation(1);
    TextView textView = new TextView(paramContext);
    textView.setText(paramString1);
    EditText editText = new EditText(paramContext);
    editText.setText(paramString2);
    linearLayout.addView((View)textView);
    linearLayout.addView((View)editText);
    paramBuilder.setView((View)linearLayout).setPositiveButton(17039370, new p(paramJsPromptResult, editText)).setNegativeButton(17039360, new o(paramJsPromptResult)).setOnCancelListener(new n(paramJsPromptResult)).create().show();
  }
  
  private static void a(AlertDialog.Builder paramBuilder, String paramString, JsResult paramJsResult) {
    paramBuilder.setMessage(paramString).setPositiveButton(17039370, new m(paramJsResult)).setNegativeButton(17039360, new l(paramJsResult)).setOnCancelListener(new k(paramJsResult)).create().show();
  }
  
  private static boolean a(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsResult paramJsResult, JsPromptResult paramJsPromptResult, boolean paramBoolean) {
    if (paramWebView instanceof AdWebView) {
      AdActivity adActivity = ((AdWebView)paramWebView).d();
      if (adActivity != null) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)adActivity);
        builder.setTitle(paramString1);
        if (paramBoolean) {
          a(builder, (Context)adActivity, paramString2, paramString3, paramJsPromptResult);
        } else {
          a(builder, paramString2, paramJsResult);
        } 
        return true;
      } 
    } 
    return false;
  }
  
  public void onCloseWindow(WebView paramWebView) {
    if (paramWebView instanceof AdWebView)
      ((AdWebView)paramWebView).a(); 
  }
  
  public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage) {
    String str = "JS: " + paramConsoleMessage.message() + " (" + paramConsoleMessage.sourceId() + ":" + paramConsoleMessage.lineNumber() + ")";
    switch (j.a[paramConsoleMessage.messageLevel().ordinal()]) {
      default:
        return super.onConsoleMessage(paramConsoleMessage);
      case 1:
        b.b(str);
      case 2:
        b.e(str);
      case 3:
      case 4:
        b.c(str);
      case 5:
        break;
    } 
    b.a(str);
  }
  
  public void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater) {
    l.a a = (l.a)((l)this.a.a.a()).a.a();
    long l = ((Long)a.i.a()).longValue() - paramLong3;
    if (l <= 0L) {
      paramQuotaUpdater.updateQuota(paramLong1);
      return;
    } 
    if (paramLong1 == 0L) {
      if (paramLong2 > l || paramLong2 > ((Long)a.j.a()).longValue())
        paramLong2 = 0L; 
    } else if (paramLong2 == 0L) {
      paramLong2 = Math.min(Math.min(((Long)a.k.a()).longValue(), l) + paramLong1, ((Long)a.j.a()).longValue());
    } else {
      paramLong3 = paramLong1;
      if (paramLong2 <= Math.min(((Long)a.j.a()).longValue() - paramLong1, l))
        paramLong3 = paramLong1 + paramLong2; 
      paramLong2 = paramLong3;
    } 
    paramQuotaUpdater.updateQuota(paramLong2);
  }
  
  public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult) {
    return a(paramWebView, paramString1, paramString2, null, paramJsResult, null, false);
  }
  
  public boolean onJsBeforeUnload(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult) {
    return a(paramWebView, paramString1, paramString2, null, paramJsResult, null, false);
  }
  
  public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult) {
    return a(paramWebView, paramString1, paramString2, null, paramJsResult, null, false);
  }
  
  public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult) {
    return a(paramWebView, paramString1, paramString2, paramString3, null, paramJsPromptResult, true);
  }
  
  public void onReachedMaxAppCacheSize(long paramLong1, long paramLong2, WebStorage.QuotaUpdater paramQuotaUpdater) {
    l.a a = (l.a)((l)this.a.a.a()).a.a();
    long l = ((Long)a.h.a()).longValue();
    paramLong1 = ((Long)a.g.a()).longValue() + paramLong1;
    if (l - paramLong2 < paramLong1) {
      paramQuotaUpdater.updateQuota(0L);
      return;
    } 
    paramQuotaUpdater.updateQuota(paramLong1);
  }
  
  public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
    paramCustomViewCallback.onCustomViewHidden();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\g$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */