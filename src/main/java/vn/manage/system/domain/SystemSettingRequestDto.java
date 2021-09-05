package vn.manage.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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
	@NotBlank(message = "Key is required")
	private String key;

	@NotBlank(message = "Value is required")
	private String value;

	private String type;

	private boolean allowOverride;

	private List<String> allowValues;

	private String description;

	private List<String> categories;
}
