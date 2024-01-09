package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import java.io.OutputStream;
import java.util.Arrays;

/* loaded from: classes.dex */
public class BlockingBinaryEncoder extends BufferedBinaryEncoder {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int STACK_STEP = 10;
    private BlockedValue[] blockStack;
    private byte[] buf;
    private byte[] headerBuffer;
    private int pos;
    private int stackTop;

    /* loaded from: classes.dex */
    public class BlockedValue {
        static final /* synthetic */ boolean $assertionsDisabled;
        public long itemsLeftToWrite;
        public Schema.Type type = null;
        public State state = State.ROOT;
        public int lastFullItem = 0;
        public int start = 0;
        public int items = 1;

        /* loaded from: classes.dex */
        public enum State {
            ROOT,
            REGULAR,
            OVERFLOW
        }

        static {
            $assertionsDisabled = !BlockingBinaryEncoder.class.desiredAssertionStatus() ? true : BlockingBinaryEncoder.$assertionsDisabled;
        }

        public boolean check(BlockedValue blockedValue, int i) {
            if ($assertionsDisabled || this.state != State.ROOT || this.type == null) {
                if ($assertionsDisabled || this.state == State.ROOT || this.type == Schema.Type.ARRAY || this.type == Schema.Type.MAP) {
                    if ($assertionsDisabled || this.items >= 0) {
                        if ($assertionsDisabled || this.items != 0 || this.start == i) {
                            if ($assertionsDisabled || 1 < this.items || this.start == this.lastFullItem) {
                                if ($assertionsDisabled || this.items <= 1 || this.start <= this.lastFullItem) {
                                    if ($assertionsDisabled || this.lastFullItem <= i) {
                                        switch (this.state) {
                                            case ROOT:
                                                if ($assertionsDisabled || this.start == 0) {
                                                    if ($assertionsDisabled || blockedValue == null) {
                                                        return BlockingBinaryEncoder.$assertionsDisabled;
                                                    }
                                                    throw new AssertionError();
                                                }
                                                throw new AssertionError();
                                            case REGULAR:
                                                if ($assertionsDisabled || this.start >= 0) {
                                                    if ($assertionsDisabled || blockedValue.lastFullItem <= this.start) {
                                                        if ($assertionsDisabled || 1 <= blockedValue.items) {
                                                            return BlockingBinaryEncoder.$assertionsDisabled;
                                                        }
                                                        throw new AssertionError();
                                                    }
                                                    throw new AssertionError();
                                                }
                                                throw new AssertionError();
                                            case OVERFLOW:
                                                if ($assertionsDisabled || this.start == 0) {
                                                    if ($assertionsDisabled || this.items == 1) {
                                                        if ($assertionsDisabled || blockedValue.state == State.ROOT || blockedValue.state == State.OVERFLOW) {
                                                            return BlockingBinaryEncoder.$assertionsDisabled;
                                                        }
                                                        throw new AssertionError();
                                                    }
                                                    throw new AssertionError();
                                                }
                                                throw new AssertionError();
                                            default:
                                                return BlockingBinaryEncoder.$assertionsDisabled;
                                        }
                                    }
                                    throw new AssertionError();
                                }
                                throw new AssertionError();
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
    }

    static {
        $assertionsDisabled = !BlockingBinaryEncoder.class.desiredAssertionStatus() ? true : $assertionsDisabled;
    }

    public BlockingBinaryEncoder(OutputStream outputStream, int i, int i2) {
        super(outputStream, i2);
        this.stackTop = -1;
        this.headerBuffer = new byte[12];
        this.buf = new byte[i];
        this.pos = 0;
        this.blockStack = new BlockedValue[0];
        expandStack();
        BlockedValue[] blockedValueArr = this.blockStack;
        int i3 = this.stackTop + 1;
        this.stackTop = i3;
        BlockedValue blockedValue = blockedValueArr[i3];
        blockedValue.type = null;
        blockedValue.state = BlockedValue.State.ROOT;
        blockedValue.lastFullItem = 0;
        blockedValue.start = 0;
        blockedValue.items = 1;
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    private boolean check() {
        if ($assertionsDisabled || this.buf != null) {
            if ($assertionsDisabled || this.pos >= 0) {
                if ($assertionsDisabled || this.pos <= this.buf.length) {
                    if ($assertionsDisabled || this.blockStack != null) {
                        BlockedValue blockedValue = null;
                        int i = 0;
                        while (i <= this.stackTop) {
                            BlockedValue blockedValue2 = this.blockStack[i];
                            blockedValue2.check(blockedValue, this.pos);
                            i++;
                            blockedValue = blockedValue2;
                        }
                        return true;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError(this.pos + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.buf.length);
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    private void compact() {
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
        int i = 1;
        BlockedValue blockedValue = null;
        while (i <= this.stackTop) {
            blockedValue = this.blockStack[i];
            if (blockedValue.state == BlockedValue.State.REGULAR) {
                break;
            }
            i++;
        }
        if (!$assertionsDisabled && blockedValue == null) {
            throw new AssertionError();
        }
        super.writeFixed(this.buf, 0, blockedValue.start);
        if (1 < blockedValue.items) {
            super.writeInt(-(blockedValue.items - 1));
            super.writeInt(blockedValue.lastFullItem - blockedValue.start);
            super.writeFixed(this.buf, blockedValue.start, blockedValue.lastFullItem - blockedValue.start);
            blockedValue.start = blockedValue.lastFullItem;
            blockedValue.items = 1;
        }
        super.writeInt(1);
        BlockedValue blockedValue2 = i + 1 <= this.stackTop ? this.blockStack[i + 1] : null;
        int i2 = blockedValue2 == null ? this.pos : blockedValue2.start;
        super.writeFixed(this.buf, blockedValue.lastFullItem, i2 - blockedValue.lastFullItem);
        System.arraycopy(this.buf, i2, this.buf, 0, this.pos - i2);
        while (true) {
            i++;
            if (i > this.stackTop) {
                break;
            }
            BlockedValue blockedValue3 = this.blockStack[i];
            blockedValue3.start -= i2;
            blockedValue3.lastFullItem -= i2;
        }
        this.pos -= i2;
        if (!$assertionsDisabled && blockedValue.items != 1) {
            throw new AssertionError();
        }
        blockedValue.lastFullItem = 0;
        blockedValue.start = 0;
        blockedValue.state = BlockedValue.State.OVERFLOW;
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    private void doWriteBytes(byte[] bArr, int i, int i2) {
        if (i2 < this.buf.length) {
            ensureBounds(i2);
            System.arraycopy(bArr, i, this.buf, this.pos, i2);
            this.pos += i2;
            return;
        }
        ensureBounds(this.buf.length);
        if (!$assertionsDisabled && this.blockStack[this.stackTop].state != BlockedValue.State.ROOT && this.blockStack[this.stackTop].state != BlockedValue.State.OVERFLOW) {
            throw new AssertionError();
        }
        write(bArr, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0064, code lost:
        r7.stackTop--;
        ensureBounds(1);
        r0 = r7.buf;
        r1 = r7.pos;
        r7.pos = r1 + 1;
        r0[r1] = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x007a, code lost:
        if (com.flurry.org.apache.avro.io.BlockingBinaryEncoder.$assertionsDisabled != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0080, code lost:
        if (check() != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0087, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x00c8, code lost:
        if (r7.blockStack[r7.stackTop].state != com.flurry.org.apache.avro.io.BlockingBinaryEncoder.BlockedValue.State.ROOT) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x00ca, code lost:
        flush();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x00cd, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void endBlockedValue() {
        /*
            Method dump skipped, instructions count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.apache.avro.io.BlockingBinaryEncoder.endBlockedValue():void");
    }

    private void ensureBounds(int i) {
        while (this.buf.length < this.pos + i) {
            if (this.blockStack[this.stackTop].state == BlockedValue.State.REGULAR) {
                compact();
            } else {
                super.writeFixed(this.buf, 0, this.pos);
                this.pos = 0;
            }
        }
    }

    private void expandStack() {
        int length = this.blockStack.length;
        this.blockStack = (BlockedValue[]) Arrays.copyOf(this.blockStack, this.blockStack.length + STACK_STEP);
        for (int i = length; i < this.blockStack.length; i++) {
            this.blockStack[i] = new BlockedValue();
        }
    }

    private void finishOverflow() {
        BlockedValue blockedValue = this.blockStack[this.stackTop];
        if (blockedValue.state != BlockedValue.State.OVERFLOW) {
            throw new IllegalStateException("Not an overflow block");
        }
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
        super.writeFixed(this.buf, 0, this.pos);
        this.pos = 0;
        blockedValue.state = BlockedValue.State.REGULAR;
        blockedValue.lastFullItem = 0;
        blockedValue.start = 0;
        blockedValue.items = 0;
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    private void write(byte[] bArr, int i, int i2) {
        if (this.blockStack[this.stackTop].state == BlockedValue.State.ROOT) {
            super.writeFixed(bArr, i, i2);
        } else if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        } else {
            while (this.buf.length < this.pos + i2) {
                if (this.blockStack[this.stackTop].state == BlockedValue.State.REGULAR) {
                    compact();
                } else {
                    super.writeFixed(this.buf, 0, this.pos);
                    this.pos = 0;
                    if (this.buf.length <= i2) {
                        super.writeFixed(bArr, i, i2);
                        i2 = 0;
                    }
                }
            }
            System.arraycopy(bArr, i, this.buf, this.pos, i2);
            this.pos += i2;
        }
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, com.flurry.org.apache.avro.io.BinaryEncoder
    public int bytesBuffered() {
        return this.pos + super.bytesBuffered();
    }

    public BlockingBinaryEncoder configure(OutputStream outputStream, int i, int i2) {
        super.configure(outputStream, i2);
        this.pos = 0;
        this.stackTop = 0;
        if (this.buf == null || this.buf.length != i) {
            this.buf = new byte[i];
        }
        if ($assertionsDisabled || check()) {
            return this;
        }
        throw new AssertionError();
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, java.io.Flushable
    public void flush() {
        BlockedValue blockedValue = this.blockStack[this.stackTop];
        if (blockedValue.state == BlockedValue.State.ROOT) {
            super.writeFixed(this.buf, 0, this.pos);
            this.pos = 0;
        } else {
            while (blockedValue.state != BlockedValue.State.OVERFLOW) {
                compact();
            }
        }
        super.flush();
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void setItemCount(long j) {
        BlockedValue blockedValue = this.blockStack[this.stackTop];
        if (!$assertionsDisabled && blockedValue.type != Schema.Type.ARRAY && blockedValue.type != Schema.Type.MAP) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && blockedValue.itemsLeftToWrite != 0) {
            throw new AssertionError();
        }
        blockedValue.itemsLeftToWrite = j;
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void startItem() {
        if (this.blockStack[this.stackTop].state == BlockedValue.State.OVERFLOW) {
            finishOverflow();
        }
        BlockedValue blockedValue = this.blockStack[this.stackTop];
        blockedValue.items++;
        blockedValue.lastFullItem = this.pos;
        blockedValue.itemsLeftToWrite--;
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeArrayEnd() {
        BlockedValue blockedValue = this.blockStack[this.stackTop];
        if (blockedValue.type != Schema.Type.ARRAY) {
            throw new AvroTypeException("Called writeArrayEnd outside of an array.");
        }
        if (blockedValue.itemsLeftToWrite != 0) {
            throw new AvroTypeException("Failed to write expected number of array elements.");
        }
        endBlockedValue();
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeArrayStart() {
        if (this.stackTop + 1 == this.blockStack.length) {
            expandStack();
        }
        BlockedValue[] blockedValueArr = this.blockStack;
        int i = this.stackTop + 1;
        this.stackTop = i;
        BlockedValue blockedValue = blockedValueArr[i];
        blockedValue.type = Schema.Type.ARRAY;
        blockedValue.state = BlockedValue.State.REGULAR;
        int i2 = this.pos;
        blockedValue.lastFullItem = i2;
        blockedValue.start = i2;
        blockedValue.items = 0;
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeBoolean(boolean z) {
        ensureBounds(1);
        this.pos += BinaryData.encodeBoolean(z, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeDouble(double d) {
        ensureBounds(8);
        this.pos += BinaryData.encodeDouble(d, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeFixed(byte[] bArr, int i, int i2) {
        doWriteBytes(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeFloat(float f) {
        ensureBounds(4);
        this.pos += BinaryData.encodeFloat(f, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeIndex(int i) {
        ensureBounds(5);
        this.pos += BinaryData.encodeInt(i, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeInt(int i) {
        ensureBounds(5);
        this.pos += BinaryData.encodeInt(i, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeLong(long j) {
        ensureBounds(STACK_STEP);
        this.pos += BinaryData.encodeLong(j, this.buf, this.pos);
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeMapEnd() {
        BlockedValue blockedValue = this.blockStack[this.stackTop];
        if (blockedValue.type != Schema.Type.MAP) {
            throw new AvroTypeException("Called writeMapEnd outside of a map.");
        }
        if (blockedValue.itemsLeftToWrite != 0) {
            throw new AvroTypeException("Failed to read write expected number of array elements.");
        }
        endBlockedValue();
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.org.apache.avro.io.BinaryEncoder, com.flurry.org.apache.avro.io.Encoder
    public void writeMapStart() {
        if (this.stackTop + 1 == this.blockStack.length) {
            expandStack();
        }
        BlockedValue[] blockedValueArr = this.blockStack;
        int i = this.stackTop + 1;
        this.stackTop = i;
        BlockedValue blockedValue = blockedValueArr[i];
        blockedValue.type = Schema.Type.MAP;
        blockedValue.state = BlockedValue.State.REGULAR;
        int i2 = this.pos;
        blockedValue.lastFullItem = i2;
        blockedValue.start = i2;
        blockedValue.items = 0;
        if (!$assertionsDisabled && !check()) {
            throw new AssertionError();
        }
    }

    @Override // com.flurry.org.apache.avro.io.BufferedBinaryEncoder, com.flurry.org.apache.avro.io.BinaryEncoder
    protected void writeZero() {
        ensureBounds(1);
        byte[] bArr = this.buf;
        int i = this.pos;
        this.pos = i + 1;
        bArr[i] = 0;
    }
}
