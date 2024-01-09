package com.b.a;

import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/* loaded from: classes.dex */
class c extends Thread {
    final /* synthetic */ a a;
    private final /* synthetic */ String b;
    private final /* synthetic */ Bundle c;
    private final /* synthetic */ String d;
    private final /* synthetic */ d e;
    private final /* synthetic */ Object f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(a aVar, String str, Bundle bundle, String str2, d dVar, Object obj) {
        this.a = aVar;
        this.b = str;
        this.c = bundle;
        this.d = str2;
        this.e = dVar;
        this.f = obj;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            this.e.onComplete(this.a.a.a(this.b, this.c, this.d), this.f);
        } catch (FileNotFoundException e) {
            this.e.onFileNotFoundException(e, this.f);
        } catch (MalformedURLException e2) {
            this.e.onMalformedURLException(e2, this.f);
        } catch (IOException e3) {
            this.e.onIOException(e3, this.f);
        }
    }
}
