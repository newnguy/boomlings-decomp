package com.flurry.org.codehaus.jackson.map.jsontype;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import java.util.Collection;

public abstract class SubtypeResolver {
  public abstract Collection collectAndResolveSubtypes(AnnotatedClass paramAnnotatedClass, MapperConfig paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract Collection collectAndResolveSubtypes(AnnotatedMember paramAnnotatedMember, MapperConfig paramMapperConfig, AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract void registerSubtypes(NamedType... paramVarArgs);
  
  public abstract void registerSubtypes(Class... paramVarArgs);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\SubtypeResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */