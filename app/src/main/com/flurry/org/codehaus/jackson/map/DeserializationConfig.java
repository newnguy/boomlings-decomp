package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.LinkedNode;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.text.DateFormat;
import java.util.HashMap;

/* loaded from: classes.dex */
public class DeserializationConfig extends MapperConfig.Impl {
    protected final JsonNodeFactory _nodeFactory;
    protected LinkedNode _problemHandlers;
    protected boolean _sortPropertiesAlphabetically;

    /* loaded from: classes.dex */
    public enum Feature implements MapperConfig.ConfigFeature {
        USE_ANNOTATIONS(true),
        AUTO_DETECT_SETTERS(true),
        AUTO_DETECT_CREATORS(true),
        AUTO_DETECT_FIELDS(true),
        USE_GETTERS_AS_SETTERS(true),
        CAN_OVERRIDE_ACCESS_MODIFIERS(true),
        USE_BIG_DECIMAL_FOR_FLOATS(false),
        USE_BIG_INTEGER_FOR_INTS(false),
        USE_JAVA_ARRAY_FOR_JSON_ARRAY(false),
        READ_ENUMS_USING_TO_STRING(false),
        FAIL_ON_UNKNOWN_PROPERTIES(true),
        FAIL_ON_NULL_FOR_PRIMITIVES(false),
        FAIL_ON_NUMBERS_FOR_ENUMS(false),
        WRAP_EXCEPTIONS(true),
        ACCEPT_SINGLE_VALUE_AS_ARRAY(false),
        UNWRAP_ROOT_VALUE(false),
        ACCEPT_EMPTY_STRING_AS_NULL_OBJECT(false);
        
        final boolean _defaultState;

        Feature(boolean z) {
            this._defaultState = z;
        }

        @Override // com.flurry.org.codehaus.jackson.map.MapperConfig.ConfigFeature
        public boolean enabledByDefault() {
            return this._defaultState;
        }

        @Override // com.flurry.org.codehaus.jackson.map.MapperConfig.ConfigFeature
        public int getMask() {
            return 1 << ordinal();
        }
    }

    public DeserializationConfig(ClassIntrospector classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker visibilityChecker, SubtypeResolver subtypeResolver, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, HandlerInstantiator handlerInstantiator) {
        super(classIntrospector, annotationIntrospector, visibilityChecker, subtypeResolver, propertyNamingStrategy, typeFactory, handlerInstantiator, collectFeatureDefaults(Feature.class));
        this._nodeFactory = JsonNodeFactory.instance;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig) {
        this(deserializationConfig, deserializationConfig._base);
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, int i) {
        super(deserializationConfig, i);
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._sortPropertiesAlphabetically = deserializationConfig._sortPropertiesAlphabetically;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, MapperConfig.Base base) {
        super(deserializationConfig, base, deserializationConfig._subtypeResolver);
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._sortPropertiesAlphabetically = deserializationConfig._sortPropertiesAlphabetically;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, JsonNodeFactory jsonNodeFactory) {
        super(deserializationConfig);
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = jsonNodeFactory;
        this._sortPropertiesAlphabetically = deserializationConfig._sortPropertiesAlphabetically;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, HashMap hashMap, SubtypeResolver subtypeResolver) {
        this(deserializationConfig, deserializationConfig._base);
        this._mixInAnnotations = hashMap;
        this._subtypeResolver = subtypeResolver;
    }

