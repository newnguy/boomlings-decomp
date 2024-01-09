package com.flurry.org.codehaus.jackson.map;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class InjectableValues {

    /* loaded from: classes.dex */
    public class Std extends InjectableValues {
        protected final Map _values;

        public Std() {
            this(new HashMap());
        }

        public Std(Map map) {
            this._values = map;
        }

        public Std addValue(Class cls, Object obj) {
            this._values.put(cls.getName(), obj);
            return this;
        }

        public Std addValue(String str, Object obj) {
            this._values.put(str, obj);
            return this;
        }

        @Override // com.flurry.org.codehaus.jackson.map.InjectableValues
        public Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2) {
            if (!(obj instanceof String)) {
                throw new IllegalArgumentException("Unrecognized inject value id type (" + (obj == null ? "[null]" : obj.getClass().getName()) + "), expecting String");
            }
            String str = (String) obj;
            Object obj3 = this._values.get(str);
            if (obj3 != null || this._values.containsKey(str)) {
                return obj3;
            }
            throw new IllegalArgumentException("No injectable id with value '" + str + "' found (for property '" + beanProperty.getName() + "')");
        }
    }

    public abstract Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2);
}
