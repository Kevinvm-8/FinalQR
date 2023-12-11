package com.example.QRcode.entity;


import com.google.gson.annotations.Expose;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "UrlBlacklist")
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlBlacklist {


    @Id
    @Expose
    private String id;
    @Expose
    private String ip;

    @Expose

    private String urlB;
}
