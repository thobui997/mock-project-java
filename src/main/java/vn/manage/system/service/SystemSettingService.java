package vn.manage.system.service;

import vn.manage.system.domain.SystemSettingRequestDto;
import vn.manage.system.domain.SystemSettingResponseDto;

public interface SystemSettingService {

    SystemSettingResponseDto createSystemSetting(SystemSettingRequestDto req);

    SystemSettingResponseDto updatedSystemSetting(Integer id, SystemSettingRequestDto req);

    void deleteSystemSetting(Integer id);

}
