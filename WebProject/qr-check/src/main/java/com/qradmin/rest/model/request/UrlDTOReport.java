package com.qradmin.rest.model.request;

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
