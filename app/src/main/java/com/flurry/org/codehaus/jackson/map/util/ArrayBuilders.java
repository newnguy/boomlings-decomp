package com.flurry.org.codehaus.jackson.map.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public final class ArrayBuilders {
    BooleanBuilder _booleanBuilder = null;
    ByteBuilder _byteBuilder = null;
    ShortBuilder _shortBuilder = null;
    IntBuilder _intBuilder = null;
    LongBuilder _longBuilder = null;
    FloatBuilder _floatBuilder = null;
    DoubleBuilder _doubleBuilder = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class ArrayIterator implements Iterable, Iterator {
        private final Object[] _array;
        private int _index = 0;

        public ArrayIterator(Object[] objArr) {
            this._array = objArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this._index < this._array.length;
        }

        @Override // java.lang.Iterable
        public Iterator iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this._index >= this._array.length) {
                throw new NoSuchElementException();
            }
            Object[] objArr = this._array;
            int i = this._index;
            this._index = i + 1;
            return objArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes.dex */
    public final class BooleanBuilder extends PrimitiveArrayBuilder {
        @Override // com.flurry.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final boolean[] _constructArray(int i) {
            return new boolean[i];
        }
    }

    /* loaded from: classes.dex */
    public final class ByteBuilder extends PrimitiveArrayBuilder {
        @Override // com.flurry.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final byte[] _constructArray(int i) {
            return new byte[i];
        }
    }

    /* loaded from: classes.dex */
    public final class DoubleBuilder extends PrimitiveArrayBuilder {
        @Override // com.flurry.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final double[] _constructArray(int i) {
            return new double[i];
        }
    }

    /* loaded from: classes.dex */
    public final class FloatBuilder extends PrimitiveArrayBuilder {
        @Override // com.flurry.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final float[] _constructArray(int i) {
            return new float[i];
        }
    }

    /* loaded from: classes.dex */
    public final class IntBuilder extends PrimitiveArrayBuilder {
        @Override // com.flurry.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final int[] _constructArray(int i) {
            return new int[i];
        }
    }

    /* loaded from: classes.dex */
    public final class LongBuilder extends PrimitiveArrayBuilder {
        @Override // com.flurry.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final long[] _constructArray(int i) {
            return new long[i];
        }
    }

    /* loaded from: classes.dex */
    public final class ShortBuilder extends PrimitiveArrayBuilder {
        @Override // com.flurry.org.codehaus.jackson.map.util.PrimitiveArrayBuilder
        public final short[] _constructArray(int i) {
            return new short[i];
        }
    }

    public static List addToList(List list, Object obj) {
        if (list == null) {
            list = new ArrayList();
        }
        list.add(obj);
        return list;
    }

    public static Iterable arrayAsIterable(Object[] objArr) {
        return new ArrayIterator(objArr);
    }

    public static Iterator arrayAsIterator(Object[] objArr) {
        return new ArrayIterator(objArr);
    }

    public static HashSet arrayToSet(Object[] objArr) {
        HashSet hashSet = new HashSet();
        if (objArr != null) {
            for (Object obj : objArr) {
                hashSet.add(obj);
            }
        }
        return hashSet;
    }

    public static Object[] insertInList(Object[] objArr, Object obj) {
        int length = objArr.length;
        Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), length + 1);
        if (length > 0) {
            System.arraycopy(objArr, 0, objArr2, 1, length);
        }
        objArr2[0] = obj;
        return objArr2;
    }

    public static Object[] insertInListNoDup(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (objArr[i] == obj) {
                if (i == 0) {
                    return objArr;
                }
                Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), length);
                System.arraycopy(objArr, 0, objArr2, 1, i);
                objArr[0] = obj;
                return objArr2;
            }
        }
        Object[] objArr3 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), length + 1);
        if (length > 0) {
            System.arraycopy(objArr, 0, objArr3, 1, length);
        }
        objArr3[0] = obj;
        return objArr3;
    }

    public BooleanBuilder getBooleanBuilder() {
        if (this._booleanBuilder == null) {
            this._booleanBuilder = new BooleanBuilder();
        }
        return this._booleanBuilder;
    }

    public ByteBuilder getByteBuilder() {
        if (this._byteBuilder == null) {
            this._byteBuilder = new ByteBuilder();
        }
        return this._byteBuilder;
    }

    public DoubleBuilder getDoubleBuilder() {
        if (this._doubleBuilder == null) {
            this._doubleBuilder = new DoubleBuilder();
        }
        return this._doubleBuilder;
    }

    public FloatBuilder getFloatBuilder() {
        if (this._floatBuilder == null) {
            this._floatBuilder = new FloatBuilder();
        }
        return this._floatBuilder;
    }

    public IntBuilder getIntBuilder() {
        if (this._intBuilder == null) {
            this._intBuilder = new IntBuilder();
        }
        return this._intBuilder;
    }

    public LongBuilder getLongBuilder() {
        if (this._longBuilder == null) {
            this._longBuilder = new LongBuilder();
        }
        return this._longBuilder;
    }

    public ShortBuilder getShortBuilder() {
        if (this._shortBuilder == null) {
            this._shortBuilder = new ShortBuilder();
        }
        return this._shortBuilder;
    }
}
