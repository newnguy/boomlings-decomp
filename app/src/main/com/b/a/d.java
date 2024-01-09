package com.b.a;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/* loaded from: classes.dex */
public interface d {
    void onComplete(String str, Object obj);

    void onFacebookError(m mVar, Object obj);

    void onFileNotFoundException(FileNotFoundException fileNotFoundException, Object obj);

    void onIOException(IOException iOException, Object obj);

    void onMalformedURLException(MalformedURLException malformedURLException, Object obj);
}
