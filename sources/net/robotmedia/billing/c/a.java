package net.robotmedia.billing.c;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class a {
    private static final byte[] a = {16, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74};
    private Cipher b;
    private Cipher c;

    public a(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC").generateSecret(new PBEKeySpec(str.toCharArray(), bArr, 1024, 256)).getEncoded(), "AES");
            this.b = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.b.init(1, secretKeySpec, new IvParameterSpec(a));
            this.c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            this.c.init(2, secretKeySpec, new IvParameterSpec(a));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return c.a(this.b.doFinal(("net.robotmedia.billing.utils.AESObfuscator-1|" + str).getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Invalid environment", e);
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException("Invalid environment", e2);
        }
    }

    public String b(String str) {
        if (str == null) {
            return null;
        }
        try {
            String str2 = new String(this.c.doFinal(c.a(str)), "UTF-8");
            if (str2.indexOf("net.robotmedia.billing.utils.AESObfuscator-1|") != 0) {
                throw new b(this, "Header not found (invalid data or key):" + str);
            }
            return str2.substring("net.robotmedia.billing.utils.AESObfuscator-1|".length(), str2.length());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Invalid environment", e);
        } catch (BadPaddingException e2) {
            throw new b(this, String.valueOf(e2.getMessage()) + ":" + str);
        } catch (IllegalBlockSizeException e3) {
            throw new b(this, String.valueOf(e3.getMessage()) + ":" + str);
        } catch (d e4) {
            throw new b(this, String.valueOf(e4.getMessage()) + ":" + str);
        }
    }
}
