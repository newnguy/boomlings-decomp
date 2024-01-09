package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.type.TypeReference;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TypeFactory {
  private static final JavaType[] NO_TYPES;
  
  @Deprecated
  public static final TypeFactory instance = new TypeFactory();
  
  protected HierarchicType _cachedArrayListType;
  
  protected HierarchicType _cachedHashMapType;
  
  protected final TypeModifier[] _modifiers = null;
  
  protected final TypeParser _parser = new TypeParser(this);
  
  static {
    NO_TYPES = new JavaType[0];
  }
  
  private TypeFactory() {}
  
  protected TypeFactory(TypeParser paramTypeParser, TypeModifier[] paramArrayOfTypeModifier) {}
  
  private JavaType _collectionType(Class paramClass) {
    JavaType[] arrayOfJavaType = findTypeParameters(paramClass, Collection.class);
    if (arrayOfJavaType == null)
      return CollectionType.construct(paramClass, _unknownType()); 
    if (arrayOfJavaType.length != 1)
      throw new IllegalArgumentException("Strange Collection type " + paramClass.getName() + ": can not determine type parameters"); 
    return CollectionType.construct(paramClass, arrayOfJavaType[0]);
  }
  
  private JavaType _mapType(Class paramClass) {
    JavaType[] arrayOfJavaType = findTypeParameters(paramClass, Map.class);
    if (arrayOfJavaType == null)
      return MapType.construct(paramClass, _unknownType(), _unknownType()); 
    if (arrayOfJavaType.length != 2)
      throw new IllegalArgumentException("Strange Map type " + paramClass.getName() + ": can not determine type parameters"); 
    return MapType.construct(paramClass, arrayOfJavaType[0], arrayOfJavaType[1]);
  }
  
  @Deprecated
  public static JavaType arrayType(JavaType paramJavaType) {
    return instance.constructArrayType(paramJavaType);
  }
  
  @Deprecated
  public static JavaType arrayType(Class paramClass) {
    return instance.constructArrayType(instance.constructType(paramClass));
  }
  
  @Deprecated
  public static JavaType collectionType(Class paramClass, JavaType paramJavaType) {
    return instance.constructCollectionType(paramClass, paramJavaType);
  }
  
  @Deprecated
  public static JavaType collectionType(Class paramClass1, Class paramClass2) {
    return instance.constructCollectionType(paramClass1, instance.constructType(paramClass2));
  }
  
  public static TypeFactory defaultInstance() {
    return instance;
  }
  
  @Deprecated
  public static JavaType fastSimpleType(Class paramClass) {
    return instance.uncheckedSimpleType(paramClass);
  }
  
  @Deprecated
  public static JavaType[] findParameterTypes(JavaType paramJavaType, Class paramClass) {
    return instance.findTypeParameters(paramJavaType, paramClass);
  }
  
  @Deprecated
  public static JavaType[] findParameterTypes(Class paramClass1, Class paramClass2) {
    return instance.findTypeParameters(paramClass1, paramClass2);
  }
  
  @Deprecated
  public static JavaType[] findParameterTypes(Class paramClass1, Class paramClass2, TypeBindings paramTypeBindings) {
    return instance.findTypeParameters(paramClass1, paramClass2, paramTypeBindings);
  }
  
  public static JavaType fromCanonical(String paramString) {
    return instance.constructFromCanonical(paramString);
  }
  
  @Deprecated
  public static JavaType fromClass(Class paramClass) {
    return instance._fromClass(paramClass, null);
  }
  
  @Deprecated
  public static JavaType fromType(Type paramType) {
    return instance._constructType(paramType, null);
  }
  
  @Deprecated
  public static JavaType fromTypeReference(TypeReference paramTypeReference) {
    return type(paramTypeReference.getType());
  }
  
  @Deprecated
  public static JavaType mapType(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2) {
    return instance.constructMapType(paramClass, paramJavaType1, paramJavaType2);
  }
  
  @Deprecated
  public static JavaType mapType(Class paramClass1, Class paramClass2, Class paramClass3) {
    return instance.constructMapType(paramClass1, type(paramClass2), instance.constructType(paramClass3));
  }
  
  @Deprecated
  public static JavaType parametricType(Class paramClass, JavaType... paramVarArgs) {
    return instance.constructParametricType(paramClass, paramVarArgs);
  }
  
  @Deprecated
  public static JavaType parametricType(Class paramClass, Class... paramVarArgs) {
    return instance.constructParametricType(paramClass, paramVarArgs);
  }
  
  public static Class rawClass(Type paramType) {
    return (paramType instanceof Class) ? (Class)paramType : defaultInstance().constructType(paramType).getRawClass();
  }
  
  @Deprecated
  public static JavaType specialize(JavaType paramJavaType, Class paramClass) {
    return instance.constructSpecializedType(paramJavaType, paramClass);
  }
  
  @Deprecated
  public static JavaType type(TypeReference paramTypeReference) {
    return instance.constructType(paramTypeReference.getType());
  }
  
  @Deprecated
  public static JavaType type(Type paramType) {
    return instance._constructType(paramType, null);
  }
  
  @Deprecated
  public static JavaType type(Type paramType, TypeBindings paramTypeBindings) {
    return instance._constructType(paramType, paramTypeBindings);
  }
  
  @Deprecated
  public static JavaType type(Type paramType, JavaType paramJavaType) {
    return instance.constructType(paramType, paramJavaType);
  }
  
  @Deprecated
  public static JavaType type(Type paramType, Class paramClass) {
    return instance.constructType(paramType, paramClass);
  }
  
  public static JavaType unknownType() {
    return defaultInstance()._unknownType();
  }
  
  protected HierarchicType _arrayListSuperInterfaceChain(HierarchicType paramHierarchicType) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _cachedArrayListType : Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   6: ifnonnull -> 30
    //   9: aload_1
    //   10: invokevirtual deepCloneWithoutSubtype : ()Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   13: astore_2
    //   14: aload_0
    //   15: aload_2
    //   16: ldc java/util/List
    //   18: invokevirtual _doFindSuperInterfaceChain : (Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   21: pop
    //   22: aload_0
    //   23: aload_2
    //   24: invokevirtual getSuperType : ()Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   27: putfield _cachedArrayListType : Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   30: aload_0
    //   31: getfield _cachedArrayListType : Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   34: invokevirtual deepCloneWithoutSubtype : ()Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   37: astore_2
    //   38: aload_1
    //   39: aload_2
    //   40: invokevirtual setSuperType : (Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;)V
    //   43: aload_2
    //   44: aload_1
    //   45: invokevirtual setSubType : (Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;)V
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: areturn
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	52	finally
    //   30	48	52	finally
  }
  
  public JavaType _constructType(Type paramType, TypeBindings paramTypeBindings) {
    JavaType javaType1;
    if (paramType instanceof Class) {
      Class clazz = (Class)paramType;
      TypeBindings typeBindings = paramTypeBindings;
      if (paramTypeBindings == null)
        typeBindings = new TypeBindings(this, clazz); 
      JavaType javaType = _fromClass(clazz, typeBindings);
      paramTypeBindings = typeBindings;
      javaType1 = javaType;
    } else if (paramType instanceof ParameterizedType) {
      javaType1 = _fromParamType((ParameterizedType)paramType, paramTypeBindings);
    } else if (paramType instanceof GenericArrayType) {
      javaType1 = _fromArrayType((GenericArrayType)paramType, paramTypeBindings);
    } else if (paramType instanceof TypeVariable) {
      javaType1 = _fromVariable((TypeVariable)paramType, paramTypeBindings);
    } else if (paramType instanceof WildcardType) {
      javaType1 = _fromWildcard((WildcardType)paramType, paramTypeBindings);
    } else {
      throw new IllegalArgumentException("Unrecognized Type: " + paramType.toString());
    } 
    JavaType javaType2 = javaType1;
    if (this._modifiers != null) {
      javaType2 = javaType1;
      if (!javaType1.isContainerType()) {
        TypeModifier[] arrayOfTypeModifier = this._modifiers;
        int i = arrayOfTypeModifier.length;
        byte b = 0;
        while (true) {
          javaType2 = javaType1;
          if (b < i) {
            javaType1 = arrayOfTypeModifier[b].modifyType(javaType1, paramType, paramTypeBindings, this);
            b++;
            continue;
          } 
          break;
        } 
      } 
    } 
    return javaType2;
  }
  
  protected HierarchicType _doFindSuperInterfaceChain(HierarchicType paramHierarchicType, Class paramClass) {
    Class clazz = paramHierarchicType.getRawClass();
    Type[] arrayOfType = clazz.getGenericInterfaces();
    if (arrayOfType != null) {
      int i = arrayOfType.length;
      for (byte b = 0; b < i; b++) {
        HierarchicType hierarchicType = _findSuperInterfaceChain(arrayOfType[b], paramClass);
        if (hierarchicType != null) {
          hierarchicType.setSubType(paramHierarchicType);
          paramHierarchicType.setSuperType(hierarchicType);
          return paramHierarchicType;
        } 
      } 
    } 
    Type type = clazz.getGenericSuperclass();
    if (type != null) {
      HierarchicType hierarchicType = _findSuperInterfaceChain(type, paramClass);
      if (hierarchicType != null) {
        hierarchicType.setSubType(paramHierarchicType);
        paramHierarchicType.setSuperType(hierarchicType);
        return paramHierarchicType;
      } 
    } 
    return null;
  }
  
  protected HierarchicType _findSuperClassChain(Type paramType, Class paramClass) {
    HierarchicType hierarchicType = new HierarchicType(paramType);
    Class clazz = hierarchicType.getRawClass();
    if (clazz != paramClass) {
      Type type = clazz.getGenericSuperclass();
      if (type != null) {
        HierarchicType hierarchicType1 = _findSuperClassChain(type, paramClass);
        if (hierarchicType1 != null) {
          hierarchicType1.setSubType(hierarchicType);
          hierarchicType.setSuperType(hierarchicType1);
          return hierarchicType;
        } 
      } 
      hierarchicType = null;
    } 
    return hierarchicType;
  }
  
  protected HierarchicType _findSuperInterfaceChain(Type paramType, Class<Map> paramClass) {
    HierarchicType hierarchicType = new HierarchicType(paramType);
    Class<HashMap> clazz = hierarchicType.getRawClass();
    return (clazz == paramClass) ? new HierarchicType(paramType) : ((clazz == HashMap.class && paramClass == Map.class) ? _hashMapSuperInterfaceChain(hierarchicType) : ((clazz == ArrayList.class && paramClass == List.class) ? _arrayListSuperInterfaceChain(hierarchicType) : _doFindSuperInterfaceChain(hierarchicType, paramClass)));
  }
  
  protected HierarchicType _findSuperTypeChain(Class paramClass1, Class paramClass2) {
    return paramClass2.isInterface() ? _findSuperInterfaceChain(paramClass1, paramClass2) : _findSuperClassChain(paramClass1, paramClass2);
  }
  
  protected JavaType _fromArrayType(GenericArrayType paramGenericArrayType, TypeBindings paramTypeBindings) {
    return ArrayType.construct(_constructType(paramGenericArrayType.getGenericComponentType(), paramTypeBindings), null, null);
  }
  
  protected JavaType _fromClass(Class<?> paramClass, TypeBindings paramTypeBindings) {
    return paramClass.isArray() ? ArrayType.construct(_constructType(paramClass.getComponentType(), null), null, null) : (paramClass.isEnum() ? new SimpleType(paramClass) : (Map.class.isAssignableFrom(paramClass) ? _mapType(paramClass) : (Collection.class.isAssignableFrom(paramClass) ? _collectionType(paramClass) : new SimpleType(paramClass))));
  }
  
  protected JavaType _fromParamType(ParameterizedType paramParameterizedType, TypeBindings paramTypeBindings) {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface getRawType : ()Ljava/lang/reflect/Type;
    //   6: checkcast java/lang/Class
    //   9: astore #6
    //   11: aload_1
    //   12: invokeinterface getActualTypeArguments : ()[Ljava/lang/reflect/Type;
    //   17: astore #7
    //   19: aload #7
    //   21: ifnonnull -> 113
    //   24: iconst_0
    //   25: istore_3
    //   26: iload_3
    //   27: ifne -> 120
    //   30: getstatic com/flurry/org/codehaus/jackson/map/type/TypeFactory.NO_TYPES : [Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   33: astore_1
    //   34: ldc java/util/Map
    //   36: aload #6
    //   38: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   41: ifeq -> 173
    //   44: aload_0
    //   45: aload_0
    //   46: aload #6
    //   48: aload_1
    //   49: invokevirtual constructSimpleType : (Ljava/lang/Class;[Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   52: ldc java/util/Map
    //   54: invokevirtual findTypeParameters : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Ljava/lang/Class;)[Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   57: astore_1
    //   58: aload_1
    //   59: arraylength
    //   60: iconst_2
    //   61: if_icmpeq -> 159
    //   64: new java/lang/IllegalArgumentException
    //   67: dup
    //   68: new java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial <init> : ()V
    //   75: ldc_w 'Could not find 2 type parameters for Map class '
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload #6
    //   83: invokevirtual getName : ()Ljava/lang/String;
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: ldc_w ' (found '
    //   92: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload_1
    //   96: arraylength
    //   97: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   100: ldc_w ')'
    //   103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual toString : ()Ljava/lang/String;
    //   109: invokespecial <init> : (Ljava/lang/String;)V
    //   112: athrow
    //   113: aload #7
    //   115: arraylength
    //   116: istore_3
    //   117: goto -> 26
    //   120: iload_3
    //   121: anewarray com/flurry/org/codehaus/jackson/type/JavaType
    //   124: astore #5
    //   126: iconst_0
    //   127: istore #4
    //   129: aload #5
    //   131: astore_1
    //   132: iload #4
    //   134: iload_3
    //   135: if_icmpge -> 34
    //   138: aload #5
    //   140: iload #4
    //   142: aload_0
    //   143: aload #7
    //   145: iload #4
    //   147: aaload
    //   148: aload_2
    //   149: invokevirtual _constructType : (Ljava/lang/reflect/Type;Lcom/flurry/org/codehaus/jackson/map/type/TypeBindings;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   152: aastore
    //   153: iinc #4, 1
    //   156: goto -> 129
    //   159: aload #6
    //   161: aload_1
    //   162: iconst_0
    //   163: aaload
    //   164: aload_1
    //   165: iconst_1
    //   166: aaload
    //   167: invokestatic construct : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/map/type/MapType;
    //   170: astore_1
    //   171: aload_1
    //   172: areturn
    //   173: ldc java/util/Collection
    //   175: aload #6
    //   177: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   180: ifeq -> 264
    //   183: aload_0
    //   184: aload_0
    //   185: aload #6
    //   187: aload_1
    //   188: invokevirtual constructSimpleType : (Ljava/lang/Class;[Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   191: ldc java/util/Collection
    //   193: invokevirtual findTypeParameters : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Ljava/lang/Class;)[Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   196: astore_1
    //   197: aload_1
    //   198: arraylength
    //   199: iconst_1
    //   200: if_icmpeq -> 252
    //   203: new java/lang/IllegalArgumentException
    //   206: dup
    //   207: new java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial <init> : ()V
    //   214: ldc_w 'Could not find 1 type parameter for Collection class '
    //   217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload #6
    //   222: invokevirtual getName : ()Ljava/lang/String;
    //   225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: ldc_w ' (found '
    //   231: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: aload_1
    //   235: arraylength
    //   236: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   239: ldc_w ')'
    //   242: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: invokevirtual toString : ()Ljava/lang/String;
    //   248: invokespecial <init> : (Ljava/lang/String;)V
    //   251: athrow
    //   252: aload #6
    //   254: aload_1
    //   255: iconst_0
    //   256: aaload
    //   257: invokestatic construct : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/map/type/CollectionType;
    //   260: astore_1
    //   261: goto -> 171
    //   264: iload_3
    //   265: ifne -> 281
    //   268: new com/flurry/org/codehaus/jackson/map/type/SimpleType
    //   271: dup
    //   272: aload #6
    //   274: invokespecial <init> : (Ljava/lang/Class;)V
    //   277: astore_1
    //   278: goto -> 171
    //   281: aload_0
    //   282: aload #6
    //   284: aload_1
    //   285: invokevirtual constructSimpleType : (Ljava/lang/Class;[Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   288: astore_1
    //   289: goto -> 171
  }
  
  protected JavaType _fromParameterizedClass(Class<?> paramClass, List<JavaType> paramList) {
    JavaType<JavaType> javaType;
    if (paramClass.isArray())
      return ArrayType.construct(_constructType(paramClass.getComponentType(), null), null, null); 
    if (paramClass.isEnum())
      return new SimpleType(paramClass); 
    if (Map.class.isAssignableFrom(paramClass)) {
      if (paramList.size() > 0) {
        JavaType javaType1 = paramList.get(0);
        if (paramList.size() >= 2) {
          javaType = paramList.get(1);
        } else {
          javaType = _unknownType();
        } 
        return MapType.construct(paramClass, javaType1, javaType);
      } 
      return _mapType(paramClass);
    } 
    return Collection.class.isAssignableFrom(paramClass) ? ((javaType.size() >= 1) ? CollectionType.construct(paramClass, javaType.get(0)) : _collectionType(paramClass)) : ((javaType.size() == 0) ? new SimpleType(paramClass) : constructSimpleType(paramClass, javaType.<JavaType>toArray(new JavaType[javaType.size()])));
  }
  
  protected JavaType _fromVariable(TypeVariable paramTypeVariable, TypeBindings paramTypeBindings) {
    if (paramTypeBindings == null)
      return _unknownType(); 
    String str = paramTypeVariable.getName();
    JavaType javaType2 = paramTypeBindings.findType(str);
    JavaType javaType1 = javaType2;
    if (javaType2 == null) {
      Type[] arrayOfType = paramTypeVariable.getBounds();
      paramTypeBindings._addPlaceholder(str);
      javaType1 = _constructType(arrayOfType[0], paramTypeBindings);
    } 
    return javaType1;
  }
  
  protected JavaType _fromWildcard(WildcardType paramWildcardType, TypeBindings paramTypeBindings) {
    return _constructType(paramWildcardType.getUpperBounds()[0], paramTypeBindings);
  }
  
  protected HierarchicType _hashMapSuperInterfaceChain(HierarchicType paramHierarchicType) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _cachedHashMapType : Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   6: ifnonnull -> 30
    //   9: aload_1
    //   10: invokevirtual deepCloneWithoutSubtype : ()Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   13: astore_2
    //   14: aload_0
    //   15: aload_2
    //   16: ldc java/util/Map
    //   18: invokevirtual _doFindSuperInterfaceChain : (Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   21: pop
    //   22: aload_0
    //   23: aload_2
    //   24: invokevirtual getSuperType : ()Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   27: putfield _cachedHashMapType : Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   30: aload_0
    //   31: getfield _cachedHashMapType : Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   34: invokevirtual deepCloneWithoutSubtype : ()Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   37: astore_2
    //   38: aload_1
    //   39: aload_2
    //   40: invokevirtual setSuperType : (Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;)V
    //   43: aload_2
    //   44: aload_1
    //   45: invokevirtual setSubType : (Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;)V
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: areturn
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	52	finally
    //   30	48	52	finally
  }
  
  protected JavaType _resolveVariableViaSubTypes(HierarchicType paramHierarchicType, String paramString, TypeBindings paramTypeBindings) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 109
    //   4: aload_1
    //   5: invokevirtual isGeneric : ()Z
    //   8: ifeq -> 109
    //   11: aload_1
    //   12: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   15: invokevirtual getTypeParameters : ()[Ljava/lang/reflect/TypeVariable;
    //   18: astore #6
    //   20: iconst_0
    //   21: istore #4
    //   23: aload #6
    //   25: arraylength
    //   26: istore #5
    //   28: iload #4
    //   30: iload #5
    //   32: if_icmpge -> 109
    //   35: aload_2
    //   36: aload #6
    //   38: iload #4
    //   40: aaload
    //   41: invokeinterface getName : ()Ljava/lang/String;
    //   46: invokevirtual equals : (Ljava/lang/Object;)Z
    //   49: ifeq -> 103
    //   52: aload_1
    //   53: invokevirtual asGeneric : ()Ljava/lang/reflect/ParameterizedType;
    //   56: invokeinterface getActualTypeArguments : ()[Ljava/lang/reflect/Type;
    //   61: iload #4
    //   63: aaload
    //   64: astore_2
    //   65: aload_2
    //   66: instanceof java/lang/reflect/TypeVariable
    //   69: ifeq -> 93
    //   72: aload_0
    //   73: aload_1
    //   74: invokevirtual getSubType : ()Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;
    //   77: aload_2
    //   78: checkcast java/lang/reflect/TypeVariable
    //   81: invokeinterface getName : ()Ljava/lang/String;
    //   86: aload_3
    //   87: invokevirtual _resolveVariableViaSubTypes : (Lcom/flurry/org/codehaus/jackson/map/type/HierarchicType;Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/map/type/TypeBindings;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   90: astore_1
    //   91: aload_1
    //   92: areturn
    //   93: aload_0
    //   94: aload_2
    //   95: aload_3
    //   96: invokevirtual _constructType : (Ljava/lang/reflect/Type;Lcom/flurry/org/codehaus/jackson/map/type/TypeBindings;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   99: astore_1
    //   100: goto -> 91
    //   103: iinc #4, 1
    //   106: goto -> 28
    //   109: aload_0
    //   110: invokevirtual _unknownType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   113: astore_1
    //   114: goto -> 91
  }
  
  protected JavaType _unknownType() {
    return new SimpleType(Object.class);
  }
  
  public ArrayType constructArrayType(JavaType paramJavaType) {
    return ArrayType.construct(paramJavaType, null, null);
  }
  
  public ArrayType constructArrayType(Class paramClass) {
    return ArrayType.construct(_constructType(paramClass, null), null, null);
  }
  
  public CollectionLikeType constructCollectionLikeType(Class paramClass, JavaType paramJavaType) {
    return CollectionLikeType.construct(paramClass, paramJavaType);
  }
  
  public CollectionLikeType constructCollectionLikeType(Class paramClass1, Class paramClass2) {
    return CollectionLikeType.construct(paramClass1, constructType(paramClass2));
  }
  
  public CollectionType constructCollectionType(Class paramClass, JavaType paramJavaType) {
    return CollectionType.construct(paramClass, paramJavaType);
  }
  
  public CollectionType constructCollectionType(Class paramClass1, Class paramClass2) {
    return CollectionType.construct(paramClass1, constructType(paramClass2));
  }
  
  public JavaType constructFromCanonical(String paramString) {
    return this._parser.parse(paramString);
  }
  
  public MapLikeType constructMapLikeType(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2) {
    return MapLikeType.construct(paramClass, paramJavaType1, paramJavaType2);
  }
  
  public MapLikeType constructMapLikeType(Class paramClass1, Class paramClass2, Class paramClass3) {
    return MapType.construct(paramClass1, constructType(paramClass2), constructType(paramClass3));
  }
  
  public MapType constructMapType(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2) {
    return MapType.construct(paramClass, paramJavaType1, paramJavaType2);
  }
  
  public MapType constructMapType(Class paramClass1, Class paramClass2, Class paramClass3) {
    return MapType.construct(paramClass1, constructType(paramClass2), constructType(paramClass3));
  }
  
  public JavaType constructParametricType(Class<?> paramClass, JavaType... paramVarArgs) {
    if (paramClass.isArray()) {
      if (paramVarArgs.length != 1)
        throw new IllegalArgumentException("Need exactly 1 parameter type for arrays (" + paramClass.getName() + ")"); 
      return constructArrayType(paramVarArgs[0]);
    } 
    if (Map.class.isAssignableFrom(paramClass)) {
      if (paramVarArgs.length != 2)
        throw new IllegalArgumentException("Need exactly 2 parameter types for Map types (" + paramClass.getName() + ")"); 
      return constructMapType(paramClass, paramVarArgs[0], paramVarArgs[1]);
    } 
    if (Collection.class.isAssignableFrom(paramClass)) {
      if (paramVarArgs.length != 1)
        throw new IllegalArgumentException("Need exactly 1 parameter type for Collection types (" + paramClass.getName() + ")"); 
      return constructCollectionType(paramClass, paramVarArgs[0]);
    } 
    return constructSimpleType(paramClass, paramVarArgs);
  }
  
  public JavaType constructParametricType(Class paramClass, Class... paramVarArgs) {
    int i = paramVarArgs.length;
    JavaType[] arrayOfJavaType = new JavaType[i];
    for (byte b = 0; b < i; b++)
      arrayOfJavaType[b] = _fromClass(paramVarArgs[b], null); 
    return constructParametricType(paramClass, arrayOfJavaType);
  }
  
  public CollectionLikeType constructRawCollectionLikeType(Class paramClass) {
    return CollectionLikeType.construct(paramClass, unknownType());
  }
  
  public CollectionType constructRawCollectionType(Class paramClass) {
    return CollectionType.construct(paramClass, unknownType());
  }
  
  public MapLikeType constructRawMapLikeType(Class paramClass) {
    return MapLikeType.construct(paramClass, unknownType(), unknownType());
  }
  
  public MapType constructRawMapType(Class paramClass) {
    return MapType.construct(paramClass, unknownType(), unknownType());
  }
  
  public JavaType constructSimpleType(Class paramClass, JavaType[] paramArrayOfJavaType) {
    TypeVariable[] arrayOfTypeVariable = paramClass.getTypeParameters();
    if (arrayOfTypeVariable.length != paramArrayOfJavaType.length)
      throw new IllegalArgumentException("Parameter type mismatch for " + paramClass.getName() + ": expected " + arrayOfTypeVariable.length + " parameters, was given " + paramArrayOfJavaType.length); 
    String[] arrayOfString = new String[arrayOfTypeVariable.length];
    byte b = 0;
    int i = arrayOfTypeVariable.length;
    while (b < i) {
      arrayOfString[b] = arrayOfTypeVariable[b].getName();
      b++;
    } 
    return new SimpleType(paramClass, arrayOfString, paramArrayOfJavaType, null, null);
  }
  
  public JavaType constructSpecializedType(JavaType paramJavaType, Class<?> paramClass) {
    JavaType javaType;
    if (paramJavaType instanceof SimpleType && (paramClass.isArray() || Map.class.isAssignableFrom(paramClass) || Collection.class.isAssignableFrom(paramClass))) {
      if (!paramJavaType.getRawClass().isAssignableFrom(paramClass))
        throw new IllegalArgumentException("Class " + paramClass.getClass().getName() + " not subtype of " + paramJavaType); 
      JavaType javaType1 = _fromClass(paramClass, new TypeBindings(this, paramJavaType.getRawClass()));
      Object object2 = paramJavaType.getValueHandler();
      javaType = javaType1;
      if (object2 != null)
        javaType = javaType1.withValueHandler(object2); 
      Object object1 = paramJavaType.getTypeHandler();
      paramJavaType = javaType;
      if (object1 != null)
        paramJavaType = javaType.withTypeHandler(object1); 
      return paramJavaType;
    } 
    return paramJavaType.narrowBy((Class)javaType);
  }
  
  public JavaType constructType(TypeReference paramTypeReference) {
    return _constructType(paramTypeReference.getType(), null);
  }
  
  public JavaType constructType(Type paramType) {
    return _constructType(paramType, null);
  }
  
  public JavaType constructType(Type paramType, TypeBindings paramTypeBindings) {
    return _constructType(paramType, paramTypeBindings);
  }
  
  public JavaType constructType(Type paramType, JavaType paramJavaType) {
    if (paramJavaType == null) {
      paramJavaType = null;
      return _constructType(paramType, (TypeBindings)paramJavaType);
    } 
    TypeBindings typeBindings = new TypeBindings(this, paramJavaType);
    return _constructType(paramType, typeBindings);
  }
  
  public JavaType constructType(Type paramType, Class paramClass) {
    if (paramClass == null) {
      paramClass = null;
      return _constructType(paramType, (TypeBindings)paramClass);
    } 
    TypeBindings typeBindings = new TypeBindings(this, paramClass);
    return _constructType(paramType, typeBindings);
  }
  
  public JavaType[] findTypeParameters(JavaType paramJavaType, Class paramClass) {
    JavaType[] arrayOfJavaType;
    Class clazz = paramJavaType.getRawClass();
    if (clazz == paramClass) {
      int i = paramJavaType.containedTypeCount();
      if (i == 0)
        return null; 
      arrayOfJavaType = new JavaType[i];
      byte b = 0;
      while (true) {
        null = arrayOfJavaType;
        if (b < i) {
          arrayOfJavaType[b] = paramJavaType.containedType(b);
          b++;
          continue;
        } 
        return null;
      } 
    } 
    return findTypeParameters((Class)arrayOfJavaType, (Class)null, new TypeBindings(this, paramJavaType));
  }
  
  public JavaType[] findTypeParameters(Class paramClass1, Class paramClass2) {
    return findTypeParameters(paramClass1, paramClass2, new TypeBindings(this, paramClass1));
  }
  
  public JavaType[] findTypeParameters(Class paramClass1, Class paramClass2, TypeBindings paramTypeBindings) {
    HierarchicType hierarchicType2 = _findSuperTypeChain(paramClass1, paramClass2);
    HierarchicType hierarchicType1 = hierarchicType2;
    if (hierarchicType2 == null)
      throw new IllegalArgumentException("Class " + paramClass1.getName() + " is not a subtype of " + paramClass2.getName()); 
    label19: while (hierarchicType1.getSuperType() != null) {
      hierarchicType1 = hierarchicType1.getSuperType();
      Class clazz = hierarchicType1.getRawClass();
      TypeBindings typeBindings = new TypeBindings(this, clazz);
      if (hierarchicType1.isGeneric()) {
        Type[] arrayOfType = hierarchicType1.asGeneric().getActualTypeArguments();
        TypeVariable[] arrayOfTypeVariable = clazz.getTypeParameters();
        int i = arrayOfType.length;
        byte b = 0;
        while (true) {
          if (b < i) {
            typeBindings.addBinding(arrayOfTypeVariable[b].getName(), instance._constructType(arrayOfType[b], paramTypeBindings));
            b++;
            continue;
          } 
          paramTypeBindings = typeBindings;
          continue label19;
        } 
      } 
      // Byte code: goto -> 61
    } 
    return !hierarchicType1.isGeneric() ? null : paramTypeBindings.typesAsArray();
  }
  
  public JavaType uncheckedSimpleType(Class paramClass) {
    return new SimpleType(paramClass);
  }
  
  public TypeFactory withModifier(TypeModifier paramTypeModifier) {
    return (this._modifiers == null) ? new TypeFactory(this._parser, new TypeModifier[] { paramTypeModifier }) : new TypeFactory(this._parser, (TypeModifier[])ArrayBuilders.insertInListNoDup((Object[])this._modifiers, paramTypeModifier));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\TypeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */