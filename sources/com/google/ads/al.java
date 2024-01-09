package com.google.ads;

import com.flurry.android.AdCreative;
import java.util.HashMap;

/* loaded from: classes.dex */
final class al extends HashMap {
    /* JADX INFO: Access modifiers changed from: package-private */
    public al() {
        put(AdCreative.kFormatBanner, AdSize.BANNER);
        put("mrec", AdSize.IAB_MRECT);
        put("fullbanner", AdSize.IAB_BANNER);
        put("leaderboard", AdSize.IAB_LEADERBOARD);
        put("skyscraper", AdSize.IAB_WIDE_SKYSCRAPER);
    }
}
