package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public final class ArrayType extends TypeBase {
    protected final JavaType _componentType;
    protected final Object _emptyArray;

    private ArrayType(JavaType javaType, Object obj, Object obj2, Object obj3) {
        super(obj.getClass(), javaType.hashCode(), obj2, obj3);
        this._componentType = javaType;
        this._emptyArray = obj;
    }

    @Deprecated
    public static ArrayType construct(JavaType javaType) {
        return construct(javaType, null, null);
    }

    public static ArrayType construct(JavaType javaType, Object obj, Object obj2) {
        return new ArrayType(javaType, Array.newInstance(javaType.getRawClass(), 0), null, null);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class cls) {
        if (cls.isArray()) {
            return construct(TypeFactory.defaultInstance().constructType(cls.getComponentType()), this._valueHandler, this._typeHandler);
        }
        throw new IllegalArgumentException("Incompatible narrowing operation: trying to narrow " + toString() + " to class " + cls.getName());
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        return this._class.getName();
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        return 1;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((ArrayType) obj)._componentType);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType getContentType() {
        return this._componentType;
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase, com.flurry.org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append('[');
        return this._componentType.getErasedSignature(sb);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase, com.flurry.org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        sb.append('[');
        return this._componentType.getGenericSignature(sb);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean isAbstract() {
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean isArrayType() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean isConcrete() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public String toString() {
        return "[array type, component type: " + this._componentType + "]";
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public ArrayType withContentTypeHandler(Object obj) {
        return obj == this._componentType.getTypeHandler() ? this : new ArrayType(this._componentType.withTypeHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public ArrayType withContentValueHandler(Object obj) {
        return obj == this._componentType.getValueHandler() ? this : new ArrayType(this._componentType.withValueHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public ArrayType withTypeHandler(Object obj) {
        return obj == this._typeHandler ? this : new ArrayType(this._componentType, this._emptyArray, this._valueHandler, obj);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public ArrayType withValueHandler(Object obj) {
        return obj == this._valueHandler ? this : new ArrayType(this._componentType, this._emptyArray, obj, this._typeHandler);
    }
}
