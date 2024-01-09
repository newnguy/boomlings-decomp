package com.b.a;

import android.content.Context;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/* loaded from: classes.dex */
class b extends Thread {
    final /* synthetic */ a a;
    private final /* synthetic */ Context b;
    private final /* synthetic */ d c;
    private final /* synthetic */ Object d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar, Context context, d dVar, Object obj) {
        this.a = aVar;
        this.b = context;
        this.c = dVar;
        this.d = obj;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            String a = this.a.a.a(this.b);
            if (a.length() == 0 || a.equals("false")) {
                this.c.onFacebookError(new m("auth.expireSession failed"), this.d);
            } else {
                this.c.onComplete(a, this.d);
            }
        } catch (FileNotFoundException e) {
            this.c.onFileNotFoundException(e, this.d);
        } catch (MalformedURLException e2) {
            this.c.onMalformedURLException(e2, this.d);
        } catch (IOException e3) {
            this.c.onIOException(e3, this.d);
        }
    }
}
