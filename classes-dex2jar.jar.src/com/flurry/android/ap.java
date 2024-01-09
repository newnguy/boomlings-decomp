package com.flurry.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

final class ap extends r implements DialogInterface.OnKeyListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, ao {
  private List A;
  
  private final String e = getClass().getSimpleName();
  
  private ProgressDialog f;
  
  private az g;
  
  private WebView h;
  
  private WebChromeClient i;
  
  private View j;
  
  private int k;
  
  private WebChromeClient.CustomViewCallback l;
  
  private Dialog m;
  
  private FrameLayout n;
  
  private Dialog o;
  
  private FrameLayout p;
  
  private AlertDialog q;
  
  private List r;
  
  private boolean s;
  
  private Map t;
  
  private Map u;
  
  private Context v;
  
  private Activity w;
  
  private bo x;
  
  private aa y;
  
  private String z = null;
  
  ap(Context paramContext, bo parambo, bl parambl, AdUnit paramAdUnit, int paramInt) {
    super(paramContext, parambo, parambl);
    this.v = paramContext;
    if (paramContext instanceof Activity)
      this.w = (Activity)paramContext; 
    setClickable(true);
    this.c = paramAdUnit;
    this.d = paramInt;
    this.b = parambl;
    if (this.c != null) {
      this.r = this.c.c();
      if (this.c.d().intValue() != 1)
        bool = false; 
      this.s = bool;
      if (this.s) {
        this.u = new HashMap<Object, Object>();
        this.t = new HashMap<Object, Object>();
        this.u.put(parambl.b(), parambl);
        this.t.put(((AdFrame)paramAdUnit.c().get(0)).f().toString(), paramAdUnit);
      } 
      this.x = parambo;
      this.y = this.x.c;
      this.c = this.c;
      this.b = this.b;
      this.A = new LinkedList();
    } 
  }
  
  private bl a(String paramString) {
    if (this.u == null)
      return null; 
    bl bl2 = (bl)this.u.get(paramString);
    bl bl1 = bl2;
    if (bl2 == null) {
      bl1 = ac.a(this.x, paramString);
      this.u.put(paramString, bl1);
    } 
    return bl1;
  }
  
