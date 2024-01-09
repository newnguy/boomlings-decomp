package com.flurry.org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonTypeInfo {

    /* loaded from: classes.dex */
    public enum As {
        PROPERTY,
        WRAPPER_OBJECT,
        WRAPPER_ARRAY,
        EXTERNAL_PROPERTY
    }

    /* loaded from: classes.dex */
    public enum Id {
        NONE(null),
        CLASS("@class"),
        MINIMAL_CLASS("@c"),
        NAME("@type"),
        CUSTOM(null);
        
        private final String _defaultPropertyName;

        Id(String str) {
            this._defaultPropertyName = str;
        }

        public String getDefaultPropertyName() {
            return this._defaultPropertyName;
        }
    }

    /* loaded from: classes.dex */
    public abstract class None {
    }

    Class defaultImpl() default None.class;

    As include() default As.PROPERTY;

    String property() default "";

    Id use();
}
