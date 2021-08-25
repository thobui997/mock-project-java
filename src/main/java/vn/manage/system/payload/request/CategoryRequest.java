package vn.manage.system.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

  private Integer id;

  @NotBlank
  private String name;

  private String description;

}
