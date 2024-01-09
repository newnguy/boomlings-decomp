package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition;

/* loaded from: classes.dex */
public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable {
    protected Node _ctorParameters;
    protected Node _fields;
    protected Node _getters;
    protected final String _internalName;
    protected final String _name;
    protected Node _setters;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class Node {
        public final String explicitName;
        public final boolean isMarkedIgnored;
        public final boolean isVisible;
        public final Node next;
        public final Object value;

        public Node(Object obj, Node node, String str, boolean z, boolean z2) {
            this.value = obj;
            this.next = node;
            if (str == null) {
                this.explicitName = null;
            } else {
                this.explicitName = str.length() == 0 ? null : str;
            }
            this.isVisible = z;
            this.isMarkedIgnored = z2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node append(Node node) {
            return this.next == null ? withNext(node) : withNext(this.next.append(node));
        }

        public String toString() {
            String str = this.value.toString() + "[visible=" + this.isVisible + "]";
            return this.next != null ? str + ", " + this.next.toString() : str;
        }

        public Node trimByVisibility() {
            if (this.next == null) {
                return this;
            }
            Node trimByVisibility = this.next.trimByVisibility();
            return this.explicitName != null ? trimByVisibility.explicitName == null ? withNext(null) : withNext(trimByVisibility) : trimByVisibility.explicitName == null ? this.isVisible == trimByVisibility.isVisible ? withNext(trimByVisibility) : this.isVisible ? withNext(null) : trimByVisibility : trimByVisibility;
        }

        public Node withNext(Node node) {
            return node == this.next ? this : new Node(this.value, node, this.explicitName, this.isVisible, this.isMarkedIgnored);
        }

        public Node withValue(Object obj) {
            return obj == this.value ? this : new Node(obj, this.next, this.explicitName, this.isVisible, this.isMarkedIgnored);
        }

        public Node withoutIgnored() {
            Node withoutIgnored;
            if (!this.isMarkedIgnored) {
                return (this.next == null || (withoutIgnored = this.next.withoutIgnored()) == this.next) ? this : withNext(withoutIgnored);
            } else if (this.next == null) {
                return null;
            } else {
                return this.next.withoutIgnored();
            }
        }

        public Node withoutNonVisible() {
            Node withoutNonVisible = this.next == null ? null : this.next.withoutNonVisible();
            return this.isVisible ? withNext(withoutNonVisible) : withoutNonVisible;
        }
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, String str) {
        this._internalName = pOJOPropertyBuilder._internalName;
        this._name = str;
        this._fields = pOJOPropertyBuilder._fields;
        this._ctorParameters = pOJOPropertyBuilder._ctorParameters;
        this._getters = pOJOPropertyBuilder._getters;
        this._setters = pOJOPropertyBuilder._setters;
    }

    public POJOPropertyBuilder(String str) {
        this._internalName = str;
        this._name = str;
    }

    private boolean _anyExplicitNames(Node node) {
        while (node != null) {
            if (node.explicitName != null && node.explicitName.length() > 0) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private boolean _anyIgnorals(Node node) {
        while (node != null) {
            if (node.isMarkedIgnored) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private boolean _anyVisible(Node node) {
        while (node != null) {
            if (node.isVisible) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private AnnotationMap _mergeAnnotations(int i, Node... nodeArr) {
        AnnotationMap allAnnotations = ((AnnotatedMember) nodeArr[i].value).getAllAnnotations();
        for (int i2 = i + 1; i2 < nodeArr.length; i2++) {
            if (nodeArr[i2] != null) {
                return AnnotationMap.merge(allAnnotations, _mergeAnnotations(i2, nodeArr));
            }
        }
        return allAnnotations;
    }

    private Node _removeIgnored(Node node) {
        return node == null ? node : node.withoutIgnored();
    }

    private Node _removeNonVisible(Node node) {
        return node == null ? node : node.withoutNonVisible();
    }

    private Node _trimByVisibility(Node node) {
        return node == null ? node : node.trimByVisibility();
    }

    private Node findRenamed(Node node, Node node2) {
        Node node3 = node2;
        for (Node node4 = node; node4 != null; node4 = node4.next) {
            String str = node4.explicitName;
            if (str != null && !str.equals(this._name)) {
                if (node3 == null) {
                    node3 = node4;
                } else if (!str.equals(node3.explicitName)) {
                    throw new IllegalStateException("Conflicting property name definitions: '" + node3.explicitName + "' (for " + node3.value + ") vs '" + node4.explicitName + "' (for " + node4.value + ")");
                }
            }
        }
        return node3;
    }

    private static Node merge(Node node, Node node2) {
        return node == null ? node2 : node2 == null ? node : node.append(node2);
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        this._fields = merge(this._fields, pOJOPropertyBuilder._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder._setters);
    }

    public void addCtor(AnnotatedParameter annotatedParameter, String str, boolean z, boolean z2) {
        this._ctorParameters = new Node(annotatedParameter, this._ctorParameters, str, z, z2);
    }

    public void addField(AnnotatedField annotatedField, String str, boolean z, boolean z2) {
        this._fields = new Node(annotatedField, this._fields, str, z, z2);
    }

    public void addGetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._getters = new Node(annotatedMethod, this._getters, str, z, z2);
    }

    public void addSetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._setters = new Node(annotatedMethod, this._setters, str, z, z2);
    }

    public boolean anyDeserializeIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    public boolean anyExplicitNames() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    public boolean anySerializeIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters);
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    @Override // java.lang.Comparable
    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder.getName());
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean couldSerialize() {
        return (this._getters == null && this._fields == null) ? false : true;
    }

    public String findNewName() {
        Node findRenamed = findRenamed(this._ctorParameters, findRenamed(this._setters, findRenamed(this._getters, findRenamed(this._fields, null))));
        if (findRenamed == null) {
            return null;
        }
        return findRenamed.explicitName;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMember getAccessor() {
        AnnotatedMethod getter = getGetter();
        return getter == null ? getField() : getter;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedParameter getConstructorParameter() {
        if (this._ctorParameters == null) {
            return null;
        }
        Node node = this._ctorParameters;
        while (true) {
            Node node2 = node;
            if (((AnnotatedParameter) node2.value).getOwner() instanceof AnnotatedConstructor) {
                return (AnnotatedParameter) node2.value;
            }
            node = node2.next;
            if (node == null) {
                return (AnnotatedParameter) this._ctorParameters.value;
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedField getField() {
        if (this._fields == null) {
            return null;
        }
        Node node = this._fields.next;
        AnnotatedField annotatedField = (AnnotatedField) this._fields.value;
        while (node != null) {
            AnnotatedField annotatedField2 = (AnnotatedField) node.value;
            Class<?> declaringClass = annotatedField.getDeclaringClass();
            Class declaringClass2 = annotatedField2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (!declaringClass.isAssignableFrom(declaringClass2)) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        annotatedField2 = annotatedField;
                    }
                }
                node = node.next;
                annotatedField = annotatedField2;
            }
            throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + annotatedField.getFullName() + " vs " + annotatedField2.getFullName());
        }
        return annotatedField;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMethod getGetter() {
        if (this._getters == null) {
            return null;
        }
        Node node = this._getters.next;
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) this._getters.value;
        while (node != null) {
            AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) node.value;
            Class<?> declaringClass = annotatedMethod.getDeclaringClass();
            Class declaringClass2 = annotatedMethod2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (!declaringClass.isAssignableFrom(declaringClass2)) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        annotatedMethod2 = annotatedMethod;
                    }
                }
                node = node.next;
                annotatedMethod = annotatedMethod2;
            }
            throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + annotatedMethod.getFullName() + " vs " + annotatedMethod2.getFullName());
        }
        return annotatedMethod;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public String getInternalName() {
        return this._internalName;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMember getMutator() {
        AnnotatedParameter constructorParameter = getConstructorParameter();
        if (constructorParameter == null) {
            AnnotatedMethod setter = getSetter();
            return setter == null ? getField() : setter;
        }
        return constructorParameter;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition, com.flurry.org.codehaus.jackson.map.util.Named
    public String getName() {
        return this._name;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMethod getSetter() {
        if (this._setters == null) {
            return null;
        }
        Node node = this._setters.next;
        AnnotatedMethod annotatedMethod = (AnnotatedMethod) this._setters.value;
        while (node != null) {
            AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) node.value;
            Class<?> declaringClass = annotatedMethod.getDeclaringClass();
            Class declaringClass2 = annotatedMethod2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (!declaringClass.isAssignableFrom(declaringClass2)) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        annotatedMethod2 = annotatedMethod;
                    }
                }
                node = node.next;
                annotatedMethod = annotatedMethod2;
            }
            throw new IllegalArgumentException("Conflicting setter definitions for property \"" + getName() + "\": " + annotatedMethod.getFullName() + " vs " + annotatedMethod2.getFullName());
        }
        return annotatedMethod;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasField() {
        return this._fields != null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasGetter() {
        return this._getters != null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasSetter() {
        return this._setters != null;
    }

    public void mergeAnnotations(boolean z) {
        if (z) {
            if (this._getters != null) {
                this._getters = this._getters.withValue(((AnnotatedMethod) this._getters.value).withAnnotations(_mergeAnnotations(0, this._getters, this._fields, this._ctorParameters, this._setters)));
            } else if (this._fields != null) {
                this._fields = this._fields.withValue(((AnnotatedField) this._fields.value).withAnnotations(_mergeAnnotations(0, this._fields, this._ctorParameters, this._setters)));
            }
        } else if (this._ctorParameters != null) {
            this._ctorParameters = this._ctorParameters.withValue(((AnnotatedParameter) this._ctorParameters.value).withAnnotations(_mergeAnnotations(0, this._ctorParameters, this._setters, this._fields, this._getters)));
        } else if (this._setters != null) {
            this._setters = this._setters.withValue(((AnnotatedMethod) this._setters.value).withAnnotations(_mergeAnnotations(0, this._setters, this._fields, this._getters)));
        } else if (this._fields != null) {
            this._fields = this._fields.withValue(((AnnotatedField) this._fields.value).withAnnotations(_mergeAnnotations(0, this._fields, this._getters)));
        }
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    public void removeNonVisible() {
        this._getters = _removeNonVisible(this._getters);
        this._ctorParameters = _removeNonVisible(this._ctorParameters);
        if (this._getters == null) {
            this._fields = _removeNonVisible(this._fields);
            this._setters = _removeNonVisible(this._setters);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[Property '").append(this._name).append("'; ctors: ").append(this._ctorParameters).append(", field(s): ").append(this._fields).append(", getter(s): ").append(this._getters).append(", setter(s): ").append(this._setters);
        sb.append("]");
        return sb.toString();
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public POJOPropertyBuilder withName(String str) {
        return new POJOPropertyBuilder(this, str);
    }
}
