package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.Deserializers;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializers;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.impl.CreatorCollector;
import com.flurry.org.codehaus.jackson.map.deser.impl.CreatorProperty;
import com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializers;
import com.flurry.org.codehaus.jackson.map.deser.std.ThrowableDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanDeserializerFactory extends BasicDeserializerFactory {
  private static final Class[] INIT_CAUSE_PARAMS = new Class[] { Throwable.class };
  
  public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(null);
  
  protected final DeserializerFactory.Config _factoryConfig;
  
  @Deprecated
  public BeanDeserializerFactory() {
    this((DeserializerFactory.Config)null);
  }
  
  public BeanDeserializerFactory(DeserializerFactory.Config paramConfig) {
    DeserializerFactory.Config config = paramConfig;
    if (paramConfig == null)
      config = new BeanDeserializerFactory$ConfigImpl(); 
    this._factoryConfig = config;
  }
  
  private KeyDeserializer _createEnumKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokevirtual introspect : (Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/map/BeanDescription;
    //   5: checkcast com/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription
    //   8: astore #4
    //   10: aload_2
    //   11: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   14: astore_3
    //   15: aload_0
    //   16: aload_3
    //   17: aload_1
    //   18: invokevirtual constructEnumResolver : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;)Lcom/flurry/org/codehaus/jackson/map/util/EnumResolver;
    //   21: astore_2
    //   22: aload #4
    //   24: invokevirtual getFactoryMethods : ()Ljava/util/List;
    //   27: invokeinterface iterator : ()Ljava/util/Iterator;
    //   32: astore #5
    //   34: aload #5
    //   36: invokeinterface hasNext : ()Z
    //   41: ifeq -> 202
    //   44: aload #5
    //   46: invokeinterface next : ()Ljava/lang/Object;
    //   51: checkcast com/flurry/org/codehaus/jackson/map/introspect/AnnotatedMethod
    //   54: astore #4
    //   56: aload_1
    //   57: invokevirtual getAnnotationIntrospector : ()Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   60: aload #4
    //   62: invokevirtual hasCreatorAnnotation : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Z
    //   65: ifeq -> 34
    //   68: aload #4
    //   70: invokevirtual getParameterCount : ()I
    //   73: iconst_1
    //   74: if_icmpne -> 157
    //   77: aload #4
    //   79: invokevirtual getRawType : ()Ljava/lang/Class;
    //   82: aload_3
    //   83: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   86: ifeq -> 157
    //   89: aload #4
    //   91: iconst_0
    //   92: invokevirtual getParameterType : (I)Ljava/lang/reflect/Type;
    //   95: ldc java/lang/String
    //   97: if_acmpeq -> 133
    //   100: new java/lang/IllegalArgumentException
    //   103: dup
    //   104: new java/lang/StringBuilder
    //   107: dup
    //   108: invokespecial <init> : ()V
    //   111: ldc 'Parameter #0 type for factory method ('
    //   113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: aload #4
    //   118: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   121: ldc ') not suitable, must be java.lang.String'
    //   123: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: invokevirtual toString : ()Ljava/lang/String;
    //   129: invokespecial <init> : (Ljava/lang/String;)V
    //   132: athrow
    //   133: aload_1
    //   134: invokevirtual canOverrideAccessModifiers : ()Z
    //   137: ifeq -> 148
    //   140: aload #4
    //   142: invokevirtual getMember : ()Ljava/lang/reflect/Member;
    //   145: invokestatic checkAndFixAccess : (Ljava/lang/reflect/Member;)V
    //   148: aload_2
    //   149: aload #4
    //   151: invokestatic constructEnumKeyDeserializer : (Lcom/flurry/org/codehaus/jackson/map/util/EnumResolver;Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMethod;)Lcom/flurry/org/codehaus/jackson/map/KeyDeserializer;
    //   154: astore_1
    //   155: aload_1
    //   156: areturn
    //   157: new java/lang/IllegalArgumentException
    //   160: dup
    //   161: new java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial <init> : ()V
    //   168: ldc 'Unsuitable method ('
    //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: aload #4
    //   175: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   178: ldc ') decorated with @JsonCreator (for Enum type '
    //   180: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: aload_3
    //   184: invokevirtual getName : ()Ljava/lang/String;
    //   187: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: ldc ')'
    //   192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: invokevirtual toString : ()Ljava/lang/String;
    //   198: invokespecial <init> : (Ljava/lang/String;)V
    //   201: athrow
    //   202: aload_2
    //   203: invokestatic constructEnumKeyDeserializer : (Lcom/flurry/org/codehaus/jackson/map/util/EnumResolver;)Lcom/flurry/org/codehaus/jackson/map/KeyDeserializer;
    //   206: astore_1
    //   207: goto -> 155
  }
  
  protected void _addDeserializerConstructors(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, VisibilityChecker paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector) {
    for (AnnotatedConstructor annotatedConstructor : paramBasicBeanDescription.getConstructors()) {
      int i = annotatedConstructor.getParameterCount();
      if (i >= 1) {
        boolean bool2 = paramAnnotationIntrospector.hasCreatorAnnotation((Annotated)annotatedConstructor);
        boolean bool1 = paramVisibilityChecker.isCreatorVisible((AnnotatedMember)annotatedConstructor);
        if (i == 1) {
          _handleSingleArgumentConstructor(paramDeserializationConfig, paramBasicBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, annotatedConstructor, bool2, bool1);
          continue;
        } 
        if (bool2 || bool1) {
          String str = null;
          byte b2 = 0;
          byte b3 = 0;
          CreatorProperty[] arrayOfCreatorProperty = new CreatorProperty[i];
          byte b1 = 0;
          while (b1 < i) {
            byte b4;
            byte b5;
            String str1;
            AnnotatedParameter annotatedParameter = annotatedConstructor.getParameter(b1);
            if (annotatedParameter == null) {
              str1 = null;
            } else {
              str1 = paramAnnotationIntrospector.findPropertyNameForParam(annotatedParameter);
            } 
            Object object = paramAnnotationIntrospector.findInjectableValueId((AnnotatedMember)annotatedParameter);
            if (str1 != null && str1.length() > 0) {
              b5 = b2 + 1;
              arrayOfCreatorProperty[b1] = constructCreatorProperty(paramDeserializationConfig, paramBasicBeanDescription, str1, b1, annotatedParameter, object);
              str1 = str;
              b4 = b3;
            } else if (object != null) {
              b4 = b3 + 1;
              arrayOfCreatorProperty[b1] = constructCreatorProperty(paramDeserializationConfig, paramBasicBeanDescription, str1, b1, annotatedParameter, object);
              b5 = b2;
              str1 = str;
            } else {
              b4 = b3;
              b5 = b2;
              AnnotatedParameter annotatedParameter1 = annotatedParameter;
              if (str != null) {
                b4 = b3;
                b5 = b2;
                str1 = str;
              } 
            } 
            b1++;
            str = str1;
            b3 = b4;
            b2 = b5;
          } 
          if (bool2 || b2 > 0 || b3 > 0) {
            if (b2 + b3 == i) {
              paramCreatorCollector.addPropertyCreator((AnnotatedWithParams)annotatedConstructor, arrayOfCreatorProperty);
              continue;
            } 
            if (b2 == 0 && b3 + 1 == i)
              throw new IllegalArgumentException("Delegated constructor with Injectables not yet supported (see [JACKSON-712]) for " + annotatedConstructor); 
            throw new IllegalArgumentException("Argument #" + str.getIndex() + " of constructor " + annotatedConstructor + " has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
          } 
        } 
      } 
    } 
  }
  
  protected void _addDeserializerFactoryMethods(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, VisibilityChecker paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual getFactoryMethods : ()Ljava/util/List;
    //   4: invokeinterface iterator : ()Ljava/util/Iterator;
    //   9: astore #10
    //   11: aload #10
    //   13: invokeinterface hasNext : ()Z
    //   18: ifeq -> 272
    //   21: aload #10
    //   23: invokeinterface next : ()Ljava/lang/Object;
    //   28: checkcast com/flurry/org/codehaus/jackson/map/introspect/AnnotatedMethod
    //   31: astore #9
    //   33: aload #9
    //   35: invokevirtual getParameterCount : ()I
    //   38: istore #7
    //   40: iload #7
    //   42: iconst_1
    //   43: if_icmplt -> 11
    //   46: aload #4
    //   48: aload #9
    //   50: invokevirtual hasCreatorAnnotation : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Z
    //   53: istore #8
    //   55: iload #7
    //   57: iconst_1
    //   58: if_icmpne -> 120
    //   61: aload #9
    //   63: iconst_0
    //   64: invokevirtual getParameter : (I)Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedParameter;
    //   67: astore #11
    //   69: aload #4
    //   71: aload #11
    //   73: invokevirtual findPropertyNameForParam : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedParameter;)Ljava/lang/String;
    //   76: astore #12
    //   78: aload #4
    //   80: aload #11
    //   82: invokevirtual findInjectableValueId : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMember;)Ljava/lang/Object;
    //   85: ifnonnull -> 130
    //   88: aload #12
    //   90: ifnull -> 101
    //   93: aload #12
    //   95: invokevirtual length : ()I
    //   98: ifne -> 130
    //   101: aload_0
    //   102: aload_1
    //   103: aload_2
    //   104: aload_3
    //   105: aload #4
    //   107: aload #5
    //   109: aload #9
    //   111: iload #8
    //   113: invokevirtual _handleSingleArgumentFactory : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription;Lcom/flurry/org/codehaus/jackson/map/introspect/VisibilityChecker;Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;Lcom/flurry/org/codehaus/jackson/map/deser/impl/CreatorCollector;Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMethod;Z)Z
    //   116: pop
    //   117: goto -> 11
    //   120: aload #4
    //   122: aload #9
    //   124: invokevirtual hasCreatorAnnotation : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Z
    //   127: ifeq -> 11
    //   130: iload #7
    //   132: anewarray com/flurry/org/codehaus/jackson/map/deser/impl/CreatorProperty
    //   135: astore #12
    //   137: iconst_0
    //   138: istore #6
    //   140: iload #6
    //   142: iload #7
    //   144: if_icmpge -> 260
    //   147: aload #9
    //   149: iload #6
    //   151: invokevirtual getParameter : (I)Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedParameter;
    //   154: astore #13
    //   156: aload #4
    //   158: aload #13
    //   160: invokevirtual findPropertyNameForParam : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedParameter;)Ljava/lang/String;
    //   163: astore #11
    //   165: aload #4
    //   167: aload #13
    //   169: invokevirtual findInjectableValueId : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMember;)Ljava/lang/Object;
    //   172: astore #14
    //   174: aload #11
    //   176: ifnull -> 187
    //   179: aload #11
    //   181: invokevirtual length : ()I
    //   184: ifne -> 235
    //   187: aload #14
    //   189: ifnonnull -> 235
    //   192: new java/lang/IllegalArgumentException
    //   195: dup
    //   196: new java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial <init> : ()V
    //   203: ldc 'Argument #'
    //   205: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: iload #6
    //   210: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   213: ldc ' of factory method '
    //   215: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload #9
    //   220: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   223: ldc ' has no property name annotation; must have when multiple-paramater static method annotated as Creator'
    //   225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: invokevirtual toString : ()Ljava/lang/String;
    //   231: invokespecial <init> : (Ljava/lang/String;)V
    //   234: athrow
    //   235: aload #12
    //   237: iload #6
    //   239: aload_0
    //   240: aload_1
    //   241: aload_2
    //   242: aload #11
    //   244: iload #6
    //   246: aload #13
    //   248: aload #14
    //   250: invokevirtual constructCreatorProperty : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription;Ljava/lang/String;ILcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedParameter;Ljava/lang/Object;)Lcom/flurry/org/codehaus/jackson/map/deser/impl/CreatorProperty;
    //   253: aastore
    //   254: iinc #6, 1
    //   257: goto -> 140
    //   260: aload #5
    //   262: aload #9
    //   264: aload #12
    //   266: invokevirtual addPropertyCreator : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedWithParams;[Lcom/flurry/org/codehaus/jackson/map/deser/impl/CreatorProperty;)V
    //   269: goto -> 11
    //   272: return
  }
  
  protected JsonDeserializer _findCustomArrayDeserializer(ArrayType paramArrayType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   4: invokevirtual deserializers : ()Ljava/lang/Iterable;
    //   7: invokeinterface iterator : ()Ljava/util/Iterator;
    //   12: astore #8
    //   14: aload #8
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 60
    //   24: aload #8
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/flurry/org/codehaus/jackson/map/Deserializers
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: aload #4
    //   39: aload #5
    //   41: aload #6
    //   43: invokeinterface findArrayDeserializer : (Lcom/flurry/org/codehaus/jackson/map/type/ArrayType;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;Lcom/flurry/org/codehaus/jackson/map/TypeDeserializer;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   48: astore #7
    //   50: aload #7
    //   52: ifnull -> 14
    //   55: aload #7
    //   57: astore_1
    //   58: aload_1
    //   59: areturn
    //   60: aconst_null
    //   61: astore_1
    //   62: goto -> 58
  }
  
  protected JsonDeserializer _findCustomBeanDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   4: invokevirtual deserializers : ()Ljava/lang/Iterable;
    //   7: invokeinterface iterator : ()Ljava/util/Iterator;
    //   12: astore #7
    //   14: aload #7
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 58
    //   24: aload #7
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/flurry/org/codehaus/jackson/map/Deserializers
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: aload #4
    //   39: aload #5
    //   41: invokeinterface findBeanDeserializer : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;Lcom/flurry/org/codehaus/jackson/map/BeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   46: astore #6
    //   48: aload #6
    //   50: ifnull -> 14
    //   53: aload #6
    //   55: astore_1
    //   56: aload_1
    //   57: areturn
    //   58: aconst_null
    //   59: astore_1
    //   60: goto -> 56
  }
  
  protected JsonDeserializer _findCustomCollectionDeserializer(CollectionType paramCollectionType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   4: invokevirtual deserializers : ()Ljava/lang/Iterable;
    //   7: invokeinterface iterator : ()Ljava/util/Iterator;
    //   12: astore #9
    //   14: aload #9
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 62
    //   24: aload #9
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/flurry/org/codehaus/jackson/map/Deserializers
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: aload #4
    //   39: aload #5
    //   41: aload #6
    //   43: aload #7
    //   45: invokeinterface findCollectionDeserializer : (Lcom/flurry/org/codehaus/jackson/map/type/CollectionType;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;Lcom/flurry/org/codehaus/jackson/map/BeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;Lcom/flurry/org/codehaus/jackson/map/TypeDeserializer;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   50: astore #8
    //   52: aload #8
    //   54: ifnull -> 14
    //   57: aload #8
    //   59: astore_1
    //   60: aload_1
    //   61: areturn
    //   62: aconst_null
    //   63: astore_1
    //   64: goto -> 60
  }
  
  protected JsonDeserializer _findCustomCollectionLikeDeserializer(CollectionLikeType paramCollectionLikeType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   4: invokevirtual deserializers : ()Ljava/lang/Iterable;
    //   7: invokeinterface iterator : ()Ljava/util/Iterator;
    //   12: astore #9
    //   14: aload #9
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 62
    //   24: aload #9
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/flurry/org/codehaus/jackson/map/Deserializers
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: aload #4
    //   39: aload #5
    //   41: aload #6
    //   43: aload #7
    //   45: invokeinterface findCollectionLikeDeserializer : (Lcom/flurry/org/codehaus/jackson/map/type/CollectionLikeType;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;Lcom/flurry/org/codehaus/jackson/map/BeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;Lcom/flurry/org/codehaus/jackson/map/TypeDeserializer;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   50: astore #8
    //   52: aload #8
    //   54: ifnull -> 14
    //   57: aload #8
    //   59: astore_1
    //   60: aload_1
    //   61: areturn
    //   62: aconst_null
    //   63: astore_1
    //   64: goto -> 60
  }
  
  protected JsonDeserializer _findCustomEnumDeserializer(Class paramClass, DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   4: invokevirtual deserializers : ()Ljava/lang/Iterable;
    //   7: invokeinterface iterator : ()Ljava/util/Iterator;
    //   12: astore #6
    //   14: aload #6
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 56
    //   24: aload #6
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/flurry/org/codehaus/jackson/map/Deserializers
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: aload #4
    //   39: invokeinterface findEnumDeserializer : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/BeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   44: astore #5
    //   46: aload #5
    //   48: ifnull -> 14
    //   51: aload #5
    //   53: astore_1
    //   54: aload_1
    //   55: areturn
    //   56: aconst_null
    //   57: astore_1
    //   58: goto -> 54
  }
  
  protected JsonDeserializer _findCustomMapDeserializer(MapType paramMapType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   4: invokevirtual deserializers : ()Ljava/lang/Iterable;
    //   7: invokeinterface iterator : ()Ljava/util/Iterator;
    //   12: astore #10
    //   14: aload #10
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 64
    //   24: aload #10
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/flurry/org/codehaus/jackson/map/Deserializers
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: aload #4
    //   39: aload #5
    //   41: aload #6
    //   43: aload #7
    //   45: aload #8
    //   47: invokeinterface findMapDeserializer : (Lcom/flurry/org/codehaus/jackson/map/type/MapType;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;Lcom/flurry/org/codehaus/jackson/map/BeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;Lcom/flurry/org/codehaus/jackson/map/KeyDeserializer;Lcom/flurry/org/codehaus/jackson/map/TypeDeserializer;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   52: astore #9
    //   54: aload #9
    //   56: ifnull -> 14
    //   59: aload #9
    //   61: astore_1
    //   62: aload_1
    //   63: areturn
    //   64: aconst_null
    //   65: astore_1
    //   66: goto -> 62
  }
  
  protected JsonDeserializer _findCustomMapLikeDeserializer(MapLikeType paramMapLikeType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   4: invokevirtual deserializers : ()Ljava/lang/Iterable;
    //   7: invokeinterface iterator : ()Ljava/util/Iterator;
    //   12: astore #10
    //   14: aload #10
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 64
    //   24: aload #10
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/flurry/org/codehaus/jackson/map/Deserializers
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: aload #4
    //   39: aload #5
    //   41: aload #6
    //   43: aload #7
    //   45: aload #8
    //   47: invokeinterface findMapLikeDeserializer : (Lcom/flurry/org/codehaus/jackson/map/type/MapLikeType;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;Lcom/flurry/org/codehaus/jackson/map/BeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;Lcom/flurry/org/codehaus/jackson/map/KeyDeserializer;Lcom/flurry/org/codehaus/jackson/map/TypeDeserializer;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   52: astore #9
    //   54: aload #9
    //   56: ifnull -> 14
    //   59: aload #9
    //   61: astore_1
    //   62: aload_1
    //   63: areturn
    //   64: aconst_null
    //   65: astore_1
    //   66: goto -> 62
  }
  
  protected JsonDeserializer _findCustomTreeNodeDeserializer(Class paramClass, DeserializationConfig paramDeserializationConfig, BeanProperty paramBeanProperty) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   4: invokevirtual deserializers : ()Ljava/lang/Iterable;
    //   7: invokeinterface iterator : ()Ljava/util/Iterator;
    //   12: astore #5
    //   14: aload #5
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 54
    //   24: aload #5
    //   26: invokeinterface next : ()Ljava/lang/Object;
    //   31: checkcast com/flurry/org/codehaus/jackson/map/Deserializers
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: invokeinterface findTreeNodeDeserializer : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   42: astore #4
    //   44: aload #4
    //   46: ifnull -> 14
    //   49: aload #4
    //   51: astore_1
    //   52: aload_1
    //   53: areturn
    //   54: aconst_null
    //   55: astore_1
    //   56: goto -> 52
  }
  
  protected boolean _handleSingleArgumentConstructor(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, VisibilityChecker paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, AnnotatedConstructor paramAnnotatedConstructor, boolean paramBoolean1, boolean paramBoolean2) {
    AnnotatedParameter annotatedParameter = paramAnnotatedConstructor.getParameter(0);
    String str = paramAnnotationIntrospector.findPropertyNameForParam(annotatedParameter);
    Object object = paramAnnotationIntrospector.findInjectableValueId((AnnotatedMember)annotatedParameter);
    if (object != null || (str != null && str.length() > 0)) {
      paramCreatorCollector.addPropertyCreator((AnnotatedWithParams)paramAnnotatedConstructor, new CreatorProperty[] { constructCreatorProperty(paramDeserializationConfig, paramBasicBeanDescription, str, 0, annotatedParameter, object) });
      return true;
    } 
    Class<String> clazz = paramAnnotatedConstructor.getParameterClass(0);
    if (clazz == String.class) {
      if (paramBoolean1 || paramBoolean2)
        paramCreatorCollector.addStringCreator((AnnotatedWithParams)paramAnnotatedConstructor); 
      return true;
    } 
    if (clazz == int.class || clazz == Integer.class) {
      if (paramBoolean1 || paramBoolean2)
        paramCreatorCollector.addIntCreator((AnnotatedWithParams)paramAnnotatedConstructor); 
      return true;
    } 
    if (clazz == long.class || clazz == Long.class) {
      if (paramBoolean1 || paramBoolean2)
        paramCreatorCollector.addLongCreator((AnnotatedWithParams)paramAnnotatedConstructor); 
      return true;
    } 
    if (clazz == double.class || clazz == Double.class) {
      if (paramBoolean1 || paramBoolean2)
        paramCreatorCollector.addDoubleCreator((AnnotatedWithParams)paramAnnotatedConstructor); 
      return true;
    } 
    if (paramBoolean1) {
      paramCreatorCollector.addDelegatingCreator((AnnotatedWithParams)paramAnnotatedConstructor);
      return true;
    } 
    return false;
  }
  
  protected boolean _handleSingleArgumentFactory(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, VisibilityChecker paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean) {
    boolean bool = true;
    Class<String> clazz = paramAnnotatedMethod.getParameterClass(0);
    if (clazz == String.class) {
      if (!paramBoolean) {
        paramBoolean = bool;
        if (paramVisibilityChecker.isCreatorVisible((AnnotatedMember)paramAnnotatedMethod)) {
          paramCreatorCollector.addStringCreator((AnnotatedWithParams)paramAnnotatedMethod);
          return bool;
        } 
        return paramBoolean;
      } 
    } else {
      if (clazz == int.class || clazz == Integer.class) {
        if (!paramBoolean) {
          paramBoolean = bool;
          if (paramVisibilityChecker.isCreatorVisible((AnnotatedMember)paramAnnotatedMethod)) {
            paramCreatorCollector.addIntCreator((AnnotatedWithParams)paramAnnotatedMethod);
            paramBoolean = bool;
          } 
          return paramBoolean;
        } 
      } else {
        if (clazz == long.class || clazz == Long.class) {
          if (!paramBoolean) {
            paramBoolean = bool;
            if (paramVisibilityChecker.isCreatorVisible((AnnotatedMember)paramAnnotatedMethod)) {
              paramCreatorCollector.addLongCreator((AnnotatedWithParams)paramAnnotatedMethod);
              paramBoolean = bool;
            } 
            return paramBoolean;
          } 
        } else {
          if (clazz == double.class || clazz == Double.class) {
            if (!paramBoolean) {
              paramBoolean = bool;
              if (paramVisibilityChecker.isCreatorVisible((AnnotatedMember)paramAnnotatedMethod)) {
                paramCreatorCollector.addDoubleCreator((AnnotatedWithParams)paramAnnotatedMethod);
                paramBoolean = bool;
              } 
              return paramBoolean;
            } 
          } else {
            if (clazz == boolean.class || clazz == Boolean.class) {
              if (!paramBoolean) {
                paramBoolean = bool;
                if (paramVisibilityChecker.isCreatorVisible((AnnotatedMember)paramAnnotatedMethod)) {
                  paramCreatorCollector.addBooleanCreator((AnnotatedWithParams)paramAnnotatedMethod);
                  paramBoolean = bool;
                } 
                return paramBoolean;
              } 
            } else {
              if (paramAnnotationIntrospector.hasCreatorAnnotation((Annotated)paramAnnotatedMethod)) {
                paramCreatorCollector.addDelegatingCreator((AnnotatedWithParams)paramAnnotatedMethod);
                return bool;
              } 
              return false;
            } 
            paramCreatorCollector.addBooleanCreator((AnnotatedWithParams)paramAnnotatedMethod);
            paramBoolean = bool;
          } 
          paramCreatorCollector.addDoubleCreator((AnnotatedWithParams)paramAnnotatedMethod);
          paramBoolean = bool;
        } 
        paramCreatorCollector.addLongCreator((AnnotatedWithParams)paramAnnotatedMethod);
        paramBoolean = bool;
      } 
      paramCreatorCollector.addIntCreator((AnnotatedWithParams)paramAnnotatedMethod);
      paramBoolean = bool;
    } 
    paramCreatorCollector.addStringCreator((AnnotatedWithParams)paramAnnotatedMethod);
    return bool;
  }
  
  protected JavaType _mapAbstractType2(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   4: astore #4
    //   6: aload_0
    //   7: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   10: invokevirtual hasAbstractTypeResolvers : ()Z
    //   13: ifeq -> 73
    //   16: aload_0
    //   17: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   20: invokevirtual abstractTypeResolvers : ()Ljava/lang/Iterable;
    //   23: invokeinterface iterator : ()Ljava/util/Iterator;
    //   28: astore #5
    //   30: aload #5
    //   32: invokeinterface hasNext : ()Z
    //   37: ifeq -> 73
    //   40: aload #5
    //   42: invokeinterface next : ()Ljava/lang/Object;
    //   47: checkcast com/flurry/org/codehaus/jackson/map/AbstractTypeResolver
    //   50: aload_1
    //   51: aload_2
    //   52: invokevirtual findTypeMapping : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   55: astore_3
    //   56: aload_3
    //   57: ifnull -> 30
    //   60: aload_3
    //   61: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   64: aload #4
    //   66: if_acmpeq -> 30
    //   69: aload_3
    //   70: astore_1
    //   71: aload_1
    //   72: areturn
    //   73: aconst_null
    //   74: astore_1
    //   75: goto -> 71
  }
  
  protected void addBeanProps(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder) {
    Set set;
    List list = paramBasicBeanDescription.findProperties();
    AnnotationIntrospector annotationIntrospector = paramDeserializationConfig.getAnnotationIntrospector();
    Boolean bool = annotationIntrospector.findIgnoreUnknownProperties(paramBasicBeanDescription.getClassInfo());
    if (bool != null)
      paramBeanDeserializerBuilder.setIgnoreUnknownProperties(bool.booleanValue()); 
    HashSet hashSet = ArrayBuilders.arrayToSet((Object[])annotationIntrospector.findPropertiesToIgnore(paramBasicBeanDescription.getClassInfo()));
    Iterator<String> iterator = hashSet.iterator();
    while (iterator.hasNext())
      paramBeanDeserializerBuilder.addIgnorable(iterator.next()); 
    AnnotatedMethod annotatedMethod = paramBasicBeanDescription.findAnySetter();
    if (annotatedMethod == null) {
      set = paramBasicBeanDescription.getIgnoredPropertyNames();
    } else {
      set = paramBasicBeanDescription.getIgnoredPropertyNamesForDeser();
    } 
    if (set != null) {
      Iterator<String> iterator1 = set.iterator();
      while (iterator1.hasNext())
        paramBeanDeserializerBuilder.addIgnorable(iterator1.next()); 
    } 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    for (BeanPropertyDefinition beanPropertyDefinition : list) {
      String str = beanPropertyDefinition.getName();
      if (!hashSet.contains(str)) {
        SettableBeanProperty settableBeanProperty;
        AnnotatedMethod annotatedMethod1;
        if (beanPropertyDefinition.hasConstructorParameter()) {
          paramBeanDeserializerBuilder.addCreatorProperty(beanPropertyDefinition);
          continue;
        } 
        if (beanPropertyDefinition.hasSetter()) {
          annotatedMethod1 = beanPropertyDefinition.getSetter();
          if (isIgnorableType(paramDeserializationConfig, paramBasicBeanDescription, annotatedMethod1.getParameterClass(0), hashMap)) {
            paramBeanDeserializerBuilder.addIgnorable(str);
            continue;
          } 
          settableBeanProperty = constructSettableProperty(paramDeserializationConfig, paramBasicBeanDescription, str, annotatedMethod1);
          if (settableBeanProperty != null)
            paramBeanDeserializerBuilder.addProperty(settableBeanProperty); 
          continue;
        } 
        if (annotatedMethod1.hasField()) {
          AnnotatedField annotatedField = annotatedMethod1.getField();
          if (isIgnorableType(paramDeserializationConfig, paramBasicBeanDescription, annotatedField.getRawType(), hashMap)) {
            paramBeanDeserializerBuilder.addIgnorable((String)settableBeanProperty);
            continue;
          } 
          settableBeanProperty = constructSettableProperty(paramDeserializationConfig, paramBasicBeanDescription, (String)settableBeanProperty, annotatedField);
          if (settableBeanProperty != null)
            paramBeanDeserializerBuilder.addProperty(settableBeanProperty); 
        } 
      } 
    } 
    if (annotatedMethod != null)
      paramBeanDeserializerBuilder.setAnySetter(constructAnySetter(paramDeserializationConfig, paramBasicBeanDescription, annotatedMethod)); 
    if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS))
      for (BeanPropertyDefinition beanPropertyDefinition : list) {
        if (beanPropertyDefinition.hasGetter()) {
          String str = beanPropertyDefinition.getName();
          if (!paramBeanDeserializerBuilder.hasProperty(str) && !hashSet.contains(str)) {
            AnnotatedMethod annotatedMethod1 = beanPropertyDefinition.getGetter();
            Class<?> clazz = annotatedMethod1.getRawType();
            if ((Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) && !hashSet.contains(str) && !paramBeanDeserializerBuilder.hasProperty(str))
              paramBeanDeserializerBuilder.addProperty(constructSetterlessProperty(paramDeserializationConfig, paramBasicBeanDescription, str, annotatedMethod1)); 
          } 
        } 
      }  
  }
  
  protected void addInjectables(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder) {
    Map map = paramBasicBeanDescription.findInjectables();
    if (map != null) {
      boolean bool = paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
      for (Map.Entry entry : map.entrySet()) {
        AnnotatedMember annotatedMember = (AnnotatedMember)entry.getValue();
        if (bool)
          annotatedMember.fixAccess(); 
        paramBeanDeserializerBuilder.addInjectable(annotatedMember.getName(), paramBasicBeanDescription.resolveType(annotatedMember.getGenericType()), paramBasicBeanDescription.getClassAnnotations(), annotatedMember, entry.getKey());
      } 
    } 
  }
  
  protected void addReferenceProperties(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder) {
    Map map = paramBasicBeanDescription.findBackReferenceProperties();
    if (map != null)
      for (Map.Entry entry : map.entrySet()) {
        String str = (String)entry.getKey();
        AnnotatedMember annotatedMember = (AnnotatedMember)entry.getValue();
        if (annotatedMember instanceof AnnotatedMethod) {
          paramBeanDeserializerBuilder.addBackReferenceProperty(str, constructSettableProperty(paramDeserializationConfig, paramBasicBeanDescription, annotatedMember.getName(), (AnnotatedMethod)annotatedMember));
          continue;
        } 
        paramBeanDeserializerBuilder.addBackReferenceProperty(str, constructSettableProperty(paramDeserializationConfig, paramBasicBeanDescription, annotatedMember.getName(), (AnnotatedField)annotatedMember));
      }  
  }
  
  public JsonDeserializer buildBeanDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty) {
    BeanDeserializerBuilder beanDeserializerBuilder2;
    ValueInstantiator valueInstantiator = findValueInstantiator(paramDeserializationConfig, paramBasicBeanDescription);
    if (paramJavaType.isAbstract() && !valueInstantiator.canInstantiate())
      return new AbstractDeserializer(paramJavaType); 
    BeanDeserializerBuilder beanDeserializerBuilder1 = constructBeanDeserializerBuilder(paramBasicBeanDescription);
    beanDeserializerBuilder1.setValueInstantiator(valueInstantiator);
    addBeanProps(paramDeserializationConfig, paramBasicBeanDescription, beanDeserializerBuilder1);
    addReferenceProperties(paramDeserializationConfig, paramBasicBeanDescription, beanDeserializerBuilder1);
    addInjectables(paramDeserializationConfig, paramBasicBeanDescription, beanDeserializerBuilder1);
    if (this._factoryConfig.hasDeserializerModifiers()) {
      Iterator<BeanDeserializerModifier> iterator = this._factoryConfig.deserializerModifiers().iterator();
      while (true) {
        beanDeserializerBuilder2 = beanDeserializerBuilder1;
        if (iterator.hasNext()) {
          beanDeserializerBuilder1 = ((BeanDeserializerModifier)iterator.next()).updateBuilder(paramDeserializationConfig, paramBasicBeanDescription, beanDeserializerBuilder1);
          continue;
        } 
        break;
      } 
    } else {
      beanDeserializerBuilder2 = beanDeserializerBuilder1;
    } 
    JsonDeserializer jsonDeserializer = beanDeserializerBuilder2.build(paramBeanProperty);
    if (this._factoryConfig.hasDeserializerModifiers()) {
      Iterator<BeanDeserializerModifier> iterator = this._factoryConfig.deserializerModifiers().iterator();
      JsonDeserializer jsonDeserializer1 = jsonDeserializer;
      while (true) {
        jsonDeserializer = jsonDeserializer1;
        if (iterator.hasNext()) {
          jsonDeserializer1 = ((BeanDeserializerModifier)iterator.next()).modifyDeserializer(paramDeserializationConfig, paramBasicBeanDescription, jsonDeserializer1);
          continue;
        } 
        return jsonDeserializer;
      } 
    } 
    return jsonDeserializer;
  }
  
  public JsonDeserializer buildThrowableDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty) {
    ThrowableDeserializer throwableDeserializer;
    JsonDeserializer jsonDeserializer1;
    BeanDeserializerBuilder beanDeserializerBuilder2;
    BeanDeserializerBuilder beanDeserializerBuilder1 = constructBeanDeserializerBuilder(paramBasicBeanDescription);
    beanDeserializerBuilder1.setValueInstantiator(findValueInstantiator(paramDeserializationConfig, paramBasicBeanDescription));
    addBeanProps(paramDeserializationConfig, paramBasicBeanDescription, beanDeserializerBuilder1);
    AnnotatedMethod annotatedMethod = paramBasicBeanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
    if (annotatedMethod != null) {
      SettableBeanProperty settableBeanProperty = constructSettableProperty(paramDeserializationConfig, paramBasicBeanDescription, "cause", annotatedMethod);
      if (settableBeanProperty != null)
        beanDeserializerBuilder1.addOrReplaceProperty(settableBeanProperty, true); 
    } 
    beanDeserializerBuilder1.addIgnorable("localizedMessage");
    beanDeserializerBuilder1.addIgnorable("message");
    if (this._factoryConfig.hasDeserializerModifiers()) {
      Iterator<BeanDeserializerModifier> iterator = this._factoryConfig.deserializerModifiers().iterator();
      while (true) {
        beanDeserializerBuilder2 = beanDeserializerBuilder1;
        if (iterator.hasNext()) {
          beanDeserializerBuilder1 = ((BeanDeserializerModifier)iterator.next()).updateBuilder(paramDeserializationConfig, paramBasicBeanDescription, beanDeserializerBuilder1);
          continue;
        } 
        break;
      } 
    } else {
      beanDeserializerBuilder2 = beanDeserializerBuilder1;
    } 
    JsonDeserializer jsonDeserializer3 = beanDeserializerBuilder2.build(paramBeanProperty);
    JsonDeserializer jsonDeserializer2 = jsonDeserializer3;
    if (jsonDeserializer3 instanceof BeanDeserializer)
      throwableDeserializer = new ThrowableDeserializer((BeanDeserializer)jsonDeserializer3); 
    if (this._factoryConfig.hasDeserializerModifiers()) {
      Iterator<BeanDeserializerModifier> iterator = this._factoryConfig.deserializerModifiers().iterator();
      while (true) {
        ThrowableDeserializer throwableDeserializer1 = throwableDeserializer;
        if (iterator.hasNext()) {
          jsonDeserializer1 = ((BeanDeserializerModifier)iterator.next()).modifyDeserializer(paramDeserializationConfig, paramBasicBeanDescription, (JsonDeserializer)throwableDeserializer);
          continue;
        } 
        break;
      } 
    } else {
      jsonDeserializer3 = jsonDeserializer1;
    } 
    return jsonDeserializer3;
  }
  
  protected SettableAnyProperty constructAnySetter(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, AnnotatedMethod paramAnnotatedMethod) {
    if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
      paramAnnotatedMethod.fixAccess(); 
    JavaType javaType = paramBasicBeanDescription.bindingsForBeanType().resolveType(paramAnnotatedMethod.getParameterType(1));
    BeanProperty.Std std = new BeanProperty.Std(paramAnnotatedMethod.getName(), javaType, paramBasicBeanDescription.getClassAnnotations(), (AnnotatedMember)paramAnnotatedMethod);
    javaType = resolveType(paramDeserializationConfig, paramBasicBeanDescription, javaType, (AnnotatedMember)paramAnnotatedMethod, (BeanProperty)std);
    JsonDeserializer jsonDeserializer = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedMethod, (BeanProperty)std);
    return (jsonDeserializer != null) ? new SettableAnyProperty((BeanProperty)std, paramAnnotatedMethod, javaType, jsonDeserializer) : new SettableAnyProperty((BeanProperty)std, paramAnnotatedMethod, modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedMethod, javaType, std.getName()), null);
  }
  
  protected BeanDeserializerBuilder constructBeanDeserializerBuilder(BasicBeanDescription paramBasicBeanDescription) {
    return new BeanDeserializerBuilder(paramBasicBeanDescription);
  }
  
  protected CreatorProperty constructCreatorProperty(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, String paramString, int paramInt, AnnotatedParameter paramAnnotatedParameter, Object paramObject) {
    TypeDeserializer typeDeserializer1;
    JavaType javaType1 = paramDeserializationConfig.getTypeFactory().constructType(paramAnnotatedParameter.getParameterType(), paramBasicBeanDescription.bindingsForBeanType());
    BeanProperty.Std std2 = new BeanProperty.Std(paramString, javaType1, paramBasicBeanDescription.getClassAnnotations(), (AnnotatedMember)paramAnnotatedParameter);
    JavaType javaType2 = resolveType(paramDeserializationConfig, paramBasicBeanDescription, javaType1, (AnnotatedMember)paramAnnotatedParameter, (BeanProperty)std2);
    BeanProperty.Std std1 = std2;
    if (javaType2 != javaType1)
      std1 = std2.withType(javaType2); 
    JsonDeserializer jsonDeserializer = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedParameter, (BeanProperty)std1);
    javaType2 = modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedParameter, javaType2, paramString);
    TypeDeserializer typeDeserializer2 = (TypeDeserializer)javaType2.getTypeHandler();
    if (typeDeserializer2 == null) {
      typeDeserializer1 = findTypeDeserializer(paramDeserializationConfig, javaType2, (BeanProperty)std1);
    } else {
      typeDeserializer1 = typeDeserializer2;
    } 
    CreatorProperty creatorProperty2 = new CreatorProperty(paramString, javaType2, typeDeserializer1, paramBasicBeanDescription.getClassAnnotations(), paramAnnotatedParameter, paramInt, paramObject);
    CreatorProperty creatorProperty1 = creatorProperty2;
    if (jsonDeserializer != null)
      creatorProperty1 = creatorProperty2.withValueDeserializer(jsonDeserializer); 
    return creatorProperty1;
  }
  
  protected ValueInstantiator constructDefaultValueInstantiator(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription) {
    boolean bool = paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    CreatorCollector creatorCollector = new CreatorCollector(paramBasicBeanDescription, bool);
    AnnotationIntrospector annotationIntrospector = paramDeserializationConfig.getAnnotationIntrospector();
    if (paramBasicBeanDescription.getType().isConcrete()) {
      AnnotatedConstructor annotatedConstructor = paramBasicBeanDescription.findDefaultConstructor();
      if (annotatedConstructor != null) {
        if (bool)
          ClassUtil.checkAndFixAccess(annotatedConstructor.getAnnotated()); 
        creatorCollector.setDefaultConstructor(annotatedConstructor);
      } 
    } 
    VisibilityChecker visibilityChecker = paramDeserializationConfig.getDefaultVisibilityChecker();
    visibilityChecker = paramDeserializationConfig.getAnnotationIntrospector().findAutoDetectVisibility(paramBasicBeanDescription.getClassInfo(), visibilityChecker);
    _addDeserializerFactoryMethods(paramDeserializationConfig, paramBasicBeanDescription, visibilityChecker, annotationIntrospector, creatorCollector);
    _addDeserializerConstructors(paramDeserializationConfig, paramBasicBeanDescription, visibilityChecker, annotationIntrospector, creatorCollector);
    return creatorCollector.constructValueInstantiator(paramDeserializationConfig);
  }
  
  protected SettableBeanProperty constructSettableProperty(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, String paramString, AnnotatedField paramAnnotatedField) {
    if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
      paramAnnotatedField.fixAccess(); 
    JavaType javaType3 = paramBasicBeanDescription.bindingsForBeanType().resolveType(paramAnnotatedField.getGenericType());
    BeanProperty.Std std2 = new BeanProperty.Std(paramString, javaType3, paramBasicBeanDescription.getClassAnnotations(), (AnnotatedMember)paramAnnotatedField);
    JavaType javaType2 = resolveType(paramDeserializationConfig, paramBasicBeanDescription, javaType3, (AnnotatedMember)paramAnnotatedField, (BeanProperty)std2);
    BeanProperty.Std std1 = std2;
    if (javaType2 != javaType3)
      std1 = std2.withType(javaType2); 
    JsonDeserializer jsonDeserializer = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedField, (BeanProperty)std1);
    JavaType javaType1 = modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedField, javaType2, paramString);
    SettableBeanProperty$FieldProperty settableBeanProperty$FieldProperty = new SettableBeanProperty$FieldProperty(paramString, javaType1, (TypeDeserializer)javaType1.getTypeHandler(), paramBasicBeanDescription.getClassAnnotations(), paramAnnotatedField);
    SettableBeanProperty settableBeanProperty = settableBeanProperty$FieldProperty;
    if (jsonDeserializer != null)
      settableBeanProperty = settableBeanProperty$FieldProperty.withValueDeserializer(jsonDeserializer); 
    AnnotationIntrospector.ReferenceProperty referenceProperty = paramDeserializationConfig.getAnnotationIntrospector().findReferenceType((AnnotatedMember)paramAnnotatedField);
    if (referenceProperty != null && referenceProperty.isManagedReference())
      settableBeanProperty.setManagedReferenceName(referenceProperty.getName()); 
    return settableBeanProperty;
  }
  
  protected SettableBeanProperty constructSettableProperty(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, String paramString, AnnotatedMethod paramAnnotatedMethod) {
    if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
      paramAnnotatedMethod.fixAccess(); 
    JavaType javaType3 = paramBasicBeanDescription.bindingsForBeanType().resolveType(paramAnnotatedMethod.getParameterType(0));
    BeanProperty.Std std2 = new BeanProperty.Std(paramString, javaType3, paramBasicBeanDescription.getClassAnnotations(), (AnnotatedMember)paramAnnotatedMethod);
    JavaType javaType2 = resolveType(paramDeserializationConfig, paramBasicBeanDescription, javaType3, (AnnotatedMember)paramAnnotatedMethod, (BeanProperty)std2);
    BeanProperty.Std std1 = std2;
    if (javaType2 != javaType3)
      std1 = std2.withType(javaType2); 
    JsonDeserializer jsonDeserializer = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedMethod, (BeanProperty)std1);
    JavaType javaType1 = modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedMethod, javaType2, paramString);
    SettableBeanProperty$MethodProperty settableBeanProperty$MethodProperty = new SettableBeanProperty$MethodProperty(paramString, javaType1, (TypeDeserializer)javaType1.getTypeHandler(), paramBasicBeanDescription.getClassAnnotations(), paramAnnotatedMethod);
    SettableBeanProperty settableBeanProperty = settableBeanProperty$MethodProperty;
    if (jsonDeserializer != null)
      settableBeanProperty = settableBeanProperty$MethodProperty.withValueDeserializer(jsonDeserializer); 
    AnnotationIntrospector.ReferenceProperty referenceProperty = paramDeserializationConfig.getAnnotationIntrospector().findReferenceType((AnnotatedMember)paramAnnotatedMethod);
    if (referenceProperty != null && referenceProperty.isManagedReference())
      settableBeanProperty.setManagedReferenceName(referenceProperty.getName()); 
    return settableBeanProperty;
  }
  
  protected SettableBeanProperty constructSetterlessProperty(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, String paramString, AnnotatedMethod paramAnnotatedMethod) {
    if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
      paramAnnotatedMethod.fixAccess(); 
    JavaType javaType2 = paramAnnotatedMethod.getType(paramBasicBeanDescription.bindingsForBeanType());
    JsonDeserializer jsonDeserializer = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedMethod, (BeanProperty)new BeanProperty.Std(paramString, javaType2, paramBasicBeanDescription.getClassAnnotations(), (AnnotatedMember)paramAnnotatedMethod));
    JavaType javaType1 = modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)paramAnnotatedMethod, javaType2, paramString);
    SettableBeanProperty$SetterlessProperty settableBeanProperty$SetterlessProperty = new SettableBeanProperty$SetterlessProperty(paramString, javaType1, (TypeDeserializer)javaType1.getTypeHandler(), paramBasicBeanDescription.getClassAnnotations(), paramAnnotatedMethod);
    SettableBeanProperty settableBeanProperty = settableBeanProperty$SetterlessProperty;
    if (jsonDeserializer != null)
      settableBeanProperty = settableBeanProperty$SetterlessProperty.withValueDeserializer(jsonDeserializer); 
    return settableBeanProperty;
  }
  
  public JsonDeserializer createBeanDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    JavaType javaType = paramJavaType;
    if (paramJavaType.isAbstract())
      javaType = mapAbstractType(paramDeserializationConfig, paramJavaType); 
    BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramDeserializationConfig.introspect(javaType);
    JsonDeserializer jsonDeserializer = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)basicBeanDescription.getClassInfo(), paramBeanProperty);
    if (jsonDeserializer == null) {
      JavaType javaType1 = modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)basicBeanDescription.getClassInfo(), javaType, null);
      if (javaType1.getRawClass() != javaType.getRawClass()) {
        basicBeanDescription = (BasicBeanDescription)paramDeserializationConfig.introspect(javaType1);
        javaType = javaType1;
      } 
      JsonDeserializer jsonDeserializer1 = _findCustomBeanDeserializer(javaType, paramDeserializationConfig, paramDeserializerProvider, basicBeanDescription, paramBeanProperty);
      jsonDeserializer = jsonDeserializer1;
      if (jsonDeserializer1 == null) {
        if (javaType.isThrowable())
          return buildThrowableDeserializer(paramDeserializationConfig, javaType, basicBeanDescription, paramBeanProperty); 
        if (javaType.isAbstract()) {
          JavaType javaType2 = materializeAbstractType(paramDeserializationConfig, basicBeanDescription);
          if (javaType2 != null)
            return buildBeanDeserializer(paramDeserializationConfig, javaType2, (BasicBeanDescription)paramDeserializationConfig.introspect(javaType2), paramBeanProperty); 
        } 
        JsonDeserializer jsonDeserializer2 = findStdBeanDeserializer(paramDeserializationConfig, paramDeserializerProvider, javaType, paramBeanProperty);
        jsonDeserializer = jsonDeserializer2;
        if (jsonDeserializer2 == null) {
          if (!isPotentialBeanType(javaType.getRawClass()))
            return null; 
          jsonDeserializer = buildBeanDeserializer(paramDeserializationConfig, javaType, basicBeanDescription, paramBeanProperty);
        } 
      } 
    } 
    return jsonDeserializer;
  }
  
  public KeyDeserializer createKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    if (this._factoryConfig.hasKeyDeserializers()) {
      BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramDeserializationConfig.introspectClassAnnotations(paramJavaType.getRawClass());
      Iterator<KeyDeserializers> iterator = this._factoryConfig.keyDeserializers().iterator();
      while (iterator.hasNext()) {
        KeyDeserializer keyDeserializer = ((KeyDeserializers)iterator.next()).findKeyDeserializer(paramJavaType, paramDeserializationConfig, (BeanDescription)basicBeanDescription, paramBeanProperty);
        if (keyDeserializer != null)
          return keyDeserializer; 
      } 
    } 
    Class<String> clazz = paramJavaType.getRawClass();
    if (clazz == String.class || clazz == Object.class)
      return StdKeyDeserializers.constructStringKeyDeserializer(paramDeserializationConfig, paramJavaType); 
    KeyDeserializer keyDeserializer2 = (KeyDeserializer)_keyDeserializers.get(paramJavaType);
    KeyDeserializer keyDeserializer1 = keyDeserializer2;
    if (keyDeserializer2 == null) {
      if (paramJavaType.isEnumType())
        return _createEnumKeyDeserializer(paramDeserializationConfig, paramJavaType, paramBeanProperty); 
      keyDeserializer1 = StdKeyDeserializers.findStringBasedKeyDeserializer(paramDeserializationConfig, paramJavaType);
    } 
    return keyDeserializer1;
  }
  
  public ValueInstantiator findValueInstantiator(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription) {
    Object object1;
    AnnotatedClass annotatedClass = paramBasicBeanDescription.getClassInfo();
    Object object2 = paramDeserializationConfig.getAnnotationIntrospector().findValueInstantiator(annotatedClass);
    if (object2 != null) {
      if (object2 instanceof ValueInstantiator) {
        object1 = object2;
      } else {
        if (!(object2 instanceof Class))
          throw new IllegalStateException("Invalid value instantiator returned for type " + paramBasicBeanDescription + ": neither a Class nor ValueInstantiator"); 
        object2 = object2;
        if (!ValueInstantiator.class.isAssignableFrom((Class<?>)object2))
          throw new IllegalStateException("Invalid instantiator Class<?> returned for type " + paramBasicBeanDescription + ": " + object2.getName() + " not a ValueInstantiator"); 
        object1 = paramDeserializationConfig.valueInstantiatorInstance((Annotated)object1, (Class)object2);
      } 
    } else {
      object1 = constructDefaultValueInstantiator(paramDeserializationConfig, paramBasicBeanDescription);
    } 
    if (this._factoryConfig.hasValueInstantiators()) {
      Iterator<ValueInstantiators> iterator = this._factoryConfig.valueInstantiators().iterator();
      while (true) {
        object2 = object1;
        if (iterator.hasNext()) {
          ValueInstantiators valueInstantiators = iterator.next();
          object2 = valueInstantiators.findValueInstantiator(paramDeserializationConfig, (BeanDescription)paramBasicBeanDescription, (ValueInstantiator)object1);
          object1 = object2;
          if (object2 == null)
            throw new JsonMappingException("Broken registered ValueInstantiators (of type " + valueInstantiators.getClass().getName() + "): returned null ValueInstantiator"); 
          continue;
        } 
        break;
      } 
    } else {
      object2 = object1;
    } 
    return (ValueInstantiator)object2;
  }
  
  public final DeserializerFactory.Config getConfig() {
    return this._factoryConfig;
  }
  
  protected boolean isIgnorableType(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, Class paramClass, Map paramMap) {
    Boolean bool2 = (Boolean)paramMap.get(paramClass);
    Boolean bool1 = bool2;
    if (bool2 == null) {
      BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramDeserializationConfig.introspectClassAnnotations(paramClass);
      Boolean bool = paramDeserializationConfig.getAnnotationIntrospector().isIgnorableType(basicBeanDescription.getClassInfo());
      bool1 = bool;
      if (bool == null)
        bool1 = Boolean.FALSE; 
    } 
    return bool1.booleanValue();
  }
  
  protected boolean isPotentialBeanType(Class paramClass) {
    String str = ClassUtil.canBeABeanType(paramClass);
    if (str != null)
      throw new IllegalArgumentException("Can not deserialize Class " + paramClass.getName() + " (of type " + str + ") as a Bean"); 
    if (ClassUtil.isProxyType(paramClass))
      throw new IllegalArgumentException("Can not deserialize Proxy class " + paramClass.getName() + " as a Bean"); 
    str = ClassUtil.isLocalType(paramClass, true);
    if (str != null)
      throw new IllegalArgumentException("Can not deserialize Class " + paramClass.getName() + " (of type " + str + ") as a Bean"); 
    return true;
  }
  
  public JavaType mapAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    while (true) {
      JavaType javaType = _mapAbstractType2(paramDeserializationConfig, paramJavaType);
      if (javaType == null)
        return paramJavaType; 
      Class clazz = paramJavaType.getRawClass();
      Class<?> clazz1 = javaType.getRawClass();
      if (clazz == clazz1 || !clazz.isAssignableFrom(clazz1))
        throw new IllegalArgumentException("Invalid abstract type resolution from " + paramJavaType + " to " + javaType + ": latter is not a subtype of former"); 
      paramJavaType = javaType;
    } 
  }
  
  protected JavaType materializeAbstractType(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual getType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   4: astore_3
    //   5: aload_0
    //   6: getfield _factoryConfig : Lcom/flurry/org/codehaus/jackson/map/DeserializerFactory$Config;
    //   9: invokevirtual abstractTypeResolvers : ()Ljava/lang/Iterable;
    //   12: invokeinterface iterator : ()Ljava/util/Iterator;
    //   17: astore #4
    //   19: aload #4
    //   21: invokeinterface hasNext : ()Z
    //   26: ifeq -> 53
    //   29: aload #4
    //   31: invokeinterface next : ()Ljava/lang/Object;
    //   36: checkcast com/flurry/org/codehaus/jackson/map/AbstractTypeResolver
    //   39: aload_1
    //   40: aload_3
    //   41: invokevirtual resolveAbstractType : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   44: astore_2
    //   45: aload_2
    //   46: ifnull -> 19
    //   49: aload_2
    //   50: astore_1
    //   51: aload_1
    //   52: areturn
    //   53: aconst_null
    //   54: astore_1
    //   55: goto -> 51
  }
  
  public DeserializerFactory withConfig(DeserializerFactory.Config paramConfig) {
    if (this._factoryConfig == paramConfig)
      return this; 
    if (getClass() != BeanDeserializerFactory.class)
      throw new IllegalStateException("Subtype of BeanDeserializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with " + "additional deserializer definitions"); 
    return new BeanDeserializerFactory(paramConfig);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\BeanDeserializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */