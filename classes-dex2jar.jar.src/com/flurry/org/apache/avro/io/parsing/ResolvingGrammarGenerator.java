package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.BinaryEncoder;
import com.flurry.org.apache.avro.io.Encoder;
import com.flurry.org.apache.avro.io.EncoderFactory;
import com.flurry.org.codehaus.jackson.JsonNode;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ResolvingGrammarGenerator extends ValidatingGrammarGenerator {
  private static int[] $SWITCH_TABLE$org$apache$avro$Schema$Type;
  
  private static EncoderFactory factory = (new EncoderFactory()).configureBufferSize(32);
  
  private static int bestBranch(Schema paramSchema1, Schema paramSchema2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: aload_1
    //   4: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   7: astore #5
    //   9: aload_0
    //   10: invokevirtual getTypes : ()Ljava/util/List;
    //   13: invokeinterface iterator : ()Ljava/util/Iterator;
    //   18: astore #6
    //   20: iconst_0
    //   21: istore_3
    //   22: aload #6
    //   24: invokeinterface hasNext : ()Z
    //   29: ifne -> 58
    //   32: aload_0
    //   33: invokevirtual getTypes : ()Ljava/util/List;
    //   36: invokeinterface iterator : ()Ljava/util/Iterator;
    //   41: astore_0
    //   42: iload #4
    //   44: istore_2
    //   45: aload_0
    //   46: invokeinterface hasNext : ()Z
    //   51: ifne -> 159
    //   54: iconst_m1
    //   55: istore_2
    //   56: iload_2
    //   57: ireturn
    //   58: aload #6
    //   60: invokeinterface next : ()Ljava/lang/Object;
    //   65: checkcast com/flurry/org/apache/avro/Schema
    //   68: astore #8
    //   70: aload #5
    //   72: aload #8
    //   74: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   77: if_acmpne -> 153
    //   80: aload #5
    //   82: getstatic com/flurry/org/apache/avro/Schema$Type.RECORD : Lcom/flurry/org/apache/avro/Schema$Type;
    //   85: if_acmpeq -> 106
    //   88: aload #5
    //   90: getstatic com/flurry/org/apache/avro/Schema$Type.ENUM : Lcom/flurry/org/apache/avro/Schema$Type;
    //   93: if_acmpeq -> 106
    //   96: iload_3
    //   97: istore_2
    //   98: aload #5
    //   100: getstatic com/flurry/org/apache/avro/Schema$Type.FIXED : Lcom/flurry/org/apache/avro/Schema$Type;
    //   103: if_acmpne -> 56
    //   106: aload_1
    //   107: invokevirtual getFullName : ()Ljava/lang/String;
    //   110: astore #7
    //   112: aload #8
    //   114: invokevirtual getFullName : ()Ljava/lang/String;
    //   117: astore #8
    //   119: aload #7
    //   121: ifnull -> 136
    //   124: iload_3
    //   125: istore_2
    //   126: aload #7
    //   128: aload #8
    //   130: invokevirtual equals : (Ljava/lang/Object;)Z
    //   133: ifne -> 56
    //   136: aload #7
    //   138: aload #8
    //   140: if_acmpne -> 153
    //   143: iload_3
    //   144: istore_2
    //   145: aload #5
    //   147: getstatic com/flurry/org/apache/avro/Schema$Type.RECORD : Lcom/flurry/org/apache/avro/Schema$Type;
    //   150: if_acmpeq -> 56
    //   153: iinc #3, 1
    //   156: goto -> 22
    //   159: aload_0
    //   160: invokeinterface next : ()Ljava/lang/Object;
    //   165: checkcast com/flurry/org/apache/avro/Schema
    //   168: astore_1
    //   169: invokestatic $SWITCH_TABLE$org$apache$avro$Schema$Type : ()[I
    //   172: aload #5
    //   174: invokevirtual ordinal : ()I
    //   177: iaload
    //   178: tableswitch default -> 204, 9 -> 210, 10 -> 254, 11 -> 254
    //   204: iinc #2, 1
    //   207: goto -> 45
    //   210: invokestatic $SWITCH_TABLE$org$apache$avro$Schema$Type : ()[I
    //   213: aload_1
    //   214: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   217: invokevirtual ordinal : ()I
    //   220: iaload
    //   221: tableswitch default -> 248, 10 -> 251, 11 -> 248, 12 -> 251
    //   248: goto -> 204
    //   251: goto -> 56
    //   254: invokestatic $SWITCH_TABLE$org$apache$avro$Schema$Type : ()[I
    //   257: aload_1
    //   258: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   261: invokevirtual ordinal : ()I
    //   264: iaload
    //   265: tableswitch default -> 284, 12 -> 287
    //   284: goto -> 204
    //   287: goto -> 56
  }
  
  public static void encode(Encoder paramEncoder, Schema paramSchema, JsonNode paramJsonNode) {
    JsonNode jsonNode;
    Schema schema1;
    Iterator<String> iterator;
    byte[] arrayOfByte2;
    Iterator<JsonNode> iterator1;
    Schema schema2;
    Iterator<Schema.Field> iterator2;
    switch (paramSchema.getType()) {
      default:
        return;
      case null:
        iterator2 = paramSchema.getFields().iterator();
        while (true) {
          if (iterator2.hasNext()) {
            Schema.Field field = iterator2.next();
            String str = field.name();
            JsonNode jsonNode1 = paramJsonNode.get(str);
            jsonNode = jsonNode1;
            if (jsonNode1 == null)
              jsonNode = field.defaultValue(); 
            if (jsonNode == null)
              throw new AvroTypeException("No default value for: " + str); 
            encode(paramEncoder, field.schema(), jsonNode);
          } 
        } 
      case null:
        paramEncoder.writeEnum(jsonNode.getEnumOrdinal(paramJsonNode.getTextValue()));
      case null:
        paramEncoder.writeArrayStart();
        paramEncoder.setItemCount(paramJsonNode.size());
        schema1 = jsonNode.getElementType();
        iterator1 = paramJsonNode.iterator();
        while (true) {
          if (!iterator1.hasNext())
            paramEncoder.writeArrayEnd(); 
          paramJsonNode = iterator1.next();
          paramEncoder.startItem();
          encode(paramEncoder, schema1, paramJsonNode);
        } 
      case null:
        paramEncoder.writeMapStart();
        paramEncoder.setItemCount(paramJsonNode.size());
        schema2 = schema1.getValueType();
        iterator = paramJsonNode.getFieldNames();
        while (true) {
          if (!iterator.hasNext())
            paramEncoder.writeMapEnd(); 
          paramEncoder.startItem();
          String str = iterator.next();
          paramEncoder.writeString(str);
          encode(paramEncoder, schema2, paramJsonNode.get(str));
        } 
      case null:
        paramEncoder.writeIndex(0);
        encode(paramEncoder, iterator.getTypes().get(0), paramJsonNode);
      case null:
        if (!paramJsonNode.isTextual())
          throw new AvroTypeException("Non-string default value for fixed: " + paramJsonNode); 
        arrayOfByte2 = paramJsonNode.getTextValue().getBytes("ISO-8859-1");
        if (arrayOfByte2.length != iterator.getFixedSize()) {
          int i;
          byte[] arrayOfByte = new byte[iterator.getFixedSize()];
          if (iterator.getFixedSize() > arrayOfByte2.length) {
            i = arrayOfByte2.length;
          } else {
            i = iterator.getFixedSize();
          } 
          System.arraycopy(arrayOfByte2, 0, arrayOfByte, 0, i);
          arrayOfByte1 = arrayOfByte;
        } else {
          break;
        } 
        paramEncoder.writeFixed(arrayOfByte1);
      case null:
        if (!arrayOfByte2.isTextual())
          throw new AvroTypeException("Non-string default value for string: " + arrayOfByte2); 
        paramEncoder.writeString(arrayOfByte2.getTextValue());
      case null:
        if (!arrayOfByte2.isTextual())
          throw new AvroTypeException("Non-string default value for bytes: " + arrayOfByte2); 
        paramEncoder.writeBytes(arrayOfByte2.getTextValue().getBytes("ISO-8859-1"));
      case null:
        if (!arrayOfByte2.isNumber())
          throw new AvroTypeException("Non-numeric default value for int: " + arrayOfByte2); 
        paramEncoder.writeInt(arrayOfByte2.getIntValue());
      case null:
        if (!arrayOfByte2.isNumber())
          throw new AvroTypeException("Non-numeric default value for long: " + arrayOfByte2); 
        paramEncoder.writeLong(arrayOfByte2.getLongValue());
      case null:
        if (!arrayOfByte2.isNumber())
          throw new AvroTypeException("Non-numeric default value for float: " + arrayOfByte2); 
        paramEncoder.writeFloat((float)arrayOfByte2.getDoubleValue());
      case null:
        if (!arrayOfByte2.isNumber())
          throw new AvroTypeException("Non-numeric default value for double: " + arrayOfByte2); 
        paramEncoder.writeDouble(arrayOfByte2.getDoubleValue());
      case null:
        if (!arrayOfByte2.isBoolean())
          throw new AvroTypeException("Non-boolean default for boolean: " + arrayOfByte2); 
        paramEncoder.writeBoolean(arrayOfByte2.getBooleanValue());
      case null:
        if (!arrayOfByte2.isNull())
          throw new AvroTypeException("Non-null default value for null type: " + arrayOfByte2); 
        paramEncoder.writeNull();
    } 
    byte[] arrayOfByte1 = arrayOfByte2;
    paramEncoder.writeFixed(arrayOfByte1);
  }
  
  private static byte[] getBinary(Schema paramSchema, JsonNode paramJsonNode) {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    BinaryEncoder binaryEncoder = factory.binaryEncoder(byteArrayOutputStream, null);
    encode((Encoder)binaryEncoder, paramSchema, paramJsonNode);
    binaryEncoder.flush();
    return byteArrayOutputStream.toByteArray();
  }
  
  private static Symbol mkEnumAdjust(List<String> paramList1, List paramList2) {
    Object[] arrayOfObject = new Object[paramList1.size()];
    for (byte b = 0;; b++) {
      Integer integer;
      if (b >= arrayOfObject.length)
        return new Symbol$EnumAdjustAction(paramList2.size(), arrayOfObject); 
      int i = paramList2.indexOf(paramList1.get(b));
      if (i == -1) {
        String str = "No match for " + (String)paramList1.get(b);
      } else {
        integer = new Integer(i);
      } 
      arrayOfObject[b] = integer;
    } 
  }
  
  private Symbol resolveRecords(Schema paramSchema1, Schema paramSchema2, Map<ResolvingGrammarGenerator$LitS2, Symbol> paramMap) {
    ResolvingGrammarGenerator$LitS2 resolvingGrammarGenerator$LitS2 = new ResolvingGrammarGenerator$LitS2(paramSchema1, paramSchema2);
    Symbol symbol2 = (Symbol)paramMap.get(resolvingGrammarGenerator$LitS2);
    Symbol symbol1 = symbol2;
    if (symbol2 == null) {
      List list2 = paramSchema1.getFields();
      List list1 = paramSchema2.getFields();
      Schema.Field[] arrayOfField = new Schema.Field[list1.size()];
      int j = list2.size();
      Iterator iterator = list2.iterator();
      int i = 0;
      while (true) {
        byte[] arrayOfByte;
        Schema.Field<Schema.Field> field;
        if (!iterator.hasNext()) {
          Iterator iterator1 = list1.iterator();
          j++;
          while (true) {
            Schema.Field<Schema.Field> field2;
            Schema.Field<Schema.Field> field3;
            if (!iterator1.hasNext()) {
              Symbol[] arrayOfSymbol = new Symbol[j];
              i = j - 1;
              arrayOfSymbol[i] = new Symbol$FieldOrderAction(arrayOfField);
              symbol1 = Symbol.seq(arrayOfSymbol);
              paramMap.put(resolvingGrammarGenerator$LitS2, symbol1);
              Iterator iterator2 = list2.iterator();
              while (true) {
                if (!iterator2.hasNext()) {
                  Iterator<Schema.Field> iterator3 = list1.iterator();
                  while (true) {
                    if (iterator3.hasNext()) {
                      field2 = iterator3.next();
                      if (paramSchema1.getField(field2.name()) == null) {
                        arrayOfByte = getBinary(field2.schema(), field2.defaultValue());
                        arrayOfSymbol[--i] = new Symbol$DefaultStartAction(arrayOfByte);
                        arrayOfSymbol[--i] = generate(field2.schema(), field2.schema(), paramMap);
                        arrayOfSymbol[--i] = Symbol.DEFAULT_END_ACTION;
                      } 
                      continue;
                    } 
                    return symbol1;
                  } 
                  break;
                } 
                Schema.Field field4 = field2.next();
                field3 = arrayOfByte.getField(field4.name());
                if (field3 == null) {
                  arrayOfSymbol[--i] = new Symbol$SkipAction(generate(field4.schema(), field4.schema(), paramMap));
                  continue;
                } 
                arrayOfSymbol[--i] = generate(field4.schema(), field3.schema(), paramMap);
              } 
              break;
            } 
            field = field3.next();
            if (paramSchema1.getField(field.name()) == null) {
              if (field.defaultValue() == null) {
                symbol1 = Symbol.error("Found " + paramSchema1.toString(true) + ", expecting " + arrayOfByte.toString(true));
                paramMap.put(field2, symbol1);
                continue;
              } 
              symbol1[i] = (Symbol)field;
              j += 3;
              i++;
            } 
          } 
        } 
        Schema.Field field1 = arrayOfByte.getField(((Schema.Field)field.next()).name());
        if (field1 != null) {
          symbol1[i] = (Symbol)field1;
          i++;
        } 
      } 
    } 
    return symbol1;
  }
  
  private Symbol resolveUnion(Schema paramSchema1, Schema paramSchema2, Map paramMap) {
    List list = paramSchema1.getTypes();
    int i = list.size();
    Symbol[] arrayOfSymbol = new Symbol[i];
    String[] arrayOfString = new String[i];
    Iterator<Schema> iterator = list.iterator();
    for (i = 0;; i++) {
      if (!iterator.hasNext())
        return Symbol.seq(new Symbol[] { Symbol.alt(arrayOfSymbol, arrayOfString), new Symbol$WriterUnionAction() }); 
      Schema schema = iterator.next();
      arrayOfSymbol[i] = generate(schema, paramSchema2, paramMap);
      arrayOfString[i] = schema.getFullName();
    } 
  }
  
  public final Symbol generate(Schema paramSchema1, Schema paramSchema2) {
    return Symbol.root(new Symbol[] { generate(paramSchema1, paramSchema2, new HashMap<Object, Object>()) });
  }
  
  public Symbol generate(Schema paramSchema1, Schema paramSchema2, Map paramMap) {
    Schema.Type type1 = paramSchema1.getType();
    Schema.Type type2 = paramSchema2.getType();
    if (type1 == type2)
      switch (type1) {
        default:
          throw new AvroTypeException("Unkown type for schema: " + type1);
        case null:
          return Symbol.NULL;
        case null:
          return Symbol.BOOLEAN;
        case null:
          return Symbol.INT;
        case null:
          return Symbol.LONG;
        case null:
          return Symbol.FLOAT;
        case null:
          return Symbol.DOUBLE;
        case null:
          return Symbol.STRING;
        case null:
          return Symbol.BYTES;
        case null:
          if (paramSchema1.getFullName().equals(paramSchema2.getFullName()) && paramSchema1.getFixedSize() == paramSchema2.getFixedSize())
            return Symbol.seq(new Symbol[] { new Symbol$IntCheckAction(paramSchema1.getFixedSize()), Symbol.FIXED }); 
          break;
        case null:
          if (paramSchema1.getFullName() == null || paramSchema1.getFullName().equals(paramSchema2.getFullName()))
            return Symbol.seq(new Symbol[] { mkEnumAdjust(paramSchema1.getEnumSymbols(), paramSchema2.getEnumSymbols()), Symbol.ENUM }); 
          break;
        case null:
          return Symbol.seq(new Symbol[] { Symbol.repeat(Symbol.ARRAY_END, new Symbol[] { generate(paramSchema1.getElementType(), paramSchema2.getElementType(), paramMap) }), Symbol.ARRAY_START });
        case null:
          return Symbol.seq(new Symbol[] { Symbol.repeat(Symbol.MAP_END, new Symbol[] { generate(paramSchema1.getValueType(), paramSchema2.getValueType(), paramMap), Symbol.STRING }), Symbol.MAP_START });
        case null:
          return resolveRecords(paramSchema1, paramSchema2, paramMap);
        case null:
          return resolveUnion(paramSchema1, paramSchema2, paramMap);
      }  
    if (type1 == Schema.Type.UNION)
      return resolveUnion(paramSchema1, paramSchema2, paramMap); 
    switch (type2) {
      default:
        throw new RuntimeException("Unexpected schema type: " + type2);
      case null:
        switch (type1) {
          default:
            return Symbol.error("Found " + paramSchema1.toString(true) + ", expecting " + paramSchema2.toString(true));
          case null:
            break;
        } 
        return Symbol.resolve(generate(paramSchema1, paramMap), Symbol.LONG);
      case null:
      case null:
      case null:
      case null:
      case null:
      case null:
      case null:
      case null:
      case null:
      
      case null:
        switch (type1) {
          default:
          
          case null:
          case null:
            break;
        } 
        return Symbol.resolve(generate(paramSchema1, paramMap), Symbol.FLOAT);
      case null:
        switch (type1) {
          default:
          
          case null:
          case null:
          case null:
            break;
        } 
        return Symbol.resolve(generate(paramSchema1, paramMap), Symbol.DOUBLE);
      case null:
        break;
    } 
    int i = bestBranch(paramSchema2, paramSchema1);
    if (i >= 0)
      return Symbol.seq(new Symbol[] { new Symbol$UnionAdjustAction(i, generate(paramSchema1, paramSchema2.getTypes().get(i), paramMap)), Symbol.UNION }); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\ResolvingGrammarGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */