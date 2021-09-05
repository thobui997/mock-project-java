package vn.manage.system.service;

import vn.manage.system.domain.CategoryRequestDto;
import vn.manage.system.domain.CategoryResponseDto;

public interface CategoryService {

	CategoryResponseDto createCategory(CategoryRequestDto request);

	void deleteCategory(Integer id);
}
