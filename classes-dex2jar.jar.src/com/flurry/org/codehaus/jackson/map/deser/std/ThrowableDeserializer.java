package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.deser.BeanDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;

public class ThrowableDeserializer extends BeanDeserializer {
  protected static final String PROP_NAME_MESSAGE = "message";
  
  public ThrowableDeserializer(BeanDeserializer paramBeanDeserializer) {
    super(paramBeanDeserializer);
  }
  
  protected ThrowableDeserializer(BeanDeserializer paramBeanDeserializer, boolean paramBoolean) {
    super(paramBeanDeserializer, paramBoolean);
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    byte b = 0;
    if (this._propertyBasedCreator != null)
      return _deserializeUsingPropertyBased(paramJsonParser, paramDeserializationContext); 
    if (this._delegateDeserializer != null)
      return this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext)); 
    if (this._beanType.isAbstract())
      throw JsonMappingException.from(paramJsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)"); 
    boolean bool2 = this._valueInstantiator.canCreateFromString();
    boolean bool1 = this._valueInstantiator.canCreateUsingDefault();
    if (!bool2 && !bool1)
      throw new JsonMappingException("Can not deserialize Throwable of type " + this._beanType + " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator"); 
    int i = 0;
    Object object3 = null;
    Object object2;
    for (object2 = null;; object2 = str) {
      if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
        Object object4;
        String str = paramJsonParser.getCurrentName();
        SettableBeanProperty settableBeanProperty = this._beanProperties.find(str);
        paramJsonParser.nextToken();
        if (settableBeanProperty != null) {
          if (object2 != null) {
            settableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, object2);
            str = (String)object3;
            object3 = object2;
            object2 = str;
          } else {
            str = (String)object3;
            if (object3 == null) {
              int k = this._beanProperties.size();
              object4 = new Object[k + k];
            } 
            int j = i + 1;
            object4[i] = settableBeanProperty;
            i = j + 1;
            object4[j] = settableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
            object3 = object2;
            object2 = object4;
          } 
          continue;
        } 
        if ("message".equals(object4) && bool2) {
          object4 = this._valueInstantiator.createFromString(paramJsonParser.getText());
          object2 = object4;
          if (object3 != null) {
            for (byte b1 = 0; b1 < i; b1 += 2)
              ((SettableBeanProperty)object3[b1]).set(object4, object3[b1 + 1]); 
            object3 = object4;
            object2 = null;
            continue;
          } 
        } else {
          if (this._ignorableProps != null && this._ignorableProps.contains(object4)) {
            paramJsonParser.skipChildren();
            object4 = object2;
            object2 = object3;
            object3 = object4;
          } else if (this._anySetter != null) {
            this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, object2, (String)object4);
            object4 = object2;
            object2 = object3;
            object3 = object4;
          } else {
            handleUnknownProperty(paramJsonParser, paramDeserializationContext, object2, (String)object4);
            object4 = object2;
            object2 = object3;
            object3 = object4;
          } 
          continue;
        } 
      } else {
        break;
      } 
      Object object = object2;
      object2 = object3;
      object3 = object;
      paramJsonParser.nextToken();
      str = (String)object3;
      object3 = object2;
    } 
    Object object1 = object2;
    if (object2 == null) {
      Object object;
      if (bool2) {
        object = this._valueInstantiator.createFromString(null);
      } else {
        object = this._valueInstantiator.createUsingDefault();
      } 
      object1 = object;
      if (object3 != null) {
        byte b1 = b;
        while (true) {
          object1 = object;
          if (b1 < i) {
            ((SettableBeanProperty)object3[b1]).set(object, object3[b1 + 1]);
            b1 += 2;
            continue;
          } 
          return object1;
        } 
      } 
    } 
    return object1;
  }
  
  public JsonDeserializer unwrappingDeserializer() {
    return (JsonDeserializer)((getClass() != ThrowableDeserializer.class) ? this : new ThrowableDeserializer(this, true));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\ThrowableDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */