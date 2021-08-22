package vn.manage.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.manage.system.entities.SystemSetting;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting, Integer> {
}
