package com.google.ads;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.google.ads.doubleclick.DfpExtras;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobAdapterExtras;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdRequest {
  public static final String a;
  
  private static final SimpleDateFormat b = new SimpleDateFormat("yyyyMMdd");
  
  private static Method c = null;
  
  private static Method d = null;
  
  private AdRequest$Gender e = null;
  
  private Date f = null;
  
  private Set g = null;
  
  private Map h = null;
  
  private final Map i = new HashMap<Object, Object>();
  
  private Location j = null;
  
  private boolean k = false;
  
  private boolean l = false;
  
  private Set m = null;
  
  static {
    try {
      for (Method method : Class.forName("com.google.analytics.tracking.android.AdMobInfo").getMethods()) {
        if (method.getName().equals("getInstance") && (method.getParameterTypes()).length == 0) {
          c = method;
        } else if (method.getName().equals("getJoinIds") && (method.getParameterTypes()).length == 0) {
          d = method;
        } 
      } 
      if (c == null || d == null) {
        c = null;
        d = null;
        b.e("No Google Analytics: Library Incompatible.");
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      b.a("No Google Analytics: Library Not Found.");
    } catch (Throwable throwable) {
      b.a("No Google Analytics: Error Loading Library");
    } 
    a = AdUtil.b("emulator");
  }
  
  public AdRequest$Gender a() {
    return this.e;
  }
  
  public AdRequest a(Location paramLocation) {
    this.j = paramLocation;
    return this;
  }
  
  public AdRequest a(AdRequest$Gender paramAdRequest$Gender) {
    this.e = paramAdRequest$Gender;
    return this;
  }
  
  public AdRequest a(NetworkExtras paramNetworkExtras) {
    if (paramNetworkExtras != null)
      this.i.put(paramNetworkExtras.getClass(), paramNetworkExtras); 
    return this;
  }
  
  public AdRequest a(String paramString) {
    if (this.m == null)
      this.m = new HashSet(); 
    this.m.add(paramString);
    return this;
  }
  
  public AdRequest a(Date paramDate) {
    if (paramDate == null) {
      this.f = null;
      return this;
    } 
    this.f = new Date(paramDate.getTime());
    return this;
  }
  
  public AdRequest a(Set paramSet) {
    this.g = paramSet;
    return this;
  }
  
  public Object a(Class paramClass) {
    return this.i.get(paramClass);
  }
  
  public Map a(Context paramContext) {
    boolean bool = true;
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    if (this.g != null)
      hashMap.put("kw", this.g); 
    if (this.e != null)
      hashMap.put("cust_gender", Integer.valueOf(this.e.ordinal())); 
    if (this.f != null)
      hashMap.put("cust_age", b.format(this.f)); 
    if (this.j != null)
      hashMap.put("uule", AdUtil.a(this.j)); 
    if (this.k)
      hashMap.put("testing", Integer.valueOf(1)); 
    if (b(paramContext)) {
      hashMap.put("adtest", "on");
    } else if (!this.l) {
      String str;
      if (AdUtil.c()) {
        str = "AdRequest.TEST_EMULATOR";
      } else {
        str = "\"" + AdUtil.a(paramContext) + "\"";
      } 
      b.c("To get test ads on this device, call adRequest.addTestDevice(" + str + ");");
      this.l = true;
    } 
    AdMobAdapterExtras adMobAdapterExtras = (AdMobAdapterExtras)a(AdMobAdapterExtras.class);
    if (adMobAdapterExtras != null && adMobAdapterExtras.d()) {
      hashMap.put("pto", Integer.valueOf(1));
    } else {
      if (!ah.a(paramContext))
        bool = false; 
      hashMap.put("cipa", Integer.valueOf(bool));
    } 
    DfpExtras dfpExtras = (DfpExtras)a(DfpExtras.class);
    if (dfpExtras != null && dfpExtras.f() != null && !dfpExtras.f().isEmpty()) {
      hashMap.put("extras", dfpExtras.f());
    } else if (adMobAdapterExtras != null && adMobAdapterExtras.f() != null && !adMobAdapterExtras.f().isEmpty()) {
      hashMap.put("extras", adMobAdapterExtras.f());
    } 
    if (dfpExtras != null) {
      String str = dfpExtras.a();
      if (!TextUtils.isEmpty(str))
        hashMap.put("ppid", str); 
    } 
    if (this.h != null)
      hashMap.put("mediation_extras", this.h); 
    try {
      if (c != null) {
        Object object = c.invoke(null, new Object[0]);
        object = d.invoke(object, new Object[0]);
        if (object != null && object.size() > 0)
          hashMap.put("analytics_join_id", object); 
      } 
    } catch (Throwable throwable) {
      b.c("Internal Analytics Error:", throwable);
    } 
    return hashMap;
  }
  
  public AdRequest b(Set paramSet) {
    this.m = paramSet;
    return this;
  }
  
  public Date b() {
    return this.f;
  }
  
  public boolean b(Context paramContext) {
    String str;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.m != null) {
      str = AdUtil.a(paramContext);
      if (str == null)
        return bool2; 
    } else {
      return bool1;
    } 
    bool1 = bool2;
    if (this.m.contains(str))
      bool1 = true; 
    return bool1;
  }
  
  public Set c() {
    return (this.g == null) ? null : Collections.unmodifiableSet(this.g);
  }
  
  public Location d() {
    return this.j;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\AdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */