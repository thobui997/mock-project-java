package vn.manage.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemSettingRequestDto {

  @JsonIgnore
  private Integer id;

  @Size(max = 1024)
  @NotNull(message = "Key is required")
  private String key;

  @NotNull(message = "Value is required")
  private String value;

  private String type;

  private boolean allowOverride;

  private List<String> allowValues;

  private String description;

  private List<String> categories;
}
