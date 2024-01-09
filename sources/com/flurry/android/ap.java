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

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ap extends r implements DialogInterface.OnKeyListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, ao {
    private List A;
    private final String e;
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
    private String z;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ap(Context context, bo boVar, bl blVar, AdUnit adUnit, int i) {
        super(context, boVar, blVar);
        this.e = getClass().getSimpleName();
        this.z = null;
        this.v = context;
        if (context instanceof Activity) {
            this.w = (Activity) context;
        }
        setClickable(true);
        this.c = adUnit;
        this.d = i;
        this.b = blVar;
        if (this.c != null) {
            this.r = this.c.c();
            this.s = this.c.d().intValue() == 1;
            if (this.s) {
                this.u = new HashMap();
                this.t = new HashMap();
                this.u.put(blVar.b(), blVar);
                this.t.put(((AdFrame) adUnit.c().get(0)).f().toString(), adUnit);
            }
            this.x = boVar;
            this.y = this.x.c;
            this.c = this.c;
            this.b = this.b;
            this.A = new LinkedList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public bl a(String str) {
        if (this.u == null) {
            return null;
        }
        bl blVar = (bl) this.u.get(str);
        if (blVar == null) {
            bl a = ac.a(this.x, str);
            this.u.put(str, a);
            return a;
        }
        return blVar;
    }

    private static String a(List list) {
        StringBuilder sb = new StringBuilder();
        sb.append("'{\"adComponents\":[");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sb.append(ac.d(((AdFrame) ((AdUnit) it.next()).c().get(0)).c().toString()));
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("]}'");
        return sb.toString();
    }

    private List a(int i, int i2) {
        List<AdUnit> a = this.y.a(this.c.a().toString(), i2);
        for (AdUnit adUnit : a) {
            if (adUnit.c().size() > 0) {
                this.t.put(((AdFrame) adUnit.c().get(0)).f().toString(), adUnit);
            }
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AdUnit b(String str) {
        if (this.t == null) {
            return null;
        }
        return (AdUnit) this.t.get(str);
    }

    private void e() {
        if (!d().equals(AdCreative.kFormatTakeover)) {
            this.x.b(this.c.a().toString());
            return;
        }
        try {
            ((Activity) this.v).finish();
            a("adClosed", Collections.emptyMap(), this.c, this.b, this.d, 0);
        } catch (ClassCastException e) {
            String str = "caught class cast exception: " + e;
        }
    }

    private String f() {
        return ((AdFrame) this.r.get(this.d)).b().toString();
    }

    @Override // com.flurry.android.ao
    public final void a(y yVar, bo boVar, int i) {
        int i2;
        int i3;
        int i4;
        String str;
        String str2;
        String str3;
        String str4;
        String str5 = yVar.a;
        bj bjVar = yVar.c;
        Map map = yVar.b;
        if (i > 10) {
            r0 = "Maximum depth for event/action loop exceeded when performing action:" + str5 + "," + map + ",triggered by:" + bjVar.a;
            return;
        }
        String str6 = "performAction(action=" + str5 + ",params=" + yVar.b + ",triggering event=" + bjVar.a + ")";
        if (str5.equals("nextAdUnit")) {
            try {
                x xVar = (x) getParent();
                if (xVar != null) {
                    try {
                        i2 = Integer.parseInt((String) yVar.b.get("delay"));
                    } catch (NumberFormatException e) {
                        i2 = 0;
                    }
                    xVar.a(i2 * 1000);
                    this.a.d.a(xVar);
                }
            } catch (ClassCastException e2) {
                e2.toString();
                e();
            }
        } else if (str5.equals("nextFrame")) {
            int i5 = this.d + 1;
            String str7 = (String) map.get("offset");
            if (str7 != null) {
                if (str7.equals("next")) {
                    i5 = this.d + 1;
                } else if (str7.equals("current")) {
                    return;
                } else {
                    try {
                        i5 = Integer.parseInt(str7);
                    } catch (NumberFormatException e3) {
                        str4 = "caught: " + e3.getMessage();
                    }
                }
            }
            if (this.s) {
                String str8 = (String) yVar.c.b.get("guid");
                if (str8 != null) {
                    this.c = b(str8);
                    this.r = this.c.c();
                    this.b = yVar.c.e;
                    if (d().equals(AdCreative.kFormatTakeover)) {
                        this.x.a(this.c);
                        this.x.b(this.b);
                    }
                    this.d = i5;
                    this.s = false;
                    initLayout(this.v);
                }
            } else if (i5 != this.d && i5 < this.r.size()) {
                String d = d();
                String obj = ((AdFrame) this.c.c().get(i5)).d().d().toString();
                if (obj.equals(d)) {
                    this.d = i5;
                    initLayout(this.v);
                } else if (obj.equals(AdCreative.kFormatTakeover)) {
                    this.x.b(this.b);
                    this.x.a(this.c);
                    Intent intent = new Intent();
                    intent.putExtra("frameIndex", i5);
                    intent.setClass(this.v, FlurryFullscreenTakeoverActivity.class);
                    this.v.startActivity(intent);
                }
            }
        } else if (str5.equals("closeAd")) {
            e();
        } else if (str5.equals("notifyUser")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.v);
            if (map.containsKey("message") && map.containsKey("confirmDisplay") && map.containsKey("cancelDisplay")) {
                str = (String) map.get("message");
                str2 = (String) map.get("confirmDisplay");
                str3 = (String) map.get("cancelDisplay");
            } else {
                str = "Are you sure?";
                str2 = "Cancel";
                str3 = "OK";
            }
            builder.setMessage(str).setCancelable(false).setPositiveButton(str3, new av(this, bjVar, i)).setNegativeButton(str2, new ar(this, bjVar, i));
            this.q = builder.create();
            if (this.g != null && b() == 3) {
                this.g.pause();
            }
            this.q.show();
        } else if (str5.equals("loadAdComponents")) {
            int i6 = 1;
            int i7 = 3;
            if (map.containsKey("min") && map.containsKey("max")) {
                try {
                    i6 = Integer.parseInt((String) map.get("min"));
                    i7 = Integer.parseInt((String) map.get("max"));
                } catch (NumberFormatException e4) {
                    i6 = 1;
                    i7 = 3;
                }
            }
            this.c.a().toString();
            List<AdUnit> a = a(i6, i7);
            a.add(this.c);
            if (a.size() > 0) {
                this.c.a().toString();
                this.h.loadUrl("javascript:(function() {var multiadwraps=document.getElementsByClassName('multiAdWrap');if(multiadwraps.length>0){var template=document.getElementsByClassName('multiAdWrap')[0];var compiled=Hogan.compile(template.innerHTML);template.innerHTML='';template.innerHTML=compiled.render(JSON.parse(" + a(a) + "));}})();");
                this.h.loadUrl("javascript:flurryadapter.callComplete();");
                for (AdUnit adUnit : a) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("guid", ((AdFrame) adUnit.c().get(0)).f().toString());
                    a("rendered", hashMap, adUnit, a(((AdFrame) adUnit.c().get(0)).f().toString()), 0, 0);
                }
                if (!a(this.h)) {
                    addView(this.h);
                }
            } else {
                a("renderFailed", Collections.emptyMap(), this.c, this.b, this.d, 0);
            }
        } else if (str5.equals("doExpand")) {
            int a2 = ac.a(this.v);
            int b = ac.b(this.v);
            if (yVar.c.b.containsKey(AdCreative.kFixWidth) && yVar.c.b.containsKey(AdCreative.kFixHeight)) {
                try {
                    i3 = ac.a(this.v, Integer.parseInt((String) yVar.c.b.get(AdCreative.kFixWidth)));
                    i4 = ac.a(this.v, Integer.parseInt((String) yVar.c.b.get(AdCreative.kFixHeight)));
                } catch (NumberFormatException e5) {
                    e5.getMessage();
                    i3 = ac.a(this.v);
                    i4 = ac.b(this.v);
                }
            } else {
                i3 = a2;
                i4 = b;
            }
            r0 = "expand to width = " + i3 + " height = " + i4;
            try {
                if (((x) getParent()) != null && this.o == null) {
                    String str9 = "expand(" + i3 + "," + i4 + ")";
                    if (this.h != null && -1 != indexOfChild(this.h)) {
                        removeView(this.h);
                    }
                    if (this.p == null) {
                        this.p = new FrameLayout(this.v);
                        this.p.setBackgroundColor(-16777216);
                        if (this.h != null && this.h.getParent() == null) {
                            this.p.addView(this.h, new FrameLayout.LayoutParams(-1, -1, 17));
                        }
                    }
                    if (this.o == null) {
                        this.o = new Dialog(this.v, 16973834);
                        this.o.setContentView(this.p, new ViewGroup.LayoutParams(-1, -1));
                        this.o.setOnDismissListener(new au(this));
                        this.o.setCancelable(true);
                        this.o.show();
                    }
                }
                if (yVar.c.b.containsKey("url")) {
                    this.z = (String) yVar.c.b.get("url");
                    initLayout(this.v);
                }
            } catch (ClassCastException e6) {
                e6.getMessage();
            }
        } else if (str5.equals("doCollapse")) {
            int intValue = ((AdFrame) this.r.get(this.d)).d().a().intValue();
            int intValue2 = ((AdFrame) this.r.get(this.d)).d().b().intValue();
            int a3 = ac.a(this.v, intValue);
            int a4 = ac.a(this.v, intValue2);
            if (this.z != null) {
                this.z = null;
                initLayout(this.v);
            }
            try {
                if (((x) getParent()) != null && this.o != null) {
                    String str10 = "collapse(" + a3 + "," + a4 + ")";
                    if (this.o != null && this.o.isShowing()) {
                        this.o.hide();
                        this.o.setOnDismissListener(null);
                        this.o.dismiss();
                    }
                    this.o = null;
                    if (this.p != null) {
                        if (this.h != null && -1 != this.p.indexOfChild(this.h)) {
                            this.p.removeView(this.h);
                        }
                        this.p = null;
                    }
                    if (this.h != null && this.h.getParent() == null) {
                        addView(this.h);
                    }
                }
            } catch (ClassCastException e7) {
                String str11 = "action doCollapse failed:" + e7.getMessage();
            }
        } else {
            this.a.a(yVar, boVar, i);
        }
        if (!this.A.contains(yVar.c.a) || this.h == null) {
            return;
        }
        this.h.loadUrl("javascript:flurryadapter.callComplete('" + yVar.c.a + "');");
        this.A.remove(yVar.c.a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, Map map, AdUnit adUnit, bl blVar, int i, int i2) {
        String str2 = "fireEvent(event=" + str + ",params=" + map + ")";
        this.a.a(new bj(str, map, this.v, adUnit, blVar, i), this, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.flurry.android.r
    public final boolean a() {
        if (!(this.o != null)) {
            if (!(this.m != null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(View view) {
        ViewParent parent = view.getParent();
        return parent != null && parent == this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        return ((AdFrame) this.r.get(this.d)).a().intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String c() {
        return ((AdFrame) this.r.get(this.d)).c().toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String d() {
        return ((AdFrame) this.r.get(this.d)).d().d().toString();
    }

    @Override // com.flurry.android.r
    public final void initLayout(Context context) {
        removeAllViews();
        int b = b();
        try {
            Activity activity = (Activity) this.v;
            if (b == 3) {
                activity.setRequestedOrientation(0);
            }
        } catch (ClassCastException e) {
            e.toString();
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        switch (b()) {
            case 1:
            case 2:
                if (this.h == null) {
                    this.h = new WebView(context);
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
                        this.h.loadUrl(f());
                    }
                } else if (b() == 2) {
                    this.h.loadDataWithBaseURL("base://url/", f(), "text/html", "utf-8", "base://url/");
                }
                this.h.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                return;
            case 3:
                if (this.g == null) {
                    this.g = new az(context);
                    this.g.setOnPreparedListener(this);
                    this.g.setOnCompletionListener(this);
                    this.g.setOnErrorListener(this);
                    this.g.setMediaController(new MediaController(context));
                }
                this.g.setVideoURI(Uri.parse(f()));
                bm.a(this.e, "URI: " + Uri.parse(f()).toString());
                this.g.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                addView(this.g);
                ((RelativeLayout) this.g.getParent()).setGravity(17);
                this.f = new ProgressDialog(context);
                this.f.setProgressStyle(0);
                this.f.setMessage("Loading...");
                this.f.setCancelable(true);
                this.f.setOnKeyListener(this);
                this.f.show();
                return;
            default:
                a("renderFailed", Collections.emptyMap(), this.c, this.b, this.d, 0);
                return;
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        a("videoCompleted", Collections.emptyMap(), this.c, this.b, this.d, 0);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
        }
        a("renderFailed", Collections.emptyMap(), this.c, this.b, this.d, 0);
        removeView(this.g);
        return false;
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        String str = "onkey,keycode=" + i + ",event=" + keyEvent.getAction();
        if (dialogInterface == this.f && i == 4 && keyEvent.getAction() == 0) {
            a("adWillClose", Collections.emptyMap(), this.c, this.b, this.d, 0);
            dialogInterface.dismiss();
            return true;
        }
        return false;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (b() == 3) {
            if (this.f.isShowing()) {
                this.f.dismiss();
            }
            if (this.q == null || !this.q.isShowing()) {
                this.g.start();
            }
            a("rendered", Collections.emptyMap(), this.c, this.b, this.d, 0);
            a("videoStart", Collections.emptyMap(), this.c, this.b, this.d, 0);
        }
    }

    @Override // com.flurry.android.r
    public final void stop() {
        if (b() == 3) {
            if (this.f != null && this.f.isShowing()) {
                this.f.dismiss();
            }
            if (this.g != null && this.g.isPlaying()) {
                this.g.stopPlayback();
            }
        }
        if ((b() == 1 || b() == 2) && this.h != null) {
            if (this.m != null && this.m.isShowing()) {
                this.m.hide();
                this.m.setOnDismissListener(null);
                this.m.dismiss();
            }
            this.m = null;
            if (this.j != null && this.i != null) {
                this.i.onHideCustomView();
            }
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
