package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.ClassIntrospector;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.StdDateFormat;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.type.TypeReference;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class MapperConfig implements ClassIntrospector.MixInResolver {
    protected static final DateFormat DEFAULT_DATE_FORMAT = StdDateFormat.instance;
    protected Base _base;
    protected HashMap _mixInAnnotations;
    protected boolean _mixInAnnotationsShared;
    protected SubtypeResolver _subtypeResolver;

    /* loaded from: classes.dex */
    public class Base {
        protected final AnnotationIntrospector _annotationIntrospector;
        protected final ClassIntrospector _classIntrospector;
        protected final DateFormat _dateFormat;
        protected final HandlerInstantiator _handlerInstantiator;
        protected final PropertyNamingStrategy _propertyNamingStrategy;
        protected final TypeFactory _typeFactory;
        protected final TypeResolverBuilder _typeResolverBuilder;
        protected final VisibilityChecker _visibilityChecker;

        public Base(ClassIntrospector classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker visibilityChecker, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, TypeResolverBuilder typeResolverBuilder, DateFormat dateFormat, HandlerInstantiator handlerInstantiator) {
            this._classIntrospector = classIntrospector;
            this._annotationIntrospector = annotationIntrospector;
            this._visibilityChecker = visibilityChecker;
            this._propertyNamingStrategy = propertyNamingStrategy;
            this._typeFactory = typeFactory;
            this._typeResolverBuilder = typeResolverBuilder;
            this._dateFormat = dateFormat;
            this._handlerInstantiator = handlerInstantiator;
        }

        public AnnotationIntrospector getAnnotationIntrospector() {
            return this._annotationIntrospector;
        }

        public ClassIntrospector getClassIntrospector() {
            return this._classIntrospector;
        }

        public DateFormat getDateFormat() {
            return this._dateFormat;
        }

        public HandlerInstantiator getHandlerInstantiator() {
            return this._handlerInstantiator;
        }

        public PropertyNamingStrategy getPropertyNamingStrategy() {
            return this._propertyNamingStrategy;
        }

        public TypeFactory getTypeFactory() {
            return this._typeFactory;
        }

        public TypeResolverBuilder getTypeResolverBuilder() {
            return this._typeResolverBuilder;
        }

        public VisibilityChecker getVisibilityChecker() {
            return this._visibilityChecker;
        }

        public Base withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return new Base(this._classIntrospector, annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return withAnnotationIntrospector(AnnotationIntrospector.Pair.create(this._annotationIntrospector, annotationIntrospector));
        }

        public Base withClassIntrospector(ClassIntrospector classIntrospector) {
            return new Base(classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withDateFormat(DateFormat dateFormat) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, dateFormat, this._handlerInstantiator);
        }

        public Base withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, handlerInstantiator);
        }

        public Base withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
            return withAnnotationIntrospector(AnnotationIntrospector.Pair.create(annotationIntrospector, this._annotationIntrospector));
        }

        public Base withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withTypeFactory(TypeFactory typeFactory) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withTypeResolverBuilder(TypeResolverBuilder typeResolverBuilder) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
            return new Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker.withVisibility(jsonMethod, visibility), this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }

        public Base withVisibilityChecker(VisibilityChecker visibilityChecker) {
            return new Base(this._classIntrospector, this._annotationIntrospector, visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
        }
    }

    /* loaded from: classes.dex */
    public interface ConfigFeature {
        boolean enabledByDefault();

        int getMask();
    }

    /* loaded from: classes.dex */
    public abstract class Impl extends MapperConfig {
        protected int _featureFlags;

        public Impl(ClassIntrospector classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator, int i) {
            super(classIntrospector, annotationIntrospector, visibilityChecker, subtypeResolver, propertyNamingStrategy, typeFactory, handlerInstantiator);
            this._featureFlags = i;
        }

        public Impl(Impl impl) {
            super(impl);
            this._featureFlags = impl._featureFlags;
        }

        public Impl(Impl impl, int i) {
            super(impl);
            this._featureFlags = i;
        }

        public Impl(Impl impl, Base base, SubtypeResolver subtypeResolver) {
            super(impl, base, subtypeResolver);
            this._featureFlags = impl._featureFlags;
        }

        public static int collectFeatureDefaults(Class cls) {
            Enum[] enumArr = (Enum[]) cls.getEnumConstants();
            int length = enumArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                Enum r2 = enumArr[i];
                i++;
                i2 = ((ConfigFeature) r2).enabledByDefault() ? ((ConfigFeature) r2).getMask() | i2 : i2;
            }
            return i2;
        }

        @Deprecated
        public void disable(ConfigFeature configFeature) {
            this._featureFlags &= configFeature.getMask() ^ (-1);
        }

        @Deprecated
        public void enable(ConfigFeature configFeature) {
            this._featureFlags |= configFeature.getMask();
        }

        @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
        public boolean isEnabled(ConfigFeature configFeature) {
            return (this._featureFlags & configFeature.getMask()) != 0;
        }

        @Deprecated
        public void set(ConfigFeature configFeature, boolean z) {
            if (z) {
                enable(configFeature);
            } else {
                disable(configFeature);
            }
        }

        public abstract Impl with(ConfigFeature... configFeatureArr);

        public abstract Impl without(ConfigFeature... configFeatureArr);
    }

    protected MapperConfig(ClassIntrospector classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator) {
        this._base = new Base(classIntrospector, annotationIntrospector, visibilityChecker, propertyNamingStrategy, typeFactory, null, DEFAULT_DATE_FORMAT, handlerInstantiator);
        this._subtypeResolver = subtypeResolver;
        this._mixInAnnotationsShared = true;
    }

    protected MapperConfig(MapperConfig mapperConfig) {
        this(mapperConfig, mapperConfig._base, mapperConfig._subtypeResolver);
    }

    protected MapperConfig(MapperConfig mapperConfig, Base base, SubtypeResolver subtypeResolver) {
        this._base = base;
        this._subtypeResolver = subtypeResolver;
        this._mixInAnnotationsShared = true;
        this._mixInAnnotations = mapperConfig._mixInAnnotations;
    }

    public final void addMixInAnnotations(Class cls, Class cls2) {
        if (this._mixInAnnotations == null) {
            this._mixInAnnotationsShared = false;
            this._mixInAnnotations = new HashMap();
        } else if (this._mixInAnnotationsShared) {
            this._mixInAnnotationsShared = false;
            this._mixInAnnotations = new HashMap(this._mixInAnnotations);
        }
        this._mixInAnnotations.put(new ClassKey(cls), cls2);
    }

    @Deprecated
    public final void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(AnnotationIntrospector.Pair.create(getAnnotationIntrospector(), annotationIntrospector));
    }

    public abstract boolean canOverrideAccessModifiers();

    public JavaType constructSpecializedType(JavaType javaType, Class cls) {
        return getTypeFactory().constructSpecializedType(javaType, cls);
    }

    public final JavaType constructType(TypeReference typeReference) {
        return getTypeFactory().constructType(typeReference.getType(), (TypeBindings) null);
    }

    public final JavaType constructType(Class cls) {
        return getTypeFactory().constructType(cls, (TypeBindings) null);
    }

    public abstract MapperConfig createUnshared(SubtypeResolver subtypeResolver);

    @Override // com.flurry.org.codehaus.jackson.map.ClassIntrospector.MixInResolver
    public final Class findMixInClassFor(Class cls) {
        if (this._mixInAnnotations == null) {
            return null;
        }
        return (Class) this._mixInAnnotations.get(new ClassKey(cls));
    }

    @Deprecated
    public abstract void fromAnnotations(Class cls);

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._base.getAnnotationIntrospector();
    }

    public ClassIntrospector getClassIntrospector() {
        return this._base.getClassIntrospector();
    }

    public final DateFormat getDateFormat() {
        return this._base.getDateFormat();
    }

    public final TypeResolverBuilder getDefaultTyper(JavaType javaType) {
        return this._base.getTypeResolverBuilder();
    }

    public VisibilityChecker getDefaultVisibilityChecker() {
        return this._base.getVisibilityChecker();
    }

    public final HandlerInstantiator getHandlerInstantiator() {
        return this._base.getHandlerInstantiator();
    }

    public final PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._base.getPropertyNamingStrategy();
    }

    public final SubtypeResolver getSubtypeResolver() {
        if (this._subtypeResolver == null) {
            this._subtypeResolver = new StdSubtypeResolver();
        }
        return this._subtypeResolver;
    }

    public final TypeFactory getTypeFactory() {
        return this._base.getTypeFactory();
    }

    @Deprecated
    public final void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(AnnotationIntrospector.Pair.create(annotationIntrospector, getAnnotationIntrospector()));
    }

    public abstract BeanDescription introspectClassAnnotations(JavaType javaType);

    public BeanDescription introspectClassAnnotations(Class cls) {
        return introspectClassAnnotations(constructType(cls));
    }

    public abstract BeanDescription introspectDirectClassAnnotations(JavaType javaType);

    public BeanDescription introspectDirectClassAnnotations(Class cls) {
        return introspectDirectClassAnnotations(constructType(cls));
    }

    public abstract boolean isAnnotationProcessingEnabled();

    public abstract boolean isEnabled(ConfigFeature configFeature);

    public final int mixInCount() {
        if (this._mixInAnnotations == null) {
            return 0;
        }
        return this._mixInAnnotations.size();
    }

    @Deprecated
    public final void setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._base = this._base.withAnnotationIntrospector(annotationIntrospector);
    }

    @Deprecated
    public void setDateFormat(DateFormat dateFormat) {
        if (dateFormat == null) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }
        this._base = this._base.withDateFormat(dateFormat);
    }

    public final void setMixInAnnotations(Map map) {
        HashMap hashMap = null;
        if (map != null && map.size() > 0) {
            HashMap hashMap2 = new HashMap(map.size());
            for (Map.Entry entry : map.entrySet()) {
                hashMap2.put(new ClassKey((Class) entry.getKey()), entry.getValue());
            }
            hashMap = hashMap2;
        }
        this._mixInAnnotationsShared = false;
        this._mixInAnnotations = hashMap;
    }

    public abstract boolean shouldSortPropertiesAlphabetically();

    public TypeIdResolver typeIdResolverInstance(Annotated annotated, Class cls) {
        TypeIdResolver typeIdResolverInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (typeIdResolverInstance = handlerInstantiator.typeIdResolverInstance(this, annotated, cls)) == null) ? (TypeIdResolver) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : typeIdResolverInstance;
    }

    public TypeResolverBuilder typeResolverBuilderInstance(Annotated annotated, Class cls) {
        TypeResolverBuilder typeResolverBuilderInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (typeResolverBuilderInstance = handlerInstantiator.typeResolverBuilderInstance(this, annotated, cls)) == null) ? (TypeResolverBuilder) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : typeResolverBuilderInstance;
    }

    public abstract MapperConfig withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract MapperConfig withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract MapperConfig withClassIntrospector(ClassIntrospector classIntrospector);

    public abstract MapperConfig withDateFormat(DateFormat dateFormat);

    public abstract MapperConfig withHandlerInstantiator(HandlerInstantiator handlerInstantiator);

    public abstract MapperConfig withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract MapperConfig withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy);

    public abstract MapperConfig withSubtypeResolver(SubtypeResolver subtypeResolver);

    public abstract MapperConfig withTypeFactory(TypeFactory typeFactory);

    public abstract MapperConfig withTypeResolverBuilder(TypeResolverBuilder typeResolverBuilder);

    public abstract MapperConfig withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility);

    public abstract MapperConfig withVisibilityChecker(VisibilityChecker visibilityChecker);
}
