package com.example.QRcode.service.Impl;


import com.example.QRcode.entity.UrlBlacklist;
import com.example.QRcode.entity.UrlReport;
import com.example.QRcode.entity.UrlWhitelist;
import com.example.QRcode.exception.custom.CustomResponseUrl;
import com.example.QRcode.model.*;
import com.example.QRcode.repository.UrlBlacklistRepository;
import com.example.QRcode.repository.UrlReportRepository;
import com.example.QRcode.repository.UrlWhitelistRepository;
import com.example.QRcode.service.UrlService;
import com.example.QRcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor

@Transactional
public class UrlServiceImpl implements UrlService {
    @Autowired
    private final UrlBlacklistRepository urlBlacklistRepository;
    @Autowired
    private final UrlWhitelistRepository urlWhitelistRepository;
    @Autowired
    private final UrlReportRepository urlReportRepository;










//
//
    protected Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .disableHtmlEscaping()
            .create();


    protected ResponseEntity<String> createResponse(List<UrlWhitelist> baseResponse, HttpStatus httpStatus) {
        try {
            return new ResponseEntity<>(gsonBuilder.toJson(baseResponse), httpStatus);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    protected ResponseEntity<String> createResponseError(ErrorCode baseResponse, HttpStatus code) {
        return new ResponseEntity<>(gsonBuilder.toJson(baseResponse), code);
    }
    @Autowired
    protected LogUtils logUtils;
    protected ResponseEntity<String> responseError(Exception e, ErrorCode baseResponse) {
        logUtils.logErr(logUtils.STEP_TRACE, e);
        logUtils.logInfo(logUtils.STEP_TRACE, e.getMessage());
        ResponseEntity<String> responseError = createResponseError(baseResponse, HttpStatus.OK);
        logUtils.logInfo(logUtils.STEP_END_PROCESS, logUtils.genContentLog(responseError));
        return responseError;
    }









    @Override
    public CustomResponseUrl ScanQRcode(UrlDTOScan urlDTOScan) {
        CustomResponseUrl customResponseUrl = null;
        try {

            Optional<UrlBlacklist> blacklistedUrl = urlBlacklistRepository.findByUrlB(urlDTOScan.getUrlScan());
            Optional<UrlWhitelist> urlWhitelist = urlWhitelistRepository.findByUrlW(urlDTOScan.getUrlScan());

//            String dbData = urlBlackRepo.getDataUrlBlack();
            if (blacklistedUrl.isPresent()){
                return CustomResponseUrl.builder().code(ErrorCode.BLACKURL.getCode()).message(ErrorCode.BLACKURL.getDescription()).build();
            }else {
                if (urlWhitelist.isPresent()){
                    return CustomResponseUrl.builder().code(ErrorCode.WHITEURL.getCode()).message(ErrorCode.WHITEURL.getDescription()).build();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return CustomResponseUrl.builder().code(ErrorCode.NOTINDATA.getCode()).message(ErrorCode.NOTINDATA.getDescription()).build();
        }
        return customResponseUrl;
    }

    @Override
    public CustomResponseUrl addUrlBlacklist(AddUrlRequest addUrlRequest) {
        CustomResponseUrl customResponseUrl = null;
        try {
            UrlBlacklist urlBlacklist = new UrlBlacklist();
            urlBlacklist.setIp(addUrlRequest.getIp());
            urlBlacklist.setUrlB(addUrlRequest.getUrl());
            urlBlacklistRepository.save(urlBlacklist);
            return customResponseUrl.builder().code(ErrorCode.SUCCESSS.getCode()).message(ErrorCode.SUCCESSS.getDescription()).build();
        } catch (Exception e) {
            return customResponseUrl.builder().code(ErrorCode.UNKNOWN_EXCEPTION.getCode()).message(ErrorCode.UNKNOWN_EXCEPTION.getDescription()).build();

        }
    }

    @Override
    public CustomResponseUrl addUrlWhitelist(AddUrlRequest addUrlRequest) {
        CustomResponseUrl customResponseUrl = null;
        try {
            UrlWhitelist urlWhitelist = new UrlWhitelist();
            urlWhitelist.setIp(addUrlRequest.getIp());
            urlWhitelist.setUrlW(addUrlRequest.getUrl());
	    urlWhitelistRepository.save((urlWhitelist));
            return customResponseUrl.builder().code(ErrorCode.SUCCESSS.getCode()).message(ErrorCode.SUCCESSS.getDescription()).build();
        } catch (Exception e) {
            return customResponseUrl.builder().code(ErrorCode.UNKNOWN_EXCEPTION.getCode()).message(ErrorCode.UNKNOWN_EXCEPTION.getDescription()).build();

        }
    }

    @Override
    public CustomResponseUrl updateUrlBlacklist(UpdateUrlRequest updateUrlRequest) {
        CustomResponseUrl customResponseUrl = null;
        try {
            UrlBlacklist urlBlacklist = new UrlBlacklist();
            urlBlacklist.setId(updateUrlRequest.getId());
            urlBlacklist.setIp(updateUrlRequest.getIp());
            urlBlacklist.setUrlB(updateUrlRequest.getUrl());

            urlBlacklistRepository.save(urlBlacklist);
            return customResponseUrl.builder().code(ErrorCode.SUCCESSS.getCode()).message(ErrorCode.SUCCESSS.getDescription()).build();
        } catch (Exception e) {
            return customResponseUrl.builder().code(ErrorCode.UNKNOWN_EXCEPTION.getCode()).message(ErrorCode.UNKNOWN_EXCEPTION.getDescription()).build();

        }
    }

    @Override
    public CustomResponseUrl updateUrlWhitelist(UpdateUrlRequest updateUrlRequest) {
        CustomResponseUrl customResponseUrl = null;
        try {
            UrlWhitelist urlWhitelist = new UrlWhitelist();
            urlWhitelist.setId(updateUrlRequest.getId());
            urlWhitelist.setIp(updateUrlRequest.getIp());
            urlWhitelist.setUrlW(updateUrlRequest.getUrl());

            urlWhitelistRepository.save(urlWhitelist);
            return customResponseUrl.builder().code(ErrorCode.SUCCESSS.getCode()).message(ErrorCode.SUCCESSS.getDescription()).build();
        } catch (Exception e) {
            return customResponseUrl.builder().code(ErrorCode.UNKNOWN_EXCEPTION.getCode()).message(ErrorCode.UNKNOWN_EXCEPTION.getDescription()).build();

        }
    }

    @Override
    public ResponseEntity<List<UrlWhitelist>> getAllWhiteUrl() {
        try {

            List<UrlWhitelist> urlWhitelists = urlWhitelistRepository.findAll();
            return ResponseEntity.ok(urlWhitelists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<UrlBlacklist>> getAllBlackUrl() {
        try {

            List<UrlBlacklist> urlLists = urlBlacklistRepository.findAll();
            return ResponseEntity.ok(urlLists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

   // report get
    @Override
    public ResponseEntity<List<UrlReport>> getAllReportUrl(){
        try{
            List<UrlReport> urlReportLists = urlReportRepository.findAll();
            return ResponseEntity.ok(urlReportLists);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @Override
    public CustomResponseUrl deleteblacklist(String id) {
        CustomResponseUrl customResponseUrl= null;
        try {
            // Kiểm tra xem bản ghi có tồn tại không
            if (urlBlacklistRepository.existsById(id)) {
                // Nếu tồn tại, thực hiện xóa
                urlBlacklistRepository.deleteById(id);
                return customResponseUrl.builder().code(ErrorCode.SUCCESSS.getCode()).message(ErrorCode.SUCCESSS.getDescription()).build();
            } else  {
                return customResponseUrl.builder().code(ErrorCode.NOT_FOUND.getCode()).message(ErrorCode.NOT_FOUND.getDescription()).build();

            }
        } catch (Exception e) {
            return customResponseUrl.builder().code(ErrorCode.UNKNOWN_EXCEPTION.getCode()).message(ErrorCode.UNKNOWN_EXCEPTION.getDescription()).build();

        }
    }@Override
    public CustomResponseUrl deletewhitelist(String id) {
        CustomResponseUrl customResponseUrl= null;
        try {
            // Kiểm tra xem bản ghi có tồn tại không
            if (urlWhitelistRepository.existsById(id)) {
                // Nếu tồn tại, thực hiện xóa
                urlWhitelistRepository.deleteById(id);
                return customResponseUrl.builder().code(ErrorCode.SUCCESSS.getCode()).message(ErrorCode.SUCCESSS.getDescription()).build();
            } else  {
                return customResponseUrl.builder().code(ErrorCode.NOT_FOUND.getCode()).message(ErrorCode.NOT_FOUND.getDescription()).build();

            }
        } catch (Exception e) {
            return customResponseUrl.builder().code(ErrorCode.UNKNOWN_EXCEPTION.getCode()).message(ErrorCode.UNKNOWN_EXCEPTION.getDescription()).build();

        }
    }

    //delete report
    public CustomResponseUrl deletereportlist(String id){
        CustomResponseUrl customResponseUrl= null;
        try{
            if(urlReportRepository.existsById(id)){
                urlReportRepository.deleteById(id);
                return customResponseUrl.builder().code(ErrorCode.SUCCESSS.getCode()).message(ErrorCode.SUCCESSS.getDescription()).build();
            } else {
                return customResponseUrl.builder().code(ErrorCode.NOT_FOUND.getCode()).message(ErrorCode.NOT_FOUND.getDescription()).build();
            }
        }catch (Exception e) {
            return customResponseUrl.builder().code(ErrorCode.UNKNOWN_EXCEPTION.getCode()).message(ErrorCode.UNKNOWN_EXCEPTION.getDescription()).build();
        }
    }

    @Override
    public CustomResponseUrl saveUrlReport(UrlDTOReport urlDTOReport, HttpServletRequest request) {
        CustomResponseUrl customResponseUrl = null;

        Optional<UrlBlacklist> blacklistedUrl = urlBlacklistRepository.findByUrlB(urlDTOReport.getUrlCheck());
        Optional<UrlWhitelist> urlWhitelist = urlWhitelistRepository.findByUrlW(urlDTOReport.getUrlCheck());
        Optional<UrlReport> urlReport = urlReportRepository.findByUrlReport(urlDTOReport.getUrlCheck());
        if (blacklistedUrl.isPresent()){
            return customResponseUrl.builder().code(ErrorCode.URL_IN_DB_BLACK.getCode()).message(ErrorCode.URL_IN_DB_BLACK.getDescription()).build();
        }else {
            if (urlWhitelist.isPresent()){
                return customResponseUrl.builder().code(ErrorCode.URL_IN_DB_WHITE.getCode()).message(ErrorCode.URL_IN_DB_WHITE.getDescription()).build();
            }else {
                if (urlReport.isPresent()){
                return customResponseUrl.builder().code(ErrorCode.URL_IN_DB_CHECK.getCode()).message(ErrorCode.URL_IN_DB_CHECK.getDescription()).build();
            }else{
                UrlReport urlReport1 = new UrlReport();
                urlReport1.setUrlReport(urlDTOReport.getUrlCheck());
                urlReportRepository.save(urlReport1);
                return customResponseUrl.builder().code(ErrorCode.SAVED_SUCCESSFULLY.getCode()).message(ErrorCode.SAVED_SUCCESSFULLY.getDescription()).build();
            }

            }

        }


    }


}

