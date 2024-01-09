package com.tapjoy;

import android.content.DialogInterface;

class ax implements DialogInterface.OnClickListener {
  final TapjoyVideoView a;
  
  ax(TapjoyVideoView paramTapjoyVideoView) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    paramDialogInterface.dismiss();
    TapjoyVideoView.f(this.a).seekTo(TapjoyVideoView.e(this.a));
    TapjoyVideoView.f(this.a).start();
    TapjoyVideoView.b(this.a, false);
    aj.a("VIDEO", "RESUME VIDEO time: " + TapjoyVideoView.e(this.a));
    aj.a("VIDEO", "currentPosition: " + TapjoyVideoView.f(this.a).getCurrentPosition());
    aj.a("VIDEO", "duration: " + TapjoyVideoView.f(this.a).getDuration() + ", elapsed: " + (TapjoyVideoView.f(this.a).getDuration() - TapjoyVideoView.f(this.a).getCurrentPosition()));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */