package com.b.a;

import android.os.Bundle;
import android.webkit.CookieSyncManager;

class g implements i {
  final f a;
  
  g(f paramf) {}
  
  public void onCancel() {
    s.a("Facebook-authorize", "Login canceled");
    f.b(this.a).onCancel();
  }
  
  public void onComplete(Bundle paramBundle) {
    CookieSyncManager.getInstance().sync();
    this.a.a(paramBundle.getString("access_token"));
    this.a.b(paramBundle.getString("expires_in"));
    if (this.a.b()) {
      s.a("Facebook-authorize", "Login Success! access_token=" + this.a.c() + " expires=" + this.a.d());
      f.b(this.a).onComplete(paramBundle);
      return;
    } 
    f.b(this.a).onFacebookError(new m("Failed to receive access token."));
  }
  
  public void onError(e parame) {
    s.a("Facebook-authorize", "Login failed: " + parame);
    f.b(this.a).onError(parame);
  }
  
  public void onFacebookError(m paramm) {
    s.a("Facebook-authorize", "Login failed: " + paramm);
    f.b(this.a).onFacebookError(paramm);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\b\a\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */