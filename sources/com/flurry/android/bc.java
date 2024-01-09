package com.flurry.android;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bc extends an {
    private /* synthetic */ String a;
    private /* synthetic */ InstallReceiver b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bc(InstallReceiver installReceiver, String str) {
        this.b = installReceiver;
        this.a = str;
    }

    @Override // com.flurry.android.an
    public final void a() {
        File file;
        File file2;
        File file3;
        String b;
        FileOutputStream fileOutputStream = null;
        fileOutputStream = null;
        FileOutputStream fileOutputStream2 = null;
        try {
            file = this.b.b;
            File parentFile = file.getParentFile();
            String str = "dir is..." + parentFile.toString();
            if (parentFile.mkdirs() || parentFile.exists()) {
                file2 = this.b.b;
                FileOutputStream fileOutputStream3 = new FileOutputStream(file2);
                try {
                    fileOutputStream3.write(this.a.getBytes());
                    file3 = this.b.b;
                    b = InstallReceiver.b(file3);
                    for (Map.Entry entry : InstallReceiver.a(b).entrySet()) {
                        StringBuilder append = new StringBuilder().append("entry: ").append((String) entry.getKey()).append("=");
                        Object value = entry.getValue();
                        append.append(value).toString();
                        fileOutputStream2 = value;
                    }
                    ac.a(fileOutputStream3);
                    fileOutputStream = fileOutputStream2;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream3;
                    try {
                        bm.b("InstallReceiver", "", th);
                    } finally {
                        ac.a(fileOutputStream);
                    }
                }
            } else {
                bm.b("InstallReceiver", "Unable to create persistent dir: " + parentFile);
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
