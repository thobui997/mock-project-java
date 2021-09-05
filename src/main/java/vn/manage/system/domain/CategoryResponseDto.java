package vn.manage.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class CategoryResponseDto {

	private Integer id;
	private String name;
	private String description;

	public CategoryResponseDto(CategoryRequestDto request) {
		BeanUtils.copyProperties(request, this);
	}
}
