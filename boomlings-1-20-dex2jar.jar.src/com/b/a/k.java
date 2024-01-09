package com.b.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

class k implements ServiceConnection {
  final Messenger a = new Messenger(new l(this));
  
  final j b;
  
  final Context c;
  
  Messenger d = null;
  
  final f e;
  
  public k(f paramf, Context paramContext, j paramj) {
    this.c = paramContext;
    this.b = paramj;
  }
  
  private void a() {
    Bundle bundle = new Bundle();
    bundle.putString("access_token", f.a(this.e));
    Message message = Message.obtain();
    message.setData(bundle);
    message.replyTo = this.a;
    try {
      this.d.send(message);
    } catch (RemoteException remoteException) {
      this.b.a(new Error("Service connection error"));
    } 
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    this.d = new Messenger(paramIBinder);
    a();
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName) {
    this.b.a(new Error("Service disconnected"));
    this.c.unbindService(this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\b\a\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */