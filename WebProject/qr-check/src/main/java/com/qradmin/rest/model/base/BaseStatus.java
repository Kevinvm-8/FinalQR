package com.qradmin.rest.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author quangvn
 */
@Getter
@AllArgsConstructor
public enum BaseStatus {
    SUCCESS(
            "00", "Success"
    ),
    INVALID_DATA(
            "99", "Invalid-data"
    ),
    UNKNOWN_EXCEPTION(
            "99", "Unknown Exception"
    ),
    NOT_FOUND(
            "99", "Not Found"
    ),
    SIGNATURE_INCORRECT(
            "99", "Signature Incorrect"
    ),
    ERROR_CHECKSUM(
            "99", "Error Checksum"
    ),
    API_NO_DATA(
            "00", "Api don't have any data"
    ),
    ORDER_ID_EXIST(
            "99", "Order id already exist"
    ),
    SIGNATURE_EMPTY(
            "99", "Don't have a signature"
    ),
    AMOUNT_INVALID(
            "99","Amount must be integer, minimum transaction must be 1.000 VND and maximum must be 20.000.000"
    ),
    TRANSID_INVALID(
            "99","Trans Id invalid"
    ),
    CURRENCY_INVALID(
            "99","Currency invalid"
    );





    private String code;
    private String message;
}
