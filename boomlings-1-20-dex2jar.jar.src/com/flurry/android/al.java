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
    //   3: istore #4
    //   5: aload_0
    //   6: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   9: invokevirtual getFormat : ()Ljava/lang/String;
    //   12: ldc 'takeover'
    //   14: invokevirtual equals : (Ljava/lang/Object;)Z
    //   17: ifeq -> 158
    //   20: new com/millennialmedia/android/MMAdView
    //   23: dup
    //   24: aload_1
    //   25: checkcast android/app/Activity
    //   28: getstatic com/flurry/android/al.sAdNetworkApiKey : Ljava/lang/String;
    //   31: ldc 'MMFullScreenAdTransition'
    //   33: iconst_1
    //   34: aconst_null
    //   35: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;ZLjava/util/Hashtable;)V
    //   38: astore_1
    //   39: aload_1
    //   40: ldc 1897808290
    //   42: invokevirtual setId : (I)V
    //   45: aload_1
    //   46: new com/flurry/android/ay
    //   49: dup
    //   50: aload_0
    //   51: invokespecial <init> : (Lcom/flurry/android/al;)V
    //   54: invokevirtual setListener : (Lcom/millennialmedia/android/MMAdView$MMAdListener;)V
    //   57: aload_1
    //   58: invokevirtual fetch : ()V
    //   61: aload_0
    //   62: aload_1
    //   63: invokevirtual display : ()Z
    //   66: putfield e : Z
    //   69: aload_0
    //   70: getfield e : Z
    //   73: ifeq -> 116
    //   76: ldc 'FlurryAgent'
    //   78: new java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial <init> : ()V
    //   85: ldc 'Millennial MMAdView Interstitial ad displayed immediately:'
    //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokestatic currentTimeMillis : ()J
    //   93: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   96: ldc ' '
    //   98: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: aload_0
    //   102: getfield e : Z
    //   105: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   108: invokevirtual toString : ()Ljava/lang/String;
    //   111: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   114: pop
    //   115: return
    //   116: ldc 'FlurryAgent'
    //   118: new java/lang/StringBuilder
    //   121: dup
    //   122: invokespecial <init> : ()V
    //   125: ldc 'Millennial MMAdView Interstitial ad did not display immediately:'
    //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokestatic currentTimeMillis : ()J
    //   133: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   136: ldc ' '
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: aload_0
    //   142: getfield e : Z
    //   145: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   148: invokevirtual toString : ()Ljava/lang/String;
    //   151: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   154: pop
    //   155: goto -> 115
    //   158: aload_0
    //   159: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   162: invokevirtual getHeight : ()I
    //   165: istore #5
    //   167: aload_0
    //   168: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   171: invokevirtual getWidth : ()I
    //   174: istore_3
    //   175: iload_3
    //   176: sipush #320
    //   179: if_icmplt -> 313
    //   182: iload #5
    //   184: bipush #50
    //   186: if_icmplt -> 313
    //   189: ldc 'FlurryAgent'
    //   191: ldc 'Determined Millennial AdSize as MMBannerAdBottom'
    //   193: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   196: pop
    //   197: ldc 'MMBannerAdBottom'
    //   199: astore #6
    //   201: aload #6
    //   203: ifnull -> 357
    //   206: new com/millennialmedia/android/MMAdView
    //   209: dup
    //   210: aload_1
    //   211: checkcast android/app/Activity
    //   214: getstatic com/flurry/android/al.sAdNetworkApiKey : Ljava/lang/String;
    //   217: aload #6
    //   219: iconst_0
    //   220: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;I)V
    //   223: astore_1
    //   224: aload_1
    //   225: ldc 1897808289
    //   227: invokevirtual setId : (I)V
    //   230: aload #6
    //   232: ldc 'MMBannerAdBottom'
    //   234: invokevirtual equals : (Ljava/lang/Object;)Z
    //   237: ifeq -> 240
    //   240: aload #6
    //   242: ldc 'MMBannerAdRectangle'
    //   244: invokevirtual equals : (Ljava/lang/Object;)Z
    //   247: ifeq -> 368
    //   250: sipush #300
    //   253: istore_3
    //   254: aload_0
    //   255: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   258: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   261: getfield density : F
    //   264: fstore_2
    //   265: aload_1
    //   266: new android/widget/LinearLayout$LayoutParams
    //   269: dup
    //   270: iload_3
    //   271: i2f
    //   272: fload_2
    //   273: fmul
    //   274: ldc 0.5
    //   276: fadd
    //   277: f2i
    //   278: iload #4
    //   280: i2f
    //   281: fload_2
    //   282: fmul
    //   283: ldc 0.5
    //   285: fadd
    //   286: f2i
    //   287: invokespecial <init> : (II)V
    //   290: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   293: aload_1
    //   294: new com/flurry/android/ak
    //   297: dup
    //   298: aload_0
    //   299: invokespecial <init> : (Lcom/flurry/android/al;)V
    //   302: invokevirtual setListener : (Lcom/millennialmedia/android/MMAdView$MMAdListener;)V
    //   305: aload_0
    //   306: aload_1
    //   307: invokevirtual addView : (Landroid/view/View;)V
    //   310: goto -> 115
    //   313: iload_3
    //   314: sipush #300
    //   317: if_icmplt -> 343
    //   320: iload #5
    //   322: sipush #250
    //   325: if_icmplt -> 343
    //   328: ldc 'FlurryAgent'
    //   330: ldc 'Determined Millennial AdSize as MMBannerAdRectangle'
    //   332: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   335: pop
    //   336: ldc 'MMBannerAdRectangle'
    //   338: astore #6
    //   340: goto -> 201
    //   343: ldc 'FlurryAgent'
    //   345: ldc 'Could not find Millennial AdSize that matches size'
    //   347: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   350: pop
    //   351: aconst_null
    //   352: astore #6
    //   354: goto -> 201
    //   357: ldc 'FlurryAgent'
    //   359: ldc '**********Could not load Millennial Ad'
    //   361: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   364: pop
    //   365: goto -> 115
    //   368: bipush #50
    //   370: istore #4
    //   372: sipush #320
    //   375: istore_3
    //   376: goto -> 254
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\al.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */