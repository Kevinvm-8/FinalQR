package com.qradmin.rest.model.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author quangvn
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseResponse {
    @Expose
    @SerializedName("error_code") // trạng thái xử lý của API 00: thành công, 99: lỗi
    protected String errCode;
    @Expose
    @SerializedName("message") // thông tin lỗi
    protected String message;
    @Expose
    @SerializedName("response_code") // trạng thái xử lý của API 00: thành công, 99: lỗi
    protected String responseCode;

    public static BaseResponse of(BaseStatus status) {
        return BaseResponse.builder()
                .errCode(status.getCode())
                .message(status.getMessage())
                .build();
    }
    public static BaseResponse of(BaseRespCode baseRespCode) {
        return BaseResponse.builder()
                .responseCode(baseRespCode.getRespCode())
                .message(baseRespCode.getMessage())
                .build();
    }
}
