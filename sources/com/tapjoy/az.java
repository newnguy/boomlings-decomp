package com.tapjoy;

import android.content.DialogInterface;
import android.widget.VideoView;

/* loaded from: classes.dex */
class az implements DialogInterface.OnCancelListener {
    final /* synthetic */ TapjoyVideoView a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public az(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        VideoView videoView;
        int i;
        VideoView videoView2;
        aj.a("VIDEO", "dialog onCancel");
        dialogInterface.dismiss();
        videoView = this.a.h;
        i = this.a.w;
        videoView.seekTo(i);
        videoView2 = this.a.h;
        videoView2.start();
        this.a.r = false;
    }
}
