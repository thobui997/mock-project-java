package vn.manage.system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.manage.system.domain.SystemSettingRequestDto;
import vn.manage.system.domain.SystemSettingResponseDto;
import vn.manage.system.models.SystemSetting;

public interface SystemSettingService {

    SystemSettingResponseDto createSystemSetting(SystemSettingRequestDto req);

    SystemSettingResponseDto updatedSystemSetting(Integer id, SystemSettingRequestDto req);

    void deleteSystemSetting(Integer id);

    Page<SystemSetting> getAllSystemSetting(String key, Pageable paging);

}
