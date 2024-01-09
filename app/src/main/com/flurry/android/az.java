package com.flurry.android;

import android.content.Context;
import android.widget.VideoView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class az extends VideoView {
    public az(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override // android.widget.VideoView, android.widget.MediaController.MediaPlayerControl
    public final void seekTo(int i) {
        if (i < getCurrentPosition()) {
            super.seekTo(i);
        }
    }
}
