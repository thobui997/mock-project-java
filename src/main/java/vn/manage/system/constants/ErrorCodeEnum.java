package vn.manage.system.constants;

public enum ErrorCodeEnum {

  SYSTEM_ERROR("E000", "System error!"),
  DATA_NOT_FOUND("E001", "Data is not found!"),
  WRONG_DATA_TYPE("E002", "Incorrect data type!");

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
