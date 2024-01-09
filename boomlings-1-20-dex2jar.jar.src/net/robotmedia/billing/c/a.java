package net.robotmedia.billing.c;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class a {
  private static final byte[] a;
  
  private Cipher b;
  
  private Cipher c;
  
  static {
    byte[] arrayOfByte = new byte[16];
    arrayOfByte[0] = 16;
    arrayOfByte[1] = 74;
    arrayOfByte[2] = 71;
    arrayOfByte[3] = -80;
    arrayOfByte[4] = 32;
    arrayOfByte[5] = 101;
    arrayOfByte[6] = -47;
    arrayOfByte[7] = 72;
    arrayOfByte[8] = 117;
    arrayOfByte[9] = -14;
    arrayOfByte[11] = -29;
    arrayOfByte[12] = 70;
    arrayOfByte[13] = 65;
    arrayOfByte[14] = -12;
    arrayOfByte[15] = 74;
    a = arrayOfByte;
  }
  
  public a(byte[] paramArrayOfbyte, String paramString) {
    try {
      SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC");
      PBEKeySpec pBEKeySpec = new PBEKeySpec();
      this(paramString.toCharArray(), paramArrayOfbyte, 1024, 256);
      SecretKey secretKey = secretKeyFactory.generateSecret(pBEKeySpec);
      SecretKeySpec secretKeySpec = new SecretKeySpec();
      this(secretKey.getEncoded(), "AES");
      this.b = Cipher.getInstance("AES/CBC/PKCS5Padding");
      Cipher cipher = this.b;
      IvParameterSpec ivParameterSpec = new IvParameterSpec();
      this(a);
      cipher.init(1, secretKeySpec, ivParameterSpec);
      this.c = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher = this.c;
      ivParameterSpec = new IvParameterSpec();
      this(a);
      cipher.init(2, secretKeySpec, ivParameterSpec);
      return;
    } catch (GeneralSecurityException generalSecurityException) {
      throw new RuntimeException("Invalid environment", generalSecurityException);
    } 
  }
  
  public String a(String paramString) {
    if (paramString == null)
      return null; 
    try {
      Cipher cipher = this.b;
      StringBuilder stringBuilder = new StringBuilder();
      this("net.robotmedia.billing.utils.AESObfuscator-1|");
      return c.a(cipher.doFinal(stringBuilder.append(paramString).toString().getBytes("UTF-8")));
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new RuntimeException("Invalid environment", unsupportedEncodingException);
    } catch (GeneralSecurityException generalSecurityException) {
      throw new RuntimeException("Invalid environment", generalSecurityException);
    } 
  }
  
  public String b(String paramString) {
    if (paramString == null)
      return null; 
    try {
      b b;
      String str = new String();
      this(this.c.doFinal(c.a(paramString)), "UTF-8");
      if (str.indexOf("net.robotmedia.billing.utils.AESObfuscator-1|") != 0) {
        b = new b();
        StringBuilder stringBuilder = new StringBuilder();
        this("Header not found (invalid data or key):");
        this(this, stringBuilder.append(paramString).toString());
        throw b;
      } 
      return b.substring("net.robotmedia.billing.utils.AESObfuscator-1|".length(), b.length());
    } catch (d d) {
      throw new b(this, String.valueOf(d.getMessage()) + ":" + paramString);
    } catch (IllegalBlockSizeException illegalBlockSizeException) {
      throw new b(this, String.valueOf(illegalBlockSizeException.getMessage()) + ":" + paramString);
    } catch (BadPaddingException badPaddingException) {
      throw new b(this, String.valueOf(badPaddingException.getMessage()) + ":" + paramString);
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new RuntimeException("Invalid environment", unsupportedEncodingException);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\c\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */