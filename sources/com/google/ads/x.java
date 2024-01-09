package com.google.ads;

import android.text.TextUtils;
import android.webkit.WebView;
import com.google.ads.internal.AdWebView;
import com.google.ads.l;
import com.google.ads.util.AdUtil;
import com.google.ads.util.i;
import java.util.HashMap;

/* loaded from: classes.dex */
public class x implements n {
    private void a(HashMap hashMap, String str, i.c cVar) {
        try {
            String str2 = (String) hashMap.get(str);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            cVar.a(Integer.valueOf(str2));
        } catch (NumberFormatException e) {
            com.google.ads.util.b.a("Could not parse \"" + str + "\" constant.");
        }
    }

    private void b(HashMap hashMap, String str, i.c cVar) {
        try {
            String str2 = (String) hashMap.get(str);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            cVar.a(new Long(str2));
        } catch (NumberFormatException e) {
            com.google.ads.util.b.a("Could not parse \"" + str + "\" constant.");
        }
    }

    private void c(HashMap hashMap, String str, i.c cVar) {
        String str2 = (String) hashMap.get(str);
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        cVar.a(str2);
    }

    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        m h = dVar.h();
        l.a aVar = (l.a) ((l) h.a.a()).a.a();
        a(hashMap, "min_hwa_banner", aVar.a);
        a(hashMap, "min_hwa_overlay", aVar.b);
        c(hashMap, "mraid_banner_path", aVar.c);
        c(hashMap, "mraid_expanded_banner_path", aVar.d);
        c(hashMap, "mraid_interstitial_path", aVar.e);
        b(hashMap, "ac_max_size", aVar.f);
        b(hashMap, "ac_padding", aVar.g);
        b(hashMap, "ac_total_quota", aVar.h);
        b(hashMap, "db_total_quota", aVar.i);
        b(hashMap, "db_quota_per_origin", aVar.j);
        b(hashMap, "db_quota_step_size", aVar.k);
        AdWebView k = dVar.k();
        if (AdUtil.a >= 11) {
            com.google.ads.util.g.a(k.getSettings(), h);
            com.google.ads.util.g.a(webView.getSettings(), h);
        }
        if (!((com.google.ads.internal.h) h.k.a()).a()) {
            boolean f = k.f();
            boolean z = AdUtil.a < ((Integer) aVar.a.a()).intValue();
            if (!z && f) {
                com.google.ads.util.b.a("Re-enabling hardware acceleration for a banner after reading constants.");
                k.c();
            } else if (z && !f) {
                com.google.ads.util.b.a("Disabling hardware acceleration for a banner after reading constants.");
                k.b();
            }
        }
        aVar.l.a(true);
    }
}
