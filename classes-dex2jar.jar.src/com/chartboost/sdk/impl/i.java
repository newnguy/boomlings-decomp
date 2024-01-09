package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.chartboost.sdk.Libraries.d;
import org.json.JSONObject;

public class i extends c implements h$b {
  private WebView b;
  
  public i(Context paramContext) {
    super(paramContext);
    this.b = new WebView(paramContext);
    addView((View)this.b, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
    this.b.setBackgroundColor(0);
    this.b.setWebViewClient(new i$1(this));
  }
  
  public int a() {
    return d.a(100, getContext());
  }
  
  public void a(JSONObject paramJSONObject, int paramInt) {
    String str = paramJSONObject.optString("html");
    if (str != null)
      try {
        this.b.loadDataWithBaseURL("file:///android_res/", str, "text/html", "UTF-8", null);
      } catch (Exception exception) {} 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */