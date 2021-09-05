package vn.manage.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.manage.system.constants.ErrorCodeEnum;
import vn.manage.system.domain.SystemSettingRequestDto;
import vn.manage.system.domain.SystemSettingResponseDto;
import vn.manage.system.exception.ManageSystemRequestException;
import vn.manage.system.models.Category;
import vn.manage.system.models.SystemSetting;
import vn.manage.system.models.SystemSettingCategory;
import vn.manage.system.repository.CategoryRepository;
import vn.manage.system.repository.SystemSettingCategoryRepository;
import vn.manage.system.repository.SystemSettingRepository;
import vn.manage.system.service.SystemSettingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemSettingServiceImpl implements SystemSettingService {

	@Autowired
	private SystemSettingRepository systemSettingRepository;

	@Autowired
	private SystemSettingCategoryRepository systemSettingCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional
	public SystemSettingResponseDto createSystemSetting(SystemSettingRequestDto req) {

		List<Category> categoryList = categoryRepository.findByNameIn(req.getCategories());
		ManageSystemRequestException.assertTrue(categoryList.size() == req.getCategories().size(),
			ErrorCodeEnum.DATA_NOT_FOUND);

		SystemSetting systemSetting = systemSettingRepository.save(new SystemSetting(req));

		systemSettingCategoryRepository.saveAll(categoryList
			.stream()
			.map(x -> new SystemSettingCategory(systemSetting.getId(), x.getId()))
			.collect(Collectors.toList()));
		req.setId(systemSetting.getId());

		return new SystemSettingResponseDto(req);
	}

	@Override
	@Transactional
	public SystemSettingResponseDto updatedSystemSetting(Integer id, SystemSettingRequestDto req) {
		systemSettingRepository
			.findById(id)
			.orElseThrow(ManageSystemRequestException.exception(ErrorCodeEnum.DATA_NOT_FOUND));

		List<Category> categoryList = categoryRepository.findByNameIn(req.getCategories());

		ManageSystemRequestException.assertTrue(categoryList.size() == req.getCategories().size(),
			ErrorCodeEnum.DATA_NOT_FOUND);

		req.setId(id);

		SystemSetting systemSetting = systemSettingRepository.save(new SystemSetting(req));

		systemSettingCategoryRepository.saveAll(categoryList
			.stream()
			.map(x -> new SystemSettingCategory(systemSetting.getId(), x.getId()))
			.collect(Collectors.toList()));

		req.setId(systemSetting.getId());

		return new SystemSettingResponseDto(req);
	}

	@Override
	@Transactional
	public void deleteSystemSetting(Integer id) {

		systemSettingRepository
			.findById(id)
			.orElseThrow(ManageSystemRequestException.exception(ErrorCodeEnum.DATA_NOT_FOUND));

		systemSettingRepository.deleteById(id);

	}

	@Override
	public Page<SystemSetting> getAllSystemSetting(List<String> keys, Pageable paging) {

		Page<SystemSetting> systemSettingPage;

		if(keys == null) {
			systemSettingPage = systemSettingRepository.findAll(paging);
		} else {
			systemSettingPage = systemSettingRepository.findByKeyIn(keys, paging);
		}

		return systemSettingPage;
	}
}
