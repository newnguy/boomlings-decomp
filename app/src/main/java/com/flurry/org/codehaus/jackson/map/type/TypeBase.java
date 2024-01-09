package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public abstract class TypeBase extends JavaType implements JsonSerializableWithType {
    volatile String _canonicalName;

    @Deprecated
    protected TypeBase(Class cls, int i) {
        super(cls, i);
    }

    public TypeBase(Class cls, int i, Object obj, Object obj2) {
        super(cls, i);
        this._valueHandler = obj;
        this._typeHandler = obj2;
    }

    public static StringBuilder _classSignature(Class cls, StringBuilder sb, boolean z) {
        if (!cls.isPrimitive()) {
            sb.append('L');
            String name = cls.getName();
            int length = name.length();
            for (int i = 0; i < length; i++) {
                char charAt = name.charAt(i);
                if (charAt == '.') {
                    charAt = '/';
                }
                sb.append(charAt);
            }
            if (z) {
                sb.append(';');
            }
        } else if (cls == Boolean.TYPE) {
            sb.append('Z');
        } else if (cls == Byte.TYPE) {
            sb.append('B');
        } else if (cls == Short.TYPE) {
            sb.append('S');
        } else if (cls == Character.TYPE) {
            sb.append('C');
        } else if (cls == Integer.TYPE) {
            sb.append('I');
        } else if (cls == Long.TYPE) {
            sb.append('J');
        } else if (cls == Float.TYPE) {
            sb.append('F');
        } else if (cls == Double.TYPE) {
            sb.append('D');
        } else if (cls != Void.TYPE) {
            throw new IllegalStateException("Unrecognized primitive type: " + cls.getName());
        } else {
            sb.append('V');
        }
        return sb;
    }

    protected abstract String buildCanonicalName();

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public abstract StringBuilder getErasedSignature(StringBuilder sb);

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public abstract StringBuilder getGenericSignature(StringBuilder sb);

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public Object getTypeHandler() {
        return this._typeHandler;
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public Object getValueHandler() {
        return this._valueHandler;
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializable
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeString(toCanonical());
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForScalar(this, jsonGenerator);
        serialize(jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(this, jsonGenerator);
    }

    @Override // com.flurry.org.codehaus.jackson.type.JavaType
    public String toCanonical() {
        String str = this._canonicalName;
        return str == null ? buildCanonicalName() : str;
    }
}
