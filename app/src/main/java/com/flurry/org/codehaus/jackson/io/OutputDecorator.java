package com.flurry.org.codehaus.jackson.io;

import java.io.OutputStream;
import java.io.Writer;

/* loaded from: classes.dex */
public abstract class OutputDecorator {
    public abstract OutputStream decorate(IOContext iOContext, OutputStream outputStream);

    public abstract Writer decorate(IOContext iOContext, Writer writer);
}
