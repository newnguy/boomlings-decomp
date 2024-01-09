package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

public final class SettableBeanProperty$ManagedReferenceProperty extends SettableBeanProperty {
  protected final SettableBeanProperty _backProperty;
  
  protected final boolean _isContainer;
  
  protected final SettableBeanProperty _managedProperty;
  
  protected final String _referenceName;
  
  protected SettableBeanProperty$ManagedReferenceProperty(SettableBeanProperty$ManagedReferenceProperty paramSettableBeanProperty$ManagedReferenceProperty, JsonDeserializer paramJsonDeserializer) {
    super(paramSettableBeanProperty$ManagedReferenceProperty, paramJsonDeserializer);
    this._referenceName = paramSettableBeanProperty$ManagedReferenceProperty._referenceName;
    this._isContainer = paramSettableBeanProperty$ManagedReferenceProperty._isContainer;
    this._managedProperty = paramSettableBeanProperty$ManagedReferenceProperty._managedProperty;
    this._backProperty = paramSettableBeanProperty$ManagedReferenceProperty._backProperty;
  }
  
  public SettableBeanProperty$ManagedReferenceProperty(String paramString, SettableBeanProperty paramSettableBeanProperty1, SettableBeanProperty paramSettableBeanProperty2, Annotations paramAnnotations, boolean paramBoolean) {
    super(paramSettableBeanProperty1.getName(), paramSettableBeanProperty1.getType(), paramSettableBeanProperty1._valueTypeDeserializer, paramAnnotations);
    this._referenceName = paramString;
    this._managedProperty = paramSettableBeanProperty1;
    this._backProperty = paramSettableBeanProperty2;
    this._isContainer = paramBoolean;
  }
  
  public void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    set(paramObject, this._managedProperty.deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return this._managedProperty.getAnnotation(paramClass);
  }
  
  public AnnotatedMember getMember() {
    return this._managedProperty.getMember();
  }
  
  public final void set(Object paramObject1, Object paramObject2) {
    this._managedProperty.set(paramObject1, paramObject2);
    if (paramObject2 != null)
      if (this._isContainer) {
        if (paramObject2 instanceof Object[]) {
          paramObject2 = paramObject2;
          int i = paramObject2.length;
          for (byte b = 0; b < i; b++) {
            Object object = paramObject2[b];
            if (object != null)
              this._backProperty.set(object, paramObject1); 
          } 
        } else if (paramObject2 instanceof Collection) {
          paramObject2 = ((Collection)paramObject2).iterator();
          while (paramObject2.hasNext()) {
            Object object = paramObject2.next();
            if (object != null)
              this._backProperty.set(object, paramObject1); 
          } 
        } else if (paramObject2 instanceof Map) {
          paramObject2 = ((Map)paramObject2).values().iterator();
          while (paramObject2.hasNext()) {
            Object object = paramObject2.next();
            if (object != null)
              this._backProperty.set(object, paramObject1); 
          } 
        } else {
          throw new IllegalStateException("Unsupported container type (" + paramObject2.getClass().getName() + ") when resolving reference '" + this._referenceName + "'");
        } 
      } else {
        this._backProperty.set(paramObject2, paramObject1);
      }  
  }
  
  public SettableBeanProperty$ManagedReferenceProperty withValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    return new SettableBeanProperty$ManagedReferenceProperty(this, paramJsonDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\SettableBeanProperty$ManagedReferenceProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */