package vn.manage.system.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.manage.system.entities.Category;
import vn.manage.system.entities.SystemSettingCategory;
import vn.manage.system.enums.ErrorCodeEnum;
import vn.manage.system.exception.ManageSystemException;
import vn.manage.system.payload.request.CategoryRequest;
import vn.manage.system.payload.response.ApiResponse;
import vn.manage.system.payload.response.CategoryResponse;
import vn.manage.system.repository.CategoryRepository;
import vn.manage.system.repository.SystemSettingCategoryRepository;
import vn.manage.system.service.CategoryService;

@Service
@Setter
@Getter
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final SystemSettingCategoryRepository systemSettingCategoryRepository;

  @Override
  @Transactional
  public CategoryResponse createCategory(CategoryRequest request) {

    Category category = categoryRepository.save(new Category(request));
    request.setId(category.getId());
    return new CategoryResponse(request);
  }

  @Override
  public ApiResponse deleteCategory(Integer id) {
    SystemSettingCategory systemSettingCategory = systemSettingCategoryRepository.findSystemSettingCategoryByCategoryId(id)
      .orElseThrow(ManageSystemException.exception(ErrorCodeEnum.DATA_NOT_FOUND));

    systemSettingCategoryRepository.deleteById(systemSettingCategory.getId());

    categoryRepository.findById(id).orElseThrow(ManageSystemException.exception(ErrorCodeEnum.DATA_NOT_FOUND));
    categoryRepository.deleteById(id);

    return ApiResponse.success();
  }
}
