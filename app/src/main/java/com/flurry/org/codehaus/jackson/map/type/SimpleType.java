package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public final class SimpleType extends TypeBase {
    protected final String[] _typeNames;
    protected final JavaType[] _typeParameters;

    public SimpleType(Class cls) {
        this(cls, null, null, null, null);
    }

    @Deprecated
    protected SimpleType(Class cls, String[] strArr, JavaType[] javaTypeArr) {
        this(cls, strArr, javaTypeArr, null, null);
    }

    public SimpleType(Class cls, String[] strArr, JavaType[] javaTypeArr, Object obj, Object obj2) {
        super(cls, 0, obj, obj2);
        if (strArr == null || strArr.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr;
        this._typeParameters = javaTypeArr;
    }

    public static SimpleType construct(Class cls) {
        if (Map.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Can not construct SimpleType for a Map (class: " + cls.getName() + ")");
        }
        if (Collection.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Can not construct SimpleType for a Collection (class: " + cls.getName() + ")");
        }
        if (cls.isArray()) {
            throw new IllegalArgumentException("Can not construct SimpleType for an array (class: " + cls.getName() + ")");
        }
        return new SimpleType(cls);
    }

    public static SimpleType constructUnsafe(Class cls) {
        return new SimpleType(cls, null, null, null, null);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class cls) {
        return new SimpleType(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        JavaType[] javaTypeArr;
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._typeParameters != null && this._typeParameters.length > 0) {
            sb.append('<');
            boolean z = true;
            for (JavaType javaType : this._typeParameters) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(javaType.toCanonical());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i < 0 || this._typeParameters == null || i >= this._typeParameters.length) {
            return null;
        }
        return this._typeParameters[i];
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        if (this._typeParameters == null) {
            return 0;
        }
        return this._typeParameters.length;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        if (i < 0 || this._typeNames == null || i >= this._typeNames.length) {
            return null;
        }
        return this._typeNames[i];
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        SimpleType simpleType = (SimpleType) obj;
        if (simpleType._class == this._class) {
            JavaType[] javaTypeArr = this._typeParameters;
            JavaType[] javaTypeArr2 = simpleType._typeParameters;
            if (javaTypeArr == null) {
                return javaTypeArr2 == null || javaTypeArr2.length == 0;
            } else if (javaTypeArr2 == null || javaTypeArr.length != javaTypeArr2.length) {
                return false;
            } else {
                int length = javaTypeArr.length;
                for (int i = 0; i < length; i++) {
                    if (!javaTypeArr[i].equals(javaTypeArr2[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase, com.flurry.org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    @Override // com.flurry.org.codehaus.jackson.map.type.TypeBase, com.flurry.org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        if (this._typeParameters != null) {
            sb.append('<');
            for (JavaType javaType : this._typeParameters) {
                sb = javaType.getGenericSignature(sb);
            }
            sb.append('>');
        }
        sb.append(';');
        return sb;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ").append(buildCanonicalName()).append(']');
        return sb.toString();
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public JavaType withContentTypeHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public SimpleType withContentValueHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public SimpleType withTypeHandler(Object obj) {
        return new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public SimpleType withValueHandler(Object obj) {
        return obj == this._valueHandler ? this : new SimpleType(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler);
    }
}
