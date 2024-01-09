package com.flurry.org.codehaus.jackson.map.annotate;

import com.flurry.org.codehaus.jackson.annotate.JacksonAnnotation;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonSerialize {

    /* loaded from: classes.dex */
    public enum Inclusion {
        ALWAYS,
        NON_NULL,
        NON_DEFAULT,
        NON_EMPTY
    }

    /* loaded from: classes.dex */
    public enum Typing {
        DYNAMIC,
        STATIC
    }

    Class as() default NoClass.class;

    Class contentAs() default NoClass.class;

    Class contentUsing() default JsonSerializer.None.class;

    Inclusion include() default Inclusion.ALWAYS;

    Class keyAs() default NoClass.class;

    Class keyUsing() default JsonSerializer.None.class;

    Typing typing() default Typing.DYNAMIC;

    Class using() default JsonSerializer.None.class;
}
