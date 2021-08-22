package vn.manage.system.payload.response;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ApiResponse {

    @JsonIgnore
    private static final String SUCCESS = "S001";
    private static final String FAIL = "-1";
    private String status = SUCCESS;
    private Object data;
    private String error;
    private int statusCode = HttpStatus.OK.value();
    private String errorDesc;

    public static ApiResponse fail(int statusCode, String error) {
        ApiResponse response = new ApiResponse();
        response.setStatus(FAIL);
        response.setError(error);
        response.setStatusCode(statusCode);
        return response;
    }

    public static ApiResponse success() {
        ApiResponse response = new ApiResponse();
        response.setStatus(SUCCESS);
        return response;
    }

    public static ApiResponse success(Object data) {
        ApiResponse response = success();
        response.setData(data);
        return response;
    }

    private ApiResponse() {}

}
