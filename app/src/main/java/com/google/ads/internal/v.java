package com.google.ads.internal;

import com.google.ads.internal.f;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class v implements f.a {
    @Override // com.google.ads.internal.f.a
    public HttpURLConnection a(URL url) {
        return (HttpURLConnection) url.openConnection();
    }
}
