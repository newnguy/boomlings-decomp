package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.chartboost.sdk.b;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class v extends com.chartboost.sdk.b {
    private String h;

    /* loaded from: classes.dex */
    public class a extends b.AbstractC0003b {
        public WebView c;

        public a(Context context, String str) {
            super(context);
            setFocusable(false);
            this.c = new b(context);
            this.c.setWebViewClient(new c(v.this));
            addView(this.c);
            this.c.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
        }

        @Override // com.chartboost.sdk.b.AbstractC0003b
        protected void a(int i, int i2) {
        }
    }

    /* loaded from: classes.dex */
    class b extends WebView {
        public b(Context context) {
            super(context);
            setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            setBackgroundColor(0);
            getSettings().setJavaScriptEnabled(true);
        }

        @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            if ((i == 4 || i == 3) && v.this.a != null) {
                v.this.a.a();
            }
            return super.onKeyDown(i, keyEvent);
        }
    }

    /* loaded from: classes.dex */
    class c extends WebViewClient {
        private v b;

        public c(v vVar) {
            this.b = vVar;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.b == null || this.b.c == null) {
                return;
            }
            this.b.c.a();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (this.b.d != null) {
                this.b.d.a();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00a3  */
        @Override // android.webkit.WebViewClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean shouldOverrideUrlLoading(android.webkit.WebView r8, java.lang.String r9) {
            /*
                r7 = this;
                r2 = 0
                r5 = 3
                r6 = 4
                r0 = 0
                java.lang.String r1 = ""
                java.net.URI r1 = new java.net.URI     // Catch: java.lang.Exception -> L36
                r1.<init>(r9)     // Catch: java.lang.Exception -> L36
                java.lang.String r1 = r1.getScheme()     // Catch: java.lang.Exception -> L36
                java.lang.String r3 = "chartboost"
                boolean r1 = r1.equals(r3)
                if (r1 == 0) goto L5d
                java.lang.String r1 = "/"
                java.lang.String[] r1 = r9.split(r1)
                int r3 = r1.length
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                int r4 = r3.intValue()
                if (r4 >= r5) goto L45
                com.chartboost.sdk.impl.v r1 = r7.b
                com.chartboost.sdk.b$a r1 = r1.a
                if (r1 == 0) goto L35
                com.chartboost.sdk.impl.v r1 = r7.b
                com.chartboost.sdk.b$a r1 = r1.a
                r1.a()
            L35:
                return r0
            L36:
                r1 = move-exception
                com.chartboost.sdk.impl.v r1 = r7.b
                com.chartboost.sdk.b$a r1 = r1.a
                if (r1 == 0) goto L35
                com.chartboost.sdk.impl.v r1 = r7.b
                com.chartboost.sdk.b$a r1 = r1.a
                r1.a()
                goto L35
            L45:
                r4 = 2
                r4 = r1[r4]
                java.lang.String r5 = "close"
                boolean r5 = r4.equals(r5)
                if (r5 == 0) goto L5f
                com.chartboost.sdk.impl.v r0 = r7.b
                com.chartboost.sdk.b$a r0 = r0.a
                if (r0 == 0) goto L5d
                com.chartboost.sdk.impl.v r0 = r7.b
                com.chartboost.sdk.b$a r0 = r0.a
                r0.a()
            L5d:
                r0 = 1
                goto L35
            L5f:
                java.lang.String r5 = "link"
                boolean r4 = r4.equals(r5)
                if (r4 == 0) goto L5d
                int r4 = r3.intValue()
                if (r4 >= r6) goto L7b
                com.chartboost.sdk.impl.v r1 = r7.b
                com.chartboost.sdk.b$a r1 = r1.a
                if (r1 == 0) goto L35
                com.chartboost.sdk.impl.v r1 = r7.b
                com.chartboost.sdk.b$a r1 = r1.a
                r1.a()
                goto L35
            L7b:
                r0 = 3
                r0 = r1[r0]     // Catch: java.lang.Exception -> Lab
                java.lang.String r4 = "UTF-8"
                java.lang.String r0 = java.net.URLDecoder.decode(r0, r4)     // Catch: java.lang.Exception -> Lab
                int r3 = r3.intValue()     // Catch: java.lang.Exception -> Lb3
                if (r3 >= r6) goto Lb1
                org.json.JSONTokener r3 = new org.json.JSONTokener     // Catch: java.lang.Exception -> Lb3
                r4 = 4
                r1 = r1[r4]     // Catch: java.lang.Exception -> Lb3
                java.lang.String r4 = "UTF-8"
                java.lang.String r1 = java.net.URLDecoder.decode(r1, r4)     // Catch: java.lang.Exception -> Lb3
                r3.<init>(r1)     // Catch: java.lang.Exception -> Lb3
                org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lb3
                r1.<init>(r3)     // Catch: java.lang.Exception -> Lb3
            L9d:
                com.chartboost.sdk.impl.v r2 = r7.b
                com.chartboost.sdk.b$c r2 = r2.b
                if (r2 == 0) goto L5d
                com.chartboost.sdk.impl.v r2 = r7.b
                com.chartboost.sdk.b$c r2 = r2.b
                r2.a(r0, r1)
                goto L5d
            Lab:
                r0 = move-exception
                r1 = r0
                r0 = r2
            Lae:
                r1.printStackTrace()
            Lb1:
                r1 = r2
                goto L9d
            Lb3:
                r1 = move-exception
                goto Lae
            */
            throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.v.c.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }
    }

    public v(com.chartboost.sdk.impl.a aVar) {
        super(aVar);
        this.h = null;
    }

    @Override // com.chartboost.sdk.b
    protected b.AbstractC0003b a(Context context) {
        return new a(context, this.h);
    }

    @Override // com.chartboost.sdk.b
    public void a(JSONObject jSONObject) {
        String optString = jSONObject.optString("html");
        if (optString == null) {
            return;
        }
        this.h = optString;
        a();
    }
}
