package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class be implements bg, Map {
    private final ConcurrentMap a;
    private final bg b;

    be(ConcurrentMap concurrentMap, bg bgVar) {
        this.a = (ConcurrentMap) bb.a("map", concurrentMap);
        this.b = (bg) bb.a("function", bgVar);
    }

    public static Map a(bg bgVar) {
        return new be(bf.c(), bgVar);
    }

    @Override // com.chartboost.sdk.impl.bg
    public Object a(Object obj) {
        return get(obj);
    }

    @Override // java.util.Map
    public void clear() {
        this.a.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.a.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.a.containsValue(obj);
    }

    @Override // java.util.Map
    public Set entrySet() {
        return this.a.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.a.equals(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        while (true) {
            Object obj2 = this.a.get(obj);
            if (obj2 != null) {
                return obj2;
            }
            Object a = this.b.a(obj);
            if (a == null) {
                return null;
            }
            this.a.putIfAbsent(obj, a);
        }
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    @Override // java.util.Map
    public Set keySet() {
        return this.a.keySet();
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        return this.a.put(obj, obj2);
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        this.a.putAll(map);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.a.remove(obj);
    }

    @Override // java.util.Map
    public int size() {
        return this.a.size();
    }

    @Override // java.util.Map
    public Collection values() {
        return this.a.values();
    }
}
