package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public abstract class StaticListSerializerBase extends SerializerBase {
    protected final BeanProperty _property;

    /* JADX INFO: Access modifiers changed from: protected */
    public StaticListSerializerBase(Class cls, BeanProperty beanProperty) {
        super(cls, false);
        this._property = beanProperty;
    }

    protected abstract JsonNode contentSchema();

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put("items", contentSchema());
        return createSchemaNode;
    }
}
