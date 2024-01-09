package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyFilter;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class SimpleBeanPropertyFilter implements BeanPropertyFilter {

    /* loaded from: classes.dex */
    public class FilterExceptFilter extends SimpleBeanPropertyFilter {
        protected final Set _propertiesToInclude;

        public FilterExceptFilter(Set set) {
            this._propertiesToInclude = set;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyFilter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) {
            if (this._propertiesToInclude.contains(beanPropertyWriter.getName())) {
                beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }
    }

    /* loaded from: classes.dex */
    public class SerializeExceptFilter extends SimpleBeanPropertyFilter {
        protected final Set _propertiesToExclude;

        public SerializeExceptFilter(Set set) {
            this._propertiesToExclude = set;
        }

        @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyFilter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) {
            if (this._propertiesToExclude.contains(beanPropertyWriter.getName())) {
                return;
            }
            beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
        }
    }

    protected SimpleBeanPropertyFilter() {
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(Set set) {
        return new FilterExceptFilter(set);
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(String... strArr) {
        HashSet hashSet = new HashSet(strArr.length);
        Collections.addAll(hashSet, strArr);
        return new FilterExceptFilter(hashSet);
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(Set set) {
        return new SerializeExceptFilter(set);
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(String... strArr) {
        HashSet hashSet = new HashSet(strArr.length);
        Collections.addAll(hashSet, strArr);
        return new SerializeExceptFilter(hashSet);
    }
}
