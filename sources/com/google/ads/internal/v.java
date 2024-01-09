package com.google.ads.internal;

import com.google.ads.internal.f;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
class v implements f.a {
    @Override // com.google.ads.internal.f.a
    public HttpURLConnection a(URL url) {
        return (HttpURLConnection) url.openConnection();
    }
}
