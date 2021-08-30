package vn.manage.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.manage.system.domain.ResponseHandler;
import vn.manage.system.domain.SystemSettingRequestDto;
import vn.manage.system.domain.SystemSettingResponseDto;
import vn.manage.system.models.SystemSetting;
import vn.manage.system.service.SystemSettingService;

import javax.validation.Valid;

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
  public ResponseEntity<?> getSystemSettingById(@RequestParam(required = false) String key, @RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "3") Integer size) {

    Pageable paging = PageRequest.of(page, size);

    Page<SystemSetting> systemSettingPage = systemSettingService.getAllSystemSetting(key, paging);

    return ResponseHandler.generateResponse(HttpStatus.OK, systemSettingPage.getContent(), systemSettingPage);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSystemSettingById(@PathVariable Integer id) {
    systemSettingService.deleteSystemSetting(id);
    return ResponseHandler.generateResponse(HttpStatus.OK, true);
  }
}
