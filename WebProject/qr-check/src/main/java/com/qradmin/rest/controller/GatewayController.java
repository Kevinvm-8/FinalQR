package com.qradmin.rest.controller;

import com.qradmin.db.UrlReport;


import com.qradmin.db.repo.UrlReportRepository;
import com.qradmin.rest.model.request.AddUrlRequest;
import com.qradmin.rest.model.request.Login;
import com.qradmin.rest.model.request.UpdateUrlRequest;
import com.qradmin.rest.model.request.UrlDTOReport;
import com.qradmin.rest.model.response.LoginResponse;
import com.qradmin.service.UrlListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Transactional

public class GatewayController {

    @Autowired
    private UrlListService urlListService;
    private final UrlReportRepository urlReportRepository;



    @RequestMapping(method = {RequestMethod.POST}, value = {"/url-list"}, produces = {
            "application/json;charset=UTF-8"})
    public ResponseEntity addUrl(@RequestBody AddUrlRequest addUrlRequest) {
        return urlListService.addUrl(addUrlRequest);
    }
    @RequestMapping(method = {RequestMethod.POST}, value = {"/login"}, produces = {
            "application/json;charset=UTF-8"})
    public ResponseEntity login(@RequestBody Login addUrlRequest) {
       if(addUrlRequest.getUsername().equals("admin") && addUrlRequest.getPassword().equals("admin"))
           return ResponseEntity.ok(LoginResponse.builder()
                           .success(true)
                           .time(600)
                   .build());
       else return ResponseEntity.status(401).build();
    }
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/url-list"}, produces = {
            "application/json;charset=UTF-8"})
    public ResponseEntity updateUrl(@RequestBody UpdateUrlRequest updateUrlRequest) {
        return urlListService.updateUrl(updateUrlRequest);
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/white-url-list"}, produces = {
            "application/json;charset=UTF-8"})
    public ResponseEntity getWhiteUrl() {
        return urlListService.getAllWhiteUrl();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/black-url-list"}, produces = {
            "application/json;charset=UTF-8"})
    public ResponseEntity getBlackUrl() {
        return urlListService.getAllBlackUrl();
    }

    @RequestMapping(method = {RequestMethod.GET}, value = {"/{id}"}, produces = {
            "application/json;charset=UTF-8"})
    public ResponseEntity getPersonById(@PathVariable String id) {
        return urlListService.getAllBlackUrl();
    }
       @RequestMapping(method = {RequestMethod.DELETE}, value = {"/url-list/{id}"}, produces = {
            "application/json;charset=UTF-8"})
    public ResponseEntity deleteUrl(@PathVariable String id) {
        return urlListService.deleteUrl(id);
    }
    @RequestMapping(method = {RequestMethod.POST}, value = {"/urlRepost"}, produces = {
            "application/json;charset=UTF-8"})
    public UrlDTOReport saveUrl(@RequestBody UrlDTOReport urlDTOReport, HttpServletRequest request){

        return urlListService.saveUrlReport(urlDTOReport,request);
    }


}
