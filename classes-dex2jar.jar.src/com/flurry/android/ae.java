package com.flurry.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

final class ae extends AdNetworkView {
  private static boolean e;
  
  ae(Context paramContext, bo parambo, bl parambl, AdCreative paramAdCreative) {
    super(paramContext, parambo, parambl, paramAdCreative);
    bo bo1;
    parambo = null;
    try {
      ApplicationInfo applicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.d("FlurryAgent", "Cannot find manifest for app");
      bo1 = parambo;
    } 
    Bundle bundle = ((ApplicationInfo)bo1).metaData;
    sAdNetworkApiKey = bundle.getString("com.flurry.inmobi.MY_APP_ID");
    e = bundle.getBoolean("com.flurry.inmobi.test");
    if (sAdNetworkApiKey == null)
      Log.d("FlurryAgent", "com.flurry.inmobi.MY_APP_ID not set in manifest"); 
    setFocusable(true);
  }
  
  public final void initLayout(Context paramContext) {
    // Byte code:
    //   0: sipush #320
    //   3: istore #4
    //   5: bipush #50
    //   7: istore #5
    //   9: aload_0
    //   10: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   13: invokevirtual getFormat : ()Ljava/lang/String;
    //   16: ldc 'takeover'
    //   18: invokevirtual equals : (Ljava/lang/Object;)Z
    //   21: ifeq -> 87
    //   24: new com/inmobi/androidsdk/IMAdInterstitial
    //   27: dup
    //   28: aload_1
    //   29: checkcast android/app/Activity
    //   32: getstatic com/flurry/android/ae.sAdNetworkApiKey : Ljava/lang/String;
    //   35: invokespecial <init> : (Landroid/app/Activity;Ljava/lang/String;)V
    //   38: astore #7
    //   40: aload #7
    //   42: new com/flurry/android/m
    //   45: dup
    //   46: aload_0
    //   47: invokespecial <init> : (Lcom/flurry/android/ae;)V
    //   50: invokevirtual setImAdInterstitialListener : (Lcom/inmobi/androidsdk/IMAdInterstitialListener;)V
    //   53: new com/inmobi/androidsdk/IMAdRequest
    //   56: dup
    //   57: invokespecial <init> : ()V
    //   60: astore_1
    //   61: getstatic com/flurry/android/ae.e : Z
    //   64: ifeq -> 80
    //   67: ldc 'FlurryAgent'
    //   69: ldc 'InMobi Interstitial set to Test Mode.'
    //   71: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   74: pop
    //   75: aload_1
    //   76: iconst_1
    //   77: invokevirtual setTestMode : (Z)V
    //   80: aload #7
    //   82: aload_1
    //   83: invokevirtual loadNewAd : (Lcom/inmobi/androidsdk/IMAdRequest;)V
    //   86: return
    //   87: aload_1
    //   88: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   91: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   94: astore #7
    //   96: aload #7
    //   98: getfield heightPixels : I
    //   101: i2f
    //   102: aload #7
    //   104: getfield density : F
    //   107: fdiv
    //   108: f2i
    //   109: istore_3
    //   110: aload #7
    //   112: getfield widthPixels : I
    //   115: i2f
    //   116: aload #7
    //   118: getfield density : F
    //   121: fdiv
    //   122: f2i
    //   123: istore #6
    //   125: iload #6
    //   127: sipush #728
    //   130: if_icmplt -> 348
    //   133: iload_3
    //   134: bipush #90
    //   136: if_icmplt -> 348
    //   139: ldc 'FlurryAgent'
    //   141: ldc 'Determined InMobi AdSize as 728x90'
    //   143: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   146: pop
    //   147: bipush #11
    //   149: istore_3
    //   150: iload_3
    //   151: iconst_m1
    //   152: if_icmpeq -> 474
    //   155: new com/inmobi/androidsdk/IMAdView
    //   158: dup
    //   159: aload_1
    //   160: checkcast android/app/Activity
    //   163: iload_3
    //   164: getstatic com/flurry/android/ae.sAdNetworkApiKey : Ljava/lang/String;
    //   167: invokespecial <init> : (Landroid/app/Activity;ILjava/lang/String;)V
    //   170: astore_1
    //   171: iload_3
    //   172: bipush #15
    //   174: if_icmpne -> 177
    //   177: iload_3
    //   178: bipush #11
    //   180: if_icmpne -> 192
    //   183: sipush #728
    //   186: istore #4
    //   188: bipush #90
    //   190: istore #5
    //   192: iload_3
    //   193: bipush #12
    //   195: if_icmpne -> 207
    //   198: sipush #468
    //   201: istore #4
    //   203: bipush #60
    //   205: istore #5
    //   207: iload_3
    //   208: bipush #10
    //   210: if_icmpne -> 223
    //   213: sipush #300
    //   216: istore #4
    //   218: sipush #250
    //   221: istore #5
    //   223: iload_3
    //   224: bipush #13
    //   226: if_icmpne -> 238
    //   229: bipush #120
    //   231: istore #4
    //   233: sipush #600
    //   236: istore #5
    //   238: aload_0
    //   239: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   242: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   245: getfield density : F
    //   248: fstore_2
    //   249: aload_1
    //   250: new android/widget/LinearLayout$LayoutParams
    //   253: dup
    //   254: iload #4
    //   256: i2f
    //   257: fload_2
    //   258: fmul
    //   259: ldc 0.5
    //   261: fadd
    //   262: f2i
    //   263: iload #5
    //   265: i2f
    //   266: fload_2
    //   267: fmul
    //   268: ldc 0.5
    //   270: fadd
    //   271: f2i
    //   272: invokespecial <init> : (II)V
    //   275: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   278: aload_1
    //   279: bipush #14
    //   281: invokevirtual setGravity : (I)V
    //   284: aload_1
    //   285: new com/flurry/android/af
    //   288: dup
    //   289: aload_0
    //   290: invokespecial <init> : (Lcom/flurry/android/ae;)V
    //   293: invokevirtual setIMAdListener : (Lcom/inmobi/androidsdk/IMAdListener;)V
    //   296: aload_0
    //   297: aload_1
    //   298: invokevirtual addView : (Landroid/view/View;)V
    //   301: new com/inmobi/androidsdk/IMAdRequest
    //   304: dup
    //   305: invokespecial <init> : ()V
    //   308: astore #7
    //   310: getstatic com/flurry/android/ae.e : Z
    //   313: ifeq -> 330
    //   316: ldc 'FlurryAgent'
    //   318: ldc 'InMobi AdView set to Test Mode.'
    //   320: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   323: pop
    //   324: aload #7
    //   326: iconst_1
    //   327: invokevirtual setTestMode : (Z)V
    //   330: aload_1
    //   331: aload #7
    //   333: invokevirtual setIMAdRequest : (Lcom/inmobi/androidsdk/IMAdRequest;)V
    //   336: aload_1
    //   337: iconst_m1
    //   338: invokevirtual setRefreshInterval : (I)V
    //   341: aload_1
    //   342: invokevirtual loadNewAd : ()V
    //   345: goto -> 86
    //   348: iload #6
    //   350: sipush #468
    //   353: if_icmplt -> 376
    //   356: iload_3
    //   357: bipush #60
    //   359: if_icmplt -> 376
    //   362: ldc 'FlurryAgent'
    //   364: ldc 'Determined InMobi AdSize as 468x60'
    //   366: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   369: pop
    //   370: bipush #12
    //   372: istore_3
    //   373: goto -> 150
    //   376: iload #6
    //   378: sipush #320
    //   381: if_icmplt -> 404
    //   384: iload_3
    //   385: bipush #50
    //   387: if_icmplt -> 404
    //   390: ldc 'FlurryAgent'
    //   392: ldc 'Determined InMobi AdSize as 320x50'
    //   394: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   397: pop
    //   398: bipush #15
    //   400: istore_3
    //   401: goto -> 150
    //   404: iload #6
    //   406: sipush #300
    //   409: if_icmplt -> 433
    //   412: iload_3
    //   413: sipush #250
    //   416: if_icmplt -> 433
    //   419: ldc 'FlurryAgent'
    //   421: ldc 'Determined InMobi AdSize as 300x250'
    //   423: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   426: pop
    //   427: bipush #10
    //   429: istore_3
    //   430: goto -> 150
    //   433: iload #6
    //   435: bipush #120
    //   437: if_icmplt -> 461
    //   440: iload_3
    //   441: sipush #600
    //   444: if_icmplt -> 461
    //   447: ldc 'FlurryAgent'
    //   449: ldc 'Determined InMobi AdSize as 120x600'
    //   451: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   454: pop
    //   455: bipush #13
    //   457: istore_3
    //   458: goto -> 150
    //   461: ldc 'FlurryAgent'
    //   463: ldc 'Could not find InMobi AdSize that matches size'
    //   465: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   468: pop
    //   469: iconst_m1
    //   470: istore_3
    //   471: goto -> 150
    //   474: ldc 'FlurryAgent'
    //   476: ldc '**********Could not load InMobi Ad'
    //   478: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   481: pop
    //   482: goto -> 86
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\ae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */