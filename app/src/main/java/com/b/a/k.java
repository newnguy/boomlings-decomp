package com.b.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/* loaded from: classes.dex */
public class k implements ServiceConnection {
    final j b;
    final Context c;
    final /* synthetic */ f e;
    final Messenger a = new Messenger(new l(this));
    Messenger d = null;

    public k(f fVar, Context context, j jVar) {
        this.e = fVar;
        this.c = context;
        this.b = jVar;
    }

    private void a() {
        String str;
        Bundle bundle = new Bundle();
        str = this.e.e;
        bundle.putString("access_token", str);
        Message obtain = Message.obtain();
        obtain.setData(bundle);
        obtain.replyTo = this.a;
        try {
            this.d.send(obtain);
        } catch (RemoteException e) {
            this.b.a(new Error("Service connection error"));
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.d = new Messenger(iBinder);
        a();
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.b.a(new Error("Service disconnected"));
        this.c.unbindService(this);
    }
}
