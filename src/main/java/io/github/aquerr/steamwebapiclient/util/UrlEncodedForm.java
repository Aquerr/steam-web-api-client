package io.github.aquerr.steamwebapiclient.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    private Map<String, String> parameters;

    public static UrlEncodedForm of(final Map<String, String> parameters) {
        return new UrlEncodedForm(new HashMap<>(parameters));
    }

    public String getAsString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final Map.Entry<String, String > entry : parameters.entrySet()) {

            if (stringBuilder.length() > 0) {
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
}
