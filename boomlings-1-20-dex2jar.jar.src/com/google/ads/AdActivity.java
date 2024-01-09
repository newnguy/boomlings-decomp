package com.google.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.google.ads.internal.AdVideoView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.a;
import com.google.ads.internal.d;
import com.google.ads.internal.e;
import com.google.ads.internal.i;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import com.google.ads.util.g;
import java.util.HashMap;
import java.util.Map;

public class AdActivity extends Activity implements View.OnClickListener {
  private static final a a = (a)a.a.b();
  
  private static final Object b = new Object();
  
  private static AdActivity c = null;
  
  private static d d = null;
  
  private static AdActivity e = null;
  
  private static AdActivity f = null;
  
  private static final AdActivity$StaticMethodWrapper g = new AdActivity$StaticMethodWrapper();
  
  private AdWebView h;
  
  private FrameLayout i;
  
  private int j;
  
  private ViewGroup k = null;
  
  private boolean l;
  
  private long m;
  
  private RelativeLayout n;
  
  private AdActivity o = null;
  
  private boolean p;
  
  private boolean q;
  
  private boolean r;
  
  private boolean s;
  
  private AdVideoView t;
  
  public static void a(d paramd, e parame) {
    g.a(paramd, parame);
  }
  
  private void a(String paramString) {
    b.b(paramString);
    finish();
  }
  
  private void a(String paramString, Throwable paramThrowable) {
    b.b(paramString, paramThrowable);
    finish();
  }
  
  private RelativeLayout.LayoutParams c(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(paramInt3, paramInt4);
    layoutParams.setMargins(paramInt1, paramInt2, 0, 0);
    layoutParams.addRule(10);
    layoutParams.addRule(9);
    return layoutParams;
  }
  
  public static boolean c() {
    return g.a();
  }
  
  private void g() {
    if (!this.l) {
      if (this.h != null) {
        a.b((WebView)this.h);
        this.h.setAdActivity(null);
        this.h.setIsExpandedMraid(false);
        if (!this.q && this.n != null && this.k != null) {
          if (this.r && !this.s) {
            b.a("Disabling hardware acceleration on collapsing MRAID WebView.");
            this.h.b();
          } else if (!this.r && this.s) {
            b.a("Re-enabling hardware acceleration on collapsing MRAID WebView.");
            this.h.c();
          } 
          this.n.removeView((View)this.h);
          this.k.addView((View)this.h);
        } 
      } 
      if (this.t != null) {
        this.t.e();
        this.t = null;
      } 
      if (this == c)
        c = null; 
      f = this.o;
      synchronized (b) {
        if (d != null && this.q && this.h != null) {
          if (this.h == d.k())
            d.a(); 
          this.h.stopLoading();
        } 
        if (this == e) {
          e = null;
          if (d != null) {
            d.s();
            d = null;
          } else {
            b.e("currentAdManager is null while trying to destroy AdActivity.");
          } 
        } 
        this.l = true;
        b.a("AdActivity is closing.");
        return;
      } 
    } 
  }
  
  protected View a(int paramInt, boolean paramBoolean) {
    this.j = (int)TypedValue.applyDimension(1, paramInt, getResources().getDisplayMetrics());
    this.i = new FrameLayout(getApplicationContext());
    this.i.setMinimumWidth(this.j);
    this.i.setMinimumHeight(this.j);
    this.i.setOnClickListener(this);
    a(paramBoolean);
    return (View)this.i;
  }
  
  public AdVideoView a() {
    return this.t;
  }
  
