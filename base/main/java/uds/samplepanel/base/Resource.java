package uds.samplepanel.base;

import java.net.URL;

public class Resource {
    private final String fileName;

    public Resource(String fileName) {
        this.fileName = fileName;
    }

    public String findPath() {
        URL url = Resource.class.getClassLoader().getResource(fileName);
        if (url == null) throw new RuntimeException("Resource not found: " + fileName);
        return url.getPath();
    }
}
