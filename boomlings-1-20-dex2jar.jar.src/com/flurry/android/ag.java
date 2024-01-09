package com.flurry.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

final class ag extends AdNetworkView {
  ag(Context paramContext, bo parambo, bl parambl, AdCreative paramAdCreative) {
    super(paramContext, parambo, parambl, paramAdCreative);
    bo bo1;
    parambo = null;
    try {
      ApplicationInfo applicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.d("FlurryAgent", "Cannot find manifest for app");
      bo1 = parambo;
    } 
    String str = ((ApplicationInfo)bo1).metaData.getString("com.mobclix.APPLICATION_ID");
    sAdNetworkApiKey = str;
    if (str == null)
      Log.d("MobclixTestApp", "com.mobclix.APPLICATION_ID not set in manifest"); 
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
    //   17: ifeq -> 50
    //   20: new com/mobclix/android/sdk/MobclixFullScreenAdView
    //   23: dup
    //   24: aload_1
    //   25: checkcast android/app/Activity
    //   28: invokespecial <init> : (Landroid/app/Activity;)V
    //   31: astore_1
    //   32: aload_1
    //   33: new com/flurry/android/ad
    //   36: dup
    //   37: aload_0
    //   38: invokespecial <init> : (Lcom/flurry/android/ag;)V
    //   41: invokevirtual addMobclixAdViewListener : (Lcom/mobclix/android/sdk/MobclixFullScreenAdViewListener;)Z
    //   44: pop
    //   45: aload_1
    //   46: invokevirtual requestAndDisplayAd : ()V
    //   49: return
    //   50: aload_0
    //   51: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   54: invokevirtual getHeight : ()I
    //   57: istore #5
    //   59: aload_0
    //   60: getfield fAdCreative : Lcom/flurry/android/AdCreative;
    //   63: invokevirtual getWidth : ()I
    //   66: istore_3
    //   67: iload_3
    //   68: sipush #320
    //   71: if_icmplt -> 192
    //   74: iload #5
    //   76: bipush #50
    //   78: if_icmplt -> 192
    //   81: ldc 'FlurryAgent'
    //   83: ldc 'Determined Mobclix AdSize as BANNER'
    //   85: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   88: pop
    //   89: new com/mobclix/android/sdk/MobclixMMABannerXLAdView
    //   92: dup
    //   93: aload_1
    //   94: checkcast android/app/Activity
    //   97: invokespecial <init> : (Landroid/content/Context;)V
    //   100: astore_1
    //   101: aload_1
    //   102: new com/flurry/android/am
    //   105: dup
    //   106: aload_0
    //   107: invokespecial <init> : (Lcom/flurry/android/ag;)V
    //   110: invokevirtual addMobclixAdViewListener : (Lcom/mobclix/android/sdk/MobclixAdViewListener;)Z
    //   113: pop
    //   114: aload_1
    //   115: instanceof com/mobclix/android/sdk/MobclixMMABannerXLAdView
    //   118: ifeq -> 121
    //   121: aload_1
    //   122: instanceof com/mobclix/android/sdk/MobclixIABRectangleMAdView
    //   125: ifeq -> 243
    //   128: sipush #320
    //   131: istore_3
    //   132: aload_0
    //   133: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   136: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   139: getfield density : F
    //   142: fstore_2
    //   143: aload_1
    //   144: new android/widget/LinearLayout$LayoutParams
    //   147: dup
    //   148: iload_3
    //   149: i2f
    //   150: fload_2
    //   151: fmul
    //   152: ldc 0.5
    //   154: fadd
    //   155: f2i
    //   156: iload #4
    //   158: i2f
    //   159: fload_2
    //   160: fmul
    //   161: ldc 0.5
    //   163: fadd
    //   164: f2i
    //   165: invokespecial <init> : (II)V
    //   168: invokevirtual setLayoutParams : (Landroid/view/ViewGroup$LayoutParams;)V
    //   171: aload_1
    //   172: bipush #14
    //   174: invokevirtual setForegroundGravity : (I)V
    //   177: aload_0
    //   178: aload_1
    //   179: invokevirtual addView : (Landroid/view/View;)V
    //   182: aload_1
    //   183: ldc2_w -1
    //   186: invokevirtual setRefreshTime : (J)V
    //   189: goto -> 49
    //   192: iload_3
    //   193: sipush #300
    //   196: if_icmplt -> 230
    //   199: iload #5
    //   201: sipush #250
    //   204: if_icmplt -> 230
    //   207: ldc 'FlurryAgent'
    //   209: ldc 'Determined Mobclix AdSize as IAB_RECT'
    //   211: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   214: pop
    //   215: new com/mobclix/android/sdk/MobclixIABRectangleMAdView
    //   218: dup
    //   219: aload_1
    //   220: checkcast android/app/Activity
    //   223: invokespecial <init> : (Landroid/content/Context;)V
    //   226: astore_1
    //   227: goto -> 101
    //   230: ldc 'FlurryAgent'
    //   232: ldc 'Could not find Mobclix AdSize that matches size'
    //   234: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   237: pop
    //   238: aconst_null
    //   239: astore_1
    //   240: goto -> 101
    //   243: bipush #50
    //   245: istore #4
    //   247: sipush #300
    //   250: istore_3
    //   251: goto -> 132
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */