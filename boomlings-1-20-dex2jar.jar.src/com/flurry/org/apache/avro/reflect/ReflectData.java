package com.flurry.org.apache.avro.reflect;

import com.flurry.org.apache.avro.AvroRemoteException;
import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Protocol;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.DatumReader;
import com.flurry.org.apache.avro.specific.SpecificData;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectData extends SpecificData {
  private static final Class BYTES_CLASS;
  
  static final String CLASS_PROP = "java-class";
  
  static final String ELEMENT_PROP = "java-element-class";
  
  private static final Map FIELD_CACHE;
  
  private static final ReflectData INSTANCE = new ReflectData();
  
  private static final Schema THROWABLE_MESSAGE;
  
  private final Paranamer paranamer = (Paranamer)new CachingParanamer();
  
  static {
    FIELD_CACHE = new ConcurrentHashMap<Object, Object>();
    BYTES_CLASS = (new byte[0]).getClass();
    THROWABLE_MESSAGE = makeNullable(Schema.create(Schema.Type.STRING));
  }
  
  private static Field findField(Class paramClass, String paramString) {
    Class clazz = paramClass;
    while (true) {
      try {
        Field field = clazz.getDeclaredField(paramString);
        field.setAccessible(true);
        return field;
      } catch (NoSuchFieldException noSuchFieldException) {
        Class clazz1 = clazz.getSuperclass();
        clazz = clazz1;
        if (clazz1 == null)
          throw new AvroRuntimeException("No field named " + paramString + " in: " + paramClass); 
      } 
    } 
  }
  
  public static ReflectData get() {
    return INSTANCE;
  }
  
  private Schema getAnnotatedUnion(Union paramUnion, Map paramMap) {
    ArrayList<Schema> arrayList = new ArrayList();
    Class[] arrayOfClass = paramUnion.value();
    int i = arrayOfClass.length;
    for (byte b = 0; b < i; b++)
      arrayList.add(createSchema(arrayOfClass[b], paramMap)); 
    return Schema.createUnion(arrayList);
  }
  
  static Class getClassProp(Schema paramSchema, String paramString) {
    String str = paramSchema.getProp(paramString);
    if (str == null)
      return null; 
    try {
      return Class.forName(str);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new AvroRuntimeException(classNotFoundException);
    } 
  }
  
  private static Field getField(Class<?> paramClass, String paramString) {
    Map<Object, Object> map = (Map)FIELD_CACHE.get(paramClass);
    if (map == null) {
      map = new ConcurrentHashMap<Object, Object>();
      FIELD_CACHE.put(paramClass, map);
    } 
    Field field2 = (Field)map.get(paramString);
    Field field1 = field2;
    if (field2 == null) {
      field1 = findField(paramClass, paramString);
      map.put(paramString, field1);
    } 
    return field1;
  }
  
  private Collection getFields(Class paramClass) {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    while (true) {
      if (paramClass.getPackage() != null && paramClass.getPackage().getName().startsWith("java."))
        return linkedHashMap.values(); 
      for (Field field : paramClass.getDeclaredFields()) {
        if ((field.getModifiers() & 0x88) == 0 && linkedHashMap.put(field.getName(), field) != null)
          throw new AvroTypeException(paramClass + " contains two fields named: " + field); 
      } 
      Class clazz = paramClass.getSuperclass();
      paramClass = clazz;
      if (clazz == null)
        return linkedHashMap.values(); 
    } 
  }
  
  private Protocol.Message getMessage(Method paramMethod, Protocol paramProtocol, Map paramMap) {
    Schema schema2;
    boolean bool = false;
    ArrayList<Schema.Field> arrayList1 = new ArrayList();
    String[] arrayOfString = this.paranamer.lookupParameterNames(paramMethod);
    Type[] arrayOfType1 = paramMethod.getGenericParameterTypes();
    Annotation[][] arrayOfAnnotation = paramMethod.getParameterAnnotations();
    byte b;
    for (b = 0; b < arrayOfType1.length; b++) {
      String str;
      schema2 = getSchema(arrayOfType1[b], paramMap);
      for (byte b1 = 0; b1 < (arrayOfAnnotation[b]).length; b1++) {
        if (arrayOfAnnotation[b][b1] instanceof Union) {
          schema2 = getAnnotatedUnion((Union)arrayOfAnnotation[b][b1], paramMap);
        } else if (arrayOfAnnotation[b][b1] instanceof Nullable) {
          schema2 = makeNullable(schema2);
        } 
      } 
      if (arrayOfString.length == arrayOfType1.length) {
        str = arrayOfString[b];
      } else {
        str = schema2.getName() + b;
      } 
      arrayList1.add(new Schema.Field(str, schema2, null, null));
    } 
    Schema schema3 = Schema.createRecord(arrayList1);
    Union union = paramMethod.<Union>getAnnotation(Union.class);
    if (union == null) {
      schema2 = getSchema(paramMethod.getGenericReturnType(), paramMap);
    } else {
      schema2 = getAnnotatedUnion((Union)schema2, paramMap);
    } 
    if (paramMethod.isAnnotationPresent((Class)Nullable.class))
      schema2 = makeNullable(schema2); 
    ArrayList<Schema> arrayList = new ArrayList();
    arrayList.add(Protocol.SYSTEM_ERROR);
    Type[] arrayOfType2 = paramMethod.getGenericExceptionTypes();
    int i = arrayOfType2.length;
    for (b = bool; b < i; b++) {
      Type type = arrayOfType2[b];
      if (type != AvroRemoteException.class)
        arrayList.add(getSchema(type, paramMap)); 
    } 
    Schema schema1 = Schema.createUnion(arrayList);
    return paramProtocol.createMessage(paramMethod.getName(), null, schema3, schema2, schema1);
  }
  
  private Schema getSchema(Type paramType, Map paramMap) {
    try {
      return createSchema(paramType, paramMap);
    } catch (AvroTypeException avroTypeException) {
      throw new AvroTypeException("Error getting schema for " + paramType + ": " + avroTypeException.getMessage(), avroTypeException);
    } 
  }
  
  public static Schema makeNullable(Schema paramSchema) {
    return Schema.createUnion(Arrays.asList(new Schema[] { Schema.create(Schema.Type.NULL), paramSchema }));
  }
  
  private void setElement(Schema paramSchema, Type paramType) {
    if (paramType instanceof Class) {
      paramType = paramType;
      if ((Union)paramType.getAnnotation(Union.class) != null)
        paramSchema.addProp("java-element-class", paramType.getName()); 
    } 
  }
  
  protected int compare(Object paramObject1, Object paramObject2, Schema paramSchema, boolean paramBoolean) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #6
    //   3: getstatic com/flurry/org/apache/avro/reflect/ReflectData$1.$SwitchMap$org$apache$avro$Schema$Type : [I
    //   6: aload_3
    //   7: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   10: invokevirtual ordinal : ()I
    //   13: iaload
    //   14: tableswitch default -> 40, 1 -> 54, 2 -> 40, 3 -> 143
    //   40: aload_0
    //   41: aload_1
    //   42: aload_2
    //   43: aload_3
    //   44: iload #4
    //   46: invokespecial compare : (Ljava/lang/Object;Ljava/lang/Object;Lcom/flurry/org/apache/avro/Schema;Z)I
    //   49: istore #5
    //   51: iload #5
    //   53: ireturn
    //   54: aload_1
    //   55: invokevirtual getClass : ()Ljava/lang/Class;
    //   58: invokevirtual isArray : ()Z
    //   61: ifeq -> 40
    //   64: aload_3
    //   65: invokevirtual getElementType : ()Lcom/flurry/org/apache/avro/Schema;
    //   68: astore_3
    //   69: aload_1
    //   70: invokestatic getLength : (Ljava/lang/Object;)I
    //   73: istore #8
    //   75: aload_2
    //   76: invokestatic getLength : (Ljava/lang/Object;)I
    //   79: istore #10
    //   81: iload #8
    //   83: iload #10
    //   85: invokestatic min : (II)I
    //   88: istore #9
    //   90: iload #6
    //   92: iload #9
    //   94: if_icmpge -> 133
    //   97: aload_0
    //   98: aload_1
    //   99: iload #6
    //   101: invokestatic get : (Ljava/lang/Object;I)Ljava/lang/Object;
    //   104: aload_2
    //   105: iload #6
    //   107: invokestatic get : (Ljava/lang/Object;I)Ljava/lang/Object;
    //   110: aload_3
    //   111: iload #4
    //   113: invokevirtual compare : (Ljava/lang/Object;Ljava/lang/Object;Lcom/flurry/org/apache/avro/Schema;Z)I
    //   116: istore #7
    //   118: iload #7
    //   120: istore #5
    //   122: iload #7
    //   124: ifne -> 51
    //   127: iinc #6, 1
    //   130: goto -> 90
    //   133: iload #8
    //   135: iload #10
    //   137: isub
    //   138: istore #5
    //   140: goto -> 51
    //   143: aload_1
    //   144: invokevirtual getClass : ()Ljava/lang/Class;
    //   147: invokevirtual isArray : ()Z
    //   150: ifeq -> 40
    //   153: aload_1
    //   154: checkcast [B
    //   157: astore_1
    //   158: aload_2
    //   159: checkcast [B
    //   162: astore_2
    //   163: aload_1
    //   164: iconst_0
    //   165: aload_1
    //   166: arraylength
    //   167: aload_2
    //   168: iconst_0
    //   169: aload_2
    //   170: arraylength
    //   171: invokestatic compareBytes : ([BII[BII)I
    //   174: istore #5
    //   176: goto -> 51
  }
  
  public DatumReader createDatumReader(Schema paramSchema) {
    return (DatumReader)new ReflectDatumReader(paramSchema, paramSchema, this);
  }
  
  protected Schema createFieldSchema(Field paramField, Map paramMap) {
    Schema schema2 = createSchema(paramField.getGenericType(), paramMap);
    Schema schema1 = schema2;
    if (paramField.isAnnotationPresent((Class)Nullable.class))
      schema1 = makeNullable(schema2); 
    return schema1;
  }
  
  protected Schema createSchema(Type paramType, Map paramMap) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof java/lang/reflect/GenericArrayType
    //   4: ifeq -> 56
    //   7: aload_1
    //   8: checkcast java/lang/reflect/GenericArrayType
    //   11: invokeinterface getGenericComponentType : ()Ljava/lang/reflect/Type;
    //   16: astore #5
    //   18: aload #5
    //   20: getstatic java/lang/Byte.TYPE : Ljava/lang/Class;
    //   23: if_acmpne -> 35
    //   26: getstatic com/flurry/org/apache/avro/Schema$Type.BYTES : Lcom/flurry/org/apache/avro/Schema$Type;
    //   29: invokestatic create : (Lcom/flurry/org/apache/avro/Schema$Type;)Lcom/flurry/org/apache/avro/Schema;
    //   32: astore_1
    //   33: aload_1
    //   34: areturn
    //   35: aload_0
    //   36: aload #5
    //   38: aload_2
    //   39: invokevirtual createSchema : (Ljava/lang/reflect/Type;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   42: invokestatic createArray : (Lcom/flurry/org/apache/avro/Schema;)Lcom/flurry/org/apache/avro/Schema;
    //   45: astore_1
    //   46: aload_0
    //   47: aload_1
    //   48: aload #5
    //   50: invokespecial setElement : (Lcom/flurry/org/apache/avro/Schema;Ljava/lang/reflect/Type;)V
    //   53: goto -> 33
    //   56: aload_1
    //   57: instanceof java/lang/reflect/ParameterizedType
    //   60: ifeq -> 215
    //   63: aload_1
    //   64: checkcast java/lang/reflect/ParameterizedType
    //   67: astore #6
    //   69: aload #6
    //   71: invokeinterface getRawType : ()Ljava/lang/reflect/Type;
    //   76: checkcast java/lang/Class
    //   79: astore #5
    //   81: aload #6
    //   83: invokeinterface getActualTypeArguments : ()[Ljava/lang/reflect/Type;
    //   88: astore #6
    //   90: ldc java/util/Map
    //   92: aload #5
    //   94: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   97: ifeq -> 159
    //   100: aload #6
    //   102: iconst_0
    //   103: aaload
    //   104: astore_1
    //   105: aload #6
    //   107: iconst_1
    //   108: aaload
    //   109: astore #5
    //   111: aload_1
    //   112: ldc java/lang/String
    //   114: if_acmpeq -> 145
    //   117: new com/flurry/org/apache/avro/AvroTypeException
    //   120: dup
    //   121: new java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial <init> : ()V
    //   128: ldc_w 'Map key class not String: '
    //   131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: aload_1
    //   135: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   138: invokevirtual toString : ()Ljava/lang/String;
    //   141: invokespecial <init> : (Ljava/lang/String;)V
    //   144: athrow
    //   145: aload_0
    //   146: aload #5
    //   148: aload_2
    //   149: invokevirtual createSchema : (Ljava/lang/reflect/Type;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   152: invokestatic createMap : (Lcom/flurry/org/apache/avro/Schema;)Lcom/flurry/org/apache/avro/Schema;
    //   155: astore_1
    //   156: goto -> 33
    //   159: ldc_w java/util/Collection
    //   162: aload #5
    //   164: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   167: ifeq -> 963
    //   170: aload #6
    //   172: arraylength
    //   173: iconst_1
    //   174: if_icmpeq -> 188
    //   177: new com/flurry/org/apache/avro/AvroTypeException
    //   180: dup
    //   181: ldc_w 'No array type specified.'
    //   184: invokespecial <init> : (Ljava/lang/String;)V
    //   187: athrow
    //   188: aload_0
    //   189: aload #6
    //   191: iconst_0
    //   192: aaload
    //   193: aload_2
    //   194: invokevirtual createSchema : (Ljava/lang/reflect/Type;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   197: invokestatic createArray : (Lcom/flurry/org/apache/avro/Schema;)Lcom/flurry/org/apache/avro/Schema;
    //   200: astore_1
    //   201: aload_1
    //   202: ldc 'java-class'
    //   204: aload #5
    //   206: invokevirtual getName : ()Ljava/lang/String;
    //   209: invokevirtual addProp : (Ljava/lang/String;Ljava/lang/String;)V
    //   212: goto -> 33
    //   215: aload_1
    //   216: ldc_w java/lang/Byte
    //   219: if_acmpeq -> 229
    //   222: aload_1
    //   223: getstatic java/lang/Byte.TYPE : Ljava/lang/Class;
    //   226: if_acmpne -> 251
    //   229: getstatic com/flurry/org/apache/avro/Schema$Type.INT : Lcom/flurry/org/apache/avro/Schema$Type;
    //   232: invokestatic create : (Lcom/flurry/org/apache/avro/Schema$Type;)Lcom/flurry/org/apache/avro/Schema;
    //   235: astore_1
    //   236: aload_1
    //   237: ldc 'java-class'
    //   239: ldc_w java/lang/Byte
    //   242: invokevirtual getName : ()Ljava/lang/String;
    //   245: invokevirtual addProp : (Ljava/lang/String;Ljava/lang/String;)V
    //   248: goto -> 33
    //   251: aload_1
    //   252: ldc_w java/lang/Short
    //   255: if_acmpeq -> 265
    //   258: aload_1
    //   259: getstatic java/lang/Short.TYPE : Ljava/lang/Class;
    //   262: if_acmpne -> 287
    //   265: getstatic com/flurry/org/apache/avro/Schema$Type.INT : Lcom/flurry/org/apache/avro/Schema$Type;
    //   268: invokestatic create : (Lcom/flurry/org/apache/avro/Schema$Type;)Lcom/flurry/org/apache/avro/Schema;
    //   271: astore_1
    //   272: aload_1
    //   273: ldc 'java-class'
    //   275: ldc_w java/lang/Short
    //   278: invokevirtual getName : ()Ljava/lang/String;
    //   281: invokevirtual addProp : (Ljava/lang/String;Ljava/lang/String;)V
    //   284: goto -> 33
    //   287: aload_1
    //   288: instanceof java/lang/Class
    //   291: ifeq -> 963
    //   294: aload_1
    //   295: checkcast java/lang/Class
    //   298: astore #7
    //   300: aload #7
    //   302: invokevirtual isPrimitive : ()Z
    //   305: ifne -> 335
    //   308: ldc_w java/lang/Number
    //   311: aload #7
    //   313: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   316: ifne -> 335
    //   319: aload #7
    //   321: ldc_w java/lang/Void
    //   324: if_acmpeq -> 335
    //   327: aload #7
    //   329: ldc_w java/lang/Boolean
    //   332: if_acmpne -> 345
    //   335: aload_0
    //   336: aload_1
    //   337: aload_2
    //   338: invokespecial createSchema : (Ljava/lang/reflect/Type;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   341: astore_1
    //   342: goto -> 33
    //   345: aload #7
    //   347: invokevirtual isArray : ()Z
    //   350: ifeq -> 399
    //   353: aload #7
    //   355: invokevirtual getComponentType : ()Ljava/lang/Class;
    //   358: astore #5
    //   360: aload #5
    //   362: getstatic java/lang/Byte.TYPE : Ljava/lang/Class;
    //   365: if_acmpne -> 378
    //   368: getstatic com/flurry/org/apache/avro/Schema$Type.BYTES : Lcom/flurry/org/apache/avro/Schema$Type;
    //   371: invokestatic create : (Lcom/flurry/org/apache/avro/Schema$Type;)Lcom/flurry/org/apache/avro/Schema;
    //   374: astore_1
    //   375: goto -> 33
    //   378: aload_0
    //   379: aload #5
    //   381: aload_2
    //   382: invokevirtual createSchema : (Ljava/lang/reflect/Type;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   385: invokestatic createArray : (Lcom/flurry/org/apache/avro/Schema;)Lcom/flurry/org/apache/avro/Schema;
    //   388: astore_1
    //   389: aload_0
    //   390: aload_1
    //   391: aload #5
    //   393: invokespecial setElement : (Lcom/flurry/org/apache/avro/Schema;Ljava/lang/reflect/Type;)V
    //   396: goto -> 33
    //   399: ldc_w java/lang/CharSequence
    //   402: aload #7
    //   404: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   407: ifeq -> 420
    //   410: getstatic com/flurry/org/apache/avro/Schema$Type.STRING : Lcom/flurry/org/apache/avro/Schema$Type;
    //   413: invokestatic create : (Lcom/flurry/org/apache/avro/Schema$Type;)Lcom/flurry/org/apache/avro/Schema;
    //   416: astore_1
    //   417: goto -> 33
    //   420: ldc_w java/nio/ByteBuffer
    //   423: aload #7
    //   425: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   428: ifeq -> 441
    //   431: getstatic com/flurry/org/apache/avro/Schema$Type.BYTES : Lcom/flurry/org/apache/avro/Schema$Type;
    //   434: invokestatic create : (Lcom/flurry/org/apache/avro/Schema$Type;)Lcom/flurry/org/apache/avro/Schema;
    //   437: astore_1
    //   438: goto -> 33
    //   441: ldc_w java/util/Collection
    //   444: aload #7
    //   446: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   449: ifeq -> 463
    //   452: new com/flurry/org/apache/avro/AvroRuntimeException
    //   455: dup
    //   456: ldc_w 'Can't find element type of Collection'
    //   459: invokespecial <init> : (Ljava/lang/String;)V
    //   462: athrow
    //   463: aload #7
    //   465: invokevirtual getName : ()Ljava/lang/String;
    //   468: astore #6
    //   470: aload_2
    //   471: aload #6
    //   473: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   478: checkcast com/flurry/org/apache/avro/Schema
    //   481: astore #5
    //   483: aload #5
    //   485: ifnonnull -> 981
    //   488: aload #7
    //   490: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   493: astore #8
    //   495: aload #7
    //   497: invokevirtual getPackage : ()Ljava/lang/Package;
    //   500: ifnonnull -> 573
    //   503: ldc_w ''
    //   506: astore #5
    //   508: aload #7
    //   510: invokevirtual getEnclosingClass : ()Ljava/lang/Class;
    //   513: ifnull -> 978
    //   516: new java/lang/StringBuilder
    //   519: dup
    //   520: invokespecial <init> : ()V
    //   523: aload #7
    //   525: invokevirtual getEnclosingClass : ()Ljava/lang/Class;
    //   528: invokevirtual getName : ()Ljava/lang/String;
    //   531: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: ldc_w '$'
    //   537: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   540: invokevirtual toString : ()Ljava/lang/String;
    //   543: astore #5
    //   545: aload #7
    //   547: ldc com/flurry/org/apache/avro/reflect/Union
    //   549: invokevirtual getAnnotation : (Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   552: checkcast com/flurry/org/apache/avro/reflect/Union
    //   555: astore #9
    //   557: aload #9
    //   559: ifnull -> 586
    //   562: aload_0
    //   563: aload #9
    //   565: aload_2
    //   566: invokespecial getAnnotatedUnion : (Lcom/flurry/org/apache/avro/reflect/Union;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   569: astore_1
    //   570: goto -> 33
    //   573: aload #7
    //   575: invokevirtual getPackage : ()Ljava/lang/Package;
    //   578: invokevirtual getName : ()Ljava/lang/String;
    //   581: astore #5
    //   583: goto -> 508
    //   586: aload #7
    //   588: ldc_w com/flurry/org/apache/avro/reflect/Stringable
    //   591: invokevirtual isAnnotationPresent : (Ljava/lang/Class;)Z
    //   594: ifeq -> 618
    //   597: getstatic com/flurry/org/apache/avro/Schema$Type.STRING : Lcom/flurry/org/apache/avro/Schema$Type;
    //   600: invokestatic create : (Lcom/flurry/org/apache/avro/Schema$Type;)Lcom/flurry/org/apache/avro/Schema;
    //   603: astore_1
    //   604: aload_1
    //   605: ldc 'java-class'
    //   607: aload #7
    //   609: invokevirtual getName : ()Ljava/lang/String;
    //   612: invokevirtual addProp : (Ljava/lang/String;Ljava/lang/String;)V
    //   615: goto -> 33
    //   618: aload #7
    //   620: invokevirtual isEnum : ()Z
    //   623: ifeq -> 696
    //   626: new java/util/ArrayList
    //   629: dup
    //   630: invokespecial <init> : ()V
    //   633: astore_1
    //   634: aload #7
    //   636: invokevirtual getEnumConstants : ()[Ljava/lang/Object;
    //   639: checkcast [Ljava/lang/Enum;
    //   642: astore #7
    //   644: iconst_0
    //   645: istore_3
    //   646: iload_3
    //   647: aload #7
    //   649: arraylength
    //   650: if_icmpge -> 673
    //   653: aload_1
    //   654: aload #7
    //   656: iload_3
    //   657: aaload
    //   658: invokevirtual name : ()Ljava/lang/String;
    //   661: invokeinterface add : (Ljava/lang/Object;)Z
    //   666: pop
    //   667: iinc #3, 1
    //   670: goto -> 646
    //   673: aload #8
    //   675: aconst_null
    //   676: aload #5
    //   678: aload_1
    //   679: invokestatic createEnum : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lcom/flurry/org/apache/avro/Schema;
    //   682: astore_1
    //   683: aload_2
    //   684: aload #6
    //   686: aload_1
    //   687: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   692: pop
    //   693: goto -> 33
    //   696: ldc_w com/flurry/org/apache/avro/generic/GenericFixed
    //   699: aload #7
    //   701: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   704: ifeq -> 735
    //   707: aload #8
    //   709: aconst_null
    //   710: aload #5
    //   712: aload #7
    //   714: ldc_w com/flurry/org/apache/avro/specific/FixedSize
    //   717: invokevirtual getAnnotation : (Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   720: checkcast com/flurry/org/apache/avro/specific/FixedSize
    //   723: invokeinterface value : ()I
    //   728: invokestatic createFixed : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Lcom/flurry/org/apache/avro/Schema;
    //   731: astore_1
    //   732: goto -> 683
    //   735: ldc_w com/flurry/org/apache/avro/generic/IndexedRecord
    //   738: aload #7
    //   740: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   743: ifeq -> 756
    //   746: aload_0
    //   747: aload_1
    //   748: aload_2
    //   749: invokespecial createSchema : (Ljava/lang/reflect/Type;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   752: astore_1
    //   753: goto -> 33
    //   756: new java/util/ArrayList
    //   759: dup
    //   760: invokespecial <init> : ()V
    //   763: astore #9
    //   765: ldc_w java/lang/Throwable
    //   768: aload #7
    //   770: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   773: istore #4
    //   775: aload #8
    //   777: aconst_null
    //   778: aload #5
    //   780: iload #4
    //   782: invokestatic createRecord : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Lcom/flurry/org/apache/avro/Schema;
    //   785: astore #5
    //   787: aload_2
    //   788: aload #7
    //   790: invokevirtual getName : ()Ljava/lang/String;
    //   793: aload #5
    //   795: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   800: pop
    //   801: aload_0
    //   802: aload #7
    //   804: invokespecial getFields : (Ljava/lang/Class;)Ljava/util/Collection;
    //   807: invokeinterface iterator : ()Ljava/util/Iterator;
    //   812: astore #7
    //   814: aload #7
    //   816: invokeinterface hasNext : ()Z
    //   821: ifeq -> 922
    //   824: aload #7
    //   826: invokeinterface next : ()Ljava/lang/Object;
    //   831: checkcast java/lang/reflect/Field
    //   834: astore #8
    //   836: aload #8
    //   838: invokevirtual getModifiers : ()I
    //   841: sipush #136
    //   844: iand
    //   845: ifne -> 814
    //   848: aload_0
    //   849: aload #8
    //   851: aload_2
    //   852: invokevirtual createFieldSchema : (Ljava/lang/reflect/Field;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   855: astore #10
    //   857: aload #10
    //   859: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   862: getstatic com/flurry/org/apache/avro/Schema$Type.UNION : Lcom/flurry/org/apache/avro/Schema$Type;
    //   865: if_acmpne -> 973
    //   868: aload #10
    //   870: invokevirtual getTypes : ()Ljava/util/List;
    //   873: iconst_0
    //   874: invokeinterface get : (I)Ljava/lang/Object;
    //   879: checkcast com/flurry/org/apache/avro/Schema
    //   882: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   885: getstatic com/flurry/org/apache/avro/Schema$Type.NULL : Lcom/flurry/org/apache/avro/Schema$Type;
    //   888: if_acmpne -> 973
    //   891: invokestatic getInstance : ()Lcom/flurry/org/codehaus/jackson/node/NullNode;
    //   894: astore_1
    //   895: aload #9
    //   897: new com/flurry/org/apache/avro/Schema$Field
    //   900: dup
    //   901: aload #8
    //   903: invokevirtual getName : ()Ljava/lang/String;
    //   906: aload #10
    //   908: aconst_null
    //   909: aload_1
    //   910: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/apache/avro/Schema;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;)V
    //   913: invokeinterface add : (Ljava/lang/Object;)Z
    //   918: pop
    //   919: goto -> 814
    //   922: iload #4
    //   924: ifeq -> 950
    //   927: aload #9
    //   929: new com/flurry/org/apache/avro/Schema$Field
    //   932: dup
    //   933: ldc_w 'detailMessage'
    //   936: getstatic com/flurry/org/apache/avro/reflect/ReflectData.THROWABLE_MESSAGE : Lcom/flurry/org/apache/avro/Schema;
    //   939: aconst_null
    //   940: aconst_null
    //   941: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/apache/avro/Schema;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;)V
    //   944: invokeinterface add : (Ljava/lang/Object;)Z
    //   949: pop
    //   950: aload #5
    //   952: aload #9
    //   954: invokevirtual setFields : (Ljava/util/List;)V
    //   957: aload #5
    //   959: astore_1
    //   960: goto -> 683
    //   963: aload_0
    //   964: aload_1
    //   965: aload_2
    //   966: invokespecial createSchema : (Ljava/lang/reflect/Type;Ljava/util/Map;)Lcom/flurry/org/apache/avro/Schema;
    //   969: astore_1
    //   970: goto -> 33
    //   973: aconst_null
    //   974: astore_1
    //   975: goto -> 895
    //   978: goto -> 545
    //   981: aload #5
    //   983: astore_1
    //   984: goto -> 33
  }
  
  public Class getClass(Schema paramSchema) {
    Class<?> clazz;
    switch (ReflectData$1.$SwitchMap$org$apache$avro$Schema$Type[paramSchema.getType().ordinal()]) {
      default:
        return super.getClass(paramSchema);
      case 1:
        clazz = getClassProp(paramSchema, "java-class");
        null = clazz;
        if (clazz == null)
          null = Array.newInstance(getClass(paramSchema.getElementType()), 0).getClass(); 
        return null;
      case 2:
        return String.class;
      case 3:
        return BYTES_CLASS;
      case 4:
        break;
    } 
    String str = paramSchema.getProp("java-class");
    if (Byte.class.getName().equals(str))
      return byte.class; 
    if (Short.class.getName().equals(str))
      return short.class; 
  }
  
  public Object getField(Object paramObject, String paramString, int paramInt) {
    if (paramObject instanceof com.flurry.org.apache.avro.generic.IndexedRecord)
      return super.getField(paramObject, paramString, paramInt); 
    try {
      return getField(paramObject.getClass(), paramString).get(paramObject);
    } catch (IllegalAccessException illegalAccessException) {
      throw new AvroRuntimeException(illegalAccessException);
    } 
  }
  
  public Protocol getProtocol(Class paramClass) {
    String str1;
    String str2 = paramClass.getSimpleName();
    if (paramClass.getPackage() == null) {
      str1 = "";
    } else {
      str1 = paramClass.getPackage().getName();
    } 
    Protocol protocol = new Protocol(str2, str1);
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    Map<String, Protocol.Message> map = protocol.getMessages();
    for (Method method : paramClass.getMethods()) {
      if ((method.getModifiers() & 0x8) == 0) {
        String str = method.getName();
        if (map.containsKey(str))
          throw new AvroTypeException("Two methods with same name: " + str); 
        map.put(str, getMessage(method, protocol, linkedHashMap));
      } 
    } 
    ArrayList<?> arrayList = new ArrayList();
    arrayList.addAll(linkedHashMap.values());
    Collections.reverse(arrayList);
    protocol.setTypes(arrayList);
    return protocol;
  }
  
  protected Schema getRecordSchema(Object paramObject) {
    return (paramObject instanceof com.flurry.org.apache.avro.generic.GenericContainer) ? super.getRecordSchema(paramObject) : getSchema(paramObject.getClass());
  }
  
  protected boolean isArray(Object paramObject) {
    boolean bool = false;
    if (paramObject != null && (paramObject instanceof Collection || paramObject.getClass().isArray()))
      bool = true; 
    return bool;
  }
  
  protected boolean isBytes(Object<?> paramObject) {
    boolean bool = true;
    if (paramObject == null)
      return false; 
    null = bool;
    if (!super.isBytes(paramObject)) {
      paramObject = (Object<?>)paramObject.getClass();
      if (paramObject.isArray()) {
        null = bool;
        return (paramObject.getComponentType() != byte.class) ? false : null;
      } 
    } else {
      return null;
    } 
    return false;
  }
  
  protected boolean isRecord(Object paramObject) {
    boolean bool2 = true;
    boolean bool1 = false;
    if (paramObject != null) {
      if (super.isRecord(paramObject))
        return true; 
      if (!(paramObject instanceof Collection)) {
        if (getSchema(paramObject.getClass()).getType() == Schema.Type.RECORD)
          return bool2; 
        bool1 = false;
      } 
    } 
    return bool1;
  }
  
  public void setField(Object paramObject1, String paramString, int paramInt, Object paramObject2) {
    if (paramObject1 instanceof com.flurry.org.apache.avro.generic.IndexedRecord) {
      super.setField(paramObject1, paramString, paramInt, paramObject2);
      return;
    } 
    try {
      getField(paramObject1.getClass(), paramString).set(paramObject1, paramObject2);
      return;
    } catch (IllegalAccessException illegalAccessException) {
      throw new AvroRuntimeException(illegalAccessException);
    } 
  }
  
  public boolean validate(Schema paramSchema, Object paramObject) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #6
    //   3: getstatic com/flurry/org/apache/avro/reflect/ReflectData$1.$SwitchMap$org$apache$avro$Schema$Type : [I
    //   6: aload_1
    //   7: invokevirtual getType : ()Lcom/flurry/org/apache/avro/Schema$Type;
    //   10: invokevirtual ordinal : ()I
    //   13: iaload
    //   14: tableswitch default -> 32, 1 -> 43
    //   32: aload_0
    //   33: aload_1
    //   34: aload_2
    //   35: invokespecial validate : (Lcom/flurry/org/apache/avro/Schema;Ljava/lang/Object;)Z
    //   38: istore #5
    //   40: iload #5
    //   42: ireturn
    //   43: aload_2
    //   44: invokevirtual getClass : ()Ljava/lang/Class;
    //   47: invokevirtual isArray : ()Z
    //   50: ifne -> 64
    //   53: aload_0
    //   54: aload_1
    //   55: aload_2
    //   56: invokespecial validate : (Lcom/flurry/org/apache/avro/Schema;Ljava/lang/Object;)Z
    //   59: istore #5
    //   61: goto -> 40
    //   64: aload_2
    //   65: invokestatic getLength : (Ljava/lang/Object;)I
    //   68: istore #4
    //   70: iconst_0
    //   71: istore_3
    //   72: iload_3
    //   73: iload #4
    //   75: if_icmpge -> 104
    //   78: iload #6
    //   80: istore #5
    //   82: aload_0
    //   83: aload_1
    //   84: invokevirtual getElementType : ()Lcom/flurry/org/apache/avro/Schema;
    //   87: aload_2
    //   88: iload_3
    //   89: invokestatic get : (Ljava/lang/Object;I)Ljava/lang/Object;
    //   92: invokevirtual validate : (Lcom/flurry/org/apache/avro/Schema;Ljava/lang/Object;)Z
    //   95: ifeq -> 40
    //   98: iinc #3, 1
    //   101: goto -> 72
    //   104: iconst_1
    //   105: istore #5
    //   107: goto -> 40
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\reflect\ReflectData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */