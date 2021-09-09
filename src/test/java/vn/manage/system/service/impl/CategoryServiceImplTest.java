package vn.manage.system.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.manage.system.domain.CategoryRequestDto;
import vn.manage.system.domain.CategoryResponseDto;
import vn.manage.system.exception.ManageSystemRequestException;
import vn.manage.system.models.Category;
import vn.manage.system.repository.CategoryRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Test
	void canCreateCategory() {

		CategoryRequestDto mockRequest = new CategoryRequestDto();
		mockRequest.setId(1);
		mockRequest.setName("name");
		mockRequest.setDescription("test description");

		CategoryResponseDto mockResponse = new CategoryResponseDto(mockRequest);

		Category category = new Category();
		category.setId(1);

		given(categoryRepository.save(any(Category.class))).willReturn(category);

		CategoryResponseDto result = categoryService.createCategory(mockRequest);

		// verify
		assertThat(result).usingRecursiveComparison().isEqualTo(mockResponse);
		verify(categoryRepository).save(any());
	}

	@Test
	void canDeleteCategory() {

		Integer mockId = 1;

		Category categoryMock = new Category();
		categoryMock.setId(1);

		given(categoryRepository.existsById(mockId)).willReturn(true);

		categoryService.deleteCategory(mockId);

		// verify
		verify(categoryRepository).deleteById(mockId);
	}

	@Test
	void canNotDeleteCategory() {

		Integer mockId = 1;

		given(categoryRepository.existsById(mockId)).willReturn(false);

		assertThatThrownBy(() -> categoryService.deleteCategory(mockId)).isInstanceOf(ManageSystemRequestException.class);

		// verify
		verify(categoryRepository, never()).deleteById(mockId);
	}
}