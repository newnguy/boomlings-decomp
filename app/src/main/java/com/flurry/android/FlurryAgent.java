package com.flurry.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ViewGroup;
import com.flurry.org.apache.avro.file.DataFileConstants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* loaded from: classes.dex */
public final class FlurryAgent implements LocationListener {
    private static Map Z;
    private static final String[] a = {"9774d56d682e549c", "dead00beef"};
    private static volatile String b = null;
    private static volatile String c = "http://data.flurry.com/aap.do";
    private static volatile String d = "https://data.flurry.com/aap.do";
    private static final FlurryAgent e = new FlurryAgent();
    private static long f = 10000;
    private static boolean g = true;
    private static boolean h = false;
    private static boolean i = false;
    private static boolean j = true;
    private static Criteria k = null;
    private static boolean l = false;
    private static AtomicInteger n = new AtomicInteger(0);
    private static AtomicInteger o = new AtomicInteger(0);
    private List A;
    private LocationManager B;
    private String C;
    private boolean E;
    private long F;
    private long H;
    private long I;
    private long J;
    private Long P;
    private int Q;
    private android.location.Location R;
    private boolean U;
    private int V;
    private int X;
    private Map Y;
    private Map aa;
    private final Handler m;
    private File p;
    private long u;
    private String w;
    private String x;
    private String y;
    private File q = null;
    private File r = null;
    private volatile boolean s = false;
    private volatile boolean t = false;
    private Map v = new WeakHashMap();
    private boolean z = true;
    private Map D = new HashMap();
    private List G = new ArrayList();
    private String K = "";
    private String L = "";
    private byte M = -1;
    private String N = "";
    private byte O = -1;
    private Map S = new HashMap();
    private List T = new ArrayList();
    private List W = new ArrayList();
    private bo ab = new bo();
    private be ac = new be();
    private ah ad = new ah();

    /* loaded from: classes.dex */
    public class FlurryDefaultExceptionHandler implements Thread.UncaughtExceptionHandler {
        private Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            try {
                FlurryAgent.e.a(th);
            } catch (Throwable th2) {
                bm.b("FlurryAgent", "", th2);
            }
            if (this.a != null) {
                this.a.uncaughtException(thread, th);
            }
        }
    }

    private FlurryAgent() {
        HandlerThread handlerThread = new HandlerThread("FlurryAgent");
        handlerThread.start();
        this.m = new Handler(handlerThread.getLooper());
    }

    private static double a(double d2) {
        return Math.round(d2 * 1000.0d) / 1000.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(java.io.File r7) {
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
            java.lang.String r4 = "FlurryAgent"
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
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.android.FlurryAgent.a(java.io.File):java.lang.String");
    }

    private void a(Context context) {
        if (l) {
            return;
        }
        if (!this.ab.g()) {
            bm.a("FlurryAgent", "Initializing Flurry Ads");
            p pVar = new p();
            pVar.a = this.w;
            pVar.b = this.ac;
            pVar.c = this.ad;
            pVar.d = this.K;
            pVar.e = this.L;
            this.ab.a(context, pVar);
            this.ab.l();
            if (Z != null) {
                this.ab.c(Z);
            }
            bm.a("FlurryAgent", "Flurry Ads initialized");
        }
        this.ab.a(context, this.H, this.I);
        l = true;
    }

    private synchronized void a(Context context, String str) {
        Bundle extras;
        String l2;
        if (this.w != null && !this.w.equals(str)) {
            bm.b("FlurryAgent", "onStartSession called with different api keys: " + this.w + " and " + str);
        }
        if (((Context) this.v.put(context, context)) != null) {
            bm.d("FlurryAgent", "onStartSession called with duplicate context, use a specific Activity or Service as context instead of using a global context");
        }
        if (!this.s) {
            bm.a("FlurryAgent", "Initializing Flurry session");
            n.set(0);
            o.set(0);
            this.w = str;
            this.q = context.getFileStreamPath(".flurryagent." + Integer.toString(this.w.hashCode(), 16));
            this.p = context.getFileStreamPath(".flurryb.");
            this.r = context.getFileStreamPath(".flurryinstallreceiver.");
            if (j) {
                Thread.setDefaultUncaughtExceptionHandler(new FlurryDefaultExceptionHandler());
            }
            Context applicationContext = context.getApplicationContext();
            if (this.y == null) {
                this.y = d(applicationContext);
            }
            String packageName = applicationContext.getPackageName();
            if (this.x != null && !this.x.equals(packageName)) {
                bm.b("FlurryAgent", "onStartSession called from different application packages: " + this.x + " and " + packageName);
            }
            this.x = packageName;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - this.u > f) {
                bm.a("FlurryAgent", "New session");
                this.H = System.currentTimeMillis();
                this.I = elapsedRealtime;
                this.J = -1L;
                this.N = "";
                this.Q = 0;
                this.R = null;
                this.L = TimeZone.getDefault().getID();
                this.K = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
                this.S = new HashMap();
                this.T = new ArrayList();
                this.U = true;
                this.W = new ArrayList();
                this.V = 0;
                this.X = 0;
                if (l) {
                    this.ab.a(context, this.H, this.I);
                }
                a(new e(this, applicationContext, this.z));
                if ((context instanceof Activity) && (extras = ((Activity) context).getIntent().getExtras()) != null) {
                    bm.a("FlurryAgent", "Launch Options Bundle is present " + extras.toString());
                    this.aa = new HashMap();
                    for (String str2 : extras.keySet()) {
                        if (this.aa.get(str2) == null) {
                            this.aa.put(str2, new ArrayList());
                        }
                        Object obj = extras.get(str2);
                        if (obj.getClass().isInstance("")) {
                            l2 = extras.getString(str2);
                        } else if (obj.getClass().isInstance(0)) {
                            extras.getInt(str2);
                            l2 = Integer.toString(0);
                        } else {
                            l2 = obj.getClass().isInstance(0L) ? Long.toString(extras.getLong(str2)) : obj.getClass().isInstance(false) ? Boolean.toString(extras.getBoolean(str2)) : "";
                        }
                        ((List) this.aa.get(str2)).add(l2);
                        bm.a("FlurryAgent", "Launch options Key: " + str2 + ". Its value: " + l2);
                    }
                }
            } else {
                bm.a("FlurryAgent", "Continuing previous session");
                if (!this.G.isEmpty()) {
                    this.G.remove(this.G.size() - 1);
                }
                if (l) {
                    this.ab.a(context);
                }
            }
            this.s = true;
        }
    }

    private synchronized void a(Context context, boolean z) {
        if (context != null) {
            if (((Context) this.v.remove(context)) == null) {
                bm.d("FlurryAgent", "onEndSession called without context from corresponding onStartSession");
            }
        }
        if (this.s && this.v.isEmpty()) {
            bm.a("FlurryAgent", "Ending session");
            n();
            Context applicationContext = context == null ? null : context.getApplicationContext();
            if (context != null) {
                String packageName = applicationContext.getPackageName();
                if (!this.x.equals(packageName)) {
                    bm.b("FlurryAgent", "onEndSession called from different application package, expected: " + this.x + " actual: " + packageName);
                }
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.u = elapsedRealtime;
            this.J = elapsedRealtime - this.I;
            if (o() == null) {
                bm.b("FlurryAgent", "Not creating report because of bad Android ID or generated ID is null");
            }
            a(new a(this, z, applicationContext));
            if (l) {
                this.ab.a();
            }
            this.s = false;
        }
    }

    public static /* synthetic */ void a(FlurryAgent flurryAgent, Context context, boolean z) {
        android.location.Location location = null;
        if (z) {
            try {
                location = flurryAgent.e(context);
            } catch (Throwable th) {
                bm.b("FlurryAgent", "", th);
                return;
            }
        }
        synchronized (flurryAgent) {
            flurryAgent.R = location;
        }
        byte[] f2 = f(context);
        if (f2 != null) {
            flurryAgent.D.put(Integer.valueOf(bo.b), ByteBuffer.wrap(f2));
        }
        flurryAgent.k();
    }

    private void a(an anVar) {
        this.m.post(anVar);
    }

    private synchronized void a(DataInputStream dataInputStream) {
        int i2 = 0;
        synchronized (this) {
            int readUnsignedShort = dataInputStream.readUnsignedShort();
            if (readUnsignedShort > 2) {
                bm.b("FlurryAgent", "Unknown agent file version: " + readUnsignedShort);
                throw new IOException("Unknown agent file version: " + readUnsignedShort);
            } else if (readUnsignedShort >= 2) {
                String readUTF = dataInputStream.readUTF();
                bm.a("FlurryAgent", "Loading API key: " + c(this.w));
                if (readUTF.equals(this.w)) {
                    String readUTF2 = dataInputStream.readUTF();
                    if (o() == null) {
                        bm.a("FlurryAgent", "Loading phoneId: " + readUTF2);
                    }
                    d(readUTF2);
                    this.E = dataInputStream.readBoolean();
                    this.F = dataInputStream.readLong();
                    bm.a("FlurryAgent", "Loading session reports");
                    while (true) {
                        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
                        if (readUnsignedShort2 == 0) {
                            break;
                        }
                        byte[] bArr = new byte[readUnsignedShort2];
                        dataInputStream.readFully(bArr);
                        this.G.add(0, bArr);
                        i2++;
                        bm.a("FlurryAgent", "Session report added: " + i2);
                    }
                    bm.a("FlurryAgent", "Persistent file loaded");
                    this.t = true;
                } else {
                    bm.a("FlurryAgent", "Api keys do not match, old: " + c(readUTF) + ", new: " + c(this.w));
                }
            } else {
                bm.d("FlurryAgent", "Deleting old file version: " + readUnsignedShort);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0019, code lost:
        r0.a(android.os.SystemClock.elapsedRealtime() - r5.I);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void a(java.lang.String r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.List r0 = r5.T     // Catch: java.lang.Throwable -> L25
            java.util.Iterator r1 = r0.iterator()     // Catch: java.lang.Throwable -> L25
        L7:
            boolean r0 = r1.hasNext()     // Catch: java.lang.Throwable -> L25
            if (r0 == 0) goto L23
            java.lang.Object r0 = r1.next()     // Catch: java.lang.Throwable -> L25
            com.flurry.android.aq r0 = (com.flurry.android.aq) r0     // Catch: java.lang.Throwable -> L25
            boolean r2 = r0.a(r6)     // Catch: java.lang.Throwable -> L25
            if (r2 == 0) goto L7
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L25
            long r3 = r5.I     // Catch: java.lang.Throwable -> L25
            long r1 = r1 - r3
            r0.a(r1)     // Catch: java.lang.Throwable -> L25
        L23:
            monitor-exit(r5)
            return
        L25:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.android.FlurryAgent.a(java.lang.String):void");
    }

    private synchronized void a(String str, String str2, String str3) {
        bb bbVar;
        if (this.W == null) {
            bm.b("FlurryAgent", "onError called before onStartSession.  Error: " + str);
        } else {
            boolean z = str != null && "uncaught".equals(str);
            this.Q++;
            if (this.W.size() < 50) {
                this.W.add(new bb(o.incrementAndGet(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3));
                bm.a("FlurryAgent", "Error logged: " + bbVar.b());
            } else if (z) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.W.size()) {
                        break;
                    }
                    bb bbVar2 = (bb) this.W.get(i2);
                    if (bbVar2.b() != null && !"uncaught".equals(bbVar2.b())) {
                        this.W.set(i2, new bb(o.incrementAndGet(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3));
                        break;
                    }
                    i2++;
                }
            } else {
                bm.a("FlurryAgent", "Max errors logged. No more errors logged.");
            }
        }
    }

    private synchronized void a(String str, Map map, boolean z) {
        if (this.T == null) {
            bm.b("FlurryAgent", "onEvent called before onStartSession.  Event: " + str);
        } else {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.I;
            String a2 = ac.a(str);
            if (a2.length() != 0) {
                l lVar = (l) this.S.get(a2);
                if (lVar != null) {
                    lVar.a++;
                    bm.a("FlurryAgent", "Event count incremented: " + a2);
                } else if (this.S.size() < 100) {
                    l lVar2 = new l();
                    lVar2.a = 1;
                    this.S.put(a2, lVar2);
                    bm.a("FlurryAgent", "Event count incremented: " + a2);
                } else if (bm.a("FlurryAgent")) {
                    bm.a("FlurryAgent", "Too many different events. Event not counted: " + a2);
                }
                if (!g || this.T.size() >= 200 || this.V >= 16000) {
                    this.U = false;
                } else {
                    Map emptyMap = map == null ? Collections.emptyMap() : map;
                    if (emptyMap.size() <= 10) {
                        aq aqVar = new aq(n.incrementAndGet(), a2, emptyMap, elapsedRealtime, z);
                        if (aqVar.a().length + this.V <= 16000) {
                            this.T.add(aqVar);
                            this.V = aqVar.a().length + this.V;
                            bm.a("FlurryAgent", "Logged event: " + a2);
                        } else {
                            this.V = DataFileConstants.DEFAULT_SYNC_INTERVAL;
                            this.U = false;
                            bm.a("FlurryAgent", "Event Log size exceeded. No more event details logged.");
                        }
                    } else if (bm.a("FlurryAgent")) {
                        bm.a("FlurryAgent", "MaxEventParams exceeded: " + emptyMap.size());
                    }
                }
            }
        }
    }

    public static boolean a() {
        return e.t && e.s;
    }

    private boolean a(byte[] bArr) {
        boolean z;
        String j2 = j();
        if (j2 == null) {
            return false;
        }
        try {
            z = a(bArr, j2);
        } catch (Exception e2) {
            bm.a("FlurryAgent", "Sending report exception: " + e2.getMessage());
            z = false;
        }
        if (z || b != null || !h || i) {
            return z;
        }
        synchronized (e) {
            i = true;
            String j3 = j();
            if (j3 == null) {
                z = false;
            } else {
                try {
                    z = a(bArr, j3);
                } catch (Exception e3) {
                }
            }
        }
        return z;
    }

    private boolean a(byte[] bArr, String str) {
        boolean z = true;
        if (!"local".equals(str)) {
            bm.a("FlurryAgent", "Sending report to: " + str);
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bArr);
            byteArrayEntity.setContentType("application/octet-stream");
            HttpPost httpPost = new HttpPost(str);
            httpPost.setEntity(byteArrayEntity);
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 15000);
            httpPost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
            int statusCode = this.ac.a(basicHttpParams).execute(httpPost).getStatusLine().getStatusCode();
            synchronized (this) {
                if (statusCode == 200) {
                    bm.a("FlurryAgent", "Report successful");
                    this.E = true;
                    this.G.removeAll(this.A);
                } else {
                    bm.a("FlurryAgent", "Report failed. HTTP response: " + statusCode);
                    z = false;
                }
                this.A = null;
            }
        }
        return z;
    }

    public static bo b() {
        return e.ab;
    }

    private static Map b(String str) {
        HashMap hashMap = new HashMap();
        String[] split = str.split("&");
        int length = split.length;
        for (int i2 = 0; i2 < length; i2++) {
            String[] split2 = split[i2].split("=");
            if (split2.length != 2) {
                bm.a("FlurryAgent", "Invalid referrer Element: " + split[i2] + " in referrer tag " + str);
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

    /* JADX WARN: Removed duplicated region for block: B:112:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x004b A[Catch: Throwable -> 0x00f5, TryCatch #4 {, blocks: (B:62:0x0001, B:64:0x000d, B:70:0x0044, B:76:0x005a, B:78:0x005e, B:80:0x006a, B:81:0x00a4, B:83:0x00bd, B:85:0x00c5, B:86:0x00c8, B:93:0x00de, B:106:0x00ff, B:92:0x00d7, B:71:0x0047, B:73:0x004b, B:75:0x0053, B:102:0x00ec), top: B:116:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x005e A[Catch: all -> 0x00e3, TryCatch #4 {, blocks: (B:62:0x0001, B:64:0x000d, B:70:0x0044, B:76:0x005a, B:78:0x005e, B:80:0x006a, B:81:0x00a4, B:83:0x00bd, B:85:0x00c5, B:86:0x00c8, B:93:0x00de, B:106:0x00ff, B:92:0x00d7, B:71:0x0047, B:73:0x004b, B:75:0x0053, B:102:0x00ec), top: B:116:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x006a A[Catch: all -> 0x00e3, TryCatch #4 {, blocks: (B:62:0x0001, B:64:0x000d, B:70:0x0044, B:76:0x005a, B:78:0x005e, B:80:0x006a, B:81:0x00a4, B:83:0x00bd, B:85:0x00c5, B:86:0x00c8, B:93:0x00de, B:106:0x00ff, B:92:0x00d7, B:71:0x0047, B:73:0x004b, B:75:0x0053, B:102:0x00ec), top: B:116:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void b(android.content.Context r9) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.android.FlurryAgent.b(android.content.Context):void");
    }

    private synchronized void b(Context context, String str) {
        DataOutputStream dataOutputStream;
        this.p = context.getFileStreamPath(".flurryb.");
        if (ah.a(this.p)) {
            try {
                dataOutputStream = new DataOutputStream(new FileOutputStream(this.p));
                try {
                    dataOutputStream.writeInt(1);
                    dataOutputStream.writeUTF(str);
                    ac.a(dataOutputStream);
                } catch (Throwable th) {
                    th = th;
                    bm.b("FlurryAgent", "Error when saving b file", th);
                    ac.a(dataOutputStream);
                }
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream = null;
            }
        }
    }

    public static /* synthetic */ void b(FlurryAgent flurryAgent, Context context) {
        boolean z;
        try {
            synchronized (flurryAgent) {
                z = !flurryAgent.s && SystemClock.elapsedRealtime() - flurryAgent.u > f && flurryAgent.G.size() > 0;
            }
            if (z) {
                flurryAgent.k();
            }
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    public static String c() {
        return e.w;
    }

    private String c(Context context) {
        DataInputStream dataInputStream;
        boolean z = false;
        String o2 = o();
        if (o2 != null) {
            return o2;
        }
        String string = Settings.System.getString(context.getContentResolver(), "android_id");
        if (string != null && string.length() > 0 && !string.equals(DataFileConstants.NULL_CODEC)) {
            String[] strArr = a;
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = true;
                    break;
                } else if (string.equals(strArr[i2])) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (z) {
            return "AND" + string;
        }
        File fileStreamPath = context.getFileStreamPath(".flurryb.");
        if (!fileStreamPath.exists()) {
            return o2;
        }
        try {
            dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
            try {
                dataInputStream.readInt();
                return dataInputStream.readUTF();
            } catch (Throwable th) {
                th = th;
                try {
                    bm.b("FlurryAgent", "Error when loading b file", th);
                    return o2;
                } finally {
                    ac.a(dataInputStream);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            dataInputStream = null;
        }
    }

    private static String c(String str) {
        if (str == null || str.length() <= 4) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length() - 4; i2++) {
            sb.append('*');
        }
        sb.append(str.substring(str.length() - 4));
        return sb.toString();
    }

    public static void clearTargetingKeywords() {
        if (l) {
            e.ab.f();
        }
    }

    public static void clearUserCookies() {
        if (l) {
            e.ab.h();
        } else {
            Z = null;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0027 -> B:22:0x0013). Please submit an issue!!! */
    private static String d(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
        if (packageInfo.versionName != null) {
            return packageInfo.versionName;
        }
        if (packageInfo.versionCode != 0) {
            return Integer.toString(packageInfo.versionCode);
        }
        return "Unknown";
    }

    private synchronized void d(String str) {
        if (str != null) {
            this.C = str;
        }
    }

    public static void displayAd(Context context, String str, ViewGroup viewGroup) {
        if (context == null) {
            bm.b("FlurryAgent", "Context passed to displayAd was null.");
        } else if (str == null) {
            bm.b("FlurryAgent", "Ad space name passed to displayAd was null.");
        } else if (str.length() == 0) {
            bm.b("FlurryAgent", "Ad space name passed to displayAd was empty.");
        } else {
            e.a(context);
            try {
                e.ab.b(context, str, viewGroup);
            } catch (Throwable th) {
                bm.b("FlurryAgent", "", th);
            }
        }
    }

    private android.location.Location e(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            synchronized (this) {
                if (this.B == null) {
                    this.B = locationManager;
                } else {
                    locationManager = this.B;
                }
            }
            Criteria criteria = k;
            if (criteria == null) {
                criteria = new Criteria();
            }
            String bestProvider = locationManager.getBestProvider(criteria, true);
            if (bestProvider != null) {
                locationManager.requestLocationUpdates(bestProvider, 0L, 0.0f, this, Looper.getMainLooper());
                return locationManager.getLastKnownLocation(bestProvider);
            }
        }
        return null;
    }

    public static void enableTestAds(boolean z) {
        e.ab.a(z);
    }

    public static void endTimedEvent(String str) {
        try {
            e.a(str);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "Failed to signify the end of event: " + str, th);
        }
    }

    private static byte[] f(Context context) {
        TelephonyManager telephonyManager;
        String deviceId;
        if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0 && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null && (deviceId = telephonyManager.getDeviceId()) != null && deviceId.trim().length() > 0) {
            try {
                byte[] c2 = ac.c(deviceId);
                if (c2 != null && c2.length == 20) {
                    return c2;
                }
                bm.b("FlurryAgent", "sha1 is not 20 bytes long: " + Arrays.toString(c2));
            } catch (Exception e2) {
            }
        }
        return null;
    }

    public static void fetchAd(Context context, String str, ViewGroup viewGroup, FlurryAdSize flurryAdSize) {
        if (context == null) {
            bm.b("FlurryAgent", "Context passed to fetchAd was null.");
        } else if (str == null) {
            bm.b("FlurryAgent", "Ad space name passed to fetchAd was null.");
        } else if (str.length() == 0) {
            bm.b("FlurryAgent", "Ad space name passed to fetchAd was empty.");
        } else {
            e.a(context);
            try {
                e.ab.a(context, str, viewGroup, flurryAdSize);
            } catch (Throwable th) {
                bm.b("FlurryAgent", "", th);
            }
        }
    }

    public synchronized void g() {
        DataOutputStream dataOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        } catch (IOException e2) {
            e = e2;
        } catch (Throwable th) {
            th = th;
            dataOutputStream = null;
        }
        try {
            dataOutputStream.writeShort(1);
            dataOutputStream.writeUTF(this.y);
            dataOutputStream.writeLong(this.H);
            dataOutputStream.writeLong(this.J);
            dataOutputStream.writeLong(0L);
            dataOutputStream.writeUTF(this.K);
            dataOutputStream.writeUTF(this.L);
            dataOutputStream.writeByte(this.M);
            dataOutputStream.writeUTF(this.N == null ? "" : this.N);
            if (this.R == null) {
                dataOutputStream.writeBoolean(false);
            } else {
                dataOutputStream.writeBoolean(true);
                dataOutputStream.writeDouble(a(this.R.getLatitude()));
                dataOutputStream.writeDouble(a(this.R.getLongitude()));
                dataOutputStream.writeFloat(this.R.getAccuracy());
            }
            dataOutputStream.writeInt(this.X);
            dataOutputStream.writeByte(-1);
            dataOutputStream.writeByte(-1);
            dataOutputStream.writeByte(this.O);
            if (this.P == null) {
                dataOutputStream.writeBoolean(false);
            } else {
                dataOutputStream.writeBoolean(true);
                dataOutputStream.writeLong(this.P.longValue());
            }
            dataOutputStream.writeShort(this.S.size());
            for (Map.Entry entry : this.S.entrySet()) {
                dataOutputStream.writeUTF((String) entry.getKey());
                dataOutputStream.writeInt(((l) entry.getValue()).a);
            }
            dataOutputStream.writeShort(this.T.size());
            for (aq aqVar : this.T) {
                dataOutputStream.write(aqVar.a());
            }
            dataOutputStream.writeBoolean(this.U);
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < this.W.size() && (i3 = i3 + ((bb) this.W.get(i4)).a().length) <= 16000; i4++) {
                i2++;
            }
            dataOutputStream.writeInt(this.Q);
            dataOutputStream.writeShort(i2);
            for (int i5 = 0; i5 < i2; i5++) {
                dataOutputStream.write(((bb) this.W.get(i5)).a());
            }
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            this.G.add(byteArrayOutputStream.toByteArray());
            ac.a(dataOutputStream);
        } catch (IOException e3) {
            e = e3;
            dataOutputStream2 = dataOutputStream;
            try {
                bm.b("FlurryAgent", "", e);
                ac.a(dataOutputStream2);
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream = dataOutputStream2;
                ac.a(dataOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            ac.a(dataOutputStream);
            throw th;
        }
    }

    public static boolean getAd(Context context, String str, ViewGroup viewGroup, FlurryAdSize flurryAdSize, long j2) {
        if (context == null) {
            bm.b("FlurryAgent", "Context passed to getAd was null.");
            return false;
        } else if (str == null) {
            bm.b("FlurryAgent", "Ad space name passed to getAd was null.");
            return false;
        } else if (str.length() == 0) {
            bm.b("FlurryAgent", "Ad space name passed to getAd was empty.");
            return false;
        } else if (viewGroup == null) {
            bm.b("FlurryAgent", "ViewGroup passed to getAd was null.");
            return false;
        } else {
            e.a(context);
            try {
                return e.ab.a(context, str, flurryAdSize, viewGroup, j2);
            } catch (Throwable th) {
                bm.b("FlurryAgent", "", th);
                return false;
            }
        }
    }

    public static int getAgentVersion() {
        return 140;
    }

    public static boolean getForbidPlaintextFallback() {
        return false;
    }

    public static String getPhoneId() {
        return e.o();
    }

    public static boolean getUseHttps() {
        return h;
    }

    private synchronized void h() {
        this.X++;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private synchronized byte[] i() {
        /*
            Method dump skipped, instructions count: 701
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.android.FlurryAgent.i():byte[]");
    }

    public static void initializeAds(Context context) {
        if (context == null) {
            bm.b("FlurryAgent", "Context passed to initializeAds was null.");
            return;
        }
        e.a(context);
        try {
            e.ab.b(context);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    public static boolean isAdAvailable(Context context, String str, FlurryAdSize flurryAdSize, long j2) {
        if (context == null) {
            bm.b("FlurryAgent", "Context passed to isAdAvailable was null.");
            return false;
        } else if (str == null) {
            bm.b("FlurryAgent", "Ad space name passed to isAdAvailable was null.");
            return false;
        } else if (str.length() == 0) {
            bm.b("FlurryAgent", "Ad space name passed to isAdAvailable was empty.");
            return false;
        } else {
            e.a(context);
            try {
                return e.ab.a(context, str, flurryAdSize, j2);
            } catch (Throwable th) {
                bm.b("FlurryAgent", "", th);
                return false;
            }
        }
    }

    public static boolean isCaptureUncaughtExceptions() {
        return j;
    }

    private static String j() {
        if (b != null) {
            return b;
        }
        if (!i && h) {
            return d;
        }
        return c;
    }

    private void k() {
        try {
            bm.a("FlurryAgent", "generating report");
            byte[] i2 = i();
            if (i2 == null) {
                bm.a("FlurryAgent", "Error generating report");
            } else if (a(i2)) {
                bm.a("FlurryAgent", "Done sending " + (this.s ? "initial " : "") + "agent report");
                m();
            }
        } catch (IOException e2) {
            bm.a("FlurryAgent", "", e2);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    private void l() {
        if (this.r.exists()) {
            bm.c("FlurryAgent", "Loading referrer info from file:  " + this.r.getAbsolutePath());
            String a2 = a(this.r);
            if (a2 != null) {
                this.Y = b(a2);
            }
        }
    }

    public static void logEvent(String str) {
        try {
            e.a(str, (Map) null, false);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, Map map) {
        try {
            e.a(str, map, false);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, Map map, boolean z) {
        try {
            e.a(str, map, z);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "Failed to log event: " + str, th);
        }
    }

    public static void logEvent(String str, boolean z) {
        try {
            e.a(str, (Map) null, z);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "Failed to log event: " + str, th);
        }
    }

    public synchronized void m() {
        DataOutputStream dataOutputStream;
        try {
            if (ah.a(this.q)) {
                dataOutputStream = new DataOutputStream(new FileOutputStream(this.q));
                try {
                    dataOutputStream.writeShort(46586);
                    dataOutputStream.writeShort(2);
                    dataOutputStream.writeUTF(this.w);
                    dataOutputStream.writeUTF(o());
                    dataOutputStream.writeBoolean(this.E);
                    dataOutputStream.writeLong(this.F);
                    int size = this.G.size() - 1;
                    while (true) {
                        if (size < 0) {
                            break;
                        }
                        byte[] bArr = (byte[]) this.G.get(size);
                        int length = bArr.length;
                        if (length + 2 + dataOutputStream.size() > 50000) {
                            bm.a("FlurryAgent", "discarded sessions: " + size);
                            break;
                        }
                        dataOutputStream.writeShort(length);
                        dataOutputStream.write(bArr);
                        size--;
                    }
                    dataOutputStream.writeShort(0);
                    ac.a(dataOutputStream);
                } catch (Throwable th) {
                    th = th;
                    bm.b("FlurryAgent", "", th);
                    ac.a(dataOutputStream);
                }
            } else {
                ac.a((Closeable) null);
            }
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream = null;
        }
    }

    private synchronized void n() {
        if (this.B != null) {
            this.B.removeUpdates(this);
        }
    }

    private synchronized String o() {
        return this.C;
    }

    public static void onEndSession(Context context) {
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        try {
            e.a(context, false);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    public static void onError(String str, String str2, String str3) {
        try {
            e.a(str, str2, str3);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    public static void onEvent(String str) {
        try {
            e.a(str, (Map) null, false);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    public static void onEvent(String str, Map map) {
        try {
            e.a(str, map, false);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    public static void onPageView() {
        try {
            e.h();
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    public static void onStartSession(Context context, String str) {
        if (context == null) {
            throw new NullPointerException("Null context");
        }
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Api key not specified");
        }
        try {
            e.a(context, str);
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    public static void removeAd(Context context, String str, ViewGroup viewGroup) {
        if (context == null) {
            bm.b("FlurryAgent", "Context passed to removeAd was null.");
        } else if (str == null) {
            bm.b("FlurryAgent", "Ad space name passed to removeAd was null.");
        } else if (str.length() == 0) {
            bm.b("FlurryAgent", "Ad space name passed to removeAd was empty.");
        } else if (viewGroup == null) {
            bm.b("FlurryAgent", "ViewGroup passed to removeAd was null.");
        } else {
            try {
                e.ab.b(str);
            } catch (Throwable th) {
                bm.b("FlurryAgent", "", th);
            }
        }
    }

    public static void sendAdLogsToServer() {
        e.ab.j();
    }

    public static void setAdListener(FlurryAdListener flurryAdListener) {
        e.ab.a(flurryAdListener);
    }

    public static void setAdLogUrl(String str) {
        str.endsWith(".do");
        e.ab.d(str);
    }

    public static void setAdServerUrl(String str) {
        str.endsWith(".do");
        e.ab.c(str);
    }

    public static void setAge(int i2) {
        if (i2 <= 0 || i2 >= 110) {
            return;
        }
        Date date = new Date(new Date(System.currentTimeMillis() - (i2 * 31449600000L)).getYear(), 1, 1);
        e.P = Long.valueOf(date.getTime());
    }

    public static void setCaptureUncaughtExceptions(boolean z) {
        synchronized (e) {
            if (e.s) {
                bm.b("FlurryAgent", "Cannot setCaptureUncaughtExceptions after onStartSession");
            } else {
                j = z;
            }
        }
    }

    public static void setContinueSessionMillis(long j2) {
        if (j2 < 5000) {
            bm.b("FlurryAgent", "Invalid time set for session resumption: " + j2);
            return;
        }
        synchronized (e) {
            f = j2;
        }
    }

    public static void setCustomAdNetworkHandler(ICustomAdNetworkHandler iCustomAdNetworkHandler) {
        e.ab.a(iCustomAdNetworkHandler);
    }

    public static void setGender(byte b2) {
        switch (b2) {
            case 0:
            case 1:
                e.O = b2;
                return;
            default:
                e.O = (byte) -1;
                return;
        }
    }

    public static void setLocation(float f2, float f3) {
        e.ab.a(f2, f3);
    }

    public static void setLocationCriteria(Criteria criteria) {
        synchronized (e) {
            k = criteria;
        }
    }

    public static void setLogEnabled(boolean z) {
        synchronized (e) {
            if (z) {
                bm.b();
            } else {
                bm.a();
            }
        }
    }

    public static void setLogEvents(boolean z) {
        synchronized (e) {
            g = z;
        }
    }

    public static void setLogLevel(int i2) {
        synchronized (e) {
            bm.a(i2);
        }
    }

    public static void setReportLocation(boolean z) {
        synchronized (e) {
            e.z = z;
        }
    }

    public static void setReportUrl(String str) {
        str.endsWith(".do");
        b = str;
    }

    public static void setTargetingKeywords(Map map) {
        e.ab.b(map);
    }

    public static void setUseHttps(boolean z) {
        h = z;
    }

    public static void setUserCookies(Map map) {
        if (l) {
            e.ab.c(map);
        } else {
            Z = map;
        }
    }

    public static void setUserId(String str) {
        synchronized (e) {
            e.N = ac.a(str);
        }
    }

    public static void setVersionName(String str) {
        synchronized (e) {
            e.y = str;
        }
    }

    final void a(Throwable th) {
        th.printStackTrace();
        String str = "";
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            StringBuilder sb = new StringBuilder();
            if (th.getMessage() != null) {
                sb.append(" (" + th.getMessage() + ")\n");
            }
            for (int i2 = 0; i2 < stackTrace.length; i2++) {
                if (i2 != 0) {
                    sb.append('\n');
                }
                StackTraceElement stackTraceElement = stackTrace[i2];
                sb.append(stackTraceElement.getClassName()).append(".").append(stackTraceElement.getMethodName()).append(":").append(stackTraceElement.getLineNumber());
            }
            str = sb.toString();
        } else if (th.getMessage() != null) {
            str = th.getMessage();
        }
        onError("uncaught", str, th.getClass().toString());
        this.v.clear();
        a((Context) null, true);
    }

    @Override // android.location.LocationListener
    public final synchronized void onLocationChanged(android.location.Location location) {
        try {
            this.R = location;
            n();
        } catch (Throwable th) {
            bm.b("FlurryAgent", "", th);
        }
    }

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i2, Bundle bundle) {
    }
}
