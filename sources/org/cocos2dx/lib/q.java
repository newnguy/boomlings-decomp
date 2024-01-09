package org.cocos2dx.lib;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.http.util.ByteArrayBuffer;

/* loaded from: classes.dex */
class q implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            File file = new File(String.valueOf(this.a) + this.b);
            if (file.exists()) {
                file.delete();
            }
            URL url = new URL(this.c);
            file.createNewFile();
            Log.d("ImageManager", "download begining");
            Log.d("ImageManager", "download url:" + url);
            Log.d("ImageManager", "downloaded file name:" + this.b);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openConnection().getInputStream());
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(50);
            while (true) {
                int read = bufferedInputStream.read();
                if (read == -1) {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(byteArrayBuffer.toByteArray());
                    fileOutputStream.close();
                    JniToCpp.promoImageDownloaded();
                    return;
                }
                byteArrayBuffer.append((byte) read);
            }
        } catch (IOException e) {
            Log.d("ImageManager", "Error: " + e);
        }
    }
}
