package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.AbstractTypeResolver;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.Deserializers;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializers;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.map.deser.impl.CreatorCollector;
import com.flurry.org.codehaus.jackson.map.deser.impl.CreatorProperty;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class BeanDeserializerFactory extends BasicDeserializerFactory {
    private static final Class[] INIT_CAUSE_PARAMS = {Throwable.class};
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(null);
    protected final DeserializerFactory.Config _factoryConfig;

    /* loaded from: classes.dex */
    public class ConfigImpl extends DeserializerFactory.Config {
        protected final AbstractTypeResolver[] _abstractTypeResolvers;
        protected final Deserializers[] _additionalDeserializers;
        protected final KeyDeserializers[] _additionalKeyDeserializers;
        protected final BeanDeserializerModifier[] _modifiers;
        protected final ValueInstantiators[] _valueInstantiators;
        protected static final KeyDeserializers[] NO_KEY_DESERIALIZERS = new KeyDeserializers[0];
        protected static final BeanDeserializerModifier[] NO_MODIFIERS = new BeanDeserializerModifier[0];
        protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];
        protected static final ValueInstantiators[] NO_VALUE_INSTANTIATORS = new ValueInstantiators[0];

        public ConfigImpl() {
            this(null, null, null, null, null);
        }

        protected ConfigImpl(Deserializers[] deserializersArr, KeyDeserializers[] keyDeserializersArr, BeanDeserializerModifier[] beanDeserializerModifierArr, AbstractTypeResolver[] abstractTypeResolverArr, ValueInstantiators[] valueInstantiatorsArr) {
            this._additionalDeserializers = deserializersArr == null ? BeanDeserializerFactory.NO_DESERIALIZERS : deserializersArr;
            this._additionalKeyDeserializers = keyDeserializersArr == null ? NO_KEY_DESERIALIZERS : keyDeserializersArr;
            this._modifiers = beanDeserializerModifierArr == null ? NO_MODIFIERS : beanDeserializerModifierArr;
            this._abstractTypeResolvers = abstractTypeResolverArr == null ? NO_ABSTRACT_TYPE_RESOLVERS : abstractTypeResolverArr;
            this._valueInstantiators = valueInstantiatorsArr == null ? NO_VALUE_INSTANTIATORS : valueInstantiatorsArr;
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable abstractTypeResolvers() {
            return ArrayBuilders.arrayAsIterable(this._abstractTypeResolvers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable deserializerModifiers() {
            return ArrayBuilders.arrayAsIterable(this._modifiers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable deserializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalDeserializers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasAbstractTypeResolvers() {
            return this._abstractTypeResolvers.length > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasDeserializerModifiers() {
            return this._modifiers.length > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasDeserializers() {
            return this._additionalDeserializers.length > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasKeyDeserializers() {
            return this._additionalKeyDeserializers.length > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public boolean hasValueInstantiators() {
            return this._valueInstantiators.length > 0;
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable keyDeserializers() {
            return ArrayBuilders.arrayAsIterable(this._additionalKeyDeserializers);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public Iterable valueInstantiators() {
            return ArrayBuilders.arrayAsIterable(this._valueInstantiators);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
            if (abstractTypeResolver == null) {
                throw new IllegalArgumentException("Can not pass null resolver");
            }
            return new ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, (AbstractTypeResolver[]) ArrayBuilders.insertInListNoDup(this._abstractTypeResolvers, abstractTypeResolver), this._valueInstantiators);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAdditionalDeserializers(Deserializers deserializers) {
            if (deserializers == null) {
                throw new IllegalArgumentException("Can not pass null Deserializers");
            }
            return new ConfigImpl((Deserializers[]) ArrayBuilders.insertInListNoDup(this._additionalDeserializers, deserializers), this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
            if (keyDeserializers == null) {
                throw new IllegalArgumentException("Can not pass null KeyDeserializers");
            }
            return new ConfigImpl(this._additionalDeserializers, (KeyDeserializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeyDeserializers, keyDeserializers), this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
            if (beanDeserializerModifier == null) {
                throw new IllegalArgumentException("Can not pass null modifier");
            }
            return new ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, (BeanDeserializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanDeserializerModifier), this._abstractTypeResolvers, this._valueInstantiators);
        }

        @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory.Config
        public DeserializerFactory.Config withValueInstantiators(ValueInstantiators valueInstantiators) {
            if (valueInstantiators == null) {
                throw new IllegalArgumentException("Can not pass null resolver");
            }
            return new ConfigImpl(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, (ValueInstantiators[]) ArrayBuilders.insertInListNoDup(this._valueInstantiators, valueInstantiators));
        }
    }

    @Deprecated
    public BeanDeserializerFactory() {
        this(null);
    }

    public BeanDeserializerFactory(DeserializerFactory.Config config) {
        this._factoryConfig = config == null ? new ConfigImpl() : config;
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        Class<?> rawClass = javaType.getRawClass();
        com.flurry.org.codehaus.jackson.map.util.EnumResolver constructEnumResolver = constructEnumResolver(rawClass, deserializationConfig);
        for (AnnotatedMethod annotatedMethod : ((BasicBeanDescription) deserializationConfig.introspect(javaType)).getFactoryMethods()) {
            if (deserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() == 1 && annotatedMethod.getRawType().isAssignableFrom(rawClass)) {
                    if (annotatedMethod.getParameterType(0) != String.class) {
                        throw new IllegalArgumentException("Parameter #0 type for factory method (" + annotatedMethod + ") not suitable, must be java.lang.String");
                    }
                    if (deserializationConfig.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
                    }
                    return com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver, annotatedMethod);
                }
                throw new IllegalArgumentException("Unsuitable method (" + annotatedMethod + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
            }
        }
        return com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver);
    }

    protected void _addDeserializerConstructors(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, VisibilityChecker visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector) {
        for (AnnotatedConstructor annotatedConstructor : basicBeanDescription.getConstructors()) {
            int parameterCount = annotatedConstructor.getParameterCount();
            if (parameterCount >= 1) {
                boolean hasCreatorAnnotation = annotationIntrospector.hasCreatorAnnotation(annotatedConstructor);
                boolean isCreatorVisible = visibilityChecker.isCreatorVisible(annotatedConstructor);
                if (parameterCount == 1) {
                    _handleSingleArgumentConstructor(deserializationConfig, basicBeanDescription, visibilityChecker, annotationIntrospector, creatorCollector, annotatedConstructor, hasCreatorAnnotation, isCreatorVisible);
                } else if (hasCreatorAnnotation || isCreatorVisible) {
                    AnnotatedParameter annotatedParameter = null;
                    int i = 0;
                    int i2 = 0;
                    CreatorProperty[] creatorPropertyArr = new CreatorProperty[parameterCount];
                    int i3 = 0;
                    while (i3 < parameterCount) {
                        AnnotatedParameter parameter = annotatedConstructor.getParameter(i3);
                        String findPropertyNameForParam = parameter == null ? null : annotationIntrospector.findPropertyNameForParam(parameter);
                        Object findInjectableValueId = annotationIntrospector.findInjectableValueId(parameter);
                        if (findPropertyNameForParam != null && findPropertyNameForParam.length() > 0) {
                            i++;
                            creatorPropertyArr[i3] = constructCreatorProperty(deserializationConfig, basicBeanDescription, findPropertyNameForParam, i3, parameter, findInjectableValueId);
                            parameter = annotatedParameter;
                        } else if (findInjectableValueId != null) {
                            i2++;
                            creatorPropertyArr[i3] = constructCreatorProperty(deserializationConfig, basicBeanDescription, findPropertyNameForParam, i3, parameter, findInjectableValueId);
                            parameter = annotatedParameter;
                        } else if (annotatedParameter != null) {
                            parameter = annotatedParameter;
                        }
                        i3++;
                        annotatedParameter = parameter;
                    }
                    if (hasCreatorAnnotation || i > 0 || i2 > 0) {
                        if (i + i2 != parameterCount) {
                            if (i != 0 || i2 + 1 != parameterCount) {
                                throw new IllegalArgumentException("Argument #" + annotatedParameter.getIndex() + " of constructor " + annotatedConstructor + " has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
                            }
                            throw new IllegalArgumentException("Delegated constructor with Injectables not yet supported (see [JACKSON-712]) for " + annotatedConstructor);
                        }
                        creatorCollector.addPropertyCreator(annotatedConstructor, creatorPropertyArr);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void _addDeserializerFactoryMethods(com.flurry.org.codehaus.jackson.map.DeserializationConfig r17, com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription r18, com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker r19, com.flurry.org.codehaus.jackson.map.AnnotationIntrospector r20, com.flurry.org.codehaus.jackson.map.deser.impl.CreatorCollector r21) {
        /*
            r16 = this;
            java.util.List r1 = r18.getFactoryMethods()
            java.util.Iterator r15 = r1.iterator()
        L8:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto Lb5
            java.lang.Object r7 = r15.next()
            com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod r7 = (com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod) r7
            int r1 = r7.getParameterCount()
            r2 = 1
            if (r1 < r2) goto L8
            r0 = r20
            boolean r8 = r0.hasCreatorAnnotation(r7)
            r2 = 1
            if (r1 != r2) goto L4f
            r2 = 0
            com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter r2 = r7.getParameter(r2)
            r0 = r20
            java.lang.String r3 = r0.findPropertyNameForParam(r2)
            r0 = r20
            java.lang.Object r2 = r0.findInjectableValueId(r2)
            if (r2 != 0) goto L57
            if (r3 == 0) goto L3f
            int r2 = r3.length()
            if (r2 != 0) goto L57
        L3f:
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r1._handleSingleArgumentFactory(r2, r3, r4, r5, r6, r7, r8)
            goto L8
        L4f:
            r0 = r20
            boolean r2 = r0.hasCreatorAnnotation(r7)
            if (r2 == 0) goto L8
        L57:
            com.flurry.org.codehaus.jackson.map.deser.impl.CreatorProperty[] r2 = new com.flurry.org.codehaus.jackson.map.deser.impl.CreatorProperty[r1]
            r12 = 0
        L5a:
            if (r12 >= r1) goto Lae
            com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter r13 = r7.getParameter(r12)
            r0 = r20
            java.lang.String r11 = r0.findPropertyNameForParam(r13)
            r0 = r20
            java.lang.Object r14 = r0.findInjectableValueId(r13)
            if (r11 == 0) goto L74
            int r3 = r11.length()
            if (r3 != 0) goto L9f
        L74:
            if (r14 != 0) goto L9f
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Argument #"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r12)
            java.lang.String r3 = " of factory method "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r7)
            java.lang.String r3 = " has no property name annotation; must have when multiple-paramater static method annotated as Creator"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L9f:
            r8 = r16
            r9 = r17
            r10 = r18
            com.flurry.org.codehaus.jackson.map.deser.impl.CreatorProperty r3 = r8.constructCreatorProperty(r9, r10, r11, r12, r13, r14)
            r2[r12] = r3
            int r12 = r12 + 1
            goto L5a
        Lae:
            r0 = r21
            r0.addPropertyCreator(r7, r2)
            goto L8
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerFactory._addDeserializerFactoryMethods(com.flurry.org.codehaus.jackson.map.DeserializationConfig, com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription, com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker, com.flurry.org.codehaus.jackson.map.AnnotationIntrospector, com.flurry.org.codehaus.jackson.map.deser.impl.CreatorCollector):void");
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer findArrayDeserializer = deserializers.findArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, typeDeserializer, jsonDeserializer);
            if (findArrayDeserializer != null) {
                return findArrayDeserializer;
            }
        }
        return null;
    }

    protected JsonDeserializer _findCustomBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer findBeanDeserializer = deserializers.findBeanDeserializer(javaType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty);
            if (findBeanDeserializer != null) {
                return findBeanDeserializer;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer findCollectionDeserializer = deserializers.findCollectionDeserializer(collectionType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer, jsonDeserializer);
            if (findCollectionDeserializer != null) {
                return findCollectionDeserializer;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer findCollectionLikeDeserializer = deserializers.findCollectionLikeDeserializer(collectionLikeType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer, jsonDeserializer);
            if (findCollectionLikeDeserializer != null) {
                return findCollectionLikeDeserializer;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer _findCustomEnumDeserializer(Class cls, DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer findEnumDeserializer = deserializers.findEnumDeserializer(cls, deserializationConfig, basicBeanDescription, beanProperty);
            if (findEnumDeserializer != null) {
                return findEnumDeserializer;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer findMapDeserializer = deserializers.findMapDeserializer(mapType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapDeserializer != null) {
                return findMapDeserializer;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer findMapLikeDeserializer = deserializers.findMapLikeDeserializer(mapLikeType, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapLikeDeserializer != null) {
                return findMapLikeDeserializer;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory
    protected JsonDeserializer _findCustomTreeNodeDeserializer(Class cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty) {
        for (Deserializers deserializers : this._factoryConfig.deserializers()) {
            JsonDeserializer findTreeNodeDeserializer = deserializers.findTreeNodeDeserializer(cls, deserializationConfig, beanProperty);
            if (findTreeNodeDeserializer != null) {
                return findTreeNodeDeserializer;
            }
        }
        return null;
    }

    protected boolean _handleSingleArgumentConstructor(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, VisibilityChecker visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedConstructor annotatedConstructor, boolean z, boolean z2) {
        AnnotatedParameter parameter = annotatedConstructor.getParameter(0);
        String findPropertyNameForParam = annotationIntrospector.findPropertyNameForParam(parameter);
        Object findInjectableValueId = annotationIntrospector.findInjectableValueId(parameter);
        if (findInjectableValueId != null || (findPropertyNameForParam != null && findPropertyNameForParam.length() > 0)) {
            creatorCollector.addPropertyCreator(annotatedConstructor, new CreatorProperty[]{constructCreatorProperty(deserializationConfig, basicBeanDescription, findPropertyNameForParam, 0, parameter, findInjectableValueId)});
            return true;
        }
        Class parameterClass = annotatedConstructor.getParameterClass(0);
        if (parameterClass == String.class) {
            if (z || z2) {
                creatorCollector.addStringCreator(annotatedConstructor);
            }
            return true;
        } else if (parameterClass == Integer.TYPE || parameterClass == Integer.class) {
            if (z || z2) {
                creatorCollector.addIntCreator(annotatedConstructor);
            }
            return true;
        } else if (parameterClass == Long.TYPE || parameterClass == Long.class) {
            if (z || z2) {
                creatorCollector.addLongCreator(annotatedConstructor);
            }
            return true;
        } else if (parameterClass == Double.TYPE || parameterClass == Double.class) {
            if (z || z2) {
                creatorCollector.addDoubleCreator(annotatedConstructor);
            }
            return true;
        } else if (z) {
            creatorCollector.addDelegatingCreator(annotatedConstructor);
            return true;
        } else {
            return false;
        }
    }

    protected boolean _handleSingleArgumentFactory(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, VisibilityChecker visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedMethod annotatedMethod, boolean z) {
        Class parameterClass = annotatedMethod.getParameterClass(0);
        if (parameterClass == String.class) {
            if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                creatorCollector.addStringCreator(annotatedMethod);
                return true;
            }
            return true;
        } else if (parameterClass == Integer.TYPE || parameterClass == Integer.class) {
            if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                creatorCollector.addIntCreator(annotatedMethod);
                return true;
            }
            return true;
        } else if (parameterClass == Long.TYPE || parameterClass == Long.class) {
            if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                creatorCollector.addLongCreator(annotatedMethod);
                return true;
            }
            return true;
        } else if (parameterClass == Double.TYPE || parameterClass == Double.class) {
            if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
                creatorCollector.addDoubleCreator(annotatedMethod);
                return true;
            }
            return true;
        } else if (parameterClass != Boolean.TYPE && parameterClass != Boolean.class) {
            if (annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
                creatorCollector.addDelegatingCreator(annotatedMethod);
                return true;
            }
            return false;
        } else if (z || visibilityChecker.isCreatorVisible(annotatedMethod)) {
            creatorCollector.addBooleanCreator(annotatedMethod);
            return true;
        } else {
            return true;
        }
    }

    protected JavaType _mapAbstractType2(DeserializationConfig deserializationConfig, JavaType javaType) {
        Class rawClass = javaType.getRawClass();
        if (this._factoryConfig.hasAbstractTypeResolvers()) {
            for (AbstractTypeResolver abstractTypeResolver : this._factoryConfig.abstractTypeResolvers()) {
                JavaType findTypeMapping = abstractTypeResolver.findTypeMapping(deserializationConfig, javaType);
                if (findTypeMapping != null && findTypeMapping.getRawClass() != rawClass) {
                    return findTypeMapping;
                }
            }
        }
        return null;
    }

    protected void addBeanProps(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        List<BeanPropertyDefinition> findProperties = basicBeanDescription.findProperties();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Boolean findIgnoreUnknownProperties = annotationIntrospector.findIgnoreUnknownProperties(basicBeanDescription.getClassInfo());
        if (findIgnoreUnknownProperties != null) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(findIgnoreUnknownProperties.booleanValue());
        }
        HashSet<String> arrayToSet = ArrayBuilders.arrayToSet(annotationIntrospector.findPropertiesToIgnore(basicBeanDescription.getClassInfo()));
        for (String str : arrayToSet) {
            beanDeserializerBuilder.addIgnorable(str);
        }
        AnnotatedMethod findAnySetter = basicBeanDescription.findAnySetter();
        Set<String> ignoredPropertyNames = findAnySetter == null ? basicBeanDescription.getIgnoredPropertyNames() : basicBeanDescription.getIgnoredPropertyNamesForDeser();
        if (ignoredPropertyNames != null) {
            for (String str2 : ignoredPropertyNames) {
                beanDeserializerBuilder.addIgnorable(str2);
            }
        }
        HashMap hashMap = new HashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : findProperties) {
            String name = beanPropertyDefinition.getName();
            if (!arrayToSet.contains(name)) {
                if (beanPropertyDefinition.hasConstructorParameter()) {
                    beanDeserializerBuilder.addCreatorProperty(beanPropertyDefinition);
                } else if (beanPropertyDefinition.hasSetter()) {
                    AnnotatedMethod setter = beanPropertyDefinition.getSetter();
                    if (isIgnorableType(deserializationConfig, basicBeanDescription, setter.getParameterClass(0), hashMap)) {
                        beanDeserializerBuilder.addIgnorable(name);
                    } else {
                        SettableBeanProperty constructSettableProperty = constructSettableProperty(deserializationConfig, basicBeanDescription, name, setter);
                        if (constructSettableProperty != null) {
                            beanDeserializerBuilder.addProperty(constructSettableProperty);
                        }
                    }
                } else if (beanPropertyDefinition.hasField()) {
                    AnnotatedField field = beanPropertyDefinition.getField();
                    if (isIgnorableType(deserializationConfig, basicBeanDescription, field.getRawType(), hashMap)) {
                        beanDeserializerBuilder.addIgnorable(name);
                    } else {
                        SettableBeanProperty constructSettableProperty2 = constructSettableProperty(deserializationConfig, basicBeanDescription, name, field);
                        if (constructSettableProperty2 != null) {
                            beanDeserializerBuilder.addProperty(constructSettableProperty2);
                        }
                    }
                }
            }
        }
        if (findAnySetter != null) {
            beanDeserializerBuilder.setAnySetter(constructAnySetter(deserializationConfig, basicBeanDescription, findAnySetter));
        }
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS)) {
            for (BeanPropertyDefinition beanPropertyDefinition2 : findProperties) {
                if (beanPropertyDefinition2.hasGetter()) {
                    String name2 = beanPropertyDefinition2.getName();
                    if (!beanDeserializerBuilder.hasProperty(name2) && !arrayToSet.contains(name2)) {
                        AnnotatedMethod getter = beanPropertyDefinition2.getGetter();
                        Class rawType = getter.getRawType();
                        if (Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType)) {
                            if (!arrayToSet.contains(name2) && !beanDeserializerBuilder.hasProperty(name2)) {
                                beanDeserializerBuilder.addProperty(constructSetterlessProperty(deserializationConfig, basicBeanDescription, name2, getter));
                            }
                        }
                    }
                }
            }
        }
    }

    protected void addInjectables(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        Map findInjectables = basicBeanDescription.findInjectables();
        if (findInjectables != null) {
            boolean isEnabled = deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
            for (Map.Entry entry : findInjectables.entrySet()) {
                AnnotatedMember annotatedMember = (AnnotatedMember) entry.getValue();
                if (isEnabled) {
                    annotatedMember.fixAccess();
                }
                beanDeserializerBuilder.addInjectable(annotatedMember.getName(), basicBeanDescription.resolveType(annotatedMember.getGenericType()), basicBeanDescription.getClassAnnotations(), annotatedMember, entry.getKey());
            }
        }
    }

    protected void addReferenceProperties(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        Map findBackReferenceProperties = basicBeanDescription.findBackReferenceProperties();
        if (findBackReferenceProperties != null) {
            for (Map.Entry entry : findBackReferenceProperties.entrySet()) {
                String str = (String) entry.getKey();
                AnnotatedMember annotatedMember = (AnnotatedMember) entry.getValue();
                if (annotatedMember instanceof AnnotatedMethod) {
                    beanDeserializerBuilder.addBackReferenceProperty(str, constructSettableProperty(deserializationConfig, basicBeanDescription, annotatedMember.getName(), (AnnotatedMethod) annotatedMember));
                } else {
                    beanDeserializerBuilder.addBackReferenceProperty(str, constructSettableProperty(deserializationConfig, basicBeanDescription, annotatedMember.getName(), (AnnotatedField) annotatedMember));
                }
            }
        }
    }

    public JsonDeserializer buildBeanDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        BeanDeserializerBuilder beanDeserializerBuilder;
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationConfig, basicBeanDescription);
        if (javaType.isAbstract() && !findValueInstantiator.canInstantiate()) {
            return new AbstractDeserializer(javaType);
        }
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(basicBeanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
        addInjectables(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator it = this._factoryConfig.deserializerModifiers().iterator();
            while (true) {
                beanDeserializerBuilder = constructBeanDeserializerBuilder;
                if (!it.hasNext()) {
                    break;
                }
                constructBeanDeserializerBuilder = ((BeanDeserializerModifier) it.next()).updateBuilder(deserializationConfig, basicBeanDescription, beanDeserializerBuilder);
            }
        } else {
            beanDeserializerBuilder = constructBeanDeserializerBuilder;
        }
        JsonDeserializer build = beanDeserializerBuilder.build(beanProperty);
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return build;
        }
        Iterator it2 = this._factoryConfig.deserializerModifiers().iterator();
        while (true) {
            JsonDeserializer jsonDeserializer = build;
            if (!it2.hasNext()) {
                return jsonDeserializer;
            }
            build = ((BeanDeserializerModifier) it2.next()).modifyDeserializer(deserializationConfig, basicBeanDescription, jsonDeserializer);
        }
    }

    public JsonDeserializer buildThrowableDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty) {
        BeanDeserializerBuilder beanDeserializerBuilder;
        SettableBeanProperty constructSettableProperty;
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(basicBeanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationConfig, basicBeanDescription));
        addBeanProps(deserializationConfig, basicBeanDescription, constructBeanDeserializerBuilder);
        AnnotatedMethod findMethod = basicBeanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (findMethod != null && (constructSettableProperty = constructSettableProperty(deserializationConfig, basicBeanDescription, "cause", findMethod)) != null) {
            constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
        }
        constructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        constructBeanDeserializerBuilder.addIgnorable("message");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            Iterator it = this._factoryConfig.deserializerModifiers().iterator();
            while (true) {
                beanDeserializerBuilder = constructBeanDeserializerBuilder;
                if (!it.hasNext()) {
                    break;
                }
                constructBeanDeserializerBuilder = ((BeanDeserializerModifier) it.next()).updateBuilder(deserializationConfig, basicBeanDescription, beanDeserializerBuilder);
            }
        } else {
            beanDeserializerBuilder = constructBeanDeserializerBuilder;
        }
        JsonDeserializer build = beanDeserializerBuilder.build(beanProperty);
        if (build instanceof BeanDeserializer) {
            build = new com.flurry.org.codehaus.jackson.map.deser.std.ThrowableDeserializer((BeanDeserializer) build);
        }
        if (!this._factoryConfig.hasDeserializerModifiers()) {
            return build;
        }
        Iterator it2 = this._factoryConfig.deserializerModifiers().iterator();
        while (true) {
            JsonDeserializer jsonDeserializer = build;
            if (!it2.hasNext()) {
                return jsonDeserializer;
            }
            build = ((BeanDeserializerModifier) it2.next()).modifyDeserializer(deserializationConfig, basicBeanDescription, jsonDeserializer);
        }
    }

    protected SettableAnyProperty constructAnySetter(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, AnnotatedMethod annotatedMethod) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType resolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedMethod.getParameterType(1));
        BeanProperty.Std std = new BeanProperty.Std(annotatedMethod.getName(), resolveType, basicBeanDescription.getClassAnnotations(), annotatedMethod);
        JavaType resolveType2 = resolveType(deserializationConfig, basicBeanDescription, resolveType, annotatedMethod, std);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, std);
        return findDeserializerFromAnnotation != null ? new SettableAnyProperty(std, annotatedMethod, resolveType2, findDeserializerFromAnnotation) : new SettableAnyProperty(std, annotatedMethod, modifyTypeByAnnotation(deserializationConfig, annotatedMethod, resolveType2, std.getName()), (JsonDeserializer) null);
    }

    protected BeanDeserializerBuilder constructBeanDeserializerBuilder(BasicBeanDescription basicBeanDescription) {
        return new BeanDeserializerBuilder(basicBeanDescription);
    }

    protected CreatorProperty constructCreatorProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, int i, AnnotatedParameter annotatedParameter, Object obj) {
        JavaType constructType = deserializationConfig.getTypeFactory().constructType(annotatedParameter.getParameterType(), basicBeanDescription.bindingsForBeanType());
        BeanProperty.Std std = new BeanProperty.Std(str, constructType, basicBeanDescription.getClassAnnotations(), annotatedParameter);
        JavaType resolveType = resolveType(deserializationConfig, basicBeanDescription, constructType, annotatedParameter, std);
        if (resolveType != constructType) {
            std = std.withType(resolveType);
        }
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedParameter, std);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedParameter, resolveType, str);
        TypeDeserializer typeDeserializer = (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler();
        CreatorProperty creatorProperty = new CreatorProperty(str, modifyTypeByAnnotation, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, modifyTypeByAnnotation, std) : typeDeserializer, basicBeanDescription.getClassAnnotations(), annotatedParameter, i, obj);
        return findDeserializerFromAnnotation != null ? creatorProperty.withValueDeserializer(findDeserializerFromAnnotation) : creatorProperty;
    }

    protected ValueInstantiator constructDefaultValueInstantiator(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) {
        AnnotatedConstructor findDefaultConstructor;
        boolean isEnabled = deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
        CreatorCollector creatorCollector = new CreatorCollector(basicBeanDescription, isEnabled);
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        if (basicBeanDescription.getType().isConcrete() && (findDefaultConstructor = basicBeanDescription.findDefaultConstructor()) != null) {
            if (isEnabled) {
                ClassUtil.checkAndFixAccess(findDefaultConstructor.getAnnotated());
            }
            creatorCollector.setDefaultConstructor(findDefaultConstructor);
        }
        VisibilityChecker findAutoDetectVisibility = deserializationConfig.getAnnotationIntrospector().findAutoDetectVisibility(basicBeanDescription.getClassInfo(), deserializationConfig.getDefaultVisibilityChecker());
        _addDeserializerFactoryMethods(deserializationConfig, basicBeanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector);
        _addDeserializerConstructors(deserializationConfig, basicBeanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector);
        return creatorCollector.constructValueInstantiator(deserializationConfig);
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedField annotatedField) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedField.fixAccess();
        }
        JavaType resolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedField.getGenericType());
        BeanProperty.Std std = new BeanProperty.Std(str, resolveType, basicBeanDescription.getClassAnnotations(), annotatedField);
        JavaType resolveType2 = resolveType(deserializationConfig, basicBeanDescription, resolveType, annotatedField, std);
        if (resolveType2 != resolveType) {
            std = std.withType(resolveType2);
        }
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedField, std);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedField, resolveType2, str);
        SettableBeanProperty fieldProperty = new SettableBeanProperty.FieldProperty(str, modifyTypeByAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedField);
        if (findDeserializerFromAnnotation != null) {
            fieldProperty = fieldProperty.withValueDeserializer(findDeserializerFromAnnotation);
        }
        AnnotationIntrospector.ReferenceProperty findReferenceType = deserializationConfig.getAnnotationIntrospector().findReferenceType(annotatedField);
        if (findReferenceType != null && findReferenceType.isManagedReference()) {
            fieldProperty.setManagedReferenceName(findReferenceType.getName());
        }
        return fieldProperty;
    }

    protected SettableBeanProperty constructSettableProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedMethod annotatedMethod) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType resolveType = basicBeanDescription.bindingsForBeanType().resolveType(annotatedMethod.getParameterType(0));
        BeanProperty.Std std = new BeanProperty.Std(str, resolveType, basicBeanDescription.getClassAnnotations(), annotatedMethod);
        JavaType resolveType2 = resolveType(deserializationConfig, basicBeanDescription, resolveType, annotatedMethod, std);
        if (resolveType2 != resolveType) {
            std = std.withType(resolveType2);
        }
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, std);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedMethod, resolveType2, str);
        SettableBeanProperty methodProperty = new SettableBeanProperty.MethodProperty(str, modifyTypeByAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedMethod);
        if (findDeserializerFromAnnotation != null) {
            methodProperty = methodProperty.withValueDeserializer(findDeserializerFromAnnotation);
        }
        AnnotationIntrospector.ReferenceProperty findReferenceType = deserializationConfig.getAnnotationIntrospector().findReferenceType(annotatedMethod);
        if (findReferenceType != null && findReferenceType.isManagedReference()) {
            methodProperty.setManagedReferenceName(findReferenceType.getName());
        }
        return methodProperty;
    }

    protected SettableBeanProperty constructSetterlessProperty(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, String str, AnnotatedMethod annotatedMethod) {
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            annotatedMethod.fixAccess();
        }
        JavaType type = annotatedMethod.getType(basicBeanDescription.bindingsForBeanType());
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, annotatedMethod, new BeanProperty.Std(str, type, basicBeanDescription.getClassAnnotations(), annotatedMethod));
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, annotatedMethod, type, str);
        SettableBeanProperty.SetterlessProperty setterlessProperty = new SettableBeanProperty.SetterlessProperty(str, modifyTypeByAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler(), basicBeanDescription.getClassAnnotations(), annotatedMethod);
        return findDeserializerFromAnnotation != null ? setterlessProperty.withValueDeserializer(findDeserializerFromAnnotation) : setterlessProperty;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription;
        JavaType materializeAbstractType;
        if (javaType.isAbstract()) {
            javaType = mapAbstractType(deserializationConfig, javaType);
        }
        BasicBeanDescription basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspect(javaType);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), javaType, null);
        if (modifyTypeByAnnotation.getRawClass() != javaType.getRawClass()) {
            basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspect(modifyTypeByAnnotation);
        } else {
            basicBeanDescription = basicBeanDescription2;
            modifyTypeByAnnotation = javaType;
        }
        JsonDeserializer _findCustomBeanDeserializer = _findCustomBeanDeserializer(modifyTypeByAnnotation, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty);
        if (_findCustomBeanDeserializer == null) {
            if (modifyTypeByAnnotation.isThrowable()) {
                return buildThrowableDeserializer(deserializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty);
            }
            if (!modifyTypeByAnnotation.isAbstract() || (materializeAbstractType = materializeAbstractType(deserializationConfig, basicBeanDescription)) == null) {
                JsonDeserializer findStdBeanDeserializer = findStdBeanDeserializer(deserializationConfig, deserializerProvider, modifyTypeByAnnotation, beanProperty);
                if (findStdBeanDeserializer == null) {
                    if (isPotentialBeanType(modifyTypeByAnnotation.getRawClass())) {
                        return buildBeanDeserializer(deserializationConfig, modifyTypeByAnnotation, basicBeanDescription, beanProperty);
                    }
                    return null;
                }
                return findStdBeanDeserializer;
            }
            return buildBeanDeserializer(deserializationConfig, materializeAbstractType, (BasicBeanDescription) deserializationConfig.introspect(materializeAbstractType), beanProperty);
        }
        return _findCustomBeanDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public KeyDeserializer createKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        if (this._factoryConfig.hasKeyDeserializers()) {
            BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(javaType.getRawClass());
            for (KeyDeserializers keyDeserializers : this._factoryConfig.keyDeserializers()) {
                KeyDeserializer findKeyDeserializer = keyDeserializers.findKeyDeserializer(javaType, deserializationConfig, basicBeanDescription, beanProperty);
                if (findKeyDeserializer != null) {
                    return findKeyDeserializer;
                }
            }
        }
        Class rawClass = javaType.getRawClass();
        if (rawClass == String.class || rawClass == Object.class) {
            return com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructStringKeyDeserializer(deserializationConfig, javaType);
        }
        KeyDeserializer keyDeserializer = (KeyDeserializer) _keyDeserializers.get(javaType);
        return keyDeserializer == null ? javaType.isEnumType() ? _createEnumKeyDeserializer(deserializationConfig, javaType, beanProperty) : com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.findStringBasedKeyDeserializer(deserializationConfig, javaType) : keyDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) {
        ValueInstantiator constructDefaultValueInstantiator;
        AnnotatedClass classInfo = basicBeanDescription.getClassInfo();
        Object findValueInstantiator = deserializationConfig.getAnnotationIntrospector().findValueInstantiator(classInfo);
        if (findValueInstantiator == null) {
            constructDefaultValueInstantiator = constructDefaultValueInstantiator(deserializationConfig, basicBeanDescription);
        } else if (findValueInstantiator instanceof ValueInstantiator) {
            constructDefaultValueInstantiator = (ValueInstantiator) findValueInstantiator;
        } else if (!(findValueInstantiator instanceof Class)) {
            throw new IllegalStateException("Invalid value instantiator returned for type " + basicBeanDescription + ": neither a Class nor ValueInstantiator");
        } else {
            Class cls = (Class) findValueInstantiator;
            if (!ValueInstantiator.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("Invalid instantiator Class<?> returned for type " + basicBeanDescription + ": " + cls.getName() + " not a ValueInstantiator");
            }
            constructDefaultValueInstantiator = deserializationConfig.valueInstantiatorInstance(classInfo, cls);
        }
        if (this._factoryConfig.hasValueInstantiators()) {
            ValueInstantiator valueInstantiator = constructDefaultValueInstantiator;
            for (ValueInstantiators valueInstantiators : this._factoryConfig.valueInstantiators()) {
                valueInstantiator = valueInstantiators.findValueInstantiator(deserializationConfig, basicBeanDescription, valueInstantiator);
                if (valueInstantiator == null) {
                    throw new JsonMappingException("Broken registered ValueInstantiators (of type " + valueInstantiators.getClass().getName() + "): returned null ValueInstantiator");
                }
            }
            return valueInstantiator;
        }
        return constructDefaultValueInstantiator;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public final DeserializerFactory.Config getConfig() {
        return this._factoryConfig;
    }

    protected boolean isIgnorableType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, Class cls, Map map) {
        Boolean bool = (Boolean) map.get(cls);
        if (bool == null && (bool = deserializationConfig.getAnnotationIntrospector().isIgnorableType(((BasicBeanDescription) deserializationConfig.introspectClassAnnotations(cls)).getClassInfo())) == null) {
            bool = Boolean.FALSE;
        }
        return bool.booleanValue();
    }

    protected boolean isPotentialBeanType(Class cls) {
        String canBeABeanType = ClassUtil.canBeABeanType(cls);
        if (canBeABeanType != null) {
            throw new IllegalArgumentException("Can not deserialize Class " + cls.getName() + " (of type " + canBeABeanType + ") as a Bean");
        }
        if (ClassUtil.isProxyType(cls)) {
            throw new IllegalArgumentException("Can not deserialize Proxy class " + cls.getName() + " as a Bean");
        }
        String isLocalType = ClassUtil.isLocalType(cls, true);
        if (isLocalType != null) {
            throw new IllegalArgumentException("Can not deserialize Class " + cls.getName() + " (of type " + isLocalType + ") as a Bean");
        }
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        JavaType _mapAbstractType2;
        while (true) {
            _mapAbstractType2 = _mapAbstractType2(deserializationConfig, javaType);
            if (_mapAbstractType2 == null) {
                return javaType;
            }
            Class rawClass = javaType.getRawClass();
            Class rawClass2 = _mapAbstractType2.getRawClass();
            if (rawClass == rawClass2 || !rawClass.isAssignableFrom(rawClass2)) {
                break;
            }
            javaType = _mapAbstractType2;
        }
        throw new IllegalArgumentException("Invalid abstract type resolution from " + javaType + " to " + _mapAbstractType2 + ": latter is not a subtype of former");
    }

    protected JavaType materializeAbstractType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription) {
        JavaType type = basicBeanDescription.getType();
        for (AbstractTypeResolver abstractTypeResolver : this._factoryConfig.abstractTypeResolvers()) {
            JavaType resolveAbstractType = abstractTypeResolver.resolveAbstractType(deserializationConfig, type);
            if (resolveAbstractType != null) {
                return resolveAbstractType;
            }
        }
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BasicDeserializerFactory, com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public DeserializerFactory withConfig(DeserializerFactory.Config config) {
        if (this._factoryConfig == config) {
            return this;
        }
        if (getClass() != BeanDeserializerFactory.class) {
            throw new IllegalStateException("Subtype of BeanDeserializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with additional deserializer definitions");
        }
        return new BeanDeserializerFactory(config);
    }
}
