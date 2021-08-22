package vn.manage.system.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ObjectUtils {

    /**
     * check if all string input have text
     * 
     * @param input string array to validate having text
     * @return true if all input have text
     */
    public static boolean allHasText(@Nullable String... input) {
        if (Objects.isNull(input)) {
            return false;
        }
        return Arrays.stream(input).allMatch(value -> StringUtils.hasText(value));
    }

    /**
     * check if input list is null or empty
     * 
     * @param input string array to validate having text
     * @return empty list if input is null or empty <br>
     *         origin list if input has element
     */
    public static <T> List<T> nvl(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list;
    }

}
