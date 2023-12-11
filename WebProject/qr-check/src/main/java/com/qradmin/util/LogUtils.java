package com.qradmin.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class LogUtils {

    protected Logger logger = LogManager.getLogger("toolLog");
    protected Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .disableHtmlEscaping()
            .create();
    public final String STEP_BEGIN_PROCESS = "Begin process";
    public final String STEP_END_PROCESS = "End process";
    public final String STEP_BEGIN_CALL_DB = "Begin call database";
    public final String STEP_END_CALL_DB = "End call database";
    public final String STEP_BEGIN_CALL_PARTNER = "Begin call partner";
    public final String STEP_END_CALL_PARTNER = "End call partner";
    public final String STEP_BEGIN_CALL_INTERNAL_SERVICE = "Begin call internal service";
    public final String STEP_END_CALL_INTERNAL_SERVICE = "End call internal service";
    public final String STEP_TRACE = "Step trace";

    public void logInfo(String stepName, String contentInfo) {
        try {
            logger.info("{} - {} - {} - {}", getRequestId(), getProcessName(), stepName, contentInfo);
        } catch (Exception e) {
            logger.info("{} - {} - {} - {}", getRequestId(), getProcessName(), STEP_TRACE, "Exception gen logs");
        }
    }

    public void logErr(String stepName, Exception ex) {
        try {
            logger.error("{} - {} - {} - {}", getRequestId(), getProcessName(), stepName, "Exception", ex);
        } catch (Exception e) {
            logger.error("{} - {} - {} - {}", getRequestId(), getProcessName(), STEP_TRACE, "Exception gen logs");
        }
    }

    public void logInfo(String stepName, Exception ex) {
        try {
            logger.error("{} - {} - {} - {}", getRequestId(), getProcessName(), stepName, "Exception " + ex.getMessage());
        } catch (Exception e) {
            logger.error("{} - {} - {} - {}", getRequestId(), getProcessName(), STEP_TRACE, "Exception gen logs");
        }
    }

    private String getRequestId() {
        return ThreadContext.get("request-id");
    }

    private String getProcessName() {
        return ThreadContext.get("process-name");
    }

    public void setProcessName(String processName){
        ThreadContext.put("process-name", processName);
    }

    public String genContentLog(String reqBody, String reqHeader) {
        try {
            JsonObject jsonObject = new JsonObject();
            try {
                JsonObject jsonObj = JsonParser.parseString((StringUtils.isEmpty(reqBody) ? "{}" : reqBody)).getAsJsonObject();
                jsonObject.add("request_body", jsonObj);
            } catch (Exception e) {
                jsonObject.addProperty("request_body", reqBody);
            }

            jsonObject.add("request_header", JsonParser.parseString((StringUtils.isEmpty(reqHeader) ? "{}" : reqHeader)).getAsJsonObject());

            return "req=" + jsonObject;
        } catch (Exception e) {
            logger.error("Exception gen logs", e);
            return "ex=" + e.getMessage();
        }
    }

    public String genContentLog(ResponseEntity<String> response) {
        try {
            String respStatusCode = "";
            String respBody = "";
            String respHeader = "";
            if (response != null) {
                respStatusCode = String.valueOf(response.getStatusCodeValue());
                respBody = response.getBody();
                respHeader = gsonBuilder.toJson(response.getHeaders());
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.add("response_body", JsonParser.parseString((StringUtils.isEmpty(respBody) ? "{}" : respBody)).getAsJsonObject());
            jsonObject.add("response_header", JsonParser.parseString((StringUtils.isEmpty(respHeader) ? "{}" : respHeader)).getAsJsonObject());
            jsonObject.addProperty("response_status_code", respStatusCode);

            return "response=" + jsonObject;
        } catch (Exception e) {
            logger.error("Exception gen logs", e);
            return "ex=" + e.getMessage();
        }
    }

    public String genContentLog(String url, String reqBody, String reqHeader, ResponseEntity<String> response, String serviceFrom,
                                String serviceTo, long requestTime, long respTime, String transID, String action) {
        try {
            String respStatusCode = "";
            String respBody = "";
            String respHeader = "";
            if (response != null) {
                respStatusCode = String.valueOf(response.getStatusCodeValue());
                respBody = response.getBody();
                respHeader = gsonBuilder.toJson(response.getHeaders());
            }

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("from_service", serviceFrom);
            jsonObject.addProperty("to_service", serviceTo);
            jsonObject.addProperty("request_time", requestTime);
            jsonObject.addProperty("response_time", respTime);
            jsonObject.addProperty("action", action);
            jsonObject.addProperty("response_status_code", respStatusCode);
            try {
                JsonObject asJsonObject = JsonParser.parseString((StringUtils.isEmpty(reqBody) ? "{}" : reqBody)).getAsJsonObject();
                jsonObject.add("request_body", asJsonObject);
            } catch (Exception e) {
                jsonObject.addProperty("request_body", reqBody);
            }
            jsonObject.add("request_header", JsonParser.parseString((StringUtils.isEmpty(reqHeader) ? "{}" : reqHeader)).getAsJsonObject());
            try {
                JsonObject asJsonObject = JsonParser.parseString((StringUtils.isEmpty(respBody) ? "{}" : respBody)).getAsJsonObject();
                jsonObject.add("response_body", asJsonObject);
            } catch (Exception e) {
                jsonObject.addProperty("response_body", respBody);
            }
            jsonObject.add("response_header", JsonParser.parseString((StringUtils.isEmpty(respHeader) ? "{}" : respHeader)).getAsJsonObject());
            jsonObject.addProperty("trans_id", transID);
            jsonObject.addProperty("path", url);

            return "response=" + jsonObject;
        } catch (Exception e) {
            logger.error("Exception gen logs", e);
            return "ex=" + e.getMessage();
        }
    }
}
