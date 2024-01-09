package com.flurry.android;

import android.content.Context;
import android.os.AsyncTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ai extends AsyncTask {
    private final String a = getClass().getSimpleName();
    private Context b;
    private String c;
    private /* synthetic */ bo d;

    public ai(bo boVar, Context context, String str) {
        this.d = boVar;
        this.b = context;
        this.c = str;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:7|(2:9|(3:11|(1:33)(2:13|(3:30|31|32)(2:15|(2:26|27)(2:19|(2:21|22)(1:24))))|23))|34|35|36|38|23) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b0, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b1, code lost:
        r2.getMessage();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a() {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = 5
            if (r1 >= r2) goto L5d
            java.lang.String r2 = r7.c     // Catch: java.lang.Throwable -> La5
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch: java.lang.Throwable -> La5
            java.lang.String r2 = r2.getScheme()     // Catch: java.lang.Throwable -> La5
            java.lang.String r3 = "http"
            boolean r2 = r2.equals(r3)     // Catch: java.lang.Throwable -> La5
            if (r2 == 0) goto Lb5
            android.content.Context r2 = r7.b     // Catch: java.lang.Throwable -> La5
            boolean r2 = com.flurry.android.bo.c(r2)     // Catch: java.lang.Throwable -> La5
            r3 = 1
            if (r2 != r3) goto Laa
            android.content.Context r2 = r7.b     // Catch: java.lang.Throwable -> La5
            boolean r2 = com.flurry.android.bo.d(r2)     // Catch: java.lang.Throwable -> La5
            if (r2 != 0) goto Laa
            com.flurry.android.bo r2 = r7.d     // Catch: java.lang.Throwable -> La5
            com.flurry.android.be r2 = com.flurry.android.bo.c(r2)     // Catch: java.lang.Throwable -> La5
            java.lang.String r3 = r7.c     // Catch: java.lang.Throwable -> La5
            r4 = 10000(0x2710, float:1.4013E-41)
            r5 = 15000(0x3a98, float:2.102E-41)
            r6 = 0
            org.apache.http.HttpResponse r2 = com.flurry.android.ac.a(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> La5
            if (r2 == 0) goto L8e
            org.apache.http.StatusLine r3 = r2.getStatusLine()     // Catch: java.lang.Throwable -> La5
            int r3 = r3.getStatusCode()     // Catch: java.lang.Throwable -> La5
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 != r4) goto L5e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La5
            r1.<init>()     // Catch: java.lang.Throwable -> La5
            java.lang.String r2 = "Redirect URL found for: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> La5
            java.lang.String r2 = r7.c     // Catch: java.lang.Throwable -> La5
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> La5
            r1.toString()     // Catch: java.lang.Throwable -> La5
            java.lang.String r0 = r7.c     // Catch: java.lang.Throwable -> La5
        L5d:
            return r0
        L5e:
            r4 = 300(0x12c, float:4.2E-43)
            if (r3 < r4) goto L92
            r4 = 400(0x190, float:5.6E-43)
            if (r3 >= r4) goto L92
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La5
            r3.<init>()     // Catch: java.lang.Throwable -> La5
            java.lang.String r4 = "NumRedirects: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> La5
            int r4 = r1 + 1
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> La5
            r3.toString()     // Catch: java.lang.Throwable -> La5
            java.lang.String r3 = "Location"
            boolean r3 = r2.containsHeader(r3)     // Catch: java.lang.Throwable -> La5
            if (r3 == 0) goto L8e
            java.lang.String r3 = "Location"
            org.apache.http.Header r2 = r2.getFirstHeader(r3)     // Catch: java.lang.Throwable -> La5
            java.lang.String r2 = r2.getValue()     // Catch: java.lang.Throwable -> La5
            r7.c = r2     // Catch: java.lang.Throwable -> La5
        L8e:
            int r1 = r1 + 1
            goto L2
        L92:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La5
            r1.<init>()     // Catch: java.lang.Throwable -> La5
            java.lang.String r2 = "Bad Response status code: "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> La5
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.lang.Throwable -> La5
            r1.toString()     // Catch: java.lang.Throwable -> La5
            goto L5d
        La5:
            r1 = move-exception
            r1.printStackTrace()
            goto L5d
        Laa:
            r2 = 100
            java.lang.Thread.sleep(r2)     // Catch: java.lang.Throwable -> La5 java.lang.InterruptedException -> Lb0
            goto L8e
        Lb0:
            r2 = move-exception
            r2.getMessage()     // Catch: java.lang.Throwable -> La5
            goto L8e
        Lb5:
            android.content.Context r1 = r7.b     // Catch: java.lang.Throwable -> La5
            java.lang.String r2 = r7.c     // Catch: java.lang.Throwable -> La5
            java.lang.String r3 = "android.intent.action.VIEW"
            boolean r1 = com.flurry.android.bo.a(r1, r2, r3)     // Catch: java.lang.Throwable -> La5
            if (r1 == 0) goto L5d
            java.lang.String r0 = r7.c     // Catch: java.lang.Throwable -> La5
            goto L5d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.android.ai.a():java.lang.String");
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }
}
