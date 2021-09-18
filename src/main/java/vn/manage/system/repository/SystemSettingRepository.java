package vn.manage.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.manage.system.models.SystemSetting;

import java.util.List;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting, Integer> {

	Page<SystemSetting> findByKeyInAndIdIn(List<String> keys, List<Integer> ids, Pageable pageable);

	Page<SystemSetting> findByKeyIn(List<String> keys, Pageable pageable);

	Page<SystemSetting> findByIdIn(List<Integer> ids, Pageable pageable);

	boolean existsByKey(String key);

}
