package vn.manage.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.manage.system.constants.ErrorCodeEnum;
import vn.manage.system.domain.CategoryRequestDto;
import vn.manage.system.domain.CategoryResponseDto;
import vn.manage.system.exception.ManageSystemRequestException;
import vn.manage.system.models.Category;
import vn.manage.system.repository.CategoryRepository;
import vn.manage.system.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;


	@Override
	@Transactional
	public CategoryResponseDto createCategory(CategoryRequestDto request) {
		Category category = categoryRepository.save(new Category(request));
		request.setId(category.getId());
		return new CategoryResponseDto(request);
	}

	@Override
	@Transactional
	public void deleteCategory(Integer id) {
		if(!categoryRepository.existsById(id)) {
			throw new ManageSystemRequestException(ErrorCodeEnum.DATA_NOT_FOUND.getMessage());
		}
		categoryRepository.deleteById(id);
	}
}
