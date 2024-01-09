package com.flurry.android;

import android.content.Context;
import android.widget.VideoView;

final class az extends VideoView {
  public az(Context paramContext) {
    super(paramContext);
    setFocusable(true);
    setFocusableInTouchMode(true);
  }
  
  public final void seekTo(int paramInt) {
    if (paramInt < getCurrentPosition())
      super.seekTo(paramInt); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */