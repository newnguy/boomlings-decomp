package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class r extends RelativeLayout {
    bo a;
    bl b;
    AdUnit c;
    int d;
    private Context e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(Context context) {
        super(context);
        a(context, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(Context context, bo boVar, bl blVar) {
        super(context);
        a(context, boVar, blVar);
    }

    private void a(Context context, bo boVar, bl blVar) {
        this.e = context;
        this.a = boVar;
        this.b = blVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ViewGroup viewGroup, x xVar) {
        if (this.c == null || this.c.c().size() < 1) {
            return;
        }
        AdSpaceLayout d = ((AdFrame) this.c.c().get(0)).d();
        LinearLayout.LayoutParams layoutParams = null;
        if (viewGroup instanceof LinearLayout) {
            layoutParams = new LinearLayout.LayoutParams(ac.a(this.e, d.a().intValue()), ac.a(this.e, d.b().intValue()));
        } else if (viewGroup instanceof AbsoluteLayout) {
            layoutParams = new AbsoluteLayout.LayoutParams(ac.a(this.e, d.a().intValue()), ac.a(this.e, d.b().intValue()), 0, 0);
        } else if (viewGroup instanceof FrameLayout) {
            layoutParams = new FrameLayout.LayoutParams(ac.a(this.e, d.a().intValue()), ac.a(this.e, d.b().intValue()), 0);
        } else if (viewGroup instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(ac.a(this.e, d.a().intValue()), ac.a(this.e, d.b().intValue()));
            String[] split = d.e().toString().split("-");
            layoutParams = layoutParams2;
            if (split.length == 2) {
                if (split[0].equals(Constants.ALIGN_BOTTOM)) {
                    layoutParams2.addRule(12);
                } else if (split[0].equals(Constants.ALIGN_TOP)) {
                    layoutParams2.addRule(10);
                } else if (split[0].equals(Constants.ALIGN_MIDDLE)) {
                    layoutParams2.addRule(15);
                }
                if (split[1].equals(Constants.ALIGN_CENTER)) {
                    layoutParams2.addRule(14);
                    layoutParams = layoutParams2;
                } else if (split[1].equals(Constants.ALIGN_LEFT)) {
                    layoutParams2.addRule(9);
                    layoutParams = layoutParams2;
                } else {
                    layoutParams = layoutParams2;
                    if (split[1].equals(Constants.ALIGN_RIGHT)) {
                        layoutParams2.addRule(11);
                        layoutParams = layoutParams2;
                    }
                }
            }
        }
        if (layoutParams != null) {
            xVar.setLayoutParams(layoutParams);
        } else {
            String str = "Ad space layout and alignment from the server is being ignored for ViewGroup subclass " + viewGroup.getClass().getSimpleName();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, Map map) {
        String str2 = "AppSpotBannerView.onEvent " + str;
        this.a.a(this.b, str, true, map);
        if (this.c != null) {
            this.a.a(new bj(str, map, this.e, this.c, this.b, this.d), this.a, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return !((AdFrame) this.c.c().get(this.d)).d().d().toString().equals(AdCreative.kFormatTakeover);
    }

    public abstract void initLayout(Context context);

    public void stop() {
    }
}
