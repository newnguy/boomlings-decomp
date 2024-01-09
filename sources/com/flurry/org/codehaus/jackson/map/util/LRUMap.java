package com.flurry.org.codehaus.jackson.map.util;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class LRUMap extends LinkedHashMap {
    protected final int _maxEntries;

    public LRUMap(int i, int i2) {
        super(i, 0.8f, true);
        this._maxEntries = i2;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry entry) {
        return size() > this._maxEntries;
    }
}
