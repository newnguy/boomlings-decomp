package com.google.ads;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.google.ads.internal.d;
import com.google.ads.internal.e;
import com.google.ads.util.b;

public class AdActivity$StaticMethodWrapper {
  public void a(d paramd, e parame) {
    synchronized (AdActivity.d()) {
      if (AdActivity.f() == null) {
        AdActivity.b(paramd);
      } else if (AdActivity.f() != paramd) {
        b.b("Tried to launch a new AdActivity with a different AdManager.");
        return;
      } 
      null = (paramd.h()).e.a();
      if (null == null) {
        b.e("activity was null while launching an AdActivity.");
        return;
      } 
    } 
    Intent intent = new Intent(SYNTHETIC_LOCAL_VARIABLE_3.getApplicationContext(), AdActivity.class);
    intent.putExtra("com.google.ads.AdOpener", parame.a());
    try {
      b.a("Launching AdActivity.");
      SYNTHETIC_LOCAL_VARIABLE_3.startActivity(intent);
    } catch (ActivityNotFoundException activityNotFoundException) {
      b.b("Activity not found.", (Throwable)activityNotFoundException);
    } 
  }
  
  public boolean a() {
    synchronized (AdActivity.d()) {
      if (AdActivity.e() != null)
        return true; 
      return false;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\AdActivity$StaticMethodWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */