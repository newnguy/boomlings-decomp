package com.flurry.android;

/* loaded from: classes.dex */
final class u extends an {
    private /* synthetic */ a a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(a aVar) {
        this.a = aVar;
    }

    @Override // com.flurry.android.an
    public final void a() {
        FlurryAgent.b(this.a.b, this.a.a);
    }
}
