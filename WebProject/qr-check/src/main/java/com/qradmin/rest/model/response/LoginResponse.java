package com.qradmin.rest.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    @Expose
    @JsonProperty("success")
    @SerializedName("success")
    private boolean success;
    @Expose
    @JsonProperty("time")
    @SerializedName("time")
    private int time;
}