  protected AdVideoView a(Activity paramActivity) {
    return new AdVideoView(paramActivity, this.h);
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.t != null) {
      this.t.setLayoutParams((ViewGroup.LayoutParams)c(paramInt1, paramInt2, paramInt3, paramInt4));
      this.t.requestLayout();
    } 
  }
  
  protected void a(AdWebView paramAdWebView, boolean paramBoolean1, int paramInt, boolean paramBoolean2, boolean paramBoolean3) {
    requestWindowFeature(1);
    Window window = getWindow();
    window.setFlags(1024, 1024);
    if (AdUtil.a >= 11)
      if (this.r) {
        b.a("Enabling hardware acceleration on the AdActivity window.");
        g.a(window);
      } else {
        b.a("Disabling hardware acceleration on the AdActivity WebView.");
        paramAdWebView.b();
      }  
    ViewParent viewParent = paramAdWebView.getParent();
    if (viewParent != null)
      if (paramBoolean2) {
        if (viewParent instanceof ViewGroup) {
          this.k = (ViewGroup)viewParent;
          this.k.removeView((View)paramAdWebView);
        } else {
          a("MRAID banner was not a child of a ViewGroup.");
          return;
        } 
      } else {
        a("Interstitial created with an AdWebView that has a parent.");
        return;
      }  
    if (paramAdWebView.d() != null) {
      a("Interstitial created with an AdWebView that is already in use by another AdActivity.");
      return;
    } 
    setRequestedOrientation(paramInt);
    paramAdWebView.setAdActivity(this);
    if (paramBoolean2) {
      paramInt = 50;
    } else {
      paramInt = 32;
    } 
    View view = a(paramInt, paramBoolean3);
    this.n.addView((View)paramAdWebView, -1, -1);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
    if (paramBoolean2) {
      layoutParams.addRule(10);
      layoutParams.addRule(11);
    } else {
      layoutParams.addRule(10);
      layoutParams.addRule(9);
    } 
    this.n.addView(view, (ViewGroup.LayoutParams)layoutParams);
    this.n.setKeepScreenOn(true);
    setContentView((View)this.n);
    this.n.getRootView().setBackgroundColor(-16777216);
    if (paramBoolean1)
      a.a((WebView)paramAdWebView); 
  }
  
  protected void a(d paramd) {
    this.h = null;
    this.m = SystemClock.elapsedRealtime();
    this.p = true;
    synchronized (b) {
      if (c == null) {
        c = this;
        paramd.u();
      } 
      return;
    } 
  }
  
  protected void a(HashMap paramHashMap, d paramd) {
    Intent intent = new Intent();
    intent.setComponent(new ComponentName("com.google.android.apps.plus", "com.google.android.apps.circles.platform.PlusOneActivity"));
    intent.addCategory("android.intent.category.LAUNCHER");
    intent.putExtras(getIntent().getExtras());
    intent.putExtra("com.google.circles.platform.intent.extra.ENTITY", (String)paramHashMap.get("u"));
    intent.putExtra("com.google.circles.platform.intent.extra.ENTITY_TYPE", ai$b.a.c);
    intent.putExtra("com.google.circles.platform.intent.extra.ACTION", (String)paramHashMap.get("a"));
    a(paramd);
    try {
      b.a("Launching Google+ intent from AdActivity.");
      startActivityForResult(intent, 0);
    } catch (ActivityNotFoundException activityNotFoundException) {
      a(activityNotFoundException.getMessage(), (Throwable)activityNotFoundException);
    } 
  }
  
  public void a(boolean paramBoolean) {
    if (this.i != null) {
      this.i.removeAllViews();
      if (!paramBoolean) {
        ImageButton imageButton = new ImageButton((Context)this);
        imageButton.setImageResource(17301527);
        imageButton.setBackgroundColor(0);
        imageButton.setOnClickListener(this);
        imageButton.setPadding(0, 0, 0, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.j, this.j, 17);
        this.i.addView((View)imageButton, (ViewGroup.LayoutParams)layoutParams);
      } 
    } 
  }
  
  public AdWebView b() {
    null = null;
    if (this.o != null)
      return this.o.h; 
    synchronized (b) {
      if (d == null) {
        b.e("currentAdManager was null while trying to get the opening AdWebView.");
        return null;
      } 
    } 
    AdWebView adWebView = d.k();
    if (adWebView != this.h) {
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_3} */
      return adWebView;
    } 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_3} */
    return (AdWebView)SYNTHETIC_LOCAL_VARIABLE_1;
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.t == null) {
      this.t = a(this);
      this.n.addView((View)this.t, 0, (ViewGroup.LayoutParams)c(paramInt1, paramInt2, paramInt3, paramInt4));
      synchronized (b) {
        if (d == null) {
          b.e("currentAdManager was null while trying to get the opening AdWebView.");
          return;
        } 
        d.l().b(false);
        return;
      } 
    } 
  }
  
  protected void b(HashMap paramHashMap, d paramd) {
    Intent intent;
    if (paramHashMap == null) {
      a("Could not get the paramMap in launchIntent()");
      return;
    } 
    String str1 = (String)paramHashMap.get("u");
    if (str1 == null) {
      a("Could not get the URL parameter in launchIntent().");
      return;
    } 
    String str3 = (String)paramHashMap.get("i");
    String str2 = (String)paramHashMap.get("m");
    Uri uri = Uri.parse(str1);
    if (str3 == null) {
      intent = new Intent("android.intent.action.VIEW", uri);
    } else {
      intent = new Intent(str3, uri);
    } 
    if (str2 != null)
      intent.setDataAndType(uri, str2); 
    a(paramd);
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      b.a(stringBuilder.append("Launching an intent from AdActivity: ").append(intent.getAction()).append(" - ").append(uri).toString());
      startActivity(intent);
    } catch (ActivityNotFoundException activityNotFoundException) {
      a(activityNotFoundException.getMessage(), (Throwable)activityNotFoundException);
    } 
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (b() != null && paramIntent != null && paramIntent.getExtras() != null && paramIntent.getExtras().getString("com.google.circles.platform.result.extra.CONFIRMATION") != null && paramIntent.getExtras().getString("com.google.circles.platform.result.extra.ACTION") != null) {
      String str2 = paramIntent.getExtras().getString("com.google.circles.platform.result.extra.CONFIRMATION");
      String str1 = paramIntent.getExtras().getString("com.google.circles.platform.result.extra.ACTION");
      if (str2.equals("yes"))
        if (str1.equals("insert")) {
          ag.a((WebView)b(), true);
        } else if (str1.equals("delete")) {
          ag.a((WebView)b(), false);
        }  
    } 
    finish();
  }
  
  public void onClick(View paramView) {
    finish();
  }
  
  public void onCreate(Bundle paramBundle) {
    AdWebView adWebView;
    boolean bool1;
    boolean bool = false;
    super.onCreate(paramBundle);
    this.l = false;
    synchronized (b) {
      if (d != null) {
        boolean bool2;
        d d1 = d;
        if (e == null) {
          e = this;
          d1.t();
        } 
        if (this.o == null && f != null)
          this.o = f; 
        f = this;
        if ((d1.h().a() && e == this) || (d1.h().b() && this.o == e))
          d1.v(); 
        bool1 = d1.q();
        l$a l$a = (l$a)((l)(d1.h()).a.a()).a.a();
        if (AdUtil.a >= ((Integer)l$a.a.a()).intValue()) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        this.s = bool2;
        if (AdUtil.a >= ((Integer)l$a.b.a()).intValue()) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        this.r = bool2;
        this.n = null;
        this.p = false;
        this.q = true;
        this.t = null;
        null = getIntent().getBundleExtra("com.google.ads.AdOpener");
        if (null == null) {
          a("Could not get the Bundle used to create AdActivity.");
          return;
        } 
      } else {
        a("Could not get currentAdManager.");
        return;
      } 
    } 
    e e = new e((Bundle)SYNTHETIC_LOCAL_VARIABLE_6);
    String str = e.b();
    HashMap hashMap = e.c();
    if (str.equals("plusone")) {
      a(hashMap, (d)paramBundle);
      return;
    } 
    if (str.equals("intent")) {
      b(hashMap, (d)paramBundle);
      return;
    } 
    this.n = new RelativeLayout(getApplicationContext());
    if (str.equals("webapp")) {
      byte b;
      boolean bool2;
      this.h = new AdWebView(paramBundle.h(), null);
      Map map = a.c;
      if (!bool1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      i i = i.a((d)paramBundle, map, true, bool2);
      i.d(true);
      if (bool1)
        i.a(true); 
      this.h.setWebViewClient((WebViewClient)i);
      str = (String)hashMap.get("u");
      String str2 = (String)hashMap.get("baseurl");
      String str1 = (String)hashMap.get("html");
      if (str != null) {
        this.h.loadUrl(str);
      } else if (str1 != null) {
        this.h.loadDataWithBaseURL(str2, str1, "text/html", "utf-8", null);
      } else {
        a("Could not get the URL or HTML parameter to show a web app.");
        return;
      } 
      str = (String)hashMap.get("o");
      if ("p".equals(str)) {
        b = AdUtil.b();
      } else if ("l".equals(str)) {
        b = AdUtil.a();
      } else if (this == e) {
        b = paramBundle.n();
      } else {
        b = -1;
      } 
      adWebView = this.h;
      if (hashMap != null && "1".equals(hashMap.get("custom_close"))) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      a(adWebView, false, b, bool1, bool2);
      return;
    } 
    if (str.equals("interstitial") || str.equals("expand")) {
      boolean bool2;
      this.h = adWebView.k();
      int i = adWebView.n();
      if (str.equals("expand")) {
        this.h.setIsExpandedMraid(true);
        this.q = false;
        bool2 = bool;
        if (hashMap != null) {
          bool2 = bool;
          if ("1".equals(hashMap.get("custom_close")))
            bool2 = true; 
        } 
        if (this.r && !this.s) {
          b.a("Re-enabling hardware acceleration on expanding MRAID WebView.");
          this.h.c();
        } 
      } else {
        bool2 = this.h.e();
      } 
      a(this.h, true, i, bool1, bool2);
      return;
    } 
    a("Unknown AdOpener, <action: " + str + ">");
  }
  
  public void onDestroy() {
    if (this.n != null)
      this.n.removeAllViews(); 
    if (isFinishing()) {
      g();
      if (this.q && this.h != null) {
        this.h.stopLoading();
        this.h.destroy();
        this.h = null;
      } 
    } 
    super.onDestroy();
  }
  
  public void onPause() {
    if (isFinishing())
      g(); 
    super.onPause();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean) {
    if (this.p && paramBoolean && SystemClock.elapsedRealtime() - this.m > 250L) {
      b.d("Launcher AdActivity got focus and is closing.");
      finish();
    } 
    super.onWindowFocusChanged(paramBoolean);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\AdActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */