package com.example.QRcode.model;

public enum ErrorCode {

    WHITEURL("00", "safe"),
    BLACKURL("13","Warning"),
    NOTINDATA("01","no data"),
    SUCCESSS("02", "Success"),
    UNKNOWN_EXCEPTION(
            "99", "Unknown Exception"
    ),
    NOT_FOUND("03","Not Found"),
    URL_IN_DB_BLACK("06","Url in DB BLACKLIST"),
    URL_IN_DB_WHITE ("07","Url in DB WHITELIST"),
    URL_IN_DB_CHECK("08","Url in DB URLREPORT"),
    SAVED_SUCCESSFULLY("05","saved successfully"),
//    EMAILEXIST("01", "Email or phone number already available in our system."),
//    PASSWORDINCORRECT("02", "Password incorrect"),
//    AUTHENOFF("03", "Authen chua bat"),
//    AUTHENINVALID("04", "Loi goi authen"),
//    EMAILNOTEXIST("05", "Email not exist"),
//    IDINVALID("06", "Sai id"),
//    ACCOUNTADMIN("07", "Tai khoan khong co quyen truy cap"),
//    ACCOUNTOFF("08", "Account chua bat"),
//    ERROR("500", "Some thing went wrong"),
//    ACCOUNTNULL("11", "Account null"),
//    OLDPASSWORDINCORRECT("12", "Old Password incorrect"),
//    IMAGESUCCESS("14","Image update success"),
//    IMAGEERROR("15","Update image not success"),
//    MACCHANGE("16","Dữ liệu đã bị thay đổi"),
//    MACNULL("17","Dữ liệu là null"),
//    FAILTOSAVE("18", "khong luu vao database"),
//    MACERROR("19","Lỗi không lấy được dữ liệu"),
//    UserInRequestCannotBeFound("21","Lỗi không thể lấy thông tin người dùng từ request"),
//    DataIsNull("22","Dữ liệu trả về là null"),
//    CannotGetData("23","Lỗi không lấy được dữ liệu"),
//    UserNotExist("24","Tài khoản không tồn tại"),
//    DontHaveDataInResponse("25","Không trả về dữ liệu"),
//    ErrorDataType("26","Có lỗi về kiểu dữ liệu"),
//    CannotSaveToDatabase("27","Không thể lưu dữ liệu vào database"),
//    DontHaveDataInRequest("28","Không có dữ liệu gửi đến"),
//    ErrorSendEmail("29","Lỗi khi gửi email"),
//    LINKVERIFY("30","Link has been activated or wrong link"),
//    CannotDeleteFromDatabase("31","Không thể xóa dữ liệu ở database"),
//    PhoneNumberExist("32","Số điện thoại đã tồn tại"),
//
//    TokenExpired("59","Token đã hết hạn"),
//    TokenNotMatch("60","Token không khớp với tài khoản"),
//    AccountDontHaveToken("61","Tài khoản không có reset password token"),
//
//    HeaderError("99","Lỗi trong headers"),
    ;

    private final String code;
    private final String description;

    private ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
