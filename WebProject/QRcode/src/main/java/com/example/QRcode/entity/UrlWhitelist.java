package com.example.QRcode.entity;


import com.google.gson.annotations.Expose;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "UrlWhitelist")
@Builder

public class UrlWhitelist {
    @Id
    @Expose
    private String id;
    @Expose
    private String ip;
    @Expose
    private String urlW;
}
