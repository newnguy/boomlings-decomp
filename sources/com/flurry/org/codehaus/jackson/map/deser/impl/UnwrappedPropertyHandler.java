package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class UnwrappedPropertyHandler {
    protected final ArrayList _properties = new ArrayList();

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        this._properties.add(settableBeanProperty);
    }

    public Object processUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) {
        int size = this._properties.size();
        for (int i = 0; i < size; i++) {
            JsonParser asParser = tokenBuffer.asParser();
            asParser.nextToken();
            ((SettableBeanProperty) this._properties.get(i)).deserializeAndSet(asParser, deserializationContext, obj);
        }
        return obj;
    }
}
