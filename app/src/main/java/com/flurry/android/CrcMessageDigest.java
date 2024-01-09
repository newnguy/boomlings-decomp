package com.flurry.android;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.zip.CRC32;

/* loaded from: classes.dex */
public class CrcMessageDigest extends MessageDigest {
    private CRC32 a;

    public CrcMessageDigest() {
        super("CRC");
        this.a = new CRC32();
    }

    @Override // java.security.MessageDigestSpi
    protected byte[] engineDigest() {
        long value = this.a.getValue();
        return new byte[]{(byte) (((-16777216) & value) >> 24), (byte) ((16711680 & value) >> 16), (byte) ((65280 & value) >> 8), (byte) (value & 255)};
    }

    @Override // java.security.MessageDigestSpi
    protected void engineReset() {
        this.a.reset();
    }

    @Override // java.security.MessageDigestSpi
    protected void engineUpdate(byte b) {
        this.a.update(b);
    }

    @Override // java.security.MessageDigestSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) {
        this.a.update(bArr, i, i2);
    }

    public int getChecksum() {
        return ByteBuffer.wrap(engineDigest()).getInt();
    }

    public byte[] getDigest() {
        return engineDigest();
    }
}
