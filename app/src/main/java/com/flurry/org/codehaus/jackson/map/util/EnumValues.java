package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class EnumValues {
    private final EnumMap _values;

    private EnumValues(Map map) {
        this._values = new EnumMap(map);
    }

    public static EnumValues construct(Class cls, AnnotationIntrospector annotationIntrospector) {
        return constructFromName(cls, annotationIntrospector);
    }

    public static EnumValues constructFromName(Class cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType(cls).getEnumConstants();
        if (enumArr != null) {
            HashMap hashMap = new HashMap();
            for (Enum r4 : enumArr) {
                hashMap.put(r4, new SerializedString(annotationIntrospector.findEnumValue(r4)));
            }
            return new EnumValues(hashMap);
        }
        throw new IllegalArgumentException("Can not determine enum constants for Class " + cls.getName());
    }

    public static EnumValues constructFromToString(Class cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType(cls).getEnumConstants();
        if (enumArr != null) {
            HashMap hashMap = new HashMap();
            for (Enum r4 : enumArr) {
                hashMap.put(r4, new SerializedString(r4.toString()));
            }
            return new EnumValues(hashMap);
        }
        throw new IllegalArgumentException("Can not determine enum constants for Class " + cls.getName());
    }

    public SerializedString serializedValueFor(Enum r2) {
        return (SerializedString) this._values.get(r2);
    }

    @Deprecated
    public String valueFor(Enum r2) {
        SerializedString serializedString = (SerializedString) this._values.get(r2);
        if (serializedString == null) {
            return null;
        }
        return serializedString.getValue();
    }

    public Collection values() {
        return this._values.values();
    }
}
