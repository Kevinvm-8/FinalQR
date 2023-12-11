package com.qradmin.db;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "UrlList")
@Data
public class UrlList {
    @Id
    @Expose
    private String id;
    @Expose
    private String ip;
    @Expose
    private String Url;
    @Expose
    private boolean isBlackList;




}
