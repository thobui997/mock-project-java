package vn.manage.system.exception.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.manage.system.exception.ManageSystemException;
import vn.manage.system.enums.ErrorCodeEnum;
import vn.manage.system.payload.response.ApiResponse;

@ControllerAdvice
public class ExceptionHelper {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

  @ExceptionHandler(value = {ManageSystemException.class})
  public ResponseEntity<?> handleChoVuiException(ManageSystemException ex) {
    logger.error("Manage System Application error", ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), ex.getCode(), ex.getErrorMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
    logger.error("Error Occur! ", e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse
      .fail(HttpStatus.BAD_REQUEST.value(), "", e.getAllErrors().get(0).getDefaultMessage()));
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<?> handle(HttpRequestMethodNotSupportedException e) {
    logger.error("Error Occur! ", e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), "", e.getMessage()));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<?> handle(HttpMessageNotReadableException e) {
    logger.error("Error Occur! ", e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(ApiResponse.fail(HttpStatus.BAD_REQUEST.value(), "", e.getMessage()));
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<?> handleException(Exception ex) {
    logger.error("System Error", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ErrorCodeEnum.SYSTEM_ERROR.getErrorCode(), ErrorCodeEnum.SYSTEM_ERROR.getMessage()));
  }

}
