package com.flurry.android;

import android.os.AsyncTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k extends AsyncTask {
    private /* synthetic */ bo a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(bo boVar) {
        this.a = boVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036 A[Catch: Throwable -> 0x005b, TRY_LEAVE, TryCatch #2 {Throwable -> 0x0050, blocks: (B:3:0x0001, B:9:0x002b, B:18:0x004c, B:10:0x002e, B:12:0x0036, B:17:0x0045), top: B:33:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Void a() {
        /*
            r5 = this;
            r2 = 0
            com.flurry.android.bo r0 = r5.a     // Catch: java.lang.Throwable -> L50
            java.io.File r0 = com.flurry.android.bo.a(r0)     // Catch: java.lang.Throwable -> L50
            boolean r0 = r0.exists()     // Catch: java.lang.Throwable -> L50
            if (r0 == 0) goto L42
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L43
            com.flurry.android.bo r1 = r5.a     // Catch: java.lang.Throwable -> L43
            java.io.File r1 = com.flurry.android.bo.a(r1)     // Catch: java.lang.Throwable -> L43
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L43
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L43
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L43
            int r0 = r1.readUnsignedShort()     // Catch: java.lang.Throwable -> L66
            r3 = 46586(0xb5fa, float:6.5281E-41)
            if (r0 != r3) goto L2b
            com.flurry.android.bo r0 = r5.a     // Catch: java.lang.Throwable -> L66
            r0.a(r1)     // Catch: java.lang.Throwable -> L66
        L2b:
            com.flurry.android.ac.a(r1)     // Catch: java.lang.Throwable -> L50
        L2e:
            com.flurry.android.bo r0 = r5.a     // Catch: java.lang.Throwable -> L5b
            boolean r0 = com.flurry.android.bo.b(r0)     // Catch: java.lang.Throwable -> L5b
            if (r0 != 0) goto L42
            com.flurry.android.bo r0 = r5.a     // Catch: java.lang.Throwable -> L5b
            java.io.File r0 = com.flurry.android.bo.a(r0)     // Catch: java.lang.Throwable -> L5b
            boolean r0 = r0.delete()     // Catch: java.lang.Throwable -> L5b
            if (r0 != 0) goto L42
        L42:
            return r2
        L43:
            r0 = move-exception
            r1 = r2
        L45:
            java.lang.String r3 = com.flurry.android.bo.a     // Catch: java.lang.Throwable -> L64
            java.lang.String r4 = "Error when loading persistent file"
            com.flurry.android.bm.b(r3, r4, r0)     // Catch: java.lang.Throwable -> L64
            com.flurry.android.ac.a(r1)     // Catch: java.lang.Throwable -> L50
            goto L2e
        L50:
            r0 = move-exception
            r0.printStackTrace()
            goto L42
        L55:
            r0 = move-exception
            r1 = r2
        L57:
            com.flurry.android.ac.a(r1)     // Catch: java.lang.Throwable -> L50
            throw r0     // Catch: java.lang.Throwable -> L50
        L5b:
            r0 = move-exception
            java.lang.String r1 = com.flurry.android.bo.a     // Catch: java.lang.Throwable -> L50
            java.lang.String r3 = ""
            com.flurry.android.bm.b(r1, r3, r0)     // Catch: java.lang.Throwable -> L50
            goto L42
        L64:
            r0 = move-exception
            goto L57
        L66:
            r0 = move-exception
            goto L45
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.android.k.a():java.lang.Void");
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return a();
    }
}
