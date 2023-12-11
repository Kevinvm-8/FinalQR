package com.qradmin.service;

import com.qradmin.db.UrlList;
import com.qradmin.db.UrlReport;
import com.qradmin.db.repo.UrlListRepository;

import com.qradmin.db.repo.UrlReportRepository;
import com.qradmin.rest.model.base.BaseResponse;
import com.qradmin.rest.model.base.BaseStatus;
import com.qradmin.rest.model.request.AddUrlRequest;
import com.qradmin.rest.model.request.UpdateUrlRequest;
import com.qradmin.rest.model.request.UrlDTOReport;
import com.qradmin.service.base.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UrlListService extends BaseService {

    @Autowired
    private UrlListRepository urlListRepository;
    @Autowired
    private UrlReportRepository urlReportRepository;

    @Override
    public ResponseEntity process() {
        return null;
    }

    public ResponseEntity addUrl(AddUrlRequest addUrlRequest) {
        try {
            UrlList urlList = new UrlList();
            urlList.setIp(addUrlRequest.getIp());
            urlList.setUrl(addUrlRequest.getUrl());
            urlList.setBlackList(addUrlRequest.isBlackList());
            urlListRepository.save(urlList);
            return createResponse(BaseResponse.of(BaseStatus.SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            return responseError(e, BaseResponse.of(BaseStatus.UNKNOWN_EXCEPTION));

        }
    }

    public ResponseEntity updateUrl(UpdateUrlRequest updateUrlRequest) {
        try {
            UrlList urlList = new UrlList();
            urlList.setId(updateUrlRequest.getId());
            urlList.setIp(updateUrlRequest.getIp());
            urlList.setUrl(updateUrlRequest.getUrl());
            urlList.setBlackList(updateUrlRequest.isBlackList());
            urlListRepository.save(urlList);
            return createResponse(BaseResponse.of(BaseStatus.SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            return responseError(e, BaseResponse.of(BaseStatus.UNKNOWN_EXCEPTION));

        }
    }

   public ResponseEntity deleteUrl(String id) {
       try {
                // Kiểm tra xem bản ghi có tồn tại không
        if (urlListRepository.existsById(id)) {
            // Nếu tồn tại, thực hiện xóa
            urlListRepository.deleteById(id);
            return createResponse(BaseResponse.of(BaseStatus.SUCCESS), HttpStatus.OK);
        } else {
            // Nếu không tồn tại, trả về mã lỗi hoặc thông báo phù hợp
            return createResponse(BaseResponse.of(BaseStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
       } catch (Exception e) {
           return responseError(e, BaseResponse.of(BaseStatus.UNKNOWN_EXCEPTION));

       }
   }
//
//    public ResponseEntity getAllUrl() {
//        try {
//
//        } catch (Exception e) {
//            return responseError(e, BaseResponse.of(BaseStatus.UNKNOWN_EXCEPTION));
//
//        }
//    }
//
    public ResponseEntity getAllBlackUrl() {
        try {
            List<UrlList> urlLists = urlListRepository.findByIsBlackList(true);
            return   createResponse(urlLists, HttpStatus.OK);
        } catch (Exception e) {
            return responseError(e, BaseResponse.of(BaseStatus.UNKNOWN_EXCEPTION));

        }
    }

    public ResponseEntity getAllWhiteUrl() {
        try {
            List<UrlList> urlLists = urlListRepository.findByIsBlackList(false);
           return createResponse(urlLists, HttpStatus.OK);
        } catch (Exception e) {
            return responseError(e, BaseResponse.of(BaseStatus.UNKNOWN_EXCEPTION));

        }
    }

//    public UrlDTOReport saveUrl(UrlDTOReport urlDTOReport, HttpServletRequest request) {
//        UrlReport urlReport = new UrlReport();
//        UrlList urlList = new UrlList();
//        if (urlList.getUrl().equalsIgnoreCase(urlDTOReport.getUrlCheck())){
//        urlReport.setUrl(urlDTOReport.getUrlCheck());
//        urlReportRepository.save(urlReport);}else {
//            System.out.println("Url ton tai");
//        }
//        return urlDTOReport;
//    }

//    public UrlReport saveUrlReport(String url) {
//        UrlReport urlReport = new UrlReport();
//        urlReport.setUrlReport(url);
//        urlReportRepository.save(urlReport);
//        return urlReport;
//
//    }

    public UrlDTOReport saveUrlReport(UrlDTOReport urlDTOReport, HttpServletRequest request) {
        UrlReport urlReport = new UrlReport();
        UrlList urlList = new UrlList();
        if(urlList.getUrl() != null && urlList.getUrl().equalsIgnoreCase(urlDTOReport.getUrlCheck())){
        urlReport.setUrlReport(urlDTOReport.getUrlCheck());
        urlReportRepository.save(urlReport);
        }else {
            System.out.println("url da ton tai");
        }
        return urlDTOReport;
    }

//    public ResponseEntity updateUrl(UrlReport urlReport, HttpServletRequest request) {
//
//    }
}
