package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.deser.BeanDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;

/* loaded from: classes.dex */
public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
    }

    protected ThrowableDeserializer(BeanDeserializer beanDeserializer, boolean z) {
        super(beanDeserializer, z);
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BeanDeserializer
    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) {
        int i;
        Object[] objArr;
        Object obj;
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._beanType.isAbstract()) {
            throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
        }
        boolean canCreateFromString = this._valueInstantiator.canCreateFromString();
        boolean canCreateUsingDefault = this._valueInstantiator.canCreateUsingDefault();
        if (canCreateFromString || canCreateUsingDefault) {
            int i2 = 0;
            Object[] objArr2 = null;
            Object obj2 = null;
            while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                SettableBeanProperty find = this._beanProperties.find(currentName);
                jsonParser.nextToken();
                if (find != null) {
                    if (obj2 != null) {
                        find.deserializeAndSet(jsonParser, deserializationContext, obj2);
                        i = i2;
                        objArr = objArr2;
                        obj = obj2;
                    } else {
                        if (objArr2 == null) {
                            int size = this._beanProperties.size();
                            objArr2 = new Object[size + size];
                        }
                        int i3 = i2 + 1;
                        objArr2[i2] = find;
                        i = i3 + 1;
                        objArr2[i3] = find.deserialize(jsonParser, deserializationContext);
                        objArr = objArr2;
                        obj = obj2;
                    }
                } else if (PROP_NAME_MESSAGE.equals(currentName) && canCreateFromString) {
                    obj2 = this._valueInstantiator.createFromString(jsonParser.getText());
                    if (objArr2 != null) {
                        for (int i4 = 0; i4 < i2; i4 += 2) {
                            ((SettableBeanProperty) objArr2[i4]).set(obj2, objArr2[i4 + 1]);
                        }
                        i = i2;
                        obj = obj2;
                        objArr = null;
                    }
                    i = i2;
                    objArr = objArr2;
                    obj = obj2;
                } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                    jsonParser.skipChildren();
                    i = i2;
                    objArr = objArr2;
                    obj = obj2;
                } else if (this._anySetter != null) {
                    this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj2, currentName);
                    i = i2;
                    objArr = objArr2;
                    obj = obj2;
                } else {
                    handleUnknownProperty(jsonParser, deserializationContext, obj2, currentName);
                    i = i2;
                    objArr = objArr2;
                    obj = obj2;
                }
                jsonParser.nextToken();
                obj2 = obj;
                objArr2 = objArr;
                i2 = i;
            }
            if (obj2 == null) {
                Object createFromString = canCreateFromString ? this._valueInstantiator.createFromString(null) : this._valueInstantiator.createUsingDefault();
                if (objArr2 != null) {
                    for (int i5 = 0; i5 < i2; i5 += 2) {
                        ((SettableBeanProperty) objArr2[i5]).set(createFromString, objArr2[i5 + 1]);
                    }
                    return createFromString;
                }
                return createFromString;
            }
            return obj2;
        }
        throw new JsonMappingException("Can not deserialize Throwable of type " + this._beanType + " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.BeanDeserializer, com.flurry.org.codehaus.jackson.map.JsonDeserializer
    public JsonDeserializer unwrappingDeserializer() {
        return getClass() != ThrowableDeserializer.class ? this : new ThrowableDeserializer(this, true);
    }
}
