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
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.Decoder;
import com.flurry.org.apache.avro.io.DecoderFactory;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.apache.avro.io.EncoderFactory;
import com.flurry.org.apache.avro.specific.SpecificDatumReader;
import com.flurry.org.apache.avro.specific.SpecificDatumWriter;
import com.flurry.org.apache.avro.specific.SpecificRecordBase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

final class bo implements ao {
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
  
  private volatile List I = new CopyOnWriteArrayList();
  
  private volatile Map J = new HashMap<Object, Object>();
  
  private volatile List K = new ArrayList();
  
  private volatile List L = new ArrayList();
  
  private volatile boolean M = false;
  
  private Map N;
  
  private AdUnit O;
  
  private final Map P;
  
  private final Set Q;
  
  aa c;
  
  bn d;
  
  private be h;
  
  private v i;
  
  private Display j;
  
  private boolean k = false;
  
  private File l = null;
  
  private String m;
  
  private String n;
  
  private String o;
  
  private String p;
  
  private boolean q = true;
  
  private volatile String r = null;
  
  private volatile String s = null;
  
  private volatile float t;
  
  private volatile float u;
  
  private volatile Map v;
  
  private bl w;
  
  private AdUnit x;
  
  private String y;
  
  private long z;
  
  bo() {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    hashMap.put("open", "directOpen");
    hashMap.put("expand", "doExpand");
    hashMap.put("collapse", "doCollapse");
    this.P = Collections.unmodifiableMap(hashMap);
    HashSet<?> hashSet = new HashSet();
    hashSet.addAll(Arrays.asList(new String[] { "closeAd", "processRedirect", "nextFrame", "nextAdUnit", "notifyUser" }));
    this.Q = Collections.unmodifiableSet(hashSet);
    HandlerThread handlerThread = new HandlerThread("FlurryAdThread");
    handlerThread.start();
    this.E = new Handler(handlerThread.getLooper());
    this.c = new aa();
    e = Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5) });
    f = new DecoderFactory();
    g = new z(this);
    this.i = new v(this);
  }
  
  private static int a(byte[] paramArrayOfbyte) {
    CrcMessageDigest crcMessageDigest = new CrcMessageDigest();
    crcMessageDigest.update(paramArrayOfbyte);
    return crcMessageDigest.getChecksum();
  }
  
  private at a(String paramString, boolean paramBoolean, Map paramMap) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/flurry/android/at
    //   5: dup
    //   6: aload_1
    //   7: iload_2
    //   8: aload_0
    //   9: invokevirtual d : ()J
    //   12: aload_3
    //   13: invokespecial <init> : (Ljava/lang/String;ZJLjava/util/Map;)V
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	21	finally
  }
  
  private bl a(String paramString1, String paramString2, boolean paramBoolean) {
    bl bl2 = (bl)this.J.get(paramString1);
    bl bl1 = bl2;
    if (bl2 == null)
      bl1 = ac.a(this, paramString1); 
    return a(bl1, paramString2, true, (Map)null);
  }
  
  private static SpecificRecordBase a(byte[] paramArrayOfbyte, Class paramClass) {
    BinaryDecoder binaryDecoder = f.binaryDecoder(new ByteArrayInputStream(paramArrayOfbyte), null);
    try {
      SpecificDatumReader specificDatumReader = new SpecificDatumReader();
      this(paramClass);
      SpecificRecordBase specificRecordBase = (SpecificRecordBase)specificDatumReader.read(null, (Decoder)binaryDecoder);
    } catch (ClassCastException classCastException) {
      "ClassCastException in parseAvroBinary:" + classCastException.getMessage();
      classCastException = null;
    } catch (IOException iOException) {
      "IOException in parseAvroBinary:" + iOException.getMessage();
      iOException = null;
    } 
    return (SpecificRecordBase)iOException;
  }
  
  private String a(bl parambl, AdUnit paramAdUnit, y paramy, String paramString) {
    Pattern pattern = Pattern.compile(".*?(%\\{\\w+\\}).*?");
    for (Matcher matcher = pattern.matcher(paramString); matcher.matches(); matcher = pattern.matcher(paramString))
      paramString = this.i.a(parambl, paramAdUnit, paramString, matcher.group(1)); 
    return paramString;
  }
  
  private static List a(AdFrame paramAdFrame, bj parambj) {
    ArrayList<y> arrayList = new ArrayList();
    List list = paramAdFrame.e();
    String str = parambj.a;
    for (Callback callback : list) {
      if (callback.a().toString().equals(str))
        for (CharSequence charSequence : callback.b()) {
          HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
          String str1 = charSequence.toString();
          int i = str1.indexOf('?');
          charSequence = str1;
          if (i != -1) {
            String str2 = str1.substring(0, i);
            str1 = str1.substring(i + 1);
            charSequence = str1;
            if (str1.contains("%{eventParams}")) {
              charSequence = str1.replace("%{eventParams}", "");
              hashMap.putAll(parambj.b);
            } 
            hashMap.putAll(ac.e((String)charSequence));
            charSequence = str2;
          } 
          arrayList.add(new y((String)charSequence, hashMap, parambj));
        }  
    } 
    return arrayList;
  }
  
  private void a(Context paramContext, String paramString, boolean paramBoolean) {
    if (o())
      this.E.post(new c(this, paramString, paramContext, paramBoolean)); 
  }
  
  private void a(SdkLogResponse paramSdkLogResponse) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual a : ()Ljava/lang/CharSequence;
    //   6: invokevirtual toString : ()Ljava/lang/String;
    //   9: ldc_w 'success'
    //   12: invokevirtual equals : (Ljava/lang/Object;)Z
    //   15: ifeq -> 32
    //   18: aload_0
    //   19: getfield I : Ljava/util/List;
    //   22: aload_0
    //   23: getfield K : Ljava/util/List;
    //   26: invokeinterface removeAll : (Ljava/util/Collection;)Z
    //   31: pop
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	35	finally
  }
  
  private void a(List<bl> paramList, DataOutputStream paramDataOutputStream) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokeinterface size : ()I
    //   8: istore #4
    //   10: iconst_0
    //   11: istore_3
    //   12: iload_3
    //   13: iload #4
    //   15: if_icmpge -> 96
    //   18: aload_1
    //   19: iload_3
    //   20: invokeinterface get : (I)Ljava/lang/Object;
    //   25: checkcast com/flurry/android/bl
    //   28: aload_2
    //   29: invokevirtual a : (Ljava/io/DataOutput;)V
    //   32: iinc #3, 1
    //   35: goto -> 12
    //   38: astore #5
    //   40: getstatic com/flurry/android/bo.a : Ljava/lang/String;
    //   43: astore #5
    //   45: new java/lang/StringBuilder
    //   48: astore #6
    //   50: aload #6
    //   52: invokespecial <init> : ()V
    //   55: aload #5
    //   57: aload #6
    //   59: ldc_w 'unable to convert adLog to byte[]: '
    //   62: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: aload_1
    //   66: iload_3
    //   67: invokeinterface get : (I)Ljava/lang/Object;
    //   72: checkcast com/flurry/android/bl
    //   75: invokevirtual b : ()Ljava/lang/String;
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual toString : ()Ljava/lang/String;
    //   84: invokestatic a : (Ljava/lang/String;Ljava/lang/String;)I
    //   87: pop
    //   88: goto -> 32
    //   91: astore_1
    //   92: aload_0
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    //   96: aload_0
    //   97: monitorexit
    //   98: return
    // Exception table:
    //   from	to	target	type
    //   2	10	91	finally
    //   18	32	38	java/io/IOException
    //   18	32	91	finally
    //   40	88	91	finally
  }
  
  static boolean a(Context paramContext, String paramString1, String paramString2) {
    Intent intent = new Intent(paramString2);
    intent.setData(Uri.parse(paramString1));
    return ac.a(paramContext, intent);
  }
  
  private boolean a(byte[] paramArrayOfbyte, String paramString) {
    String str;
    if (paramString == null)
      return false; 
    if (paramString.equals("/v3/getAds.do")) {
      StringBuilder stringBuilder = new StringBuilder();
      if (this.r != null) {
        str = this.r;
      } else if (FlurryAgent.getUseHttps()) {
        str = "https://ads.flurry.com";
      } else {
        str = "http://ads.flurry.com";
      } 
      str = stringBuilder.append(str).append(paramString).toString();
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      if (this.s != null) {
        str = this.s;
      } else if (FlurryAgent.getUseHttps()) {
        str = "https://adlog.flurry.com";
      } else {
        str = "http://adlog.flurry.com";
      } 
      str = stringBuilder.append(str).append(paramString).toString();
    } 
    paramArrayOfbyte = b(paramArrayOfbyte, str);
    if (paramArrayOfbyte != null) {
      boolean bool1;
      try {
        if (paramString.equals("/postAdLog.do"))
          b(paramArrayOfbyte); 
        bool1 = true;
      } catch (IOException iOException) {
        bm.b(a, "IOException: " + iOException.toString());
      } 
      return bool1;
    } 
    boolean bool = true;
  }
  
  private static byte[] a(InputStream paramInputStream) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[128];
    while (true) {
      int i = paramInputStream.read(arrayOfByte);
      if (i != -1) {
        byteArrayOutputStream.write(arrayOfByte, 0, i);
        continue;
      } 
      return byteArrayOutputStream.toByteArray();
    } 
  }
  
  private void b(byte[] paramArrayOfbyte) {
    SdkLogResponse sdkLogResponse = (SdkLogResponse)a(paramArrayOfbyte, SdkLogResponse.class);
    if (sdkLogResponse != null) {
      "got an AdLogResponse:" + sdkLogResponse;
      if (sdkLogResponse.a().toString().equals("success")) {
        a(sdkLogResponse);
        return;
      } 
    } else {
      return;
    } 
    Iterator<CharSequence> iterator = sdkLogResponse.b().iterator();
    while (true) {
      if (iterator.hasNext()) {
        CharSequence charSequence = iterator.next();
        bm.b(a, charSequence.toString());
        continue;
      } 
      return;
    } 
  }
  
  private byte[] b(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, FlurryAdSize paramFlurryAdSize) {
    byte[] arrayOfByte;
    List list = q();
    AdRequest adRequest = AdRequest.a().setApiKey(this.y).setAdSpaceName("").setBindings(e).setAdReportedIds(list).setLocation(Location.a().setLat(this.t).setLon(this.u).build()).setTestDevice(this.M).setAgentVersion(Integer.toString(FlurryAgent.getAgentVersion())).setSessionId(this.z).setAdViewContainer(AdViewContainer.a().setScreenHeight(this.j.getHeight()).setScreenWidth(this.j.getWidth()).setViewHeight(paramInt2).setViewWidth(paramInt1).build()).setLocale(this.m).setTimezone(this.n).setOsVersion(this.o).setDevicePlatform(this.p).build();
    if (paramBoolean) {
      adRequest.a(Boolean.valueOf(paramBoolean));
    } else {
      adRequest.a(paramString);
    } 
    if (paramFlurryAdSize != null)
      adRequest.a(TestAds.a().setAdspacePlacement(paramFlurryAdSize.a()).build()); 
    if (this.N != null) {
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      for (Map.Entry entry : this.N.entrySet())
        hashMap.put(entry.getKey(), entry.getValue()); 
      adRequest.a(hashMap);
    } 
    "Got ad request  " + adRequest;
    SpecificDatumWriter specificDatumWriter = new SpecificDatumWriter(AdRequest.class);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(byteArrayOutputStream, null);
    try {
      specificDatumWriter.write(adRequest, (Encoder)binaryEncoder);
      binaryEncoder.flush();
      arrayOfByte = byteArrayOutputStream.toByteArray();
    } catch (IOException iOException) {
      iOException.getMessage();
      arrayOfByte = new byte[0];
    } 
    return arrayOfByte;
  }
  
  private byte[] b(byte[] paramArrayOfbyte, String paramString) {
    ByteArrayEntity byteArrayEntity = new ByteArrayEntity(paramArrayOfbyte);
    byteArrayEntity.setContentType("avro/binary");
    HttpPost httpPost = new HttpPost(paramString);
    httpPost.setEntity((HttpEntity)byteArrayEntity);
    httpPost.setHeader("accept", "avro/binary");
    httpPost.setHeader("FM-Checksum", Integer.toString(a(paramArrayOfbyte)));
    BasicHttpParams basicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)basicHttpParams, 10000);
    HttpConnectionParams.setSoTimeout((HttpParams)basicHttpParams, 15000);
    httpPost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
    null = this.h.a((HttpParams)basicHttpParams);
    try {
      HttpResponse httpResponse = null.execute((HttpUriRequest)httpPost);
      int i = httpResponse.getStatusLine().getStatusCode();
      if (i == 200 && httpResponse.getEntity() != null && httpResponse.getEntity().getContentLength() != 0L) {
        bm.c(a, "Request successful");
        byte[] arrayOfByte2 = a(httpResponse.getEntity().getContent());
        byteArrayEntity.consumeContent();
        String str = Integer.toString(a(arrayOfByte2));
        byte[] arrayOfByte1 = arrayOfByte2;
        if (httpResponse.containsHeader("FM-Checksum")) {
          if (httpResponse.getFirstHeader("FM-Checksum").getValue().equals(str))
            return arrayOfByte2; 
        } else {
          return arrayOfByte1;
        } 
      } else {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        this();
        bm.b(str, stringBuilder.append("Request failed with HTTP ").append(i).toString());
      } 
    } catch (IOException iOException) {
      bm.b(a, "IOException: " + iOException.toString());
    } 
    return null;
  }
  
  private static boolean e(Context paramContext) {
    return ((KeyguardManager)paramContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
  }
  
  private static boolean f(Context paramContext) {
    boolean bool1;
    if (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0)
      return true; 
    NetworkInfo networkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (networkInfo != null && (networkInfo.isConnected() || networkInfo.isRoaming())) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    boolean bool2 = bool1;
    if (!bool1) {
      bm.d(a, "No connectivity found.");
      bool2 = bool1;
    } 
    return bool2;
  }
  
  private boolean f(String paramString) {
    return this.c.b(paramString);
  }
  
  private aj g(String paramString) {
    return this.d.c(paramString);
  }
  
  private void h(String paramString) {
    this.d.d(paramString);
  }
  
  private boolean o() {
    if (!this.H)
      bm.d(a, "Flurry Ads not initialized"); 
    if (this.D == null)
      bm.d(a, "Cannot identify UDID."); 
    return this.H;
  }
  
  private byte[] p() {
    List list1;
    null = null;
    List list2 = q();
    synchronized (this.I) {
      List list = g.a(this.I);
      if (list.size() == 0)
        return null; 
      SdkLogRequest sdkLogRequest = SdkLogRequest.a().setApiKey(this.y).setAdReportedIds(list2).setSdkAdLogs(list).setTestDevice(false).setAgentTimestamp(System.currentTimeMillis()).build();
      "Got ad log request:" + sdkLogRequest.toString();
      SpecificDatumWriter specificDatumWriter = new SpecificDatumWriter(SdkLogRequest.class);
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(byteArrayOutputStream, null);
      try {
        specificDatumWriter.write(sdkLogRequest, (Encoder)binaryEncoder);
        binaryEncoder.flush();
        null = byteArrayOutputStream.toByteArray();
      } catch (IOException iOException) {
        iOException.getMessage();
      } 
    } 
  }
  
  private List q() {
    ArrayList<AdReportedId> arrayList = new ArrayList();
    ByteBuffer byteBuffer = ByteBuffer.wrap(this.D.getBytes());
    arrayList.add(AdReportedId.a().setId(byteBuffer).setType(0).build());
    for (Map.Entry entry : this.C.entrySet())
      arrayList.add(AdReportedId.a().setId((ByteBuffer)entry.getValue()).setType(((Integer)entry.getKey()).intValue()).build()); 
    return arrayList;
  }
  
  final bl a(bl parambl, String paramString, boolean paramBoolean, Map paramMap) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield I : Ljava/util/List;
    //   8: aload_1
    //   9: invokeinterface contains : (Ljava/lang/Object;)Z
    //   14: ifne -> 28
    //   17: aload_0
    //   18: getfield I : Ljava/util/List;
    //   21: aload_1
    //   22: invokeinterface add : (Ljava/lang/Object;)Z
    //   27: pop
    //   28: aload_1
    //   29: aload_0
    //   30: aload_2
    //   31: iload_3
    //   32: aload #4
    //   34: invokespecial a : (Ljava/lang/String;ZLjava/util/Map;)Lcom/flurry/android/at;
    //   37: invokevirtual a : (Lcom/flurry/android/at;)V
    //   40: aload_1
    //   41: monitorexit
    //   42: aload_0
    //   43: monitorexit
    //   44: aload_1
    //   45: areturn
    //   46: astore_2
    //   47: aload_1
    //   48: monitorexit
    //   49: aload_2
    //   50: athrow
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    // Exception table:
    //   from	to	target	type
    //   2	4	51	finally
    //   4	28	46	finally
    //   28	42	46	finally
    //   47	49	46	finally
    //   49	51	51	finally
  }
  
  final List a(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, FlurryAdSize paramFlurryAdSize) {
    byte[] arrayOfByte2 = b(paramString, paramInt1, paramInt2, paramBoolean, paramFlurryAdSize);
    if (arrayOfByte2 == null || arrayOfByte2.length < 1)
      return Collections.emptyList(); 
    if (this.r != null) {
      paramString = this.r + "/v3/getAds.do";
    } else if (FlurryAgent.getUseHttps()) {
      paramString = "https://ads.flurry.com/v3/getAds.do";
    } else {
      paramString = "http://ads.flurry.com/v3/getAds.do";
    } 
    byte[] arrayOfByte1 = b(arrayOfByte2, paramString);
    if (arrayOfByte1 == null || arrayOfByte1.length < 1)
      return Collections.emptyList(); 
    AdResponse adResponse = (AdResponse)a(arrayOfByte1, AdResponse.class);
    if (adResponse == null)
      return Collections.emptyList(); 
    "adResponse: " + adResponse;
    if (adResponse.b().size() > 0) {
      bm.b(a, "Ad server responded with the following error(s):");
      for (CharSequence charSequence : adResponse.b())
        bm.b(a, charSequence.toString()); 
      return Collections.emptyList();
    } 
    if (adResponse.a() == null || adResponse.a().size() == 0) {
      bm.b(a, "Ad server responded but sent no ad units.");
      return Collections.emptyList();
    } 
    return adResponse.a();
  }
  
  final void a() {
    this.d.a((Context)null);
    j();
  }
  
  final void a(float paramFloat1, float paramFloat2) {
    this.t = paramFloat1;
    this.u = paramFloat2;
  }
  
  final void a(Context paramContext) {
    this.d.a(paramContext);
    this.d.a();
  }
  
  final void a(Context paramContext, long paramLong1, long paramLong2) {
    this.z = paramLong1;
    this.A = paramLong2;
    this.B = 0L;
    this.d.a(paramContext);
  }
  
  final void a(Context paramContext, p paramp) {
    if (!this.H) {
      this.y = paramp.a;
      this.d = new bn(paramContext);
      this.H = true;
    } 
    this.h = paramp.b;
    this.m = paramp.d;
    this.n = paramp.e;
    this.o = Build.VERSION.RELEASE;
    this.p = Build.DEVICE;
    this.j = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    this.l = paramContext.getFileStreamPath(".flurryadlog." + Integer.toString(this.y.hashCode(), 16));
    String str = paramContext.getPackageName();
    this.q = a(paramContext, "market://details?id=" + str, "android.intent.action.VIEW");
    this.v = new HashMap<Object, Object>();
  }
  
  final void a(Context paramContext, String paramString) {
    ba ba1;
    AdUnit adUnit = this.c.a(paramString);
    if (adUnit == null) {
      if (this.G != null)
        this.G.spaceDidFailToReceiveAd(paramString.toString()); 
      return;
    } 
    if (this.G != null)
      this.G.spaceDidReceiveAd(paramString.toString()); 
    ba ba2 = new ba();
    this.O = adUnit;
    List<AdFrame> list = adUnit.c();
    if (list.size() > 0) {
      AdFrame adFrame = list.get(0);
      int i = adFrame.a().intValue();
      String str1 = adFrame.c().toString();
      String str2 = adFrame.d().d().toString();
      bl bl1 = a(adFrame.f().toString(), "requested", true);
      a(new bj("filled", Collections.emptyMap(), paramContext, adUnit, bl1, 0), this, 1);
      if (this.O == null) {
        ba1 = new ba();
      } else {
        bh bh;
        if (this.O != adUnit) {
          adUnit = this.O;
          adFrame = adUnit.c().get(0);
          i = adFrame.a().intValue();
          str1 = adFrame.c().toString();
          str2 = adFrame.d().d().toString();
          bl1 = a(adFrame.f().toString(), "requested", true);
        } 
        "Processing ad request for binding: " + i + ", networkType: " + str1 + ", format: " + str2;
        if (adUnit.d().intValue() == 1 || i == 2 || i == 1 || i == 3) {
          bk bk;
          if (str2.equals("takeover")) {
            this.w = bl1;
            this.x = adUnit;
            bk = new bk(adUnit);
          } else {
            bh = new bh(new ap((Context)bk, this, bl1, adUnit, 0), adUnit);
          } 
        } else if (i == 4) {
          ab ab;
          g g;
          AdSpaceLayout adSpaceLayout = adFrame.d();
          AdCreative adCreative = new AdCreative(adSpaceLayout.b().intValue(), adSpaceLayout.a().intValue(), adSpaceLayout.d().toString(), adSpaceLayout.c().toString(), adSpaceLayout.e().toString());
          if (str1.equalsIgnoreCase("Admob")) {
            "Retrieving BannerView for:" + str1;
            ab = new ab((Context)bh, this, bl1, adCreative);
            ab.d = 0;
            ab.c = adUnit;
          } else {
            al al;
            if (str1.equalsIgnoreCase("Millennial Media")) {
              "Retrieving BannerView for:" + str1;
              al = new al((Context)ab, this, bl1, adCreative);
              al.d = 0;
              al.c = adUnit;
            } else {
              ae ae;
              if (str1.equalsIgnoreCase("InMobi")) {
                "Retrieving BannerView for:" + str1;
                ae = new ae((Context)al, this, bl1, adCreative);
                ae.d = 0;
                ae.c = adUnit;
              } else {
                ag ag;
                if (str1.equalsIgnoreCase("Mobclix")) {
                  "Retrieving BannerView for:" + str1;
                  ag = new ag((Context)ae, this, bl1, adCreative);
                  ag.d = 0;
                  ag.c = adUnit;
                } else if (str1.equalsIgnoreCase("Jumptap")) {
                  "Retrieving BannerView for:" + str1;
                  g = new g((Context)ag, this, bl1, adCreative);
                  g.d = 0;
                  g.c = adUnit;
                } else {
                  ICustomAdNetworkHandler iCustomAdNetworkHandler = this.F;
                  if (iCustomAdNetworkHandler != null) {
                    AdNetworkView adNetworkView = iCustomAdNetworkHandler.getAdFromNetwork((Context)g, adCreative, str1);
                    if (adNetworkView != null) {
                      adNetworkView.a = this;
                      adNetworkView.b = bl1;
                      adNetworkView.d = 0;
                      adNetworkView.c = adUnit;
                    } else {
                      bm.d(a, "CustomAdNetworkHandler returned null banner view");
                    } 
                  } else {
                    bm.d(a, "No CustomAdNetworkHandler set");
                    g = null;
                  } 
                } 
              } 
            } 
          } 
          bh bh1 = new bh(g, adUnit);
        } else {
          "Do not support binding: " + i;
          ba1 = ba2;
        } 
      } 
    } else {
      ba1 = new ba();
    } 
    this.d.a(paramString, ba1);
  }
  
  final void a(Context paramContext, String paramString, ViewGroup paramViewGroup, FlurryAdSize paramFlurryAdSize) {
    if (f(paramString) && !e(paramContext)) {
      a(paramContext, paramString);
      return;
    } 
    if (!e(paramContext))
      (new n(this, paramContext, paramString, paramFlurryAdSize, paramViewGroup, true, false)).execute((Object[])new Void[0]); 
  }
  
  final void a(AdUnit paramAdUnit) {
    this.x = paramAdUnit;
  }
  
  final void a(FlurryAdListener paramFlurryAdListener) {
    if (paramFlurryAdListener != null)
      this.G = paramFlurryAdListener; 
  }
  
  final void a(ICustomAdNetworkHandler paramICustomAdNetworkHandler) {
    if (paramICustomAdNetworkHandler != null)
      this.F = paramICustomAdNetworkHandler; 
  }
  
  public final void a(bj parambj, ao paramao, int paramInt) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: ldc_w 'onEvent:event='
    //   10: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: aload_1
    //   14: getfield a : Ljava/lang/String;
    //   17: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: ldc_w ',params='
    //   23: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: aload_1
    //   27: getfield b : Ljava/util/Map;
    //   30: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   33: invokevirtual toString : ()Ljava/lang/String;
    //   36: pop
    //   37: aload_1
    //   38: getfield d : Lcom/flurry/android/AdUnit;
    //   41: invokevirtual c : ()Ljava/util/List;
    //   44: aload_1
    //   45: getfield f : I
    //   48: invokeinterface get : (I)Ljava/lang/Object;
    //   53: checkcast com/flurry/android/AdFrame
    //   56: aload_1
    //   57: invokestatic a : (Lcom/flurry/android/AdFrame;Lcom/flurry/android/bj;)Ljava/util/List;
    //   60: astore #5
    //   62: aload #5
    //   64: invokeinterface isEmpty : ()Z
    //   69: ifeq -> 163
    //   72: aload_0
    //   73: getfield P : Ljava/util/Map;
    //   76: invokeinterface entrySet : ()Ljava/util/Set;
    //   81: invokeinterface iterator : ()Ljava/util/Iterator;
    //   86: astore #6
    //   88: aload #6
    //   90: invokeinterface hasNext : ()Z
    //   95: ifeq -> 163
    //   98: aload #6
    //   100: invokeinterface next : ()Ljava/lang/Object;
    //   105: checkcast java/util/Map$Entry
    //   108: astore #7
    //   110: aload #7
    //   112: invokeinterface getKey : ()Ljava/lang/Object;
    //   117: checkcast java/lang/String
    //   120: aload_1
    //   121: getfield a : Ljava/lang/String;
    //   124: invokevirtual equals : (Ljava/lang/Object;)Z
    //   127: ifeq -> 88
    //   130: aload #5
    //   132: new com/flurry/android/y
    //   135: dup
    //   136: aload #7
    //   138: invokeinterface getValue : ()Ljava/lang/Object;
    //   143: checkcast java/lang/String
    //   146: aload_1
    //   147: getfield b : Ljava/util/Map;
    //   150: aload_1
    //   151: invokespecial <init> : (Ljava/lang/String;Ljava/util/Map;Lcom/flurry/android/bj;)V
    //   154: invokeinterface add : (Ljava/lang/Object;)Z
    //   159: pop
    //   160: goto -> 88
    //   163: aload_1
    //   164: getfield a : Ljava/lang/String;
    //   167: ldc_w 'adWillClose'
    //   170: invokevirtual equals : (Ljava/lang/Object;)Z
    //   173: ifeq -> 311
    //   176: aload_1
    //   177: getfield a : Ljava/lang/String;
    //   180: ldc_w 'clicked'
    //   183: invokevirtual equals : (Ljava/lang/Object;)Z
    //   186: ifeq -> 573
    //   189: aload_0
    //   190: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   193: ifnull -> 234
    //   196: aload_0
    //   197: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   200: aload_1
    //   201: getfield d : Lcom/flurry/android/AdUnit;
    //   204: invokevirtual a : ()Ljava/lang/CharSequence;
    //   207: invokevirtual toString : ()Ljava/lang/String;
    //   210: invokeinterface onAdClicked : (Ljava/lang/String;)V
    //   215: aload_0
    //   216: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   219: aload_1
    //   220: getfield d : Lcom/flurry/android/AdUnit;
    //   223: invokevirtual a : ()Ljava/lang/CharSequence;
    //   226: invokevirtual toString : ()Ljava/lang/String;
    //   229: invokeinterface onAdClosed : (Ljava/lang/String;)V
    //   234: aload #5
    //   236: invokeinterface iterator : ()Ljava/util/Iterator;
    //   241: astore #7
    //   243: aload #7
    //   245: invokeinterface hasNext : ()Z
    //   250: ifeq -> 672
    //   253: aload #7
    //   255: invokeinterface next : ()Ljava/lang/Object;
    //   260: checkcast com/flurry/android/y
    //   263: astore #6
    //   265: aload_0
    //   266: getfield Q : Ljava/util/Set;
    //   269: aload #6
    //   271: getfield a : Ljava/lang/String;
    //   274: invokeinterface contains : (Ljava/lang/Object;)Z
    //   279: ifeq -> 243
    //   282: iconst_1
    //   283: istore #4
    //   285: iload #4
    //   287: ifne -> 311
    //   290: aload #5
    //   292: new com/flurry/android/y
    //   295: dup
    //   296: ldc 'closeAd'
    //   298: invokestatic emptyMap : ()Ljava/util/Map;
    //   301: aload_1
    //   302: invokespecial <init> : (Ljava/lang/String;Ljava/util/Map;Lcom/flurry/android/bj;)V
    //   305: invokeinterface add : (Ljava/lang/Object;)Z
    //   310: pop
    //   311: aload_1
    //   312: getfield a : Ljava/lang/String;
    //   315: ldc_w 'renderFailed'
    //   318: invokevirtual equals : (Ljava/lang/Object;)Z
    //   321: ifeq -> 350
    //   324: aload_0
    //   325: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   328: ifnull -> 350
    //   331: aload_0
    //   332: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   335: aload_1
    //   336: getfield d : Lcom/flurry/android/AdUnit;
    //   339: invokevirtual a : ()Ljava/lang/CharSequence;
    //   342: invokevirtual toString : ()Ljava/lang/String;
    //   345: invokeinterface onRenderFailed : (Ljava/lang/String;)V
    //   350: aload_1
    //   351: getfield a : Ljava/lang/String;
    //   354: ldc_w 'clicked'
    //   357: invokevirtual equals : (Ljava/lang/Object;)Z
    //   360: ifeq -> 408
    //   363: aload_0
    //   364: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   367: ifnull -> 408
    //   370: aload_0
    //   371: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   374: aload_1
    //   375: getfield d : Lcom/flurry/android/AdUnit;
    //   378: invokevirtual a : ()Ljava/lang/CharSequence;
    //   381: invokevirtual toString : ()Ljava/lang/String;
    //   384: invokeinterface onAdClicked : (Ljava/lang/String;)V
    //   389: aload_0
    //   390: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   393: aload_1
    //   394: getfield d : Lcom/flurry/android/AdUnit;
    //   397: invokevirtual a : ()Ljava/lang/CharSequence;
    //   400: invokevirtual toString : ()Ljava/lang/String;
    //   403: invokeinterface onAdOpened : (Ljava/lang/String;)V
    //   408: aload #5
    //   410: invokeinterface iterator : ()Ljava/util/Iterator;
    //   415: astore #7
    //   417: aconst_null
    //   418: astore #5
    //   420: aload #7
    //   422: invokeinterface hasNext : ()Z
    //   427: ifeq -> 617
    //   430: aload #7
    //   432: invokeinterface next : ()Ljava/lang/Object;
    //   437: checkcast com/flurry/android/y
    //   440: astore #6
    //   442: aload #6
    //   444: getfield a : Ljava/lang/String;
    //   447: ldc_w 'logEvent'
    //   450: invokevirtual equals : (Ljava/lang/Object;)Z
    //   453: ifeq -> 669
    //   456: aload #6
    //   458: getfield b : Ljava/util/Map;
    //   461: ldc_w '__sendToServer'
    //   464: ldc_w 'true'
    //   467: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   472: pop
    //   473: aload #6
    //   475: astore #5
    //   477: aload #6
    //   479: getfield a : Ljava/lang/String;
    //   482: ldc_w 'loadAdComponents'
    //   485: invokevirtual equals : (Ljava/lang/Object;)Z
    //   488: ifeq -> 602
    //   491: aload #6
    //   493: getfield c : Lcom/flurry/android/bj;
    //   496: getfield b : Ljava/util/Map;
    //   499: invokeinterface entrySet : ()Ljava/util/Set;
    //   504: invokeinterface iterator : ()Ljava/util/Iterator;
    //   509: astore #9
    //   511: aload #9
    //   513: invokeinterface hasNext : ()Z
    //   518: ifeq -> 602
    //   521: aload #9
    //   523: invokeinterface next : ()Ljava/lang/Object;
    //   528: checkcast java/util/Map$Entry
    //   531: astore #8
    //   533: aload #6
    //   535: getfield b : Ljava/util/Map;
    //   538: aload #8
    //   540: invokeinterface getKey : ()Ljava/lang/Object;
    //   545: checkcast java/lang/String
    //   548: invokevirtual toString : ()Ljava/lang/String;
    //   551: aload #8
    //   553: invokeinterface getValue : ()Ljava/lang/Object;
    //   558: checkcast java/lang/String
    //   561: invokevirtual toString : ()Ljava/lang/String;
    //   564: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   569: pop
    //   570: goto -> 511
    //   573: aload_0
    //   574: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   577: ifnull -> 234
    //   580: aload_0
    //   581: getfield G : Lcom/flurry/android/FlurryAdListener;
    //   584: aload_1
    //   585: getfield d : Lcom/flurry/android/AdUnit;
    //   588: invokevirtual a : ()Ljava/lang/CharSequence;
    //   591: invokevirtual toString : ()Ljava/lang/String;
    //   594: invokeinterface onAdClosed : (Ljava/lang/String;)V
    //   599: goto -> 234
    //   602: aload_2
    //   603: aload #6
    //   605: aload_0
    //   606: iload_3
    //   607: iconst_1
    //   608: iadd
    //   609: invokeinterface a : (Lcom/flurry/android/y;Lcom/flurry/android/bo;I)V
    //   614: goto -> 420
    //   617: aload #5
    //   619: ifnonnull -> 668
    //   622: new java/util/HashMap
    //   625: dup
    //   626: invokespecial <init> : ()V
    //   629: astore #5
    //   631: aload #5
    //   633: ldc_w '__sendToServer'
    //   636: ldc_w 'false'
    //   639: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   644: pop
    //   645: aload_2
    //   646: new com/flurry/android/y
    //   649: dup
    //   650: ldc_w 'logEvent'
    //   653: aload #5
    //   655: aload_1
    //   656: invokespecial <init> : (Ljava/lang/String;Ljava/util/Map;Lcom/flurry/android/bj;)V
    //   659: aload_0
    //   660: iload_3
    //   661: iconst_1
    //   662: iadd
    //   663: invokeinterface a : (Lcom/flurry/android/y;Lcom/flurry/android/bo;I)V
    //   668: return
    //   669: goto -> 477
    //   672: iconst_0
    //   673: istore #4
    //   675: goto -> 285
  }
  
  final void a(bl parambl) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield I : Ljava/util/List;
    //   6: invokeinterface size : ()I
    //   11: sipush #32767
    //   14: if_icmpge -> 43
    //   17: aload_0
    //   18: getfield I : Ljava/util/List;
    //   21: aload_1
    //   22: invokeinterface add : (Ljava/lang/Object;)Z
    //   27: pop
    //   28: aload_0
    //   29: getfield J : Ljava/util/Map;
    //   32: aload_1
    //   33: invokevirtual b : ()Ljava/lang/String;
    //   36: aload_1
    //   37: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   42: pop
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   2	43	46	finally
  }
  
  public final void a(y paramy, bo parambo, int paramInt) {
    Intent intent1;
    String str1;
    d d;
    String str2;
    long l;
    String str3;
    x x;
    Intent intent2;
    boolean bool = true;
    "performAction:action=" + paramy.a + ",params=" + paramy.b + ",triggering event=" + paramy.c.a;
    String str4 = paramy.a;
    Context context = paramy.c.c;
    bl bl1 = paramy.c.e;
    AdUnit adUnit = paramy.c.d;
    if (paramInt > 10) {
      "Maximum depth for event/action loop exceeded when performing action:" + str4 + "," + paramy.b + ",triggered by:" + paramy.c.a;
      return;
    } 
    if (str4.equals("directOpen")) {
      if (paramy.c.b.containsKey("url")) {
        str2 = (String)paramy.c.b.get("url");
        if (str2.startsWith("market://")) {
          if (this.G != null)
            this.G.onApplicationExit(adUnit.a().toString()); 
          b(context, str2);
          return;
        } 
        intent1 = new Intent();
        intent1.setClass(context, FlurryFullscreenTakeoverActivity.class);
        intent1.putExtra("url", str2);
        if (ac.a(context, intent1)) {
          context.startActivity(intent1);
          if (this.G != null)
            this.G.onAdOpened(adUnit.a().toString()); 
          return;
        } 
        bm.d(a, "Can't start FlurryFullscreenTakeoverActivity, was it declared in the manifest? Falling back to default browser");
        ac.a(context, str2);
        return;
      } 
      bm.b(a, "failed to perform directOpen action: no url in " + ((y)intent1).c.a);
      return;
    } 
    if (str4.equals("processRedirect")) {
      if (((y)intent1).b.containsKey("url")) {
        if (this.G != null)
          this.G.onApplicationExit(adUnit.a().toString()); 
        str2 = (String)((y)intent1).b.get("url");
        if (str2.startsWith("http")) {
          str2 = a(bl1, adUnit, (y)intent1, str2);
          ai ai = new ai(this, context, str2);
          try {
            str1 = (String)ai.execute((Object[])new Void[0]).get();
            if (str1 != null) {
              a(context, str1, true);
              return;
            } 
          } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            str1 = "";
            if (str1 != null) {
              a(context, str1, true);
              return;
            } 
          } catch (ExecutionException executionException) {
            executionException.printStackTrace();
            str1 = "";
            if (str1 != null) {
              a(context, str1, true);
              return;
            } 
          } 
          bm.d(a, "Redirect URL could not be found for: " + str2);
          return;
        } 
        a(context, str2, false);
      } 
      return;
    } 
    if (str4.equals("verifyUrl")) {
      if (((y)str1).b.containsKey("url")) {
        str4 = (String)((y)str1).b.get("url");
        intent2 = context.getPackageManager().getLaunchIntentForPackage(str4);
        if (intent2 == null || !ac.a(context, intent2))
          bool = false; 
        if (bool) {
          str3 = "urlVerified";
        } else {
          str3 = "urlNotVerified";
        } 
        str2.a(new bj(str3, Collections.emptyMap(), ((y)str1).c.c, adUnit, bl1, ((y)str1).c.f), this, paramInt + 1);
      } 
      return;
    } 
    if (intent2.equals("launchPackage")) {
      if (((y)str1).b.containsKey("package")) {
        str1 = (String)((y)str1).b.get("package");
        Intent intent = str3.getPackageManager().getLaunchIntentForPackage(str1);
        if (intent != null && ac.a((Context)str3, intent)) {
          str3.startActivity(intent);
          return;
        } 
        a((Context)str3, "https://play.google.com/store/apps/details?id=" + str1, false);
      } 
      return;
    } 
    if (intent2.equals("sendUrlAsync")) {
      if (((y)str1).b.containsKey("url")) {
        if (this.G != null)
          this.G.onApplicationExit(adUnit.a().toString()); 
        str1 = a(bl1, adUnit, (y)str1, (String)((y)str1).b.get("url"));
        "url after is: " + str1;
        d = new d(this, (Context)str3, str1);
        this.E.post(d);
      } 
      return;
    } 
    if (intent2.equals("sendAdLogs")) {
      j();
      return;
    } 
    if (intent2.equals("logEvent")) {
      boolean bool1;
      if (((y)d).b.containsKey("__sendToServer") && ((String)((y)d).b.get("__sendToServer")).equals("true")) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      ((y)d).b.remove("__sendToServer");
      a(((y)d).c.e, ((y)d).c.a, bool1, ((y)d).b);
      return;
    } 
    if (!intent2.equals("nextFrame")) {
      String str;
      if (intent2.equals("nextAdUnit")) {
        x = this.d.a(adUnit.a().toString());
        if (x != null) {
          if (((y)d).b.containsKey("delay"))
            try {
              long l1 = Long.parseLong((String)((y)d).b.get("delay"));
              x.a((int)(l1 * 1000L));
              this.d.a(x);
              return;
            } catch (NumberFormatException numberFormatException) {
              "caught NumberFormatException with delay parameter in nextAdUnit:" + (String)((y)d).b.get("delay");
            }  
          l = 30L;
        } else {
          str = ((y)d).c.d.a().toString();
          this.O = this.c.a(str);
          return;
        } 
      } else {
        "Unknown action:" + intent2 + ",triggered by:" + ((y)str).c.a;
        return;
      } 
    } else {
      return;
    } 
    x.a((int)(l * 1000L));
    this.d.a(x);
  }
  
  final void a(DataInputStream paramDataInputStream) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual readUnsignedShort : ()I
    //   6: ifeq -> 55
    //   9: aload_0
    //   10: getfield I : Ljava/util/List;
    //   13: astore_2
    //   14: aload_2
    //   15: monitorenter
    //   16: aload_0
    //   17: getfield I : Ljava/util/List;
    //   20: astore #4
    //   22: new com/flurry/android/bl
    //   25: astore_3
    //   26: aload_3
    //   27: aload_1
    //   28: invokespecial <init> : (Ljava/io/DataInput;)V
    //   31: aload #4
    //   33: aload_3
    //   34: invokeinterface add : (Ljava/lang/Object;)Z
    //   39: pop
    //   40: aload_2
    //   41: monitorexit
    //   42: goto -> 2
    //   45: astore_1
    //   46: aload_2
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    //   50: astore_1
    //   51: aload_0
    //   52: monitorexit
    //   53: aload_1
    //   54: athrow
    //   55: aload_0
    //   56: iconst_1
    //   57: putfield k : Z
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    // Exception table:
    //   from	to	target	type
    //   2	16	50	finally
    //   16	42	45	finally
    //   46	48	45	finally
    //   48	50	50	finally
    //   55	60	50	finally
  }
  
  final void a(String paramString) {
    this.D = paramString;
  }
  
  final void a(List paramList) {
    this.K = paramList;
  }
  
  final void a(Map paramMap) {
    this.C = paramMap;
  }
  
  final void a(boolean paramBoolean) {
    this.M = paramBoolean;
  }
  
  final boolean a(Context paramContext, String paramString, ViewGroup paramViewGroup) {
    a(paramContext, paramString);
    if (g(paramString) instanceof ba) {
      if (this.G != null)
        this.G.spaceDidFailToReceiveAd(paramString.toString()); 
      return false;
    } 
    if (this.G != null)
      this.G.spaceDidReceiveAd(paramString.toString()); 
    b(paramContext, paramString, paramViewGroup);
    return true;
  }
  
  final boolean a(Context paramContext, String paramString, FlurryAdSize paramFlurryAdSize, long paramLong) {
    boolean bool1;
    boolean bool2 = false;
    if (f(paramString))
      return true; 
    if (!FlurryAgent.a()) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.E.postDelayed(new h(this, paramContext, paramString, paramFlurryAdSize), bool1);
    long l = System.currentTimeMillis();
    while (true) {
      boolean bool = bool2;
      if (ac.a(l + paramLong)) {
        if (f(paramString))
          return true; 
        continue;
      } 
      return bool;
    } 
  }
  
  final boolean a(Context paramContext, String paramString, FlurryAdSize paramFlurryAdSize, ViewGroup paramViewGroup, long paramLong) {
    if (!f(paramString)) {
      long l;
      if (f(paramContext) && !e(paramContext)) {
        boolean bool1;
        boolean bool2;
        if (paramLong == 0L) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        if (paramLong == 0L) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        (new n(this, paramContext, paramString, paramFlurryAdSize, paramViewGroup, bool1, bool2)).execute((Object[])new Void[0]);
        if (paramLong > 0L) {
          l = System.currentTimeMillis();
          while (ac.a(l + paramLong)) {
            if (f(paramString))
              return a(paramContext, paramString, paramViewGroup); 
          } 
        } 
      } else if (!f(paramContext) && this.G != null) {
        this.G.spaceDidFailToReceiveAd(paramString.toString());
      } 
      boolean bool = false;
      while (ac.a(l + paramLong)) {
        if (f(paramString))
          return a(paramContext, paramString, paramViewGroup); 
      } 
    } 
    return !e(paramContext) ? a(paramContext, paramString, paramViewGroup) : false;
  }
  
  final long b() {
    return this.z;
  }
  
  final void b(Context paramContext) {
    bm.c(a, "Initializing ads -- requesting ads with precaching enabled on the server");
    char c = Character.MIN_VALUE;
    if (!FlurryAgent.a())
      c = 'Ǵ'; 
    this.E.postDelayed(new f(this, paramContext), c);
  }
  
  final void b(Context paramContext, String paramString) {
    if (paramString.startsWith("market://details?id=")) {
      String str = paramString.substring("market://details?id=".length());
      if (this.q) {
        try {
          ac.a(paramContext, paramString);
        } catch (Exception exception) {
          bm.b(a, "Cannot launch Google Play url " + paramString, exception);
        } 
        return;
      } 
      ac.a((Context)exception, "https://market.android.com/details?id=" + str);
      return;
    } 
    bm.d(a, "Unexpected Google Play url scheme: " + paramString);
  }
  
  final void b(Context paramContext, String paramString, ViewGroup paramViewGroup) {
    aj aj = g(paramString);
    if (aj != null) {
      if (this.G != null)
        if (aj instanceof bh) {
          this.G.shouldDisplayAd(paramString.toString(), FlurryAdType.WEB_BANNER);
        } else {
          this.G.shouldDisplayAd(paramString.toString(), FlurryAdType.WEB_TAKEOVER);
          this.G.onAdOpened(paramString.toString());
        }  
      aj.a(paramContext, paramViewGroup);
    } 
  }
  
  final void b(bl parambl) {
    this.w = parambl;
  }
  
  final void b(String paramString) {
    if (o()) {
      x x = this.d.a(paramString);
      if (x == null) {
        h(paramString);
        return;
      } 
      r r = x.e();
      if (r != null)
        r.stop(); 
      ViewGroup viewGroup = x.c();
      if (viewGroup != null) {
        x.removeAllViews();
        viewGroup.removeView((View)x);
      } 
      h(paramString);
      this.d.b(paramString);
    } 
  }
  
  final void b(List paramList) {
    this.c.a(paramList);
  }
  
  final void b(Map paramMap) {
    if (paramMap != null)
      this.N = paramMap; 
  }
  
  final String c() {
    return this.y;
  }
  
  final void c(String paramString) {
    this.r = paramString;
  }
  
  final void c(Map paramMap) {
    if (paramMap != null && paramMap.size() > 0)
      for (Map.Entry entry : paramMap.entrySet())
        this.v.put(entry.getKey(), entry.getValue());  
  }
  
  final boolean c(Context paramContext, String paramString) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #4
    //   3: iconst_0
    //   4: istore_3
    //   5: iload_3
    //   6: iconst_5
    //   7: if_icmpge -> 112
    //   10: aload_1
    //   11: invokestatic f : (Landroid/content/Context;)Z
    //   14: iconst_1
    //   15: if_icmpne -> 89
    //   18: aload_1
    //   19: invokestatic e : (Landroid/content/Context;)Z
    //   22: ifne -> 89
    //   25: aload_0
    //   26: getfield h : Lcom/flurry/android/be;
    //   29: aload_2
    //   30: sipush #10000
    //   33: sipush #15000
    //   36: iconst_1
    //   37: invokestatic a : (Lcom/flurry/android/be;Ljava/lang/String;IIZ)Lorg/apache/http/HttpResponse;
    //   40: astore #5
    //   42: aload #5
    //   44: ifnull -> 95
    //   47: aload #5
    //   49: invokeinterface getStatusLine : ()Lorg/apache/http/StatusLine;
    //   54: invokeinterface getStatusCode : ()I
    //   59: sipush #200
    //   62: if_icmpne -> 95
    //   65: new java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial <init> : ()V
    //   72: ldc_w 'URL hit succeeded for: '
    //   75: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: aload_2
    //   79: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: invokevirtual toString : ()Ljava/lang/String;
    //   85: pop
    //   86: iload #4
    //   88: ireturn
    //   89: ldc2_w 100
    //   92: invokestatic sleep : (J)V
    //   95: iinc #3, 1
    //   98: goto -> 5
    //   101: astore #5
    //   103: aload #5
    //   105: invokevirtual getMessage : ()Ljava/lang/String;
    //   108: pop
    //   109: goto -> 95
    //   112: iconst_0
    //   113: istore #4
    //   115: goto -> 86
    // Exception table:
    //   from	to	target	type
    //   89	95	101	java/lang/InterruptedException
  }
  
  final long d() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic elapsedRealtime : ()J
    //   5: aload_0
    //   6: getfield A : J
    //   9: lsub
    //   10: lstore_1
    //   11: lload_1
    //   12: aload_0
    //   13: getfield B : J
    //   16: lcmp
    //   17: ifle -> 34
    //   20: aload_0
    //   21: lload_1
    //   22: putfield B : J
    //   25: aload_0
    //   26: getfield B : J
    //   29: lstore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: lload_1
    //   33: lreturn
    //   34: aload_0
    //   35: getfield B : J
    //   38: lconst_1
    //   39: ladd
    //   40: lstore_1
    //   41: aload_0
    //   42: lload_1
    //   43: putfield B : J
    //   46: goto -> 20
    //   49: astore_3
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_3
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	49	finally
    //   20	30	49	finally
    //   34	46	49	finally
  }
  
  final void d(String paramString) {
    this.s = paramString;
  }
  
  final String e() {
    return this.D;
  }
  
  final void e(String paramString) {
    byte[] arrayOfByte = p();
    if (arrayOfByte != null)
      a(arrayOfByte, paramString); 
  }
  
  final void f() {
    this.N = null;
  }
  
  final boolean g() {
    return this.H;
  }
  
  final void h() {
    this.v.clear();
  }
  
  final Map i() {
    return this.v;
  }
  
  final void j() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/flurry/android/i
    //   5: astore_1
    //   6: aload_1
    //   7: aload_0
    //   8: invokespecial <init> : (Lcom/flurry/android/bo;)V
    //   11: aload_0
    //   12: getfield E : Landroid/os/Handler;
    //   15: aload_1
    //   16: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   19: pop
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	23	finally
  }
  
  final void k() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield l : Ljava/io/File;
    //   6: invokestatic a : (Ljava/io/File;)Z
    //   9: istore_1
    //   10: iload_1
    //   11: ifne -> 21
    //   14: aconst_null
    //   15: invokestatic a : (Ljava/io/Closeable;)V
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: new java/io/FileOutputStream
    //   24: astore_2
    //   25: aload_2
    //   26: aload_0
    //   27: getfield l : Ljava/io/File;
    //   30: invokespecial <init> : (Ljava/io/File;)V
    //   33: new java/io/DataOutputStream
    //   36: astore_3
    //   37: aload_3
    //   38: aload_2
    //   39: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   42: aload_3
    //   43: astore_2
    //   44: aload_3
    //   45: ldc_w 46586
    //   48: invokevirtual writeShort : (I)V
    //   51: aload_3
    //   52: astore_2
    //   53: aload_0
    //   54: getfield I : Ljava/util/List;
    //   57: astore #5
    //   59: aload_3
    //   60: astore_2
    //   61: aload #5
    //   63: monitorenter
    //   64: aload_0
    //   65: aload_0
    //   66: getfield I : Ljava/util/List;
    //   69: aload_3
    //   70: invokespecial a : (Ljava/util/List;Ljava/io/DataOutputStream;)V
    //   73: aload #5
    //   75: monitorexit
    //   76: aload_3
    //   77: astore_2
    //   78: aload_3
    //   79: iconst_0
    //   80: invokevirtual writeShort : (I)V
    //   83: aload_3
    //   84: invokestatic a : (Ljava/io/Closeable;)V
    //   87: goto -> 18
    //   90: astore_2
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_2
    //   94: athrow
    //   95: astore #4
    //   97: aload #5
    //   99: monitorexit
    //   100: aload_3
    //   101: astore_2
    //   102: aload #4
    //   104: athrow
    //   105: astore #4
    //   107: aload_3
    //   108: astore_2
    //   109: getstatic com/flurry/android/bo.a : Ljava/lang/String;
    //   112: ldc_w ''
    //   115: aload #4
    //   117: invokestatic b : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   120: pop
    //   121: aload_3
    //   122: invokestatic a : (Ljava/io/Closeable;)V
    //   125: goto -> 18
    //   128: astore_3
    //   129: aconst_null
    //   130: astore_2
    //   131: aload_2
    //   132: invokestatic a : (Ljava/io/Closeable;)V
    //   135: aload_3
    //   136: athrow
    //   137: astore_3
    //   138: goto -> 131
    //   141: astore #4
    //   143: aconst_null
    //   144: astore_3
    //   145: goto -> 107
    // Exception table:
    //   from	to	target	type
    //   2	10	141	java/lang/Throwable
    //   2	10	128	finally
    //   14	18	90	finally
    //   21	42	141	java/lang/Throwable
    //   21	42	128	finally
    //   44	51	105	java/lang/Throwable
    //   44	51	137	finally
    //   53	59	105	java/lang/Throwable
    //   53	59	137	finally
    //   61	64	105	java/lang/Throwable
    //   61	64	137	finally
    //   64	76	95	finally
    //   78	83	105	java/lang/Throwable
    //   78	83	137	finally
    //   83	87	90	finally
    //   97	100	95	finally
    //   102	105	105	java/lang/Throwable
    //   102	105	137	finally
    //   109	121	137	finally
    //   121	125	90	finally
    //   131	137	90	finally
  }
  
  final void l() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/flurry/android/k
    //   5: astore_1
    //   6: aload_1
    //   7: aload_0
    //   8: invokespecial <init> : (Lcom/flurry/android/bo;)V
    //   11: aload_1
    //   12: iconst_0
    //   13: anewarray java/lang/Void
    //   16: invokevirtual execute : ([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   19: pop
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	23	finally
  }
  
  final bl m() {
    return this.w;
  }
  
  final AdUnit n() {
    return this.x;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\bo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */