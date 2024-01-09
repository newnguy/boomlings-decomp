package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

@JacksonStdImpl
/* loaded from: classes.dex */
public class JsonValueSerializer extends SerializerBase implements ResolvableSerializer, SchemaAware {
    protected final Method _accessorMethod;
    protected boolean _forceTypeInformation;
    protected final BeanProperty _property;
    protected JsonSerializer _valueSerializer;

    public JsonValueSerializer(Method method, JsonSerializer jsonSerializer, BeanProperty beanProperty) {
        super(Object.class);
        this._accessorMethod = method;
        this._valueSerializer = jsonSerializer;
        this._property = beanProperty;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return this._valueSerializer instanceof SchemaAware ? ((SchemaAware) this._valueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode();
    }

    protected boolean isNaturalTypeWithStdHandling(JavaType javaType, JsonSerializer jsonSerializer) {
        Class rawClass = javaType.getRawClass();
        if (javaType.isPrimitive()) {
            if (rawClass != Integer.TYPE && rawClass != Boolean.TYPE && rawClass != Double.TYPE) {
                return false;
            }
        } else if (rawClass != String.class && rawClass != Integer.class && rawClass != Boolean.class && rawClass != Double.class) {
            return false;
        }
        return jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) != null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) {
        if (this._valueSerializer == null) {
            if (serializerProvider.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING) || Modifier.isFinal(this._accessorMethod.getReturnType().getModifiers())) {
                JavaType constructType = serializerProvider.constructType(this._accessorMethod.getGenericReturnType());
                this._valueSerializer = serializerProvider.findTypedValueSerializer(constructType, false, this._property);
                this._forceTypeInformation = isNaturalTypeWithStdHandling(constructType, this._valueSerializer);
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        try {
            Object invoke = this._accessorMethod.invoke(obj, new Object[0]);
            if (invoke == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                return;
            }
            JsonSerializer jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider.findTypedValueSerializer((Class) invoke.getClass(), true, this._property);
            }
            jsonSerializer.serialize(invoke, jsonGenerator, serializerProvider);
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            e = e2;
            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                e = e.getCause();
            }
            if (!(e instanceof Error)) {
                throw JsonMappingException.wrapWithPath(e, obj, this._accessorMethod.getName() + "()");
            }
            throw ((Error) e);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        try {
            Object invoke = this._accessorMethod.invoke(obj, new Object[0]);
            if (invoke == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
                return;
            }
            JsonSerializer jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                serializerProvider.findTypedValueSerializer((Class) invoke.getClass(), true, this._property).serialize(invoke, jsonGenerator, serializerProvider);
                return;
            }
            if (this._forceTypeInformation) {
                typeSerializer.writeTypePrefixForScalar(obj, jsonGenerator);
            }
            jsonSerializer.serializeWithType(invoke, jsonGenerator, serializerProvider, typeSerializer);
            if (this._forceTypeInformation) {
                typeSerializer.writeTypeSuffixForScalar(obj, jsonGenerator);
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            e = e2;
            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                e = e.getCause();
            }
            if (!(e instanceof Error)) {
                throw JsonMappingException.wrapWithPath(e, obj, this._accessorMethod.getName() + "()");
            }
            throw ((Error) e);
        }
    }

    public String toString() {
        return "(@JsonValue serializer for method " + this._accessorMethod.getDeclaringClass() + "#" + this._accessorMethod.getName() + ")";
    }
}
