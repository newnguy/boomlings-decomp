package com.a.a.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class b extends Binder implements a {
  public static a a(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.android.vending.billing.IMarketBillingService");
    return (iInterface != null && iInterface instanceof a) ? (a)iInterface : new c(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) {
    switch (paramInt1) {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.android.vending.billing.IMarketBillingService");
        return true;
      case 1:
        break;
    } 
    paramParcel1.enforceInterface("com.android.vending.billing.IMarketBillingService");
    if (paramParcel1.readInt() != 0) {
      Bundle bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    Bundle bundle = a((Bundle)paramParcel1);
    paramParcel2.writeNoException();
    if (bundle != null) {
      paramParcel2.writeInt(1);
      bundle.writeToParcel(paramParcel2, 1);
    } else {
      paramParcel2.writeInt(0);
    } 
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\a\a\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */