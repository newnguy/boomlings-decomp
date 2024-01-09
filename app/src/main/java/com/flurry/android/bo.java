package com.flurry.android;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import com.flurry.org.apache.avro.io.EncoderFactory;
import com.flurry.org.apache.avro.specific.SpecificDatumReader;
import com.flurry.org.apache.avro.specific.SpecificDatumWriter;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* loaded from: classes.dex */
public final class bo implements ao {
    static String a = "FlurryAgent";
    static int b = 5;
    private static List e;
    private static DecoderFactory f;
    private static z g;
    private long A;
    private long B;
    private Map C;
    private String D;
    private Handler E;
    private ICustomAdNetworkHandler F;
    private FlurryAdListener G;
    private volatile boolean H;
    private Map N;
    private AdUnit O;
    private final Map P;
    private final Set Q;
    aa c;
    bn d;
    private be h;
    private v i;
    private Display j;
    private String m;
    private String n;
    private String o;
    private String p;
    private volatile float t;
    private volatile float u;
    private volatile Map v;
    private bl w;
    private AdUnit x;
    private String y;
    private long z;
    private boolean k = false;
    private File l = null;
    private boolean q = true;
    private volatile String r = null;
    private volatile String s = null;
    private volatile List I = new CopyOnWriteArrayList();
    private volatile Map J = new HashMap();
    private volatile List K = new ArrayList();
    private volatile List L = new ArrayList();
    private volatile boolean M = false;

    public bo() {
        HashMap hashMap = new HashMap();
        hashMap.put("open", "directOpen");
        hashMap.put("expand", "doExpand");
        hashMap.put("collapse", "doCollapse");
        this.P = Collections.unmodifiableMap(hashMap);
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList("closeAd", "processRedirect", "nextFrame", "nextAdUnit", "notifyUser"));
        this.Q = Collections.unmodifiableSet(hashSet);
        HandlerThread handlerThread = new HandlerThread("FlurryAdThread");
        handlerThread.start();
        this.E = new Handler(handlerThread.getLooper());
        this.c = new aa();
        e = Arrays.asList(0, 1, 2, 3, 4, 5);
        f = new DecoderFactory();
        g = new z(this);
        this.i = new v(this);
    }

    private static int a(byte[] bArr) {
        CrcMessageDigest crcMessageDigest = new CrcMessageDigest();
        crcMessageDigest.update(bArr);
        return crcMessageDigest.getChecksum();
    }

    private synchronized at a(String str, boolean z, Map map) {
        return new at(str, z, d(), map);
    }

    private bl a(String str, String str2, boolean z) {
        bl blVar = (bl) this.J.get(str);
        if (blVar == null) {
            blVar = ac.a(this, str);
        }
        return a(blVar, str2, true, (Map) null);
    }

    private static SpecificRecordBase a(byte[] bArr, Class cls) {
        try {
            return (SpecificRecordBase) new SpecificDatumReader(cls).read(null, f.binaryDecoder(new ByteArrayInputStream(bArr), (BinaryDecoder) null));
        } catch (IOException e2) {
            String str = "IOException in parseAvroBinary:" + e2.getMessage();
            return null;
        } catch (ClassCastException e3) {
            String str2 = "ClassCastException in parseAvroBinary:" + e3.getMessage();
            return null;
        }
    }

    private String a(bl blVar, AdUnit adUnit, y yVar, String str) {
        Pattern compile = Pattern.compile(".*?(%\\{\\w+\\}).*?");
        Matcher matcher = compile.matcher(str);
        while (matcher.matches()) {
            str = this.i.a(blVar, adUnit, str, matcher.group(1));
            matcher = compile.matcher(str);
        }
        return str;
    }

    private static List a(AdFrame adFrame, bj bjVar) {
        ArrayList arrayList = new ArrayList();
        List<Callback> e2 = adFrame.e();
        String str = bjVar.a;
        for (Callback callback : e2) {
            if (callback.a().toString().equals(str)) {
                for (CharSequence charSequence : callback.b()) {
                    HashMap hashMap = new HashMap();
                    String obj = charSequence.toString();
                    int indexOf = obj.indexOf(63);
                    if (indexOf != -1) {
                        String substring = obj.substring(0, indexOf);
                        String substring2 = obj.substring(indexOf + 1);
                        if (substring2.contains("%{eventParams}")) {
                            substring2 = substring2.replace("%{eventParams}", "");
                            hashMap.putAll(bjVar.b);
                        }
                        hashMap.putAll(ac.e(substring2));
                        obj = substring;
                    }
                    arrayList.add(new y(obj, hashMap, bjVar));
                }
            }
        }
        return arrayList;
    }

    private void a(Context context, String str, boolean z) {
        if (o()) {
            this.E.post(new c(this, str, context, z));
        }
    }

    private synchronized void a(SdkLogResponse sdkLogResponse) {
        if (sdkLogResponse.a().toString().equals("success")) {
            this.I.removeAll(this.K);
        }
    }

    private synchronized void a(List list, DataOutputStream dataOutputStream) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                ((bl) list.get(i)).a(dataOutputStream);
            } catch (IOException e2) {
                bm.a(a, "unable to convert adLog to byte[]: " + ((bl) list.get(i)).b());
            }
        }
    }

    public static boolean a(Context context, String str, String str2) {
        Intent intent = new Intent(str2);
        intent.setData(Uri.parse(str));
        return ac.a(context, intent);
    }

    private boolean a(byte[] bArr, String str) {
        String str2;
        if (str == null) {
            return false;
        }
        if (str.equals("/v3/getAds.do")) {
            str2 = (this.r != null ? this.r : FlurryAgent.getUseHttps() ? "https://ads.flurry.com" : "http://ads.flurry.com") + str;
        } else {
            str2 = (this.s != null ? this.s : FlurryAgent.getUseHttps() ? "https://adlog.flurry.com" : "http://adlog.flurry.com") + str;
        }
        byte[] b2 = b(bArr, str2);
        if (b2 != null) {
            try {
                if (str.equals("/postAdLog.do")) {
                    b(b2);
                }
            } catch (IOException e2) {
                bm.b(a, "IOException: " + e2.toString());
            }
        }
        return true;
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[128];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private void b(byte[] bArr) {
        SdkLogResponse sdkLogResponse = (SdkLogResponse) a(bArr, SdkLogResponse.class);
        if (sdkLogResponse != null) {
            String str = "got an AdLogResponse:" + sdkLogResponse;
            if (sdkLogResponse.a().toString().equals("success")) {
                a(sdkLogResponse);
                return;
            }
            for (CharSequence charSequence : sdkLogResponse.b()) {
                bm.b(a, charSequence.toString());
            }
        }
    }

    private byte[] b(String str, int i, int i2, boolean z, FlurryAdSize flurryAdSize) {
        AdRequest build = AdRequest.a().setApiKey(this.y).setAdSpaceName("").setBindings(e).setAdReportedIds(q()).setLocation(Location.a().setLat(this.t).setLon(this.u).build()).setTestDevice(this.M).setAgentVersion(Integer.toString(FlurryAgent.getAgentVersion())).setSessionId(this.z).setAdViewContainer(AdViewContainer.a().setScreenHeight(this.j.getHeight()).setScreenWidth(this.j.getWidth()).setViewHeight(i2).setViewWidth(i).build()).setLocale(this.m).setTimezone(this.n).setOsVersion(this.o).setDevicePlatform(this.p).build();
        if (z) {
            build.a(Boolean.valueOf(z));
        } else {
            build.a(str);
        }
        if (flurryAdSize != null) {
            build.a(TestAds.a().setAdspacePlacement(flurryAdSize.a()).build());
        }
        if (this.N != null) {
            HashMap hashMap = new HashMap();
            for (Map.Entry entry : this.N.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            build.a(hashMap);
        }
        String str2 = "Got ad request  " + build;
        SpecificDatumWriter specificDatumWriter = new SpecificDatumWriter(AdRequest.class);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryEncoder directBinaryEncoder = EncoderFactory.get().directBinaryEncoder(byteArrayOutputStream, null);
        try {
            specificDatumWriter.write(build, directBinaryEncoder);
            directBinaryEncoder.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            e2.getMessage();
            return new byte[0];
        }
    }

    private byte[] b(byte[] bArr, String str) {
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bArr);
        byteArrayEntity.setContentType("avro/binary");
        HttpPost httpPost = new HttpPost(str);
        httpPost.setEntity(byteArrayEntity);
        httpPost.setHeader("accept", "avro/binary");
        httpPost.setHeader("FM-Checksum", Integer.toString(a(bArr)));
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 15000);
        httpPost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        try {
            HttpResponse execute = this.h.a(basicHttpParams).execute(httpPost);
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode != 200 || execute.getEntity() == null || execute.getEntity().getContentLength() == 0) {
                bm.b(a, "Request failed with HTTP " + statusCode);
            } else {
                bm.c(a, "Request successful");
                byte[] a2 = a(execute.getEntity().getContent());
                byteArrayEntity.consumeContent();
                String num = Integer.toString(a(a2));
                if (!execute.containsHeader("FM-Checksum") || execute.getFirstHeader("FM-Checksum").getValue().equals(num)) {
                    return a2;
                }
            }
        } catch (IOException e2) {
            bm.b(a, "IOException: " + e2.toString());
        }
        return null;
    }

    public static boolean e(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    public static boolean f(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && (activeNetworkInfo.isConnected() || activeNetworkInfo.isRoaming());
        if (z) {
            return z;
        }
        bm.d(a, "No connectivity found.");
        return z;
    }

    private boolean f(String str) {
        return this.c.b(str);
    }

    private aj g(String str) {
        return this.d.c(str);
    }

    private void h(String str) {
        this.d.d(str);
    }

    private boolean o() {
        if (!this.H) {
            bm.d(a, "Flurry Ads not initialized");
        }
        if (this.D == null) {
            bm.d(a, "Cannot identify UDID.");
        }
        return this.H;
    }

    private byte[] p() {
        List q = q();
        synchronized (this.I) {
            List a2 = g.a(this.I);
            if (a2.size() == 0) {
                return null;
            }
            SdkLogRequest build = SdkLogRequest.a().setApiKey(this.y).setAdReportedIds(q).setSdkAdLogs(a2).setTestDevice(false).setAgentTimestamp(System.currentTimeMillis()).build();
            String str = "Got ad log request:" + build.toString();
            SpecificDatumWriter specificDatumWriter = new SpecificDatumWriter(SdkLogRequest.class);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BinaryEncoder directBinaryEncoder = EncoderFactory.get().directBinaryEncoder(byteArrayOutputStream, null);
            try {
                specificDatumWriter.write(build, directBinaryEncoder);
                directBinaryEncoder.flush();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e2) {
                e2.getMessage();
                return null;
            }
        }
    }

    private List q() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(AdReportedId.a().setId(ByteBuffer.wrap(this.D.getBytes())).setType(0).build());
        for (Map.Entry entry : this.C.entrySet()) {
            arrayList.add(AdReportedId.a().setId((ByteBuffer) entry.getValue()).setType(((Integer) entry.getKey()).intValue()).build());
        }
        return arrayList;
    }

    public final synchronized bl a(bl blVar, String str, boolean z, Map map) {
        synchronized (blVar) {
            if (!this.I.contains(blVar)) {
                this.I.add(blVar);
            }
            blVar.a(a(str, z, map));
        }
        return blVar;
    }

    public final List a(String str, int i, int i2, boolean z, FlurryAdSize flurryAdSize) {
        byte[] b2 = b(str, i, i2, z, flurryAdSize);
        if (b2 == null || b2.length < 1) {
            return Collections.emptyList();
        }
        byte[] b3 = b(b2, this.r != null ? this.r + "/v3/getAds.do" : FlurryAgent.getUseHttps() ? "https://ads.flurry.com/v3/getAds.do" : "http://ads.flurry.com/v3/getAds.do");
        if (b3 == null || b3.length < 1) {
            return Collections.emptyList();
        }
        AdResponse adResponse = (AdResponse) a(b3, AdResponse.class);
        if (adResponse == null) {
            return Collections.emptyList();
        }
        String str2 = "adResponse: " + adResponse;
        if (adResponse.b().size() <= 0) {
            if (adResponse.a() == null || adResponse.a().size() == 0) {
                bm.b(a, "Ad server responded but sent no ad units.");
                return Collections.emptyList();
            } else {
                return adResponse.a();
            }
        }
        bm.b(a, "Ad server responded with the following error(s):");
        for (CharSequence charSequence : adResponse.b()) {
            bm.b(a, charSequence.toString());
        }
        return Collections.emptyList();
    }

    public final void a() {
        this.d.a((Context) null);
        j();
    }

    public final void a(float f2, float f3) {
        this.t = f2;
        this.u = f3;
    }

    public final void a(Context context) {
        this.d.a(context);
        this.d.a();
    }

    public final void a(Context context, long j, long j2) {
        this.z = j;
        this.A = j2;
        this.B = 0L;
        this.d.a(context);
    }

    public final void a(Context context, p pVar) {
        if (!this.H) {
            this.y = pVar.a;
            this.d = new bn(context);
            this.H = true;
        }
        this.h = pVar.b;
        this.m = pVar.d;
        this.n = pVar.e;
        this.o = Build.VERSION.RELEASE;
        this.p = Build.DEVICE;
        this.j = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        this.l = context.getFileStreamPath(".flurryadlog." + Integer.toString(this.y.hashCode(), 16));
        this.q = a(context, "market://details?id=" + context.getPackageName(), "android.intent.action.VIEW");
        this.v = new HashMap();
    }

    public final void a(Context context, String str) {
        aj baVar;
        bl blVar;
        AdFrame adFrame;
        String str2;
        String str3;
        int i;
        AdNetworkView adNetworkView;
        AdUnit a2 = this.c.a(str);
        if (a2 == null) {
            if (this.G != null) {
                this.G.spaceDidFailToReceiveAd(str.toString());
                return;
            }
            return;
        }
        if (this.G != null) {
            this.G.spaceDidReceiveAd(str.toString());
        }
        ba baVar2 = new ba();
        this.O = a2;
        List c = a2.c();
        if (c.size() > 0) {
            AdFrame adFrame2 = (AdFrame) c.get(0);
            int intValue = adFrame2.a().intValue();
            String obj = adFrame2.c().toString();
            String obj2 = adFrame2.d().d().toString();
            bl a3 = a(adFrame2.f().toString(), "requested", true);
            a(new bj("filled", Collections.emptyMap(), context, a2, a3, 0), this, 1);
            if (this.O == null) {
                baVar = new ba();
            } else {
                if (this.O != a2) {
                    AdUnit adUnit = this.O;
                    adFrame = (AdFrame) adUnit.c().get(0);
                    int intValue2 = adFrame.a().intValue();
                    str3 = adFrame.c().toString();
                    str2 = adFrame.d().d().toString();
                    blVar = a(adFrame.f().toString(), "requested", true);
                    a2 = adUnit;
                    i = intValue2;
                } else {
                    blVar = a3;
                    adFrame = adFrame2;
                    str2 = obj2;
                    str3 = obj;
                    i = intValue;
                }
                String str4 = "Processing ad request for binding: " + i + ", networkType: " + str3 + ", format: " + str2;
                if (a2.d().intValue() == 1 || i == 2 || i == 1 || i == 3) {
                    if (str2.equals(AdCreative.kFormatTakeover)) {
                        this.w = blVar;
                        this.x = a2;
                        baVar = new bk(a2);
                    } else {
                        baVar = new bh(new ap(context, this, blVar, a2, 0), a2);
                    }
                } else if (i == 4) {
                    AdSpaceLayout d = adFrame.d();
                    AdCreative adCreative = new AdCreative(d.b().intValue(), d.a().intValue(), d.d().toString(), d.c().toString(), d.e().toString());
                    if (str3.equalsIgnoreCase("Admob")) {
                        String str5 = "Retrieving BannerView for:" + str3;
                        adNetworkView = new ab(context, this, blVar, adCreative);
                        adNetworkView.d = 0;
                        adNetworkView.c = a2;
                    } else if (str3.equalsIgnoreCase("Millennial Media")) {
                        String str6 = "Retrieving BannerView for:" + str3;
                        adNetworkView = new al(context, this, blVar, adCreative);
                        adNetworkView.d = 0;
                        adNetworkView.c = a2;
                    } else if (str3.equalsIgnoreCase("InMobi")) {
                        String str7 = "Retrieving BannerView for:" + str3;
                        adNetworkView = new ae(context, this, blVar, adCreative);
                        adNetworkView.d = 0;
                        adNetworkView.c = a2;
                    } else if (str3.equalsIgnoreCase("Mobclix")) {
                        String str8 = "Retrieving BannerView for:" + str3;
                        adNetworkView = new ag(context, this, blVar, adCreative);
                        adNetworkView.d = 0;
                        adNetworkView.c = a2;
                    } else if (str3.equalsIgnoreCase("Jumptap")) {
                        String str9 = "Retrieving BannerView for:" + str3;
                        adNetworkView = new g(context, this, blVar, adCreative);
                        adNetworkView.d = 0;
                        adNetworkView.c = a2;
                    } else {
                        ICustomAdNetworkHandler iCustomAdNetworkHandler = this.F;
                        if (iCustomAdNetworkHandler != null) {
                            adNetworkView = iCustomAdNetworkHandler.getAdFromNetwork(context, adCreative, str3);
                            if (adNetworkView != null) {
                                adNetworkView.a = this;
                                adNetworkView.b = blVar;
                                adNetworkView.d = 0;
                                adNetworkView.c = a2;
                            } else {
                                bm.d(a, "CustomAdNetworkHandler returned null banner view");
                            }
                        } else {
                            bm.d(a, "No CustomAdNetworkHandler set");
                            adNetworkView = null;
                        }
                    }
                    baVar = new bh(adNetworkView, a2);
                } else {
                    String str10 = "Do not support binding: " + i;
                    baVar = baVar2;
                }
            }
        } else {
            baVar = new ba();
        }
        this.d.a(str, baVar);
    }

    public final void a(Context context, String str, ViewGroup viewGroup, FlurryAdSize flurryAdSize) {
        if (f(str) && !e(context)) {
            a(context, str);
        } else if (e(context)) {
        } else {
            new n(this, context, str, flurryAdSize, viewGroup, true, false).execute(new Void[0]);
        }
    }

    public final void a(AdUnit adUnit) {
        this.x = adUnit;
    }

    public final void a(FlurryAdListener flurryAdListener) {
        if (flurryAdListener != null) {
            this.G = flurryAdListener;
        }
    }

    public final void a(ICustomAdNetworkHandler iCustomAdNetworkHandler) {
        if (iCustomAdNetworkHandler != null) {
            this.F = iCustomAdNetworkHandler;
        }
    }

    public final void a(bj bjVar, ao aoVar, int i) {
        y yVar;
        boolean z;
        String str = "onEvent:event=" + bjVar.a + ",params=" + bjVar.b;
        List<y> a2 = a((AdFrame) bjVar.d.c().get(bjVar.f), bjVar);
        if (a2.isEmpty()) {
            for (Map.Entry entry : this.P.entrySet()) {
                if (((String) entry.getKey()).equals(bjVar.a)) {
                    a2.add(new y((String) entry.getValue(), bjVar.b, bjVar));
                }
            }
        }
        if (bjVar.a.equals("adWillClose")) {
            if (bjVar.a.equals("clicked")) {
                if (this.G != null) {
                    this.G.onAdClicked(bjVar.d.a().toString());
                    this.G.onAdClosed(bjVar.d.a().toString());
                }
            } else if (this.G != null) {
                this.G.onAdClosed(bjVar.d.a().toString());
            }
            Iterator it = a2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (this.Q.contains(((y) it.next()).a)) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                a2.add(new y("closeAd", Collections.emptyMap(), bjVar));
            }
        }
        if (bjVar.a.equals("renderFailed") && this.G != null) {
            this.G.onRenderFailed(bjVar.d.a().toString());
        }
        if (bjVar.a.equals("clicked") && this.G != null) {
            this.G.onAdClicked(bjVar.d.a().toString());
            this.G.onAdOpened(bjVar.d.a().toString());
        }
        y yVar2 = null;
        for (y yVar3 : a2) {
            if (yVar3.a.equals("logEvent")) {
                yVar3.b.put("__sendToServer", "true");
                yVar = yVar3;
            } else {
                yVar = yVar2;
            }
            if (yVar3.a.equals("loadAdComponents")) {
                for (Map.Entry entry2 : yVar3.c.b.entrySet()) {
                    yVar3.b.put(((String) entry2.getKey()).toString(), ((String) entry2.getValue()).toString());
                }
            }
            aoVar.a(yVar3, this, i + 1);
            yVar2 = yVar;
        }
        if (yVar2 == null) {
            HashMap hashMap = new HashMap();
            hashMap.put("__sendToServer", "false");
            aoVar.a(new y("logEvent", hashMap, bjVar), this, i + 1);
        }
    }

    public final synchronized void a(bl blVar) {
        if (this.I.size() < 32767) {
            this.I.add(blVar);
            this.J.put(blVar.b(), blVar);
        }
    }

    @Override // com.flurry.android.ao
    public final void a(y yVar, bo boVar, int i) {
        long parseLong;
        String str;
        boolean z = true;
        String str2 = "performAction:action=" + yVar.a + ",params=" + yVar.b + ",triggering event=" + yVar.c.a;
        String str3 = yVar.a;
        Context context = yVar.c.c;
        bl blVar = yVar.c.e;
        AdUnit adUnit = yVar.c.d;
        if (i > 10) {
            String str4 = "Maximum depth for event/action loop exceeded when performing action:" + str3 + "," + yVar.b + ",triggered by:" + yVar.c.a;
        } else if (str3.equals("directOpen")) {
            if (!yVar.c.b.containsKey("url")) {
                bm.b(a, "failed to perform directOpen action: no url in " + yVar.c.a);
                return;
            }
            String str5 = (String) yVar.c.b.get("url");
            if (str5.startsWith("market://")) {
                if (this.G != null) {
                    this.G.onApplicationExit(adUnit.a().toString());
                }
                b(context, str5);
                return;
            }
            Intent intent = new Intent();
            intent.setClass(context, FlurryFullscreenTakeoverActivity.class);
            intent.putExtra("url", str5);
            if (!ac.a(context, intent)) {
                bm.d(a, "Can't start FlurryFullscreenTakeoverActivity, was it declared in the manifest? Falling back to default browser");
                ac.a(context, str5);
                return;
            }
            context.startActivity(intent);
            if (this.G != null) {
                this.G.onAdOpened(adUnit.a().toString());
            }
        } else if (str3.equals("processRedirect")) {
            if (yVar.b.containsKey("url")) {
                if (this.G != null) {
                    this.G.onApplicationExit(adUnit.a().toString());
                }
                String str6 = (String) yVar.b.get("url");
                if (!str6.startsWith("http")) {
                    a(context, str6, false);
                    return;
                }
                String a2 = a(blVar, adUnit, yVar, str6);
                try {
                    str = (String) new ai(this, context, a2).execute(new Void[0]).get();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    str = "";
                } catch (ExecutionException e3) {
                    e3.printStackTrace();
                    str = "";
                }
                if (str != null) {
                    a(context, str, true);
                } else {
                    bm.d(a, "Redirect URL could not be found for: " + a2);
                }
            }
        } else if (str3.equals("verifyUrl")) {
            if (yVar.b.containsKey("url")) {
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage((String) yVar.b.get("url"));
                if (launchIntentForPackage == null || !ac.a(context, launchIntentForPackage)) {
                    z = false;
                }
                boVar.a(new bj(z ? "urlVerified" : "urlNotVerified", Collections.emptyMap(), yVar.c.c, adUnit, blVar, yVar.c.f), this, i + 1);
            }
        } else if (str3.equals("launchPackage")) {
            if (yVar.b.containsKey("package")) {
                String str7 = (String) yVar.b.get("package");
                Intent launchIntentForPackage2 = context.getPackageManager().getLaunchIntentForPackage(str7);
                if (launchIntentForPackage2 == null || !ac.a(context, launchIntentForPackage2)) {
                    a(context, "https://play.google.com/store/apps/details?id=" + str7, false);
                } else {
                    context.startActivity(launchIntentForPackage2);
                }
            }
        } else if (str3.equals("sendUrlAsync")) {
            if (yVar.b.containsKey("url")) {
                if (this.G != null) {
                    this.G.onApplicationExit(adUnit.a().toString());
                }
                String a3 = a(blVar, adUnit, yVar, (String) yVar.b.get("url"));
                String str8 = "url after is: " + a3;
                this.E.post(new d(this, context, a3));
            }
        } else if (str3.equals("sendAdLogs")) {
            j();
        } else if (str3.equals("logEvent")) {
            boolean z2 = yVar.b.containsKey("__sendToServer") && ((String) yVar.b.get("__sendToServer")).equals("true");
            yVar.b.remove("__sendToServer");
            a(yVar.c.e, yVar.c.a, z2, yVar.b);
        } else if (str3.equals("nextFrame")) {
        } else {
            if (!str3.equals("nextAdUnit")) {
                String str9 = "Unknown action:" + str3 + ",triggered by:" + yVar.c.a;
                return;
            }
            x a4 = this.d.a(adUnit.a().toString());
            if (a4 == null) {
                this.O = this.c.a(yVar.c.d.a().toString());
                return;
            }
            if (yVar.b.containsKey("delay")) {
                try {
                    parseLong = Long.parseLong((String) yVar.b.get("delay"));
                } catch (NumberFormatException e4) {
                    String str10 = "caught NumberFormatException with delay parameter in nextAdUnit:" + ((String) yVar.b.get("delay"));
                }
                a4.a((int) (parseLong * 1000));
                this.d.a(a4);
            }
            parseLong = 30;
            a4.a((int) (parseLong * 1000));
            this.d.a(a4);
        }
    }

    public final synchronized void a(DataInputStream dataInputStream) {
        while (dataInputStream.readUnsignedShort() != 0) {
            synchronized (this.I) {
                this.I.add(new bl(dataInputStream));
            }
        }
        this.k = true;
    }

    public final void a(String str) {
        this.D = str;
    }

    public final void a(List list) {
        this.K = list;
    }

    public final void a(Map map) {
        this.C = map;
    }

    public final void a(boolean z) {
        this.M = z;
    }

    public final boolean a(Context context, String str, ViewGroup viewGroup) {
        a(context, str);
        if (g(str) instanceof ba) {
            if (this.G != null) {
                this.G.spaceDidFailToReceiveAd(str.toString());
            }
            return false;
        }
        if (this.G != null) {
            this.G.spaceDidReceiveAd(str.toString());
        }
        b(context, str, viewGroup);
        return true;
    }

    public final boolean a(Context context, String str, FlurryAdSize flurryAdSize, long j) {
        if (f(str)) {
            return true;
        }
        this.E.postDelayed(new h(this, context, str, flurryAdSize), !FlurryAgent.a() ? 500 : 0);
        long currentTimeMillis = System.currentTimeMillis();
        while (ac.a(currentTimeMillis + j)) {
            if (f(str)) {
                return true;
            }
        }
        return false;
    }

    public final boolean a(Context context, String str, FlurryAdSize flurryAdSize, ViewGroup viewGroup, long j) {
        if (f(str)) {
            if (e(context)) {
                return false;
            }
            return a(context, str, viewGroup);
        }
        if (f(context) && !e(context)) {
            new n(this, context, str, flurryAdSize, viewGroup, j == 0, j == 0).execute(new Void[0]);
            if (j > 0) {
                long currentTimeMillis = System.currentTimeMillis();
                while (ac.a(currentTimeMillis + j)) {
                    if (f(str)) {
                        return a(context, str, viewGroup);
                    }
                }
            }
        } else if (!f(context) && this.G != null) {
            this.G.spaceDidFailToReceiveAd(str.toString());
        }
        return false;
    }

    public final long b() {
        return this.z;
    }

    public final void b(Context context) {
        bm.c(a, "Initializing ads -- requesting ads with precaching enabled on the server");
        this.E.postDelayed(new f(this, context), FlurryAgent.a() ? 0 : 500);
    }

    public final void b(Context context, String str) {
        if (!str.startsWith("market://details?id=")) {
            bm.d(a, "Unexpected Google Play url scheme: " + str);
            return;
        }
        String substring = str.substring("market://details?id=".length());
        if (!this.q) {
            ac.a(context, "https://market.android.com/details?id=" + substring);
            return;
        }
        try {
            ac.a(context, str);
        } catch (Exception e2) {
            bm.b(a, "Cannot launch Google Play url " + str, e2);
        }
    }

    public final void b(Context context, String str, ViewGroup viewGroup) {
        aj g2 = g(str);
        if (g2 != null) {
            if (this.G != null) {
                if (g2 instanceof bh) {
                    this.G.shouldDisplayAd(str.toString(), FlurryAdType.WEB_BANNER);
                } else {
                    this.G.shouldDisplayAd(str.toString(), FlurryAdType.WEB_TAKEOVER);
                    this.G.onAdOpened(str.toString());
                }
            }
            g2.a(context, viewGroup);
        }
    }

    public final void b(bl blVar) {
        this.w = blVar;
    }

    public final void b(String str) {
        if (o()) {
            x a2 = this.d.a(str);
            if (a2 == null) {
                h(str);
                return;
            }
            r e2 = a2.e();
            if (e2 != null) {
                e2.stop();
            }
            ViewGroup c = a2.c();
            if (c != null) {
                a2.removeAllViews();
                c.removeView(a2);
            }
            h(str);
            this.d.b(str);
        }
    }

    public final void b(List list) {
        this.c.a(list);
    }

    public final void b(Map map) {
        if (map != null) {
            this.N = map;
        }
    }

    public final String c() {
        return this.y;
    }

    public final void c(String str) {
        this.r = str;
    }

    public final void c(Map map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            this.v.put(entry.getKey(), entry.getValue());
        }
    }

    public final boolean c(Context context, String str) {
        for (int i = 0; i < 5; i++) {
            if (!f(context) || e(context)) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e2) {
                    e2.getMessage();
                }
            } else {
                HttpResponse a2 = ac.a(this.h, str, 10000, 15000, true);
                if (a2 != null && a2.getStatusLine().getStatusCode() == 200) {
                    String str2 = "URL hit succeeded for: " + str;
                    return true;
                }
            }
        }
        return false;
    }

    public final synchronized long d() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.A;
        if (elapsedRealtime <= this.B) {
            elapsedRealtime = this.B + 1;
            this.B = elapsedRealtime;
        }
        this.B = elapsedRealtime;
        return this.B;
    }

    public final void d(String str) {
        this.s = str;
    }

    public final String e() {
        return this.D;
    }

    public final void e(String str) {
        byte[] p = p();
        if (p != null) {
            a(p, str);
        }
    }

    public final void f() {
        this.N = null;
    }

    public final boolean g() {
        return this.H;
    }

    public final void h() {
        this.v.clear();
    }

    public final Map i() {
        return this.v;
    }

    public final synchronized void j() {
        this.E.post(new i(this));
    }

    public final synchronized void k() {
        DataOutputStream dataOutputStream;
        try {
            if (ah.a(this.l)) {
                dataOutputStream = new DataOutputStream(new FileOutputStream(this.l));
                try {
                    dataOutputStream.writeShort(46586);
                    synchronized (this.I) {
                        a(this.I, dataOutputStream);
                    }
                    dataOutputStream.writeShort(0);
                    ac.a(dataOutputStream);
                } catch (Throwable th) {
                    th = th;
                    bm.b(a, "", th);
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

    public final synchronized void l() {
        new k(this).execute(new Void[0]);
    }

    public final bl m() {
        return this.w;
    }

    public final AdUnit n() {
        return this.x;
    }
}
