package com.google.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.AppEventListener;

public class DfpAdView extends AdView {
  public DfpAdView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
  }
  
  public DfpAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener) {
    super.setAppEventListener(paramAppEventListener);
  }
  
  public void setSupportedAdSizes(AdSize... paramVarArgs) {
    super.setSupportedAdSizes(paramVarArgs);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\doubleclick\DfpAdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */