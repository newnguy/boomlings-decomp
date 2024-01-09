package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ser.impl.SerializerCache;
import java.util.Map;

/* loaded from: classes.dex */
public class JsonSerializerMap {
    private final Bucket[] _buckets;
    private final int _size;

    /* loaded from: classes.dex */
    public final class Bucket {
        public final SerializerCache.TypeKey key;
        public final Bucket next;
        public final JsonSerializer value;

        public Bucket(Bucket bucket, SerializerCache.TypeKey typeKey, JsonSerializer jsonSerializer) {
            this.next = bucket;
            this.key = typeKey;
            this.value = jsonSerializer;
        }
    }

    public JsonSerializerMap(Map map) {
        int findSize = findSize(map.size());
        this._size = findSize;
        int i = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (Map.Entry entry : map.entrySet()) {
            SerializerCache.TypeKey typeKey = (SerializerCache.TypeKey) entry.getKey();
            int hashCode = typeKey.hashCode() & i;
            bucketArr[hashCode] = new Bucket(bucketArr[hashCode], typeKey, (JsonSerializer) entry.getValue());
        }
        this._buckets = bucketArr;
    }

    private static final int findSize(int i) {
        int i2 = 8;
        while (i2 < (i <= 64 ? i + i : (i >> 2) + i)) {
            i2 += i2;
        }
        return i2;
    }

    public JsonSerializer find(SerializerCache.TypeKey typeKey) {
        Bucket bucket = this._buckets[typeKey.hashCode() & (this._buckets.length - 1)];
        if (bucket == null) {
            return null;
        }
        if (typeKey.equals(bucket.key)) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return null;
            }
        } while (!typeKey.equals(bucket.key));
        return bucket.value;
    }

    public int size() {
        return this._size;
    }
}
