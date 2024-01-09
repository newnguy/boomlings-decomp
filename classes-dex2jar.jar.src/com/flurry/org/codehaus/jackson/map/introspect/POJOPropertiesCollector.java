package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.PropertyNamingStrategy;
import com.flurry.org.codehaus.jackson.map.util.BeanUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class POJOPropertiesCollector {
  protected final AnnotationIntrospector _annotationIntrospector;
  
  protected LinkedList _anyGetters;
  
  protected LinkedList _anySetters;
  
  protected final AnnotatedClass _classDef;
  
  protected final MapperConfig _config;
  
  protected LinkedList _creatorProperties;
  
  protected final boolean _forSerialization;
  
  protected Set _ignoredPropertyNames;
  
  protected Set _ignoredPropertyNamesForDeser;
  
  protected LinkedHashMap _injectables;
  
  protected LinkedList _jsonValueGetters;
  
  protected final LinkedHashMap _properties;
  
  protected final JavaType _type;
  
  protected final VisibilityChecker _visibilityChecker;
  
  protected POJOPropertiesCollector(MapperConfig paramMapperConfig, boolean paramBoolean, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass) {
    AnnotationIntrospector annotationIntrospector;
    this._properties = new LinkedHashMap<Object, Object>();
    this._creatorProperties = null;
    this._anyGetters = null;
    this._anySetters = null;
    this._jsonValueGetters = null;
    this._config = paramMapperConfig;
    this._forSerialization = paramBoolean;
    this._type = paramJavaType;
    this._classDef = paramAnnotatedClass;
    paramJavaType = javaType;
    if (paramMapperConfig.isAnnotationProcessingEnabled())
      annotationIntrospector = this._config.getAnnotationIntrospector(); 
    this._annotationIntrospector = annotationIntrospector;
    if (this._annotationIntrospector == null) {
      this._visibilityChecker = this._config.getDefaultVisibilityChecker();
      return;
    } 
    this._visibilityChecker = this._annotationIntrospector.findAutoDetectVisibility(paramAnnotatedClass, this._config.getDefaultVisibilityChecker());
  }
  
  private void _addIgnored(POJOPropertyBuilder paramPOJOPropertyBuilder) {
    if (!this._forSerialization) {
      String str = paramPOJOPropertyBuilder.getName();
      this._ignoredPropertyNames = addToSet(this._ignoredPropertyNames, str);
      if (paramPOJOPropertyBuilder.anyDeserializeIgnorals())
        this._ignoredPropertyNamesForDeser = addToSet(this._ignoredPropertyNamesForDeser, str); 
    } 
  }
  
  private Set addToSet(Set<String> paramSet, String paramString) {
    Set<String> set = paramSet;
    if (paramSet == null)
      set = new HashSet(); 
    set.add(paramString);
    return set;
  }
  
  protected void _addCreators() {
    AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
    if (annotationIntrospector != null) {
      for (AnnotatedConstructor annotatedConstructor : this._classDef.getConstructors()) {
        if (this._creatorProperties == null)
          this._creatorProperties = new LinkedList(); 
        int i = annotatedConstructor.getParameterCount();
        for (byte b = 0; b < i; b++) {
          AnnotatedParameter annotatedParameter = annotatedConstructor.getParameter(b);
          String str = annotationIntrospector.findPropertyNameForParam(annotatedParameter);
          if (str != null) {
            POJOPropertyBuilder pOJOPropertyBuilder = _property(str);
            pOJOPropertyBuilder.addCtor(annotatedParameter, str, true, false);
            this._creatorProperties.add(pOJOPropertyBuilder);
          } 
        } 
      } 
      Iterator<AnnotatedMethod> iterator = this._classDef.getStaticMethods().iterator();
      while (true) {
        if (iterator.hasNext()) {
          AnnotatedMethod annotatedMethod = iterator.next();
          if (this._creatorProperties == null)
            this._creatorProperties = new LinkedList(); 
          int i = annotatedMethod.getParameterCount();
          for (byte b = 0; b < i; b++) {
            AnnotatedParameter annotatedParameter = annotatedMethod.getParameter(b);
            String str = annotationIntrospector.findPropertyNameForParam(annotatedParameter);
            if (str != null) {
              POJOPropertyBuilder pOJOPropertyBuilder = _property(str);
              pOJOPropertyBuilder.addCtor(annotatedParameter, str, true, false);
              this._creatorProperties.add(pOJOPropertyBuilder);
            } 
          } 
          continue;
        } 
        return;
      } 
    } 
  }
  
  protected void _addFields() {
    AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
    for (AnnotatedField annotatedField : this._classDef.fields()) {
      boolean bool;
      boolean bool1;
      String str1;
      String str2 = annotatedField.getName();
      if (annotationIntrospector == null) {
        str1 = null;
      } else if (this._forSerialization) {
        str1 = annotationIntrospector.findSerializablePropertyName(annotatedField);
      } else {
        str1 = annotationIntrospector.findDeserializablePropertyName(annotatedField);
      } 
      if ("".equals(str1))
        str1 = str2; 
      if (str1 != null) {
        bool = true;
      } else {
        bool = false;
      } 
      if (!bool)
        bool = this._visibilityChecker.isFieldVisible(annotatedField); 
      if (annotationIntrospector != null && annotationIntrospector.hasIgnoreMarker(annotatedField)) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      _property(str2).addField(annotatedField, str1, bool, bool1);
    } 
  }
  
  protected void _addInjectables() {
    AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
    if (annotationIntrospector != null) {
      for (AnnotatedField annotatedField : this._classDef.fields())
        _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedField), annotatedField); 
      Iterator<AnnotatedMethod> iterator = this._classDef.memberMethods().iterator();
      while (true) {
        if (iterator.hasNext()) {
          AnnotatedMethod annotatedMethod = iterator.next();
          if (annotatedMethod.getParameterCount() == 1)
            _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMethod), annotatedMethod); 
          continue;
        } 
        return;
      } 
    } 
  }
  
  protected void _addMethods() {
    AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
    for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
      int i = annotatedMethod.getParameterCount();
      if (i == 0) {
        boolean bool;
        boolean bool1;
        String str1;
        String str2;
        if (annotationIntrospector != null) {
          if (annotationIntrospector.hasAnyGetterAnnotation(annotatedMethod)) {
            if (this._anyGetters == null)
              this._anyGetters = new LinkedList(); 
            this._anyGetters.add(annotatedMethod);
            continue;
          } 
          if (annotationIntrospector.hasAsValueAnnotation(annotatedMethod)) {
            if (this._jsonValueGetters == null)
              this._jsonValueGetters = new LinkedList(); 
            this._jsonValueGetters.add(annotatedMethod);
            continue;
          } 
        } 
        if (annotationIntrospector == null) {
          str1 = null;
        } else {
          str1 = annotationIntrospector.findGettablePropertyName(annotatedMethod);
        } 
        if (str1 == null) {
          String str = BeanUtil.okNameForRegularGetter(annotatedMethod, annotatedMethod.getName());
          if (str == null) {
            str = BeanUtil.okNameForIsGetter(annotatedMethod, annotatedMethod.getName());
            if (str != null) {
              bool = this._visibilityChecker.isIsGetterVisible(annotatedMethod);
              str2 = str1;
              str1 = str;
            } else {
              continue;
            } 
          } else {
            bool = this._visibilityChecker.isGetterVisible(annotatedMethod);
            str2 = str1;
            str1 = str;
          } 
        } else {
          String str = BeanUtil.okNameForGetter(annotatedMethod);
          str2 = str;
          if (str == null)
            str2 = annotatedMethod.getName(); 
          str = str1;
          if (str1.length() == 0)
            str = str2; 
          str1 = str2;
          str2 = str;
          bool = true;
        } 
        if (annotationIntrospector == null) {
          bool1 = false;
        } else {
          bool1 = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
        } 
        _property(str1).addGetter(annotatedMethod, str2, bool, bool1);
        continue;
      } 
      if (i == 1) {
        boolean bool;
        boolean bool1;
        String str1;
        String str2;
        if (annotationIntrospector == null) {
          str1 = null;
        } else {
          str1 = annotationIntrospector.findSettablePropertyName(annotatedMethod);
        } 
        if (str1 == null) {
          String str = BeanUtil.okNameForSetter(annotatedMethod);
          if (str != null) {
            bool = this._visibilityChecker.isSetterVisible(annotatedMethod);
            str2 = str1;
            str1 = str;
          } else {
            continue;
          } 
        } else {
          String str = BeanUtil.okNameForSetter(annotatedMethod);
          str2 = str;
          if (str == null)
            str2 = annotatedMethod.getName(); 
          str = str1;
          if (str1.length() == 0)
            str = str2; 
          str1 = str2;
          str2 = str;
          bool = true;
        } 
        if (annotationIntrospector == null) {
          bool1 = false;
        } else {
          bool1 = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
        } 
        _property(str1).addSetter(annotatedMethod, str2, bool, bool1);
        continue;
      } 
      if (i == 2 && annotationIntrospector != null && annotationIntrospector.hasAnySetterAnnotation(annotatedMethod)) {
        if (this._anySetters == null)
          this._anySetters = new LinkedList(); 
        this._anySetters.add(annotatedMethod);
      } 
    } 
  }
  
  protected void _doAddInjectable(Object paramObject, AnnotatedMember paramAnnotatedMember) {
    if (paramObject != null) {
      if (this._injectables == null)
        this._injectables = new LinkedHashMap<Object, Object>(); 
      if ((AnnotatedMember)this._injectables.put(paramObject, paramAnnotatedMember) != null) {
        if (paramObject == null) {
          String str1 = "[null]";
          throw new IllegalArgumentException("Duplicate injectable value with id '" + String.valueOf(paramObject) + "' (of type " + str1 + ")");
        } 
        String str = paramObject.getClass().getName();
        throw new IllegalArgumentException("Duplicate injectable value with id '" + String.valueOf(paramObject) + "' (of type " + str + ")");
      } 
    } 
  }
  
  protected POJOPropertyBuilder _property(String paramString) {
    POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder)this._properties.get(paramString);
    POJOPropertyBuilder pOJOPropertyBuilder1 = pOJOPropertyBuilder2;
    if (pOJOPropertyBuilder2 == null) {
      pOJOPropertyBuilder1 = new POJOPropertyBuilder(paramString);
      this._properties.put(paramString, pOJOPropertyBuilder1);
    } 
    return pOJOPropertyBuilder1;
  }
  
  protected void _removeUnwantedProperties() {
    Iterator<Map.Entry> iterator = this._properties.entrySet().iterator();
    while (iterator.hasNext()) {
      POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder)((Map.Entry)iterator.next()).getValue();
      if (!pOJOPropertyBuilder.anyVisible()) {
        iterator.remove();
        continue;
      } 
      if (pOJOPropertyBuilder.anyIgnorals()) {
        _addIgnored(pOJOPropertyBuilder);
        if (!pOJOPropertyBuilder.anyExplicitNames()) {
          iterator.remove();
          continue;
        } 
        pOJOPropertyBuilder.removeIgnored();
      } 
      pOJOPropertyBuilder.removeNonVisible();
    } 
  }
  
  protected void _renameProperties() {
    Iterator<Map.Entry> iterator = this._properties.entrySet().iterator();
    LinkedList<POJOPropertyBuilder> linkedList = null;
    while (iterator.hasNext()) {
      POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder)((Map.Entry)iterator.next()).getValue();
      String str = pOJOPropertyBuilder.findNewName();
      if (str != null) {
        LinkedList<POJOPropertyBuilder> linkedList1 = linkedList;
        if (linkedList == null)
          linkedList1 = new LinkedList(); 
        linkedList1.add(pOJOPropertyBuilder.withName(str));
        iterator.remove();
        linkedList = linkedList1;
      } 
    } 
    if (linkedList != null)
      for (POJOPropertyBuilder pOJOPropertyBuilder2 : linkedList) {
        String str = pOJOPropertyBuilder2.getName();
        POJOPropertyBuilder pOJOPropertyBuilder1 = (POJOPropertyBuilder)this._properties.get(str);
        if (pOJOPropertyBuilder1 == null) {
          this._properties.put(str, pOJOPropertyBuilder2);
          continue;
        } 
        pOJOPropertyBuilder1.addAll(pOJOPropertyBuilder2);
      }  
  }
  
  protected void _renameUsing(PropertyNamingStrategy paramPropertyNamingStrategy) {
    POJOPropertyBuilder[] arrayOfPOJOPropertyBuilder = (POJOPropertyBuilder[])this._properties.values().toArray((Object[])new POJOPropertyBuilder[this._properties.size()]);
    this._properties.clear();
    int i = arrayOfPOJOPropertyBuilder.length;
    for (byte b = 0; b < i; b++) {
      POJOPropertyBuilder pOJOPropertyBuilder2 = arrayOfPOJOPropertyBuilder[b];
      String str = pOJOPropertyBuilder2.getName();
      if (this._forSerialization) {
        if (pOJOPropertyBuilder2.hasGetter()) {
          str = paramPropertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder2.getGetter(), str);
        } else if (pOJOPropertyBuilder2.hasField()) {
          str = paramPropertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder2.getField(), str);
        } 
      } else if (pOJOPropertyBuilder2.hasSetter()) {
        str = paramPropertyNamingStrategy.nameForSetterMethod(this._config, pOJOPropertyBuilder2.getSetter(), str);
      } else if (pOJOPropertyBuilder2.hasConstructorParameter()) {
        str = paramPropertyNamingStrategy.nameForConstructorParameter(this._config, pOJOPropertyBuilder2.getConstructorParameter(), str);
      } else if (pOJOPropertyBuilder2.hasField()) {
        str = paramPropertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder2.getField(), str);
      } else if (pOJOPropertyBuilder2.hasGetter()) {
        str = paramPropertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder2.getGetter(), str);
      } 
      POJOPropertyBuilder pOJOPropertyBuilder1 = pOJOPropertyBuilder2;
      if (!str.equals(pOJOPropertyBuilder2.getName()))
        pOJOPropertyBuilder1 = pOJOPropertyBuilder2.withName(str); 
      pOJOPropertyBuilder2 = (POJOPropertyBuilder)this._properties.get(str);
      if (pOJOPropertyBuilder2 == null) {
        this._properties.put(str, pOJOPropertyBuilder1);
      } else {
        pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder1);
      } 
    } 
  }
  
  protected void _sortProperties() {
    // Byte code:
    //   0: aload_0
    //   1: getfield _config : Lcom/flurry/org/codehaus/jackson/map/MapperConfig;
    //   4: invokevirtual getAnnotationIntrospector : ()Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   7: astore #4
    //   9: aload #4
    //   11: aload_0
    //   12: getfield _classDef : Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;
    //   15: invokevirtual findSerializationSortAlphabetically : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;)Ljava/lang/Boolean;
    //   18: astore #5
    //   20: aload #5
    //   22: ifnonnull -> 61
    //   25: aload_0
    //   26: getfield _config : Lcom/flurry/org/codehaus/jackson/map/MapperConfig;
    //   29: invokevirtual shouldSortPropertiesAlphabetically : ()Z
    //   32: istore_3
    //   33: aload #4
    //   35: aload_0
    //   36: getfield _classDef : Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;
    //   39: invokevirtual findSerializationPropertyOrder : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;)[Ljava/lang/String;
    //   42: astore #8
    //   44: iload_3
    //   45: ifne -> 70
    //   48: aload_0
    //   49: getfield _creatorProperties : Ljava/util/LinkedList;
    //   52: ifnonnull -> 70
    //   55: aload #8
    //   57: ifnonnull -> 70
    //   60: return
    //   61: aload #5
    //   63: invokevirtual booleanValue : ()Z
    //   66: istore_3
    //   67: goto -> 33
    //   70: aload_0
    //   71: getfield _properties : Ljava/util/LinkedHashMap;
    //   74: invokevirtual size : ()I
    //   77: istore_1
    //   78: iload_3
    //   79: ifeq -> 145
    //   82: new java/util/TreeMap
    //   85: dup
    //   86: invokespecial <init> : ()V
    //   89: astore #4
    //   91: aload_0
    //   92: getfield _properties : Ljava/util/LinkedHashMap;
    //   95: invokevirtual values : ()Ljava/util/Collection;
    //   98: invokeinterface iterator : ()Ljava/util/Iterator;
    //   103: astore #5
    //   105: aload #5
    //   107: invokeinterface hasNext : ()Z
    //   112: ifeq -> 160
    //   115: aload #5
    //   117: invokeinterface next : ()Ljava/lang/Object;
    //   122: checkcast com/flurry/org/codehaus/jackson/map/introspect/POJOPropertyBuilder
    //   125: astore #6
    //   127: aload #4
    //   129: aload #6
    //   131: invokevirtual getName : ()Ljava/lang/String;
    //   134: aload #6
    //   136: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   141: pop
    //   142: goto -> 105
    //   145: new java/util/LinkedHashMap
    //   148: dup
    //   149: iload_1
    //   150: iload_1
    //   151: iadd
    //   152: invokespecial <init> : (I)V
    //   155: astore #4
    //   157: goto -> 91
    //   160: new java/util/LinkedHashMap
    //   163: dup
    //   164: iload_1
    //   165: iload_1
    //   166: iadd
    //   167: invokespecial <init> : (I)V
    //   170: astore #9
    //   172: aload #8
    //   174: ifnull -> 296
    //   177: aload #8
    //   179: arraylength
    //   180: istore_2
    //   181: iconst_0
    //   182: istore_1
    //   183: iload_1
    //   184: iload_2
    //   185: if_icmpge -> 296
    //   188: aload #8
    //   190: iload_1
    //   191: aaload
    //   192: astore #6
    //   194: aload #4
    //   196: aload #6
    //   198: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   203: checkcast com/flurry/org/codehaus/jackson/map/introspect/POJOPropertyBuilder
    //   206: astore #7
    //   208: aload #7
    //   210: ifnonnull -> 380
    //   213: aload_0
    //   214: getfield _properties : Ljava/util/LinkedHashMap;
    //   217: invokevirtual values : ()Ljava/util/Collection;
    //   220: invokeinterface iterator : ()Ljava/util/Iterator;
    //   225: astore #10
    //   227: aload #10
    //   229: invokeinterface hasNext : ()Z
    //   234: ifeq -> 380
    //   237: aload #10
    //   239: invokeinterface next : ()Ljava/lang/Object;
    //   244: checkcast com/flurry/org/codehaus/jackson/map/introspect/POJOPropertyBuilder
    //   247: astore #5
    //   249: aload #6
    //   251: aload #5
    //   253: invokevirtual getInternalName : ()Ljava/lang/String;
    //   256: invokevirtual equals : (Ljava/lang/Object;)Z
    //   259: ifeq -> 227
    //   262: aload #5
    //   264: invokevirtual getName : ()Ljava/lang/String;
    //   267: astore #6
    //   269: aload #5
    //   271: astore #7
    //   273: aload #7
    //   275: ifnull -> 290
    //   278: aload #9
    //   280: aload #6
    //   282: aload #7
    //   284: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   289: pop
    //   290: iinc #1, 1
    //   293: goto -> 183
    //   296: aload_0
    //   297: getfield _creatorProperties : Ljava/util/LinkedList;
    //   300: ifnull -> 352
    //   303: aload_0
    //   304: getfield _creatorProperties : Ljava/util/LinkedList;
    //   307: invokevirtual iterator : ()Ljava/util/Iterator;
    //   310: astore #5
    //   312: aload #5
    //   314: invokeinterface hasNext : ()Z
    //   319: ifeq -> 352
    //   322: aload #5
    //   324: invokeinterface next : ()Ljava/lang/Object;
    //   329: checkcast com/flurry/org/codehaus/jackson/map/introspect/POJOPropertyBuilder
    //   332: astore #6
    //   334: aload #9
    //   336: aload #6
    //   338: invokevirtual getName : ()Ljava/lang/String;
    //   341: aload #6
    //   343: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   348: pop
    //   349: goto -> 312
    //   352: aload #9
    //   354: aload #4
    //   356: invokeinterface putAll : (Ljava/util/Map;)V
    //   361: aload_0
    //   362: getfield _properties : Ljava/util/LinkedHashMap;
    //   365: invokevirtual clear : ()V
    //   368: aload_0
    //   369: getfield _properties : Ljava/util/LinkedHashMap;
    //   372: aload #9
    //   374: invokevirtual putAll : (Ljava/util/Map;)V
    //   377: goto -> 60
    //   380: aload #6
    //   382: astore #5
    //   384: aload #5
    //   386: astore #6
    //   388: goto -> 273
  }
  
  public POJOPropertiesCollector collect() {
    this._properties.clear();
    _addFields();
    _addMethods();
    _addCreators();
    _addInjectables();
    _removeUnwantedProperties();
    _renameProperties();
    PropertyNamingStrategy propertyNamingStrategy = this._config.getPropertyNamingStrategy();
    if (propertyNamingStrategy != null)
      _renameUsing(propertyNamingStrategy); 
    Iterator<POJOPropertyBuilder> iterator = this._properties.values().iterator();
    while (iterator.hasNext())
      ((POJOPropertyBuilder)iterator.next()).trimByVisibility(); 
    iterator = this._properties.values().iterator();
    while (iterator.hasNext())
      ((POJOPropertyBuilder)iterator.next()).mergeAnnotations(this._forSerialization); 
    _sortProperties();
    return this;
  }
  
  public AnnotationIntrospector getAnnotationIntrospector() {
    return this._annotationIntrospector;
  }
  
  public AnnotatedMethod getAnyGetterMethod() {
    if (this._anyGetters != null) {
      if (this._anyGetters.size() > 1)
        reportProblem("Multiple 'any-getters' defined (" + this._anyGetters.get(0) + " vs " + this._anyGetters.get(1) + ")"); 
      return this._anyGetters.getFirst();
    } 
    return null;
  }
  
  public AnnotatedMethod getAnySetterMethod() {
    if (this._anySetters != null) {
      if (this._anySetters.size() > 1)
        reportProblem("Multiple 'any-setters' defined (" + this._anySetters.get(0) + " vs " + this._anySetters.get(1) + ")"); 
      return this._anySetters.getFirst();
    } 
    return null;
  }
  
  public AnnotatedClass getClassDef() {
    return this._classDef;
  }
  
  public MapperConfig getConfig() {
    return this._config;
  }
  
  public Set getIgnoredPropertyNames() {
    return this._ignoredPropertyNames;
  }
  
  public Set getIgnoredPropertyNamesForDeser() {
    return this._ignoredPropertyNamesForDeser;
  }
  
  public Map getInjectables() {
    return this._injectables;
  }
  
  public AnnotatedMethod getJsonValueMethod() {
    if (this._jsonValueGetters != null) {
      if (this._jsonValueGetters.size() > 1)
        reportProblem("Multiple value properties defined (" + this._jsonValueGetters.get(0) + " vs " + this._jsonValueGetters.get(1) + ")"); 
      return this._jsonValueGetters.get(0);
    } 
    return null;
  }
  
  public List getProperties() {
    return new ArrayList(this._properties.values());
  }
  
  protected Map getPropertyMap() {
    return this._properties;
  }
  
  public JavaType getType() {
    return this._type;
  }
  
  protected void reportProblem(String paramString) {
    throw new IllegalArgumentException("Problem with definition of " + this._classDef + ": " + paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\POJOPropertiesCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */