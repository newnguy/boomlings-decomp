package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;

abstract class MapperConfig$Impl extends MapperConfig {
  protected int _featureFlags;
  
  protected MapperConfig$Impl(ClassIntrospector paramClassIntrospector, AnnotationIntrospector paramAnnotationIntrospector, VisibilityChecker paramVisibilityChecker, SubtypeResolver paramSubtypeResolver, PropertyNamingStrategy paramPropertyNamingStrategy, TypeFactory paramTypeFactory, HandlerInstantiator paramHandlerInstantiator, int paramInt) {
    super(paramClassIntrospector, paramAnnotationIntrospector, paramVisibilityChecker, paramSubtypeResolver, paramPropertyNamingStrategy, paramTypeFactory, paramHandlerInstantiator);
    this._featureFlags = paramInt;
  }
  
  protected MapperConfig$Impl(MapperConfig$Impl paramMapperConfig$Impl) {
    super(paramMapperConfig$Impl);
    this._featureFlags = paramMapperConfig$Impl._featureFlags;
  }
  
  protected MapperConfig$Impl(MapperConfig$Impl paramMapperConfig$Impl, int paramInt) {
    super(paramMapperConfig$Impl);
    this._featureFlags = paramInt;
  }
  
  protected MapperConfig$Impl(MapperConfig$Impl paramMapperConfig$Impl, MapperConfig$Base paramMapperConfig$Base, SubtypeResolver paramSubtypeResolver) {
    super(paramMapperConfig$Impl, paramMapperConfig$Base, paramSubtypeResolver);
    this._featureFlags = paramMapperConfig$Impl._featureFlags;
  }
  
  static int collectFeatureDefaults(Class<Enum> paramClass) {
    Enum[] arrayOfEnum = paramClass.getEnumConstants();
    int j = arrayOfEnum.length;
    byte b = 0;
    int i = 0;
    while (b < j) {
      Enum enum_ = arrayOfEnum[b];
      if (((MapperConfig$ConfigFeature)enum_).enabledByDefault())
        i = ((MapperConfig$ConfigFeature)enum_).getMask() | i; 
      b++;
    } 
    return i;
  }
  
  @Deprecated
  public void disable(MapperConfig$ConfigFeature paramMapperConfig$ConfigFeature) {
    this._featureFlags &= paramMapperConfig$ConfigFeature.getMask() ^ 0xFFFFFFFF;
  }
  
  @Deprecated
  public void enable(MapperConfig$ConfigFeature paramMapperConfig$ConfigFeature) {
    this._featureFlags |= paramMapperConfig$ConfigFeature.getMask();
  }
  
  public boolean isEnabled(MapperConfig$ConfigFeature paramMapperConfig$ConfigFeature) {
    return ((this._featureFlags & paramMapperConfig$ConfigFeature.getMask()) != 0);
  }
  
  @Deprecated
  public void set(MapperConfig$ConfigFeature paramMapperConfig$ConfigFeature, boolean paramBoolean) {
    if (paramBoolean) {
      enable(paramMapperConfig$ConfigFeature);
      return;
    } 
    disable(paramMapperConfig$ConfigFeature);
  }
  
  public abstract MapperConfig$Impl with(MapperConfig$ConfigFeature... paramVarArgs);
  
  public abstract MapperConfig$Impl without(MapperConfig$ConfigFeature... paramVarArgs);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\MapperConfig$Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */