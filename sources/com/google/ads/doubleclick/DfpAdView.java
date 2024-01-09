package com.google.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.AppEventListener;

/* loaded from: classes.dex */
public class DfpAdView extends AdView {
    public DfpAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DfpAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.google.ads.AdView
    public void setAppEventListener(AppEventListener appEventListener) {
        super.setAppEventListener(appEventListener);
    }

    @Override // com.google.ads.AdView
    public void setSupportedAdSizes(AdSize... adSizeArr) {
        super.setSupportedAdSizes(adSizeArr);
    }
}
