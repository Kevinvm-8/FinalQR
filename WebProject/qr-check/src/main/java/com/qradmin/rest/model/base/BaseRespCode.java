package com.qradmin.rest.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author quangvn
 */
@Getter
@AllArgsConstructor
public enum BaseRespCode {
    SUCCESS(
            "00", "Success"
    ),
    UNKNOWN_EXCEPTION(
            "99", "Unknown Exception"
    ),
    SIGNATURE_INCORRECT(
            "99", "Signature Incorrect"
    ),
    ERROR_CHECKSUM(
            "99", "Error Checksum"
    ),
    API_NO_DATA(
            "99", "Api don't have any data"
    ),
    SIGNATURE_EMPTY(
            "99", "Don't have a signature"
    ),
    API_RESPONSE_NULL(
            "99","Api response null value"
    ),
    CALL_PARTNER_EXCEPTION(
            "99","Call partner exception"
    );

    private String respCode;
    private String message;
}
