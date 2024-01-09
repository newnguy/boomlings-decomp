package com.flurry.org.apache.avro.io;

import com.flurry.android.Constants;
import com.flurry.org.apache.avro.AvroRuntimeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.generic.GenericDatumReader;
import com.flurry.org.apache.avro.io.BinaryDecoder;
import java.io.IOException;

/* loaded from: classes.dex */
public class BinaryData {
    private static final ThreadLocal DECODERS = new ThreadLocal() { // from class: com.flurry.org.apache.avro.io.BinaryData.1
        @Override // java.lang.ThreadLocal
        public Decoders initialValue() {
            return new Decoders();
        }
    };
    private static final ThreadLocal HASH_DATA = new ThreadLocal() { // from class: com.flurry.org.apache.avro.io.BinaryData.2
        @Override // java.lang.ThreadLocal
        public HashData initialValue() {
            return new HashData();
        }
    };

    /* loaded from: classes.dex */
    public class Decoders {
        private final BinaryDecoder d1 = new BinaryDecoder(new byte[0], 0, 0);
        private final BinaryDecoder d2 = new BinaryDecoder(new byte[0], 0, 0);
        private final BinaryDecoder.BufferAccessor b1 = this.d1.getBufferAccessor();
        private final BinaryDecoder.BufferAccessor b2 = this.d2.getBufferAccessor();

        public void set(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
            this.d1.configure(bArr, i, i2);
            this.d2.configure(bArr2, i3, i4);
        }
    }

    /* loaded from: classes.dex */
    public class HashData {
        private final BinaryDecoder decoder = new BinaryDecoder(new byte[0], 0, 0);
        private final BinaryDecoder.BufferAccessor bytes = this.decoder.getBufferAccessor();

        public void set(byte[] bArr, int i, int i2) {
            this.decoder.configure(bArr, i, i2);
        }
    }

