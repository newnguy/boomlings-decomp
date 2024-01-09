/*    */ package com.google.ads.util;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
/*    */ import java.nio.IntBuffer;
/*    */ 
/*    */ public class c$b extends c$a {
/*    */   private static final int[] c = $d2j$hex$b1f12dc7$decode_I("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff3e000000ffffffffffffffffffffffff3f0000003400000035000000360000003700000038000000390000003a0000003b0000003c0000003d000000fffffffffffffffffffffffffeffffffffffffffffffffffffffffff000000000100000002000000030000000400000005000000060000000700000008000000090000000a0000000b0000000c0000000d0000000e0000000f00000010000000110000001200000013000000140000001500000016000000170000001800000019000000ffffffffffffffffffffffffffffffffffffffffffffffff1a0000001b0000001c0000001d0000001e0000001f000000200000002100000022000000230000002400000025000000260000002700000028000000290000002a0000002b0000002c0000002d0000002e0000002f00000030000000310000003200000033000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
/*    */   private static final int[] d = $d2j$hex$b1f12dc7$decode_I("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff3e000000ffffffffffffffff3400000035000000360000003700000038000000390000003a0000003b0000003c0000003d000000fffffffffffffffffffffffffeffffffffffffffffffffffffffffff000000000100000002000000030000000400000005000000060000000700000008000000090000000a0000000b0000000c0000000d0000000e0000000f00000010000000110000001200000013000000140000001500000016000000170000001800000019000000ffffffffffffffffffffffffffffffff3f000000ffffffff1a0000001b0000001c0000001d0000001e0000001f000000200000002100000022000000230000002400000025000000260000002700000028000000290000002a0000002b0000002c0000002d0000002e0000002f00000030000000310000003200000033000000ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
/*    */   private int e;
/*    */   
/* 12 */   private static long[] $d2j$hex$b1f12dc7$decode_J(String src) { byte[] d = $d2j$hex$b1f12dc7$decode_B(src);
/* 13 */     ByteBuffer b = ByteBuffer.wrap(d);
/* 14 */     b.order(ByteOrder.LITTLE_ENDIAN);
/* 15 */     LongBuffer s = b.asLongBuffer();
/* 16 */     long[] data = new long[d.length / 8];
/* 17 */     s.get(data);
/* 18 */     return data; }
/*    */   private int f;
/*    */   private final int[] g; public c$b(int paramInt, byte[] paramArrayOfbyte) { int[] arrayOfInt; this.a = paramArrayOfbyte; if ((paramInt & 0x8) == 0) { arrayOfInt = c; }
/*    */     else { arrayOfInt = d; }
/*    */      this.g = arrayOfInt; this.e = 0; this.f = 0; } public boolean a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield e : I
/*    */     //   4: bipush #6
/*    */     //   6: if_icmpne -> 15
/*    */     //   9: iconst_0
/*    */     //   10: istore #4
/*    */     //   12: iload #4
/*    */     //   14: ireturn
/*    */     //   15: iload_3
/*    */     //   16: iload_2
/*    */     //   17: iadd
/*    */     //   18: istore #11
/*    */     //   20: aload_0
/*    */     //   21: getfield e : I
/*    */     //   24: istore #6
/*    */     //   26: aload_0
/*    */     //   27: getfield f : I
/*    */     //   30: istore_3
/*    */     //   31: iconst_0
/*    */     //   32: istore #5
/*    */     //   34: aload_0
/*    */     //   35: getfield a : [B
/*    */     //   38: astore #12
/*    */     //   40: aload_0
/*    */     //   41: getfield g : [I
/*    */     //   44: astore #13
/*    */     //   46: iload_2
/*    */     //   47: iload #11
/*    */     //   49: if_icmpge -> 762
/*    */     //   52: iload #5
/*    */     //   54: istore #7
/*    */     //   56: iload_3
/*    */     //   57: istore #8
/*    */     //   59: iload_2
/*    */     //   60: istore #10
/*    */     //   62: iload #6
/*    */     //   64: ifne -> 240
/*    */     //   67: iload_2
/*    */     //   68: istore #9
/*    */     //   70: iload_3
/*    */     //   71: istore_2
/*    */     //   72: iload #9
/*    */     //   74: iconst_4
/*    */     //   75: iadd
/*    */     //   76: iload #11
/*    */     //   78: if_icmpgt -> 192
/*    */     //   81: aload #13
/*    */     //   83: aload_1
/*    */     //   84: iload #9
/*    */     //   86: baload
/*    */     //   87: sipush #255
/*    */     //   90: iand
/*    */     //   91: iaload
/*    */     //   92: bipush #18
/*    */     //   94: ishl
/*    */     //   95: aload #13
/*    */     //   97: aload_1
/*    */     //   98: iload #9
/*    */     //   100: iconst_1
/*    */     //   101: iadd
/*    */     //   102: baload
/*    */     //   103: sipush #255
/*    */     //   106: iand
/*    */     //   107: iaload
/*    */     //   108: bipush #12
/*    */     //   110: ishl
/*    */     //   111: ior
/*    */     //   112: aload #13
/*    */     //   114: aload_1
/*    */     //   115: iload #9
/*    */     //   117: iconst_2
/*    */     //   118: iadd
/*    */     //   119: baload
/*    */     //   120: sipush #255
/*    */     //   123: iand
/*    */     //   124: iaload
/*    */     //   125: bipush #6
/*    */     //   127: ishl
/*    */     //   128: ior
/*    */     //   129: aload #13
/*    */     //   131: aload_1
/*    */     //   132: iload #9
/*    */     //   134: iconst_3
/*    */     //   135: iadd
/*    */     //   136: baload
/*    */     //   137: sipush #255
/*    */     //   140: iand
/*    */     //   141: iaload
/*    */     //   142: ior
/*    */     //   143: istore_3
/*    */     //   144: iload_3
/*    */     //   145: istore_2
/*    */     //   146: iload_3
/*    */     //   147: iflt -> 192
/*    */     //   150: aload #12
/*    */     //   152: iload #5
/*    */     //   154: iconst_2
/*    */     //   155: iadd
/*    */     //   156: iload_3
/*    */     //   157: i2b
/*    */     //   158: bastore
/*    */     //   159: aload #12
/*    */     //   161: iload #5
/*    */     //   163: iconst_1
/*    */     //   164: iadd
/*    */     //   165: iload_3
/*    */     //   166: bipush #8
/*    */     //   168: ishr
/*    */     //   169: i2b
/*    */     //   170: bastore
/*    */     //   171: aload #12
/*    */     //   173: iload #5
/*    */     //   175: iload_3
/*    */     //   176: bipush #16
/*    */     //   178: ishr
/*    */     //   179: i2b
/*    */     //   180: bastore
/*    */     //   181: iinc #5, 3
/*    */     //   184: iinc #9, 4
/*    */     //   187: iload_3
/*    */     //   188: istore_2
/*    */     //   189: goto -> 72
/*    */     //   192: iload #5
/*    */     //   194: istore #7
/*    */     //   196: iload_2
/*    */     //   197: istore #8
/*    */     //   199: iload #9
/*    */     //   201: istore #10
/*    */     //   203: iload #9
/*    */     //   205: iload #11
/*    */     //   207: if_icmplt -> 240
/*    */     //   210: iload_2
/*    */     //   211: istore_3
/*    */     //   212: iload #4
/*    */     //   214: ifne -> 627
/*    */     //   217: aload_0
/*    */     //   218: iload #6
/*    */     //   220: putfield e : I
/*    */     //   223: aload_0
/*    */     //   224: iload_3
/*    */     //   225: putfield f : I
/*    */     //   228: aload_0
/*    */     //   229: iload #5
/*    */     //   231: putfield b : I
/*    */     //   234: iconst_1
/*    */     //   235: istore #4
/*    */     //   237: goto -> 12
/*    */     //   240: aload #13
/*    */     //   242: aload_1
/*    */     //   243: iload #10
/*    */     //   245: baload
/*    */     //   246: sipush #255
/*    */     //   249: iand
/*    */     //   250: iaload
/*    */     //   251: istore_3
/*    */     //   252: iload #6
/*    */     //   254: tableswitch default -> 292, 0 -> 313, 1 -> 346, 2 -> 387, 3 -> 458, 4 -> 572, 5 -> 610
/*    */     //   292: iload #6
/*    */     //   294: istore_2
/*    */     //   295: iload #8
/*    */     //   297: istore_3
/*    */     //   298: iload #7
/*    */     //   300: istore #5
/*    */     //   302: iload_2
/*    */     //   303: istore #6
/*    */     //   305: iload #10
/*    */     //   307: iconst_1
/*    */     //   308: iadd
/*    */     //   309: istore_2
/*    */     //   310: goto -> 46
/*    */     //   313: iload_3
/*    */     //   314: iflt -> 329
/*    */     //   317: iload #6
/*    */     //   319: iconst_1
/*    */     //   320: iadd
/*    */     //   321: istore_2
/*    */     //   322: iload #7
/*    */     //   324: istore #5
/*    */     //   326: goto -> 302
/*    */     //   329: iload_3
/*    */     //   330: iconst_m1
/*    */     //   331: if_icmpeq -> 292
/*    */     //   334: aload_0
/*    */     //   335: bipush #6
/*    */     //   337: putfield e : I
/*    */     //   340: iconst_0
/*    */     //   341: istore #4
/*    */     //   343: goto -> 12
/*    */     //   346: iload_3
/*    */     //   347: iflt -> 370
/*    */     //   350: iload #8
/*    */     //   352: bipush #6
/*    */     //   354: ishl
/*    */     //   355: iload_3
/*    */     //   356: ior
/*    */     //   357: istore_3
/*    */     //   358: iload #6
/*    */     //   360: iconst_1
/*    */     //   361: iadd
/*    */     //   362: istore_2
/*    */     //   363: iload #7
/*    */     //   365: istore #5
/*    */     //   367: goto -> 302
/*    */     //   370: iload_3
/*    */     //   371: iconst_m1
/*    */     //   372: if_icmpeq -> 292
/*    */     //   375: aload_0
/*    */     //   376: bipush #6
/*    */     //   378: putfield e : I
/*    */     //   381: iconst_0
/*    */     //   382: istore #4
/*    */     //   384: goto -> 12
/*    */     //   387: iload_3
/*    */     //   388: iflt -> 411
/*    */     //   391: iload #8
/*    */     //   393: bipush #6
/*    */     //   395: ishl
/*    */     //   396: iload_3
/*    */     //   397: ior
/*    */     //   398: istore_3
/*    */     //   399: iload #6
/*    */     //   401: iconst_1
/*    */     //   402: iadd
/*    */     //   403: istore_2
/*    */     //   404: iload #7
/*    */     //   406: istore #5
/*    */     //   408: goto -> 302
/*    */     //   411: iload_3
/*    */     //   412: bipush #-2
/*    */     //   414: if_icmpne -> 441
/*    */     //   417: aload #12
/*    */     //   419: iload #7
/*    */     //   421: iload #8
/*    */     //   423: iconst_4
/*    */     //   424: ishr
/*    */     //   425: i2b
/*    */     //   426: bastore
/*    */     //   427: iconst_4
/*    */     //   428: istore_2
/*    */     //   429: iload #7
/*    */     //   431: iconst_1
/*    */     //   432: iadd
/*    */     //   433: istore #5
/*    */     //   435: iload #8
/*    */     //   437: istore_3
/*    */     //   438: goto -> 302
/*    */     //   441: iload_3
/*    */     //   442: iconst_m1
/*    */     //   443: if_icmpeq -> 292
/*    */     //   446: aload_0
/*    */     //   447: bipush #6
/*    */     //   449: putfield e : I
/*    */     //   452: iconst_0
/*    */     //   453: istore #4
/*    */     //   455: goto -> 12
/*    */     //   458: iload_3
/*    */     //   459: iflt -> 512
/*    */     //   462: iload #8
/*    */     //   464: bipush #6
/*    */     //   466: ishl
/*    */     //   467: iload_3
/*    */     //   468: ior
/*    */     //   469: istore_3
/*    */     //   470: aload #12
/*    */     //   472: iload #7
/*    */     //   474: iconst_2
/*    */     //   475: iadd
/*    */     //   476: iload_3
/*    */     //   477: i2b
/*    */     //   478: bastore
/*    */     //   479: aload #12
/*    */     //   481: iload #7
/*    */     //   483: iconst_1
/*    */     //   484: iadd
/*    */     //   485: iload_3
/*    */     //   486: bipush #8
/*    */     //   488: ishr
/*    */     //   489: i2b
/*    */     //   490: bastore
/*    */     //   491: aload #12
/*    */     //   493: iload #7
/*    */     //   495: iload_3
/*    */     //   496: bipush #16
/*    */     //   498: ishr
/*    */     //   499: i2b
/*    */     //   500: bastore
/*    */     //   501: iload #7
/*    */     //   503: iconst_3
/*    */     //   504: iadd
/*    */     //   505: istore #5
/*    */     //   507: iconst_0
/*    */     //   508: istore_2
/*    */     //   509: goto -> 302
/*    */     //   512: iload_3
/*    */     //   513: bipush #-2
/*    */     //   515: if_icmpne -> 555
/*    */     //   518: aload #12
/*    */     //   520: iload #7
/*    */     //   522: iconst_1
/*    */     //   523: iadd
/*    */     //   524: iload #8
/*    */     //   526: iconst_2
/*    */     //   527: ishr
/*    */     //   528: i2b
/*    */     //   529: bastore
/*    */     //   530: aload #12
/*    */     //   532: iload #7
/*    */     //   534: iload #8
/*    */     //   536: bipush #10
/*    */     //   538: ishr
/*    */     //   539: i2b
/*    */     //   540: bastore
/*    */     //   541: iload #7
/*    */     //   543: iconst_2
/*    */     //   544: iadd
/*    */     //   545: istore #5
/*    */     //   547: iconst_5
/*    */     //   548: istore_2
/*    */     //   549: iload #8
/*    */     //   551: istore_3
/*    */     //   552: goto -> 302
/*    */     //   555: iload_3
/*    */     //   556: iconst_m1
/*    */     //   557: if_icmpeq -> 292
/*    */     //   560: aload_0
/*    */     //   561: bipush #6
/*    */     //   563: putfield e : I
/*    */     //   566: iconst_0
/*    */     //   567: istore #4
/*    */     //   569: goto -> 12
/*    */     //   572: iload_3
/*    */     //   573: bipush #-2
/*    */     //   575: if_icmpne -> 593
/*    */     //   578: iload #6
/*    */     //   580: iconst_1
/*    */     //   581: iadd
/*    */     //   582: istore_2
/*    */     //   583: iload #7
/*    */     //   585: istore #5
/*    */     //   587: iload #8
/*    */     //   589: istore_3
/*    */     //   590: goto -> 302
/*    */     //   593: iload_3
/*    */     //   594: iconst_m1
/*    */     //   595: if_icmpeq -> 292
/*    */     //   598: aload_0
/*    */     //   599: bipush #6
/*    */     //   601: putfield e : I
/*    */     //   604: iconst_0
/*    */     //   605: istore #4
/*    */     //   607: goto -> 12
/*    */     //   610: iload_3
/*    */     //   611: iconst_m1
/*    */     //   612: if_icmpeq -> 292
/*    */     //   615: aload_0
/*    */     //   616: bipush #6
/*    */     //   618: putfield e : I
/*    */     //   621: iconst_0
/*    */     //   622: istore #4
/*    */     //   624: goto -> 12
/*    */     //   627: iload #5
/*    */     //   629: istore_2
/*    */     //   630: iload #6
/*    */     //   632: tableswitch default -> 668, 0 -> 671, 1 -> 688, 2 -> 700, 3 -> 717, 4 -> 750
/*    */     //   668: iload #5
/*    */     //   670: istore_2
/*    */     //   671: aload_0
/*    */     //   672: iload #6
/*    */     //   674: putfield e : I
/*    */     //   677: aload_0
/*    */     //   678: iload_2
/*    */     //   679: putfield b : I
/*    */     //   682: iconst_1
/*    */     //   683: istore #4
/*    */     //   685: goto -> 12
/*    */     //   688: aload_0
/*    */     //   689: bipush #6
/*    */     //   691: putfield e : I
/*    */     //   694: iconst_0
/*    */     //   695: istore #4
/*    */     //   697: goto -> 12
/*    */     //   700: aload #12
/*    */     //   702: iload #5
/*    */     //   704: iload_3
/*    */     //   705: iconst_4
/*    */     //   706: ishr
/*    */     //   707: i2b
/*    */     //   708: bastore
/*    */     //   709: iload #5
/*    */     //   711: iconst_1
/*    */     //   712: iadd
/*    */     //   713: istore_2
/*    */     //   714: goto -> 671
/*    */     //   717: iload #5
/*    */     //   719: iconst_1
/*    */     //   720: iadd
/*    */     //   721: istore #7
/*    */     //   723: aload #12
/*    */     //   725: iload #5
/*    */     //   727: iload_3
/*    */     //   728: bipush #10
/*    */     //   730: ishr
/*    */     //   731: i2b
/*    */     //   732: bastore
/*    */     //   733: iload #7
/*    */     //   735: iconst_1
/*    */     //   736: iadd
/*    */     //   737: istore_2
/*    */     //   738: aload #12
/*    */     //   740: iload #7
/*    */     //   742: iload_3
/*    */     //   743: iconst_2
/*    */     //   744: ishr
/*    */     //   745: i2b
/*    */     //   746: bastore
/*    */     //   747: goto -> 671
/*    */     //   750: aload_0
/*    */     //   751: bipush #6
/*    */     //   753: putfield e : I
/*    */     //   756: iconst_0
/*    */     //   757: istore #4
/*    */     //   759: goto -> 12
/* 22 */     //   762: goto -> 212 } private static int[] $d2j$hex$b1f12dc7$decode_I(String src) { byte[] d = $d2j$hex$b1f12dc7$decode_B(src);
/* 23 */     ByteBuffer b = ByteBuffer.wrap(d);
/* 24 */     b.order(ByteOrder.LITTLE_ENDIAN);
/* 25 */     IntBuffer s = b.asIntBuffer();
/* 26 */     int[] data = new int[d.length / 4];
/* 27 */     s.get(data);
/* 28 */     return data; }
/*    */ 
/*    */   
/*    */   private static short[] $d2j$hex$b1f12dc7$decode_S(String src) {
/* 32 */     byte[] d = $d2j$hex$b1f12dc7$decode_B(src);
/* 33 */     ByteBuffer b = ByteBuffer.wrap(d);
/* 34 */     b.order(ByteOrder.LITTLE_ENDIAN);
/* 35 */     ShortBuffer s = b.asShortBuffer();
/* 36 */     short[] data = new short[d.length / 2];
/* 37 */     s.get(data);
/* 38 */     return data;
/*    */   }
/*    */   
/*    */   private static byte[] $d2j$hex$b1f12dc7$decode_B(String src) {
/* 42 */     char[] d = src.toCharArray();
/* 43 */     byte[] ret = new byte[src.length() / 2];
/* 44 */     for (int i = 0; i < ret.length; i++) {
/* 45 */       int hh, ll; char h = d[2 * i];
/* 46 */       char l = d[2 * i + 1];
/*    */       
/* 48 */       if (h >= '0' && h <= '9') {
/* 49 */         hh = h - 48;
/* 50 */       } else if (h >= 'a' && h <= 'f') {
/* 51 */         hh = h - 97 + 10;
/* 52 */       } else if (h >= 'A' && h <= 'F') {
/* 53 */         hh = h - 65 + 10;
/*    */       } else {
/* 55 */         throw new RuntimeException();
/*    */       } 
/*    */       
/* 58 */       if (l >= '0' && l <= '9') {
/* 59 */         ll = l - 48;
/* 60 */       } else if (l >= 'a' && l <= 'f') {
/* 61 */         ll = l - 97 + 10;
/* 62 */       } else if (l >= 'A' && l <= 'F') {
/* 63 */         ll = l - 65 + 10;
/*    */       } else {
/* 65 */         throw new RuntimeException();
/*    */       } 
/* 67 */       ret[i] = (byte)(hh << 4 | ll);
/*    */     } 
/* 69 */     return ret;
/*    */   }
/*    */ }


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\c$b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */