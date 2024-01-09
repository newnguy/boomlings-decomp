package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.ClassIntrospector;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class AnnotatedClass extends Annotated {
  private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
  
  protected final AnnotationIntrospector _annotationIntrospector;
  
  protected final Class _class;
  
  protected AnnotationMap _classAnnotations;
  
  protected List _constructors;
  
  protected List _creatorMethods;
  
  protected AnnotatedConstructor _defaultConstructor;
  
  protected List _fields;
  
  protected AnnotatedMethodMap _memberMethods;
  
  protected final ClassIntrospector.MixInResolver _mixInResolver;
  
  protected final Class _primaryMixIn;
  
  protected final List _superTypes;
  
  private AnnotatedClass(Class paramClass, List paramList, AnnotationIntrospector paramAnnotationIntrospector, ClassIntrospector.MixInResolver paramMixInResolver, AnnotationMap paramAnnotationMap) {
    this._class = paramClass;
    this._superTypes = paramList;
    this._annotationIntrospector = paramAnnotationIntrospector;
    this._mixInResolver = paramMixInResolver;
    if (this._mixInResolver == null) {
      paramClass = null;
    } else {
      paramClass = this._mixInResolver.findMixInClassFor(this._class);
    } 
    this._primaryMixIn = paramClass;
    this._classAnnotations = paramAnnotationMap;
  }
  
  private AnnotationMap _emptyAnnotationMap() {
    return new AnnotationMap();
  }
  
  private AnnotationMap[] _emptyAnnotationMaps(int paramInt) {
    if (paramInt == 0)
      return NO_ANNOTATION_MAPS; 
    AnnotationMap[] arrayOfAnnotationMap = new AnnotationMap[paramInt];
    byte b = 0;
    while (true) {
      AnnotationMap[] arrayOfAnnotationMap1 = arrayOfAnnotationMap;
      if (b < paramInt) {
        arrayOfAnnotationMap[b] = _emptyAnnotationMap();
        b++;
        continue;
      } 
      return arrayOfAnnotationMap1;
    } 
  }
  
  private boolean _isIncludableField(Field paramField) {
    boolean bool2 = false;
    if (paramField.isSynthetic())
      return bool2; 
    int i = paramField.getModifiers();
    boolean bool1 = bool2;
    if (!Modifier.isStatic(i)) {
      bool1 = bool2;
      if (!Modifier.isTransient(i))
        bool1 = true; 
    } 
    return bool1;
  }
  
  public static AnnotatedClass construct(Class paramClass, AnnotationIntrospector paramAnnotationIntrospector, ClassIntrospector.MixInResolver paramMixInResolver) {
    AnnotatedClass annotatedClass = new AnnotatedClass(paramClass, ClassUtil.findSuperTypes(paramClass, null), paramAnnotationIntrospector, paramMixInResolver, null);
    annotatedClass.resolveClassAnnotations();
    return annotatedClass;
  }
  
  public static AnnotatedClass constructWithoutSuperTypes(Class paramClass, AnnotationIntrospector paramAnnotationIntrospector, ClassIntrospector.MixInResolver paramMixInResolver) {
    AnnotatedClass annotatedClass = new AnnotatedClass(paramClass, Collections.emptyList(), paramAnnotationIntrospector, paramMixInResolver, null);
    annotatedClass.resolveClassAnnotations();
    return annotatedClass;
  }
  
  protected void _addClassMixIns(AnnotationMap paramAnnotationMap, Class paramClass) {
    if (this._mixInResolver != null)
      _addClassMixIns(paramAnnotationMap, paramClass, this._mixInResolver.findMixInClassFor(paramClass)); 
  }
  
  protected void _addClassMixIns(AnnotationMap paramAnnotationMap, Class paramClass1, Class paramClass2) {
    if (paramClass2 != null) {
      for (Annotation annotation : paramClass2.getDeclaredAnnotations()) {
        if (this._annotationIntrospector.isHandled(annotation))
          paramAnnotationMap.addIfNotPresent(annotation); 
      } 
      Iterator<Class<?>> iterator = ClassUtil.findSuperTypes(paramClass2, paramClass1).iterator();
      while (true) {
        if (iterator.hasNext()) {
          for (Annotation annotation : ((Class)iterator.next()).getDeclaredAnnotations()) {
            if (this._annotationIntrospector.isHandled(annotation))
              paramAnnotationMap.addIfNotPresent(annotation); 
          } 
          continue;
        } 
        return;
      } 
    } 
  }
  
  protected void _addConstructorMixIns(Class paramClass) {
    int i;
    if (this._constructors == null) {
      i = 0;
    } else {
      i = this._constructors.size();
    } 
    Constructor[] arrayOfConstructor = (Constructor[])paramClass.getDeclaredConstructors();
    int j = arrayOfConstructor.length;
    byte b = 0;
    paramClass = null;
    label33: while (true) {
      if (b < j) {
        MemberKey[] arrayOfMemberKey2;
        Constructor constructor = arrayOfConstructor[b];
        if ((constructor.getParameterTypes()).length == 0) {
          Class clazz = paramClass;
          if (this._defaultConstructor != null) {
            _addMixOvers(constructor, this._defaultConstructor, false);
            clazz = paramClass;
          } 
        } else {
          MemberKey[] arrayOfMemberKey;
          if (paramClass == null) {
            MemberKey[] arrayOfMemberKey3 = new MemberKey[i];
            byte b2 = 0;
            while (true) {
              arrayOfMemberKey = arrayOfMemberKey3;
              if (b2 < i) {
                arrayOfMemberKey3[b2] = new MemberKey(((AnnotatedConstructor)this._constructors.get(b2)).getAnnotated());
                b2++;
                continue;
              } 
              break;
            } 
          } 
          MemberKey memberKey = new MemberKey(constructor);
          byte b1 = 0;
          while (b1 < i) {
            if (!memberKey.equals(arrayOfMemberKey[b1])) {
              b1++;
              continue;
            } 
            _addMixOvers(constructor, this._constructors.get(b1), true);
            MemberKey[] arrayOfMemberKey3 = arrayOfMemberKey;
            continue label33;
          } 
          arrayOfMemberKey2 = arrayOfMemberKey;
        } 
        b++;
        MemberKey[] arrayOfMemberKey1 = arrayOfMemberKey2;
        continue;
      } 
      return;
    } 
  }
  
  protected void _addFactoryMixIns(Class paramClass) {
    Class clazz = null;
    int i = this._creatorMethods.size();
    Method[] arrayOfMethod = paramClass.getDeclaredMethods();
    int j = arrayOfMethod.length;
    byte b = 0;
    paramClass = clazz;
    label29: while (true) {
      if (b < j) {
        MemberKey[] arrayOfMemberKey2;
        Method method = arrayOfMethod[b];
        if (!Modifier.isStatic(method.getModifiers())) {
          clazz = paramClass;
        } else {
          clazz = paramClass;
          if ((method.getParameterTypes()).length != 0) {
            MemberKey[] arrayOfMemberKey;
            if (paramClass == null) {
              MemberKey[] arrayOfMemberKey3 = new MemberKey[i];
              byte b2 = 0;
              while (true) {
                arrayOfMemberKey = arrayOfMemberKey3;
                if (b2 < i) {
                  arrayOfMemberKey3[b2] = new MemberKey(((AnnotatedMethod)this._creatorMethods.get(b2)).getAnnotated());
                  b2++;
                  continue;
                } 
                break;
              } 
            } 
            MemberKey memberKey = new MemberKey(method);
            byte b1 = 0;
            while (b1 < i) {
              if (!memberKey.equals(arrayOfMemberKey[b1])) {
                b1++;
                continue;
              } 
              _addMixOvers(method, this._creatorMethods.get(b1), true);
              MemberKey[] arrayOfMemberKey3 = arrayOfMemberKey;
              continue label29;
            } 
            arrayOfMemberKey2 = arrayOfMemberKey;
          } 
        } 
        b++;
        MemberKey[] arrayOfMemberKey1 = arrayOfMemberKey2;
        continue;
      } 
      return;
    } 
  }
  
  protected void _addFieldMixIns(Class paramClass, Map paramMap) {
    Field[] arrayOfField = paramClass.getDeclaredFields();
    int i = arrayOfField.length;
    byte b = 0;
    label19: while (b < i) {
      Field field = arrayOfField[b];
      if (!_isIncludableField(field))
        continue; 
      AnnotatedField annotatedField = (AnnotatedField)paramMap.get(field.getName());
      if (annotatedField != null) {
        Annotation[] arrayOfAnnotation = field.getDeclaredAnnotations();
        int j = arrayOfAnnotation.length;
        byte b1 = 0;
        while (true) {
          if (b1 < j) {
            Annotation annotation = arrayOfAnnotation[b1];
            if (this._annotationIntrospector.isHandled(annotation))
              annotatedField.addOrOverride(annotation); 
            b1++;
            continue;
          } 
          b++;
          continue label19;
        } 
      } 
      continue;
    } 
  }
  
  protected void _addFields(Map<String, AnnotatedField> paramMap, Class paramClass) {
    Class clazz = paramClass.getSuperclass();
    if (clazz != null) {
      _addFields(paramMap, clazz);
      for (Field field : paramClass.getDeclaredFields()) {
        if (_isIncludableField(field))
          paramMap.put(field.getName(), _constructField(field)); 
      } 
      if (this._mixInResolver != null) {
        paramClass = this._mixInResolver.findMixInClassFor(paramClass);
        if (paramClass != null)
          _addFieldMixIns(paramClass, paramMap); 
      } 
    } 
  }
  
  protected void _addMemberMethods(Class paramClass1, MethodFilter paramMethodFilter, AnnotatedMethodMap paramAnnotatedMethodMap1, Class paramClass2, AnnotatedMethodMap paramAnnotatedMethodMap2) {
    if (paramClass2 != null)
      _addMethodMixIns(paramMethodFilter, paramAnnotatedMethodMap1, paramClass2, paramAnnotatedMethodMap2); 
    if (paramClass1 != null) {
      Method[] arrayOfMethod = paramClass1.getDeclaredMethods();
      int i = arrayOfMethod.length;
      byte b = 0;
      while (true) {
        if (b < i) {
          Method method = arrayOfMethod[b];
          if (_isIncludableMethod(method, paramMethodFilter)) {
            AnnotatedMethod annotatedMethod2;
            AnnotatedMethod annotatedMethod1 = paramAnnotatedMethodMap1.find(method);
            if (annotatedMethod1 == null) {
              annotatedMethod1 = _constructMethod(method);
              paramAnnotatedMethodMap1.add(annotatedMethod1);
              annotatedMethod2 = paramAnnotatedMethodMap2.remove(method);
              if (annotatedMethod2 != null)
                _addMixOvers(annotatedMethod2.getAnnotated(), annotatedMethod1, false); 
            } else {
              _addMixUnders((Method)annotatedMethod2, annotatedMethod1);
              if (annotatedMethod1.getDeclaringClass().isInterface() && !annotatedMethod2.getDeclaringClass().isInterface())
                paramAnnotatedMethodMap1.add(annotatedMethod1.withMethod((Method)annotatedMethod2)); 
            } 
          } 
          b++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  protected void _addMethodMixIns(MethodFilter paramMethodFilter, AnnotatedMethodMap paramAnnotatedMethodMap1, Class paramClass, AnnotatedMethodMap paramAnnotatedMethodMap2) {
    for (Method method : paramClass.getDeclaredMethods()) {
      if (_isIncludableMethod(method, paramMethodFilter)) {
        AnnotatedMethod annotatedMethod = paramAnnotatedMethodMap1.find(method);
        if (annotatedMethod != null) {
          _addMixUnders(method, annotatedMethod);
        } else {
          paramAnnotatedMethodMap2.add(_constructMethod(method));
        } 
      } 
    } 
  }
  
  protected void _addMixOvers(Constructor paramConstructor, AnnotatedConstructor paramAnnotatedConstructor, boolean paramBoolean) {
    for (Annotation annotation : paramConstructor.getDeclaredAnnotations()) {
      if (this._annotationIntrospector.isHandled(annotation))
        paramAnnotatedConstructor.addOrOverride(annotation); 
    } 
    if (paramBoolean)
      for (Annotation[] arrayOfAnnotation : paramConstructor.getParameterAnnotations()) {
        int i = arrayOfAnnotation.length;
        for (byte b = 0; b < i; b++)
          paramAnnotatedConstructor.addOrOverrideParam(null, arrayOfAnnotation[b]); 
      }  
  }
  
  protected void _addMixOvers(Method paramMethod, AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean) {
    for (Annotation annotation : paramMethod.getDeclaredAnnotations()) {
      if (this._annotationIntrospector.isHandled(annotation))
        paramAnnotatedMethod.addOrOverride(annotation); 
    } 
    if (paramBoolean)
      for (Annotation[] arrayOfAnnotation : paramMethod.getParameterAnnotations()) {
        int i = arrayOfAnnotation.length;
        for (byte b = 0; b < i; b++)
          paramAnnotatedMethod.addOrOverrideParam(null, arrayOfAnnotation[b]); 
      }  
  }
  
  protected void _addMixUnders(Method paramMethod, AnnotatedMethod paramAnnotatedMethod) {
    for (Annotation annotation : paramMethod.getDeclaredAnnotations()) {
      if (this._annotationIntrospector.isHandled(annotation))
        paramAnnotatedMethod.addIfNotPresent(annotation); 
    } 
  }
  
  protected AnnotationMap _collectRelevantAnnotations(Annotation[] paramArrayOfAnnotation) {
    AnnotationMap annotationMap = new AnnotationMap();
    if (paramArrayOfAnnotation != null) {
      int i = paramArrayOfAnnotation.length;
      for (byte b = 0; b < i; b++) {
        Annotation annotation = paramArrayOfAnnotation[b];
        if (this._annotationIntrospector.isHandled(annotation))
          annotationMap.add(annotation); 
      } 
    } 
    return annotationMap;
  }
  
  protected AnnotationMap[] _collectRelevantAnnotations(Annotation[][] paramArrayOfAnnotation) {
    int i = paramArrayOfAnnotation.length;
    AnnotationMap[] arrayOfAnnotationMap = new AnnotationMap[i];
    for (byte b = 0; b < i; b++)
      arrayOfAnnotationMap[b] = _collectRelevantAnnotations(paramArrayOfAnnotation[b]); 
    return arrayOfAnnotationMap;
  }
  
  protected AnnotatedConstructor _constructConstructor(Constructor paramConstructor, boolean paramBoolean) {
    AnnotationMap[] arrayOfAnnotationMap1;
    AnnotationMap[] arrayOfAnnotationMap2;
    if (this._annotationIntrospector == null)
      return new AnnotatedConstructor(paramConstructor, _emptyAnnotationMap(), _emptyAnnotationMaps((paramConstructor.getParameterTypes()).length)); 
    if (paramBoolean)
      return new AnnotatedConstructor(paramConstructor, _collectRelevantAnnotations(paramConstructor.getDeclaredAnnotations()), null); 
    Annotation[][] arrayOfAnnotation = paramConstructor.getParameterAnnotations();
    int i = (paramConstructor.getParameterTypes()).length;
    if (i != arrayOfAnnotation.length) {
      Annotation[][] arrayOfAnnotation1;
      AnnotationMap[] arrayOfAnnotationMap;
      Class clazz = paramConstructor.getDeclaringClass();
      if (clazz.isEnum() && i == arrayOfAnnotation.length + 2) {
        arrayOfAnnotation1 = new Annotation[arrayOfAnnotation.length + 2][];
        System.arraycopy(arrayOfAnnotation, 0, arrayOfAnnotation1, 2, arrayOfAnnotation.length);
        arrayOfAnnotationMap1 = _collectRelevantAnnotations(arrayOfAnnotation1);
      } else if (arrayOfAnnotation1.isMemberClass() && i == arrayOfAnnotationMap1.length + 1) {
        arrayOfAnnotation1 = new Annotation[arrayOfAnnotationMap1.length + 1][];
        System.arraycopy(arrayOfAnnotationMap1, 0, arrayOfAnnotation1, 1, arrayOfAnnotationMap1.length);
        arrayOfAnnotationMap1 = _collectRelevantAnnotations(arrayOfAnnotation1);
      } else {
        arrayOfAnnotationMap = arrayOfAnnotationMap1;
        arrayOfAnnotationMap1 = null;
      } 
      arrayOfAnnotationMap2 = arrayOfAnnotationMap1;
      if (arrayOfAnnotationMap1 == null)
        throw new IllegalStateException("Internal error: constructor for " + paramConstructor.getDeclaringClass().getName() + " has mismatch: " + i + " parameters; " + arrayOfAnnotationMap.length + " sets of annotations"); 
    } else {
      arrayOfAnnotationMap2 = _collectRelevantAnnotations((Annotation[][])arrayOfAnnotationMap1);
    } 
    return new AnnotatedConstructor(paramConstructor, _collectRelevantAnnotations(paramConstructor.getDeclaredAnnotations()), arrayOfAnnotationMap2);
  }
  
  protected AnnotatedMethod _constructCreatorMethod(Method paramMethod) {
    return (this._annotationIntrospector == null) ? new AnnotatedMethod(paramMethod, _emptyAnnotationMap(), _emptyAnnotationMaps((paramMethod.getParameterTypes()).length)) : new AnnotatedMethod(paramMethod, _collectRelevantAnnotations(paramMethod.getDeclaredAnnotations()), _collectRelevantAnnotations(paramMethod.getParameterAnnotations()));
  }
  
  protected AnnotatedField _constructField(Field paramField) {
    return (this._annotationIntrospector == null) ? new AnnotatedField(paramField, _emptyAnnotationMap()) : new AnnotatedField(paramField, _collectRelevantAnnotations(paramField.getDeclaredAnnotations()));
  }
  
  protected AnnotatedMethod _constructMethod(Method paramMethod) {
    return (this._annotationIntrospector == null) ? new AnnotatedMethod(paramMethod, _emptyAnnotationMap(), null) : new AnnotatedMethod(paramMethod, _collectRelevantAnnotations(paramMethod.getDeclaredAnnotations()), null);
  }
  
  protected boolean _isIncludableMethod(Method paramMethod, MethodFilter paramMethodFilter) {
    boolean bool2 = false;
    if (paramMethodFilter != null && !paramMethodFilter.includeMethod(paramMethod))
      return bool2; 
    boolean bool1 = bool2;
    if (!paramMethod.isSynthetic()) {
      bool1 = bool2;
      if (!paramMethod.isBridge())
        bool1 = true; 
    } 
    return bool1;
  }
  
  public Iterable fields() {
    return (this._fields == null) ? Collections.emptyList() : this._fields;
  }
  
  public AnnotatedMethod findMethod(String paramString, Class[] paramArrayOfClass) {
    return this._memberMethods.find(paramString, paramArrayOfClass);
  }
  
  protected AnnotationMap getAllAnnotations() {
    return this._classAnnotations;
  }
  
  public Class getAnnotated() {
    return this._class;
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return (this._classAnnotations == null) ? null : this._classAnnotations.get(paramClass);
  }
  
  public Annotations getAnnotations() {
    return this._classAnnotations;
  }
  
  public List getConstructors() {
    return (this._constructors == null) ? Collections.emptyList() : this._constructors;
  }
  
  public AnnotatedConstructor getDefaultConstructor() {
    return this._defaultConstructor;
  }
  
  public int getFieldCount() {
    return (this._fields == null) ? 0 : this._fields.size();
  }
  
  public Type getGenericType() {
    return this._class;
  }
  
  public int getMemberMethodCount() {
    return this._memberMethods.size();
  }
  
  public int getModifiers() {
    return this._class.getModifiers();
  }
  
  public String getName() {
    return this._class.getName();
  }
  
  public Class getRawType() {
    return this._class;
  }
  
  public List getStaticMethods() {
    return (this._creatorMethods == null) ? Collections.emptyList() : this._creatorMethods;
  }
  
  public boolean hasAnnotations() {
    return (this._classAnnotations.size() > 0);
  }
  
  public Iterable memberMethods() {
    return this._memberMethods;
  }
  
  public void resolveClassAnnotations() {
    this._classAnnotations = new AnnotationMap();
    if (this._annotationIntrospector != null) {
      if (this._primaryMixIn != null)
        _addClassMixIns(this._classAnnotations, this._class, this._primaryMixIn); 
      for (Annotation annotation : this._class.getDeclaredAnnotations()) {
        if (this._annotationIntrospector.isHandled(annotation))
          this._classAnnotations.addIfNotPresent(annotation); 
      } 
      for (Class clazz : this._superTypes) {
        _addClassMixIns(this._classAnnotations, clazz);
        for (Annotation annotation : clazz.getDeclaredAnnotations()) {
          if (this._annotationIntrospector.isHandled(annotation))
            this._classAnnotations.addIfNotPresent(annotation); 
        } 
      } 
      _addClassMixIns(this._classAnnotations, Object.class);
    } 
  }
  
  public void resolveCreators(boolean paramBoolean) {
    this._constructors = null;
    for (Constructor<?> constructor : this._class.getDeclaredConstructors()) {
      if ((constructor.getParameterTypes()).length == 0) {
        this._defaultConstructor = _constructConstructor(constructor, true);
      } else if (paramBoolean) {
        if (this._constructors == null)
          this._constructors = new ArrayList(Math.max(10, null.length)); 
        this._constructors.add(_constructConstructor(constructor, false));
      } 
    } 
    if (this._primaryMixIn != null && (this._defaultConstructor != null || this._constructors != null))
      _addConstructorMixIns(this._primaryMixIn); 
    if (this._annotationIntrospector != null) {
      if (this._defaultConstructor != null && this._annotationIntrospector.isIgnorableConstructor(this._defaultConstructor))
        this._defaultConstructor = null; 
      if (this._constructors != null) {
        int i = this._constructors.size();
        while (--i >= 0) {
          if (this._annotationIntrospector.isIgnorableConstructor(this._constructors.get(i)))
            this._constructors.remove(i); 
        } 
      } 
    } 
    this._creatorMethods = null;
    if (paramBoolean) {
      for (Method method : this._class.getDeclaredMethods()) {
        if (Modifier.isStatic(method.getModifiers()) && (method.getParameterTypes()).length >= 1) {
          if (this._creatorMethods == null)
            this._creatorMethods = new ArrayList(8); 
          this._creatorMethods.add(_constructCreatorMethod(method));
        } 
      } 
      if (this._primaryMixIn != null && this._creatorMethods != null)
        _addFactoryMixIns(this._primaryMixIn); 
      if (this._annotationIntrospector != null && this._creatorMethods != null) {
        int i = this._creatorMethods.size();
        while (--i >= 0) {
          if (this._annotationIntrospector.isIgnorableMethod(this._creatorMethods.get(i)))
            this._creatorMethods.remove(i); 
        } 
      } 
    } 
  }
  
  public void resolveFields() {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    _addFields(linkedHashMap, this._class);
    if (linkedHashMap.isEmpty()) {
      this._fields = Collections.emptyList();
      return;
    } 
    this._fields = new ArrayList(linkedHashMap.size());
    this._fields.addAll(linkedHashMap.values());
  }
  
  @Deprecated
  public void resolveFields(boolean paramBoolean) {
    resolveFields();
  }
  
  public void resolveMemberMethods(MethodFilter paramMethodFilter) {
    this._memberMethods = new AnnotatedMethodMap();
    AnnotatedMethodMap annotatedMethodMap = new AnnotatedMethodMap();
    _addMemberMethods(this._class, paramMethodFilter, this._memberMethods, this._primaryMixIn, annotatedMethodMap);
    for (Class clazz2 : this._superTypes) {
      Class clazz1;
      if (this._mixInResolver == null) {
        clazz1 = null;
      } else {
        clazz1 = this._mixInResolver.findMixInClassFor(clazz2);
      } 
      _addMemberMethods(clazz2, paramMethodFilter, this._memberMethods, clazz1, annotatedMethodMap);
    } 
    if (this._mixInResolver != null) {
      Class clazz = this._mixInResolver.findMixInClassFor(Object.class);
      if (clazz != null)
        _addMethodMixIns(paramMethodFilter, this._memberMethods, clazz, annotatedMethodMap); 
    } 
    if (this._annotationIntrospector != null && !annotatedMethodMap.isEmpty())
      for (AnnotatedMethod annotatedMethod : annotatedMethodMap) {
        try {
          Method method = Object.class.getDeclaredMethod(annotatedMethod.getName(), annotatedMethod.getParameterClasses());
          if (method != null) {
            AnnotatedMethod annotatedMethod1 = _constructMethod(method);
            _addMixOvers(annotatedMethod.getAnnotated(), annotatedMethod1, false);
            this._memberMethods.add(annotatedMethod1);
          } 
        } catch (Exception exception) {}
      }  
  }
  
  @Deprecated
  public void resolveMemberMethods(MethodFilter paramMethodFilter, boolean paramBoolean) {
    resolveMemberMethods(paramMethodFilter);
  }
  
  public String toString() {
    return "[AnnotedClass " + this._class.getName() + "]";
  }
  
  public AnnotatedClass withAnnotations(AnnotationMap paramAnnotationMap) {
    return new AnnotatedClass(this._class, this._superTypes, this._annotationIntrospector, this._mixInResolver, paramAnnotationMap);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\AnnotatedClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */