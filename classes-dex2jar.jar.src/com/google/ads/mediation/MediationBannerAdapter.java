package com.google.ads.mediation;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdSize;

public interface MediationBannerAdapter extends MediationAdapter {
  void a(MediationBannerListener paramMediationBannerListener, Activity paramActivity, MediationServerParameters paramMediationServerParameters, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, NetworkExtras paramNetworkExtras);
  
  View d();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\mediation\MediationBannerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */