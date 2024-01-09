package com.google.ads.mediation.customevent;

import android.view.View;
import com.google.ads.mediation.MediationBannerListener;

class a implements CustomEventBannerListener {
  final CustomEventAdapter a;
  
  private View b;
  
  private final MediationBannerListener c;
  
  public a(CustomEventAdapter paramCustomEventAdapter, MediationBannerListener paramMediationBannerListener) {
    this.c = paramMediationBannerListener;
  }
  
  public View a() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield b : Landroid/view/View;
    //   6: astore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: aload_1
    //   10: areturn
    //   11: astore_1
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_1
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\mediation\customevent\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */