package com.google.ads;

import android.app.Activity;
import android.content.Context;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.util.b;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

class aw implements Runnable {
  private final h a;
  
  private final String b;
  
  private final AdRequest c;
  
  private final HashMap d;
  
  private final boolean e;
  
  private final WeakReference f;
  
  public aw(h paramh, Activity paramActivity, String paramString, AdRequest paramAdRequest, HashMap<?, ?> paramHashMap) {
    this.a = paramh;
    this.b = paramString;
    this.f = new WeakReference<Activity>(paramActivity);
    this.c = paramAdRequest;
    this.d = new HashMap<Object, Object>(paramHashMap);
    this.e = a(this.d);
  }
  
  private void a(MediationAdapter paramMediationAdapter) {
    Activity activity = this.f.get();
    if (activity == null)
      throw new ax("Activity became null while trying to instantiate adapter."); 
    this.a.a(paramMediationAdapter);
    Class<MediationServerParameters> clazz = paramMediationAdapter.c();
    if (clazz != null) {
      MediationServerParameters mediationServerParameters = clazz.newInstance();
      mediationServerParameters.a(this.d);
    } else {
      clazz = null;
    } 
    Class clazz1 = paramMediationAdapter.b();
    if (clazz1 != null) {
      NetworkExtras networkExtras = (NetworkExtras)this.c.a(clazz1);
    } else {
      clazz1 = null;
    } 
    MediationAdRequest mediationAdRequest = new MediationAdRequest(this.c, (Context)activity, this.e);
    if (this.a.a.a()) {
      if (!(paramMediationAdapter instanceof MediationInterstitialAdapter))
        throw new ax("Adapter " + this.b + " doesn't support the MediationInterstitialAdapter" + " interface."); 
      ((MediationInterstitialAdapter)paramMediationAdapter).a(new az(this.a), activity, (MediationServerParameters)clazz, mediationAdRequest, (NetworkExtras)clazz1);
    } else {
      if (!(paramMediationAdapter instanceof MediationBannerAdapter))
        throw new ax("Adapter " + this.b + " doesn't support the MediationBannerAdapter interface"); 
      ((MediationBannerAdapter)paramMediationAdapter).a(new ay(this.a), activity, (MediationServerParameters)clazz, this.a.a.b(), mediationAdRequest, (NetworkExtras)clazz1);
    } 
    this.a.k();
  }
  
  private void a(String paramString, Throwable paramThrowable, g$a paramg$a) {
    b.b(paramString, paramThrowable);
    this.a.a(false, paramg$a);
  }
  
  private static boolean a(Map paramMap) {
    String str = (String)paramMap.remove("gwhirl_share_location");
    if ("1".equals(str))
      return true; 
    if (str != null && !"0".equals(str))
      b.b("Received an illegal value, '" + str + "', for the special share location parameter from mediation server" + " (expected '0' or '1'). Will not share the location."); 
    return false;
  }
  
  public void run() {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      b.a(stringBuilder.append("Trying to instantiate: ").append(this.b).toString());
      a((MediationAdapter)g.a(this.b, MediationAdapter.class));
    } catch (ClassNotFoundException classNotFoundException) {
      a("Cannot find adapter class '" + this.b + "'. Did you link the ad network's mediation adapter? Skipping ad network.", classNotFoundException, g$a.e);
    } catch (Throwable throwable) {
      a("Error while creating adapter and loading ad from ad network. Skipping ad network.", throwable, g$a.f);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\aw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */