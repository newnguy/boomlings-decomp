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

/* loaded from: classes.dex */
public class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter {
    private String a;
    private CustomEventBanner b = null;
    private a c = null;
    private CustomEventInterstitial d = null;

    private Object a(String str, Class cls, String str2) {
        try {
            return g.a(str, cls);
        } catch (ClassCastException e) {
            a("Make sure your custom event implements the " + cls.getName() + " interface.", e);
            return null;
        } catch (ClassNotFoundException e2) {
            a("Make sure you created a visible class named: " + str + ". ", e2);
            return null;
        } catch (IllegalAccessException e3) {
            a("Make sure the default constructor for class " + str + " is visible. ", e3);
            return null;
        } catch (InstantiationException e4) {
            a("Make sure the name " + str + " does not denote an abstract class or an interface.", e4);
            return null;
        } catch (Throwable th) {
            a("", th);
            return null;
        }
    }

    private void a(String str, Throwable th) {
        com.google.ads.util.b.b("Error during processing of custom event with label: '" + this.a + "'. Skipping custom event. " + str, th);
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public void a() {
        if (this.b != null) {
            this.b.a();
        }
        if (this.d != null) {
            this.d.a();
        }
    }

    @Override // com.google.ads.mediation.MediationBannerAdapter
    public void a(MediationBannerListener mediationBannerListener, Activity activity, CustomEventServerParameters customEventServerParameters, AdSize adSize, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        com.google.ads.util.a.a((Object) this.a);
        this.a = customEventServerParameters.a;
        String str = customEventServerParameters.b;
        String str2 = customEventServerParameters.c;
        this.b = (CustomEventBanner) a(str, CustomEventBanner.class, this.a);
        if (this.b == null) {
            mediationBannerListener.a(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        com.google.ads.util.a.a(this.c);
        this.c = new a(this, mediationBannerListener);
        try {
            this.b.a(this.c, activity, this.a, str2, adSize, mediationAdRequest, customEventExtras == null ? null : customEventExtras.a(this.a));
        } catch (Throwable th) {
            a("", th);
            mediationBannerListener.a(this, AdRequest.ErrorCode.INTERNAL_ERROR);
        }
    }

    @Override // com.google.ads.mediation.MediationInterstitialAdapter
    public void a(MediationInterstitialListener mediationInterstitialListener, Activity activity, CustomEventServerParameters customEventServerParameters, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        com.google.ads.util.a.a((Object) this.a);
        this.a = customEventServerParameters.a;
        String str = customEventServerParameters.b;
        String str2 = customEventServerParameters.c;
        this.d = (CustomEventInterstitial) a(str, CustomEventInterstitial.class, this.a);
        if (this.d == null) {
            mediationInterstitialListener.a(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        try {
            this.d.a(new b(this, mediationInterstitialListener), activity, this.a, str2, mediationAdRequest, customEventExtras == null ? null : customEventExtras.a(this.a));
        } catch (Throwable th) {
            a("", th);
            mediationInterstitialListener.a(this, AdRequest.ErrorCode.INTERNAL_ERROR);
        }
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public Class b() {
        return CustomEventExtras.class;
    }

    @Override // com.google.ads.mediation.MediationAdapter
    public Class c() {
        return CustomEventServerParameters.class;
    }

    @Override // com.google.ads.mediation.MediationBannerAdapter
    public View d() {
        com.google.ads.util.a.b(this.c);
        return this.c.a();
    }

    @Override // com.google.ads.mediation.MediationInterstitialAdapter
    public void e() {
        com.google.ads.util.a.b(this.d);
        try {
            this.d.b();
        } catch (Throwable th) {
            com.google.ads.util.b.b("Exception when showing custom event labeled '" + this.a + "'.", th);
        }
    }
}
