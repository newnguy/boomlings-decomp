package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.impl.a;
import org.json.JSONObject;

public abstract class b {
  public b$a a = null;
  
  public b$c b = null;
  
  public b$a c = null;
  
  public b$a d = null;
  
  protected int e = 0;
  
  protected JSONObject f;
  
  protected a g;
  
  private int h;
  
  private int i;
  
  private int j;
  
  private b$b k;
  
  public b(a parama) {
    this.g = parama;
    this.k = null;
  }
  
  protected abstract b$b a(Context paramContext);
  
  protected void a(a.a parama) {
    if (parama != null)
      this.h++; 
    this.i++;
    if (this.i == this.e && !a() && this.d != null)
      this.d.a(); 
  }
  
  protected void a(String paramString, e.b paramb) {
    a(paramString, paramb, false);
  }
  
  protected void a(String paramString, e.b paramb, boolean paramBoolean) {
    Bundle bundle = new Bundle();
    bundle.putBoolean("paramNoMemoryCache", paramBoolean);
    a(this.f, paramString, paramb, bundle);
  }
  
  public void a(JSONObject paramJSONObject) {
    this.i = 0;
    this.j = 0;
    this.h = 0;
    this.f = paramJSONObject.optJSONObject("assets");
    if (this.f == null && this.d != null)
      this.d.a(); 
  }
  
  protected void a(JSONObject paramJSONObject, String paramString, e.b paramb, Bundle paramBundle) {
    JSONObject jSONObject = paramJSONObject.optJSONObject(paramString);
    if (jSONObject != null) {
      this.j++;
      String str1 = jSONObject.optString("url");
      String str2 = jSONObject.optString("checksum");
      e.a().a(str1, str2, paramb, null, paramBundle);
      return;
    } 
    a((a.a)null);
  }
  
  public boolean a() {
    if (this.h != this.j)
      return false; 
    if (this.c != null)
      this.c.a(); 
    return true;
  }
  
  public boolean b() {
    boolean bool = false;
    if (this.g.c == a.b.b) {
      Chartboost.sharedChartboost().a(this.g);
      Activity activity = Chartboost.sharedChartboost().b();
      if (activity == null) {
        this.k = null;
        return bool;
      } 
      this.k = a((Context)activity);
      if (this.k.a(activity))
        return true; 
      this.k = null;
    } 
    return bool;
  }
  
  public void c() {
    e();
    this.c = null;
    this.d = null;
    this.b = null;
    this.a = null;
    this.f = null;
  }
  
  public b$b d() {
    return this.k;
  }
  
  public void e() {
    if (this.k != null)
      this.k.b(); 
    this.k = null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */