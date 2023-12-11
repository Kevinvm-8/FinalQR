package com.example.QRcode.Controller;


import com.example.QRcode.entity.UrlBlacklist;
import com.example.QRcode.entity.UrlReport;
import com.example.QRcode.entity.UrlWhitelist;
import com.example.QRcode.exception.custom.CustomResponseUrl;
import com.example.QRcode.exception.custom.CustomResponseUrlBlack;
import com.example.QRcode.model.*;
import com.example.QRcode.model.response.LoginResponse;
import com.example.QRcode.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@Transactional
@RequiredArgsConstructor
public class UrlController {

    @Autowired
    private final UrlService urlService;
    @PostMapping("/url-blacklist")
    public CustomResponseUrl addUrlBlacklist(@RequestBody AddUrlRequest addUrlRequest) {
        return urlService.addUrlBlacklist(addUrlRequest);
    }@PostMapping("/url-Whitelist")
    public CustomResponseUrl addUrlWhitelist(@RequestBody AddUrlRequest addUrlRequest) {
        return urlService.addUrlWhitelist(addUrlRequest);
    }
    @PostMapping("/urlScan")
    public CustomResponseUrl checkurlScan(@RequestBody UrlDTOScan urlDTOScan) {
        return urlService.ScanQRcode(urlDTOScan);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Login addUrlRequest) {
        if(addUrlRequest.getUsername().equals("admin") && addUrlRequest.getPassword().equals("admin"))
            return ResponseEntity.ok(LoginResponse.builder()
                    .success(true)
                    .time(600)
                    .build());
        else return ResponseEntity.status(401).build();
    }
    @PutMapping("/url-blacklist")
    public CustomResponseUrl updateUrlBlack(@RequestBody UpdateUrlRequest updateUrlRequest) {
        return urlService.updateUrlBlacklist(updateUrlRequest);
    }@PutMapping("/url-Whitelist")
    public CustomResponseUrl updateUrlWhite(@RequestBody UpdateUrlRequest updateUrlRequest) {
        return urlService.updateUrlWhitelist(updateUrlRequest);
    }
    @GetMapping("/white-url-list")
    public ResponseEntity<List<UrlWhitelist>> getWhiteUrl() {
        return urlService.getAllWhiteUrl();
    }
    @GetMapping("/black-url-list")
    public ResponseEntity<List<UrlBlacklist>> getBlackUrl() {
        return urlService.getAllBlackUrl();
    }
    @GetMapping("/report-url-list")
    public ResponseEntity<List<UrlReport>> getReportUrl(){
        return urlService.getAllReportUrl();
    }
    @GetMapping("/{id}")
    public ResponseEntity getPersonById(@PathVariable String id) {
        return urlService.getAllBlackUrl();
    }
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/url-blacklist/{id}"}, produces = {
            "application/json;charset=UTF-8"})
    public CustomResponseUrl deleteblacklist(@PathVariable String id) {
        return urlService.deleteblacklist(id);
    }
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/url-Whitelist/{id}"}, produces = {
            "application/json;charset=UTF-8"})
    public CustomResponseUrl deletewhitelist(@PathVariable String id) {
        return urlService.deletewhitelist(id);
    }

    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/url-report/{id}"}, produces = {
            "application/json;charset=UTF-8"})
    public CustomResponseUrl deletereportlist(@PathVariable String id){
        return urlService.deletereportlist(id);
    }

    @PostMapping("/urlRepost")
    public CustomResponseUrl saveUrl(@RequestBody UrlDTOReport urlDTOReport, HttpServletRequest request){

        return urlService.saveUrlReport(urlDTOReport,request);
    }



}
