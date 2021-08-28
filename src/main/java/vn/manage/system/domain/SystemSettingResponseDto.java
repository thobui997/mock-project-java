package vn.manage.system.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
public class SystemSettingResponseDto {

    private Integer id;

    private String key;

    private String value;

    private String type;

    private boolean allowOverride;

    private List<String> allowValues;

    private String description;

    private List<String> categories;

    public SystemSettingResponseDto(SystemSettingRequestDto request) {
        BeanUtils.copyProperties(request, this);
    }
}