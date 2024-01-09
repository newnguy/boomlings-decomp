package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.d;
import com.google.ads.internal.e;
import com.google.ads.util.b;
import java.util.HashMap;

public class z implements n {
  private AdActivity$StaticMethodWrapper a;
  
  public z() {
    this(new AdActivity$StaticMethodWrapper());
  }
  
  public z(AdActivity$StaticMethodWrapper paramAdActivity$StaticMethodWrapper) {
    this.a = paramAdActivity$StaticMethodWrapper;
  }
  
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    String str = (String)paramHashMap.get("a");
    if (str == null) {
      b.a("Could not get the action parameter for open GMSG.");
      return;
    } 
    if (str.equals("webapp")) {
      this.a.a(paramd, new e("webapp", paramHashMap));
      return;
    } 
    if (str.equals("expand")) {
      this.a.a(paramd, new e("expand", paramHashMap));
      return;
    } 
    this.a.a(paramd, new e("intent", paramHashMap));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\z.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */