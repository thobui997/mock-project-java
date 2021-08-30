package vn.manage.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.manage.system.models.SystemSetting;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting, Integer> {

  Page<SystemSetting> findByKeyContaining(String key, Pageable pageable);

  Page<SystemSetting> findByIdContaining(Integer id, Pageable pageable);
}
