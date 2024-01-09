package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class BasicBeanDescription extends BeanDescription {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected AnnotatedMethod _anyGetterMethod;
    protected AnnotatedMethod _anySetterMethod;
    protected TypeBindings _bindings;
    protected final AnnotatedClass _classInfo;
    protected final MapperConfig _config;
    protected Set _ignoredPropertyNames;
    protected Set _ignoredPropertyNamesForDeser;
    protected Map _injectables;
    protected AnnotatedMethod _jsonValueMethod;
    protected final List _properties;

    @Deprecated
    public BasicBeanDescription(MapperConfig mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        this(mapperConfig, javaType, annotatedClass, Collections.emptyList());
    }

    protected BasicBeanDescription(MapperConfig mapperConfig, JavaType javaType, AnnotatedClass annotatedClass, List list) {
        super(javaType);
        this._config = mapperConfig;
        this._annotationIntrospector = mapperConfig == null ? null : mapperConfig.getAnnotationIntrospector();
        this._classInfo = annotatedClass;
        this._properties = list;
    }

    public static BasicBeanDescription forDeserialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        BasicBeanDescription basicBeanDescription = new BasicBeanDescription(pOJOPropertiesCollector.getConfig(), pOJOPropertiesCollector.getType(), pOJOPropertiesCollector.getClassDef(), pOJOPropertiesCollector.getProperties());
        basicBeanDescription._anySetterMethod = pOJOPropertiesCollector.getAnySetterMethod();
        basicBeanDescription._ignoredPropertyNames = pOJOPropertiesCollector.getIgnoredPropertyNames();
        basicBeanDescription._ignoredPropertyNamesForDeser = pOJOPropertiesCollector.getIgnoredPropertyNamesForDeser();
        basicBeanDescription._injectables = pOJOPropertiesCollector.getInjectables();
        return basicBeanDescription;
    }

    public static BasicBeanDescription forOtherUse(MapperConfig mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        return new BasicBeanDescription(mapperConfig, javaType, annotatedClass, Collections.emptyList());
    }

    public static BasicBeanDescription forSerialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        BasicBeanDescription basicBeanDescription = new BasicBeanDescription(pOJOPropertiesCollector.getConfig(), pOJOPropertiesCollector.getType(), pOJOPropertiesCollector.getClassDef(), pOJOPropertiesCollector.getProperties());
        basicBeanDescription._jsonValueMethod = pOJOPropertiesCollector.getJsonValueMethod();
        basicBeanDescription._anyGetterMethod = pOJOPropertiesCollector.getAnyGetterMethod();
        return basicBeanDescription;
    }

    public LinkedHashMap _findPropertyFields(Collection collection, boolean z) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedField field = beanPropertyDefinition.getField();
            if (field != null) {
                String name = beanPropertyDefinition.getName();
                if (collection == null || !collection.contains(name)) {
                    linkedHashMap.put(name, field);
                }
            }
        }
        return linkedHashMap;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public TypeBindings bindingsForBeanType() {
        if (this._bindings == null) {
            this._bindings = new TypeBindings(this._config.getTypeFactory(), this._type);
        }
        return this._bindings;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public AnnotatedMethod findAnyGetter() {
        if (this._anyGetterMethod == null || Map.class.isAssignableFrom(this._anyGetterMethod.getRawType())) {
            return this._anyGetterMethod;
        }
        throw new IllegalArgumentException("Invalid 'any-getter' annotation on method " + this._anyGetterMethod.getName() + "(): return type is not instance of java.util.Map");
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public AnnotatedMethod findAnySetter() {
        Class parameterClass;
        if (this._anySetterMethod == null || (parameterClass = this._anySetterMethod.getParameterClass(0)) == String.class || parameterClass == Object.class) {
            return this._anySetterMethod;
        }
        throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + this._anySetterMethod.getName() + "(): first argument not of type String or Object, but " + parameterClass.getName());
    }

    public Map findBackReferenceProperties() {
        AnnotationIntrospector.ReferenceProperty findReferenceType;
        HashMap hashMap = null;
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedMember mutator = beanPropertyDefinition.getMutator();
            if (mutator != null && (findReferenceType = this._annotationIntrospector.findReferenceType(mutator)) != null && findReferenceType.isBackReference()) {
                HashMap hashMap2 = hashMap == null ? new HashMap() : hashMap;
                String name = findReferenceType.getName();
                if (hashMap2.put(name, mutator) != null) {
                    throw new IllegalArgumentException("Multiple back-reference properties with name '" + name + "'");
                }
                hashMap = hashMap2;
            }
        }
        return hashMap;
    }

    public List findCreatorPropertyNames() {
        String findPropertyNameForParam;
        ArrayList arrayList = null;
        int i = 0;
        while (i < 2) {
            for (AnnotatedWithParams annotatedWithParams : i == 0 ? getConstructors() : getFactoryMethods()) {
                int parameterCount = annotatedWithParams.getParameterCount();
                if (parameterCount >= 1 && (findPropertyNameForParam = this._annotationIntrospector.findPropertyNameForParam(annotatedWithParams.getParameter(0))) != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(findPropertyNameForParam);
                    for (int i2 = 1; i2 < parameterCount; i2++) {
                        arrayList.add(this._annotationIntrospector.findPropertyNameForParam(annotatedWithParams.getParameter(i2)));
                    }
                }
            }
            i++;
        }
        return arrayList == null ? Collections.emptyList() : arrayList;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public AnnotatedConstructor findDefaultConstructor() {
        return this._classInfo.getDefaultConstructor();
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public LinkedHashMap findDeserializableFields(VisibilityChecker visibilityChecker, Collection collection) {
        return _findPropertyFields(collection, false);
    }

    public Method findFactoryMethod(Class... clsArr) {
        for (AnnotatedMethod annotatedMethod : this._classInfo.getStaticMethods()) {
            if (isFactoryMethod(annotatedMethod)) {
                Class parameterClass = annotatedMethod.getParameterClass(0);
                for (Class cls : clsArr) {
                    if (parameterClass.isAssignableFrom(cls)) {
                        return annotatedMethod.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public LinkedHashMap findGetters(VisibilityChecker visibilityChecker, Collection collection) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedMethod getter = beanPropertyDefinition.getGetter();
            if (getter != null) {
                String name = beanPropertyDefinition.getName();
                if (collection == null || !collection.contains(name)) {
                    linkedHashMap.put(name, getter);
                }
            }
        }
        return linkedHashMap;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public Map findInjectables() {
        return this._injectables;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public AnnotatedMethod findJsonValueMethod() {
        return this._jsonValueMethod;
    }

    public AnnotatedMethod findMethod(String str, Class[] clsArr) {
        return this._classInfo.findMethod(str, clsArr);
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public List findProperties() {
        return this._properties;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public LinkedHashMap findSerializableFields(VisibilityChecker visibilityChecker, Collection collection) {
        return _findPropertyFields(collection, true);
    }

    public JsonSerialize.Inclusion findSerializationInclusion(JsonSerialize.Inclusion inclusion) {
        return this._annotationIntrospector == null ? inclusion : this._annotationIntrospector.findSerializationInclusion(this._classInfo, inclusion);
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public LinkedHashMap findSetters(VisibilityChecker visibilityChecker) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedMethod setter = beanPropertyDefinition.getSetter();
            if (setter != null) {
                linkedHashMap.put(beanPropertyDefinition.getName(), setter);
            }
        }
        return linkedHashMap;
    }

    public Constructor findSingleArgConstructor(Class... clsArr) {
        for (AnnotatedConstructor annotatedConstructor : this._classInfo.getConstructors()) {
            if (annotatedConstructor.getParameterCount() == 1) {
                Class parameterClass = annotatedConstructor.getParameterClass(0);
                for (Class cls : clsArr) {
                    if (cls == parameterClass) {
                        return annotatedConstructor.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public Annotations getClassAnnotations() {
        return this._classInfo.getAnnotations();
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public AnnotatedClass getClassInfo() {
        return this._classInfo;
    }

    public List getConstructors() {
        return this._classInfo.getConstructors();
    }

    public List getFactoryMethods() {
        List<AnnotatedMethod> staticMethods = this._classInfo.getStaticMethods();
        if (staticMethods.isEmpty()) {
            return staticMethods;
        }
        ArrayList arrayList = new ArrayList();
        for (AnnotatedMethod annotatedMethod : staticMethods) {
            if (isFactoryMethod(annotatedMethod)) {
                arrayList.add(annotatedMethod);
            }
        }
        return arrayList;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public Set getIgnoredPropertyNames() {
        return this._ignoredPropertyNames == null ? Collections.emptySet() : this._ignoredPropertyNames;
    }

    public Set getIgnoredPropertyNamesForDeser() {
        return this._ignoredPropertyNamesForDeser;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public boolean hasKnownClassAnnotations() {
        return this._classInfo.hasAnnotations();
    }

    public Object instantiateBean(boolean z) {
        AnnotatedConstructor defaultConstructor = this._classInfo.getDefaultConstructor();
        if (defaultConstructor == null) {
            return null;
        }
        if (z) {
            defaultConstructor.fixAccess();
        }
        try {
            return defaultConstructor.getAnnotated().newInstance(new Object[0]);
        } catch (Exception e) {
            e = e;
            while (e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw ((Error) e);
            }
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            throw new IllegalArgumentException("Failed to instantiate bean of type " + this._classInfo.getAnnotated().getName() + ": (" + e.getClass().getName() + ") " + e.getMessage(), e);
        }
    }

    protected boolean isFactoryMethod(AnnotatedMethod annotatedMethod) {
        if (getBeanClass().isAssignableFrom(annotatedMethod.getRawType())) {
            return this._annotationIntrospector.hasCreatorAnnotation(annotatedMethod) || "valueOf".equals(annotatedMethod.getName());
        }
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanDescription
    public JavaType resolveType(Type type) {
        if (type == null) {
            return null;
        }
        return bindingsForBeanType().resolveType(type);
    }
}
