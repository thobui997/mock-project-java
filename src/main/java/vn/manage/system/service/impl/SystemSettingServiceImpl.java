package vn.manage.system.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.manage.system.entities.Category;
import vn.manage.system.entities.SystemSetting;
import vn.manage.system.entities.SystemSettingCategory;
import vn.manage.system.enums.ErrorCodeEnum;
import vn.manage.system.exception.ManageSystemException;
import vn.manage.system.payload.request.SystemSettingRequest;
import vn.manage.system.payload.response.ApiResponse;
import vn.manage.system.payload.response.SystemSettingResponse;
import vn.manage.system.repository.CategoryRepository;
import vn.manage.system.repository.SystemSettingCategoryRepository;
import vn.manage.system.repository.SystemSettingRepository;
import vn.manage.system.service.SystemSettingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Setter
@Getter
@RequiredArgsConstructor
public class SystemSettingServiceImpl implements SystemSettingService {

  private final SystemSettingRepository systemSettingRepository;
  private final SystemSettingCategoryRepository systemSettingCategoryRepository;
  private final CategoryRepository categoryRepository;

  @Override
  @Transactional
  public SystemSettingResponse createSystemSetting(SystemSettingRequest req) {

    List<Category> categoryList = categoryRepository.findByNameIn(req.getCategories());
    ManageSystemException.assertTrue(categoryList.size() == req.getCategories().size(),
      ErrorCodeEnum.DATA_NOT_FOUND);

    SystemSetting systemSetting = systemSettingRepository.save(new SystemSetting(req));

    systemSettingCategoryRepository.saveAll(categoryList.stream()
      .map(x -> new SystemSettingCategory(systemSetting.getId(), x.getId()))
      .collect(Collectors.toList()));
    req.setId(systemSetting.getId());

    return new SystemSettingResponse(req);
  }

  @Override
  @Transactional
  public SystemSettingResponse updatedSystemSetting(Integer id, SystemSettingRequest req) {
    systemSettingRepository.findById(id)
      .orElseThrow(ManageSystemException.exception(ErrorCodeEnum.DATA_NOT_FOUND));

    List<Category> categoryList = categoryRepository.findByNameIn(req.getCategories());
    ManageSystemException.assertTrue(categoryList.size() == req.getCategories().size(),
      ErrorCodeEnum.DATA_NOT_FOUND);
    req.setId(id);

    SystemSetting systemSetting = systemSettingRepository.save(new SystemSetting(req));

    systemSettingCategoryRepository.saveAll(categoryList.stream()
      .map(x -> new SystemSettingCategory(systemSetting.getId(), x.getId()))
      .collect(Collectors.toList()));
    req.setId(systemSetting.getId());

    return new SystemSettingResponse(req);
  }

  @Override
  @Transactional
  public ApiResponse deleteSystemSetting(Integer id) {
    SystemSettingCategory systemSettingCategory = systemSettingCategoryRepository.findSystemSettingCategoryBySystemSettingId(id).
      orElseThrow(ManageSystemException.exception(ErrorCodeEnum.DATA_NOT_FOUND));

    systemSettingCategoryRepository.deleteById(systemSettingCategory.getId());

    systemSettingRepository.findById(id).orElseThrow(ManageSystemException.exception(ErrorCodeEnum.DATA_NOT_FOUND));
    systemSettingRepository.deleteById(id);

    return ApiResponse.success();
  }
}
