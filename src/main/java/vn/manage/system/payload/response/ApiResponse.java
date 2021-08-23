package vn.manage.system.payload.response;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ApiResponse {

  @JsonIgnore
  private Object data;
  private String error;
  private Boolean success;
  private int statusCode = HttpStatus.OK.value();

  public static ApiResponse fail(int statusCode, String error) {
    ApiResponse response = new ApiResponse();
    response.setError(error);
    response.setStatusCode(statusCode);
    response.setSuccess(false);
    return response;
  }

  public static ApiResponse success() {
    ApiResponse response = new ApiResponse();
    response.setSuccess(true);
    return response;
  }

  public static ApiResponse success(Object data) {
    ApiResponse response = success();
    response.setData(data);
    response.setSuccess(true);
    return response;
  }

  private ApiResponse() {
  }

}
