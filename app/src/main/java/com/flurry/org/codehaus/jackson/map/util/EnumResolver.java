package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import java.util.HashMap;

/* loaded from: classes.dex */
public class EnumResolver {
    protected final Class _enumClass;
    protected final Enum[] _enums;
    protected final HashMap _enumsById;

    public EnumResolver(Class cls, Enum[] enumArr, HashMap hashMap) {
        this._enumClass = cls;
        this._enums = enumArr;
        this._enumsById = hashMap;
    }

    public static EnumResolver constructFor(Class cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        if (enumArr == null) {
            throw new IllegalArgumentException("No enum constants for class " + cls.getName());
        }
        HashMap hashMap = new HashMap();
        for (Enum r4 : enumArr) {
            hashMap.put(annotationIntrospector.findEnumValue(r4), r4);
        }
        return new EnumResolver(cls, enumArr, hashMap);
    }

    public static EnumResolver constructUnsafe(Class cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver constructUnsafeUsingToString(Class cls) {
        return constructUsingToString(cls);
    }

    public static EnumResolver constructUsingToString(Class cls) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return new EnumResolver(cls, enumArr, hashMap);
            }
            Enum r3 = enumArr[length];
            hashMap.put(r3.toString(), r3);
        }
    }

    public Enum findEnum(String str) {
        return (Enum) this._enumsById.get(str);
    }

    public Enum getEnum(int i) {
        if (i < 0 || i >= this._enums.length) {
            return null;
        }
        return this._enums[i];
    }

    public Class getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
