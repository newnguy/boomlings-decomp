package com.flurry.android;

import com.flurry.org.apache.avro.Protocol;

/* loaded from: classes.dex */
public interface SdkAdLogResponse {
    public static final Protocol PROTOCOL = Protocol.parse("{\"protocol\":\"SdkAdLogResponse\",\"namespace\":\"com.flurry.android\",\"types\":[{\"type\":\"record\",\"name\":\"SdkLogResponse\",\"fields\":[{\"name\":\"result\",\"type\":\"string\"},{\"name\":\"errors\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}]}],\"messages\":{}}");

    /* loaded from: classes.dex */
    public interface Callback extends SdkAdLogResponse {
        public static final Protocol PROTOCOL = SdkAdLogResponse.PROTOCOL;
    }
}
