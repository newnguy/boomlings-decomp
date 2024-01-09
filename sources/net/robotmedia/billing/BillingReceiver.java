package net.robotmedia.billing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes.dex */
public class BillingReceiver extends BroadcastReceiver {
    private void a(Context context, Intent intent) {
        a.a(context, intent.getStringExtra("inapp_signed_data"), intent.getStringExtra("inapp_signature"));
    }

    private void b(Context context, Intent intent) {
        a.a(context, intent.getStringExtra("notification_id"));
    }

    private void c(Context context, Intent intent) {
        a.a(context, intent.getLongExtra("request_id", -1L), intent.getIntExtra("response_code", 0));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        a.a("Received " + action);
        if ("com.android.vending.billing.PURCHASE_STATE_CHANGED".equals(action)) {
            a(context, intent);
        } else if ("com.android.vending.billing.IN_APP_NOTIFY".equals(action)) {
            b(context, intent);
        } else if ("com.android.vending.billing.RESPONSE_CODE".equals(action)) {
            c(context, intent);
        } else {
            Log.w(getClass().getSimpleName(), "Unexpected action: " + action);
        }
    }
}
