package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class SerializerCache {
    private HashMap _sharedMap = new HashMap(64);
    private ReadOnlyClassToSerializerMap _readOnlyMap = null;

    /* loaded from: classes.dex */
    public final class TypeKey {
        protected Class _class;
        protected int _hashCode;
        protected boolean _isTyped;
        protected JavaType _type;

        public TypeKey(JavaType javaType, boolean z) {
            this._type = javaType;
            this._class = null;
            this._isTyped = z;
            this._hashCode = hash(javaType, z);
        }

        public TypeKey(Class cls, boolean z) {
            this._class = cls;
            this._type = null;
            this._isTyped = z;
            this._hashCode = hash(cls, z);
        }

        private static final int hash(JavaType javaType, boolean z) {
            int hashCode = javaType.hashCode() - 1;
            return z ? hashCode - 1 : hashCode;
        }

        private static final int hash(Class cls, boolean z) {
            int hashCode = cls.getName().hashCode();
            return z ? hashCode + 1 : hashCode;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            TypeKey typeKey = (TypeKey) obj;
            if (typeKey._isTyped == this._isTyped) {
                return this._class != null ? typeKey._class == this._class : this._type.equals(typeKey._type);
            }
            return false;
        }

        public final int hashCode() {
            return this._hashCode;
        }

        public void resetTyped(JavaType javaType) {
            this._type = javaType;
            this._class = null;
            this._isTyped = true;
            this._hashCode = hash(javaType, true);
        }

        public void resetTyped(Class cls) {
            this._type = null;
            this._class = cls;
            this._isTyped = true;
            this._hashCode = hash(cls, true);
        }

        public void resetUntyped(JavaType javaType) {
            this._type = javaType;
            this._class = null;
            this._isTyped = false;
            this._hashCode = hash(javaType, false);
        }

        public void resetUntyped(Class cls) {
            this._type = null;
            this._class = cls;
            this._isTyped = false;
            this._hashCode = hash(cls, false);
        }

        public final String toString() {
            return this._class != null ? "{class: " + this._class.getName() + ", typed? " + this._isTyped + "}" : "{type: " + this._type + ", typed? " + this._isTyped + "}";
        }
    }

    public void addAndResolveNonTypedSerializer(JavaType javaType, JsonSerializer jsonSerializer, SerializerProvider serializerProvider) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(javaType, false), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
            if (jsonSerializer instanceof ResolvableSerializer) {
                ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
            }
        }
    }

    public void addAndResolveNonTypedSerializer(Class cls, JsonSerializer jsonSerializer, SerializerProvider serializerProvider) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(cls, false), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
            if (jsonSerializer instanceof ResolvableSerializer) {
                ((ResolvableSerializer) jsonSerializer).resolve(serializerProvider);
            }
        }
    }

    public void addTypedSerializer(JavaType javaType, JsonSerializer jsonSerializer) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(javaType, true), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
        }
    }

    public void addTypedSerializer(Class cls, JsonSerializer jsonSerializer) {
        synchronized (this) {
            if (this._sharedMap.put(new TypeKey(cls, true), jsonSerializer) == null) {
                this._readOnlyMap = null;
            }
        }
    }

    public synchronized void flush() {
        this._sharedMap.clear();
    }

    public ReadOnlyClassToSerializerMap getReadOnlyLookupMap() {
        ReadOnlyClassToSerializerMap readOnlyClassToSerializerMap;
        synchronized (this) {
            readOnlyClassToSerializerMap = this._readOnlyMap;
            if (readOnlyClassToSerializerMap == null) {
                readOnlyClassToSerializerMap = ReadOnlyClassToSerializerMap.from(this._sharedMap);
                this._readOnlyMap = readOnlyClassToSerializerMap;
            }
        }
        return readOnlyClassToSerializerMap.instance();
    }

    public synchronized int size() {
        return this._sharedMap.size();
    }

    public JsonSerializer typedValueSerializer(JavaType javaType) {
        JsonSerializer jsonSerializer;
        synchronized (this) {
            jsonSerializer = (JsonSerializer) this._sharedMap.get(new TypeKey(javaType, true));
        }
        return jsonSerializer;
    }

    public JsonSerializer typedValueSerializer(Class cls) {
        JsonSerializer jsonSerializer;
        synchronized (this) {
            jsonSerializer = (JsonSerializer) this._sharedMap.get(new TypeKey(cls, true));
        }
        return jsonSerializer;
    }

    public JsonSerializer untypedValueSerializer(JavaType javaType) {
        JsonSerializer jsonSerializer;
        synchronized (this) {
            jsonSerializer = (JsonSerializer) this._sharedMap.get(new TypeKey(javaType, false));
        }
        return jsonSerializer;
    }

    public JsonSerializer untypedValueSerializer(Class cls) {
        JsonSerializer jsonSerializer;
        synchronized (this) {
            jsonSerializer = (JsonSerializer) this._sharedMap.get(new TypeKey(cls, false));
        }
        return jsonSerializer;
    }
}
