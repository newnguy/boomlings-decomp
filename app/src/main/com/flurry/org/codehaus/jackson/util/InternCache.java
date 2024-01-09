package com.flurry.org.codehaus.jackson.util;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class InternCache extends LinkedHashMap {
    private static final int MAX_ENTRIES = 192;
    public static final InternCache instance = new InternCache();

    private InternCache() {
        super(MAX_ENTRIES, 0.8f, true);
    }

    public synchronized String intern(String str) {
        String str2;
        str2 = (String) get(str);
        if (str2 == null) {
            str2 = str.intern();
            put(str2, str2);
        }
        return str2;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry entry) {
        return size() > MAX_ENTRIES;
    }
}
