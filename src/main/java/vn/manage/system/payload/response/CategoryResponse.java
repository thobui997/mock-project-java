package vn.manage.system.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import vn.manage.system.payload.request.CategoryRequest;

@Getter
@Setter
public class CategoryResponse {

  private Integer id;
  private String name;
  private String description;

  public CategoryResponse(CategoryRequest request) {
    BeanUtils.copyProperties(request, this);
  }
}
