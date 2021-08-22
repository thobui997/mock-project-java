package vn.manage.system.utils;

import java.util.Objects;

public class NumberUtils {

    /**
     * check if input Long value is null
     * 
     * @param input Long value
     * @return 0 if input is null <br>
     *         origin value if input is not null
     */
    public static Long nvl(Long input) {
        return Objects.isNull(input) ? 0 : input;
    }

    /**
     * check if input Integer value is null
     * 
     * @param input Integer value
     * @return 0 if input is null <br>
     *         origin value if input is not null
     */
    public static Integer nvl(Integer input) {
        return Objects.isNull(input) ? 0 : input;
    }

    /**
     * check if input Double value is null
     * 
     * @param input Double value
     * @return 0 if input is null <br>
     *         origin value if input is not null
     */
    public static Double nvl(Double input) {
        return Objects.isNull(input) ? 0 : input;
    }

    /**
     * check if input Float value is null
     * 
     * @param input Float value
     * @return 0 if input is null <br>
     *         origin value if input is not null
     */
    public static Float nvl(Float input) {
        return Objects.isNull(input) ? 0 : input;
    }

}
