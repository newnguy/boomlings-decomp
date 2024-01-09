package com.google.ads;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.google.ads.internal.d;
import com.google.ads.internal.e;
import java.util.HashMap;

public class ai implements n {
  public void a(d paramd, HashMap<Object, Object> paramHashMap, WebView paramWebView) {
    Context context = (Context)(paramd.h()).f.a();
    String str = (String)paramHashMap.get("a");
    if (str != null) {
      if (str.equals("resize")) {
        ag.a(paramWebView, (String)paramHashMap.get("u"));
        return;
      } 
      if (str.equals("state")) {
        ag.a((Activity)(paramd.h()).e.a(), paramWebView, (String)paramHashMap.get("u"));
        return;
      } 
    } 
    Intent intent = new Intent();
    intent.setComponent(new ComponentName("com.google.android.apps.plus", "com.google.android.apps.circles.platform.PlusOneActivity"));
    if (!ah.a(intent, context)) {
      if (ah.a(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.apps.plus")), context)) {
        if (!TextUtils.isEmpty((CharSequence)paramHashMap.get("d")) && !TextUtils.isEmpty((CharSequence)paramHashMap.get("o")) && !TextUtils.isEmpty((CharSequence)paramHashMap.get("c"))) {
          AlertDialog.Builder builder = new AlertDialog.Builder(context);
          builder.setMessage((CharSequence)paramHashMap.get("d")).setPositiveButton((CharSequence)paramHashMap.get("o"), new ak(paramd)).setNegativeButton((CharSequence)paramHashMap.get("c"), new aj());
          builder.create().show();
          return;
        } 
        paramHashMap = new HashMap<Object, Object>();
        paramHashMap.put("u", "market://details?id=com.google.android.apps.plus");
        AdActivity.a(paramd, new e("intent", paramHashMap));
      } 
      return;
    } 
    AdActivity.a(paramd, new e("plusone", paramHashMap));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */