package com.flurry.android;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public final class bk extends aj {
    public bk(AdUnit adUnit) {
        super(adUnit);
    }

    @Override // com.flurry.android.aj
    public final void a(Context context, ViewGroup viewGroup) {
        Intent intent = new Intent();
        intent.setClass(context, FlurryFullscreenTakeoverActivity.class);
        intent.putExtra("frameIndex", 0);
        if (ac.a(context, intent)) {
            context.startActivity(intent);
        } else {
            bm.b("FlurryAgent", "Unable to launch FlurryFullscreenTakeoverActivity. Fix by declaring this Activity in your AndroidManifest.xml");
        }
    }
}
