package com.google.ads.util;

public class c$c extends c$a {
  static final boolean g;
  
  private static final byte[] h = new byte[] { 
      65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
      75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
      85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
      101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
      111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
      121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
      56, 57, 43, 47 };
  
  private static final byte[] i = new byte[] { 
      65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
      75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
      85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
      101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
      111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
      121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
      56, 57, 45, 95 };
  
  public int c;
  
  public final boolean d;
  
  public final boolean e;
  
  public final boolean f;
  
  private final byte[] j;
  
  private int k;
  
  private final byte[] l;
  
  public c$c(int paramInt, byte[] paramArrayOfbyte) {
    boolean bool1;
    this.a = paramArrayOfbyte;
    if ((paramInt & 0x1) == 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.d = bool1;
    if ((paramInt & 0x2) == 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.e = bool1;
    if ((paramInt & 0x4) != 0) {
      bool1 = bool2;
    } else {
      bool1 = false;
    } 
    this.f = bool1;
    if ((paramInt & 0x8) == 0) {
      paramArrayOfbyte = h;
    } else {
      paramArrayOfbyte = i;
    } 
    this.l = paramArrayOfbyte;
    this.j = new byte[2];
    this.c = 0;
    if (this.e) {
      paramInt = 19;
    } else {
      paramInt = -1;
    } 
    this.k = paramInt;
  }
  
  public boolean a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: getfield l : [B
    //   4: astore #11
    //   6: aload_0
    //   7: getfield a : [B
    //   10: astore #12
    //   12: iconst_0
    //   13: istore #5
    //   15: aload_0
    //   16: getfield k : I
    //   19: istore #8
    //   21: iload_3
    //   22: iload_2
    //   23: iadd
    //   24: istore #10
    //   26: iconst_m1
    //   27: istore #7
    //   29: aload_0
    //   30: getfield c : I
    //   33: tableswitch default -> 60, 0 -> 349, 1 -> 354, 2 -> 415
    //   60: iload_2
    //   61: istore_3
    //   62: iload #8
    //   64: istore_2
    //   65: iload_3
    //   66: istore #6
    //   68: iload #7
    //   70: iconst_m1
    //   71: if_icmpeq -> 1199
    //   74: aload #12
    //   76: iconst_0
    //   77: aload #11
    //   79: iload #7
    //   81: bipush #18
    //   83: ishr
    //   84: bipush #63
    //   86: iand
    //   87: baload
    //   88: bastore
    //   89: aload #12
    //   91: iconst_1
    //   92: aload #11
    //   94: iload #7
    //   96: bipush #12
    //   98: ishr
    //   99: bipush #63
    //   101: iand
    //   102: baload
    //   103: bastore
    //   104: aload #12
    //   106: iconst_2
    //   107: aload #11
    //   109: iload #7
    //   111: bipush #6
    //   113: ishr
    //   114: bipush #63
    //   116: iand
    //   117: baload
    //   118: bastore
    //   119: iconst_4
    //   120: istore #5
    //   122: aload #12
    //   124: iconst_3
    //   125: aload #11
    //   127: iload #7
    //   129: bipush #63
    //   131: iand
    //   132: baload
    //   133: bastore
    //   134: iload #8
    //   136: iconst_1
    //   137: isub
    //   138: istore #7
    //   140: iload #7
    //   142: istore_2
    //   143: iload_3
    //   144: istore #6
    //   146: iload #7
    //   148: ifne -> 1199
    //   151: aload_0
    //   152: getfield f : Z
    //   155: ifeq -> 1215
    //   158: iconst_5
    //   159: istore_2
    //   160: aload #12
    //   162: iconst_4
    //   163: bipush #13
    //   165: bastore
    //   166: aload #12
    //   168: iload_2
    //   169: bipush #10
    //   171: bastore
    //   172: bipush #19
    //   174: istore #6
    //   176: iinc #2, 1
    //   179: iload_3
    //   180: iconst_3
    //   181: iadd
    //   182: iload #10
    //   184: if_icmpgt -> 480
    //   187: aload_1
    //   188: iload_3
    //   189: baload
    //   190: sipush #255
    //   193: iand
    //   194: bipush #16
    //   196: ishl
    //   197: aload_1
    //   198: iload_3
    //   199: iconst_1
    //   200: iadd
    //   201: baload
    //   202: sipush #255
    //   205: iand
    //   206: bipush #8
    //   208: ishl
    //   209: ior
    //   210: aload_1
    //   211: iload_3
    //   212: iconst_2
    //   213: iadd
    //   214: baload
    //   215: sipush #255
    //   218: iand
    //   219: ior
    //   220: istore #5
    //   222: aload #12
    //   224: iload_2
    //   225: aload #11
    //   227: iload #5
    //   229: bipush #18
    //   231: ishr
    //   232: bipush #63
    //   234: iand
    //   235: baload
    //   236: bastore
    //   237: aload #12
    //   239: iload_2
    //   240: iconst_1
    //   241: iadd
    //   242: aload #11
    //   244: iload #5
    //   246: bipush #12
    //   248: ishr
    //   249: bipush #63
    //   251: iand
    //   252: baload
    //   253: bastore
    //   254: aload #12
    //   256: iload_2
    //   257: iconst_2
    //   258: iadd
    //   259: aload #11
    //   261: iload #5
    //   263: bipush #6
    //   265: ishr
    //   266: bipush #63
    //   268: iand
    //   269: baload
    //   270: bastore
    //   271: aload #12
    //   273: iload_2
    //   274: iconst_3
    //   275: iadd
    //   276: aload #11
    //   278: iload #5
    //   280: bipush #63
    //   282: iand
    //   283: baload
    //   284: bastore
    //   285: iinc #3, 3
    //   288: iload_2
    //   289: iconst_4
    //   290: iadd
    //   291: istore #7
    //   293: iload #6
    //   295: iconst_1
    //   296: isub
    //   297: istore #8
    //   299: iload #8
    //   301: istore_2
    //   302: iload #7
    //   304: istore #5
    //   306: iload_3
    //   307: istore #6
    //   309: iload #8
    //   311: ifne -> 1199
    //   314: aload_0
    //   315: getfield f : Z
    //   318: ifeq -> 1193
    //   321: iload #7
    //   323: iconst_1
    //   324: iadd
    //   325: istore_2
    //   326: aload #12
    //   328: iload #7
    //   330: bipush #13
    //   332: bastore
    //   333: aload #12
    //   335: iload_2
    //   336: bipush #10
    //   338: bastore
    //   339: bipush #19
    //   341: istore #6
    //   343: iinc #2, 1
    //   346: goto -> 179
    //   349: iload_2
    //   350: istore_3
    //   351: goto -> 62
    //   354: iload_2
    //   355: iconst_2
    //   356: iadd
    //   357: iload #10
    //   359: if_icmpgt -> 60
    //   362: aload_0
    //   363: getfield j : [B
    //   366: iconst_0
    //   367: baload
    //   368: istore #6
    //   370: iload_2
    //   371: iconst_1
    //   372: iadd
    //   373: istore_3
    //   374: iload #6
    //   376: sipush #255
    //   379: iand
    //   380: bipush #16
    //   382: ishl
    //   383: aload_1
    //   384: iload_2
    //   385: baload
    //   386: sipush #255
    //   389: iand
    //   390: bipush #8
    //   392: ishl
    //   393: ior
    //   394: aload_1
    //   395: iload_3
    //   396: baload
    //   397: sipush #255
    //   400: iand
    //   401: ior
    //   402: istore #7
    //   404: aload_0
    //   405: iconst_0
    //   406: putfield c : I
    //   409: iinc #3, 1
    //   412: goto -> 62
    //   415: iload_2
    //   416: iconst_1
    //   417: iadd
    //   418: iload #10
    //   420: if_icmpgt -> 60
    //   423: aload_0
    //   424: getfield j : [B
    //   427: iconst_0
    //   428: baload
    //   429: istore #7
    //   431: aload_0
    //   432: getfield j : [B
    //   435: iconst_1
    //   436: baload
    //   437: istore #6
    //   439: iload_2
    //   440: iconst_1
    //   441: iadd
    //   442: istore_3
    //   443: iload #7
    //   445: sipush #255
    //   448: iand
    //   449: bipush #16
    //   451: ishl
    //   452: iload #6
    //   454: sipush #255
    //   457: iand
    //   458: bipush #8
    //   460: ishl
    //   461: ior
    //   462: aload_1
    //   463: iload_2
    //   464: baload
    //   465: sipush #255
    //   468: iand
    //   469: ior
    //   470: istore #7
    //   472: aload_0
    //   473: iconst_0
    //   474: putfield c : I
    //   477: goto -> 62
    //   480: iload #4
    //   482: ifeq -> 1059
    //   485: iload_3
    //   486: aload_0
    //   487: getfield c : I
    //   490: isub
    //   491: iload #10
    //   493: iconst_1
    //   494: isub
    //   495: if_icmpne -> 692
    //   498: aload_0
    //   499: getfield c : I
    //   502: ifle -> 678
    //   505: aload_0
    //   506: getfield j : [B
    //   509: astore_1
    //   510: iconst_1
    //   511: istore #5
    //   513: aload_1
    //   514: iconst_0
    //   515: baload
    //   516: istore #7
    //   518: iload #7
    //   520: sipush #255
    //   523: iand
    //   524: iconst_4
    //   525: ishl
    //   526: istore #7
    //   528: aload_0
    //   529: aload_0
    //   530: getfield c : I
    //   533: iload #5
    //   535: isub
    //   536: putfield c : I
    //   539: iload_2
    //   540: iconst_1
    //   541: iadd
    //   542: istore #8
    //   544: aload #12
    //   546: iload_2
    //   547: aload #11
    //   549: iload #7
    //   551: bipush #6
    //   553: ishr
    //   554: bipush #63
    //   556: iand
    //   557: baload
    //   558: bastore
    //   559: iload #8
    //   561: iconst_1
    //   562: iadd
    //   563: istore #5
    //   565: aload #12
    //   567: iload #8
    //   569: aload #11
    //   571: iload #7
    //   573: bipush #63
    //   575: iand
    //   576: baload
    //   577: bastore
    //   578: iload #5
    //   580: istore_2
    //   581: aload_0
    //   582: getfield d : Z
    //   585: ifeq -> 613
    //   588: iload #5
    //   590: iconst_1
    //   591: iadd
    //   592: istore #7
    //   594: aload #12
    //   596: iload #5
    //   598: bipush #61
    //   600: bastore
    //   601: iload #7
    //   603: iconst_1
    //   604: iadd
    //   605: istore_2
    //   606: aload #12
    //   608: iload #7
    //   610: bipush #61
    //   612: bastore
    //   613: iload_2
    //   614: istore #5
    //   616: aload_0
    //   617: getfield e : Z
    //   620: ifeq -> 654
    //   623: iload_2
    //   624: istore #5
    //   626: aload_0
    //   627: getfield f : Z
    //   630: ifeq -> 644
    //   633: aload #12
    //   635: iload_2
    //   636: bipush #13
    //   638: bastore
    //   639: iload_2
    //   640: iconst_1
    //   641: iadd
    //   642: istore #5
    //   644: aload #12
    //   646: iload #5
    //   648: bipush #10
    //   650: bastore
    //   651: iinc #5, 1
    //   654: iload_3
    //   655: istore #7
    //   657: getstatic com/google/ads/util/c$c.g : Z
    //   660: ifne -> 1030
    //   663: aload_0
    //   664: getfield c : I
    //   667: ifeq -> 1030
    //   670: new java/lang/AssertionError
    //   673: dup
    //   674: invokespecial <init> : ()V
    //   677: athrow
    //   678: aload_1
    //   679: iload_3
    //   680: baload
    //   681: istore #7
    //   683: iinc #3, 1
    //   686: iconst_0
    //   687: istore #5
    //   689: goto -> 518
    //   692: iload_3
    //   693: aload_0
    //   694: getfield c : I
    //   697: isub
    //   698: iload #10
    //   700: iconst_2
    //   701: isub
    //   702: if_icmpne -> 956
    //   705: aload_0
    //   706: getfield c : I
    //   709: iconst_1
    //   710: if_icmple -> 920
    //   713: aload_0
    //   714: getfield j : [B
    //   717: astore #13
    //   719: iconst_1
    //   720: istore #8
    //   722: aload #13
    //   724: iconst_0
    //   725: baload
    //   726: istore #7
    //   728: iload_3
    //   729: istore #5
    //   731: iload #8
    //   733: istore_3
    //   734: aload_0
    //   735: getfield c : I
    //   738: ifle -> 935
    //   741: aload_0
    //   742: getfield j : [B
    //   745: iload_3
    //   746: baload
    //   747: istore #8
    //   749: iload_3
    //   750: iconst_1
    //   751: iadd
    //   752: istore #9
    //   754: iload #5
    //   756: istore_3
    //   757: iload #9
    //   759: istore #5
    //   761: iload #8
    //   763: sipush #255
    //   766: iand
    //   767: iconst_2
    //   768: ishl
    //   769: iload #7
    //   771: sipush #255
    //   774: iand
    //   775: bipush #10
    //   777: ishl
    //   778: ior
    //   779: istore #7
    //   781: aload_0
    //   782: aload_0
    //   783: getfield c : I
    //   786: iload #5
    //   788: isub
    //   789: putfield c : I
    //   792: iload_2
    //   793: iconst_1
    //   794: iadd
    //   795: istore #8
    //   797: aload #12
    //   799: iload_2
    //   800: aload #11
    //   802: iload #7
    //   804: bipush #12
    //   806: ishr
    //   807: bipush #63
    //   809: iand
    //   810: baload
    //   811: bastore
    //   812: iload #8
    //   814: iconst_1
    //   815: iadd
    //   816: istore #5
    //   818: aload #12
    //   820: iload #8
    //   822: aload #11
    //   824: iload #7
    //   826: bipush #6
    //   828: ishr
    //   829: bipush #63
    //   831: iand
    //   832: baload
    //   833: bastore
    //   834: iload #5
    //   836: iconst_1
    //   837: iadd
    //   838: istore_2
    //   839: aload #12
    //   841: iload #5
    //   843: aload #11
    //   845: iload #7
    //   847: bipush #63
    //   849: iand
    //   850: baload
    //   851: bastore
    //   852: aload_0
    //   853: getfield d : Z
    //   856: ifeq -> 1190
    //   859: iload_2
    //   860: iconst_1
    //   861: iadd
    //   862: istore #5
    //   864: aload #12
    //   866: iload_2
    //   867: bipush #61
    //   869: bastore
    //   870: iload #5
    //   872: istore_2
    //   873: iload_2
    //   874: istore #5
    //   876: aload_0
    //   877: getfield e : Z
    //   880: ifeq -> 914
    //   883: iload_2
    //   884: istore #5
    //   886: aload_0
    //   887: getfield f : Z
    //   890: ifeq -> 904
    //   893: aload #12
    //   895: iload_2
    //   896: bipush #13
    //   898: bastore
    //   899: iload_2
    //   900: iconst_1
    //   901: iadd
    //   902: istore #5
    //   904: aload #12
    //   906: iload #5
    //   908: bipush #10
    //   910: bastore
    //   911: iinc #5, 1
    //   914: iload_3
    //   915: istore #7
    //   917: goto -> 657
    //   920: aload_1
    //   921: iload_3
    //   922: baload
    //   923: istore #7
    //   925: iload_3
    //   926: iconst_1
    //   927: iadd
    //   928: istore #5
    //   930: iconst_0
    //   931: istore_3
    //   932: goto -> 734
    //   935: aload_1
    //   936: iload #5
    //   938: baload
    //   939: istore #8
    //   941: iload #5
    //   943: iconst_1
    //   944: iadd
    //   945: istore #9
    //   947: iload_3
    //   948: istore #5
    //   950: iload #9
    //   952: istore_3
    //   953: goto -> 761
    //   956: iload_3
    //   957: istore #7
    //   959: iload_2
    //   960: istore #5
    //   962: aload_0
    //   963: getfield e : Z
    //   966: ifeq -> 657
    //   969: iload_3
    //   970: istore #7
    //   972: iload_2
    //   973: istore #5
    //   975: iload_2
    //   976: ifle -> 657
    //   979: iload_3
    //   980: istore #7
    //   982: iload_2
    //   983: istore #5
    //   985: iload #6
    //   987: bipush #19
    //   989: if_icmpeq -> 657
    //   992: aload_0
    //   993: getfield f : Z
    //   996: ifeq -> 1187
    //   999: iload_2
    //   1000: iconst_1
    //   1001: iadd
    //   1002: istore #5
    //   1004: aload #12
    //   1006: iload_2
    //   1007: bipush #13
    //   1009: bastore
    //   1010: iload #5
    //   1012: istore_2
    //   1013: iload_2
    //   1014: iconst_1
    //   1015: iadd
    //   1016: istore #5
    //   1018: aload #12
    //   1020: iload_2
    //   1021: bipush #10
    //   1023: bastore
    //   1024: iload_3
    //   1025: istore #7
    //   1027: goto -> 657
    //   1030: iload #5
    //   1032: istore #8
    //   1034: getstatic com/google/ads/util/c$c.g : Z
    //   1037: ifne -> 1098
    //   1040: iload #5
    //   1042: istore #8
    //   1044: iload #7
    //   1046: iload #10
    //   1048: if_icmpeq -> 1098
    //   1051: new java/lang/AssertionError
    //   1054: dup
    //   1055: invokespecial <init> : ()V
    //   1058: athrow
    //   1059: iload_3
    //   1060: iload #10
    //   1062: iconst_1
    //   1063: isub
    //   1064: if_icmpne -> 1112
    //   1067: aload_0
    //   1068: getfield j : [B
    //   1071: astore #11
    //   1073: aload_0
    //   1074: getfield c : I
    //   1077: istore #5
    //   1079: aload_0
    //   1080: iload #5
    //   1082: iconst_1
    //   1083: iadd
    //   1084: putfield c : I
    //   1087: aload #11
    //   1089: iload #5
    //   1091: aload_1
    //   1092: iload_3
    //   1093: baload
    //   1094: bastore
    //   1095: iload_2
    //   1096: istore #8
    //   1098: aload_0
    //   1099: iload #8
    //   1101: putfield b : I
    //   1104: aload_0
    //   1105: iload #6
    //   1107: putfield k : I
    //   1110: iconst_1
    //   1111: ireturn
    //   1112: iload_2
    //   1113: istore #8
    //   1115: iload_3
    //   1116: iload #10
    //   1118: iconst_2
    //   1119: isub
    //   1120: if_icmpne -> 1098
    //   1123: aload_0
    //   1124: getfield j : [B
    //   1127: astore #11
    //   1129: aload_0
    //   1130: getfield c : I
    //   1133: istore #5
    //   1135: aload_0
    //   1136: iload #5
    //   1138: iconst_1
    //   1139: iadd
    //   1140: putfield c : I
    //   1143: aload #11
    //   1145: iload #5
    //   1147: aload_1
    //   1148: iload_3
    //   1149: baload
    //   1150: bastore
    //   1151: aload_0
    //   1152: getfield j : [B
    //   1155: astore #11
    //   1157: aload_0
    //   1158: getfield c : I
    //   1161: istore #5
    //   1163: aload_0
    //   1164: iload #5
    //   1166: iconst_1
    //   1167: iadd
    //   1168: putfield c : I
    //   1171: aload #11
    //   1173: iload #5
    //   1175: aload_1
    //   1176: iload_3
    //   1177: iconst_1
    //   1178: iadd
    //   1179: baload
    //   1180: bastore
    //   1181: iload_2
    //   1182: istore #8
    //   1184: goto -> 1098
    //   1187: goto -> 1013
    //   1190: goto -> 873
    //   1193: iload #7
    //   1195: istore_2
    //   1196: goto -> 333
    //   1199: iload_2
    //   1200: istore #7
    //   1202: iload #5
    //   1204: istore_2
    //   1205: iload #6
    //   1207: istore_3
    //   1208: iload #7
    //   1210: istore #6
    //   1212: goto -> 179
    //   1215: iconst_4
    //   1216: istore_2
    //   1217: goto -> 166
  }
  
  static {
    boolean bool;
    if (!c.class.desiredAssertionStatus()) {
      bool = true;
    } else {
      bool = false;
    } 
    g = bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\c$c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */