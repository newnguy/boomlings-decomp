package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.ClassIntrospector;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.jsontype.NamedType;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class StdSubtypeResolver extends SubtypeResolver {
  protected LinkedHashSet _registeredSubtypes;
  
  protected void _collectAndResolve(AnnotatedClass paramAnnotatedClass, NamedType paramNamedType, MapperConfig paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector, HashMap<NamedType, NamedType> paramHashMap) {
    NamedType namedType = paramNamedType;
    if (!paramNamedType.hasName()) {
      String str = paramAnnotationIntrospector.findTypeName(paramAnnotatedClass);
      namedType = paramNamedType;
      if (str != null)
        namedType = new NamedType(paramNamedType.getType(), str); 
    } 
    if (paramHashMap.containsKey(namedType)) {
      if (namedType.hasName() && !((NamedType)paramHashMap.get(namedType)).hasName())
        paramHashMap.put(namedType, namedType); 
      return;
    } 
    paramHashMap.put(namedType, namedType);
    List list = paramAnnotationIntrospector.findSubtypes((Annotated)paramAnnotatedClass);
    if (list != null && !list.isEmpty()) {
      Iterator<NamedType> iterator = list.iterator();
      while (true) {
        if (iterator.hasNext()) {
          NamedType namedType1 = iterator.next();
          AnnotatedClass annotatedClass = AnnotatedClass.constructWithoutSuperTypes(namedType1.getType(), paramAnnotationIntrospector, (ClassIntrospector.MixInResolver)paramMapperConfig);
          if (!namedType1.hasName())
            namedType1 = new NamedType(namedType1.getType(), paramAnnotationIntrospector.findTypeName(annotatedClass)); 
          _collectAndResolve(annotatedClass, namedType1, paramMapperConfig, paramAnnotationIntrospector, paramHashMap);
          continue;
        } 
        return;
      } 
    } 
  }
  
  public Collection collectAndResolveSubtypes(AnnotatedClass paramAnnotatedClass, MapperConfig paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    if (this._registeredSubtypes != null) {
      Class clazz = paramAnnotatedClass.getRawType();
      for (NamedType namedType : this._registeredSubtypes) {
        if (clazz.isAssignableFrom(namedType.getType()))
          _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), paramAnnotationIntrospector, (ClassIntrospector.MixInResolver)paramMapperConfig), namedType, paramMapperConfig, paramAnnotationIntrospector, hashMap); 
      } 
    } 
    _collectAndResolve(paramAnnotatedClass, new NamedType(paramAnnotatedClass.getRawType(), null), paramMapperConfig, paramAnnotationIntrospector, hashMap);
    return new ArrayList(hashMap.values());
  }
  
  public Collection collectAndResolveSubtypes(AnnotatedMember paramAnnotatedMember, MapperConfig paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector) {
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    if (this._registeredSubtypes != null) {
      Class clazz = paramAnnotatedMember.getRawType();
      for (NamedType namedType1 : this._registeredSubtypes) {
        if (clazz.isAssignableFrom(namedType1.getType()))
          _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType1.getType(), paramAnnotationIntrospector, (ClassIntrospector.MixInResolver)paramMapperConfig), namedType1, paramMapperConfig, paramAnnotationIntrospector, hashMap); 
      } 
    } 
    List list = paramAnnotationIntrospector.findSubtypes((Annotated)paramAnnotatedMember);
    if (list != null)
      for (NamedType namedType1 : list)
        _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType1.getType(), paramAnnotationIntrospector, (ClassIntrospector.MixInResolver)paramMapperConfig), namedType1, paramMapperConfig, paramAnnotationIntrospector, hashMap);  
    NamedType namedType = new NamedType(paramAnnotatedMember.getRawType(), null);
    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(paramAnnotatedMember.getRawType(), paramAnnotationIntrospector, (ClassIntrospector.MixInResolver)paramMapperConfig), namedType, paramMapperConfig, paramAnnotationIntrospector, hashMap);
    return new ArrayList(hashMap.values());
  }
  
  public void registerSubtypes(NamedType... paramVarArgs) {
    if (this._registeredSubtypes == null)
      this._registeredSubtypes = new LinkedHashSet(); 
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++) {
      NamedType namedType = paramVarArgs[b];
      this._registeredSubtypes.add(namedType);
    } 
  }
  
  public void registerSubtypes(Class... paramVarArgs) {
    NamedType[] arrayOfNamedType = new NamedType[paramVarArgs.length];
    byte b = 0;
    int i = paramVarArgs.length;
    while (b < i) {
      arrayOfNamedType[b] = new NamedType(paramVarArgs[b]);
      b++;
    } 
    registerSubtypes(arrayOfNamedType);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\StdSubtypeResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */