package io.github.aquerr.steamwebapiclient.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.aquerr.steamwebapiclient.request.SteamWebApiRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class UrlEncodedForm
{
    private final Map<String, String> parameters;

    /**
     * Constructs a url encoded form from request attributes marked with @JsonProperty
     * @param request the request
     * @return UrlEncodedForm
     */
    public static UrlEncodedForm of(final SteamWebApiRequest request) {
        try
        {
            return new UrlEncodedForm(retrieveParameters(request));
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static UrlEncodedForm of(final Map<String, String> parameters) {
        return new UrlEncodedForm(new HashMap<>(parameters));
    }

    public String getAsString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Map.Entry<String, String > entry : parameters.entrySet()) {

            if (!stringBuilder.isEmpty()) {
                stringBuilder.append("&");
            }
            stringBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return stringBuilder.toString();
    }

    public Map<String, String> getParameters()
    {
        return Collections.unmodifiableMap(this.parameters);
    }

    public byte[] getAsByteArray()
    {
        return getAsString().getBytes(StandardCharsets.UTF_8);
    }

    private static Map<String, String> retrieveParameters(SteamWebApiRequest request) throws IllegalAccessException {

        Map<String, String> parameters = new HashMap<>();
        Field[] fields = request.getClass().getDeclaredFields();
        for (final Field field : fields) {

            if (field.isAnnotationPresent(JsonProperty.class)) {

                field.setAccessible(true);
                parameters.put(field.getAnnotation(JsonProperty.class).value(), String.valueOf(field.get(request)));
                field.setAccessible(false);
            }
        }

        return parameters;
    }
}
