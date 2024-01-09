package com.flurry.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.ads.InterstitialAd;

final class ab extends AdNetworkView {
  private static boolean e;
  
  private InterstitialAd f;
  
  ab(Context paramContext, bo parambo, bl parambl, AdCreative paramAdCreative) {
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
    sAdNetworkApiKey = bundle.getString("com.flurry.admob.MY_AD_UNIT_ID");
    sAdNetworkApiKey2 = bundle.getString("com.flurry.admob.MYTEST_AD_DEVICE_ID");
    e = bundle.getBoolean("com.flurry.admob.test");
    if (sAdNetworkApiKey == null)
      Log.d("FlurryAgent", "com.flurry.admob.MY_AD_UNIT_ID not set in manifest"); 
    setFocusable(true);
  }
  
  public final void initLayout(Context paramContext) {
    // Byte code:
    //   0: sipush #320
    //   3: istore #4
    //   5: bipush #50
    //   7: istore_3
    //   8: aload_0
    //   9: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   12: invokevirtual getFormat : ()Ljava/lang/String;
    //   15: ldc 'takeover'
    //   17: invokevirtual equals : (Ljava/lang/Object;)Z
    //   20: ifeq -> 119
    //   23: aload_0
    //   24: new com/google/ads/InterstitialAd
    //   27: dup
    //   28: aload_1
    //   29: checkcast android/app/Activity
    //   32: getstatic com/flurry/android/ab.sAdNetworkApiKey : Ljava/lang/String;
    //   35: invokespecial <init> : (Landroid/app/Activity;Ljava/lang/String;)V
    //   38: putfield f : Lcom/google/ads/InterstitialAd;
    //   41: new com/flurry/android/j
    //   44: dup
    //   45: aload_0
    //   46: invokespecial <init> : (Lcom/flurry/android/ab;)V
    //   49: astore_1
    //   50: aload_0
    //   51: getfield f : Lcom/google/ads/InterstitialAd;
    //   54: aload_1
    //   55: invokevirtual a : (Lcom/google/ads/AdListener;)V
    //   58: new com/google/ads/AdRequest
    //   61: dup
    //   62: invokespecial <init> : ()V
    //   65: astore_1
    //   66: getstatic com/flurry/android/ab.e : Z
    //   69: ifeq -> 110
    //   72: ldc 'FlurryAgent'
    //   74: ldc 'Admob AdView set to Test Mode.'
    //   76: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   79: pop
    //   80: aload_1
    //   81: getstatic com/google/ads/AdRequest.a : Ljava/lang/String;
    //   84: invokevirtual a : (Ljava/lang/String;)Lcom/google/ads/AdRequest;
    //   87: pop
    //   88: getstatic com/flurry/android/ab.sAdNetworkApiKey2 : Ljava/lang/String;
    //   91: ifnull -> 110
    //   94: getstatic com/flurry/android/ab.sAdNetworkApiKey2 : Ljava/lang/String;
    //   97: ldc ''
    //   99: if_acmpeq -> 110
    //   102: aload_1
    //   103: getstatic com/flurry/android/ab.sAdNetworkApiKey2 : Ljava/lang/String;
    //   106: invokevirtual a : (Ljava/lang/String;)Lcom/google/ads/AdRequest;
    //   109: pop
    //   110: aload_0
    //   111: getfield f : Lcom/google/ads/InterstitialAd;
    //   114: aload_1
    //   115: invokevirtual a : (Lcom/google/ads/AdRequest;)V
    //   118: return
    //   119: aload_1
    //   120: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   123: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   126: astore #7
    //   128: aload #7
    //   130: getfield heightPixels : I
    //   133: i2f
    //   134: aload #7
    //   136: getfield density : F
    //   139: fdiv
    //   140: f2i
    //   141: istore #6
    //   143: aload #7
    //   145: getfield widthPixels : I
    //   148: i2f
    //   149: aload #7
    //   151: getfield density : F
    //   154: fdiv
    //   155: f2i
    //   156: istore #5
    //   158: iload #5
    //   160: getstatic com/google/ads/AdSize.IAB_LEADERBOARD : Lcom/google/ads/AdSize;
    //   163: invokevirtual a : ()I
    //   166: if_icmplt -> 372
    //   169: iload #6
    //   171: getstatic com/google/ads/AdSize.IAB_LEADERBOARD : Lcom/google/ads/AdSize;
    //   174: invokevirtual b : ()I
    //   177: if_icmplt -> 372
    //   180: ldc 'FlurryAgent'
    //   182: ldc 'Determined Admob AdSize as IAB_LEADERBOARD'
    //   184: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   187: pop
    //   188: getstatic com/google/ads/AdSize.IAB_LEADERBOARD : Lcom/google/ads/AdSize;
    //   191: astore #7
    //   193: aload #7
    //   195: ifnull -> 500
    //   198: new com/google/ads/AdView
    //   201: dup
    //   202: aload_1
    //   203: checkcast android/app/Activity
    //   206: aload #7
    //   208: getstatic com/flurry/android/ab.sAdNetworkApiKey : Ljava/lang/String;
    //   211: invokespecial <init> : (Landroid/app/Activity;Lcom/google/ads/AdSize;Ljava/lang/String;)V
    //   214: astore_1
    //   215: aload #7
    //   217: getstatic com/google/ads/AdSize.BANNER : Lcom/google/ads/AdSize;
    //   220: invokevirtual equals : (Ljava/lang/Object;)Z
    //   223: ifeq -> 226
    //   226: aload #7
    //   228: getstatic com/google/ads/AdSize.IAB_MRECT : Lcom/google/ads/AdSize;
    //   231: invokevirtual equals : (Ljava/lang/Object;)Z
    //   234: ifeq -> 246
    //   237: sipush #300
    //   240: istore #4
    //   242: sipush #250
    //   245: istore_3
    //   246: aload_0
    //   247: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   250: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   253: getfield density : F
    //   256: fstore_2
    //   257: aload_1
    //   258: new android/widget/LinearLayout$LayoutParams
    //   261: dup
    //   262: iload #4
    //   264: i2f
    //   265: fload_2
    //   266: fmul
    //   267: ldc 0.5
    //   269: fadd
    //   270: f2i
    //   271: iload_3
    //   272: i2f
    //   273: fload_2
    //   274: fmul
    //   275: ldc 0.5
    //   277: fadd
    //   278: f2i
    //   279: invokespecial <init> : (II)V
    //   282: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   285: aload_1
    //   286: bipush #14
    //   288: invokevirtual setGravity : (I)V
    //   291: aload_1
    //   292: new com/flurry/android/bg
    //   295: dup
    //   296: aload_0
    //   297: invokespecial <init> : (Lcom/flurry/android/ab;)V
    //   300: invokevirtual setAdListener : (Lcom/google/ads/AdListener;)V
    //   303: aload_0
    //   304: aload_1
    //   305: invokevirtual addView : (Landroid/view/View;)V
    //   308: new com/google/ads/AdRequest
    //   311: dup
    //   312: invokespecial <init> : ()V
    //   315: astore #7
    //   317: getstatic com/flurry/android/ab.e : Z
    //   320: ifeq -> 363
    //   323: ldc 'FlurryAgent'
    //   325: ldc 'Admob AdView set to Test Mode.'
    //   327: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   330: pop
    //   331: aload #7
    //   333: getstatic com/google/ads/AdRequest.a : Ljava/lang/String;
    //   336: invokevirtual a : (Ljava/lang/String;)Lcom/google/ads/AdRequest;
    //   339: pop
    //   340: getstatic com/flurry/android/ab.sAdNetworkApiKey2 : Ljava/lang/String;
    //   343: ifnull -> 363
    //   346: getstatic com/flurry/android/ab.sAdNetworkApiKey2 : Ljava/lang/String;
    //   349: ldc ''
    //   351: if_acmpeq -> 363
    //   354: aload #7
    //   356: getstatic com/flurry/android/ab.sAdNetworkApiKey2 : Ljava/lang/String;
    //   359: invokevirtual a : (Ljava/lang/String;)Lcom/google/ads/AdRequest;
    //   362: pop
    //   363: aload_1
    //   364: aload #7
    //   366: invokevirtual loadAd : (Lcom/google/ads/AdRequest;)V
    //   369: goto -> 118
    //   372: iload #5
    //   374: getstatic com/google/ads/AdSize.IAB_BANNER : Lcom/google/ads/AdSize;
    //   377: invokevirtual a : ()I
    //   380: if_icmplt -> 410
    //   383: iload #6
    //   385: getstatic com/google/ads/AdSize.IAB_BANNER : Lcom/google/ads/AdSize;
    //   388: invokevirtual b : ()I
    //   391: if_icmplt -> 410
    //   394: ldc 'FlurryAgent'
    //   396: ldc 'Determined Admob AdSize as IAB_BANNER'
    //   398: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   401: pop
    //   402: getstatic com/google/ads/AdSize.IAB_BANNER : Lcom/google/ads/AdSize;
    //   405: astore #7
    //   407: goto -> 193
    //   410: iload #5
    //   412: getstatic com/google/ads/AdSize.BANNER : Lcom/google/ads/AdSize;
    //   415: invokevirtual a : ()I
    //   418: if_icmplt -> 448
    //   421: iload #6
    //   423: getstatic com/google/ads/AdSize.BANNER : Lcom/google/ads/AdSize;
    //   426: invokevirtual b : ()I
    //   429: if_icmplt -> 448
    //   432: ldc 'FlurryAgent'
    //   434: ldc 'Determined Admob AdSize as BANNER'
    //   436: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   439: pop
    //   440: getstatic com/google/ads/AdSize.BANNER : Lcom/google/ads/AdSize;
    //   443: astore #7
    //   445: goto -> 193
    //   448: iload #5
    //   450: getstatic com/google/ads/AdSize.IAB_MRECT : Lcom/google/ads/AdSize;
    //   453: invokevirtual a : ()I
    //   456: if_icmplt -> 486
    //   459: iload #6
    //   461: getstatic com/google/ads/AdSize.IAB_MRECT : Lcom/google/ads/AdSize;
    //   464: invokevirtual b : ()I
    //   467: if_icmplt -> 486
    //   470: ldc 'FlurryAgent'
    //   472: ldc 'Determined Admob AdSize as IAB_MRECT'
    //   474: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   477: pop
    //   478: getstatic com/google/ads/AdSize.IAB_MRECT : Lcom/google/ads/AdSize;
    //   481: astore #7
    //   483: goto -> 193
    //   486: ldc 'FlurryAgent'
    //   488: ldc 'Could not find Admob AdSize that matches size'
    //   490: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   493: pop
    //   494: aconst_null
    //   495: astore #7
    //   497: goto -> 193
    //   500: ldc 'FlurryAgent'
    //   502: ldc '**********Could not load Admob Ad'
    //   504: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   507: pop
    //   508: goto -> 118
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */