package com.chartboost.sdk.Analytics;

import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.impl.j;
import com.chartboost.sdk.impl.k;
import java.math.BigDecimal;
import java.util.Map;
import org.json.JSONObject;

public class CBAnalytics {
  public static final String TAG = "Chartboost Analytics";
  
  private static CBAnalytics a = null;
  
  private j b = new j(null, null, "CBAnalytics");
  
  private CBAnalytics() {
    this.b.a();
  }
  
  private String a(double paramDouble, int paramInt1, int paramInt2) {
    return (new StringBuilder(String.valueOf((new BigDecimal(paramDouble)).setScale(paramInt1, paramInt2).doubleValue()))).toString();
  }
  
  public static CBAnalytics sharedAnalytics() {
    // Byte code:
    //   0: ldc com/chartboost/sdk/Analytics/CBAnalytics
    //   2: monitorenter
    //   3: getstatic com/chartboost/sdk/Analytics/CBAnalytics.a : Lcom/chartboost/sdk/Analytics/CBAnalytics;
    //   6: ifnonnull -> 21
    //   9: new com/chartboost/sdk/Analytics/CBAnalytics
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/chartboost/sdk/Analytics/CBAnalytics.a : Lcom/chartboost/sdk/Analytics/CBAnalytics;
    //   21: getstatic com/chartboost/sdk/Analytics/CBAnalytics.a : Lcom/chartboost/sdk/Analytics/CBAnalytics;
    //   24: astore_0
    //   25: ldc com/chartboost/sdk/Analytics/CBAnalytics
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/chartboost/sdk/Analytics/CBAnalytics
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  public Boolean recordPaymentTransaction(String paramString1, String paramString2, double paramDouble, String paramString3, int paramInt, Map paramMap) {
    Chartboost chartboost = Chartboost.sharedChartboost();
    if (chartboost.getContext() == null)
      throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling recordPaymentTransaction()."); 
    if (chartboost.getAppID() == null || chartboost.getAppSignature() == null)
      return Boolean.valueOf(false); 
    k k = new k("api", "purchase");
    k.a(chartboost.getContext());
    k.a("product_id", paramString1);
    k.a("title", paramString2);
    k.a("price", a(paramDouble, 2, 4));
    k.a("currency", paramString3);
    k.a("quantity", (new StringBuilder(String.valueOf(paramInt))).toString());
    k.a("timestamp", (new StringBuilder(String.valueOf(System.currentTimeMillis() / 1000.0D))).toString());
    if (paramMap != null)
      k.a("meta", (new JSONObject(paramMap)).toString()); 
    k.c(chartboost.getAppID(), chartboost.getAppSignature());
    this.b.a(k);
    return Boolean.valueOf(true);
  }
  
  public Boolean trackEvent(String paramString) {
    return trackEvent(paramString, 1.0D, null);
  }
  
  public Boolean trackEvent(String paramString, double paramDouble) {
    return trackEvent(paramString, paramDouble, null);
  }
  
  public Boolean trackEvent(String paramString, double paramDouble, Map paramMap) {
    Chartboost chartboost = Chartboost.sharedChartboost();
    if (chartboost.getContext() == null)
      throw new IllegalStateException("The context must be set through the Chartboost method onCreate() before calling trackEvent()."); 
    if (chartboost.getAppID() == null || chartboost.getAppSignature() == null)
      return Boolean.valueOf(false); 
    k k = new k("api", "event");
    k.a(chartboost.getContext());
    k.a("key", paramString);
    k.a("value", (new StringBuilder(String.valueOf(paramDouble))).toString());
    k.a("timestamp", (new StringBuilder(String.valueOf(System.currentTimeMillis() / 1000.0D))).toString());
    if (paramMap != null)
      k.a("meta", (new JSONObject(paramMap)).toString()); 
    k.c(chartboost.getAppID(), chartboost.getAppSignature());
    k.i = true;
    this.b.a(k);
    return Boolean.valueOf(true);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Analytics\CBAnalytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */