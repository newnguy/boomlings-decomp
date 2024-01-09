package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;

/* loaded from: classes.dex */
public abstract class PropertyNamingStrategy {
    public static final PropertyNamingStrategy CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES = new LowerCaseWithUnderscoresStrategy();

    /* loaded from: classes.dex */
    public class LowerCaseWithUnderscoresStrategy extends PropertyNamingStrategyBase {
        @Override // com.flurry.org.codehaus.jackson.map.PropertyNamingStrategy.PropertyNamingStrategyBase
        public String translate(String str) {
            int i;
            char c;
            boolean z;
            if (str == null) {
                return str;
            }
            int length = str.length();
            StringBuilder sb = new StringBuilder(length * 2);
            int i2 = 0;
            boolean z2 = false;
            int i3 = 0;
            while (i2 < length) {
                char charAt = str.charAt(i2);
                if (i2 > 0 || charAt != '_') {
                    if (Character.isUpperCase(charAt)) {
                        if (!z2 && i3 > 0 && sb.charAt(i3 - 1) != '_') {
                            sb.append('_');
                            i3++;
                        }
                        char lowerCase = Character.toLowerCase(charAt);
                        z = true;
                        i = i3;
                        c = lowerCase;
                    } else {
                        i = i3;
                        c = charAt;
                        z = false;
                    }
                    sb.append(c);
                    i3 = i + 1;
                } else {
                    z = z2;
                }
                i2++;
                z2 = z;
            }
            return i3 > 0 ? sb.toString() : str;
        }
    }

    /* loaded from: classes.dex */
    public abstract class PropertyNamingStrategyBase extends PropertyNamingStrategy {
        @Override // com.flurry.org.codehaus.jackson.map.PropertyNamingStrategy
        public String nameForConstructorParameter(MapperConfig mapperConfig, AnnotatedParameter annotatedParameter, String str) {
            return translate(str);
        }

        @Override // com.flurry.org.codehaus.jackson.map.PropertyNamingStrategy
        public String nameForField(MapperConfig mapperConfig, AnnotatedField annotatedField, String str) {
            return translate(str);
        }

        @Override // com.flurry.org.codehaus.jackson.map.PropertyNamingStrategy
        public String nameForGetterMethod(MapperConfig mapperConfig, AnnotatedMethod annotatedMethod, String str) {
            return translate(str);
        }

        @Override // com.flurry.org.codehaus.jackson.map.PropertyNamingStrategy
        public String nameForSetterMethod(MapperConfig mapperConfig, AnnotatedMethod annotatedMethod, String str) {
            return translate(str);
        }

        public abstract String translate(String str);
    }

    public String nameForConstructorParameter(MapperConfig mapperConfig, AnnotatedParameter annotatedParameter, String str) {
        return str;
    }

    public String nameForField(MapperConfig mapperConfig, AnnotatedField annotatedField, String str) {
        return str;
    }

    public String nameForGetterMethod(MapperConfig mapperConfig, AnnotatedMethod annotatedMethod, String str) {
        return str;
    }

    public String nameForSetterMethod(MapperConfig mapperConfig, AnnotatedMethod annotatedMethod, String str) {
        return str;
    }
}
