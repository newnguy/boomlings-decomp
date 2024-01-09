package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.impl.h;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d extends c implements h.b {
    private static int b = 100;
    private static int c = 5;
    private g d;

    public d(Context context) {
        super(context);
        this.d = new g(context);
        addView(this.d, new LinearLayout.LayoutParams(-1, -1));
    }

    @Override // com.chartboost.sdk.impl.h.b
    public int a() {
        return com.chartboost.sdk.Libraries.d.a(b + (c * 2), getContext());
    }

    @Override // com.chartboost.sdk.impl.h.b
    public void a(JSONObject jSONObject, int i) {
        boolean isPortrait = Chartboost.sharedChartboost().orientation().isPortrait();
        JSONObject optJSONObject = jSONObject.optJSONObject("assets");
        if (optJSONObject != null) {
            JSONObject optJSONObject2 = optJSONObject.optJSONObject(isPortrait ? "portrait" : "landscape");
            if (optJSONObject2 != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                com.chartboost.sdk.Libraries.e.a().a(optJSONObject2.optString("url"), optJSONObject2.optString("checksum"), null, this.d, bundle);
            }
        }
    }
}
