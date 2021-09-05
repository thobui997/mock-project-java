package vn.manage.system.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.manage.system.constants.ErrorCodeEnum;

@ControllerAdvice
public class ApiExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@ExceptionHandler(value = {ManageSystemRequestException.class})
	public ResponseEntity<?> handleChoVuiException(ManageSystemRequestException ex) {
		logger.error("Manage System Application error", ex);

		MangeSystemException mangeSystemException =
			new MangeSystemException(ex.getMessage(), HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(mangeSystemException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
		logger.error("Error Occur! ", e);
		MangeSystemException mangeSystemException =
			new MangeSystemException(e.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(mangeSystemException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handle(HttpRequestMethodNotSupportedException e) {
		logger.error("Error Occur! ", e);

		MangeSystemException mangeSystemException =
			new MangeSystemException(e.getMessage(), HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(mangeSystemException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handle(HttpMessageNotReadableException e) {
		logger.error("Error Occur! ", e);

		MangeSystemException mangeSystemException =
			new MangeSystemException(e.getMessage(), HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(mangeSystemException, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<?> handleException(Exception ex) {
		logger.error("System Error", ex);

		MangeSystemException mangeSystemException =
			new MangeSystemException(ErrorCodeEnum.SYSTEM_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ResponseEntity<>(mangeSystemException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
