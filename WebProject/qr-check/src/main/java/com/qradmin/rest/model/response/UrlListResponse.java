package com.qradmin.rest.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class UrlListResponse {
    @Expose
    @JsonProperty("url_list")
    @SerializedName("url_list")
    private List<UrlList> urlList;

    private static class UrlList{
        @Expose
        @JsonProperty("id")
        @SerializedName("id")
        private String id;
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


}
