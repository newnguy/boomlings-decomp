package com.flurry.android;

import com.flurry.org.apache.avro.Protocol;

public interface SdkAdLogResponse {
  public static final Protocol PROTOCOL = Protocol.parse("{\"protocol\":\"SdkAdLogResponse\",\"namespace\":\"com.flurry.android\",\"types\":[{\"type\":\"record\",\"name\":\"SdkLogResponse\",\"fields\":[{\"name\":\"result\",\"type\":\"string\"},{\"name\":\"errors\",\"type\":{\"type\":\"array\",\"items\":\"string\"}}]}],\"messages\":{}}");
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\android\SdkAdLogResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */