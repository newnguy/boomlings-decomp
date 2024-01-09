package net.robotmedia.billing.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import net.robotmedia.billing.a;
import net.robotmedia.billing.a.d;
import net.robotmedia.billing.b;
import net.robotmedia.billing.c;
import net.robotmedia.billing.k;

public abstract class AbstractBillingActivity extends Activity implements c {
  protected b mBillingObserver;
  
  public b checkBillingSupported() {
    return a.a((Context)this);
  }
  
  public b checkSubscriptionSupported() {
    return a.b((Context)this);
  }
  
  public void doRestoreCheck() {
    if (!this.mBillingObserver.b())
      a.d((Context)this); 
  }
  
  public abstract void onBillingChecked(boolean paramBoolean);
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    this.mBillingObserver = new a(this, this);
    a.a(this.mBillingObserver);
    a.a(this);
    checkBillingSupported();
  }
  
  protected void onDestroy() {
    super.onDestroy();
    a.b(this.mBillingObserver);
    a.a(null);
  }
  
  public abstract void onPurchaseStateChanged(String paramString, d paramd);
  
  public abstract void onRequestPurchaseResponse(String paramString, k paramk);
  
  public abstract void onSubscriptionChecked(boolean paramBoolean);
  
  public void requestPurchase(String paramString) {
    a.b((Context)this, paramString);
  }
  
  public void requestSubscription(String paramString) {
    a.c((Context)this, paramString);
  }
  
  public void restoreTransactions() {
    a.d((Context)this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\net\robotmedia\billing\helper\AbstractBillingActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */