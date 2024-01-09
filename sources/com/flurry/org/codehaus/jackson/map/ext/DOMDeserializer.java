package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/* loaded from: classes.dex */
public abstract class DOMDeserializer extends FromStringDeserializer {
    static final DocumentBuilderFactory _parserFactory = DocumentBuilderFactory.newInstance();

    /* loaded from: classes.dex */
    public class DocumentDeserializer extends DOMDeserializer {
        public DocumentDeserializer() {
            super(Document.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ext.DOMDeserializer, com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Document _deserialize(String str, DeserializationContext deserializationContext) {
            return parse(str);
        }
    }

    /* loaded from: classes.dex */
    public class NodeDeserializer extends DOMDeserializer {
        public NodeDeserializer() {
            super(Node.class);
        }

        @Override // com.flurry.org.codehaus.jackson.map.ext.DOMDeserializer, com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Node _deserialize(String str, DeserializationContext deserializationContext) {
            return parse(str);
        }
    }

    static {
        _parserFactory.setNamespaceAware(true);
    }

    protected DOMDeserializer(Class cls) {
        super(cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer
    public abstract Object _deserialize(String str, DeserializationContext deserializationContext);

    protected final Document parse(String str) {
        try {
            return _parserFactory.newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse JSON String as XML: " + e.getMessage(), e);
        }
    }
}
