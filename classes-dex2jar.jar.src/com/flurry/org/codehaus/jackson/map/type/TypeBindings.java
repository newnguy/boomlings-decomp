package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeBindings {
  private static final JavaType[] NO_TYPES = new JavaType[0];
  
  public static final JavaType UNBOUND = new SimpleType(Object.class);
  
  protected Map _bindings;
  
  protected final Class _contextClass;
  
  protected final JavaType _contextType;
  
  private final TypeBindings _parentBindings;
  
  protected HashSet _placeholders;
  
  protected final TypeFactory _typeFactory;
  
  private TypeBindings(TypeFactory paramTypeFactory, TypeBindings paramTypeBindings, Class paramClass, JavaType paramJavaType) {
    this._typeFactory = paramTypeFactory;
    this._parentBindings = paramTypeBindings;
    this._contextClass = paramClass;
    this._contextType = paramJavaType;
  }
  
  public TypeBindings(TypeFactory paramTypeFactory, JavaType paramJavaType) {
    this(paramTypeFactory, null, paramJavaType.getRawClass(), paramJavaType);
  }
  
  public TypeBindings(TypeFactory paramTypeFactory, Class paramClass) {
    this(paramTypeFactory, null, paramClass, null);
  }
  
  public void _addPlaceholder(String paramString) {
    if (this._placeholders == null)
      this._placeholders = new HashSet(); 
    this._placeholders.add(paramString);
  }
  
  protected void _resolve() {
    _resolveBindings(this._contextClass);
    if (this._contextType != null) {
      int i = this._contextType.containedTypeCount();
      if (i > 0) {
        if (this._bindings == null)
          this._bindings = new LinkedHashMap<Object, Object>(); 
        for (byte b = 0; b < i; b++) {
          String str = this._contextType.containedTypeName(b);
          JavaType javaType = this._contextType.containedType(b);
          this._bindings.put(str, javaType);
        } 
      } 
    } 
    if (this._bindings == null)
      this._bindings = Collections.emptyMap(); 
  }
  
  protected void _resolveBindings(Type paramType) {
    byte b = 0;
    if (paramType != null) {
      if (paramType instanceof java.lang.reflect.ParameterizedType) {
        paramType = paramType;
        Type[] arrayOfType1 = paramType.getActualTypeArguments();
        if (arrayOfType1 != null && arrayOfType1.length > 0) {
          Class clazz = (Class)paramType.getRawType();
          TypeVariable[] arrayOfTypeVariable = clazz.getTypeParameters();
          if (arrayOfTypeVariable.length != arrayOfType1.length)
            throw new IllegalArgumentException("Strange parametrized type (in class " + clazz.getName() + "): number of type arguments != number of type parameters (" + arrayOfType1.length + " vs " + arrayOfTypeVariable.length + ")"); 
          int j = arrayOfType1.length;
          for (byte b2 = 0; b2 < j; b2++) {
            String str = arrayOfTypeVariable[b2].getName();
            if (this._bindings == null) {
              this._bindings = new LinkedHashMap<Object, Object>();
            } else if (this._bindings.containsKey(str)) {
              continue;
            } 
            _addPlaceholder(str);
            this._bindings.put(str, this._typeFactory._constructType(arrayOfType1[b2], this));
            continue;
          } 
        } 
        paramType = paramType.getRawType();
      } else if (paramType instanceof Class) {
        Class clazz = (Class)paramType;
        _resolveBindings(clazz.getDeclaringClass());
        TypeVariable[] arrayOfTypeVariable = clazz.getTypeParameters();
        if (arrayOfTypeVariable != null && arrayOfTypeVariable.length > 0) {
          JavaType[] arrayOfJavaType;
          Type type = null;
          paramType = type;
          if (this._contextType != null) {
            paramType = type;
            if (clazz.isAssignableFrom(this._contextType.getRawClass()))
              arrayOfJavaType = this._typeFactory.findTypeParameters(this._contextType, clazz); 
          } 
          for (byte b2 = 0; b2 < arrayOfTypeVariable.length; b2++) {
            TypeVariable typeVariable = arrayOfTypeVariable[b2];
            String str = typeVariable.getName();
            Type type1 = typeVariable.getBounds()[0];
            if (type1 != null) {
              if (this._bindings == null) {
                this._bindings = new LinkedHashMap<Object, Object>();
              } else if (this._bindings.containsKey(str)) {
                continue;
              } 
              _addPlaceholder(str);
              if (arrayOfJavaType != null) {
                this._bindings.put(str, arrayOfJavaType[b2]);
              } else {
                this._bindings.put(str, this._typeFactory._constructType(type1, this));
              } 
            } 
            continue;
          } 
        } 
        paramType = clazz;
      } else {
        return;
      } 
      _resolveBindings(paramType.getGenericSuperclass());
      Type[] arrayOfType = paramType.getGenericInterfaces();
      int i = arrayOfType.length;
      byte b1 = b;
      while (true) {
        if (b1 < i) {
          _resolveBindings(arrayOfType[b1]);
          b1++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void addBinding(String paramString, JavaType paramJavaType) {
    if (this._bindings == null || this._bindings.size() == 0)
      this._bindings = new LinkedHashMap<Object, Object>(); 
    this._bindings.put(paramString, paramJavaType);
  }
  
  public TypeBindings childInstance() {
    return new TypeBindings(this._typeFactory, this, this._contextClass, this._contextType);
  }
  
  public JavaType findType(String paramString) {
    if (this._bindings == null)
      _resolve(); 
    JavaType javaType = (JavaType)this._bindings.get(paramString);
    if (javaType != null)
      return javaType; 
    if (this._placeholders != null && this._placeholders.contains(paramString))
      return UNBOUND; 
    if (this._parentBindings != null)
      return this._parentBindings.findType(paramString); 
    if (this._contextClass != null && this._contextClass.getEnclosingClass() != null && !Modifier.isStatic(this._contextClass.getModifiers()))
      return UNBOUND; 
    if (this._contextClass != null) {
      String str1 = this._contextClass.getName();
      throw new IllegalArgumentException("Type variable '" + paramString + "' can not be resolved (with context of class " + str1 + ")");
    } 
    if (this._contextType != null) {
      String str1 = this._contextType.toString();
      throw new IllegalArgumentException("Type variable '" + paramString + "' can not be resolved (with context of class " + str1 + ")");
    } 
    String str = "UNKNOWN";
    throw new IllegalArgumentException("Type variable '" + paramString + "' can not be resolved (with context of class " + str + ")");
  }
  
  public int getBindingCount() {
    if (this._bindings == null)
      _resolve(); 
    return this._bindings.size();
  }
  
  public JavaType resolveType(Class paramClass) {
    return this._typeFactory._constructType(paramClass, this);
  }
  
  public JavaType resolveType(Type paramType) {
    return this._typeFactory._constructType(paramType, this);
  }
  
  public String toString() {
    if (this._bindings == null)
      _resolve(); 
    StringBuilder stringBuilder = new StringBuilder("[TypeBindings for ");
    if (this._contextType != null) {
      stringBuilder.append(this._contextType.toString());
      stringBuilder.append(": ").append(this._bindings).append("]");
      return stringBuilder.toString();
    } 
    stringBuilder.append(this._contextClass.getName());
    stringBuilder.append(": ").append(this._bindings).append("]");
    return stringBuilder.toString();
  }
  
  public JavaType[] typesAsArray() {
    if (this._bindings == null)
      _resolve(); 
    return (this._bindings.size() == 0) ? NO_TYPES : (JavaType[])this._bindings.values().toArray((Object[])new JavaType[this._bindings.size()]);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\TypeBindings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */