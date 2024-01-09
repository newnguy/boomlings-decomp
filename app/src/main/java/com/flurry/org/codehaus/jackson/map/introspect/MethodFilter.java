package com.flurry.org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
public interface MethodFilter {
    boolean includeMethod(Method method);
}
