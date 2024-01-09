package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public class RootNameLookup {
    protected LRUMap _rootNames;

    public SerializedString findRootName(JavaType javaType, MapperConfig mapperConfig) {
        return findRootName(javaType.getRawClass(), mapperConfig);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0042, code lost:
        if (r0 != null) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized com.flurry.org.codehaus.jackson.io.SerializedString findRootName(java.lang.Class r5, com.flurry.org.codehaus.jackson.map.MapperConfig r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.flurry.org.codehaus.jackson.map.type.ClassKey r2 = new com.flurry.org.codehaus.jackson.map.type.ClassKey     // Catch: java.lang.Throwable -> L45
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L45
            com.flurry.org.codehaus.jackson.map.util.LRUMap r0 = r4._rootNames     // Catch: java.lang.Throwable -> L45
            if (r0 != 0) goto L3a
            com.flurry.org.codehaus.jackson.map.util.LRUMap r0 = new com.flurry.org.codehaus.jackson.map.util.LRUMap     // Catch: java.lang.Throwable -> L45
            r1 = 20
            r3 = 200(0xc8, float:2.8E-43)
            r0.<init>(r1, r3)     // Catch: java.lang.Throwable -> L45
            r4._rootNames = r0     // Catch: java.lang.Throwable -> L45
        L15:
            com.flurry.org.codehaus.jackson.map.BeanDescription r0 = r6.introspectClassAnnotations(r5)     // Catch: java.lang.Throwable -> L45
            com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription r0 = (com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription) r0     // Catch: java.lang.Throwable -> L45
            com.flurry.org.codehaus.jackson.map.AnnotationIntrospector r1 = r6.getAnnotationIntrospector()     // Catch: java.lang.Throwable -> L45
            com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass r0 = r0.getClassInfo()     // Catch: java.lang.Throwable -> L45
            java.lang.String r0 = r1.findRootName(r0)     // Catch: java.lang.Throwable -> L45
            if (r0 != 0) goto L48
            java.lang.String r0 = r5.getSimpleName()     // Catch: java.lang.Throwable -> L45
            r1 = r0
        L2e:
            com.flurry.org.codehaus.jackson.io.SerializedString r0 = new com.flurry.org.codehaus.jackson.io.SerializedString     // Catch: java.lang.Throwable -> L45
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L45
            com.flurry.org.codehaus.jackson.map.util.LRUMap r1 = r4._rootNames     // Catch: java.lang.Throwable -> L45
            r1.put(r2, r0)     // Catch: java.lang.Throwable -> L45
        L38:
            monitor-exit(r4)
            return r0
        L3a:
            com.flurry.org.codehaus.jackson.map.util.LRUMap r0 = r4._rootNames     // Catch: java.lang.Throwable -> L45
            java.lang.Object r0 = r0.get(r2)     // Catch: java.lang.Throwable -> L45
            com.flurry.org.codehaus.jackson.io.SerializedString r0 = (com.flurry.org.codehaus.jackson.io.SerializedString) r0     // Catch: java.lang.Throwable -> L45
            if (r0 == 0) goto L15
            goto L38
        L45:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L48:
            r1 = r0
            goto L2e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.map.util.RootNameLookup.findRootName(java.lang.Class, com.flurry.org.codehaus.jackson.map.MapperConfig):com.flurry.org.codehaus.jackson.io.SerializedString");
    }
}
