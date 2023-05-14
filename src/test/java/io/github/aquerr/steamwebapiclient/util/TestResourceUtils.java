package io.github.aquerr.steamwebapiclient.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class TestResourceUtils
{
    public static String loadMockJson(String path) {
        try(InputStream resourceInputStream = TestResourceUtils.class.getClassLoader().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceInputStream)))
        {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
