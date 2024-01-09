package com.flurry.org.codehaus.jackson.map;

/* loaded from: classes.dex */
public abstract class DeserializationProblemHandler {
    public boolean handleUnknownProperty(DeserializationContext deserializationContext, JsonDeserializer jsonDeserializer, Object obj, String str) {
        return false;
    }
}
