package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.FormatSchema;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.SerializableString;
import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class JsonGeneratorDelegate extends JsonGenerator {
    protected JsonGenerator delegate;

    public JsonGeneratorDelegate(JsonGenerator jsonGenerator) {
        this.delegate = jsonGenerator;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void copyCurrentEvent(JsonParser jsonParser) {
        this.delegate.copyCurrentEvent(jsonParser);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void copyCurrentStructure(JsonParser jsonParser) {
        this.delegate.copyCurrentStructure(jsonParser);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        return this.delegate.disable(feature);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        return this.delegate.enable(feature);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void flush() {
        this.delegate.flush();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonStreamContext getOutputContext() {
        return this.delegate.getOutputContext();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public Object getOutputTarget() {
        return this.delegate.getOutputTarget();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this.delegate.setCodec(objectCodec);
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void setSchema(FormatSchema formatSchema) {
        this.delegate.setSchema(formatSchema);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        this.delegate.useDefaultPrettyPrinter();
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator, com.flurry.org.codehaus.jackson.Versioned
    public Version version() {
        return this.delegate.version();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) {
        this.delegate.writeBinary(base64Variant, bArr, i, i2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeBoolean(boolean z) {
        this.delegate.writeBoolean(z);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeEndArray() {
        this.delegate.writeEndArray();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeEndObject() {
        this.delegate.writeEndObject();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializableString serializableString) {
        this.delegate.writeFieldName(serializableString);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializedString serializedString) {
        this.delegate.writeFieldName(serializedString);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(String str) {
        this.delegate.writeFieldName(str);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNull() {
        this.delegate.writeNull();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(double d) {
        this.delegate.writeNumber(d);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(float f) {
        this.delegate.writeNumber(f);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(int i) {
        this.delegate.writeNumber(i);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(long j) {
        this.delegate.writeNumber(j);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(String str) {
        this.delegate.writeNumber(str);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        this.delegate.writeNumber(bigDecimal);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        this.delegate.writeNumber(bigInteger);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeObject(Object obj) {
        this.delegate.writeObject(obj);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char c) {
        this.delegate.writeRaw(c);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str) {
        this.delegate.writeRaw(str);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str, int i, int i2) {
        this.delegate.writeRaw(str, i, i2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) {
        this.delegate.writeRaw(cArr, i, i2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) {
        this.delegate.writeRawUTF8String(bArr, i, i2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str) {
        this.delegate.writeRawValue(str);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str, int i, int i2) {
        this.delegate.writeRawValue(str, i, i2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) {
        this.delegate.writeRawValue(cArr, i, i2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeStartArray() {
        this.delegate.writeStartArray();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeStartObject() {
        this.delegate.writeStartObject();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeString(SerializableString serializableString) {
        this.delegate.writeString(serializableString);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeString(String str) {
        this.delegate.writeString(str);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) {
        this.delegate.writeString(cArr, i, i2);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeTree(JsonNode jsonNode) {
        this.delegate.writeTree(jsonNode);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i, int i2) {
        this.delegate.writeUTF8String(bArr, i, i2);
    }
}
