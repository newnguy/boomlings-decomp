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
    //   178: astore #7
    //   180: aload_0
    //   181: invokevirtual getFields : ()Ljava/util/List;
    //   184: invokeinterface iterator : ()Ljava/util/Iterator;
    //   189: astore #6
    //   191: aload #6
    //   193: invokeinterface hasNext : ()Z
    //   198: ifeq -> 289
    //   201: aload #6
    //   203: invokeinterface next : ()Ljava/lang/Object;
    //   208: checkcast com/flurry/org/apache/avro/Schema$Field
    //   211: astore #8
    //   213: aload #8
    //   215: invokestatic access$1600 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/apache/avro/Schema;
    //   218: aload_1
    //   219: aload_2
    //   220: aload_3
    //   221: invokestatic applyAliases : (Lcom/flurry/org/apache/avro/Schema;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   224: astore #9
    //   226: new com/flurry/org/apache/avro/Schema$Field
    //   229: dup
    //   230: aload #4
    //   232: aload #8
    //   234: invokestatic access$1700 : (Lcom/flurry/org/apache/avro/Schema$Field;)Ljava/lang/String;
    //   237: aload_3
    //   238: invokestatic getFieldAlias : (Lcom/flurry/org/apache/avro/Schema$Name;Ljava/lang/String;Ljava/util/Map;)Ljava/lang/String;
    //   241: aload #9
    //   243: aload #8
    //   245: invokestatic access$1800 : (Lcom/flurry/org/apache/avro/Schema$Field;)Ljava/lang/String;
    //   248: aload #8
    //   250: invokestatic access$1900 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   253: aload #8
    //   255: invokestatic access$2000 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/apache/avro/Schema$Field$Order;
    //   258: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/apache/avro/Schema;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Field$Order;)V
    //   261: astore #9
    //   263: aload #9
    //   265: invokestatic access$1400 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/apache/avro/Schema$Props;
    //   268: aload #8
    //   270: invokestatic access$1400 : (Lcom/flurry/org/apache/avro/Schema$Field;)Lcom/flurry/org/apache/avro/Schema$Props;
    //   273: invokevirtual putAll : (Ljava/util/Map;)V
    //   276: aload #7
    //   278: aload #9
    //   280: invokeinterface add : (Ljava/lang/Object;)Z
    //   285: pop
    //   286: goto -> 191
    //   289: aload #5
    //   291: aload #7
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
    //   404: astore #5
    //   406: aload_0
    //   407: invokevirtual getTypes : ()Ljava/util/List;
    //   410: invokeinterface iterator : ()Ljava/util/Iterator;
    //   415: astore #4
    //   417: aload #4
    //   419: invokeinterface hasNext : ()Z
    //   424: ifeq -> 454
    //   427: aload #5
    //   429: aload #4
    //   431: invokeinterface next : ()Ljava/lang/Object;
    //   436: checkcast com/flurry/org/apache/avro/Schema
    //   439: aload_1
    //   440: aload_2
    //   441: aload_3
    //   442: invokestatic applyAliases : (Lcom/flurry/org/apache/avro/Schema;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   445: invokeinterface add : (Ljava/lang/Object;)Z
    //   450: pop
    //   451: goto -> 417
    //   454: aload #5
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
    //   22: ifnonnull -> 1313
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
    //   57: ifeq -> 1250
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
    //   112: ifeq -> 1357
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
    //   165: ifnull -> 1343
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
    //   224: astore #6
    //   226: aload #6
    //   228: invokeinterface hasNext : ()Z
    //   233: ifeq -> 1187
    //   236: aload #6
    //   238: invokeinterface next : ()Ljava/lang/Object;
    //   243: checkcast java/lang/String
    //   246: astore #5
    //   248: aload_0
    //   249: aload #5
    //   251: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   254: invokevirtual getTextValue : ()Ljava/lang/String;
    //   257: astore #4
    //   259: getstatic com/flurry/org/apache/avro/Schema.SCHEMA_RESERVED : Ljava/util/Set;
    //   262: aload #5
    //   264: invokeinterface contains : (Ljava/lang/Object;)Z
    //   269: ifne -> 226
    //   272: aload #4
    //   274: ifnull -> 226
    //   277: aload_2
    //   278: aload #5
    //   280: aload #4
    //   282: invokevirtual addProp : (Ljava/lang/String;Ljava/lang/String;)V
    //   285: goto -> 226
    //   288: aload #6
    //   290: ldc_w 'record'
    //   293: invokevirtual equals : (Ljava/lang/Object;)Z
    //   296: ifne -> 310
    //   299: aload #6
    //   301: ldc_w 'error'
    //   304: invokevirtual equals : (Ljava/lang/Object;)Z
    //   307: ifeq -> 795
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
    //   408: ifeq -> 782
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
    //   696: astore #4
    //   698: aload #4
    //   700: invokeinterface hasNext : ()Z
    //   705: ifeq -> 758
    //   708: aload #4
    //   710: invokeinterface next : ()Ljava/lang/Object;
    //   715: checkcast java/lang/String
    //   718: astore_2
    //   719: aload #9
    //   721: aload_2
    //   722: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   725: invokevirtual getTextValue : ()Ljava/lang/String;
    //   728: astore #10
    //   730: getstatic com/flurry/org/apache/avro/Schema.FIELD_RESERVED : Ljava/util/Set;
    //   733: aload_2
    //   734: invokeinterface contains : (Ljava/lang/Object;)Z
    //   739: ifne -> 698
    //   742: aload #10
    //   744: ifnull -> 698
    //   747: aload #6
    //   749: aload_2
    //   750: aload #10
    //   752: invokevirtual addProp : (Ljava/lang/String;Ljava/lang/String;)V
    //   755: goto -> 698
    //   758: aload #6
    //   760: aload #9
    //   762: invokestatic parseAliases : (Lcom/flurry/org/codehaus/jackson/JsonNode;)Ljava/util/Set;
    //   765: invokestatic access$1302 : (Lcom/flurry/org/apache/avro/Schema$Field;Ljava/util/Set;)Ljava/util/Set;
    //   768: pop
    //   769: aload #7
    //   771: aload #6
    //   773: invokeinterface add : (Ljava/lang/Object;)Z
    //   778: pop
    //   779: goto -> 401
    //   782: aload #5
    //   784: aload #7
    //   786: invokevirtual setFields : (Ljava/util/List;)V
    //   789: aload #5
    //   791: astore_2
    //   792: goto -> 220
    //   795: aload #6
    //   797: ldc_w 'enum'
    //   800: invokevirtual equals : (Ljava/lang/Object;)Z
    //   803: ifeq -> 933
    //   806: aload_0
    //   807: ldc 'symbols'
    //   809: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   812: astore #6
    //   814: aload #6
    //   816: ifnull -> 827
    //   819: aload #6
    //   821: invokevirtual isArray : ()Z
    //   824: ifne -> 855
    //   827: new com/flurry/org/apache/avro/SchemaParseException
    //   830: dup
    //   831: new java/lang/StringBuilder
    //   834: dup
    //   835: invokespecial <init> : ()V
    //   838: ldc_w 'Enum has no symbols: '
    //   841: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   844: aload_0
    //   845: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   848: invokevirtual toString : ()Ljava/lang/String;
    //   851: invokespecial <init> : (Ljava/lang/String;)V
    //   854: athrow
    //   855: new com/flurry/org/apache/avro/Schema$LockableArrayList
    //   858: dup
    //   859: invokespecial <init> : ()V
    //   862: astore #5
    //   864: aload #6
    //   866: invokevirtual iterator : ()Ljava/util/Iterator;
    //   869: astore #6
    //   871: aload #6
    //   873: invokeinterface hasNext : ()Z
    //   878: ifeq -> 903
    //   881: aload #5
    //   883: aload #6
    //   885: invokeinterface next : ()Ljava/lang/Object;
    //   890: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   893: invokevirtual getTextValue : ()Ljava/lang/String;
    //   896: invokevirtual add : (Ljava/lang/Object;)Z
    //   899: pop
    //   900: goto -> 871
    //   903: new com/flurry/org/apache/avro/Schema$EnumSchema
    //   906: dup
    //   907: aload_2
    //   908: aload #4
    //   910: aload #5
    //   912: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema$Name;Ljava/lang/String;Lcom/flurry/org/apache/avro/Schema$LockableArrayList;)V
    //   915: astore #4
    //   917: aload_2
    //   918: ifnull -> 927
    //   921: aload_1
    //   922: aload #4
    //   924: invokevirtual add : (Lcom/flurry/org/apache/avro/Schema;)V
    //   927: aload #4
    //   929: astore_2
    //   930: goto -> 220
    //   933: aload #6
    //   935: ldc_w 'array'
    //   938: invokevirtual equals : (Ljava/lang/Object;)Z
    //   941: ifeq -> 999
    //   944: aload_0
    //   945: ldc 'items'
    //   947: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   950: astore_2
    //   951: aload_2
    //   952: ifnonnull -> 983
    //   955: new com/flurry/org/apache/avro/SchemaParseException
    //   958: dup
    //   959: new java/lang/StringBuilder
    //   962: dup
    //   963: invokespecial <init> : ()V
    //   966: ldc_w 'Array has no items type: '
    //   969: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   972: aload_0
    //   973: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   976: invokevirtual toString : ()Ljava/lang/String;
    //   979: invokespecial <init> : (Ljava/lang/String;)V
    //   982: athrow
    //   983: new com/flurry/org/apache/avro/Schema$ArraySchema
    //   986: dup
    //   987: aload_2
    //   988: aload_1
    //   989: invokestatic parse : (Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Names;)Lcom/flurry/org/apache/avro/Schema;
    //   992: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema;)V
    //   995: astore_2
    //   996: goto -> 220
    //   999: aload #6
    //   1001: ldc_w 'map'
    //   1004: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1007: ifeq -> 1065
    //   1010: aload_0
    //   1011: ldc 'values'
    //   1013: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   1016: astore_2
    //   1017: aload_2
    //   1018: ifnonnull -> 1049
    //   1021: new com/flurry/org/apache/avro/SchemaParseException
    //   1024: dup
    //   1025: new java/lang/StringBuilder
    //   1028: dup
    //   1029: invokespecial <init> : ()V
    //   1032: ldc_w 'Map has no values type: '
    //   1035: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1038: aload_0
    //   1039: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1042: invokevirtual toString : ()Ljava/lang/String;
    //   1045: invokespecial <init> : (Ljava/lang/String;)V
    //   1048: athrow
    //   1049: new com/flurry/org/apache/avro/Schema$MapSchema
    //   1052: dup
    //   1053: aload_2
    //   1054: aload_1
    //   1055: invokestatic parse : (Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Names;)Lcom/flurry/org/apache/avro/Schema;
    //   1058: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema;)V
    //   1061: astore_2
    //   1062: goto -> 220
    //   1065: aload #6
    //   1067: ldc_w 'fixed'
    //   1070: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1073: ifeq -> 1158
    //   1076: aload_0
    //   1077: ldc 'size'
    //   1079: invokevirtual get : (Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   1082: astore #5
    //   1084: aload #5
    //   1086: ifnull -> 1097
    //   1089: aload #5
    //   1091: invokevirtual isInt : ()Z
    //   1094: ifne -> 1125
    //   1097: new com/flurry/org/apache/avro/SchemaParseException
    //   1100: dup
    //   1101: new java/lang/StringBuilder
    //   1104: dup
    //   1105: invokespecial <init> : ()V
    //   1108: ldc_w 'Invalid or no size: '
    //   1111: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1114: aload_0
    //   1115: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1118: invokevirtual toString : ()Ljava/lang/String;
    //   1121: invokespecial <init> : (Ljava/lang/String;)V
    //   1124: athrow
    //   1125: new com/flurry/org/apache/avro/Schema$FixedSchema
    //   1128: dup
    //   1129: aload_2
    //   1130: aload #4
    //   1132: aload #5
    //   1134: invokevirtual getIntValue : ()I
    //   1137: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema$Name;Ljava/lang/String;I)V
    //   1140: astore #4
    //   1142: aload_2
    //   1143: ifnull -> 1152
    //   1146: aload_1
    //   1147: aload #4
    //   1149: invokevirtual add : (Lcom/flurry/org/apache/avro/Schema;)V
    //   1152: aload #4
    //   1154: astore_2
    //   1155: goto -> 220
    //   1158: new com/flurry/org/apache/avro/SchemaParseException
    //   1161: dup
    //   1162: new java/lang/StringBuilder
    //   1165: dup
    //   1166: invokespecial <init> : ()V
    //   1169: ldc_w 'Type not supported: '
    //   1172: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1175: aload #6
    //   1177: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1180: invokevirtual toString : ()Ljava/lang/String;
    //   1183: invokespecial <init> : (Ljava/lang/String;)V
    //   1186: athrow
    //   1187: aload_3
    //   1188: ifnull -> 1196
    //   1191: aload_1
    //   1192: aload_3
    //   1193: invokevirtual space : (Ljava/lang/String;)V
    //   1196: aload_2
    //   1197: astore_1
    //   1198: aload_2
    //   1199: instanceof com/flurry/org/apache/avro/Schema$NamedSchema
    //   1202: ifeq -> 1313
    //   1205: aload_0
    //   1206: invokestatic parseAliases : (Lcom/flurry/org/codehaus/jackson/JsonNode;)Ljava/util/Set;
    //   1209: astore_0
    //   1210: aload_2
    //   1211: astore_1
    //   1212: aload_0
    //   1213: ifnull -> 1313
    //   1216: aload_0
    //   1217: invokeinterface iterator : ()Ljava/util/Iterator;
    //   1222: astore_0
    //   1223: aload_2
    //   1224: astore_1
    //   1225: aload_0
    //   1226: invokeinterface hasNext : ()Z
    //   1231: ifeq -> 1313
    //   1234: aload_2
    //   1235: aload_0
    //   1236: invokeinterface next : ()Ljava/lang/Object;
    //   1241: checkcast java/lang/String
    //   1244: invokevirtual addAlias : (Ljava/lang/String;)V
    //   1247: goto -> 1223
    //   1250: aload_0
    //   1251: invokevirtual isArray : ()Z
    //   1254: ifeq -> 1315
    //   1257: new com/flurry/org/apache/avro/Schema$LockableArrayList
    //   1260: dup
    //   1261: aload_0
    //   1262: invokevirtual size : ()I
    //   1265: invokespecial <init> : (I)V
    //   1268: astore_2
    //   1269: aload_0
    //   1270: invokevirtual iterator : ()Ljava/util/Iterator;
    //   1273: astore_0
    //   1274: aload_0
    //   1275: invokeinterface hasNext : ()Z
    //   1280: ifeq -> 1304
    //   1283: aload_2
    //   1284: aload_0
    //   1285: invokeinterface next : ()Ljava/lang/Object;
    //   1290: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   1293: aload_1
    //   1294: invokestatic parse : (Lcom/flurry/org/codehaus/jackson/JsonNode;Lcom/flurry/org/apache/avro/Schema$Names;)Lcom/flurry/org/apache/avro/Schema;
    //   1297: invokevirtual add : (Ljava/lang/Object;)Z
    //   1300: pop
    //   1301: goto -> 1274
    //   1304: new com/flurry/org/apache/avro/Schema$UnionSchema
    //   1307: dup
    //   1308: aload_2
    //   1309: invokespecial <init> : (Lcom/flurry/org/apache/avro/Schema$LockableArrayList;)V
    //   1312: astore_1
    //   1313: aload_1
    //   1314: areturn
    //   1315: new com/flurry/org/apache/avro/SchemaParseException
    //   1318: dup
    //   1319: new java/lang/StringBuilder
    //   1322: dup
    //   1323: invokespecial <init> : ()V
    //   1326: ldc_w 'Schema not yet supported: '
    //   1329: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1332: aload_0
    //   1333: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1336: invokevirtual toString : ()Ljava/lang/String;
    //   1339: invokespecial <init> : (Ljava/lang/String;)V
    //   1342: athrow
    //   1343: aconst_null
    //   1344: astore #5
    //   1346: aload_2
    //   1347: astore #4
    //   1349: aload_3
    //   1350: astore_2
    //   1351: aload #5
    //   1353: astore_3
    //   1354: goto -> 190
    //   1357: aconst_null
    //   1358: astore_3
    //   1359: aconst_null
    //   1360: astore_2
    //   1361: goto -> 190
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


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */