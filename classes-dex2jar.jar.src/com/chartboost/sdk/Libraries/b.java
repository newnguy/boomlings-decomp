package com.chartboost.sdk.Libraries;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class b {
  public static byte[] a(byte[] paramArrayOfbyte) {
    MessageDigest messageDigest2 = null;
    MessageDigest messageDigest1 = messageDigest2;
    if (paramArrayOfbyte != null)
      try {
        messageDigest1 = MessageDigest.getInstance("SHA-1");
        messageDigest1.update(paramArrayOfbyte);
        byte[] arrayOfByte = messageDigest1.digest();
      } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        messageDigest1 = messageDigest2;
      }  
    return (byte[])messageDigest1;
  }
  
  public static String b(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte == null)
      return null; 
    BigInteger bigInteger = new BigInteger(1, paramArrayOfbyte);
    return String.format(Locale.US, "%0" + (paramArrayOfbyte.length << 1) + "x", new Object[] { bigInteger });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Libraries\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */