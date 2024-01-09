package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Map;

/* loaded from: classes.dex */
public class MapLikeType extends TypeBase {
    protected final JavaType _keyType;
    protected final JavaType _valueType;

    @Deprecated
    protected MapLikeType(Class cls, JavaType javaType, JavaType javaType2) {
        super(cls, javaType.hashCode() ^ javaType2.hashCode(), null, null);
        this._keyType = javaType;
        this._valueType = javaType2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MapLikeType(Class cls, JavaType javaType, JavaType javaType2, Object obj, Object obj2) {
        super(cls, javaType.hashCode() ^ javaType2.hashCode(), obj, obj2);
        this._keyType = javaType;
        this._valueType = javaType2;
    }

    public static MapLikeType construct(Class cls, JavaType javaType, JavaType javaType2) {
        return new MapLikeType(cls, javaType, javaType2, null, null);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class cls) {
        return new MapLikeType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._keyType != null) {
            sb.append('<');
            sb.append(this._keyType.toCanonical());
            sb.append(',');
            sb.append(this._valueType.toCanonical());
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i == 0) {
            return this._keyType;
        }
        if (i == 1) {
            return this._valueType;
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        return 2;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        if (i == 0) {
            return "K";
        }
        if (i == 1) {
            return "V";
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            MapLikeType mapLikeType = (MapLikeType) obj;
            return this._class == mapLikeType._class && this._keyType.equals(mapLikeType._keyType) && this._valueType.equals(mapLikeType._valueType);
        }
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType getContentType() {
        return this._valueType;
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase, com.flurry.org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase, com.flurry.org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        sb.append('<');
        this._keyType.getGenericSignature(sb);
        this._valueType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType getKeyType() {
        return this._keyType;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean isMapLikeType() {
        return true;
    }

    public boolean isTrueMapType() {
        return Map.class.isAssignableFrom(this._class);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class cls) {
        return cls == this._valueType.getRawClass() ? this : new MapLikeType(this._class, this._keyType, this._valueType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    public JavaType narrowKey(Class cls) {
        return cls == this._keyType.getRawClass() ? this : new MapLikeType(this._class, this._keyType.narrowBy(cls), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public String toString() {
        return "[map-like type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class cls) {
        return cls == this._valueType.getRawClass() ? this : new MapLikeType(this._class, this._keyType, this._valueType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    public JavaType widenKey(Class cls) {
        return cls == this._keyType.getRawClass() ? this : new MapLikeType(this._class, this._keyType.widenBy(cls), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public MapLikeType withContentTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType.withTypeHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public MapLikeType withContentValueHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType.withValueHandler(obj), this._valueHandler, this._typeHandler);
    }

    public MapLikeType withKeyTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType.withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler);
    }

    public MapLikeType withKeyValueHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType.withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public MapLikeType withTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType, this._valueHandler, obj);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public MapLikeType withValueHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType, obj, this._typeHandler);
    }
}
