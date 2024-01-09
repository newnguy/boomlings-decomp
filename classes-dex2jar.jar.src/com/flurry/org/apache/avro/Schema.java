package com.flurry.org.apache.avro;

import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParseException;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.map.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Schema {
  static final JsonFactory FACTORY = new JsonFactory();
  
  private static final Set FIELD_RESERVED;
  
  static final ObjectMapper MAPPER = new ObjectMapper(FACTORY);
  
  private static final int NO_HASHCODE = -2147483648;
  
  static final Map PRIMITIVES;
  
  private static final Set SCHEMA_RESERVED = new HashSet();
  
  private static final ThreadLocal SEEN_EQUALS;
  
  private static final ThreadLocal SEEN_HASHCODE;
  
  private static ThreadLocal validateNames;
  
  int hashCode = Integer.MIN_VALUE;
  
  Schema$Props props = new Schema$Props(SCHEMA_RESERVED);
  
  private final Schema$Type type;
  
  static {
    Collections.addAll(SCHEMA_RESERVED, new String[] { "doc", "fields", "items", "name", "namespace", "size", "symbols", "values", "type" });
    FIELD_RESERVED = new HashSet();
    Collections.addAll(FIELD_RESERVED, new String[] { "default", "doc", "name", "order", "type" });
    SEEN_EQUALS = new Schema$1();
    SEEN_HASHCODE = new Schema$2();
    PRIMITIVES = new HashMap<Object, Object>();
    PRIMITIVES.put("string", Schema$Type.STRING);
    PRIMITIVES.put("bytes", Schema$Type.BYTES);
    PRIMITIVES.put("int", Schema$Type.INT);
    PRIMITIVES.put("long", Schema$Type.LONG);
    PRIMITIVES.put("float", Schema$Type.FLOAT);
    PRIMITIVES.put("double", Schema$Type.DOUBLE);
    PRIMITIVES.put("boolean", Schema$Type.BOOLEAN);
    PRIMITIVES.put("null", Schema$Type.NULL);
    validateNames = new Schema$3();
  }
  
  Schema(Schema$Type paramSchema$Type) {
    this.type = paramSchema$Type;
  }
  
  public static Schema applyAliases(Schema paramSchema1, Schema paramSchema2) {
    if (paramSchema1 == paramSchema2)
      return paramSchema1; 
    IdentityHashMap<Object, Object> identityHashMap = new IdentityHashMap<Object, Object>(1);
    HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>(1);
    HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>(1);
    getAliases(paramSchema2, identityHashMap, hashMap2, hashMap1);
    if (hashMap2.size() == 0) {
      paramSchema2 = paramSchema1;
      if (hashMap1.size() != 0) {
        identityHashMap.clear();
        return applyAliases(paramSchema1, identityHashMap, hashMap2, hashMap1);
      } 
      return paramSchema2;
    } 
    identityHashMap.clear();
    return applyAliases(paramSchema1, identityHashMap, hashMap2, hashMap1);
  }
  
  private static Schema applyAliases(Schema paramSchema, Map paramMap1, Map paramMap2, Map paramMap3) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof com/flurry/org/apache/avro/Schema$NamedSchema
    //   4: ifeq -> 88
    //   7: aload_0
    //   8: checkcast com/flurry/org/apache/avro/Schema$NamedSchema
    //   11: getfield name : Lcom/flurry/org/apache/avro/Schema$Name;
    //   14: astore #4
    //   16: getstatic com/flurry/org/apache/avro/Schema$4.$SwitchMap$org$apache$avro$Schema$Type : [I
    //   19: aload_0
    //   20: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   23: invokevirtual ordinal : ()I
    //   26: iaload
    //   27: tableswitch default -> 64, 9 -> 94, 10 -> 302, 11 -> 343, 12 -> 370, 13 -> 397, 14 -> 463
    //   64: aload_0
    //   65: astore_1
    //   66: aload_1
    //   67: astore_2
    //   68: aload_1
    //   69: aload_0
    //   70: if_acmpeq -> 86
    //   73: aload_1
    //   74: getfield props : Lcom/flurry/org/apache/avro/Schema$Props;
    //   77: aload_0
    //   78: getfield props : Lcom/flurry/org/apache/avro/Schema$Props;
    //   81: invokevirtual putAll : (Ljava/util/Map;)V
    //   84: aload_1
    //   85: astore_2
    //   86: aload_2
    //   87: areturn
    //   88: aconst_null
    //   89: astore #4
    //   91: goto -> 16
    //   94: aload_1
    //   95: aload_0
    //   96: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   101: ifeq -> 118
    //   104: aload_1
    //   105: aload_0
    //   106: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   111: checkcast com/flurry/org/apache/avro/Schema
    //   114: astore_2
    //   115: goto -> 86
    //   118: aload_2
    //   119: aload #4
    //   121: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   126: ifeq -> 504
    //   129: aload_2
    //   130: aload #4
    //   132: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   137: checkcast com/flurry/org/apache/avro/Schema$Name
    //   140: astore #4
    //   142: aload #4
    //   144: invokestatic access$400 : (Lcom/flurry/org/apache/avro/Schema$Name;)Ljava/lang/String;
    //   147: aload_0
    //   148: invokevirtual getDoc : ()Ljava/lang/String;
    //   151: aconst_null
    //   152: aload_0
    //   153: invokevirtual isError : ()Z
    //   156: invokestatic createRecord : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Lcom/flurry/org/apache/avro/Schema;
    //   159: astore #5
    //   161: aload_1
    //   162: aload_0
    //   163: aload #5
    //   165: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   170: pop
    //   171: new java/util/ArrayList
    //   174: dup
    //   175: invokespecial <init> : ()V
    //   178: astore #6
    //   180: aload_0
    //   181: invokevirtual getFields : ()Ljava/util/List;
    //   184: invokeinterface iterator : ()Ljava/util/Iterator;
    //   189: astore #8
    //   191: aload #8
    //   193: invokeinterface hasNext : ()Z
    //   198: ifeq -> 289
    //   201: aload #8
    //   203: invokeinterface next : ()Ljava/lang/Object;
    //   208: checkcast com/flurry/org/apache/avro/Schema$Field
    //   211: astore #7
    //   213: aload #7
    //   215: invokestatic access$1600 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/apache/avro/Schema;
    //   218: aload_1
    //   219: aload_2
    //   220: aload_3
    //   221: invokestatic applyAliases : (Lcom/flurry/org/apache/avro/Schema;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   224: astore #9
    //   226: new com/flurry/org/apache/avro/Schema$Field
    //   229: dup
    //   230: aload #4
    //   232: aload #7
    //   234: invokestatic access$1700 : (Lcom/flurry/org/apache/avro/Schema$Field;)Ljava/lang/String;
    //   237: aload_3
    //   238: invokestatic getFieldAlias : (Lcom/flurry/org/apache/avro/Schema$Name;Ljava/lang/String;Ljava/util/Map;)Ljava/lang/String;
    //   241: aload #9
    //   243: aload #7
    //   245: invokestatic access$1800 : (Lcom/flurry/org/apache/avro/Schema$Field;)Ljava/lang/String;
    //   248: aload #7
    //   250: invokestatic access$1900 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   253: aload #7
    //   255: invokestatic access$2000 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/apache/avro/Schema$Field$Order;
    //   258: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/apache/avro/Schema;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Field$Order;)V
    //   261: astore #9
    //   263: aload #9
    //   265: invokestatic access$1400 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/apache/avro/Schema$Props;
    //   268: aload #7
    //   270: invokestatic access$1400 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/apache/avro/Schema$Props;
    //   273: invokevirtual putAll : (Ljava/util/Map;)V
    //   276: aload #6
    //   278: aload #9
    //   280: invokeinterface add : (Ljava/lang/Object;)Z
    //   285: pop
    //   286: goto -> 191
    //   289: aload #5
    //   291: aload #6
    //   293: invokevirtual setFields : (Ljava/util/List;)V
    //   296: aload #5
    //   298: astore_1
    //   299: goto -> 66
    //   302: aload_2
    //   303: aload #4
    //   305: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   310: ifeq -> 64
    //   313: aload_2
    //   314: aload #4
    //   316: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   321: checkcast com/flurry/org/apache/avro/Schema$Name
    //   324: invokestatic access$400 : (Lcom/flurry/org/apache/avro/Schema$Name;)Ljava/lang/String;
    //   327: aload_0
    //   328: invokevirtual getDoc : ()Ljava/lang/String;
    //   331: aconst_null
    //   332: aload_0
    //   333: invokevirtual getEnumSymbols : ()Ljava/util/List;
    //   336: invokestatic createEnum : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lcom/flurry/org/apache/avro/Schema;
    //   339: astore_1
    //   340: goto -> 66
    //   343: aload_0
    //   344: invokevirtual getElementType : ()Lcom/flurry/org/apache/avro/Schema;
    //   347: aload_1
    //   348: aload_2
    //   349: aload_3
    //   350: invokestatic applyAliases : (Lcom/flurry/org/apache/avro/Schema;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   353: astore_1
    //   354: aload_1
    //   355: aload_0
    //   356: invokevirtual getElementType : ()Lcom/flurry/org/apache/avro/Schema;
    //   359: if_acmpeq -> 64
    //   362: aload_1
    //   363: invokestatic createArray : (Lcom/flurry/org/apache/avro/Schema;)Lcom/flurry/org/apache/avro/Schema;
    //   366: astore_1
    //   367: goto -> 66
    //   370: aload_0
    //   371: invokevirtual getValueType : ()Lcom/flurry/org/apache/avro/Schema;
    //   374: aload_1
    //   375: aload_2
    //   376: aload_3
    //   377: invokestatic applyAliases : (Lcom/flurry/org/apache/avro/Schema;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   380: astore_1
    //   381: aload_1
    //   382: aload_0
    //   383: invokevirtual getValueType : ()Lcom/flurry/org/apache/avro/Schema;
    //   386: if_acmpeq -> 64
    //   389: aload_1
    //   390: invokestatic createMap : (Lcom/flurry/org/apache/avro/Schema;)Lcom/flurry/org/apache/avro/Schema;
    //   393: astore_1
    //   394: goto -> 66
    //   397: new java/util/ArrayList
    //   400: dup
    //   401: invokespecial <init> : ()V
    //   404: astore #4
    //   406: aload_0
    //   407: invokevirtual getTypes : ()Ljava/util/List;
    //   410: invokeinterface iterator : ()Ljava/util/Iterator;
    //   415: astore #5
    //   417: aload #5
    //   419: invokeinterface hasNext : ()Z
    //   424: ifeq -> 454
    //   427: aload #4
    //   429: aload #5
    //   431: invokeinterface next : ()Ljava/lang/Object;
    //   436: checkcast com/flurry/org/apache/avro/Schema
    //   439: aload_1
    //   440: aload_2
    //   441: aload_3
    //   442: invokestatic applyAliases : (Lcom/flurry/org/apache/avro/Schema;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   445: invokeinterface add : (Ljava/lang/Object;)Z
    //   450: pop
    //   451: goto -> 417
    //   454: aload #4
    //   456: invokestatic createUnion : (Ljava/util/List;)Lcom/flurry/org/apache/avro/Schema;
    //   459: astore_1
    //   460: goto -> 66
    //   463: aload_2
    //   464: aload #4
    //   466: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   471: ifeq -> 64
    //   474: aload_2
    //   475: aload #4
    //   477: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   482: checkcast com/flurry/org/apache/avro/Schema$Name
    //   485: invokestatic access$400 : (Lcom/flurry/org/apache/avro/Schema$Name;)Ljava/lang/String;
    //   488: aload_0
    //   489: invokevirtual getDoc : ()Ljava/lang/String;
    //   492: aconst_null
    //   493: aload_0
    //   494: invokevirtual getFixedSize : ()I
    //   497: invokestatic createFixed : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Lcom/flurry/org/apache/avro/Schema;
    //   500: astore_1
    //   501: goto -> 66
    //   504: goto -> 142
  }
  
  public static Schema create(Schema$Type paramSchema$Type) {
    switch (Schema$4.$SwitchMap$org$apache$avro$Schema$Type[paramSchema$Type.ordinal()]) {
      default:
        throw new AvroRuntimeException("Can't create a: " + paramSchema$Type);
      case 1:
        return new Schema$StringSchema();
      case 2:
        return new Schema$BytesSchema();
      case 3:
        return new Schema$IntSchema();
      case 4:
        return new Schema$LongSchema();
      case 5:
        return new Schema$FloatSchema();
      case 6:
        return new Schema$DoubleSchema();
      case 7:
        return new Schema$BooleanSchema();
      case 8:
        break;
    } 
    return new Schema$NullSchema();
  }
  
  public static Schema createArray(Schema paramSchema) {
    return new Schema$ArraySchema(paramSchema);
  }
  
  public static Schema createEnum(String paramString1, String paramString2, String paramString3, List paramList) {
    return new Schema$EnumSchema(new Schema$Name(paramString1, paramString3), paramString2, new Schema$LockableArrayList(paramList));
  }
  
  public static Schema createFixed(String paramString1, String paramString2, String paramString3, int paramInt) {
    return new Schema$FixedSchema(new Schema$Name(paramString1, paramString3), paramString2, paramInt);
  }
  
  public static Schema createMap(Schema paramSchema) {
    return new Schema$MapSchema(paramSchema);
  }
  
  public static Schema createRecord(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    return new Schema$RecordSchema(new Schema$Name(paramString1, paramString3), paramString2, paramBoolean);
  }
  
  public static Schema createRecord(List paramList) {
    Schema schema = createRecord(null, null, null, false);
    schema.setFields(paramList);
    return schema;
  }
  
  public static Schema createUnion(List paramList) {
    return new Schema$UnionSchema(new Schema$LockableArrayList(paramList));
  }
  
  private static void getAliases(Schema paramSchema, Map<Schema, Schema> paramMap1, Map<Schema$Name, Schema$Name> paramMap2, Map<Schema$Name, Map<Object, Object>> paramMap3) {
    if (paramSchema instanceof Schema$NamedSchema) {
      Schema$NamedSchema schema$NamedSchema = (Schema$NamedSchema)paramSchema;
      if (schema$NamedSchema.aliases != null) {
        Iterator<Schema$Name> iterator1 = schema$NamedSchema.aliases.iterator();
        while (iterator1.hasNext())
          paramMap2.put(iterator1.next(), schema$NamedSchema.name); 
      } 
    } 
    switch (Schema$4.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        return;
      case 9:
        if (!paramMap1.containsKey(paramSchema)) {
          paramMap1.put(paramSchema, paramSchema);
          Schema$RecordSchema schema$RecordSchema = (Schema$RecordSchema)paramSchema;
          for (Schema$Field schema$Field : paramSchema.getFields()) {
            if (Schema$Field.access$1300(schema$Field) != null)
              for (String str : Schema$Field.access$1300(schema$Field)) {
                Map<Object, Object> map2 = (Map)paramMap3.get(schema$RecordSchema.name);
                Map<Object, Object> map1 = map2;
                if (map2 == null) {
                  Schema$Name schema$Name = schema$RecordSchema.name;
                  map1 = new HashMap<Object, Object>();
                  paramMap3.put(schema$Name, map1);
                } 
                map1.put(str, Schema$Field.access$1700(schema$Field));
              }  
            getAliases(Schema$Field.access$1600(schema$Field), paramMap1, paramMap2, paramMap3);
          } 
          if (schema$RecordSchema.aliases != null && paramMap3.containsKey(schema$RecordSchema.name)) {
            iterator = schema$RecordSchema.aliases.iterator();
            while (true) {
              if (iterator.hasNext())
                paramMap3.put(iterator.next(), paramMap3.get(schema$RecordSchema.name)); 
            } 
          } 
        } 
      case 11:
        getAliases(iterator.getElementType(), paramMap1, paramMap2, paramMap3);
      case 12:
        getAliases(iterator.getValueType(), paramMap1, paramMap2, paramMap3);
      case 13:
        break;
    } 
    Iterator<Schema$Name> iterator = iterator.getTypes().iterator();
    while (true) {
      if (iterator.hasNext())
        getAliases((Schema)iterator.next(), paramMap1, paramMap2, paramMap3); 
    } 
  }
  
  private static String getFieldAlias(Schema$Name paramSchema$Name, String paramString, Map paramMap) {
    Map map = (Map)paramMap.get(paramSchema$Name);
    if (map != null) {
      String str = (String)map.get(paramString);
      if (str != null)
        paramString = str; 
    } 
    return paramString;
  }
  
  private static String getOptionalText(JsonNode paramJsonNode, String paramString) {
    paramJsonNode = paramJsonNode.get(paramString);
    return (paramJsonNode != null) ? paramJsonNode.getTextValue() : null;
  }
  
  private static String getRequiredText(JsonNode paramJsonNode, String paramString1, String paramString2) {
    paramString1 = getOptionalText(paramJsonNode, paramString1);
    if (paramString1 == null)
      throw new SchemaParseException(paramString2 + ": " + paramJsonNode); 
    return paramString1;
  }
  
  static Schema parse(JsonNode paramJsonNode, Schema$Names paramSchema$Names) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #4
    //   3: aload_0
    //   4: invokevirtual isTextual : ()Z
    //   7: ifeq -> 53
    //   10: aload_1
    //   11: aload_0
    //   12: invokevirtual getTextValue : ()Ljava/lang/String;
    //   15: invokevirtual get : (Ljava/lang/Object;)Lcom/flurry/org/apache/avro/Schema;
    //   18: astore_2
    //   19: aload_2
    //   20: astore_1
    //   21: aload_2
    //   22: ifnonnull -> 1314
    //   25: new com/flurry/org/apache/avro/SchemaParseException
    //   28: dup
    //   29: new java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial <init> : ()V
    //   36: ldc_w 'Undefined name: '
    //   39: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: aload_0
    //   43: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   46: invokevirtual toString : ()Ljava/lang/String;
    //   49: invokespecial <init> : (Ljava/lang/String;)V
    //   52: athrow
    //   53: aload_0
    //   54: invokevirtual isObject : ()Z
    //   57: ifeq -> 1251
    //   60: aload_0
    //   61: ldc 'type'
    //   63: ldc_w 'No type'
    //   66: invokestatic getRequiredText : (Lcom/flurry/org/codehaus/jackson/JsonNode;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   69: astore #6
    //   71: aload #6
    //   73: ldc_w 'record'
    //   76: invokevirtual equals : (Ljava/lang/Object;)Z
    //   79: ifne -> 115
    //   82: aload #6
    //   84: ldc_w 'error'
    //   87: invokevirtual equals : (Ljava/lang/Object;)Z
    //   90: ifne -> 115
    //   93: aload #6
    //   95: ldc_w 'enum'
    //   98: invokevirtual equals : (Ljava/lang/Object;)Z
    //   101: ifne -> 115
    //   104: aload #6
    //   106: ldc_w 'fixed'
    //   109: invokevirtual equals : (Ljava/lang/Object;)Z
    //   112: ifeq -> 1358
    //   115: aload_0
    //   116: ldc 'namespace'
    //   118: invokestatic getOptionalText : (Lcom/flurry/org/codehaus/jackson/JsonNode;Ljava/lang/String;)Ljava/lang/String;
    //   121: astore #4
    //   123: aload_0
    //   124: ldc 'doc'
    //   126: invokestatic getOptionalText : (Lcom/flurry/org/codehaus/jackson/JsonNode;Ljava/lang/String;)Ljava/lang/String;
    //   129: astore_2
    //   130: aload #4
    //   132: astore_3
    //   133: aload #4
    //   135: ifnonnull -> 143
    //   138: aload_1
    //   139: invokevirtual space : ()Ljava/lang/String;
    //   142: astore_3
    //   143: new com/flurry/org/apache/avro/Schema$Name
    //   146: dup
    //   147: aload_0
    //   148: ldc 'name'
    //   150: ldc_w 'No name in schema'
    //   153: invokestatic getRequiredText : (Lcom/flurry/org/codehaus/jackson/JsonNode;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   156: aload_3
    //   157: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   160: astore_3
    //   161: aload_3
    //   162: invokestatic access$600 : (Lcom/flurry/org/apache/avro/Schema$Name;)Ljava/lang/String;
    //   165: ifnull -> 1344
    //   168: aload_1
    //   169: invokevirtual space : ()Ljava/lang/String;
    //   172: astore #5
    //   174: aload_1
    //   175: aload_3
    //   176: invokestatic access$600 : (Lcom/flurry/org/apache/avro/Schema$Name;)Ljava/lang/String;
    //   179: invokevirtual space : (Ljava/lang/String;)V
    //   182: aload_2
    //   183: astore #4
    //   185: aload_3
    //   186: astore_2
    //   187: aload #5
    //   189: astore_3
    //   190: getstatic com/flurry/org/apache/avro/Schema.PRIMITIVES : Ljava/util/Map;
    //   193: aload #6
    //   195: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   200: ifeq -> 288
    //   203: getstatic com/flurry/org/apache/avro/Schema.PRIMITIVES : Ljava/util/Map;
    //   206: aload #6
    //   208: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   213: checkcast com/flurry/org/apache/avro/Schema$Type
    //   216: invokestatic create : (Lcom/flurry/org/apache/avro/Schema$Type;)Lcom/flurry/org/apache/avro/Schema;
    //   219: astore_2
    //   220: aload_0
    //   221: invokevirtual getFieldNames : ()Ljava/util/Iterator;
    //   224: astore #4
    //   226: aload #4
    //   228: invokeinterface hasNext : ()Z
    //   233: ifeq -> 1188
    //   236: aload #4
    //   238: invokeinterface next : ()Ljava/lang/Object;
    //   243: checkcast java/lang/String
    //   246: astore #6
    //   248: aload_0
    //   249: aload #6
    //   251: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   254: invokevirtual getTextValue : ()Ljava/lang/String;
    //   257: astore #5
    //   259: getstatic com/flurry/org/apache/avro/Schema.SCHEMA_RESERVED : Ljava/util/Set;
    //   262: aload #6
    //   264: invokeinterface contains : (Ljava/lang/Object;)Z
    //   269: ifne -> 226
    //   272: aload #5
    //   274: ifnull -> 226
    //   277: aload_2
    //   278: aload #6
    //   280: aload #5
    //   282: invokevirtual addProp : (Ljava/lang/String;Ljava/lang/String;)V
    //   285: goto -> 226
    //   288: aload #6
    //   290: ldc_w 'record'
    //   293: invokevirtual equals : (Ljava/lang/Object;)Z
    //   296: ifne -> 310
    //   299: aload #6
    //   301: ldc_w 'error'
    //   304: invokevirtual equals : (Ljava/lang/Object;)Z
    //   307: ifeq -> 796
    //   310: new java/util/ArrayList
    //   313: dup
    //   314: invokespecial <init> : ()V
    //   317: astore #7
    //   319: new com/flurry/org/apache/avro/Schema$RecordSchema
    //   322: dup
    //   323: aload_2
    //   324: aload #4
    //   326: aload #6
    //   328: ldc_w 'error'
    //   331: invokevirtual equals : (Ljava/lang/Object;)Z
    //   334: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema$Name;Ljava/lang/String;Z)V
    //   337: astore #5
    //   339: aload_2
    //   340: ifnull -> 349
    //   343: aload_1
    //   344: aload #5
    //   346: invokevirtual add : (Lcom/flurry/org/apache/avro/Schema;)V
    //   349: aload_0
    //   350: ldc 'fields'
    //   352: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   355: astore_2
    //   356: aload_2
    //   357: ifnull -> 367
    //   360: aload_2
    //   361: invokevirtual isArray : ()Z
    //   364: ifne -> 395
    //   367: new com/flurry/org/apache/avro/SchemaParseException
    //   370: dup
    //   371: new java/lang/StringBuilder
    //   374: dup
    //   375: invokespecial <init> : ()V
    //   378: ldc_w 'Record has no fields: '
    //   381: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: aload_0
    //   385: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   388: invokevirtual toString : ()Ljava/lang/String;
    //   391: invokespecial <init> : (Ljava/lang/String;)V
    //   394: athrow
    //   395: aload_2
    //   396: invokevirtual iterator : ()Ljava/util/Iterator;
    //   399: astore #8
    //   401: aload #8
    //   403: invokeinterface hasNext : ()Z
    //   408: ifeq -> 783
    //   411: aload #8
    //   413: invokeinterface next : ()Ljava/lang/Object;
    //   418: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   421: astore #9
    //   423: aload #9
    //   425: ldc 'name'
    //   427: ldc_w 'No field name'
    //   430: invokestatic getRequiredText : (Lcom/flurry/org/codehaus/jackson/JsonNode;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   433: astore #11
    //   435: aload #9
    //   437: ldc 'doc'
    //   439: invokestatic getOptionalText : (Lcom/flurry/org/codehaus/jackson/JsonNode;Ljava/lang/String;)Ljava/lang/String;
    //   442: astore #10
    //   444: aload #9
    //   446: ldc 'type'
    //   448: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   451: astore_2
    //   452: aload_2
    //   453: ifnonnull -> 485
    //   456: new com/flurry/org/apache/avro/SchemaParseException
    //   459: dup
    //   460: new java/lang/StringBuilder
    //   463: dup
    //   464: invokespecial <init> : ()V
    //   467: ldc_w 'No field type: '
    //   470: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: aload #9
    //   475: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   478: invokevirtual toString : ()Ljava/lang/String;
    //   481: invokespecial <init> : (Ljava/lang/String;)V
    //   484: athrow
    //   485: aload_2
    //   486: invokevirtual isTextual : ()Z
    //   489: ifeq -> 554
    //   492: aload_1
    //   493: aload_2
    //   494: invokevirtual getTextValue : ()Ljava/lang/String;
    //   497: invokevirtual get : (Ljava/lang/Object;)Lcom/flurry/org/apache/avro/Schema;
    //   500: ifnonnull -> 554
    //   503: new com/flurry/org/apache/avro/SchemaParseException
    //   506: dup
    //   507: new java/lang/StringBuilder
    //   510: dup
    //   511: invokespecial <init> : ()V
    //   514: aload_2
    //   515: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   518: ldc_w ' is not a defined name.'
    //   521: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   524: ldc_w ' The type of the "'
    //   527: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   530: aload #11
    //   532: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   535: ldc_w '" field must be'
    //   538: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: ldc_w ' a defined name or a {"type": ...} expression.'
    //   544: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   547: invokevirtual toString : ()Ljava/lang/String;
    //   550: invokespecial <init> : (Ljava/lang/String;)V
    //   553: athrow
    //   554: aload_2
    //   555: aload_1
    //   556: invokestatic parse : (Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Names;)Lcom/flurry/org/apache/avro/Schema;
    //   559: astore #12
    //   561: getstatic com/flurry/org/apache/avro/Schema$Field$Order.ASCENDING : Lcom/flurry/org/apache/avro/Schema$Field$Order;
    //   564: astore_2
    //   565: aload #9
    //   567: ldc 'order'
    //   569: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   572: astore #4
    //   574: aload #4
    //   576: ifnull -> 591
    //   579: aload #4
    //   581: invokevirtual getTextValue : ()Ljava/lang/String;
    //   584: invokevirtual toUpperCase : ()Ljava/lang/String;
    //   587: invokestatic valueOf : (Ljava/lang/String;)Lcom/flurry/org/apache/avro/Schema$Field$Order;
    //   590: astore_2
    //   591: aload #9
    //   593: ldc 'default'
    //   595: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   598: astore #6
    //   600: aload #6
    //   602: astore #4
    //   604: aload #6
    //   606: ifnull -> 673
    //   609: getstatic com/flurry/org/apache/avro/Schema$Type.FLOAT : Lcom/flurry/org/apache/avro/Schema$Type;
    //   612: aload #12
    //   614: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   617: invokevirtual equals : (Ljava/lang/Object;)Z
    //   620: ifne -> 641
    //   623: aload #6
    //   625: astore #4
    //   627: getstatic com/flurry/org/apache/avro/Schema$Type.DOUBLE : Lcom/flurry/org/apache/avro/Schema$Type;
    //   630: aload #12
    //   632: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   635: invokevirtual equals : (Ljava/lang/Object;)Z
    //   638: ifeq -> 673
    //   641: aload #6
    //   643: astore #4
    //   645: aload #6
    //   647: invokevirtual isTextual : ()Z
    //   650: ifeq -> 673
    //   653: new com/flurry/org/codehaus/jackson/node/DoubleNode
    //   656: dup
    //   657: aload #6
    //   659: invokevirtual getTextValue : ()Ljava/lang/String;
    //   662: invokestatic valueOf : (Ljava/lang/String;)Ljava/lang/Double;
    //   665: invokevirtual doubleValue : ()D
    //   668: invokespecial <init> : (D)V
    //   671: astore #4
    //   673: new com/flurry/org/apache/avro/Schema$Field
    //   676: dup
    //   677: aload #11
    //   679: aload #12
    //   681: aload #10
    //   683: aload #4
    //   685: aload_2
    //   686: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/apache/avro/Schema;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Field$Order;)V
    //   689: astore #6
    //   691: aload #9
    //   693: invokevirtual getFieldNames : ()Ljava/util/Iterator;
    //   696: astore_2
    //   697: aload_2
    //   698: invokeinterface hasNext : ()Z
    //   703: ifeq -> 759
    //   706: aload_2
    //   707: invokeinterface next : ()Ljava/lang/Object;
    //   712: checkcast java/lang/String
    //   715: astore #4
    //   717: aload #9
    //   719: aload #4
    //   721: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   724: invokevirtual getTextValue : ()Ljava/lang/String;
    //   727: astore #10
    //   729: getstatic com/flurry/org/apache/avro/Schema.FIELD_RESERVED : Ljava/util/Set;
    //   732: aload #4
    //   734: invokeinterface contains : (Ljava/lang/Object;)Z
    //   739: ifne -> 697
    //   742: aload #10
    //   744: ifnull -> 697
    //   747: aload #6
    //   749: aload #4
    //   751: aload #10
    //   753: invokevirtual addProp : (Ljava/lang/String;Ljava/lang/String;)V
    //   756: goto -> 697
    //   759: aload #6
    //   761: aload #9
    //   763: invokestatic parseAliases : (Lcom/flurry/org/codehaus/jackson/JsonNode;)Ljava/util/Set;
    //   766: invokestatic access$1302 : (Lcom/flurry/org/apache/avro/Schema$Field;Ljava/util/Set;)Ljava/util/Set;
    //   769: pop
    //   770: aload #7
    //   772: aload #6
    //   774: invokeinterface add : (Ljava/lang/Object;)Z
    //   779: pop
    //   780: goto -> 401
    //   783: aload #5
    //   785: aload #7
    //   787: invokevirtual setFields : (Ljava/util/List;)V
    //   790: aload #5
    //   792: astore_2
    //   793: goto -> 220
    //   796: aload #6
    //   798: ldc_w 'enum'
    //   801: invokevirtual equals : (Ljava/lang/Object;)Z
    //   804: ifeq -> 934
    //   807: aload_0
    //   808: ldc 'symbols'
    //   810: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   813: astore #6
    //   815: aload #6
    //   817: ifnull -> 828
    //   820: aload #6
    //   822: invokevirtual isArray : ()Z
    //   825: ifne -> 856
    //   828: new com/flurry/org/apache/avro/SchemaParseException
    //   831: dup
    //   832: new java/lang/StringBuilder
    //   835: dup
    //   836: invokespecial <init> : ()V
    //   839: ldc_w 'Enum has no symbols: '
    //   842: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   845: aload_0
    //   846: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   849: invokevirtual toString : ()Ljava/lang/String;
    //   852: invokespecial <init> : (Ljava/lang/String;)V
    //   855: athrow
    //   856: new com/flurry/org/apache/avro/Schema$LockableArrayList
    //   859: dup
    //   860: invokespecial <init> : ()V
    //   863: astore #5
    //   865: aload #6
    //   867: invokevirtual iterator : ()Ljava/util/Iterator;
    //   870: astore #6
    //   872: aload #6
    //   874: invokeinterface hasNext : ()Z
    //   879: ifeq -> 904
    //   882: aload #5
    //   884: aload #6
    //   886: invokeinterface next : ()Ljava/lang/Object;
    //   891: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   894: invokevirtual getTextValue : ()Ljava/lang/String;
    //   897: invokevirtual add : (Ljava/lang/Object;)Z
    //   900: pop
    //   901: goto -> 872
    //   904: new com/flurry/org/apache/avro/Schema$EnumSchema
    //   907: dup
    //   908: aload_2
    //   909: aload #4
    //   911: aload #5
    //   913: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema$Name;Ljava/lang/String;Lcom/flurry/org/apache/avro/Schema$LockableArrayList;)V
    //   916: astore #4
    //   918: aload_2
    //   919: ifnull -> 928
    //   922: aload_1
    //   923: aload #4
    //   925: invokevirtual add : (Lcom/flurry/org/apache/avro/Schema;)V
    //   928: aload #4
    //   930: astore_2
    //   931: goto -> 220
    //   934: aload #6
    //   936: ldc_w 'array'
    //   939: invokevirtual equals : (Ljava/lang/Object;)Z
    //   942: ifeq -> 1000
    //   945: aload_0
    //   946: ldc 'items'
    //   948: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   951: astore_2
    //   952: aload_2
    //   953: ifnonnull -> 984
    //   956: new com/flurry/org/apache/avro/SchemaParseException
    //   959: dup
    //   960: new java/lang/StringBuilder
    //   963: dup
    //   964: invokespecial <init> : ()V
    //   967: ldc_w 'Array has no items type: '
    //   970: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   973: aload_0
    //   974: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   977: invokevirtual toString : ()Ljava/lang/String;
    //   980: invokespecial <init> : (Ljava/lang/String;)V
    //   983: athrow
    //   984: new com/flurry/org/apache/avro/Schema$ArraySchema
    //   987: dup
    //   988: aload_2
    //   989: aload_1
    //   990: invokestatic parse : (Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Names;)Lcom/flurry/org/apache/avro/Schema;
    //   993: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema;)V
    //   996: astore_2
    //   997: goto -> 220
    //   1000: aload #6
    //   1002: ldc_w 'map'
    //   1005: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1008: ifeq -> 1066
    //   1011: aload_0
    //   1012: ldc 'values'
    //   1014: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   1017: astore_2
    //   1018: aload_2
    //   1019: ifnonnull -> 1050
    //   1022: new com/flurry/org/apache/avro/SchemaParseException
    //   1025: dup
    //   1026: new java/lang/StringBuilder
    //   1029: dup
    //   1030: invokespecial <init> : ()V
    //   1033: ldc_w 'Map has no values type: '
    //   1036: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1039: aload_0
    //   1040: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1043: invokevirtual toString : ()Ljava/lang/String;
    //   1046: invokespecial <init> : (Ljava/lang/String;)V
    //   1049: athrow
    //   1050: new com/flurry/org/apache/avro/Schema$MapSchema
    //   1053: dup
    //   1054: aload_2
    //   1055: aload_1
    //   1056: invokestatic parse : (Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Names;)Lcom/flurry/org/apache/avro/Schema;
    //   1059: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema;)V
    //   1062: astore_2
    //   1063: goto -> 220
    //   1066: aload #6
    //   1068: ldc_w 'fixed'
    //   1071: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1074: ifeq -> 1159
    //   1077: aload_0
    //   1078: ldc 'size'
    //   1080: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   1083: astore #5
    //   1085: aload #5
    //   1087: ifnull -> 1098
    //   1090: aload #5
    //   1092: invokevirtual isInt : ()Z
    //   1095: ifne -> 1126
    //   1098: new com/flurry/org/apache/avro/SchemaParseException
    //   1101: dup
    //   1102: new java/lang/StringBuilder
    //   1105: dup
    //   1106: invokespecial <init> : ()V
    //   1109: ldc_w 'Invalid or no size: '
    //   1112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1115: aload_0
    //   1116: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1119: invokevirtual toString : ()Ljava/lang/String;
    //   1122: invokespecial <init> : (Ljava/lang/String;)V
    //   1125: athrow
    //   1126: new com/flurry/org/apache/avro/Schema$FixedSchema
    //   1129: dup
    //   1130: aload_2
    //   1131: aload #4
    //   1133: aload #5
    //   1135: invokevirtual getIntValue : ()I
    //   1138: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema$Name;Ljava/lang/String;I)V
    //   1141: astore #4
    //   1143: aload_2
    //   1144: ifnull -> 1153
    //   1147: aload_1
    //   1148: aload #4
    //   1150: invokevirtual add : (Lcom/flurry/org/apache/avro/Schema;)V
    //   1153: aload #4
    //   1155: astore_2
    //   1156: goto -> 220
    //   1159: new com/flurry/org/apache/avro/SchemaParseException
    //   1162: dup
    //   1163: new java/lang/StringBuilder
    //   1166: dup
    //   1167: invokespecial <init> : ()V
    //   1170: ldc_w 'Type not supported: '
    //   1173: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: aload #6
    //   1178: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1181: invokevirtual toString : ()Ljava/lang/String;
    //   1184: invokespecial <init> : (Ljava/lang/String;)V
    //   1187: athrow
    //   1188: aload_3
    //   1189: ifnull -> 1197
    //   1192: aload_1
    //   1193: aload_3
    //   1194: invokevirtual space : (Ljava/lang/String;)V
    //   1197: aload_2
    //   1198: astore_1
    //   1199: aload_2
    //   1200: instanceof com/flurry/org/apache/avro/Schema$NamedSchema
    //   1203: ifeq -> 1314
    //   1206: aload_0
    //   1207: invokestatic parseAliases : (Lcom/flurry/org/codehaus/jackson/JsonNode;)Ljava/util/Set;
    //   1210: astore_0
    //   1211: aload_2
    //   1212: astore_1
    //   1213: aload_0
    //   1214: ifnull -> 1314
    //   1217: aload_0
    //   1218: invokeinterface iterator : ()Ljava/util/Iterator;
    //   1223: astore_0
    //   1224: aload_2
    //   1225: astore_1
    //   1226: aload_0
    //   1227: invokeinterface hasNext : ()Z
    //   1232: ifeq -> 1314
    //   1235: aload_2
    //   1236: aload_0
    //   1237: invokeinterface next : ()Ljava/lang/Object;
    //   1242: checkcast java/lang/String
    //   1245: invokevirtual addAlias : (Ljava/lang/String;)V
    //   1248: goto -> 1224
    //   1251: aload_0
    //   1252: invokevirtual isArray : ()Z
    //   1255: ifeq -> 1316
    //   1258: new com/flurry/org/apache/avro/Schema$LockableArrayList
    //   1261: dup
    //   1262: aload_0
    //   1263: invokevirtual size : ()I
    //   1266: invokespecial <init> : (I)V
    //   1269: astore_2
    //   1270: aload_0
    //   1271: invokevirtual iterator : ()Ljava/util/Iterator;
    //   1274: astore_0
    //   1275: aload_0
    //   1276: invokeinterface hasNext : ()Z
    //   1281: ifeq -> 1305
    //   1284: aload_2
    //   1285: aload_0
    //   1286: invokeinterface next : ()Ljava/lang/Object;
    //   1291: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   1294: aload_1
    //   1295: invokestatic parse : (Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Names;)Lcom/flurry/org/apache/avro/Schema;
    //   1298: invokevirtual add : (Ljava/lang/Object;)Z
    //   1301: pop
    //   1302: goto -> 1275
    //   1305: new com/flurry/org/apache/avro/Schema$UnionSchema
    //   1308: dup
    //   1309: aload_2
    //   1310: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema$LockableArrayList;)V
    //   1313: astore_1
    //   1314: aload_1
    //   1315: areturn
    //   1316: new com/flurry/org/apache/avro/SchemaParseException
    //   1319: dup
    //   1320: new java/lang/StringBuilder
    //   1323: dup
    //   1324: invokespecial <init> : ()V
    //   1327: ldc_w 'Schema not yet supported: '
    //   1330: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1333: aload_0
    //   1334: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1337: invokevirtual toString : ()Ljava/lang/String;
    //   1340: invokespecial <init> : (Ljava/lang/String;)V
    //   1343: athrow
    //   1344: aconst_null
    //   1345: astore #5
    //   1347: aload_2
    //   1348: astore #4
    //   1350: aload_3
    //   1351: astore_2
    //   1352: aload #5
    //   1354: astore_3
    //   1355: goto -> 190
    //   1358: aconst_null
    //   1359: astore_3
    //   1360: aconst_null
    //   1361: astore_2
    //   1362: goto -> 190
  }
  
  public static Schema parse(File paramFile) {
    return (new Schema$Parser()).parse(paramFile);
  }
  
  public static Schema parse(InputStream paramInputStream) {
    return (new Schema$Parser()).parse(paramInputStream);
  }
  
  public static Schema parse(String paramString) {
    return (new Schema$Parser()).parse(paramString);
  }
  
  public static Schema parse(String paramString, boolean paramBoolean) {
    return (new Schema$Parser()).setValidate(paramBoolean).parse(paramString);
  }
  
  private static Set parseAliases(JsonNode paramJsonNode) {
    JsonNode jsonNode = paramJsonNode.get("aliases");
    if (jsonNode == null)
      return null; 
    if (!jsonNode.isArray())
      throw new SchemaParseException("aliases not an array: " + paramJsonNode); 
    LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
    Iterator<JsonNode> iterator = jsonNode.iterator();
    while (true) {
      if (iterator.hasNext()) {
        jsonNode = iterator.next();
        if (!jsonNode.isTextual())
          throw new SchemaParseException("alias not a string: " + jsonNode); 
        linkedHashSet.add(jsonNode.getTextValue());
        continue;
      } 
      return linkedHashSet;
    } 
  }
  
  static JsonNode parseJson(String paramString) {
    try {
      ObjectMapper objectMapper = MAPPER;
      JsonFactory jsonFactory = FACTORY;
      StringReader stringReader = new StringReader();
      this(paramString);
      return objectMapper.readTree(jsonFactory.createJsonParser(stringReader));
    } catch (JsonParseException jsonParseException) {
      throw new RuntimeException(jsonParseException);
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  private static String validateName(String paramString) {
    if (((Boolean)validateNames.get()).booleanValue()) {
      int i = paramString.length();
      if (i == 0)
        throw new SchemaParseException("Empty name"); 
      char c = paramString.charAt(0);
      if (!Character.isLetter(c) && c != '_')
        throw new SchemaParseException("Illegal initial character: " + paramString); 
      byte b = 1;
      while (true) {
        if (b < i) {
          c = paramString.charAt(b);
          if (!Character.isLetterOrDigit(c) && c != '_')
            throw new SchemaParseException("Illegal character in: " + paramString); 
          b++;
          continue;
        } 
        return paramString;
      } 
    } 
    return paramString;
  }
  
  public void addAlias(String paramString) {
    throw new AvroRuntimeException("Not a named type: " + this);
  }
  
  public void addProp(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield props : Lcom/flurry/org/apache/avro/Schema$Props;
    //   6: aload_1
    //   7: aload_2
    //   8: invokevirtual add : (Ljava/lang/String;Ljava/lang/String;)V
    //   11: aload_0
    //   12: ldc -2147483648
    //   14: putfield hashCode : I
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  int computeHash() {
    return getType().hashCode() + this.props.hashCode();
  }
  
  final boolean equalCachedHash(Schema paramSchema) {
    return (this.hashCode == paramSchema.hashCode || this.hashCode == Integer.MIN_VALUE || paramSchema.hashCode == Integer.MIN_VALUE);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (!(paramObject instanceof Schema))
        return false; 
      paramObject = paramObject;
      if (this.type != ((Schema)paramObject).type)
        return false; 
      if (!equalCachedHash((Schema)paramObject) || !this.props.equals(((Schema)paramObject).props))
        bool = false; 
    } 
    return bool;
  }
  
  void fieldsToJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    throw new AvroRuntimeException("Not a record: " + this);
  }
  
  public Set getAliases() {
    throw new AvroRuntimeException("Not a named type: " + this);
  }
  
  public String getDoc() {
    return null;
  }
  
  public Schema getElementType() {
    throw new AvroRuntimeException("Not an array: " + this);
  }
  
  public int getEnumOrdinal(String paramString) {
    throw new AvroRuntimeException("Not an enum: " + this);
  }
  
  public List getEnumSymbols() {
    throw new AvroRuntimeException("Not an enum: " + this);
  }
  
  public Schema$Field getField(String paramString) {
    throw new AvroRuntimeException("Not a record: " + this);
  }
  
  public List getFields() {
    throw new AvroRuntimeException("Not a record: " + this);
  }
  
  public int getFixedSize() {
    throw new AvroRuntimeException("Not fixed: " + this);
  }
  
  public String getFullName() {
    return getName();
  }
  
  public Integer getIndexNamed(String paramString) {
    throw new AvroRuntimeException("Not a union: " + this);
  }
  
  public String getName() {
    return Schema$Type.access$000(this.type);
  }
  
  public String getNamespace() {
    throw new AvroRuntimeException("Not a named type: " + this);
  }
  
  public String getProp(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield props : Lcom/flurry/org/apache/avro/Schema$Props;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast java/lang/String
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	18	finally
  }
  
  public Map getProps() {
    return Collections.unmodifiableMap(this.props);
  }
  
  public Schema$Type getType() {
    return this.type;
  }
  
  public List getTypes() {
    throw new AvroRuntimeException("Not a union: " + this);
  }
  
  public Schema getValueType() {
    throw new AvroRuntimeException("Not a map: " + this);
  }
  
  public boolean hasEnumSymbol(String paramString) {
    throw new AvroRuntimeException("Not an enum: " + this);
  }
  
  public final int hashCode() {
    if (this.hashCode == Integer.MIN_VALUE)
      this.hashCode = computeHash(); 
    return this.hashCode;
  }
  
  public boolean isError() {
    throw new AvroRuntimeException("Not a record: " + this);
  }
  
  public void setFields(List paramList) {
    throw new AvroRuntimeException("Not a record: " + this);
  }
  
  void toJson(Schema$Names paramSchema$Names, JsonGenerator paramJsonGenerator) {
    if (this.props.size() == 0) {
      paramJsonGenerator.writeString(getName());
      return;
    } 
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.writeStringField("type", getName());
    this.props.write(paramJsonGenerator);
    paramJsonGenerator.writeEndObject();
  }
  
  public String toString() {
    return toString(false);
  }
  
  public String toString(boolean paramBoolean) {
    try {
      StringWriter stringWriter = new StringWriter();
      this();
      JsonGenerator jsonGenerator = FACTORY.createJsonGenerator(stringWriter);
      if (paramBoolean)
        jsonGenerator.useDefaultPrettyPrinter(); 
      Schema$Names schema$Names = new Schema$Names();
      this();
      toJson(schema$Names, jsonGenerator);
      jsonGenerator.flush();
      return stringWriter.toString();
    } catch (IOException iOException) {
      throw new AvroRuntimeException(iOException);
    } 
  }
  
  static {
    FACTORY.enable(JsonParser.Feature.ALLOW_COMMENTS);
    FACTORY.setCodec((ObjectCodec)MAPPER);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\Schema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */