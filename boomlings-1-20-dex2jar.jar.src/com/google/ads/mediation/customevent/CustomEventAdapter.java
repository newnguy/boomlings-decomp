package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.g;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.util.a;
import com.google.ads.util.b;

public class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {
  private String a;
  
  private CustomEventBanner b = null;
  
  private a c = null;
  
  private CustomEventInterstitial d = null;
  
  private Object a(String paramString1, Class paramClass, String paramString2) {
    try {
      return g.a(paramString1, paramClass);
    } catch (ClassNotFoundException classNotFoundException) {
      a("Make sure you created a visible class named: " + paramString1 + ". ", classNotFoundException);
    } catch (ClassCastException null) {
      a("Make sure your custom event implements the " + classNotFoundException.getName() + " interface.", null);
    } catch (IllegalAccessException illegalAccessException) {
      a("Make sure the default constructor for class " + null + " is visible. ", illegalAccessException);
    } catch (InstantiationException instantiationException) {
      a("Make sure the name " + null + " does not denote an abstract class or an interface.", instantiationException);
    } catch (Throwable throwable) {
      a("", throwable);
    } 
    return null;
  }
  
  private void a(String paramString, Throwable paramThrowable) {
    b.b("Error during processing of custom event with label: '" + this.a + "'. Skipping custom event. " + paramString, paramThrowable);
  }
  
  public void a() {
    if (this.b != null)
      this.b.a(); 
    if (this.d != null)
      this.d.a(); 
  }
  
  public void a(MediationBannerListener paramMediationBannerListener, Activity paramActivity, CustomEventServerParameters paramCustomEventServerParameters, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, CustomEventExtras paramCustomEventExtras) {
    a.a(this.a);
    this.a = paramCustomEventServerParameters.a;
    String str2 = paramCustomEventServerParameters.b;
    String str1 = paramCustomEventServerParameters.c;
    this.b = (CustomEventBanner)a(str2, CustomEventBanner.class, this.a);
    if (this.b == null) {
      paramMediationBannerListener.a(this, AdRequest.ErrorCode.d);
      return;
    } 
    a.a(this.c);
    this.c = new a(this, paramMediationBannerListener);
    try {
      Object object;
      CustomEventBanner customEventBanner = this.b;
      a a1 = this.c;
      String str = this.a;
      if (paramCustomEventExtras == null) {
        paramCustomEventServerParameters = null;
      } else {
        object = paramCustomEventExtras.a(this.a);
      } 
      customEventBanner.a(a1, paramActivity, str, str1, paramAdSize, paramMediationAdRequest, object);
    } catch (Throwable throwable) {
      a("", throwable);
      paramMediationBannerListener.a(this, AdRequest.ErrorCode.d);
    } 
  }
  
  public void a(MediationInterstitialListener paramMediationInterstitialListener, Activity paramActivity, CustomEventServerParameters paramCustomEventServerParameters, MediationAdRequest paramMediationAdRequest, CustomEventExtras paramCustomEventExtras) {
    a.a(this.a);
    this.a = paramCustomEventServerParameters.a;
    String str2 = paramCustomEventServerParameters.b;
    String str1 = paramCustomEventServerParameters.c;
    this.d = (CustomEventInterstitial)a(str2, CustomEventInterstitial.class, this.a);
    if (this.d == null) {
      paramMediationInterstitialListener.a(this, AdRequest.ErrorCode.d);
      return;
    } 
    try {
      Object object;
      CustomEventInterstitial customEventInterstitial = this.d;
      b b = new b();
      this(this, paramMediationInterstitialListener);
      str2 = this.a;
      if (paramCustomEventExtras == null) {
        paramCustomEventServerParameters = null;
      } else {
        object = paramCustomEventExtras.a(this.a);
      } 
      customEventInterstitial.a(b, paramActivity, str2, str1, paramMediationAdRequest, object);
    } catch (Throwable throwable) {
      a("", throwable);
      paramMediationInterstitialListener.a(this, AdRequest.ErrorCode.d);
    } 
  }
  
  public Class b() {
    return CustomEventExtras.class;
  }
  
  public Class c() {
    return CustomEventServerParameters.class;
  }
  
  public View d() {
    a.b(this.c);
    return this.c.a();
  }
  
  public void e() {
    a.b(this.d);
    try {
      this.d.b();
    } catch (Throwable throwable) {
      b.b("Exception when showing custom event labeled '" + this.a + "'.", throwable);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\mediation\customevent\CustomEventAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */