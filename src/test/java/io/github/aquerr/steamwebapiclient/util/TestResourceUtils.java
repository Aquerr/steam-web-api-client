package io.github.aquerr.steamwebapiclient.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestResourceUtils
{
    public static String loadMockFileContent(String path) {
        try(InputStream resourceInputStream = TestResourceUtils.class.getClassLoader().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceInputStream))) {

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
