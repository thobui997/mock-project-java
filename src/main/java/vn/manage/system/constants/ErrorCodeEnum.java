package vn.manage.system.constants;

public enum ErrorCodeEnum {

  SYSTEM_ERROR("E000", "Lỗi hệ thống"),
  DATA_NOT_FOUND("E001", "Khong tim thay gia tri!"),
  WRONG_DATA_TYPE("E002", "Sai kieu du lieu"),
  DATA_EXISTED("E003", "Data da ton tai");

  private String errorCode;

  private String message;

  ErrorCodeEnum(String errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
