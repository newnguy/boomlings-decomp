package com.flurry.android;

import java.io.File;

/* loaded from: classes.dex */
final class ah {
    private static String a = "FlurryAgent";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.mkdirs() || parentFile.exists()) {
            return true;
        }
        bm.b(a, "Unable to create persistent dir: " + parentFile);
        return false;
    }
}
