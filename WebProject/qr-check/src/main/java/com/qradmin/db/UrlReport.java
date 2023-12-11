package com.qradmin.db;

import com.google.gson.annotations.Expose;
import com.qradmin.rest.model.request.UrlDTOReport;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UrlReport")
@Data
public class UrlReport {
    @Id
    private String id;
    private String urlReport;


}
