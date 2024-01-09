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
import net.robotmedia.billing.c.d;

/* loaded from: classes.dex */
public class a implements b {
    private c a;

    public a(c cVar) {
        this.a = cVar;
    }

    protected PublicKey a(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(net.robotmedia.billing.c.c.a(str)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e2) {
            Log.e("Billing", "Invalid key specification.");
            throw new IllegalArgumentException(e2);
        } catch (d e3) {
            Log.e("Billing", "Base64 decoding failed.");
            throw new IllegalArgumentException(e3);
        }
    }

    @Override // net.robotmedia.billing.b.b
    public boolean a(String str, String str2) {
        if (this.a != null) {
            String publicKey = this.a.getPublicKey();
            if (!TextUtils.isEmpty(publicKey)) {
                if (str == null) {
                    Log.e("Billing", "Data is null");
                    return false;
                }
                return a(a(publicKey), str, str2);
            }
        }
        Log.w("Billing", "Please set the public key or turn on debug mode");
        return false;
    }

    protected boolean a(PublicKey publicKey, String str, String str2) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(str.getBytes());
            if (signature.verify(net.robotmedia.billing.c.c.a(str2))) {
                return true;
            }
            Log.e("Billing", "Signature verification failed.");
            return false;
        } catch (InvalidKeyException e) {
            Log.e("Billing", "Invalid key specification");
            return false;
        } catch (NoSuchAlgorithmException e2) {
            Log.e("Billing", "NoSuchAlgorithmException");
            return false;
        } catch (SignatureException e3) {
            Log.e("Billing", "Signature exception");
            return false;
        } catch (d e4) {
            Log.e("Billing", "Base64 decoding failed");
            return false;
        }
    }
}
