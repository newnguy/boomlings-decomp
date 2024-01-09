package com.google.ads;

import android.app.Activity;
import com.google.ads.g;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
class aw implements Runnable {
    private final h a;
    private final String b;
    private final AdRequest c;
    private final HashMap d;
    private final boolean e;
    private final WeakReference f;

    public aw(h hVar, Activity activity, String str, AdRequest adRequest, HashMap hashMap) {
        this.a = hVar;
        this.b = str;
        this.f = new WeakReference(activity);
        this.c = adRequest;
        this.d = new HashMap(hashMap);
        this.e = a(this.d);
    }

    private void a(MediationAdapter mediationAdapter) {
        MediationServerParameters mediationServerParameters;
        Activity activity = (Activity) this.f.get();
        if (activity == null) {
            throw new ax("Activity became null while trying to instantiate adapter.");
        }
        this.a.a(mediationAdapter);
        Class c = mediationAdapter.c();
        if (c != null) {
            MediationServerParameters mediationServerParameters2 = (MediationServerParameters) c.newInstance();
            mediationServerParameters2.a(this.d);
            mediationServerParameters = mediationServerParameters2;
        } else {
            mediationServerParameters = null;
        }
        Class b = mediationAdapter.b();
        NetworkExtras networkExtras = b != null ? (NetworkExtras) this.c.a(b) : null;
        MediationAdRequest mediationAdRequest = new MediationAdRequest(this.c, activity, this.e);
        if (this.a.a.a()) {
            if (!(mediationAdapter instanceof MediationInterstitialAdapter)) {
                throw new ax("Adapter " + this.b + " doesn't support the MediationInterstitialAdapter interface.");
            }
            ((MediationInterstitialAdapter) mediationAdapter).a(new az(this.a), activity, mediationServerParameters, mediationAdRequest, networkExtras);
        } else if (!(mediationAdapter instanceof MediationBannerAdapter)) {
            throw new ax("Adapter " + this.b + " doesn't support the MediationBannerAdapter interface");
        } else {
            ((MediationBannerAdapter) mediationAdapter).a(new ay(this.a), activity, mediationServerParameters, this.a.a.b(), mediationAdRequest, networkExtras);
        }
        this.a.k();
    }

    private void a(String str, Throwable th, g.a aVar) {
        com.google.ads.util.b.b(str, th);
        this.a.a(false, aVar);
    }

    private static boolean a(Map map) {
        String str = (String) map.remove("gwhirl_share_location");
        if ("1".equals(str)) {
            return true;
        }
        if (str != null && !"0".equals(str)) {
            com.google.ads.util.b.b("Received an illegal value, '" + str + "', for the special share location parameter from mediation server (expected '0' or '1'). Will not share the location.");
        }
        return false;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            com.google.ads.util.b.a("Trying to instantiate: " + this.b);
            a((MediationAdapter) g.a(this.b, MediationAdapter.class));
        } catch (ClassNotFoundException e) {
            a("Cannot find adapter class '" + this.b + "'. Did you link the ad network's mediation adapter? Skipping ad network.", e, g.a.NOT_FOUND);
        } catch (Throwable th) {
            a("Error while creating adapter and loading ad from ad network. Skipping ad network.", th, g.a.EXCEPTION);
        }
    }
}
