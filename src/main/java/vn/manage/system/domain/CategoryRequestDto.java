package vn.manage.system.domain;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

  @JsonIgnore
  private Integer id;

  @NotBlank
  private String name;

  private String description;

}
