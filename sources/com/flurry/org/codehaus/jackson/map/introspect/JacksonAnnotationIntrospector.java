package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.annotate.JacksonAnnotation;
import com.flurry.org.codehaus.jackson.annotate.JsonAnyGetter;
import com.flurry.org.codehaus.jackson.annotate.JsonAnySetter;
import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonBackReference;
import com.flurry.org.codehaus.jackson.annotate.JsonCreator;
import com.flurry.org.codehaus.jackson.annotate.JsonGetter;
import com.flurry.org.codehaus.jackson.annotate.JsonIgnore;
import com.flurry.org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.flurry.org.codehaus.jackson.annotate.JsonIgnoreType;
import com.flurry.org.codehaus.jackson.annotate.JsonManagedReference;
import com.flurry.org.codehaus.jackson.annotate.JsonProperty;
import com.flurry.org.codehaus.jackson.annotate.JsonPropertyOrder;
import com.flurry.org.codehaus.jackson.annotate.JsonRawValue;
import com.flurry.org.codehaus.jackson.annotate.JsonSetter;
import com.flurry.org.codehaus.jackson.annotate.JsonSubTypes;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeName;
import com.flurry.org.codehaus.jackson.annotate.JsonUnwrapped;
import com.flurry.org.codehaus.jackson.annotate.JsonValue;
import com.flurry.org.codehaus.jackson.annotate.JsonWriteNullProperties;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonInject;
import com.flurry.org.codehaus.jackson.map.annotate.JsonCachable;
import com.flurry.org.codehaus.jackson.map.annotate.JsonDeserialize;
import com.flurry.org.codehaus.jackson.map.annotate.JsonFilter;
import com.flurry.org.codehaus.jackson.map.annotate.JsonRootName;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.annotate.JsonTypeIdResolver;
import com.flurry.org.codehaus.jackson.map.annotate.JsonTypeResolver;
import com.flurry.org.codehaus.jackson.map.annotate.JsonValueInstantiator;
import com.flurry.org.codehaus.jackson.map.annotate.JsonView;
import com.flurry.org.codehaus.jackson.map.annotate.NoClass;
import com.flurry.org.codehaus.jackson.map.jsontype.NamedType;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.ser.std.RawSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class JacksonAnnotationIntrospector extends AnnotationIntrospector {
    protected StdTypeResolverBuilder _constructNoTypeResolverBuilder() {
        return StdTypeResolverBuilder.noTypeInfoBuilder();
    }

    protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
        return new StdTypeResolverBuilder();
    }

    protected TypeResolverBuilder _findTypeResolver(MapperConfig mapperConfig, Annotated annotated, JavaType javaType) {
        StdTypeResolverBuilder _constructStdTypeResolverBuilder;
        JsonTypeInfo jsonTypeInfo = (JsonTypeInfo) annotated.getAnnotation(JsonTypeInfo.class);
        JsonTypeResolver jsonTypeResolver = (JsonTypeResolver) annotated.getAnnotation(JsonTypeResolver.class);
        if (jsonTypeResolver != null) {
            if (jsonTypeInfo == null) {
                return null;
            }
            _constructStdTypeResolverBuilder = mapperConfig.typeResolverBuilderInstance(annotated, jsonTypeResolver.value());
        } else if (jsonTypeInfo == null) {
            return null;
        } else {
            if (jsonTypeInfo.use() == JsonTypeInfo.Id.NONE) {
                return _constructNoTypeResolverBuilder();
            }
            _constructStdTypeResolverBuilder = _constructStdTypeResolverBuilder();
        }
        JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver) annotated.getAnnotation(JsonTypeIdResolver.class);
        TypeIdResolver typeIdResolverInstance = jsonTypeIdResolver != null ? mapperConfig.typeIdResolverInstance(annotated, jsonTypeIdResolver.value()) : null;
        if (typeIdResolverInstance != null) {
            typeIdResolverInstance.init(javaType);
        }
        TypeResolverBuilder init = _constructStdTypeResolverBuilder.init(jsonTypeInfo.use(), typeIdResolverInstance);
        JsonTypeInfo.As include = jsonTypeInfo.include();
        if (include == JsonTypeInfo.As.EXTERNAL_PROPERTY && (annotated instanceof AnnotatedClass)) {
            include = JsonTypeInfo.As.PROPERTY;
        }
        TypeResolverBuilder typeProperty = init.inclusion(include).typeProperty(jsonTypeInfo.property());
        Class defaultImpl = jsonTypeInfo.defaultImpl();
        return defaultImpl != JsonTypeInfo.None.class ? typeProperty.defaultImpl(defaultImpl) : typeProperty;
    }

    protected boolean _isIgnorable(Annotated annotated) {
        JsonIgnore jsonIgnore = (JsonIgnore) annotated.getAnnotation(JsonIgnore.class);
        return jsonIgnore != null && jsonIgnore.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public VisibilityChecker findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker visibilityChecker) {
        JsonAutoDetect jsonAutoDetect = (JsonAutoDetect) annotatedClass.getAnnotation(JsonAutoDetect.class);
        return jsonAutoDetect == null ? visibilityChecker : visibilityChecker.with(jsonAutoDetect);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findCachability(AnnotatedClass annotatedClass) {
        JsonCachable jsonCachable = (JsonCachable) annotatedClass.getAnnotation(JsonCachable.class);
        if (jsonCachable == null) {
            return null;
        }
        return jsonCachable.value() ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findContentDeserializer(Annotated annotated) {
        Class contentUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentUsing = jsonDeserialize.contentUsing()) == JsonDeserializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findContentSerializer(Annotated annotated) {
        Class contentUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (contentUsing = jsonSerialize.contentUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return contentUsing;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String findDeserializablePropertyName(AnnotatedField annotatedField) {
        JsonProperty jsonProperty = (JsonProperty) annotatedField.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (annotatedField.hasAnnotation(JsonDeserialize.class) || annotatedField.hasAnnotation(JsonView.class) || annotatedField.hasAnnotation(JsonBackReference.class) || annotatedField.hasAnnotation(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findDeserializationContentType(Annotated annotated, JavaType javaType, String str) {
        Class contentAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (contentAs = jsonDeserialize.contentAs()) == NoClass.class) {
            return null;
        }
        return contentAs;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findDeserializationKeyType(Annotated annotated, JavaType javaType, String str) {
        Class keyAs;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyAs = jsonDeserialize.keyAs()) == NoClass.class) {
            return null;
        }
        return keyAs;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findDeserializationType(Annotated annotated, JavaType javaType, String str) {
        Class as;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (as = jsonDeserialize.as()) == NoClass.class) {
            return null;
        }
        return as;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findDeserializer(Annotated annotated) {
        Class using;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (using = jsonDeserialize.using()) == JsonDeserializer.None.class) {
            return null;
        }
        return using;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String findEnumValue(Enum r2) {
        return r2.name();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findFilterId(AnnotatedClass annotatedClass) {
        JsonFilter jsonFilter = (JsonFilter) annotatedClass.getAnnotation(JsonFilter.class);
        if (jsonFilter != null) {
            String value = jsonFilter.value();
            if (value.length() > 0) {
                return value;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String findGettablePropertyName(AnnotatedMethod annotatedMethod) {
        JsonProperty jsonProperty = (JsonProperty) annotatedMethod.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        JsonGetter jsonGetter = (JsonGetter) annotatedMethod.getAnnotation(JsonGetter.class);
        if (jsonGetter != null) {
            return jsonGetter.value();
        }
        if (annotatedMethod.hasAnnotation(JsonSerialize.class) || annotatedMethod.hasAnnotation(JsonView.class)) {
            return "";
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotatedClass.getAnnotation(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        JacksonInject jacksonInject = (JacksonInject) annotatedMember.getAnnotation(JacksonInject.class);
        if (jacksonInject == null) {
            return null;
        }
        String value = jacksonInject.value();
        if (value.length() == 0) {
            if (annotatedMember instanceof AnnotatedMethod) {
                AnnotatedMethod annotatedMethod = (AnnotatedMethod) annotatedMember;
                return annotatedMethod.getParameterCount() == 0 ? annotatedMember.getRawType().getName() : annotatedMethod.getParameterClass(0).getName();
            }
            return annotatedMember.getRawType().getName();
        }
        return value;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findKeyDeserializer(Annotated annotated) {
        Class keyUsing;
        JsonDeserialize jsonDeserialize = (JsonDeserialize) annotated.getAnnotation(JsonDeserialize.class);
        if (jsonDeserialize == null || (keyUsing = jsonDeserialize.keyUsing()) == KeyDeserializer.None.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findKeySerializer(Annotated annotated) {
        Class keyUsing;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (keyUsing = jsonSerialize.keyUsing()) == JsonSerializer.None.class) {
            return null;
        }
        return keyUsing;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String[] findPropertiesToIgnore(AnnotatedClass annotatedClass) {
        JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties) annotatedClass.getAnnotation(JsonIgnoreProperties.class);
        if (jsonIgnoreProperties == null) {
            return null;
        }
        return jsonIgnoreProperties.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return _findTypeResolver(mapperConfig, annotatedMember, javaType);
        }
        throw new IllegalArgumentException("Must call method with a container type (got " + javaType + ")");
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String findPropertyNameForParam(AnnotatedParameter annotatedParameter) {
        JsonProperty jsonProperty;
        if (annotatedParameter == null || (jsonProperty = (JsonProperty) annotatedParameter.getAnnotation(JsonProperty.class)) == null) {
            return null;
        }
        return jsonProperty.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public TypeResolverBuilder findPropertyTypeResolver(MapperConfig mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        if (javaType.isContainerType()) {
            return null;
        }
        return _findTypeResolver(mapperConfig, annotatedMember, javaType);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        JsonManagedReference jsonManagedReference = (JsonManagedReference) annotatedMember.getAnnotation(JsonManagedReference.class);
        if (jsonManagedReference != null) {
            return AnnotationIntrospector.ReferenceProperty.managed(jsonManagedReference.value());
        }
        JsonBackReference jsonBackReference = (JsonBackReference) annotatedMember.getAnnotation(JsonBackReference.class);
        if (jsonBackReference != null) {
            return AnnotationIntrospector.ReferenceProperty.back(jsonBackReference.value());
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String findRootName(AnnotatedClass annotatedClass) {
        JsonRootName jsonRootName = (JsonRootName) annotatedClass.getAnnotation(JsonRootName.class);
        if (jsonRootName == null) {
            return null;
        }
        return jsonRootName.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String findSerializablePropertyName(AnnotatedField annotatedField) {
        JsonProperty jsonProperty = (JsonProperty) annotatedField.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        if (annotatedField.hasAnnotation(JsonSerialize.class) || annotatedField.hasAnnotation(JsonView.class)) {
            return "";
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findSerializationContentType(Annotated annotated, JavaType javaType) {
        Class contentAs;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (contentAs = jsonSerialize.contentAs()) == NoClass.class) {
            return null;
        }
        return contentAs;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public JsonSerialize.Inclusion findSerializationInclusion(Annotated annotated, JsonSerialize.Inclusion inclusion) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize != null) {
            return jsonSerialize.include();
        }
        JsonWriteNullProperties jsonWriteNullProperties = (JsonWriteNullProperties) annotated.getAnnotation(JsonWriteNullProperties.class);
        return jsonWriteNullProperties != null ? jsonWriteNullProperties.value() ? JsonSerialize.Inclusion.ALWAYS : JsonSerialize.Inclusion.NON_NULL : inclusion;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findSerializationKeyType(Annotated annotated, JavaType javaType) {
        Class keyAs;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (keyAs = jsonSerialize.keyAs()) == NoClass.class) {
            return null;
        }
        return keyAs;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return jsonPropertyOrder.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean findSerializationSortAlphabetically(AnnotatedClass annotatedClass) {
        JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder) annotatedClass.getAnnotation(JsonPropertyOrder.class);
        if (jsonPropertyOrder == null) {
            return null;
        }
        return Boolean.valueOf(jsonPropertyOrder.alphabetic());
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class findSerializationType(Annotated annotated) {
        Class as;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (as = jsonSerialize.as()) == NoClass.class) {
            return null;
        }
        return as;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null) {
            return null;
        }
        return jsonSerialize.typing();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Class[] findSerializationViews(Annotated annotated) {
        JsonView jsonView = (JsonView) annotated.getAnnotation(JsonView.class);
        if (jsonView == null) {
            return null;
        }
        return jsonView.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findSerializer(Annotated annotated) {
        Class using;
        JsonSerialize jsonSerialize = (JsonSerialize) annotated.getAnnotation(JsonSerialize.class);
        if (jsonSerialize == null || (using = jsonSerialize.using()) == JsonSerializer.None.class) {
            JsonRawValue jsonRawValue = (JsonRawValue) annotated.getAnnotation(JsonRawValue.class);
            if (jsonRawValue == null || !jsonRawValue.value()) {
                return null;
            }
            return new RawSerializer(annotated.getRawType());
        }
        return using;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String findSettablePropertyName(AnnotatedMethod annotatedMethod) {
        JsonProperty jsonProperty = (JsonProperty) annotatedMethod.getAnnotation(JsonProperty.class);
        if (jsonProperty != null) {
            return jsonProperty.value();
        }
        JsonSetter jsonSetter = (JsonSetter) annotatedMethod.getAnnotation(JsonSetter.class);
        if (jsonSetter != null) {
            return jsonSetter.value();
        }
        if (annotatedMethod.hasAnnotation(JsonDeserialize.class) || annotatedMethod.hasAnnotation(JsonView.class) || annotatedMethod.hasAnnotation(JsonBackReference.class) || annotatedMethod.hasAnnotation(JsonManagedReference.class)) {
            return "";
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public List findSubtypes(Annotated annotated) {
        JsonSubTypes jsonSubTypes = (JsonSubTypes) annotated.getAnnotation(JsonSubTypes.class);
        if (jsonSubTypes == null) {
            return null;
        }
        JsonSubTypes.Type[] value = jsonSubTypes.value();
        ArrayList arrayList = new ArrayList(value.length);
        for (JsonSubTypes.Type type : value) {
            arrayList.add(new NamedType(type.value(), type.name()));
        }
        return arrayList;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public String findTypeName(AnnotatedClass annotatedClass) {
        JsonTypeName jsonTypeName = (JsonTypeName) annotatedClass.getAnnotation(JsonTypeName.class);
        if (jsonTypeName == null) {
            return null;
        }
        return jsonTypeName.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public TypeResolverBuilder findTypeResolver(MapperConfig mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return _findTypeResolver(mapperConfig, annotatedClass, javaType);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator) annotatedClass.getAnnotation(JsonValueInstantiator.class);
        if (jsonValueInstantiator == null) {
            return null;
        }
        return jsonValueInstantiator.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnyGetter.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.hasAnnotation(JsonAnySetter.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        JsonValue jsonValue = (JsonValue) annotatedMethod.getAnnotation(JsonValue.class);
        return jsonValue != null && jsonValue.value();
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasCreatorAnnotation(Annotated annotated) {
        return annotated.hasAnnotation(JsonCreator.class);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return _isIgnorable(annotatedMember);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isHandled(Annotation annotation) {
        return annotation.annotationType().getAnnotation(JacksonAnnotation.class) != null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableConstructor(AnnotatedConstructor annotatedConstructor) {
        return _isIgnorable(annotatedConstructor);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableField(AnnotatedField annotatedField) {
        return _isIgnorable(annotatedField);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public boolean isIgnorableMethod(AnnotatedMethod annotatedMethod) {
        return _isIgnorable(annotatedMethod);
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        JsonIgnoreType jsonIgnoreType = (JsonIgnoreType) annotatedClass.getAnnotation(JsonIgnoreType.class);
        if (jsonIgnoreType == null) {
            return null;
        }
        return Boolean.valueOf(jsonIgnoreType.value());
    }

    @Override // com.flurry.org.codehaus.jackson.map.AnnotationIntrospector
    public Boolean shouldUnwrapProperty(AnnotatedMember annotatedMember) {
        JsonUnwrapped jsonUnwrapped = (JsonUnwrapped) annotatedMember.getAnnotation(JsonUnwrapped.class);
        if (jsonUnwrapped == null || !jsonUnwrapped.enabled()) {
            return null;
        }
        return Boolean.TRUE;
    }
}
