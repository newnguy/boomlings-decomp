package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.jsontype.NamedType;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* loaded from: classes.dex */
public class StdSubtypeResolver extends SubtypeResolver {
    protected LinkedHashSet _registeredSubtypes;

    protected void _collectAndResolve(AnnotatedClass annotatedClass, NamedType namedType, MapperConfig mapperConfig, AnnotationIntrospector annotationIntrospector, HashMap hashMap) {
        String findTypeName;
        if (!namedType.hasName() && (findTypeName = annotationIntrospector.findTypeName(annotatedClass)) != null) {
            namedType = new NamedType(namedType.getType(), findTypeName);
        }
        if (hashMap.containsKey(namedType)) {
            if (!namedType.hasName() || ((NamedType) hashMap.get(namedType)).hasName()) {
                return;
            }
            hashMap.put(namedType, namedType);
            return;
        }
        hashMap.put(namedType, namedType);
        List<NamedType> findSubtypes = annotationIntrospector.findSubtypes(annotatedClass);
        if (findSubtypes == null || findSubtypes.isEmpty()) {
            return;
        }
        for (NamedType namedType2 : findSubtypes) {
            AnnotatedClass constructWithoutSuperTypes = AnnotatedClass.constructWithoutSuperTypes(namedType2.getType(), annotationIntrospector, mapperConfig);
            _collectAndResolve(constructWithoutSuperTypes, !namedType2.hasName() ? new NamedType(namedType2.getType(), annotationIntrospector.findTypeName(constructWithoutSuperTypes)) : namedType2, mapperConfig, annotationIntrospector, hashMap);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver
    public Collection collectAndResolveSubtypes(AnnotatedClass annotatedClass, MapperConfig mapperConfig, AnnotationIntrospector annotationIntrospector) {
        HashMap hashMap = new HashMap();
        if (this._registeredSubtypes != null) {
            Class rawType = annotatedClass.getRawType();
            Iterator it = this._registeredSubtypes.iterator();
            while (it.hasNext()) {
                NamedType namedType = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), annotationIntrospector, mapperConfig), namedType, mapperConfig, annotationIntrospector, hashMap);
                }
            }
        }
        _collectAndResolve(annotatedClass, new NamedType(annotatedClass.getRawType(), null), mapperConfig, annotationIntrospector, hashMap);
        return new ArrayList(hashMap.values());
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver
    public Collection collectAndResolveSubtypes(AnnotatedMember annotatedMember, MapperConfig mapperConfig, AnnotationIntrospector annotationIntrospector) {
        HashMap hashMap = new HashMap();
        if (this._registeredSubtypes != null) {
            Class rawType = annotatedMember.getRawType();
            Iterator it = this._registeredSubtypes.iterator();
            while (it.hasNext()) {
                NamedType namedType = (NamedType) it.next();
                if (rawType.isAssignableFrom(namedType.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType.getType(), annotationIntrospector, mapperConfig), namedType, mapperConfig, annotationIntrospector, hashMap);
                }
            }
        }
        List<NamedType> findSubtypes = annotationIntrospector.findSubtypes(annotatedMember);
        if (findSubtypes != null) {
            for (NamedType namedType2 : findSubtypes) {
                _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(namedType2.getType(), annotationIntrospector, mapperConfig), namedType2, mapperConfig, annotationIntrospector, hashMap);
            }
        }
        _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(annotatedMember.getRawType(), annotationIntrospector, mapperConfig), new NamedType(annotatedMember.getRawType(), null), mapperConfig, annotationIntrospector, hashMap);
        return new ArrayList(hashMap.values());
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver
    public void registerSubtypes(NamedType... namedTypeArr) {
        if (this._registeredSubtypes == null) {
            this._registeredSubtypes = new LinkedHashSet();
        }
        for (NamedType namedType : namedTypeArr) {
            this._registeredSubtypes.add(namedType);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver
    public void registerSubtypes(Class... clsArr) {
        NamedType[] namedTypeArr = new NamedType[clsArr.length];
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            namedTypeArr[i] = new NamedType(clsArr[i]);
        }
        registerSubtypes(namedTypeArr);
    }
}
