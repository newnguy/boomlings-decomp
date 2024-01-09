package com.flurry.org.codehaus.jackson.map.annotate;

import com.flurry.org.codehaus.jackson.annotate.JacksonAnnotation;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonDeserialize {
    Class as() default NoClass.class;

    Class contentAs() default NoClass.class;

    Class contentUsing() default JsonDeserializer.None.class;

    Class keyAs() default NoClass.class;

    Class keyUsing() default KeyDeserializer.None.class;

    Class using() default JsonDeserializer.None.class;
}
