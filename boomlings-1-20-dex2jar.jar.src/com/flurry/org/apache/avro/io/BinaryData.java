package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericDatumReader;
import java.io.IOException;
import java.util.Iterator;

public class BinaryData {
  private static final ThreadLocal DECODERS = new BinaryData$1();
  
  private static final ThreadLocal HASH_DATA = new BinaryData$2();
  
  private static int compare(BinaryData$Decoders paramBinaryData$Decoders, Schema paramSchema) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic access$000 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   4: astore #27
    //   6: aload_0
    //   7: invokestatic access$100 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   10: astore #26
    //   12: getstatic com/flurry/org/apache/avro/io/BinaryData$3.$SwitchMap$org$apache$avro$Schema$Type : [I
    //   15: aload_1
    //   16: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   19: invokevirtual ordinal : ()I
    //   22: iaload
    //   23: tableswitch default -> 92, 1 -> 102, 2 -> 210, 3 -> 210, 4 -> 256, 5 -> 304, 6 -> 537, 7 -> 547, 8 -> 601, 9 -> 665, 10 -> 665, 11 -> 737, 12 -> 785, 13 -> 830, 14 -> 870
    //   92: new com/flurry/org/apache/avro/AvroRuntimeException
    //   95: dup
    //   96: ldc 'Unexpected schema to compare!'
    //   98: invokespecial <init> : (Ljava/lang/String;)V
    //   101: athrow
    //   102: aload_1
    //   103: invokevirtual getFields : ()Ljava/util/List;
    //   106: invokeinterface iterator : ()Ljava/util/Iterator;
    //   111: astore_1
    //   112: aload_1
    //   113: invokeinterface hasNext : ()Z
    //   118: ifeq -> 204
    //   121: aload_1
    //   122: invokeinterface next : ()Ljava/lang/Object;
    //   127: checkcast com/flurry/org/apache/avro/Schema$Field
    //   130: astore #28
    //   132: aload #28
    //   134: invokevirtual order : ()Lcom/flurry/org/apache/avro/Schema$Field$Order;
    //   137: getstatic com/flurry/org/apache/avro/Schema$Field$Order.IGNORE : Lcom/flurry/org/apache/avro/Schema$Field$Order;
    //   140: if_acmpne -> 166
    //   143: aload #28
    //   145: invokevirtual schema : ()Lcom/flurry/org/apache/avro/Schema;
    //   148: aload #27
    //   150: invokestatic skip : (Lcom/flurry/org/apache/avro/Schema;Lcom/flurry/org/apache/avro/io/Decoder;)V
    //   153: aload #28
    //   155: invokevirtual schema : ()Lcom/flurry/org/apache/avro/Schema;
    //   158: aload #26
    //   160: invokestatic skip : (Lcom/flurry/org/apache/avro/Schema;Lcom/flurry/org/apache/avro/io/Decoder;)V
    //   163: goto -> 112
    //   166: aload_0
    //   167: aload #28
    //   169: invokevirtual schema : ()Lcom/flurry/org/apache/avro/Schema;
    //   172: invokestatic compare : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;Lcom/flurry/org/apache/avro/Schema;)I
    //   175: istore #8
    //   177: iload #8
    //   179: ifeq -> 112
    //   182: aload #28
    //   184: invokevirtual order : ()Lcom/flurry/org/apache/avro/Schema$Field$Order;
    //   187: getstatic com/flurry/org/apache/avro/Schema$Field$Order.DESCENDING : Lcom/flurry/org/apache/avro/Schema$Field$Order;
    //   190: if_acmpeq -> 196
    //   193: iload #8
    //   195: ireturn
    //   196: iload #8
    //   198: ineg
    //   199: istore #8
    //   201: goto -> 193
    //   204: iconst_0
    //   205: istore #8
    //   207: goto -> 193
    //   210: aload #27
    //   212: invokevirtual readInt : ()I
    //   215: istore #8
    //   217: aload #26
    //   219: invokevirtual readInt : ()I
    //   222: istore #9
    //   224: iload #8
    //   226: iload #9
    //   228: if_icmpne -> 237
    //   231: iconst_0
    //   232: istore #8
    //   234: goto -> 193
    //   237: iload #8
    //   239: iload #9
    //   241: if_icmple -> 250
    //   244: iconst_1
    //   245: istore #8
    //   247: goto -> 193
    //   250: iconst_m1
    //   251: istore #8
    //   253: goto -> 193
    //   256: aload #27
    //   258: invokevirtual readLong : ()J
    //   261: lstore #13
    //   263: aload #26
    //   265: invokevirtual readLong : ()J
    //   268: lstore #11
    //   270: lload #13
    //   272: lload #11
    //   274: lcmp
    //   275: ifne -> 284
    //   278: iconst_0
    //   279: istore #8
    //   281: goto -> 193
    //   284: lload #13
    //   286: lload #11
    //   288: lcmp
    //   289: ifle -> 298
    //   292: iconst_1
    //   293: istore #8
    //   295: goto -> 193
    //   298: iconst_m1
    //   299: istore #8
    //   301: goto -> 193
    //   304: lconst_0
    //   305: lstore #11
    //   307: lconst_0
    //   308: lstore #19
    //   310: lconst_0
    //   311: lstore #17
    //   313: lconst_0
    //   314: lstore #13
    //   316: lconst_0
    //   317: lstore #15
    //   319: lload #19
    //   321: lconst_0
    //   322: lcmp
    //   323: ifne -> 879
    //   326: aload #27
    //   328: invokevirtual readLong : ()J
    //   331: lstore #21
    //   333: lload #21
    //   335: lstore #19
    //   337: lload #21
    //   339: lconst_0
    //   340: lcmp
    //   341: ifge -> 355
    //   344: lload #21
    //   346: lneg
    //   347: lstore #19
    //   349: aload #27
    //   351: invokevirtual readLong : ()J
    //   354: pop2
    //   355: lload #13
    //   357: lload #19
    //   359: ladd
    //   360: lstore #13
    //   362: lload #17
    //   364: lconst_0
    //   365: lcmp
    //   366: ifne -> 876
    //   369: aload #26
    //   371: invokevirtual readLong : ()J
    //   374: lstore #21
    //   376: lload #21
    //   378: lstore #17
    //   380: lload #21
    //   382: lconst_0
    //   383: lcmp
    //   384: ifge -> 398
    //   387: lload #21
    //   389: lneg
    //   390: lstore #17
    //   392: aload #26
    //   394: invokevirtual readLong : ()J
    //   397: pop2
    //   398: lload #15
    //   400: lload #17
    //   402: ladd
    //   403: lstore #15
    //   405: lload #19
    //   407: lconst_0
    //   408: lcmp
    //   409: ifeq -> 419
    //   412: lload #17
    //   414: lconst_0
    //   415: lcmp
    //   416: ifne -> 453
    //   419: lload #13
    //   421: lload #15
    //   423: lcmp
    //   424: ifne -> 433
    //   427: iconst_0
    //   428: istore #8
    //   430: goto -> 193
    //   433: lload #13
    //   435: lload #15
    //   437: lcmp
    //   438: ifle -> 447
    //   441: iconst_1
    //   442: istore #8
    //   444: goto -> 193
    //   447: iconst_m1
    //   448: istore #8
    //   450: goto -> 193
    //   453: lload #13
    //   455: lload #15
    //   457: invokestatic min : (JJ)J
    //   460: lstore #23
    //   462: lload #17
    //   464: lstore #21
    //   466: lload #19
    //   468: lstore #17
    //   470: lload #21
    //   472: lstore #19
    //   474: lload #11
    //   476: lload #23
    //   478: lcmp
    //   479: ifge -> 522
    //   482: aload_0
    //   483: aload_1
    //   484: invokevirtual getElementType : ()Lcom/flurry/org/apache/avro/Schema;
    //   487: invokestatic compare : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;Lcom/flurry/org/apache/avro/Schema;)I
    //   490: istore #9
    //   492: iload #9
    //   494: istore #8
    //   496: iload #9
    //   498: ifne -> 193
    //   501: lload #11
    //   503: lconst_1
    //   504: ladd
    //   505: lstore #11
    //   507: lload #17
    //   509: lconst_1
    //   510: lsub
    //   511: lstore #17
    //   513: lload #19
    //   515: lconst_1
    //   516: lsub
    //   517: lstore #19
    //   519: goto -> 474
    //   522: lload #19
    //   524: lstore #21
    //   526: lload #17
    //   528: lstore #19
    //   530: lload #21
    //   532: lstore #17
    //   534: goto -> 319
    //   537: new com/flurry/org/apache/avro/AvroRuntimeException
    //   540: dup
    //   541: ldc 'Can't compare maps!'
    //   543: invokespecial <init> : (Ljava/lang/String;)V
    //   546: athrow
    //   547: aload #27
    //   549: invokevirtual readInt : ()I
    //   552: istore #9
    //   554: aload #26
    //   556: invokevirtual readInt : ()I
    //   559: istore #8
    //   561: iload #9
    //   563: iload #8
    //   565: if_icmpne -> 591
    //   568: aload_0
    //   569: aload_1
    //   570: invokevirtual getTypes : ()Ljava/util/List;
    //   573: iload #9
    //   575: invokeinterface get : (I)Ljava/lang/Object;
    //   580: checkcast com/flurry/org/apache/avro/Schema
    //   583: invokestatic compare : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;Lcom/flurry/org/apache/avro/Schema;)I
    //   586: istore #8
    //   588: goto -> 193
    //   591: iload #9
    //   593: iload #8
    //   595: isub
    //   596: istore #8
    //   598: goto -> 193
    //   601: aload_1
    //   602: invokevirtual getFixedSize : ()I
    //   605: istore #9
    //   607: aload_0
    //   608: invokestatic access$200 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder$BufferAccessor;
    //   611: invokevirtual getBuf : ()[B
    //   614: aload_0
    //   615: invokestatic access$200 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder$BufferAccessor;
    //   618: invokevirtual getPos : ()I
    //   621: iload #9
    //   623: aload_0
    //   624: invokestatic access$300 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder$BufferAccessor;
    //   627: invokevirtual getBuf : ()[B
    //   630: aload_0
    //   631: invokestatic access$300 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder$BufferAccessor;
    //   634: invokevirtual getPos : ()I
    //   637: iload #9
    //   639: invokestatic compareBytes : ([BII[BII)I
    //   642: istore #8
    //   644: aload_0
    //   645: invokestatic access$000 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   648: iload #9
    //   650: invokevirtual skipFixed : (I)V
    //   653: aload_0
    //   654: invokestatic access$100 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   657: iload #9
    //   659: invokevirtual skipFixed : (I)V
    //   662: goto -> 193
    //   665: aload #27
    //   667: invokevirtual readInt : ()I
    //   670: istore #9
    //   672: aload #26
    //   674: invokevirtual readInt : ()I
    //   677: istore #10
    //   679: aload_0
    //   680: invokestatic access$200 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder$BufferAccessor;
    //   683: invokevirtual getBuf : ()[B
    //   686: aload_0
    //   687: invokestatic access$200 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder$BufferAccessor;
    //   690: invokevirtual getPos : ()I
    //   693: iload #9
    //   695: aload_0
    //   696: invokestatic access$300 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder$BufferAccessor;
    //   699: invokevirtual getBuf : ()[B
    //   702: aload_0
    //   703: invokestatic access$300 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder$BufferAccessor;
    //   706: invokevirtual getPos : ()I
    //   709: iload #10
    //   711: invokestatic compareBytes : ([BII[BII)I
    //   714: istore #8
    //   716: aload_0
    //   717: invokestatic access$000 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   720: iload #9
    //   722: invokevirtual skipFixed : (I)V
    //   725: aload_0
    //   726: invokestatic access$100 : (Lcom/flurry/org/apache/avro/io/BinaryData$Decoders;)Lcom/flurry/org/apache/avro/io/BinaryDecoder;
    //   729: iload #10
    //   731: invokevirtual skipFixed : (I)V
    //   734: goto -> 193
    //   737: aload #27
    //   739: invokevirtual readFloat : ()F
    //   742: fstore #6
    //   744: aload #26
    //   746: invokevirtual readFloat : ()F
    //   749: fstore #7
    //   751: fload #6
    //   753: fload #7
    //   755: fcmpl
    //   756: ifne -> 765
    //   759: iconst_0
    //   760: istore #8
    //   762: goto -> 193
    //   765: fload #6
    //   767: fload #7
    //   769: fcmpl
    //   770: ifle -> 779
    //   773: iconst_1
    //   774: istore #8
    //   776: goto -> 193
    //   779: iconst_m1
    //   780: istore #8
    //   782: goto -> 193
    //   785: aload #27
    //   787: invokevirtual readDouble : ()D
    //   790: dstore #4
    //   792: aload #26
    //   794: invokevirtual readDouble : ()D
    //   797: dstore_2
    //   798: dload #4
    //   800: dload_2
    //   801: dcmpl
    //   802: ifne -> 811
    //   805: iconst_0
    //   806: istore #8
    //   808: goto -> 193
    //   811: dload #4
    //   813: dload_2
    //   814: dcmpl
    //   815: ifle -> 824
    //   818: iconst_1
    //   819: istore #8
    //   821: goto -> 193
    //   824: iconst_m1
    //   825: istore #8
    //   827: goto -> 193
    //   830: aload #27
    //   832: invokevirtual readBoolean : ()Z
    //   835: istore #25
    //   837: iload #25
    //   839: aload #26
    //   841: invokevirtual readBoolean : ()Z
    //   844: if_icmpne -> 853
    //   847: iconst_0
    //   848: istore #8
    //   850: goto -> 193
    //   853: iload #25
    //   855: ifeq -> 864
    //   858: iconst_1
    //   859: istore #8
    //   861: goto -> 193
    //   864: iconst_m1
    //   865: istore #8
    //   867: goto -> 193
    //   870: iconst_0
    //   871: istore #8
    //   873: goto -> 193
    //   876: goto -> 405
    //   879: goto -> 362
  }
  
  public static int compare(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3, int paramInt4, Schema paramSchema) {
    BinaryData$Decoders binaryData$Decoders = DECODERS.get();
    binaryData$Decoders.set(paramArrayOfbyte1, paramInt1, paramInt2, paramArrayOfbyte2, paramInt3, paramInt4);
    try {
      return compare(binaryData$Decoders, paramSchema);
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  public static int compare(byte[] paramArrayOfbyte1, int paramInt1, byte[] paramArrayOfbyte2, int paramInt2, Schema paramSchema) {
    return compare(paramArrayOfbyte1, paramInt1, paramArrayOfbyte1.length - paramInt1, paramArrayOfbyte2, paramInt2, paramArrayOfbyte2.length - paramInt2, paramSchema);
  }
  
  public static int compareBytes(byte[] paramArrayOfbyte1, int paramInt1, int paramInt2, byte[] paramArrayOfbyte2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: iload #4
    //   2: istore #7
    //   4: iload_1
    //   5: istore #6
    //   7: iload #6
    //   9: iload_1
    //   10: iload_2
    //   11: iadd
    //   12: if_icmpge -> 69
    //   15: iload #7
    //   17: iload #4
    //   19: iload #5
    //   21: iadd
    //   22: if_icmpge -> 69
    //   25: aload_0
    //   26: iload #6
    //   28: baload
    //   29: sipush #255
    //   32: iand
    //   33: istore #9
    //   35: aload_3
    //   36: iload #7
    //   38: baload
    //   39: sipush #255
    //   42: iand
    //   43: istore #8
    //   45: iload #9
    //   47: iload #8
    //   49: if_icmpeq -> 60
    //   52: iload #9
    //   54: iload #8
    //   56: isub
    //   57: istore_1
    //   58: iload_1
    //   59: ireturn
    //   60: iinc #6, 1
    //   63: iinc #7, 1
    //   66: goto -> 7
    //   69: iload_2
    //   70: iload #5
    //   72: isub
    //   73: istore_1
    //   74: goto -> 58
  }
  
  public static int encodeBoolean(boolean paramBoolean, byte[] paramArrayOfbyte, int paramInt) {
    if (paramBoolean) {
      boolean bool1 = true;
      paramArrayOfbyte[paramInt] = bool1;
      return 1;
    } 
    boolean bool = false;
    paramArrayOfbyte[paramInt] = bool;
    return 1;
  }
  
  public static int encodeDouble(double paramDouble, byte[] paramArrayOfbyte, int paramInt) {
    long l = Double.doubleToRawLongBits(paramDouble);
    int i = (int)(l & 0xFFFFFFFFFFFFFFFFL);
    int j = (int)(l >>> 32L & 0xFFFFFFFFFFFFFFFFL);
    paramArrayOfbyte[paramInt] = (byte)(i & 0xFF);
    paramArrayOfbyte[paramInt + 4] = (byte)(j & 0xFF);
    paramArrayOfbyte[paramInt + 5] = (byte)(j >>> 8 & 0xFF);
    paramArrayOfbyte[paramInt + 1] = (byte)(i >>> 8 & 0xFF);
    paramArrayOfbyte[paramInt + 2] = (byte)(i >>> 16 & 0xFF);
    paramArrayOfbyte[paramInt + 6] = (byte)(j >>> 16 & 0xFF);
    paramArrayOfbyte[paramInt + 7] = (byte)(j >>> 24 & 0xFF);
    paramArrayOfbyte[paramInt + 3] = (byte)(i >>> 24 & 0xFF);
    return 8;
  }
  
  public static int encodeFloat(float paramFloat, byte[] paramArrayOfbyte, int paramInt) {
    int i = Float.floatToRawIntBits(paramFloat);
    paramArrayOfbyte[paramInt] = (byte)(i & 0xFF);
    paramArrayOfbyte[1 + paramInt] = (byte)(i >>> 8 & 0xFF);
    paramArrayOfbyte[paramInt + 2] = (byte)(i >>> 16 & 0xFF);
    paramArrayOfbyte[paramInt + 3] = (byte)(i >>> 24 & 0xFF);
    return 4;
  }
  
  public static int encodeInt(int paramInt1, byte[] paramArrayOfbyte, int paramInt2) {
    int i = paramInt1 << 1 ^ paramInt1 >> 31;
    if ((i & 0xFFFFFF80) != 0) {
      int j = paramInt2 + 1;
      paramArrayOfbyte[paramInt2] = (byte)((i | 0x80) & 0xFF);
      int k = i >>> 7;
      i = j;
      paramInt1 = k;
      if (k > 127) {
        paramInt1 = j + 1;
        paramArrayOfbyte[j] = (byte)((k | 0x80) & 0xFF);
        i = k >>> 7;
        if (i > 127) {
          j = paramInt1 + 1;
          paramArrayOfbyte[paramInt1] = (byte)((i | 0x80) & 0xFF);
          k = i >>> 7;
          i = j;
          paramInt1 = k;
          if (k > 127) {
            paramInt1 = j + 1;
            paramArrayOfbyte[j] = (byte)((k | 0x80) & 0xFF);
            i = k >>> 7;
            paramArrayOfbyte[paramInt1] = (byte)i;
            return paramInt1 + 1 - paramInt2;
          } 
        } else {
          paramArrayOfbyte[paramInt1] = (byte)i;
          return paramInt1 + 1 - paramInt2;
        } 
      } 
      j = paramInt1;
      paramInt1 = i;
      i = j;
      paramArrayOfbyte[paramInt1] = (byte)i;
      return paramInt1 + 1 - paramInt2;
    } 
    paramInt1 = paramInt2;
    paramArrayOfbyte[paramInt1] = (byte)i;
    return paramInt1 + 1 - paramInt2;
  }
  
  public static int encodeLong(long paramLong, byte[] paramArrayOfbyte, int paramInt) {
    paramLong = paramLong << 1L ^ paramLong >> 63L;
    if ((0xFFFFFFFFFFFFFF80L & paramLong) != 0L) {
      int k = paramInt + 1;
      paramArrayOfbyte[paramInt] = (byte)(int)((0x80L | paramLong) & 0xFFL);
      long l = paramLong >>> 7L;
      int j = k;
      paramLong = l;
      if (l > 127L) {
        int m = k + 1;
        paramArrayOfbyte[k] = (byte)(int)((0x80L | l) & 0xFFL);
        l >>>= 7L;
        j = m;
        paramLong = l;
        if (l > 127L) {
          k = m + 1;
          paramArrayOfbyte[m] = (byte)(int)((0x80L | l) & 0xFFL);
          l >>>= 7L;
          j = k;
          paramLong = l;
          if (l > 127L) {
            m = k + 1;
            paramArrayOfbyte[k] = (byte)(int)((0x80L | l) & 0xFFL);
            l >>>= 7L;
            j = m;
            paramLong = l;
            if (l > 127L) {
              int n = m + 1;
              paramArrayOfbyte[m] = (byte)(int)((0x80L | l) & 0xFFL);
              l >>>= 7L;
              j = n;
              paramLong = l;
              if (l > 127L) {
                k = n + 1;
                paramArrayOfbyte[n] = (byte)(int)((0x80L | l) & 0xFFL);
                l >>>= 7L;
                j = k;
                paramLong = l;
                if (l > 127L) {
                  m = k + 1;
                  paramArrayOfbyte[k] = (byte)(int)((0x80L | l) & 0xFFL);
                  l >>>= 7L;
                  j = m;
                  paramLong = l;
                  if (l > 127L) {
                    k = m + 1;
                    paramArrayOfbyte[m] = (byte)(int)((0x80L | l) & 0xFFL);
                    l >>>= 7L;
                    j = k;
                    paramLong = l;
                    if (l > 127L) {
                      j = k + 1;
                      paramArrayOfbyte[k] = (byte)(int)((0x80L | l) & 0xFFL);
                      paramLong = l >>> 7L;
                    } 
                  } 
                } 
              } 
            } 
          } 
        } 
      } 
      paramArrayOfbyte[j] = (byte)(int)paramLong;
      return j + 1 - paramInt;
    } 
    int i = paramInt;
    paramArrayOfbyte[i] = (byte)(int)paramLong;
    return i + 1 - paramInt;
  }
  
  private static int hashBytes(int paramInt1, BinaryData$HashData paramBinaryData$HashData, int paramInt2, boolean paramBoolean) {
    int i;
    byte[] arrayOfByte = BinaryData$HashData.access$500(paramBinaryData$HashData).getBuf();
    int j = BinaryData$HashData.access$500(paramBinaryData$HashData).getPos();
    int k = j + paramInt2;
    if (paramBoolean) {
      int m = k - 1;
      while (true) {
        i = paramInt1;
        if (m >= j) {
          i = arrayOfByte[m];
          m--;
          paramInt1 = paramInt1 * 31 + i;
          continue;
        } 
        break;
      } 
    } else {
      int m = j;
      while (true) {
        i = paramInt1;
        if (m < k) {
          i = arrayOfByte[m];
          m++;
          paramInt1 = paramInt1 * 31 + i;
          continue;
        } 
        break;
      } 
    } 
    BinaryData$HashData.access$400(paramBinaryData$HashData).skipFixed(paramInt2);
    return i;
  }
  
  private static int hashCode(BinaryData$HashData paramBinaryData$HashData, Schema paramSchema) {
    Iterator<Schema.Field> iterator;
    Schema schema;
    int j;
    long l;
    int i = 0;
    BinaryDecoder binaryDecoder = BinaryData$HashData.access$400(paramBinaryData$HashData);
    switch (BinaryData$3.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        throw new AvroRuntimeException("Unexpected schema to hashCode!");
      case 1:
        iterator = paramSchema.getFields().iterator();
        j = 1;
        while (true) {
          i = j;
          if (iterator.hasNext()) {
            Schema.Field field = iterator.next();
            if (field.order() == Schema.Field.Order.IGNORE) {
              GenericDatumReader.skip(field.schema(), binaryDecoder);
              continue;
            } 
            j = hashCode(paramBinaryData$HashData, field.schema()) + j * 31;
            continue;
          } 
          break;
        } 
      case 2:
      case 3:
        i = binaryDecoder.readInt();
      case 14:
        return i;
      case 11:
        i = Float.floatToIntBits(binaryDecoder.readFloat());
      case 4:
        l = binaryDecoder.readLong();
        i = (int)(l ^ l >>> 32L);
      case 12:
        l = Double.doubleToLongBits(binaryDecoder.readDouble());
        i = (int)(l ^ l >>> 32L);
      case 5:
        schema = iterator.getElementType();
        l = binaryDecoder.readArrayStart();
        j = 1;
        while (true) {
          i = j;
          if (l != 0L) {
            i = j;
            long l1;
            for (l1 = 0L; l1 < l; l1++)
              i = i * 31 + hashCode(paramBinaryData$HashData, schema); 
            l = binaryDecoder.arrayNext();
            j = i;
          } 
        } 
      case 6:
        throw new AvroRuntimeException("Can't hashCode maps!");
      case 7:
        i = hashCode(paramBinaryData$HashData, schema.getTypes().get(binaryDecoder.readInt()));
      case 8:
        i = hashBytes(1, paramBinaryData$HashData, schema.getFixedSize(), false);
      case 9:
        i = hashBytes(0, paramBinaryData$HashData, binaryDecoder.readInt(), false);
      case 10:
        i = hashBytes(1, paramBinaryData$HashData, binaryDecoder.readInt(), true);
      case 13:
        break;
    } 
    if (binaryDecoder.readBoolean())
      i = 1231; 
    i = 1237;
  }
  
  public static int hashCode(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, Schema paramSchema) {
    BinaryData$HashData binaryData$HashData = HASH_DATA.get();
    binaryData$HashData.set(paramArrayOfbyte, paramInt1, paramInt2);
    try {
      return hashCode(binaryData$HashData, paramSchema);
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  public static int skipLong(byte[] paramArrayOfbyte, int paramInt) {
    int i = paramInt + 1;
    byte b = paramArrayOfbyte[paramInt];
    for (paramInt = i; (b & 0x80) != 0; paramInt++)
      b = paramArrayOfbyte[paramInt]; 
    return paramInt;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\BinaryData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */