package vn.manage.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class MangeSystemException {

  private final String message;
  private final Integer status;
}
