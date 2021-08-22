package vn.manage.system.utils;

import java.util.List;
import java.util.Objects;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonUtils {

    private static Gson gson;

    /**
     * get gson instance
     * 
     * @return gson instance
     */
    public static Gson getGson() {
        if (Objects.isNull(gson)) {
            gson = new GsonBuilder().disableHtmlEscaping().setLenient().create();
        }
        return gson;
    }

    /**
     * convert a jsonString into java class
     * 
     * @param jsonString a string with json format
     * @param clazz target class to convert jsonString
     * @return instance of target class
     */
    public static <T> T convert(String jsonString, Class<T> clazz) {
        return getGson().fromJson(jsonString, clazz);
    }

    /**
     * convert a java object into string with json format
     * 
     * @param object object to convert to jsonString
     * @return string with json format
     */
    public static String convert(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        return getGson().toJson(object);
    }

    /**
     * convert a jsonString into java {@link List}
     * 
     * @param jsonString a string with json format
     * @return instance of {@link List}
     */
    public static <T> List<T> convert(String jsonString) {
        return getGson().fromJson(jsonString, new TypeToken<List<T>>() {}.getType());
    }

}