  private static String a(List paramList) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("'{\"adComponents\":[");
    Iterator<AdUnit> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      stringBuilder.append(ac.d(((AdFrame)((AdUnit)iterator.next()).c().get(0)).c().toString()));
      if (iterator.hasNext())
        stringBuilder.append(","); 
    } 
    stringBuilder.append("]}'");
    return stringBuilder.toString();
  }
  
  private List a(int paramInt1, int paramInt2) {
    List list = this.y.a(this.c.a().toString(), paramInt2);
    for (AdUnit adUnit : list) {
      if (adUnit.c().size() > 0)
        this.t.put(((AdFrame)adUnit.c().get(0)).f().toString(), adUnit); 
    } 
    return list;
  }
  
  private AdUnit b(String paramString) {
    return (this.t == null) ? null : (AdUnit)this.t.get(paramString);
  }
  
  private void e() {
    if (d().equals("takeover")) {
      try {
        ((Activity)this.v).finish();
        a("adClosed", Collections.emptyMap(), this.c, this.b, this.d, 0);
      } catch (ClassCastException classCastException) {
        "caught class cast exception: " + classCastException;
      } 
      return;
    } 
    this.x.b(this.c.a().toString());
  }
  
  private String f() {
    return ((AdFrame)this.r.get(this.d)).b().toString();
  }
  
  public final void a(y paramy, bo parambo, int paramInt) {
    String str = paramy.a;
    bj bj = paramy.c;
    Map map = paramy.b;
    if (paramInt > 10) {
      "Maximum depth for event/action loop exceeded when performing action:" + str + "," + map + ",triggered by:" + bj.a;
      return;
    } 
    "performAction(action=" + str + ",params=" + paramy.b + ",triggering event=" + bj.a + ")";
    if (str.equals("nextAdUnit")) {
      try {
        x x = (x)getParent();
        if (x != null) {
          try {
            paramInt = Integer.parseInt((String)paramy.b.get("delay"));
          } catch (NumberFormatException numberFormatException) {
            paramInt = 0;
          } 
          x.a(paramInt * 1000);
          this.a.d.a(x);
        } 
      } catch (ClassCastException classCastException) {
        classCastException.toString();
        e();
      } 
    } else {
      String str1;
      if (numberFormatException.equals("nextFrame")) {
        int i = this.d + 1;
        String str2 = (String)map.get("offset");
        paramInt = i;
        if (str2 != null)
          if (str2.equals("next")) {
            paramInt = this.d + 1;
          } else if (!str2.equals("current")) {
            try {
              paramInt = Integer.parseInt(str2);
            } catch (NumberFormatException numberFormatException1) {
              "caught: " + numberFormatException1.getMessage();
              paramInt = i;
            } 
          } else {
            return;
          }  
        if (!this.s) {
          if (paramInt != this.d && paramInt < this.r.size()) {
            AdFrame adFrame = this.c.c().get(paramInt);
            str2 = d();
            str1 = adFrame.d().d().toString();
            if (!str1.equals(str2)) {
              if (str1.equals("takeover")) {
                this.x.b(this.b);
                this.x.a(this.c);
                Intent intent = new Intent();
                intent.putExtra("frameIndex", paramInt);
                intent.setClass(this.v, FlurryFullscreenTakeoverActivity.class);
                this.v.startActivity(intent);
              } 
            } else {
              this.d = paramInt;
              initLayout(this.v);
            } 
          } 
        } else {
          str2 = (String)paramy.c.b.get("guid");
          if (str2 != null) {
            this.c = b(str2);
            this.r = this.c.c();
            this.b = paramy.c.e;
            if (d().equals("takeover")) {
              this.x.a(this.c);
              this.x.b(this.b);
            } 
            this.d = paramInt;
            this.s = false;
            initLayout(this.v);
          } 
        } 
      } else if (str1.equals("closeAd")) {
        e();
      } else if (str1.equals("notifyUser")) {
        String str2;
        String str3;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.v);
        if (map.containsKey("message") && map.containsKey("confirmDisplay") && map.containsKey("cancelDisplay")) {
          str3 = (String)map.get("message");
          str1 = (String)map.get("confirmDisplay");
          str2 = (String)map.get("cancelDisplay");
        } else {
          str3 = "Are you sure?";
          str1 = "Cancel";
          str2 = "OK";
        } 
        builder.setMessage(str3).setCancelable(false).setPositiveButton(str2, new av(this, bj, paramInt)).setNegativeButton(str1, new ar(this, bj, paramInt));
        this.q = builder.create();
        if (this.g != null && b() == 3)
          this.g.pause(); 
        this.q.show();
      } else if (str1.equals("loadAdComponents")) {
        byte b1 = 1;
        byte b2 = 3;
        paramInt = b2;
        int i = b1;
        if (map.containsKey("min")) {
          paramInt = b2;
          i = b1;
          if (map.containsKey("max"))
            try {
              i = Integer.parseInt((String)map.get("min"));
              paramInt = Integer.parseInt((String)map.get("max"));
            } catch (NumberFormatException numberFormatException1) {
              i = 1;
              paramInt = 3;
            }  
        } 
        this.c.a().toString();
        List<AdUnit> list = a(i, paramInt);
        list.add(this.c);
        if (list.size() > 0) {
          this.c.a().toString();
          String str2 = a(list);
          this.h.loadUrl("javascript:(function() {var multiadwraps=document.getElementsByClassName('multiAdWrap');if(multiadwraps.length>0){var template=document.getElementsByClassName('multiAdWrap')[0];var compiled=Hogan.compile(template.innerHTML);template.innerHTML='';template.innerHTML=compiled.render(JSON.parse(" + str2 + "));}})();");
          this.h.loadUrl("javascript:flurryadapter.callComplete();");
          for (AdUnit adUnit : list) {
            HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
            hashMap.put("guid", ((AdFrame)adUnit.c().get(0)).f().toString());
            a("rendered", hashMap, adUnit, a(((AdFrame)adUnit.c().get(0)).f().toString()), 0, 0);
          } 
          if (!a((View)this.h))
            addView((View)this.h); 
        } else {
          a("renderFailed", Collections.emptyMap(), this.c, this.b, this.d, 0);
        } 
      } else {
        au au;
        if (str1.equals("doExpand")) {
          int i = ac.a(this.v);
          paramInt = ac.b(this.v);
          if (paramy.c.b.containsKey("width") && paramy.c.b.containsKey("height"))
            try {
              i = ac.a(this.v, Integer.parseInt((String)paramy.c.b.get("width")));
              paramInt = ac.a(this.v, Integer.parseInt((String)paramy.c.b.get("height")));
            } catch (NumberFormatException numberFormatException1) {
              numberFormatException1.getMessage();
              i = ac.a(this.v);
              paramInt = ac.b(this.v);
            }  
          "expand to width = " + i + " height = " + paramInt;
          try {
            if ((x)getParent() != null && this.o == null) {
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("expand(").append(i).append(",").append(paramInt).append(")").toString();
              if (this.h != null && -1 != indexOfChild((View)this.h))
                removeView((View)this.h); 
              if (this.p == null) {
                FrameLayout frameLayout = new FrameLayout();
                this(this.v);
                this.p = frameLayout;
                this.p.setBackgroundColor(-16777216);
                if (this.h != null && this.h.getParent() == null) {
                  FrameLayout frameLayout1 = this.p;
                  WebView webView = this.h;
                  FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams();
                  this(-1, -1, 17);
                  frameLayout1.addView((View)webView, (ViewGroup.LayoutParams)layoutParams);
                } 
              } 
              if (this.o == null) {
                Dialog dialog2 = new Dialog();
                this(this.v, 16973834);
                this.o = dialog2;
                Dialog dialog3 = this.o;
                FrameLayout frameLayout = this.p;
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams();
                this(-1, -1);
                dialog3.setContentView((View)frameLayout, layoutParams);
                Dialog dialog1 = this.o;
                au = new au();
                this(this);
                dialog1.setOnDismissListener(au);
                this.o.setCancelable(true);
                this.o.show();
              } 
            } 
            if (paramy.c.b.containsKey("url")) {
              this.z = (String)paramy.c.b.get("url");
              initLayout(this.v);
            } 
          } catch (ClassCastException classCastException) {
            classCastException.getMessage();
          } 
        } else if (au.equals("doCollapse")) {
          int i = ((AdFrame)this.r.get(this.d)).d().a().intValue();
          paramInt = ((AdFrame)this.r.get(this.d)).d().b().intValue();
          i = ac.a(this.v, i);
          paramInt = ac.a(this.v, paramInt);
          if (this.z != null) {
            this.z = null;
            initLayout(this.v);
          } 
          try {
            if ((x)getParent() != null && this.o != null) {
              StringBuilder stringBuilder = new StringBuilder();
              this();
              stringBuilder.append("collapse(").append(i).append(",").append(paramInt).append(")").toString();
              if (this.o != null && this.o.isShowing()) {
                this.o.hide();
                this.o.setOnDismissListener(null);
                this.o.dismiss();
              } 
              this.o = null;
              if (this.p != null) {
                if (this.h != null && -1 != this.p.indexOfChild((View)this.h))
                  this.p.removeView((View)this.h); 
                this.p = null;
              } 
              if (this.h != null && this.h.getParent() == null)
                addView((View)this.h); 
            } 
          } catch (ClassCastException classCastException) {
            "action doCollapse failed:" + classCastException.getMessage();
          } 
        } else {
          this.a.a(paramy, (bo)classCastException, paramInt);
        } 
      } 
    } 
    if (this.A.contains(paramy.c.a) && this.h != null) {
      this.h.loadUrl("javascript:flurryadapter.callComplete('" + paramy.c.a + "');");
      this.A.remove(paramy.c.a);
    } 
  }
  
  final void a(String paramString, Map paramMap, AdUnit paramAdUnit, bl parambl, int paramInt1, int paramInt2) {
    "fireEvent(event=" + paramString + ",params=" + paramMap + ")";
    this.a.a(new bj(paramString, paramMap, this.v, paramAdUnit, parambl, paramInt1), this, paramInt2);
  }
  
  final boolean a() {
    boolean bool;
    null = true;
    if (this.o != null) {
      bool = true;
    } else {
      bool = false;
    } 
    if (!bool) {
      if (this.m != null) {
        bool = true;
      } else {
        bool = false;
      } 
      if (!bool)
        return null; 
    } 
    return false;
  }
  
  final boolean a(View paramView) {
    ViewParent viewParent = paramView.getParent();
    return (viewParent != null && viewParent == this);
  }
  
  final int b() {
    return ((AdFrame)this.r.get(this.d)).a().intValue();
  }
  
  final String c() {
    return ((AdFrame)this.r.get(this.d)).c().toString();
  }
  
  final String d() {
    return ((AdFrame)this.r.get(this.d)).d().d().toString();
  }
  
  public final void initLayout(Context paramContext) {
    removeAllViews();
    int i = b();
    try {
      Activity activity = (Activity)this.v;
      if (i == 3)
        activity.setRequestedOrientation(0); 
      setFocusable(true);
      setFocusableInTouchMode(true);
      switch (b()) {
        default:
          a("renderFailed", Collections.emptyMap(), this.c, this.b, this.d, 0);
          return;
        case 3:
          if (this.g == null) {
            this.g = new az(paramContext);
            this.g.setOnPreparedListener(this);
            this.g.setOnCompletionListener(this);
            this.g.setOnErrorListener(this);
            this.g.setMediaController(new MediaController(paramContext));
          } 
          this.g.setVideoURI(Uri.parse(f()));
          bm.a(this.e, "URI: " + Uri.parse(f()).toString());
          this.g.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
          addView((View)this.g);
          ((RelativeLayout)this.g.getParent()).setGravity(17);
          this.f = new ProgressDialog(paramContext);
          this.f.setProgressStyle(0);
          this.f.setMessage("Loading...");
          this.f.setCancelable(true);
          this.f.setOnKeyListener(this);
          this.f.show();
          return;
        case 1:
        case 2:
          break;
      } 
    } catch (ClassCastException classCastException) {
      classCastException.toString();
      setFocusable(true);
      setFocusableInTouchMode(true);
      switch (b()) {
        default:
          a("renderFailed", Collections.emptyMap(), this.c, this.b, this.d, 0);
          return;
        case 3:
          if (this.g == null) {
            this.g = new az(paramContext);
            this.g.setOnPreparedListener(this);
            this.g.setOnCompletionListener(this);
            this.g.setOnErrorListener(this);
            this.g.setMediaController(new MediaController(paramContext));
          } 
          this.g.setVideoURI(Uri.parse(f()));
          bm.a(this.e, "URI: " + Uri.parse(f()).toString());
          this.g.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
          addView((View)this.g);
          ((RelativeLayout)this.g.getParent()).setGravity(17);
          this.f = new ProgressDialog(paramContext);
          this.f.setProgressStyle(0);
          this.f.setMessage("Loading...");
          this.f.setCancelable(true);
          this.f.setOnKeyListener(this);
          this.f.show();
          return;
        case 1:
        case 2:
          break;
      } 
    } 
    if (this.h == null) {
      this.h = new WebView(paramContext);
      this.h.setWebViewClient(new ax(this));
      this.h.getSettings().setJavaScriptEnabled(true);
      this.h.setVerticalScrollBarEnabled(false);
      this.h.setHorizontalScrollBarEnabled(false);
      this.h.setBackgroundColor(0);
      this.i = new bf(this);
      this.h.setWebChromeClient(this.i);
    } 
    if (b() == 1) {
      if (this.z != null) {
        this.h.loadUrl(this.z);
      } else {
        String str = f();
        this.h.loadUrl(str);
      } 
    } else if (b() == 2) {
      this.h.loadDataWithBaseURL("base://url/", f(), "text/html", "utf-8", "base://url/");
    } 
    this.h.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
  }
  
  public final void onCompletion(MediaPlayer paramMediaPlayer) {
    a("videoCompleted", Collections.emptyMap(), this.c, this.b, this.d, 0);
  }
  
  public final boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2) {
    if (this.f != null && this.f.isShowing())
      this.f.dismiss(); 
    a("renderFailed", Collections.emptyMap(), this.c, this.b, this.d, 0);
    removeView((View)this.g);
    return false;
  }
  
  public final boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent) {
    boolean bool2 = false;
    "onkey,keycode=" + paramInt + ",event=" + paramKeyEvent.getAction();
    boolean bool1 = bool2;
    if (paramDialogInterface == this.f) {
      bool1 = bool2;
      if (paramInt == 4) {
        bool1 = bool2;
        if (paramKeyEvent.getAction() == 0) {
          a("adWillClose", Collections.emptyMap(), this.c, this.b, this.d, 0);
          paramDialogInterface.dismiss();
          bool1 = true;
        } 
      } 
    } 
    return bool1;
  }
  
  public final void onPrepared(MediaPlayer paramMediaPlayer) {
    if (b() == 3) {
      if (this.f.isShowing())
        this.f.dismiss(); 
      if (this.q == null || !this.q.isShowing())
        this.g.start(); 
      a("rendered", Collections.emptyMap(), this.c, this.b, this.d, 0);
      a("videoStart", Collections.emptyMap(), this.c, this.b, this.d, 0);
    } 
  }
  
  public final void stop() {
    if (b() == 3) {
      if (this.f != null && this.f.isShowing())
        this.f.dismiss(); 
      if (this.g != null && this.g.isPlaying())
        this.g.stopPlayback(); 
    } 
    if ((b() == 1 || b() == 2) && this.h != null) {
      if (this.m != null && this.m.isShowing()) {
        this.m.hide();
        this.m.setOnDismissListener(null);
        this.m.dismiss();
      } 
      this.m = null;
      if (this.j != null && this.i != null)
        this.i.onHideCustomView(); 
      if (this.o != null && this.o.isShowing()) {
        this.o.hide();
        this.o.setOnDismissListener(null);
        this.o.dismiss();
      } 
      this.o = null;
      this.h.loadUrl("javascript:if(window.mraid){window.mraid.close();};");
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */