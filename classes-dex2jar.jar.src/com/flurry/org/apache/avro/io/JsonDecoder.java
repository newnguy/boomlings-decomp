package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.JsonGrammarGenerator;
import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.util.Utf8;
import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Stack;

public class JsonDecoder extends ParsingDecoder implements Parser.ActionHandler {
  static final String CHARSET = "ISO-8859-1";
  
  private static JsonFactory jsonFactory = new JsonFactory();
  
  JsonDecoder$ReorderBuffer currentReorderBuffer;
  
  private JsonParser in;
  
  Stack reorderBuffers = new Stack();
  
  JsonDecoder(Schema paramSchema, InputStream paramInputStream) {
    this(getSymbol(paramSchema), paramInputStream);
  }
  
  JsonDecoder(Schema paramSchema, String paramString) {
    this(getSymbol(paramSchema), paramString);
  }
  
  private JsonDecoder(Symbol paramSymbol, InputStream paramInputStream) {
    super(paramSymbol);
    configure(paramInputStream);
  }
  
  private JsonDecoder(Symbol paramSymbol, String paramString) {
    super(paramSymbol);
    configure(paramString);
  }
  
  private void advance(Symbol paramSymbol) {
    this.parser.processTrailingImplicitActions();
    if (this.in.getCurrentToken() == null && this.parser.depth() == 1)
      throw new EOFException(); 
    this.parser.advance(paramSymbol);
  }
  
  private void checkFixed(int paramInt) {
    advance(Symbol.FIXED);
    Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction)this.parser.popSymbol();
    if (paramInt != intCheckAction.size)
      throw new AvroTypeException("Incorrect length for fixed binary: expected " + intCheckAction.size + " but received " + paramInt + " bytes."); 
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
  
  private void doSkipFixed(int paramInt) {
    if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
      byte[] arrayOfByte = readByteArray();
      this.in.nextToken();
      if (arrayOfByte.length != paramInt)
        throw new AvroTypeException("Expected fixed length " + paramInt + ", but got" + arrayOfByte.length); 
    } else {
      throw error("fixed");
    } 
  }
  
  private AvroTypeException error(String paramString) {
    return new AvroTypeException("Expected " + paramString + ". Got " + this.in.getCurrentToken());
  }
  
  private static Symbol getSymbol(Schema paramSchema) {
    if (paramSchema == null)
      throw new NullPointerException("Schema cannot be null!"); 
    return (new JsonGrammarGenerator()).generate(paramSchema);
  }
  
  private static List getVaueAsTree(JsonParser paramJsonParser) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new java/util/ArrayList
    //   5: dup
    //   6: invokespecial <init> : ()V
    //   9: astore #4
    //   11: aload_0
    //   12: invokevirtual getCurrentToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   15: astore_3
    //   16: getstatic com/flurry/org/apache/avro/io/JsonDecoder$2.$SwitchMap$org$codehaus$jackson$JsonToken : [I
    //   19: aload_3
    //   20: invokevirtual ordinal : ()I
    //   23: iaload
    //   24: tableswitch default -> 84, 1 -> 116, 2 -> 116, 3 -> 139, 4 -> 139, 5 -> 162, 6 -> 162, 7 -> 162, 8 -> 162, 9 -> 162, 10 -> 162, 11 -> 162
    //   84: iload_2
    //   85: istore_1
    //   86: aload_0
    //   87: invokevirtual nextToken : ()Lcom/flurry/org/codehaus/jackson/JsonToken;
    //   90: pop
    //   91: iload_1
    //   92: istore_2
    //   93: iload_1
    //   94: ifne -> 11
    //   97: aload #4
    //   99: new com/flurry/org/apache/avro/io/JsonDecoder$JsonElement
    //   102: dup
    //   103: aconst_null
    //   104: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/JsonToken;)V
    //   107: invokeinterface add : (Ljava/lang/Object;)Z
    //   112: pop
    //   113: aload #4
    //   115: areturn
    //   116: iload_2
    //   117: iconst_1
    //   118: iadd
    //   119: istore_1
    //   120: aload #4
    //   122: new com/flurry/org/apache/avro/io/JsonDecoder$JsonElement
    //   125: dup
    //   126: aload_3
    //   127: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/JsonToken;)V
    //   130: invokeinterface add : (Ljava/lang/Object;)Z
    //   135: pop
    //   136: goto -> 86
    //   139: iload_2
    //   140: iconst_1
    //   141: isub
    //   142: istore_1
    //   143: aload #4
    //   145: new com/flurry/org/apache/avro/io/JsonDecoder$JsonElement
    //   148: dup
    //   149: aload_3
    //   150: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/JsonToken;)V
    //   153: invokeinterface add : (Ljava/lang/Object;)Z
    //   158: pop
    //   159: goto -> 86
    //   162: aload #4
    //   164: new com/flurry/org/apache/avro/io/JsonDecoder$JsonElement
    //   167: dup
    //   168: aload_3
    //   169: aload_0
    //   170: invokevirtual getText : ()Ljava/lang/String;
    //   173: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/JsonToken;Ljava/lang/String;)V
    //   176: invokeinterface add : (Ljava/lang/Object;)Z
    //   181: pop
    //   182: iload_2
    //   183: istore_1
    //   184: goto -> 86
  }
  
  private JsonParser makeParser(List paramList) {
    return new JsonDecoder$1(this, paramList);
  }
  
  private byte[] readByteArray() {
    return this.in.getText().getBytes("ISO-8859-1");
  }
  
  public long arrayNext() {
    advance(Symbol.ITEM_END);
    return doArrayNext();
  }
  
  public JsonDecoder configure(InputStream paramInputStream) {
    if (paramInputStream == null)
      throw new NullPointerException("InputStream to read from cannot be null!"); 
    this.parser.reset();
    this.in = jsonFactory.createJsonParser(paramInputStream);
    this.in.nextToken();
    return this;
  }
  
  public JsonDecoder configure(String paramString) {
    if (paramString == null)
      throw new NullPointerException("String to read from cannot be null!"); 
    this.parser.reset();
    this.in = (new JsonFactory()).createJsonParser(paramString);
    this.in.nextToken();
    return this;
  }
  
  public Symbol doAction(Symbol paramSymbol1, Symbol paramSymbol2) {
    Symbol.FieldAdjustAction fieldAdjustAction;
    if (paramSymbol2 instanceof Symbol.FieldAdjustAction) {
      fieldAdjustAction = (Symbol.FieldAdjustAction)paramSymbol2;
      String str = fieldAdjustAction.fname;
      if (this.currentReorderBuffer != null) {
        List list = (List)this.currentReorderBuffer.savedFields.get(str);
        if (list != null) {
          this.currentReorderBuffer.savedFields.remove(str);
          this.currentReorderBuffer.origParser = this.in;
          this.in = makeParser(list);
          return null;
        } 
      } 
      if (this.in.getCurrentToken() == JsonToken.FIELD_NAME)
        while (true) {
          String str1 = this.in.getText();
          this.in.nextToken();
          if (!str.equals(str1)) {
            if (this.currentReorderBuffer == null)
              this.currentReorderBuffer = new JsonDecoder$ReorderBuffer(null); 
            this.currentReorderBuffer.savedFields.put(str1, getVaueAsTree(this.in));
            if (this.in.getCurrentToken() != JsonToken.FIELD_NAME)
              throw new AvroTypeException("Expected field name not found: " + fieldAdjustAction.fname); 
            continue;
          } 
          return null;
        }  
      return null;
    } 
    if (fieldAdjustAction == Symbol.FIELD_END) {
      if (this.currentReorderBuffer != null && this.currentReorderBuffer.origParser != null) {
        this.in = this.currentReorderBuffer.origParser;
        this.currentReorderBuffer.origParser = null;
      } 
      return null;
    } 
    if (fieldAdjustAction == Symbol.RECORD_START) {
      if (this.in.getCurrentToken() == JsonToken.START_OBJECT) {
        this.in.nextToken();
        this.reorderBuffers.push(this.currentReorderBuffer);
        this.currentReorderBuffer = null;
        return null;
      } 
      throw error("record-start");
    } 
    if (fieldAdjustAction == Symbol.RECORD_END || fieldAdjustAction == Symbol.UNION_END) {
      if (this.in.getCurrentToken() == JsonToken.END_OBJECT) {
        this.in.nextToken();
        if (fieldAdjustAction == Symbol.RECORD_END) {
          if (this.currentReorderBuffer != null && !this.currentReorderBuffer.savedFields.isEmpty())
            throw error("Unknown fields: " + this.currentReorderBuffer.savedFields.keySet()); 
          this.currentReorderBuffer = this.reorderBuffers.pop();
        } 
        return null;
      } 
      if (fieldAdjustAction == Symbol.RECORD_END) {
        String str1 = "record-end";
        throw error(str1);
      } 
      String str = "union-end";
      throw error(str);
    } 
    throw new AvroTypeException("Unknown action symbol " + fieldAdjustAction);
  }
  
  public long mapNext() {
    advance(Symbol.ITEM_END);
    return doMapNext();
  }
  
  public long readArrayStart() {
    advance(Symbol.ARRAY_START);
    if (this.in.getCurrentToken() == JsonToken.START_ARRAY) {
      this.in.nextToken();
      return doArrayNext();
    } 
    throw error("array-start");
  }
  
  public boolean readBoolean() {
    advance(Symbol.BOOLEAN);
    JsonToken jsonToken = this.in.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_TRUE || jsonToken == JsonToken.VALUE_FALSE) {
      this.in.nextToken();
      return (jsonToken == JsonToken.VALUE_TRUE);
    } 
    throw error("boolean");
  }
  
  public ByteBuffer readBytes(ByteBuffer paramByteBuffer) {
    advance(Symbol.BYTES);
    if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
      byte[] arrayOfByte = readByteArray();
      this.in.nextToken();
      return ByteBuffer.wrap(arrayOfByte);
    } 
    throw error("bytes");
  }
  
  public double readDouble() {
    advance(Symbol.DOUBLE);
    if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
      double d = this.in.getDoubleValue();
      this.in.nextToken();
      return d;
    } 
    throw error("double");
  }
  
  public int readEnum() {
    advance(Symbol.ENUM);
    Symbol.EnumLabelsAction enumLabelsAction = (Symbol.EnumLabelsAction)this.parser.popSymbol();
    if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
      this.in.getText();
      int i = enumLabelsAction.findLabel(this.in.getText());
      if (i >= 0) {
        this.in.nextToken();
        return i;
      } 
      throw new AvroTypeException("Unknown symbol in enum " + this.in.getText());
    } 
    throw error("fixed");
  }
  
  public void readFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    checkFixed(paramInt2);
    if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
      byte[] arrayOfByte = readByteArray();
      this.in.nextToken();
      if (arrayOfByte.length != paramInt2)
        throw new AvroTypeException("Expected fixed length " + paramInt2 + ", but got" + arrayOfByte.length); 
      System.arraycopy(arrayOfByte, 0, paramArrayOfbyte, paramInt1, paramInt2);
      return;
    } 
    throw error("fixed");
  }
  
  public float readFloat() {
    advance(Symbol.FLOAT);
    if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_FLOAT) {
      float f = this.in.getFloatValue();
      this.in.nextToken();
      return f;
    } 
    throw error("float");
  }
  
  public int readIndex() {
    String str;
    advance(Symbol.UNION);
    Symbol.Alternative alternative = (Symbol.Alternative)this.parser.popSymbol();
    if (this.in.getCurrentToken() == JsonToken.VALUE_NULL) {
      str = "null";
    } else if (this.in.getCurrentToken() == JsonToken.START_OBJECT && this.in.nextToken() == JsonToken.FIELD_NAME) {
      str = this.in.getText();
      this.in.nextToken();
      this.parser.pushSymbol(Symbol.UNION_END);
    } else {
      throw error("start-union");
    } 
    int i = alternative.findLabel(str);
    if (i < 0)
      throw new AvroTypeException("Unknown union branch " + str); 
    this.parser.pushSymbol(alternative.getSymbol(i));
    return i;
  }
  
  public int readInt() {
    advance(Symbol.INT);
    if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
      int i = this.in.getIntValue();
      this.in.nextToken();
      return i;
    } 
    throw error("int");
  }
  
  public long readLong() {
    advance(Symbol.LONG);
    if (this.in.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
      long l = this.in.getLongValue();
      this.in.nextToken();
      return l;
    } 
    throw error("long");
  }
  
  public long readMapStart() {
    advance(Symbol.MAP_START);
    if (this.in.getCurrentToken() == JsonToken.START_OBJECT) {
      this.in.nextToken();
      return doMapNext();
    } 
    throw error("map-start");
  }
  
  public void readNull() {
    advance(Symbol.NULL);
    if (this.in.getCurrentToken() == JsonToken.VALUE_NULL) {
      this.in.nextToken();
      return;
    } 
    throw error("null");
  }
  
  public Utf8 readString(Utf8 paramUtf8) {
    return new Utf8(readString());
  }
  
  public String readString() {
    advance(Symbol.STRING);
    if (this.parser.topSymbol() == Symbol.MAP_KEY_MARKER) {
      this.parser.advance(Symbol.MAP_KEY_MARKER);
      if (this.in.getCurrentToken() != JsonToken.FIELD_NAME)
        throw error("map-key"); 
    } else if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
      throw error("string");
    } 
    String str = this.in.getText();
    this.in.nextToken();
    return str;
  }
  
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
  
  public void skipBytes() {
    advance(Symbol.BYTES);
    if (this.in.getCurrentToken() == JsonToken.VALUE_STRING) {
      this.in.nextToken();
      return;
    } 
    throw error("bytes");
  }
  
  protected void skipFixed() {
    advance(Symbol.FIXED);
    doSkipFixed(((Symbol.IntCheckAction)this.parser.popSymbol()).size);
  }
  
  public void skipFixed(int paramInt) {
    checkFixed(paramInt);
    doSkipFixed(paramInt);
  }
  
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
  
  public void skipString() {
    advance(Symbol.STRING);
    if (this.parser.topSymbol() == Symbol.MAP_KEY_MARKER) {
      this.parser.advance(Symbol.MAP_KEY_MARKER);
      if (this.in.getCurrentToken() != JsonToken.FIELD_NAME)
        throw error("map-key"); 
    } else if (this.in.getCurrentToken() != JsonToken.VALUE_STRING) {
      throw error("string");
    } 
    this.in.nextToken();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\JsonDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */