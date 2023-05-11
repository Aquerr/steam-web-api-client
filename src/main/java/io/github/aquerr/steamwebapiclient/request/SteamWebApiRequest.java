package io.github.aquerr.steamwebapiclient.request;

import io.github.aquerr.steamwebapiclient.annotation.SteamRequestQueryParam;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Base interface for steam web api request.
 */
public interface SteamWebApiRequest
{
    static Map<String, String> toQueryParams(SteamWebApiRequest steamWebApiRequest)
    {
        Map<String, String> params = new HashMap<>();
        Field[] fields = steamWebApiRequest.getClass().getDeclaredFields();
        for (final Field field : fields)
        {
            if (!field.isAnnotationPresent(SteamRequestQueryParam.class))
                continue;

            try
            {
                SteamRequestQueryParam steamRequestQueryParam = field.getAnnotation(SteamRequestQueryParam.class);
                field.setAccessible(true);
                Object value = field.get(steamWebApiRequest);
                field.setAccessible(false);
                params.put(steamRequestQueryParam.name(), String.valueOf(value));
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        return params;
    }
}
