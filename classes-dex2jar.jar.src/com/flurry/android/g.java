package com.flurry.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import com.jumptap.adtag.JtAdInterstitial;
import com.jumptap.adtag.JtAdView;
import com.jumptap.adtag.JtAdWidgetSettings;
import com.jumptap.adtag.JtAdWidgetSettingsFactory;
import com.jumptap.adtag.utils.JtException;

final class g extends AdNetworkView {
  g(Context paramContext, bo parambo, bl parambl, AdCreative paramAdCreative) {
    super(paramContext, parambo, parambl, paramAdCreative);
    bo bo1;
    parambo = null;
    try {
      ApplicationInfo applicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.d("FlurryAgent", "Cannot find manifest for app");
      bo1 = parambo;
    } 
    String str = ((ApplicationInfo)bo1).metaData.getString("com.flurry.jumptap.PUBLISHER_ID");
    sAdNetworkApiKey = str;
    if (str == null)
      Log.d("FlurryAgent", "com.flurry.jumptap.PUBLISHER_ID not set in manifest"); 
    setFocusable(true);
  }
  
  public final void initLayout(Context paramContext) {
    JtAdInterstitial jtAdInterstitial1;
    JtAdInterstitial jtAdInterstitial2 = null;
    if (this.fAdCreative.getFormat().equals("takeover")) {
      JtAdWidgetSettings jtAdWidgetSettings1 = JtAdWidgetSettingsFactory.createWidgetSettings();
      jtAdWidgetSettings1.setPublisherId(sAdNetworkApiKey);
      jtAdWidgetSettings1.setRefreshPeriod(0);
      jtAdWidgetSettings1.setShouldSendLocation(false);
      try {
        jtAdInterstitial2 = new JtAdInterstitial();
        this(paramContext, jtAdWidgetSettings1);
        jtAdInterstitial1 = jtAdInterstitial2;
      } catch (JtException jtException) {
        Log.d("FlurryAgent", "Jumptap JtException when creating ad object.");
        jtException = null;
      } 
      jtException.setAdViewListener(new aw(this));
      jtException.showAsPopup();
      return;
    } 
    JtAdWidgetSettings jtAdWidgetSettings = JtAdWidgetSettingsFactory.createWidgetSettings();
    jtAdWidgetSettings.setPublisherId(sAdNetworkApiKey);
    jtAdWidgetSettings.setRefreshPeriod(0);
    jtAdWidgetSettings.setShouldSendLocation(false);
    try {
      JtAdView jtAdView2 = new JtAdView();
      this((Context)jtException, jtAdWidgetSettings);
      JtAdView jtAdView1 = jtAdView2;
    } catch (JtException jtException1) {
      Log.d("FlurryAgent", "Jumptap JtException when creating ad object.");
      jtAdInterstitial1 = jtAdInterstitial2;
    } 
    jtAdInterstitial1.setAdViewListener(new w(this));
    addView((View)jtAdInterstitial1);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */