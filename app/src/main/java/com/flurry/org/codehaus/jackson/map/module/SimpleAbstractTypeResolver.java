package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.AbstractTypeResolver;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SimpleAbstractTypeResolver extends AbstractTypeResolver {
    protected final HashMap _mappings = new HashMap();

    public SimpleAbstractTypeResolver addMapping(Class cls, Class cls2) {
        if (cls == cls2) {
            throw new IllegalArgumentException("Can not add mapping from class to itself");
        }
        if (cls.isAssignableFrom(cls2)) {
            if (Modifier.isAbstract(cls.getModifiers())) {
                this._mappings.put(new ClassKey(cls), cls2);
                return this;
            }
            throw new IllegalArgumentException("Can not add mapping from class " + cls.getName() + " since it is not abstract");
        }
        throw new IllegalArgumentException("Can not add mapping from class " + cls.getName() + " to " + cls2.getName() + ", as latter is not a subtype of former");
    }

    @Override // com.flurry.org.codehaus.jackson.map.AbstractTypeResolver
    public JavaType findTypeMapping(DeserializationConfig deserializationConfig, JavaType javaType) {
        Class cls = (Class) this._mappings.get(new ClassKey(javaType.getRawClass()));
        if (cls == null) {
            return null;
        }
        return javaType.narrowBy(cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AbstractTypeResolver
    public JavaType resolveAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        return null;
    }
}
