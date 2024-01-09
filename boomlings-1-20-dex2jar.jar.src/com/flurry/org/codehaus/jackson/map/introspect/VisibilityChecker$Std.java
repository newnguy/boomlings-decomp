package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, isGetterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, setterVisibility = JsonAutoDetect.Visibility.ANY)
public class VisibilityChecker$Std implements VisibilityChecker {
  protected static final VisibilityChecker$Std DEFAULT = new VisibilityChecker$Std(VisibilityChecker$Std.class.<JsonAutoDetect>getAnnotation(JsonAutoDetect.class));
  
  protected final JsonAutoDetect.Visibility _creatorMinLevel;
  
  protected final JsonAutoDetect.Visibility _fieldMinLevel;
  
  protected final JsonAutoDetect.Visibility _getterMinLevel;
  
  protected final JsonAutoDetect.Visibility _isGetterMinLevel;
  
  protected final JsonAutoDetect.Visibility _setterMinLevel;
  
  public VisibilityChecker$Std(JsonAutoDetect.Visibility paramVisibility) {
    if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT) {
      this._getterMinLevel = DEFAULT._getterMinLevel;
      this._isGetterMinLevel = DEFAULT._isGetterMinLevel;
      this._setterMinLevel = DEFAULT._setterMinLevel;
      this._creatorMinLevel = DEFAULT._creatorMinLevel;
      this._fieldMinLevel = DEFAULT._fieldMinLevel;
      return;
    } 
    this._getterMinLevel = paramVisibility;
    this._isGetterMinLevel = paramVisibility;
    this._setterMinLevel = paramVisibility;
    this._creatorMinLevel = paramVisibility;
    this._fieldMinLevel = paramVisibility;
  }
  
  public VisibilityChecker$Std(JsonAutoDetect.Visibility paramVisibility1, JsonAutoDetect.Visibility paramVisibility2, JsonAutoDetect.Visibility paramVisibility3, JsonAutoDetect.Visibility paramVisibility4, JsonAutoDetect.Visibility paramVisibility5) {
    this._getterMinLevel = paramVisibility1;
    this._isGetterMinLevel = paramVisibility2;
    this._setterMinLevel = paramVisibility3;
    this._creatorMinLevel = paramVisibility4;
    this._fieldMinLevel = paramVisibility5;
  }
  
  public VisibilityChecker$Std(JsonAutoDetect paramJsonAutoDetect) {
    JsonAutoDetect.Visibility visibility1;
    JsonAutoDetect.Visibility visibility2;
    JsonMethod[] arrayOfJsonMethod = paramJsonAutoDetect.value();
    if (hasMethod(arrayOfJsonMethod, JsonMethod.GETTER)) {
      visibility2 = paramJsonAutoDetect.getterVisibility();
    } else {
      visibility2 = JsonAutoDetect.Visibility.NONE;
    } 
    this._getterMinLevel = visibility2;
    if (hasMethod(arrayOfJsonMethod, JsonMethod.IS_GETTER)) {
      visibility2 = paramJsonAutoDetect.isGetterVisibility();
    } else {
      visibility2 = JsonAutoDetect.Visibility.NONE;
    } 
    this._isGetterMinLevel = visibility2;
    if (hasMethod(arrayOfJsonMethod, JsonMethod.SETTER)) {
      visibility2 = paramJsonAutoDetect.setterVisibility();
    } else {
      visibility2 = JsonAutoDetect.Visibility.NONE;
    } 
    this._setterMinLevel = visibility2;
    if (hasMethod(arrayOfJsonMethod, JsonMethod.CREATOR)) {
      visibility2 = paramJsonAutoDetect.creatorVisibility();
    } else {
      visibility2 = JsonAutoDetect.Visibility.NONE;
    } 
    this._creatorMinLevel = visibility2;
    if (hasMethod(arrayOfJsonMethod, JsonMethod.FIELD)) {
      visibility1 = paramJsonAutoDetect.fieldVisibility();
    } else {
      visibility1 = JsonAutoDetect.Visibility.NONE;
    } 
    this._fieldMinLevel = visibility1;
  }
  
  public static VisibilityChecker$Std defaultInstance() {
    return DEFAULT;
  }
  
  private static boolean hasMethod(JsonMethod[] paramArrayOfJsonMethod, JsonMethod paramJsonMethod) {
    boolean bool = false;
    int i = paramArrayOfJsonMethod.length;
    for (byte b = 0;; b++) {
      boolean bool1 = bool;
      if (b < i) {
        JsonMethod jsonMethod = paramArrayOfJsonMethod[b];
        if (jsonMethod == paramJsonMethod || jsonMethod == JsonMethod.ALL)
          return true; 
      } else {
        return bool1;
      } 
    } 
  }
  
  public boolean isCreatorVisible(AnnotatedMember paramAnnotatedMember) {
    return isCreatorVisible(paramAnnotatedMember.getMember());
  }
  
  public boolean isCreatorVisible(Member paramMember) {
    return this._creatorMinLevel.isVisible(paramMember);
  }
  
  public boolean isFieldVisible(AnnotatedField paramAnnotatedField) {
    return isFieldVisible(paramAnnotatedField.getAnnotated());
  }
  
  public boolean isFieldVisible(Field paramField) {
    return this._fieldMinLevel.isVisible(paramField);
  }
  
  public boolean isGetterVisible(AnnotatedMethod paramAnnotatedMethod) {
    return isGetterVisible(paramAnnotatedMethod.getAnnotated());
  }
  
  public boolean isGetterVisible(Method paramMethod) {
    return this._getterMinLevel.isVisible(paramMethod);
  }
  
  public boolean isIsGetterVisible(AnnotatedMethod paramAnnotatedMethod) {
    return isIsGetterVisible(paramAnnotatedMethod.getAnnotated());
  }
  
  public boolean isIsGetterVisible(Method paramMethod) {
    return this._isGetterMinLevel.isVisible(paramMethod);
  }
  
  public boolean isSetterVisible(AnnotatedMethod paramAnnotatedMethod) {
    return isSetterVisible(paramAnnotatedMethod.getAnnotated());
  }
  
  public boolean isSetterVisible(Method paramMethod) {
    return this._setterMinLevel.isVisible(paramMethod);
  }
  
  public String toString() {
    return "[Visibility:" + " getter: " + this._getterMinLevel + ", isGetter: " + this._isGetterMinLevel + ", setter: " + this._setterMinLevel + ", creator: " + this._creatorMinLevel + ", field: " + this._fieldMinLevel + "]";
  }
  
  public VisibilityChecker$Std with(JsonAutoDetect.Visibility paramVisibility) {
    return (paramVisibility == JsonAutoDetect.Visibility.DEFAULT) ? DEFAULT : new VisibilityChecker$Std(paramVisibility);
  }
  
  public VisibilityChecker$Std with(JsonAutoDetect paramJsonAutoDetect) {
    JsonAutoDetect.Visibility visibility1;
    JsonAutoDetect.Visibility visibility2;
    if (paramJsonAutoDetect == null)
      return this; 
    JsonMethod[] arrayOfJsonMethod = paramJsonAutoDetect.value();
    if (hasMethod(arrayOfJsonMethod, JsonMethod.GETTER)) {
      visibility2 = paramJsonAutoDetect.getterVisibility();
    } else {
      visibility2 = JsonAutoDetect.Visibility.NONE;
    } 
    VisibilityChecker$Std visibilityChecker$Std2 = withGetterVisibility(visibility2);
    if (hasMethod(arrayOfJsonMethod, JsonMethod.IS_GETTER)) {
      visibility2 = paramJsonAutoDetect.isGetterVisibility();
    } else {
      visibility2 = JsonAutoDetect.Visibility.NONE;
    } 
    visibilityChecker$Std2 = visibilityChecker$Std2.withIsGetterVisibility(visibility2);
    if (hasMethod(arrayOfJsonMethod, JsonMethod.SETTER)) {
      visibility2 = paramJsonAutoDetect.setterVisibility();
    } else {
      visibility2 = JsonAutoDetect.Visibility.NONE;
    } 
    visibilityChecker$Std2 = visibilityChecker$Std2.withSetterVisibility(visibility2);
    if (hasMethod(arrayOfJsonMethod, JsonMethod.CREATOR)) {
      visibility2 = paramJsonAutoDetect.creatorVisibility();
    } else {
      visibility2 = JsonAutoDetect.Visibility.NONE;
    } 
    VisibilityChecker$Std visibilityChecker$Std1 = visibilityChecker$Std2.withCreatorVisibility(visibility2);
    if (hasMethod(arrayOfJsonMethod, JsonMethod.FIELD)) {
      visibility1 = paramJsonAutoDetect.fieldVisibility();
    } else {
      visibility1 = JsonAutoDetect.Visibility.NONE;
    } 
    return visibilityChecker$Std1.withFieldVisibility(visibility1);
  }
  
  public VisibilityChecker$Std withCreatorVisibility(JsonAutoDetect.Visibility paramVisibility) {
    if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT)
      paramVisibility = DEFAULT._creatorMinLevel; 
    return (this._creatorMinLevel == paramVisibility) ? this : new VisibilityChecker$Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, paramVisibility, this._fieldMinLevel);
  }
  
  public VisibilityChecker$Std withFieldVisibility(JsonAutoDetect.Visibility paramVisibility) {
    if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT)
      paramVisibility = DEFAULT._fieldMinLevel; 
    return (this._fieldMinLevel == paramVisibility) ? this : new VisibilityChecker$Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, paramVisibility);
  }
  
  public VisibilityChecker$Std withGetterVisibility(JsonAutoDetect.Visibility paramVisibility) {
    if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT)
      paramVisibility = DEFAULT._getterMinLevel; 
    return (this._getterMinLevel == paramVisibility) ? this : new VisibilityChecker$Std(paramVisibility, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
  }
  
  public VisibilityChecker$Std withIsGetterVisibility(JsonAutoDetect.Visibility paramVisibility) {
    if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT)
      paramVisibility = DEFAULT._isGetterMinLevel; 
    return (this._isGetterMinLevel == paramVisibility) ? this : new VisibilityChecker$Std(this._getterMinLevel, paramVisibility, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
  }
  
  public VisibilityChecker$Std withSetterVisibility(JsonAutoDetect.Visibility paramVisibility) {
    if (paramVisibility == JsonAutoDetect.Visibility.DEFAULT)
      paramVisibility = DEFAULT._setterMinLevel; 
    return (this._setterMinLevel == paramVisibility) ? this : new VisibilityChecker$Std(this._getterMinLevel, this._isGetterMinLevel, paramVisibility, this._creatorMinLevel, this._fieldMinLevel);
  }
  
  public VisibilityChecker$Std withVisibility(JsonMethod paramJsonMethod, JsonAutoDetect.Visibility paramVisibility) {
    switch (VisibilityChecker$1.$SwitchMap$org$codehaus$jackson$annotate$JsonMethod[paramJsonMethod.ordinal()]) {
      default:
        return this;
      case 1:
        return withGetterVisibility(paramVisibility);
      case 2:
        return withSetterVisibility(paramVisibility);
      case 3:
        return withCreatorVisibility(paramVisibility);
      case 4:
        return withFieldVisibility(paramVisibility);
      case 5:
        return withIsGetterVisibility(paramVisibility);
      case 6:
        break;
    } 
    return with(paramVisibility);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\VisibilityChecker$Std.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */