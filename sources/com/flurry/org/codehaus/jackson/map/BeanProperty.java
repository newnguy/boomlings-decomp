package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.map.util.Named;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;

/* loaded from: classes.dex */
public interface BeanProperty extends Named {

    /* loaded from: classes.dex */
    public class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final AnnotatedMember _member;
        protected final String _name;
        protected final JavaType _type;

        public Std(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember) {
            this._name = str;
            this._type = javaType;
            this._member = annotatedMember;
            this._contextAnnotations = annotations;
        }

        @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
        public Annotation getAnnotation(Class cls) {
            return this._member.getAnnotation(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
        public Annotation getContextAnnotation(Class cls) {
            if (this._contextAnnotations == null) {
                return null;
            }
            return this._contextAnnotations.get(cls);
        }

        @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._member;
        }

        @Override // com.flurry.org.codehaus.jackson.map.BeanProperty, com.flurry.org.codehaus.jackson.map.util.Named
        public String getName() {
            return this._name;
        }

        @Override // com.flurry.org.codehaus.jackson.map.BeanProperty
        public JavaType getType() {
            return this._type;
        }

        public Std withType(JavaType javaType) {
            return new Std(this._name, javaType, this._contextAnnotations, this._member);
        }
    }

    Annotation getAnnotation(Class cls);

    Annotation getContextAnnotation(Class cls);

    AnnotatedMember getMember();

    @Override // com.flurry.org.codehaus.jackson.map.util.Named
    String getName();

    JavaType getType();
}
