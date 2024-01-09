package com.flurry.org.codehaus.jackson.map.jsontype;

import com.flurry.org.apache.avro.file.DataFileConstants;

/* loaded from: classes.dex */
public final class NamedType {
    protected final Class _class;
    protected final int _hashCode;
    protected String _name;

    public NamedType(Class cls) {
        this(cls, null);
    }

    public NamedType(Class cls, String str) {
        this._class = cls;
        this._hashCode = cls.getName().hashCode();
        setName(str);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == getClass() && this._class == ((NamedType) obj)._class;
    }

    public String getName() {
        return this._name;
    }

    public Class getType() {
        return this._class;
    }

    public boolean hasName() {
        return this._name != null;
    }

    public int hashCode() {
        return this._hashCode;
    }

    public void setName(String str) {
        this._name = (str == null || str.length() == 0) ? null : null;
    }

    public String toString() {
        return "[NamedType, class " + this._class.getName() + ", name: " + (this._name == null ? DataFileConstants.NULL_CODEC : "'" + this._name + "'") + "]";
    }
}
