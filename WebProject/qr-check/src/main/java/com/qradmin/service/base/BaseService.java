package com.qradmin.service.base;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.qradmin.config.ResourceProperties;
import com.qradmin.rest.model.base.BaseResponse;
import com.qradmin.rest.model.base.BaseStatus;
import com.qradmin.util.JsonUtil;
import com.qradmin.util.LogUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Type;
import java.util.*;

import static com.google.common.base.Strings.isNullOrEmpty;

public abstract class BaseService {

    @Autowired
    protected ResourceProperties resourceProperties;
    @Autowired
    protected LogUtils logUtils;

    protected String req;
    protected String jwt = null;
    protected String ip;
    protected String device;
    protected String reqData;
    protected int userId;
    protected Map<String, String> header = new HashMap<>();
    protected static final Gson gson = new Gson();

    protected String genID() {
        return UUID.randomUUID().toString();
    }

    public abstract ResponseEntity process();


    protected Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .disableHtmlEscaping()
            .create();
    protected Type type = new TypeToken<Map<String, String>>() {
    }.getType();

    protected ResponseEntity<String> createResponse(Object baseResponse, HttpStatus httpStatus) {
        try {
            return new ResponseEntity<>(gsonBuilder.toJson(baseResponse), httpStatus);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    protected ResponseEntity<String> createResponseError(BaseResponse baseResponse, HttpStatus code) {
        return new ResponseEntity<>(gsonBuilder.toJson(baseResponse), code);
    }

    protected ResponseEntity<String> responseError(Exception e, BaseResponse baseResponse) {
        logUtils.logErr(logUtils.STEP_TRACE, e);
        logUtils.logInfo(logUtils.STEP_TRACE, e.getMessage());
        ResponseEntity<String> responseError = createResponseError(baseResponse, HttpStatus.OK);
        logUtils.logInfo(logUtils.STEP_END_PROCESS, logUtils.genContentLog(responseError));
        return responseError;
    }

    /**
     * Common validate a {@link String request} for each API process.
     * <p>Each request come to this validation should have at least:</p>
     * <p>1. {@code ip_address},</p>
     * <p>2. {@code device},</p>
     * <p>3. {@code jwt} and exist user id</p>
     *
     * @return {@link Boolean boolean} true if everything valid
     */
    protected boolean parseReq() {
        try {

            JsonObject jsonReq = JsonParser.parseString(req).getAsJsonObject();
            ip = JsonUtil.getAsString(jsonReq, "ip_address", null);
            if (isNullOrEmpty(ip) || ip.length() > 24 || !jsonReq.has("device")) {
                return false;
            }
            device = JsonUtil.getAsString(jsonReq, "device", null);
            jsonReq.remove("device");
            jsonReq.remove("ip_address");
            reqData = jsonReq.toString();
            if (device.isEmpty() || device.length() > 512) {
                return false;
            }


            return true;
        } catch (Exception ex) {
            logger.error("parseReq exception>>>" + ex.getMessage());
            return false;
        }
    }

    //    protected ResponseEntity<String> responseError(Exception e){
//        logUtils.logInfo(logUtils.STEP_TRACE, e);
//        logUtils.logErr(logUtils.STEP_TRACE, e);
//        ResponseEntity<String> responseError = createResponse(BaseResponse.of(BaseStatus.UNKNOWN_ERROR), HttpStatus.OK);
//        logUtils.logInfo(logUtils.STEP_END_PROCESS, logUtils.genContentLog(responseError));
//        return responseError;
//    }
    protected ResponseEntity<String> invalidData() {
        logUtils.logInfo(logUtils.STEP_TRACE, BaseStatus.INVALID_DATA.getMessage());
        BaseResponse baseResponse = BaseResponse.of(BaseStatus.INVALID_DATA);
        ResponseEntity<String> responseError = createResponseError(baseResponse, HttpStatus.OK);
        logUtils.logInfo(logUtils.STEP_END_PROCESS, logUtils.genContentLog(responseError.getBody(), gsonBuilder.toJson(responseError.getHeaders())));
        return responseError;
    }

    protected Logger logger = LogManager.getLogger("toolLog");



    protected boolean isValidLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    protected  boolean isValidLong(Number number) {
        return  number.longValue() == number.doubleValue();
    }
    protected  boolean isValidPaymentAmount(Number number) {
        if (isValidLong(number)) {
            return number.longValue() >= 1000 && number.longValue() <= 20000000;
        }
        return false;
    }

}
