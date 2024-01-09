package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.Module;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class SimpleModule extends Module {
    protected final String _name;
    protected final Version _version;
    protected SimpleSerializers _serializers = null;
    protected SimpleDeserializers _deserializers = null;
    protected SimpleSerializers _keySerializers = null;
    protected SimpleKeyDeserializers _keyDeserializers = null;
    protected SimpleAbstractTypeResolver _abstractTypes = null;
    protected SimpleValueInstantiators _valueInstantiators = null;
    protected HashMap _mixins = null;

    public SimpleModule(String str, Version version) {
        this._name = str;
        this._version = version;
    }

    public SimpleModule addAbstractTypeMapping(Class cls, Class cls2) {
        if (this._abstractTypes == null) {
            this._abstractTypes = new SimpleAbstractTypeResolver();
        }
        this._abstractTypes = this._abstractTypes.addMapping(cls, cls2);
        return this;
    }

    public SimpleModule addDeserializer(Class cls, JsonDeserializer jsonDeserializer) {
        if (this._deserializers == null) {
            this._deserializers = new SimpleDeserializers();
        }
        this._deserializers.addDeserializer(cls, jsonDeserializer);
        return this;
    }

    public SimpleModule addKeyDeserializer(Class cls, KeyDeserializer keyDeserializer) {
        if (this._keyDeserializers == null) {
            this._keyDeserializers = new SimpleKeyDeserializers();
        }
        this._keyDeserializers.addDeserializer(cls, keyDeserializer);
        return this;
    }

    public SimpleModule addKeySerializer(Class cls, JsonSerializer jsonSerializer) {
        if (this._keySerializers == null) {
            this._keySerializers = new SimpleSerializers();
        }
        this._keySerializers.addSerializer(cls, jsonSerializer);
        return this;
    }

    public SimpleModule addSerializer(JsonSerializer jsonSerializer) {
        if (this._serializers == null) {
            this._serializers = new SimpleSerializers();
        }
        this._serializers.addSerializer(jsonSerializer);
        return this;
    }

    public SimpleModule addSerializer(Class cls, JsonSerializer jsonSerializer) {
        if (this._serializers == null) {
            this._serializers = new SimpleSerializers();
        }
        this._serializers.addSerializer(cls, jsonSerializer);
        return this;
    }

    public SimpleModule addValueInstantiator(Class cls, ValueInstantiator valueInstantiator) {
        if (this._valueInstantiators == null) {
            this._valueInstantiators = new SimpleValueInstantiators();
        }
        this._valueInstantiators = this._valueInstantiators.addValueInstantiator(cls, valueInstantiator);
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.map.Module
    public String getModuleName() {
        return this._name;
    }

    public void setAbstractTypes(SimpleAbstractTypeResolver simpleAbstractTypeResolver) {
        this._abstractTypes = simpleAbstractTypeResolver;
    }

    public void setDeserializers(SimpleDeserializers simpleDeserializers) {
        this._deserializers = simpleDeserializers;
    }

    public void setKeyDeserializers(SimpleKeyDeserializers simpleKeyDeserializers) {
        this._keyDeserializers = simpleKeyDeserializers;
    }

    public void setKeySerializers(SimpleSerializers simpleSerializers) {
        this._keySerializers = simpleSerializers;
    }

    public SimpleModule setMixInAnnotation(Class cls, Class cls2) {
        if (this._mixins == null) {
            this._mixins = new HashMap();
        }
        this._mixins.put(cls, cls2);
        return this;
    }

    public void setSerializers(SimpleSerializers simpleSerializers) {
        this._serializers = simpleSerializers;
    }

    public void setValueInstantiators(SimpleValueInstantiators simpleValueInstantiators) {
        this._valueInstantiators = simpleValueInstantiators;
    }

    @Override // com.flurry.org.codehaus.jackson.map.Module
    public void setupModule(Module.SetupContext setupContext) {
        if (this._serializers != null) {
            setupContext.addSerializers(this._serializers);
        }
        if (this._deserializers != null) {
            setupContext.addDeserializers(this._deserializers);
        }
        if (this._keySerializers != null) {
            setupContext.addKeySerializers(this._keySerializers);
        }
        if (this._keyDeserializers != null) {
            setupContext.addKeyDeserializers(this._keyDeserializers);
        }
        if (this._abstractTypes != null) {
            setupContext.addAbstractTypeResolver(this._abstractTypes);
        }
        if (this._valueInstantiators != null) {
            setupContext.addValueInstantiators(this._valueInstantiators);
        }
        if (this._mixins != null) {
            for (Map.Entry entry : this._mixins.entrySet()) {
                setupContext.setMixInAnnotations((Class) entry.getKey(), (Class) entry.getValue());
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.Module, com.flurry.org.codehaus.jackson.Versioned
    public Version version() {
        return this._version;
    }
}
