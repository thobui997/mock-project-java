package vn.manage.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.manage.system.domain.ResponseHandler;
import vn.manage.system.domain.SystemSettingRequestDto;
import vn.manage.system.domain.SystemSettingResponseDto;
import vn.manage.system.service.SystemSettingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/system-settings")
public class SystemSettingController {

  @Autowired
  private SystemSettingService systemSettingService;

  @PostMapping
  public ResponseEntity<?> postSystemSettings(@Valid @RequestBody SystemSettingRequestDto req) {

    SystemSettingResponseDto systemSettingResponseDto = systemSettingService.createSystemSetting(req);
    return ResponseHandler.generateResponse(HttpStatus.CREATED, systemSettingResponseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> putSystemSettings(@PathVariable Integer id, @Valid @RequestBody SystemSettingRequestDto req) {
    SystemSettingResponseDto systemSettingResponseDto = systemSettingService.updatedSystemSetting(id, req);
    return ResponseHandler.generateResponse(HttpStatus.OK, systemSettingResponseDto);
  }

  @GetMapping
  public ResponseEntity<?> getSystemSettingById(@RequestParam List<Integer> id, @RequestParam List<String> key) {
    return ResponseEntity.ok().body("");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSystemSettingById(@PathVariable Integer id) {
    systemSettingService.deleteSystemSetting(id);
    return ResponseHandler.responseSuccess(HttpStatus.OK, true);
  }
}
