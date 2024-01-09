package com.a.a.a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

class c implements a {
  private IBinder a;
  
  c(IBinder paramIBinder) {
    this.a = paramIBinder;
  }
  
  public Bundle a(Bundle paramBundle) {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("com.android.vending.billing.IMarketBillingService");
      if (paramBundle != null) {
        parcel1.writeInt(1);
        paramBundle.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      this.a.transact(1, parcel1, parcel2, 0);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
    paramBundle = null;
    parcel2.recycle();
    parcel1.recycle();
    return paramBundle;
  }
  
  public IBinder asBinder() {
    return this.a;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\a\a\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */