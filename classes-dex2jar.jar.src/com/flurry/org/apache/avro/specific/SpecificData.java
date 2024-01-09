package com.flurry.org.apache.avro.specific;

import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Protocol;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericData;
import com.flurry.org.apache.avro.io.DatumReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SpecificData extends GenericData {
  private static final Map CTOR_CACHE;
  
  private static final SpecificData INSTANCE = new SpecificData();
  
  private static final Class[] NO_ARG = new Class[0];
  
  private static final Class NO_CLASS;
  
  private static final Schema NULL_SCHEMA;
  
  private static final Class[] SCHEMA_ARG = new Class[] { Schema.class };
  
  private Map classCache = new ConcurrentHashMap<Object, Object>();
  
  private final ClassLoader classLoader;
  
  private final WeakHashMap schemaCache = new WeakHashMap<Object, Object>();
  
  static {
    CTOR_CACHE = new ConcurrentHashMap<Object, Object>();
    NO_CLASS = (new SpecificData$1()).getClass();
    NULL_SCHEMA = Schema.create(Schema.Type.NULL);
  }
  
  protected SpecificData() {
    this(SpecificData.class.getClassLoader());
  }
  
  public SpecificData(ClassLoader paramClassLoader) {
    this.classLoader = paramClassLoader;
  }
  
  public static SpecificData get() {
    return INSTANCE;
  }
  
  public static String getClassName(Schema paramSchema) {
    String str2 = paramSchema.getNamespace();
    String str1 = paramSchema.getName();
    if (str2 == null || "".equals(str2))
      return str1; 
    if (str2.endsWith("$")) {
      null = "";
    } else {
      null = ".";
    } 
    return str2 + null + str1;
  }
  
  public static Object newInstance(Class<?> paramClass, Schema paramSchema) {
    boolean bool = SpecificData$SchemaConstructable.class.isAssignableFrom(paramClass);
    try {
      Constructor constructor1 = (Constructor)CTOR_CACHE.get(paramClass);
      Constructor<?> constructor = constructor1;
      if (constructor1 == null) {
        Class[] arrayOfClass;
        if (bool) {
          arrayOfClass = SCHEMA_ARG;
        } else {
          arrayOfClass = NO_ARG;
        } 
        constructor = paramClass.getDeclaredConstructor(arrayOfClass);
        constructor.setAccessible(true);
        CTOR_CACHE.put(paramClass, constructor);
      } 
      if (bool) {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = paramSchema;
        return constructor.newInstance(arrayOfObject1);
      } 
      Object[] arrayOfObject = (Object[])null;
      return constructor.newInstance(arrayOfObject);
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    } 
  }
  
  protected int compare(Object paramObject1, Object paramObject2, Schema paramSchema, boolean paramBoolean) {
    switch (SpecificData$2.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        return super.compare(paramObject1, paramObject2, paramSchema, paramBoolean);
      case 3:
        break;
    } 
    if (paramObject1 instanceof Enum)
      return ((Enum)paramObject1).ordinal() - ((Enum)paramObject2).ordinal(); 
  }
  
  public DatumReader createDatumReader(Schema paramSchema) {
    return (DatumReader)new SpecificDatumReader(paramSchema, paramSchema, this);
  }
  
  public Object createFixed(Object paramObject, Schema paramSchema) {
    Class clazz = get().getClass(paramSchema);
    if (clazz == null)
      return super.createFixed(paramObject, paramSchema); 
    Object object = paramObject;
    if (!clazz.isInstance(paramObject))
      object = newInstance(clazz, paramSchema); 
    return object;
  }
  
  protected Schema createSchema(Type paramType, Map<String, Schema> paramMap) {
    String str;
    if (paramType instanceof Class && CharSequence.class.isAssignableFrom((Class)paramType))
      return Schema.create(Schema.Type.STRING); 
    if (paramType == ByteBuffer.class)
      return Schema.create(Schema.Type.BYTES); 
    if (paramType == Integer.class || paramType == int.class)
      return Schema.create(Schema.Type.INT); 
    if (paramType == Long.class || paramType == long.class)
      return Schema.create(Schema.Type.LONG); 
    if (paramType == Float.class || paramType == float.class)
      return Schema.create(Schema.Type.FLOAT); 
    if (paramType == Double.class || paramType == double.class)
      return Schema.create(Schema.Type.DOUBLE); 
    if (paramType == Boolean.class || paramType == boolean.class)
      return Schema.create(Schema.Type.BOOLEAN); 
    if (paramType == Void.class || paramType == void.class)
      return Schema.create(Schema.Type.NULL); 
    if (paramType instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType)paramType;
      Type type = parameterizedType.getRawType();
      Type[] arrayOfType = parameterizedType.getActualTypeArguments();
      if (Collection.class.isAssignableFrom((Class<?>)type)) {
        if (arrayOfType.length != 1)
          throw new AvroTypeException("No array type specified."); 
        return Schema.createArray(createSchema(arrayOfType[0], paramMap));
      } 
      if (Map.class.isAssignableFrom((Class<?>)type)) {
        type = arrayOfType[0];
        Type type1 = arrayOfType[1];
        if (!(paramType instanceof Class) || !CharSequence.class.isAssignableFrom((Class)paramType))
          throw new AvroTypeException("Map key class not CharSequence: " + type); 
        return Schema.createMap(createSchema(type1, paramMap));
      } 
      return createSchema(type, paramMap);
    } 
    if (paramType instanceof Class) {
      Class clazz = (Class)paramType;
      str = clazz.getName();
      Schema schema2 = (Schema)paramMap.get(str);
      Schema schema1 = schema2;
      if (schema2 == null) {
        try {
          schema2 = (Schema)clazz.getDeclaredField("SCHEMA$").get(null);
          schema1 = schema2;
          if (!str.equals(getClassName(schema2)))
            schema1 = Schema.parse(schema2.toString().replace(schema2.getNamespace(), clazz.getPackage().getName())); 
          paramMap.put(str, schema1);
        } catch (NoSuchFieldException noSuchFieldException) {
          throw new AvroRuntimeException("Not a Specific class: " + clazz);
        } catch (IllegalAccessException illegalAccessException) {
          throw new AvroRuntimeException(illegalAccessException);
        } 
        return (Schema)illegalAccessException;
      } 
    } else {
      throw new AvroTypeException("Unknown type: " + illegalAccessException);
    } 
    paramMap.put(str, illegalAccessException);
  }
  
  public Class getClass(Schema paramSchema) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: getstatic com/flurry/org/apache/avro/specific/SpecificData$2.$SwitchMap$org$apache$avro$Schema$Type : [I
    //   5: aload_1
    //   6: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   9: invokevirtual ordinal : ()I
    //   12: iaload
    //   13: tableswitch default -> 84, 1 -> 112, 2 -> 112, 3 -> 112, 4 -> 198, 5 -> 205, 6 -> 211, 7 -> 288, 8 -> 316, 9 -> 322, 10 -> 329, 11 -> 336, 12 -> 343, 13 -> 350, 14 -> 357
    //   84: new com/flurry/org/apache/avro/AvroRuntimeException
    //   87: dup
    //   88: new java/lang/StringBuilder
    //   91: dup
    //   92: invokespecial <init> : ()V
    //   95: ldc_w 'Unknown type: '
    //   98: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: aload_1
    //   102: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   105: invokevirtual toString : ()Ljava/lang/String;
    //   108: invokespecial <init> : (Ljava/lang/String;)V
    //   111: athrow
    //   112: aload_1
    //   113: invokevirtual getFullName : ()Ljava/lang/String;
    //   116: astore #5
    //   118: aload #5
    //   120: ifnonnull -> 127
    //   123: aload_3
    //   124: astore_1
    //   125: aload_1
    //   126: areturn
    //   127: aload_0
    //   128: getfield classCache : Ljava/util/Map;
    //   131: aload #5
    //   133: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   138: checkcast java/lang/Class
    //   141: astore #4
    //   143: aload #4
    //   145: astore_3
    //   146: aload #4
    //   148: ifnonnull -> 176
    //   151: aload_0
    //   152: getfield classLoader : Ljava/lang/ClassLoader;
    //   155: aload_1
    //   156: invokestatic getClassName : (Lcom/flurry/org/apache/avro/Schema;)Ljava/lang/String;
    //   159: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   162: astore_3
    //   163: aload_0
    //   164: getfield classCache : Ljava/util/Map;
    //   167: aload #5
    //   169: aload_3
    //   170: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   175: pop
    //   176: aload_3
    //   177: astore_1
    //   178: aload_3
    //   179: getstatic com/flurry/org/apache/avro/specific/SpecificData.NO_CLASS : Ljava/lang/Class;
    //   182: if_acmpne -> 187
    //   185: aconst_null
    //   186: astore_1
    //   187: goto -> 125
    //   190: astore_1
    //   191: getstatic com/flurry/org/apache/avro/specific/SpecificData.NO_CLASS : Ljava/lang/Class;
    //   194: astore_3
    //   195: goto -> 163
    //   198: ldc_w java/util/List
    //   201: astore_1
    //   202: goto -> 125
    //   205: ldc java/util/Map
    //   207: astore_1
    //   208: goto -> 125
    //   211: aload_1
    //   212: invokevirtual getTypes : ()Ljava/util/List;
    //   215: astore_1
    //   216: aload_1
    //   217: invokeinterface size : ()I
    //   222: iconst_2
    //   223: if_icmpne -> 282
    //   226: aload_1
    //   227: getstatic com/flurry/org/apache/avro/specific/SpecificData.NULL_SCHEMA : Lcom/flurry/org/apache/avro/Schema;
    //   230: invokeinterface contains : (Ljava/lang/Object;)Z
    //   235: ifeq -> 282
    //   238: aload_1
    //   239: iconst_0
    //   240: invokeinterface get : (I)Ljava/lang/Object;
    //   245: checkcast com/flurry/org/apache/avro/Schema
    //   248: getstatic com/flurry/org/apache/avro/specific/SpecificData.NULL_SCHEMA : Lcom/flurry/org/apache/avro/Schema;
    //   251: invokevirtual equals : (Ljava/lang/Object;)Z
    //   254: ifeq -> 277
    //   257: iconst_1
    //   258: istore_2
    //   259: aload_0
    //   260: aload_1
    //   261: iload_2
    //   262: invokeinterface get : (I)Ljava/lang/Object;
    //   267: checkcast com/flurry/org/apache/avro/Schema
    //   270: invokevirtual getClass : (Lcom/flurry/org/apache/avro/Schema;)Ljava/lang/Class;
    //   273: astore_1
    //   274: goto -> 125
    //   277: iconst_0
    //   278: istore_2
    //   279: goto -> 259
    //   282: ldc java/lang/Object
    //   284: astore_1
    //   285: goto -> 125
    //   288: ldc_w 'String'
    //   291: aload_1
    //   292: ldc_w 'avro.java.string'
    //   295: invokevirtual getProp : (Ljava/lang/String;)Ljava/lang/String;
    //   298: invokevirtual equals : (Ljava/lang/Object;)Z
    //   301: ifeq -> 310
    //   304: ldc java/lang/String
    //   306: astore_1
    //   307: goto -> 125
    //   310: ldc java/lang/CharSequence
    //   312: astore_1
    //   313: goto -> 125
    //   316: ldc java/nio/ByteBuffer
    //   318: astore_1
    //   319: goto -> 125
    //   322: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
    //   325: astore_1
    //   326: goto -> 125
    //   329: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
    //   332: astore_1
    //   333: goto -> 125
    //   336: getstatic java/lang/Float.TYPE : Ljava/lang/Class;
    //   339: astore_1
    //   340: goto -> 125
    //   343: getstatic java/lang/Double.TYPE : Ljava/lang/Class;
    //   346: astore_1
    //   347: goto -> 125
    //   350: getstatic java/lang/Boolean.TYPE : Ljava/lang/Class;
    //   353: astore_1
    //   354: goto -> 125
    //   357: getstatic java/lang/Void.TYPE : Ljava/lang/Class;
    //   360: astore_1
    //   361: goto -> 125
    // Exception table:
    //   from	to	target	type
    //   151	163	190	java/lang/ClassNotFoundException
  }
  
  protected Schema getEnumSchema(Object paramObject) {
    return (paramObject instanceof Enum) ? getSchema(paramObject.getClass()) : super.getEnumSchema(paramObject);
  }
  
  public Protocol getProtocol(Class paramClass) {
    try {
      Protocol protocol2 = (Protocol)paramClass.getDeclaredField("PROTOCOL").get(null);
      Protocol protocol1 = protocol2;
      if (!protocol2.getNamespace().equals(paramClass.getPackage().getName()))
        protocol1 = Protocol.parse(protocol2.toString().replace(protocol2.getNamespace(), paramClass.getPackage().getName())); 
      return protocol1;
    } catch (NoSuchFieldException noSuchFieldException) {
      throw new AvroRuntimeException("Not a Specific protocol: " + paramClass);
    } catch (IllegalAccessException illegalAccessException) {
      throw new AvroRuntimeException(illegalAccessException);
    } 
  }
  
  public Schema getSchema(Type paramType) {
    Schema schema2 = (Schema)this.schemaCache.get(paramType);
    Schema schema1 = schema2;
    if (schema2 == null) {
      schema1 = createSchema(paramType, new LinkedHashMap<Object, Object>());
      this.schemaCache.put(paramType, schema1);
    } 
    return schema1;
  }
  
  protected boolean isEnum(Object paramObject) {
    return (paramObject instanceof Enum || super.isEnum(paramObject));
  }
  
  public Object newRecord(Object paramObject, Schema paramSchema) {
    Class clazz = get().getClass(paramSchema);
    if (clazz == null)
      return super.newRecord(paramObject, paramSchema); 
    Object object = paramObject;
    if (!clazz.isInstance(paramObject))
      object = newInstance(clazz, paramSchema); 
    return object;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\specific\SpecificData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */