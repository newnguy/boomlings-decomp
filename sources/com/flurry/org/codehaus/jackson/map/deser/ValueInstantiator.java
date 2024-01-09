package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public abstract class ValueInstantiator {
    public boolean canCreateFromBoolean() {
        return false;
    }

    public boolean canCreateFromDouble() {
        return false;
    }

    public boolean canCreateFromInt() {
        return false;
    }

    public boolean canCreateFromLong() {
        return false;
    }

    public boolean canCreateFromObjectWith() {
        return false;
    }

    public boolean canCreateFromString() {
        return false;
    }

    public boolean canCreateUsingDefault() {
        return getDefaultCreator() != null;
    }

    public boolean canCreateUsingDelegate() {
        return getDelegateType() != null;
    }

    public boolean canInstantiate() {
        return canCreateUsingDefault() || canCreateUsingDelegate() || canCreateFromObjectWith() || canCreateFromString() || canCreateFromInt() || canCreateFromLong() || canCreateFromDouble() || canCreateFromBoolean();
    }

    public Object createFromBoolean(boolean z) {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON boolean value");
    }

    public Object createFromDouble(double d) {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON floating-point number");
    }

    public Object createFromInt(int i) {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON int number");
    }

    public Object createFromLong(long j) {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON long number");
    }

    public Object createFromObjectWith(Object[] objArr) {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " with arguments");
    }

    public Object createFromString(String str) {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON String");
    }

    public Object createUsingDefault() {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + "; no default creator found");
    }

    public Object createUsingDelegate(Object obj) {
        throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " using delegate");
    }

    public AnnotatedWithParams getDefaultCreator() {
        return null;
    }

    public AnnotatedWithParams getDelegateCreator() {
        return null;
    }

    public JavaType getDelegateType() {
        return null;
    }

    public SettableBeanProperty[] getFromObjectArguments() {
        return null;
    }

    public abstract String getValueTypeDesc();

    public AnnotatedWithParams getWithArgsCreator() {
        return null;
    }
}
