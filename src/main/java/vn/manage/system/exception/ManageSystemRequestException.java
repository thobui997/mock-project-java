package vn.manage.system.exception;

import vn.manage.system.constants.ErrorCodeEnum;

import java.util.function.Supplier;

public class ManageSystemRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ManageSystemRequestException(String message) {
		super(message);
	}

	public static Supplier<ManageSystemRequestException> exception(ErrorCodeEnum error) {
		return () -> new ManageSystemRequestException(error.getMessage());
	}

	public static void doThrow(ErrorCodeEnum errorCodeEnum) {
		throw new ManageSystemRequestException(errorCodeEnum.getMessage());
	}

	public static void assertTrue(boolean logicExpression, ErrorCodeEnum error) {
		if(! logicExpression) {
			doThrow(error);
		}
	}

}
