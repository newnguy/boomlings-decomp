package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes.dex */
public class PropertyBuilder {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BasicBeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonSerialize.Inclusion _outputProps;

    /* loaded from: classes.dex */
    public class EmptyArrayChecker {
        public boolean equals(Object obj) {
            return obj == null || Array.getLength(obj) == 0;
        }
    }

    /* loaded from: classes.dex */
    public class EmptyCollectionChecker {
        public boolean equals(Object obj) {
            return obj == null || ((Collection) obj).size() == 0;
        }
    }

    /* loaded from: classes.dex */
    public class EmptyMapChecker {
        public boolean equals(Object obj) {
            return obj == null || ((Map) obj).size() == 0;
        }
    }

    /* loaded from: classes.dex */
    public class EmptyStringChecker {
        public boolean equals(Object obj) {
            return obj == null || ((String) obj).length() == 0;
        }
    }

    public PropertyBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription) {
        this._config = serializationConfig;
        this._beanDesc = basicBeanDescription;
        this._outputProps = basicBeanDescription.findSerializationInclusion(serializationConfig.getSerializationInclusion());
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    protected Object _throwWrapped(Exception exc, String str, Object obj) {
        Throwable th = exc;
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        throw new IllegalArgumentException("Failed to get property '" + str + "' of default " + obj.getClass().getName() + " instance");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter buildWriter(java.lang.String r16, com.flurry.org.codehaus.jackson.type.JavaType r17, com.flurry.org.codehaus.jackson.map.JsonSerializer r18, com.flurry.org.codehaus.jackson.map.TypeSerializer r19, com.flurry.org.codehaus.jackson.map.TypeSerializer r20, com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember r21, boolean r22) {
        /*
            r15 = this;
            r0 = r21
            boolean r3 = r0 instanceof com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField
            if (r3 == 0) goto L60
            r11 = 0
            r3 = r21
            com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField r3 = (com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField) r3
            java.lang.reflect.Field r12 = r3.getAnnotated()
        Lf:
            r0 = r21
            r1 = r22
            r2 = r17
            com.flurry.org.codehaus.jackson.type.JavaType r3 = r15.findSerializationType(r0, r1, r2)
            if (r20 == 0) goto Le6
            if (r3 != 0) goto L1f
            r3 = r17
        L1f:
            com.flurry.org.codehaus.jackson.type.JavaType r4 = r3.getContentType()
            if (r4 != 0) goto L6a
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Problem trying to create BeanPropertyWriter for property '"
            java.lang.StringBuilder r5 = r5.append(r6)
            r0 = r16
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = "' (of type "
            java.lang.StringBuilder r5 = r5.append(r6)
            com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription r6 = r15._beanDesc
            com.flurry.org.codehaus.jackson.type.JavaType r6 = r6.getType()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = "); serialization type "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r3 = r5.append(r3)
            java.lang.String r5 = " has no content"
            java.lang.StringBuilder r3 = r3.append(r5)
            java.lang.String r3 = r3.toString()
            r4.<init>(r3)
            throw r4
        L60:
            r3 = r21
            com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod r3 = (com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod) r3
            java.lang.reflect.Method r11 = r3.getAnnotated()
            r12 = 0
            goto Lf
        L6a:
            r0 = r20
            com.flurry.org.codehaus.jackson.type.JavaType r10 = r3.withContentTypeHandler(r0)
            r10.getContentType()
        L73:
            r14 = 0
            r3 = 0
            com.flurry.org.codehaus.jackson.map.AnnotationIntrospector r4 = r15._annotationIntrospector
            com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize$Inclusion r5 = r15._outputProps
            r0 = r21
            com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize$Inclusion r4 = r4.findSerializationInclusion(r0, r5)
            if (r4 == 0) goto L8c
            int[] r5 = com.flurry.org.codehaus.jackson.map.ser.PropertyBuilder.AnonymousClass1.$SwitchMap$org$codehaus$jackson$map$annotate$JsonSerialize$Inclusion
            int r4 = r4.ordinal()
            r4 = r5[r4]
            switch(r4) {
                case 1: goto Lb7;
                case 2: goto Ld2;
                case 3: goto Ld9;
                case 4: goto Lda;
                default: goto L8c;
            }
        L8c:
            r13 = r3
        L8d:
            com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter r3 = new com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter
            com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription r4 = r15._beanDesc
            com.flurry.org.codehaus.jackson.map.util.Annotations r5 = r4.getClassAnnotations()
            r4 = r21
            r6 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.flurry.org.codehaus.jackson.map.AnnotationIntrospector r4 = r15._annotationIntrospector
            r0 = r21
            java.lang.Boolean r4 = r4.shouldUnwrapProperty(r0)
            if (r4 == 0) goto Lb6
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto Lb6
            com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter r3 = r3.unwrappingWriter()
        Lb6:
            return r3
        Lb7:
            r0 = r16
            java.lang.Object r14 = r15.getDefaultValue(r0, r11, r12)
            if (r14 != 0) goto Lc2
            r3 = 1
            r13 = r3
            goto L8d
        Lc2:
            java.lang.Class r4 = r14.getClass()
            boolean r4 = r4.isArray()
            if (r4 == 0) goto L8c
            java.lang.Object r14 = com.flurry.org.codehaus.jackson.map.util.Comparators.getArrayComparator(r14)
            r13 = r3
            goto L8d
        Ld2:
            r3 = 1
            java.lang.Object r14 = r15.getEmptyValueChecker(r16, r17)
            r13 = r3
            goto L8d
        Ld9:
            r3 = 1
        Lda:
            boolean r4 = r17.isContainerType()
            if (r4 == 0) goto L8c
            java.lang.Object r14 = r15.getContainerValueChecker(r16, r17)
            r13 = r3
            goto L8d
        Le6:
            r10 = r3
            goto L73
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.map.ser.PropertyBuilder.buildWriter(java.lang.String, com.flurry.org.codehaus.jackson.type.JavaType, com.flurry.org.codehaus.jackson.map.JsonSerializer, com.flurry.org.codehaus.jackson.map.TypeSerializer, com.flurry.org.codehaus.jackson.map.TypeSerializer, com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember, boolean):com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter");
    }

    protected JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) {
        JavaType javaType2;
        boolean z2;
        JsonSerialize.Typing findSerializationTyping;
        boolean z3 = true;
        Class<?> findSerializationType = this._annotationIntrospector.findSerializationType(annotated);
        if (findSerializationType != null) {
            Class<?> rawClass = javaType.getRawClass();
            if (findSerializationType.isAssignableFrom(rawClass)) {
                javaType2 = javaType.widenBy(findSerializationType);
            } else if (!rawClass.isAssignableFrom(findSerializationType)) {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + findSerializationType.getName() + " not a super-type of (declared) class " + rawClass.getName());
            } else {
                javaType2 = this._config.constructSpecializedType(javaType, findSerializationType);
            }
            z = true;
        } else {
            javaType2 = javaType;
        }
        JavaType modifySecondaryTypesByAnnotation = BeanSerializerFactory.modifySecondaryTypesByAnnotation(this._config, annotated, javaType2);
        if (modifySecondaryTypesByAnnotation != javaType2) {
            javaType2 = modifySecondaryTypesByAnnotation;
            z2 = true;
        } else {
            z2 = z;
        }
        if (z2 || (findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated)) == null) {
            z3 = z2;
        } else if (findSerializationTyping != JsonSerialize.Typing.STATIC) {
            z3 = false;
        }
        if (z3) {
            return javaType2;
        }
        return null;
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    protected Object getContainerValueChecker(String str, JavaType javaType) {
        if (!this._config.isEnabled(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS)) {
            if (javaType.isArrayType()) {
                return new EmptyArrayChecker();
            }
            if (Collection.class.isAssignableFrom(javaType.getRawClass())) {
                return new EmptyCollectionChecker();
            }
        }
        return null;
    }

    protected Object getDefaultBean() {
        if (this._defaultBean == null) {
            this._defaultBean = this._beanDesc.instantiateBean(this._config.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS));
            if (this._defaultBean == null) {
                throw new IllegalArgumentException("Class " + this._beanDesc.getClassInfo().getAnnotated().getName() + " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation");
            }
        }
        return this._defaultBean;
    }

    protected Object getDefaultValue(String str, Method method, Field field) {
        Object defaultBean = getDefaultBean();
        try {
            return method != null ? method.invoke(defaultBean, new Object[0]) : field.get(defaultBean);
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }

    protected Object getEmptyValueChecker(String str, JavaType javaType) {
        Class rawClass = javaType.getRawClass();
        if (rawClass == String.class) {
            return new EmptyStringChecker();
        }
        if (javaType.isArrayType()) {
            return new EmptyArrayChecker();
        }
        if (Collection.class.isAssignableFrom(rawClass)) {
            return new EmptyCollectionChecker();
        }
        if (Map.class.isAssignableFrom(rawClass)) {
            return new EmptyMapChecker();
        }
        return null;
    }
}
