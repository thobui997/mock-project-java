package vn.manage.system.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.manage.system.payload.request.SystemSettingRequest;
import vn.manage.system.payload.response.ApiResponse;
import vn.manage.system.service.SystemSettingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/system-settings")
@RequiredArgsConstructor
public class SystemSettingApi {

  private final SystemSettingService systemSettingService;

  @PostMapping
  public ResponseEntity<?> postSystemSettings(@Valid @RequestBody SystemSettingRequest req) {

    return ResponseEntity.ok()
      .body(ApiResponse.success(systemSettingService.createSystemSetting(req)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> putSystemSettings(@PathVariable Integer id, @Valid @RequestBody SystemSettingRequest req) {
    return ResponseEntity.ok()
      .body(ApiResponse.success(systemSettingService.updatedSystemSetting(id, req)));
  }

  @GetMapping
  public ResponseEntity<?> getSystemSettingById(@RequestParam List<Integer> id, @RequestParam List<String> key) {
    return ResponseEntity.ok().body("");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSystemSettingById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(systemSettingService.deleteSystemSetting(id));
  }
}