    public void addHandler(DeserializationProblemHandler deserializationProblemHandler) {
        if (LinkedNode.contains(this._problemHandlers, deserializationProblemHandler)) {
            return;
        }
        this._problemHandlers = new LinkedNode(deserializationProblemHandler, this._problemHandlers);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public boolean canOverrideAccessModifiers() {
        return isEnabled(Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    public void clearHandlers() {
        this._problemHandlers = null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig createUnshared(SubtypeResolver subtypeResolver) {
        HashMap hashMap = this._mixInAnnotations;
        this._mixInAnnotationsShared = true;
        return new DeserializationConfig(this, hashMap, subtypeResolver);
    }

    public JsonDeserializer deserializerInstance(Annotated annotated, Class cls) {
        JsonDeserializer deserializerInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (deserializerInstance = handlerInstantiator.deserializerInstance(this, annotated, cls)) == null) ? (JsonDeserializer) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : deserializerInstance;
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void disable(Feature feature) {
        super.disable((MapperConfig.ConfigFeature) feature);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void enable(Feature feature) {
        super.enable((MapperConfig.ConfigFeature) feature);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    @Deprecated
    public void fromAnnotations(Class cls) {
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        this._base = this._base.withVisibilityChecker(annotationIntrospector.findAutoDetectVisibility(AnnotatedClass.construct(cls, annotationIntrospector, null), getDefaultVisibilityChecker()));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public AnnotationIntrospector getAnnotationIntrospector() {
        return isEnabled(Feature.USE_ANNOTATIONS) ? super.getAnnotationIntrospector() : NopAnnotationIntrospector.instance;
    }

    public Base64Variant getBase64Variant() {
        return Base64Variants.getDefaultVariant();
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public VisibilityChecker getDefaultVisibilityChecker() {
        VisibilityChecker defaultVisibilityChecker = super.getDefaultVisibilityChecker();
        if (!isEnabled(Feature.AUTO_DETECT_SETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(Feature.AUTO_DETECT_CREATORS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return !isEnabled(Feature.AUTO_DETECT_FIELDS) ? defaultVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE) : defaultVisibilityChecker;
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._nodeFactory;
    }

    public LinkedNode getProblemHandlers() {
        return this._problemHandlers;
    }

    public BeanDescription introspect(JavaType javaType) {
        return getClassIntrospector().forDeserialization(this, javaType, this);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public BeanDescription introspectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forClassAnnotations(this, javaType, this);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public BeanDescription introspectDirectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forDirectClassAnnotations(this, javaType, this);
    }

    public BeanDescription introspectForCreation(JavaType javaType) {
        return getClassIntrospector().forCreation(this, javaType, this);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public boolean isAnnotationProcessingEnabled() {
        return isEnabled(Feature.USE_ANNOTATIONS);
    }

    public boolean isEnabled(Feature feature) {
        return (this._featureFlags & feature.getMask()) != 0;
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig.Impl, com.flurry.org.codehaus.jackson.map.MapperConfig
    public /* bridge */ /* synthetic */ boolean isEnabled(MapperConfig.ConfigFeature configFeature) {
        return super.isEnabled(configFeature);
    }

    public KeyDeserializer keyDeserializerInstance(Annotated annotated, Class cls) {
        KeyDeserializer keyDeserializerInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (keyDeserializerInstance = handlerInstantiator.keyDeserializerInstance(this, annotated, cls)) == null) ? (KeyDeserializer) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : keyDeserializerInstance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DeserializationConfig passSerializationFeatures(int i) {
        this._sortPropertiesAlphabetically = (SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY.getMask() & i) != 0;
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig.Impl
    @Deprecated
    public void set(Feature feature, boolean z) {
        super.set((MapperConfig.ConfigFeature) feature, z);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public boolean shouldSortPropertiesAlphabetically() {
        return this._sortPropertiesAlphabetically;
    }

    public ValueInstantiator valueInstantiatorInstance(Annotated annotated, Class cls) {
        ValueInstantiator valueInstantiatorInstance;
        HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
        return (handlerInstantiator == null || (valueInstantiatorInstance = handlerInstantiator.valueInstantiatorInstance(this, annotated, cls)) == null) ? (ValueInstantiator) ClassUtil.createInstance(cls, canOverrideAccessModifiers()) : valueInstantiatorInstance;
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig.Impl
    public DeserializationConfig with(Feature... featureArr) {
        int i = this._featureFlags;
        for (Feature feature : featureArr) {
            i |= feature.getMask();
        }
        return new DeserializationConfig(this, i);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new DeserializationConfig(this, this._base.withAnnotationIntrospector(annotationIntrospector));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new DeserializationConfig(this, this._base.withAppendedAnnotationIntrospector(annotationIntrospector));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withClassIntrospector(ClassIntrospector classIntrospector) {
        return new DeserializationConfig(this, this._base.withClassIntrospector(classIntrospector));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withDateFormat(DateFormat dateFormat) {
        return dateFormat == this._base.getDateFormat() ? this : new DeserializationConfig(this, this._base.withDateFormat(dateFormat));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        return handlerInstantiator == this._base.getHandlerInstantiator() ? this : new DeserializationConfig(this, this._base.withHandlerInstantiator(handlerInstantiator));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return new DeserializationConfig(this, this._base.withInsertedAnnotationIntrospector(annotationIntrospector));
    }

    public DeserializationConfig withNodeFactory(JsonNodeFactory jsonNodeFactory) {
        return new DeserializationConfig(this, jsonNodeFactory);
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        return new DeserializationConfig(this, this._base.withPropertyNamingStrategy(propertyNamingStrategy));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withSubtypeResolver(SubtypeResolver subtypeResolver) {
        DeserializationConfig deserializationConfig = new DeserializationConfig(this);
        deserializationConfig._subtypeResolver = subtypeResolver;
        return deserializationConfig;
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withTypeFactory(TypeFactory typeFactory) {
        return typeFactory == this._base.getTypeFactory() ? this : new DeserializationConfig(this, this._base.withTypeFactory(typeFactory));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withTypeResolverBuilder(TypeResolverBuilder typeResolverBuilder) {
        return new DeserializationConfig(this, this._base.withTypeResolverBuilder(typeResolverBuilder));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
        return new DeserializationConfig(this, this._base.withVisibility(jsonMethod, visibility));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig
    public DeserializationConfig withVisibilityChecker(VisibilityChecker visibilityChecker) {
        return new DeserializationConfig(this, this._base.withVisibilityChecker(visibilityChecker));
    }

    @Override // com.flurry.org.codehaus.jackson.map.MapperConfig.Impl
    public DeserializationConfig without(Feature... featureArr) {
        int i = this._featureFlags;
        for (Feature feature : featureArr) {
            i &= feature.getMask() ^ (-1);
        }
        return new DeserializationConfig(this, i);
    }
}