    private BinaryData() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:183:0x00d1, code lost:
        if (r11 != r8) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x00d3, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x00d7, code lost:
        if (r11 <= r8) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x00d9, code lost:
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x00db, code lost:
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int compare(com.flurry.org.apache.avro.io.BinaryData.Decoders r25, com.flurry.org.apache.avro.Schema r26) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.apache.avro.io.BinaryData.compare(com.flurry.org.apache.avro.io.BinaryData$Decoders, com.flurry.org.apache.avro.Schema):int");
    }

    public static int compare(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, Schema schema) {
        Decoders decoders = (Decoders) DECODERS.get();
        decoders.set(bArr, i, i2, bArr2, i3, i4);
        try {
            return compare(decoders, schema);
        } catch (IOException e) {
            throw new AvroRuntimeException(e);
        }
    }

    public static int compare(byte[] bArr, int i, byte[] bArr2, int i2, Schema schema) {
        return compare(bArr, i, bArr.length - i, bArr2, i2, bArr2.length - i2, schema);
    }

    public static int compareBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5 = i + i2;
        int i6 = i3 + i4;
        while (i < i5 && i3 < i6) {
            int i7 = bArr[i] & Constants.UNKNOWN;
            int i8 = bArr2[i3] & Constants.UNKNOWN;
            if (i7 != i8) {
                return i7 - i8;
            }
            i++;
            i3++;
        }
        return i2 - i4;
    }

    public static int encodeBoolean(boolean z, byte[] bArr, int i) {
        bArr[i] = z ? (byte) 1 : (byte) 0;
        return 1;
    }

    public static int encodeDouble(double d, byte[] bArr, int i) {
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        int i2 = (int) (doubleToRawLongBits & (-1));
        int i3 = (int) ((doubleToRawLongBits >>> 32) & (-1));
        bArr[i] = (byte) (i2 & 255);
        bArr[i + 4] = (byte) (i3 & 255);
        bArr[i + 5] = (byte) ((i3 >>> 8) & 255);
        bArr[i + 1] = (byte) ((i2 >>> 8) & 255);
        bArr[i + 2] = (byte) ((i2 >>> 16) & 255);
        bArr[i + 6] = (byte) ((i3 >>> 16) & 255);
        bArr[i + 7] = (byte) ((i3 >>> 24) & 255);
        bArr[i + 3] = (byte) ((i2 >>> 24) & 255);
        return 8;
    }

    public static int encodeFloat(float f, byte[] bArr, int i) {
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        bArr[i] = (byte) (floatToRawIntBits & 255);
        bArr[1 + i] = (byte) ((floatToRawIntBits >>> 8) & 255);
        bArr[i + 2] = (byte) ((floatToRawIntBits >>> 16) & 255);
        bArr[i + 3] = (byte) ((floatToRawIntBits >>> 24) & 255);
        return 4;
    }

    public static int encodeInt(int i, byte[] bArr, int i2) {
        int i3;
        int i4;
        int i5 = (i << 1) ^ (i >> 31);
        if ((i5 & (-128)) != 0) {
            int i6 = i2 + 1;
            bArr[i2] = (byte) ((i5 | 128) & 255);
            int i7 = i5 >>> 7;
            if (i7 > 127) {
                i4 = i6 + 1;
                bArr[i6] = (byte) ((i7 | 128) & 255);
                int i8 = i7 >>> 7;
                if (i8 > 127) {
                    i6 = i4 + 1;
                    bArr[i4] = (byte) ((i8 | 128) & 255);
                    i7 = i8 >>> 7;
                    if (i7 > 127) {
                        i4 = i6 + 1;
                        bArr[i6] = (byte) ((i7 | 128) & 255);
                        i3 = i7 >>> 7;
                    }
                } else {
                    i3 = i8;
                }
            }
            i4 = i6;
            i3 = i7;
        } else {
            i3 = i5;
            i4 = i2;
        }
        bArr[i4] = (byte) i3;
        return (i4 + 1) - i2;
    }

    public static int encodeLong(long j, byte[] bArr, int i) {
        int i2;
        long j2 = (j << 1) ^ (j >> 63);
        if (((-128) & j2) != 0) {
            i2 = i + 1;
            bArr[i] = (byte) ((128 | j2) & 255);
            long j3 = j2 >>> 7;
            if (j3 > 127) {
                int i3 = i2 + 1;
                bArr[i2] = (byte) ((128 | j3) & 255);
                long j4 = j3 >>> 7;
                if (j4 > 127) {
                    i2 = i3 + 1;
                    bArr[i3] = (byte) ((128 | j4) & 255);
                    j3 = j4 >>> 7;
                    if (j3 > 127) {
                        i3 = i2 + 1;
                        bArr[i2] = (byte) ((128 | j3) & 255);
                        j4 = j3 >>> 7;
                        if (j4 > 127) {
                            i2 = i3 + 1;
                            bArr[i3] = (byte) ((128 | j4) & 255);
                            j3 = j4 >>> 7;
                            if (j3 > 127) {
                                i3 = i2 + 1;
                                bArr[i2] = (byte) ((128 | j3) & 255);
                                j4 = j3 >>> 7;
                                if (j4 > 127) {
                                    i2 = i3 + 1;
                                    bArr[i3] = (byte) ((128 | j4) & 255);
                                    j3 = j4 >>> 7;
                                    if (j3 > 127) {
                                        i3 = i2 + 1;
                                        bArr[i2] = (byte) ((128 | j3) & 255);
                                        j4 = j3 >>> 7;
                                        if (j4 > 127) {
                                            i2 = i3 + 1;
                                            bArr[i3] = (byte) ((128 | j4) & 255);
                                            j2 = j4 >>> 7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                i2 = i3;
                j2 = j4;
            }
            j2 = j3;
        } else {
            i2 = i;
        }
        bArr[i2] = (byte) j2;
        return (i2 + 1) - i;
    }

    private static int hashBytes(int i, HashData hashData, int i2, boolean z) {
        int i3;
        byte[] buf = hashData.bytes.getBuf();
        int pos = hashData.bytes.getPos();
        int i4 = pos + i2;
        if (z) {
            int i5 = i4 - 1;
            i3 = i;
            while (i5 >= pos) {
                int i6 = (i3 * 31) + buf[i5];
                i5--;
                i3 = i6;
            }
        } else {
            i3 = i;
            while (pos < i4) {
                int i7 = (i3 * 31) + buf[pos];
                pos++;
                i3 = i7;
            }
        }
        hashData.decoder.skipFixed(i2);
        return i3;
    }

    private static int hashCode(HashData hashData, Schema schema) {
        BinaryDecoder binaryDecoder = hashData.decoder;
        switch (schema.getType()) {
            case RECORD:
                int i = 1;
                for (Schema.Field field : schema.getFields()) {
                    if (field.order() == Schema.Field.Order.IGNORE) {
                        GenericDatumReader.skip(field.schema(), binaryDecoder);
                    } else {
                        i = hashCode(hashData, field.schema()) + (i * 31);
                    }
                }
                return i;
            case ENUM:
            case INT:
                return binaryDecoder.readInt();
            case LONG:
                long readLong = binaryDecoder.readLong();
                return (int) (readLong ^ (readLong >>> 32));
            case ARRAY:
                Schema elementType = schema.getElementType();
                int i2 = 1;
                long readArrayStart = binaryDecoder.readArrayStart();
                while (readArrayStart != 0) {
                    int i3 = i2;
                    for (long j = 0; j < readArrayStart; j++) {
                        i3 = (i3 * 31) + hashCode(hashData, elementType);
                    }
                    readArrayStart = binaryDecoder.arrayNext();
                    i2 = i3;
                }
                return i2;
            case MAP:
                throw new AvroRuntimeException("Can't hashCode maps!");
            case UNION:
                return hashCode(hashData, (Schema) schema.getTypes().get(binaryDecoder.readInt()));
            case FIXED:
                return hashBytes(1, hashData, schema.getFixedSize(), false);
            case STRING:
                return hashBytes(0, hashData, binaryDecoder.readInt(), false);
            case BYTES:
                return hashBytes(1, hashData, binaryDecoder.readInt(), true);
            case FLOAT:
                return Float.floatToIntBits(binaryDecoder.readFloat());
            case DOUBLE:
                long doubleToLongBits = Double.doubleToLongBits(binaryDecoder.readDouble());
                return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
            case BOOLEAN:
                return binaryDecoder.readBoolean() ? 1231 : 1237;
            case NULL:
                return 0;
            default:
                throw new AvroRuntimeException("Unexpected schema to hashCode!");
        }
    }

    public static int hashCode(byte[] bArr, int i, int i2, Schema schema) {
        HashData hashData = (HashData) HASH_DATA.get();
        hashData.set(bArr, i, i2);
        try {
            return hashCode(hashData, schema);
        } catch (IOException e) {
            throw new AvroRuntimeException(e);
        }
    }

    public static int skipLong(byte[] bArr, int i) {
        int i2 = i + 1;
        byte b = bArr[i];
        while ((b & 128) != 0) {
            b = bArr[i2];
            i2++;
        }
        return i2;
    }
}
