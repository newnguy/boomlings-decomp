package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BeanDescription {
  protected final JavaType _type;
  
  protected BeanDescription(JavaType paramJavaType) {
    this._type = paramJavaType;
  }
  
  public abstract TypeBindings bindingsForBeanType();
  
  public abstract AnnotatedMethod findAnyGetter();
  
  public abstract AnnotatedMethod findAnySetter();
  
  public abstract AnnotatedConstructor findDefaultConstructor();
  
  @Deprecated
  public abstract LinkedHashMap findDeserializableFields(VisibilityChecker paramVisibilityChecker, Collection paramCollection);
  
  @Deprecated
  public abstract LinkedHashMap findGetters(VisibilityChecker paramVisibilityChecker, Collection paramCollection);
  
  public abstract Map findInjectables();
  
  public abstract AnnotatedMethod findJsonValueMethod();
  
  public abstract List findProperties();
  
  @Deprecated
  public abstract Map findSerializableFields(VisibilityChecker paramVisibilityChecker, Collection paramCollection);
  
  @Deprecated
  public abstract LinkedHashMap findSetters(VisibilityChecker paramVisibilityChecker);
  
  public Class getBeanClass() {
    return this._type.getRawClass();
  }
  
  public abstract Annotations getClassAnnotations();
  
  public abstract AnnotatedClass getClassInfo();
  
  public abstract Set getIgnoredPropertyNames();
  
  public JavaType getType() {
    return this._type;
  }
  
  public abstract boolean hasKnownClassAnnotations();
  
  public abstract JavaType resolveType(Type paramType);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\BeanDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */