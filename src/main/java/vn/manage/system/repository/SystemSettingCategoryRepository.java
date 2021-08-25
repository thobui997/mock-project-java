package vn.manage.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.manage.system.entities.SystemSettingCategory;

import java.util.Optional;

@Repository
public interface SystemSettingCategoryRepository extends JpaRepository<SystemSettingCategory, Integer> {

  Optional<SystemSettingCategory> findSystemSettingCategoryByCategoryId(Integer categoryId);

  Optional<SystemSettingCategory> findSystemSettingCategoryBySystemSettingId(Integer settingId);
}
