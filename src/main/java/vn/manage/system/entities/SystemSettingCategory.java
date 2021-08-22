package vn.manage.system.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "system_setting_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemSettingCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "system_setting_id")
    private Integer systemSettingId;

    @Column(name = "category_id")
    private Integer categoryId;

    public SystemSettingCategory(Integer systemSettingId, Integer categoryId) {
        this.systemSettingId = systemSettingId;
        this.categoryId = categoryId;
    }
}
