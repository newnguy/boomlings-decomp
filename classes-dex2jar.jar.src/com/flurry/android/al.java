package com.flurry.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

final class al extends AdNetworkView {
  private boolean e;
  
  al(Context paramContext, bo parambo, bl parambl, AdCreative paramAdCreative) {
    super(paramContext, parambo, parambl, paramAdCreative);
    bo bo1;
    parambo = null;
    try {
      ApplicationInfo applicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.d("FlurryAgent", "Cannot find manifest for app");
      bo1 = parambo;
    } 
    String str = ((ApplicationInfo)bo1).metaData.getString("com.flurry.millennial.MYAPID");
    sAdNetworkApiKey = str;
    if (str == null)
      Log.d("FlurryAgent", "com.flurry.millennial.MYAPID not set in manifest"); 
    setFocusable(true);
  }
  
  public final void initLayout(Context paramContext) {
    // Byte code:
    //   0: sipush #250
    //   3: istore_3
    //   4: aload_0
    //   5: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   8: invokevirtual getFormat : ()Ljava/lang/String;
    //   11: ldc 'takeover'
    //   13: invokevirtual equals : (Ljava/lang/Object;)Z
    //   16: ifeq -> 157
    //   19: new com/millennialmedia/android/MMAdView
    //   22: dup
    //   23: aload_1
    //   24: checkcast android/app/Activity
    //   27: getstatic com/flurry/android/al.sAdNetworkApiKey : Ljava/lang/String;
    //   30: ldc 'MMFullScreenAdTransition'
    //   32: iconst_1
    //   33: aconst_null
    //   34: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;ZLjava/util/Hashtable;)V
    //   37: astore_1
    //   38: aload_1
    //   39: ldc 1897808290
    //   41: invokevirtual setId : (I)V
    //   44: aload_1
    //   45: new com/flurry/android/ay
    //   48: dup
    //   49: aload_0
    //   50: invokespecial <init> : (Lcom/flurry/android/al;)V
    //   53: invokevirtual setListener : (Lcom/millennialmedia/android/MMAdView$MMAdListener;)V
    //   56: aload_1
    //   57: invokevirtual fetch : ()V
    //   60: aload_0
    //   61: aload_1
    //   62: invokevirtual display : ()Z
    //   65: putfield e : Z
    //   68: aload_0
    //   69: getfield e : Z
    //   72: ifeq -> 115
    //   75: ldc 'FlurryAgent'
    //   77: new java/lang/StringBuilder
    //   80: dup
    //   81: invokespecial <init> : ()V
    //   84: ldc 'Millennial MMAdView Interstitial ad displayed immediately:'
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokestatic currentTimeMillis : ()J
    //   92: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   95: ldc ' '
    //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: aload_0
    //   101: getfield e : Z
    //   104: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   107: invokevirtual toString : ()Ljava/lang/String;
    //   110: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: return
    //   115: ldc 'FlurryAgent'
    //   117: new java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial <init> : ()V
    //   124: ldc 'Millennial MMAdView Interstitial ad did not display immediately:'
    //   126: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: invokestatic currentTimeMillis : ()J
    //   132: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   135: ldc ' '
    //   137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload_0
    //   141: getfield e : Z
    //   144: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   147: invokevirtual toString : ()Ljava/lang/String;
    //   150: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   153: pop
    //   154: goto -> 114
    //   157: aload_0
    //   158: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   161: invokevirtual getHeight : ()I
    //   164: istore #4
    //   166: aload_0
    //   167: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   170: invokevirtual getWidth : ()I
    //   173: istore #5
    //   175: iload #5
    //   177: sipush #320
    //   180: if_icmplt -> 315
    //   183: iload #4
    //   185: bipush #50
    //   187: if_icmplt -> 315
    //   190: ldc 'FlurryAgent'
    //   192: ldc 'Determined Millennial AdSize as MMBannerAdBottom'
    //   194: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   197: pop
    //   198: ldc 'MMBannerAdBottom'
    //   200: astore #6
    //   202: aload #6
    //   204: ifnull -> 360
    //   207: new com/millennialmedia/android/MMAdView
    //   210: dup
    //   211: aload_1
    //   212: checkcast android/app/Activity
    //   215: getstatic com/flurry/android/al.sAdNetworkApiKey : Ljava/lang/String;
    //   218: aload #6
    //   220: iconst_0
    //   221: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;I)V
    //   224: astore_1
    //   225: aload_1
    //   226: ldc 1897808289
    //   228: invokevirtual setId : (I)V
    //   231: aload #6
    //   233: ldc 'MMBannerAdBottom'
    //   235: invokevirtual equals : (Ljava/lang/Object;)Z
    //   238: ifeq -> 241
    //   241: aload #6
    //   243: ldc 'MMBannerAdRectangle'
    //   245: invokevirtual equals : (Ljava/lang/Object;)Z
    //   248: ifeq -> 371
    //   251: sipush #300
    //   254: istore #4
    //   256: aload_0
    //   257: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   260: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   263: getfield density : F
    //   266: fstore_2
    //   267: aload_1
    //   268: new android/widget/LinearLayout$LayoutParams
    //   271: dup
    //   272: iload #4
    //   274: i2f
    //   275: fload_2
    //   276: fmul
    //   277: ldc 0.5
    //   279: fadd
    //   280: f2i
    //   281: iload_3
    //   282: i2f
    //   283: fload_2
    //   284: fmul
    //   285: ldc 0.5
    //   287: fadd
    //   288: f2i
    //   289: invokespecial <init> : (II)V
    //   292: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   295: aload_1
    //   296: new com/flurry/android/ak
    //   299: dup
    //   300: aload_0
    //   301: invokespecial <init> : (Lcom/flurry/android/al;)V
    //   304: invokevirtual setListener : (Lcom/millennialmedia/android/MMAdView$MMAdListener;)V
    //   307: aload_0
    //   308: aload_1
    //   309: invokevirtual addView : (Landroid/view/View;)V
    //   312: goto -> 114
    //   315: iload #5
    //   317: sipush #300
    //   320: if_icmplt -> 346
    //   323: iload #4
    //   325: sipush #250
    //   328: if_icmplt -> 346
    //   331: ldc 'FlurryAgent'
    //   333: ldc 'Determined Millennial AdSize as MMBannerAdRectangle'
    //   335: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   338: pop
    //   339: ldc 'MMBannerAdRectangle'
    //   341: astore #6
    //   343: goto -> 202
    //   346: ldc 'FlurryAgent'
    //   348: ldc 'Could not find Millennial AdSize that matches size'
    //   350: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   353: pop
    //   354: aconst_null
    //   355: astore #6
    //   357: goto -> 202
    //   360: ldc 'FlurryAgent'
    //   362: ldc '**********Could not load Millennial Ad'
    //   364: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   367: pop
    //   368: goto -> 114
    //   371: bipush #50
    //   373: istore_3
    //   374: sipush #320
    //   377: istore #4
    //   379: goto -> 256
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\al.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */