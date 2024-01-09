package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class ba implements Serializable, ConcurrentMap {
    private volatile Map a;
    private final transient Lock b = new ReentrantLock();
    private final h c;

    /* loaded from: classes.dex */
    public abstract class a implements Collection {
        protected a() {
        }

        abstract Collection a();

        @Override // java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            return a().contains(obj);
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection collection) {
            return a().containsAll(collection);
        }

        @Override // java.util.Collection
        public boolean equals(Object obj) {
            return a().equals(obj);
        }

        @Override // java.util.Collection
        public int hashCode() {
            return a().hashCode();
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return a().isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new f(a().iterator());
        }

        @Override // java.util.Collection
        public final int size() {
            return a().size();
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            return a().toArray();
        }

        @Override // java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            return a().toArray(objArr);
        }

        public String toString() {
            return a().toString();
        }
    }

    /* loaded from: classes.dex */
    class b extends a implements Set {
        private b() {
        }

        @Override // com.chartboost.sdk.impl.ba.a
        Collection a() {
            return ba.this.a.entrySet();
        }

        @Override // java.util.Collection, java.util.Set
        public void clear() {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                a.entrySet().clear();
                ba.this.b(a);
            } finally {
                ba.this.b.unlock();
            }
        }

        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            ba.this.b.lock();
            try {
                if (!contains(obj)) {
                    return false;
                }
                Map a = ba.this.a();
                boolean remove = a.entrySet().remove(obj);
                ba.this.b(a);
                return remove;
            } finally {
                ba.this.b.unlock();
            }
        }

        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection collection) {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                boolean removeAll = a.entrySet().removeAll(collection);
                ba.this.b(a);
                return removeAll;
            } finally {
                ba.this.b.unlock();
            }
        }

        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection collection) {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                boolean retainAll = a.entrySet().retainAll(collection);
                ba.this.b(a);
                return retainAll;
            } finally {
                ba.this.b.unlock();
            }
        }
    }

    /* loaded from: classes.dex */
    final class c extends h implements Serializable {
        c() {
        }

        @Override // com.chartboost.sdk.impl.ba.h
        public Set a() {
            return Collections.unmodifiableSet(ba.this.a.keySet());
        }

        @Override // com.chartboost.sdk.impl.ba.h
        public Set b() {
            return Collections.unmodifiableSet(ba.this.a.entrySet());
        }

        @Override // com.chartboost.sdk.impl.ba.h
        public Collection c() {
            return Collections.unmodifiableCollection(ba.this.a.values());
        }
    }

    /* loaded from: classes.dex */
    class d extends a implements Set {
        private d() {
        }

        @Override // com.chartboost.sdk.impl.ba.a
        Collection a() {
            return ba.this.a.keySet();
        }

        @Override // java.util.Collection, java.util.Set
        public void clear() {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                a.keySet().clear();
                ba.this.b(a);
            } finally {
                ba.this.b.unlock();
            }
        }

        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return ba.this.remove(obj) != null;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection collection) {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                boolean removeAll = a.keySet().removeAll(collection);
                ba.this.b(a);
                return removeAll;
            } finally {
                ba.this.b.unlock();
            }
        }

        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection collection) {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                boolean retainAll = a.keySet().retainAll(collection);
                ba.this.b(a);
                return retainAll;
            } finally {
                ba.this.b.unlock();
            }
        }
    }

    /* loaded from: classes.dex */
    final class e extends h implements Serializable {
        private final transient d b;
        private final transient b c;
        private final transient g d;

        e() {
            this.b = new d();
            this.c = new b();
            this.d = new g();
        }

        @Override // com.chartboost.sdk.impl.ba.h
        public Set a() {
            return this.b;
        }

        @Override // com.chartboost.sdk.impl.ba.h
        public Set b() {
            return this.c;
        }

        @Override // com.chartboost.sdk.impl.ba.h
        public Collection c() {
            return this.d;
        }
    }

    /* loaded from: classes.dex */
    class f implements Iterator {
        private final Iterator a;

        public f(Iterator it) {
            this.a = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a.hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            return this.a.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes.dex */
    final class g extends a {
        private g() {
        }

        @Override // com.chartboost.sdk.impl.ba.a
        Collection a() {
            return ba.this.a.values();
        }

        @Override // java.util.Collection
        public void clear() {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                a.values().clear();
                ba.this.b(a);
            } finally {
                ba.this.b.unlock();
            }
        }

        @Override // java.util.Collection
        public boolean remove(Object obj) {
            ba.this.b.lock();
            try {
                if (!contains(obj)) {
                    return false;
                }
                Map a = ba.this.a();
                boolean remove = a.values().remove(obj);
                ba.this.b(a);
                return remove;
            } finally {
                ba.this.b.unlock();
            }
        }

        @Override // java.util.Collection
        public boolean removeAll(Collection collection) {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                boolean removeAll = a.values().removeAll(collection);
                ba.this.b(a);
                return removeAll;
            } finally {
                ba.this.b.unlock();
            }
        }

        @Override // java.util.Collection
        public boolean retainAll(Collection collection) {
            ba.this.b.lock();
            try {
                Map a = ba.this.a();
                boolean retainAll = a.values().retainAll(collection);
                ba.this.b(a);
                return retainAll;
            } finally {
                ba.this.b.unlock();
            }
        }
    }

    /* loaded from: classes.dex */
    public abstract class h {

        /* loaded from: classes.dex */
        public enum a {
            STABLE { // from class: com.chartboost.sdk.impl.ba.h.a.1
                @Override // com.chartboost.sdk.impl.ba.h.a
                h a(ba baVar) {
                    baVar.getClass();
                    return new c();
                }
            },
            LIVE { // from class: com.chartboost.sdk.impl.ba.h.a.2
                @Override // com.chartboost.sdk.impl.ba.h.a
                h a(ba baVar) {
                    baVar.getClass();
                    return new e();
                }
            };

            abstract h a(ba baVar);
        }

        h() {
        }

        abstract Set a();

        abstract Set b();

        abstract Collection c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ba(Map map, h.a aVar) {
        this.a = (Map) bb.a("delegate", a((Map) bb.a("map", map)));
        this.c = ((h.a) bb.a("viewType", aVar)).a(this);
    }

    private boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    protected Map a() {
        this.b.lock();
        try {
            return a(this.a);
        } finally {
            this.b.unlock();
        }
    }

    abstract Map a(Map map);

    protected void b(Map map) {
        this.a = map;
    }

    @Override // java.util.Map
    public final void clear() {
        this.b.lock();
        try {
            b(a(Collections.emptyMap()));
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return this.a.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return this.a.containsValue(obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return this.c.b();
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        return this.a.equals(obj);
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return this.a.get(obj);
    }

    @Override // java.util.Map
    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.a.isEmpty();
    }

    @Override // java.util.Map
    public final Set keySet() {
        return this.c.a();
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        this.b.lock();
        try {
            Map a2 = a();
            Object put = a2.put(obj, obj2);
            b(a2);
            return put;
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        this.b.lock();
        try {
            Map a2 = a();
            a2.putAll(map);
            b(a2);
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public Object putIfAbsent(Object obj, Object obj2) {
        Object obj3;
        this.b.lock();
        try {
            if (this.a.containsKey(obj)) {
                obj3 = this.a.get(obj);
            } else {
                Map a2 = a();
                obj3 = a2.put(obj, obj2);
                b(a2);
            }
            return obj3;
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        this.b.lock();
        try {
            if (!this.a.containsKey(obj)) {
                return null;
            }
            Map a2 = a();
            Object remove = a2.remove(obj);
            b(a2);
            return remove;
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean remove(Object obj, Object obj2) {
        Lock lock;
        this.b.lock();
        try {
            if (!this.a.containsKey(obj) || !a(obj2, this.a.get(obj))) {
                return false;
            }
            Map a2 = a();
            a2.remove(obj);
            b(a2);
            return true;
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public Object replace(Object obj, Object obj2) {
        this.b.lock();
        try {
            if (!this.a.containsKey(obj)) {
                return null;
            }
            Map a2 = a();
            Object put = a2.put(obj, obj2);
            b(a2);
            return put;
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean replace(Object obj, Object obj2, Object obj3) {
        Lock lock;
        this.b.lock();
        try {
            if (!this.a.containsKey(obj) || !a(obj2, this.a.get(obj))) {
                return false;
            }
            Map a2 = a();
            a2.put(obj, obj3);
            b(a2);
            return true;
        } finally {
            this.b.unlock();
        }
    }

    @Override // java.util.Map
    public final int size() {
        return this.a.size();
    }

    public String toString() {
        return this.a.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.c.c();
    }
}
