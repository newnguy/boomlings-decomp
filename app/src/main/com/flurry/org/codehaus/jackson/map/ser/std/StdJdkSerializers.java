package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.io.File;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class StdJdkSerializers implements Provider {

    /* loaded from: classes.dex */
    public final class AtomicBooleanSerializer extends ScalarSerializerBase {
        public AtomicBooleanSerializer() {
            super(AtomicBoolean.class, false);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("boolean", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(AtomicBoolean atomicBoolean, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeBoolean(atomicBoolean.get());
        }
    }

    /* loaded from: classes.dex */
    public final class AtomicIntegerSerializer extends ScalarSerializerBase {
        public AtomicIntegerSerializer() {
            super(AtomicInteger.class, false);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(AtomicInteger atomicInteger, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(atomicInteger.get());
        }
    }

    /* loaded from: classes.dex */
    public final class AtomicLongSerializer extends ScalarSerializerBase {
        public AtomicLongSerializer() {
            super(AtomicLong.class, false);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("integer", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(AtomicLong atomicLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeNumber(atomicLong.get());
        }
    }

    /* loaded from: classes.dex */
    public final class AtomicReferenceSerializer extends SerializerBase {
        public AtomicReferenceSerializer() {
            super(AtomicReference.class, false);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("any", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(AtomicReference atomicReference, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            serializerProvider.defaultSerializeValue(atomicReference.get(), jsonGenerator);
        }
    }

    /* loaded from: classes.dex */
    public final class ClassSerializer extends ScalarSerializerBase {
        public ClassSerializer() {
            super(Class.class, false);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Class cls, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeString(cls.getName());
        }
    }

    /* loaded from: classes.dex */
    public final class FileSerializer extends ScalarSerializerBase {
        public FileSerializer() {
            super(File.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("string", true);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(File file, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            jsonGenerator.writeString(file.getAbsolutePath());
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.util.Provider
    public Collection provide() {
        HashMap hashMap = new HashMap();
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        hashMap.put(URL.class, toStringSerializer);
        hashMap.put(URI.class, toStringSerializer);
        hashMap.put(Currency.class, toStringSerializer);
        hashMap.put(UUID.class, toStringSerializer);
        hashMap.put(Pattern.class, toStringSerializer);
        hashMap.put(Locale.class, toStringSerializer);
        hashMap.put(Locale.class, toStringSerializer);
        hashMap.put(AtomicReference.class, AtomicReferenceSerializer.class);
        hashMap.put(AtomicBoolean.class, AtomicBooleanSerializer.class);
        hashMap.put(AtomicInteger.class, AtomicIntegerSerializer.class);
        hashMap.put(AtomicLong.class, AtomicLongSerializer.class);
        hashMap.put(File.class, FileSerializer.class);
        hashMap.put(Class.class, ClassSerializer.class);
        hashMap.put(Void.TYPE, NullSerializer.class);
        return hashMap.entrySet();
    }
}
