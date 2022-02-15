package uds.samplepanel.base;

import java.net.URL;

public class PathUtil {
    public static String findResourcePath(String fileName) {
        URL url = PathUtil.class.getClassLoader().getResource(fileName);
        if (url == null) throw new RuntimeException("Resource not found: " + fileName);
        return url.getPath();
    }
}
