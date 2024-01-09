package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class AnnotationMap implements Annotations {
    protected HashMap _annotations;

    public AnnotationMap() {
    }

    private AnnotationMap(HashMap hashMap) {
        this._annotations = hashMap;
    }

    public static AnnotationMap merge(AnnotationMap annotationMap, AnnotationMap annotationMap2) {
        if (annotationMap == null || annotationMap._annotations == null || annotationMap._annotations.isEmpty()) {
            return annotationMap2;
        }
        if (annotationMap2 == null || annotationMap2._annotations == null || annotationMap2._annotations.isEmpty()) {
            return annotationMap;
        }
        HashMap hashMap = new HashMap();
        for (Annotation annotation : annotationMap2._annotations.values()) {
            hashMap.put(annotation.annotationType(), annotation);
        }
        for (Annotation annotation2 : annotationMap._annotations.values()) {
            hashMap.put(annotation2.annotationType(), annotation2);
        }
        return new AnnotationMap(hashMap);
    }

    protected final void _add(Annotation annotation) {
        if (this._annotations == null) {
            this._annotations = new HashMap();
        }
        this._annotations.put(annotation.annotationType(), annotation);
    }

    public void add(Annotation annotation) {
        _add(annotation);
    }

    public void addIfNotPresent(Annotation annotation) {
        if (this._annotations == null || !this._annotations.containsKey(annotation.annotationType())) {
            _add(annotation);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.util.Annotations
    public Annotation get(Class cls) {
        if (this._annotations == null) {
            return null;
        }
        return (Annotation) this._annotations.get(cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.util.Annotations
    public int size() {
        if (this._annotations == null) {
            return 0;
        }
        return this._annotations.size();
    }

    public String toString() {
        return this._annotations == null ? "[null]" : this._annotations.toString();
    }
}
