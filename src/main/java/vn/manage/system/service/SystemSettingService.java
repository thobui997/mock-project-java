package vn.manage.system.service;

import vn.manage.system.payload.request.SystemSettingRequest;
import vn.manage.system.payload.response.ApiResponse;
import vn.manage.system.payload.response.SystemSettingResponse;

public interface SystemSettingService {

    SystemSettingResponse createSystemSetting(SystemSettingRequest req);

    SystemSettingResponse updatedSystemSetting(Integer id, SystemSettingRequest req);

    ApiResponse deleteSystemSetting(Integer id);

}
