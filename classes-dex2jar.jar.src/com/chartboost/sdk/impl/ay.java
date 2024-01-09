package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ay implements Serializable, Comparable {
  static final Logger a;
  
  private static AtomicInteger f;
  
  private static final int g;
  
  final int b;
  
  final int c;
  
  final int d;
  
  boolean e;
  
  static {
    // Byte code:
    //   0: ldc 'org.bson.ObjectId'
    //   2: invokestatic getLogger : (Ljava/lang/String;)Ljava/util/logging/Logger;
    //   5: putstatic com/chartboost/sdk/impl/ay.a : Ljava/util/logging/Logger;
    //   8: new java/util/concurrent/atomic/AtomicInteger
    //   11: dup
    //   12: new java/util/Random
    //   15: dup
    //   16: invokespecial <init> : ()V
    //   19: invokevirtual nextInt : ()I
    //   22: invokespecial <init> : (I)V
    //   25: putstatic com/chartboost/sdk/impl/ay.f : Ljava/util/concurrent/atomic/AtomicInteger;
    //   28: new java/lang/StringBuilder
    //   31: astore #4
    //   33: aload #4
    //   35: invokespecial <init> : ()V
    //   38: invokestatic getNetworkInterfaces : ()Ljava/util/Enumeration;
    //   41: astore_3
    //   42: aload_3
    //   43: invokeinterface hasMoreElements : ()Z
    //   48: ifeq -> 294
    //   51: aload #4
    //   53: aload_3
    //   54: invokeinterface nextElement : ()Ljava/lang/Object;
    //   59: checkcast java/net/NetworkInterface
    //   62: invokevirtual toString : ()Ljava/lang/String;
    //   65: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: goto -> 42
    //   72: astore_3
    //   73: getstatic com/chartboost/sdk/impl/ay.a : Ljava/util/logging/Logger;
    //   76: getstatic java/util/logging/Level.WARNING : Ljava/util/logging/Level;
    //   79: aload_3
    //   80: invokevirtual getMessage : ()Ljava/lang/String;
    //   83: aload_3
    //   84: invokevirtual log : (Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   87: new java/util/Random
    //   90: astore_3
    //   91: aload_3
    //   92: invokespecial <init> : ()V
    //   95: aload_3
    //   96: invokevirtual nextInt : ()I
    //   99: bipush #16
    //   101: ishl
    //   102: istore_0
    //   103: getstatic com/chartboost/sdk/impl/ay.a : Ljava/util/logging/Logger;
    //   106: astore #4
    //   108: new java/lang/StringBuilder
    //   111: astore_3
    //   112: aload_3
    //   113: invokespecial <init> : ()V
    //   116: aload #4
    //   118: aload_3
    //   119: ldc 'machine piece post: '
    //   121: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: iload_0
    //   125: invokestatic toHexString : (I)Ljava/lang/String;
    //   128: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual toString : ()Ljava/lang/String;
    //   134: invokevirtual fine : (Ljava/lang/String;)V
    //   137: new java/util/Random
    //   140: astore_3
    //   141: aload_3
    //   142: invokespecial <init> : ()V
    //   145: aload_3
    //   146: invokevirtual nextInt : ()I
    //   149: istore_1
    //   150: invokestatic getRuntimeMXBean : ()Ljava/lang/management/RuntimeMXBean;
    //   153: invokeinterface getName : ()Ljava/lang/String;
    //   158: invokevirtual hashCode : ()I
    //   161: istore_2
    //   162: iload_2
    //   163: istore_1
    //   164: ldc com/chartboost/sdk/impl/ay
    //   166: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   169: astore_3
    //   170: aload_3
    //   171: ifnull -> 315
    //   174: aload_3
    //   175: invokestatic identityHashCode : (Ljava/lang/Object;)I
    //   178: istore_2
    //   179: new java/lang/StringBuilder
    //   182: astore_3
    //   183: aload_3
    //   184: invokespecial <init> : ()V
    //   187: aload_3
    //   188: iload_1
    //   189: invokestatic toHexString : (I)Ljava/lang/String;
    //   192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: pop
    //   196: aload_3
    //   197: iload_2
    //   198: invokestatic toHexString : (I)Ljava/lang/String;
    //   201: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: aload_3
    //   206: invokevirtual toString : ()Ljava/lang/String;
    //   209: invokevirtual hashCode : ()I
    //   212: ldc 65535
    //   214: iand
    //   215: istore_1
    //   216: getstatic com/chartboost/sdk/impl/ay.a : Ljava/util/logging/Logger;
    //   219: astore_3
    //   220: new java/lang/StringBuilder
    //   223: astore #4
    //   225: aload #4
    //   227: invokespecial <init> : ()V
    //   230: aload_3
    //   231: aload #4
    //   233: ldc 'process piece: '
    //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: iload_1
    //   239: invokestatic toHexString : (I)Ljava/lang/String;
    //   242: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: invokevirtual toString : ()Ljava/lang/String;
    //   248: invokevirtual fine : (Ljava/lang/String;)V
    //   251: iload_1
    //   252: iload_0
    //   253: ior
    //   254: putstatic com/chartboost/sdk/impl/ay.g : I
    //   257: getstatic com/chartboost/sdk/impl/ay.a : Ljava/util/logging/Logger;
    //   260: astore #4
    //   262: new java/lang/StringBuilder
    //   265: astore_3
    //   266: aload_3
    //   267: invokespecial <init> : ()V
    //   270: aload #4
    //   272: aload_3
    //   273: ldc 'machine : '
    //   275: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: getstatic com/chartboost/sdk/impl/ay.g : I
    //   281: invokestatic toHexString : (I)Ljava/lang/String;
    //   284: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: invokevirtual toString : ()Ljava/lang/String;
    //   290: invokevirtual fine : (Ljava/lang/String;)V
    //   293: return
    //   294: aload #4
    //   296: invokevirtual toString : ()Ljava/lang/String;
    //   299: invokevirtual hashCode : ()I
    //   302: istore_0
    //   303: iload_0
    //   304: bipush #16
    //   306: ishl
    //   307: istore_0
    //   308: goto -> 103
    //   311: astore_3
    //   312: goto -> 164
    //   315: iconst_0
    //   316: istore_2
    //   317: goto -> 179
    //   320: astore_3
    //   321: new java/lang/RuntimeException
    //   324: dup
    //   325: aload_3
    //   326: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   329: athrow
    // Exception table:
    //   from	to	target	type
    //   28	42	72	java/lang/Throwable
    //   28	42	320	java/lang/Exception
    //   42	69	72	java/lang/Throwable
    //   42	69	320	java/lang/Exception
    //   73	103	320	java/lang/Exception
    //   103	150	320	java/lang/Exception
    //   150	162	311	java/lang/Throwable
    //   150	162	320	java/lang/Exception
    //   164	170	320	java/lang/Exception
    //   174	179	320	java/lang/Exception
    //   179	293	320	java/lang/Exception
    //   294	303	72	java/lang/Throwable
    //   294	303	320	java/lang/Exception
  }
  
  public ay() {
    this.b = (int)(System.currentTimeMillis() / 1000L);
    this.c = g;
    this.d = f.getAndIncrement();
    this.e = true;
  }
  
  public ay(int paramInt1, int paramInt2, int paramInt3) {
    this.b = paramInt1;
    this.c = paramInt2;
    this.d = paramInt3;
    this.e = false;
  }
  
  public ay(String paramString) {
    this(paramString, false);
  }
  
  public ay(String paramString, boolean paramBoolean) {
    if (!a(paramString))
      throw new IllegalArgumentException("invalid ObjectId [" + paramString + "]"); 
    String str = paramString;
    if (paramBoolean)
      str = b(paramString); 
    byte[] arrayOfByte = new byte[12];
    for (byte b = 0; b < arrayOfByte.length; b++)
      arrayOfByte[b] = (byte)Integer.parseInt(str.substring(b * 2, b * 2 + 2), 16); 
    ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte);
    this.b = byteBuffer.getInt();
    this.c = byteBuffer.getInt();
    this.d = byteBuffer.getInt();
    this.e = false;
  }
  
  public static ay a(Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof ay)
      return (ay)paramObject; 
    if (paramObject instanceof String) {
      paramObject = paramObject.toString();
      if (a((String)paramObject))
        return new ay((String)paramObject); 
    } 
    return null;
  }
  
  static String a(String paramString, int paramInt) {
    return paramString.substring(paramInt * 2, paramInt * 2 + 2);
  }
  
  public static boolean a(String paramString) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: aload_0
    //   4: ifnonnull -> 14
    //   7: iload #5
    //   9: istore #4
    //   11: iload #4
    //   13: ireturn
    //   14: aload_0
    //   15: invokevirtual length : ()I
    //   18: istore_2
    //   19: iload #5
    //   21: istore #4
    //   23: iload_2
    //   24: bipush #24
    //   26: if_icmpne -> 11
    //   29: iconst_0
    //   30: istore_1
    //   31: iload_1
    //   32: iload_2
    //   33: if_icmpge -> 95
    //   36: aload_0
    //   37: iload_1
    //   38: invokevirtual charAt : (I)C
    //   41: istore_3
    //   42: iload_3
    //   43: bipush #48
    //   45: if_icmplt -> 60
    //   48: iload_3
    //   49: bipush #57
    //   51: if_icmpgt -> 60
    //   54: iinc #1, 1
    //   57: goto -> 31
    //   60: iload_3
    //   61: bipush #97
    //   63: if_icmplt -> 72
    //   66: iload_3
    //   67: bipush #102
    //   69: if_icmple -> 54
    //   72: iload #5
    //   74: istore #4
    //   76: iload_3
    //   77: bipush #65
    //   79: if_icmplt -> 11
    //   82: iload #5
    //   84: istore #4
    //   86: iload_3
    //   87: bipush #70
    //   89: if_icmpgt -> 11
    //   92: goto -> 54
    //   95: iconst_1
    //   96: istore #4
    //   98: goto -> 11
  }
  
  public static String b(String paramString) {
    if (!a(paramString))
      throw new IllegalArgumentException("invalid object id: " + paramString); 
    StringBuilder stringBuilder = new StringBuilder(24);
    byte b;
    for (b = 7; b >= 0; b--)
      stringBuilder.append(a(paramString, b)); 
    for (b = 11; b >= 8; b--)
      stringBuilder.append(a(paramString, b)); 
    return stringBuilder.toString();
  }
  
  int a(int paramInt1, int paramInt2) {
    long l = (paramInt1 & 0xFFFFFFFFL) - (paramInt2 & 0xFFFFFFFFL);
    return (l < -2147483648L) ? Integer.MIN_VALUE : ((l > 2147483647L) ? Integer.MAX_VALUE : (int)l);
  }
  
  public int a(ay paramay) {
    if (paramay == null)
      return -1; 
    int j = a(this.b, paramay.b);
    int i = j;
    if (j == 0) {
      j = a(this.c, paramay.c);
      i = j;
      if (j == 0)
        i = a(this.d, paramay.d); 
    } 
    return i;
  }
  
  public String a() {
    byte[] arrayOfByte = b();
    StringBuilder stringBuilder = new StringBuilder(24);
    for (byte b = 0; b < arrayOfByte.length; b++) {
      String str = Integer.toHexString(arrayOfByte[b] & 0xFF);
      if (str.length() == 1)
        stringBuilder.append("0"); 
      stringBuilder.append(str);
    } 
    return stringBuilder.toString();
  }
  
  public byte[] b() {
    byte[] arrayOfByte = new byte[12];
    ByteBuffer byteBuffer = ByteBuffer.wrap(arrayOfByte);
    byteBuffer.putInt(this.b);
    byteBuffer.putInt(this.c);
    byteBuffer.putInt(this.d);
    return arrayOfByte;
  }
  
  public int c() {
    return this.b;
  }
  
  public int d() {
    return this.c;
  }
  
  public int e() {
    return this.d;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject) {
      paramObject = a(paramObject);
      if (paramObject == null)
        return false; 
      if (this.b != ((ay)paramObject).b || this.c != ((ay)paramObject).c || this.d != ((ay)paramObject).d)
        bool = false; 
    } 
    return bool;
  }
  
  public int hashCode() {
    return this.b + this.c * 111 + this.d * 17;
  }
  
  public String toString() {
    return a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */