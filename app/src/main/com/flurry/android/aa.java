package com.flurry.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class aa {
    private Map a = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized AdUnit a(String str) {
        AdUnit adUnit;
        List list = (List) this.a.get(str);
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                adUnit = (AdUnit) it.next();
                if (ac.a(adUnit.b().longValue()) && adUnit.c().size() > 0) {
                    it.remove();
                    break;
                }
            }
        }
        adUnit = null;
        return adUnit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized List a(String str, int i) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        List list = (List) this.a.get(str);
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext() && arrayList.size() <= i) {
                AdUnit adUnit = (AdUnit) it.next();
                if (ac.a(adUnit.b().longValue()) && adUnit.d().intValue() == 1 && adUnit.c().size() > 0) {
                    arrayList.add(adUnit);
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void a(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AdUnit adUnit = (AdUnit) it.next();
            String obj = adUnit.a().toString();
            List list2 = (List) this.a.get(obj);
            if (list2 == null) {
                list2 = new ArrayList();
            }
            list2.add(adUnit);
            this.a.put(obj, list2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean b(String str) {
        boolean z;
        List<AdUnit> list = (List) this.a.get(str);
        if (list != null && !list.isEmpty()) {
            for (AdUnit adUnit : list) {
                if (ac.a(adUnit.b().longValue())) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        return z;
    }
}
