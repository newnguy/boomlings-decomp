package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.Versioned;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.flurry.org.codehaus.jackson.map.ser.BeanSerializerModifier;
import com.flurry.org.codehaus.jackson.map.type.TypeModifier;

/* loaded from: classes.dex */
public abstract class Module implements Versioned {

    /* loaded from: classes.dex */
    public interface SetupContext {
        void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver);

        void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier);

        void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier);

        void addDeserializers(Deserializers deserializers);

        void addKeyDeserializers(KeyDeserializers keyDeserializers);

        void addKeySerializers(Serializers serializers);

        void addSerializers(Serializers serializers);

        void addTypeModifier(TypeModifier typeModifier);

        void addValueInstantiators(ValueInstantiators valueInstantiators);

        void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

        DeserializationConfig getDeserializationConfig();

        Version getMapperVersion();

        SerializationConfig getSerializationConfig();

        void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

        boolean isEnabled(JsonGenerator.Feature feature);

        boolean isEnabled(JsonParser.Feature feature);

        boolean isEnabled(DeserializationConfig.Feature feature);

        boolean isEnabled(SerializationConfig.Feature feature);

        void setMixInAnnotations(Class cls, Class cls2);
    }

    public abstract String getModuleName();

    public abstract void setupModule(SetupContext setupContext);

    @Override // com.flurry.org.codehaus.jackson.Versioned
    public abstract Version version();
}
