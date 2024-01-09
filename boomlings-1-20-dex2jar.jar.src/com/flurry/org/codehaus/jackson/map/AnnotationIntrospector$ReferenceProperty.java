package com.flurry.org.codehaus.jackson.map;

public class AnnotationIntrospector$ReferenceProperty {
  private final String _name;
  
  private final AnnotationIntrospector$ReferenceProperty$Type _type;
  
  public AnnotationIntrospector$ReferenceProperty(AnnotationIntrospector$ReferenceProperty$Type paramAnnotationIntrospector$ReferenceProperty$Type, String paramString) {
    this._type = paramAnnotationIntrospector$ReferenceProperty$Type;
    this._name = paramString;
  }
  
  public static AnnotationIntrospector$ReferenceProperty back(String paramString) {
    return new AnnotationIntrospector$ReferenceProperty(AnnotationIntrospector$ReferenceProperty$Type.BACK_REFERENCE, paramString);
  }
  
  public static AnnotationIntrospector$ReferenceProperty managed(String paramString) {
    return new AnnotationIntrospector$ReferenceProperty(AnnotationIntrospector$ReferenceProperty$Type.MANAGED_REFERENCE, paramString);
  }
  
  public String getName() {
    return this._name;
  }
  
  public AnnotationIntrospector$ReferenceProperty$Type getType() {
    return this._type;
  }
  
  public boolean isBackReference() {
    return (this._type == AnnotationIntrospector$ReferenceProperty$Type.BACK_REFERENCE);
  }
  
  public boolean isManagedReference() {
    return (this._type == AnnotationIntrospector$ReferenceProperty$Type.MANAGED_REFERENCE);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\AnnotationIntrospector$ReferenceProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */