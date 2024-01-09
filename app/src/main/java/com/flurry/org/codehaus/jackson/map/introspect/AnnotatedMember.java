package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.lang.reflect.Member;

/* loaded from: classes.dex */
public abstract class AnnotatedMember extends Annotated {
    protected final AnnotationMap _annotations;

    public AnnotatedMember(AnnotationMap annotationMap) {
        this._annotations = annotationMap;
    }

    public final void fixAccess() {
        ClassUtil.checkAndFixAccess(getMember());
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public AnnotationMap getAllAnnotations() {
        return this._annotations;
    }

    public abstract Class getDeclaringClass();

    public abstract Member getMember();

    public abstract void setValue(Object obj, Object obj2);
}
