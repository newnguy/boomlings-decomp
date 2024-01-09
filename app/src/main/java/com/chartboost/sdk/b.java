package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.impl.a;
import com.chartboost.sdk.impl.s;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class b {
    protected JSONObject f;
    protected com.chartboost.sdk.impl.a g;
    private int h;
    private int i;
    private int j;
    public a a = null;
    public c b = null;
    public a c = null;
    public a d = null;
    protected int e = 0;
    private AbstractC0003b k = null;

    /* loaded from: classes.dex */
    public interface a {
        void a();
    }

    /* renamed from: com.chartboost.sdk.b$b */
    /* loaded from: classes.dex */
    public abstract class AbstractC0003b extends RelativeLayout implements s.a {
        protected boolean a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AbstractC0003b(Context context) {
            super(context);
            b.this = r2;
            this.a = false;
            setFocusableInTouchMode(true);
            requestFocus();
            setOnTouchListener(new View.OnTouchListener() { // from class: com.chartboost.sdk.b.b.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }

        private boolean b(int i, int i2) {
            try {
                a(i, i2);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override // com.chartboost.sdk.impl.s.a
        public void a() {
            a((Activity) getContext());
        }

        protected abstract void a(int i, int i2);

        public boolean a(Activity activity) {
            int i;
            int i2;
            try {
                i2 = getWidth();
                i = getHeight();
                if (i2 == 0 || i == 0) {
                    View findViewById = activity.getWindow().findViewById(16908290);
                    if (findViewById == null) {
                        findViewById = activity.getWindow().getDecorView();
                    }
                    i2 = findViewById.getWidth();
                    i = findViewById.getHeight();
                }
            } catch (Exception e) {
                i = 0;
                i2 = 0;
            }
            if (i2 == 0 || i == 0) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                i2 = displayMetrics.widthPixels;
                i = displayMetrics.heightPixels;
            }
            if (!Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
                int i3 = i;
                i = i2;
                i2 = i3;
            }
            return b(i, i2);
        }

        public void b() {
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            if (this.a) {
                return;
            }
            if (Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd()) {
                b(i2, i);
            } else {
                b(i, i2);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(String str, JSONObject jSONObject);
    }

    public b(com.chartboost.sdk.impl.a aVar) {
        this.g = aVar;
    }

    protected abstract AbstractC0003b a(Context context);

    public void a(a.C0000a c0000a) {
        if (c0000a != null) {
            this.h++;
        }
        this.i++;
        if (this.i != this.e || a() || this.d == null) {
            return;
        }
        this.d.a();
    }

    public void a(String str, e.b bVar) {
        a(str, bVar, false);
    }

    public void a(String str, e.b bVar, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("paramNoMemoryCache", z);
        a(this.f, str, bVar, bundle);
    }

    public void a(JSONObject jSONObject) {
        this.i = 0;
        this.j = 0;
        this.h = 0;
        this.f = jSONObject.optJSONObject("assets");
        if (this.f != null || this.d == null) {
            return;
        }
        this.d.a();
    }

    public void a(JSONObject jSONObject, String str, e.b bVar, Bundle bundle) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            a((a.C0000a) null);
            return;
        }
        this.j++;
        e.a().a(optJSONObject.optString("url"), optJSONObject.optString("checksum"), bVar, null, bundle);
    }

    public boolean a() {
        if (this.h != this.j) {
            return false;
        }
        if (this.c != null) {
            this.c.a();
        }
        return true;
    }

    public boolean b() {
        if (this.g.c != a.b.CBImpressionStateWaitingForDisplay) {
            return false;
        }
        Chartboost.sharedChartboost().a(this.g);
        Activity b = Chartboost.sharedChartboost().b();
        if (b == null) {
            this.k = null;
            return false;
        }
        this.k = a(b);
        if (this.k.a(b)) {
            return true;
        }
        this.k = null;
        return false;
    }

    public void c() {
        e();
        this.c = null;
        this.d = null;
        this.b = null;
        this.a = null;
        this.f = null;
    }

    public AbstractC0003b d() {
        return this.k;
    }

    public void e() {
        if (this.k != null) {
            this.k.b();
        }
        this.k = null;
    }
}
