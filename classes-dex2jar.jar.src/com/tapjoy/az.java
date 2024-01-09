package com.tapjoy;

import android.content.DialogInterface;

class az implements DialogInterface.OnCancelListener {
  final TapjoyVideoView a;
  
  az(TapjoyVideoView paramTapjoyVideoView) {}
  
  public void onCancel(DialogInterface paramDialogInterface) {
    aj.a("VIDEO", "dialog onCancel");
    paramDialogInterface.dismiss();
    TapjoyVideoView.f(this.a).seekTo(TapjoyVideoView.e(this.a));
    TapjoyVideoView.f(this.a).start();
    TapjoyVideoView.b(this.a, false);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */