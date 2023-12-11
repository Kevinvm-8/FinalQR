package com.example.QRcode.model.response;

import com.example.QRcode.entity.UrlBlacklist;
import com.example.QRcode.model.UrlDTOBlack;

public class UrlMapper {
    public static UrlDTOBlack toUrlDTOBlack(UrlBlacklist urlBlacklist){
        return UrlDTOBlack.builder().id(urlBlacklist.getId()).ip(urlBlacklist.getIp()).urlB(urlBlacklist.getUrlB()).build();
    }
}
