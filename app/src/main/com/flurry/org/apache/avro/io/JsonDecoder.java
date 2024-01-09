package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.file.DataFileConstants;
import com.flurry.org.apache.avro.io.parsing.JsonGrammarGenerator;
import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.util.Utf8;
import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import java.io.EOFException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* loaded from: classes.dex */
public class JsonDecoder extends ParsingDecoder implements Parser.ActionHandler {
    static final String CHARSET = "ISO-8859-1";
    private static JsonFactory jsonFactory = new JsonFactory();
    ReorderBuffer currentReorderBuffer;
    private JsonParser in;
    Stack reorderBuffers;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class JsonElement {
        public final JsonToken token;
        public final String value;

        public JsonElement(JsonToken jsonToken) {
            this(jsonToken, null);
        }

        public JsonElement(JsonToken jsonToken, String str) {
            this.token = jsonToken;
            this.value = str;
        }
    }

    /* loaded from: classes.dex */
    class ReorderBuffer {
        public JsonParser origParser;
        public Map savedFields;

        private ReorderBuffer() {
            this.savedFields = new HashMap();
            this.origParser = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonDecoder(Schema schema, InputStream inputStream) {
        this(getSymbol(schema), inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonDecoder(Schema schema, String str) {
        this(getSymbol(schema), str);
    }

    private JsonDecoder(Symbol symbol, InputStream inputStream) {
        super(symbol);
        this.reorderBuffers = new Stack();
        configure(inputStream);
    }

    private JsonDecoder(Symbol symbol, String str) {
        super(symbol);
        this.reorderBuffers = new Stack();
        configure(str);
    }

    private void advance(Symbol symbol) {
        this.parser.processTrailingImplicitActions();
        if (this.in.getCurrentToken() == null && this.parser.depth() == 1) {
            throw new EOFException();
        }
        this.parser.advance(symbol);
    }

    private void checkFixed(int i) {
        advance(Symbol.FIXED);
        Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction) this.parser.popSymbol();
        if (i != intCheckAction.size) {
            throw new AvroTypeException("Incorrect length for fixed binary: expected " + intCheckAction.size + " but received " + i + " bytes.");
        }
    }

    private long doArrayNext() {
        if (this.in.getCurrentToken() == JsonToken.END_ARRAY) {
            this.parser.advance(Symbol.ARRAY_END);
            this.in.nextToken();
            return 0L;
        }
        return 1L;
    }

    private long doMapNext() {
        if (this.in.getCurrentToken() == JsonToken.END_OBJECT) {
            this.in.nextToken();
            advance(Symbol.MAP_END);
            return 0L;
        }
        return 1L;
    }

    private void doSkipFixed(int i) {
        if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
            throw error("fixed");
        }
        byte[] readByteArray = readByteArray();
        this.in.nextToken();
        if (readByteArray.length != i) {
            throw new AvroTypeException("Expected fixed length " + i + ", but got" + readByteArray.length);
        }
    }

    private AvroTypeException error(String str) {
        return new AvroTypeException("Expected " + str + ". Got " + this.in.getCurrentToken());
    }

    private static Symbol getSymbol(Schema schema) {
        if (schema == null) {
            throw new NullPointerException("Schema cannot be null!");
        }
        return new JsonGrammarGenerator().generate(schema);
    }

    private static List getVaueAsTree(JsonParser jsonParser) {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        do {
            JsonToken currentToken = jsonParser.getCurrentToken();
            switch (currentToken) {
                case START_OBJECT:
                case START_ARRAY:
                    i++;
                    arrayList.add(new JsonElement(currentToken));
                    break;
                case END_OBJECT:
                case END_ARRAY:
                    i--;
                    arrayList.add(new JsonElement(currentToken));
                    break;
                case FIELD_NAME:
                case VALUE_STRING:
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                case VALUE_TRUE:
                case VALUE_FALSE:
                case VALUE_NULL:
                    arrayList.add(new JsonElement(currentToken, jsonParser.getText()));
                    break;
            }
            jsonParser.nextToken();
        } while (i != 0);
        arrayList.add(new JsonElement(null));
        return arrayList;
    }

    private JsonParser makeParser(final List list) {
        return new JsonParser() { // from class: com.flurry.org.apache.avro.io.JsonDecoder.1
            int pos = 0;

            @Override // com.flurry.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public BigInteger getBigIntegerValue() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public byte[] getBinaryValue(Base64Variant base64Variant) {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public ObjectCodec getCodec() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public JsonLocation getCurrentLocation() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public String getCurrentName() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public JsonToken getCurrentToken() {
                return ((JsonElement) list.get(this.pos)).token;
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public BigDecimal getDecimalValue() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public double getDoubleValue() {
                return Double.parseDouble(getText());
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public float getFloatValue() {
                return Float.parseFloat(getText());
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public int getIntValue() {
                return Integer.parseInt(getText());
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public long getLongValue() {
                return Long.parseLong(getText());
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public JsonParser.NumberType getNumberType() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public Number getNumberValue() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public JsonStreamContext getParsingContext() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public String getText() {
                return ((JsonElement) list.get(this.pos)).value;
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public char[] getTextCharacters() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public int getTextLength() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public int getTextOffset() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public JsonLocation getTokenLocation() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public boolean isClosed() {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public JsonToken nextToken() {
                this.pos++;
                return ((JsonElement) list.get(this.pos)).token;
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public void setCodec(ObjectCodec objectCodec) {
                throw new UnsupportedOperationException();
            }

            @Override // com.flurry.org.codehaus.jackson.JsonParser
            public JsonParser skipChildren() {
                int i = 0;
                while (true) {
                    int i2 = i;
                    int[] iArr = AnonymousClass2.$SwitchMap$org$codehaus$jackson$JsonToken;
                    List list2 = list;
                    int i3 = this.pos;
                    this.pos = i3 + 1;
                    switch (iArr[((JsonElement) list2.get(i3)).token.ordinal()]) {
                        case 1:
                        case 2:
                            i = i2 + 1;
                            break;
                        case 3:
                        case 4:
                            i = i2 - 1;
                            break;
                        default:
                            i = i2;
                            break;
                    }
                    if (i <= 0) {
                        return this;
                    }
                }
            }
        };
    }

    private byte[] readByteArray() {
        return this.in.getText().getBytes(CHARSET);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long arrayNext() {
        advance(Symbol.ITEM_END);
        return doArrayNext();
    }

    public JsonDecoder configure(InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException("InputStream to read from cannot be null!");
        }
        this.parser.reset();
        this.in = jsonFactory.createJsonParser(inputStream);
        this.in.nextToken();
        return this;
    }

    public JsonDecoder configure(String str) {
        if (str == null) {
            throw new NullPointerException("String to read from cannot be null!");
        }
        this.parser.reset();
        this.in = new JsonFactory().createJsonParser(str);
        this.in.nextToken();
        return this;
    }

    @Override // com.flurry.org.apache.avro.io.parsing.Parser.ActionHandler
    public Symbol doAction(Symbol symbol, Symbol symbol2) {
        List list;
        if (symbol2 instanceof Symbol.FieldAdjustAction) {
            Symbol.FieldAdjustAction fieldAdjustAction = (Symbol.FieldAdjustAction) symbol2;
            String str = fieldAdjustAction.fname;
            if (this.currentReorderBuffer != null && (list = (List) this.currentReorderBuffer.savedFields.get(str)) != null) {
                this.currentReorderBuffer.savedFields.remove(str);
                this.currentReorderBuffer.origParser = this.in;
                this.in = makeParser(list);
            } else if (this.in.getCurrentToken() == JsonToken.FIELD_NAME) {
                do {
                    String text = this.in.getText();
                    this.in.nextToken();
                    if (!str.equals(text)) {
                        if (this.currentReorderBuffer == null) {
                            this.currentReorderBuffer = new ReorderBuffer();
                        }
                        this.currentReorderBuffer.savedFields.put(text, getVaueAsTree(this.in));
                    }
                } while (this.in.getCurrentToken() == JsonToken.FIELD_NAME);
                throw new AvroTypeException("Expected field name not found: " + fieldAdjustAction.fname);
            }
        } else if (symbol2 == Symbol.FIELD_END) {
            if (this.currentReorderBuffer != null && this.currentReorderBuffer.origParser != null) {
                this.in = this.currentReorderBuffer.origParser;
                this.currentReorderBuffer.origParser = null;
            }
        } else if (symbol2 == Symbol.RECORD_START) {
            if (this.in.getCurrentToken() != JsonToken.START_OBJECT) {
                throw error("record-start");
            }
            this.in.nextToken();
            this.reorderBuffers.push(this.currentReorderBuffer);
            this.currentReorderBuffer = null;
        } else if (symbol2 != Symbol.RECORD_END && symbol2 != Symbol.UNION_END) {
            throw new AvroTypeException("Unknown action symbol " + symbol2);
        } else {
            if (this.in.getCurrentToken() != JsonToken.END_OBJECT) {
                throw error(symbol2 == Symbol.RECORD_END ? "record-end" : "union-end");
            }
            this.in.nextToken();
            if (symbol2 == Symbol.RECORD_END) {
                if (this.currentReorderBuffer != null && !this.currentReorderBuffer.savedFields.isEmpty()) {
                    throw error("Unknown fields: " + this.currentReorderBuffer.savedFields.keySet());
                }
                this.currentReorderBuffer = (ReorderBuffer) this.reorderBuffers.pop();
            }
        }
        return null;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long mapNext() {
        advance(Symbol.ITEM_END);
        return doMapNext();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readArrayStart() {
        advance(Symbol.ARRAY_START);
        if (this.in.getCurrentToken() == JsonToken.START_ARRAY) {
            this.in.nextToken();
            return doArrayNext();
        }
        throw error("array-start");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public boolean readBoolean() {
        advance(Symbol.BOOLEAN);
        JsonToken currentToken = this.in.getCurrentToken();
        if (currentToken == JsonToken.VALUE_TRUE || currentToken == JsonToken.VALUE_FALSE) {
            this.in.nextToken();
            return currentToken == JsonToken.VALUE_TRUE;
        }
        throw error("boolean");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public ByteBuffer readBytes(ByteBuffer byteBuffer) {
        advance(Symbol.BYTES);
        if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
            byte[] readByteArray = readByteArray();
            this.in.nextToken();
            return ByteBuffer.wrap(readByteArray);
        }
        throw error("bytes");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public double readDouble() {
        advance(Symbol.DOUBLE);
        if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
            double doubleValue = this.in.getDoubleValue();
            this.in.nextToken();
            return doubleValue;
        }
        throw error("double");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readEnum() {
        advance(Symbol.ENUM);
        Symbol.EnumLabelsAction enumLabelsAction = (Symbol.EnumLabelsAction) this.parser.popSymbol();
        if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
            this.in.getText();
            int findLabel = enumLabelsAction.findLabel(this.in.getText());
            if (findLabel >= 0) {
                this.in.nextToken();
                return findLabel;
            }
            throw new AvroTypeException("Unknown symbol in enum " + this.in.getText());
        }
        throw error("fixed");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void readFixed(byte[] bArr, int i, int i2) {
        checkFixed(i2);
        if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
            throw error("fixed");
        }
        byte[] readByteArray = readByteArray();
        this.in.nextToken();
        if (readByteArray.length != i2) {
            throw new AvroTypeException("Expected fixed length " + i2 + ", but got" + readByteArray.length);
        }
        System.arraycopy(readByteArray, 0, bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public float readFloat() {
        advance(Symbol.FLOAT);
        if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
            float floatValue = this.in.getFloatValue();
            this.in.nextToken();
            return floatValue;
        }
        throw error("float");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readIndex() {
        String text;
        advance(Symbol.UNION);
        Symbol.Alternative alternative = (Symbol.Alternative) this.parser.popSymbol();
        if (this.in.getCurrentToken() == JsonToken.VALUE_NULL) {
            text = DataFileConstants.NULL_CODEC;
        } else if (this.in.getCurrentToken() != JsonToken.START_OBJECT || this.in.nextToken() != JsonToken.FIELD_NAME) {
            throw error("start-union");
        } else {
            text = this.in.getText();
            this.in.nextToken();
            this.parser.pushSymbol(Symbol.UNION_END);
        }
        int findLabel = alternative.findLabel(text);
        if (findLabel < 0) {
            throw new AvroTypeException("Unknown union branch " + text);
        }
        this.parser.pushSymbol(alternative.getSymbol(findLabel));
        return findLabel;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readInt() {
        advance(Symbol.INT);
        if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
            int intValue = this.in.getIntValue();
            this.in.nextToken();
            return intValue;
        }
        throw error("int");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readLong() {
        advance(Symbol.LONG);
        if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
            long longValue = this.in.getLongValue();
            this.in.nextToken();
            return longValue;
        }
        throw error("long");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readMapStart() {
        advance(Symbol.MAP_START);
        if (this.in.getCurrentToken() == JsonToken.START_OBJECT) {
            this.in.nextToken();
            return doMapNext();
        }
        throw error("map-start");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void readNull() {
        advance(Symbol.NULL);
        if (this.in.getCurrentToken() != JsonToken.VALUE_NULL) {
            throw error(DataFileConstants.NULL_CODEC);
        }
        this.in.nextToken();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public Utf8 readString(Utf8 utf8) {
        return new Utf8(readString());
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public String readString() {
        advance(Symbol.STRING);
        if (this.parser.topSymbol() == Symbol.MAP_KEY_MARKER) {
            this.parser.advance(Symbol.MAP_KEY_MARKER);
            if (this.in.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw error("map-key");
            }
        } else if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
            throw error("string");
        }
        String text = this.in.getText();
        this.in.nextToken();
        return text;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long skipArray() {
        advance(Symbol.ARRAY_START);
        if (this.in.getCurrentToken() == JsonToken.START_ARRAY) {
            this.in.skipChildren();
            this.in.nextToken();
            advance(Symbol.ARRAY_END);
            return 0L;
        }
        throw error("array-start");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipBytes() {
        advance(Symbol.BYTES);
        if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
            throw error("bytes");
        }
        this.in.nextToken();
    }

    @Override // com.flurry.org.apache.avro.io.ParsingDecoder
    protected void skipFixed() {
        advance(Symbol.FIXED);
        doSkipFixed(((Symbol.IntCheckAction) this.parser.popSymbol()).size);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipFixed(int i) {
        checkFixed(i);
        doSkipFixed(i);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long skipMap() {
        advance(Symbol.MAP_START);
        if (this.in.getCurrentToken() == JsonToken.START_OBJECT) {
            this.in.skipChildren();
            this.in.nextToken();
            advance(Symbol.MAP_END);
            return 0L;
        }
        throw error("map-start");
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipString() {
        advance(Symbol.STRING);
        if (this.parser.topSymbol() == Symbol.MAP_KEY_MARKER) {
            this.parser.advance(Symbol.MAP_KEY_MARKER);
            if (this.in.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw error("map-key");
            }
        } else if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
            throw error("string");
        }
        this.in.nextToken();
    }
}
