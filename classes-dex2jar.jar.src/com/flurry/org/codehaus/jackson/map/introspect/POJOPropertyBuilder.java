package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition;

public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable {
  protected POJOPropertyBuilder$Node _ctorParameters;
  
  protected POJOPropertyBuilder$Node _fields;
  
  protected POJOPropertyBuilder$Node _getters;
  
  protected final String _internalName;
  
  protected final String _name;
  
  protected POJOPropertyBuilder$Node _setters;
  
  public POJOPropertyBuilder(POJOPropertyBuilder paramPOJOPropertyBuilder, String paramString) {
    this._internalName = paramPOJOPropertyBuilder._internalName;
    this._name = paramString;
    this._fields = paramPOJOPropertyBuilder._fields;
    this._ctorParameters = paramPOJOPropertyBuilder._ctorParameters;
    this._getters = paramPOJOPropertyBuilder._getters;
    this._setters = paramPOJOPropertyBuilder._setters;
  }
  
  public POJOPropertyBuilder(String paramString) {
    this._internalName = paramString;
    this._name = paramString;
  }
  
  private boolean _anyExplicitNames(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 33
    //   4: aload_1
    //   5: getfield explicitName : Ljava/lang/String;
    //   8: ifnull -> 25
    //   11: aload_1
    //   12: getfield explicitName : Ljava/lang/String;
    //   15: invokevirtual length : ()I
    //   18: ifle -> 25
    //   21: iconst_1
    //   22: istore_2
    //   23: iload_2
    //   24: ireturn
    //   25: aload_1
    //   26: getfield next : Lcom/flurry/org/codehaus/jackson/map/introspect/POJOPropertyBuilder$Node;
    //   29: astore_1
    //   30: goto -> 0
    //   33: iconst_0
    //   34: istore_2
    //   35: goto -> 23
  }
  
  private boolean _anyIgnorals(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 23
    //   4: aload_1
    //   5: getfield isMarkedIgnored : Z
    //   8: ifeq -> 15
    //   11: iconst_1
    //   12: istore_2
    //   13: iload_2
    //   14: ireturn
    //   15: aload_1
    //   16: getfield next : Lcom/flurry/org/codehaus/jackson/map/introspect/POJOPropertyBuilder$Node;
    //   19: astore_1
    //   20: goto -> 0
    //   23: iconst_0
    //   24: istore_2
    //   25: goto -> 13
  }
  
  private boolean _anyVisible(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 23
    //   4: aload_1
    //   5: getfield isVisible : Z
    //   8: ifeq -> 15
    //   11: iconst_1
    //   12: istore_2
    //   13: iload_2
    //   14: ireturn
    //   15: aload_1
    //   16: getfield next : Lcom/flurry/org/codehaus/jackson/map/introspect/POJOPropertyBuilder$Node;
    //   19: astore_1
    //   20: goto -> 0
    //   23: iconst_0
    //   24: istore_2
    //   25: goto -> 13
  }
  
  private AnnotationMap _mergeAnnotations(int paramInt, POJOPropertyBuilder$Node... paramVarArgs) {
    // Byte code:
    //   0: aload_2
    //   1: iload_1
    //   2: aaload
    //   3: getfield value : Ljava/lang/Object;
    //   6: checkcast com/flurry/org/codehaus/jackson/map/introspect/AnnotatedMember
    //   9: invokevirtual getAllAnnotations : ()Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotationMap;
    //   12: astore_3
    //   13: iinc #1, 1
    //   16: iload_1
    //   17: aload_2
    //   18: arraylength
    //   19: if_icmpge -> 47
    //   22: aload_2
    //   23: iload_1
    //   24: aaload
    //   25: ifnull -> 41
    //   28: aload_3
    //   29: aload_0
    //   30: iload_1
    //   31: aload_2
    //   32: invokespecial _mergeAnnotations : (I[Lcom/flurry/org/codehaus/jackson/map/introspect/POJOPropertyBuilder$Node;)Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotationMap;
    //   35: invokestatic merge : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotationMap;Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotationMap;)Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotationMap;
    //   38: astore_2
    //   39: aload_2
    //   40: areturn
    //   41: iinc #1, 1
    //   44: goto -> 16
    //   47: aload_3
    //   48: astore_2
    //   49: goto -> 39
  }
  
  private POJOPropertyBuilder$Node _removeIgnored(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node) {
    if (paramPOJOPropertyBuilder$Node != null)
      paramPOJOPropertyBuilder$Node = paramPOJOPropertyBuilder$Node.withoutIgnored(); 
    return paramPOJOPropertyBuilder$Node;
  }
  
  private POJOPropertyBuilder$Node _removeNonVisible(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node) {
    if (paramPOJOPropertyBuilder$Node != null)
      paramPOJOPropertyBuilder$Node = paramPOJOPropertyBuilder$Node.withoutNonVisible(); 
    return paramPOJOPropertyBuilder$Node;
  }
  
  private POJOPropertyBuilder$Node _trimByVisibility(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node) {
    if (paramPOJOPropertyBuilder$Node != null)
      paramPOJOPropertyBuilder$Node = paramPOJOPropertyBuilder$Node.trimByVisibility(); 
    return paramPOJOPropertyBuilder$Node;
  }
  
  private POJOPropertyBuilder$Node findRenamed(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node1, POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node2) {
    while (paramPOJOPropertyBuilder$Node1 != null) {
      POJOPropertyBuilder$Node pOJOPropertyBuilder$Node;
      String str = paramPOJOPropertyBuilder$Node1.explicitName;
      if (str == null) {
        pOJOPropertyBuilder$Node = paramPOJOPropertyBuilder$Node2;
      } else {
        pOJOPropertyBuilder$Node = paramPOJOPropertyBuilder$Node2;
        if (!str.equals(this._name))
          if (paramPOJOPropertyBuilder$Node2 == null) {
            pOJOPropertyBuilder$Node = paramPOJOPropertyBuilder$Node1;
          } else {
            pOJOPropertyBuilder$Node = paramPOJOPropertyBuilder$Node2;
            if (!str.equals(paramPOJOPropertyBuilder$Node2.explicitName))
              throw new IllegalStateException("Conflicting property name definitions: '" + paramPOJOPropertyBuilder$Node2.explicitName + "' (for " + paramPOJOPropertyBuilder$Node2.value + ") vs '" + paramPOJOPropertyBuilder$Node1.explicitName + "' (for " + paramPOJOPropertyBuilder$Node1.value + ")"); 
          }  
      } 
      paramPOJOPropertyBuilder$Node1 = paramPOJOPropertyBuilder$Node1.next;
      paramPOJOPropertyBuilder$Node2 = pOJOPropertyBuilder$Node;
    } 
    return paramPOJOPropertyBuilder$Node2;
  }
  
  private static POJOPropertyBuilder$Node merge(POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node1, POJOPropertyBuilder$Node paramPOJOPropertyBuilder$Node2) {
    if (paramPOJOPropertyBuilder$Node1 != null) {
      if (paramPOJOPropertyBuilder$Node2 == null)
        return paramPOJOPropertyBuilder$Node1; 
      paramPOJOPropertyBuilder$Node2 = POJOPropertyBuilder$Node.access$000(paramPOJOPropertyBuilder$Node1, paramPOJOPropertyBuilder$Node2);
    } 
    return paramPOJOPropertyBuilder$Node2;
  }
  
  public void addAll(POJOPropertyBuilder paramPOJOPropertyBuilder) {
    this._fields = merge(this._fields, paramPOJOPropertyBuilder._fields);
    this._ctorParameters = merge(this._ctorParameters, paramPOJOPropertyBuilder._ctorParameters);
    this._getters = merge(this._getters, paramPOJOPropertyBuilder._getters);
    this._setters = merge(this._setters, paramPOJOPropertyBuilder._setters);
  }
  
  public void addCtor(AnnotatedParameter paramAnnotatedParameter, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    this._ctorParameters = new POJOPropertyBuilder$Node(paramAnnotatedParameter, this._ctorParameters, paramString, paramBoolean1, paramBoolean2);
  }
  
  public void addField(AnnotatedField paramAnnotatedField, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    this._fields = new POJOPropertyBuilder$Node(paramAnnotatedField, this._fields, paramString, paramBoolean1, paramBoolean2);
  }
  
  public void addGetter(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    this._getters = new POJOPropertyBuilder$Node(paramAnnotatedMethod, this._getters, paramString, paramBoolean1, paramBoolean2);
  }
  
  public void addSetter(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    this._setters = new POJOPropertyBuilder$Node(paramAnnotatedMethod, this._setters, paramString, paramBoolean1, paramBoolean2);
  }
  
  public boolean anyDeserializeIgnorals() {
    return (_anyIgnorals(this._fields) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters));
  }
  
  public boolean anyExplicitNames() {
    return (_anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters));
  }
  
  public boolean anyIgnorals() {
    return (_anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters));
  }
  
  public boolean anySerializeIgnorals() {
    return (_anyIgnorals(this._fields) || _anyIgnorals(this._getters));
  }
  
  public boolean anyVisible() {
    return (_anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters));
  }
  
  public int compareTo(POJOPropertyBuilder paramPOJOPropertyBuilder) {
    if (this._ctorParameters != null) {
      if (paramPOJOPropertyBuilder._ctorParameters == null)
        return -1; 
    } else if (paramPOJOPropertyBuilder._ctorParameters != null) {
      return 1;
    } 
    return getName().compareTo(paramPOJOPropertyBuilder.getName());
  }
  
  public boolean couldSerialize() {
    return (this._getters != null || this._fields != null);
  }
  
  public String findNewName() {
    String str = null;
    POJOPropertyBuilder$Node pOJOPropertyBuilder$Node = findRenamed(this._fields, null);
    pOJOPropertyBuilder$Node = findRenamed(this._getters, pOJOPropertyBuilder$Node);
    pOJOPropertyBuilder$Node = findRenamed(this._setters, pOJOPropertyBuilder$Node);
    pOJOPropertyBuilder$Node = findRenamed(this._ctorParameters, pOJOPropertyBuilder$Node);
    if (pOJOPropertyBuilder$Node != null)
      str = pOJOPropertyBuilder$Node.explicitName; 
    return str;
  }
  
  public AnnotatedMember getAccessor() {
    AnnotatedField annotatedField;
    AnnotatedMethod annotatedMethod2 = getGetter();
    AnnotatedMethod annotatedMethod1 = annotatedMethod2;
    if (annotatedMethod2 == null)
      annotatedField = getField(); 
    return annotatedField;
  }
  
  public AnnotatedParameter getConstructorParameter() {
    if (this._ctorParameters == null)
      return null; 
    POJOPropertyBuilder$Node pOJOPropertyBuilder$Node = this._ctorParameters;
    while (true) {
      if (((AnnotatedParameter)pOJOPropertyBuilder$Node.value).getOwner() instanceof AnnotatedConstructor)
        return (AnnotatedParameter)pOJOPropertyBuilder$Node.value; 
      pOJOPropertyBuilder$Node = pOJOPropertyBuilder$Node.next;
      if (pOJOPropertyBuilder$Node == null)
        return (AnnotatedParameter)this._ctorParameters.value; 
    } 
  }
  
  public AnnotatedField getField() {
    AnnotatedField annotatedField2;
    if (this._fields == null)
      return null; 
    AnnotatedField annotatedField1 = (AnnotatedField)this._fields.value;
    for (POJOPropertyBuilder$Node pOJOPropertyBuilder$Node = this._fields.next;; pOJOPropertyBuilder$Node = pOJOPropertyBuilder$Node.next) {
      annotatedField2 = annotatedField1;
      if (pOJOPropertyBuilder$Node != null) {
        annotatedField2 = (AnnotatedField)pOJOPropertyBuilder$Node.value;
        Class<?> clazz1 = annotatedField1.getDeclaringClass();
        Class<?> clazz2 = annotatedField2.getDeclaringClass();
        if (clazz1 != clazz2) {
          if (clazz1.isAssignableFrom(clazz2)) {
            annotatedField1 = annotatedField2;
            continue;
          } 
          if (clazz2.isAssignableFrom(clazz1))
            continue; 
        } 
        break;
      } 
      return annotatedField2;
    } 
    throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + annotatedField1.getFullName() + " vs " + annotatedField2.getFullName());
  }
  
  public AnnotatedMethod getGetter() {
    AnnotatedMethod annotatedMethod2;
    if (this._getters == null)
      return null; 
    AnnotatedMethod annotatedMethod1 = (AnnotatedMethod)this._getters.value;
    for (POJOPropertyBuilder$Node pOJOPropertyBuilder$Node = this._getters.next;; pOJOPropertyBuilder$Node = pOJOPropertyBuilder$Node.next) {
      annotatedMethod2 = annotatedMethod1;
      if (pOJOPropertyBuilder$Node != null) {
        annotatedMethod2 = (AnnotatedMethod)pOJOPropertyBuilder$Node.value;
        Class<?> clazz2 = annotatedMethod1.getDeclaringClass();
        Class<?> clazz1 = annotatedMethod2.getDeclaringClass();
        if (clazz2 != clazz1) {
          if (clazz2.isAssignableFrom(clazz1)) {
            annotatedMethod1 = annotatedMethod2;
            continue;
          } 
          if (clazz1.isAssignableFrom(clazz2))
            continue; 
        } 
        break;
      } 
      return annotatedMethod2;
    } 
    throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + annotatedMethod1.getFullName() + " vs " + annotatedMethod2.getFullName());
  }
  
  public String getInternalName() {
    return this._internalName;
  }
  
  public AnnotatedMember getMutator() {
    AnnotatedField annotatedField;
    AnnotatedParameter annotatedParameter2 = getConstructorParameter();
    AnnotatedParameter annotatedParameter1 = annotatedParameter2;
    if (annotatedParameter2 == null) {
      AnnotatedMethod annotatedMethod2 = getSetter();
      AnnotatedMethod annotatedMethod1 = annotatedMethod2;
      if (annotatedMethod2 == null)
        annotatedField = getField(); 
    } 
    return annotatedField;
  }
  
  public String getName() {
    return this._name;
  }
  
  public AnnotatedMethod getSetter() {
    AnnotatedMethod annotatedMethod2;
    if (this._setters == null)
      return null; 
    AnnotatedMethod annotatedMethod1 = (AnnotatedMethod)this._setters.value;
    for (POJOPropertyBuilder$Node pOJOPropertyBuilder$Node = this._setters.next;; pOJOPropertyBuilder$Node = pOJOPropertyBuilder$Node.next) {
      annotatedMethod2 = annotatedMethod1;
      if (pOJOPropertyBuilder$Node != null) {
        annotatedMethod2 = (AnnotatedMethod)pOJOPropertyBuilder$Node.value;
        Class<?> clazz2 = annotatedMethod1.getDeclaringClass();
        Class<?> clazz1 = annotatedMethod2.getDeclaringClass();
        if (clazz2 != clazz1) {
          if (clazz2.isAssignableFrom(clazz1)) {
            annotatedMethod1 = annotatedMethod2;
            continue;
          } 
          if (clazz1.isAssignableFrom(clazz2))
            continue; 
        } 
        break;
      } 
      return annotatedMethod2;
    } 
    throw new IllegalArgumentException("Conflicting setter definitions for property \"" + getName() + "\": " + annotatedMethod1.getFullName() + " vs " + annotatedMethod2.getFullName());
  }
  
  public boolean hasConstructorParameter() {
    return (this._ctorParameters != null);
  }
  
  public boolean hasField() {
    return (this._fields != null);
  }
  
  public boolean hasGetter() {
    return (this._getters != null);
  }
  
  public boolean hasSetter() {
    return (this._setters != null);
  }
  
  public void mergeAnnotations(boolean paramBoolean) {
    if (paramBoolean) {
      if (this._getters != null) {
        AnnotationMap annotationMap = _mergeAnnotations(0, new POJOPropertyBuilder$Node[] { this._getters, this._fields, this._ctorParameters, this._setters });
        this._getters = this._getters.withValue(((AnnotatedMethod)this._getters.value).withAnnotations(annotationMap));
        return;
      } 
      if (this._fields != null) {
        AnnotationMap annotationMap = _mergeAnnotations(0, new POJOPropertyBuilder$Node[] { this._fields, this._ctorParameters, this._setters });
        this._fields = this._fields.withValue(((AnnotatedField)this._fields.value).withAnnotations(annotationMap));
      } 
      return;
    } 
    if (this._ctorParameters != null) {
      AnnotationMap annotationMap = _mergeAnnotations(0, new POJOPropertyBuilder$Node[] { this._ctorParameters, this._setters, this._fields, this._getters });
      this._ctorParameters = this._ctorParameters.withValue(((AnnotatedParameter)this._ctorParameters.value).withAnnotations(annotationMap));
      return;
    } 
    if (this._setters != null) {
      AnnotationMap annotationMap = _mergeAnnotations(0, new POJOPropertyBuilder$Node[] { this._setters, this._fields, this._getters });
      this._setters = this._setters.withValue(((AnnotatedMethod)this._setters.value).withAnnotations(annotationMap));
      return;
    } 
    if (this._fields != null) {
      AnnotationMap annotationMap = _mergeAnnotations(0, new POJOPropertyBuilder$Node[] { this._fields, this._getters });
      this._fields = this._fields.withValue(((AnnotatedField)this._fields.value).withAnnotations(annotationMap));
    } 
  }
  
  public void removeIgnored() {
    this._fields = _removeIgnored(this._fields);
    this._getters = _removeIgnored(this._getters);
    this._setters = _removeIgnored(this._setters);
    this._ctorParameters = _removeIgnored(this._ctorParameters);
  }
  
  public void removeNonVisible() {
    this._getters = _removeNonVisible(this._getters);
    this._ctorParameters = _removeNonVisible(this._ctorParameters);
    if (this._getters == null) {
      this._fields = _removeNonVisible(this._fields);
      this._setters = _removeNonVisible(this._setters);
    } 
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[Property '").append(this._name).append("'; ctors: ").append(this._ctorParameters).append(", field(s): ").append(this._fields).append(", getter(s): ").append(this._getters).append(", setter(s): ").append(this._setters);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void trimByVisibility() {
    this._fields = _trimByVisibility(this._fields);
    this._getters = _trimByVisibility(this._getters);
    this._setters = _trimByVisibility(this._setters);
    this._ctorParameters = _trimByVisibility(this._ctorParameters);
  }
  
  public POJOPropertyBuilder withName(String paramString) {
    return new POJOPropertyBuilder(this, paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\POJOPropertyBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */