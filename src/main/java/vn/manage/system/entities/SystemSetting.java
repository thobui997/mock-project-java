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

  @Column(name = "_key", unique = true)
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
    validationValue(req.getValue(), req.getType());
    BeanUtils.copyProperties(req, this);
    this.dataType = DataType.valueOf(req.getType().toUpperCase()).getDataType();
    this.allowValues = String.join(",", req.getAllowValues());
  }

  public void validationValue(String value, String type) {
    try {
      switch (DataType.valueOf(type.toUpperCase())) {
        case BOOLEAN:
          if (value.equals("true") || value.equals("false")) {
            return;
          } else {
            throw new ManageSystemException(ErrorCodeEnum.WRONG_DATA_TYPE);
          }
        case FLOAT:
          Float.valueOf(value);
          return;
        case INTEGER:
          Integer.valueOf(value);
          return;
        case DATE:
          LocalDateTime.parse(value);
          return;
        default:
      }
    } catch (Exception e) {
      throw new ManageSystemException(ErrorCodeEnum.WRONG_DATA_TYPE);
    }
  }
}
