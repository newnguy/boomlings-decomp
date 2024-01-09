package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.jsontype.NamedType;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TypeNameIdResolver extends TypeIdResolverBase {
    protected final MapperConfig _config;
    protected final HashMap _idToType;
    protected final HashMap _typeToId;

    protected TypeNameIdResolver(MapperConfig mapperConfig, JavaType javaType, HashMap hashMap, HashMap hashMap2) {
        super(javaType, mapperConfig.getTypeFactory());
        this._config = mapperConfig;
        this._typeToId = hashMap;
        this._idToType = hashMap2;
    }

    protected static String _defaultTypeId(Class cls) {
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf < 0 ? name : name.substring(lastIndexOf + 1);
    }

    public static TypeNameIdResolver construct(MapperConfig mapperConfig, JavaType javaType, Collection collection, boolean z, boolean z2) {
        JavaType javaType2;
        if (z == z2) {
            throw new IllegalArgumentException();
        }
        HashMap hashMap = z ? new HashMap() : null;
        HashMap hashMap2 = z2 ? new HashMap() : null;
        if (collection != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                NamedType namedType = (NamedType) it.next();
                Class type = namedType.getType();
                String name = namedType.hasName() ? namedType.getName() : _defaultTypeId(type);
                if (z) {
                    hashMap.put(type.getName(), name);
                }
                if (z2 && ((javaType2 = (JavaType) hashMap2.get(name)) == null || !type.isAssignableFrom(javaType2.getRawClass()))) {
                    hashMap2.put(name, mapperConfig.constructType(type));
                }
            }
        }
        return new TypeNameIdResolver(mapperConfig, javaType, hashMap, hashMap2);
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.NAME;
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver
    public String idFromValue(Object obj) {
        String str;
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        synchronized (this._typeToId) {
            str = (String) this._typeToId.get(name);
            if (str == null) {
                if (this._config.isAnnotationProcessingEnabled()) {
                    str = this._config.getAnnotationIntrospector().findTypeName(((BasicBeanDescription) this._config.introspectClassAnnotations(cls)).getClassInfo());
                }
                if (str == null) {
                    str = _defaultTypeId(cls);
                }
                this._typeToId.put(name, str);
            }
        }
        return str;
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver
    public String idFromValueAndType(Object obj, Class cls) {
        return idFromValue(obj);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[').append(getClass().getName());
        sb.append("; id-to-type=").append(this._idToType);
        sb.append(']');
        return sb.toString();
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver
    public JavaType typeFromId(String str) {
        return (JavaType) this._idToType.get(str);
    }
}
