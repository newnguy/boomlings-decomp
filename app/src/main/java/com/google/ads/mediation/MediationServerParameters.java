package com.google.ads.mediation;

import com.google.ads.util.b;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class MediationServerParameters {

    /* loaded from: classes.dex */
    public class MappingException extends Exception {
        public MappingException(String str) {
            super(str);
        }
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes.dex */
    public @interface Parameter {
        String a();

        boolean b() default true;
    }

    protected void a() {
    }

    public void a(Map map) {
        Field[] fields;
        String str;
        HashMap hashMap = new HashMap();
        for (Field field : getClass().getFields()) {
            Parameter parameter = (Parameter) field.getAnnotation(Parameter.class);
            if (parameter != null) {
                hashMap.put(parameter.a(), field);
            }
        }
        if (hashMap.isEmpty()) {
            b.e("No server options fields detected.  To suppress this message either add a field with the @Parameter annotation, or override the load() method");
        }
        for (Map.Entry entry : map.entrySet()) {
            Field field2 = (Field) hashMap.remove(entry.getKey());
            if (field2 != null) {
                try {
                    field2.set(this, entry.getValue());
                } catch (IllegalAccessException e) {
                    b.b("Server Option '" + ((String) entry.getKey()) + "' could not be set: Illegal Access");
                } catch (IllegalArgumentException e2) {
                    b.b("Server Option '" + ((String) entry.getKey()) + "' could not be set: Bad Type");
                }
            } else {
                b.e("Unexpected Server Option: " + ((String) entry.getKey()) + " = '" + ((String) entry.getValue()) + "'");
            }
        }
        String str2 = null;
        for (Field field3 : hashMap.values()) {
            if (((Parameter) field3.getAnnotation(Parameter.class)).b()) {
                b.b("Required Server Option missing: " + ((Parameter) field3.getAnnotation(Parameter.class)).a());
                str = (str2 == null ? "" : str2 + ", ") + ((Parameter) field3.getAnnotation(Parameter.class)).a();
            } else {
                str = str2;
            }
            str2 = str;
        }
        if (str2 != null) {
            throw new MappingException("Required Server Option(s) missing: " + str2);
        }
        a();
    }
}
