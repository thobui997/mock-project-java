package vn.manage.system.payload.request;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemSettingRequest {

  private Integer id;

  @Size(max = 1024)
  private String key;

  private String value;

  private String type;

  private boolean allowOverride;

  private List<String> allowValues;

  private String description;

  private List<String> categories;
}
