package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.Signature;
import android.os.Looper;
import com.chartboost.sdk.Chartboost;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONObject;

public class d {
  private static String a = null;
  
  private static final X500Principal b = new X500Principal("CN=Android Debug,O=Android,C=US");
  
  public static int a(int paramInt, Context paramContext) {
    return Math.round(paramInt * b(paramContext));
  }
  
  public static SharedPreferences a() {
    return Chartboost.sharedChartboost().getContext().getSharedPreferences("cbPrefs", 0);
  }
  
  public static String a(Map paramMap) {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull -> 9
    //   4: ldc ''
    //   6: astore_0
    //   7: aload_0
    //   8: areturn
    //   9: new java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: astore_3
    //   17: aload_0
    //   18: invokeinterface keySet : ()Ljava/util/Set;
    //   23: invokeinterface isEmpty : ()Z
    //   28: ifne -> 38
    //   31: aload_3
    //   32: ldc '?'
    //   34: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: pop
    //   38: aload_0
    //   39: invokeinterface keySet : ()Ljava/util/Set;
    //   44: invokeinterface iterator : ()Ljava/util/Iterator;
    //   49: astore_2
    //   50: aload_2
    //   51: invokeinterface hasNext : ()Z
    //   56: ifne -> 67
    //   59: aload_3
    //   60: invokevirtual toString : ()Ljava/lang/String;
    //   63: astore_0
    //   64: goto -> 7
    //   67: aload_2
    //   68: invokeinterface next : ()Ljava/lang/Object;
    //   73: checkcast java/lang/String
    //   76: astore_1
    //   77: aload_3
    //   78: invokevirtual length : ()I
    //   81: ifle -> 91
    //   84: aload_3
    //   85: ldc '&'
    //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: aload_0
    //   92: aload_1
    //   93: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   98: checkcast java/lang/String
    //   101: astore #4
    //   103: aload_1
    //   104: ifnull -> 161
    //   107: aload_1
    //   108: ldc 'UTF-8'
    //   110: invokestatic encode : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   113: astore_1
    //   114: aload_3
    //   115: aload_1
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: aload_3
    //   121: ldc '='
    //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: aload #4
    //   129: ifnull -> 167
    //   132: aload #4
    //   134: ldc 'UTF-8'
    //   136: invokestatic encode : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   139: astore_1
    //   140: aload_3
    //   141: aload_1
    //   142: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: goto -> 50
    //   149: astore_0
    //   150: new java/lang/RuntimeException
    //   153: dup
    //   154: ldc 'This method requires UTF-8 encoding support'
    //   156: aload_0
    //   157: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   160: athrow
    //   161: ldc ''
    //   163: astore_1
    //   164: goto -> 114
    //   167: ldc ''
    //   169: astore_1
    //   170: goto -> 140
    // Exception table:
    //   from	to	target	type
    //   107	114	149	java/io/UnsupportedEncodingException
    //   114	127	149	java/io/UnsupportedEncodingException
    //   132	140	149	java/io/UnsupportedEncodingException
    //   140	146	149	java/io/UnsupportedEncodingException
  }
  
  public static List a(JSONArray paramJSONArray) {
    if (paramJSONArray == null)
      return null; 
    ArrayList<String> arrayList = new ArrayList();
    for (byte b = 0;; b++) {
      if (b >= paramJSONArray.length())
        return arrayList; 
      try {
        arrayList.add(paramJSONArray.getString(b));
      } catch (Exception exception) {}
    } 
  }
  
  public static Map a(JSONObject paramJSONObject) {
    if (paramJSONObject == null)
      return null; 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    Iterator<String> iterator = paramJSONObject.keys();
    while (true) {
      if (!iterator.hasNext())
        return hashMap; 
      try {
        String str = iterator.next();
        hashMap.put(str, paramJSONObject.getString(str));
      } catch (Exception exception) {}
    } 
  }
  
  public static JSONArray a(List paramList) {
    if (paramList == null)
      return null; 
    JSONArray jSONArray = new JSONArray();
    for (byte b = 0;; b++) {
      if (b >= paramList.size())
        return jSONArray; 
      try {
        jSONArray.put(paramList.get(b));
      } catch (Exception exception) {}
    } 
  }
  
