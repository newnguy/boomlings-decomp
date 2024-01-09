package com.flurry.android;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

final class bk extends aj {
  bk(AdUnit paramAdUnit) {
    super(paramAdUnit);
  }
  
  final void a(Context paramContext, ViewGroup paramViewGroup) {
    Intent intent = new Intent();
    intent.setClass(paramContext, FlurryFullscreenTakeoverActivity.class);
    intent.putExtra("frameIndex", 0);
    if (ac.a(paramContext, intent)) {
      paramContext.startActivity(intent);
      return;
    } 
    bm.b("FlurryAgent", "Unable to launch FlurryFullscreenTakeoverActivity. Fix by declaring this Activity in your AndroidManifest.xml");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\bk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */