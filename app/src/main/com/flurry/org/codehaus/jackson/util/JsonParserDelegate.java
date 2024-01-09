package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.FormatSchema;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.Version;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(JsonParser jsonParser) {
        this.delegate = jsonParser;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonParser disable(JsonParser.Feature feature) {
        this.delegate.disable(feature);
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonParser enable(JsonParser.Feature feature) {
        this.delegate.enable(feature);
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public BigInteger getBigIntegerValue() {
        return this.delegate.getBigIntegerValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        return this.delegate.getBinaryValue(base64Variant);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public boolean getBooleanValue() {
        return this.delegate.getBooleanValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public byte getByteValue() {
        return this.delegate.getByteValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public String getCurrentName() {
        return this.delegate.getCurrentName();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public BigDecimal getDecimalValue() {
        return this.delegate.getDecimalValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public double getDoubleValue() {
        return this.delegate.getDoubleValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public Object getEmbeddedObject() {
        return this.delegate.getEmbeddedObject();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public float getFloatValue() {
        return this.delegate.getFloatValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this.delegate.getInputSource();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int getIntValue() {
        return this.delegate.getIntValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonToken getLastClearedToken() {
        return this.delegate.getLastClearedToken();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public long getLongValue() {
        return this.delegate.getLongValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonParser.NumberType getNumberType() {
        return this.delegate.getNumberType();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public Number getNumberValue() {
        return this.delegate.getNumberValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonStreamContext getParsingContext() {
        return this.delegate.getParsingContext();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public short getShortValue() {
        return this.delegate.getShortValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public String getText() {
        return this.delegate.getText();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() {
        return this.delegate.getTextCharacters();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int getTextLength() {
        return this.delegate.getTextLength();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public int getTextOffset() {
        return this.delegate.getTextOffset();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public boolean isEnabled(JsonParser.Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonToken nextToken() {
        return this.delegate.nextToken();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this.delegate.setCodec(objectCodec);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public void setSchema(FormatSchema formatSchema) {
        this.delegate.setSchema(formatSchema);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() {
        this.delegate.skipChildren();
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonParser, com.flurry.org.codehaus.jackson.Versioned
    public Version version() {
        return this.delegate.version();
    }
}
