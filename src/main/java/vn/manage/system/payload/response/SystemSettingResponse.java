package vn.manage.system.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import vn.manage.system.payload.request.SystemSettingRequest;

import java.util.List;

@Getter
@Setter
public class SystemSettingResponse {

    private Integer id;

    private String key;

    private String value;

    private String type;

    private boolean allowOverride;

    private List<String> allowValues;

    private String description;

    private List<String> categories;

    public SystemSettingResponse(SystemSettingRequest request) {
        BeanUtils.copyProperties(request, this);
    }
}
