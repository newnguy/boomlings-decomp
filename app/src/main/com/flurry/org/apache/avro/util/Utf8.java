package com.flurry.org.apache.avro.util;

import com.flurry.org.apache.avro.io.BinaryData;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class Utf8 implements CharSequence, Comparable {
    private static final byte[] EMPTY = new byte[0];
    private byte[] bytes;
    private int length;
    private String string;

    public Utf8() {
        this.bytes = EMPTY;
    }

    public Utf8(Utf8 utf8) {
        this.bytes = EMPTY;
        this.length = utf8.length;
        this.bytes = new byte[utf8.length];
        System.arraycopy(utf8.bytes, 0, this.bytes, 0, this.length);
        this.string = utf8.string;
    }

    public Utf8(String str) {
        this.bytes = EMPTY;
        this.bytes = getBytesFor(str);
        this.length = this.bytes.length;
        this.string = str;
    }

    public Utf8(byte[] bArr) {
        this.bytes = EMPTY;
        this.bytes = bArr;
        this.length = bArr.length;
    }

    public static final byte[] getBytesFor(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return toString().charAt(i);
    }

    @Override // java.lang.Comparable
    public int compareTo(Utf8 utf8) {
        return BinaryData.compareBytes(this.bytes, 0, this.length, utf8.bytes, 0, utf8.length);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Utf8) {
            Utf8 utf8 = (Utf8) obj;
            if (this.length == utf8.length) {
                byte[] bArr = utf8.bytes;
                for (int i = 0; i < this.length; i++) {
                    if (this.bytes[i] != bArr[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public int getByteLength() {
        return this.length;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public int getLength() {
        return this.length;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.length; i2++) {
            i = (i * 31) + this.bytes[i2];
        }
        return i;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return toString().length();
    }

    public Utf8 setByteLength(int i) {
        if (this.length < i) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.bytes, 0, bArr, 0, this.length);
            this.bytes = bArr;
        }
        this.length = i;
        this.string = null;
        return this;
    }

    public Utf8 setLength(int i) {
        return setByteLength(i);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return toString().subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        if (this.string == null) {
            try {
                this.string = new String(this.bytes, 0, this.length, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return this.string;
    }
}
