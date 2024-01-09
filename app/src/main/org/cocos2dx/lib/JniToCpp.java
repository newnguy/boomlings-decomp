package org.cocos2dx.lib;

/* loaded from: classes.dex */
public class JniToCpp {
    public static native void adColonyV4VCReward();

    public static native void awardGold(int i);

    public static native void didCacheInterstitial(String str);

    public static native void didClickInterstitial();

    public static native void didCloseInterstitial();

    public static native void didDismissInterstitial();

    public static native boolean gameLoaded();

    public static native void hideLoadingCircle();

    public static void invokeNativeCode() {
        nativeAdd();
    }

    public static native void itemPurchased(String str);

    public static native void itemRefunded(String str);

    private static native void nativeAdd();

    public static native void onFacebookError();

    public static native void onFacebookLogin();

    public static native void onFacebookLogout();

    public static native void onFacebookScoreSubmitted();

    public static native void onFacebookScoresRecieved(String str);

    public static native void onRefIDRecieved(String str, String str2);

    public static native void openSupportMail();

    public static native void promoImageDownloaded();

    public static native void resumeSound();

    public static native void setLoadError();

    public static native void setV4VCReward(int i);

    public static native void showInterstitialFailed();

    public static native void updateDeviceID(String str);

    public static native void updateUserID(String str);
}
