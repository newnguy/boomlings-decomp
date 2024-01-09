package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import com.chartboost.sdk.impl.a;
import com.chartboost.sdk.impl.k;
import org.json.JSONException;
import org.json.JSONObject;

class Chartboost$2 implements a.a {
  final Chartboost a;
  
  Chartboost$2(Chartboost paramChartboost) {}
  
  private void a(JSONObject paramJSONObject, String paramString, k paramk) {
    if (paramJSONObject != null)
      try {
        if (paramJSONObject.getString(paramString) != null)
          paramk.a(paramString, paramJSONObject.optString(paramString)); 
      } catch (JSONException jSONException) {} 
  }
  
  public void a(a parama) {
    if (parama.c == a.b.b) {
      parama.c = a.b.a;
      if (parama.d == a.c.b) {
        Chartboost.a(this.a, parama);
        return;
      } 
      if (parama.d == a.c.c)
        Chartboost.b(this.a, parama); 
      return;
    } 
    if (parama.c == a.b.e) {
      if (parama.d == a.c.b && parama.e != null) {
        Chartboost.b(this.a).put(parama.e, parama);
        if (this.a.getDelegate() != null)
          this.a.getDelegate().didCacheInterstitial(parama.e); 
      } else if (parama.d == a.c.c) {
        if (this.a.getDelegate() != null)
          this.a.getDelegate().didCacheMoreApps(); 
        if (Chartboost.c(this.a) != null)
          Chartboost.c(this.a, null); 
        Chartboost.c(this.a, parama);
      } 
      parama.c = a.b.f;
    } 
  }
  
  public void a(a parama, String paramString, JSONObject paramJSONObject) {
    boolean bool1;
    Activity activity;
    boolean bool2 = false;
    boolean bool3 = false;
    Chartboost.d(this.a, null);
    if (paramString != null && !paramString.equals("") && !paramString.equals("null")) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (parama.d == a.c.b) {
      if (this.a.getDelegate() != null)
        this.a.getDelegate().didDismissInterstitial(parama.e); 
      if (this.a.getDelegate() != null)
        this.a.getDelegate().didClickInterstitial(parama.e); 
      if (parama.c == a.b.c) {
        a a1 = Chartboost.a(this.a);
        if (a1 != null) {
          if (bool1) {
            bool2 = bool3;
          } else {
            bool2 = true;
          } 
          a1.a(parama, bool2);
        } 
      } 
    } else if (parama.d == a.c.c) {
      if (this.a.getDelegate() != null)
        this.a.getDelegate().didDismissMoreApps(); 
      if (this.a.getDelegate() != null)
        this.a.getDelegate().didClickMoreApps(); 
      if (parama.c == a.b.c) {
        a a1 = Chartboost.a(this.a);
        if (a1 != null) {
          if (!bool1)
            bool2 = true; 
          a1.a(parama, bool2);
        } 
      } 
    } 
    k k = new k("api", "click");
    if (Chartboost.d(this.a) == null) {
      activity = this.a.b();
    } else {
      activity = Chartboost.d(this.a);
    } 
    k.a((Context)activity);
    a(parama.a, "to", k);
    a(parama.a, "cgn", k);
    a(parama.a, "creative", k);
    a(parama.a, "ad_id", k);
    a(paramJSONObject, "cgn", k);
    a(paramJSONObject, "creative", k);
    a(paramJSONObject, "type", k);
    a(paramJSONObject, "more_type", k);
    k.c(this.a.getAppID(), this.a.getAppSignature());
    if (bool1) {
      k.h = new Chartboost$2$1(this, paramString);
      this.a.a(new a$a(true, null));
    } 
    Chartboost.e(this.a).a(k);
  }
  
  public void b(a parama) {
    Chartboost.d(this.a, null);
    if (parama.d == a.c.b) {
      if (this.a.getDelegate() != null)
        this.a.getDelegate().didDismissInterstitial(parama.e); 
      if (this.a.getDelegate() != null)
        this.a.getDelegate().didCloseInterstitial(parama.e); 
      if (parama.c == a.b.c) {
        a a1 = Chartboost.a(this.a);
        if (a1 != null)
          a1.a(parama, true); 
      } 
      return;
    } 
    if (parama.d == a.c.c) {
      if (this.a.getDelegate() != null)
        this.a.getDelegate().didDismissMoreApps(); 
      if (this.a.getDelegate() != null)
        this.a.getDelegate().didCloseMoreApps(); 
      if (parama.c == a.b.c) {
        a a1 = Chartboost.a(this.a);
        if (a1 != null)
          a1.a(parama, true); 
      } 
    } 
  }
  
  public void c(a parama) {
    Chartboost chartboost = this.a;
    a.c c = parama.d;
    if (parama.d == a.c.b) {
      String str = parama.e;
    } else {
      parama = null;
    } 
    Chartboost.a(chartboost, c, (String)parama);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Chartboost$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */