package org.cocos2dx.lib;

import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

/* loaded from: classes.dex */
public abstract class e implements com.b.a.d {
    @Override // com.b.a.d
    public void onFacebookError(com.b.a.m mVar, Object obj) {
        Log.e("Facebook", mVar.getMessage());
        mVar.printStackTrace();
    }

    @Override // com.b.a.d
    public void onFileNotFoundException(FileNotFoundException fileNotFoundException, Object obj) {
        Log.e("Facebook", fileNotFoundException.getMessage());
        fileNotFoundException.printStackTrace();
    }

    @Override // com.b.a.d
    public void onIOException(IOException iOException, Object obj) {
        Log.e("Facebook", iOException.getMessage());
        iOException.printStackTrace();
    }

    @Override // com.b.a.d
    public void onMalformedURLException(MalformedURLException malformedURLException, Object obj) {
        Log.e("Facebook", malformedURLException.getMessage());
        malformedURLException.printStackTrace();
    }
}