  public static boolean a(Context paramContext) {
    boolean bool2;
    try {
      Signature[] arrayOfSignature = (paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64)).signatures;
      int i = 0;
      int j = 0;
      try {
        while (true) {
          int m = arrayOfSignature.length;
          if (i < m) {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
            this(arrayOfSignature[i].toByteArray());
            int n = ((X509Certificate)certificateFactory.generateCertificate(byteArrayInputStream)).getSubjectX500Principal().equals(b);
            j = n;
            if (!n) {
              i++;
              j = n;
              continue;
            } 
          } 
          if (((paramContext.getApplicationInfo()).flags & 0x2) != 0) {
            i = 1;
            return j | i;
          } 
          int k = 0;
          return j | k;
        } 
      } catch (Exception exception) {}
    } catch (Exception exception) {
      bool2 = false;
    } 
    if (((paramContext.getApplicationInfo()).flags & 0x2) != 0) {
      boolean bool = true;
      return bool2 | bool;
    } 
    boolean bool1 = false;
    return bool2 | bool1;
  }
  
  public static float b(int paramInt, Context paramContext) {
    return paramInt * b(paramContext);
  }
  
  public static float b(Context paramContext) {
    return (paramContext.getResources().getDisplayMetrics()).density;
  }
  
  public static String b() {
    if (c())
      return null; 
    if (a != null)
      return a; 
    a = c.a();
    return a;
  }
  
  public static JSONObject b(Map paramMap) {
    if (paramMap == null)
      return null; 
    JSONObject jSONObject = new JSONObject();
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    while (true) {
      if (!iterator.hasNext())
        return jSONObject; 
      Map.Entry entry = iterator.next();
      String str = (String)entry.getKey();
      entry = (Map.Entry)entry.getValue();
      try {
        jSONObject.put(str, entry);
      } catch (Exception exception) {}
    } 
  }
  
  public static CBOrientation c(Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 'window'
    //   4: invokevirtual getSystemService : (Ljava/lang/String;)Ljava/lang/Object;
    //   7: checkcast android/view/WindowManager
    //   10: invokeinterface getDefaultDisplay : ()Landroid/view/Display;
    //   15: astore #4
    //   17: aload_0
    //   18: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   21: invokevirtual getConfiguration : ()Landroid/content/res/Configuration;
    //   24: getfield orientation : I
    //   27: istore_2
    //   28: aload #4
    //   30: invokevirtual getRotation : ()I
    //   33: istore_3
    //   34: aload #4
    //   36: invokevirtual getWidth : ()I
    //   39: aload #4
    //   41: invokevirtual getHeight : ()I
    //   44: if_icmpne -> 106
    //   47: iconst_3
    //   48: istore_1
    //   49: iload_1
    //   50: iconst_1
    //   51: if_icmpne -> 129
    //   54: iconst_1
    //   55: istore_1
    //   56: iload_1
    //   57: istore_2
    //   58: iload_3
    //   59: ifeq -> 69
    //   62: iload_3
    //   63: iconst_2
    //   64: if_icmpne -> 164
    //   67: iload_1
    //   68: istore_2
    //   69: iload_2
    //   70: ifeq -> 199
    //   73: iload_3
    //   74: tableswitch default -> 100, 1 -> 178, 2 -> 185, 3 -> 192
    //   100: getstatic com/chartboost/sdk/Libraries/CBOrientation.PORTRAIT : Lcom/chartboost/sdk/Libraries/CBOrientation;
    //   103: astore_0
    //   104: aload_0
    //   105: areturn
    //   106: aload #4
    //   108: invokevirtual getWidth : ()I
    //   111: aload #4
    //   113: invokevirtual getHeight : ()I
    //   116: if_icmpge -> 124
    //   119: iconst_1
    //   120: istore_1
    //   121: goto -> 49
    //   124: iconst_2
    //   125: istore_1
    //   126: goto -> 49
    //   129: iload_1
    //   130: iconst_2
    //   131: if_icmpne -> 139
    //   134: iconst_0
    //   135: istore_1
    //   136: goto -> 56
    //   139: iload_1
    //   140: iconst_3
    //   141: if_icmpne -> 256
    //   144: iload_2
    //   145: iconst_1
    //   146: if_icmpne -> 154
    //   149: iconst_1
    //   150: istore_1
    //   151: goto -> 56
    //   154: iload_2
    //   155: iconst_2
    //   156: if_icmpne -> 256
    //   159: iconst_0
    //   160: istore_1
    //   161: goto -> 56
    //   164: iload_1
    //   165: ifeq -> 173
    //   168: iconst_0
    //   169: istore_2
    //   170: goto -> 69
    //   173: iconst_1
    //   174: istore_2
    //   175: goto -> 69
    //   178: getstatic com/chartboost/sdk/Libraries/CBOrientation.LANDSCAPE_LEFT : Lcom/chartboost/sdk/Libraries/CBOrientation;
    //   181: astore_0
    //   182: goto -> 104
    //   185: getstatic com/chartboost/sdk/Libraries/CBOrientation.PORTRAIT_REVERSE : Lcom/chartboost/sdk/Libraries/CBOrientation;
    //   188: astore_0
    //   189: goto -> 104
    //   192: getstatic com/chartboost/sdk/Libraries/CBOrientation.LANDSCAPE_RIGHT : Lcom/chartboost/sdk/Libraries/CBOrientation;
    //   195: astore_0
    //   196: goto -> 104
    //   199: iload_3
    //   200: tableswitch default -> 228, 1 -> 235, 2 -> 242, 3 -> 249
    //   228: getstatic com/chartboost/sdk/Libraries/CBOrientation.LANDSCAPE : Lcom/chartboost/sdk/Libraries/CBOrientation;
    //   231: astore_0
    //   232: goto -> 104
    //   235: getstatic com/chartboost/sdk/Libraries/CBOrientation.PORTRAIT_LEFT : Lcom/chartboost/sdk/Libraries/CBOrientation;
    //   238: astore_0
    //   239: goto -> 104
    //   242: getstatic com/chartboost/sdk/Libraries/CBOrientation.LANDSCAPE_REVERSE : Lcom/chartboost/sdk/Libraries/CBOrientation;
    //   245: astore_0
    //   246: goto -> 104
    //   249: getstatic com/chartboost/sdk/Libraries/CBOrientation.PORTRAIT_RIGHT : Lcom/chartboost/sdk/Libraries/CBOrientation;
    //   252: astore_0
    //   253: goto -> 104
    //   256: iconst_1
    //   257: istore_1
    //   258: goto -> 56
  }
  
  public static boolean c() {
    return a().getBoolean("cbIdentityTrackingDisabled", false);
  }
  
  public static boolean d() {
    return (Looper.myLooper() == Looper.getMainLooper());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Libraries\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */