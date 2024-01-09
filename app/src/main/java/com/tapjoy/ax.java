package com.tapjoy;

import android.content.DialogInterface;
import android.widget.VideoView;

/* loaded from: classes.dex */
class ax implements DialogInterface.OnClickListener {
    final /* synthetic */ TapjoyVideoView a;

    public ax(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        VideoView videoView;
        int i2;
        VideoView videoView2;
        int i3;
        VideoView videoView3;
        VideoView videoView4;
        VideoView videoView5;
        VideoView videoView6;
        dialogInterface.dismiss();
        videoView = this.a.h;
        i2 = this.a.w;
        videoView.seekTo(i2);
        videoView2 = this.a.h;
        videoView2.start();
        this.a.r = false;
        StringBuilder append = new StringBuilder().append("RESUME VIDEO time: ");
        i3 = this.a.w;
        aj.a("VIDEO", append.append(i3).toString());
        StringBuilder append2 = new StringBuilder().append("currentPosition: ");
        videoView3 = this.a.h;
        aj.a("VIDEO", append2.append(videoView3.getCurrentPosition()).toString());
        StringBuilder append3 = new StringBuilder().append("duration: ");
        videoView4 = this.a.h;
        StringBuilder append4 = append3.append(videoView4.getDuration()).append(", elapsed: ");
        videoView5 = this.a.h;
        int duration = videoView5.getDuration();
        videoView6 = this.a.h;
        aj.a("VIDEO", append4.append(duration - videoView6.getCurrentPosition()).toString());
    }
}
