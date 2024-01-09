package com.google.ads.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import com.google.ads.AdActivity;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.nio.CharBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AdUtil {
  public static final int a = a(Build.VERSION.SDK);
  
  private static Boolean b = null;
  
  private static String c = null;
  
  private static String d;
  
  private static String e = null;
  
  private static AudioManager f;
  
  private static boolean g = true;
  
  private static boolean h = false;
  
  private static String i = null;
  
  public static int a() {
    return (a >= 9) ? 6 : 0;
  }
  
  public static int a(Context paramContext, DisplayMetrics paramDisplayMetrics) {
    return (a >= 4) ? e.a(paramContext, paramDisplayMetrics) : paramDisplayMetrics.heightPixels;
  }
  
  public static int a(String paramString) {
    byte b;
    try {
      b = Integer.parseInt(paramString);
    } catch (NumberFormatException numberFormatException) {
      b.e("The Android SDK version couldn't be parsed to an int: " + Build.VERSION.SDK);
      b.e("Defaulting to Android SDK version 3.");
      b = 3;
    } 
    return b;
  }
  
  public static DisplayMetrics a(Activity paramActivity) {
    if (paramActivity.getWindowManager() == null)
      return null; 
    DisplayMetrics displayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    return displayMetrics;
  }
  
  public static String a(Context paramContext) {
    if (c == null) {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if (str == null || c()) {
        str = b("emulator");
      } else {
        str = b(str);
      } 
      if (str == null)
        return null; 
      c = str.toUpperCase(Locale.US);
    } 
    return c;
  }
  
  public static String a(Location paramLocation) {
    if (paramLocation == null)
      return null; 
    null = c(b(paramLocation));
    return "e1+" + null;
  }
  
  public static String a(Readable paramReadable) {
    StringBuilder stringBuilder = new StringBuilder();
    CharBuffer charBuffer = CharBuffer.allocate(2048);
    while (true) {
      int i = paramReadable.read(charBuffer);
      if (i != -1) {
        charBuffer.flip();
        stringBuilder.append(charBuffer, 0, i);
        continue;
      } 
      return stringBuilder.toString();
    } 
  }
  
  public static String a(Map paramMap) {
    JSONException jSONException2 = null;
    try {
      String str = b(paramMap).toString();
    } catch (JSONException jSONException1) {
      b.d("JsonException in serialization: ", (Throwable)jSONException1);
      jSONException1 = jSONException2;
    } 
    return (String)jSONException1;
  }
  
  public static JSONArray a(Set paramSet) {
    JSONArray jSONArray = new JSONArray();
    if (paramSet != null && !paramSet.isEmpty()) {
      Iterator<Object> iterator = paramSet.iterator();
      while (true) {
        if (iterator.hasNext()) {
          Map map = (Map)iterator.next();
          if (map instanceof String || map instanceof Integer || map instanceof Double || map instanceof Long || map instanceof Float) {
            jSONArray.put(map);
            continue;
          } 
          if (map instanceof Map) {
            try {
              jSONArray.put(b(map));
            } catch (ClassCastException null) {
              b.d("Unknown map type in json serialization: ", classCastException);
            } 
            continue;
          } 
          if (classCastException instanceof Set) {
            try {
              jSONArray.put(a((Set)classCastException));
            } catch (ClassCastException classCastException) {
              b.d("Unknown map type in json serialization: ", classCastException);
            } 
            continue;
          } 
          b.e("Unknown value in json serialization: " + classCastException);
          continue;
        } 
        return jSONArray;
      } 
    } 
    return jSONArray;
  }
  
  public static void a(WebView paramWebView) {
    String str = i(paramWebView.getContext().getApplicationContext());
    paramWebView.getSettings().setUserAgentString(str);
  }
  
  public static void a(HttpURLConnection paramHttpURLConnection, Context paramContext) {
    paramHttpURLConnection.setRequestProperty("User-Agent", i(paramContext));
  }
  
  public static void a(boolean paramBoolean) {
    g = paramBoolean;
  }
  
  public static boolean a(int paramInt1, int paramInt2, String paramString) {
    if ((paramInt1 & paramInt2) == 0) {
      b.b("The android:configChanges value of the com.google.ads.AdActivity must include " + paramString + ".");
      return false;
    } 
    return true;
  }
  
  public static boolean a(Intent paramIntent, Context paramContext) {
    return (paramContext.getPackageManager().resolveActivity(paramIntent, 65536) != null);
  }
  
  public static boolean a(Uri paramUri) {
    boolean bool = false;
    if (paramUri != null) {
      String str = paramUri.getScheme();
      if ("http".equalsIgnoreCase(str) || "https".equalsIgnoreCase(str))
        bool = true; 
    } 
    return bool;
  }
  
  static boolean a(d paramd) {
    d d1 = paramd;
    if (paramd == null)
      d1 = d.d; 
    return d1.equals(d.e);
  }
  
  public static int b() {
    return (a >= 9) ? 7 : 1;
  }
  
  public static int b(Context paramContext, DisplayMetrics paramDisplayMetrics) {
    return (a >= 4) ? e.b(paramContext, paramDisplayMetrics) : paramDisplayMetrics.widthPixels;
  }
  
  private static String b(Location paramLocation) {
    return String.format(Locale.US, "role: 6 producer: 24 historical_role: 1 historical_producer: 12 timestamp: %d latlng < latitude_e7: %d longitude_e7: %d> radius: %d", new Object[] { Long.valueOf(paramLocation.getTime() * 1000L), Long.valueOf((long)(paramLocation.getLatitude() * 1.0E7D)), Long.valueOf((long)(paramLocation.getLongitude() * 1.0E7D)), Long.valueOf((long)(paramLocation.getAccuracy() * 1000.0F)) });
  }
  
  public static String b(String paramString) {
    String str;
    MessageDigest messageDigest2 = null;
    MessageDigest messageDigest1 = messageDigest2;
    if (paramString != null) {
      messageDigest1 = messageDigest2;
      if (paramString.length() > 0)
        try {
          messageDigest1 = MessageDigest.getInstance("MD5");
          messageDigest1.update(paramString.getBytes(), 0, paramString.length());
          Locale locale = Locale.US;
          BigInteger bigInteger = new BigInteger();
          this(1, messageDigest1.digest());
          str = String.format(locale, "%032X", new Object[] { bigInteger });
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
          str = paramString.substring(0, 32);
        }  
    } 
    return str;
  }
  
  public static HashMap b(Uri paramUri) {
    Uri uri = null;
    if (paramUri == null)
      return (HashMap)uri; 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    str = paramUri.getEncodedQuery();
    if (str != null)
      for (String str : str.split("&")) {
        int i = str.indexOf("=");
        if (i < 0) {
          hashMap.put(Uri.decode(str), null);
        } else {
          hashMap.put(Uri.decode(str.substring(0, i)), Uri.decode(str.substring(i + 1, str.length())));
        } 
      }  
    return hashMap;
  }
  
  public static JSONObject b(Map paramMap) {
    JSONObject jSONObject = new JSONObject();
    if (paramMap != null && !paramMap.isEmpty()) {
      Iterator<String> iterator = paramMap.keySet().iterator();
      while (true) {
        if (iterator.hasNext()) {
          String str = iterator.next();
          object = paramMap.get(str);
          if (object instanceof String || object instanceof Integer || object instanceof Double || object instanceof Long || object instanceof Float) {
            jSONObject.put(str, object);
            continue;
          } 
          if (object instanceof Map) {
            try {
              jSONObject.put(str, b((Map)object));
            } catch (ClassCastException null) {
              b.d("Unknown map type in json serialization: ", (Throwable)object);
            } 
            continue;
          } 
          if (object instanceof Set) {
            try {
              jSONObject.put(str, a((Set)object));
            } catch (ClassCastException object) {
              b.d("Unknown map type in json serialization: ", (Throwable)object);
            } 
            continue;
          } 
          b.e("Unknown value in json serialization: " + object);
          continue;
        } 
        return jSONObject;
      } 
    } 
    return jSONObject;
  }
  
  public static boolean b(Context paramContext) {
    null = false;
    PackageManager packageManager = paramContext.getPackageManager();
    String str = paramContext.getPackageName();
    if (packageManager.checkPermission("android.permission.INTERNET", str) == -1) {
      b.b("INTERNET permissions must be enabled in AndroidManifest.xml.");
      return null;
    } 
    if (packageManager.checkPermission("android.permission.ACCESS_NETWORK_STATE", str) == -1) {
      b.b("ACCESS_NETWORK_STATE permissions must be enabled in AndroidManifest.xml.");
      return null;
    } 
    return true;
  }
  
  private static String c(String paramString) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec secretKeySpec = new SecretKeySpec();
      this(new byte[] { 
            10, 55, -112, -47, -6, 7, 11, 75, -7, -121, 
            121, 69, 80, -61, 15, 5 }, "AES");
      cipher.init(1, secretKeySpec);
      byte[] arrayOfByte2 = cipher.getIV();
      byte[] arrayOfByte3 = cipher.doFinal(paramString.getBytes());
      byte[] arrayOfByte1 = new byte[arrayOfByte2.length + arrayOfByte3.length];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, arrayOfByte2.length);
      System.arraycopy(arrayOfByte3, 0, arrayOfByte1, arrayOfByte2.length, arrayOfByte3.length);
      String str = c.b(arrayOfByte1, 11);
    } catch (GeneralSecurityException generalSecurityException) {
      generalSecurityException = null;
    } 
    return (String)generalSecurityException;
  }
  
  public static boolean c() {
    return a((d)null);
  }
  
  public static boolean c(Context paramContext) {
    if (b != null)
      return b.booleanValue(); 
    ResolveInfo resolveInfo = paramContext.getPackageManager().resolveActivity(new Intent(paramContext, AdActivity.class), 65536);
    b = Boolean.valueOf(true);
    if (resolveInfo == null || resolveInfo.activityInfo == null) {
      b.b("Could not find com.google.ads.AdActivity, please make sure it is registered in AndroidManifest.xml.");
      b = Boolean.valueOf(false);
    } else {
      if (!a(resolveInfo.activityInfo.configChanges, 16, "keyboard"))
        b = Boolean.valueOf(false); 
      if (!a(resolveInfo.activityInfo.configChanges, 32, "keyboardHidden"))
        b = Boolean.valueOf(false); 
      if (!a(resolveInfo.activityInfo.configChanges, 128, "orientation"))
        b = Boolean.valueOf(false); 
      if (!a(resolveInfo.activityInfo.configChanges, 256, "screenLayout"))
        b = Boolean.valueOf(false); 
      if (!a(resolveInfo.activityInfo.configChanges, 512, "uiMode"))
        b = Boolean.valueOf(false); 
      if (!a(resolveInfo.activityInfo.configChanges, 1024, "screenSize"))
        b = Boolean.valueOf(false); 
      if (!a(resolveInfo.activityInfo.configChanges, 2048, "smallestScreenSize"))
        b = Boolean.valueOf(false); 
    } 
    return b.booleanValue();
  }
  
  public static String d(Context paramContext) {
    NetworkInfo networkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (networkInfo == null)
      return null; 
    switch (networkInfo.getType()) {
      default:
        return "unknown";
      case 0:
        return "ed";
      case 1:
        break;
    } 
    return "wi";
  }
  
  public static boolean d() {
    return g;
  }
  
  public static String e(Context paramContext) {
    if (d == null) {
      StringBuilder stringBuilder = new StringBuilder();
      PackageManager packageManager = paramContext.getPackageManager();
      List list2 = packageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=donuts")), 65536);
      if (list2 == null || list2.size() == 0)
        stringBuilder.append("m"); 
      list2 = packageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pname:com.google")), 65536);
      if (list2 == null || list2.size() == 0) {
        if (stringBuilder.length() > 0)
          stringBuilder.append(","); 
        stringBuilder.append("a");
      } 
      List list1 = packageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("tel://6509313940")), 65536);
      if (list1 == null || list1.size() == 0) {
        if (stringBuilder.length() > 0)
          stringBuilder.append(","); 
        stringBuilder.append("t");
      } 
      d = stringBuilder.toString();
    } 
    return d;
  }
  
  public static String f(Context paramContext) {
    Intent intent1;
    Intent intent2 = null;
    if (e != null)
      return e; 
    try {
      PackageManager packageManager = paramContext.getPackageManager();
      intent1 = new Intent();
      this("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.ads"));
      ResolveInfo resolveInfo = packageManager.resolveActivity(intent1, 65536);
      intent1 = intent2;
      if (resolveInfo != null) {
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        intent1 = intent2;
        if (activityInfo != null) {
          PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
          intent1 = intent2;
          if (packageInfo != null) {
            StringBuilder stringBuilder = new StringBuilder();
            this();
            e = stringBuilder.append(packageInfo.versionCode).append(".").append(activityInfo.packageName).toString();
            String str = e;
          } 
        } 
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      intent1 = intent2;
    } 
    return (String)intent1;
  }
  
  public static AdUtil$a g(Context paramContext) {
    if (f == null)
      f = (AudioManager)paramContext.getSystemService("audio"); 
    null = AdUtil$a.f;
    int i = f.getMode();
    if (c())
      return AdUtil$a.e; 
    if (f.isMusicActive() || f.isSpeakerphoneOn() || i == 2 || i == 1)
      return AdUtil$a.d; 
    i = f.getRingerMode();
    return (i == 0 || i == 1) ? AdUtil$a.d : AdUtil$a.b;
  }
  
  public static void h(Context paramContext) {
    if (!h) {
      IntentFilter intentFilter = new IntentFilter();
      intentFilter.addAction("android.intent.action.USER_PRESENT");
      intentFilter.addAction("android.intent.action.SCREEN_OFF");
      paramContext.registerReceiver(new AdUtil$UserActivityReceiver(), intentFilter);
      h = true;
    } 
  }
  
  public static String i(Context paramContext) {
    // Byte code:
    //   0: getstatic com/google/ads/util/AdUtil.i : Ljava/lang/String;
    //   3: ifnonnull -> 289
    //   6: new android/webkit/WebView
    //   9: dup
    //   10: aload_0
    //   11: invokespecial <init> : (Landroid/content/Context;)V
    //   14: invokevirtual getSettings : ()Landroid/webkit/WebSettings;
    //   17: invokevirtual getUserAgentString : ()Ljava/lang/String;
    //   20: astore_1
    //   21: aload_1
    //   22: ifnull -> 44
    //   25: aload_1
    //   26: invokevirtual length : ()I
    //   29: ifeq -> 44
    //   32: aload_1
    //   33: astore_0
    //   34: aload_1
    //   35: ldc_w 'Java0'
    //   38: invokevirtual equals : (Ljava/lang/Object;)Z
    //   41: ifeq -> 248
    //   44: ldc_w 'os.name'
    //   47: ldc_w 'Linux'
    //   50: invokestatic getProperty : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   53: astore_3
    //   54: new java/lang/StringBuilder
    //   57: dup
    //   58: invokespecial <init> : ()V
    //   61: ldc_w 'Android '
    //   64: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: getstatic android/os/Build$VERSION.RELEASE : Ljava/lang/String;
    //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: invokevirtual toString : ()Ljava/lang/String;
    //   76: astore_2
    //   77: invokestatic getDefault : ()Ljava/util/Locale;
    //   80: astore #4
    //   82: aload #4
    //   84: invokevirtual getLanguage : ()Ljava/lang/String;
    //   87: getstatic java/util/Locale.US : Ljava/util/Locale;
    //   90: invokevirtual toLowerCase : (Ljava/util/Locale;)Ljava/lang/String;
    //   93: astore_1
    //   94: aload_1
    //   95: astore_0
    //   96: aload_1
    //   97: invokevirtual length : ()I
    //   100: ifne -> 107
    //   103: ldc_w 'en'
    //   106: astore_0
    //   107: aload #4
    //   109: invokevirtual getCountry : ()Ljava/lang/String;
    //   112: getstatic java/util/Locale.US : Ljava/util/Locale;
    //   115: invokevirtual toLowerCase : (Ljava/util/Locale;)Ljava/lang/String;
    //   118: astore #4
    //   120: aload_0
    //   121: astore_1
    //   122: aload #4
    //   124: invokevirtual length : ()I
    //   127: ifle -> 156
    //   130: new java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial <init> : ()V
    //   137: aload_0
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: ldc_w '-'
    //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: aload #4
    //   149: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: invokevirtual toString : ()Ljava/lang/String;
    //   155: astore_1
    //   156: new java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial <init> : ()V
    //   163: getstatic android/os/Build.MODEL : Ljava/lang/String;
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: ldc_w ' Build/'
    //   172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: getstatic android/os/Build.ID : Ljava/lang/String;
    //   178: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: invokevirtual toString : ()Ljava/lang/String;
    //   184: astore_0
    //   185: new java/lang/StringBuilder
    //   188: dup
    //   189: invokespecial <init> : ()V
    //   192: ldc_w 'Mozilla/5.0 ('
    //   195: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: aload_3
    //   199: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: ldc_w '; U; '
    //   205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload_2
    //   209: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: ldc_w '; '
    //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_1
    //   219: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: ldc_w '; '
    //   225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload_0
    //   229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: ldc_w ') AppleWebKit/0.0 (KHTML, like '
    //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: ldc_w 'Gecko) Version/0.0 Mobile Safari/0.0'
    //   241: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: invokevirtual toString : ()Ljava/lang/String;
    //   247: astore_0
    //   248: new java/lang/StringBuilder
    //   251: dup
    //   252: invokespecial <init> : ()V
    //   255: aload_0
    //   256: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: ldc_w ' (Mobile; '
    //   262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: ldc_w 'afma-sdk-a-v'
    //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: ldc_w '6.2.1'
    //   274: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: ldc_w ')'
    //   280: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: invokevirtual toString : ()Ljava/lang/String;
    //   286: putstatic com/google/ads/util/AdUtil.i : Ljava/lang/String;
    //   289: getstatic com/google/ads/util/AdUtil.i : Ljava/lang/String;
    //   292: areturn
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ad\\util\AdUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */