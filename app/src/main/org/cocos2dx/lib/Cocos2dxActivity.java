package org.cocos2dx.lib;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import com.robtopx.boomlings.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import net.robotmedia.billing.helper.AbstractBillingActivity;

/* loaded from: classes.dex */
public class Cocos2dxActivity extends AbstractBillingActivity {
    private static final int HANDLER_SHOW_DIALOG = 1;
    private static Cocos2dxAccelerometer accelerometer;
    private static a adMobDelegate_;
    private static ao backgroundMusicPlayer;
    public static f cbDelegate_;
    public static as fbDelegate_;
    private static Handler handler;
    private static String packageName;
    private static ap soundPlayer;
    private static bb tapJoyDelegate_;
    public static boolean transactionsRestored_;
    private static boolean accelerometerEnabled = false;
    private static Cocos2dxActivity me = null;
    private static boolean kDisableIAP = false;
    public static boolean isLoaded_ = false;
    private static boolean billingSupported_ = false;
    public static boolean countryIsFR_ = false;
    public static boolean showLoadingError_ = false;
    private static int prio0 = 0;
    private static int prio1 = 1;
    private static int prio2 = 2;
    private static int prio3 = 3;
    private static int prio4 = 4;
    private static int prioMod0 = 1;
    private static int prioMod1 = 30;
    private static int prioMod2 = 0;
    private static int prioMod3 = 0;
    private static int prioMod4 = 0;

    public static void appRequestFriends(String str) {
        me.runOnUiThread(new l(str));
    }

    public static void appRequestFriendsRefID(String str, String str2) {
        me.runOnUiThread(new m(str, str2));
    }

    private static String boomKey() {
        return "y27vyZlpIJk2C8wd";
    }

    public static void cacheInterstitial() {
        Log.d("MAIN", "Cache interstitial");
        cbDelegate_.cacheInterstitial();
    }

    public static void cacheInterstitial(String str) {
        cbDelegate_.cacheInterstitial(str);
    }

    public static void decryptFile(String str) {
        File file = new File(String.valueOf(str) + "x");
        File file2 = new File(str);
        if (!file.exists()) {
            return;
        }
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
            }
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            SecretKeySpec secretKeySpec = new SecretKeySpec(boomKey().getBytes(), "AES/ECB/PKCS5Padding");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, secretKeySpec);
            CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, cipher);
            byte[] bArr = new byte[8];
            while (true) {
                int read = cipherInputStream.read(bArr);
                if (read == -1) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    cipherInputStream.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e2) {
            try {
                removeFile(str);
                decryptFileSpecial(str);
            } catch (Exception e3) {
            }
        }
    }

    public static void decryptFileSpecial(String str) {
        File file = new File(String.valueOf(str) + "x");
        File file2 = new File(str);
        if (!file.exists()) {
            return;
        }
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
            }
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            SecretKeySpec secretKeySpec = new SecretKeySpec(boomKey().getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, secretKeySpec);
            CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, cipher);
            byte[] bArr = new byte[8];
            while (true) {
                int read = cipherInputStream.read(bArr);
                if (read == -1) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    cipherInputStream.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e2) {
            try {
                removeFile(str);
            } catch (Exception e3) {
            }
        }
    }

    public static void disableAccelerometer() {
        accelerometerEnabled = false;
        accelerometer.disable();
    }

    public static void disableBanner() {
        adMobDelegate_.disableBanner();
    }

    public static boolean doesFileExist(String str) {
        return new File(str).exists();
    }

    public static void downloadImage(String str, String str2, String str3) {
        new Thread(new q(str3, str2, str)).start();
    }

    public static void enableAccelerometer() {
        accelerometerEnabled = true;
        accelerometer.enable();
    }

    public static void enableBanner() {
        adMobDelegate_.enableBanner();
    }

    public static void encryptFile(String str) {
        File file = new File(str);
        File file2 = new File(String.valueOf(str) + "x1");
        File file3 = new File(String.valueOf(str) + "x");
        if (!file.exists()) {
            return;
        }
        if (!file2.exists()) {
            file2.createNewFile();
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        SecretKeySpec secretKeySpec = new SecretKeySpec(boomKey().getBytes(), "AES/ECB/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, secretKeySpec);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
        byte[] bArr = new byte[8];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                cipherOutputStream.flush();
                cipherOutputStream.close();
                fileInputStream.close();
                file.delete();
                file3.delete();
                file2.renameTo(file3);
                return;
            }
            cipherOutputStream.write(bArr, 0, read);
        }
    }

    public static void end() {
        backgroundMusicPlayer.end();
        soundPlayer.end();
    }

    public static float getBackgroundMusicVolume() {
        return backgroundMusicPlayer.getBackgroundVolume();
    }

    public static String getCocos2dxPackageName() {
        return packageName;
    }

    public static String getCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static void getDeviceID() {
        TelephonyManager telephonyManager = (TelephonyManager) me.getBaseContext().getSystemService("phone");
        JniToCpp.updateDeviceID(new UUID((Settings.Secure.getString(me.getContentResolver(), "android_id")).hashCode(), ((telephonyManager.getDeviceId()).hashCode() << 32) | (telephonyManager.getSimSerialNumber()).hashCode()).toString());
    }

    public static float getEffectsVolume() {
        return soundPlayer.getEffectsVolume();
    }

    public static void getFacebookScores() {
        new Thread(new p()).start();
    }

    public static String getFacebookUserId() {
        return fbDelegate_.getFacebookUserId();
    }

    public static void getImageForUserId(String str) {
        fbDelegate_.getImageForUserId(str);
    }

    public static void getTapPoints() {
        if (!isLoaded_ || kDisableIAP) {
            return;
        }
        Log.d("TAPJOY", "GETTAPPOINTS");
        com.tapjoy.g.a().a((com.tapjoy.ak) tapJoyDelegate_);
    }

    public static void getUserID() {
        TelephonyManager telephonyManager = (TelephonyManager) me.getBaseContext().getSystemService("phone");
        JniToCpp.updateUserID(new UUID((Settings.Secure.getString(me.getContentResolver(), "android_id")).hashCode(), ((telephonyManager.getDeviceId()).hashCode() << 32) | (telephonyManager.getSimSerialNumber()).hashCode()).toString());
    }

    public static boolean hasCachedInterstitial(String str) {
        return cbDelegate_.hasCachedInterstitial(str);
    }

    public static boolean isBackgroundMusicPlaying() {
        return backgroundMusicPlayer.isBackgroundMusicPlaying();
    }

    public static boolean isBillingSupported() {
        if (kDisableIAP) {
            return false;
        }
        return billingSupported_;
    }

    public static boolean isLoggedInFacebook() {
        return fbDelegate_.isLoggedIn();
    }

    public static boolean isNetworkAvailable() {
        NetworkInfo[] allNetworkInfo;
        boolean z = false;
        boolean z2 = false;
        for (NetworkInfo networkInfo : ((ConnectivityManager) me.getSystemService("connectivity")).getAllNetworkInfo()) {
            if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
                z2 = true;
            }
            if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE") && networkInfo.isConnected()) {
                z = true;
            }
        }
        return z2 || z;
    }

    public static void loadingFinished() {
        isLoaded_ = true;
        me.doRestoreCheck();
    }

    public static void loginFacebook() {
        me.runOnUiThread(new j());
    }

    public static void logoutFacebook() {
        me.runOnUiThread(new k());
    }

    private static native void nativeSetPaths(String str);

    public static boolean onBackKeyPressed() {
        if (cbDelegate_ == null) {
            return false;
        }
        return cbDelegate_.onBackPressed();
    }

    public static void onBoomPause() {
        pauseAds();
    }

    public static void onBoomResume() {
        resumeAds();
    }

    public static void onGameLaunch() {
    }

    public static void openAppPage() {
        me.runOnUiThread(new x());
    }

    public static void openURL(String str) {
        Log.d("MAIN", "Open URL");
        me.runOnUiThread(new s(str));
    }

    public static void pauseAds() {
        adMobDelegate_.pauseBanner();
    }

    public static void pauseAllEffects() {
        soundPlayer.pauseAllEffects();
    }

    public static void pauseBackgroundMusic() {
        backgroundMusicPlayer.pauseBackgroundMusic();
    }

    public static void pauseEffect(int i) {
        soundPlayer.pauseEffect(i);
    }

    public static void performQueuedFBAction() {
        me.runOnUiThread(new aa());
    }

    public static void playBackgroundMusic(String str, boolean z) {
        backgroundMusicPlayer.playBackgroundMusic(str, z);
    }

    public static int playEffect(String str, boolean z, float f, float f2, float f3) {
        return soundPlayer.playEffect(str, z, f, f2, f3);
    }

    public static void postScore(String str) {
        new Thread(new o(str)).start();
    }

    public static void postToWall(String str, String str2, String str3, String str4) {
        me.runOnUiThread(new n(str, str2, str3, str4));
    }

    public static void preloadBackgroundMusic(String str) {
        backgroundMusicPlayer.preloadBackgroundMusic(str);
    }

    public static void preloadEffect(String str) {
        soundPlayer.preloadEffect(str);
    }

    public static void purchaseItem(String str) {
        Log.i("IAP", "IAP Purchase item: " + str);
        net.robotmedia.billing.a.a(me, str, true, null);
    }

    public static void removeFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void restorePurchases() {
        transactionsRestored_ = true;
        me.doRestoreCheck();
    }

    public static void resumeAds() {
        adMobDelegate_.resumeBanner();
    }

    public static void resumeAllEffects() {
        soundPlayer.resumeAllEffects();
    }

    public static void resumeBackgroundMusic() {
        backgroundMusicPlayer.resumeBackgroundMusic();
    }

    public static void resumeEffect(int i) {
        soundPlayer.resumeEffect(i);
    }

    public static void rewindBackgroundMusic() {
        backgroundMusicPlayer.rewindBackgroundMusic();
    }

    public static void sendMail(String str, String str2, String str3) {
        me.runOnUiThread(new z(str3, str, str2));
    }

    public static void setBackgroundMusicVolume(float f) {
        backgroundMusicPlayer.setBackgroundVolume(f);
    }

    public static void setEffectsVolume(float f) {
        soundPlayer.setEffectsVolume(f);
    }

    public static boolean shouldShowPromo() {
        return countryIsFR_;
    }

    public static void showDailyReward() {
        tapJoyDelegate_.showDailyReward();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(String str, String str2) {
        new AlertDialog.Builder(this).setTitle(str).setMessage(str2).setPositiveButton("Ok", new t(this)).create().show();
    }

    public static int showInterstitial() {
        int tryCB = me.tryCB();
        cacheInterstitial();
        Log.d("MAIN", "SHOW INTERSTITIAL: " + tryCB);
        return tryCB;
    }

    public static void showInterstitial(String str) {
        cbDelegate_.showInterstitial(str);
    }

    public static void showLoadError() {
        if (showLoadingError_) {
            showLoadingError_ = false;
            me.runOnUiThread(new u());
        }
    }

    public static void showMessageBox(String str, String str2) {
        Message message = new Message();
        message.what = 1;
        message.obj = new ar(str, str2);
        handler.sendMessage(message);
    }

    public static void showTapJoyOffers() {
        if (kDisableIAP) {
            return;
        }
        com.tapjoy.g.a().b();
    }

    public static void stopAllEffects() {
        soundPlayer.stopAllEffects();
    }

    public static void stopBackgroundMusic() {
        backgroundMusicPlayer.stopBackgroundMusic();
    }

    public static void stopEffect(int i) {
        soundPlayer.stopEffect(i);
    }

    public static void terminateProcess() {
        Process.killProcess(Process.myPid());
    }

    public static void testInterstitialPrio() {
        int i;
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, 5, 2);
        for (int i2 = 0; i2 < 5; i2++) {
            switch (i2) {
                case 0:
                    i = prioMod0;
                    break;
                case 1:
                    i = prioMod1;
                    break;
                case 2:
                    i = prioMod2;
                    break;
                case 3:
                    i = prioMod3;
                    break;
                case 4:
                    i = prioMod4;
                    break;
                default:
                    i = 1;
                    break;
            }
            iArr[i2][0] = i2;
            int round = (int) Math.round(i * Math.random());
            iArr[i2][1] = (round != 0 || i <= 0) ? round : 1;
        }
        Arrays.sort(iArr, new r());
        for (int i3 = 0; i3 < 5; i3++) {
            int i4 = iArr[i3][0];
            int i5 = iArr[i3][1];
            Log.d("CHANCE", "PRIO: " + i4 + " chance: " + i5);
            if (i5 <= 0) {
                i4 = -1;
            }
            switch (i3) {
                case 0:
                    prio0 = i4;
                    break;
                case 1:
                    prio1 = i4;
                    break;
                case 2:
                    prio2 = i4;
                    break;
                case 3:
                    prio3 = i4;
                    break;
                case 4:
                    prio4 = i4;
                    break;
            }
        }
    }

    public static void tryShowRateDialog() {
        me.runOnUiThread(new y());
    }

    public static void unloadEffect(String str) {
        soundPlayer.unloadEffect(str);
    }

    public static void updateAdPrio(int i, int i2, int i3, int i4, int i5) {
        prioMod0 = i;
        prioMod1 = i2;
        prioMod2 = i3;
        prioMod3 = i4;
        prioMod4 = i5;
    }

    private void updateOwnedItems() {
        List<net.robotmedia.billing.a.c> c = net.robotmedia.billing.a.c(this);
        Log.i("IAP", "IAP Update owned items");
        for (net.robotmedia.billing.a.c cVar : c) {
            Log.i("IAP", "IAP: " + cVar.e);
            if (cVar.f == net.robotmedia.billing.a.d.PURCHASED) {
                JniToCpp.itemPurchased(cVar.e);
            } else if (cVar.f == net.robotmedia.billing.a.d.REFUNDED) {
                JniToCpp.itemRefunded(cVar.e);
            }
        }
        for (net.robotmedia.billing.a.c cVar2 : c) {
            net.robotmedia.billing.a.c(this, cVar2);
        }
    }

    public int callInterstitial(int i) {
        switch (i) {
            case 0:
                return me.tryTJ();
            case 1:
                return me.tryCB();
            case 2:
                return me.tryPH();
            default:
                return 0;
        }
    }

    public void checkDeviceCountry() {
        String networkOperator = ((TelephonyManager) me.getBaseContext().getSystemService("phone")).getNetworkOperator();
        if (networkOperator.length() >= 4) {
            networkOperator = networkOperator.substring(0, 3);
        }
        String country = me.getResources().getConfiguration().locale.getCountry();
        if (networkOperator.equals("208") || country.equals("FR")) {
            countryIsFR_ = true;
        }
    }

    @Override // net.robotmedia.billing.c
    public byte[] getObfuscationSalt() {
        return new byte[]{40, -92, -112, -31, 52, -43, 122, -120, -112, -86, -87, 75, 123, 105, 2, 71, 55, 108, 50, -112};
    }

    @Override // net.robotmedia.billing.c
    public String getPublicKey() {
        return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj5ns81HIg1bXgBl4/ftMRD75LqUaM9TsYUBA3edPPPqNgu1YPDUSjnSQlVndpQRCBWUjQ7teuTjzK+VZTZErLu4uaoaWSplbJ7dAu+TyVJRI81K/RvwM/uFU4chgk628GpnoGqh7eNTG29Ol1FO/Q1KoQA1SXuJbwvdkiSxry4pTPgpdnGaOfs3DyBADe2jLKvRD6zS4o0NaM1w/NLEl5GytayK25oLM6s0gdbpqc8voX8bo8GDHTJwiLlnP6paRe2KkY1tFdifP4bzoEVR7i3v0zxq40Zku5W3tZJepGIg0tJVzvy9eRgxSpGycNdRITRoHLvpvyd1O7PvFajl4fQIDAQAB";
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        fbDelegate_.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (onBackKeyPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override // net.robotmedia.billing.helper.AbstractBillingActivity
    public void onBillingChecked(boolean z) {
        if (z) {
            Log.i("IAP", "IAP Billing Supported!");
        } else {
            Log.i("IAP", "IAP Billing NOT Supported!");
        }
        billingSupported_ = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.robotmedia.billing.helper.AbstractBillingActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
        accelerometer = new Cocos2dxAccelerometer(this);
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.mContext = this;
        } else {
            backgroundMusicPlayer = new ao(this);
        }
        if (soundPlayer != null) {
            soundPlayer.mContext = this;
        } else {
            soundPlayer = new ap(this);
        }
        Cocos2dxBitmap.setContext(this);
        handler = new i(this);
        setContentView(R.layout.main);
        if (me == null) {
            com.robtopx.boomlings.a.a(this);
        }
        tapJoyDelegate_ = new bb();
        tapJoyDelegate_.setup(this);
        adMobDelegate_ = new a();
        adMobDelegate_.setup(this);
        cbDelegate_ = new f();
        cbDelegate_.setup(this);
        fbDelegate_ = new as();
        fbDelegate_.setup(this);
        me = this;
    }

    @Override // net.robotmedia.billing.helper.AbstractBillingActivity, android.app.Activity
    protected void onDestroy() {
        com.tapjoy.g.a().e();
        adMobDelegate_.onDestroy();
        cbDelegate_.onDestroy();
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        if (accelerometerEnabled) {
            accelerometer.disable();
        }
        com.tapjoy.g.a().a(false);
    }

    @Override // net.robotmedia.billing.helper.AbstractBillingActivity
    public void onPurchaseStateChanged(String str, net.robotmedia.billing.a.d dVar) {
        Log.i("IAP", "IAP onPurchaseStateChanged() itemId: " + str);
        updateOwnedItems();
    }

    @Override // net.robotmedia.billing.helper.AbstractBillingActivity
    public void onRequestPurchaseResponse(String str, net.robotmedia.billing.k kVar) {
        Log.i("IAP", "IAP onRequestPurchaseResponse(): " + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        if (accelerometerEnabled) {
            accelerometer.enable();
        }
        if (isLoaded_ && !kDisableIAP) {
            com.tapjoy.g.a().a((com.tapjoy.ak) tapJoyDelegate_);
        }
        fbDelegate_.onResume();
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        adMobDelegate_.createBanner();
        cbDelegate_.onStart();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        cbDelegate_.onStop();
    }

    @Override // net.robotmedia.billing.helper.AbstractBillingActivity
    public void onSubscriptionChecked(boolean z) {
        Log.i("IAP", "IAP onSubscriptionChecked(): " + z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPackageName(String str) {
        packageName = str;
        try {
            String str2 = getApplication().getPackageManager().getApplicationInfo(str, 0).sourceDir;
            Log.w("apk path", str2);
            nativeSetPaths(str2);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to locate assets, aborting...");
        }
    }

    public int tryCB() {
        Log.d("BOOM", "TRY CB");
        if (cbDelegate_.hasCachedInterstitial()) {
            Log.d("MAIN", "SHOW INTERSTITIAL CB");
            cbDelegate_.showInterstitial();
            return 2;
        }
        return 0;
    }

    public int tryPH() {
        return 0;
    }

    public int tryTJ() {
        Log.d("BOOM", "TRY TJ");
        if (tapJoyDelegate_.hasCachedInterstitial()) {
            Log.d("MAIN", "SHOW INTERSTITIAL TJ");
            tapJoyDelegate_.showInterstitial();
            return 1;
        }
        return 0;
    }
}
