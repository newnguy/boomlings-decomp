package com.b.a;

import android.content.Context;
import android.os.AsyncTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h extends AsyncTask {
    final /* synthetic */ f a;
    private final String b;
    private final Context c;

    public h(f fVar, String str, Context context) {
        this.a = fVar;
        this.b = str;
        this.c = context.getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public Void doInBackground(Void... voidArr) {
        try {
            f.b(this.a, this.b, this.c);
            return null;
        } catch (Exception e) {
            s.a("Facebook-publish", e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* renamed from: a */
    public void onPostExecute(Void r4) {
        synchronized (this.a) {
            this.a.o = null;
        }
    }
}
