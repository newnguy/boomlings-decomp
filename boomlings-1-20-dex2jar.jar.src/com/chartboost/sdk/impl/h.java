package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class h extends b {
  private static int h = 50;
  
  private static int i = 50;
  
  private static int j = 30;
  
  private List k = new ArrayList();
  
  private a.a l;
  
  private a.a m;
  
  private a.a n;
  
  private SparseArray o;
  
  public h(a parama) {
    super(parama);
  }
  
  protected b.b a(Context paramContext) {
    return new h$a(this, paramContext, null);
  }
  
  public void a(JSONObject paramJSONObject) {
    super.a(paramJSONObject);
    JSONArray jSONArray = paramJSONObject.optJSONArray("cells");
    if (jSONArray == null) {
      if (this.d != null)
        this.d.a(); 
      return;
    } 
    this.o = new SparseArray();
    h$1 h$1 = new h$1(this);
    for (byte b1 = 0;; b1++) {
      h$4 h$4;
      h$2 h$2;
      Bundle bundle;
      if (b1 >= jSONArray.length()) {
        h$2 = new h$2(this);
        h$3 h$3 = new h$3(this);
        h$4 = new h$4(this);
        a("close", h$2);
        a("header-center", h$3);
        a("header-tile", h$4);
        return;
      } 
      JSONObject jSONObject = h$2.optJSONObject(b1);
      this.k.add(jSONObject);
      String str = jSONObject.optString("type", "");
      if (str.equals("regular")) {
        jSONObject = jSONObject.optJSONObject("assets");
        if (jSONObject != null) {
          this.e++;
          bundle = new Bundle();
          bundle.putInt("index", b1);
          a(jSONObject, "icon", h$4, bundle);
        } 
      } else {
        JSONObject jSONObject1;
        if (bundle.equals("featured")) {
          jSONObject1 = jSONObject.optJSONObject("assets");
          if (jSONObject1 != null) {
            this.e++;
            Bundle bundle1 = new Bundle();
            bundle1.putInt("index", b1);
            a(jSONObject1, "portrait", h$4, bundle1);
            this.e++;
            bundle1 = new Bundle();
            bundle1.putInt("index", b1);
            a(jSONObject1, "landscape", h$4, bundle1);
          } 
        } else {
          jSONObject1.equals("webview");
        } 
      } 
    } 
  }
  
  public void c() {
    super.c();
    this.k = null;
    this.l = null;
    this.n = null;
    this.m = null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */