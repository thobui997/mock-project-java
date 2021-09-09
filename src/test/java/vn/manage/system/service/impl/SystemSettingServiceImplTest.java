package vn.manage.system.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vn.manage.system.exception.ManageSystemRequestException;
import vn.manage.system.repository.CategoryRepository;
import vn.manage.system.repository.SystemSettingCategoryRepository;
import vn.manage.system.repository.SystemSettingRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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
	@Disabled
	void createSystemSetting() {
	}

	@Test
	@Disabled
	void updatedSystemSetting() {
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

		assertThatThrownBy(() -> systemSettingService.deleteSystemSetting(1)).isInstanceOf(ManageSystemRequestException.class);

		// verify
		verify(systemSettingRepository, never()).deleteById(any());
	}

	@Test
	@Disabled
	void getAllSystemSetting() {
	}
}