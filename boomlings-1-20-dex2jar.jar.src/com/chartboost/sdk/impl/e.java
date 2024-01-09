package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.d;
import org.json.JSONObject;

public class e extends c implements h$b {
  private static int c = 50;
  
  private static int d = 10;
  
  public f b;
  
  private g e;
  
  private TextView f;
  
  public e(Context paramContext) {
    super(paramContext);
    this.e = new g(paramContext);
    this.b = new f(paramContext);
    this.f = new TextView(paramContext);
    this.f.setTypeface(null, 1);
    this.f.setTextSize(2, 16.0F);
    this.f.setShadowLayer(1.0F, 1.0F, 1.0F, -1);
    this.f.setBackgroundColor(0);
    this.f.setTextColor(-16777216);
    this.f.setEllipsize(TextUtils.TruncateAt.END);
    setBackgroundColor(-3355444);
    setFocusable(false);
    addView((View)this.e);
    addView((View)this.f);
    addView((View)this.b);
  }
  
  public int a() {
    return d.a(c + d * 2, getContext());
  }
  
  public void a(JSONObject paramJSONObject, int paramInt) {
    this.f.setText(paramJSONObject.optString("name", "Unknown App"));
    String str = paramJSONObject.optString("deep-text");
    if (str != null && !str.equals("")) {
      this.b.setText(str);
    } else {
      str = paramJSONObject.optString("text", "VIEW");
      this.b.setText(str);
    } 
    paramJSONObject = paramJSONObject.optJSONObject("assets");
    if (paramJSONObject != null) {
      JSONObject jSONObject = paramJSONObject.optJSONObject("icon");
      if (jSONObject != null) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", paramInt);
        com.chartboost.sdk.Libraries.e.a().a(jSONObject.optString("url"), jSONObject.optString("checksum"), null, this.e, bundle);
      } 
    } 
    b();
  }
  
  protected void b() {
    int i = d.a(c, getContext());
    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i, i);
    LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(-2, -1);
    LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
    i = d.a(d, getContext());
    layoutParams2.setMargins(i, i, i, i);
    layoutParams1.setMargins(i, i, i, i);
    layoutParams3.setMargins(i, i, i, i);
    layoutParams1.weight = 1.0F;
    this.f.setGravity(16);
    layoutParams3.gravity = 16;
    this.e.setLayoutParams((ViewGroup.LayoutParams)layoutParams2);
    this.e.setScaleType(ImageView.ScaleType.FIT_CENTER);
    this.f.setLayoutParams((ViewGroup.LayoutParams)layoutParams1);
    this.b.setLayoutParams((ViewGroup.LayoutParams)layoutParams3);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */