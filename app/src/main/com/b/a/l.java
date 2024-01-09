package com.b.a;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class l extends Handler {
    final /* synthetic */ k a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(k kVar) {
        this.a = kVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        f fVar;
        f fVar2;
        String string = message.getData().getString("access_token");
        long j = message.getData().getLong("expires_in") * 1000;
        Bundle bundle = (Bundle) message.getData().clone();
        bundle.putLong("expires_in", j);
        if (string != null) {
            fVar = this.a.e;
            fVar.a(string);
            fVar2 = this.a.e;
            fVar2.a(j);
            if (this.a.b != null) {
                this.a.b.a(bundle);
            }
        } else if (this.a.b != null) {
            String string2 = message.getData().getString("error");
            if (message.getData().containsKey("error_code")) {
                this.a.b.a(new m(string2, null, message.getData().getInt("error_code")));
            } else {
                j jVar = this.a.b;
                if (string2 == null) {
                    string2 = "Unknown service error";
                }
                jVar.a(new Error(string2));
            }
        }
        this.a.c.unbindService(this.a);
    }
}
