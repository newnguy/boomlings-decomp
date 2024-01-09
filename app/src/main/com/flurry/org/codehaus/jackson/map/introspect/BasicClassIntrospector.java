package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.ClassIntrospector;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.type.SimpleType;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public class BasicClassIntrospector extends ClassIntrospector {
    protected static final BasicBeanDescription STRING_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(String.class), AnnotatedClass.constructWithoutSuperTypes(String.class, null, null));
    protected static final BasicBeanDescription BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Boolean.TYPE), AnnotatedClass.constructWithoutSuperTypes(Boolean.TYPE, null, null));
    protected static final BasicBeanDescription INT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Integer.TYPE), AnnotatedClass.constructWithoutSuperTypes(Integer.TYPE, null, null));
    protected static final BasicBeanDescription LONG_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Long.TYPE), AnnotatedClass.constructWithoutSuperTypes(Long.TYPE, null, null));
    @Deprecated
    public static final GetterMethodFilter DEFAULT_GETTER_FILTER = new GetterMethodFilter();
    @Deprecated
    public static final SetterMethodFilter DEFAULT_SETTER_FILTER = new SetterMethodFilter();
    @Deprecated
    public static final SetterAndGetterMethodFilter DEFAULT_SETTER_AND_GETTER_FILTER = new SetterAndGetterMethodFilter();
    protected static final MethodFilter MINIMAL_FILTER = new MinimalMethodFilter();
    public static final BasicClassIntrospector instance = new BasicClassIntrospector();

    @Deprecated
    /* loaded from: classes.dex */
    public class GetterMethodFilter implements MethodFilter {
        private GetterMethodFilter() {
        }

        @Override // com.flurry.org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            return ClassUtil.hasGetterSignature(method);
        }
    }

    /* loaded from: classes.dex */
    class MinimalMethodFilter implements MethodFilter {
        private MinimalMethodFilter() {
        }

        @Override // com.flurry.org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            return !Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length <= 2;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public final class SetterAndGetterMethodFilter extends SetterMethodFilter {
        @Override // com.flurry.org.codehaus.jackson.map.introspect.BasicClassIntrospector.SetterMethodFilter, com.flurry.org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            if (super.includeMethod(method)) {
                return true;
            }
            if (ClassUtil.hasGetterSignature(method)) {
                Class<?> returnType = method.getReturnType();
                return Collection.class.isAssignableFrom(returnType) || Map.class.isAssignableFrom(returnType);
            }
            return false;
        }
    }

    @Deprecated
    /* loaded from: classes.dex */
    public class SetterMethodFilter implements MethodFilter {
        @Override // com.flurry.org.codehaus.jackson.map.introspect.MethodFilter
        public boolean includeMethod(Method method) {
            if (Modifier.isStatic(method.getModifiers())) {
                return false;
            }
            switch (method.getParameterTypes().length) {
                case 1:
                    return true;
                case 2:
                    return true;
                default:
                    return false;
            }
        }
    }

    protected BasicBeanDescription _findCachedDesc(JavaType javaType) {
        Class rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return STRING_DESC;
        }
        if (rawClass == Boolean.TYPE) {
            return BOOLEAN_DESC;
        }
        if (rawClass == Integer.TYPE) {
            return INT_DESC;
        }
        if (rawClass == Long.TYPE) {
            return LONG_DESC;
        }
        return null;
    }

    public AnnotatedClass classWithCreators(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Class rawClass = javaType.getRawClass();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        AnnotatedClass construct = AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver);
        construct.resolveMemberMethods(MINIMAL_FILTER);
        construct.resolveCreators(true);
        return construct;
    }

    public POJOPropertiesCollector collectProperties(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver, boolean z) {
        AnnotatedClass classWithCreators = classWithCreators(mapperConfig, javaType, mixInResolver);
        classWithCreators.resolveMemberMethods(MINIMAL_FILTER);
        classWithCreators.resolveFields();
        return constructPropertyCollector(mapperConfig, classWithCreators, javaType, z).collect();
    }

    protected POJOPropertiesCollector constructPropertyCollector(MapperConfig mapperConfig, AnnotatedClass annotatedClass, JavaType javaType, boolean z) {
        return new POJOPropertiesCollector(mapperConfig, z, javaType, annotatedClass);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forClassAnnotations(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Class rawClass = javaType.getRawClass();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        return BasicBeanDescription.forOtherUse(mapperConfig, javaType, AnnotatedClass.construct(rawClass, annotationIntrospector, mixInResolver));
    }

    @Override // com.flurry.org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forCreation(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        return _findCachedDesc == null ? BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false)) : _findCachedDesc;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        return _findCachedDesc == null ? BasicBeanDescription.forDeserialization(collectProperties(deserializationConfig, javaType, mixInResolver, false)) : _findCachedDesc;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forDirectClassAnnotations(MapperConfig mapperConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        boolean isAnnotationProcessingEnabled = mapperConfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        Class rawClass = javaType.getRawClass();
        if (!isAnnotationProcessingEnabled) {
            annotationIntrospector = null;
        }
        return BasicBeanDescription.forOtherUse(mapperConfig, javaType, AnnotatedClass.constructWithoutSuperTypes(rawClass, annotationIntrospector, mixInResolver));
    }

    @Override // com.flurry.org.codehaus.jackson.map.ClassIntrospector
    public BasicBeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, ClassIntrospector.MixInResolver mixInResolver) {
        BasicBeanDescription _findCachedDesc = _findCachedDesc(javaType);
        return _findCachedDesc == null ? BasicBeanDescription.forSerialization(collectProperties(serializationConfig, javaType, mixInResolver, true)) : _findCachedDesc;
    }

    @Deprecated
    protected MethodFilter getDeserializationMethodFilter(DeserializationConfig deserializationConfig) {
        return deserializationConfig.isEnabled(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS) ? DEFAULT_SETTER_AND_GETTER_FILTER : DEFAULT_SETTER_FILTER;
    }

    @Deprecated
    protected MethodFilter getSerializationMethodFilter(SerializationConfig serializationConfig) {
        return DEFAULT_GETTER_FILTER;
    }
}
