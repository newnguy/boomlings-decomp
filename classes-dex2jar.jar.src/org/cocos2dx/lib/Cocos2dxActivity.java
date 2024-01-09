package org.cocos2dx.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
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
import com.robtopx.boomlings.a;
import com.tapjoy.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import net.robotmedia.billing.a;
import net.robotmedia.billing.a.c;
import net.robotmedia.billing.a.d;
import net.robotmedia.billing.helper.AbstractBillingActivity;
import net.robotmedia.billing.k;

public class Cocos2dxActivity extends AbstractBillingActivity {
  private static final int HANDLER_SHOW_DIALOG = 1;
  
  private static Cocos2dxAccelerometer accelerometer;
  
  private static boolean accelerometerEnabled = false;
  
  private static a adMobDelegate_;
  
  private static ao backgroundMusicPlayer;
  
  private static boolean billingSupported_;
  
  public static f cbDelegate_;
  
  public static boolean countryIsFR_;
  
  public static as fbDelegate_;
  
  private static Handler handler;
  
  public static boolean isLoaded_;
  
  private static boolean kDisableIAP;
  
  private static Cocos2dxActivity me = null;
  
  private static String packageName;
  
  private static int prio0;
  
  private static int prio1;
  
  private static int prio2;
  
  private static int prio3;
  
  private static int prio4;
  
  private static int prioMod0;
  
  private static int prioMod1;
  
  private static int prioMod2;
  
  private static int prioMod3;
  
  private static int prioMod4;
  
  public static boolean showLoadingError_;
  
  private static ap soundPlayer;
  
  private static bb tapJoyDelegate_;
  
  public static boolean transactionsRestored_;
  
  static {
    kDisableIAP = false;
    isLoaded_ = false;
    billingSupported_ = false;
    countryIsFR_ = false;
    showLoadingError_ = false;
    prio0 = 0;
    prio1 = 1;
    prio2 = 2;
    prio3 = 3;
    prio4 = 4;
    prioMod0 = 1;
    prioMod1 = 30;
    prioMod2 = 0;
    prioMod3 = 0;
    prioMod4 = 0;
  }
  
  public static void appRequestFriends(String paramString) {
    me.runOnUiThread(new l(paramString));
  }
  
  public static void appRequestFriendsRefID(String paramString1, String paramString2) {
    me.runOnUiThread(new m(paramString1, paramString2));
  }
  
  private static String boomKey() {
    return "y27vyZlpIJk2C8wd";
  }
  
  public static void cacheInterstitial() {
    Log.d("MAIN", "Cache interstitial");
    cbDelegate_.cacheInterstitial();
  }
  
  public static void cacheInterstitial(String paramString) {
    cbDelegate_.cacheInterstitial(paramString);
  }
  
  public static void decryptFile(String paramString) {
    File file1 = new File(String.valueOf(paramString) + "x");
    File file2 = new File(paramString);
    if (file1.exists()) {
      if (!file2.exists())
        try {
          file2.createNewFile();
        } catch (IOException iOException) {} 
      try {
        FileInputStream fileInputStream = new FileInputStream();
        this(file1);
        FileOutputStream fileOutputStream = new FileOutputStream();
        this(file2);
        SecretKeySpec secretKeySpec = new SecretKeySpec();
        this(boomKey().getBytes(), "AES/ECB/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(2, secretKeySpec);
        CipherInputStream cipherInputStream = new CipherInputStream();
        this(fileInputStream, cipher);
        byte[] arrayOfByte = new byte[8];
        while (true) {
          int i = cipherInputStream.read(arrayOfByte);
          if (i == -1) {
            fileOutputStream.flush();
            fileOutputStream.close();
            cipherInputStream.close();
            return;
          } 
          fileOutputStream.write(arrayOfByte, 0, i);
        } 
      } catch (Exception exception) {}
    } 
  }
  
  public static void decryptFileSpecial(String paramString) {
    File file1 = new File(String.valueOf(paramString) + "x");
    File file2 = new File(paramString);
    if (file1.exists()) {
      if (!file2.exists())
        try {
          file2.createNewFile();
        } catch (IOException iOException) {} 
      try {
        FileInputStream fileInputStream = new FileInputStream();
        this(file1);
        FileOutputStream fileOutputStream = new FileOutputStream();
        this(file2);
        SecretKeySpec secretKeySpec = new SecretKeySpec();
        this(boomKey().getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, secretKeySpec);
        CipherInputStream cipherInputStream = new CipherInputStream();
        this(fileInputStream, cipher);
        byte[] arrayOfByte = new byte[8];
        while (true) {
          int i = cipherInputStream.read(arrayOfByte);
          if (i == -1) {
            fileOutputStream.flush();
            fileOutputStream.close();
            cipherInputStream.close();
            return;
          } 
          fileOutputStream.write(arrayOfByte, 0, i);
        } 
      } catch (Exception exception) {}
    } 
  }
  
  public static void disableAccelerometer() {
    accelerometerEnabled = false;
    accelerometer.disable();
  }
  
  public static void disableBanner() {
    adMobDelegate_.disableBanner();
  }
  
  public static boolean doesFileExist(String paramString) {
    return (new File(paramString)).exists();
  }
  
  public static void downloadImage(String paramString1, String paramString2, String paramString3) {
    (new Thread(new q(paramString3, paramString2, paramString1))).start();
  }
  
  public static void enableAccelerometer() {
    accelerometerEnabled = true;
    accelerometer.enable();
  }
  
  public static void enableBanner() {
    adMobDelegate_.enableBanner();
  }
  
  public static void encryptFile(String paramString) {
    File file2 = new File(paramString);
    File file1 = new File(String.valueOf(paramString) + "x1");
    File file3 = new File(String.valueOf(paramString) + "x");
    if (file2.exists()) {
      if (!file1.exists())
        file1.createNewFile(); 
      FileInputStream fileInputStream = new FileInputStream(file2);
      FileOutputStream fileOutputStream = new FileOutputStream(file1);
      SecretKeySpec secretKeySpec = new SecretKeySpec(boomKey().getBytes(), "AES/ECB/PKCS5Padding");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(1, secretKeySpec);
      CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
      byte[] arrayOfByte = new byte[8];
      while (true) {
        int i = fileInputStream.read(arrayOfByte);
        if (i == -1) {
          cipherOutputStream.flush();
          cipherOutputStream.close();
          fileInputStream.close();
          file2.delete();
          file3.delete();
          file1.renameTo(file3);
          return;
        } 
        cipherOutputStream.write(arrayOfByte, 0, i);
      } 
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
    TelephonyManager telephonyManager = (TelephonyManager)me.getBaseContext().getSystemService("phone");
    String str1 = telephonyManager.getDeviceId();
    String str2 = telephonyManager.getSimSerialNumber();
    JniToCpp.updateDeviceID((new UUID(Settings.Secure.getString(me.getContentResolver(), "android_id").hashCode(), str1.hashCode() << 32L | str2.hashCode())).toString());
  }
  
  public static float getEffectsVolume() {
    return soundPlayer.getEffectsVolume();
  }
  
  public static void getFacebookScores() {
    (new Thread(new p())).start();
  }
  
  public static String getFacebookUserId() {
    return fbDelegate_.getFacebookUserId();
  }
  
  public static void getImageForUserId(String paramString) {
    fbDelegate_.getImageForUserId(paramString);
  }
  
  public static void getTapPoints() {
    if (isLoaded_ && !kDisableIAP) {
      Log.d("TAPJOY", "GETTAPPOINTS");
      g.a().a(tapJoyDelegate_);
    } 
  }
  
  public static void getUserID() {
    TelephonyManager telephonyManager = (TelephonyManager)me.getBaseContext().getSystemService("phone");
    String str1 = telephonyManager.getDeviceId();
    String str2 = telephonyManager.getSimSerialNumber();
    JniToCpp.updateUserID((new UUID(Settings.Secure.getString(me.getContentResolver(), "android_id").hashCode(), str1.hashCode() << 32L | str2.hashCode())).toString());
  }
  
  public static boolean hasCachedInterstitial(String paramString) {
    return cbDelegate_.hasCachedInterstitial(paramString);
  }
  
  public static boolean isBackgroundMusicPlaying() {
    return backgroundMusicPlayer.isBackgroundMusicPlaying();
  }
  
  public static boolean isBillingSupported() {
    return kDisableIAP ? false : billingSupported_;
  }
  
  public static boolean isLoggedInFacebook() {
    return fbDelegate_.isLoggedIn();
  }
  
  public static boolean isNetworkAvailable() {
    boolean bool3 = true;
    NetworkInfo[] arrayOfNetworkInfo = ((ConnectivityManager)me.getSystemService("connectivity")).getAllNetworkInfo();
    int i = arrayOfNetworkInfo.length;
    byte b = 0;
    boolean bool1 = false;
    boolean bool2 = false;
    while (true) {
      if (b >= i) {
        boolean bool = bool3;
        if (!bool2) {
          bool = bool3;
          if (!bool1)
            bool = false; 
        } 
        return bool;
      } 
      NetworkInfo networkInfo = arrayOfNetworkInfo[b];
      boolean bool4 = bool2;
      if (networkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
        bool4 = bool2;
        if (networkInfo.isConnected())
          bool4 = true; 
      } 
      boolean bool5 = bool1;
      if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
        bool5 = bool1;
        if (networkInfo.isConnected())
          bool5 = true; 
      } 
      b++;
      bool2 = bool4;
      bool1 = bool5;
    } 
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
  
  private static native void nativeSetPaths(String paramString);
  
  public static boolean onBackKeyPressed() {
    return (cbDelegate_ == null) ? false : cbDelegate_.onBackPressed();
  }
  
  public static void onBoomPause() {
    pauseAds();
  }
  
  public static void onBoomResume() {
    resumeAds();
  }
  
  public static void onGameLaunch() {}
  
  public static void openAppPage() {
    me.runOnUiThread(new x());
  }
  
  public static void openURL(String paramString) {
    Log.d("MAIN", "Open URL");
    me.runOnUiThread(new s(paramString));
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
  
  public static void pauseEffect(int paramInt) {
    soundPlayer.pauseEffect(paramInt);
  }
  
  public static void performQueuedFBAction() {
    me.runOnUiThread(new aa());
  }
  
  public static void playBackgroundMusic(String paramString, boolean paramBoolean) {
    backgroundMusicPlayer.playBackgroundMusic(paramString, paramBoolean);
  }
  
  public static int playEffect(String paramString, boolean paramBoolean, float paramFloat1, float paramFloat2, float paramFloat3) {
    return soundPlayer.playEffect(paramString, paramBoolean, paramFloat1, paramFloat2, paramFloat3);
  }
  
  public static void postScore(String paramString) {
    (new Thread(new o(paramString))).start();
  }
  
  public static void postToWall(String paramString1, String paramString2, String paramString3, String paramString4) {
    me.runOnUiThread(new n(paramString1, paramString2, paramString3, paramString4));
  }
  
  public static void preloadBackgroundMusic(String paramString) {
    backgroundMusicPlayer.preloadBackgroundMusic(paramString);
  }
  
  public static void preloadEffect(String paramString) {
    soundPlayer.preloadEffect(paramString);
  }
  
  public static void purchaseItem(String paramString) {
    Log.i("IAP", "IAP Purchase item: " + paramString);
    a.a((Context)me, paramString, true, null);
  }
  
  public static void removeFile(String paramString) {
    File file = new File(paramString);
    if (file.exists())
      file.delete(); 
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
  
  public static void resumeEffect(int paramInt) {
    soundPlayer.resumeEffect(paramInt);
  }
  
  public static void rewindBackgroundMusic() {
    backgroundMusicPlayer.rewindBackgroundMusic();
  }
  
  public static void sendMail(String paramString1, String paramString2, String paramString3) {
    me.runOnUiThread(new z(paramString3, paramString1, paramString2));
  }
  
  public static void setBackgroundMusicVolume(float paramFloat) {
    backgroundMusicPlayer.setBackgroundVolume(paramFloat);
  }
  
  public static void setEffectsVolume(float paramFloat) {
    soundPlayer.setEffectsVolume(paramFloat);
  }
  
  public static boolean shouldShowPromo() {
    return countryIsFR_;
  }
  
  public static void showDailyReward() {
    tapJoyDelegate_.showDailyReward();
  }
  
  private void showDialog(String paramString1, String paramString2) {
    (new AlertDialog.Builder((Context)this)).setTitle(paramString1).setMessage(paramString2).setPositiveButton("Ok", new t(this)).create().show();
  }
  
  public static int showInterstitial() {
    int i = me.tryCB();
    cacheInterstitial();
    Log.d("MAIN", "SHOW INTERSTITIAL: " + i);
    return i;
  }
  
  public static void showInterstitial(String paramString) {
    cbDelegate_.showInterstitial(paramString);
  }
  
  public static void showLoadError() {
    if (showLoadingError_) {
      showLoadingError_ = false;
      me.runOnUiThread(new u());
    } 
  }
  
  public static void showMessageBox(String paramString1, String paramString2) {
    Message message = new Message();
    message.what = 1;
    message.obj = new ar(paramString1, paramString2);
    handler.sendMessage(message);
  }
  
  public static void showTapJoyOffers() {
    if (!kDisableIAP)
      g.a().b(); 
  }
  
  public static void stopAllEffects() {
    soundPlayer.stopAllEffects();
  }
  
  public static void stopBackgroundMusic() {
    backgroundMusicPlayer.stopBackgroundMusic();
  }
  
  public static void stopEffect(int paramInt) {
    soundPlayer.stopEffect(paramInt);
  }
  
  public static void terminateProcess() {
    Process.killProcess(Process.myPid());
  }
  
  public static void testInterstitialPrio() {
    // Byte code:
    //   0: iconst_5
    //   1: iconst_2
    //   2: multianewarray[[I 2
    //   6: astore_3
    //   7: iconst_0
    //   8: istore_1
    //   9: iload_1
    //   10: iconst_5
    //   11: if_icmplt -> 33
    //   14: aload_3
    //   15: new org/cocos2dx/lib/r
    //   18: dup
    //   19: invokespecial <init> : ()V
    //   22: invokestatic sort : ([Ljava/lang/Object;Ljava/util/Comparator;)V
    //   25: iconst_0
    //   26: istore_0
    //   27: iload_0
    //   28: iconst_5
    //   29: if_icmplt -> 144
    //   32: return
    //   33: iload_1
    //   34: tableswitch default -> 68, 0 -> 109, 1 -> 116, 2 -> 123, 3 -> 130, 4 -> 137
    //   68: iconst_1
    //   69: istore_0
    //   70: aload_3
    //   71: iload_1
    //   72: aaload
    //   73: iconst_0
    //   74: iload_1
    //   75: iastore
    //   76: iload_0
    //   77: i2d
    //   78: invokestatic random : ()D
    //   81: dmul
    //   82: invokestatic round : (D)J
    //   85: l2i
    //   86: istore_2
    //   87: iload_2
    //   88: ifne -> 273
    //   91: iload_0
    //   92: ifle -> 273
    //   95: iconst_1
    //   96: istore_0
    //   97: aload_3
    //   98: iload_1
    //   99: aaload
    //   100: iconst_1
    //   101: iload_0
    //   102: iastore
    //   103: iinc #1, 1
    //   106: goto -> 9
    //   109: getstatic org/cocos2dx/lib/Cocos2dxActivity.prioMod0 : I
    //   112: istore_0
    //   113: goto -> 70
    //   116: getstatic org/cocos2dx/lib/Cocos2dxActivity.prioMod1 : I
    //   119: istore_0
    //   120: goto -> 70
    //   123: getstatic org/cocos2dx/lib/Cocos2dxActivity.prioMod2 : I
    //   126: istore_0
    //   127: goto -> 70
    //   130: getstatic org/cocos2dx/lib/Cocos2dxActivity.prioMod3 : I
    //   133: istore_0
    //   134: goto -> 70
    //   137: getstatic org/cocos2dx/lib/Cocos2dxActivity.prioMod4 : I
    //   140: istore_0
    //   141: goto -> 70
    //   144: aload_3
    //   145: iload_0
    //   146: aaload
    //   147: iconst_0
    //   148: iaload
    //   149: istore_1
    //   150: aload_3
    //   151: iload_0
    //   152: aaload
    //   153: iconst_1
    //   154: iaload
    //   155: istore_2
    //   156: ldc_w 'CHANCE'
    //   159: new java/lang/StringBuilder
    //   162: dup
    //   163: ldc_w 'PRIO: '
    //   166: invokespecial <init> : (Ljava/lang/String;)V
    //   169: iload_1
    //   170: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   173: ldc_w ' chance: '
    //   176: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: iload_2
    //   180: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   183: invokevirtual toString : ()Ljava/lang/String;
    //   186: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   189: pop
    //   190: iload_2
    //   191: ifgt -> 196
    //   194: iconst_m1
    //   195: istore_1
    //   196: iload_0
    //   197: tableswitch default -> 232, 0 -> 238, 1 -> 245, 2 -> 252, 3 -> 259, 4 -> 266
    //   232: iinc #0, 1
    //   235: goto -> 27
    //   238: iload_1
    //   239: putstatic org/cocos2dx/lib/Cocos2dxActivity.prio0 : I
    //   242: goto -> 232
    //   245: iload_1
    //   246: putstatic org/cocos2dx/lib/Cocos2dxActivity.prio1 : I
    //   249: goto -> 232
    //   252: iload_1
    //   253: putstatic org/cocos2dx/lib/Cocos2dxActivity.prio2 : I
    //   256: goto -> 232
    //   259: iload_1
    //   260: putstatic org/cocos2dx/lib/Cocos2dxActivity.prio3 : I
    //   263: goto -> 232
    //   266: iload_1
    //   267: putstatic org/cocos2dx/lib/Cocos2dxActivity.prio4 : I
    //   270: goto -> 232
    //   273: iload_2
    //   274: istore_0
    //   275: goto -> 97
  }
  
  public static void tryShowRateDialog() {
    me.runOnUiThread(new y());
  }
  
  public static void unloadEffect(String paramString) {
    soundPlayer.unloadEffect(paramString);
  }
  
  public static void updateAdPrio(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    prioMod0 = paramInt1;
    prioMod1 = paramInt2;
    prioMod2 = paramInt3;
    prioMod3 = paramInt4;
    prioMod4 = paramInt5;
  }
  
  private void updateOwnedItems() {
    List list = a.c((Context)this);
    Log.i("IAP", "IAP Update owned items");
    Iterator<c> iterator = list.iterator();
    while (true) {
      if (!iterator.hasNext()) {
        Iterator<c> iterator1 = list.iterator();
        while (true) {
          if (!iterator1.hasNext())
            return; 
          a.c((Context)this, iterator1.next());
        } 
        break;
      } 
      c c = iterator.next();
      Log.i("IAP", "IAP: " + c.e);
      if (c.f == d.a) {
        JniToCpp.itemPurchased(c.e);
        continue;
      } 
      if (c.f == d.c)
        JniToCpp.itemRefunded(c.e); 
    } 
  }
  
  public int callInterstitial(int paramInt) {
    switch (paramInt) {
      default:
        return 0;
      case 0:
        return me.tryTJ();
      case 1:
        return me.tryCB();
      case 2:
        break;
    } 
    return me.tryPH();
  }
  
  public void checkDeviceCountry() {
    String str2 = ((TelephonyManager)me.getBaseContext().getSystemService("phone")).getNetworkOperator();
    String str1 = str2;
    if (str2.length() >= 4)
      str1 = str2.substring(0, 3); 
    str2 = (me.getResources().getConfiguration()).locale.getCountry();
    if (str1.equals("208") || str2.equals("FR"))
      countryIsFR_ = true; 
  }
  
  public byte[] getObfuscationSalt() {
    return new byte[] { 
        40, -92, -112, -31, 52, -43, 122, -120, -112, -86, 
        -87, 75, 123, 105, 2, 71, 55, 108, 50, -112 };
  }
  
  public String getPublicKey() {
    return "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj5ns81HIg1bXgBl4/ftMRD75LqUaM9TsYUBA3edPPPqNgu1YPDUSjnSQlVndpQRCBWUjQ7teuTjzK+VZTZErLu4uaoaWSplbJ7dAu+TyVJRI81K/RvwM/uFU4chgk628GpnoGqh7eNTG29Ol1FO/Q1KoQA1SXuJbwvdkiSxry4pTPgpdnGaOfs3DyBADe2jLKvRD6zS4o0NaM1w/NLEl5GytayK25oLM6s0gdbpqc8voX8bo8GDHTJwiLlnP6paRe2KkY1tFdifP4bzoEVR7i3v0zxq40Zku5W3tZJepGIg0tJVzvy9eRgxSpGycNdRITRoHLvpvyd1O7PvFajl4fQIDAQAB";
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    fbDelegate_.onActivityResult(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed() {
    if (!onBackKeyPressed())
      super.onBackPressed(); 
  }
  
  public void onBillingChecked(boolean paramBoolean) {
    if (paramBoolean) {
      Log.i("IAP", "IAP Billing Supported!");
    } else {
      Log.i("IAP", "IAP Billing NOT Supported!");
    } 
    billingSupported_ = paramBoolean;
  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    accelerometer = new Cocos2dxAccelerometer((Context)this);
    if (backgroundMusicPlayer != null) {
      backgroundMusicPlayer.mContext = (Context)this;
    } else {
      backgroundMusicPlayer = new ao((Context)this);
    } 
    if (soundPlayer != null) {
      soundPlayer.mContext = (Context)this;
    } else {
      soundPlayer = new ap((Context)this);
    } 
    Cocos2dxBitmap.setContext((Context)this);
    handler = new i(this);
    setContentView(2130903040);
    if (me == null)
      a.a((Context)this); 
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
  
  protected void onDestroy() {
    g.a().e();
    adMobDelegate_.onDestroy();
    cbDelegate_.onDestroy();
    super.onDestroy();
  }
  
  protected void onPause() {
    super.onPause();
    if (accelerometerEnabled)
      accelerometer.disable(); 
    g.a().a(false);
  }
  
  public void onPurchaseStateChanged(String paramString, d paramd) {
    Log.i("IAP", "IAP onPurchaseStateChanged() itemId: " + paramString);
    updateOwnedItems();
  }
  
  public void onRequestPurchaseResponse(String paramString, k paramk) {
    Log.i("IAP", "IAP onRequestPurchaseResponse(): " + paramString);
  }
  
  protected void onResume() {
    super.onResume();
    if (accelerometerEnabled)
      accelerometer.enable(); 
    if (isLoaded_ && !kDisableIAP)
      g.a().a(tapJoyDelegate_); 
    fbDelegate_.onResume();
  }
  
  public void onStart() {
    super.onStart();
    adMobDelegate_.createBanner();
    cbDelegate_.onStart();
  }
  
  public void onStop() {
    super.onStop();
    cbDelegate_.onStop();
  }
  
  public void onSubscriptionChecked(boolean paramBoolean) {
    Log.i("IAP", "IAP onSubscriptionChecked(): " + paramBoolean);
  }
  
  protected void setPackageName(String paramString) {
    packageName = paramString;
    PackageManager packageManager = getApplication().getPackageManager();
    try {
      ApplicationInfo applicationInfo = packageManager.getApplicationInfo(paramString, 0);
      String str = applicationInfo.sourceDir;
      Log.w("apk path", str);
      nativeSetPaths(str);
      return;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      nameNotFoundException.printStackTrace();
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


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\org\cocos2dx\lib\Cocos2dxActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */