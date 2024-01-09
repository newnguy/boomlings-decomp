package com.flurry.android;

import android.content.Context;
import android.content.Intent;

final class c extends an {
  private String a;
  
  private Context b;
  
  private boolean c;
  
  private bo d;
  
  c(bo parambo, String paramString, Context paramContext, boolean paramBoolean) {}
  
  public final void a() {
    if (this.a != null) {
      if (this.a.startsWith("market://")) {
        this.d.b(this.b, this.a);
        return;
      } 
      if (this.a.startsWith("http")) {
        Intent intent = new Intent(this.b, FlurryFullscreenTakeoverActivity.class);
        intent.putExtra("url", this.a);
        if (this.c && ac.a(this.b, intent)) {
          this.b.startActivity(intent);
          return;
        } 
        bm.b(bo.a, "Unable to launch FlurryFullscreenTakeoverActivity, falling back to browser. Fix by declaring this Activity in your AndroidManifest.xml");
        ac.a(this.b, this.a);
        return;
      } 
      if (!ac.a(this.b, this.a))
        bm.d(bo.a, "Failed to launch intent for:" + this.a); 
      return;
    } 
    String str = "Unable to launch intent for: " + this.a;
    bm.d(bo.a, str);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */