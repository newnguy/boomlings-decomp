package com.google.ads;

import com.google.ads.util.i;

/* loaded from: classes.dex */
public class l extends com.google.ads.util.i {
    private static final l b = new l();
    public final i.b a = new i.b("constants", new a());

    /* loaded from: classes.dex */
    public final class a extends com.google.ads.util.i {
        public final i.c a = new i.c("minHwAccelerationVersionBanner", 17);
        public final i.c b = new i.c("minHwAccelerationVersionOverlay", 14);
        public final i.c c = new i.c("mraidBannerPath", "http://media.admob.com/mraid/v1/mraid_app_banner.js");
        public final i.c d = new i.c("mraidExpandedBannerPath", "http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js");
        public final i.c e = new i.c("mraidInterstitialPath", "http://media.admob.com/mraid/v1/mraid_app_interstitial.js");
        public final i.c f = new i.c("appCacheMaxSize", 0L);
        public final i.c g = new i.c("appCacheMaxSizePaddingInBytes", 131072L);
        public final i.c h = new i.c("maxTotalAppCacheQuotaInBytes", 5242880L);
        public final i.c i = new i.c("maxTotalDatabaseQuotaInBytes", 5242880L);
        public final i.c j = new i.c("maxDatabaseQuotaPerOriginInBytes", 1048576L);
        public final i.c k = new i.c("databaseQuotaIncreaseStepInBytes", 131072L);
        public final i.c l = new i.c("isInitialized", false);
    }

    private l() {
    }

    public static l a() {
        return b;
    }
}
