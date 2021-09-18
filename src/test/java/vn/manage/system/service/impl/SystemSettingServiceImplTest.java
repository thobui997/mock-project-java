package vn.manage.system.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import vn.manage.system.domain.SystemSettingRequestDto;
import vn.manage.system.domain.SystemSettingResponseDto;
import vn.manage.system.exception.ManageSystemRequestException;
import vn.manage.system.models.Category;
import vn.manage.system.models.SystemSetting;
import vn.manage.system.repository.CategoryRepository;
import vn.manage.system.repository.SystemSettingCategoryRepository;
import vn.manage.system.repository.SystemSettingRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SystemSettingServiceImplTest {

	@Mock
	private SystemSettingRepository systemSettingRepository;

	@Mock
	private SystemSettingCategoryRepository systemSettingCategoryRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private SystemSettingServiceImpl systemSettingService;

	@Test
	void canCreateSystemSetting() {
		Category category = new Category();
		category.setName("tho");
		category.setDescription("test description");

		SystemSetting systemSetting = new SystemSetting();
		systemSetting.setId(1);

		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key 1");
		requestDto.setValue("value 1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value 1", "value 2"));
		requestDto.setCategories(List.of("category 1"));

		SystemSettingResponseDto mockResponse = new SystemSettingResponseDto(requestDto);
		mockResponse.setId(1);

		given(categoryRepository.findByNameIn(any())).willReturn(List.of(category));
		given(systemSettingRepository.existsByKey(anyString())).willReturn(false);
		given(systemSettingRepository.save(any())).willReturn(systemSetting);

		SystemSettingResponseDto result = systemSettingService.createSystemSetting(requestDto);

		// verify
		assertThat(result).usingRecursiveComparison().isEqualTo(mockResponse);
		verify(categoryRepository).findByNameIn(any());
		verify(systemSettingRepository).save(any());
		verify(systemSettingCategoryRepository).saveAll(any());
		verify(systemSettingRepository).existsByKey(anyString());
	}

	@Test
	void canNotCreateSystemSetting() {
		Category category = new Category();
		category.setName("tho");
		category.setDescription("test description");

		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key 1");
		requestDto.setValue("value 1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value 1", "value 2"));
		requestDto.setCategories(List.of("category 1", "category 2"));

		given(categoryRepository.findByNameIn(any())).willReturn(List.of(category));

		assertThatThrownBy(() -> systemSettingService.createSystemSetting(requestDto)).isInstanceOf(
			ManageSystemRequestException.class);

		// verify
		verify(categoryRepository).findByNameIn(any());
		verify(systemSettingRepository, never()).save(any());
		verify(systemSettingCategoryRepository, never()).saveAll(any());
		verify(systemSettingRepository, never()).existsByKey("test");
	}

	@Test
	void canNotCreateSystemSettingWhenRequestBodyInvalid() {
		Category category = new Category();
		category.setName("tho");
		category.setDescription("test description");

		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key 1");
		requestDto.setValue("value 1");
		requestDto.setType("wrong_data_type");
		requestDto.setAllowValues(List.of("value 1", "value 2"));
		requestDto.setCategories(List.of("category 1"));

		given(categoryRepository.findByNameIn(any())).willReturn(List.of(category));

		assertThatThrownBy(() -> systemSettingService.createSystemSetting(requestDto)).isInstanceOf(
			ManageSystemRequestException.class);

		// verify
		verify(categoryRepository).findByNameIn(any());
		verify(systemSettingRepository, never()).save(any());
		verify(systemSettingCategoryRepository, never()).saveAll(any());
		verify(systemSettingRepository, never()).existsByKey("test");
	}

	@Test
	void canNotCreateSystemSettingWhenKeyExisted() {
		Category category = new Category();
		category.setName("category");
		category.setDescription("test description");

		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key 1");
		requestDto.setValue("value 1");
		requestDto.setType("wrong_data_type");
		requestDto.setAllowValues(List.of("value 1", "value 2"));
		requestDto.setCategories(List.of("category 1"));

		given(categoryRepository.findByNameIn(any())).willReturn(List.of(category));
		given(systemSettingRepository.existsByKey(requestDto.getKey())).willReturn(true);

		assertThatThrownBy(() -> systemSettingService.createSystemSetting(requestDto)).isInstanceOf(
			ManageSystemRequestException.class);

		// verify
		verify(categoryRepository).findByNameIn(any());
		verify(systemSettingRepository, never()).save(any());
		verify(systemSettingCategoryRepository, never()).saveAll(any());
		verify(systemSettingRepository).existsByKey(requestDto.getKey());
	}

	@Test
	void canUpdatedSystemSetting() {
		Integer mockId = 1;

		Category category = new Category();
		category.setName("tho");
		category.setDescription("test description");

		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setId(mockId);
		requestDto.setKey("key 1");
		requestDto.setValue("value 1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value 1", "value 2"));
		requestDto.setCategories(List.of("category 1"));

		SystemSetting systemSetting = new SystemSetting(requestDto);

		SystemSettingResponseDto mockResponse = new SystemSettingResponseDto(requestDto);

		given(systemSettingRepository.existsById(mockId)).willReturn(true);
		given(categoryRepository.findByNameIn(any())).willReturn(List.of(category));
		given(systemSettingRepository.save(any())).willReturn(systemSetting);

		SystemSettingResponseDto result = systemSettingService.updatedSystemSetting(mockId, requestDto);

		// verify
		assertThat(result).usingRecursiveComparison().isEqualTo(mockResponse);
		verify(categoryRepository).findByNameIn(any());
		verify(systemSettingRepository).save(any());
		verify(systemSettingCategoryRepository).saveAll(any());
	}

	@Test
	void canNotUpdatedSystemSettingWhenNotExist() {

		Integer mockId = 1;

		given(systemSettingRepository.existsById(mockId)).willReturn(false);

		assertThatThrownBy(() -> systemSettingService.updatedSystemSetting(mockId, any())).isInstanceOf(
			ManageSystemRequestException.class);

		// verify
		verify(categoryRepository, never()).findByNameIn(any());
		verify(systemSettingRepository, never()).save(any());
		verify(systemSettingCategoryRepository, never()).saveAll(any());
	}

	@Test
	void canNotUpdateSystemSettingWhenCategoryListNotEqual() {
		Category category = new Category();
		category.setName("tho");
		category.setDescription("test description");

		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key 1");
		requestDto.setValue("value 1");
		requestDto.setType("string");
		requestDto.setAllowValues(List.of("value 1", "value 2"));
		requestDto.setCategories(List.of("category 1", "category 2"));

		given(systemSettingRepository.existsById(1)).willReturn(true);
		given(categoryRepository.findByNameIn(any())).willReturn(List.of(category));

		assertThatThrownBy(() -> systemSettingService.updatedSystemSetting(1, requestDto)).isInstanceOf(
			ManageSystemRequestException.class);

		// verify
		verify(categoryRepository).findByNameIn(any());
		verify(systemSettingRepository, never()).save(any());
		verify(systemSettingCategoryRepository, never()).saveAll(any());
	}

	@Test
	void canNotUpdateSystemSettingWhenRequestBodyInvalid() {
		Category category = new Category();
		category.setName("tho");
		category.setDescription("test description");

		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key 1");
		requestDto.setValue("value 1");
		requestDto.setType("wrong_data_type");
		requestDto.setAllowValues(List.of("value 1", "value 2"));
		requestDto.setCategories(List.of("category 1"));

		given(systemSettingRepository.existsById(1)).willReturn(true);
		given(categoryRepository.findByNameIn(any())).willReturn(List.of(category));

		assertThatThrownBy(() -> systemSettingService.updatedSystemSetting(1, requestDto)).isInstanceOf(
			ManageSystemRequestException.class);

		// verify
		verify(categoryRepository).findByNameIn(any());
		verify(systemSettingRepository, never()).save(any());
		verify(systemSettingCategoryRepository, never()).saveAll(any());
	}

	@Test
	void canNotUpdateSystemSettingWhenKeyExisted() {
		Category category = new Category();
		category.setName("category");
		category.setDescription("test description");

		SystemSettingRequestDto requestDto = new SystemSettingRequestDto();
		requestDto.setKey("key 1");
		requestDto.setValue("value 1");
		requestDto.setType("wrong_data_type");
		requestDto.setAllowValues(List.of("value 1", "value 2"));
		requestDto.setCategories(List.of("category 1"));

		given(systemSettingRepository.existsById(1)).willReturn(true);
		given(categoryRepository.findByNameIn(any())).willReturn(List.of(category));
		given(systemSettingRepository.existsByKey(requestDto.getKey())).willReturn(true);

		assertThatThrownBy(() -> systemSettingService.updatedSystemSetting(1, requestDto)).isInstanceOf(
			ManageSystemRequestException.class);

		// verify
		verify(categoryRepository).findByNameIn(any());
		verify(systemSettingRepository, never()).save(any());
		verify(systemSettingCategoryRepository, never()).saveAll(any());
		verify(systemSettingRepository).existsByKey(requestDto.getKey());
		verify(systemSettingRepository).existsById(1);
	}

	@Test
	void canDeleteSystemSetting() {
		given(systemSettingRepository.existsById(1)).willReturn(true);

		systemSettingService.deleteSystemSetting(1);

		// verify
		verify(systemSettingRepository).deleteById(any());
	}

	@Test
	void canNotDeleteSystemSetting() {
		given(systemSettingRepository.existsById(1)).willReturn(false);

		assertThatThrownBy(() -> systemSettingService.deleteSystemSetting(1)).isInstanceOf(
			ManageSystemRequestException.class);

		// verify
		verify(systemSettingRepository, never()).deleteById(any());
	}

	@Test
	void canGetAllSystemSettingWhenKeysAndIdsIsNull() {
		Pageable mockPaging = PageRequest.of(0, 5);
		List<String> mockKeys = null;
		List<Integer> mockIds = null;

		systemSettingService.getAllSystemSetting(mockKeys, mockIds, mockPaging);

		// verify
		verify(systemSettingRepository).findAll(mockPaging);
	}

	@Test
	void canGetAllSystemSettingWhenKeysIsNull() {
		Pageable mockPaging = PageRequest.of(0, 5);
		List<String> mockKeys = null;
		List<Integer> mockIds = List.of(1, 2, 3);

		systemSettingService.getAllSystemSetting(mockKeys, mockIds, mockPaging);

		// verify
		verify(systemSettingRepository).findByKeyInAndIdIn(Collections.emptyList(), mockIds, mockPaging);
	}

	@Test
	void canGetAllSystemSettingWhenIdsIsNull() {
		Pageable mockPaging = PageRequest.of(0, 5);
		List<String> mockKeys = List.of("key1", "key2");
		List<Integer> mockIds = null;

		systemSettingService.getAllSystemSetting(mockKeys, mockIds, mockPaging);

		// verify
		verify(systemSettingRepository).findByKeyInAndIdIn(mockKeys, Collections.emptyList(), mockPaging);
	}

	@Test
	void canGetAllSystemSettingWhenIdsAndKeysIsNotNull() {
		Pageable mockPaging = PageRequest.of(0, 5);
		List<String> mockKeys = List.of("key1", "key2");
		List<Integer> mockIds = List.of(1, 2, 3);

		systemSettingService.getAllSystemSetting(mockKeys, mockIds, mockPaging);

		// verify
		verify(systemSettingRepository).findByKeyInAndIdIn(mockKeys, mockIds, mockPaging);
	}
}