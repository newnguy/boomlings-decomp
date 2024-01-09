package com.flurry.org.codehaus.jackson.map.util;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class Comparators {
    public static Object getArrayComparator(final Object obj) {
        final int length = Array.getLength(obj);
        return new Object() { // from class: com.flurry.org.codehaus.jackson.map.util.Comparators.1
            public boolean equals(Object obj2) {
                if (obj2 == this) {
                    return true;
                }
                if (obj2 != null && obj2.getClass() == obj.getClass() && Array.getLength(obj2) == length) {
                    for (int i = 0; i < length; i++) {
                        Object obj3 = Array.get(obj, i);
                        Object obj4 = Array.get(obj2, i);
                        if (obj3 != obj4 && obj3 != null && !obj3.equals(obj4)) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        };
    }
}
