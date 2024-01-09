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

public class JsonGeneratorDelegate extends JsonGenerator {
  protected JsonGenerator delegate;
  
  public JsonGeneratorDelegate(JsonGenerator paramJsonGenerator) {
    this.delegate = paramJsonGenerator;
  }
  
  public boolean canUseSchema(FormatSchema paramFormatSchema) {
    return this.delegate.canUseSchema(paramFormatSchema);
  }
  
  public void close() {
    this.delegate.close();
  }
  
  public void copyCurrentEvent(JsonParser paramJsonParser) {
    this.delegate.copyCurrentEvent(paramJsonParser);
  }
  
  public void copyCurrentStructure(JsonParser paramJsonParser) {
    this.delegate.copyCurrentStructure(paramJsonParser);
  }
  
  public JsonGenerator disable(JsonGenerator.Feature paramFeature) {
    return this.delegate.disable(paramFeature);
  }
  
  public JsonGenerator enable(JsonGenerator.Feature paramFeature) {
    return this.delegate.enable(paramFeature);
  }
  
  public void flush() {
    this.delegate.flush();
  }
  
  public ObjectCodec getCodec() {
    return this.delegate.getCodec();
  }
  
  public JsonStreamContext getOutputContext() {
    return this.delegate.getOutputContext();
  }
  
  public Object getOutputTarget() {
    return this.delegate.getOutputTarget();
  }
  
  public boolean isClosed() {
    return this.delegate.isClosed();
  }
  
  public boolean isEnabled(JsonGenerator.Feature paramFeature) {
    return this.delegate.isEnabled(paramFeature);
  }
  
  public JsonGenerator setCodec(ObjectCodec paramObjectCodec) {
    this.delegate.setCodec(paramObjectCodec);
    return this;
  }
  
  public void setSchema(FormatSchema paramFormatSchema) {
    this.delegate.setSchema(paramFormatSchema);
  }
  
  public JsonGenerator useDefaultPrettyPrinter() {
    this.delegate.useDefaultPrettyPrinter();
    return this;
  }
  
  public Version version() {
    return this.delegate.version();
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.delegate.writeBinary(paramBase64Variant, paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeBoolean(boolean paramBoolean) {
    this.delegate.writeBoolean(paramBoolean);
  }
  
  public void writeEndArray() {
    this.delegate.writeEndArray();
  }
  
  public void writeEndObject() {
    this.delegate.writeEndObject();
  }
  
  public void writeFieldName(SerializableString paramSerializableString) {
    this.delegate.writeFieldName(paramSerializableString);
  }
  
  public void writeFieldName(SerializedString paramSerializedString) {
    this.delegate.writeFieldName(paramSerializedString);
  }
  
  public void writeFieldName(String paramString) {
    this.delegate.writeFieldName(paramString);
  }
  
  public void writeNull() {
    this.delegate.writeNull();
  }
  
  public void writeNumber(double paramDouble) {
    this.delegate.writeNumber(paramDouble);
  }
  
  public void writeNumber(float paramFloat) {
    this.delegate.writeNumber(paramFloat);
  }
  
  public void writeNumber(int paramInt) {
    this.delegate.writeNumber(paramInt);
  }
  
  public void writeNumber(long paramLong) {
    this.delegate.writeNumber(paramLong);
  }
  
  public void writeNumber(String paramString) {
    this.delegate.writeNumber(paramString);
  }
  
  public void writeNumber(BigDecimal paramBigDecimal) {
    this.delegate.writeNumber(paramBigDecimal);
  }
  
  public void writeNumber(BigInteger paramBigInteger) {
    this.delegate.writeNumber(paramBigInteger);
  }
  
  public void writeObject(Object paramObject) {
    this.delegate.writeObject(paramObject);
  }
  
  public void writeRaw(char paramChar) {
    this.delegate.writeRaw(paramChar);
  }
  
  public void writeRaw(String paramString) {
    this.delegate.writeRaw(paramString);
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2) {
    this.delegate.writeRaw(paramString, paramInt1, paramInt2);
  }
  
  public void writeRaw(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this.delegate.writeRaw(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.delegate.writeRawUTF8String(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeRawValue(String paramString) {
    this.delegate.writeRawValue(paramString);
  }
  
  public void writeRawValue(String paramString, int paramInt1, int paramInt2) {
    this.delegate.writeRawValue(paramString, paramInt1, paramInt2);
  }
  
  public void writeRawValue(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this.delegate.writeRawValue(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public void writeStartArray() {
    this.delegate.writeStartArray();
  }
  
  public void writeStartObject() {
    this.delegate.writeStartObject();
  }
  
  public void writeString(SerializableString paramSerializableString) {
    this.delegate.writeString(paramSerializableString);
  }
  
  public void writeString(String paramString) {
    this.delegate.writeString(paramString);
  }
  
  public void writeString(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this.delegate.writeString(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public void writeTree(JsonNode paramJsonNode) {
    this.delegate.writeTree(paramJsonNode);
  }
  
  public void writeUTF8String(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.delegate.writeUTF8String(paramArrayOfbyte, paramInt1, paramInt2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\JsonGeneratorDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */