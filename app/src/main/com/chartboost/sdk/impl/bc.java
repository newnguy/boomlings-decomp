package com.chartboost.sdk.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
class bc {
    private static final ConcurrentMap a = bf.c();

    public static List a(Class cls) {
        ConcurrentMap a2 = a();
        while (true) {
            List list = (List) a2.get(cls);
            if (list != null) {
                return list;
            }
            a2.putIfAbsent(cls, b(cls));
        }
    }

    private static ConcurrentMap a() {
        return a;
    }

    private static void a(Class cls, List list) {
        if (cls == null || cls == Object.class) {
            return;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        for (int length = interfaces.length - 1; length >= 0; length--) {
            a(interfaces[length], list);
        }
        a(cls.getSuperclass(), list);
        if (list.contains(cls)) {
            return;
        }
        list.add(cls);
    }

    private static List b(Class cls) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Object.class);
        a(cls, arrayList);
        Collections.reverse(arrayList);
        return Collections.unmodifiableList(new ArrayList(arrayList));
    }
}
