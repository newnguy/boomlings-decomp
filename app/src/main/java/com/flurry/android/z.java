package com.flurry.android;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public final class z {
    private bo a;

    public z(bo boVar) {
        this.a = boVar;
    }

    public final List a(List list) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            bl blVar = (bl) it.next();
            SdkAdLog sdkAdLog = new SdkAdLog();
            sdkAdLog.a(Long.valueOf(blVar.c()));
            sdkAdLog.a(blVar.b());
            CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            synchronized (blVar) {
                for (at atVar : blVar.d()) {
                    if (atVar.b()) {
                        SdkAdEvent sdkAdEvent = new SdkAdEvent();
                        sdkAdEvent.a(atVar.a());
                        sdkAdEvent.a(Long.valueOf(atVar.c()));
                        Map d = atVar.d();
                        HashMap hashMap = new HashMap();
                        for (Map.Entry entry : d.entrySet()) {
                            hashMap.put(entry.getKey(), entry.getValue());
                        }
                        sdkAdEvent.a(hashMap);
                        copyOnWriteArrayList2.add(sdkAdEvent);
                    }
                }
            }
            sdkAdLog.a(copyOnWriteArrayList2);
            copyOnWriteArrayList.add(sdkAdLog);
        }
        this.a.a(list);
        return copyOnWriteArrayList;
    }
}
