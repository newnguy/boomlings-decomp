package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.chartboost.sdk.Libraries.a;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class h extends com.chartboost.sdk.b {
    private static int h = 50;
    private static int i = 50;
    private static int j = 30;
    private List k;
    private a.C0000a l;
    private a.C0000a m;
    private a.C0000a n;
    private SparseArray o;

    /* loaded from: classes.dex */
    public class a extends b.AbstractC0003b {
        private static /* synthetic */ int[] k;
        private ImageView d;
        private ImageView e;
        private FrameLayout f;
        private q g;
        private t h;
        private t i;
        private C0005a j;

        /* renamed from: com.chartboost.sdk.impl.h$a$a */
        /* loaded from: classes.dex */
        public class C0005a extends ArrayAdapter {
            Context a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0005a(Context context) {
                super(context, 0, h.this.k);
                a.this = r3;
                this.a = context;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            /* renamed from: a */
            public JSONObject getItem(int i) {
                return (JSONObject) h.this.k.get(i);
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public int getCount() {
                return h.this.k.size();
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(int i, View view, ViewGroup viewGroup) {
                t tVar;
                CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
                if (forcedOrientationDifference.isReverse()) {
                    i = (getCount() - 1) - i;
                }
                final JSONObject item = getItem(i);
                String optString = item.optString("type", "");
                b bVar = null;
                if (view == null) {
                    if (optString.equals("featured")) {
                        bVar = new d(this.a);
                    } else if (optString.equals("regular")) {
                        bVar = new e(this.a);
                    } else if (optString.equals("webview")) {
                        bVar = new i(this.a);
                    }
                    tVar = new t(this.a, (View) bVar);
                } else {
                    tVar = (t) view;
                    bVar = (b) tVar.a();
                }
                bVar.a(item, i);
                c cVar = (c) bVar;
                if (forcedOrientationDifference.isOdd()) {
                    tVar.setLayoutParams(new AbsListView.LayoutParams(bVar.a(), -1));
                } else {
                    tVar.setLayoutParams(new AbsListView.LayoutParams(-1, bVar.a()));
                }
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.chartboost.sdk.impl.h.a.a.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String optString2 = item.optString("deep-link");
                        if (optString2 == null || optString2.equals("")) {
                            optString2 = item.optString("link");
                        }
                        if (h.this.b != null) {
                            h.this.b.a(optString2, item);
                        }
                    }
                };
                cVar.a = onClickListener;
                cVar.setOnClickListener(onClickListener);
                if (bVar instanceof e) {
                    ((e) bVar).b.setOnClickListener(onClickListener);
                }
                return tVar;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private a(Context context) {
            super(context);
            h.this = r6;
            setBackgroundColor(-1842205);
            this.f = new FrameLayout(context);
            this.e = new ImageView(context);
            this.d = new ImageView(context);
            this.g = new q(context, Chartboost.sharedChartboost().getForcedOrientationDifference().isOdd() ? 0 : 1);
            this.g.b().setBackgroundColor(-1842205);
            this.f.setFocusable(false);
            this.e.setFocusable(false);
            this.d.setFocusable(false);
            this.d.setClickable(true);
            this.h = new t(context, this.d);
            this.i = new t(context, this.f);
            addView(this.i);
            this.f.addView(this.e);
            addView(this.h);
            a(this.e);
            a(this.f);
            a(this.d);
            a(this.i);
            a(this.h);
            this.d.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.h.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (h.this.a != null) {
                        h.this.a.a();
                    }
                }
            });
            this.j = new C0005a(context);
        }

        /* synthetic */ a(h hVar, Context context, a aVar) {
            this(context);
        }

        private void a(View view) {
            int i = 200 == getId() ? 201 : 200;
            int i2 = i;
            View findViewById = findViewById(i);
            while (findViewById != null) {
                i2++;
                findViewById = findViewById(i2);
            }
            view.setId(i2);
            view.setSaveEnabled(false);
        }

        static /* synthetic */ int[] c() {
            int[] iArr = k;
            if (iArr == null) {
                iArr = new int[CBOrientation.Difference.valuesCustom().length];
                try {
                    iArr[CBOrientation.Difference.ANGLE_0.ordinal()] = 1;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_180.ordinal()] = 3;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_270.ordinal()] = 4;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[CBOrientation.Difference.ANGLE_90.ordinal()] = 2;
                } catch (NoSuchFieldError e4) {
                }
                k = iArr;
            }
            return iArr;
        }

        @Override // com.chartboost.sdk.b.AbstractC0003b
        protected void a(int i, int i2) {
            if (this.g.a() != null) {
                removeView(this.g.a());
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            final CBOrientation.Difference forcedOrientationDifference = Chartboost.sharedChartboost().getForcedOrientationDifference();
            layoutParams2.width = forcedOrientationDifference.isOdd() ? com.chartboost.sdk.Libraries.d.a(h.h, getContext()) : -1;
            layoutParams2.height = forcedOrientationDifference.isOdd() ? -1 : com.chartboost.sdk.Libraries.d.a(h.h, getContext());
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams2.addRule(11);
                    break;
                case 3:
                    layoutParams2.addRule(12);
                    break;
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(h.this.n.b());
            bitmapDrawable.setTileModeX(Shader.TileMode.REPEAT);
            bitmapDrawable.setTileModeY(Shader.TileMode.CLAMP);
            this.f.setBackgroundDrawable(bitmapDrawable);
            if (h.this.m != null) {
                this.e.setImageBitmap(h.this.m.b());
                layoutParams.width = com.chartboost.sdk.Libraries.d.a(h.this.m.c(), getContext());
                layoutParams.height = com.chartboost.sdk.Libraries.d.a(Math.min(h.h, h.this.m.d()), getContext());
            }
            this.d.setImageBitmap(h.this.l.b());
            layoutParams3.width = com.chartboost.sdk.Libraries.d.a(forcedOrientationDifference.isOdd() ? h.j : h.i, getContext());
            layoutParams3.height = com.chartboost.sdk.Libraries.d.a(forcedOrientationDifference.isOdd() ? h.i : h.j, getContext());
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams3.bottomMargin = com.chartboost.sdk.Libraries.d.a(10, getContext());
                    layoutParams3.rightMargin = com.chartboost.sdk.Libraries.d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(11);
                    layoutParams3.addRule(12);
                    break;
                case 3:
                    layoutParams3.leftMargin = com.chartboost.sdk.Libraries.d.a(10, getContext());
                    layoutParams3.bottomMargin = com.chartboost.sdk.Libraries.d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(12);
                    break;
                case 4:
                    layoutParams3.topMargin = com.chartboost.sdk.Libraries.d.a(10, getContext());
                    layoutParams3.leftMargin = com.chartboost.sdk.Libraries.d.a((h.h - h.j) / 2, getContext());
                    break;
                default:
                    layoutParams3.rightMargin = com.chartboost.sdk.Libraries.d.a(10, getContext());
                    layoutParams3.topMargin = com.chartboost.sdk.Libraries.d.a((h.h - h.j) / 2, getContext());
                    layoutParams3.addRule(11);
                    break;
            }
            layoutParams4.width = -1;
            layoutParams4.height = -1;
            switch (c()[forcedOrientationDifference.ordinal()]) {
                case 2:
                    layoutParams4.addRule(0, this.i.getId());
                    break;
                case 3:
                    layoutParams4.addRule(2, this.i.getId());
                    break;
                case 4:
                    layoutParams4.addRule(1, this.i.getId());
                    break;
                default:
                    layoutParams4.addRule(3, this.i.getId());
                    break;
            }
            this.g.a(forcedOrientationDifference.isOdd() ? 0 : 1);
            a(this.g.a());
            this.g.a(this.j);
            addView(this.g.a(), layoutParams4);
            if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_180) {
                this.g.b().setGravity(80);
            } else if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_90) {
                this.g.b().setGravity(5);
            } else {
                this.g.b().setGravity(0);
            }
            this.i.setLayoutParams(layoutParams2);
            this.e.setLayoutParams(layoutParams);
            this.e.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.h.setLayoutParams(layoutParams3);
            this.d.setScaleType(ImageView.ScaleType.FIT_CENTER);
            post(new Runnable() { // from class: com.chartboost.sdk.impl.h.a.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        a.this.a = true;
                        a.this.requestLayout();
                        a.this.g.a().requestLayout();
                        a.this.g.b().requestLayout();
                        a.this.a = false;
                        if (forcedOrientationDifference == CBOrientation.Difference.ANGLE_180 || forcedOrientationDifference == CBOrientation.Difference.ANGLE_90) {
                            a.this.g.c();
                        }
                    } catch (Exception e) {
                    }
                }
            });
        }

        @Override // com.chartboost.sdk.b.AbstractC0003b
        public void b() {
            super.b();
            this.d = null;
            this.e = null;
            this.g = null;
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        int a();

        void a(JSONObject jSONObject, int i);
    }

    public h(com.chartboost.sdk.impl.a aVar) {
        super(aVar);
        this.e = 3;
        this.k = new ArrayList();
    }

    @Override // com.chartboost.sdk.b
    protected b.AbstractC0003b a(Context context) {
        return new a(this, context, null);
    }

    @Override // com.chartboost.sdk.b
    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        JSONArray optJSONArray = jSONObject.optJSONArray("cells");
        if (optJSONArray == null) {
            if (this.d != null) {
                this.d.a();
                return;
            }
            return;
        }
        this.o = new SparseArray();
        e.b bVar = new e.b() { // from class: com.chartboost.sdk.impl.h.1
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle) {
                h.this.o.put(bundle.getInt("index"), c0000a);
                h.this.a(c0000a);
            }
        };
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            this.k.add(optJSONObject);
            String optString = optJSONObject.optString("type", "");
            if (optString.equals("regular")) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("assets");
                if (optJSONObject2 != null) {
                    this.e++;
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", i2);
                    a(optJSONObject2, "icon", bVar, bundle);
                }
            } else if (optString.equals("featured")) {
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("assets");
                if (optJSONObject3 != null) {
                    this.e++;
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("index", i2);
                    a(optJSONObject3, "portrait", bVar, bundle2);
                    this.e++;
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("index", i2);
                    a(optJSONObject3, "landscape", bVar, bundle3);
                }
            } else {
                optString.equals("webview");
            }
        }
        e.b bVar2 = new e.b() { // from class: com.chartboost.sdk.impl.h.2
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle4) {
                h.this.l = c0000a;
                h.this.a(c0000a);
            }
        };
        e.b bVar3 = new e.b() { // from class: com.chartboost.sdk.impl.h.3
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle4) {
                h.this.m = c0000a;
                h.this.a(c0000a);
            }
        };
        e.b bVar4 = new e.b() { // from class: com.chartboost.sdk.impl.h.4
            @Override // com.chartboost.sdk.Libraries.e.b
            public void a(a.C0000a c0000a, Bundle bundle4) {
                h.this.n = c0000a;
                h.this.a(c0000a);
            }
        };
        a("close", bVar2);
        a("header-center", bVar3);
        a("header-tile", bVar4);
    }

    @Override // com.chartboost.sdk.b
    public void c() {
        super.c();
        this.k = null;
        this.l = null;
        this.n = null;
        this.m = null;
    }
}
