package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.flurry.org.codehaus.jackson.map.util.Named;

/* loaded from: classes.dex */
public abstract class BeanPropertyDefinition implements Named {
    public boolean couldDeserialize() {
        return getMutator() != null;
    }

    public boolean couldSerialize() {
        return getAccessor() != null;
    }

    public abstract AnnotatedMember getAccessor();

    public abstract AnnotatedParameter getConstructorParameter();

    public abstract AnnotatedField getField();

    public abstract AnnotatedMethod getGetter();

    public abstract String getInternalName();

    public abstract AnnotatedMember getMutator();

    @Override // com.flurry.org.codehaus.jackson.map.util.Named
    public abstract String getName();

    public abstract AnnotatedMethod getSetter();

    public abstract boolean hasConstructorParameter();

    public abstract boolean hasField();

    public abstract boolean hasGetter();

    public abstract boolean hasSetter();
}
