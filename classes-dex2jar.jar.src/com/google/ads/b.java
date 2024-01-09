package com.google.ads;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class b {
  private static b c = null;
  
  private final BigInteger a = d();
  
  private BigInteger b = BigInteger.ONE;
  
  public static b a() {
    // Byte code:
    //   0: ldc com/google/ads/b
    //   2: monitorenter
    //   3: getstatic com/google/ads/b.c : Lcom/google/ads/b;
    //   6: ifnonnull -> 21
    //   9: new com/google/ads/b
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/google/ads/b.c : Lcom/google/ads/b;
    //   21: getstatic com/google/ads/b.c : Lcom/google/ads/b;
    //   24: astore_0
    //   25: ldc com/google/ads/b
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/google/ads/b
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  private static byte[] a(long paramLong) {
    return BigInteger.valueOf(paramLong).toByteArray();
  }
  
  private static BigInteger d() {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      UUID uUID = UUID.randomUUID();
      messageDigest.update(a(uUID.getLeastSignificantBits()));
      messageDigest.update(a(uUID.getMostSignificantBits()));
      byte[] arrayOfByte = new byte[9];
      arrayOfByte[0] = 0;
      System.arraycopy(messageDigest.digest(), 0, arrayOfByte, 1, 8);
      return new BigInteger(arrayOfByte);
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw new RuntimeException("Cannot find MD5 message digest algorithm.");
    } 
  }
  
  public BigInteger b() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield a : Ljava/math/BigInteger;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public BigInteger c() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield b : Ljava/math/BigInteger;
    //   6: astore_1
    //   7: aload_0
    //   8: aload_0
    //   9: getfield b : Ljava/math/BigInteger;
    //   12: getstatic java/math/BigInteger.ONE : Ljava/math/BigInteger;
    //   15: invokevirtual add : (Ljava/math/BigInteger;)Ljava/math/BigInteger;
    //   18: putfield b : Ljava/math/BigInteger;
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: areturn
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	25	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */