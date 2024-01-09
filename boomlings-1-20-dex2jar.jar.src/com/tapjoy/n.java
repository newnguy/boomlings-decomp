package com.tapjoy;

import android.content.Context;
import android.content.Intent;

public class n {
  public static String a;
  
  private static p c;
  
  private static am d = null;
  
  private static String g;
  
  final String b = "Daily Reward";
  
  private Context e;
  
  private String f;
  
  public n(Context paramContext) {
    this.e = paramContext;
    d = new am();
  }
  
  public void a() {
    aj.a("Daily Reward", "Displaying Daily Reward ad...");
    if (g != null && g.length() != 0) {
      Intent intent = new Intent(this.e, TapjoyDailyRewardAdWebView.class);
      intent.setFlags(268435456);
      intent.putExtra("RE_ENGAGEMENT_HTML_DATA", g);
      this.e.startActivity(intent);
    } 
  }
  
  public void a(p paramp) {
    aj.a("Daily Reward", "Getting Daily Reward Ad");
    a(null, paramp);
  }
  
  public void a(String paramString, p paramp) {
    this.f = paramString;
    aj.a("Daily Reward", "Getting Daily Reward ad userID: " + h.e() + ", currencyID: " + this.f);
    c = paramp;
    a = h.c();
    a += "&publisher_user_id=" + h.e();
    if (this.f != null)
      a += "&currency_id=" + this.f; 
    (new Thread(new o(this))).start();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */