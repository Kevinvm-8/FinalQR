package com.example.QRcode.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UrlReport")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlReport {
    @Id

    private String id;

    private String urlReport;


}
