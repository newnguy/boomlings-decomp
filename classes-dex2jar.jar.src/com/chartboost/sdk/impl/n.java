package com.chartboost.sdk.impl;

import android.view.View;
import android.view.ViewTreeObserver;
import com.chartboost.sdk.Libraries.CBOrientation;

public class n {
  private static int[] a;
  
  private static int[] b;
  
  public static void a(n$b paramn$b, a parama) {
    a(paramn$b, parama, null);
  }
  
  public static void a(n$b paramn$b, a parama, n$a paramn$a) {
    b(paramn$b, parama, paramn$a, Boolean.valueOf(true));
  }
  
  public static void b(n$b paramn$b, a parama, n$a paramn$a) {
    c(paramn$b, parama, paramn$a, Boolean.valueOf(false));
  }
  
  private static void b(n$b paramn$b, a parama, n$a paramn$a, Boolean paramBoolean) {
    if (parama != null && parama.h != null) {
      View view = parama.h.c();
      if (view != null) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive())
          viewTreeObserver.addOnGlobalLayoutListener(new n$1(view, paramn$b, parama, paramn$a, paramBoolean)); 
      } 
    } 
  }
  
  private static void c(n$b paramn$b, a parama, n$a paramn$a, Boolean paramBoolean) {
    // Byte code:
    //   0: new android/view/animation/AnimationSet
    //   3: dup
    //   4: iconst_1
    //   5: invokespecial <init> : (Z)V
    //   8: astore #10
    //   10: aload #10
    //   12: new android/view/animation/AlphaAnimation
    //   15: dup
    //   16: fconst_1
    //   17: fconst_1
    //   18: invokespecial <init> : (FF)V
    //   21: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   24: aload_1
    //   25: ifnull -> 35
    //   28: aload_1
    //   29: getfield h : Lcom/chartboost/sdk/impl/s;
    //   32: ifnonnull -> 36
    //   35: return
    //   36: aload_1
    //   37: getfield h : Lcom/chartboost/sdk/impl/s;
    //   40: invokevirtual c : ()Landroid/view/View;
    //   43: astore #9
    //   45: aload #9
    //   47: ifnull -> 35
    //   50: aload #9
    //   52: invokevirtual getWidth : ()I
    //   55: i2f
    //   56: fstore #5
    //   58: aload #9
    //   60: invokevirtual getHeight : ()I
    //   63: i2f
    //   64: fstore #4
    //   66: fconst_1
    //   67: ldc 0.4
    //   69: fsub
    //   70: fconst_2
    //   71: fdiv
    //   72: fstore #6
    //   74: invokestatic sharedChartboost : ()Lcom/chartboost/sdk/Chartboost;
    //   77: invokevirtual getForcedOrientationDifference : ()Lcom/chartboost/sdk/Libraries/CBOrientation$Difference;
    //   80: astore #11
    //   82: invokestatic b : ()[I
    //   85: aload_0
    //   86: invokevirtual ordinal : ()I
    //   89: iaload
    //   90: tableswitch default -> 124, 2 -> 728, 3 -> 1887, 4 -> 148, 5 -> 1308, 6 -> 1591
    //   124: aload #10
    //   126: new com/chartboost/sdk/impl/n$2
    //   129: dup
    //   130: aload_2
    //   131: aload_1
    //   132: invokespecial <init> : (Lcom/chartboost/sdk/impl/n$a;Lcom/chartboost/sdk/impl/a;)V
    //   135: invokevirtual setAnimationListener : (Landroid/view/animation/Animation$AnimationListener;)V
    //   138: aload #9
    //   140: aload #10
    //   142: invokevirtual startAnimation : (Landroid/view/animation/Animation;)V
    //   145: goto -> 35
    //   148: invokestatic a : ()[I
    //   151: aload #11
    //   153: invokevirtual ordinal : ()I
    //   156: iaload
    //   157: tableswitch default -> 184, 2 -> 353, 3 -> 407, 4 -> 461
    //   184: aload_3
    //   185: invokevirtual booleanValue : ()Z
    //   188: ifeq -> 515
    //   191: new com/chartboost/sdk/impl/p
    //   194: dup
    //   195: ldc 60.0
    //   197: fneg
    //   198: fconst_0
    //   199: fload #5
    //   201: fconst_2
    //   202: fdiv
    //   203: fload #4
    //   205: fconst_2
    //   206: fdiv
    //   207: iconst_0
    //   208: invokespecial <init> : (FFFFZ)V
    //   211: astore_0
    //   212: aload_0
    //   213: ldc2_w 600
    //   216: invokevirtual setDuration : (J)V
    //   219: aload_0
    //   220: iconst_1
    //   221: invokevirtual setFillAfter : (Z)V
    //   224: aload #10
    //   226: aload_0
    //   227: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   230: aload_3
    //   231: invokevirtual booleanValue : ()Z
    //   234: ifeq -> 538
    //   237: new android/view/animation/ScaleAnimation
    //   240: dup
    //   241: ldc 0.4
    //   243: fconst_1
    //   244: ldc 0.4
    //   246: fconst_1
    //   247: invokespecial <init> : (FFFF)V
    //   250: astore_0
    //   251: aload_0
    //   252: ldc2_w 600
    //   255: invokevirtual setDuration : (J)V
    //   258: aload_0
    //   259: iconst_1
    //   260: invokevirtual setFillAfter : (Z)V
    //   263: aload #10
    //   265: aload_0
    //   266: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   269: invokestatic a : ()[I
    //   272: aload #11
    //   274: invokevirtual ordinal : ()I
    //   277: iaload
    //   278: tableswitch default -> 304, 2 -> 555, 3 -> 606, 4 -> 657
    //   304: aload_3
    //   305: invokevirtual booleanValue : ()Z
    //   308: ifeq -> 708
    //   311: new android/view/animation/TranslateAnimation
    //   314: dup
    //   315: fload #5
    //   317: fload #6
    //   319: fmul
    //   320: fconst_0
    //   321: fload #4
    //   323: fneg
    //   324: ldc 0.4
    //   326: fmul
    //   327: fconst_0
    //   328: invokespecial <init> : (FFFF)V
    //   331: astore_0
    //   332: aload_0
    //   333: ldc2_w 600
    //   336: invokevirtual setDuration : (J)V
    //   339: aload_0
    //   340: iconst_1
    //   341: invokevirtual setFillAfter : (Z)V
    //   344: aload #10
    //   346: aload_0
    //   347: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   350: goto -> 124
    //   353: aload_3
    //   354: invokevirtual booleanValue : ()Z
    //   357: ifeq -> 384
    //   360: new com/chartboost/sdk/impl/p
    //   363: dup
    //   364: ldc 60.0
    //   366: fneg
    //   367: fconst_0
    //   368: fload #5
    //   370: fconst_2
    //   371: fdiv
    //   372: fload #4
    //   374: fconst_2
    //   375: fdiv
    //   376: iconst_1
    //   377: invokespecial <init> : (FFFFZ)V
    //   380: astore_0
    //   381: goto -> 212
    //   384: new com/chartboost/sdk/impl/p
    //   387: dup
    //   388: fconst_0
    //   389: ldc 60.0
    //   391: fload #5
    //   393: fconst_2
    //   394: fdiv
    //   395: fload #4
    //   397: fconst_2
    //   398: fdiv
    //   399: iconst_1
    //   400: invokespecial <init> : (FFFFZ)V
    //   403: astore_0
    //   404: goto -> 212
    //   407: aload_3
    //   408: invokevirtual booleanValue : ()Z
    //   411: ifeq -> 437
    //   414: new com/chartboost/sdk/impl/p
    //   417: dup
    //   418: ldc 60.0
    //   420: fconst_0
    //   421: fload #5
    //   423: fconst_2
    //   424: fdiv
    //   425: fload #4
    //   427: fconst_2
    //   428: fdiv
    //   429: iconst_0
    //   430: invokespecial <init> : (FFFFZ)V
    //   433: astore_0
    //   434: goto -> 212
    //   437: new com/chartboost/sdk/impl/p
    //   440: dup
    //   441: fconst_0
    //   442: ldc 60.0
    //   444: fneg
    //   445: fload #5
    //   447: fconst_2
    //   448: fdiv
    //   449: fload #4
    //   451: fconst_2
    //   452: fdiv
    //   453: iconst_0
    //   454: invokespecial <init> : (FFFFZ)V
    //   457: astore_0
    //   458: goto -> 212
    //   461: aload_3
    //   462: invokevirtual booleanValue : ()Z
    //   465: ifeq -> 491
    //   468: new com/chartboost/sdk/impl/p
    //   471: dup
    //   472: ldc 60.0
    //   474: fconst_0
    //   475: fload #5
    //   477: fconst_2
    //   478: fdiv
    //   479: fload #4
    //   481: fconst_2
    //   482: fdiv
    //   483: iconst_1
    //   484: invokespecial <init> : (FFFFZ)V
    //   487: astore_0
    //   488: goto -> 212
    //   491: new com/chartboost/sdk/impl/p
    //   494: dup
    //   495: fconst_0
    //   496: ldc 60.0
    //   498: fneg
    //   499: fload #5
    //   501: fconst_2
    //   502: fdiv
    //   503: fload #4
    //   505: fconst_2
    //   506: fdiv
    //   507: iconst_1
    //   508: invokespecial <init> : (FFFFZ)V
    //   511: astore_0
    //   512: goto -> 212
    //   515: new com/chartboost/sdk/impl/p
    //   518: dup
    //   519: fconst_0
    //   520: ldc 60.0
    //   522: fload #5
    //   524: fconst_2
    //   525: fdiv
    //   526: fload #4
    //   528: fconst_2
    //   529: fdiv
    //   530: iconst_0
    //   531: invokespecial <init> : (FFFFZ)V
    //   534: astore_0
    //   535: goto -> 212
    //   538: new android/view/animation/ScaleAnimation
    //   541: dup
    //   542: fconst_1
    //   543: ldc 0.4
    //   545: fconst_1
    //   546: ldc 0.4
    //   548: invokespecial <init> : (FFFF)V
    //   551: astore_0
    //   552: goto -> 251
    //   555: aload_3
    //   556: invokevirtual booleanValue : ()Z
    //   559: ifeq -> 582
    //   562: new android/view/animation/TranslateAnimation
    //   565: dup
    //   566: fload #5
    //   568: fconst_0
    //   569: fload #4
    //   571: fload #6
    //   573: fmul
    //   574: fconst_0
    //   575: invokespecial <init> : (FFFF)V
    //   578: astore_0
    //   579: goto -> 332
    //   582: new android/view/animation/TranslateAnimation
    //   585: dup
    //   586: fconst_0
    //   587: fload #5
    //   589: fneg
    //   590: ldc 0.4
    //   592: fmul
    //   593: fconst_0
    //   594: fload #4
    //   596: fload #6
    //   598: fmul
    //   599: invokespecial <init> : (FFFF)V
    //   602: astore_0
    //   603: goto -> 332
    //   606: aload_3
    //   607: invokevirtual booleanValue : ()Z
    //   610: ifeq -> 633
    //   613: new android/view/animation/TranslateAnimation
    //   616: dup
    //   617: fload #5
    //   619: fload #6
    //   621: fmul
    //   622: fconst_0
    //   623: fload #4
    //   625: fconst_0
    //   626: invokespecial <init> : (FFFF)V
    //   629: astore_0
    //   630: goto -> 332
    //   633: new android/view/animation/TranslateAnimation
    //   636: dup
    //   637: fconst_0
    //   638: fload #5
    //   640: fload #6
    //   642: fmul
    //   643: fconst_0
    //   644: fload #4
    //   646: fneg
    //   647: ldc 0.4
    //   649: fmul
    //   650: invokespecial <init> : (FFFF)V
    //   653: astore_0
    //   654: goto -> 332
    //   657: aload_3
    //   658: invokevirtual booleanValue : ()Z
    //   661: ifeq -> 688
    //   664: new android/view/animation/TranslateAnimation
    //   667: dup
    //   668: fload #5
    //   670: fneg
    //   671: ldc 0.4
    //   673: fmul
    //   674: fconst_0
    //   675: fload #4
    //   677: fload #6
    //   679: fmul
    //   680: fconst_0
    //   681: invokespecial <init> : (FFFF)V
    //   684: astore_0
    //   685: goto -> 332
    //   688: new android/view/animation/TranslateAnimation
    //   691: dup
    //   692: fconst_0
    //   693: fload #5
    //   695: fconst_0
    //   696: fload #4
    //   698: fload #6
    //   700: fmul
    //   701: invokespecial <init> : (FFFF)V
    //   704: astore_0
    //   705: goto -> 332
    //   708: new android/view/animation/TranslateAnimation
    //   711: dup
    //   712: fconst_0
    //   713: fload #5
    //   715: fload #6
    //   717: fmul
    //   718: fconst_0
    //   719: fload #4
    //   721: invokespecial <init> : (FFFF)V
    //   724: astore_0
    //   725: goto -> 332
    //   728: invokestatic a : ()[I
    //   731: aload #11
    //   733: invokevirtual ordinal : ()I
    //   736: iaload
    //   737: tableswitch default -> 764, 2 -> 933, 3 -> 987, 4 -> 1041
    //   764: aload_3
    //   765: invokevirtual booleanValue : ()Z
    //   768: ifeq -> 1095
    //   771: new com/chartboost/sdk/impl/p
    //   774: dup
    //   775: ldc 60.0
    //   777: fneg
    //   778: fconst_0
    //   779: fload #5
    //   781: fconst_2
    //   782: fdiv
    //   783: fload #4
    //   785: fconst_2
    //   786: fdiv
    //   787: iconst_1
    //   788: invokespecial <init> : (FFFFZ)V
    //   791: astore_0
    //   792: aload_0
    //   793: ldc2_w 600
    //   796: invokevirtual setDuration : (J)V
    //   799: aload_0
    //   800: iconst_1
    //   801: invokevirtual setFillAfter : (Z)V
    //   804: aload #10
    //   806: aload_0
    //   807: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   810: aload_3
    //   811: invokevirtual booleanValue : ()Z
    //   814: ifeq -> 1118
    //   817: new android/view/animation/ScaleAnimation
    //   820: dup
    //   821: ldc 0.4
    //   823: fconst_1
    //   824: ldc 0.4
    //   826: fconst_1
    //   827: invokespecial <init> : (FFFF)V
    //   830: astore_0
    //   831: aload_0
    //   832: ldc2_w 600
    //   835: invokevirtual setDuration : (J)V
    //   838: aload_0
    //   839: iconst_1
    //   840: invokevirtual setFillAfter : (Z)V
    //   843: aload #10
    //   845: aload_0
    //   846: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   849: invokestatic a : ()[I
    //   852: aload #11
    //   854: invokevirtual ordinal : ()I
    //   857: iaload
    //   858: tableswitch default -> 884, 2 -> 1135, 3 -> 1186, 4 -> 1237
    //   884: aload_3
    //   885: invokevirtual booleanValue : ()Z
    //   888: ifeq -> 1288
    //   891: new android/view/animation/TranslateAnimation
    //   894: dup
    //   895: fload #5
    //   897: fneg
    //   898: ldc 0.4
    //   900: fmul
    //   901: fconst_0
    //   902: fload #4
    //   904: fload #6
    //   906: fmul
    //   907: fconst_0
    //   908: invokespecial <init> : (FFFF)V
    //   911: astore_0
    //   912: aload_0
    //   913: ldc2_w 600
    //   916: invokevirtual setDuration : (J)V
    //   919: aload_0
    //   920: iconst_1
    //   921: invokevirtual setFillAfter : (Z)V
    //   924: aload #10
    //   926: aload_0
    //   927: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   930: goto -> 124
    //   933: aload_3
    //   934: invokevirtual booleanValue : ()Z
    //   937: ifeq -> 963
    //   940: new com/chartboost/sdk/impl/p
    //   943: dup
    //   944: ldc 60.0
    //   946: fconst_0
    //   947: fload #5
    //   949: fconst_2
    //   950: fdiv
    //   951: fload #4
    //   953: fconst_2
    //   954: fdiv
    //   955: iconst_0
    //   956: invokespecial <init> : (FFFFZ)V
    //   959: astore_0
    //   960: goto -> 792
    //   963: new com/chartboost/sdk/impl/p
    //   966: dup
    //   967: fconst_0
    //   968: ldc 60.0
    //   970: fneg
    //   971: fload #5
    //   973: fconst_2
    //   974: fdiv
    //   975: fload #4
    //   977: fconst_2
    //   978: fdiv
    //   979: iconst_0
    //   980: invokespecial <init> : (FFFFZ)V
    //   983: astore_0
    //   984: goto -> 792
    //   987: aload_3
    //   988: invokevirtual booleanValue : ()Z
    //   991: ifeq -> 1017
    //   994: new com/chartboost/sdk/impl/p
    //   997: dup
    //   998: ldc 60.0
    //   1000: fconst_0
    //   1001: fload #5
    //   1003: fconst_2
    //   1004: fdiv
    //   1005: fload #4
    //   1007: fconst_2
    //   1008: fdiv
    //   1009: iconst_1
    //   1010: invokespecial <init> : (FFFFZ)V
    //   1013: astore_0
    //   1014: goto -> 792
    //   1017: new com/chartboost/sdk/impl/p
    //   1020: dup
    //   1021: fconst_0
    //   1022: ldc 60.0
    //   1024: fneg
    //   1025: fload #5
    //   1027: fconst_2
    //   1028: fdiv
    //   1029: fload #4
    //   1031: fconst_2
    //   1032: fdiv
    //   1033: iconst_1
    //   1034: invokespecial <init> : (FFFFZ)V
    //   1037: astore_0
    //   1038: goto -> 792
    //   1041: aload_3
    //   1042: invokevirtual booleanValue : ()Z
    //   1045: ifeq -> 1072
    //   1048: new com/chartboost/sdk/impl/p
    //   1051: dup
    //   1052: ldc 60.0
    //   1054: fneg
    //   1055: fconst_0
    //   1056: fload #5
    //   1058: fconst_2
    //   1059: fdiv
    //   1060: fload #4
    //   1062: fconst_2
    //   1063: fdiv
    //   1064: iconst_0
    //   1065: invokespecial <init> : (FFFFZ)V
    //   1068: astore_0
    //   1069: goto -> 792
    //   1072: new com/chartboost/sdk/impl/p
    //   1075: dup
    //   1076: fconst_0
    //   1077: ldc 60.0
    //   1079: fload #5
    //   1081: fconst_2
    //   1082: fdiv
    //   1083: fload #4
    //   1085: fconst_2
    //   1086: fdiv
    //   1087: iconst_0
    //   1088: invokespecial <init> : (FFFFZ)V
    //   1091: astore_0
    //   1092: goto -> 792
    //   1095: new com/chartboost/sdk/impl/p
    //   1098: dup
    //   1099: fconst_0
    //   1100: ldc 60.0
    //   1102: fload #5
    //   1104: fconst_2
    //   1105: fdiv
    //   1106: fload #4
    //   1108: fconst_2
    //   1109: fdiv
    //   1110: iconst_1
    //   1111: invokespecial <init> : (FFFFZ)V
    //   1114: astore_0
    //   1115: goto -> 792
    //   1118: new android/view/animation/ScaleAnimation
    //   1121: dup
    //   1122: fconst_1
    //   1123: ldc 0.4
    //   1125: fconst_1
    //   1126: ldc 0.4
    //   1128: invokespecial <init> : (FFFF)V
    //   1131: astore_0
    //   1132: goto -> 831
    //   1135: aload_3
    //   1136: invokevirtual booleanValue : ()Z
    //   1139: ifeq -> 1166
    //   1142: new android/view/animation/TranslateAnimation
    //   1145: dup
    //   1146: fload #5
    //   1148: fload #6
    //   1150: fmul
    //   1151: fconst_0
    //   1152: fload #4
    //   1154: fneg
    //   1155: ldc 0.4
    //   1157: fmul
    //   1158: fconst_0
    //   1159: invokespecial <init> : (FFFF)V
    //   1162: astore_0
    //   1163: goto -> 912
    //   1166: new android/view/animation/TranslateAnimation
    //   1169: dup
    //   1170: fconst_0
    //   1171: fload #5
    //   1173: fload #6
    //   1175: fmul
    //   1176: fconst_0
    //   1177: fload #4
    //   1179: invokespecial <init> : (FFFF)V
    //   1182: astore_0
    //   1183: goto -> 912
    //   1186: aload_3
    //   1187: invokevirtual booleanValue : ()Z
    //   1190: ifeq -> 1213
    //   1193: new android/view/animation/TranslateAnimation
    //   1196: dup
    //   1197: fload #5
    //   1199: fconst_0
    //   1200: fload #4
    //   1202: fload #6
    //   1204: fmul
    //   1205: fconst_0
    //   1206: invokespecial <init> : (FFFF)V
    //   1209: astore_0
    //   1210: goto -> 912
    //   1213: new android/view/animation/TranslateAnimation
    //   1216: dup
    //   1217: fconst_0
    //   1218: fload #5
    //   1220: fneg
    //   1221: ldc 0.4
    //   1223: fmul
    //   1224: fconst_0
    //   1225: fload #4
    //   1227: fload #6
    //   1229: fmul
    //   1230: invokespecial <init> : (FFFF)V
    //   1233: astore_0
    //   1234: goto -> 912
    //   1237: aload_3
    //   1238: invokevirtual booleanValue : ()Z
    //   1241: ifeq -> 1264
    //   1244: new android/view/animation/TranslateAnimation
    //   1247: dup
    //   1248: fload #5
    //   1250: fload #6
    //   1252: fmul
    //   1253: fconst_0
    //   1254: fload #4
    //   1256: fconst_0
    //   1257: invokespecial <init> : (FFFF)V
    //   1260: astore_0
    //   1261: goto -> 912
    //   1264: new android/view/animation/TranslateAnimation
    //   1267: dup
    //   1268: fconst_0
    //   1269: fload #5
    //   1271: fload #6
    //   1273: fmul
    //   1274: fconst_0
    //   1275: fload #4
    //   1277: fneg
    //   1278: ldc 0.4
    //   1280: fmul
    //   1281: invokespecial <init> : (FFFF)V
    //   1284: astore_0
    //   1285: goto -> 912
    //   1288: new android/view/animation/TranslateAnimation
    //   1291: dup
    //   1292: fconst_0
    //   1293: fload #5
    //   1295: fconst_0
    //   1296: fload #4
    //   1298: fload #6
    //   1300: fmul
    //   1301: invokespecial <init> : (FFFF)V
    //   1304: astore_0
    //   1305: goto -> 912
    //   1308: invokestatic a : ()[I
    //   1311: aload #11
    //   1313: invokevirtual ordinal : ()I
    //   1316: iaload
    //   1317: tableswitch default -> 1348, 1 -> 1397, 2 -> 1445, 3 -> 1494, 4 -> 1551
    //   1348: fconst_0
    //   1349: fstore #7
    //   1351: fconst_0
    //   1352: fstore #4
    //   1354: fconst_0
    //   1355: fstore #5
    //   1357: fconst_0
    //   1358: fstore #6
    //   1360: new android/view/animation/TranslateAnimation
    //   1363: dup
    //   1364: fload #6
    //   1366: fload #5
    //   1368: fload #4
    //   1370: fload #7
    //   1372: invokespecial <init> : (FFFF)V
    //   1375: astore_0
    //   1376: aload_0
    //   1377: ldc2_w 600
    //   1380: invokevirtual setDuration : (J)V
    //   1383: aload_0
    //   1384: iconst_1
    //   1385: invokevirtual setFillAfter : (Z)V
    //   1388: aload #10
    //   1390: aload_0
    //   1391: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   1394: goto -> 124
    //   1397: aload_3
    //   1398: invokevirtual booleanValue : ()Z
    //   1401: ifeq -> 1439
    //   1404: fload #4
    //   1406: fstore #5
    //   1408: fload #4
    //   1410: fstore #7
    //   1412: aload_3
    //   1413: invokevirtual booleanValue : ()Z
    //   1416: ifeq -> 1422
    //   1419: fconst_0
    //   1420: fstore #7
    //   1422: fconst_0
    //   1423: fstore #8
    //   1425: fconst_0
    //   1426: fstore #6
    //   1428: fload #5
    //   1430: fstore #4
    //   1432: fload #8
    //   1434: fstore #5
    //   1436: goto -> 1360
    //   1439: fconst_0
    //   1440: fstore #5
    //   1442: goto -> 1408
    //   1445: aload_3
    //   1446: invokevirtual booleanValue : ()Z
    //   1449: ifeq -> 1480
    //   1452: fload #5
    //   1454: fneg
    //   1455: fstore #4
    //   1457: aload_3
    //   1458: invokevirtual booleanValue : ()Z
    //   1461: ifeq -> 1486
    //   1464: fconst_0
    //   1465: fstore #5
    //   1467: fconst_0
    //   1468: fstore #7
    //   1470: fload #4
    //   1472: fstore #6
    //   1474: fconst_0
    //   1475: fstore #4
    //   1477: goto -> 1360
    //   1480: fconst_0
    //   1481: fstore #4
    //   1483: goto -> 1457
    //   1486: fload #5
    //   1488: fneg
    //   1489: fstore #5
    //   1491: goto -> 1467
    //   1494: aload_3
    //   1495: invokevirtual booleanValue : ()Z
    //   1498: ifeq -> 1537
    //   1501: fload #4
    //   1503: fneg
    //   1504: fstore #5
    //   1506: aload_3
    //   1507: invokevirtual booleanValue : ()Z
    //   1510: ifeq -> 1543
    //   1513: fconst_0
    //   1514: fstore #4
    //   1516: fload #4
    //   1518: fstore #7
    //   1520: fconst_0
    //   1521: fstore #6
    //   1523: fconst_0
    //   1524: fstore #8
    //   1526: fload #5
    //   1528: fstore #4
    //   1530: fload #8
    //   1532: fstore #5
    //   1534: goto -> 1360
    //   1537: fconst_0
    //   1538: fstore #5
    //   1540: goto -> 1506
    //   1543: fload #4
    //   1545: fneg
    //   1546: fstore #4
    //   1548: goto -> 1516
    //   1551: aload_3
    //   1552: invokevirtual booleanValue : ()Z
    //   1555: ifeq -> 1585
    //   1558: fload #5
    //   1560: fstore #4
    //   1562: aload_3
    //   1563: invokevirtual booleanValue : ()Z
    //   1566: ifeq -> 1572
    //   1569: fconst_0
    //   1570: fstore #5
    //   1572: fconst_0
    //   1573: fstore #7
    //   1575: fload #4
    //   1577: fstore #6
    //   1579: fconst_0
    //   1580: fstore #4
    //   1582: goto -> 1360
    //   1585: fconst_0
    //   1586: fstore #4
    //   1588: goto -> 1562
    //   1591: invokestatic a : ()[I
    //   1594: aload #11
    //   1596: invokevirtual ordinal : ()I
    //   1599: iaload
    //   1600: tableswitch default -> 1632, 1 -> 1681, 2 -> 1738, 3 -> 1782, 4 -> 1834
    //   1632: fconst_0
    //   1633: fstore #5
    //   1635: fconst_0
    //   1636: fstore #4
    //   1638: fconst_0
    //   1639: fstore #7
    //   1641: fconst_0
    //   1642: fstore #6
    //   1644: new android/view/animation/TranslateAnimation
    //   1647: dup
    //   1648: fload #6
    //   1650: fload #7
    //   1652: fload #4
    //   1654: fload #5
    //   1656: invokespecial <init> : (FFFF)V
    //   1659: astore_0
    //   1660: aload_0
    //   1661: ldc2_w 600
    //   1664: invokevirtual setDuration : (J)V
    //   1667: aload_0
    //   1668: iconst_1
    //   1669: invokevirtual setFillAfter : (Z)V
    //   1672: aload #10
    //   1674: aload_0
    //   1675: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   1678: goto -> 124
    //   1681: aload_3
    //   1682: invokevirtual booleanValue : ()Z
    //   1685: ifeq -> 1724
    //   1688: fload #4
    //   1690: fneg
    //   1691: fstore #5
    //   1693: aload_3
    //   1694: invokevirtual booleanValue : ()Z
    //   1697: ifeq -> 1730
    //   1700: fconst_0
    //   1701: fstore #6
    //   1703: fconst_0
    //   1704: fstore #7
    //   1706: fconst_0
    //   1707: fstore #8
    //   1709: fload #5
    //   1711: fstore #4
    //   1713: fload #6
    //   1715: fstore #5
    //   1717: fload #8
    //   1719: fstore #6
    //   1721: goto -> 1644
    //   1724: fconst_0
    //   1725: fstore #5
    //   1727: goto -> 1693
    //   1730: fload #4
    //   1732: fneg
    //   1733: fstore #6
    //   1735: goto -> 1703
    //   1738: aload_3
    //   1739: invokevirtual booleanValue : ()Z
    //   1742: ifeq -> 1776
    //   1745: fload #5
    //   1747: fstore #4
    //   1749: fload #5
    //   1751: fstore #7
    //   1753: aload_3
    //   1754: invokevirtual booleanValue : ()Z
    //   1757: ifeq -> 1763
    //   1760: fconst_0
    //   1761: fstore #7
    //   1763: fconst_0
    //   1764: fstore #5
    //   1766: fload #4
    //   1768: fstore #6
    //   1770: fconst_0
    //   1771: fstore #4
    //   1773: goto -> 1644
    //   1776: fconst_0
    //   1777: fstore #4
    //   1779: goto -> 1749
    //   1782: aload_3
    //   1783: invokevirtual booleanValue : ()Z
    //   1786: ifeq -> 1828
    //   1789: fload #4
    //   1791: fstore #5
    //   1793: aload_3
    //   1794: invokevirtual booleanValue : ()Z
    //   1797: ifeq -> 1803
    //   1800: fconst_0
    //   1801: fstore #4
    //   1803: fload #4
    //   1805: fstore #6
    //   1807: fconst_0
    //   1808: fstore #7
    //   1810: fconst_0
    //   1811: fstore #8
    //   1813: fload #5
    //   1815: fstore #4
    //   1817: fload #6
    //   1819: fstore #5
    //   1821: fload #8
    //   1823: fstore #6
    //   1825: goto -> 1644
    //   1828: fconst_0
    //   1829: fstore #5
    //   1831: goto -> 1793
    //   1834: aload_3
    //   1835: invokevirtual booleanValue : ()Z
    //   1838: ifeq -> 1873
    //   1841: fload #5
    //   1843: fneg
    //   1844: fstore #4
    //   1846: aload_3
    //   1847: invokevirtual booleanValue : ()Z
    //   1850: ifeq -> 1879
    //   1853: fconst_0
    //   1854: fstore #5
    //   1856: fload #5
    //   1858: fstore #7
    //   1860: fconst_0
    //   1861: fstore #5
    //   1863: fload #4
    //   1865: fstore #6
    //   1867: fconst_0
    //   1868: fstore #4
    //   1870: goto -> 1644
    //   1873: fconst_0
    //   1874: fstore #4
    //   1876: goto -> 1846
    //   1879: fload #5
    //   1881: fneg
    //   1882: fstore #5
    //   1884: goto -> 1856
    //   1887: aload_3
    //   1888: invokevirtual booleanValue : ()Z
    //   1891: ifeq -> 2072
    //   1894: new android/view/animation/ScaleAnimation
    //   1897: dup
    //   1898: ldc 0.6
    //   1900: ldc 1.1
    //   1902: ldc 0.6
    //   1904: ldc 1.1
    //   1906: iconst_1
    //   1907: ldc 0.5
    //   1909: iconst_1
    //   1910: ldc 0.5
    //   1912: invokespecial <init> : (FFFFIFIF)V
    //   1915: astore_0
    //   1916: aload_0
    //   1917: ldc2_w 600
    //   1920: l2f
    //   1921: ldc 0.6
    //   1923: fmul
    //   1924: invokestatic round : (F)I
    //   1927: i2l
    //   1928: invokevirtual setDuration : (J)V
    //   1931: aload_0
    //   1932: lconst_0
    //   1933: invokevirtual setStartOffset : (J)V
    //   1936: aload_0
    //   1937: iconst_1
    //   1938: invokevirtual setFillAfter : (Z)V
    //   1941: aload #10
    //   1943: aload_0
    //   1944: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   1947: new android/view/animation/ScaleAnimation
    //   1950: dup
    //   1951: fconst_1
    //   1952: ldc 0.81818175
    //   1954: fconst_1
    //   1955: ldc 0.81818175
    //   1957: iconst_1
    //   1958: ldc 0.5
    //   1960: iconst_1
    //   1961: ldc 0.5
    //   1963: invokespecial <init> : (FFFFIFIF)V
    //   1966: astore_0
    //   1967: aload_0
    //   1968: ldc2_w 600
    //   1971: l2f
    //   1972: ldc 0.19999999
    //   1974: fmul
    //   1975: invokestatic round : (F)I
    //   1978: i2l
    //   1979: invokevirtual setDuration : (J)V
    //   1982: aload_0
    //   1983: ldc2_w 600
    //   1986: l2f
    //   1987: ldc 0.6
    //   1989: fmul
    //   1990: invokestatic round : (F)I
    //   1993: i2l
    //   1994: invokevirtual setStartOffset : (J)V
    //   1997: aload_0
    //   1998: iconst_1
    //   1999: invokevirtual setFillAfter : (Z)V
    //   2002: aload #10
    //   2004: aload_0
    //   2005: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   2008: new android/view/animation/ScaleAnimation
    //   2011: dup
    //   2012: fconst_1
    //   2013: ldc 1.1111112
    //   2015: fconst_1
    //   2016: ldc 1.1111112
    //   2018: iconst_1
    //   2019: ldc 0.5
    //   2021: iconst_1
    //   2022: ldc 0.5
    //   2024: invokespecial <init> : (FFFFIFIF)V
    //   2027: astore_0
    //   2028: aload_0
    //   2029: ldc2_w 600
    //   2032: l2f
    //   2033: ldc 0.099999964
    //   2035: fmul
    //   2036: invokestatic round : (F)I
    //   2039: i2l
    //   2040: invokevirtual setDuration : (J)V
    //   2043: aload_0
    //   2044: ldc2_w 600
    //   2047: l2f
    //   2048: ldc 0.8
    //   2050: fmul
    //   2051: invokestatic round : (F)I
    //   2054: i2l
    //   2055: invokevirtual setStartOffset : (J)V
    //   2058: aload_0
    //   2059: iconst_1
    //   2060: invokevirtual setFillAfter : (Z)V
    //   2063: aload #10
    //   2065: aload_0
    //   2066: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   2069: goto -> 124
    //   2072: new android/view/animation/ScaleAnimation
    //   2075: dup
    //   2076: fconst_1
    //   2077: fconst_0
    //   2078: fconst_1
    //   2079: fconst_0
    //   2080: iconst_1
    //   2081: ldc 0.5
    //   2083: iconst_1
    //   2084: ldc 0.5
    //   2086: invokespecial <init> : (FFFFIFIF)V
    //   2089: astore_0
    //   2090: aload_0
    //   2091: ldc2_w 600
    //   2094: invokevirtual setDuration : (J)V
    //   2097: aload_0
    //   2098: lconst_0
    //   2099: invokevirtual setStartOffset : (J)V
    //   2102: aload_0
    //   2103: iconst_1
    //   2104: invokevirtual setFillAfter : (Z)V
    //   2107: aload #10
    //   2109: aload_0
    //   2110: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   2113: goto -> 124
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */