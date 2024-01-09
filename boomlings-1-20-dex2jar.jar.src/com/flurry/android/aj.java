package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;

abstract class aj {
  protected String a;
  
  aj(AdUnit paramAdUnit) {
    if (paramAdUnit != null)
      this.a = paramAdUnit.a().toString(); 
  }
  
  abstract void a(Context paramContext, ViewGroup paramViewGroup);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */