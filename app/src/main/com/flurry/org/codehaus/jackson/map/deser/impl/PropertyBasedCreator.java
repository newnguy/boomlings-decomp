package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.util.Collection;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class PropertyBasedCreator {
    protected Object[] _defaultValues;
    protected final HashMap _properties = new HashMap();
    protected final SettableBeanProperty[] _propertiesWithInjectables;
    protected final ValueInstantiator _valueInstantiator;

    public PropertyBasedCreator(ValueInstantiator valueInstantiator) {
        Object[] objArr = null;
        this._valueInstantiator = valueInstantiator;
        SettableBeanProperty[] fromObjectArguments = valueInstantiator.getFromObjectArguments();
        int length = fromObjectArguments.length;
        SettableBeanProperty[] settableBeanPropertyArr = null;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = fromObjectArguments[i];
            this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
            if (settableBeanProperty.getType().isPrimitive()) {
                objArr = objArr == null ? new Object[length] : objArr;
                objArr[i] = ClassUtil.defaultValue(settableBeanProperty.getType().getRawClass());
            }
            if (settableBeanProperty.getInjectableValueId() != null) {
                settableBeanPropertyArr = settableBeanPropertyArr == null ? new SettableBeanProperty[length] : settableBeanPropertyArr;
                settableBeanPropertyArr[i] = settableBeanProperty;
            }
        }
        this._defaultValues = objArr;
        this._propertiesWithInjectables = settableBeanPropertyArr;
    }

    public void assignDeserializer(SettableBeanProperty settableBeanProperty, JsonDeserializer jsonDeserializer) {
        SettableBeanProperty withValueDeserializer = settableBeanProperty.withValueDeserializer(jsonDeserializer);
        this._properties.put(withValueDeserializer.getName(), withValueDeserializer);
        Object nullValue = jsonDeserializer.getNullValue();
        if (nullValue != null) {
            if (this._defaultValues == null) {
                this._defaultValues = new Object[this._properties.size()];
            }
            this._defaultValues[withValueDeserializer.getPropertyIndex()] = nullValue;
        }
    }

    public Object build(PropertyValueBuffer propertyValueBuffer) {
        Object createFromObjectWith = this._valueInstantiator.createFromObjectWith(propertyValueBuffer.getParameters(this._defaultValues));
        for (PropertyValue buffered = propertyValueBuffer.buffered(); buffered != null; buffered = buffered.next) {
            buffered.assign(createFromObjectWith);
        }
        return createFromObjectWith;
    }

    public SettableBeanProperty findCreatorProperty(String str) {
        return (SettableBeanProperty) this._properties.get(str);
    }

    public Collection getCreatorProperties() {
        return this._properties.values();
    }

    public PropertyValueBuffer startBuilding(JsonParser jsonParser, DeserializationContext deserializationContext) {
        PropertyValueBuffer propertyValueBuffer = new PropertyValueBuffer(jsonParser, deserializationContext, this._properties.size());
        if (this._propertiesWithInjectables != null) {
            propertyValueBuffer.inject(this._propertiesWithInjectables);
        }
        return propertyValueBuffer;
    }
}
