package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.flurry.android.FlurryAgent;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class InstallReceiver extends BroadcastReceiver {
    private final Handler a;
    private File b = null;

    public InstallReceiver() {
        HandlerThread handlerThread = new HandlerThread("InstallReceiver");
        handlerThread.start();
        this.a = new Handler(handlerThread.getLooper());
    }

    public static Map a(String str) {
        HashMap hashMap = new HashMap();
        String[] split = str.split("&");
        int length = split.length;
        for (int i = 0; i < length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length != 2) {
                bm.a("InstallReceiver", "Invalid referrer Element: " + split[i] + " in referrer tag " + str);
            } else {
                String decode = URLDecoder.decode(split2[0]);
                String decode2 = URLDecoder.decode(split2[1]);
                if (hashMap.get(decode) == null) {
                    hashMap.put(decode, new ArrayList());
                }
                ((List) hashMap.get(decode)).add(decode2);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (hashMap.get("utm_source") == null) {
            sb.append("Campaign Source is missing.\n");
        }
        if (hashMap.get("utm_medium") == null) {
            sb.append("Campaign Medium is missing.\n");
        }
        if (hashMap.get("utm_campaign") == null) {
            sb.append("Campaign Name is missing.\n");
        }
        if (sb.length() > 0) {
            Log.w("Detected missing referrer keys", sb.toString());
        }
        return hashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.io.File r7) {
        /*
            r0 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3e
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L3e
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L42
            r2.<init>()     // Catch: java.lang.Throwable -> L42
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L1f
        Lf:
            int r4 = r3.read(r1)     // Catch: java.lang.Throwable -> L1f
            if (r4 <= 0) goto L31
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L1f
            r6 = 0
            r5.<init>(r1, r6, r4)     // Catch: java.lang.Throwable -> L1f
            r2.append(r5)     // Catch: java.lang.Throwable -> L1f
            goto Lf
        L1f:
            r1 = move-exception
        L20:
            java.lang.String r4 = "InstallReceiver"
            java.lang.String r5 = "Error when loading persistent file"
            com.flurry.android.bm.b(r4, r5, r1)     // Catch: java.lang.Throwable -> L3c
            com.flurry.android.ac.a(r3)
        L2a:
            if (r2 == 0) goto L30
            java.lang.String r0 = r2.toString()
        L30:
            return r0
        L31:
            com.flurry.android.ac.a(r3)
            goto L2a
        L35:
            r1 = move-exception
            r3 = r0
            r0 = r1
        L38:
            com.flurry.android.ac.a(r3)
            throw r0
        L3c:
            r0 = move-exception
            goto L38
        L3e:
            r1 = move-exception
            r2 = r0
            r3 = r0
            goto L20
        L42:
            r1 = move-exception
            r2 = r0
            goto L20
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.android.InstallReceiver.b(java.io.File):java.lang.String");
    }

    private synchronized void b(String str) {
        this.a.post(new bc(this, str));
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        bm.c("InstallReceiver", "Received an Install nofication of " + intent.getAction());
        this.b = context.getFileStreamPath(".flurryinstallreceiver.");
        bm.c("InstallReceiver", "fInstallReceiverFile is " + this.b);
        if (FlurryAgent.isCaptureUncaughtExceptions()) {
            Thread.setDefaultUncaughtExceptionHandler(new FlurryAgent.FlurryDefaultExceptionHandler());
        }
        String string = intent.getExtras().getString("referrer");
        bm.c("InstallReceiver", "Received an Install referrer of " + string);
        if (string == null || !"com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
            bm.c("InstallReceiver", "referrer is null");
            return;
        }
        if (!string.contains("=")) {
            bm.c("InstallReceiver", "referrer is before decoding: " + string);
            string = URLDecoder.decode(string);
            bm.c("InstallReceiver", "referrer is: " + string);
        }
        b(string);
    }
}
