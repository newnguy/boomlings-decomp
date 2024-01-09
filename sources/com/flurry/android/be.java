package com.flurry.android;

import java.security.KeyStore;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class be {
    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized HttpClient a(HttpParams httpParams) {
        DefaultHttpClient defaultHttpClient;
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            bi biVar = new bi(this, keyStore);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", biVar, 443));
            defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(httpParams, schemeRegistry), httpParams);
        } catch (Exception e) {
            defaultHttpClient = new DefaultHttpClient(httpParams);
        }
        return defaultHttpClient;
    }
}
