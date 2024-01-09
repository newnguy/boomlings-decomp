package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.deser.SettableAnyProperty;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;

/* loaded from: classes.dex */
public abstract class PropertyValue {
    public final PropertyValue next;
    public final Object value;

    /* loaded from: classes.dex */
    final class Any extends PropertyValue {
        final SettableAnyProperty _property;
        final String _propertyName;

        public Any(PropertyValue propertyValue, Object obj, SettableAnyProperty settableAnyProperty, String str) {
            super(propertyValue, obj);
            this._property = settableAnyProperty;
            this._propertyName = str;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.impl.PropertyValue
        public void assign(Object obj) {
            this._property.set(obj, this._propertyName, this.value);
        }
    }

    /* loaded from: classes.dex */
    final class Map extends PropertyValue {
        final Object _key;

        public Map(PropertyValue propertyValue, Object obj, Object obj2) {
            super(propertyValue, obj);
            this._key = obj2;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.impl.PropertyValue
        public void assign(Object obj) {
            ((java.util.Map) obj).put(this._key, this.value);
        }
    }

    /* loaded from: classes.dex */
    final class Regular extends PropertyValue {
        final SettableBeanProperty _property;

        public Regular(PropertyValue propertyValue, Object obj, SettableBeanProperty settableBeanProperty) {
            super(propertyValue, obj);
            this._property = settableBeanProperty;
        }

        @Override // com.flurry.org.codehaus.jackson.map.deser.impl.PropertyValue
        public void assign(Object obj) {
            this._property.set(obj, this.value);
        }
    }

    protected PropertyValue(PropertyValue propertyValue, Object obj) {
        this.next = propertyValue;
        this.value = obj;
    }

    public abstract void assign(Object obj);
}
