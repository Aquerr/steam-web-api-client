package io.github.aquerr.steamwebapiclient.util;

import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRequest;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TestHttpUtils
{
    public static Map<String, StringValuePattern> toQueryParams(SteamWebApiRequest steamWebApiRequest) {
        Map<String, StringValuePattern> params = new HashMap<>();
        Field[] fields = steamWebApiRequest.getClass().getDeclaredFields();
        for (final Field field : fields) {
            if (!field.isAnnotationPresent(SteamRequestQueryParam.class)) {
                continue;
            }

            String name = getSteamRequestParamNameFromField(field);
            String fieldValue = getFieldValueAsString(steamWebApiRequest, field);
            if (fieldValue != null) {
                params.put(name, new EqualToPattern(fieldValue));
            }
        }
        return params;
    }

    private static String getSteamRequestParamNameFromField(Field field) {
        try {
            SteamRequestQueryParam steamRequestQueryParam = field.getAnnotation(SteamRequestQueryParam.class);
            if (steamRequestQueryParam.value() != null && !steamRequestQueryParam.value().trim().equals("")) {
                return steamRequestQueryParam.value();
            }
            return field.getName();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFieldValueAsString(SteamWebApiRequest steamWebApiRequest, Field field) {
        try {
            field.setAccessible(true);
            Object value = field.get(steamWebApiRequest);
            field.setAccessible(false);
            if (value != null) {
                return String.valueOf(value);
            }
            else return null;
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
