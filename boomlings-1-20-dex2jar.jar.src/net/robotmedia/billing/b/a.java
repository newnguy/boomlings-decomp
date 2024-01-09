package net.robotmedia.billing.b;

import android.text.TextUtils;
import android.util.Log;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import net.robotmedia.billing.c;
import net.robotmedia.billing.c.c;
import net.robotmedia.billing.c.d;

public class a implements b {
  private c a;
  
  public a(c paramc) {
    this.a = paramc;
  }
  
  protected PublicKey a(String paramString) {
    try {
      byte[] arrayOfByte = c.a(paramString);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec();
      this(arrayOfByte);
      return keyFactory.generatePublic(x509EncodedKeySpec);
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      throw new RuntimeException(noSuchAlgorithmException);
    } catch (InvalidKeySpecException invalidKeySpecException) {
      Log.e("Billing", "Invalid key specification.");
      throw new IllegalArgumentException(invalidKeySpecException);
    } catch (d d) {
      Log.e("Billing", "Base64 decoding failed.");
      throw new IllegalArgumentException(d);
    } 
  }
  
  public boolean a(String paramString1, String paramString2) {
    boolean bool = false;
    if (this.a != null) {
      String str = this.a.getPublicKey();
      if (TextUtils.isEmpty(str)) {
        Log.w("Billing", "Please set the public key or turn on debug mode");
        return bool;
      } 
      if (paramString1 == null) {
        Log.e("Billing", "Data is null");
        return bool;
      } 
      return a(a(str), paramString1, paramString2);
    } 
    Log.w("Billing", "Please set the public key or turn on debug mode");
    return bool;
  }
  
  protected boolean a(PublicKey paramPublicKey, String paramString1, String paramString2) {
    boolean bool = false;
    try {
      Signature signature = Signature.getInstance("SHA1withRSA");
      signature.initVerify(paramPublicKey);
      signature.update(paramString1.getBytes());
      if (!signature.verify(c.a(paramString2))) {
        Log.e("Billing", "Signature verification failed.");
        return bool;
      } 
      bool = true;
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      Log.e("Billing", "NoSuchAlgorithmException");
    } catch (InvalidKeyException invalidKeyException) {
      Log.e("Billing", "Invalid key specification");
    } catch (SignatureException signatureException) {
      Log.e("Billing", "Signature exception");
    } catch (d d) {
      Log.e("Billing", "Base64 decoding failed");
    } 
    return bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\b\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */