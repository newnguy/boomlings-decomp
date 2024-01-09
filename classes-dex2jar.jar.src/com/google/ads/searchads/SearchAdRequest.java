package com.google.ads.searchads;

import android.content.Context;
import android.graphics.Color;
import com.google.ads.AdRequest;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobAdapterExtras;
import java.util.Locale;
import java.util.Map;

public class SearchAdRequest extends AdRequest {
  private String b;
  
  private int c;
  
  private int d;
  
  private int e;
  
  private int f;
  
  private int g;
  
  private int h;
  
  private String i;
  
  private int j;
  
  private int k;
  
  private SearchAdRequest$BorderType l;
  
  private int m;
  
  private String n;
  
  private String a(int paramInt) {
    return String.format(Locale.US, "#%06x", new Object[] { Integer.valueOf(0xFFFFFF & paramInt) });
  }
  
  public Map a(Context paramContext) {
    AdMobAdapterExtras adMobAdapterExtras2 = (AdMobAdapterExtras)a(AdMobAdapterExtras.class);
    AdMobAdapterExtras adMobAdapterExtras1 = adMobAdapterExtras2;
    if (adMobAdapterExtras2 == null) {
      adMobAdapterExtras1 = new AdMobAdapterExtras();
      a((NetworkExtras)adMobAdapterExtras1);
    } 
    if (this.b != null)
      adMobAdapterExtras1.f().put("q", this.b); 
    if (Color.alpha(this.c) != 0)
      adMobAdapterExtras1.f().put("bgcolor", a(this.c)); 
    if (Color.alpha(this.d) == 255 && Color.alpha(this.e) == 255) {
      adMobAdapterExtras1.f().put("gradientfrom", a(this.d));
      adMobAdapterExtras1.f().put("gradientto", a(this.e));
    } 
    if (Color.alpha(this.f) != 0)
      adMobAdapterExtras1.f().put("hcolor", a(this.f)); 
    if (Color.alpha(this.g) != 0)
      adMobAdapterExtras1.f().put("dcolor", a(this.g)); 
    if (Color.alpha(this.h) != 0)
      adMobAdapterExtras1.f().put("acolor", a(this.h)); 
    if (this.i != null)
      adMobAdapterExtras1.f().put("font", this.i); 
    adMobAdapterExtras1.f().put("headersize", Integer.toString(this.j));
    if (Color.alpha(this.k) != 0)
      adMobAdapterExtras1.f().put("bcolor", a(this.k)); 
    if (this.l != null)
      adMobAdapterExtras1.f().put("btype", this.l.toString()); 
    adMobAdapterExtras1.f().put("bthick", Integer.toString(this.m));
    if (this.n != null)
      adMobAdapterExtras1.f().put("channel", this.n); 
    return super.a(paramContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\searchads\SearchAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */