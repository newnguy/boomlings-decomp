package com.chartboost.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.impl.a;
import com.chartboost.sdk.impl.j;
import com.chartboost.sdk.impl.k;
import com.chartboost.sdk.impl.l;
import com.chartboost.sdk.impl.m;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public final class Chartboost {
  private static Chartboost b = null;
  
  private m.a A = new Chartboost$1(this);
  
  private a.a B = new Chartboost$2(this);
  
  private j.c C = new Chartboost$3(this);
  
  protected Handler a = new Handler();
  
  private a c;
  
  private m d = new m(this.A);
  
  private Context e = null;
  
  private Activity f = null;
  
  private CBImpressionActivity g = null;
  
  private j h = new j(null, this.C, null);
  
  private List i = new ArrayList();
  
  private Map j = new HashMap<Object, Object>();
  
  private a k = null;
  
  private a l;
  
  private String m;
  
  private String n;
  
  private ChartboostDelegate o;
  
  private int p = 30000;
  
  private boolean q = false;
  
  private CBOrientation r;
  
  private boolean s;
  
  private boolean t = false;
  
  private Map u = new HashMap<Object, Object>();
  
  private Map v = new HashMap<Object, Object>();
  
  private boolean w = false;
  
  private long x = 0L;
  
  private boolean y = false;
  
  private Runnable z = new Chartboost$a(this, null);
  
  private void a(Activity paramActivity, boolean paramBoolean) {
    if (paramActivity != null) {
      int i = paramActivity.hashCode();
      this.u.put(Integer.valueOf(i), Boolean.valueOf(paramBoolean));
    } 
  }
  
  private void a(a.c paramc, String paramString) {
    a a1 = e();
    if (paramc == a.c.c && a1 != null && a1.a())
      a1.a(true); 
    if (paramc == a.c.b && getDelegate() != null)
      getDelegate().didFailToLoadInterstitial(paramString); 
    if (paramc == a.c.c && getDelegate() != null)
      getDelegate().didFailToLoadMoreApps(); 
  }
  
  private void a(String paramString, boolean paramBoolean) {
    if (getDelegate() == null || getDelegate().shouldRequestInterstitial(paramString)) {
      if (!l.a()) {
        if (getDelegate() != null)
          getDelegate().didFailToLoadInterstitial(paramString); 
        return;
      } 
      k k = new k("api", "get");
      k.a((Context)this.f);
      k.a("location", paramString);
      if (paramBoolean)
        k.a("cache", "1"); 
      k.c(getAppID(), getAppSignature());
      k.h = new Chartboost$5(this, paramBoolean, paramString);
      this.h.a(k);
    } 
  }
  
  private void a(JSONObject paramJSONObject, a.c paramc, boolean paramBoolean, String paramString) {
    boolean bool1;
    boolean bool3 = false;
    if (!paramJSONObject.optString("status", "").equals("200")) {
      a(paramc, paramString);
      return;
    } 
    if (getDelegate() != null && !getDelegate().shouldDisplayLoadingViewForMoreApps()) {
      bool1 = false;
    } else {
      bool1 = true;
    } 
    boolean bool2 = bool3;
    if (e() != null) {
      bool2 = bool3;
      if (e().a())
        bool2 = true; 
    } 
    if (paramc != a.c.c || paramBoolean || !bool1 || bool2) {
      a.b b;
      if (paramBoolean) {
        b = a.b.e;
      } else {
        b = a.b.b;
      } 
      new a(paramJSONObject, paramc, this.B, b, paramString);
    } 
  }
  
  private void a(JSONObject paramJSONObject, String paramString) {
    this.d.a(paramString, (Context)this.f);
  }
  
  private void a(boolean paramBoolean) {
    if (!l.a()) {
      if (getDelegate() != null)
        getDelegate().didFailToLoadMoreApps(); 
      return;
    } 
    if (!paramBoolean && (getDelegate() == null || getDelegate().shouldDisplayLoadingViewForMoreApps()))
      a(new a$a(true, null)); 
    k k = new k("api", "more");
    k.a((Context)this.f);
    if (paramBoolean)
      k.a("cache", "1"); 
    k.c(getAppID(), getAppSignature());
    k.h = new Chartboost$6(this, paramBoolean);
    this.h.a(k);
  }
  
  private void b(Activity paramActivity, boolean paramBoolean) {
    int i = paramActivity.hashCode();
    a a1 = (a)this.v.get(Integer.valueOf(i));
    if (a1 == null && paramBoolean) {
      a a2;
      if (this.c != null) {
        a1 = this.c;
        this.c = null;
        a1.a(paramActivity);
        a2 = a1;
      } else {
        a2 = new a(this, (Activity)a2);
      } 
      this.v.put(Integer.valueOf(i), a2);
      return;
    } 
    if (a1 != null && !paramBoolean)
      this.c = (a)this.v.remove(Integer.valueOf(i)); 
  }
  
  private void b(a parama) {
    if (parama.k || getDelegate() == null || getDelegate().shouldDisplayInterstitial(parama.e)) {
      if (parama.c == a.b.f && this.j.get(parama.e) == parama) {
        this.j.remove(parama.e);
        k k = new k("api", "show");
        k.a((Context)this.f);
        String str = parama.a.optString("ad_id");
        if (str != null)
          k.a("ad_id", str); 
        k.c(getAppID(), getAppSignature());
        this.h.a(k);
      } 
      parama.c = a.b.b;
      a(new a$a(parama));
    } 
  }
  
  private void c(Activity paramActivity) {
    this.f = paramActivity;
    this.e = paramActivity.getApplicationContext();
  }
  
  private void c(a parama) {
    boolean bool = true;
    if (parama.k || getDelegate() == null || getDelegate().shouldDisplayMoreApps()) {
      boolean bool1;
      if (parama == this.k)
        this.k = null; 
      if (parama.c == a.b.f) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      parama.c = a.b.a;
      boolean bool2 = bool;
      if (getDelegate() != null) {
        bool2 = bool;
        if (!getDelegate().shouldDisplayLoadingViewForMoreApps())
          bool2 = false; 
      } 
      a a1 = e();
      if (a1 != null)
        if (a1.a() || !bool2) {
          if (bool2)
            a1.a(false); 
        } else if (!bool1 && !parama.j) {
          return;
        }  
      parama.c = a.b.b;
      a(new a$a(parama));
    } 
  }
  
  private boolean d() {
    return d(this.f);
  }
  
  private boolean d(Activity paramActivity) {
    boolean bool = false;
    if (paramActivity != null) {
      Boolean bool1 = (Boolean)this.u.get(Integer.valueOf(paramActivity.hashCode()));
      if (bool1 != null)
        return bool1.booleanValue(); 
      bool = false;
    } 
    return bool;
  }
  
  private a e() {
    return (b() == null) ? null : (a)this.v.get(Integer.valueOf(b().hashCode()));
  }
  
  private void f() {
    if (this.l != null)
      this.B.b(this.l); 
  }
  
  public static Chartboost sharedChartboost() {
    // Byte code:
    //   0: ldc com/chartboost/sdk/Chartboost
    //   2: monitorenter
    //   3: getstatic com/chartboost/sdk/Chartboost.b : Lcom/chartboost/sdk/Chartboost;
    //   6: ifnonnull -> 21
    //   9: new com/chartboost/sdk/Chartboost
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/chartboost/sdk/Chartboost.b : Lcom/chartboost/sdk/Chartboost;
    //   21: getstatic com/chartboost/sdk/Chartboost.b : Lcom/chartboost/sdk/Chartboost;
    //   24: astore_0
    //   25: ldc com/chartboost/sdk/Chartboost
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/chartboost/sdk/Chartboost
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  protected void a(Activity paramActivity) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   5: putfield e : Landroid/content/Context;
    //   8: aload_1
    //   9: instanceof com/chartboost/sdk/CBImpressionActivity
    //   12: ifne -> 53
    //   15: aload_0
    //   16: aload_1
    //   17: putfield f : Landroid/app/Activity;
    //   20: aload_0
    //   21: aload_0
    //   22: getfield f : Landroid/app/Activity;
    //   25: iconst_1
    //   26: invokespecial a : (Landroid/app/Activity;Z)V
    //   29: aload_0
    //   30: getfield a : Landroid/os/Handler;
    //   33: aload_0
    //   34: getfield z : Ljava/lang/Runnable;
    //   37: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)V
    //   40: aload_1
    //   41: ifnull -> 52
    //   44: aload_1
    //   45: aload_0
    //   46: invokevirtual b : ()Landroid/app/Activity;
    //   49: if_acmpeq -> 64
    //   52: return
    //   53: aload_0
    //   54: aload_1
    //   55: checkcast com/chartboost/sdk/CBImpressionActivity
    //   58: putfield g : Lcom/chartboost/sdk/CBImpressionActivity;
    //   61: goto -> 29
    //   64: aload_0
    //   65: aload_1
    //   66: iconst_1
    //   67: invokespecial b : (Landroid/app/Activity;Z)V
    //   70: aload_1
    //   71: instanceof com/chartboost/sdk/CBImpressionActivity
    //   74: ifeq -> 115
    //   77: aload_0
    //   78: invokespecial e : ()Lcom/chartboost/sdk/a;
    //   81: astore_1
    //   82: aload_1
    //   83: ifnull -> 110
    //   86: iconst_0
    //   87: istore_2
    //   88: iload_2
    //   89: aload_0
    //   90: getfield i : Ljava/util/List;
    //   93: invokeinterface size : ()I
    //   98: if_icmplt -> 187
    //   101: aload_0
    //   102: getfield i : Ljava/util/List;
    //   105: invokeinterface clear : ()V
    //   110: aload_0
    //   111: iconst_0
    //   112: putfield y : Z
    //   115: aload_0
    //   116: getfield w : Z
    //   119: ifeq -> 210
    //   122: aload_0
    //   123: iconst_0
    //   124: putfield w : Z
    //   127: iconst_1
    //   128: istore_2
    //   129: iload_2
    //   130: istore_3
    //   131: aload_0
    //   132: getfield l : Lcom/chartboost/sdk/impl/a;
    //   135: ifnull -> 167
    //   138: iload_2
    //   139: istore_3
    //   140: aload_0
    //   141: getfield l : Lcom/chartboost/sdk/impl/a;
    //   144: getfield c : Lcom/chartboost/sdk/impl/a$b;
    //   147: getstatic com/chartboost/sdk/impl/a$b.b : Lcom/chartboost/sdk/impl/a$b;
    //   150: if_acmpne -> 167
    //   153: iload_2
    //   154: istore_3
    //   155: aload_0
    //   156: getfield l : Lcom/chartboost/sdk/impl/a;
    //   159: invokevirtual a : ()Z
    //   162: ifeq -> 167
    //   165: iconst_0
    //   166: istore_3
    //   167: iload_3
    //   168: ifeq -> 52
    //   171: aload_0
    //   172: new com/chartboost/sdk/a$a
    //   175: dup
    //   176: iconst_1
    //   177: aconst_null
    //   178: invokespecial <init> : (ZLcom/chartboost/sdk/impl/a;)V
    //   181: invokevirtual a : (Lcom/chartboost/sdk/a$a;)V
    //   184: goto -> 52
    //   187: aload_1
    //   188: aload_0
    //   189: getfield i : Ljava/util/List;
    //   192: iload_2
    //   193: invokeinterface get : (I)Ljava/lang/Object;
    //   198: checkcast com/chartboost/sdk/a$a
    //   201: invokevirtual a : (Lcom/chartboost/sdk/a$a;)V
    //   204: iinc #2, 1
    //   207: goto -> 88
    //   210: iconst_0
    //   211: istore_2
    //   212: goto -> 129
  }
  
  protected void a(CBImpressionActivity paramCBImpressionActivity) {
    if (!this.t) {
      this.e = paramCBImpressionActivity.getApplicationContext();
      this.g = paramCBImpressionActivity;
      this.t = true;
    } 
    this.a.removeCallbacks(this.z);
  }
  
  protected void a(a$a parama$a) {
    boolean bool = true;
    if (getImpressionsUseActivities()) {
      a a2 = e();
      if (b() != null && this.t && a2 != null) {
        a2.a(parama$a);
        return;
      } 
      if (d()) {
        boolean bool1;
        boolean bool2;
        this.i.add(parama$a);
        Intent intent = new Intent((Context)this.f, CBImpressionActivity.class);
        if (((this.f.getWindow().getAttributes()).flags & 0x400) != 0) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        if (((this.f.getWindow().getAttributes()).flags & 0x800) != 0) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        if (!bool1 || bool2)
          bool = false; 
        intent.putExtra("paramFullscreen", bool);
        try {
          this.f.startActivity(intent);
          this.y = true;
          return;
        } catch (ActivityNotFoundException activityNotFoundException) {
          throw new RuntimeException("Chartboost impression activity not declared in manifest. Please add the following inside your manifest's <application> tag: \n<activity android:name=\"com.chartboost.sdk.CBImpressionActivity\" android:theme=\"@android:style/Theme.Translucent.NoTitleBar\" android:excludeFromRecents=\"true\" />");
        } 
      } 
      return;
    } 
    a a1 = e();
    if (a1 != null)
      a1.a((a$a)activityNotFoundException); 
  }
  
  protected void a(a parama) {
    this.l = parama;
  }
  
  protected boolean a() {
    null = true;
    if (this.l != null && this.l.c == a.b.c) {
      f();
      return null;
    } 
    a a1 = e();
    if (a1 != null && a1.a()) {
      a1.a(true);
      return null;
    } 
    return false;
  }
  
  protected Activity b() {
    return this.q ? this.g : this.f;
  }
  
  protected void b(Activity paramActivity) {
    a a1 = e();
    if (paramActivity == b() && a1 != null) {
      b(paramActivity, false);
      this.w = false;
      if (a1.a()) {
        a1.a(false);
        this.w = true;
      } 
      if (this.l != null)
        a1.a(this.l); 
    } 
    if (!(paramActivity instanceof CBImpressionActivity))
      a(this.f, false); 
  }
  
  protected void c() {
    if (this.t) {
      this.e = this.f.getApplicationContext();
      this.g = null;
      this.t = false;
    } 
  }
  
  public void cacheInterstitial() {
    cacheInterstitial("Default");
  }
  
  public void cacheInterstitial(String paramString) {
    if (this.f == null)
      throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling cacheInterstitial()."); 
    if (getDelegate() == null || getDelegate().shouldRequestInterstitialsInFirstSession() || d.a().getInt("cbPrefSessionCount", 0) > 1)
      a(paramString, true); 
  }
  
  public void cacheMoreApps() {
    if (this.f == null)
      throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling cacheMoreApps()."); 
    a(true);
  }
  
  public void clearCache() {
    this.j = new HashMap<Object, Object>();
  }
  
  public void clearImageCache() {
    e.a().b();
  }
  
  public String getAppID() {
    return this.m;
  }
  
  public String getAppSignature() {
    return this.n;
  }
  
  public Context getContext() {
    return this.e;
  }
  
  public ChartboostDelegate getDelegate() {
    return this.o;
  }
  
  public CBOrientation.Difference getForcedOrientationDifference() {
    if (!this.s)
      return CBOrientation.Difference.ANGLE_0; 
    CBOrientation cBOrientation2 = d.c(getContext());
    CBOrientation cBOrientation1 = orientation();
    return (cBOrientation1 == CBOrientation.UNSPECIFIED || cBOrientation1 == cBOrientation2) ? CBOrientation.Difference.ANGLE_0 : ((cBOrientation1 == cBOrientation2.rotate90()) ? CBOrientation.Difference.ANGLE_90 : ((cBOrientation1 == cBOrientation2.rotate180()) ? CBOrientation.Difference.ANGLE_180 : CBOrientation.Difference.ANGLE_270));
  }
  
  public boolean getImpressionsUseActivities() {
    return this.q;
  }
  
  public int getTimeout() {
    return this.p;
  }
  
  public boolean hasCachedInterstitial() {
    return hasCachedInterstitial("Default");
  }
  
  public boolean hasCachedInterstitial(String paramString) {
    boolean bool = false;
    a a1 = (a)this.j.get(paramString);
    if (a1 != null) {
      if (TimeUnit.MILLISECONDS.toSeconds((new Date()).getTime() - a1.b.getTime()) < 86400L)
        return true; 
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasCachedMoreApps() {
    return (this.k != null);
  }
  
  public boolean isIdentityTrackingDisabledOnThisDevice() {
    return d.c();
  }
  
  public boolean onBackPressed() {
    null = false;
    if (!d.d())
      throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the onBackPressed() method of your host activity."); 
    if (this.f == null)
      throw new IllegalStateException("The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly."); 
    if (this.q) {
      if (this.y) {
        this.y = false;
        a();
        null = true;
      } 
      return null;
    } 
    return a();
  }
  
  public void onCreate(Activity paramActivity, String paramString1, String paramString2, ChartboostDelegate paramChartboostDelegate) {
    if (!d.d())
      throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the onCreate() method of your host activity."); 
    if (this.f != null && d() && this.f != paramActivity) {
      onStop(this.f);
      a(this.f, false);
    } 
    this.a.removeCallbacks(this.z);
    c(paramActivity);
    setAppID(paramString1);
    setAppSignature(paramString2);
    setDelegate(paramChartboostDelegate);
  }
  
  public void onDestroy(Activity paramActivity) {
    this.a.postDelayed(this.z, 10000L);
  }
  
  public void onStart(Activity paramActivity) {
    if (!d.d())
      throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the onStart() method of your host activity."); 
    this.a.removeCallbacks(this.z);
    a(paramActivity, true);
    c(paramActivity);
    if (!this.q)
      a(paramActivity); 
  }
  
  public void onStop(Activity paramActivity) {
    if (!d.d())
      throw new IllegalStateException("It is illegal to call this method from any thread other than the UI thread. Please call it from the onStop() method of your host activity."); 
    if (!this.q)
      b(paramActivity); 
    if (!(paramActivity instanceof CBImpressionActivity))
      a(paramActivity, false); 
    this.x = (long)(System.nanoTime() / 1000000.0D);
  }
  
  public CBOrientation orientation() {
    if (getContext() == null)
      throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling orientation()."); 
    return (this.s && this.r != CBOrientation.UNSPECIFIED) ? this.r : d.c(getContext());
  }
  
  public void setAppID(String paramString) {
    this.m = paramString;
  }
  
  public void setAppSignature(String paramString) {
    this.n = paramString;
  }
  
  public void setDelegate(ChartboostDelegate paramChartboostDelegate) {
    this.o = paramChartboostDelegate;
  }
  
  public void setIdentityTrackingDisabledOnThisDevice(boolean paramBoolean) {
    SharedPreferences.Editor editor = d.a().edit();
    editor.putBoolean("cbIdentityTrackingDisabled", paramBoolean);
    editor.commit();
  }
  
  public void setImpressionsUseActivities(boolean paramBoolean) {
    this.q = paramBoolean;
  }
  
  public void setOrientation(CBOrientation paramCBOrientation) {
    boolean bool;
    if (paramCBOrientation != CBOrientation.UNSPECIFIED) {
      bool = true;
    } else {
      bool = false;
    } 
    this.s = bool;
    this.r = paramCBOrientation;
  }
  
  public void setTimeout(int paramInt) {
    this.p = paramInt;
  }
  
  public void showInterstitial() {
    showInterstitial("Default");
  }
  
  public void showInterstitial(String paramString) {
    if (this.f == null)
      throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling showInterstitial()."); 
    if (getDelegate() == null || getDelegate().shouldRequestInterstitialsInFirstSession() || d.a().getInt("cbPrefSessionCount", 0) != 1) {
      Chartboost$b chartboost$b = new Chartboost$b(this, paramString);
      this.a.post(chartboost$b);
    } 
  }
  
  public void showMoreApps() {
    if (this.f == null)
      throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling showMoreApps()."); 
    Chartboost$c chartboost$c = new Chartboost$c(this, null);
    this.a.post(chartboost$c);
  }
  
  public void startSession() {
    if (this.f == null)
      throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling startSession()."); 
    if ((long)(System.nanoTime() / 1000000.0D) - this.x >= 10000L) {
      SharedPreferences sharedPreferences = d.a();
      int i = sharedPreferences.getInt("cbPrefSessionCount", 0);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putInt("cbPrefSessionCount", i + 1);
      editor.commit();
      k k = new k("api", "install");
      k.a((Context)this.f);
      k.c(getAppID(), getAppSignature());
      k.h = new Chartboost$4(this);
      this.h.a(k);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Chartboost.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */