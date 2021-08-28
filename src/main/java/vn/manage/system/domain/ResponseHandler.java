package vn.manage.system.domain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

  public static ResponseEntity<?> generateResponse(HttpStatus status, Object responseObj) {
    Map<String, Object> map = new HashMap<>();

    map.put("data", responseObj);
    map.put("status", status.value());

    return new ResponseEntity<Object>(map, status);
  }

  public static ResponseEntity<?> responseSuccess(HttpStatus status, boolean success) {
    Map<String, Object> map = new HashMap<>();
    map.put("success", success);

    return new ResponseEntity<>(map, status);
  }
}
