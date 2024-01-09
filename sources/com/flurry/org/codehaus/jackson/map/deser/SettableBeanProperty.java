package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.util.InternCache;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class SettableBeanProperty implements BeanProperty {
    protected final Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected NullProvider _nullProvider;
    protected final String _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected JsonDeserializer _valueDeserializer;
    protected TypeDeserializer _valueTypeDeserializer;

    /* loaded from: classes.dex */
    public final class FieldProperty extends SettableBeanProperty {
        protected final AnnotatedField _annotated;
        protected final Field _field;

        protected FieldProperty(FieldProperty fieldProperty, JsonDeserializer jsonDeserializer) {
            super(fieldProperty, jsonDeserializer);
            this._annotated = fieldProperty._annotated;
            this._field = fieldProperty._field;
        }

        public FieldProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedField annotatedField) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedField;
            this._field = annotatedField.getAnnotated();
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public Annotation getAnnotation(Class cls) {
            return this._annotated.getAnnotation(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            try {
                this._field.set(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public FieldProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return new FieldProperty(this, jsonDeserializer);
        }
    }

    /* loaded from: classes.dex */
    public final class InnerClassProperty extends SettableBeanProperty {
        protected final Constructor _creator;
        protected final SettableBeanProperty _delegate;

        protected InnerClassProperty(InnerClassProperty innerClassProperty, JsonDeserializer jsonDeserializer) {
            super(innerClassProperty, jsonDeserializer);
            this._delegate = innerClassProperty._delegate.withValueDeserializer(jsonDeserializer);
            this._creator = innerClassProperty._creator;
        }

        public InnerClassProperty(SettableBeanProperty settableBeanProperty, Constructor constructor) {
            super(settableBeanProperty);
            this._delegate = settableBeanProperty;
            this._creator = constructor;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            Object obj2 = null;
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                if (this._nullProvider != null) {
                    obj2 = this._nullProvider.nullValue(deserializationContext);
                }
            } else if (this._valueTypeDeserializer != null) {
                obj2 = this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
            } else {
                try {
                    obj2 = this._creator.newInstance(obj);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e, "Failed to instantiate class " + this._creator.getDeclaringClass().getName() + ", problem: " + e.getMessage());
                }
                this._valueDeserializer.deserialize(jsonParser, deserializationContext, obj2);
            }
            set(obj, obj2);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public Annotation getAnnotation(Class cls) {
            return this._delegate.getAnnotation(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._delegate.getMember();
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            this._delegate.set(obj, obj2);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public InnerClassProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return new InnerClassProperty(this, jsonDeserializer);
        }
    }

    /* loaded from: classes.dex */
    public final class ManagedReferenceProperty extends SettableBeanProperty {
        protected final SettableBeanProperty _backProperty;
        protected final boolean _isContainer;
        protected final SettableBeanProperty _managedProperty;
        protected final String _referenceName;

        protected ManagedReferenceProperty(ManagedReferenceProperty managedReferenceProperty, JsonDeserializer jsonDeserializer) {
            super(managedReferenceProperty, jsonDeserializer);
            this._referenceName = managedReferenceProperty._referenceName;
            this._isContainer = managedReferenceProperty._isContainer;
            this._managedProperty = managedReferenceProperty._managedProperty;
            this._backProperty = managedReferenceProperty._backProperty;
        }

        public ManagedReferenceProperty(String str, SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2, Annotations annotations, boolean z) {
            super(settableBeanProperty.getName(), settableBeanProperty.getType(), settableBeanProperty._valueTypeDeserializer, annotations);
            this._referenceName = str;
            this._managedProperty = settableBeanProperty;
            this._backProperty = settableBeanProperty2;
            this._isContainer = z;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, this._managedProperty.deserialize(jsonParser, deserializationContext));
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public Annotation getAnnotation(Class cls) {
            return this._managedProperty.getAnnotation(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._managedProperty.getMember();
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            Object[] objArr;
            this._managedProperty.set(obj, obj2);
            if (obj2 != null) {
                if (!this._isContainer) {
                    this._backProperty.set(obj2, obj);
                } else if (obj2 instanceof Object[]) {
                    for (Object obj3 : (Object[]) obj2) {
                        if (obj3 != null) {
                            this._backProperty.set(obj3, obj);
                        }
                    }
                } else if (obj2 instanceof Collection) {
                    for (Object obj4 : (Collection) obj2) {
                        if (obj4 != null) {
                            this._backProperty.set(obj4, obj);
                        }
                    }
                } else if (!(obj2 instanceof Map)) {
                    throw new IllegalStateException("Unsupported container type (" + obj2.getClass().getName() + ") when resolving reference '" + this._referenceName + "'");
                } else {
                    for (Object obj5 : ((Map) obj2).values()) {
                        if (obj5 != null) {
                            this._backProperty.set(obj5, obj);
                        }
                    }
                }
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public ManagedReferenceProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return new ManagedReferenceProperty(this, jsonDeserializer);
        }
    }

    /* loaded from: classes.dex */
    public final class MethodProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _setter;

        protected MethodProperty(MethodProperty methodProperty, JsonDeserializer jsonDeserializer) {
            super(methodProperty, jsonDeserializer);
            this._annotated = methodProperty._annotated;
            this._setter = methodProperty._setter;
        }

        public MethodProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._setter = annotatedMethod.getAnnotated();
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            set(obj, deserialize(jsonParser, deserializationContext));
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public Annotation getAnnotation(Class cls) {
            return this._annotated.getAnnotation(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            try {
                this._setter.invoke(obj, obj2);
            } catch (Exception e) {
                _throwAsIOE(e, obj2);
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public MethodProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return new MethodProperty(this, jsonDeserializer);
        }
    }

    /* loaded from: classes.dex */
    public final class NullProvider {
        private final boolean _isPrimitive;
        private final Object _nullValue;
        private final Class _rawType;

        protected NullProvider(JavaType javaType, Object obj) {
            this._nullValue = obj;
            this._isPrimitive = javaType.isPrimitive();
            this._rawType = javaType.getRawClass();
        }

        public Object nullValue(DeserializationContext deserializationContext) {
            if (this._isPrimitive && deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                throw deserializationContext.mappingException("Can not map JSON null into type " + this._rawType.getName() + " (set DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)");
            }
            return this._nullValue;
        }
    }

    /* loaded from: classes.dex */
    public final class SetterlessProperty extends SettableBeanProperty {
        protected final AnnotatedMethod _annotated;
        protected final Method _getter;

        protected SetterlessProperty(SetterlessProperty setterlessProperty, JsonDeserializer jsonDeserializer) {
            super(setterlessProperty, jsonDeserializer);
            this._annotated = setterlessProperty._annotated;
            this._getter = setterlessProperty._getter;
        }

        public SetterlessProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedMethod annotatedMethod) {
            super(str, javaType, typeDeserializer, annotations);
            this._annotated = annotatedMethod;
            this._getter = annotatedMethod.getAnnotated();
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                return;
            }
            try {
                Object invoke = this._getter.invoke(obj, new Object[0]);
                if (invoke == null) {
                    throw new JsonMappingException("Problem deserializing 'setterless' property '" + getName() + "': get method returned null");
                }
                this._valueDeserializer.deserialize(jsonParser, deserializationContext, invoke);
            } catch (Exception e) {
                _throwAsIOE(e);
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public Annotation getAnnotation(Class cls) {
            return this._annotated.getAnnotation(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._annotated;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public final void set(Object obj, Object obj2) {
            throw new UnsupportedOperationException("Should never call 'set' on setterless property");
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
        public SetterlessProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
            return new SetterlessProperty(this, jsonDeserializer);
        }
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty) {
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._nullProvider = settableBeanProperty._nullProvider;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SettableBeanProperty(SettableBeanProperty settableBeanProperty, JsonDeserializer jsonDeserializer) {
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        this._valueDeserializer = jsonDeserializer;
        if (jsonDeserializer == null) {
            this._nullProvider = null;
            return;
        }
        Object nullValue = jsonDeserializer.getNullValue();
        this._nullProvider = nullValue != null ? new NullProvider(this._type, nullValue) : null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SettableBeanProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations) {
        this._propertyIndex = -1;
        if (str == null || str.length() == 0) {
            this._propName = "";
        } else {
            this._propName = InternCache.instance.intern(str);
        }
        this._type = javaType;
        this._contextAnnotations = annotations;
        this._valueTypeDeserializer = typeDeserializer;
    }

    protected IOException _throwAsIOE(Exception exc) {
        if (exc instanceof IOException) {
            throw ((IOException) exc);
        }
        boolean z = exc instanceof RuntimeException;
        Exception exc2 = exc;
        if (z) {
            throw ((RuntimeException) exc);
        }
        while (exc2.getCause() != null) {
            exc2 = exc2.getCause();
        }
        throw new JsonMappingException(exc2.getMessage(), null, exc2);
    }

    protected void _throwAsIOE(Exception exc, Object obj) {
        if (!(exc instanceof IllegalArgumentException)) {
            _throwAsIOE(exc);
            return;
        }
        String name = obj == null ? "[NULL]" : obj.getClass().getName();
        StringBuilder append = new StringBuilder("Problem deserializing property '").append(getPropertyName());
        append.append("' (expected type: ").append(getType());
        append.append("; actual type: ").append(name).append(")");
        String message = exc.getMessage();
        if (message != null) {
            append.append(", problem: ").append(message);
        } else {
            append.append(" (no error message provided)");
        }
        throw new JsonMappingException(append.toString(), null, exc);
    }

    public void assignIndex(int i) {
        if (this._propertyIndex != -1) {
            throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + i);
        }
        this._propertyIndex = i;
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
            return this._valueTypeDeserializer != null ? this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer) : this._valueDeserializer.deserialize(jsonParser, deserializationContext);
        } else if (this._nullProvider == null) {
            return null;
        } else {
            return this._nullProvider.nullValue(deserializationContext);
        }
    }

    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj);

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
    public abstract Annotation getAnnotation(Class cls);

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
    public Annotation getContextAnnotation(Class cls) {
        return this._contextAnnotations.get(cls);
    }

    protected final Class getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    public Object getInjectableValueId() {
        return null;
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
    public abstract AnnotatedMember getMember();

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty, com.flurry.org.codehaus.jackson.map.util.Named
    public final String getName() {
        return this._propName;
    }

    public int getPropertyIndex() {
        return this._propertyIndex;
    }

    @Deprecated
    public String getPropertyName() {
        return this._propName;
    }

    @Deprecated
    public int getProperytIndex() {
        return getPropertyIndex();
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
    public JavaType getType() {
        return this._type;
    }

    public JsonDeserializer getValueDeserializer() {
        return this._valueDeserializer;
    }

    public TypeDeserializer getValueTypeDeserializer() {
        return this._valueTypeDeserializer;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public boolean hasValueTypeDeserializer() {
        return this._valueTypeDeserializer != null;
    }

    public abstract void set(Object obj, Object obj2);

    public void setManagedReferenceName(String str) {
        this._managedReferenceName = str;
    }

    @Deprecated
    public void setValueDeserializer(JsonDeserializer jsonDeserializer) {
        if (this._valueDeserializer != null) {
            throw new IllegalStateException("Already had assigned deserializer for property '" + getName() + "' (class " + getDeclaringClass().getName() + ")");
        }
        this._valueDeserializer = jsonDeserializer;
        Object nullValue = this._valueDeserializer.getNullValue();
        this._nullProvider = nullValue == null ? null : new NullProvider(this._type, nullValue);
    }

    public String toString() {
        return "[property '" + getName() + "']";
    }

    public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer jsonDeserializer);
}
