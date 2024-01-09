package com.flurry.org.codehaus.jackson.format;

import com.flurry.org.codehaus.jackson.JsonFactory;
import java.io.EOFException;
import java.io.InputStream;

/* loaded from: classes.dex */
public interface InputAccessor {

    /* loaded from: classes.dex */
    public class Std implements InputAccessor {
        protected final byte[] _buffer;
        protected int _bufferedAmount;
        protected final InputStream _in;
        protected int _ptr;

        public Std(InputStream inputStream, byte[] bArr) {
            this._in = inputStream;
            this._buffer = bArr;
            this._bufferedAmount = 0;
        }

        public Std(byte[] bArr) {
            this._in = null;
            this._buffer = bArr;
            this._bufferedAmount = bArr.length;
        }

        public DataFormatMatcher createMatcher(JsonFactory jsonFactory, MatchStrength matchStrength) {
            return new DataFormatMatcher(this._in, this._buffer, this._bufferedAmount, jsonFactory, matchStrength);
        }

        @Override // com.flurry.org.codehaus.jackson.format.InputAccessor
        public boolean hasMoreBytes() {
            int read;
            if (this._ptr < this._bufferedAmount) {
                return true;
            }
            int length = this._buffer.length - this._ptr;
            if (length >= 1 && (read = this._in.read(this._buffer, this._ptr, length)) > 0) {
                this._bufferedAmount += read;
                return true;
            }
            return false;
        }

        @Override // com.flurry.org.codehaus.jackson.format.InputAccessor
        public byte nextByte() {
            if (this._ptr <= (-this._bufferedAmount) || hasMoreBytes()) {
                byte[] bArr = this._buffer;
                int i = this._ptr;
                this._ptr = i + 1;
                return bArr[i];
            }
            throw new EOFException("Could not read more than " + this._ptr + " bytes (max buffer size: " + this._buffer.length + ")");
        }

        @Override // com.flurry.org.codehaus.jackson.format.InputAccessor
        public void reset() {
            this._ptr = 0;
        }
    }

    boolean hasMoreBytes();

    byte nextByte();

    void reset();
}
