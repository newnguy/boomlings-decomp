package com.flurry.android;

import android.content.Context;
import java.util.Map;

public abstract class AdNetworkView extends r {
  public static String sAdNetworkApiKey;
  
  public static String sAdNetworkApiKey2;
  
  public AdCreative fAdCreative;
  
  public AdNetworkView(Context paramContext, AdCreative paramAdCreative) {
    super(paramContext);
    this.fAdCreative = paramAdCreative;
  }
  
  AdNetworkView(Context paramContext, bo parambo, bl parambl, AdCreative paramAdCreative) {
    super(paramContext, parambo, parambl);
    this.fAdCreative = paramAdCreative;
  }
  
  public void onAdClicked(Map paramMap) {
    a("clicked", paramMap);
  }
  
  public void onAdClosed(Map paramMap) {
    a("adClosed", paramMap);
  }
  
  public void onAdFilled(Map paramMap) {
    a("filled", paramMap);
  }
  
  public void onAdShown(Map paramMap) {
    a("rendered", paramMap);
  }
  
  public void onAdUnFilled(Map paramMap) {
    a("unfilled", paramMap);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\AdNetworkView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */