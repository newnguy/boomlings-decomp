package com.chartboost.sdk.impl;

import android.content.Context;
import com.chartboost.sdk.b;
import org.json.JSONObject;

public class v extends b {
  private String h = null;
  
  public v(a parama) {
    super(parama);
  }
  
  protected b.b a(Context paramContext) {
    return new v$a(this, paramContext, this.h);
  }
  
  public void a(JSONObject paramJSONObject) {
    String str = paramJSONObject.optString("html");
    if (str != null) {
      this.h = str;
      a();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */