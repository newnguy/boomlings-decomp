package net.robotmedia.billing.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import net.robotmedia.billing.a.d;
import net.robotmedia.billing.c;
import net.robotmedia.billing.k;

/* loaded from: classes.dex */
public abstract class AbstractBillingActivity extends Activity implements c {
    protected b mBillingObserver;

    public net.robotmedia.billing.b checkBillingSupported() {
        return net.robotmedia.billing.a.a((Context) this);
    }

    public net.robotmedia.billing.b checkSubscriptionSupported() {
        return net.robotmedia.billing.a.b(this);
    }

    public void doRestoreCheck() {
        if (this.mBillingObserver.b()) {
            return;
        }
        net.robotmedia.billing.a.d(this);
    }

    public abstract void onBillingChecked(boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBillingObserver = new a(this, this);
        net.robotmedia.billing.a.a(this.mBillingObserver);
        net.robotmedia.billing.a.a((c) this);
        checkBillingSupported();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        net.robotmedia.billing.a.b(this.mBillingObserver);
        net.robotmedia.billing.a.a((c) null);
    }

    public abstract void onPurchaseStateChanged(String str, d dVar);

    public abstract void onRequestPurchaseResponse(String str, k kVar);

    public abstract void onSubscriptionChecked(boolean z);

    public void requestPurchase(String str) {
        net.robotmedia.billing.a.b(this, str);
    }

    public void requestSubscription(String str) {
        net.robotmedia.billing.a.c(this, str);
    }

    public void restoreTransactions() {
        net.robotmedia.billing.a.d(this);
    }
}
