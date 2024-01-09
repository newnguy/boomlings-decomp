package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public class PropertyBuilder {
  protected final AnnotationIntrospector _annotationIntrospector;
  
  protected final BasicBeanDescription _beanDesc;
  
  protected final SerializationConfig _config;
  
  protected Object _defaultBean;
  
  protected final JsonSerialize.Inclusion _outputProps;
  
  public PropertyBuilder(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription) {
    this._config = paramSerializationConfig;
    this._beanDesc = paramBasicBeanDescription;
    this._outputProps = paramBasicBeanDescription.findSerializationInclusion(paramSerializationConfig.getSerializationInclusion());
    this._annotationIntrospector = this._config.getAnnotationIntrospector();
  }
  
  protected Object _throwWrapped(Exception paramException, String paramString, Object paramObject) {
    Throwable throwable;
    while (paramException.getCause() != null)
      throwable = paramException.getCause(); 
    if (throwable instanceof Error)
      throw (Error)throwable; 
    if (throwable instanceof RuntimeException)
      throw (RuntimeException)throwable; 
    throw new IllegalArgumentException("Failed to get property '" + paramString + "' of default " + paramObject.getClass().getName() + " instance");
  }
  
  protected BeanPropertyWriter buildWriter(String paramString, JavaType paramJavaType, JsonSerializer paramJsonSerializer, TypeSerializer paramTypeSerializer1, TypeSerializer paramTypeSerializer2, AnnotatedMember paramAnnotatedMember, boolean paramBoolean) {
    // Byte code:
    //   0: aload #6
    //   2: instanceof com/flurry/org/codehaus/jackson/map/introspect/AnnotatedField
    //   5: ifeq -> 114
    //   8: aconst_null
    //   9: astore #11
    //   11: aload #6
    //   13: checkcast com/flurry/org/codehaus/jackson/map/introspect/AnnotatedField
    //   16: invokevirtual getAnnotated : ()Ljava/lang/reflect/Field;
    //   19: astore #10
    //   21: aload_0
    //   22: aload #6
    //   24: iload #7
    //   26: aload_2
    //   27: invokevirtual findSerializationType : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;ZLcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   30: astore #12
    //   32: aload #5
    //   34: ifnull -> 397
    //   37: aload #12
    //   39: astore #13
    //   41: aload #12
    //   43: ifnonnull -> 49
    //   46: aload_2
    //   47: astore #13
    //   49: aload #13
    //   51: invokevirtual getContentType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   54: ifnonnull -> 130
    //   57: new java/lang/IllegalStateException
    //   60: dup
    //   61: new java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial <init> : ()V
    //   68: ldc 'Problem trying to create BeanPropertyWriter for property ''
    //   70: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: aload_1
    //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: ldc '' (of type '
    //   79: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: aload_0
    //   83: getfield _beanDesc : Lcom/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription;
    //   86: invokevirtual getType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   89: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   92: ldc '); serialization type '
    //   94: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload #13
    //   99: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   102: ldc ' has no content'
    //   104: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: invokevirtual toString : ()Ljava/lang/String;
    //   110: invokespecial <init> : (Ljava/lang/String;)V
    //   113: athrow
    //   114: aload #6
    //   116: checkcast com/flurry/org/codehaus/jackson/map/introspect/AnnotatedMethod
    //   119: invokevirtual getAnnotated : ()Ljava/lang/reflect/Method;
    //   122: astore #11
    //   124: aconst_null
    //   125: astore #10
    //   127: goto -> 21
    //   130: aload #13
    //   132: aload #5
    //   134: invokevirtual withContentTypeHandler : (Ljava/lang/Object;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   137: astore #12
    //   139: aload #12
    //   141: invokevirtual getContentType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   144: pop
    //   145: aconst_null
    //   146: astore #13
    //   148: iconst_0
    //   149: istore #7
    //   151: iconst_0
    //   152: istore #9
    //   154: aload_0
    //   155: getfield _annotationIntrospector : Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   158: aload #6
    //   160: aload_0
    //   161: getfield _outputProps : Lcom/flurry/org/codehaus/jackson/map/annotate/JsonSerialize$Inclusion;
    //   164: invokevirtual findSerializationInclusion : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/map/annotate/JsonSerialize$Inclusion;)Lcom/flurry/org/codehaus/jackson/map/annotate/JsonSerialize$Inclusion;
    //   167: astore #14
    //   169: iload #9
    //   171: istore #8
    //   173: aload #13
    //   175: astore #5
    //   177: aload #14
    //   179: ifnull -> 228
    //   182: getstatic com/flurry/org/codehaus/jackson/map/ser/PropertyBuilder$1.$SwitchMap$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion : [I
    //   185: aload #14
    //   187: invokevirtual ordinal : ()I
    //   190: iaload
    //   191: tableswitch default -> 220, 1 -> 296, 2 -> 354, 3 -> 368, 4 -> 371
    //   220: aload #13
    //   222: astore #5
    //   224: iload #9
    //   226: istore #8
    //   228: iload #8
    //   230: istore #7
    //   232: new com/flurry/org/codehaus/jackson/map/ser/BeanPropertyWriter
    //   235: dup
    //   236: aload #6
    //   238: aload_0
    //   239: getfield _beanDesc : Lcom/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription;
    //   242: invokevirtual getClassAnnotations : ()Lcom/flurry/org/codehaus/jackson/map/util/Annotations;
    //   245: aload_1
    //   246: aload_2
    //   247: aload_3
    //   248: aload #4
    //   250: aload #12
    //   252: aload #11
    //   254: aload #10
    //   256: iload #7
    //   258: aload #5
    //   260: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMember;Lcom/flurry/org/codehaus/jackson/map/util/Annotations;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;Lcom/flurry/org/codehaus/jackson/map/TypeSerializer;Lcom/flurry/org/codehaus/jackson/type/JavaType;Ljava/lang/reflect/Method;Ljava/lang/reflect/Field;ZLjava/lang/Object;)V
    //   263: astore_2
    //   264: aload_0
    //   265: getfield _annotationIntrospector : Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   268: aload #6
    //   270: invokevirtual shouldUnwrapProperty : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMember;)Ljava/lang/Boolean;
    //   273: astore_3
    //   274: aload_2
    //   275: astore_1
    //   276: aload_3
    //   277: ifnull -> 294
    //   280: aload_2
    //   281: astore_1
    //   282: aload_3
    //   283: invokevirtual booleanValue : ()Z
    //   286: ifeq -> 294
    //   289: aload_2
    //   290: invokevirtual unwrappingWriter : ()Lcom/flurry/org/codehaus/jackson/map/ser/BeanPropertyWriter;
    //   293: astore_1
    //   294: aload_1
    //   295: areturn
    //   296: aload_0
    //   297: aload_1
    //   298: aload #11
    //   300: aload #10
    //   302: invokevirtual getDefaultValue : (Ljava/lang/String;Ljava/lang/reflect/Method;Ljava/lang/reflect/Field;)Ljava/lang/Object;
    //   305: astore #13
    //   307: aload #13
    //   309: ifnonnull -> 322
    //   312: iconst_1
    //   313: istore #7
    //   315: aload #13
    //   317: astore #5
    //   319: goto -> 232
    //   322: iload #9
    //   324: istore #8
    //   326: aload #13
    //   328: astore #5
    //   330: aload #13
    //   332: invokevirtual getClass : ()Ljava/lang/Class;
    //   335: invokevirtual isArray : ()Z
    //   338: ifeq -> 228
    //   341: aload #13
    //   343: invokestatic getArrayComparator : (Ljava/lang/Object;)Ljava/lang/Object;
    //   346: astore #5
    //   348: iconst_0
    //   349: istore #7
    //   351: goto -> 232
    //   354: aload_0
    //   355: aload_1
    //   356: aload_2
    //   357: invokevirtual getEmptyValueChecker : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Ljava/lang/Object;
    //   360: astore #5
    //   362: iconst_1
    //   363: istore #7
    //   365: goto -> 232
    //   368: iconst_1
    //   369: istore #7
    //   371: iload #7
    //   373: istore #8
    //   375: aload #13
    //   377: astore #5
    //   379: aload_2
    //   380: invokevirtual isContainerType : ()Z
    //   383: ifeq -> 228
    //   386: aload_0
    //   387: aload_1
    //   388: aload_2
    //   389: invokevirtual getContainerValueChecker : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Ljava/lang/Object;
    //   392: astore #5
    //   394: goto -> 232
    //   397: goto -> 145
  }
  
  protected JavaType findSerializationType(Annotated paramAnnotated, boolean paramBoolean, JavaType paramJavaType) {
    boolean bool = true;
    Class<?> clazz = this._annotationIntrospector.findSerializationType(paramAnnotated);
    if (clazz != null) {
      Class<?> clazz1 = paramJavaType.getRawClass();
      if (clazz.isAssignableFrom(clazz1)) {
        paramJavaType = paramJavaType.widenBy(clazz);
      } else {
        if (!clazz1.isAssignableFrom(clazz))
          throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + paramAnnotated.getName() + "': class " + clazz.getName() + " not a super-type of (declared) class " + clazz1.getName()); 
        paramJavaType = this._config.constructSpecializedType(paramJavaType, clazz);
      } 
      paramBoolean = true;
    } 
    JavaType javaType = BeanSerializerFactory.modifySecondaryTypesByAnnotation(this._config, paramAnnotated, paramJavaType);
    if (javaType != paramJavaType) {
      paramJavaType = javaType;
      paramBoolean = true;
    } 
    if (!paramBoolean) {
      JsonSerialize.Typing typing = this._annotationIntrospector.findSerializationTyping(paramAnnotated);
      if (typing != null)
        if (typing == JsonSerialize.Typing.STATIC) {
          paramBoolean = bool;
        } else {
          paramBoolean = false;
        }  
    } 
    if (!paramBoolean)
      paramJavaType = null; 
    return paramJavaType;
  }
  
  public Annotations getClassAnnotations() {
    return this._beanDesc.getClassAnnotations();
  }
  
  protected Object getContainerValueChecker(String paramString, JavaType paramJavaType) {
    if (!this._config.isEnabled(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS)) {
      if (paramJavaType.isArrayType())
        return new PropertyBuilder$EmptyArrayChecker(); 
      if (Collection.class.isAssignableFrom(paramJavaType.getRawClass()))
        return new PropertyBuilder$EmptyCollectionChecker(); 
    } 
    return null;
  }
  
  protected Object getDefaultBean() {
    if (this._defaultBean == null) {
      this._defaultBean = this._beanDesc.instantiateBean(this._config.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
      if (this._defaultBean == null) {
        Class clazz = this._beanDesc.getClassInfo().getAnnotated();
        throw new IllegalArgumentException("Class " + clazz.getName() + " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation");
      } 
    } 
    return this._defaultBean;
  }
  
  protected Object getDefaultValue(String paramString, Method paramMethod, Field paramField) {
    Object object = getDefaultBean();
    if (paramMethod != null) {
      Object object1;
      try {
        Object object2 = paramMethod.invoke(object, new Object[0]);
        object1 = object2;
      } catch (Exception exception) {
        object1 = _throwWrapped(exception, (String)object1, object);
      } 
      return object1;
    } 
    return paramField.get(object);
  }
  
  protected Object getEmptyValueChecker(String paramString, JavaType paramJavaType) {
    null = paramJavaType.getRawClass();
    return (null == String.class) ? new PropertyBuilder$EmptyStringChecker() : (paramJavaType.isArrayType() ? new PropertyBuilder$EmptyArrayChecker() : (Collection.class.isAssignableFrom(null) ? new PropertyBuilder$EmptyCollectionChecker() : (Map.class.isAssignableFrom(null) ? new PropertyBuilder$EmptyMapChecker() : null)));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\PropertyBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */