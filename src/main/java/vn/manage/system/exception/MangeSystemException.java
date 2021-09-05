package vn.manage.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MangeSystemException {

	private final String message;
	private final Integer status;
}
