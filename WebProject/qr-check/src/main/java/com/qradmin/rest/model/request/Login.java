package com.qradmin.rest.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Login {
    @Expose
    @JsonProperty("username")
    @SerializedName("username")
    private String username;
    @Expose
    @JsonProperty("password")
    @SerializedName("password")
    private String password;
}

