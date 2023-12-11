package com.example.QRcode.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlDTOReport {
    private String urlCheck;

    public UrlDTOReport(UrlDTOReport urlDTOReport) {
    }
}
