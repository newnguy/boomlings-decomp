package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public final class MapType extends MapLikeType {
    @Deprecated
    private MapType(Class cls, JavaType javaType, JavaType javaType2) {
        this(cls, javaType, javaType2, null, null);
    }

    private MapType(Class cls, JavaType javaType, JavaType javaType2, Object obj, Object obj2) {
        super(cls, javaType, javaType2, obj, obj2);
    }

    public static MapType construct(Class cls, JavaType javaType, JavaType javaType2) {
        return new MapType(cls, javaType, javaType2, null, null);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType, com.flurry.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class cls) {
        return new MapType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType, com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class cls) {
        return cls == this._valueType.getRawClass() ? this : new MapType(this._class, this._keyType, this._valueType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType
    public JavaType narrowKey(Class cls) {
        return cls == this._keyType.getRawClass() ? this : new MapType(this._class, this._keyType.narrowBy(cls), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType, com.flurry.org.codehaus.jackson.type.JavaType
    public String toString() {
        return "[map type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType, com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class cls) {
        return cls == this._valueType.getRawClass() ? this : new MapType(this._class, this._keyType, this._valueType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType
    public JavaType widenKey(Class cls) {
        return cls == this._keyType.getRawClass() ? this : new MapType(this._class, this._keyType.widenBy(cls), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType, com.flurry.org.codehaus.jackson.type.JavaType
    public MapType withContentTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType.withTypeHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType, com.flurry.org.codehaus.jackson.type.JavaType
    public MapType withContentValueHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType.withValueHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType
    public MapType withKeyTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType.withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType
    public MapType withKeyValueHandler(Object obj) {
        return new MapType(this._class, this._keyType.withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType, com.flurry.org.codehaus.jackson.type.JavaType
    public MapType withTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType, this._valueHandler, obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.MapLikeType, com.flurry.org.codehaus.jackson.type.JavaType
    public MapType withValueHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType, obj, this._typeHandler);
    }
}
