package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.b;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b extends com.chartboost.sdk.b {
    public a.C0000a h;
    public a.C0000a i;
    public a.C0000a j;
    public a.C0000a k;
    public a.C0000a l;

    /* loaded from: classes.dex */
    public class a extends b.AbstractC0003b {
        public ImageView c;
        public ImageView d;
        public Button e;
        public t f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private a(Context context) {
            super(context);
            b.this = r4;
            setBackgroundColor(0);
            setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.f = new t(context);
            this.d = new ImageView(context);
            this.e = new Button(context);
            this.c = new ImageView(context);
            this.e.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.b.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (b.this.a != null) {
                        b.this.a.a();
                    }
                }
            });
            this.d.setClickable(true);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.b.a.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (b.this.b != null) {
                        b.this.b.a(null, null);
                    }
                }
            });
            this.f.a(this.c);
            this.f.a(this.d);
            this.f.a(this.e);
            addView(this.f, new RelativeLayout.LayoutParams(-1, -1));
        }

        /* synthetic */ a(b bVar, Context context, a aVar) {
            this(context);
        }

        private Point a(String str) {
            JSONObject optJSONObject;
            JSONObject optJSONObject2 = b.this.f.optJSONObject(str);
            return (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("offset")) == null) ? new Point(0, 0) : new Point(optJSONObject.optInt("x", 0), optJSONObject.optInt("y", 0));
        }

        @Override // com.chartboost.sdk.b.AbstractC0003b
        protected void a(int i, int i2) {
            boolean isPortrait = Chartboost.sharedChartboost().orientation().isPortrait();
            a.C0000a c0000a = isPortrait ? b.this.h : b.this.i;
            a.C0000a c0000a2 = isPortrait ? b.this.j : b.this.k;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            float max = isPortrait ? Math.max(320.0f / i, 480.0f / i2) : Math.max(320.0f / i2, 480.0f / i);
            layoutParams.width = (int) (c0000a2.c() / max);
            layoutParams.height = (int) (c0000a2.d() / max);
            Point a = a(isPortrait ? "frame-portrait" : "frame-landscape");
            layoutParams.leftMargin = Math.round(((i - layoutParams.width) / 2.0f) + (a.x / max));
            layoutParams.topMargin = Math.round((a.y / max) + ((i2 - layoutParams.height) / 2.0f));
            this.d.setId(100);
            layoutParams2.width = (int) (c0000a.c() / max);
            layoutParams2.height = (int) (c0000a.d() / max);
            Point a2 = a(isPortrait ? "ad-portrait" : "ad-landscape");
            layoutParams2.leftMargin = Math.round(((i - layoutParams2.width) / 2.0f) + (a2.x / max));
            layoutParams2.topMargin = Math.round((a2.y / max) + ((i2 - layoutParams2.height) / 2.0f));
            layoutParams3.width = (int) (b.this.l.c() / max);
            layoutParams3.height = (int) (b.this.l.d() / max);
            Point a3 = a("close");
            layoutParams3.leftMargin = layoutParams2.leftMargin + layoutParams2.width + Math.round((a3.x / max) - com.chartboost.sdk.Libraries.d.b(10, getContext()));
            layoutParams3.topMargin = Math.round((a3.y / max) - com.chartboost.sdk.Libraries.d.b(10, getContext())) + (layoutParams2.topMargin - layoutParams3.height);
            this.c.setLayoutParams(layoutParams);
            this.d.setLayoutParams(layoutParams2);
            this.e.setLayoutParams(layoutParams3);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(c0000a2.b());
            this.c.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.c.setImageDrawable(bitmapDrawable);
            BitmapDrawable bitmapDrawable2 = new BitmapDrawable(c0000a.b());
            this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.d.setImageDrawable(bitmapDrawable2);
            this.e.setBackgroundDrawable(new BitmapDrawable(b.this.l.b()));
        }

        @Override // com.chartboost.sdk.b.AbstractC0003b
        public void b() {
            super.b();
            this.d = null;
            this.c = null;
            this.e = null;
        }
    }

    public b(com.chartboost.sdk.impl.a aVar) {
        super(aVar);
        this.e = 5;
        this.j = null;
        this.k = null;
        this.h = null;
        this.i = null;
        this.l = null;
    }

    @Override // com.chartboost.sdk.b
    protected b.AbstractC0003b a(Context context) {
        return new a(this, context, null);
    }

    @Override // com.chartboost.sdk.b
    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        e.b bVar = new e.b() { // from class: com.chartboost.sdk.impl.b.1
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle) {
                b.this.i = c0000a;
                b.this.a(c0000a);
            }
        };
        e.b bVar2 = new e.b() { // from class: com.chartboost.sdk.impl.b.2
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle) {
                b.this.h = c0000a;
                b.this.a(c0000a);
            }
        };
        e.b bVar3 = new e.b() { // from class: com.chartboost.sdk.impl.b.3
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle) {
                b.this.k = c0000a;
                b.this.a(c0000a);
            }
        };
        e.b bVar4 = new e.b() { // from class: com.chartboost.sdk.impl.b.4
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle) {
                b.this.j = c0000a;
                b.this.a(c0000a);
            }
        };
        e.b bVar5 = new e.b() { // from class: com.chartboost.sdk.impl.b.5
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle) {
                b.this.l = c0000a;
                b.this.a(c0000a);
            }
        };
        a("ad-landscape", bVar, true);
        a("ad-portrait", bVar2, true);
        a("frame-landscape", bVar3);
        a("frame-portrait", bVar4);
        a("close", bVar5);
    }

    @Override // com.chartboost.sdk.b
    public void c() {
        super.c();
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
        this.k = null;
        this.j = null;
        this.l = null;
    }
}
