package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.e;
import org.json.JSONObject;

public class d extends c implements h$b {
  private static int b = 100;
  
  private static int c = 5;
  
  private g d;
  
  public d(Context paramContext) {
    super(paramContext);
    this.d = new g(paramContext);
    addView((View)this.d, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
  }
  
  public int a() {
    return com.chartboost.sdk.Libraries.d.a(b + c * 2, getContext());
  }
  
  public void a(JSONObject paramJSONObject, int paramInt) {
    boolean bool = Chartboost.sharedChartboost().orientation().isPortrait();
    JSONObject jSONObject = paramJSONObject.optJSONObject("assets");
    if (jSONObject != null) {
      String str;
      if (bool) {
        str = "portrait";
      } else {
        str = "landscape";
      } 
      jSONObject = jSONObject.optJSONObject(str);
      if (jSONObject != null) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", paramInt);
        e.a().a(jSONObject.optString("url"), jSONObject.optString("checksum"), null, this.d, bundle);
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */