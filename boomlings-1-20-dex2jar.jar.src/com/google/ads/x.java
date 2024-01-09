package com.google.ads;

import android.text.TextUtils;
import android.webkit.WebView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.d;
import com.google.ads.internal.h;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import com.google.ads.util.g;
import com.google.ads.util.i;
import java.util.HashMap;

public class x implements n {
  private void a(HashMap paramHashMap, String paramString, i.c paramc) {
    try {
      String str = (String)paramHashMap.get(paramString);
      if (!TextUtils.isEmpty(str))
        paramc.a(Integer.valueOf(str)); 
    } catch (NumberFormatException numberFormatException) {
      b.a("Could not parse \"" + paramString + "\" constant.");
    } 
  }
  
  private void b(HashMap paramHashMap, String paramString, i.c paramc) {
    try {
      String str = (String)paramHashMap.get(paramString);
      if (!TextUtils.isEmpty(str)) {
        Long long_ = new Long();
        this(str);
        paramc.a(long_);
      } 
    } catch (NumberFormatException numberFormatException) {
      b.a("Could not parse \"" + paramString + "\" constant.");
    } 
  }
  
  private void c(HashMap paramHashMap, String paramString, i.c paramc) {
    String str = (String)paramHashMap.get(paramString);
    if (!TextUtils.isEmpty(str))
      paramc.a(str); 
  }
  
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    m m = paramd.h();
    l$a l$a = (l$a)((l)m.a.a()).a.a();
    a(paramHashMap, "min_hwa_banner", l$a.a);
    a(paramHashMap, "min_hwa_overlay", l$a.b);
    c(paramHashMap, "mraid_banner_path", l$a.c);
    c(paramHashMap, "mraid_expanded_banner_path", l$a.d);
    c(paramHashMap, "mraid_interstitial_path", l$a.e);
    b(paramHashMap, "ac_max_size", l$a.f);
    b(paramHashMap, "ac_padding", l$a.g);
    b(paramHashMap, "ac_total_quota", l$a.h);
    b(paramHashMap, "db_total_quota", l$a.i);
    b(paramHashMap, "db_quota_per_origin", l$a.j);
    b(paramHashMap, "db_quota_step_size", l$a.k);
    AdWebView adWebView = paramd.k();
    if (AdUtil.a >= 11) {
      g.a(adWebView.getSettings(), m);
      g.a(paramWebView.getSettings(), m);
    } 
    if (!((h)m.k.a()).a()) {
      boolean bool;
      boolean bool1 = adWebView.f();
      if (AdUtil.a < ((Integer)l$a.a.a()).intValue()) {
        bool = true;
      } else {
        bool = false;
      } 
      if (!bool && bool1) {
        b.a("Re-enabling hardware acceleration for a banner after reading constants.");
        adWebView.c();
      } else if (bool && !bool1) {
        b.a("Disabling hardware acceleration for a banner after reading constants.");
        adWebView.b();
      } 
    } 
    l$a.l.a(Boolean.valueOf(true));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */