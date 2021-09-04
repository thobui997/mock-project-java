package vn.manage.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

  @JsonIgnore
  private Integer id;

  @NotBlank(message = "Name is not empty")
  @NotNull(message = "Name is required")
  private String name;

  private String description;

}
