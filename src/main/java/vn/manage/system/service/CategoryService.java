package vn.manage.system.service;

import vn.manage.system.payload.request.CategoryRequest;
import vn.manage.system.payload.response.ApiResponse;
import vn.manage.system.payload.response.CategoryResponse;

public interface CategoryService {

  CategoryResponse createCategory(CategoryRequest request);

  ApiResponse deleteCategory(Integer id);
}
