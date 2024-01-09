package com.google.ads;

import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import com.google.ads.internal.d;
import com.google.ads.internal.h;
import com.google.ads.util.a;
import com.google.ads.util.b;
import java.util.HashMap;
import java.util.List;

public class e {
  private final d a;
  
  private h b = null;
  
  private Object c = new Object();
  
  private Thread d = null;
  
  private Object e = new Object();
  
  private boolean f = false;
  
  private Object g = new Object();
  
  protected e() {
    this.a = null;
  }
  
  public e(d paramd) {
    a.b(paramd);
    this.a = paramd;
  }
  
  public static boolean a(c paramc, d paramd) {
    if (paramc.j() == null)
      return true; 
    if (paramd.h().b()) {
      if (!paramc.j().a()) {
        b.e("InterstitialAd received a mediation response corresponding to a non-interstitial ad. Make sure you specify 'interstitial' as the ad-type in the mediation UI.");
        return false;
      } 
      return true;
    } 
    AdSize adSize2 = ((h)(paramd.h()).k.a()).b();
    if (paramc.j().a()) {
      b.e("AdView received a mediation response corresponding to an interstitial ad. Make sure you specify the banner ad size corresponding to the AdSize you used in your AdView  (" + adSize2 + ") in the ad-type field in the mediation UI.");
      return false;
    } 
    AdSize adSize1 = paramc.j().b();
    if (adSize1 != adSize2) {
      b.e("Mediation server returned ad size: '" + adSize1 + "', while the AdView was created with ad size: '" + adSize2 + "'. Using the ad-size passed to the AdView on creation.");
      return false;
    } 
    return true;
  }
  
  private boolean a(h paramh, String paramString) {
    if (e() != paramh) {
      b.c("GWController: ignoring callback to " + paramString + " from non showing ambassador with adapter class: '" + paramh.h() + "'.");
      return false;
    } 
    return true;
  }
  
  private boolean a(String paramString, Activity paramActivity, AdRequest paramAdRequest, f paramf, HashMap paramHashMap, long paramLong) {
    synchronized (new h(this, (h)(this.a.h()).k.a(), paramf, paramString, paramAdRequest, paramHashMap)) {
      null.a(paramActivity);
      try {
        while (!null.c() && paramLong > 0L) {
          long l1 = SystemClock.elapsedRealtime();
          null.wait(paramLong);
          long l2 = SystemClock.elapsedRealtime();
          paramLong -= l2 - l1;
        } 
      } catch (InterruptedException interruptedException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        b.a(stringBuilder.append("Interrupted while waiting for ad network to load ad using adapter class: ").append(paramString).toString());
      } 
      this.a.m().a(null.e());
      if (null.c() && null.d()) {
        View view;
        if (this.a.h().b()) {
          paramString = null;
        } else {
          view = null.f();
        } 
        d d1 = this.a;
        at at = new at();
        this(this, null, view, paramf);
        d1.a(at);
        return true;
      } 
      null.b();
      return false;
    } 
  }
  
  private void b(c paramc, AdRequest paramAdRequest) {
    synchronized (this.e) {
      long l;
      a.a(Thread.currentThread(), this.d);
      null = paramc.f();
      if (paramc.a()) {
        l = paramc.b();
      } else {
        l = 10000L;
      } 
      for (a a : null) {
        b.a("Looking to fetch ads from network: " + a.b());
        List list = a.c();
        HashMap hashMap = a.e();
        null = a.d();
        str1 = a.a();
        String str3 = a.b();
        String str2 = paramc.c();
        if (null == null)
          null = paramc.g(); 
        null = new f(str1, str3, str2, (List)null, paramc.h(), paramc.i());
        for (String str1 : list) {
          Activity activity = (Activity)(this.a.h()).e.a();
          if (activity == null) {
            b.a("Activity is null while mediating.  Terminating mediation thread.");
            return;
          } 
          this.a.m().c();
          if (!a(str1, activity, paramAdRequest, (f)null, hashMap, l)) {
            if (d()) {
              b.a("GWController.destroy() called. Terminating mediation thread.");
              return;
            } 
            continue;
          } 
          return;
        } 
      } 
    } 
    this.a.a(new as(this, paramc));
  }
  
  private boolean d() {
    synchronized (this.g) {
      return this.f;
    } 
  }
  
  private h e() {
    synchronized (this.c) {
      return this.b;
    } 
  }
  
  private boolean e(h paramh) {
    synchronized (this.g) {
      if (d()) {
        paramh.b();
        return true;
      } 
      return false;
    } 
  }
  
  public void a(c paramc, AdRequest paramAdRequest) {
    synchronized (this.e) {
      if (a()) {
        b.c("Mediation thread is not done executing previous mediation  request. Ignoring new mediation request");
        return;
      } 
      a(paramc, this.a);
      Thread thread = new Thread();
      am am = new am();
      this(this, paramc, paramAdRequest);
      this(am);
      this.d = thread;
      this.d.start();
      return;
    } 
  }
  
  public void a(h paramh) {
    if (a(paramh, "onPresentScreen"))
      this.a.a(new ap(this)); 
  }
  
  public void a(h paramh, View paramView) {
    if (e() != paramh) {
      b.c("GWController: ignoring onAdRefreshed() callback from non-showing ambassador (adapter class name is '" + paramh.h() + "').");
      return;
    } 
    this.a.m().a(g$a.a);
    f f = this.b.a();
    this.a.a(new ao(this, paramView, f));
  }
  
  public void a(h paramh, boolean paramBoolean) {
    if (a(paramh, "onAdClicked()")) {
      f f = paramh.a();
      this.a.a(new an(this, f, paramBoolean));
    } 
  }
  
  public boolean a() {
    synchronized (this.e) {
      if (this.d != null)
        return true; 
      return false;
    } 
  }
  
  public void b() {
    synchronized (this.g) {
      this.f = true;
      d(null);
      synchronized (this.e) {
        if (this.d != null)
          this.d.interrupt(); 
        return;
      } 
    } 
  }
  
  public void b(h paramh) {
    if (a(paramh, "onDismissScreen"))
      this.a.a(new aq(this)); 
  }
  
  public void c(h paramh) {
    if (a(paramh, "onLeaveApplication"))
      this.a.a(new ar(this)); 
  }
  
  public boolean c() {
    a.a(this.a.h().b());
    h h1 = e();
    if (h1 != null) {
      h1.g();
      return true;
    } 
    b.b("There is no ad ready to show.");
    return false;
  }
  
  public void d(h paramh) {
    synchronized (this.c) {
      if (this.b != paramh) {
        if (this.b != null)
          this.b.b(); 
        this.b = paramh;
      } 
      return;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */