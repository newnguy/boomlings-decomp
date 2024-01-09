package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.EnumSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class EnumSetSerializer extends AsArraySerializerBase {
    public EnumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        super(EnumSet.class, javaType, true, null, beanProperty, null);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.AsArraySerializerBase
    public void serializeContents(EnumSet enumSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        JsonSerializer jsonSerializer = this._elementSerializer;
        Iterator it = enumSet.iterator();
        JsonSerializer jsonSerializer2 = jsonSerializer;
        while (it.hasNext()) {
            Enum r0 = (Enum) it.next();
            if (jsonSerializer2 == null) {
                jsonSerializer2 = serializerProvider.findValueSerializer(r0.getDeclaringClass(), this._property);
            }
            jsonSerializer2.serialize(r0, jsonGenerator, serializerProvider);
        }
    }
}
