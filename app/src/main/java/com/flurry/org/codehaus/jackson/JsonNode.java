package com.flurry.org.codehaus.jackson;

import com.flurry.org.codehaus.jackson.JsonParser;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class JsonNode implements Iterable {
    protected static final List NO_NODES = Collections.emptyList();
    protected static final List NO_STRINGS = Collections.emptyList();

    public boolean asBoolean() {
        return asBoolean(false);
    }

    public boolean asBoolean(boolean z) {
        return z;
    }

    public double asDouble() {
        return asDouble(0.0d);
    }

    public double asDouble(double d) {
        return d;
    }

    public int asInt() {
        return asInt(0);
    }

    public int asInt(int i) {
        return i;
    }

    public long asLong() {
        return asLong(0L);
    }

    public long asLong(long j) {
        return j;
    }

    public abstract String asText();

    public abstract JsonToken asToken();

    public abstract boolean equals(Object obj);

    public abstract JsonNode findParent(String str);

    public final List findParents(String str) {
        List findParents = findParents(str, null);
        return findParents == null ? Collections.emptyList() : findParents;
    }

    public abstract List findParents(String str, List list);

    public abstract JsonNode findPath(String str);

    public abstract JsonNode findValue(String str);

    public final List findValues(String str) {
        List findValues = findValues(str, null);
        return findValues == null ? Collections.emptyList() : findValues;
    }

    public abstract List findValues(String str, List list);

    public final List findValuesAsText(String str) {
        List findValuesAsText = findValuesAsText(str, null);
        return findValuesAsText == null ? Collections.emptyList() : findValuesAsText;
    }

    public abstract List findValuesAsText(String str, List list);

    public JsonNode get(int i) {
        return null;
    }

    public JsonNode get(String str) {
        return null;
    }

    public BigInteger getBigIntegerValue() {
        return BigInteger.ZERO;
    }

    public byte[] getBinaryValue() {
        return null;
    }

    public boolean getBooleanValue() {
        return false;
    }

    public BigDecimal getDecimalValue() {
        return BigDecimal.ZERO;
    }

    public double getDoubleValue() {
        return 0.0d;
    }

    public Iterator getElements() {
        return NO_NODES.iterator();
    }

    public Iterator getFieldNames() {
        return NO_STRINGS.iterator();
    }

    public Iterator getFields() {
        return Collections.emptyList().iterator();
    }

    public int getIntValue() {
        return 0;
    }

    public long getLongValue() {
        return 0L;
    }

    public abstract JsonParser.NumberType getNumberType();

    public Number getNumberValue() {
        return null;
    }

    @Deprecated
    public final JsonNode getPath(int i) {
        return path(i);
    }

    @Deprecated
    public final JsonNode getPath(String str) {
        return path(str);
    }

    public String getTextValue() {
        return null;
    }

    @Deprecated
    public boolean getValueAsBoolean() {
        return asBoolean(false);
    }

    @Deprecated
    public boolean getValueAsBoolean(boolean z) {
        return asBoolean(z);
    }

    @Deprecated
    public double getValueAsDouble() {
        return asDouble(0.0d);
    }

    @Deprecated
    public double getValueAsDouble(double d) {
        return asDouble(d);
    }

    @Deprecated
    public int getValueAsInt() {
        return asInt(0);
    }

    @Deprecated
    public int getValueAsInt(int i) {
        return asInt(i);
    }

    @Deprecated
    public long getValueAsLong() {
        return asLong(0L);
    }

    @Deprecated
    public long getValueAsLong(long j) {
        return asLong(j);
    }

    @Deprecated
    public String getValueAsText() {
        return asText();
    }

    public boolean has(int i) {
        return get(i) != null;
    }

    public boolean has(String str) {
        return get(str) != null;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isBigDecimal() {
        return false;
    }

    public boolean isBigInteger() {
        return false;
    }

    public boolean isBinary() {
        return false;
    }

    public boolean isBoolean() {
        return false;
    }

    public boolean isContainerNode() {
        return false;
    }

    public boolean isDouble() {
        return false;
    }

    public boolean isFloatingPointNumber() {
        return false;
    }

    public boolean isInt() {
        return false;
    }

    public boolean isIntegralNumber() {
        return false;
    }

    public boolean isLong() {
        return false;
    }

    public boolean isMissingNode() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isObject() {
        return false;
    }

    public boolean isPojo() {
        return false;
    }

    public boolean isTextual() {
        return false;
    }

    public boolean isValueNode() {
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return getElements();
    }

    public abstract JsonNode path(int i);

    public abstract JsonNode path(String str);

    public int size() {
        return 0;
    }

    public abstract String toString();

    public abstract JsonParser traverse();

    public JsonNode with(String str) {
        throw new UnsupportedOperationException("JsonNode not of type ObjectNode (but " + getClass().getName() + "), can not call with() on it");
    }
}
