package vn.manage.system.exception;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.util.StringUtils;
import vn.manage.system.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManageSystemException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private final String code;
  private final String errorMessage;

  public ManageSystemException(ErrorCodeEnum error) {
    this.code = error.getErrorCode();
    this.errorMessage = error.getMessage();
  }

  public ManageSystemException(String code, String errorMessage) {
    this.code = code;
    this.errorMessage = errorMessage;
  }

  public ManageSystemException(String code, Throwable cause, String errorMessage) {
    super(cause);
    this.code = code;
    this.errorMessage = errorMessage;
  }

  public String getCode() {
    return code;
  }

  public static void doThrow(ErrorCodeEnum error) {
    throw new ManageSystemException(error);
  }

  public static Supplier<ManageSystemException> exception(ErrorCodeEnum error) {
    return () -> new ManageSystemException(error);
  }

  @SuppressWarnings("rawtypes")
  public static void assertNotEmpty(Object obj, ErrorCodeEnum error) {
    if (Objects.isNull(obj)) {
      doThrow(error);
    }
    if (obj instanceof String && !StringUtils.hasText(obj.toString())) {
      doThrow(error);
    }
    if (obj instanceof Collection && ((Collection) obj).size() == 0) {
      doThrow(error);
    }
  }

  public static void assertTrue(boolean logicExpression, ErrorCodeEnum error) {
    if (!logicExpression) {
      doThrow(error);
    }
  }

}
