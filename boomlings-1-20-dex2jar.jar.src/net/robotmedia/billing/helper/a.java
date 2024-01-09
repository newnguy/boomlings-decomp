package net.robotmedia.billing.helper;

import android.app.Activity;
import net.robotmedia.billing.a.d;
import net.robotmedia.billing.k;

class a extends b {
  final AbstractBillingActivity a;
  
  a(AbstractBillingActivity paramAbstractBillingActivity, Activity paramActivity) {
    super(paramActivity);
  }
  
  public void a(String paramString, d paramd) {
    this.a.onPurchaseStateChanged(paramString, paramd);
  }
  
  public void a(String paramString, k paramk) {
    this.a.onRequestPurchaseResponse(paramString, paramk);
  }
  
  public void a(boolean paramBoolean) {
    this.a.onBillingChecked(paramBoolean);
  }
  
  public void b(boolean paramBoolean) {
    this.a.onSubscriptionChecked(paramBoolean);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\net\robotmedia\billing\helper\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */