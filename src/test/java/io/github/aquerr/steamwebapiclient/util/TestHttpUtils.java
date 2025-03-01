package io.github.aquerr.steamwebapiclient.util;

import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.github.aquerr.steamwebapiclient.annotation.QueryParamCollectionBehaviour;
import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRequest;

import java.lang.reflect.Field;
import java.util.Collection;
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
            insertParam(params, steamWebApiRequest, field);
        }
        return params;
    }

    private static void insertParam(Map<String, StringValuePattern> params,
                             SteamWebApiRequest steamWebApiRequest,
                             Field field)
    {
        SteamRequestQueryParam steamRequestQueryParam = field.getAnnotation(SteamRequestQueryParam.class);
        String name = getSteamRequestParamNameFromField(steamRequestQueryParam, field);

        // Handle lists
        if (Collection.class.isAssignableFrom(field.getType()) && steamRequestQueryParam.collectionBehaviour() == QueryParamCollectionBehaviour.MULTIPLE_PARAMETERS) {
            Collection<?> collection = getFieldValueAsCollection(steamWebApiRequest, field);
            if (collection != null) {
                int index = 0;
                for (Object object : collection) {
                    String paramName = name + "[" + index + "]";
                    index++;
                    params.put(paramName, new EqualToPattern(String.valueOf(object)));
                }
            }
        } else {
            String fieldValue = getFieldValueAsString(steamWebApiRequest, field);
            if (fieldValue != null) {
                params.put(name, new EqualToPattern(fieldValue));
            }
        }
    }

    private static Collection<?> getFieldValueAsCollection(SteamWebApiRequest steamWebApiRequest, Field field) {
        try {
            boolean canAccess = field.canAccess(steamWebApiRequest);
            if (!canAccess) {
                field.setAccessible(true);
            }
            Object value = field.get(steamWebApiRequest);
            field.setAccessible(canAccess);
            if (value != null) {
                if (value instanceof Collection<?>) {
                    return (Collection<?>) value;
                }
            }
            return null;
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getSteamRequestParamNameFromField(SteamRequestQueryParam steamRequestQueryParam, Field field) {
        try {
            if (steamRequestQueryParam.value() != null && !steamRequestQueryParam.value().trim().isEmpty()) {
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
