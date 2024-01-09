package com.flurry.org.codehaus.jackson.schema;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public interface SchemaAware {
    JsonNode getSchema(SerializerProvider serializerProvider, Type type);
}
