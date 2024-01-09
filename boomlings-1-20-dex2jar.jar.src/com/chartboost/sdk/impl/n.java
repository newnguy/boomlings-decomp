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
    //   8: astore #9
    //   10: aload #9
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
    //   43: astore #10
    //   45: aload #10
    //   47: ifnull -> 35
    //   50: aload #10
    //   52: invokevirtual getWidth : ()I
    //   55: i2f
    //   56: fstore #4
    //   58: aload #10
    //   60: invokevirtual getHeight : ()I
    //   63: i2f
    //   64: fstore #5
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
    //   90: tableswitch default -> 124, 2 -> 728, 3 -> 1875, 4 -> 148, 5 -> 1308, 6 -> 1599
    //   124: aload #9
    //   126: new com/chartboost/sdk/impl/n$2
    //   129: dup
    //   130: aload_2
    //   131: aload_1
    //   132: invokespecial <init> : (Lcom/chartboost/sdk/impl/n$a;Lcom/chartboost/sdk/impl/a;)V
    //   135: invokevirtual setAnimationListener : (Landroid/view/animation/Animation$AnimationListener;)V
    //   138: aload #10
    //   140: aload #9
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
    //   199: fload #4
    //   201: fconst_2
    //   202: fdiv
    //   203: fload #5
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
    //   224: aload #9
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
    //   263: aload #9
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
    //   315: fload #4
    //   317: fload #6
    //   319: fmul
    //   320: fconst_0
    //   321: fload #5
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
    //   344: aload #9
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
    //   368: fload #4
    //   370: fconst_2
    //   371: fdiv
    //   372: fload #5
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
    //   391: fload #4
    //   393: fconst_2
    //   394: fdiv
    //   395: fload #5
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
    //   421: fload #4
    //   423: fconst_2
    //   424: fdiv
    //   425: fload #5
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
    //   445: fload #4
    //   447: fconst_2
    //   448: fdiv
    //   449: fload #5
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
    //   475: fload #4
    //   477: fconst_2
    //   478: fdiv
    //   479: fload #5
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
    //   499: fload #4
    //   501: fconst_2
    //   502: fdiv
    //   503: fload #5
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
    //   522: fload #4
    //   524: fconst_2
    //   525: fdiv
    //   526: fload #5
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
    //   566: fload #4
    //   568: fconst_0
    //   569: fload #5
    //   571: fload #6
    //   573: fmul
    //   574: fconst_0
    //   575: invokespecial <init> : (FFFF)V
    //   578: astore_0
    //   579: goto -> 332
    //   582: new android/view/animation/TranslateAnimation
    //   585: dup
    //   586: fconst_0
    //   587: fload #4
    //   589: fneg
    //   590: ldc 0.4
    //   592: fmul
    //   593: fconst_0
    //   594: fload #5
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
    //   617: fload #4
    //   619: fload #6
    //   621: fmul
    //   622: fconst_0
    //   623: fload #5
    //   625: fconst_0
    //   626: invokespecial <init> : (FFFF)V
    //   629: astore_0
    //   630: goto -> 332
    //   633: new android/view/animation/TranslateAnimation
    //   636: dup
    //   637: fconst_0
    //   638: fload #4
    //   640: fload #6
    //   642: fmul
    //   643: fconst_0
    //   644: fload #5
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
    //   668: fload #4
    //   670: fneg
    //   671: ldc 0.4
    //   673: fmul
    //   674: fconst_0
    //   675: fload #5
    //   677: fload #6
    //   679: fmul
    //   680: fconst_0
    //   681: invokespecial <init> : (FFFF)V
    //   684: astore_0
    //   685: goto -> 332
    //   688: new android/view/animation/TranslateAnimation
    //   691: dup
    //   692: fconst_0
    //   693: fload #4
    //   695: fconst_0
    //   696: fload #5
    //   698: fload #6
    //   700: fmul
    //   701: invokespecial <init> : (FFFF)V
    //   704: astore_0
    //   705: goto -> 332
    //   708: new android/view/animation/TranslateAnimation
    //   711: dup
    //   712: fconst_0
    //   713: fload #4
    //   715: fload #6
    //   717: fmul
    //   718: fconst_0
    //   719: fload #5
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
    //   779: fload #4
    //   781: fconst_2
    //   782: fdiv
    //   783: fload #5
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
    //   804: aload #9
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
    //   843: aload #9
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
    //   895: fload #4
    //   897: fneg
    //   898: ldc 0.4
    //   900: fmul
    //   901: fconst_0
    //   902: fload #5
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
    //   924: aload #9
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
    //   947: fload #4
    //   949: fconst_2
    //   950: fdiv
    //   951: fload #5
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
    //   971: fload #4
    //   973: fconst_2
    //   974: fdiv
    //   975: fload #5
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
    //   1001: fload #4
    //   1003: fconst_2
    //   1004: fdiv
    //   1005: fload #5
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
    //   1025: fload #4
    //   1027: fconst_2
    //   1028: fdiv
    //   1029: fload #5
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
    //   1056: fload #4
    //   1058: fconst_2
    //   1059: fdiv
    //   1060: fload #5
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
    //   1079: fload #4
    //   1081: fconst_2
    //   1082: fdiv
    //   1083: fload #5
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
    //   1102: fload #4
    //   1104: fconst_2
    //   1105: fdiv
    //   1106: fload #5
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
    //   1146: fload #4
    //   1148: fload #6
    //   1150: fmul
    //   1151: fconst_0
    //   1152: fload #5
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
    //   1171: fload #4
    //   1173: fload #6
    //   1175: fmul
    //   1176: fconst_0
    //   1177: fload #5
    //   1179: invokespecial <init> : (FFFF)V
    //   1182: astore_0
    //   1183: goto -> 912
    //   1186: aload_3
    //   1187: invokevirtual booleanValue : ()Z
    //   1190: ifeq -> 1213
    //   1193: new android/view/animation/TranslateAnimation
    //   1196: dup
    //   1197: fload #4
    //   1199: fconst_0
    //   1200: fload #5
    //   1202: fload #6
    //   1204: fmul
    //   1205: fconst_0
    //   1206: invokespecial <init> : (FFFF)V
    //   1209: astore_0
    //   1210: goto -> 912
    //   1213: new android/view/animation/TranslateAnimation
    //   1216: dup
    //   1217: fconst_0
    //   1218: fload #4
    //   1220: fneg
    //   1221: ldc 0.4
    //   1223: fmul
    //   1224: fconst_0
    //   1225: fload #5
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
    //   1248: fload #4
    //   1250: fload #6
    //   1252: fmul
    //   1253: fconst_0
    //   1254: fload #5
    //   1256: fconst_0
    //   1257: invokespecial <init> : (FFFF)V
    //   1260: astore_0
    //   1261: goto -> 912
    //   1264: new android/view/animation/TranslateAnimation
    //   1267: dup
    //   1268: fconst_0
    //   1269: fload #4
    //   1271: fload #6
    //   1273: fmul
    //   1274: fconst_0
    //   1275: fload #5
    //   1277: fneg
    //   1278: ldc 0.4
    //   1280: fmul
    //   1281: invokespecial <init> : (FFFF)V
    //   1284: astore_0
    //   1285: goto -> 912
    //   1288: new android/view/animation/TranslateAnimation
    //   1291: dup
    //   1292: fconst_0
    //   1293: fload #4
    //   1295: fconst_0
    //   1296: fload #5
    //   1298: fload #6
    //   1300: fmul
    //   1301: invokespecial <init> : (FFFF)V
    //   1304: astore_0
    //   1305: goto -> 912
    //   1308: invokestatic a : ()[I
    //   1311: aload #11
    //   1313: invokevirtual ordinal : ()I
    //   1316: iaload
    //   1317: tableswitch default -> 1348, 1 -> 1397, 2 -> 1437, 3 -> 1498, 4 -> 1547
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
    //   1388: aload #9
    //   1390: aload_0
    //   1391: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   1394: goto -> 124
    //   1397: aload_3
    //   1398: invokevirtual booleanValue : ()Z
    //   1401: ifeq -> 1431
    //   1404: fload #5
    //   1406: fstore #4
    //   1408: fload #5
    //   1410: fstore #7
    //   1412: aload_3
    //   1413: invokevirtual booleanValue : ()Z
    //   1416: ifeq -> 1422
    //   1419: fconst_0
    //   1420: fstore #7
    //   1422: fconst_0
    //   1423: fstore #5
    //   1425: fconst_0
    //   1426: fstore #6
    //   1428: goto -> 1360
    //   1431: fconst_0
    //   1432: fstore #4
    //   1434: goto -> 1408
    //   1437: aload_3
    //   1438: invokevirtual booleanValue : ()Z
    //   1441: ifeq -> 1484
    //   1444: fload #4
    //   1446: fneg
    //   1447: fstore #5
    //   1449: aload_3
    //   1450: invokevirtual booleanValue : ()Z
    //   1453: ifeq -> 1490
    //   1456: fconst_0
    //   1457: fstore #6
    //   1459: fconst_0
    //   1460: fstore #8
    //   1462: fload #5
    //   1464: fstore #7
    //   1466: fconst_0
    //   1467: fstore #4
    //   1469: fload #6
    //   1471: fstore #5
    //   1473: fload #7
    //   1475: fstore #6
    //   1477: fload #8
    //   1479: fstore #7
    //   1481: goto -> 1360
    //   1484: fconst_0
    //   1485: fstore #5
    //   1487: goto -> 1449
    //   1490: fload #4
    //   1492: fneg
    //   1493: fstore #6
    //   1495: goto -> 1459
    //   1498: aload_3
    //   1499: invokevirtual booleanValue : ()Z
    //   1502: ifeq -> 1533
    //   1505: fload #5
    //   1507: fneg
    //   1508: fstore #4
    //   1510: aload_3
    //   1511: invokevirtual booleanValue : ()Z
    //   1514: ifeq -> 1539
    //   1517: fconst_0
    //   1518: fstore #5
    //   1520: fload #5
    //   1522: fstore #7
    //   1524: fconst_0
    //   1525: fstore #6
    //   1527: fconst_0
    //   1528: fstore #5
    //   1530: goto -> 1360
    //   1533: fconst_0
    //   1534: fstore #4
    //   1536: goto -> 1510
    //   1539: fload #5
    //   1541: fneg
    //   1542: fstore #5
    //   1544: goto -> 1520
    //   1547: aload_3
    //   1548: invokevirtual booleanValue : ()Z
    //   1551: ifeq -> 1593
    //   1554: fload #4
    //   1556: fstore #5
    //   1558: aload_3
    //   1559: invokevirtual booleanValue : ()Z
    //   1562: ifeq -> 1568
    //   1565: fconst_0
    //   1566: fstore #4
    //   1568: fconst_0
    //   1569: fstore #8
    //   1571: fload #4
    //   1573: fstore #7
    //   1575: fload #5
    //   1577: fstore #6
    //   1579: fconst_0
    //   1580: fstore #4
    //   1582: fload #7
    //   1584: fstore #5
    //   1586: fload #8
    //   1588: fstore #7
    //   1590: goto -> 1360
    //   1593: fconst_0
    //   1594: fstore #5
    //   1596: goto -> 1558
    //   1599: invokestatic a : ()[I
    //   1602: aload #11
    //   1604: invokevirtual ordinal : ()I
    //   1607: iaload
    //   1608: tableswitch default -> 1640, 1 -> 1689, 2 -> 1734, 3 -> 1782, 4 -> 1818
    //   1640: fconst_0
    //   1641: fstore #5
    //   1643: fconst_0
    //   1644: fstore #4
    //   1646: fconst_0
    //   1647: fstore #7
    //   1649: fconst_0
    //   1650: fstore #6
    //   1652: new android/view/animation/TranslateAnimation
    //   1655: dup
    //   1656: fload #6
    //   1658: fload #7
    //   1660: fload #4
    //   1662: fload #5
    //   1664: invokespecial <init> : (FFFF)V
    //   1667: astore_0
    //   1668: aload_0
    //   1669: ldc2_w 600
    //   1672: invokevirtual setDuration : (J)V
    //   1675: aload_0
    //   1676: iconst_1
    //   1677: invokevirtual setFillAfter : (Z)V
    //   1680: aload #9
    //   1682: aload_0
    //   1683: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   1686: goto -> 124
    //   1689: aload_3
    //   1690: invokevirtual booleanValue : ()Z
    //   1693: ifeq -> 1720
    //   1696: fload #5
    //   1698: fneg
    //   1699: fstore #4
    //   1701: aload_3
    //   1702: invokevirtual booleanValue : ()Z
    //   1705: ifeq -> 1726
    //   1708: fconst_0
    //   1709: fstore #5
    //   1711: fconst_0
    //   1712: fstore #7
    //   1714: fconst_0
    //   1715: fstore #6
    //   1717: goto -> 1652
    //   1720: fconst_0
    //   1721: fstore #4
    //   1723: goto -> 1701
    //   1726: fload #5
    //   1728: fneg
    //   1729: fstore #5
    //   1731: goto -> 1711
    //   1734: aload_3
    //   1735: invokevirtual booleanValue : ()Z
    //   1738: ifeq -> 1776
    //   1741: fload #4
    //   1743: fstore #5
    //   1745: fload #4
    //   1747: fstore #7
    //   1749: aload_3
    //   1750: invokevirtual booleanValue : ()Z
    //   1753: ifeq -> 1759
    //   1756: fconst_0
    //   1757: fstore #7
    //   1759: fconst_0
    //   1760: fstore #8
    //   1762: fload #5
    //   1764: fstore #6
    //   1766: fconst_0
    //   1767: fstore #4
    //   1769: fload #8
    //   1771: fstore #5
    //   1773: goto -> 1652
    //   1776: fconst_0
    //   1777: fstore #5
    //   1779: goto -> 1745
    //   1782: aload_3
    //   1783: invokevirtual booleanValue : ()Z
    //   1786: ifeq -> 1812
    //   1789: fload #5
    //   1791: fstore #4
    //   1793: aload_3
    //   1794: invokevirtual booleanValue : ()Z
    //   1797: ifeq -> 1803
    //   1800: fconst_0
    //   1801: fstore #5
    //   1803: fconst_0
    //   1804: fstore #7
    //   1806: fconst_0
    //   1807: fstore #6
    //   1809: goto -> 1652
    //   1812: fconst_0
    //   1813: fstore #4
    //   1815: goto -> 1793
    //   1818: aload_3
    //   1819: invokevirtual booleanValue : ()Z
    //   1822: ifeq -> 1861
    //   1825: fload #4
    //   1827: fneg
    //   1828: fstore #5
    //   1830: aload_3
    //   1831: invokevirtual booleanValue : ()Z
    //   1834: ifeq -> 1867
    //   1837: fconst_0
    //   1838: fstore #4
    //   1840: fload #4
    //   1842: fstore #7
    //   1844: fconst_0
    //   1845: fstore #8
    //   1847: fload #5
    //   1849: fstore #6
    //   1851: fconst_0
    //   1852: fstore #4
    //   1854: fload #8
    //   1856: fstore #5
    //   1858: goto -> 1652
    //   1861: fconst_0
    //   1862: fstore #5
    //   1864: goto -> 1830
    //   1867: fload #4
    //   1869: fneg
    //   1870: fstore #4
    //   1872: goto -> 1840
    //   1875: aload_3
    //   1876: invokevirtual booleanValue : ()Z
    //   1879: ifeq -> 2060
    //   1882: new android/view/animation/ScaleAnimation
    //   1885: dup
    //   1886: ldc 0.6
    //   1888: ldc 1.1
    //   1890: ldc 0.6
    //   1892: ldc 1.1
    //   1894: iconst_1
    //   1895: ldc 0.5
    //   1897: iconst_1
    //   1898: ldc 0.5
    //   1900: invokespecial <init> : (FFFFIFIF)V
    //   1903: astore_0
    //   1904: aload_0
    //   1905: ldc2_w 600
    //   1908: l2f
    //   1909: ldc 0.6
    //   1911: fmul
    //   1912: invokestatic round : (F)I
    //   1915: i2l
    //   1916: invokevirtual setDuration : (J)V
    //   1919: aload_0
    //   1920: lconst_0
    //   1921: invokevirtual setStartOffset : (J)V
    //   1924: aload_0
    //   1925: iconst_1
    //   1926: invokevirtual setFillAfter : (Z)V
    //   1929: aload #9
    //   1931: aload_0
    //   1932: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   1935: new android/view/animation/ScaleAnimation
    //   1938: dup
    //   1939: fconst_1
    //   1940: ldc 0.81818175
    //   1942: fconst_1
    //   1943: ldc 0.81818175
    //   1945: iconst_1
    //   1946: ldc 0.5
    //   1948: iconst_1
    //   1949: ldc 0.5
    //   1951: invokespecial <init> : (FFFFIFIF)V
    //   1954: astore_0
    //   1955: aload_0
    //   1956: ldc2_w 600
    //   1959: l2f
    //   1960: ldc 0.19999999
    //   1962: fmul
    //   1963: invokestatic round : (F)I
    //   1966: i2l
    //   1967: invokevirtual setDuration : (J)V
    //   1970: aload_0
    //   1971: ldc2_w 600
    //   1974: l2f
    //   1975: ldc 0.6
    //   1977: fmul
    //   1978: invokestatic round : (F)I
    //   1981: i2l
    //   1982: invokevirtual setStartOffset : (J)V
    //   1985: aload_0
    //   1986: iconst_1
    //   1987: invokevirtual setFillAfter : (Z)V
    //   1990: aload #9
    //   1992: aload_0
    //   1993: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   1996: new android/view/animation/ScaleAnimation
    //   1999: dup
    //   2000: fconst_1
    //   2001: ldc 1.1111112
    //   2003: fconst_1
    //   2004: ldc 1.1111112
    //   2006: iconst_1
    //   2007: ldc 0.5
    //   2009: iconst_1
    //   2010: ldc 0.5
    //   2012: invokespecial <init> : (FFFFIFIF)V
    //   2015: astore_0
    //   2016: aload_0
    //   2017: ldc2_w 600
    //   2020: l2f
    //   2021: ldc 0.099999964
    //   2023: fmul
    //   2024: invokestatic round : (F)I
    //   2027: i2l
    //   2028: invokevirtual setDuration : (J)V
    //   2031: aload_0
    //   2032: ldc2_w 600
    //   2035: l2f
    //   2036: ldc 0.8
    //   2038: fmul
    //   2039: invokestatic round : (F)I
    //   2042: i2l
    //   2043: invokevirtual setStartOffset : (J)V
    //   2046: aload_0
    //   2047: iconst_1
    //   2048: invokevirtual setFillAfter : (Z)V
    //   2051: aload #9
    //   2053: aload_0
    //   2054: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   2057: goto -> 124
    //   2060: new android/view/animation/ScaleAnimation
    //   2063: dup
    //   2064: fconst_1
    //   2065: fconst_0
    //   2066: fconst_1
    //   2067: fconst_0
    //   2068: iconst_1
    //   2069: ldc 0.5
    //   2071: iconst_1
    //   2072: ldc 0.5
    //   2074: invokespecial <init> : (FFFFIFIF)V
    //   2077: astore_0
    //   2078: aload_0
    //   2079: ldc2_w 600
    //   2082: invokevirtual setDuration : (J)V
    //   2085: aload_0
    //   2086: lconst_0
    //   2087: invokevirtual setStartOffset : (J)V
    //   2090: aload_0
    //   2091: iconst_1
    //   2092: invokevirtual setFillAfter : (Z)V
    //   2095: aload #9
    //   2097: aload_0
    //   2098: invokevirtual addAnimation : (Landroid/view/animation/Animation;)V
    //   2101: goto -> 124
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */