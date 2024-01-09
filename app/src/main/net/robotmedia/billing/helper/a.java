package net.robotmedia.billing.helper;

import android.app.Activity;
import net.robotmedia.billing.a.d;
import net.robotmedia.billing.k;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class a extends b {
    final /* synthetic */ AbstractBillingActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(AbstractBillingActivity abstractBillingActivity, Activity activity) {
        super(activity);
        this.a = abstractBillingActivity;
    }

    @Override // net.robotmedia.billing.n
    public void a(String str, d dVar) {
        this.a.onPurchaseStateChanged(str, dVar);
    }

    @Override // net.robotmedia.billing.n
    public void a(String str, k kVar) {
        this.a.onRequestPurchaseResponse(str, kVar);
    }

    @Override // net.robotmedia.billing.n
    public void a(boolean z) {
        this.a.onBillingChecked(z);
    }

    @Override // net.robotmedia.billing.n
    public void b(boolean z) {
        this.a.onSubscriptionChecked(z);
    }
}
