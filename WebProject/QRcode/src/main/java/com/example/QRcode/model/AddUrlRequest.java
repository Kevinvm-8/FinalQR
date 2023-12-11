package com.example.QRcode.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AddUrlRequest {
    @Expose
    @JsonProperty("ip")
    @SerializedName("ip")
    private String ip;
    @Expose
    @JsonProperty("url")
    @SerializedName("url")
    private String url;
    @Expose
    @JsonProperty("isBlackList")
    @SerializedName("isBlackList")
    private boolean isBlackList;
}
