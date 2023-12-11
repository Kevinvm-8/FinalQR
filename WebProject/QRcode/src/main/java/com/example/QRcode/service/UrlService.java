package com.example.QRcode.service;


import com.example.QRcode.entity.UrlBlacklist;
import com.example.QRcode.entity.UrlReport;
import com.example.QRcode.entity.UrlWhitelist;
import com.example.QRcode.exception.custom.CustomResponseUrl;
import com.example.QRcode.model.AddUrlRequest;
import com.example.QRcode.model.UpdateUrlRequest;
import com.example.QRcode.model.UrlDTOReport;
import com.example.QRcode.model.UrlDTOScan;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UrlService {
    CustomResponseUrl ScanQRcode(UrlDTOScan urlDTOScan);



    CustomResponseUrl addUrlBlacklist(AddUrlRequest addUrlRequest);

    CustomResponseUrl addUrlWhitelist(AddUrlRequest addUrlRequest);



    CustomResponseUrl updateUrlBlacklist(UpdateUrlRequest updateUrlRequest);

    CustomResponseUrl updateUrlWhitelist(UpdateUrlRequest updateUrlRequest);

    ResponseEntity<List<UrlWhitelist>> getAllWhiteUrl();

    ResponseEntity<List<UrlBlacklist>> getAllBlackUrl();

    ResponseEntity<List<UrlReport>> getAllReportUrl();

    CustomResponseUrl deleteblacklist(String id);
    CustomResponseUrl deletewhitelist(String id);

    CustomResponseUrl deletereportlist(String id);

    CustomResponseUrl saveUrlReport(UrlDTOReport urlDTOReport, HttpServletRequest request);
}
