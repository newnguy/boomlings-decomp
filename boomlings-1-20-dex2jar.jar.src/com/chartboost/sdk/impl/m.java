package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.chartboost.sdk.Chartboost;
import java.net.URI;
import java.net.URISyntaxException;

public class m {
  public m$a a;
  
  public m(m$a paramm$a) {
    this.a = paramm$a;
  }
  
  private void b(String paramString, Context paramContext) {
    if (this.a != null)
      this.a.a(paramString); 
    Context context = paramContext;
    if (paramContext == null)
      context = Chartboost.sharedChartboost().getContext(); 
    if (context != null)
      try {
        Intent intent = new Intent();
        this("android.intent.action.VIEW");
        if (!(context instanceof android.app.Activity))
          intent.addFlags(268435456); 
        intent.setData(Uri.parse(paramString));
        context.startActivity(intent);
      } catch (Exception exception) {} 
  }
  
  public void a(String paramString, Context paramContext) {
    try {
      URI uRI = new URI();
      this(paramString);
      String str = uRI.getScheme();
      if (str != null) {
        if (!str.equals("http") && !str.equals("https")) {
          b(paramString, paramContext);
          return;
        } 
        (new m$1(this, paramString, paramContext)).execute((Object[])new Void[0]);
      } 
    } catch (URISyntaxException uRISyntaxException) {}
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */