package com.flurry.org.apache.avro.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class WeakIdentityHashMap implements Map {
    private final ReferenceQueue queue = new ReferenceQueue();
    private Map backingStore = new HashMap();

    /* loaded from: classes.dex */
    public class IdentityWeakReference extends WeakReference {
        int hash;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        IdentityWeakReference(Object obj) {
            super(obj, r2.queue);
            WeakIdentityHashMap.this = r2;
            this.hash = System.identityHashCode(obj);
        }

        public boolean equals(Object obj) {
            return this == obj || get() == ((IdentityWeakReference) obj).get();
        }

        public int hashCode() {
            return this.hash;
        }
    }

    private synchronized void reap() {
        Reference poll = this.queue.poll();
        while (poll != null) {
            this.backingStore.remove((IdentityWeakReference) poll);
            poll = this.queue.poll();
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.backingStore.clear();
        reap();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        reap();
        return this.backingStore.containsKey(new IdentityWeakReference(obj));
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        reap();
        return this.backingStore.containsValue(obj);
    }

    @Override // java.util.Map
    public Set entrySet() {
        reap();
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : this.backingStore.entrySet()) {
            final Object obj = ((IdentityWeakReference) entry.getKey()).get();
            final Object value = entry.getValue();
            hashSet.add(new Map.Entry() { // from class: com.flurry.org.apache.avro.util.WeakIdentityHashMap.1
                @Override // java.util.Map.Entry
                public Object getKey() {
                    return obj;
                }

                @Override // java.util.Map.Entry
                public Object getValue() {
                    return value;
                }

                @Override // java.util.Map.Entry
                public Object setValue(Object obj2) {
                    throw new UnsupportedOperationException();
                }
            });
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.backingStore.equals(((WeakIdentityHashMap) obj).backingStore);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        reap();
        return this.backingStore.get(new IdentityWeakReference(obj));
    }

    @Override // java.util.Map
    public int hashCode() {
        reap();
        return this.backingStore.hashCode();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        reap();
        return this.backingStore.isEmpty();
    }

    @Override // java.util.Map
    public Set keySet() {
        reap();
        HashSet hashSet = new HashSet();
        for (IdentityWeakReference identityWeakReference : this.backingStore.keySet()) {
            hashSet.add(identityWeakReference.get());
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // java.util.Map
    public Object put(Object obj, Object obj2) {
        reap();
        return this.backingStore.put(new IdentityWeakReference(obj), obj2);
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        reap();
        return this.backingStore.remove(new IdentityWeakReference(obj));
    }

    @Override // java.util.Map
    public int size() {
        reap();
        return this.backingStore.size();
    }

    @Override // java.util.Map
    public Collection values() {
        reap();
        return this.backingStore.values();
    }
}
