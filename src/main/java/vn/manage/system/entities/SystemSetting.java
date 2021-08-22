package vn.manage.system.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import vn.manage.system.enums.DataType;
import vn.manage.system.enums.ErrorCodeEnum;
import vn.manage.system.exception.ManageSystemException;
import vn.manage.system.payload.request.SystemSettingRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "system_setting")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "_key")
    private String key;

    @Column(name = "value")
    private String value;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "allow_override")
    private boolean allowOverride;

    @Column(name = "allow_values")
    private String allowValues;

    public SystemSetting(SystemSettingRequest req) {
        BeanUtils.copyProperties(req, this);
        this.dataType = DataType.valueOf(req.getType().toUpperCase()).getDataType();
        req.getAllowValues().forEach(x -> validationAllowValues(x, req.getType()));
        this.allowValues = String.join(",", req.getAllowValues());
    }

    public boolean validationAllowValues(String value, String type) {
        try {
            switch (DataType.valueOf(type.toUpperCase())) {
                case BOOLEAN:
                    if (value.equals("true") || value.equals("false")) {
                        return true;
                    } else {
                        throw new ManageSystemException(ErrorCodeEnum.WRONG_DATA_TYPE);
                    }
                case FLOAT:
                    Float.valueOf(value);
                    return true;
                case INTEGER:
                    Integer.valueOf(value);
                    return true;
                case DATE:
                    LocalDateTime.parse(value);
                    return true;
                default:
                    return true;
            }
        } catch (Exception e) {
            throw new ManageSystemException(ErrorCodeEnum.WRONG_DATA_TYPE);
        }
    }
}
