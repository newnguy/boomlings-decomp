package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerFactory;
import com.flurry.org.codehaus.jackson.map.Serializers;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class BeanSerializerFactory extends BasicSerializerFactory {
    public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);
    protected final SerializerFactory.Config _factoryConfig;

    /* loaded from: classes.dex */
    public class ConfigImpl extends SerializerFactory.Config {
        protected final Serializers[] _additionalKeySerializers;
        protected final Serializers[] _additionalSerializers;
        protected final BeanSerializerModifier[] _modifiers;
        protected static final Serializers[] NO_SERIALIZERS = new Serializers[0];
        protected static final BeanSerializerModifier[] NO_MODIFIERS = new BeanSerializerModifier[0];

        public ConfigImpl() {
            this(null, null, null);
        }

        protected ConfigImpl(Serializers[] serializersArr, Serializers[] serializersArr2, BeanSerializerModifier[] beanSerializerModifierArr) {
            this._additionalSerializers = serializersArr == null ? NO_SERIALIZERS : serializersArr;
            this._additionalKeySerializers = serializersArr2 == null ? NO_SERIALIZERS : serializersArr2;
            this._modifiers = beanSerializerModifierArr == null ? NO_MODIFIERS : beanSerializerModifierArr;
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasKeySerializers() {
            return this._additionalKeySerializers.length > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasSerializerModifiers() {
            return this._modifiers.length > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public boolean hasSerializers() {
            return this._additionalSerializers.length > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable keySerializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalKeySerializers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable serializerModifiers() {
            return ArrayBuilders.arrayAsIterable(this._modifiers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public Iterable serializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalSerializers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withAdditionalKeySerializers(Serializers serializers) {
            if (serializers == null) {
                throw new IllegalArgumentException("Can not pass null Serializers");
            }
            return new ConfigImpl(this._additionalSerializers, (Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeySerializers, serializers), this._modifiers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withAdditionalSerializers(Serializers serializers) {
            if (serializers == null) {
                throw new IllegalArgumentException("Can not pass null Serializers");
            }
            return new ConfigImpl((Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalSerializers, serializers), this._additionalKeySerializers, this._modifiers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory.Config
        public SerializerFactory.Config withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
            if (beanSerializerModifier == null) {
                throw new IllegalArgumentException("Can not pass null modifier");
            }
            return new ConfigImpl(this._additionalSerializers, this._additionalKeySerializers, (BeanSerializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanSerializerModifier));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerFactory(SerializerFactory.Config config) {
        this._factoryConfig = config == null ? new ConfigImpl() : config;
    }

    protected BeanPropertyWriter _constructWriter(SerializationConfig serializationConfig, TypeBindings typeBindings, PropertyBuilder propertyBuilder, boolean z, String str, AnnotatedMember annotatedMember) {
        if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMember.fixAccess();
        }
        JavaType type = annotatedMember.getType(typeBindings);
        BeanProperty.Std std = new BeanProperty.Std(str, type, propertyBuilder.getClassAnnotations(), annotatedMember);
        BeanPropertyWriter buildWriter = propertyBuilder.buildWriter(str, type, findSerializerFromAnnotation(serializationConfig, annotatedMember, std), findPropertyTypeSerializer(type, serializationConfig, annotatedMember, std), ClassUtil.isCollectionMapOrArray(type.getRawClass()) ? findPropertyContentTypeSerializer(type, serializationConfig, annotatedMember, std) : null, annotatedMember, z);
        buildWriter.setViews(serializationConfig.getAnnotationIntrospector().findSerializationViews(annotatedMember));
        return buildWriter;
    }

    protected JsonSerializer constructBeanSerializer(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        List list;
        List list2;
        BeanSerializerBuilder beanSerializerBuilder;
        if (basicBeanDescription.getBeanClass() == Object.class) {
            throw new IllegalArgumentException("Can not create bean serializer for Object.class");
        }
        BeanSerializerBuilder constructBeanSerializerBuilder = constructBeanSerializerBuilder(basicBeanDescription);
        List findBeanProperties = findBeanProperties(serializationConfig, basicBeanDescription);
        if (findBeanProperties == null) {
            findBeanProperties = new ArrayList();
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            Iterator it = this._factoryConfig.serializerModifiers().iterator();
            while (true) {
                list = findBeanProperties;
                if (!it.hasNext()) {
                    break;
                }
                findBeanProperties = ((BeanSerializerModifier) it.next()).changeProperties(serializationConfig, basicBeanDescription, list);
            }
        } else {
            list = findBeanProperties;
        }
        List sortBeanProperties = sortBeanProperties(serializationConfig, basicBeanDescription, filterBeanProperties(serializationConfig, basicBeanDescription, list));
        if (this._factoryConfig.hasSerializerModifiers()) {
            Iterator it2 = this._factoryConfig.serializerModifiers().iterator();
            while (true) {
                list2 = sortBeanProperties;
                if (!it2.hasNext()) {
                    break;
                }
                sortBeanProperties = ((BeanSerializerModifier) it2.next()).orderProperties(serializationConfig, basicBeanDescription, list2);
            }
        } else {
            list2 = sortBeanProperties;
        }
        constructBeanSerializerBuilder.setProperties(list2);
        constructBeanSerializerBuilder.setFilterId(findFilterId(serializationConfig, basicBeanDescription));
        AnnotatedMethod findAnyGetter = basicBeanDescription.findAnyGetter();
        if (findAnyGetter != null) {
            if (serializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
                findAnyGetter.fixAccess();
            }
            JavaType type = findAnyGetter.getType(basicBeanDescription.bindingsForBeanType());
            constructBeanSerializerBuilder.setAnyGetter(new AnyGetterWriter(findAnyGetter, com.flurry.org.codehaus.jackson.map.ser.std.MapSerializer.construct(null, type, serializationConfig.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING), createTypeSerializer(serializationConfig, type.getContentType(), beanProperty), beanProperty, null, null)));
        }
        processViews(serializationConfig, constructBeanSerializerBuilder);
        if (this._factoryConfig.hasSerializerModifiers()) {
            beanSerializerBuilder = constructBeanSerializerBuilder;
            for (BeanSerializerModifier beanSerializerModifier : this._factoryConfig.serializerModifiers()) {
                beanSerializerBuilder = beanSerializerModifier.updateBuilder(serializationConfig, basicBeanDescription, beanSerializerBuilder);
            }
        } else {
            beanSerializerBuilder = constructBeanSerializerBuilder;
        }
        JsonSerializer build = beanSerializerBuilder.build();
        return (build == null && basicBeanDescription.hasKnownClassAnnotations()) ? beanSerializerBuilder.createDummy() : build;
    }

    protected BeanSerializerBuilder constructBeanSerializerBuilder(BasicBeanDescription basicBeanDescription) {
        return new BeanSerializerBuilder(basicBeanDescription);
    }

    protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter beanPropertyWriter, Class[] clsArr) {
        return FilteredBeanPropertyWriter.constructViewBased(beanPropertyWriter, clsArr);
    }

    protected PropertyBuilder constructPropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        return new PropertyBuilder(serializationConfig, basicBeanDescription);
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer createKeySerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer jsonSerializer = null;
        if (this._factoryConfig.hasKeySerializers()) {
            BasicBeanDescription basicBeanDescription = (BasicBeanDescription) serializationConfig.introspectClassAnnotations(javaType.getRawClass());
            Iterator it = this._factoryConfig.keySerializers().iterator();
            while (it.hasNext() && (jsonSerializer = ((Serializers) it.next()).findSerializer(serializationConfig, javaType, basicBeanDescription, beanProperty)) == null) {
            }
        }
        return jsonSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.BasicSerializerFactory, com.flurry.org.codehaus.jackson.map.SerializerFactory
    public JsonSerializer createSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) serializationConfig.introspect(javaType);
        JsonSerializer findSerializerFromAnnotation = findSerializerFromAnnotation(serializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findSerializerFromAnnotation != null) {
            return findSerializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(serializationConfig, basicBeanDescription.getClassInfo(), javaType);
        boolean z = modifyTypeByAnnotation != javaType;
        if (javaType.isContainerType()) {
            return buildContainerSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty, z);
        }
        for (Serializers serializers : this._factoryConfig.serializers()) {
            JsonSerializer findSerializer = serializers.findSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty);
            if (findSerializer != null) {
                return findSerializer;
            }
        }
        JsonSerializer findSerializerByLookup = findSerializerByLookup(modifyTypeByAnnotation, serializationConfig, basicBeanDescription, beanProperty, z);
        if (findSerializerByLookup == null) {
            JsonSerializer findSerializerByPrimaryType = findSerializerByPrimaryType(modifyTypeByAnnotation, serializationConfig, basicBeanDescription, beanProperty, z);
            if (findSerializerByPrimaryType == null) {
                JsonSerializer findBeanSerializer = findBeanSerializer(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty);
                return findBeanSerializer == null ? findSerializerByAddonType(serializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty, z) : findBeanSerializer;
            }
            return findSerializerByPrimaryType;
        }
        return findSerializerByLookup;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.BasicSerializerFactory
    protected Iterable customSerializers() {
        return this._factoryConfig.serializers();
    }

    protected List filterBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List list) {
        String[] findPropertiesToIgnore = serializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo());
        if (findPropertiesToIgnore != null && findPropertiesToIgnore.length > 0) {
            HashSet arrayToSet = ArrayBuilders.arrayToSet(findPropertiesToIgnore);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (arrayToSet.contains(((BeanPropertyWriter) it.next()).getName())) {
                    it.remove();
                }
            }
        }
        return list;
    }

    protected List findBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        List<BeanPropertyDefinition> findProperties = basicBeanDescription.findProperties();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        removeIgnorableTypes(serializationConfig, basicBeanDescription, findProperties);
        if (serializationConfig.isEnabled(SerializationConfig.Feature.REQUIRE_SETTERS_FOR_GETTERS)) {
            removeSetterlessGetters(serializationConfig, basicBeanDescription, findProperties);
        }
        if (findProperties.isEmpty()) {
            return null;
        }
        boolean usesStaticTyping = usesStaticTyping(serializationConfig, basicBeanDescription, null, null);
        PropertyBuilder constructPropertyBuilder = constructPropertyBuilder(serializationConfig, basicBeanDescription);
        ArrayList arrayList = new ArrayList(findProperties.size());
        TypeBindings bindingsForBeanType = basicBeanDescription.bindingsForBeanType();
        for (BeanPropertyDefinition beanPropertyDefinition : findProperties) {
            AnnotatedMember accessor = beanPropertyDefinition.getAccessor();
            AnnotationIntrospector.ReferenceProperty findReferenceType = annotationIntrospector.findReferenceType(accessor);
            if (findReferenceType == null || !findReferenceType.isBackReference()) {
                String name = beanPropertyDefinition.getName();
                if (accessor instanceof AnnotatedMethod) {
                    arrayList.add(_constructWriter(serializationConfig, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, name, (AnnotatedMethod) accessor));
                } else {
                    arrayList.add(_constructWriter(serializationConfig, bindingsForBeanType, constructPropertyBuilder, usesStaticTyping, name, (AnnotatedField) accessor));
                }
            }
        }
        return arrayList;
    }

    public JsonSerializer findBeanSerializer(SerializationConfig serializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        if (!isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        JsonSerializer constructBeanSerializer = constructBeanSerializer(serializationConfig, basicBeanDescription, beanProperty);
        if (!this._factoryConfig.hasSerializerModifiers()) {
            return constructBeanSerializer;
        }
        Iterator it = this._factoryConfig.serializerModifiers().iterator();
        while (true) {
            JsonSerializer jsonSerializer = constructBeanSerializer;
            if (!it.hasNext()) {
                return jsonSerializer;
            }
            constructBeanSerializer = ((BeanSerializerModifier) it.next()).modifySerializer(serializationConfig, basicBeanDescription, jsonSerializer);
        }
    }

    protected Object findFilterId(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        return serializationConfig.getAnnotationIntrospector().findFilterId(basicBeanDescription.getClassInfo());
    }

    public TypeSerializer findPropertyContentTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        JavaType contentType = javaType.getContentType();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(serializationConfig, annotatedMember, javaType);
        return findPropertyContentTypeResolver == null ? createTypeSerializer(serializationConfig, contentType, beanProperty) : findPropertyContentTypeResolver.buildTypeSerializer(serializationConfig, contentType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, serializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeSerializer findPropertyTypeSerializer(JavaType javaType, SerializationConfig serializationConfig, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(serializationConfig, annotatedMember, javaType);
        return findPropertyTypeResolver == null ? createTypeSerializer(serializationConfig, javaType, beanProperty) : findPropertyTypeResolver.buildTypeSerializer(serializationConfig, javaType, serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, serializationConfig, annotationIntrospector), beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory.Config getConfig() {
        return this._factoryConfig;
    }

    protected boolean isPotentialBeanType(Class cls) {
        return ClassUtil.canBeABeanType(cls) == null && !ClassUtil.isProxyType(cls);
    }

    protected void processViews(SerializationConfig serializationConfig, BeanSerializerBuilder beanSerializerBuilder) {
        List properties = beanSerializerBuilder.getProperties();
        boolean isEnabled = serializationConfig.isEnabled(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION);
        int size = properties.size();
        BeanPropertyWriter[] beanPropertyWriterArr = new BeanPropertyWriter[size];
        int i = 0;
        int i2 = 0;
        while (i < size) {
            BeanPropertyWriter beanPropertyWriter = (BeanPropertyWriter) properties.get(i);
            Class[] views = beanPropertyWriter.getViews();
            if (views != null) {
                i2++;
                beanPropertyWriterArr[i] = constructFilteredBeanWriter(beanPropertyWriter, views);
            } else if (isEnabled) {
                beanPropertyWriterArr[i] = beanPropertyWriter;
            }
            i++;
            i2 = i2;
        }
        if (isEnabled && i2 == 0) {
            return;
        }
        beanSerializerBuilder.setFilteredProperties(beanPropertyWriterArr);
    }

    protected void removeIgnorableTypes(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List list) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AnnotatedMember accessor = ((BeanPropertyDefinition) it.next()).getAccessor();
            if (accessor == null) {
                it.remove();
            } else {
                Class rawType = accessor.getRawType();
                Boolean bool = (Boolean) hashMap.get(rawType);
                if (bool == null) {
                    bool = annotationIntrospector.isIgnorableType(((BasicBeanDescription) serializationConfig.introspectClassAnnotations(rawType)).getClassInfo());
                    if (bool == null) {
                        bool = Boolean.FALSE;
                    }
                    hashMap.put(rawType, bool);
                }
                if (bool.booleanValue()) {
                    it.remove();
                }
            }
        }
    }

    protected void removeSetterlessGetters(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!((BeanPropertyDefinition) it.next()).couldDeserialize()) {
                it.remove();
            }
        }
    }

    @Deprecated
    protected List sortBeanProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List list) {
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerFactory
    public SerializerFactory withConfig(SerializerFactory.Config config) {
        if (this._factoryConfig == config) {
            return this;
        }
        if (getClass() != BeanSerializerFactory.class) {
            throw new IllegalStateException("Subtype of BeanSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with additional serializer definitions");
        }
        return new BeanSerializerFactory(config);
    }
}
