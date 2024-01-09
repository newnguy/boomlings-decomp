package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ExternalTypeHandler {
    private final HashMap _nameToPropertyIndex;
    private final ExtTypedProperty[] _properties;
    private final TokenBuffer[] _tokens;
    private final String[] _typeIds;

    /* loaded from: classes.dex */
    public class Builder {
        private final ArrayList _properties = new ArrayList();
        private final HashMap _nameToPropertyIndex = new HashMap();

        public void addExternal(SettableBeanProperty settableBeanProperty, String str) {
            Integer valueOf = Integer.valueOf(this._properties.size());
            this._properties.add(new ExtTypedProperty(settableBeanProperty, str));
            this._nameToPropertyIndex.put(settableBeanProperty.getName(), valueOf);
            this._nameToPropertyIndex.put(str, valueOf);
        }

        public ExternalTypeHandler build() {
            return new ExternalTypeHandler((ExtTypedProperty[]) this._properties.toArray(new ExtTypedProperty[this._properties.size()]), this._nameToPropertyIndex, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class ExtTypedProperty {
        private final SettableBeanProperty _property;
        private final String _typePropertyName;

        public ExtTypedProperty(SettableBeanProperty settableBeanProperty, String str) {
            this._property = settableBeanProperty;
            this._typePropertyName = str;
        }

        public SettableBeanProperty getProperty() {
            return this._property;
        }

        public String getTypePropertyName() {
            return this._typePropertyName;
        }

        public boolean hasTypePropertyName(String str) {
            return str.equals(this._typePropertyName);
        }
    }

    protected ExternalTypeHandler(ExternalTypeHandler externalTypeHandler) {
        this._properties = externalTypeHandler._properties;
        this._nameToPropertyIndex = externalTypeHandler._nameToPropertyIndex;
        int length = this._properties.length;
        this._typeIds = new String[length];
        this._tokens = new TokenBuffer[length];
    }

    protected ExternalTypeHandler(ExtTypedProperty[] extTypedPropertyArr, HashMap hashMap, String[] strArr, TokenBuffer[] tokenBufferArr) {
        this._properties = extTypedPropertyArr;
        this._nameToPropertyIndex = hashMap;
        this._typeIds = strArr;
        this._tokens = tokenBufferArr;
    }

    protected final void _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, int i) {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.writeStartArray();
        tokenBuffer.writeString(this._typeIds[i]);
        JsonParser asParser = this._tokens[i].asParser(jsonParser);
        asParser.nextToken();
        tokenBuffer.copyCurrentStructure(asParser);
        tokenBuffer.writeEndArray();
        JsonParser asParser2 = tokenBuffer.asParser(jsonParser);
        asParser2.nextToken();
        this._properties[i].getProperty().deserializeAndSet(asParser2, deserializationContext, obj);
    }

    public Object complete(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        int length = this._properties.length;
        for (int i = 0; i < length; i++) {
            if (this._typeIds[i] == null) {
                if (this._tokens[i] != null) {
                    throw deserializationContext.mappingException("Missing external type id property '" + this._properties[i].getTypePropertyName());
                }
            } else if (this._tokens[i] == null) {
                throw deserializationContext.mappingException("Missing property '" + this._properties[i].getProperty().getName() + "' for external type id '" + this._properties[i].getTypePropertyName());
            } else {
                _deserialize(jsonParser, deserializationContext, obj, i);
            }
        }
        return obj;
    }

    public boolean handleToken(JsonParser jsonParser, DeserializationContext deserializationContext, String str, Object obj) {
        boolean z;
        boolean z2 = false;
        Integer num = (Integer) this._nameToPropertyIndex.get(str);
        if (num == null) {
            return false;
        }
        int intValue = num.intValue();
        if (this._properties[intValue].hasTypePropertyName(str)) {
            this._typeIds[intValue] = jsonParser.getText();
            jsonParser.skipChildren();
            z = (obj == null || this._tokens[intValue] == null) ? false : true;
        } else {
            TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
            tokenBuffer.copyCurrentStructure(jsonParser);
            this._tokens[intValue] = tokenBuffer;
            if (obj != null && this._typeIds[intValue] != null) {
                z2 = true;
            }
            z = z2;
        }
        if (z) {
            _deserialize(jsonParser, deserializationContext, obj, intValue);
            this._typeIds[intValue] = null;
            this._tokens[intValue] = null;
        }
        return true;
    }

    public ExternalTypeHandler start() {
        return new ExternalTypeHandler(this);
    }
}
