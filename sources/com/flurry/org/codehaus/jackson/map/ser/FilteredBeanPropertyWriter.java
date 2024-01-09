package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

/* loaded from: classes.dex */
public abstract class FilteredBeanPropertyWriter {

    /* loaded from: classes.dex */
    final class MultiView extends BeanPropertyWriter {
        protected final BeanPropertyWriter _delegate;
        protected final Class[] _views;

        protected MultiView(BeanPropertyWriter beanPropertyWriter, Class[] clsArr) {
            super(beanPropertyWriter);
            this._delegate = beanPropertyWriter;
            this._views = clsArr;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            Class<?> serializationView = serializerProvider.getSerializationView();
            if (serializationView != null) {
                int i = 0;
                int length = this._views.length;
                while (i < length && !this._views[i].isAssignableFrom(serializationView)) {
                    i++;
                }
                if (i == length) {
                    return;
                }
            }
            this._delegate.serializeAsField(obj, jsonGenerator, serializerProvider);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter
        public BeanPropertyWriter withSerializer(JsonSerializer jsonSerializer) {
            return new MultiView(this._delegate.withSerializer(jsonSerializer), this._views);
        }
    }

    /* loaded from: classes.dex */
    final class SingleView extends BeanPropertyWriter {
        protected final BeanPropertyWriter _delegate;
        protected final Class _view;

        protected SingleView(BeanPropertyWriter beanPropertyWriter, Class cls) {
            super(beanPropertyWriter);
            this._delegate = beanPropertyWriter;
            this._view = cls;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            Class<?> serializationView = serializerProvider.getSerializationView();
            if (serializationView == null || this._view.isAssignableFrom(serializationView)) {
                this._delegate.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter
        public BeanPropertyWriter withSerializer(JsonSerializer jsonSerializer) {
            return new SingleView(this._delegate.withSerializer(jsonSerializer), this._view);
        }
    }

    public static BeanPropertyWriter constructViewBased(BeanPropertyWriter beanPropertyWriter, Class[] clsArr) {
        return clsArr.length == 1 ? new SingleView(beanPropertyWriter, clsArr[0]) : new MultiView(beanPropertyWriter, clsArr);
    }
}
